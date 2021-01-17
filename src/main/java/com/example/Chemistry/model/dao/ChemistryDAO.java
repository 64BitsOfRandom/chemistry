package com.example.Chemistry.model.dao;

import com.example.Chemistry.connector.DatabaseConnector;
import com.example.Chemistry.model.beans.Ion;
import com.example.Chemistry.model.beans.Substance;
import com.example.Chemistry.model.beans.SubstanceClass;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component("chemistryDAO")
public class ChemistryDAO implements IChemistryDAO {

    // read

    private static Ion readIonFromResultSet(ResultSet resultSet) throws SQLException {
        Ion ion = Ion.builder()
                .id(resultSet.getInt("id"))
                .type(resultSet.getString("type"))
                .notation(resultSet.getString("notation"))
                .valence(resultSet.getInt("valence"))
                .build();
        ion.setNotation(resolveIon(ion));
        return ion;
    }

    private static SubstanceClass readSubstanceClassFromResultSet(ResultSet resultSet) throws SQLException {
        return SubstanceClass.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .build();
    }

    @Override
    public List<Ion> readIons() {
        List<Ion> result = new ArrayList<>();
        String script = """
                SELECT *
                FROM IONS
                """;

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(script);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(ChemistryDAO.readIonFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
    }

    @Override
    public List<SubstanceClass> readSubstanceClasses() {
        List<SubstanceClass> result = new ArrayList<>();
        String script = """
                SELECT *
                FROM CLASSES;
                """;

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(script);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(ChemistryDAO.readSubstanceClassFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static Substance readSubstanceFromResultSet(ResultSet resultSet) throws SQLException {
        Ion cation = Ion.builder()
                .type(Ion.CATION_TYPE)
                .valence(resultSet.getInt("cation_valence"))
                .notation(resultSet.getString("cation_notation"))
                .build();
        Ion anion = Ion.builder()
                .type(Ion.ANION_TYPE)
                .valence(resultSet.getInt("anion_valence"))
                .notation(resultSet.getString("anion_notation"))
                .build();
        String formula = resolveFormula(cation, anion);
        return Substance.builder()
                .id(resultSet.getInt("id"))
                .formula(formula)
                .notation(resultSet.getString("notation"))
                .className(resultSet.getString("className"))
                .build();
    }

    @Override
    public List<Substance> readSubstances() {
        List<Substance> result = new ArrayList<>();
        String script = """
                SELECT * FROM formulas_substances;
                """;
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(script);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                result.add(ChemistryDAO.readSubstanceFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void createIon(String type, int valence, String notation) {
        String script = """
                INSERT INTO ions (type, valence, notation)
                VALUES(?, ?, ?);
                """;

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(script)) {
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
        String script = """
                INSERT INTO CLASSES (name)
                VALUES(?);
                """;
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(script)) {
            statement.setString(1, name);
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createSubstanceAndFormula(int substanceClassId, int anionId, int cationId, String notation) {
        String formulasScript = """
                INSERT INTO formulas (anion, cation, notation)
                VALUES(?, ?, ?);
                """;
        String substancesScript = """
                INSERT INTO substances (id, formulaId, classId)
                VALUES(IDENTITY(), IDENTITY(), ?);
                """;
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(formulasScript);
             PreparedStatement substanceStatement = connection.prepareStatement(substancesScript)) {
            statement.setInt(1, anionId);
            statement.setInt(2, cationId);
            statement.setString(3, notation);
            statement.execute();
            substanceStatement.setInt(1, substanceClassId);
            substanceStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateIon(int id, String type, int valence, String notation) {
        String script = """
                UPDATE substances
                SET formulaId = ?,
                    classId = ?
                WHERE id = ?;
                """;
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(script)) {
            statement.setString(1, type);
            statement.setInt(2, valence);
            statement.setString(3, notation);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteIon(int id) {
        String script = """
                DELETE FROM ions
                WHERE id = ?;
                """;
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(script)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSubstanceClass(int id) {
        String script = """
                DELETE FROM CLASSES
                WHERE id = ?;
                """;
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(script)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSubstanceAndFormula(int id) {
        String substancesScript = """
                DELETE FROM SUBSTANCES
                WHERE id = ?;
                """;
        String formulasScript = """
                DELETE FROM FORMULAS
                WHERE id = ?;
                """;
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement substancesStatement = connection.prepareStatement(substancesScript);
             PreparedStatement formulasStatement = connection.prepareStatement(formulasScript)) {
            substancesStatement.setInt(1, id);
            substancesStatement.execute();
            formulasStatement.setInt(1, id);
            formulasStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String resolveFormula(Ion cation, Ion anion) {
        if (!cation.isConsistent() || !anion.isConsistent()) {
            throw new IllegalArgumentException("You should use different types of ions!");
        }
        if (cation.getValence() == anion.getValence()) return cation.getNotation() + anion.getNotation();
        if (cation.getValence() == 1) {
            return cation.getNotation() + "<sub>" + anion.getValence() + "</sub>" + anion.getNotation();
        }
        if (anion.getValence() == 1) {
            return cation.getNotation() + anion.getNotation() + "<sub>" + cation.getValence() + "</sub>";
        } else {
            return cation.getNotation() + "<sub>" + anion.getValence() + "</sub>" + anion.getNotation() + "<sub>" + cation.getValence() + "</sub>";
        }
    }

    private static String resolveIon(Ion ion) {
        if (!ion.isConsistent()) {
            throw new IllegalArgumentException("You should use different types of ions!");
        }
        return ion.getNotation() + "<sup>" + (Ion.CATION_TYPE.equalsIgnoreCase(ion.getType()) ? "+" : "-").repeat(ion.getValence()) + "</sup>";
    }
}
