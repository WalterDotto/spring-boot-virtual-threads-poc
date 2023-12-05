package com.virtual.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.virtual.example.model.Pizza;
import com.virtual.example.service.PizzaService;
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

    @PostMapping("/pizza-virtual")
    public ResponseEntity<Pizza> processPizzaOrder() {

        Pizza result = pizzaService.cookAndSlicePizza();
        log.info("{}", result);

        return ResponseEntity.status(HttpStatus.OK)
            .body(result);
    }
}