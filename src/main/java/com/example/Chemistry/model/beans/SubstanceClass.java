package com.example.Chemistry.model.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubstanceClass {
    private int id;
    private String name;

    public boolean isConsistent() {
        return id >= 0 && name != null;
    }

}
