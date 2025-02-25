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

import com.ws.beans.TypeUtilisateur;

public class TypeUtilisateurDaoImpl implements TypeUtilisateurDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  TypeUtilisateurDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER TYPEUTILISATEUR
		// =================================================================================
    @Override
    public void ajouterTypeUtilisateur(TypeUtilisateur typeUtilisateur) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion  = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO typeUtilisateur(nom, libelle, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, typeUtilisateur.getNom());
            preparedStatement.setString(2, typeUtilisateur.getLibelle());
            preparedStatement.setString(3, "TypeUtilisateurDao");
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table TypeUtilisateur"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER TYPEUTILISATEUR
		// =================================================================================
    @Override
    public void modifierTypeUtilisateur(TypeUtilisateur typeUtilisateur) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE typeUtilisateur SET nom=?, libelle=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, typeUtilisateur.getNom());
            preparedStatement.setString(2, typeUtilisateur.getLibelle());
            preparedStatement.setString(3, "TypeUtilisateurDao");
            preparedStatement.setString(4, dateTime);
            preparedStatement.setString(5, System.getProperty("user.name"));
            preparedStatement.setInt(6, typeUtilisateur.getId());
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table TypeUtilisateur"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER TYPEUTILISATEUR
  		// =================================================================================
    @Override
    public void copierTypeUtilisateur(TypeUtilisateur typeUtilisateur) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO typeUtilisateur(nom, libelle, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, typeUtilisateur.getNom());
            preparedStatement.setString(2, typeUtilisateur.getLibelle());
            preparedStatement.setString(3, "TypeUtilisateurDao");
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table TypeUtilisateur"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER TYPEUTILISATEUR
  		// =================================================================================

    @Override
    public void supprimerTypeUtilisateur(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM typeUtilisateur WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table TypeUtilisateur"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER TYPEUTILISATEUR
 		// =================================================================================
 		@Override
 		public void renommerTypeUtilisateur(TypeUtilisateur typeUtilisateur) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();

 				preparedStatement = connexion.prepareStatement(
 						"UPDATE typeUtilisateur SET typeUtilisateur.nom=?, typeUtilisateur.pgmmodification=?, typeUtilisateur.datemodification=?,"
 								+ " typeUtilisateur.usermodification=? where typeUtilisateur.id=?;");
 				preparedStatement.setString(1, typeUtilisateur.getNom());
 				preparedStatement.setString(2, "RENOMMER TYPEUTILISATEUR");
 				preparedStatement.setString(3, dateTime);
 				preparedStatement.setString(4, System.getProperty("user.name"));
 				preparedStatement.setInt(5, typeUtilisateur.getId());
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
  	// LISTER DES STATUTS
  	// =================================================================================
    @Override
    public List<TypeUtilisateur> listerTypeUtilisateur() throws DaoException {
        List<TypeUtilisateur> typeUtilisateurs = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, nom, libelle FROM typeUtilisateur;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeUtilisateur typeUtilisateur = new TypeUtilisateur();
                typeUtilisateur.setId(resultat.getInt("id"));
                typeUtilisateur.setNom(resultat.getString("nom"));
                typeUtilisateur.setLibelle(resultat.getString("libelle"));
                typeUtilisateurs.add(typeUtilisateur);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table TypeUtilisateur"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeUtilisateurs;
    }

    @Override
    public List<TypeUtilisateur> listerTypeUtilisateurClient() throws DaoException {
        return listerTypeUtilisateur();
    }

    @Override
    public List<TypeUtilisateur> listerTypeUtilisateurFournisseur() throws DaoException {
        return listerTypeUtilisateur();
    }

    @Override
    public List<TypeUtilisateur> listerTypeUtilisateurSalarie() throws DaoException {
        return listerTypeUtilisateur();
    }
    	// =================================================================================
		// TROUVER TYPEUTILISATEUR PAR ID
		// =================================================================================
    @Override
    public TypeUtilisateur trouverTypeUtilisateur(Integer id) throws DaoException {
        TypeUtilisateur typeUtilisateur = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM typeUtilisateur WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                typeUtilisateur = new TypeUtilisateur();
                typeUtilisateur.setId(id);
                typeUtilisateur.setNom(resultat.getString("nom"));
                typeUtilisateur.setLibelle(resultat.getString("libelle"));
                typeUtilisateur.setUsermodification(resultat.getString("usermodification"));
                typeUtilisateur.setDatemodification(resultat.getDate("datemodification"));
                typeUtilisateur.setPgmmodification(resultat.getString("pgmmodification"));
                typeUtilisateur.setUsercreation(resultat.getString("usercreation"));
                typeUtilisateur.setDatecreation(resultat.getDate("datecreation"));
                typeUtilisateur.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table TypeUtilisateur"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return typeUtilisateur;
    }

    	// =================================================================================
		// TROUVER TYPEUTILISATEUR PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomTypeUtilisateur(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM typeUtilisateur WHERE nom=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table TypeUtilisateur"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE TYPEUTILISATEUR
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheTypeUtilisateurs(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM typeUtilisateur ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Map<String, Object> typeUtilisateurFields = new LinkedHashMap<>();
                typeUtilisateurFields.put("id", rs.getInt("id"));
                typeUtilisateurFields.put("nom", rs.getString("nom"));
                typeUtilisateurFields.put("libelle", rs.getString("libelle"));
                list.add(typeUtilisateurFields);
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
    public List<Map<String, Object>>  rechercheLikeTypeUtilisateurs(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM typeUtilisateur WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> typeUtilisateurFields = new LinkedHashMap<>();
                typeUtilisateurFields.put("id", rs.getInt("id"));
                typeUtilisateurFields.put("nom", rs.getString("nom"));
                typeUtilisateurFields.put("libelle", rs.getString("libelle"));
                list.add(typeUtilisateurFields);
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
	// RECUPERATION N° ENREGISTREMENT ENCOURS TYPEUTILISATEUR
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
	// FERMETURE DES RESSOURCES TYPEUTILISATEUR
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