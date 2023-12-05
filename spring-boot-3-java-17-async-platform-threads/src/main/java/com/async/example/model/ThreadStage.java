package com.async.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ThreadStage {

    String stage;
    String threadName;

    @Override
    public String toString() {
        return stage + ":" + threadName;
    }
}
