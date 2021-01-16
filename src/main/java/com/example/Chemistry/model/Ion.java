package com.example.Chemistry.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Ion extends AbstractEntity {
    public static String ANION_TYPE = "anion";
    public static String CATION_TYPE = "cation";

    private int id;
    private String type;
    private int valence;
    private String notation;

    public Ion() {
    }

    public Ion(int id, String type, int valence, String notation) {
        this.id = id;
        this.type = type;
        this.valence = valence;
        this.notation = notation;
    }

    @Override
    public boolean isConsistent() {
        return id >= 0
                && type != null
                && valence >= 1
                && valence <= 7
                && notation != null;
    }

    @Override
    public String toString() {
        return String.format("(%d - %s %d %s)", id, type, valence, notation);
    }
}
