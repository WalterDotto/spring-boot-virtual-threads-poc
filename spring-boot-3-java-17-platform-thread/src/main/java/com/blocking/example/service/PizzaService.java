package com.blocking.example.service;

import org.springframework.stereotype.Service;
import com.blocking.example.model.Pizza;
import com.blocking.example.model.ThreadStage;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PizzaService {

    public Pizza cookAndSlicePizza() {
        return this.slice(cookPizza());
    }

    private Pizza cookPizza() {
        ThreadStage firstStage = createThreadStage("Starting to cook");

        simulateTimeConsumingTask(1000,"Starting to cook");

        ThreadStage secondStage = createThreadStage("Cooking pizza");

        Pizza pizza = new Pizza();
        pizza.addStage(firstStage);
        pizza.addStage(secondStage);

        log.info("Pizza {} successfully cooked", pizza.getId());
        return pizza;
    }

    private Pizza slice(Pizza pizza) {

            simulateTimeConsumingTask(100,"Starting to slice pizza");
            ThreadStage thirdStage = createThreadStage("Slicing pizza");

            pizza.slice();
            log.info("Pizza {} successfully sliced", pizza.getId());

            pizza.addStage(thirdStage);
            return pizza;
    }

    private ThreadStage createThreadStage(String stageName) {
        Thread current = Thread.currentThread();

        return ThreadStage.builder()
            .stage(stageName)
            .threadName(current.toString())
            .build();
    }

    private void simulateTimeConsumingTask(int milliseconds,String taskDescription) {
        try {
            log.info(taskDescription);
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            log.info("InterruptedException, {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

}
