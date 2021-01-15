package com.example.Chemistry.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Ion {
    public static String ANION_TYPE = "anion";
    public static String CATION_TYPE = "cation";

    private int id;
    private String type;
    private int valence;
    private String notation;

    public boolean isConsistent() {
        return id >= 0
                && type != null
                && valence >= 1
                && valence <= 7
                && notation != null;
    }
}
