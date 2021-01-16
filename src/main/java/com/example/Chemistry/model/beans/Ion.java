package com.example.Chemistry.model.beans;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ion {
    public static String ANION_TYPE = "анион";
    public static String CATION_TYPE = "катион";

    private int id;
    private String type;
    private int valence;
    private String notation;

    public boolean isConsistent() {
        return id >= 0
                && (ANION_TYPE.equalsIgnoreCase(type) || CATION_TYPE.equalsIgnoreCase(type))
                && valence >= 1
                && valence <= 7
                && notation != null;
    }
}
