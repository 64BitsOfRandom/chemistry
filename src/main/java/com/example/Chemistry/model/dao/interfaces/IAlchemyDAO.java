package com.example.Chemistry.model.dao.interfaces;

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
    String ID_COL_NAME = "id";
    String FORMULA_COL_NAME = "formula";
    String CATION_COL_NAME = "cation";
    String ANION_COL_NAME = "anion";
//
//    //insert queries
//    boolean addSubstance(Substance substance);
//
//    boolean addFormula(Formula formula);
//
//    boolean addIon(Ion ion);
//
//    boolean addSubstanceClass(SubstanceClass substanceClass);
//
//    //select queries
//    Substance getSubstanceById(int id);
//
//    Formula getFormulaById(int id);
//
//    Ion getIonById(int id);
//
////    Ion getClassById(int id);
////    List<Formula> getFormulas();
////    List<Formula> getClasses();
////    List<Formula> getFormulas();
//
//    //update queries
//    boolean updateSubstance(Substance substance);
//
//    boolean updateFormula(Formula formula);
//
//    boolean updateIon(Ion ion);
//
//    boolean updateSubstanceClass(SubstanceClass substanceClass);
//
    //delete queries
    boolean deleteEntityById(String entity, int entityId);

}
