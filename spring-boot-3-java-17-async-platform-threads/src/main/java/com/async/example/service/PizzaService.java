package com.async.example.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.async.example.model.Pizza;
import com.async.example.model.ThreadStage;
import lombok.extern.slf4j.Slf4j;
import static java.lang.String.format;

@Service
@Slf4j
public class PizzaService {

    @Async("asyncCustomTaskExecutor")
    public CompletableFuture<Pizza> cookPizza() {

        ThreadStage firstStage = createThreadStage("Starting to cook");

        simulateTimeConsumingTask(1000, "Starting to cook");
        ThreadStage secondStage = createThreadStage("Cooking pizza");

        Pizza pizza = new Pizza();
        pizza.addStage(firstStage);
        pizza.addStage(secondStage);

        log.info("Pizza {} successfully cooked", pizza.getId());
        return CompletableFuture.completedFuture(pizza);
    }

    @Async("asyncCustomTaskExecutor")
    public CompletableFuture<Pizza> slice(Pizza pizza) {

        simulateTimeConsumingTask(100, "Starting to slice pizza");
        ThreadStage thirdStage = createThreadStage("Slicing pizza");

        pizza.slice();
        log.info("Pizza {} successfully sliced", pizza.getId());

        pizza.addStage(thirdStage);
        return CompletableFuture.completedFuture(pizza);

    }

    private ThreadStage createThreadStage(String stageName) {
        Thread current = Thread.currentThread();

        return ThreadStage.builder()
            .stage(stageName)
            .threadName(format("%s[#%d]/%s",
                current.getClass().getSimpleName(),
                current.getId(),
                current.getName()))
            .build();
    }

    private void simulateTimeConsumingTask(int milliseconds, String taskDescription) {
        try {
            log.info(taskDescription);
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            log.info("InterruptedException, {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
