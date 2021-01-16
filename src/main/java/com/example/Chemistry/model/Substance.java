package com.example.Chemistry.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Substance extends AbstractEntity {
    public int id;

    public String formula;
    public String notation;
    public String className;

    public Substance() {
    }

    public Substance(int id, String formula, String notation, String className) {
        this.id = id;
        this.formula = formula;
        this.notation = notation;
        this.className = className;
    }

    @Override
    public boolean isConsistent() {
        return id >= 0
                && formula != null
                && notation != null
                && className != null;
    }

    @Override
    public String toString() {
        return String.format("(%d - %s %s %s)", id, formula, notation, className);
    }
}
