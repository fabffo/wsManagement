package com.ws.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ws.beans.Contrat;
import com.ws.beans.ParametreSysteme;
import com.ws.configuration.DatasourceH;

public class ParametreSystemeDaoImpl implements ParametreSystemeDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  ParametreSystemeDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER ENTETE PARAMETETRE SYSTEME
		// =================================================================================
    @Override
    public void ajouterParametreSysteme(ParametreSysteme parametreSysteme) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion  = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO parametreSysteme(nom, libelle, nom_ecran_gestion, nom_ecran_crud, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, parametreSysteme.getNom());
            preparedStatement.setString(2, parametreSysteme.getLibelle());
            preparedStatement.setString(3, parametreSysteme.getNom_ecran_gestion());
            preparedStatement.setString(4, parametreSysteme.getNom_ecran_crud());
            preparedStatement.setString(5, "ParametreSys_entete");
            preparedStatement.setString(6, dateTime);
            preparedStatement.setString(7, System.getProperty("user.name"));
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table ParametreSysteme"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER ENTETE PARAMETRE
		// =================================================================================
    @Override
    public void modifierParametreSysteme(ParametreSysteme parametreSysteme) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE parametreSysteme SET nom=?, libelle=?, Nom_ecran_gestion=?, Nom_ecran_crud=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, parametreSysteme.getNom());
            preparedStatement.setString(2, parametreSysteme.getLibelle());
            preparedStatement.setString(3, parametreSysteme.getNom_ecran_gestion());
            preparedStatement.setString(4, parametreSysteme.getNom_ecran_crud());
            preparedStatement.setString(5, "ParametreSys_entete");
            preparedStatement.setString(6, dateTime);
            preparedStatement.setString(7, System.getProperty("user.name"));
            preparedStatement.setInt(8, parametreSysteme.getId());
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table ParametreSysteme"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER ENTETE PARAMETRE
  		// =================================================================================
    @Override
    public void copierParametreSysteme(ParametreSysteme parametreSysteme) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO parametreSysteme(nom, libelle, nom_ecran_gestion, nom_ecran_crud, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, parametreSysteme.getNom());
            preparedStatement.setString(2, parametreSysteme.getLibelle());
            preparedStatement.setString(3, parametreSysteme.getNom_ecran_gestion());
            preparedStatement.setString(4, parametreSysteme.getNom_ecran_crud());
            preparedStatement.setString(5, "ParametreSys_entete");
            preparedStatement.setString(6, dateTime);
            preparedStatement.setString(7, System.getProperty("user.name"));
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table ParametreSysteme"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER ENTETE PARAMETRE
  		// =================================================================================

    @Override
    public void supprimerParametreSysteme(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM parametreSysteme WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table ParametreSysteme"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER ENTETE PARAMETRE
 		// =================================================================================
 		@Override
 		public void renommerParametreSysteme(ParametreSysteme parametreSysteme) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();

 				preparedStatement = connexion.prepareStatement(
 						"UPDATE parametreSysteme SET parametreSysteme.nom=?, parametreSysteme.libelle=?, parametreSysteme.nom_ecran_gestion=?, parametreSysteme.nom_ecran_crud=?, parametreSysteme.pgmmodification=?, parametreSysteme.datemodification=?,"
 								+ " parametreSysteme.usermodification=? where parametreSysteme.id=?;");
 				preparedStatement.setString(1, parametreSysteme.getNom());
 				preparedStatement.setString(2, parametreSysteme.getLibelle());
 				preparedStatement.setString(3, parametreSysteme.getNom_ecran_gestion());
 				preparedStatement.setString(4, parametreSysteme.getNom_ecran_crud());
 				preparedStatement.setString(5, "ParametreSys_entete");
 				preparedStatement.setString(6, dateTime);
 				preparedStatement.setString(7, System.getProperty("user.name"));
 				preparedStatement.setInt(8, parametreSysteme.getId());
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
    public List<ParametreSysteme> listerParametreSysteme() throws DaoException {
        List<ParametreSysteme> parametreSystemes = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, nom, libelle, nom_ecran_gestion, nom_ecran_crud FROM parametreSysteme;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                ParametreSysteme parametreSysteme = new ParametreSysteme();
                parametreSysteme.setId(resultat.getInt("id"));
                parametreSysteme.setNom(resultat.getString("nom"));
                parametreSysteme.setLibelle(resultat.getString("libelle"));
                parametreSysteme.setNom_ecran_gestion(resultat.getString("nom_ecran_gestion"));
                parametreSysteme.setNom_ecran_crud(resultat.getString("nom_ecran_crud"));
                parametreSystemes.add(parametreSysteme);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table ParametreSysteme"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return parametreSystemes;
    }

    	// =================================================================================
		// TROUVER ENTETE PARAMETRE PAR ID
		// =================================================================================
    @Override
    public ParametreSysteme trouverParametreSysteme(Integer id) throws DaoException {
        ParametreSysteme parametreSysteme = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM parametreSysteme WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                parametreSysteme = new ParametreSysteme();
                parametreSysteme.setId(id);
                parametreSysteme.setNom(resultat.getString("nom"));
                parametreSysteme.setLibelle(resultat.getString("libelle"));
                parametreSysteme.setNom_ecran_gestion(resultat.getString("nom_ecran_gestion"));
                parametreSysteme.setNom_ecran_crud(resultat.getString("nom_ecran_crud"));
                parametreSysteme.setUsermodification(resultat.getString("usermodification"));
                parametreSysteme.setDatemodification(resultat.getDate("datemodification"));
                parametreSysteme.setPgmmodification(resultat.getString("pgmmodification"));
                parametreSysteme.setUsercreation(resultat.getString("usercreation"));
                parametreSysteme.setDatecreation(resultat.getDate("datecreation"));
                parametreSysteme.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table ParametreSysteme"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return parametreSysteme;
    }

    	// =================================================================================
		// TROUVER ENTETE PARAMETRE PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomParametreSysteme(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM parametreSysteme WHERE nom=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table ParametreSysteme"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE ENTETE PARAMETRE
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheParametreSystemes(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM parametreSysteme ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Map<String, Object> entiteFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                entiteFields.put("id", rs.getInt("id"));
                }
                if (dictionnaire_nom_colonne.containsKey("nom")) {
                entiteFields.put("nom", rs.getString("nom"));
                }
                if (dictionnaire_nom_colonne.containsKey("libelle")) {
                entiteFields.put("libelle", rs.getString("libelle"));
                }
                if (dictionnaire_nom_colonne.containsKey("nom_ecran_gestion")) {
                entiteFields.put("nom_ecran_gestion", rs.getString("nom_ecran_gestion"));
                }
                if (dictionnaire_nom_colonne.containsKey("nom_ecran_crud")) {
                entiteFields.put("nom_ecran_crud", rs.getString("nom_ecran_crud"));
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
    public List<Map<String, Object>> rechercheLikeParametreSystemes(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM parametreSysteme WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> entiteFields = new LinkedHashMap<>();
            	if (dictionnaire_nom_colonne.containsKey("id")) {
                    entiteFields.put("id", rs.getInt("id"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("nom")) {
                    entiteFields.put("nom", rs.getString("nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("libelle")) {
                    entiteFields.put("libelle", rs.getString("libelle"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("nom_ecran_gestion")) {
                    entiteFields.put("nom_ecran_gestion", rs.getString("nom_ecran_gestion"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("nom_ecran_crud")) {
                    entiteFields.put("nom_ecran_crud", rs.getString("nom_ecran_crud"));
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