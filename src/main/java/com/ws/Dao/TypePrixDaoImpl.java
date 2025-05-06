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

import com.ws.beans.ContratAncien;
import com.ws.beans.TypePrix;
import com.ws.configuration.DatasourceH;

public class TypePrixDaoImpl implements TypePrixDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  TypePrixDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER TYPEPRIX
		// =================================================================================
    @Override
    public void ajouterTypePrix(TypePrix typePrix) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion  = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO typePrix(nom, libelle, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, typePrix.getNom());
            preparedStatement.setString(2, typePrix.getLibelle());
            preparedStatement.setString(3, "TypePrixDao");
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table TypePrix"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER TYPEPRIX
		// =================================================================================
    @Override
    public void modifierTypePrix(TypePrix typePrix) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE typePrix SET nom=?, libelle=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, typePrix.getNom());
            preparedStatement.setString(2, typePrix.getLibelle());
            preparedStatement.setString(3, "TypePrixDao");
            preparedStatement.setString(4, dateTime);
            preparedStatement.setString(5, System.getProperty("user.name"));
            preparedStatement.setInt(6, typePrix.getId());
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table TypePrix"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER TYPEPRIX
  		// =================================================================================
    @Override
    public void copierTypePrix(TypePrix typePrix) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO typePrix(nom, libelle, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, typePrix.getNom());
            preparedStatement.setString(2, typePrix.getLibelle());
            preparedStatement.setString(3, "TypePrixDao");
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table TypePrix"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER TYPEPRIX
  		// =================================================================================

    @Override
    public void supprimerTypePrix(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM typePrix WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table TypePrix"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER TYPEPRIX
 		// =================================================================================
 		@Override
 		public void renommerTypePrix(TypePrix typePrix) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();

 				preparedStatement = connexion.prepareStatement(
 						"UPDATE typePrix SET typePrix.nom=?, typePrix.pgmmodification=?, typePrix.datemodification=?,"
 								+ " typePrix.usermodification=? where typePrix.id=?;");
 				preparedStatement.setString(1, typePrix.getNom());
 				preparedStatement.setString(2, "RENOMMER TYPEPRIX");
 				preparedStatement.setString(3, dateTime);
 				preparedStatement.setString(4, System.getProperty("user.name"));
 				preparedStatement.setInt(5, typePrix.getId());
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
  	// LISTER DES TYPEPRIXS
  	// =================================================================================
    @Override
    public List<TypePrix> listerTypePrix() throws DaoException {
        List<TypePrix> typePrixs = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, nom, libelle FROM typePrix;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypePrix typePrix = new TypePrix();
                typePrix.setId(resultat.getInt("id"));
                typePrix.setNom(resultat.getString("nom"));
                typePrix.setLibelle(resultat.getString("libelle"));
                typePrixs.add(typePrix);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table TypePrix"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typePrixs;
    }
    @Override
    public List<TypePrix> listerTypePrixClient() throws DaoException {
        return listerTypePrix();
    }
    @Override
    public List<TypePrix> listerTypePrixFournisseur() throws DaoException {
        return listerTypePrix();
    }

    @Override
    public List<TypePrix> listerTypePrixSalarie() throws DaoException {
    	return listerTypePrix();
    }

    	// =================================================================================
		// TROUVER TYPEPRIX PAR ID
		// =================================================================================
    @Override
    public TypePrix trouverTypePrix(Integer id) throws DaoException {
        TypePrix typePrix = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM typePrix WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                typePrix = new TypePrix();
                typePrix.setId(id);
                typePrix.setNom(resultat.getString("nom"));
                typePrix.setLibelle(resultat.getString("libelle"));
                typePrix.setUsermodification(resultat.getString("usermodification"));
                typePrix.setDatemodification(resultat.getDate("datemodification"));
                typePrix.setPgmmodification(resultat.getString("pgmmodification"));
                typePrix.setUsercreation(resultat.getString("usercreation"));
                typePrix.setDatecreation(resultat.getDate("datecreation"));
                typePrix.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table TypePrix"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return typePrix;
    }

    	// =================================================================================
		// TROUVER TYPEPRIX PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomTypePrix(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM typePrix WHERE nom=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table TypePrix"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE TYPEPRIX
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheTypePrixs(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_typePrix, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM typePrix ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Map<String, Object> typePrixFields = new LinkedHashMap<>();
                typePrixFields.put("id", rs.getInt("id"));
                typePrixFields.put("nom", rs.getString("nom"));
                typePrixFields.put("libelle", rs.getString("libelle"));
                list.add(typePrixFields);
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
		// RECHERCHE TYPEPRIXS SUIVANT LIKE
		// =================================================================================
    @Override
    public List<Map<String, Object>>  rechercheLikeTypePrixs(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_typePrix, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM typePrix WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> typePrixFields = new LinkedHashMap<>();
                typePrixFields.put("id", rs.getInt("id"));
                typePrixFields.put("nom", rs.getString("nom"));
                typePrixFields.put("libelle", rs.getString("libelle"));
                list.add(typePrixFields);
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
	// RECUPERATION N° ENREGISTREMENT ENCOURS TYPEPRIX
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
	// FERMETURE DES RESSOURCES TYPEPRIX
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