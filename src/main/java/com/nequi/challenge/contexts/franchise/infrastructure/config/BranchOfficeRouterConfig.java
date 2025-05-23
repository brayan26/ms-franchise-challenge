package com.nequi.challenge.contexts.franchise.infrastructure.config;

import com.nequi.challenge.contexts.franchise.infrastructure.adapters.handlers.BranchOfficeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class BranchOfficeRouterConfig {

   @Bean
   public RouterFunction<ServerResponse> routerFunction(BranchOfficeHandler branchOfficeHandler) {
      return RouterFunctions.route()
            .POST("/branch_office/create", branchOfficeHandler::create)
            .PATCH("/branch_office/{id}/update", branchOfficeHandler::update)
            .GET("/branch_office/findByFranchiseId/{franchiseId}", branchOfficeHandler::findByFranchiseId)
            .build();
   }
}
