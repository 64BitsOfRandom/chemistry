package com.example.Chemistry.model.dao;

import com.example.Chemistry.model.Ion;
import com.example.Chemistry.model.Substance;
import com.example.Chemistry.model.SubstanceClass;
import com.example.Chemistry.model.dao.interfaces.IChemistryDAO;

import java.util.List;

public class ChemistryDAO implements IChemistryDAO {
    @Override
    public List<Ion> readIons() {
        return null;
    }

    @Override
    public List<SubstanceClass> readSubstanceClasses() {
        return null;
    }

    @Override
    public List<Substance> readSubstances() {
        return null;
    }

    @Override
    public void createIon(Ion ion) {

    }

    @Override
    public void createSubstanceClass(SubstanceClass substanceClass) {

    }

    @Override
    public void createSubstanceAndFormula(int substanceId, int substanceClassId, int formulaId, int anionId, int cationId, String notation) {

    }

    @Override
    public void updateIon(Ion ion) {

    }

    @Override
    public void updateSubstanceClass(SubstanceClass substanceClass) {

    }

    @Override
    public void updateSubstanceAndFormula(int substanceId, int substanceClassId, int formulaId, int anionId, int cationId, String notation) {

    }

    @Override
    public void deleteIon(int id) {

    }

    @Override
    public void deleteSubstanceClass(int id) {

    }

    @Override
    public void deleteSubstanceAndFormula(int substanceId, int formulaId) {

    }
}
