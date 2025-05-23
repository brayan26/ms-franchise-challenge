package com.nequi.challenge.contexts.franchise.infrastructure.config;

import com.nequi.challenge.contexts.franchise.infrastructure.adapters.handlers.FranchiseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class FranchiseRouterConfig {
   @Bean
   public RouterFunction<ServerResponse> routerFunction(FranchiseHandler franchiseHandler) {
      return RouterFunctions.route()
            .POST("/franchise/create", franchiseHandler::create)
            .PATCH("/franchise/{id}/update", franchiseHandler::update)
            .GET("/franchise/{id}/findOne", franchiseHandler::findById)
            .GET("/franchise/findAll", franchiseHandler::findAll)
            .build();
   }
}
