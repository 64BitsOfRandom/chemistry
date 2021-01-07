package com.example.Chemistry.model.dao.interfaces;

import com.example.Chemistry.model.Formula;
import com.example.Chemistry.model.Ion;
import com.example.Chemistry.model.Substance;

public interface IAlchemyDAO {
    //select queries
    Substance getSubstanceById(int substanceId);
    Formula getFormulaById(int formulaId);
    Ion getIonById(int ionId);
    //insert queries
    boolean addSubstance(Substance substance);
    boolean addFormula(Formula formula);
    boolean addIon(Ion ion);
    //delete queries
    boolean deleteEntityById(String entity, int entityId);
    //update queries
}
