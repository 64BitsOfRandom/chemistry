package com.example.Chemistry.model.dao.interfaces;

import com.example.Chemistry.model.Ion;
import com.example.Chemistry.model.Substance;
import com.example.Chemistry.model.SubstanceClass;

import java.util.List;

public interface IChemistryDAO {
    List<Ion> readIons();
    List<SubstanceClass> readSubstanceClasses();
    List<Substance> readSubstances();

    void createIon(String type, int valence, String notation);
    void createSubstanceClass(String name);
    void createSubstanceAndFormula(int substanceClassId, int anionId, int cationId, String notation);

    void updateIon(int id, String type, int valence, String notation);
    void updateSubstanceClass(int id, String name);
    void updateSubstanceAndFormula(int substanceId, int substanceClassId,
                                   int formulaId, int anionId, int cationId, String notation);

    void deleteIon(int id);
    void deleteSubstanceClass(int id);
    void deleteSubstanceAndFormula(int substanceId, int formulaId);
}
