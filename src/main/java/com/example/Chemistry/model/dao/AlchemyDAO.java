package com.example.Chemistry.model.dao;

import com.example.Chemistry.model.Formula;
import com.example.Chemistry.model.Ion;
import com.example.Chemistry.model.Substance;
import com.example.Chemistry.model.dao.interfaces.IAlchemyDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class AlchemyDAO implements IAlchemyDAO {
    //tables
    public static final String FORMULA_TABLE_NAME = "formulas";
    public static final String IONS_TABLE_NAME = "ions";
    public static final String SUBSTANCES_TABLE_NAME = "substances";
    //columns vars ??
    public static final String FORMULA_COL_NAME = "formula";
    public static final String CATION_COL_NAME = "cation";
    public static final String ANION_COL_NAME = "anion";
    //queries
    private static final String selectQuery = "SELECT * FROM ? WHERE id = ?";
    private static final String deleteQuery = "DELETE * FROM ? WHERE id = ?";
    private static final String substanceInsertQuery = "INSERT INTO substances VALUES(?, ?, ?)";
    private static final String formulaInsertQuery = "INSERT INTO formulas VALUES(?, ?, ?, ?)";
    private static final String ionInsertQuery = "INSERT INTO ions VALUES(?, ?, ?, ?)";

    @Override
    public boolean addSubstance(Substance substance) {
        boolean isSubstanceAdded = false;
        try(Connection connection = null;
        PreparedStatement statement = connection.prepareStatement(substanceInsertQuery)){
            //TODO:add logic
            isSubstanceAdded = statement.execute();
        }catch (SQLException ex){
            //TODO: log errors
        }
        return isSubstanceAdded;
    }

    @Override
    public boolean addFormula(Formula substance) {
        boolean isFormulaAdded = false;
        try(Connection connection = null;
            PreparedStatement statement = connection.prepareStatement(formulaInsertQuery)){
            //TODO:add logic
            isFormulaAdded = statement.execute();
        }catch (SQLException ex){
            //TODO: log errors
        }
        return isFormulaAdded;
    }

    @Override
    public boolean addIon(Ion ion) {
        boolean isIonAdded = false;
        try(Connection connection = null;
            PreparedStatement statement = connection.prepareStatement(ionInsertQuery)){
            //TODO:add logic
            isIonAdded = statement.execute();
        }catch (SQLException ex){
            //TODO: log errors
        }
        return isIonAdded;
    }

    @Override
    public Formula getFormulaById(int formulaId) {
        Formula formula = null;
        try (Connection connection = null;
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1,FORMULA_TABLE_NAME); //TODO: find more convenient way
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                formula = Formula.builder()
                        .id(formulaId)
                        .notation(resultSet.getString(FORMULA_COL_NAME))
                        .cationId(resultSet.getInt(CATION_COL_NAME))
                        .anionId(resultSet.getInt(ANION_COL_NAME)).build();
            }
            if (!isFormulaConsistent(formula)) {
                throw new SQLDataException("Inconsistent data acquired. Check database");
            }
        } catch (SQLException ex) {
            //TODO: write to log
        }
        return formula;
    }

    @Override
    public Substance getSubstanceById(int substanceId) {
        Substance substance = null;
        try(Connection connection = null; //TODO: init connect
            PreparedStatement statement = connection.prepareStatement(selectQuery)){
            statement.setInt(1, substanceId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                substance = Substance.builder()
                        .id(resultSet.getInt("id"))
                        .formula(resultSet.getString("formula"))
                        .classId(resultSet.getInt("classId")).build();
            }
            if(!isSubstanceConsistent(substance)){
                throw new SQLDataException("Inconsistent data acquired. Check database");
            }
        }catch (SQLException ex){
            //TODO: write to log
        }
        return substance;
    }

    @Override
    public Ion getIonById(int ionId) {
        Ion ion = null;
        try (Connection connection = null;
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1,IONS_TABLE_NAME);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ion = Ion.builder()
                        .id(ionId)
                        .notation(resultSet.getString("notation"))
                        .type(Ion.IonType.valueOf(resultSet.getString("type")))
                        .valence(resultSet.getInt("valence"))
                        .build();
            }

            if (!isIonConsistent(ion)) {
                throw new SQLDataException("Inconsistent data acquired. Check database");
            }
        } catch (SQLException ex) {
            //TODO: write to log
        }
        return ion;
    }

    @Override
    public boolean deleteEntityById(String entity, int id) {
        boolean isDeleted = false;
        String deleteQuery;
        switch (entity){
            entity ->
        }
        try(Connection connection = null;
        PreparedStatement statement = connection.prepareStatement()){
            switch ()
        }catch (SQLException ex){
            //TODO write to log
        }
        return isDeleted;
    }


    private boolean isSubstanceConsistent(Substance substance){
        return substance != null
                && substance.getId() > 0
                && !substance.getFormula().isBlank();
    }
    private boolean isFormulaConsistent(Formula formula) {
        return formula != null
                && !formula.getNotation().isBlank()
                && formula.getAnionId() > 0
                && formula.getCationId() > 0;
    }
    private boolean isIonConsistent(Ion ion){
        return ion != null
                && ion.getId() > 0
                && !ion.getNotation().isBlank()
                && ion.getValence() > 0;
    }
}
enum Entity{ion,formula,substance}
