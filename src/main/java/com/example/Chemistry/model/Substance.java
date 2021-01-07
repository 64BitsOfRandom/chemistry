package com.example.Chemistry.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Substance {
    private int id;
    private String formula;
    private int classId;
}
