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

import com.ws.beans.Cra_detail;
import com.ws.beans.Statut;

public class Cra_detailDaoImpl implements Cra_detailDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  Cra_detailDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }


	  	// =================================================================================
		// AJOUTER CRA_DETAIL
		// =================================================================================
    @Override
    public void ajouterCra_detail(Cra_detail cra_detail) throws DaoException {
    	Connection connexion = null;
    	PreparedStatement preparedStatement = null;
        try {
        	connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false); // Début de la transaction
			// ------ On crée les données cra_detail ------ //
            String sql = "INSERT INTO cra_detail(id, nom_jour, numero_jour, temps_passe, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, cra_detail.getId());
            preparedStatement.setString(2, cra_detail.getNom_jour());
            preparedStatement.setInt(3, cra_detail.getNumero_jour());
            preparedStatement.setFloat(4, cra_detail.getTemps_passe());
            preparedStatement.setString(5, "Cra_detailDao");
            preparedStatement.setString(6, dateTime);
            preparedStatement.setString(7, System.getProperty("user.name"));
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table Cra_detail"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER CRA_DETAIL
		// =================================================================================
    @Override
    public void modifierCra_detail(Cra_detail cra_detail) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        int id = cra_detail.getId();

        // Afficher les résultats
         try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE cra_detail SET nom_jour=?, numero_jour=?, temps_passe=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, cra_detail.getNom_jour());
            preparedStatement.setInt(2, cra_detail.getNumero_jour());
            preparedStatement.setFloat(3, cra_detail.getTemps_passe());
            preparedStatement.setString(4, "Cra_detailDao");
            preparedStatement.setString(5, dateTime);
            preparedStatement.setString(6, System.getProperty("user.name"));
            preparedStatement.setInt(7, id);
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table Cra_detail"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER CRA_DETAIL
  		// =================================================================================
    @Override
    public void copierCra_detail(Cra_detail cra_detail) throws DaoException {
    	Connection connexion = null;
    	PreparedStatement preparedStatement = null;
        int statutEnCours = 0; // ID du statut "En-cours"
        try {
			connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false); // Début de la transaction
			// ------ On crée les données cra_detail ------ //
			String sql = "INSERT INTO cra_detail(id, nom_jour, numero_jour, temps_passe, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, cra_detail.getId());
            preparedStatement.setString(2, cra_detail.getNom_jour());
            preparedStatement.setInt(3, cra_detail.getNumero_jour());
            preparedStatement.setFloat(4, cra_detail.getTemps_passe());
            preparedStatement.setString(5, "Cra_detailDao");
            preparedStatement.setString(6, dateTime);
            preparedStatement.setString(7, System.getProperty("user.name"));
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table Cra_detail"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER CRA_DETAIL
  		// =================================================================================

    @Override
    public void supprimerCra_detail(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM cra_detail WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table Cra_detail"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

 			// =================================================================================
 	 		// TERMINER CRA_DETAIL
 	 		// =================================================================================
 	 		@Override
 	 		public void terminerCra_detail(Integer id_cra_detail) throws DaoException {
 	 			Connection connexion = null;
 	 			PreparedStatement preparedStatement = null;

 	 			try {
 	 				connexion = daoFactory.getConnection();

 	 				preparedStatement = connexion.prepareStatement(
 	 						"UPDATE cra_detail SET cra_detail.statut=(SELECT id FROM statut WHERE nom = 'Terminé'), cra_detail.pgmmodification=?, cra_detail.datemodification=?,"
 	 								+ " cra_detail.usermodification=? where cra_detail.id=?;");
 	 				preparedStatement.setString(1, "TERMINER CRA_DETAIL");
 	 				preparedStatement.setString(2, dateTime);
 	 				preparedStatement.setString(3, System.getProperty("user.name"));
 	 				preparedStatement.setInt(4, id_cra_detail);
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
 	 		// ANNULER CRA_DETAIL
 	 		// =================================================================================
 	 		@Override
 	 		public void annulerCra_detail(Integer id_cra_detail) throws DaoException {
 	 			Connection connexion = null;
 	 			PreparedStatement preparedStatement = null;

 	 			try {
 	 				connexion = daoFactory.getConnection();

 	 				preparedStatement = connexion.prepareStatement(
 	 						"UPDATE cra_detail SET cra_detail.statut=(SELECT id FROM statut WHERE nom = 'Annulé'), cra_detail.pgmmodification=?, cra_detail.datemodification=?,"
 	 								+ " cra_detail.usermodification=? where cra_detail.id=?;");
 	 				preparedStatement.setString(1, "ANNULER CRA_DETAIL");
 	 				preparedStatement.setString(2, dateTime);
 	 				preparedStatement.setString(3, System.getProperty("user.name"));
 	 				preparedStatement.setInt(4, id_cra_detail);
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
    public List<Cra_detail> listerCra_detail() throws DaoException {
        List<Cra_detail> cra_details = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT * FROM cra_detail;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Cra_detail cra_detail = new Cra_detail();
                cra_detail.setId(resultat.getInt("id"));
                cra_detail.setNom_jour(resultat.getString("nom_jour"));
                cra_detail.setNumero_jour(resultat.getInt("numero_jour"));
                cra_detail.setTemps_passe(resultat.getFloat("temps_passe"));
                cra_details.add(cra_detail);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Cra_detail"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return cra_details;
    }

    	// =================================================================================
		// TROUVER CRA_DETAIL PAR ID
		// =================================================================================
    @Override
    public Cra_detail trouverCra_detail(Integer id) throws DaoException {
        Cra_detail cra_detail = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM cra_detail WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                cra_detail = new Cra_detail();
                cra_detail.setId(id);
                cra_detail.setNom_jour(resultat.getString("nom_jour"));
                cra_detail.setNumero_jour(resultat.getInt("numero_jour"));
                cra_detail.setTemps_passe(resultat.getFloat("temps_passe"));
                cra_detail.setUsermodification(resultat.getString("usermodification"));
                cra_detail.setDatemodification(resultat.getDate("datemodification"));
                cra_detail.setPgmmodification(resultat.getString("pgmmodification"));
                cra_detail.setUsercreation(resultat.getString("usercreation"));
                cra_detail.setDatecreation(resultat.getDate("datecreation"));
                cra_detail.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table Cra_detail"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return cra_detail;
    }

    	// =================================================================================
		// TROUVER CRA_DETAIL PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomCra_detail(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM cra_detail WHERE nom=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table Cra_detail"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE CRA_DETAIL
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheCra_details(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
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
            String query = "SELECT SQL_CALC_FOUND_ROWS id, nom_jour, numero_jour, temps_passe FROM cra_detail "
            		+ ww_where
            		+ " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Map<String, Object> cra_detailFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                cra_detailFields.put("id", rs.getInt("id"));
                }
                if (dictionnaire_nom_colonne.containsKey("nom_jour")) {
                cra_detailFields.put("nom_jour", rs.getString("nom_jour"));
                }
                if (dictionnaire_nom_colonne.containsKey("numero_jour")) {
                cra_detailFields.put("numero_jour", rs.getInt("numero_jour"));
                }
                if (dictionnaire_nom_colonne.containsKey("temps_passe")) {
                cra_detailFields.put("temps_passe", rs.getFloat("temps_passe"));
                }
                list.add(cra_detailFields);
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
    public List<Map<String, Object>>  rechercheLikeCra_details(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
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
        	String query = "SELECT SQL_CALC_FOUND_ROWS id, nom_jour, numero_jour, temps_passe FROM cra_detail "
                    + ww_where
            		+ select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Map<String, Object> cra_detailFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                cra_detailFields.put("id", rs.getInt("id"));
                }
                if (dictionnaire_nom_colonne.containsKey("nom_jour")) {
                    cra_detailFields.put("nom_jour", rs.getString("nom_jour"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("numero_jour")) {
                    cra_detailFields.put("numero_jour", rs.getInt("numero_jour"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("temps_passe")) {
                    cra_detailFields.put("temps_passe", rs.getFloat("temps_passe"));
                    }
                list.add(cra_detailFields);
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
	// RECUPERATION N° ENREGISTREMENT ENCOURS CRA_DETAIL
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
	// FERMETURE DES RESSOURCES CRA_DETAIL
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
	public void renommerCra_detail(Cra_detail cra_detail) throws DaoException {
		// TODO Auto-generated method stub

	}
}