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
import com.ws.beans.Relation;
import com.ws.configuration.DatasourceH;

public class RelationDaoImpl implements RelationDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  RelationDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER RELATION
		// =================================================================================
    @Override
    public void ajouterRelation(Relation relation) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
        "INSERT INTO relation(nom, prenom, adresse, code_postal, ville, pays, telephone, email, telephone_secondaire, email_secondaire, civilite, organisation, metier, commentaire, pgmcreation, datecreation, usercreation ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

		preparedStatement.setString(1, relation.getNom());
		preparedStatement.setString(2, relation.getPrenom());
		preparedStatement.setString(3, relation.getAdresse());
		preparedStatement.setString(4, relation.getCode_postal());
		preparedStatement.setString(5, relation.getVille());
		preparedStatement.setString(6, relation.getPays());
		preparedStatement.setString(7, relation.getTelephone());
		preparedStatement.setString(8, relation.getEmail());
		preparedStatement.setString(9, relation.getTelephone_secondaire());
		preparedStatement.setString(10, relation.getEmail_secondaire());
		preparedStatement.setString(11, relation.getCivilite());
		preparedStatement.setInt(12, relation.getOrganisation());
		preparedStatement.setInt(13, relation.getMetier());
		preparedStatement.setString(14, relation.getCommentaire());
		preparedStatement.setString(15, "ContactDao");
		preparedStatement.setString(16, dateTime);
		preparedStatement.setString(17, System.getProperty("user.name"));
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table Relation"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER RELATION
		// =================================================================================
    @Override
    public void modifierRelation(Relation relation) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);

            String sql = "UPDATE relation SET nom=?, prenom=?, adresse=?, code_postal=?, ville=?, pays=?, telephone=?, email=?, telephone_secondaire=?, email_secondaire=?, civilite=?, organisation=?, metier=?, commentaire=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";

            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, relation.getNom());
    		preparedStatement.setString(2, relation.getPrenom());
    		preparedStatement.setString(3, relation.getAdresse());
    		preparedStatement.setString(4, relation.getCode_postal());
    		preparedStatement.setString(5, relation.getVille());
    		preparedStatement.setString(6, relation.getPays());
    		preparedStatement.setString(7, relation.getTelephone());
    		preparedStatement.setString(8, relation.getEmail());
    		preparedStatement.setString(9, relation.getTelephone_secondaire());
    		preparedStatement.setString(10, relation.getEmail_secondaire());
    		preparedStatement.setString(11, relation.getCivilite());
    		preparedStatement.setInt(12, relation.getOrganisation());
    		preparedStatement.setInt(13, relation.getMetier());
    		preparedStatement.setString(14, relation.getCommentaire());
    		preparedStatement.setString(15, "ContactDao");
    		preparedStatement.setString(16, dateTime);
    		preparedStatement.setString(17, System.getProperty("user.name"));
            preparedStatement.setInt(18, relation.getId());
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table Relation"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER RELATION
  		// =================================================================================
    @Override
    public void copierRelation(Relation relation) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO relation(nom, prenom, adresse, code_postal, ville, pays, telephone, email, telephone_secondaire, email_secondaire, civilite, organisation, metier, commentaire, pgmcreation, datecreation, usercreation ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, relation.getNom());
    		preparedStatement.setString(2, relation.getPrenom());
    		preparedStatement.setString(3, relation.getAdresse());
    		preparedStatement.setString(4, relation.getCode_postal());
    		preparedStatement.setString(5, relation.getVille());
    		preparedStatement.setString(6, relation.getPays());
    		preparedStatement.setString(7, relation.getTelephone());
    		preparedStatement.setString(8, relation.getEmail());
    		preparedStatement.setString(9, relation.getTelephone_secondaire());
    		preparedStatement.setString(10, relation.getEmail_secondaire());
    		preparedStatement.setString(11, relation.getCivilite());
    		preparedStatement.setInt(12, relation.getOrganisation());
    		preparedStatement.setInt(13, relation.getMetier());
    		preparedStatement.setString(14, relation.getCommentaire());
    		preparedStatement.setString(15, "ContactDao");
    		preparedStatement.setString(16, dateTime);
    		preparedStatement.setString(17, System.getProperty("user.name"));
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table Relation"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER RELATION
  		// =================================================================================

    @Override
    public void supprimerRelation(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM relation WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table Relation"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER RELATION
 		// =================================================================================
 		@Override
 		public void renommerRelation(Relation relation) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();

 				preparedStatement = connexion.prepareStatement(
 						"UPDATE relation SET relation.nom=?, relation.pgmmodification=?, relation.datemodification=?,"
 								+ " relation.usermodification=? where relation.id=?;");
 				preparedStatement.setString(1, relation.getNom());
 				preparedStatement.setString(2, "RENOMMER RELATION");
 				preparedStatement.setString(3, dateTime);
 				preparedStatement.setString(4, System.getProperty("user.name"));
 				preparedStatement.setInt(5, relation.getId());
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
  	// LISTER DES RELATIONS
  	// =================================================================================
    @Override
    public List<Relation> listerRelation() throws DaoException {
        List<Relation> relations = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, nom, prenom, adresse, code_postal, ville, pays, telephone, email, telephone_secondaire, email_secondaire, civilite, organisation, metier, commentaire FROM relation;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Relation relation = new Relation();
                relation.setId(resultat.getInt("id"));
                relation.setNom(resultat.getString("nom"));
                relation.setPrenom(resultat.getString("prenom"));
				relation.setAdresse(resultat.getString("adresse"));
				relation.setCode_postal(resultat.getString("code_postal"));
				relation.setVille(resultat.getString("ville"));
				relation.setPays(resultat.getString("pays"));
				relation.setEmail(resultat.getString("email"));
				relation.setTelephone(resultat.getString("telephone"));
				relation.setEmail_secondaire(resultat.getString("email_secondaire"));
				relation.setTelephone_secondaire(resultat.getString("telephone_secondaire"));
				relation.setCivilite(resultat.getString("civilite"));
				relation.setOrganisation(resultat.getInt("organisation"));
				relation.setMetier(resultat.getInt("metier"));
				relation.setCommentaire(resultat.getString("commentaire"));
                relations.add(relation);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Relation"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return relations;
    }
    @Override
    public List<Relation> listerRelationClient() throws DaoException {
    	return listerRelation();
    }
    @Override
    public List<Relation> listerRelationFournisseur() throws DaoException {
    	return listerRelation();
    }
    @Override
    public List<Relation> listerRelationSalarie() throws DaoException {
       return listerRelation();
    }

    	// =================================================================================
		// TROUVER RELATION PAR ID
		// =================================================================================
    @Override
    public Relation trouverRelation(Integer id) throws DaoException {
        Relation relation = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM relation WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                relation = new Relation();
                relation.setId(resultat.getInt("id") );
                relation.setNom(resultat.getString("nom"));
                relation.setPrenom(resultat.getString("prenom"));
				relation.setAdresse(resultat.getString("adresse"));
				relation.setCode_postal(resultat.getString("code_postal"));
				relation.setVille(resultat.getString("ville"));
				relation.setPays(resultat.getString("pays"));
				relation.setEmail(resultat.getString("email"));
				relation.setTelephone(resultat.getString("telephone"));
				relation.setEmail_secondaire(resultat.getString("email_secondaire"));
				relation.setTelephone_secondaire(resultat.getString("telephone_secondaire"));
				relation.setCivilite(resultat.getString("civilite"));
				relation.setOrganisation(resultat.getInt("organisation"));
				relation.setMetier(resultat.getInt("metier"));
				relation.setCommentaire(resultat.getString("commentaire"));
				relation.setUsermodification(resultat.getString("usermodification"));
                relation.setDatemodification(resultat.getDate("datemodification"));
                relation.setPgmmodification(resultat.getString("pgmmodification"));
                relation.setUsercreation(resultat.getString("usercreation"));
                relation.setDatecreation(resultat.getDate("datecreation"));
                relation.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table Relation"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return relation;
    }

    	// =================================================================================
		// TROUVER RELATION PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomRelation(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM relation WHERE nom=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table Relation"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE RELATION
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheRelations(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS Relation.id, Relation.nom, Relation.prenom, Relation.adresse, Relation.code_postal, Relation.ville, Relation.pays, Relation.telephone, Relation.email, Relation.telephone_secondaire, Relation.email_secondaire, Relation.civilite, Relation.organisation, Relation.metier, commentaire, organisation.raison_sociale, metier.nom FROM relation "
        	+ "left join organisation on organisation.id=Relation.organisation left join metier on metier.id=Relation.metier "
            + "ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
				/*
				 * Relation relation = new Relation(); relation.setId(rs.getInt("id"));
				 * relation.setNom(rs.getString("nom")); relation.setDomaine(rs.getString("domaine"));
				 * relation.setTaux(rs.getDouble("taux")); list.add(relation);
				 */
                Map<String, Object> relationFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                relationFields.put("id", rs.getInt("Relation.id"));
                }
                if (dictionnaire_nom_colonne.containsKey("nom")) {
                relationFields.put("nom", rs.getString("Relation.nom"));
                }
                if (dictionnaire_nom_colonne.containsKey("prenom")) {
                relationFields.put("prenom", rs.getString("Relation.prenom"));
                }
                if (dictionnaire_nom_colonne.containsKey("adresse")) {
                relationFields.put("adresse", rs.getString("Relation.adresse"));
                }
                if (dictionnaire_nom_colonne.containsKey("code_postal")) {
                relationFields.put("code_postal", rs.getString("Relation.code_postal"));
                }
                if (dictionnaire_nom_colonne.containsKey("ville")) {
                relationFields.put("ville", rs.getString("Relation.ville"));
                }
                if (dictionnaire_nom_colonne.containsKey("pays")) {
                relationFields.put("pays", rs.getString("Relation.pays"));
                }
                if (dictionnaire_nom_colonne.containsKey("email")) {
                relationFields.put("email", rs.getString("Relation.email"));
                }
                if (dictionnaire_nom_colonne.containsKey("telephone")) {
                relationFields.put("telephone", rs.getString("Relation.telephone"));
                }
                if (dictionnaire_nom_colonne.containsKey("email_secondaire")) {
                relationFields.put("email_secondaire", rs.getString("Relation.email_secondaire"));
                }
                if (dictionnaire_nom_colonne.containsKey("telephone_secondaire")) {
                relationFields.put("telephone_secondaire", rs.getString("Relation.telephone_secondaire"));
                }
                if (dictionnaire_nom_colonne.containsKey("civilite")) {
                relationFields.put("civilite", rs.getString("Relation.civilite"));
                }
                if (dictionnaire_nom_colonne.containsKey("organisation")) {
                relationFields.put("organisation", rs.getString("organisation.raison_sociale"));
                }
                if (dictionnaire_nom_colonne.containsKey("metier")) {
                relationFields.put("metier", rs.getString("metier.nom"));
                }
                if (dictionnaire_nom_colonne.containsKey("commentaire")) {
                relationFields.put("commentaire", rs.getString("commentaire"));
                }
                list.add(relationFields);
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
		// RECHERCHE RELATIONS SUIVANT LIKE
		// =================================================================================
    @Override
    public List<Map<String, Object>>  rechercheLikeRelations(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS SQL_CALC_FOUND_ROWS Relation.id, Relation.nom, Relation.prenom, Relation.adresse, Relation.code_postal, Relation.ville, Relation.pays, Relation.telephone, Relation.email, Relation.telephone_secondaire, Relation.email_secondaire, Relation.civilite, Relation.organisation, Relation.metier, commentaire, organisation.raison_sociale, metier.nom FROM relation "
            + "left join organisation on organisation.id=Relation.organisation left join metier on metier.id=Relation.metier "
             + "WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> relationFields = new LinkedHashMap<>();
            	if (dictionnaire_nom_colonne.containsKey("id")) {
                    relationFields.put("id", rs.getInt("Relation.id"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("nom")) {
                    relationFields.put("nom", rs.getString("Relation.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("prenom")) {
                    relationFields.put("prenom", rs.getString("Relation.prenom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("adresse")) {
                    relationFields.put("adresse", rs.getString("Relation.adresse"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("code_postal")) {
                    relationFields.put("code_postal", rs.getString("Relation.code_postal"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("ville")) {
                    relationFields.put("ville", rs.getString("Relation.ville"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("pays")) {
                    relationFields.put("pays", rs.getString("Relation.pays"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("email")) {
                    relationFields.put("email", rs.getString("Relation.email"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("telephone")) {
                    relationFields.put("telephone", rs.getString("Relation.telephone"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("email_secondaire")) {
                    relationFields.put("email_secondaire", rs.getString("Relation.email_secondaire"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("telephone_secondaire")) {
                    relationFields.put("telephone_secondaire", rs.getString("Relation.telephone_secondaire"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("civilite")) {
                    relationFields.put("civilite", rs.getString("Relation.civilite"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("organisation")) {
                    relationFields.put("organisation", rs.getString("organisation.raison_sociale"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("metier")) {
                    relationFields.put("metier", rs.getString("metier.nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("commentaire")) {
                    relationFields.put("commentaire", rs.getString("commentaire"));
                    }
                list.add(relationFields);
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
	// RECUPERATION N° ENREGISTREMENT ENCOURS RELATION
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
	// FERMETURE DES RESSOURCES RELATION
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