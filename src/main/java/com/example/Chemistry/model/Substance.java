package com.example.Chemistry.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Substance {
    private int id;

    private String formula;
    private String notation;

    public boolean isConsistent() {
        return id >= 0
                && formula != null
                && notation != null;
    }
}
