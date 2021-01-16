package com.example.Chemistry.model.dao;

import com.example.Chemistry.connector.DatabaseConnector;
import com.example.Chemistry.model.Ion;
import com.example.Chemistry.model.Substance;
import com.example.Chemistry.model.SubstanceClass;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component("chemistryDAO")
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

    @Override
    public List<Ion> readIons() {
        String code = """
                SELECT *
                FROM IONS
                """;

        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(code);

            List<Ion> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(ChemistryDAO.readIonFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public List<SubstanceClass> readSubstanceClasses() {
        String code = """
                SELECT *
                FROM CLASSES;
                """;

        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(code);

            List<SubstanceClass> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(ChemistryDAO.readSubstanceClassFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    private static Substance readSubstanceFromResultSet(ResultSet resultSet) throws SQLException {
        return Substance.builder()
                .id(resultSet.getInt("id"))
                .formula(resultSet.getString("formula"))
                .notation(resultSet.getString("notation"))
                .className(resultSet.getString("className"))
                .build();
    }

    @Override
    public List<Substance> readSubstances() {
        String code = """
                select FORMULAS.ID                                    as id,
                       FORMULAS.NOTATION                              as notation,
                       CONCAT(CATIONS.NOTATION, ANIONS.NOTATION) as formula,
                       CLASSES.NAME                                   as className
                from FORMULAS
                         inner join SUBSTANCES on FORMULAS.ID = SUBSTANCES.ID and FORMULAS.ID = SUBSTANCES.FORMULAID
                         inner join CLASSES on SUBSTANCES.CLASSID = CLASSES.ID
                         inner join IONS ANIONS on ANIONS.ID = FORMULAS.ANION
                         inner join IONS CATIONS on CATIONS.ID = FORMULAS.CATION
                """;

        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(code);

            List<Substance> result = new ArrayList<>();
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
                INSERT INTO ions (type, valence, notation)
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
                INSERT INTO CLASSES (name)
                VALUES(?);
                """;

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(code);
            statement.setString(1, name);
            statement.execute();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createSubstanceAndFormula(int substanceClassId, int anionId, int cationId, String notation) {
        String code1 = """
                INSERT INTO formulas (anion, cation, notation)
                VALUES(?, ?, ?);
                """;
        String code2 = """
                INSERT INTO substances (id, formulaId, classId)
                VALUES(IDENTITY(), IDENTITY(), ?);
                """;

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(code1);
            statement.setInt(1, anionId);
            statement.setInt(2, cationId);
            statement.setString(3, notation);
            statement.execute();

            statement = connection.prepareStatement(code2);
            statement.setInt(1, substanceClassId);
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
                DELETE FROM CLASSES
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
    public void deleteSubstanceAndFormula(int id) {
        String code1 = """
                DELETE FROM SUBSTANCES
                WHERE id = ?;
                """;
        String code2 = """
                DELETE FROM FORMULAS
                WHERE id = ?;
                """;

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(code1);
            statement.setInt(1, id);
            statement.execute();

            statement = connection.prepareStatement(code2);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ChemistryDAO dao = new ChemistryDAO();
        dao.createSubstanceAndFormula(0, 1, 2, "3");
        System.out.println(dao.readSubstances());
    }
}
