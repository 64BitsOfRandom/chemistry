package com.example.Chemistry.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Substance {
    private int substance_id;
    private int formula_id;

    private String formula;
    private String notation;

    public boolean isConsistent() {
        return substance_id >= 0
                && formula_id >= 0
                && formula != null
                && notation != null;
    }
}
