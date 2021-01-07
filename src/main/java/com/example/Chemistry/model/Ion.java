package com.example.Chemistry.model;

import lombok.*;

@Getter
@Builder
public class Ion {
    private int id;
    private int valence;
    private String notation;
    private IonType type;

    public enum IonType{
        Cation, Anion
    }
}

