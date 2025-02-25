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
import com.ws.beans.Organisation;
import com.ws.configuration.DatasourceH;

public class OrganisationDaoImpl implements OrganisationDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  OrganisationDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER ORGANISATION
		// =================================================================================
    @Override
    public void ajouterOrganisation(Organisation organisation) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
			connexion = daoFactory.getConnection();
			// ------ On crée les données contrat ------ //
			preparedStatement = connexion.prepareStatement(
        "INSERT INTO organisation(entite, raison_sociale, adresse, code_postal, ville, pays, siren, siret, code_naf, tva, metier, telephone, email, relation, pgmcreation, datecreation, usercreation ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

		preparedStatement.setInt(1, organisation.getEntite());
		preparedStatement.setString(2, organisation.getRaison_sociale());
		preparedStatement.setString(3, organisation.getAdresse());
		preparedStatement.setString(4, organisation.getCode_postal());
		preparedStatement.setString(5, organisation.getVille());
		preparedStatement.setString(6, organisation.getPays());
		preparedStatement.setString(7, organisation.getSiren());
		preparedStatement.setString(8, organisation.getSiret());
		preparedStatement.setString(9, organisation.getCode_naf());
		preparedStatement.setInt(10, organisation.getTva());
		preparedStatement.setInt(11, organisation.getMetier());
		preparedStatement.setString(12, organisation.getTelephone());
		preparedStatement.setString(13, organisation.getEmail());
		preparedStatement.setInt(14, organisation.getRelation());
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table Organisation"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER ORGANISATION
		// =================================================================================
    @Override
    public void modifierOrganisation(Organisation organisation) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        System.out.println("debutmodidf"+organisation.toString());
        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE organisation SET entite=?, raison_sociale=?, adresse=?, code_postal=?, ville=?, pays=?, siren=?, siret=?, code_naf=?, tva=?, metier=?, telephone=?, email=?, relation=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, organisation.getEntite());
    		preparedStatement.setString(2, organisation.getRaison_sociale());
    		preparedStatement.setString(3, organisation.getAdresse());
    		preparedStatement.setString(4, organisation.getCode_postal());
    		preparedStatement.setString(5, organisation.getVille());
    		preparedStatement.setString(6, organisation.getPays());
    		preparedStatement.setString(7, organisation.getSiren());
    		preparedStatement.setString(8, organisation.getSiret());
    		preparedStatement.setString(9, organisation.getCode_naf());
    		preparedStatement.setInt(10, organisation.getTva());
    		preparedStatement.setInt(11, organisation.getMetier());
    		preparedStatement.setString(12, organisation.getTelephone());
    		preparedStatement.setString(13, organisation.getEmail());
    		preparedStatement.setInt(14, organisation.getRelation());
    		preparedStatement.setString(15, "ContactDao");
    		preparedStatement.setString(16, dateTime);
    		preparedStatement.setString(17, System.getProperty("user.name"));
            preparedStatement.setInt(18, organisation.getId());
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table Organisation"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER ORGANISATION
  		// =================================================================================
    @Override
    public void copierOrganisation(Organisation organisation) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO organisation(entite, raison_sociale, adresse, code_postal, ville, pays, siren, siret, code_naf, tva, metier, telephone, email, relation, pgmcreation, datecreation, usercreation ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, organisation.getEntite());
    		preparedStatement.setString(2, organisation.getRaison_sociale());
    		preparedStatement.setString(3, organisation.getAdresse());
    		preparedStatement.setString(4, organisation.getCode_postal());
    		preparedStatement.setString(5, organisation.getVille());
    		preparedStatement.setString(6, organisation.getPays());
    		preparedStatement.setString(7, organisation.getSiren());
    		preparedStatement.setString(8, organisation.getSiret());
    		preparedStatement.setString(9, organisation.getCode_naf());
    		preparedStatement.setInt(10, organisation.getTva());
    		preparedStatement.setInt(11, organisation.getMetier());
    		preparedStatement.setString(12, organisation.getTelephone());
    		preparedStatement.setString(13, organisation.getEmail());
    		preparedStatement.setInt(14, organisation.getRelation());
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table Organisation"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER ORGANISATION
  		// =================================================================================

    @Override
    public void supprimerOrganisation(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM organisation WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table Organisation"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER ORGANISATION
 		// =================================================================================
 		@Override
 		public void renommerOrganisation(Organisation organisation) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();

 				preparedStatement = connexion.prepareStatement(
 						"UPDATE organisation SET organisation.raison_sociale=?, organisation.pgmmodification=?, organisation.datemodification=?,"
 								+ " organisation.usermodification=? where organisation.id=?;");
 				preparedStatement.setString(1, organisation.getRaison_sociale());
 				preparedStatement.setString(2, "RENOMMER ORGANISATION");
 				preparedStatement.setString(3, dateTime);
 				preparedStatement.setString(4, System.getProperty("user.name"));
 				preparedStatement.setInt(5, organisation.getId());
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
  	// LISTER DES ORGANISATIONS
  	// =================================================================================
    @Override
    public List<Organisation> listerOrganisation() throws DaoException {
        List<Organisation> organisations = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, entite, raison_sociale, adresse, code_postal, ville, pays, siren, siret, code_naf, tva, metier, telephone, email, relation FROM organisation;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Organisation organisation = new Organisation();
                organisation.setId(resultat.getInt("id"));
                organisation.setEntite(resultat.getInt("entite"));
                organisation.setRaison_sociale(resultat.getString("raison_sociale"));
                organisation.setNom(resultat.getString("raison_sociale"));
				organisation.setAdresse(resultat.getString("adresse"));
				organisation.setCode_postal(resultat.getString("code_postal"));
				organisation.setVille(resultat.getString("ville"));
				organisation.setPays(resultat.getString("pays"));
				organisation.setSiren(resultat.getString("siren"));
				organisation.setSiret(resultat.getString("siret"));
				organisation.setCode_naf(resultat.getString("code_naf"));
				organisation.setTva(resultat.getInt("tva"));
				organisation.setMetier(resultat.getInt("metier"));
				organisation.setTelephone(resultat.getString("telephone"));
				organisation.setEmail(resultat.getString("email"));
				organisation.setRelation(resultat.getInt("relation"));
                organisations.add(organisation);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Organisation"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return organisations;
    }

    @Override
    public List<Organisation> listerOrganisationClient() throws DaoException {
        List<Organisation> organisations = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT organisation.id, organisation.entite, raison_sociale, adresse, code_postal, ville, pays, siren, siret, code_naf, tva, metier, telephone, email, relation FROM organisation "
            		+ "inner join entite on entite.id=organisation.entite where entite.nom='Client'"
            		+ ";";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Organisation organisation = new Organisation();
                organisation.setId(resultat.getInt("organisation.id"));
                organisation.setEntite(resultat.getInt("organisation.entite"));
                organisation.setRaison_sociale(resultat.getString("raison_sociale"));
                organisation.setNom(resultat.getString("raison_sociale"));
				organisation.setAdresse(resultat.getString("adresse"));
				organisation.setCode_postal(resultat.getString("code_postal"));
				organisation.setVille(resultat.getString("ville"));
				organisation.setPays(resultat.getString("pays"));
				organisation.setSiren(resultat.getString("siren"));
				organisation.setSiret(resultat.getString("siret"));
				organisation.setCode_naf(resultat.getString("code_naf"));
				organisation.setTva(resultat.getInt("tva"));
				organisation.setMetier(resultat.getInt("metier"));
				organisation.setTelephone(resultat.getString("telephone"));
				organisation.setEmail(resultat.getString("email"));
				organisation.setRelation(resultat.getInt("relation"));
                organisations.add(organisation);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Organisation"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return organisations;
    }

    @Override
    public List<Organisation> listerOrganisationFournisseur() throws DaoException {
        List<Organisation> organisations = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT organisation.id, organisation.entite, raison_sociale, adresse, code_postal, ville, pays, siren, siret, code_naf, tva, metier, telephone, email, relation FROM organisation"
            		+ " inner join entite on entite.id=organisation.entite where entite.nom='Fournisseur'"
            		+ ";";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Organisation organisation = new Organisation();
                organisation.setId(resultat.getInt("organisation.id"));
                organisation.setEntite(resultat.getInt("organisation.entite"));
                organisation.setRaison_sociale(resultat.getString("raison_sociale"));
                organisation.setNom(resultat.getString("raison_sociale"));
				organisation.setAdresse(resultat.getString("adresse"));
				organisation.setCode_postal(resultat.getString("code_postal"));
				organisation.setVille(resultat.getString("ville"));
				organisation.setPays(resultat.getString("pays"));
				organisation.setSiren(resultat.getString("siren"));
				organisation.setSiret(resultat.getString("siret"));
				organisation.setCode_naf(resultat.getString("code_naf"));
				organisation.setTva(resultat.getInt("tva"));
				organisation.setMetier(resultat.getInt("metier"));
				organisation.setTelephone(resultat.getString("telephone"));
				organisation.setEmail(resultat.getString("email"));
				organisation.setRelation(resultat.getInt("relation"));
                organisations.add(organisation);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Organisation"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return organisations;
    }

    @Override
    public List<Organisation> listerOrganisationSalarie() throws DaoException {
        List<Organisation> organisations = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT organisation.id, organisation.entite, raison_sociale, adresse, code_postal, ville, pays, siren, siret, code_naf, tva, metier, telephone, email, relation FROM organisation"
            		+ " inner join entite on entite.id=organisation.entite where entite.nom='Salarie'"
            		+ ";";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Organisation organisation = new Organisation();
                organisation.setId(resultat.getInt("organisation.id"));
                organisation.setEntite(resultat.getInt("organisation.entite"));
                organisation.setRaison_sociale(resultat.getString("raison_sociale"));
                organisation.setNom(resultat.getString("raison_sociale"));
				organisation.setAdresse(resultat.getString("adresse"));
				organisation.setCode_postal(resultat.getString("code_postal"));
				organisation.setVille(resultat.getString("ville"));
				organisation.setPays(resultat.getString("pays"));
				organisation.setSiren(resultat.getString("siren"));
				organisation.setSiret(resultat.getString("siret"));
				organisation.setCode_naf(resultat.getString("code_naf"));
				organisation.setTva(resultat.getInt("tva"));
				organisation.setMetier(resultat.getInt("metier"));
				organisation.setTelephone(resultat.getString("telephone"));
				organisation.setEmail(resultat.getString("email"));
				organisation.setRelation(resultat.getInt("relation"));
                organisations.add(organisation);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Organisation"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return organisations;
    }

    	// =================================================================================
		// TROUVER ORGANISATION PAR ID
		// =================================================================================
    @Override
    public Organisation trouverOrganisation(Integer id) throws DaoException {
        Organisation organisation = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM organisation WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                organisation = new Organisation();
                organisation.setId(resultat.getInt("id"));
                organisation.setEntite(resultat.getInt("entite"));
                organisation.setRaison_sociale(resultat.getString("raison_sociale"));
				organisation.setAdresse(resultat.getString("adresse"));
				organisation.setCode_postal(resultat.getString("code_postal"));
				organisation.setVille(resultat.getString("ville"));
				organisation.setPays(resultat.getString("pays"));
				organisation.setSiren(resultat.getString("siren"));
				organisation.setSiret(resultat.getString("siret"));
				organisation.setCode_naf(resultat.getString("code_naf"));
				organisation.setTva(resultat.getInt("tva"));
				organisation.setMetier(resultat.getInt("metier"));
				organisation.setTelephone(resultat.getString("telephone"));
				organisation.setEmail(resultat.getString("email"));
				organisation.setRelation(resultat.getInt("relation"));
				organisation.setUsermodification(resultat.getString("usermodification"));
                organisation.setDatemodification(resultat.getDate("datemodification"));
                organisation.setPgmmodification(resultat.getString("pgmmodification"));
                organisation.setUsercreation(resultat.getString("usercreation"));
                organisation.setDatecreation(resultat.getDate("datecreation"));
                organisation.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table Organisation"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return organisation;
    }

    	// =================================================================================
		// TROUVER ORGANISATION PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomOrganisation(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM organisation WHERE raison_sociale=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table Organisation"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE ORGANISATION
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheOrganisations(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String entite) {


    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String query;
        int id_entite = 0;

        try {
        	connexion = daoFactory.getConnection();
        	if (entite == null || entite.trim().isEmpty()) {
        		query = "SELECT SQL_CALC_FOUND_ROWS organisation.id, organisation.entite, raison_sociale, organisation.adresse, organisation.code_postal, organisation.ville, organisation.pays, siren, siret, code_naf, organisation.tva, organisation.metier, organisation.telephone, organisation.email, organisation.relation, entite.nom, metier.libelle, tva.libelle, relation.prenom, relation.nom FROM organisation "
                		+ "left join entite on organisation.entite=entite.id left join tva on organisation.tva=tva.id left join metier on organisation.metier=metier.id left join relation on organisation.relation=relation.id "
                		+ "ORDER BY " + select_tri + " LIMIT ?, ?";
            }
        	else {
        		try {
                	connexion = daoFactory.getConnection();
                    String sql = "SELECT id FROM entite WHERE nom=?;";
                    preparedStatement = connexion.prepareStatement(sql);
                    preparedStatement.setString(1, entite);
                    rs = preparedStatement.executeQuery();
                    if (rs.next()) {
                        id_entite = rs.getInt("id");
                    }
                }  finally {

                }
        		query = "SELECT SQL_CALC_FOUND_ROWS organisation.id, organisation.entite, raison_sociale, organisation.adresse, organisation.code_postal, organisation.ville, organisation.pays, siren, siret, code_naf, organisation.tva, organisation.metier, organisation.telephone, organisation.email, organisation.relation, entite.nom, metier.libelle, tva.libelle, relation.prenom, relation.nom FROM organisation "
            		+ "left join entite on organisation.entite=entite.id left join tva on organisation.tva=tva.id left join metier on organisation.metier=metier.id left join relation on organisation.relation=relation.id "
        			+ " where entite.id= "+	id_entite
            		+ " ORDER BY " + select_tri + " LIMIT ?, ?";
        	}
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Map<String, Object> organisationFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                organisationFields.put("id", rs.getInt("id"));
                }
                if (dictionnaire_nom_colonne.containsKey("entite")) {
                organisationFields.put("entite", rs.getString("entite.nom"));
                }
                if (dictionnaire_nom_colonne.containsKey("raison_sociale")) {
                organisationFields.put("raison_sociale", rs.getString("raison_sociale"));
                }
                if (dictionnaire_nom_colonne.containsKey("adresse")) {
                organisationFields.put("adresse", rs.getString("adresse"));
                }
                if (dictionnaire_nom_colonne.containsKey("code_postal")) {
                organisationFields.put("code_postal", rs.getString("code_postal"));
                }
                if (dictionnaire_nom_colonne.containsKey("ville")) {
                organisationFields.put("ville", rs.getString("ville"));
                }
                if (dictionnaire_nom_colonne.containsKey("pays")) {
                organisationFields.put("pays", rs.getString("pays"));
                }
                if (dictionnaire_nom_colonne.containsKey("siren")) {
                organisationFields.put("siren", rs.getString("siren"));
                }
                if (dictionnaire_nom_colonne.containsKey("siret")) {
                organisationFields.put("siret", rs.getString("siret"));
                }
                if (dictionnaire_nom_colonne.containsKey("code_naf")) {
                organisationFields.put("code_naf", rs.getString("code_naf"));
                }
                if (dictionnaire_nom_colonne.containsKey("tva")) {
                organisationFields.put("tva", rs.getString("tva.libelle"));
                }
                if (dictionnaire_nom_colonne.containsKey("metier")) {
                organisationFields.put("metier", rs.getString("metier.libelle"));
                }
                if (dictionnaire_nom_colonne.containsKey("email")) {
                organisationFields.put("email", rs.getString("organisation.email"));
                }
                if (dictionnaire_nom_colonne.containsKey("telephone")) {
                organisationFields.put("telephone", rs.getString("telephone"));
                }
                if (dictionnaire_nom_colonne.containsKey("relation")) {
                organisationFields.put("relation", rs.getString("relation.prenom")+""+rs.getString("relation.nom"));
                }
                list.add(organisationFields);
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
		// RECHERCHE ORGANISATIONS SUIVANT LIKE
		// =================================================================================
    @Override
    public List<Map<String, Object>>  rechercheLikeOrganisations(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM organisation WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> organisationFields = new LinkedHashMap<>();
            	if (dictionnaire_nom_colonne.containsKey("id")) {
                    organisationFields.put("id", rs.getInt("id"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("entite")) {
                    organisationFields.put("entite", rs.getString("entite"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("raison_sociale")) {
                    organisationFields.put("raison_sociale", rs.getString("raison_sociale"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("adresse")) {
                    organisationFields.put("adresse", rs.getString("adresse"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("code_postal")) {
                    organisationFields.put("code_postal", rs.getString("code_postal"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("ville")) {
                    organisationFields.put("ville", rs.getString("ville"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("pays")) {
                    organisationFields.put("pays", rs.getString("pays"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("siren")) {
                    organisationFields.put("siren", rs.getString("siren"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("siret")) {
                    organisationFields.put("siret", rs.getString("siret"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("code_naf")) {
                    organisationFields.put("code_naf", rs.getString("code_naf"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("tva")) {
                    organisationFields.put("tva", rs.getInt("tva"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("metier")) {
                    organisationFields.put("metier", rs.getInt("metier"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("email")) {
                    organisationFields.put("email", rs.getString("email"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("telephone")) {
                    organisationFields.put("telephone", rs.getString("telephone"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("relation")) {
                    organisationFields.put("relation", rs.getInt("relation"));
                    }

                list.add(organisationFields);
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
	// RECUPERATION N° ENREGISTREMENT ENCOURS ORGANISATION
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
	// FERMETURE DES RESSOURCES ORGANISATION
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