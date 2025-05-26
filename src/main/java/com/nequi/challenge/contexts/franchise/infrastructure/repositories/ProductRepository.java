package com.nequi.challenge.contexts.franchise.infrastructure.repositories;

import com.nequi.challenge.contexts.franchise.domain.model.Product;
import com.nequi.challenge.contexts.franchise.domain.repositories.IProductRepository;
import com.nequi.challenge.contexts.franchise.infrastructure.mappers.ProductMapper;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.ProductDocument;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories.MongoProductRepository;
import com.nequi.challenge.contexts.shared.domain.constants.ErrorMessages;
import com.nequi.challenge.contexts.shared.domain.exceptions.GenericNotFoundException;
import com.nequi.challenge.contexts.shared.infrastructure.util.BuildErrorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ProductRepository implements IProductRepository {
   private final MongoProductRepository repository;
   private final InventoryRepository inventoryRepository;
   private final ProductMapper mapper;
   private final TransactionalOperator transactionalOperator;

   @Override
   public Mono<Product> create(Product product) {
      ProductDocument document = this.mapper.toEntity(product);
      document.setCreated_at(LocalDateTime.now());
      return this.repository.save(document).map(this.mapper::toDomain);
   }

   @Override
   public Mono<Product> update(String id, Product product) {
      return this.findOne(id)
            .map(domain -> {
               ProductDocument document = this.mapper.toEntity(domain);
               if (product.name() != null) {
                  document.setName(product.name());
                  document.setUpdated_at(LocalDateTime.now());
               }
               return document;
            })
            .flatMap(this.repository::save)
            .map(this.mapper::toDomain);
   }

   @Override
   public Mono<Product> findOne(String id) {
      return this.repository.findById(id).switchIfEmpty(
            Mono.error(new GenericNotFoundException(String.format("Product id %s not found in the database", id),
                  BuildErrorUtil.create(ErrorMessages.PRODUCT_NOT_FOUND_CODE, ErrorMessages.PRODUCT_NOT_FOUND_DESCRIPTION))
            )
      ).map(this.mapper::toDomain);
   }

   @Override
   public Mono<Void> deleteProduct(String id) {
      return findOne(id).flatMap(product ->
            inventoryRepository.deleteProductWithInventory(id)
            .then(repository.deleteById(id))
            .as(transactionalOperator::transactional));
   }
}
