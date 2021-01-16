package com.example.Chemistry.model.dao;

import com.example.Chemistry.connector.DatabaseConnector;
import com.example.Chemistry.model.Ion;
import com.example.Chemistry.model.Substance;
import com.example.Chemistry.model.SubstanceClass;
import com.example.Chemistry.model.dao.interfaces.IChemistryDAO;

import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChemistryDAO implements IChemistryDAO {

    // read

    private static Ion readIonFromResultSet(ResultSet resultSet) throws SQLException {
        return Ion.builder()
                .id(resultSet.getInt("id"))
                .type(resultSet.getString("type"))
                .notation(resultSet.getString("notation"))
                .valence(resultSet.getInt("valence"))
                .build();
    }

    private static SubstanceClass readSubstanceClassFromResultSet(ResultSet resultSet) throws SQLException {
        return SubstanceClass.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .build();
    }

    private static Substance readSubstanceFromResultSet(ResultSet resultSet) throws SQLException {
        return Substance.builder()
                .id(resultSet.getInt("id"))
                .formula(resultSet.getString("formula"))
                .notation(resultSet.getString("notation"))
                .build();
    }

    @Override
    public List<Ion> readIons() {
        String code = """
                SELECT *
                FROM IONS
                """;

        try (Connection connection = DatabaseConnector.getConnection()) {
            List<Ion> result = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(code);
            while (resultSet.next()) {
                result.add(ChemistryDAO.readIonFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<SubstanceClass> readSubstanceClasses() {
        String code = """
                SELECT *
                FROM substance_classes;
                """;

        try (Connection connection = DatabaseConnector.getConnection()) {
            List<SubstanceClass> result = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(code);
            while (resultSet.next()) {
                result.add(ChemistryDAO.readSubstanceClassFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<Substance> readSubstances() {
        String code = """
                SELECT *
                FROM substances;
                """;

        try (Connection connection = DatabaseConnector.getConnection()) {
            List<Substance> result = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(code);
            while (resultSet.next()) {
                result.add(ChemistryDAO.readSubstanceFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    // create

    @Override
    public void createIon(String type, int valence, String notation) {
        String code = """
                INSERT INTO ions
                VALUES(?, ?, ?);
                """;

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(code);
            statement.setString(1, type);
            statement.setInt(2, valence);
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

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(code);
            statement.setString(1, name);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createSubstanceAndFormula(int substanceClassId, int anionId, int cationId, String notation) {
        String code = """
                INSERT INTO ions
                VALUES(?, ?, ?);
                """;

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(code);
//            statement.setString(1, type);
//            statement.setInt(2, valence);
//            statement.setString(3, notation);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // update

    @Override
    public void updateIon(int id, String type, int valence, String notation) {
        String code = """
                UPDATE substances
                SET formulaId = ?,
                    classId = ?
                WHERE id = ?;
                """;

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(code);
            statement.setString(1, type);
            statement.setInt(2, valence);
            statement.setString(3, notation);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSubstanceClass(int id, String name) {
        String code = """
                UPDATE substances
                SET formulaId = ?,
                    classId = ?
                WHERE id = ?;
                """;

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(code);
//            statement.setString(1, type);
//            statement.setInt(2, valence);
//            statement.setString(3, notation);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSubstanceAndFormula(int substanceId, int substanceClassId, int formulaId, int anionId, int cationId, String notation) {
        String code = """
                UPDATE substances
                SET formulaId = ?,
                    classId = ?
                WHERE id = ?;
                """;

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(code);
//            statement.setString(1, type);
//            statement.setInt(2, valence);
//            statement.setString(3, notation);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // delete

    @Override
    public void deleteIon(int id) {
        String code = """
                DELETE FROM ions
                WHERE id = ?;
                """;

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(code);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSubstanceClass(int id) {
        String code = """
                DELETE FROM substance_classes
                WHERE id = ?;
                """;

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(code);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteSubstanceAndFormula(int substanceId, int formulaId) {
        String code = """
                DELETE FROM substances
                WHERE id = ?;
                """;

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(code);
            statement.setInt(1, substanceId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ChemistryDAO chemistryDAO = new ChemistryDAO();
        List<Ion> ions = chemistryDAO.readIons();
        System.out.println(ions);
    }
}
