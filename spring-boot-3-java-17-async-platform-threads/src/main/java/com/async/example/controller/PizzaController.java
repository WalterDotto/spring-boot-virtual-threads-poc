package com.async.example.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.async.example.model.Pizza;
import com.async.example.service.PizzaService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/api")
@Slf4j
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(final PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @PostMapping("/pizza-async")
    public CompletableFuture<ResponseEntity<Pizza>> processPizzaOrder() {

        CompletableFuture<Pizza> pizzaCompletableFuture = pizzaService.cookPizza().thenCompose(pizzaService::slice);

        log.info("Waiting for pizza to be cooked and sliced...");

        return pizzaCompletableFuture.thenApply(result -> {
            log.info("{}", result);
            return ResponseEntity.status(HttpStatus.OK).body(result);
            }
        );
    }
}