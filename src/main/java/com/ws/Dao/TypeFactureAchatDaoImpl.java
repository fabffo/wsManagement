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
import com.ws.beans.TypeFactureAchat;
import com.ws.configuration.DatasourceH;

public class TypeFactureAchatDaoImpl implements TypeFactureAchatDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  TypeFactureAchatDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER TYPEFACTUREACHAT
		// =================================================================================
    @Override
    public void ajouterTypeFactureAchat(TypeFactureAchat typeFactureAchat) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion  = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO typeFactureAchat(nom, libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, typeFactureAchat.getNom());
            preparedStatement.setString(2, typeFactureAchat.getLibelle());
            preparedStatement.setInt(3, typeFactureAchat.getEntite());
            preparedStatement.setInt(4, typeFactureAchat.getActivite());
            preparedStatement.setString(5, typeFactureAchat.getCheminRelatif());
            preparedStatement.setString(6, typeFactureAchat.getCheminAbsolu());
            preparedStatement.setInt(7, typeFactureAchat.getTypeIntervenant());
            preparedStatement.setString(8, "TypeFactureAchatDao");
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table TypeFactureAchat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER TYPEFACTUREACHAT
		// =================================================================================
    @Override
    public void modifierTypeFactureAchat(TypeFactureAchat typeFactureAchat) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE typeFactureAchat SET nom=?, libelle=?, entite=?, activite=?, cheminRelatif=?, cheminAbsolu=?, typeIntervenant=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, typeFactureAchat.getNom());
            preparedStatement.setString(2, typeFactureAchat.getLibelle());
            preparedStatement.setInt(3, typeFactureAchat.getEntite());
            preparedStatement.setInt(4, typeFactureAchat.getActivite());
            preparedStatement.setString(5, typeFactureAchat.getCheminRelatif());
            preparedStatement.setString(6, typeFactureAchat.getCheminAbsolu());
            preparedStatement.setInt(7, typeFactureAchat.getTypeIntervenant());
            preparedStatement.setString(8, "TypeFactureAchatDao");
            preparedStatement.setString(9, dateTime);
            preparedStatement.setString(10, System.getProperty("user.name"));
            preparedStatement.setInt(11, typeFactureAchat.getId());
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table TypeFactureAchat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER TYPEFACTUREACHAT
  		// =================================================================================
    @Override
    public void copierTypeFactureAchat(TypeFactureAchat typeFactureAchat) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO typeFactureAchat(nom, libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, typeFactureAchat.getNom());
            preparedStatement.setString(2, typeFactureAchat.getLibelle());
            preparedStatement.setInt(3, typeFactureAchat.getEntite());
            preparedStatement.setInt(4, typeFactureAchat.getActivite());
            preparedStatement.setString(5, typeFactureAchat.getCheminRelatif());
            preparedStatement.setString(6, typeFactureAchat.getCheminAbsolu());
            preparedStatement.setInt(7, typeFactureAchat.getTypeIntervenant());
            preparedStatement.setString(8, "TypeFactureAchatDao");
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table TypeFactureAchat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER TYPEFACTUREACHAT
  		// =================================================================================

    @Override
    public void supprimerTypeFactureAchat(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM typeFactureAchat WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table TypeFactureAchat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER TYPEFACTUREACHAT
 		// =================================================================================
 		@Override
 		public void renommerTypeFactureAchat(TypeFactureAchat typeFactureAchat) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();

 				preparedStatement = connexion.prepareStatement(
 						"UPDATE typeFactureAchat SET typeFactureAchat.nom=?, typeFactureAchat.pgmmodification=?, typeFactureAchat.datemodification=?,"
 								+ " typeFactureAchat.usermodification=? where typeFactureAchat.id=?;");
 				preparedStatement.setString(1, typeFactureAchat.getNom());
 				preparedStatement.setString(2, "RENOMMER TYPEFACTUREACHAT");
 				preparedStatement.setString(3, dateTime);
 				preparedStatement.setString(4, System.getProperty("user.name"));
 				preparedStatement.setInt(5, typeFactureAchat.getId());
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
  	// LISTER DES TYPEFACTUREACHATS
  	// =================================================================================
    @Override
    public List<TypeFactureAchat> listerTypeFactureAchat() throws DaoException {
        List<TypeFactureAchat> typeFactureAchats = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT typeFactureAchat.id, typeFactureAchat.nom, typeFactureAchat.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, entite.nom FROM typeFactureAchat inner join entite on typeFactureAchat.entite=entite.id;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeFactureAchat typeFactureAchat = new TypeFactureAchat();
                typeFactureAchat.setId(resultat.getInt("typeFactureAchat.id"));
                typeFactureAchat.setNom(resultat.getString("typeFactureAchat.nom"));
                typeFactureAchat.setLibelle(resultat.getString("typeFactureAchat.libelle"));
                typeFactureAchat.setEntite(resultat.getInt("entite"));
                typeFactureAchat.setActivite(resultat.getInt("activite"));
                typeFactureAchat.setCheminRelatif(resultat.getString("cheminRelatif"));
                typeFactureAchat.setCheminAbsolu(resultat.getString("cheminAbsolu"));
                typeFactureAchat.setActivite(resultat.getInt("typeIntervenant"));
                typeFactureAchats.add(typeFactureAchat);
            }
        } catch (SQLException e) {

        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeFactureAchats;
    }

    @Override
    public List<TypeFactureAchat> listerTypeFactureAchatClient() throws DaoException {
        List<TypeFactureAchat> typeFactureAchats = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT typeFactureAchat.id, typeFactureAchat.nom, typeFactureAchat.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, entite.nom FROM typeFactureAchat inner join entite on typeFactureAchat.entite=entite.id where entite.nom='Client';";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeFactureAchat typeFactureAchat = new TypeFactureAchat();
                typeFactureAchat.setId(resultat.getInt("typeFactureAchat.id"));
                typeFactureAchat.setNom(resultat.getString("typeFactureAchat.nom"));
                typeFactureAchat.setLibelle(resultat.getString("typeFactureAchat.libelle"));
                typeFactureAchat.setEntite(resultat.getInt("entite"));
                typeFactureAchat.setActivite(resultat.getInt("activite"));
                typeFactureAchat.setCheminRelatif(resultat.getString("cheminRelatif"));
                typeFactureAchat.setCheminAbsolu(resultat.getString("cheminAbsolu"));
                typeFactureAchat.setActivite(resultat.getInt("typeIntervenant"));
                typeFactureAchats.add(typeFactureAchat);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table TypeFactureAchat"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeFactureAchats;
    }

    @Override
    public List<TypeFactureAchat> listerTypeFactureAchatFournisseur() throws DaoException {
        List<TypeFactureAchat> typeFactureAchats = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT typeFactureAchat.id, typeFactureAchat.nom, typeFactureAchat.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, entite.nom FROM typeFactureAchat inner join entite on typeFactureAchat.entite=entite.id where entite.nom='Fournisseur';";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeFactureAchat typeFactureAchat = new TypeFactureAchat();
                typeFactureAchat.setId(resultat.getInt("typeFactureAchat.id"));
                typeFactureAchat.setNom(resultat.getString("typeFactureAchat.nom"));
                typeFactureAchat.setLibelle(resultat.getString("typeFactureAchat.libelle"));
                typeFactureAchat.setEntite(resultat.getInt("entite"));
                typeFactureAchat.setActivite(resultat.getInt("activite"));
                typeFactureAchat.setCheminRelatif(resultat.getString("cheminRelatif"));
                typeFactureAchat.setCheminAbsolu(resultat.getString("cheminAbsolu"));
                typeFactureAchat.setActivite(resultat.getInt("typeIntervenant"));
                typeFactureAchats.add(typeFactureAchat);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table TypeFactureAchat"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeFactureAchats;
    }

    @Override
    public List<TypeFactureAchat> listerTypeFactureAchatSalarie() throws DaoException {
        List<TypeFactureAchat> typeFactureAchats = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT typeFactureAchat.id, typeFactureAchat.nom, typeFactureAchat.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, entite.nom FROM typeFactureAchat inner join entite on typeFactureAchat.entite=entite.id where entite.nom='Salarie';";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeFactureAchat typeFactureAchat = new TypeFactureAchat();
                typeFactureAchat.setId(resultat.getInt("typeFactureAchat.id"));
                typeFactureAchat.setNom(resultat.getString("typeFactureAchat.nom"));
                typeFactureAchat.setLibelle(resultat.getString("typeFactureAchat.libelle"));
                typeFactureAchat.setEntite(resultat.getInt("entite"));
                typeFactureAchat.setActivite(resultat.getInt("activite"));
                typeFactureAchat.setCheminRelatif(resultat.getString("cheminRelatif"));
                typeFactureAchat.setCheminAbsolu(resultat.getString("cheminAbsolu"));
                typeFactureAchat.setActivite(resultat.getInt("typeIntervenant"));
                typeFactureAchats.add(typeFactureAchat);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table TypeFactureAchat"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeFactureAchats;
    }

    @Override
    public List<TypeFactureAchat> listerTypeFactureAchatInterne() throws DaoException {
        List<TypeFactureAchat> typeFactureAchats = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT typeFactureAchat.id, typeFactureAchat.nom, typeFactureAchat.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, entite.nom FROM typeFactureAchat inner join entite on typeFactureAchat.entite=entite.id where entite.nom='Interne';";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeFactureAchat typeFactureAchat = new TypeFactureAchat();
                typeFactureAchat.setId(resultat.getInt("typeFactureAchat.id"));
                typeFactureAchat.setNom(resultat.getString("typeFactureAchat.nom"));
                typeFactureAchat.setLibelle(resultat.getString("typeFactureAchat.libelle"));
                typeFactureAchat.setEntite(resultat.getInt("entite"));
                typeFactureAchat.setActivite(resultat.getInt("activite"));
                typeFactureAchat.setCheminRelatif(resultat.getString("cheminRelatif"));
                typeFactureAchat.setCheminAbsolu(resultat.getString("cheminAbsolu"));
                typeFactureAchat.setActivite(resultat.getInt("typeIntervenant"));
                typeFactureAchats.add(typeFactureAchat);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table TypeFactureAchat"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeFactureAchats;
    }
    	// =================================================================================
		// TROUVER TYPEFACTUREACHAT PAR ID
		// =================================================================================
    @Override
    public TypeFactureAchat trouverTypeFactureAchat(Integer id) throws DaoException {
        TypeFactureAchat typeFactureAchat = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM typeFactureAchat WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                typeFactureAchat = new TypeFactureAchat();
                typeFactureAchat.setId(id);
                typeFactureAchat.setNom(resultat.getString("nom"));
                typeFactureAchat.setLibelle(resultat.getString("libelle"));
                typeFactureAchat.setEntite(resultat.getInt("entite"));
                typeFactureAchat.setActivite(resultat.getInt("activite"));
                typeFactureAchat.setCheminRelatif(resultat.getString("cheminRelatif"));
                typeFactureAchat.setCheminAbsolu(resultat.getString("cheminAbsolu"));
                typeFactureAchat.setActivite(resultat.getInt("typeIntervenant"));
                typeFactureAchat.setUsermodification(resultat.getString("usermodification"));
                typeFactureAchat.setDatemodification(resultat.getDate("datemodification"));
                typeFactureAchat.setPgmmodification(resultat.getString("pgmmodification"));
                typeFactureAchat.setUsercreation(resultat.getString("usercreation"));
                typeFactureAchat.setDatecreation(resultat.getDate("datecreation"));
                typeFactureAchat.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table TypeFactureAchat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return typeFactureAchat;
    }

    	// =================================================================================
		// TROUVER TYPEFACTUREACHAT PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomTypeFactureAchat(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM typeFactureAchat WHERE nom=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table TypeFactureAchat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE TYPEFACTUREACHAT
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheTypeFactureAchats(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
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
            String query = "SELECT SQL_CALC_FOUND_ROWS typeFactureAchat.id, typeFactureAchat.nom, typeFactureAchat.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, typeIntervenant.nom, entite.nom, activite.nom FROM typeFactureAchat left join entite on entite.id=typeFactureAchat.entite left join activite on activite.id=typeFactureAchat.activite left join typeIntervenant on typeIntervenant.id = typeIntervenant"
            		+ ww_where
            		+ " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Map<String, Object> typeFactureAchatFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                    typeFactureAchatFields.put("id", rs.getInt("typeFactureAchat.id"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("nom")) {
                    typeFactureAchatFields.put("nom", rs.getString("typeFactureAchat.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("libelle")) {
                    typeFactureAchatFields.put("libelle", rs.getString("typeFactureAchat.libelle"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("entite")) {
                    typeFactureAchatFields.put("entite", rs.getString("entite.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("activite")) {
                    typeFactureAchatFields.put("activite", rs.getString("activite.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("cheminRelatif")) {
                    typeFactureAchatFields.put("cheminRelatif", rs.getString("cheminRelatif"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("cheminAbsolu")) {
                    typeFactureAchatFields.put("cheminAbsolu", rs.getString("cheminAbsolu"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("typeIntervenant")) {
                        typeFactureAchatFields.put("typeIntervenant", rs.getString("typeIntervenant.nom"));
                        }
                    list.add(typeFactureAchatFields);
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
		// RECHERCHE TYPEFACTUREACHATS SUIVANT LIKE
		// =================================================================================
    @Override
    public List<Map<String, Object>>  rechercheLikeTypeFactureAchats(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
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
            String query = "SELECT SQL_CALC_FOUND_ROWS typeFactureAchat.id, typeFactureAchat.nom, typeFactureAchat.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, typeIntervenant.nom, entite.nom, activite.nom FROM typeFactureAchat left join entite on entite.id=typeFactureAchat.entite left join activite on activite.id=typeFactureAchat.activite left join typeIntervenant on typeIntervenant.id = typeIntervenant "
            		+ "WHERE " + select_like
            		+ ww_where
            		+ " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> typeFactureAchatFields = new LinkedHashMap<>();
            	if (dictionnaire_nom_colonne.containsKey("id")) {
                    typeFactureAchatFields.put("id", rs.getInt("typeFactureAchat.id"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("nom")) {
                    typeFactureAchatFields.put("nom", rs.getString("typeFactureAchat.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("libelle")) {
                    typeFactureAchatFields.put("libelle", rs.getString("typeFactureAchat.libelle"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("entite")) {
                    typeFactureAchatFields.put("entite", rs.getString("entite.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("activite")) {
                    typeFactureAchatFields.put("activite", rs.getString("activite.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("cheminRelatif")) {
                    typeFactureAchatFields.put("cheminRelatif", rs.getString("cheminRelatif"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("cheminAbsolu")) {
                    typeFactureAchatFields.put("cheminAbsolu", rs.getString("cheminAbsolu"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("typeIntervenant")) {
                        typeFactureAchatFields.put("typeIntervenant", rs.getString("typeIntervenant.nom"));
                        }
                list.add(typeFactureAchatFields);
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
	// RECUPERATION N° ENREGISTREMENT ENCOURS TYPEFACTUREACHAT
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
	// FERMETURE DES RESSOURCES TYPEFACTUREACHAT
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