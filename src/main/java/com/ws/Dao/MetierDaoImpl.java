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
import com.ws.beans.Metier;
import com.ws.configuration.DatasourceH;

public class MetierDaoImpl implements MetierDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  MetierDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER METIER
		// =================================================================================
    @Override
    public void ajouterMetier(Metier metier) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion  = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO metier(nom, domaine, libelle, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, metier.getNom());
            preparedStatement.setString(2, metier.getLibelle());
            preparedStatement.setString(3, metier.getDomaine());
            preparedStatement.setString(4, "MetierDao");
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table Metier"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER METIER
		// =================================================================================
    @Override
    public void modifierMetier(Metier metier) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE metier SET nom=?, libelle=?, domaine=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, metier.getNom());
            preparedStatement.setString(2, metier.getLibelle());
            preparedStatement.setString(3, metier.getDomaine());
            preparedStatement.setString(4, "MetierDao");
            preparedStatement.setString(5, dateTime);
            preparedStatement.setString(6, System.getProperty("user.name"));
            preparedStatement.setInt(7, metier.getId());
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table Metier"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER METIER
  		// =================================================================================
    @Override
    public void copierMetier(Metier metier) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO metier(nom, domaine, libelle, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, metier.getNom());
            preparedStatement.setString(2, metier.getLibelle());
            preparedStatement.setString(3, metier.getDomaine());
            preparedStatement.setString(4, "MetierDao");
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table Metier"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER METIER
  		// =================================================================================

    @Override
    public void supprimerMetier(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM metier WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table Metier"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER METIER
 		// =================================================================================
 		@Override
 		public void renommerMetier(Metier metier) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();

 				preparedStatement = connexion.prepareStatement(
 						"UPDATE metier SET metier.nom=?, metier.pgmmodification=?, metier.datemodification=?,"
 								+ " metier.usermodification=? where metier.id=?;");
 				preparedStatement.setString(1, metier.getNom());
 				preparedStatement.setString(2, "RENOMMER METIER");
 				preparedStatement.setString(3, dateTime);
 				preparedStatement.setString(4, System.getProperty("user.name"));
 				preparedStatement.setInt(5, metier.getId());
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
  	// LISTER DES METIERS
  	// =================================================================================
    @Override
    public List<Metier> listerMetier() throws DaoException {
        List<Metier> metiers = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, nom, libelle, domaine FROM metier;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Metier metier = new Metier();
                metier.setId(resultat.getInt("id"));
                metier.setNom(resultat.getString("nom"));
                metier.setNom(resultat.getString("libelle"));
                metier.setDomaine(resultat.getString("domaine"));
                metiers.add(metier);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Metier"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return metiers;
    }
    @Override
    public List<Metier> listerMetierClient() throws DaoException {
    	 return listerMetier();
    }
    @Override
    public List<Metier> listerMetierFournisseur() throws DaoException {
    	 return listerMetier();
    }
    @Override
    public List<Metier> listerMetierSalarie() throws DaoException {
        return listerMetier();
    }
    	// =================================================================================
		// TROUVER METIER PAR ID
		// =================================================================================
    @Override
    public Metier trouverMetier(Integer id) throws DaoException {
        Metier metier = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM metier WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                metier = new Metier();
                metier.setId(id);
                metier.setNom(resultat.getString("nom"));
                metier.setLibelle(resultat.getString("libelle"));
                metier.setDomaine(resultat.getString("domaine"));
                metier.setUsermodification(resultat.getString("usermodification"));
                metier.setDatemodification(resultat.getDate("datemodification"));
                metier.setPgmmodification(resultat.getString("pgmmodification"));
                metier.setUsercreation(resultat.getString("usercreation"));
                metier.setDatecreation(resultat.getDate("datecreation"));
                metier.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table Metier"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return metier;
    }

    	// =================================================================================
		// TROUVER METIER PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomMetier(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM metier WHERE nom=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table Metier"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE METIER
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheMetiers(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM metier ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
				/*
				 * Metier metier = new Metier(); metier.setId(rs.getInt("id"));
				 * metier.setNom(rs.getString("nom")); metier.setDomaine(rs.getString("domaine"));
				 * metier.setTaux(rs.getDouble("taux")); list.add(metier);
				 */
                Map<String, Object> metierFields = new LinkedHashMap<>();
                metierFields.put("id", rs.getInt("id"));
                metierFields.put("nom", rs.getString("nom"));
                metierFields.put("libelle", rs.getString("libelle"));
                metierFields.put("domaine", rs.getString("domaine"));
                list.add(metierFields);
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
		// RECHERCHE METIERS SUIVANT LIKE
		// =================================================================================
    @Override
    public List<Map<String, Object>>  rechercheLikeMetiers(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM metier WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> metierFields = new LinkedHashMap<>();
                metierFields.put("id", rs.getInt("id"));
                metierFields.put("nom", rs.getString("nom"));
                metierFields.put("libelle", rs.getString("libelle"));
                metierFields.put("domaine", rs.getString("domaine"));
                list.add(metierFields);
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
	// RECUPERATION N° ENREGISTREMENT ENCOURS METIER
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
	// FERMETURE DES RESSOURCES METIER
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