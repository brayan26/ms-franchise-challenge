package com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories;

import com.nequi.challenge.contexts.franchise.domain.model.Inventory;
import com.nequi.challenge.contexts.franchise.domain.repositories.IInventoryRepository;
import com.nequi.challenge.contexts.franchise.infrastructure.mappers.InventoryMapper;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.BranchOfficeDocument;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.InventoryDocument;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.ProductDocument;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories.mongo.MongoBranchOfficeRepository;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories.mongo.MongoInventoryRepository;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories.mongo.MongoProductRepository;
import com.nequi.challenge.contexts.shared.domain.constants.ErrorMessages;
import com.nequi.challenge.contexts.shared.domain.exceptions.GenericBadRequestException;
import com.nequi.challenge.contexts.shared.domain.exceptions.GenericNotFoundException;
import com.nequi.challenge.contexts.shared.infrastructure.util.BuildErrorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Comparator;

@Repository
@RequiredArgsConstructor
public class InventoryRepository implements IInventoryRepository {
   private final MongoInventoryRepository repository;
   private final MongoBranchOfficeRepository branchOfficeRepository;
   private final MongoProductRepository productRepository;
   private final InventoryMapper mapper;

   @Override
   public Mono<Inventory> addProduct(Inventory inventory) {
      productRepository.findById(inventory.productId()).switchIfEmpty(
            Mono.error(new GenericBadRequestException(
                  String.format("Product id <%s> doesn't exists", inventory.productId()),
                  BuildErrorUtil.create(ErrorMessages.PRODUCT_NOT_FOUND_CODE, ErrorMessages.PRODUCT_NOT_FOUND_DESCRIPTION)
            ))
      );
      Mono<BranchOfficeDocument> branchOfficeMono = branchOfficeRepository.findById(inventory.branchOfficeId()).switchIfEmpty(
            Mono.error(new GenericBadRequestException(
                  String.format("Branch office id <%s> doesn't exists", inventory.branchOfficeId()),
                  BuildErrorUtil.create(ErrorMessages.BRANCHOFFICE_NOT_FOUND_CODE, ErrorMessages.BRANCHOFFICE_NOT_FOUND_DESCRIPTION)
            ))
      );

      return this.repository.existsByBranchOfficeIdAndProductId(
                  inventory.branchOfficeId(), inventory.productId())
            .flatMap(exists -> {
               if (exists) {
                  return Mono.error(new GenericBadRequestException(
                        String.format("A product already exists for the branch office with id %s", inventory.branchOfficeId()),
                        BuildErrorUtil.create(ErrorMessages.INVENTORY_ALREADY_EXIST_CODE, ErrorMessages.INVENTORY_ALREADY_EXIST_DESCRIPTION)
                  ));
               }
               InventoryDocument document = this.mapper.toEntity(inventory);
               document.setCreated_at(LocalDateTime.now());
               return this.repository.save(document);
            })
            .map(this.mapper::toDomain);
   }

   @Override
   public Mono<Void> updateStock(Inventory inventory) {
      return this.repository.findByBranchOfficeIdAndProductId(
                  inventory.branchOfficeId(), inventory.productId())
            .switchIfEmpty(Mono.error(new GenericNotFoundException(
                  String.format("The product <%s> or branch office id <%s> does not exist in the inventory of this branch office", inventory.productId(), inventory.branchOfficeId()),
                  BuildErrorUtil.create(ErrorMessages.INVENTORY_NOT_FOUND_CODE, ErrorMessages.INVENTORY_NOT_FOUND_DESCRIPTION)
            )))
            .flatMap(document -> {
               if (inventory.productPrice() != null) document.setPrice(inventory.productPrice());
               document.setStock(inventory.stock());
               document.setUpdated_at(LocalDateTime.now());
               return this.repository.save(document);
            })
            .then();
   }

   @Override
   public Flux<Inventory> getTopStockProductPerBranch(String franchiseId) {
      return this.branchOfficeRepository.findAllByFranchiseId(franchiseId)
            .flatMap(branch -> repository.findByBranchOfficeId(branch.getId())
                  .sort(Comparator.comparingInt(InventoryDocument::getStock).reversed()) // sort in-memory
                  .take(1) // top stock product
                  .flatMap(inv -> productRepository.findById(inv.getProductId())
                        .map(product -> Inventory.builder()
                              .id(inv.getId())
                              .branchOfficeId(branch.getId())
                              .branchOfficeName(branch.getName())
                              .productId(product.getId())
                              .productName(product.getName())
                              .productPrice(inv.getPrice())
                              .stock(inv.getStock())
                              .created_at(inv.getCreated_at())
                              .updated_at(inv.getUpdated_at())
                              .build())
                  )
            );
   }

   @Override
   public Flux<Inventory> getInventoryByBranchOffice(String branchOfficeId) {
      return this.repository.findByBranchOfficeId(branchOfficeId)
            .map(this.mapper::toDomain);
   }
}
