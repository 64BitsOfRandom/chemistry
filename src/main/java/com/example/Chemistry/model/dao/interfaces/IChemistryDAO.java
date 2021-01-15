package com.example.Chemistry.model.dao.interfaces;

import com.example.Chemistry.model.Ion;
import com.example.Chemistry.model.Substance;
import com.example.Chemistry.model.SubstanceClass;

import java.util.List;

public interface IChemistryDAO {
    List<Ion> readIons();
    List<SubstanceClass> readSubstanceClasses();
    List<Substance> readSubstances();

    void createIon(Ion ion);
    void createSubstanceClass(SubstanceClass substanceClass);
    void createSubstanceAndFormula(int substanceId, int substanceClassId,
                                   int formulaId, int anionId, int cationId, String notation);

    void updateIon(Ion ion);
    void updateSubstanceClass(SubstanceClass substanceClass);
    void updateSubstanceAndFormula(int substanceId, int substanceClassId,
                                   int formulaId, int anionId, int cationId, String notation);

    void deleteIon(int id);
    void deleteSubstanceClass(int id);
    void deleteSubstanceAndFormula(int substanceId, int formulaId);
}
