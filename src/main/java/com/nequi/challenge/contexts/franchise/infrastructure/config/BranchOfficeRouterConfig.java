package com.nequi.challenge.contexts.franchise.infrastructure.config;

import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.BranchOfficeRequestDto;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.handlers.BranchOfficeHandler;
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
public class BranchOfficeRouterConfig {

   @Bean
   @RouterOperations({
         @RouterOperation(
               path = "/branch_office/create",
               produces = {MediaType.APPLICATION_JSON_VALUE},
               method = RequestMethod.POST,
               beanClass = BranchOfficeHandler.class,
               beanMethod = "create",
               operation = @Operation(
                     requestBody = @RequestBody(
                           required = true,
                           content = @Content(
                                 schema = @Schema(implementation = BranchOfficeRequestDto.class)
                           )
                     ),
                     responses = {
                           @ApiResponse(responseCode = "201", description = "Branch office created"),
                     }
               )
         ),
         @RouterOperation(
               path = "/branch_office/{id}/update",
               produces = {MediaType.APPLICATION_JSON_VALUE},
               method = RequestMethod.PATCH,
               beanClass = BranchOfficeHandler.class,
               beanMethod = "update",
               operation = @Operation(
                     operationId = "update",
                     summary = "Update branch office",
                     parameters = {
                           @Parameter(
                                 name = "id",
                                 in = ParameterIn.PATH,
                                 required = true,
                                 description = "Branch office id"
                           ),
                     },
                     requestBody = @RequestBody(
                           required = true,
                           content = @Content(
                                 schema = @Schema(implementation = BranchOfficeRequestDto.class)
                           )
                     ),
                     responses = {
                           @ApiResponse(responseCode = "200", description = "Branch office updated"),
                           @ApiResponse(responseCode = "404", description = "Branch office id not found"),
                           @ApiResponse(responseCode = "400", description = "invalid request body")
                     }
               )
         ),
         @RouterOperation(
               path = "/branch_office/{franchiseId}/findAll",
               produces = {MediaType.APPLICATION_JSON_VALUE},
               method = RequestMethod.GET,
               beanClass = BranchOfficeHandler.class,
               beanMethod = "findByFranchiseId",
               operation = @Operation(
                     operationId = "findByFranchiseId",
                     summary = "Get all branch office by franchise id",
                     parameters = {
                           @Parameter(
                                 name = "franchiseId",
                                 in = ParameterIn.PATH,
                                 required = true,
                                 description = "franchise id"
                           )
                     }
               )
         )
   })
   public RouterFunction<ServerResponse> routerFunction(BranchOfficeHandler branchOfficeHandler) {
      return RouterFunctions.route()
            .POST("/branch_office/create", branchOfficeHandler::create)
            .PATCH("/branch_office/{id}/update", branchOfficeHandler::update)
            .GET("/branch_office/{franchiseId}/findAll", branchOfficeHandler::findByFranchiseId)
            .build();
   }
}
