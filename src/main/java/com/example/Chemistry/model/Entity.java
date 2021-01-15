package com.example.Chemistry.model;

import java.sql.ResultSet;

public abstract class Entity {
    public enum Type {
        ION, SUBSTANCE, SUBSTANCE_CLASS
    }

    public static <T extends Entity> T createEntity(ResultSet resultSet, Type type) {
        return switch (type) {
            case ION -> Ion.builder().build();
            case SUBSTANCE_CLASS -> SubstanceClass.builder().build();
            case SUBSTANCE -> Substance.builder().build();
        };
    }

    abstract boolean isConsistent();
}
