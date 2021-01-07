package com.example.Chemistry.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Formula {
    private int id;
    private int anionId;
    private int cationId;
    private String notation;
}
