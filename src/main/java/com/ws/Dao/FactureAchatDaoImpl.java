package com.ws.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ws.beans.Activite;
import com.ws.beans.ContratAncien;
import com.ws.beans.FactureAchat;
import com.ws.configuration.DatasourceH;

public class FactureAchatDaoImpl implements FactureAchatDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  FactureAchatDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER FACTUREACHAT
		// =================================================================================
    @Override
    public void ajouterFactureAchat(FactureAchat factureAchat) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion  = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO factureAchat(nom, entite, organisation, typeFactureAchat, document, tva, date_facture, montant_ht, montant_ttc, montant_tva,  date_import, user_import, pgm_import, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, factureAchat.getNom());
            preparedStatement.setInt(2, factureAchat.getEntite());
            preparedStatement.setInt(3, factureAchat.getOrganisation());
            preparedStatement.setInt(4, factureAchat.getTypeFactureAchat());
            preparedStatement.setString(5, factureAchat.getDocument());
            preparedStatement.setInt(6, factureAchat.getTva());
            if (factureAchat.getDate_facture().isEmpty() ) {
            	preparedStatement.setNull(7, java.sql.Types.DATE);
            } else {
            preparedStatement.setString(7, factureAchat.getDate_facture());
            }
            preparedStatement.setDouble(8, factureAchat.getMontant_ht());
            preparedStatement.setDouble(9, factureAchat.getMontant_ttc());
            preparedStatement.setDouble(10, factureAchat.getMontant_tva());
            if (factureAchat.getDate_import().trim().isEmpty()) {
            	preparedStatement.setNull(11, java.sql.Types.DATE);
            } else {
            	preparedStatement.setString(11, factureAchat.getDate_import());
            }
            preparedStatement.setString(12, factureAchat.getUser_import());
            preparedStatement.setString(13, factureAchat.getPgm_import());
            preparedStatement.setString(14, "FactureAchatDao");
            preparedStatement.setString(15, dateTime);
            preparedStatement.setString(16, System.getProperty("user.name"));
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table FactureAchat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER FACTUREACHAT
		// =================================================================================
    @Override
    public void modifierFactureAchat(FactureAchat factureAchat) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE factureAchat SET nom=?, entite=?, organisation=?, typeFactureAchat=?, document=?, tva=?, date_facture=?, montant_ht=?, montant_ttc=?, montant_tva=?,  date_import=?, user_import=?, pgm_import=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, factureAchat.getNom());
            preparedStatement.setInt(2, factureAchat.getEntite());
            preparedStatement.setInt(3, factureAchat.getOrganisation());
            preparedStatement.setInt(4, factureAchat.getTypeFactureAchat());
            preparedStatement.setString(5, factureAchat.getDocument());
            preparedStatement.setInt(6, factureAchat.getTva());
            if (factureAchat.getDate_facture().isEmpty() ) {
            	preparedStatement.setNull(7, java.sql.Types.DATE);
            } else {
            preparedStatement.setString(7, factureAchat.getDate_facture());
            }
            preparedStatement.setDouble(8, factureAchat.getMontant_ht());
            preparedStatement.setDouble(9, factureAchat.getMontant_ttc());
            preparedStatement.setDouble(10, factureAchat.getMontant_tva());
            if (factureAchat.getDate_import().trim().isEmpty()) {
            	preparedStatement.setNull(11, java.sql.Types.DATE);
            } else {
            	preparedStatement.setString(11, factureAchat.getDate_import());
            }
            preparedStatement.setString(12, factureAchat.getUser_import());
            preparedStatement.setString(13, factureAchat.getPgm_import());
            preparedStatement.setString(14, "FactureAchatDao");
            preparedStatement.setString(15, dateTime);
            preparedStatement.setString(16, System.getProperty("user.name"));
            preparedStatement.setInt(17, factureAchat.getId());
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table FactureAchat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER FACTUREACHAT
  		// =================================================================================
    @Override
    public void copierFactureAchat(FactureAchat factureAchat) throws DaoException {
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion  = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO factureAchat(nom, entite, organisation, typeFactureAchat, document, tva, date_facture, montant_ht, montant_ttc, montant_tva,  date_import, user_import, pgm_import, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, factureAchat.getNom());
            preparedStatement.setInt(2, factureAchat.getEntite());
            preparedStatement.setInt(3, factureAchat.getOrganisation());
            preparedStatement.setInt(4, factureAchat.getTypeFactureAchat());
            preparedStatement.setString(5, factureAchat.getDocument());
            preparedStatement.setInt(6, factureAchat.getTva());
            if (factureAchat.getDate_facture().isEmpty() ) {
            	preparedStatement.setNull(7, java.sql.Types.DATE);
            } else {
            preparedStatement.setString(7, factureAchat.getDate_facture());
            }
            preparedStatement.setDouble(8, factureAchat.getMontant_ht());
            preparedStatement.setDouble(9, factureAchat.getMontant_ttc());
            preparedStatement.setDouble(10, factureAchat.getMontant_tva());
            if (factureAchat.getDate_import().trim().isEmpty()) {
            	preparedStatement.setNull(11, java.sql.Types.DATE);
            } else {
            	preparedStatement.setString(11, factureAchat.getDate_import());
            }
            preparedStatement.setString(12, factureAchat.getUser_import());
            preparedStatement.setString(13, factureAchat.getPgm_import());
            preparedStatement.setString(14, "FactureAchatDao");
            preparedStatement.setString(15, dateTime);
            preparedStatement.setString(16, System.getProperty("user.name"));
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table FactureAchat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER FACTUREACHAT
  		// =================================================================================

    @Override
    public void supprimerFactureAchat(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM factureAchat WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table FactureAchat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER FACTUREACHAT
 		// =================================================================================
 		@Override
 		public void renommerFactureAchat(FactureAchat factureAchat) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();

 				preparedStatement = connexion.prepareStatement(
 						"UPDATE factureAchat SET factureAchat.nom=?, factureAchat.pgmmodification=?, factureAchat.datemodification=?,"
 								+ " factureAchat.usermodification=? where factureAchat.id=?;");
 				preparedStatement.setString(1, factureAchat.getNom());
 				preparedStatement.setString(2, "RENOMMER FACTUREACHAT");
 				preparedStatement.setString(3, dateTime);
 				preparedStatement.setString(4, System.getProperty("user.name"));
 				preparedStatement.setInt(5, factureAchat.getId());
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
  	// LISTER DES FACTUREACHATS
  	// =================================================================================
    @Override
    public List<FactureAchat> listerFactureAchat() throws DaoException {
        List<FactureAchat> factureAchats = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, nom, entite, organisation, typeFactureAchat, document, tva, date_facture, montant_ht, montant_ttc, montant_tva,  date_import, user_import, pgm_import FROM factureAchat;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                FactureAchat factureAchat = new FactureAchat();
                factureAchat.setId(resultat.getInt("id"));
                factureAchat.setNom(resultat.getString("nom"));
                factureAchat.setEntite(resultat.getInt("entite"));
                factureAchat.setEntite(resultat.getInt("organisation"));
                factureAchat.setEntite(resultat.getInt("typeFactureAchat"));
                factureAchat.setDocument(resultat.getString("document"));
                factureAchat.setTva(resultat.getInt("tva"));
                factureAchat.setDate_facture(resultat.getString("date_facture"));
                factureAchat.setMontant_ht(resultat.getDouble("montant_ht"));
                factureAchat.setMontant_ttc(resultat.getDouble("montant_ttc"));
                factureAchat.setMontant_tva(resultat.getDouble("montant_tva"));
                factureAchat.setDate_import(resultat.getString("date_import"));
                factureAchat.setUser_import(resultat.getString("user_import"));
                factureAchat.setPgm_import(resultat.getString("pgm_import"));
                factureAchats.add(factureAchat);
                }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table FactureAchat"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return factureAchats;
    }

    @Override
    public List<FactureAchat> listerFactureAchatClient() throws DaoException {
        List<FactureAchat> factureAchats = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, nom, entite, organisation, tva, date_facture, typeFactureAchat, document, montant_ht, montant_ttc, montant_tva,  date_import, user_import, pgm_import FROM factureAchat where nom='Client';";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                FactureAchat factureAchat = new FactureAchat();
                factureAchat.setId(resultat.getInt("id"));
                factureAchat.setNom(resultat.getString("nom"));
                factureAchat.setEntite(resultat.getInt("entite"));
                factureAchat.setEntite(resultat.getInt("organisation"));
                factureAchat.setEntite(resultat.getInt("typeFactureAchat"));
                factureAchat.setDocument(resultat.getString("document"));
                factureAchat.setTva(resultat.getInt("tva"));
                factureAchat.setDate_facture(resultat.getString("date_facture"));
                factureAchat.setMontant_ht(resultat.getDouble("montant_ht"));
                factureAchat.setMontant_ttc(resultat.getDouble("montant_ttc"));
                factureAchat.setMontant_tva(resultat.getDouble("montant_tva"));
                factureAchat.setDate_import(resultat.getString("date_import"));
                factureAchat.setUser_import(resultat.getString("user_import"));
                factureAchat.setPgm_import(resultat.getString("pgm_import"));
                factureAchats.add(factureAchat);
                }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table FactureAchat"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return factureAchats;
    }

    @Override
    public List<FactureAchat> listerFactureAchatFournisseur() throws DaoException {
        List<FactureAchat> factureAchats = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, nom, entite, organisation, typeFactureAchat, document, tva, date_facture, montant_ht, montant_ttc, montant_tva,  date_import, user_import, pgm_import FROM factureAchat where nom='Fournisseur';";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                FactureAchat factureAchat = new FactureAchat();
                factureAchat.setId(resultat.getInt("id"));
                factureAchat.setNom(resultat.getString("nom"));
                factureAchat.setEntite(resultat.getInt("entite"));
                factureAchat.setEntite(resultat.getInt("organisation"));
                factureAchat.setEntite(resultat.getInt("typeFactureAchat"));
                factureAchat.setDocument(resultat.getString("document"));
                factureAchat.setTva(resultat.getInt("tva"));
                factureAchat.setDate_facture(resultat.getString("date_facture"));
                factureAchat.setMontant_ht(resultat.getDouble("montant_ht"));
                factureAchat.setMontant_ttc(resultat.getDouble("montant_ttc"));
                factureAchat.setMontant_tva(resultat.getDouble("montant_tva"));
                factureAchat.setDate_import(resultat.getString("date_import"));
                factureAchat.setUser_import(resultat.getString("user_import"));
                factureAchat.setPgm_import(resultat.getString("pgm_import"));
                factureAchats.add(factureAchat);
                }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table FactureAchat"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return factureAchats;
    }

    @Override
    public List<FactureAchat> listerFactureAchatSalarie() throws DaoException {
        List<FactureAchat> factureAchats = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, nom, entite, organisation, typeFactureAchat, document, tva, date_facture, montant_ht, montant_ttc, montant_tva,  date_import, user_import, pgm_import FROM factureAchat where nom='Salarie';";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                FactureAchat factureAchat = new FactureAchat();
                factureAchat.setId(resultat.getInt("id"));
                factureAchat.setNom(resultat.getString("nom"));
                factureAchat.setEntite(resultat.getInt("entite"));
                factureAchat.setEntite(resultat.getInt("organisation"));
                factureAchat.setEntite(resultat.getInt("typeFactureAchat"));
                factureAchat.setDocument(resultat.getString("document"));
                factureAchat.setTva(resultat.getInt("tva"));
                factureAchat.setDate_facture(resultat.getString("date_facture"));
                factureAchat.setMontant_ht(resultat.getDouble("montant_ht"));
                factureAchat.setMontant_ttc(resultat.getDouble("montant_ttc"));
                factureAchat.setMontant_tva(resultat.getDouble("montant_tva"));
                factureAchat.setDate_import(resultat.getString("date_import"));
                factureAchat.setUser_import(resultat.getString("user_import"));
                factureAchat.setPgm_import(resultat.getString("pgm_import"));
                factureAchats.add(factureAchat);
                }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table FactureAchat"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return factureAchats;
    }
    	// =================================================================================
		// TROUVER FACTUREACHAT PAR ID
		// =================================================================================
    @Override
    public FactureAchat trouverFactureAchat(Integer id) throws DaoException {
        FactureAchat factureAchat = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM factureAchat WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
            	factureAchat = new FactureAchat();
                factureAchat.setId(resultat.getInt("id"));
                factureAchat.setNom(resultat.getString("nom"));
                factureAchat.setEntite(resultat.getInt("entite"));
                factureAchat.setEntite(resultat.getInt("organisation"));
                factureAchat.setEntite(resultat.getInt("typeFactureAchat"));
                factureAchat.setDocument(resultat.getString("document"));
                factureAchat.setTva(resultat.getInt("tva"));
                factureAchat.setDate_facture(resultat.getString("date_facture"));
                factureAchat.setMontant_ht(resultat.getDouble("montant_ht"));
                factureAchat.setMontant_ttc(resultat.getDouble("montant_ttc"));
                factureAchat.setMontant_tva(resultat.getDouble("montant_tva"));
                factureAchat.setDate_import(resultat.getString("date_import"));
                factureAchat.setUser_import(resultat.getString("user_import"));
                factureAchat.setPgm_import(resultat.getString("pgm_import"));
                factureAchat.setUsermodification(resultat.getString("usermodification"));
                factureAchat.setDatemodification(resultat.getDate("datemodification"));
                factureAchat.setPgmmodification(resultat.getString("pgmmodification"));
                factureAchat.setUsercreation(resultat.getString("usercreation"));
                factureAchat.setDatecreation(resultat.getDate("datecreation"));
                factureAchat.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table FactureAchat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return factureAchat;
    }

    	// =================================================================================
		// TROUVER FACTUREACHAT PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomFactureAchat(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM factureAchat WHERE nom=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table FactureAchat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE FACTUREACHAT
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheFactureAchats(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_factureAchat) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS  factureAchat.id, factureAchat.nom, document, factureAchat.entite, factureAchat.tva, date_facture, montant_ht, montant_ttc, montant_tva,  date_import, user_import, pgm_import, tva.nom, entite.nom, organisation.raison_sociale, typeFactureAchat.nom FROM factureAchat "
            		+ "left join tva on factureAchat.tva=tva.id left join entite on factureAchat.entite=entite.id left join organisation on factureAchat.organisation=organisation.id left join typeFactureAchat on factureAchat.typeFactureAchat=typeFactureAchat.id "
            		+ " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            System.out.println(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Map<String, Object> factureAchatFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                	factureAchatFields.put("id", rs.getInt("id"));
				}
                if (dictionnaire_nom_colonne.containsKey("nom")) {
                	factureAchatFields.put("nom", rs.getString("nom"));
				}
                if (dictionnaire_nom_colonne.containsKey("entite")) {
                	factureAchatFields.put("entite", rs.getString("entite.nom"));
				}
                if (dictionnaire_nom_colonne.containsKey("organisation")) {
                	factureAchatFields.put("organisation", rs.getString("organisation.raison_sociale"));
				}
                if (dictionnaire_nom_colonne.containsKey("typeFactureAchat")) {
                	factureAchatFields.put("typeFactureAchat", rs.getString("typeFactureAchat.nom"));
				}
                if (dictionnaire_nom_colonne.containsKey("document")) {
                	factureAchatFields.put("document", rs.getString("document"));
				}
                if (dictionnaire_nom_colonne.containsKey("tva")) {
                	factureAchatFields.put("tva", rs.getString("tva.nom"));
				}
                if (dictionnaire_nom_colonne.containsKey("date_facture")) {
                	factureAchatFields.put("date_facture", rs.getString("date_facture"));
				}
                if (dictionnaire_nom_colonne.containsKey("montant_ht")) {
                	factureAchatFields.put("montant_ht", rs.getDouble("montant_ht"));
				}
                if (dictionnaire_nom_colonne.containsKey("montant_ttc")) {
                	factureAchatFields.put("montant_ttc", rs.getDouble("montant_ttc"));
				}
                if (dictionnaire_nom_colonne.containsKey("montant_tva")) {
                	factureAchatFields.put("montant_tva", rs.getDouble("montant_tva"));
				}
                if (dictionnaire_nom_colonne.containsKey("date_import")) {
                	factureAchatFields.put("date_import", rs.getString("date_import"));
				}
                if (dictionnaire_nom_colonne.containsKey("user_import")) {
                	factureAchatFields.put("user_import", rs.getString("user_import"));
				}
                if (dictionnaire_nom_colonne.containsKey("pgm_import")) {
                	factureAchatFields.put("pgm_import", rs.getString("pgm_import"));
				}
                list.add(factureAchatFields);
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
		// RECHERCHE FACTUREACHATS SUIVANT LIKE
		// =================================================================================
    @Override
    public List<Map<String, Object>>  rechercheLikeFactureAchats(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_factureAchat) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query ="SELECT SQL_CALC_FOUND_ROWS  factureAchat.id, factureAchat.nom, factureAchat.entite, document, factureAchat.tva, date_facture, montant_ht, montant_ttc, montant_tva,  date_import, user_import, pgm_import, tva.nom, entite.nom, organisation.raison_sociale, typeFactureAchat.nom FROM factureAchat "
            		+ "left join tva on factureAchat.tva=tva.id left join entite on factureAchat.entite=entite.id  left join organisation on factureAchat.organisation=organisation.id  left join typeFactureAchat on factureAchat.typeFactureAchat=typeFactureAchat.id "
            		+ " and " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> factureAchatFields = new LinkedHashMap<>();
            	 if (dictionnaire_nom_colonne.containsKey("id")) {
                 	factureAchatFields.put("id", rs.getInt("id"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("nom")) {
                 	factureAchatFields.put("nom", rs.getString("nom"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("entite")) {
                 	factureAchatFields.put("entite", rs.getString("entite.nom"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("organisation")) {
                 	factureAchatFields.put("organisation", rs.getString("organisation.raison_sociale"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("typeFactureAchat")) {
                 	factureAchatFields.put("typeFactureAchat", rs.getString("typeFactureAchat.nom"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("document")) {
                 	factureAchatFields.put("document", rs.getString("document"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("tva")) {
                 	factureAchatFields.put("tva", rs.getString("tva.nom"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("date_facture")) {
                 	factureAchatFields.put("date_facture", rs.getString("date_facture"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("montant_ht")) {
                 	factureAchatFields.put("montant_ht", rs.getDouble("montant_ht"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("montant_ttc")) {
                 	factureAchatFields.put("montant_ttc", rs.getDouble("montant_ttc"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("montant_tva")) {
                 	factureAchatFields.put("montant_tva", rs.getDouble("montant_tva"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("date_import")) {
                 	factureAchatFields.put("date_import", rs.getString("date_import"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("user_import")) {
                 	factureAchatFields.put("user_import", rs.getString("user_import"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("pgm_import")) {
                 	factureAchatFields.put("pgm_import", rs.getString("pgm_import"));
 				}
                list.add(factureAchatFields);
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
	// RECUPERATION N° ENREGISTREMENT ENCOURS FACTUREACHAT
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
	// FERMETURE DES RESSOURCES FACTUREACHAT
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