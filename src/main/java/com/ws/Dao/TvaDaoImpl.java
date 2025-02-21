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

import com.ws.beans.Contrat;
import com.ws.beans.Tva;
import com.ws.configuration.DatasourceH;

public class TvaDaoImpl implements TvaDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  TvaDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER TVA
		// =================================================================================
    @Override
    public void ajouterTva(Tva tva) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion  = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO tva(nom, libelle, taux, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, tva.getNom());
            preparedStatement.setString(2, tva.getLibelle());
            preparedStatement.setDouble(3, tva.getTaux());
            preparedStatement.setString(4, "TvaDao");
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table Tva"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER TVA
		// =================================================================================
    @Override
    public void modifierTva(Tva tva) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE tva SET nom=?, libelle=?, taux=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, tva.getNom());
            preparedStatement.setString(2, tva.getLibelle());
            preparedStatement.setDouble(3, tva.getTaux());
            preparedStatement.setString(4, "TvaDao");
            preparedStatement.setString(5, dateTime);
            preparedStatement.setString(6, System.getProperty("user.name"));
            preparedStatement.setInt(7, tva.getId());
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table Tva"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER TVA
  		// =================================================================================
    @Override
    public void copierTva(Tva tva) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO tva(nom, libelle, taux, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, tva.getNom());
            preparedStatement.setString(2, tva.getLibelle());
            preparedStatement.setDouble(3, tva.getTaux());
            preparedStatement.setString(4, "TvaDao");
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table Tva"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER TVA
  		// =================================================================================

    @Override
    public void supprimerTva(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM tva WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table Tva"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER TVA
 		// =================================================================================
 		@Override
 		public void renommerTva(Tva tva) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();

 				preparedStatement = connexion.prepareStatement(
 						"UPDATE tva SET tva.nom=?, tva.pgmmodification=?, tva.datemodification=?,"
 								+ " tva.usermodification=? where tva.id=?;");
 				preparedStatement.setString(1, tva.getNom());
 				preparedStatement.setString(2, "RENOMMER TVA");
 				preparedStatement.setString(3, dateTime);
 				preparedStatement.setString(4, System.getProperty("user.name"));
 				preparedStatement.setInt(5, tva.getId());
 				preparedStatement.executeUpdate();
 				connexion.commit();
 			} catch (SQLException e) {
 				try {
 					if (connexion != null) {
 						connexion.rollback();
 					}
 				} catch (SQLException e2) {
 				}
 				throw new DaoException("Impossible de renommer enregistrement avec la table contratt" + e);
 			} finally {
 				try {
 					if (connexion != null) {
 						connexion.close();
 					}
 				} catch (SQLException e) {
 					throw new DaoException("Impossible de renommer enregistrement avec la table contratt" + e);
 				}
 			}
 		}

 	// =================================================================================
  	// LISTER DES TVAS
  	// =================================================================================
    @Override
    public List<Tva> listerTva() throws DaoException {
        List<Tva> tvas = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, nom, libelle, taux FROM tva;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Tva tva = new Tva();
                tva.setId(resultat.getInt("id"));
                tva.setNom(resultat.getString("nom"));
                tva.setLibelle(resultat.getString("libelle"));
                tva.setTaux(resultat.getDouble("taux"));
                tvas.add(tva);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Tva"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return tvas;
    }

    	// =================================================================================
		// TROUVER TVA PAR ID
		// =================================================================================
    @Override
    public Tva trouverTva(Integer id) throws DaoException {
        Tva tva = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM tva WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                tva = new Tva();
                tva.setId(id);
                tva.setNom(resultat.getString("nom"));
                tva.setLibelle(resultat.getString("libelle"));
                tva.setTaux(resultat.getDouble("taux"));
                tva.setUsermodification(resultat.getString("usermodification"));
                tva.setDatemodification(resultat.getDate("datemodification"));
                tva.setPgmmodification(resultat.getString("pgmmodification"));
                tva.setUsercreation(resultat.getString("usercreation"));
                tva.setDatecreation(resultat.getDate("datecreation"));
                tva.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table Tva"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return tva;
    }

    	// =================================================================================
		// TROUVER TVA PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomTva(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM tva WHERE nom=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table Tva"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE TVA
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheTvas(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM tva ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
				/*
				 * Tva tva = new Tva(); tva.setId(rs.getInt("id"));
				 * tva.setNom(rs.getString("nom")); tva.setLibelle(rs.getString("libelle"));
				 * tva.setTaux(rs.getDouble("taux")); list.add(tva);
				 */
                Map<String, Object> tvaFields = new LinkedHashMap<>();
                tvaFields.put("id", rs.getInt("id"));
                tvaFields.put("nom", rs.getString("nom"));
                tvaFields.put("libelle", rs.getString("libelle"));
                tvaFields.put("taux", rs.getDouble("taux"));
                list.add(tvaFields);
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
		// RECHERCHE TVAS SUIVANT LIKE
		// =================================================================================
    @Override
    public List<Map<String, Object>>  rechercheLikeTvas(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM tva WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> tvaFields = new LinkedHashMap<>();
                tvaFields.put("id", rs.getInt("id"));
                tvaFields.put("nom", rs.getString("nom"));
                tvaFields.put("libelle", rs.getString("libelle"));
                tvaFields.put("taux", rs.getDouble("taux"));
                list.add(tvaFields);
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
	// RECUPERATION N° ENREGISTREMENT ENCOURS TVA
	// =================================================================================
    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    @Override
    public String getStringRecords() {
    	String StringRecords = Integer.toString(noOfRecords);
        return StringRecords;
    }

    @Override
    public Integer getIntegerRecords() {
    	Integer integerRecords = noOfRecords;
        return integerRecords;
    }


    // =================================================================================
	// FERMETURE DES RESSOURCES TVA
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