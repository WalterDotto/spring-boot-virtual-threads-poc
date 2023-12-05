package com.blocking.example.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import static java.lang.String.format;

@Data
@Slf4j
public class Pizza {

    private static int pizzaCount = 0;

    private final String id;
    private final List<ThreadStage> threadStageList;
    private boolean isSliced = false;

    public Pizza() {
        pizzaCount++;
        this.id = String.valueOf(pizzaCount);
        this.threadStageList = new ArrayList<>();
    }

    public void slice() {
        this.isSliced = true;
    }

    public void addStage(ThreadStage stage) {
        this.threadStageList.add(stage);
    }

    @Override
    public String toString() {
        return "\n\t----------------------------------------------------------------------------------------" +
            "\n\tPizza: " + id
            + " -  stages:" + printStageList(threadStageList)
            + "\n\t----------------------------------------------------------------------------------------";
    }

    private String printStageList(List<ThreadStage> threadStageList){

        StringBuilder sb = new StringBuilder();

        for(int i=0; i< threadStageList.size(); i++){
            sb.append(format("\n\t%d) %s \t-> \t%s", i, threadStageList.get(i).getStage(),
                threadStageList.get(i).getThreadName()));
        }
        return sb.toString();
    }
}
