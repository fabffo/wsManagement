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
import com.ws.beans.Entite;
import com.ws.configuration.DatasourceH;

public class EntiteDaoImpl implements EntiteDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  EntiteDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER ENTITE
		// =================================================================================
    @Override
    public void ajouterEntite(Entite entite) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion  = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO entite(nom, libelle, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, entite.getNom());
            preparedStatement.setString(2, entite.getLibelle());
            preparedStatement.setString(3, "EntiteDao");
            preparedStatement.setString(4, dateTime);
            preparedStatement.setString(5, System.getProperty("user.name"));
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table Entite"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER ENTITE
		// =================================================================================
    @Override
    public void modifierEntite(Entite entite) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE entite SET nom=?, libelle=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, entite.getNom());
            preparedStatement.setString(2, entite.getLibelle());
            preparedStatement.setString(3, "EntiteDao");
            preparedStatement.setString(4, dateTime);
            preparedStatement.setString(5, System.getProperty("user.name"));
            preparedStatement.setInt(6, entite.getId());
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table Entite"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER ENTITE
  		// =================================================================================
    @Override
    public void copierEntite(Entite entite) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO entite(nom, libelle, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, entite.getNom());
            preparedStatement.setString(2, entite.getLibelle());
            preparedStatement.setString(3, "EntiteDao");
            preparedStatement.setString(4, dateTime);
            preparedStatement.setString(5, System.getProperty("user.name"));
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table Entite"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER ENTITE
  		// =================================================================================

    @Override
    public void supprimerEntite(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM entite WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table Entite"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER ENTITE
 		// =================================================================================
 		@Override
 		public void renommerEntite(Entite entite) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();

 				preparedStatement = connexion.prepareStatement(
 						"UPDATE entite SET entite.nom=?, entite.pgmmodification=?, entite.datemodification=?,"
 								+ " entite.usermodification=? where entite.id=?;");
 				preparedStatement.setString(1, entite.getNom());
 				preparedStatement.setString(2, "RENOMMER ENTITE");
 				preparedStatement.setString(3, dateTime);
 				preparedStatement.setString(4, System.getProperty("user.name"));
 				preparedStatement.setInt(5, entite.getId());
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
  	// LISTER DES ENTITES
  	// =================================================================================
    @Override
    public List<Entite> listerEntite() throws DaoException {
        List<Entite> entites = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, nom, libelle FROM entite;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Entite entite = new Entite();
                entite.setId(resultat.getInt("id"));
                entite.setNom(resultat.getString("nom"));
                entite.setLibelle(resultat.getString("libelle"));
                entites.add(entite);
                }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Entite"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return entites;
    }

    	// =================================================================================
		// TROUVER ENTITE PAR ID
		// =================================================================================
    @Override
    public Entite trouverEntite(Integer id) throws DaoException {
        Entite entite = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM entite WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                entite = new Entite();
                entite.setId(id);
                entite.setNom(resultat.getString("nom"));
                entite.setLibelle(resultat.getString("libelle"));
                entite.setUsermodification(resultat.getString("usermodification"));
                entite.setDatemodification(resultat.getDate("datemodification"));
                entite.setPgmmodification(resultat.getString("pgmmodification"));
                entite.setUsercreation(resultat.getString("usercreation"));
                entite.setDatecreation(resultat.getDate("datecreation"));
                entite.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table Entite"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return entite;
    }

    	// =================================================================================
		// TROUVER ENTITE PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomEntite(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM entite WHERE nom=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table Entite"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE ENTITE
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheEntites(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM entite ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Map<String, Object> entiteFields = new LinkedHashMap<>();
                entiteFields.put("id", rs.getInt("id"));
                entiteFields.put("nom", rs.getString("nom"));
                entiteFields.put("libelle", rs.getString("libelle"));
                list.add(entiteFields);
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
		// RECHERCHE ENTITES SUIVANT LIKE
		// =================================================================================
    @Override
    public List<Map<String, Object>>  rechercheLikeEntites(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM entite WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> entiteFields = new LinkedHashMap<>();
                entiteFields.put("id", rs.getInt("id"));
                entiteFields.put("nom", rs.getString("nom"));
                entiteFields.put("libelle", rs.getString("libelle"));
                list.add(entiteFields);
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
	// RECUPERATION N° ENREGISTREMENT ENCOURS ENTITE
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
	// FERMETURE DES RESSOURCES ENTITE
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