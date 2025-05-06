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
import com.ws.beans.TypeFactureVente;
import com.ws.configuration.DatasourceH;

public class TypeFactureVenteDaoImpl implements TypeFactureVenteDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  TypeFactureVenteDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER TYPEFACTUREVENTE
		// =================================================================================
    @Override
    public void ajouterTypeFactureVente(TypeFactureVente typeFactureVente) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion  = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO typeFactureVente(nom, libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, typeFactureVente.getNom());
            preparedStatement.setString(2, typeFactureVente.getLibelle());
            preparedStatement.setInt(3, typeFactureVente.getEntite());
            preparedStatement.setInt(4, typeFactureVente.getActivite());
            preparedStatement.setString(5, typeFactureVente.getCheminRelatif());
            preparedStatement.setString(6, typeFactureVente.getCheminAbsolu());
            preparedStatement.setInt(7, typeFactureVente.getTypeIntervenant());
            preparedStatement.setString(8, "TypeFactureVenteDao");
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table TypeFactureVente"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER TYPEFACTUREVENTE
		// =================================================================================
    @Override
    public void modifierTypeFactureVente(TypeFactureVente typeFactureVente) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE typeFactureVente SET nom=?, libelle=?, entite=?, activite=?, cheminRelatif=?, cheminAbsolu=?, typeIntervenant=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, typeFactureVente.getNom());
            preparedStatement.setString(2, typeFactureVente.getLibelle());
            preparedStatement.setInt(3, typeFactureVente.getEntite());
            preparedStatement.setInt(4, typeFactureVente.getActivite());
            preparedStatement.setString(5, typeFactureVente.getCheminRelatif());
            preparedStatement.setString(6, typeFactureVente.getCheminAbsolu());
            preparedStatement.setInt(7, typeFactureVente.getTypeIntervenant());
            preparedStatement.setString(8, "TypeFactureVenteDao");
            preparedStatement.setString(9, dateTime);
            preparedStatement.setString(10, System.getProperty("user.name"));
            preparedStatement.setInt(11, typeFactureVente.getId());
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table TypeFactureVente"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER TYPEFACTUREVENTE
  		// =================================================================================
    @Override
    public void copierTypeFactureVente(TypeFactureVente typeFactureVente) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO typeFactureVente(nom, libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, typeFactureVente.getNom());
            preparedStatement.setString(2, typeFactureVente.getLibelle());
            preparedStatement.setInt(3, typeFactureVente.getEntite());
            preparedStatement.setInt(4, typeFactureVente.getActivite());
            preparedStatement.setString(5, typeFactureVente.getCheminRelatif());
            preparedStatement.setString(6, typeFactureVente.getCheminAbsolu());
            preparedStatement.setInt(7, typeFactureVente.getTypeIntervenant());
            preparedStatement.setString(8, "TypeFactureVenteDao");
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table TypeFactureVente"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER TYPEFACTUREVENTE
  		// =================================================================================

    @Override
    public void supprimerTypeFactureVente(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM typeFactureVente WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table TypeFactureVente"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER TYPEFACTUREVENTE
 		// =================================================================================
 		@Override
 		public void renommerTypeFactureVente(TypeFactureVente typeFactureVente) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();

 				preparedStatement = connexion.prepareStatement(
 						"UPDATE typeFactureVente SET typeFactureVente.nom=?, typeFactureVente.pgmmodification=?, typeFactureVente.datemodification=?,"
 								+ " typeFactureVente.usermodification=? where typeFactureVente.id=?;");
 				preparedStatement.setString(1, typeFactureVente.getNom());
 				preparedStatement.setString(2, "RENOMMER TYPEFACTUREVENTE");
 				preparedStatement.setString(3, dateTime);
 				preparedStatement.setString(4, System.getProperty("user.name"));
 				preparedStatement.setInt(5, typeFactureVente.getId());
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
  	// LISTER DES TYPEFACTUREVENTES
  	// =================================================================================
    @Override
    public List<TypeFactureVente> listerTypeFactureVente() throws DaoException {
        List<TypeFactureVente> typeFactureVentes = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT typeFactureVente.id, typeFactureVente.nom, typeFactureVente.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, entite.nom FROM typeFactureVente inner join entite on typeFactureVente.entite=entite.id;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeFactureVente typeFactureVente = new TypeFactureVente();
                typeFactureVente.setId(resultat.getInt("typeFactureVente.id"));
                typeFactureVente.setNom(resultat.getString("typeFactureVente.nom"));
                typeFactureVente.setLibelle(resultat.getString("typeFactureVente.libelle"));
                typeFactureVente.setEntite(resultat.getInt("entite"));
                typeFactureVente.setActivite(resultat.getInt("activite"));
                typeFactureVente.setCheminRelatif(resultat.getString("cheminRelatif"));
                typeFactureVente.setCheminAbsolu(resultat.getString("cheminAbsolu"));
                typeFactureVente.setActivite(resultat.getInt("typeIntervenant"));
                typeFactureVentes.add(typeFactureVente);
            }
        } catch (SQLException e) {

        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeFactureVentes;
    }

    @Override
    public List<TypeFactureVente> listerTypeFactureVenteClient() throws DaoException {
        List<TypeFactureVente> typeFactureVentes = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT typeFactureVente.id, typeFactureVente.nom, typeFactureVente.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, entite.nom FROM typeFactureVente inner join entite on typeFactureVente.entite=entite.id where entite.nom='Client';";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeFactureVente typeFactureVente = new TypeFactureVente();
                typeFactureVente.setId(resultat.getInt("typeFactureVente.id"));
                typeFactureVente.setNom(resultat.getString("typeFactureVente.nom"));
                typeFactureVente.setLibelle(resultat.getString("typeFactureVente.libelle"));
                typeFactureVente.setEntite(resultat.getInt("entite"));
                typeFactureVente.setActivite(resultat.getInt("activite"));
                typeFactureVente.setCheminRelatif(resultat.getString("cheminRelatif"));
                typeFactureVente.setCheminAbsolu(resultat.getString("cheminAbsolu"));
                typeFactureVente.setActivite(resultat.getInt("typeIntervenant"));
                typeFactureVentes.add(typeFactureVente);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table TypeFactureVente"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeFactureVentes;
    }

    @Override
    public List<TypeFactureVente> listerTypeFactureVenteFournisseur() throws DaoException {
        List<TypeFactureVente> typeFactureVentes = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT typeFactureVente.id, typeFactureVente.nom, typeFactureVente.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, entite.nom FROM typeFactureVente inner join entite on typeFactureVente.entite=entite.id where entite.nom='Fournisseur';";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeFactureVente typeFactureVente = new TypeFactureVente();
                typeFactureVente.setId(resultat.getInt("typeFactureVente.id"));
                typeFactureVente.setNom(resultat.getString("typeFactureVente.nom"));
                typeFactureVente.setLibelle(resultat.getString("typeFactureVente.libelle"));
                typeFactureVente.setEntite(resultat.getInt("entite"));
                typeFactureVente.setActivite(resultat.getInt("activite"));
                typeFactureVente.setCheminRelatif(resultat.getString("cheminRelatif"));
                typeFactureVente.setCheminAbsolu(resultat.getString("cheminAbsolu"));
                typeFactureVente.setActivite(resultat.getInt("typeIntervenant"));
                typeFactureVentes.add(typeFactureVente);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table TypeFactureVente"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeFactureVentes;
    }

    @Override
    public List<TypeFactureVente> listerTypeFactureVenteSalarie() throws DaoException {
        List<TypeFactureVente> typeFactureVentes = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT typeFactureVente.id, typeFactureVente.nom, typeFactureVente.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, entite.nom FROM typeFactureVente inner join entite on typeFactureVente.entite=entite.id where entite.nom='Salarie';";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeFactureVente typeFactureVente = new TypeFactureVente();
                typeFactureVente.setId(resultat.getInt("typeFactureVente.id"));
                typeFactureVente.setNom(resultat.getString("typeFactureVente.nom"));
                typeFactureVente.setLibelle(resultat.getString("typeFactureVente.libelle"));
                typeFactureVente.setEntite(resultat.getInt("entite"));
                typeFactureVente.setActivite(resultat.getInt("activite"));
                typeFactureVente.setCheminRelatif(resultat.getString("cheminRelatif"));
                typeFactureVente.setCheminAbsolu(resultat.getString("cheminAbsolu"));
                typeFactureVente.setActivite(resultat.getInt("typeIntervenant"));
                typeFactureVentes.add(typeFactureVente);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table TypeFactureVente"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeFactureVentes;
    }

    @Override
    public List<TypeFactureVente> listerTypeFactureVenteInterne() throws DaoException {
        List<TypeFactureVente> typeFactureVentes = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT typeFactureVente.id, typeFactureVente.nom, typeFactureVente.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, entite.nom FROM typeFactureVente inner join entite on typeFactureVente.entite=entite.id where entite.nom='Interne';";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                TypeFactureVente typeFactureVente = new TypeFactureVente();
                typeFactureVente.setId(resultat.getInt("typeFactureVente.id"));
                typeFactureVente.setNom(resultat.getString("typeFactureVente.nom"));
                typeFactureVente.setLibelle(resultat.getString("typeFactureVente.libelle"));
                typeFactureVente.setEntite(resultat.getInt("entite"));
                typeFactureVente.setActivite(resultat.getInt("activite"));
                typeFactureVente.setCheminRelatif(resultat.getString("cheminRelatif"));
                typeFactureVente.setCheminAbsolu(resultat.getString("cheminAbsolu"));
                typeFactureVente.setActivite(resultat.getInt("typeIntervenant"));
                typeFactureVentes.add(typeFactureVente);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table TypeFactureVente"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return typeFactureVentes;
    }
    	// =================================================================================
		// TROUVER TYPEFACTUREVENTE PAR ID
		// =================================================================================
    @Override
    public TypeFactureVente trouverTypeFactureVente(Integer id) throws DaoException {
        TypeFactureVente typeFactureVente = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM typeFactureVente WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                typeFactureVente = new TypeFactureVente();
                typeFactureVente.setId(id);
                typeFactureVente.setNom(resultat.getString("nom"));
                typeFactureVente.setLibelle(resultat.getString("libelle"));
                typeFactureVente.setEntite(resultat.getInt("entite"));
                typeFactureVente.setActivite(resultat.getInt("activite"));
                typeFactureVente.setCheminRelatif(resultat.getString("cheminRelatif"));
                typeFactureVente.setCheminAbsolu(resultat.getString("cheminAbsolu"));
                typeFactureVente.setActivite(resultat.getInt("typeIntervenant"));
                typeFactureVente.setUsermodification(resultat.getString("usermodification"));
                typeFactureVente.setDatemodification(resultat.getDate("datemodification"));
                typeFactureVente.setPgmmodification(resultat.getString("pgmmodification"));
                typeFactureVente.setUsercreation(resultat.getString("usercreation"));
                typeFactureVente.setDatecreation(resultat.getDate("datecreation"));
                typeFactureVente.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table TypeFactureVente"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return typeFactureVente;
    }

    	// =================================================================================
		// TROUVER TYPEFACTUREVENTE PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomTypeFactureVente(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM typeFactureVente WHERE nom=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table TypeFactureVente"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE TYPEFACTUREVENTE
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheTypeFactureVentes(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
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
            String query = "SELECT SQL_CALC_FOUND_ROWS typeFactureVente.id, typeFactureVente.nom, typeFactureVente.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, typeIntervenant.nom, entite.nom, activite.nom FROM typeFactureVente left join entite on entite.id=typeFactureVente.entite left join activite on activite.id=typeFactureVente.activite left join typeIntervenant on typeIntervenant.id = typeIntervenant"
            		+ ww_where
            		+ " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Map<String, Object> typeFactureVenteFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                    typeFactureVenteFields.put("id", rs.getInt("typeFactureVente.id"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("nom")) {
                    typeFactureVenteFields.put("nom", rs.getString("typeFactureVente.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("libelle")) {
                    typeFactureVenteFields.put("libelle", rs.getString("typeFactureVente.libelle"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("entite")) {
                    typeFactureVenteFields.put("entite", rs.getString("entite.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("activite")) {
                    typeFactureVenteFields.put("activite", rs.getString("activite.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("cheminRelatif")) {
                    typeFactureVenteFields.put("cheminRelatif", rs.getString("cheminRelatif"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("cheminAbsolu")) {
                    typeFactureVenteFields.put("cheminAbsolu", rs.getString("cheminAbsolu"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("typeIntervenant")) {
                        typeFactureVenteFields.put("typeIntervenant", rs.getString("typeIntervenant.nom"));
                        }
                    list.add(typeFactureVenteFields);
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
		// RECHERCHE TYPEFACTUREVENTES SUIVANT LIKE
		// =================================================================================
    @Override
    public List<Map<String, Object>>  rechercheLikeTypeFactureVentes(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
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
            String query = "SELECT SQL_CALC_FOUND_ROWS typeFactureVente.id, typeFactureVente.nom, typeFactureVente.libelle, entite, activite, cheminRelatif, cheminAbsolu, typeIntervenant, typeIntervenant.nom, entite.nom, activite.nom FROM typeFactureVente left join entite on entite.id=typeFactureVente.entite left join activite on activite.id=typeFactureVente.activite left join typeIntervenant on typeIntervenant.id = typeIntervenant "
            		+ "WHERE " + select_like
            		+ ww_where
            		+ " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> typeFactureVenteFields = new LinkedHashMap<>();
            	if (dictionnaire_nom_colonne.containsKey("id")) {
                    typeFactureVenteFields.put("id", rs.getInt("typeFactureVente.id"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("nom")) {
                    typeFactureVenteFields.put("nom", rs.getString("typeFactureVente.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("libelle")) {
                    typeFactureVenteFields.put("libelle", rs.getString("typeFactureVente.libelle"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("entite")) {
                    typeFactureVenteFields.put("entite", rs.getString("entite.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("activite")) {
                    typeFactureVenteFields.put("activite", rs.getString("activite.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("cheminRelatif")) {
                    typeFactureVenteFields.put("cheminRelatif", rs.getString("cheminRelatif"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("cheminAbsolu")) {
                    typeFactureVenteFields.put("cheminAbsolu", rs.getString("cheminAbsolu"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("typeIntervenant")) {
                        typeFactureVenteFields.put("typeIntervenant", rs.getString("typeIntervenant.nom"));
                        }
                list.add(typeFactureVenteFields);
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
	// RECUPERATION N° ENREGISTREMENT ENCOURS TYPEFACTUREVENTE
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
	// FERMETURE DES RESSOURCES TYPEFACTUREVENTE
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