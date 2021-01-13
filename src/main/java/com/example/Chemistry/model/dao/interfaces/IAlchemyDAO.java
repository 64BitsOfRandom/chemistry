package com.example.Chemistry.model.dao.interfaces;

import com.example.Chemistry.model.Formula;
import com.example.Chemistry.model.Ion;
import com.example.Chemistry.model.Substance;
import com.example.Chemistry.model.SubstanceClass;

public interface IAlchemyDAO {
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
