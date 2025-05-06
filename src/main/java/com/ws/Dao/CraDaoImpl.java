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

import com.ws.beans.Cra;
import com.ws.beans.Statut;

public class CraDaoImpl implements CraDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  CraDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }


	  	// =================================================================================
		// AJOUTER CRA
		// =================================================================================
    @Override
    public void ajouterCra(Cra cra) throws DaoException {
    	Connection connexion = null;
    	PreparedStatement preparedStatement = null;
        try {
        	connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false); // Début de la transaction
			// ------ On crée les données cra ------ //
            String sql = "INSERT INTO cra(mission, personnel, annee, mois, nbr_jours_ouvres, nbr_jours_activites, statut, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, cra.getMission());
            preparedStatement.setInt(2, cra.getPersonnel());
            preparedStatement.setInt(3, cra.getAnnee());
            preparedStatement.setString(4, cra.getMois());
            preparedStatement.setFloat(5, cra.getNbr_jours_ouvres());
            preparedStatement.setFloat(6, cra.getNbr_jours_activites());
            preparedStatement.setInt(7, cra.getStatut());
            preparedStatement.setString(8, "CraDao");
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table Cra"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER CRA
		// =================================================================================
    @Override
    public void modifierCra(Cra cra) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        int id = cra.getId();

        // Afficher les résultats
         try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE cra SET mission=?, personnel=?, annee=?, mois=?, nbr_jours_ouvres=?, nbr_jours_activites=?, statut=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, cra.getMission());
            preparedStatement.setInt(2, cra.getPersonnel());
            preparedStatement.setInt(3, cra.getAnnee());
            preparedStatement.setString(4, cra.getMois());
            preparedStatement.setFloat(5, cra.getNbr_jours_ouvres());
            preparedStatement.setFloat(6, cra.getNbr_jours_activites());
            preparedStatement.setInt(7, cra.getStatut());
            preparedStatement.setString(8, "CraDao");
            preparedStatement.setString(9, dateTime);
            preparedStatement.setString(10, System.getProperty("user.name"));
            preparedStatement.setInt(11, id);
            System.out.println(preparedStatement);
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table Cra"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER CRA
  		// =================================================================================
    @Override
    public void copierCra(Cra cra) throws DaoException {
    	Connection connexion = null;
    	PreparedStatement preparedStatement = null;
        int statutEnCours = 0; // ID du statut "En-cours"
        try {
			connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false); // Début de la transaction
			// ------ On crée les données cra ------ //
			String sql = "INSERT INTO cra(mission, personnel, annee, mois, nbr_jours_ouvres, nbr_jours_activites, statut, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, cra.getMission());
            preparedStatement.setInt(2, cra.getPersonnel());
            preparedStatement.setInt(3, cra.getAnnee());
            preparedStatement.setString(4, cra.getMois());
            preparedStatement.setFloat(5, cra.getNbr_jours_ouvres());
            preparedStatement.setFloat(6, cra.getNbr_jours_activites());
            preparedStatement.setInt(7, cra.getStatut());
            preparedStatement.setString(8, "CraDao");
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table Cra"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER CRA
  		// =================================================================================

    @Override
    public void supprimerCra(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM cra WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table Cra"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

 			// =================================================================================
 	 		// TERMINER CRA
 	 		// =================================================================================
 	 		@Override
 	 		public void terminerCra(Integer id_cra) throws DaoException {
 	 			Connection connexion = null;
 	 			PreparedStatement preparedStatement = null;

 	 			try {
 	 				connexion = daoFactory.getConnection();

 	 				preparedStatement = connexion.prepareStatement(
 	 						"UPDATE cra SET cra.statut=(SELECT id FROM statut WHERE nom = 'Terminé'), cra.pgmmodification=?, cra.datemodification=?,"
 	 								+ " cra.usermodification=? where cra.id=?;");
 	 				preparedStatement.setString(1, "TERMINER CRA");
 	 				preparedStatement.setString(2, dateTime);
 	 				preparedStatement.setString(3, System.getProperty("user.name"));
 	 				preparedStatement.setInt(4, id_cra);
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
 	 		// ANNULER CRA
 	 		// =================================================================================
 	 		@Override
 	 		public void annulerCra(Integer id_cra) throws DaoException {
 	 			Connection connexion = null;
 	 			PreparedStatement preparedStatement = null;

 	 			try {
 	 				connexion = daoFactory.getConnection();

 	 				preparedStatement = connexion.prepareStatement(
 	 						"UPDATE cra SET cra.statut=(SELECT id FROM statut WHERE nom = 'Annulé'), cra.pgmmodification=?, cra.datemodification=?,"
 	 								+ " cra.usermodification=? where cra.id=?;");
 	 				preparedStatement.setString(1, "ANNULER CRA");
 	 				preparedStatement.setString(2, dateTime);
 	 				preparedStatement.setString(3, System.getProperty("user.name"));
 	 				preparedStatement.setInt(4, id_cra);
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
    public List<Cra> listerCra() throws DaoException {
        List<Cra> cras = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT * FROM cra;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Cra cra = new Cra();
                cra.setId(resultat.getInt("id"));
                cra.setMission(resultat.getInt("mission"));
                cra.setPersonnel(resultat.getInt("personnel"));
                cra.setAnnee(resultat.getInt("annee"));
                cra.setMois(resultat.getString("mois"));
                cra.setNbr_jours_ouvres(resultat.getFloat("nbr_jours_ouvres"));
                cra.setNbr_jours_activites(resultat.getFloat("nbr_jours_activites"));
                cra.setStatut(resultat.getInt("statut"));
                cra.setNbr_jours_ouvres(resultat.getFloat("nbr_jours_ouvres"));
                cras.add(cra);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Cra"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return cras;
    }

    	// =================================================================================
		// TROUVER CRA PAR ID
		// =================================================================================
    @Override
    public Cra trouverCra(Integer id) throws DaoException {
        Cra cra = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM cra WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                cra = new Cra();
                cra.setId(id);
                cra.setMission(resultat.getInt("mission"));
                cra.setPersonnel(resultat.getInt("personnel"));
                cra.setAnnee(resultat.getInt("annee"));
                cra.setMois(resultat.getString("mois"));
                cra.setNbr_jours_ouvres(resultat.getFloat("nbr_jours_ouvres"));
                cra.setNbr_jours_activites(resultat.getFloat("nbr_jours_activites"));
                cra.setStatut(resultat.getInt("statut"));
                cra.setUsermodification(resultat.getString("usermodification"));
                cra.setDatemodification(resultat.getDate("datemodification"));
                cra.setPgmmodification(resultat.getString("pgmmodification"));
                cra.setUsercreation(resultat.getString("usercreation"));
                cra.setDatecreation(resultat.getDate("datecreation"));
                cra.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table Cra"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return cra;
    }

    	// =================================================================================
		// TROUVER CRA PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomCra(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM cra WHERE nom=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table Cra"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE CRA
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheCras(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
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
            String query = "SELECT SQL_CALC_FOUND_ROWS cra.id, mission, personnel, annee, mois, nbr_jours_ouvres, nbr_jours_activites, statut.nom, mission.nom, personnel.nom FROM cra "
            		+ " left join statut on cra.statut=statut.id left join mission on cra.mission=mission.id left join personnel on cra.personnel=personnel.id "
            		+ ww_where
            		+ " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Map<String, Object> craFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                craFields.put("id", rs.getInt("cra.id"));
                }
                if (dictionnaire_nom_colonne.containsKey("mission")) {
                craFields.put("mission", rs.getString("mission.nom"));
                System.out.println("mission"+rs.getString("mission.nom"));

                }
                if (dictionnaire_nom_colonne.containsKey("personnel")) {
                    craFields.put("personnel", rs.getString("personnel.nom"));
                    }
                if (dictionnaire_nom_colonne.containsKey("annee")) {
                    craFields.put("annee", rs.getInt("annee"));
                    }
                if (dictionnaire_nom_colonne.containsKey("mois")) {
                    craFields.put("mois", rs.getString("mois"));
                    }
                if (dictionnaire_nom_colonne.containsKey("nbr_jours_ouvres")) {
                    craFields.put("nbr_jours_ouvres", rs.getFloat("nbr_jours_ouvres"));
                    }
                if (dictionnaire_nom_colonne.containsKey("nbr_jours_activites")) {
                    craFields.put("nbr_jours_activites", rs.getFloat("nbr_jours_activites"));
                    }
                if (dictionnaire_nom_colonne.containsKey("statut")) {
                craFields.put("statut", rs.getString("statut.nom"));
                }
                list.add(craFields);
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
    public List<Map<String, Object>>  rechercheLikeCras(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
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
            String query = "SELECT SQL_CALC_FOUND_ROWS mission, personnel, annee, mois, nbr_jours_ouvres, nbr_jours_activites, statut frOM cra "
            		+ ww_where
            		+ select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Map<String, Object> craFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                craFields.put("id", rs.getInt("id"));
                }
                if (dictionnaire_nom_colonne.containsKey("mission")) {
                craFields.put("mission", rs.getInt("mission"));
                }
                if (dictionnaire_nom_colonne.containsKey("personnel")) {
                craFields.put("personnel", rs.getInt("personnel"));
                }
                if (dictionnaire_nom_colonne.containsKey("annee")) {
                    craFields.put("annee", rs.getInt("annee"));
                    }
                if (dictionnaire_nom_colonne.containsKey("mois")) {
                craFields.put("mois", rs.getString("mois"));
                }
                if (dictionnaire_nom_colonne.containsKey("nbr_jours_ouvres")) {
                    craFields.put("nbr_jours_ouvres", rs.getFloat("nbr_jours_ouvres"));
                    }
                if (dictionnaire_nom_colonne.containsKey("nbr_jours_activites")) {
                    craFields.put("nbr_jours_activites", rs.getFloat("nbr_jours_activites"));
                    }
                list.add(craFields);
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
	// RECUPERATION N° ENREGISTREMENT ENCOURS CRA
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
	// FERMETURE DES RESSOURCES CRA
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


	@Override
	public void renommerCra(Cra cra) throws DaoException {
		// TODO Auto-generated method stub

	}
}