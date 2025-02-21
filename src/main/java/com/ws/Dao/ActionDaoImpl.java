package com.ws.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ws.beans.Action;
import com.ws.beans.Action;

public class ActionDaoImpl implements ActionDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  ActionDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER ACTION
		// =================================================================================
    @Override
    public void ajouterAction(Action action) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion  = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO action(nom_programme, action, disponible, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, action.getNom_programme());
            preparedStatement.setString(2, action.getAction());
            preparedStatement.setString(3, action.getDisponible());
            preparedStatement.setString(4, "ActionDao");
            preparedStatement.setString(5, dateTime);
            preparedStatement.setString(6, System.getProperty("user.name"));
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            if (connexion != null) {
                try {
                    connexion.rollback();
                } catch (SQLException ex) {
                    System.err.println("Rollback failed: " + ex.getMessage());
                }
            }
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table Action"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER ACTION
		// =================================================================================
    @Override
    public void modifierAction(Action action) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE action SET nom_programme=?, action=?, disponible=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, action.getNom_programme());
            preparedStatement.setString(2, action.getAction());
            preparedStatement.setString(3, action.getDisponible());
            preparedStatement.setString(4, "ActionDao");
            preparedStatement.setString(5, dateTime);
            preparedStatement.setString(6, System.getProperty("user.name"));
            preparedStatement.setInt(7, action.getId());
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            if (connexion != null) {
                try {
                    connexion.rollback();
                } catch (SQLException ex) {
                    System.err.println("Rollback failed: " + ex.getMessage());
                }
            }
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table Action"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER ACTION
  		// =================================================================================
    @Override
    public void copierAction(Action action) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO action(nom_programme, action, disponible, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, action.getNom_programme());
            preparedStatement.setString(2, action.getAction());
            preparedStatement.setString(3, action.getDisponible());
            preparedStatement.setString(4, "ActionDao");
            preparedStatement.setString(5, dateTime);
            preparedStatement.setString(6, System.getProperty("user.name"));
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            if (connexion != null) {
                try {
                    connexion.rollback();
                } catch (SQLException ex) {
                    System.err.println("Rollback failed: " + ex.getMessage());
                }
            }
            throw new DaoException("Impossible de copier l'enregistrement avec la table Action"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER ACTION
  		// =================================================================================

    @Override
    public void supprimerAction(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM action WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table Action"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

 	// =================================================================================
  	// LISTER DES STATUTS
  	// =================================================================================
    @Override
    public List<Action> listerAction() throws DaoException {
        List<Action> actions = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, nom_programme, action, disponible FROM action;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Action action = new Action();
                action.setId(resultat.getInt("id"));
                action.setNom_programme(resultat.getString("nom_programme"));
                action.setAction(resultat.getString("action"));
                action.setDisponible(resultat.getString("disponible"));
                actions.add(action);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Action"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return actions;
    }

    	// =================================================================================
		// TROUVER ACTION PAR ID
		// =================================================================================
    @Override
    public Action trouverAction(Integer id) throws DaoException {
        Action action = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM action WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                action = new Action();
                action.setId(id);
                action.setNom_programme(resultat.getString("nom_programme"));
                action.setAction(resultat.getString("action"));
                action.setDisponible(resultat.getString("disponible"));
                action.setUsermodification(resultat.getString("usermodification"));
                action.setDatemodification(resultat.getDate("datemodification"));
                action.setPgmmodification(resultat.getString("pgmmodification"));
                action.setUsercreation(resultat.getString("usercreation"));
                action.setDatecreation(resultat.getDate("datecreation"));
                action.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table Action"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return action;
    }

    	// =================================================================================
		// TROUVER ACTION PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomAction(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM action WHERE nom_programme=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table Action"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE ACTION
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheActions(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM action ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Map<String, Object> actionFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                actionFields.put("id", rs.getInt("id"));
                }
                if (dictionnaire_nom_colonne.containsKey("nom_programme")) {
                actionFields.put("nom_programme", rs.getString("nom_programme"));
                }
                if (dictionnaire_nom_colonne.containsKey("action")) {
                actionFields.put("action", rs.getString("action"));
                }
                if (dictionnaire_nom_colonne.containsKey("disponible")) {
                    actionFields.put("disponible", rs.getString("disponible"));
                }
                list.add(actionFields);
            }

            rs.close();

            preparedStatement = connexion.prepareStatement("SELECT FOUND_ROWS()");
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connexion, preparedStatement, rs);
        }
        return list;
    }

    	// =================================================================================
		// RECHERCHE STATUTS SUIVANT LIKE
		// =================================================================================
    @Override
    public List<Map<String, Object>>  rechercheLikeActions(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM action WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> actionFields = new LinkedHashMap<>();
            	  if (dictionnaire_nom_colonne.containsKey("id")) {
                      actionFields.put("id", rs.getInt("id"));
                      }
                      if (dictionnaire_nom_colonne.containsKey("nom_programme")) {
                      actionFields.put("nom_programme", rs.getString("nom_programme"));
                      }
                      if (dictionnaire_nom_colonne.containsKey("action")) {
                      actionFields.put("action", rs.getString("action"));
                      }
                      if (dictionnaire_nom_colonne.containsKey("disponible")) {
                          actionFields.put("disponible", rs.getString("disponible"));
                      }
                list.add(actionFields);
            }

            rs.close();

            preparedStatement = connexion.prepareStatement("SELECT FOUND_ROWS()");
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connexion, preparedStatement, rs);
        }
        return list;
    }

    @Override
    public Map<String, String> getActionsDisponibles(String parametreNomProgramme) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        Map<String, String> actionsDisponibles = new HashMap<>();
        try {
        	connexion = daoFactory.getConnection();
        	 String sql = "SELECT action, disponible FROM action WHERE nom_programme = ?";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, parametreNomProgramme);
            System.out.println(preparedStatement);
            resultat = preparedStatement.executeQuery();
            while (resultat.next()) {
                actionsDisponibles.put(resultat.getString("action"), resultat.getString("disponible"));
                System.out.println(actionsDisponibles.toString());
            }
        } catch (SQLException e) {
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return actionsDisponibles;
    }

    // =================================================================================
	// RECUPERATION N° ENREGISTREMENT ENCOURS ACTION
	// =================================================================================
    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    @Override
    public Integer getIntegerRecords() {
    	Integer integerRecords = noOfRecords;
        return integerRecords;
    }


    // =================================================================================
	// FERMETURE DES RESSOURCES ACTION
	// =================================================================================
    private void closeResources(Connection connexion, Statement statement, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Error closing result set: " + e.getMessage());
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement: " + e.getMessage());
            }
        }
        if (connexion != null) {
            try {
                connexion.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

}