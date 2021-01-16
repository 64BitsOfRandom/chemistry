package com.example.Chemistry.view;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateSubstanceForm {
    private int classId;
    private int cationId;
    private int anionId;
    private String notation;

    public CreateSubstanceForm() {
    }

    public CreateSubstanceForm(int classId, int cationId, int anionId, String notation) {
        this.classId = classId;
        this.cationId = cationId;
        this.anionId = anionId;
        this.notation = notation;
    }
}
