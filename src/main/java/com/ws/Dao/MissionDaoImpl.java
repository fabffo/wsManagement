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

import com.ws.beans.Mission;
import com.ws.beans.Statut;

public class MissionDaoImpl implements MissionDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  MissionDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }


	  	// =================================================================================
		// AJOUTER MISSION
		// =================================================================================
    @Override
    public void ajouterMission(Mission mission) throws DaoException {
    	Connection connexion = null;
    	PreparedStatement preparedStatement = null;
        try {
        	connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false); // Début de la transaction
			// ------ On crée les données mission ------ //
            String sql = "INSERT INTO mission(contrat, nom, statut, prix_ht, tva, typePrix, date_demarrage, date_fin, commentaire, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, mission.getAccordOrganisation());
            preparedStatement.setString(2, mission.getNom());
            preparedStatement.setInt(3, mission.getStatut());
            preparedStatement.setFloat(4, mission.getPrix_ht());
            preparedStatement.setInt(5, mission.getTva());
            preparedStatement.setInt(6, mission.getTypePrix());
            preparedStatement.setString(7, mission.getDate_demarrage());
            preparedStatement.setString(8, mission.getDate_fin());
            preparedStatement.setString(9, mission.getCommentaire());
            preparedStatement.setString(10, "MissionDao");
            preparedStatement.setString(11, dateTime);
            preparedStatement.setString(12, System.getProperty("user.name"));
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table Mission"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER MISSION
		// =================================================================================
    @Override
    public void modifierMission(Mission mission) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        int id = mission.getId();

        // Afficher les résultats
         try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE mission SET contrat=?, nom=?, statut=?, prix_ht=?, tva=?, typePrix=?, date_demarrage=?, date_fin=?, commentaire=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, mission.getAccordOrganisation());
            preparedStatement.setString(2, mission.getNom());
            preparedStatement.setInt(3, mission.getStatut());
            preparedStatement.setFloat(4, mission.getPrix_ht());
            preparedStatement.setInt(5, mission.getTva());
            preparedStatement.setInt(6, mission.getTypePrix());
            preparedStatement.setString(7, mission.getDate_demarrage());
            preparedStatement.setString(8, mission.getDate_fin());
            preparedStatement.setString(9, mission.getCommentaire());
            preparedStatement.setString(10, "MissionDao");
            preparedStatement.setString(11, dateTime);
            preparedStatement.setString(12, System.getProperty("user.name"));
            preparedStatement.setInt(13, id);
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table Mission"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER MISSION
  		// =================================================================================
    @Override
    public void copierMission(Mission mission) throws DaoException {
    	Connection connexion = null;
    	PreparedStatement preparedStatement = null;
        int statutEnCours = 0; // ID du statut "En-cours"
        try {
			connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false); // Début de la transaction
			// ------ On crée les données mission ------ //
            String sql = "INSERT INTO mission(contrat, nom, statut, prix_ht, tva, typePrix, date_demarrage, date_fin, commentaire, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, mission.getAccordOrganisation());
            preparedStatement.setString(2, mission.getNom());
            preparedStatement.setInt(3, mission.getStatut());
            preparedStatement.setFloat(4, mission.getPrix_ht());
            preparedStatement.setInt(5, mission.getTva());
            preparedStatement.setInt(6, mission.getTypePrix());
            preparedStatement.setString(7, mission.getDate_demarrage());
            preparedStatement.setString(8, mission.getDate_fin());
            preparedStatement.setString(9, mission.getCommentaire());
            preparedStatement.setString(10, "MissionDao");
            preparedStatement.setString(11, dateTime);
            preparedStatement.setString(12, System.getProperty("user.name"));
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table Mission"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER MISSION
  		// =================================================================================

    @Override
    public void supprimerMission(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM mission WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table Mission"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER MISSION
 		// =================================================================================
 		@Override
 		public void renommerMission(Mission mission) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();

 				preparedStatement = connexion.prepareStatement(
 						"UPDATE mission SET mission.nom=?, mission.pgmmodification=?, mission.datemodification=?,"
 								+ " mission.usermodification=? where mission.id=?;");
 				preparedStatement.setString(1, mission.getNom());
 				preparedStatement.setString(2, "RENOMMER MISSION");
 				preparedStatement.setString(3, dateTime);
 				preparedStatement.setString(4, System.getProperty("user.name"));
 				preparedStatement.setInt(5, mission.getId());
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
 	 		// TERMINER MISSION
 	 		// =================================================================================
 	 		@Override
 	 		public void terminerMission(Integer id_mission) throws DaoException {
 	 			Connection connexion = null;
 	 			PreparedStatement preparedStatement = null;

 	 			try {
 	 				connexion = daoFactory.getConnection();

 	 				preparedStatement = connexion.prepareStatement(
 	 						"UPDATE mission SET mission.statut=(SELECT id FROM statut WHERE nom = 'Terminé'), mission.pgmmodification=?, mission.datemodification=?,"
 	 								+ " mission.usermodification=? where mission.id=?;");
 	 				preparedStatement.setString(1, "TERMINER MISSION");
 	 				preparedStatement.setString(2, dateTime);
 	 				preparedStatement.setString(3, System.getProperty("user.name"));
 	 				preparedStatement.setInt(4, id_mission);
 	 				preparedStatement.executeUpdate();
 	 				connexion.commit();
 	 			} catch (SQLException e) {
 	 				try {
 	 					if (connexion != null) {
 	 						connexion.rollback();
 	 					}
 	 				} catch (SQLException e2) {
 	 				}
 	 				throw new DaoException("Impossible de supprimer enregistrement avec la table contratt" + e);
 	 			} finally {
 	 				try {
 	 					if (connexion != null) {
 	 						connexion.close();
 	 					}
 	 				} catch (SQLException e) {
 	 					throw new DaoException("Impossible de supprimer enregistrement avec la table contratt" + e);
 	 				}
 	 			}
 	 		}

 	 	// =================================================================================
 	 		// ANNULER MISSION
 	 		// =================================================================================
 	 		@Override
 	 		public void annulerMission(Integer id_mission) throws DaoException {
 	 			Connection connexion = null;
 	 			PreparedStatement preparedStatement = null;

 	 			try {
 	 				connexion = daoFactory.getConnection();

 	 				preparedStatement = connexion.prepareStatement(
 	 						"UPDATE mission SET mission.statut=(SELECT id FROM statut WHERE nom = 'Annulé'), mission.pgmmodification=?, mission.datemodification=?,"
 	 								+ " mission.usermodification=? where mission.id=?;");
 	 				preparedStatement.setString(1, "ANNULER MISSION");
 	 				preparedStatement.setString(2, dateTime);
 	 				preparedStatement.setString(3, System.getProperty("user.name"));
 	 				preparedStatement.setInt(4, id_mission);
 	 				preparedStatement.executeUpdate();
 	 				connexion.commit();
 	 			} catch (SQLException e) {
 	 				try {
 	 					if (connexion != null) {
 	 						connexion.rollback();
 	 					}
 	 				} catch (SQLException e2) {
 	 				}
 	 				throw new DaoException("Impossible de supprimer enregistrement avec la table contratt" + e);
 	 			} finally {
 	 				try {
 	 					if (connexion != null) {
 	 						connexion.close();
 	 					}
 	 				} catch (SQLException e) {
 	 					throw new DaoException("Impossible de supprimer enregistrement avec la table contratt" + e);
 	 				}
 	 			}
 	 		}

 	// =================================================================================
  	// LISTER DES STATUTS
  	// =================================================================================
    @Override
    public List<Mission> listerMission() throws DaoException {
        List<Mission> missions = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT * FROM mission;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Mission mission = new Mission();
                mission.setId(resultat.getInt("id"));
                mission.setAccordOrganisation(resultat.getInt("contrat"));
                mission.setNom(resultat.getString("nom"));
                mission.setStatut(resultat.getInt("statut"));
                mission.setPrix_ht(resultat.getFloat("prix_ht"));
                mission.setTva(resultat.getInt("tva"));
                mission.setTypePrix(resultat.getInt("typePrix"));
                mission.setDate_demarrage(resultat.getString("date_demarrage"));
                mission.setDate_fin(resultat.getString("date_fin"));
                mission.setCommentaire(resultat.getString("commentaire"));
                missions.add(mission);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Mission"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return missions;
    }

    	// =================================================================================
		// TROUVER MISSION PAR ID
		// =================================================================================
    @Override
    public Mission trouverMission(Integer id) throws DaoException {
        Mission mission = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM mission WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                mission = new Mission();
                mission.setId(id);
                mission.setId(resultat.getInt("contrat"));
                mission.setNom(resultat.getString("nom"));
                mission.setStatut(resultat.getInt("statut"));
                mission.setPrix_ht(resultat.getFloat("prix_ht"));
                mission.setTva(resultat.getInt("tva"));
                mission.setTypePrix(resultat.getInt("typePrix"));
                mission.setDate_demarrage(resultat.getString("date_demarrage"));
                mission.setDate_fin(resultat.getString("date_fin"));
                mission.setCommentaire(resultat.getString("commentaire"));
                mission.setUsermodification(resultat.getString("usermodification"));
                mission.setDatemodification(resultat.getDate("datemodification"));
                mission.setPgmmodification(resultat.getString("pgmmodification"));
                mission.setUsercreation(resultat.getString("usercreation"));
                mission.setDatecreation(resultat.getDate("datecreation"));
                mission.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table Mission"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return mission;
    }

    	// =================================================================================
		// TROUVER MISSION PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomMission(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM mission WHERE nom=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table Mission"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE MISSION
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheMissions(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
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
            String query = "SELECT SQL_CALC_FOUND_ROWS mission.id, contrat, mission.nom, mission.statut, prix_ht, mission.tva, mission.typePrix, date_demarrage, date_fin, commentaire, statut.nom, tva.nom, typePrix.nom FROM mission "
            		+ " inner join statut on mission.statut=statut.id "
            		+ " inner join tva on mission.tva=tva.id inner join typePrix on mission.typePrix=typePrix.id "
            		+ ww_where
            		+ " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Map<String, Object> missionFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                missionFields.put("id", rs.getInt("id"));
                }
                if (dictionnaire_nom_colonne.containsKey("nom")) {
                missionFields.put("nom", rs.getString("nom"));
                }
                if (dictionnaire_nom_colonne.containsKey("contrat")) {
                missionFields.put("contrat", rs.getString("contrat"));
                }
                if (dictionnaire_nom_colonne.containsKey("statut")) {
                missionFields.put("statut", rs.getString("statut.nom"));
                }
                if (dictionnaire_nom_colonne.containsKey("prix_ht")) {
                missionFields.put("prix_ht", rs.getString("prix_ht"));
                }
                if (dictionnaire_nom_colonne.containsKey("tva")) {
                    missionFields.put("tva", rs.getString("tva.nom"));
                    }
                if (dictionnaire_nom_colonne.containsKey("typePrix")) {
                    missionFields.put("typePrix", rs.getString("typePrix.nom"));
                    }
                if (dictionnaire_nom_colonne.containsKey("date_demarrage")) {
                missionFields.put("date_demarrage", rs.getString("date_demarrage"));
                }
                if (dictionnaire_nom_colonne.containsKey("date_fin")) {
                missionFields.put("date_fin", rs.getString("date_fin"));
                }
                if (dictionnaire_nom_colonne.containsKey("commentaire")) {
                missionFields.put("commentaire", rs.getString("commentaire"));
                }
                if (dictionnaire_nom_colonne.containsKey("document")) {
                    missionFields.put("document", rs.getString("document"));
                    System.out.println("yes put"+rs.getString("document"));
                    }
                list.add(missionFields);
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
    public List<Map<String, Object>>  rechercheLikeMissions(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
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
            String query = "SELECT SQL_CALC_FOUND_ROWS mission.id, contrat, mission.nom, mission.statut, prix_ht, mission.tva, mission.typePrix, date_demarrage, date_fin, commentaire, statut.nom, tva.nom, typePrix.nom FROM mission "
            		+ " inner join statut on mission.statut=statut.id inner join organisation on mission.organisation=organisation.id "
            		+ " inner join tva on mission.tva=tva.id inner join typePrix on mission.typePrix=typePrix.id "
            		+ ww_where
            		+ select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Map<String, Object> missionFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                missionFields.put("id", rs.getInt("id"));
                }
                if (dictionnaire_nom_colonne.containsKey("nom")) {
                missionFields.put("nom", rs.getString("nom"));
                }
                if (dictionnaire_nom_colonne.containsKey("contrat")) {
                missionFields.put("contrat", rs.getString("contrat"));
                }
                if (dictionnaire_nom_colonne.containsKey("statut")) {
                missionFields.put("statut", rs.getString("statut.nom"));
                }
                if (dictionnaire_nom_colonne.containsKey("prix_ht")) {
                missionFields.put("prix_ht", rs.getString("prix_ht"));
                }
                if (dictionnaire_nom_colonne.containsKey("tva")) {
                    missionFields.put("tva", rs.getString("tva.nom"));
                    }
                if (dictionnaire_nom_colonne.containsKey("typePrix")) {
                    missionFields.put("typePrix", rs.getString("typePrix.nom"));
                    }
                if (dictionnaire_nom_colonne.containsKey("date_demarrage")) {
                missionFields.put("date_demarrage", rs.getString("date_demarrage"));
                }
                if (dictionnaire_nom_colonne.containsKey("date_fin")) {
                missionFields.put("date_fin", rs.getString("date_fin"));
                }
                if (dictionnaire_nom_colonne.containsKey("commentaire")) {
                missionFields.put("commentaire", rs.getString("commentaire"));
                }
                if (dictionnaire_nom_colonne.containsKey("document")) {
                    missionFields.put("document", rs.getString("document"));
                    System.out.println("yes put"+rs.getString("document"));
                    }
                list.add(missionFields);
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
	// RECUPERATION N° ENREGISTREMENT ENCOURS MISSION
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
	// FERMETURE DES RESSOURCES MISSION
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