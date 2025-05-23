package com.nequi.challenge.contexts.franchise.infrastructure.config;

import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.FranchiseRequestDto;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.ProductRequestDto;
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
public class ProductRouterConfig {
   @Bean
   @RouterOperations({
         @RouterOperation(
               path = "/product/create",
               produces = {MediaType.APPLICATION_JSON_VALUE},
               method = RequestMethod.POST,
               beanClass = ProductHandler.class,
               beanMethod = "create",
               operation = @Operation(
                     operationId = "create",
                     summary = "Create a new product",
                     requestBody = @RequestBody(
                           required = true,
                           content = @Content(
                                 schema = @Schema(implementation = ProductRequestDto.class)
                           )
                     ),
                     responses = {
                           @ApiResponse(responseCode = "201", description = "Product created"),
                     }
               )
         ),
         @RouterOperation(
               path = "/product/{id}/update",
               produces = {MediaType.APPLICATION_JSON_VALUE},
               method = RequestMethod.PATCH,
               beanClass = ProductHandler.class,
               beanMethod = "update",
               operation = @Operation(
                     operationId = "update",
                     summary = "Update product",
                     parameters = {
                           @Parameter(
                                 name = "id",
                                 in = ParameterIn.PATH,
                                 required = true,
                                 description = "Product id"
                           ),
                     },
                     requestBody = @RequestBody(
                           required = true,
                           content = @Content(
                                 schema = @Schema(implementation = FranchiseRequestDto.class)
                           )
                     ),
                     responses = {
                           @ApiResponse(responseCode = "200", description = "Product updated"),
                           @ApiResponse(responseCode = "404", description = "Product id not found"),
                     }
               )
         ),
         @RouterOperation(
               path = "/product/{id}/findOne",
               produces = {MediaType.APPLICATION_JSON_VALUE},
               method = RequestMethod.GET,
               beanClass = ProductHandler.class,
               beanMethod = "findById",
               operation = @Operation(
                     operationId = "findOne",
                     summary = "Get a product by id",
                     parameters = {
                           @Parameter(
                                 name = "id",
                                 in = ParameterIn.PATH,
                                 required = true,
                                 description = "Product id"
                           ),
                     },

                     responses = {
                           @ApiResponse(responseCode = "200", description = "Product fetched"),
                           @ApiResponse(responseCode = "404", description = "Product id not found"),
                     }
               )
         ),
         @RouterOperation(
               path = "/product/{id}",
               produces = {MediaType.APPLICATION_JSON_VALUE},
               method = RequestMethod.DELETE,
               beanClass = ProductHandler.class,
               beanMethod = "delete",
               operation = @Operation(
                     operationId = "delete",
                     summary = "Delete a product by id",
                     parameters = {
                           @Parameter(
                                 name = "id",
                                 in = ParameterIn.PATH,
                                 required = true,
                                 description = "Product id"
                           ),
                     },
                     responses = {
                           @ApiResponse(responseCode = "204", description = "Product deleted"),
                           @ApiResponse(responseCode = "404", description = "Product id not found"),
                     }
               )
         )
   })
   public RouterFunction<ServerResponse> routerProductFunction(ProductHandler productHandler) {
      return RouterFunctions.route()
            .POST("/product/create", productHandler::create)
            .PATCH("/product/{id}/update", productHandler::update)
            .GET("/product/{id}/findOne", productHandler::findById)
            .DELETE("/product/{id}", productHandler::delete)
            .build();
   }
}
