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
import com.ws.beans.TypeContrat;
import com.ws.configuration.DatasourceH;

public class TypeContratDaoImpl implements TypeContratDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  TypeContratDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER TYPECONTRAT
		// =================================================================================
    @Override
    public void ajouterTypeContrat(TypeContrat typeContrat) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion  = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO typeContrat(nom, libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, typeContrat.getNom());
            preparedStatement.setString(2, typeContrat.getLibelle());
            preparedStatement.setInt(3, typeContrat.getEntite());
            preparedStatement.setInt(4, typeContrat.getActivite());
            preparedStatement.setString(5, typeContrat.getCheminRelatif());
            preparedStatement.setString(6, typeContrat.getCheminAbsolu());
            preparedStatement.setInt(7, typeContrat.getTypeIntervenant());
            preparedStatement.setString(8, "TypeContratDao");
            preparedStatement.setString(9, dateTime);
            preparedStatement.setString(10, System.getProperty("user.name"));
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table TypeContrat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER TYPECONTRAT
		// =================================================================================
    @Override
    public void modifierTypeContrat(TypeContrat typeContrat) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE typeContrat SET nom=?, libelle=?, entite=?, activite=?, cheminRelatif=?, cheminAbsolu=?, typeIntervenant=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, typeContrat.getNom());
            preparedStatement.setString(2, typeContrat.getLibelle());
            preparedStatement.setInt(3, typeContrat.getEntite());
            preparedStatement.setInt(4, typeContrat.getActivite());
            preparedStatement.setString(5, typeContrat.getCheminRelatif());
            preparedStatement.setString(6, typeContrat.getCheminAbsolu());
            preparedStatement.setInt(7, typeContrat.getTypeIntervenant());
            preparedStatement.setString(8, "TypeContratDao");
            preparedStatement.setString(9, dateTime);
            preparedStatement.setString(10, System.getProperty("user.name"));
            preparedStatement.setInt(11, typeContrat.getId());
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table TypeContrat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER TYPECONTRAT
  		// =================================================================================
    @Override
    public void copierTypeContrat(TypeContrat typeContrat) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO typeContrat(nom, libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, typeContrat.getNom());
            preparedStatement.setString(2, typeContrat.getLibelle());
            preparedStatement.setInt(3, typeContrat.getEntite());
            preparedStatement.setInt(4, typeContrat.getActivite());
            preparedStatement.setString(5, typeContrat.getCheminRelatif());
            preparedStatement.setString(6, typeContrat.getCheminAbsolu());
            preparedStatement.setInt(7, typeContrat.getTypeIntervenant());
            preparedStatement.setString(8, "TypeContratDao");
            preparedStatement.setString(9, dateTime);
            preparedStatement.setString(10, System.getProperty("user.name"));
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table TypeContrat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER TYPECONTRAT
  		// =================================================================================

    @Override
    public void supprimerTypeContrat(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM typeContrat WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table TypeContrat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER TYPECONTRAT
 		// =================================================================================
 		@Override
 		public void renommerTypeContrat(TypeContrat typeContrat) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();

 				preparedStatement = connexion.prepareStatement(
 						"UPDATE typeContrat SET typeContrat.nom=?, typeContrat.pgmmodification=?, typeContrat.datemodification=?,"
 								+ " typeContrat.usermodification=? where typeContrat.id=?;");
 				preparedStatement.setString(1, typeContrat.getNom());
 				preparedStatement.setString(2, "RENOMMER TYPECONTRAT");
 				preparedStatement.setString(3, dateTime);
 				preparedStatement.setString(4, System.getProperty("user.name"));
 				preparedStatement.setInt(5, typeContrat.getId());
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
  	// LISTER DES TYPECONTRATS
  	// =================================================================================
    @Override
    public List<TypeContrat> listerTypeContrat() throws DaoException {
        List<TypeContrat> typeContrats = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT typecontrat.id, typecontrat.nom, typecontrat.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, entite.nom FROM typeContrat inner join entite on typecontrat.entite=entite.id;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeContrat typeContrat = new TypeContrat();
                typeContrat.setId(resultat.getInt("typecontrat.id"));
                typeContrat.setNom(resultat.getString("typecontrat.nom"));
                typeContrat.setLibelle(resultat.getString("typecontrat.libelle"));
                typeContrat.setEntite(resultat.getInt("entite"));
                typeContrat.setActivite(resultat.getInt("activite"));
                typeContrat.setCheminRelatif(resultat.getString("cheminRelatif"));
                typeContrat.setCheminAbsolu(resultat.getString("cheminAbsolu"));
                typeContrat.setActivite(resultat.getInt("typeIntervenant"));
                typeContrats.add(typeContrat);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table TypeContrat"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeContrats;
    }

    @Override
    public List<TypeContrat> listerTypeContratClient() throws DaoException {
        List<TypeContrat> typeContrats = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT typecontrat.id, typecontrat.nom, typecontrat.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, entite.nom FROM typeContrat inner join entite on typecontrat.entite=entite.id where entite.nom='Client';";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeContrat typeContrat = new TypeContrat();
                typeContrat.setId(resultat.getInt("typecontrat.id"));
                typeContrat.setNom(resultat.getString("typecontrat.nom"));
                typeContrat.setLibelle(resultat.getString("typecontrat.libelle"));
                typeContrat.setEntite(resultat.getInt("entite"));
                typeContrat.setActivite(resultat.getInt("activite"));
                typeContrat.setCheminRelatif(resultat.getString("cheminRelatif"));
                typeContrat.setCheminAbsolu(resultat.getString("cheminAbsolu"));
                typeContrat.setActivite(resultat.getInt("typeIntervenant"));
                typeContrats.add(typeContrat);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table TypeContrat"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeContrats;
    }

    @Override
    public List<TypeContrat> listerTypeContratFournisseur() throws DaoException {
        List<TypeContrat> typeContrats = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT typecontrat.id, typecontrat.nom, typecontrat.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, entite.nom FROM typeContrat inner join entite on typecontrat.entite=entite.id where entite.nom='Fournisseur';";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeContrat typeContrat = new TypeContrat();
                typeContrat.setId(resultat.getInt("typecontrat.id"));
                typeContrat.setNom(resultat.getString("typecontrat.nom"));
                typeContrat.setLibelle(resultat.getString("typecontrat.libelle"));
                typeContrat.setEntite(resultat.getInt("entite"));
                typeContrat.setActivite(resultat.getInt("activite"));
                typeContrat.setCheminRelatif(resultat.getString("cheminRelatif"));
                typeContrat.setCheminAbsolu(resultat.getString("cheminAbsolu"));
                typeContrat.setActivite(resultat.getInt("typeIntervenant"));
                typeContrats.add(typeContrat);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table TypeContrat"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeContrats;
    }

    @Override
    public List<TypeContrat> listerTypeContratSalarie() throws DaoException {
        List<TypeContrat> typeContrats = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT typecontrat.id, typecontrat.nom, typecontrat.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, entite.nom FROM typeContrat inner join entite on typecontrat.entite=entite.id where entite.nom='Salarie';";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeContrat typeContrat = new TypeContrat();
                typeContrat.setId(resultat.getInt("typecontrat.id"));
                typeContrat.setNom(resultat.getString("typecontrat.nom"));
                typeContrat.setLibelle(resultat.getString("typecontrat.libelle"));
                typeContrat.setEntite(resultat.getInt("entite"));
                typeContrat.setActivite(resultat.getInt("activite"));
                typeContrat.setCheminRelatif(resultat.getString("cheminRelatif"));
                typeContrat.setCheminAbsolu(resultat.getString("cheminAbsolu"));
                typeContrat.setActivite(resultat.getInt("typeIntervenant"));
                typeContrats.add(typeContrat);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table TypeContrat"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeContrats;
    }
    	// =================================================================================
		// TROUVER TYPECONTRAT PAR ID
		// =================================================================================
    @Override
    public TypeContrat trouverTypeContrat(Integer id) throws DaoException {
        TypeContrat typeContrat = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM typeContrat WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                typeContrat = new TypeContrat();
                typeContrat.setId(id);
                typeContrat.setNom(resultat.getString("nom"));
                typeContrat.setLibelle(resultat.getString("libelle"));
                typeContrat.setEntite(resultat.getInt("entite"));
                typeContrat.setActivite(resultat.getInt("activite"));
                typeContrat.setCheminRelatif(resultat.getString("cheminRelatif"));
                typeContrat.setCheminAbsolu(resultat.getString("cheminAbsolu"));
                typeContrat.setActivite(resultat.getInt("typeIntervenant"));
                typeContrat.setUsermodification(resultat.getString("usermodification"));
                typeContrat.setDatemodification(resultat.getDate("datemodification"));
                typeContrat.setPgmmodification(resultat.getString("pgmmodification"));
                typeContrat.setUsercreation(resultat.getString("usercreation"));
                typeContrat.setDatecreation(resultat.getDate("datecreation"));
                typeContrat.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table TypeContrat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return typeContrat;
    }

    	// =================================================================================
		// TROUVER TYPECONTRAT PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomTypeContrat(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM typeContrat WHERE nom=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table TypeContrat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE TYPECONTRAT
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheTypeContrats(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String ww_where ="";
        if  (!type_entite.equals("")) {
        	ww_where = " where entite.nom='"+type_entite+"'";
        }
        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS typeContrat.id, typeContrat.nom, typeContrat.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, typeIntervenant.nom, entite.nom, activite.nom FROM typeContrat left join entite on entite.id=typeContrat.entite left join activite on activite.id=typeContrat.activite left join typeIntervenant on typeIntervenant.id = typeIntervenant"
            		+ ww_where
            		+ " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Map<String, Object> typeContratFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                    typeContratFields.put("id", rs.getInt("typeContrat.id"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("nom")) {
                    typeContratFields.put("nom", rs.getString("typeContrat.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("libelle")) {
                    typeContratFields.put("libelle", rs.getString("typeContrat.libelle"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("entite")) {
                    typeContratFields.put("entite", rs.getString("entite.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("activite")) {
                    typeContratFields.put("activite", rs.getString("activite.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("cheminRelatif")) {
                    typeContratFields.put("cheminRelatif", rs.getString("cheminRelatif"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("cheminAbsolu")) {
                    typeContratFields.put("cheminAbsolu", rs.getString("cheminAbsolu"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("typeIntervenant")) {
                        typeContratFields.put("typeIntervenant", rs.getString("typeIntervenant.nom"));
                        }
                    list.add(typeContratFields);
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
		// RECHERCHE TYPECONTRATS SUIVANT LIKE
		// =================================================================================
    @Override
    public List<Map<String, Object>>  rechercheLikeTypeContrats(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String ww_where ="";
        if  (!type_entite.equals("")) {
        	ww_where = " and entite.nom='"+type_entite+"'";
        }
        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS typeContrat.id, typeContrat.nom, typeContrat.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, typeIntervenant.nom, entite.nom, activite.nom FROM typeContrat left join entite on entite.id=typeContrat.entite left join activite on activite.id=typeContrat.activite left join typeIntervenant on typeIntervenant.id = typeIntervenant "
            		+ "WHERE " + select_like
            		+ ww_where
            		+ " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> typeContratFields = new LinkedHashMap<>();
            	if (dictionnaire_nom_colonne.containsKey("id")) {
                    typeContratFields.put("id", rs.getInt("typeContrat.id"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("nom")) {
                    typeContratFields.put("nom", rs.getString("typeContrat.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("libelle")) {
                    typeContratFields.put("libelle", rs.getString("typeContrat.libelle"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("entite")) {
                    typeContratFields.put("entite", rs.getString("entite.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("activite")) {
                    typeContratFields.put("activite", rs.getString("activite.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("cheminRelatif")) {
                    typeContratFields.put("cheminRelatif", rs.getString("cheminRelatif"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("cheminAbsolu")) {
                    typeContratFields.put("cheminAbsolu", rs.getString("cheminAbsolu"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("typeIntervenant")) {
                        typeContratFields.put("typeIntervenant", rs.getString("typeIntervenant.nom"));
                        }
                list.add(typeContratFields);
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
	// RECUPERATION N° ENREGISTREMENT ENCOURS TYPECONTRAT
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
	// FERMETURE DES RESSOURCES TYPECONTRAT
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