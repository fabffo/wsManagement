package com.ws.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ws.beans.ContratAncien;
import com.ws.beans.Parametre_ecranCrud_entete;
import com.ws.configuration.DatasourceH;

public class Parametre_ecranCrud_enteteDaoImpl implements Parametre_ecranCrud_enteteDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  Parametre_ecranCrud_enteteDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER ENTETE PARAMETETRE SYSTEME
		// =================================================================================
    @Override
    public void ajouterParametre_ecranCrud_entete(Parametre_ecranCrud_entete parametre_ecranCrud_entete) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion  = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO parametre_ecranCrud_entete(parametreSysteme, nom_programme, largeur_ecran, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, parametre_ecranCrud_entete.getParametreSysteme());
            preparedStatement.setString(2, parametre_ecranCrud_entete.getNom_programme());
            preparedStatement.setInt(3, parametre_ecranCrud_entete.getLargeur_ecran());
            preparedStatement.setString(4, "ParametreSys_entete");
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table Parametre_ecranCrud_entete"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER ENTETE PARAMETRE
		// =================================================================================
    @Override
    public void modifierParametre_ecranCrud_entete(Parametre_ecranCrud_entete parametre_ecranCrud_entete) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE parametre_ecranCrud_entete SET parametreSysteme=?, nom_programme=?, largeur_ecran=?,  pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, parametre_ecranCrud_entete.getParametreSysteme());
            preparedStatement.setString(2, parametre_ecranCrud_entete.getNom_programme());
            preparedStatement.setInt(3, parametre_ecranCrud_entete.getLargeur_ecran());
            preparedStatement.setString(4, "ParametreSys_entete");
            preparedStatement.setString(5, dateTime);
            preparedStatement.setString(6, System.getProperty("user.name"));
            preparedStatement.setInt(7, parametre_ecranCrud_entete.getId());
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table Parametre_ecranCrud_entete"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER ENTETE PARAMETRE
  		// =================================================================================
    @Override
    public void copierParametre_ecranCrud_entete(Parametre_ecranCrud_entete parametre_ecranCrud_entete) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO parametre_ecranCrud_entete(parametreSysteme, nom_programme, largeur_ecran, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, parametre_ecranCrud_entete.getParametreSysteme());
            preparedStatement.setString(2, parametre_ecranCrud_entete.getNom_programme());
            preparedStatement.setInt(3, parametre_ecranCrud_entete.getLargeur_ecran());
            preparedStatement.setString(4, "ParametreSys_entete");
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table Parametre_ecranCrud_entete"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER ENTETE PARAMETRE
  		// =================================================================================

    @Override
    public void supprimerParametre_ecranCrud_entete(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM parametre_ecranCrud_entete WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table Parametre_ecranCrud_entete"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER ENTETE PARAMETRE
 		// =================================================================================
 		@Override
 		public void renommerParametre_ecranCrud_entete(Parametre_ecranCrud_entete parametre_ecranCrud_entete) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();

 				preparedStatement = connexion.prepareStatement(
 						"UPDATE parametre_ecranCrud_entete SET nom_programme=?, pgmmodification=?, datemodification=?, usermodification=? where id=?;");
 	            preparedStatement.setString(1, parametre_ecranCrud_entete.getNom_programme());
 				preparedStatement.setString(2, "ParametreSys_entete");
 				preparedStatement.setString(3, dateTime);
 				preparedStatement.setString(4, System.getProperty("user.name"));
 				preparedStatement.setInt(5, parametre_ecranCrud_entete.getId());
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
  	// LISTER DES ENTETE PARAMETRES
  	// =================================================================================
    @Override
    public List<Parametre_ecranCrud_entete> listerParametre_ecranCrud_entete() throws DaoException {
        List<Parametre_ecranCrud_entete> parametre_ecranCrud_entetes = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, parametreSysteme, nom_programme, largeur_ecran FROM parametre_ecranCrud_entete;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Parametre_ecranCrud_entete parametre_ecranCrud_entete = new Parametre_ecranCrud_entete();
                parametre_ecranCrud_entete.setId(resultat.getInt("id"));
                parametre_ecranCrud_entete.setParametreSysteme(resultat.getInt("parametreSysteme"));
                parametre_ecranCrud_entete.setNom_programme(resultat.getString("nom_programme"));
                parametre_ecranCrud_entete.setLargeur_ecran(resultat.getInt("largeur_ecran"));
                parametre_ecranCrud_entetes.add(parametre_ecranCrud_entete);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Parametre_ecranCrud_entete"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return parametre_ecranCrud_entetes;
    }

    	// =================================================================================
		// TROUVER ENTETE PARAMETRE PAR ID
		// =================================================================================
    @Override
    public Parametre_ecranCrud_entete trouverParametre_ecranCrud_entete(Integer id) throws DaoException {
        Parametre_ecranCrud_entete parametre_ecranCrud_entete = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT parametre_ecranCrud_entete.id, parametre_ecranCrud_entete.parametreSysteme, nom_programme, largeur_ecran, parametreSysteme.nom, parametre_ecranCrud_entete.pgmcreation, parametre_ecranCrud_entete.datecreation, parametre_ecranCrud_entete.usercreation, parametre_ecranCrud_entete.pgmmodification, parametre_ecranCrud_entete.datemodification, parametre_ecranCrud_entete.usermodification FROM parametre_ecranCrud_entete inner join parametreSysteme on parametreSysteme.id=parametre_ecranCrud_entete.parametreSysteme  WHERE parametre_ecranCrud_entete.id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, id.toString());
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                parametre_ecranCrud_entete = new Parametre_ecranCrud_entete();
                parametre_ecranCrud_entete.setId(id);
                parametre_ecranCrud_entete.setParametreSysteme(resultat.getInt("parametreSysteme"));
                parametre_ecranCrud_entete.setNom_programme(resultat.getString("nom_programme"));
                parametre_ecranCrud_entete.setLargeur_ecran(resultat.getInt("largeur_ecran"));
                parametre_ecranCrud_entete.setUsermodification(resultat.getString("parametre_ecranCrud_entete.usermodification"));
                parametre_ecranCrud_entete.setDatemodification(resultat.getDate("parametre_ecranCrud_entete.datemodification"));
                parametre_ecranCrud_entete.setPgmmodification(resultat.getString("parametre_ecranCrud_entete.pgmmodification"));
                parametre_ecranCrud_entete.setUsercreation(resultat.getString("parametre_ecranCrud_entete.usercreation"));
                parametre_ecranCrud_entete.setDatecreation(resultat.getDate("parametre_ecranCrud_entete.datecreation"));
                parametre_ecranCrud_entete.setPgmcreation(resultat.getString("parametre_ecranCrud_entete.pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table Parametre_ecranCrud_entete"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return parametre_ecranCrud_entete;
    }

    	// =================================================================================
		// TROUVER ENTETE PARAMETRE PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomParametre_ecranCrud_entete(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM parametre_ecranCrud_entete WHERE nom_programme=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table Parametre_ecranCrud_entete"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE ENTETE PARAMETRE
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheParametre_ecranCrud_entetes(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS parametre_ecranCrud_entete.id, parametre_ecranCrud_entete.parametreSysteme, nom_programme, largeur_ecran, parametreSysteme.nom FROM parametre_ecranCrud_entete inner join parametreSysteme on parametreSysteme.id=parametre_ecranCrud_entete.parametreSysteme ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Map<String, Object> entiteFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                entiteFields.put("id", rs.getInt("id"));
                }
                if (dictionnaire_nom_colonne.containsKey("parametreSysteme")) {
                entiteFields.put("parametreSysteme", rs.getString("nom"));
                }
                if (dictionnaire_nom_colonne.containsKey("nom_programme")) {
                entiteFields.put("nom_programme", rs.getString("nom_programme"));
                }
                if (dictionnaire_nom_colonne.containsKey("largeur_ecran")) {
                entiteFields.put("largeur_ecran", rs.getInt("largeur_ecran"));
                }
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
		// RECHERCHE ENTETE PARAMETRES SUIVANT LIKE
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheLikeParametre_ecranCrud_entetes(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS  parametre_ecranCrud_entete.id, parametre_ecranCrud_entete.parametreSysteme, nom_programme, largeur_ecran, parametreSysteme.nom FROM parametre_ecranCrud_entete inner join parametreSysteme on parametreSysteme.id=parametre_ecranCrud_entete.parametreSysteme WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> entiteFields = new LinkedHashMap<>();
            	if (dictionnaire_nom_colonne.containsKey("id")) {
                    entiteFields.put("id", rs.getInt("id"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("parametreSysteme")) {
                    entiteFields.put("nom", rs.getString("nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("nom_programme")) {
                    entiteFields.put("nom_programme", rs.getString("nom_programme"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("largeur_ecran")) {
                    entiteFields.put("largeur_ecran", rs.getInt("largeur_ecran"));
                    }
                    list.add(entiteFields);
            }

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
	// RECUPERATION N° ENREGISTREMENT ENCOURS ENTETE PARAMETRE
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
	// FERMETURE DES RESSOURCES ENTETE PARAMETRE
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