package com.example.Chemistry.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Substance extends AbstractEntity {
    private int id;

    private String formula;
    private String notation;

    @Override
    public boolean isConsistent() {
        return id >= 0
                && formula != null
                && notation != null;
    }
}
