package com.example.Chemistry.model.dao.interfaces;

import com.example.Chemistry.model.Formula;
import com.example.Chemistry.model.Ion;
import com.example.Chemistry.model.Substance;
import com.example.Chemistry.model.SubstanceClass;

public interface IAlchemyDAO {
    //tables
    String FORMULA_TABLE_NAME = "formulas";
    String IONS_TABLE_NAME = "ions";
    String SUBSTANCES_TABLE_NAME = "substances";
    String CLASSES_TABLE_NAME = "classes";
    //columns vars ??
    String FORMULA_COL_NAME = "formula";
    String CATION_COL_NAME = "cation";
    String ANION_COL_NAME = "anion";

    //select queries
    Substance getSubstanceById(int substanceId);
    Formula getFormulaById(int formulaId);
    Ion getIonById(int ionId);
    //insert queries
    boolean addSubstance(Substance substance);
    boolean addFormula(Formula formula);
    boolean addIon(Ion ion);
    boolean addSubstanceClass(SubstanceClass substanceClass);
    //delete queries
    boolean deleteEntityById(String entity, int entityId);
    //update queries
    boolean updateSubstance(Substance substance);
    boolean updateFormula(Formula formula);
    boolean updateIon(Ion ion);
    boolean updateSubstanceClass(SubstanceClass substanceClass);

}
