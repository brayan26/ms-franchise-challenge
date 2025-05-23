package com.nequi.challenge.contexts.franchise.infrastructure.config;

import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.FranchiseRequestDto;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.InventoryRequestDto;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.ProductRequestDto;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.handlers.InventoryHandler;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.handlers.ProductHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class InventoryRouterConfig {
   @Bean
   @RouterOperations({
         @RouterOperation(
               path = "/inventory/addProduct",
               produces = {MediaType.APPLICATION_JSON_VALUE},
               method = RequestMethod.POST,
               beanClass = InventoryHandler.class,
               beanMethod = "addProduct",
               operation = @Operation(
                     operationId = "addProduct",
                     summary = "Add a new product to inventory",
                     requestBody = @RequestBody(
                           required = true,
                           content = @Content(
                                 schema = @Schema(implementation = InventoryRequestDto.class)
                           )
                     ),
                     responses = {
                           @ApiResponse(responseCode = "201", description = "Product added to inventory"),
                     }
               )
         ),
         @RouterOperation(
               path = "/inventory/stock/update",
               produces = {MediaType.APPLICATION_JSON_VALUE},
               method = RequestMethod.PATCH,
               beanClass = InventoryHandler.class,
               beanMethod = "updateStock",
               operation = @Operation(
                     operationId = "updateStock",
                     summary = "Update stock of a product",
                     requestBody = @RequestBody(
                           required = true,
                           content = @Content(
                                 schema = @Schema(implementation = InventoryRequestDto.class)
                           )
                     ),
                     responses = {
                           @ApiResponse(responseCode = "200", description = "Stock updated"),
                           @ApiResponse(responseCode = "404", description = "Product or branch office id not found"),
                     }
               )
         ),
         @RouterOperation(
               path = "/inventory/{branchOfficeId}/find",
               produces = {MediaType.APPLICATION_JSON_VALUE},
               method = RequestMethod.GET,
               beanClass = InventoryHandler.class,
               beanMethod = "findByBranchOfficeId",
               operation = @Operation(
                     operationId = "findByBranchOfficeId",
                     summary = "Get inventory of a branchOffice",
                     parameters = {
                           @Parameter(
                                 name = "branchOfficeId",
                                 in = ParameterIn.PATH,
                                 required = true,
                                 description = "BranchOffice id"
                           ),
                     },

                     responses = {
                           @ApiResponse(responseCode = "200", description = "Inventory fetched"),
                     }
               )
         ),
         @RouterOperation(
               path = "/inventory/{franchiseId}/topStopProductPerBranchOfficeOfFranchise",
               produces = {MediaType.APPLICATION_JSON_VALUE},
               method = RequestMethod.GET,
               beanClass = InventoryHandler.class,
               beanMethod = "topStopProductPerBranchOfficeOfFranchise",
               operation = @Operation(
                     operationId = "topStopProductPerBranchOfficeOfFranchise",
                     summary = "Top stop product per branchOffice of franchise",
                     parameters = {
                           @Parameter(
                                 name = "franchiseId",
                                 in = ParameterIn.PATH,
                                 required = true,
                                 description = "Franchise id"
                           ),
                     },
                     responses = {
                           @ApiResponse(responseCode = "200", description = "Sorted inventory"),
                     }
               )
         )
   })
   public RouterFunction<ServerResponse> routerInventoryFunction(InventoryHandler inventoryHandler) {
      return RouterFunctions.route()
            .POST("/inventory/addProduct", inventoryHandler::addProduct)
            .PATCH("/inventory/stock/update", inventoryHandler::updateStock)
            .GET("/inventory/{branchOfficeId}/find", inventoryHandler::findByBranchOfficeId)
            .GET("/inventory/{franchiseId}/topStopProductPerBranchOfficeOfFranchise", inventoryHandler::topStopProductPerBranchOfficeOfFranchise)
            .build();
   }
}
