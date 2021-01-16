package com.example.Chemistry.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SubstanceClass extends AbstractEntity {
    public int id;
    public String name;

    public SubstanceClass() {
    }

    public SubstanceClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean isConsistent() {
        return id >= 0 && name != null;
    }

    @Override
    public String toString() {
        return String.format("(%d - %s)", id, name);
    }
}
