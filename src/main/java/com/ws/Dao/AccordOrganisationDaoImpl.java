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

import com.ws.beans.AccordOrganisation;
import com.ws.beans.Statut;

public class AccordOrganisationDaoImpl implements AccordOrganisationDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  AccordOrganisationDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }


	  	// =================================================================================
		// AJOUTER ACCORDORGANISATION
		// =================================================================================
    @Override
    public void ajouterAccordOrganisation(AccordOrganisation accordOrganisation) throws DaoException {
    	Connection connexion = null;
    	PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        int lastid_unique =0;
        int id =0;
        try {
        	connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false); // Début de la transaction

			// ------ On récupère l'id du dernier accordOrganisation créé + 1 ------
			preparedStatement = connexion.prepareStatement("SELECT MAX(id_unique) AS id_unique FROM accordOrganisation");
			resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				 lastid_unique = resultat.getInt("id_unique") + 1 ;
			}else {
				throw new SQLException("Creating contratOrganisation failed, no ID obtained.");
			}
			// ------ On crée les données accordOrganisation ------ //
            String sql = "INSERT INTO accordOrganisation(id_unique, version, id, nom, typeContrat, statut, organisation, date_demarrage, date_fin, commentaire,  pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, lastid_unique);
            preparedStatement.setInt(2, 1);
            id = lastid_unique*10 + 1;
            preparedStatement.setInt(3, id);
            preparedStatement.setString(4, accordOrganisation.getNom());
            preparedStatement.setInt(5, accordOrganisation.getTypeContrat());
            preparedStatement.setInt(6, accordOrganisation.getStatut());
            preparedStatement.setInt(7, accordOrganisation.getOrganisation());
            preparedStatement.setString(8, accordOrganisation.getDate_demarrage());
            preparedStatement.setString(9, accordOrganisation.getDate_fin());
            preparedStatement.setString(10, accordOrganisation.getCommentaire());
            preparedStatement.setString(11, "AccordOrganisationDao");
            preparedStatement.setString(12, dateTime);
            preparedStatement.setString(13, System.getProperty("user.name"));
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table AccordOrganisation"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER ACCORDORGANISATION
		// =================================================================================
    @Override
    public void modifierAccordOrganisation(AccordOrganisation accordOrganisation) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        int id_unique =0;
        int version = 0;
        int id = accordOrganisation.getId();
     // Retrouver id et version
        id_unique = id / 10;        // Division entière pour obtenir l'id
        version = id % 10;  // Modulo pour obtenir la version

        // Afficher les résultats
         try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE accordOrganisation SET nom=?, typeContrat=?, statut=?, organisation=?, date_demarrage=?, date_fin=?, commentaire=?,  pgmmodification=?, datemodification=?, usermodification=? WHERE id_unique=? and version=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, accordOrganisation.getNom());
            preparedStatement.setInt(2, accordOrganisation.getTypeContrat());
            preparedStatement.setInt(3, accordOrganisation.getStatut());
            preparedStatement.setInt(4, accordOrganisation.getOrganisation());
            preparedStatement.setString(5, accordOrganisation.getDate_demarrage());
            preparedStatement.setString(6, accordOrganisation.getDate_fin());
            preparedStatement.setString(7, accordOrganisation.getCommentaire());
            preparedStatement.setString(8, "AccordOrganisationDao");
            preparedStatement.setString(9, dateTime);
            preparedStatement.setString(10, System.getProperty("user.name"));
            preparedStatement.setInt(11, id_unique);
            preparedStatement.setInt(12, version);
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table AccordOrganisation"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER ACCORDORGANISATION
  		// =================================================================================
    @Override
    public void copierAccordOrganisation(AccordOrganisation accordOrganisation) throws DaoException {
    	Connection connexion = null;
    	PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        int lastid_unique =0;
        int id =0;
        try {
			connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false); // Début de la transaction

			// ------ On récupère l'id du dernier accordOrganisation créé + 1 ------
			preparedStatement = connexion.prepareStatement("SELECT MAX(id_unique) AS id_unique FROM accordOrganisation");
			resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				 lastid_unique = resultat.getInt("id_unique") + 1 ;
			}else {
				throw new SQLException("Coping contratOrganisation failed, no ID obtained.");
			}

			// ------ On crée les données accordOrganisation ------ //
            String sql = "INSERT INTO accordOrganisation(id_unique, version, id, nom, typeContrat, statut, organisation, date_demarrage, date_fin, commentaire,  pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, lastid_unique);
            preparedStatement.setInt(2, 1);
            id = lastid_unique*10 + 1;
            preparedStatement.setInt(3, id);
            preparedStatement.setString(4, accordOrganisation.getNom());
            preparedStatement.setInt(5, accordOrganisation.getTypeContrat());
            preparedStatement.setInt(6, accordOrganisation.getStatut());
            preparedStatement.setInt(7, accordOrganisation.getOrganisation());
            preparedStatement.setString(8, accordOrganisation.getDate_demarrage());
            preparedStatement.setString(9, accordOrganisation.getDate_fin());
            preparedStatement.setString(10, accordOrganisation.getCommentaire());
            preparedStatement.setString(11, "AccordOrganisationDao");
            preparedStatement.setString(12, dateTime);
            preparedStatement.setString(13, System.getProperty("user.name"));
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table AccordOrganisation"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER ACCORDORGANISATION
  		// =================================================================================

    @Override
    public void supprimerAccordOrganisation(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        int id_unique =0;
        int version = 0;

     // Retrouver id et version
        id_unique = id / 10;        // Division entière pour obtenir l'id
        version = id % 10;  // Modulo pour obtenir la version

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM accordOrganisation WHERE ID_UNIQUE=? and version=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id_unique);
            preparedStatement.setInt(2, version);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table AccordOrganisation"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER ACCORDORGANISATION
 		// =================================================================================
 		@Override
 		public void renommerAccordOrganisation(AccordOrganisation accordOrganisation) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();

 				preparedStatement = connexion.prepareStatement(
 						"UPDATE accordOrganisation SET accordOrganisation.nom=?, accordOrganisation.pgmmodification=?, accordOrganisation.datemodification=?,"
 								+ " accordOrganisation.usermodification=? where accordOrganisation.id=?;");
 				preparedStatement.setString(1, accordOrganisation.getNom());
 				preparedStatement.setString(2, "RENOMMER ACCORDORGANISATION");
 				preparedStatement.setString(3, dateTime);
 				preparedStatement.setString(4, System.getProperty("user.name"));
 				preparedStatement.setInt(5, accordOrganisation.getId());
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
    public List<AccordOrganisation> listerAccordOrganisation() throws DaoException {
        List<AccordOrganisation> accordOrganisations = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT * FROM accordOrganisation;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                AccordOrganisation accordOrganisation = new AccordOrganisation();
                accordOrganisation.setId(resultat.getInt("id"));
                accordOrganisation.setNom(resultat.getString("nom"));
                accordOrganisation.setTypeContrat(resultat.getInt("typeContrat"));
                accordOrganisation.setStatut(resultat.getInt("statut"));
                accordOrganisation.setOrganisation(resultat.getInt("organisation"));
                accordOrganisation.setDate_demarrage(resultat.getString("date_demarrage"));
                accordOrganisation.setDate_fin(resultat.getString("date_fin"));
                accordOrganisation.setCommentaire(resultat.getString("commentaire"));
                accordOrganisations.add(accordOrganisation);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table AccordOrganisation"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return accordOrganisations;
    }

    	// =================================================================================
		// TROUVER ACCORDORGANISATION PAR ID
		// =================================================================================
    @Override
    public AccordOrganisation trouverAccordOrganisation(Integer id) throws DaoException {
        AccordOrganisation accordOrganisation = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        int id_unique =0;
        int version = 0;
     // Retrouver id et version
        id_unique = id / 10;        // Division entière pour obtenir l'id
        version = id % 10;  // Modulo pour obtenir la version
        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM accordOrganisation WHERE id_unique=? and version=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id_unique);
            preparedStatement.setInt(2, version);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                accordOrganisation = new AccordOrganisation();
                accordOrganisation.setId(id);
                accordOrganisation.setNom(resultat.getString("nom"));
                accordOrganisation.setTypeContrat(resultat.getInt("typeContrat"));
                accordOrganisation.setStatut(resultat.getInt("statut"));
                accordOrganisation.setOrganisation(resultat.getInt("organisation"));
                accordOrganisation.setDate_demarrage(resultat.getString("date_demarrage"));
                accordOrganisation.setDate_fin(resultat.getString("date_fin"));
                accordOrganisation.setCommentaire(resultat.getString("commentaire"));
                accordOrganisation.setUsermodification(resultat.getString("usermodification"));
                accordOrganisation.setDatemodification(resultat.getDate("datemodification"));
                accordOrganisation.setPgmmodification(resultat.getString("pgmmodification"));
                accordOrganisation.setUsercreation(resultat.getString("usercreation"));
                accordOrganisation.setDatecreation(resultat.getDate("datecreation"));
                accordOrganisation.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table AccordOrganisation"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return accordOrganisation;
    }

    	// =================================================================================
		// TROUVER ACCORDORGANISATION PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomAccordOrganisation(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM accordOrganisation WHERE nom=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table AccordOrganisation"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE ACCORDORGANISATION
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheAccordOrganisations(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String ww_where ="";
        String ww_statut="";
        if  (tag_statut.equals("Tout")) {
        	ww_statut="";
        } else {
        	ww_statut=tag_statut;
        }
        int id_statut = 0;
        if  (!ww_statut.equals("")) {
        	try {
            	connexion = daoFactory.getConnection();
                String sql = "SELECT * FROM statut WHERE nom=?;";
                preparedStatement = connexion.prepareStatement(sql);
                preparedStatement.setString(1, ww_statut);
                rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    id_statut= rs.getInt("id");
                }
            } catch (SQLException e) {
            } finally {
                }
        	if  (id_statut!=0) {
        		ww_where =" where statut.id="+id_statut;
        }
        }
        if  (!type_entite.equals("")) {
        	if (!ww_where.equals("")) {
        		ww_where = ww_where + " and entite.nom='"+type_entite+"'";
        	}
        	else {
        	ww_where = " where entite.nom='"+type_entite+"'";
        	}
        }
        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS accordOrganisation.id_unique, accordOrganisation.version, accordOrganisation.id, accordOrganisation.nom, typeContrat, statut, Organisation, date_demarrage, date_fin, commentaire, typeContrat.nom, statut.nom, organisation.raison_sociale FROM accordOrganisation "
            		+ " inner join typeContrat on accordOrganisation.typeContrat = typeContrat.id inner join statut on accordOrganisation.statut=statut.id inner join organisation on accordOrganisation.organisation=organisation.id inner join entite on typecontrat.entite=entite.id"
            		+ ww_where
            		+ " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Map<String, Object> accordOrganisationFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                accordOrganisationFields.put("id", rs.getInt("id"));
                }
                if (dictionnaire_nom_colonne.containsKey("id_unique")) {
                    accordOrganisationFields.put("id_unique", rs.getInt("id_unique"));
                    }
                if (dictionnaire_nom_colonne.containsKey("version")) {
                accordOrganisationFields.put("version", rs.getInt("version"));
                }
                if (dictionnaire_nom_colonne.containsKey("nom")) {
                accordOrganisationFields.put("nom", rs.getString("nom"));
                }
                if (dictionnaire_nom_colonne.containsKey("typeContrat")) {
                accordOrganisationFields.put("typeContrat", rs.getString("typeContrat.nom"));
                }
                if (dictionnaire_nom_colonne.containsKey("statut")) {
                accordOrganisationFields.put("statut", rs.getString("statut.nom"));
                }
                if (dictionnaire_nom_colonne.containsKey("organisation")) {
                accordOrganisationFields.put("organisation", rs.getString("organisation.raison_sociale"));
                }
                if (dictionnaire_nom_colonne.containsKey("date_demarrage")) {
                accordOrganisationFields.put("date_demarrage", rs.getString("date_demarrage"));
                }
                if (dictionnaire_nom_colonne.containsKey("date_fin")) {
                accordOrganisationFields.put("date_fin", rs.getString("date_fin"));
                }
                if (dictionnaire_nom_colonne.containsKey("commentaire")) {
                accordOrganisationFields.put("commentaire", rs.getString("commentaire"));
                }
                list.add(accordOrganisationFields);
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
    public List<Map<String, Object>>  rechercheLikeAccordOrganisations(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS id_unique, version, id, nom, typeContrat, statut, Organisation, date_demarrage, date_fin, commentaire FROM accordOrganisation WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> accordOrganisationFields = new LinkedHashMap<>();
            	 if (dictionnaire_nom_colonne.containsKey("id")) {
                     accordOrganisationFields.put("id", rs.getInt("id"));
                     }
            	 	if (dictionnaire_nom_colonne.containsKey("id_unique")) {
                     accordOrganisationFields.put("id_unique", rs.getInt("id_unique"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("version")) {
                     accordOrganisationFields.put("version", rs.getInt("version"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("nom")) {
                     accordOrganisationFields.put("nom", rs.getString("nom"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("typeContrat")) {
                     accordOrganisationFields.put("typeContrat", rs.getString("typeContrat"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("statut")) {
                     accordOrganisationFields.put("statut", rs.getInt("statut"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("organisation")) {
                     accordOrganisationFields.put("organisation", rs.getString("organisation"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("date_demarrage")) {
                     accordOrganisationFields.put("date_demarrage", rs.getString("date_demarrage"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("date_fin")) {
                     accordOrganisationFields.put("date_fin", rs.getString("date_fin"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("commentaire")) {
                     accordOrganisationFields.put("commentaire", rs.getString("commentaire"));
                     }
                list.add(accordOrganisationFields);
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
	// RECUPERATION N° ENREGISTREMENT ENCOURS ACCORDORGANISATION
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
	// FERMETURE DES RESSOURCES ACCORDORGANISATION
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