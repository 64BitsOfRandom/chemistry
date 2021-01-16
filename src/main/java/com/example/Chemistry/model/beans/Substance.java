package com.example.Chemistry.model.beans;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Substance  {
    private int id;
    private String formula;
    private String notation;
    private String className;

    public boolean isConsistent() {
        return id >= 0
                && formula != null
                && notation != null
                && className != null;
    }

}
