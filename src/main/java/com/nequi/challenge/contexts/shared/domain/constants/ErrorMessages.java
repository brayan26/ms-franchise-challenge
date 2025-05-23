package com.nequi.challenge.contexts.shared.domain.constants;

public class ErrorMessages {
   public static final String UNKNOW_ERROR_DESCRIPTION = "Unknow error, please contact to technical support";
   //Franchise
   public static final String FRANCHISE_NOT_FOUND_CODE = "FCS-001";
   public static final String FRANCHISE_NOT_FOUND_DESCRIPTION = "Franchise id %s not found";
   //Branch Office
   public static final String BRANCHOFFICE_NOT_FOUND_CODE = "BRC-001";
   public static final String BRANCHOFFICE_NOT_FOUND_DESCRIPTION = "Branch office id not found";
   //Products
   public static final String PRODUCT_NOT_FOUND_CODE = "PRD-001";
   public static final String PRODUCT_NOT_FOUND_DESCRIPTION = "Product id not found";
   //Inventory
   public static final String INVENTORY_GENERIC_EXIST_CODE = "INV-000";
   public static final String INVENTORY_GENERIC_EXIST_DESCRIPTION = "Product or branch office id not found";
   public static final String INVENTORY_ALREADY_EXIST_CODE = "INV-001";
   public static final String INVENTORY_ALREADY_EXIST_DESCRIPTION = "The product already exists in this branch office inventory";
   public static final String INVENTORY_NOT_FOUND_CODE = "INV-002";
   public static final String INVENTORY_NOT_FOUND_DESCRIPTION = "The product does not exist in the inventory of this branch";
}
