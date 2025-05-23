package com.nequi.challenge.contexts.franchise.infrastructure.config;

import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.FranchiseRequestDto;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.handlers.FranchiseHandler;
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
public class FranchiseRouterConfig {
   @Bean
   @RouterOperations({
         @RouterOperation(
               path = "/franchise/create",
               produces = {MediaType.APPLICATION_JSON_VALUE},
               method = RequestMethod.POST,
               beanClass = FranchiseRequestDto.class,
               beanMethod = "create",
               operation = @Operation(
                     requestBody = @RequestBody(
                           required = true,
                           content = @Content(
                                 schema = @Schema(implementation = FranchiseRequestDto.class)
                           )
                     ),
                     responses = {
                           @ApiResponse(responseCode = "201", description = "Franchise created"),
                     }
               )
         ),
         @RouterOperation(
               path = "/franchise/{id}/update",
               produces = {MediaType.APPLICATION_JSON_VALUE},
               method = RequestMethod.POST,
               beanClass = FranchiseRequestDto.class,
               beanMethod = "update",
               operation = @Operation(
                     parameters = {
                           @Parameter(
                                 name = "id",
                                 in = ParameterIn.PATH,
                                 required = true,
                                 description = "Franchise id"
                           ),
                     },
                     requestBody = @RequestBody(
                           required = true,
                           content = @Content(
                                 schema = @Schema(implementation = FranchiseRequestDto.class)
                           )
                     ),
                     responses = {
                           @ApiResponse(responseCode = "200", description = "Franchise updated"),
                           @ApiResponse(responseCode = "404", description = "Franchise id not found"),
                     }
               )
         ),
         @RouterOperation(
               path = "/franchise/{id}/findOne",
               produces = {MediaType.APPLICATION_JSON_VALUE},
               method = RequestMethod.GET,
               beanClass = FranchiseRequestDto.class,
               beanMethod = "findOne",
               operation = @Operation(
                     parameters = {
                           @Parameter(
                                 name = "id",
                                 in = ParameterIn.PATH,
                                 required = true,
                                 description = "Franchise id"
                           ),
                     },

                     responses = {
                           @ApiResponse(responseCode = "200", description = "Franchise fetched"),
                           @ApiResponse(responseCode = "404", description = "Franchise id not found"),
                     }
               )
         ),
         @RouterOperation(
               path = "/franchise/{id}/update",
               produces = {MediaType.APPLICATION_JSON_VALUE},
               method = RequestMethod.POST,
               beanClass = FranchiseRequestDto.class,
               beanMethod = "findAll",
               operation = @Operation(
                     responses = {
                           @ApiResponse(responseCode = "200", description = "Franchise fetched"),
                     }
               )
         ),
   })
   public RouterFunction<ServerResponse> routerFunction(FranchiseHandler franchiseHandler) {
      return RouterFunctions.route()
            .POST("/franchise/create", franchiseHandler::create)
            .PATCH("/franchise/{id}/update", franchiseHandler::update)
            .GET("/franchise/{id}/findOne", franchiseHandler::findById)
            .GET("/franchise/findAll", franchiseHandler::findAll)
            .build();
   }
}
