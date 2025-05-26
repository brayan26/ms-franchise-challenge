package com.nequi.challenge.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;

@Configuration
public class MongoConfig {
   @Bean
   public ReactiveTransactionManager transactionManager(ReactiveMongoDatabaseFactory dbFactory) {
      return new ReactiveMongoTransactionManager(dbFactory);
   }

   @Bean
   public TransactionalOperator transactionalOperator(ReactiveTransactionManager txManager) {
      return TransactionalOperator.create(txManager);
   }
}
