package com.example.Chemistry.model.dao;

import com.example.Chemistry.model.Ion;
import com.example.Chemistry.model.Substance;
import com.example.Chemistry.model.SubstanceClass;
import com.example.Chemistry.model.dao.interfaces.IAlchemyDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component("alchemyDAO")
public class AlchemyDAO implements IAlchemyDAO {
    private static final Logger log = LogManager.getLogger(AlchemyDAO.class);
//
//    //queries
//    private static final String selectQuery = "SELECT * FROM ? WHERE id = ?";
//    private static final String selectAllQuery = "SELECT * FROM ?";
//
    private static final String deleteQuery = "DELETE FROM ? WHERE id = ?";
//
//    private static final String substanceInsertQuery = "INSERT INTO substances VALUES(?, ?, ?)";
//    private static final String formulaInsertQuery = "INSERT INTO formulas VALUES(?, ?, ?, ?)";
//    private static final String ionInsertQuery = "INSERT INTO ions VALUES(?, ?, ?, ?)";
//    private static final String classInsertQuery = "INSERT INTO classes VALUES(?)";
//
//    private static final String substanceUpdateQuery = "UPDATE substances SET formulaId = ?, classId = ? WHERE id = ?;";
//    private static final String formulaUpdateQuery = "UPDATE formulas SET cationId = ?, anionId = ?, notation = ? WHERE id = ?;";
//    private static final String ionUpdateQuery = "UPDATE ions SET valence = ?, notation = ?, type = ? WHERE id = ?;";
//    private static final String classUpdateQuery = "UPDATE classes SET name = ? WHERE id = ?;";
//
//    @Override
//    public boolean addSubstance(Substance substance) {
//        boolean isSubstanceAdded = false;
//        try (Connection connection = null;
//             PreparedStatement statement = connection.prepareStatement(substanceInsertQuery)) {
//            statement.setInt(2, substance.getFormulaId());
//            statement.setInt(3, substance.getClassId());
//            isSubstanceAdded = statement.execute();
//        } catch (SQLException ex) {
//            log.error("Failed to add substance!");
//        }
//        log.info("Substance {} added", substance.getFormulaId());
//        return isSubstanceAdded;
//    }
//
//    @Override
//    public boolean addFormula(Formula formula) {
//        boolean isFormulaAdded = false;
//        try (Connection connection = null;
//             PreparedStatement statement = connection.prepareStatement(formulaInsertQuery)) {
//            statement.setInt(2, formula.getAnionId());
//            statement.setInt(3, formula.getCationId());
//            statement.setString(4, formula.getNotation());
//            isFormulaAdded = statement.execute();
//        } catch (SQLException ex) {
//            log.error("Failed to add formula!");
//        }
//        log.info("Formula {} added", formula.getNotation());
//        return isFormulaAdded;
//    }
//
//    @Override
//    public boolean addIon(Ion ion) {
//        boolean isIonAdded = false;
//        try (Connection connection = null;
//             PreparedStatement statement = connection.prepareStatement(ionInsertQuery)) {
//            statement.setInt(2, ion.getValence());
//            statement.setString(3, ion.getNotation());
//            statement.setString(4, ion.getType().name());
//            isIonAdded = statement.execute();
//        } catch (SQLException ex) {
//            log.error("Failed to add Ion!");
//        }
//        log.info("Ion {} added", ion.getNotation());
//        return isIonAdded;
//    }
//
//    @Override
//    public boolean addSubstanceClass(SubstanceClass substanceClass) {
//        boolean isAdded = false;
//        try (Connection connection = null;
//             PreparedStatement statement = connection.prepareStatement(classInsertQuery)) {
//            statement.setString(2, substanceClass.getName());
//            isAdded = statement.execute();
//        } catch (SQLException ex) {
//            log.error("Failed to add Ion!");
//        }
//        log.info("Ion {} added", substanceClass.getName());
//        return isAdded;
//    }

    @Override
    public boolean deleteEntityById(String tableName, int id) {
        boolean isDeleted = false;
        try (Connection connection = null;
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setString(1, tableName);
            statement.setInt(2, id);
            isDeleted = statement.execute();
        } catch (SQLException ex) {
            log.error("Unable to delete entity #{} from {}!", id, tableName);
        }
        if(isDeleted) log.info("Entity #{} deleted from {} successfully", id, tableName);
        return isDeleted;
    }

//
//    // update
//
//    @Override
//    public boolean updateSubstance(Substance substance) {
//        boolean isUpdated = false;
//        try (Connection connection = null;
//             PreparedStatement statement = connection.prepareStatement(substanceUpdateQuery)) {
//            statement.setInt(2, substance.getFormulaId());
//            statement.setInt(3, substance.getClassId());
//            isUpdated = statement.execute();
//        } catch (SQLException ex) {
//            log.error("Failed to add substance!");
//        }
//        log.info("Substance {} added", substance.getFormulaId());
//        return isUpdated;
//    }
//
//    @Override
//    public boolean updateFormula(Formula formula) {
//        boolean isUpdated = false;
//        try (Connection connection = null;
//             PreparedStatement statement = connection.prepareStatement(formulaUpdateQuery)) {
//            statement.setInt(2, formula.getAnionId());
//            statement.setInt(3, formula.getCationId());
//            statement.setString(4, formula.getNotation());
//            isUpdated = statement.execute();
//        } catch (SQLException ex) {
//            log.error("Failed to add formula!");
//        }
//        log.info("Formula {} added", formula.getNotation());
//        return isUpdated;
//    }
//
//    @Override
//    public boolean updateIon(Ion ion) {
//        boolean isUpdated = false;
//        try (Connection connection = null;
//             PreparedStatement statement = connection.prepareStatement(ionUpdateQuery)) {
//            statement.setInt(2, ion.getValence());
//            statement.setString(3, ion.getNotation());
//            statement.setString(4, ion.getType().name());
//            isUpdated = statement.execute();
//        } catch (SQLException ex) {
//            log.error("Failed to add Ion!");
//        }
//        log.info("Ion {} added", ion.getNotation());
//        return isUpdated;
//    }
//
//    @Override
//    public boolean updateSubstanceClass(SubstanceClass substanceClass) {
//        boolean isUpdated = false;
//        try (Connection connection = null;
//             PreparedStatement statement = connection.prepareStatement(classUpdateQuery)) {
//            statement.setString(2, substanceClass.getName());
//            isUpdated = statement.execute();
//        } catch (SQLException ex) {
//            log.error("Failed to add Ion!");
//        }
//        log.info("Ion {} added", substanceClass.getName());
//        return isUpdated;
//    }
}
