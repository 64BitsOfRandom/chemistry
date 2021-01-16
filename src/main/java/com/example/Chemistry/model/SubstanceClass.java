package com.example.Chemistry.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SubstanceClass extends AbstractEntity {
    private int id;
    private String name;

    @Override
    public boolean isConsistent() {
        return id >= 0
                && name != null;
    }
}
