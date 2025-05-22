package com.nequi.challenge.contexts.franchise.domain.repositories;

import com.nequi.challenge.contexts.franchise.domain.model.Inventory;

import java.util.List;

public interface IInventoryRepository {
   Inventory addProduct(Inventory inventory);
   void updateStock(Inventory inventory);
   List<Inventory> listByBranchOffice(Long branchOfficeId);
}
