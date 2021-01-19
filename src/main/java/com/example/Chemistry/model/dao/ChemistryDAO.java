package com.example.Chemistry.model.dao;

import com.example.Chemistry.connector.DatabaseConnector;
import com.example.Chemistry.model.beans.Ion;
import com.example.Chemistry.model.beans.Substance;
import com.example.Chemistry.model.beans.SubstanceClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@PropertySource("classpath:scripts.properties")
@Component("chemistryDAO")
public class ChemistryDAO implements IChemistryDAO {
    private static final Logger log = LogManager.getLogger(ChemistryDAO.class);
    @Value("${read.ions}")
    private String readIonsScript;
    @Value("${read.substanceClasses}")
    private String readSubstanceClassesScript;
    @Value("${read.substances}")
    private String readSubstancesScript;
    @Value("${create.ion}")
    private String createIonScript;
    @Value("${create.substanceClass}")
    private String createSubstanceClassScript;
    @Value("${create.substance}")
    private String createSubstanceScript;
    @Value("${create.formula}")
    private String createFormulaScript;
    @Value("${update.ion}")
    private String updateIonScript;
    @Value("${delete.ion}")
    private String deleteIonScript;
    @Value("${delete.substanceClass}")
    private String deleteSubstanceClassScript;
    @Value("${delete.substanceFormula}")
    private String deleteSubstanceFormulaProcedure;


    @Override
    public List<Ion> readIons() {
        log.info("read ions invoked");
        List<Ion> result = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(readIonsScript);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                result.add(readIonFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public List<SubstanceClass> readSubstanceClasses() {
        log.info("readSubstanceClasses invoked");
        List<SubstanceClass> result = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(readSubstanceClassesScript);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(readSubstanceClassFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Substance> readSubstances() {
        log.info("readSubstances invoked");
        List<Substance> result = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(readSubstancesScript);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                result.add(readSubstanceFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public void createIon(String type, int valence, String notation) {
        log.info("createIon invoked");
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(createIonScript)) {
            statement.setString(1, type);
            statement.setInt(2, valence);
            statement.setString(3, notation);
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void createSubstanceClass(String name) {
        log.info("createSubstanceClass invoked");
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(createSubstanceClassScript)) {
            statement.setString(1, name);
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void createSubstanceAndFormula(int substanceClassId, int anionId, int cationId, String notation) {
        log.info("createSubstanceAndFormula invoked");
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(createFormulaScript);
             PreparedStatement substanceStatement = connection.prepareStatement(createSubstanceScript)) {
            statement.setInt(1, anionId);
            statement.setInt(2, cationId);
            statement.setString(3, notation);
            statement.execute();
            substanceStatement.setInt(1, substanceClassId);
            substanceStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateIon(int id, String type, int valence, String notation) {
        log.info("updateIon invoked");
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateIonScript)) {
            statement.setString(1, type);
            statement.setInt(2, valence);
            statement.setString(3, notation);
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void deleteIon(int id) {
        log.info("deleteIon invoked");
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteIonScript)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void deleteSubstanceClass(int id) {
        log.info("deleteSubstanceClass invoked");
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteSubstanceClassScript)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void deleteSubstanceAndFormula(int id) {
        log.info("deleteSubstanceAndFormula invoked");
        try (Connection connection = DatabaseConnector.getConnection();
             CallableStatement callableStatement = connection.prepareCall(deleteSubstanceFormulaProcedure);
        ) {
            callableStatement.setInt(1,id);
            callableStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    private Ion readIonFromResultSet(ResultSet resultSet) throws SQLException {
        Ion ion = Ion.builder()
                .id(resultSet.getInt("id"))
                .type(resultSet.getString("type"))
                .notation(resultSet.getString("notation"))
                .valence(resultSet.getInt("valence"))
                .build();
        ion.setNotation(resolveIon(ion));
//        log.info("Substance {} added", substance.getFormulaId());
        return ion;
    }

    private SubstanceClass readSubstanceClassFromResultSet(ResultSet resultSet) throws SQLException {
        return SubstanceClass.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .build();
    }

    private Substance readSubstanceFromResultSet(ResultSet resultSet) throws SQLException {
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

    private String resolveFormula(Ion cation, Ion anion) {
        if (!cation.isConsistent() || !anion.isConsistent()) {
            throw new IllegalArgumentException("You should use different types of ions!");
        }
        if (cation.getValence() == anion.getValence()) return cation.getNotation() + anion.getNotation();
        if (cation.getValence() == 1) {
            return "%s<sub>%d</sub>%s".formatted(cation.getNotation(),anion.getValence(),anion.getNotation());
        }
        if (anion.getValence() == 1) {
            return "%s%s<sub>%d</sub>".formatted(cation.getNotation(),anion.getNotation(), cation.getValence());
        } else {
            return "%s<sub>%d</sub>%s<sub>%d</sub>".formatted(cation.getNotation(), anion.getValence(), anion.getNotation(), cation.getValence());
        }
    }

    private String resolveIon(Ion ion) {
        if (!ion.isConsistent()) {
            throw new IllegalArgumentException("You should use different types of ions!");
        }
        return "%s<sup>%s</sup>".formatted(ion.getNotation(), (Ion.CATION_TYPE.equalsIgnoreCase(ion.getType()) ? "+" : "-").repeat(ion.getValence()));
    }
}
