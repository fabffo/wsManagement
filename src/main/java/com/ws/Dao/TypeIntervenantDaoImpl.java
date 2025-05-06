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

import com.ws.beans.TypeIntervenant;

public class TypeIntervenantDaoImpl implements TypeIntervenantDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  TypeIntervenantDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER TYPEINTERVENANT
		// =================================================================================
    @Override
    public void ajouterTypeIntervenant(TypeIntervenant typeIntervenant) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion  = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO typeIntervenant(nom, libelle, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, typeIntervenant.getNom());
            preparedStatement.setString(2, typeIntervenant.getLibelle());
            preparedStatement.setString(3, "TypeIntervenantDao");
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table TypeIntervenant"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER TYPEINTERVENANT
		// =================================================================================
    @Override
    public void modifierTypeIntervenant(TypeIntervenant typeIntervenant) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE typeIntervenant SET nom=?, libelle=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, typeIntervenant.getNom());
            preparedStatement.setString(2, typeIntervenant.getLibelle());
            preparedStatement.setString(3, "TypeIntervenantDao");
            preparedStatement.setString(4, dateTime);
            preparedStatement.setString(5, System.getProperty("user.name"));
            preparedStatement.setInt(6, typeIntervenant.getId());
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table TypeIntervenant"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER TYPEINTERVENANT
  		// =================================================================================
    @Override
    public void copierTypeIntervenant(TypeIntervenant typeIntervenant) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO typeIntervenant(nom, libelle, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, typeIntervenant.getNom());
            preparedStatement.setString(2, typeIntervenant.getLibelle());
            preparedStatement.setString(3, "TypeIntervenantDao");
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table TypeIntervenant"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER TYPEINTERVENANT
  		// =================================================================================

    @Override
    public void supprimerTypeIntervenant(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM typeIntervenant WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table TypeIntervenant"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER TYPEINTERVENANT
 		// =================================================================================
 		@Override
 		public void renommerTypeIntervenant(TypeIntervenant typeIntervenant) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();

 				preparedStatement = connexion.prepareStatement(
 						"UPDATE typeIntervenant SET typeIntervenant.nom=?, typeIntervenant.pgmmodification=?, typeIntervenant.datemodification=?,"
 								+ " typeIntervenant.usermodification=? where typeIntervenant.id=?;");
 				preparedStatement.setString(1, typeIntervenant.getNom());
 				preparedStatement.setString(2, "RENOMMER TYPEINTERVENANT");
 				preparedStatement.setString(3, dateTime);
 				preparedStatement.setString(4, System.getProperty("user.name"));
 				preparedStatement.setInt(5, typeIntervenant.getId());
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
    public List<TypeIntervenant> listerTypeIntervenant() throws DaoException {
        List<TypeIntervenant> typeIntervenants = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, nom, libelle FROM typeIntervenant;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeIntervenant typeIntervenant = new TypeIntervenant();
                typeIntervenant.setId(resultat.getInt("id"));
                typeIntervenant.setNom(resultat.getString("nom"));
                typeIntervenant.setLibelle(resultat.getString("libelle"));
                typeIntervenants.add(typeIntervenant);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table TypeIntervenant"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeIntervenants;
    }
    @Override
    public List<TypeIntervenant> listerTypeIntervenantClient() throws DaoException {
        List<TypeIntervenant> typeIntervenants = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, nom, libelle FROM typeIntervenant;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeIntervenant typeIntervenant = new TypeIntervenant();
                typeIntervenant.setId(resultat.getInt("id"));
                typeIntervenant.setNom(resultat.getString("nom"));
                typeIntervenant.setLibelle(resultat.getString("libelle"));
                typeIntervenants.add(typeIntervenant);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table TypeIntervenant"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeIntervenants;
    }
    @Override
    public List<TypeIntervenant> listerTypeIntervenantFournisseur() throws DaoException {
        List<TypeIntervenant> typeIntervenants = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, nom, libelle FROM typeIntervenant;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeIntervenant typeIntervenant = new TypeIntervenant();
                typeIntervenant.setId(resultat.getInt("id"));
                typeIntervenant.setNom(resultat.getString("nom"));
                typeIntervenant.setLibelle(resultat.getString("libelle"));
                typeIntervenants.add(typeIntervenant);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table TypeIntervenant"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeIntervenants;
    }
    @Override
    public List<TypeIntervenant> listerTypeIntervenantSalarie() throws DaoException {
        List<TypeIntervenant> typeIntervenants = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, nom, libelle FROM typeIntervenant;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeIntervenant typeIntervenant = new TypeIntervenant();
                typeIntervenant.setId(resultat.getInt("id"));
                typeIntervenant.setNom(resultat.getString("nom"));
                typeIntervenant.setLibelle(resultat.getString("libelle"));
                typeIntervenants.add(typeIntervenant);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table TypeIntervenant"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeIntervenants;
    }
    @Override
    public List<TypeIntervenant> listerTypeIntervenantInterne() throws DaoException {
        List<TypeIntervenant> typeIntervenants = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, nom, libelle FROM typeIntervenant;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeIntervenant typeIntervenant = new TypeIntervenant();
                typeIntervenant.setId(resultat.getInt("id"));
                typeIntervenant.setNom(resultat.getString("nom"));
                typeIntervenant.setLibelle(resultat.getString("libelle"));
                typeIntervenants.add(typeIntervenant);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table TypeIntervenant"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeIntervenants;
    }
    	// =================================================================================
		// TROUVER TYPEINTERVENANT PAR ID
		// =================================================================================
    @Override
    public TypeIntervenant trouverTypeIntervenant(Integer id) throws DaoException {
        TypeIntervenant typeIntervenant = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM typeIntervenant WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                typeIntervenant = new TypeIntervenant();
                typeIntervenant.setId(id);
                typeIntervenant.setNom(resultat.getString("nom"));
                typeIntervenant.setLibelle(resultat.getString("libelle"));
                typeIntervenant.setUsermodification(resultat.getString("usermodification"));
                typeIntervenant.setDatemodification(resultat.getDate("datemodification"));
                typeIntervenant.setPgmmodification(resultat.getString("pgmmodification"));
                typeIntervenant.setUsercreation(resultat.getString("usercreation"));
                typeIntervenant.setDatecreation(resultat.getDate("datecreation"));
                typeIntervenant.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table TypeIntervenant"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return typeIntervenant;
    }

    	// =================================================================================
		// TROUVER TYPEINTERVENANT PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomTypeIntervenant(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM typeIntervenant WHERE nom=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table TypeIntervenant"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE TYPEINTERVENANT
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheTypeIntervenants(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM typeIntervenant ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Map<String, Object> typeIntervenantFields = new LinkedHashMap<>();
                typeIntervenantFields.put("id", rs.getInt("id"));
                typeIntervenantFields.put("nom", rs.getString("nom"));
                typeIntervenantFields.put("libelle", rs.getString("libelle"));
                list.add(typeIntervenantFields);
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
    public List<Map<String, Object>>  rechercheLikeTypeIntervenants(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM typeIntervenant WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> typeIntervenantFields = new LinkedHashMap<>();
                typeIntervenantFields.put("id", rs.getInt("id"));
                typeIntervenantFields.put("nom", rs.getString("nom"));
                typeIntervenantFields.put("libelle", rs.getString("libelle"));
                list.add(typeIntervenantFields);
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
	// RECUPERATION N° ENREGISTREMENT ENCOURS TYPEINTERVENANT
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
	// FERMETURE DES RESSOURCES TYPEINTERVENANT
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