package com.example.Chemistry.model.dao;

import com.example.Chemistry.model.Entity;
import com.example.Chemistry.model.Ion;
import com.example.Chemistry.model.Substance;
import com.example.Chemistry.model.SubstanceClass;
import com.example.Chemistry.model.dao.interfaces.IChemistryDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChemistryDAO implements IChemistryDAO {

    // read

    private <T extends Entity> List<T> readEntities(Entity.Type type) {
        String code = switch (type) {
            case ION -> """
                    SELECT *
                    FROM ions;
                    """;
            case SUBSTANCE -> """
                    SELECT *
                    FROM substance_classes;
                    """;

            // todo
            case SUBSTANCE_CLASS -> """
                    SELECT *
                    FROM substances;
                    """;
        };
        try {
            List<T> result = new ArrayList<>();
            Connection connection = null;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(code);
            while (resultSet.next()) {
                result.add(Entity.createEntity(resultSet, type));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<Ion> readIons() {
        return readEntities(Entity.Type.ION);
    }

    @Override
    public List<SubstanceClass> readSubstanceClasses() {
        return readEntities(Entity.Type.SUBSTANCE_CLASS);
    }

    @Override
    public List<Substance> readSubstances() {
        return readEntities(Entity.Type.SUBSTANCE);
    }

    // create

    @Override
    public void createIon(String type, int valence, String notation) {
        String code = """
                INSERT INTO ions
                VALUES(?, ?, ?);
                """;

        try {
            Connection connection = null;
            PreparedStatement statement = connection.prepareStatement(code);
            statement.setString(2, type);
            statement.setInt(3, valence);
            statement.setString(3, notation);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createSubstanceClass(String name) {
        String code = """
                INSERT INTO substance_classes
                VALUES(?);
                """;

        try {
            Connection connection = null;
            PreparedStatement statement = connection.prepareStatement(code);
            statement.setString(2, name);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createSubstanceAndFormula(int substanceClassId, int anionId, int cationId, String notation) {
        Connection connection = null;

    }

    // update

    @Override
    public void updateIon(int id, String type, int valence, String notation) {
        Connection connection = null;

    }

    @Override
    public void updateSubstanceClass(int id, String name) {
        Connection connection = null;

    }

    @Override
    public void updateSubstanceAndFormula(int substanceId, int substanceClassId, int formulaId, int anionId, int cationId, String notation) {
        Connection connection = null;

    }

    // delete

    @Override
    public void deleteIon(int id) {
        Connection connection = null;

    }

    @Override
    public void deleteSubstanceClass(int id) {
        Connection connection = null;

    }

    @Override
    public void deleteSubstanceAndFormula(int substanceId, int formulaId) {
        Connection connection = null;

    }
}
