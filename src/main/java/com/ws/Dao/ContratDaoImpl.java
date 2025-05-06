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

import com.ws.beans.Contrat;
import com.ws.beans.Statut;

public class ContratDaoImpl implements ContratDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  ContratDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }


	  	// =================================================================================
		// AJOUTER CONTRAT
		// =================================================================================
    @Override
    public void ajouterContrat(Contrat contrat) throws DaoException {
    	Connection connexion = null;
    	PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        int lastid_unique =0;
        int id =0;
        int statutEnCours = 0; // ID du statut "En-cours"
        try {
			connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false); // Début de la transaction
			 // ------ Récupérer l'ID du statut "En-cours" ------
	        String sqlStatut = "SELECT id FROM statut WHERE nom = 'En-cours' LIMIT 1";
	        preparedStatement = connexion.prepareStatement(sqlStatut);
	        resultat = preparedStatement.executeQuery();
	        if (resultat.next()) {
	            statutEnCours = resultat.getInt("id");
	        } else {
	            throw new SQLException("Impossible de récupérer l'ID du statut 'En-cours'.");
	        }
	        resultat.close();
	        preparedStatement.close();

			// ------ On récupère l'id du dernier contrat créé + 1 ------
			preparedStatement = connexion.prepareStatement("SELECT MAX(id_unique) AS id_unique FROM contrat");
			resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				 lastid_unique = resultat.getInt("id_unique") + 1 ;
			}else {
				throw new SQLException("Coping contratOrganisation failed, no ID obtained.");
			}

			// ------ On crée les données contrat ------ //
            String sql = "INSERT INTO contrat(id_unique, version, id, nom, typeContrat, statut, organisation, personnel, date_demarrage, date_fin, commentaire,  document, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, lastid_unique);
            preparedStatement.setInt(2, 1);
            id = lastid_unique*10 + 1;
            preparedStatement.setInt(3, id);
            preparedStatement.setString(4, contrat.getNom());
            preparedStatement.setInt(5, contrat.getTypeContrat());
            preparedStatement.setInt(6, statutEnCours);// Utilisation de l'ID du statut "En-cours"
            preparedStatement.setInt(7, contrat.getOrganisation());
            preparedStatement.setInt(8, contrat.getPersonnel());
            preparedStatement.setString(9, contrat.getDate_demarrage());
            preparedStatement.setString(10, contrat.getDate_fin());
            preparedStatement.setString(11, contrat.getCommentaire());
            preparedStatement.setString(12, contrat.getDocument());
            preparedStatement.setString(13, "ContratDao");
            preparedStatement.setString(14, dateTime);
            preparedStatement.setString(15, System.getProperty("user.name"));
            System.out.println(preparedStatement+"statut"+statutEnCours);
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table Contrat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER CONTRAT
		// =================================================================================
    @Override
    public void modifierContrat(Contrat contrat) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        int id_unique =0;
        int version = 0;
        int id = contrat.getId();
     // Retrouver id et version
        id_unique = id / 10;        // Division entière pour obtenir l'id
        version = id % 10;  // Modulo pour obtenir la version

        // Afficher les résultats
         try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE contrat SET nom=?, typeContrat=?, statut=?, organisation=?, personnel=?, date_demarrage=?, date_fin=?, commentaire=?, document=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id_unique=? and version=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, contrat.getNom());
            preparedStatement.setInt(2, contrat.getTypeContrat());
            preparedStatement.setInt(3, contrat.getStatut());
            preparedStatement.setInt(4, contrat.getOrganisation());
            preparedStatement.setInt(5, contrat.getPersonnel());
            preparedStatement.setString(6, contrat.getDate_demarrage());
            preparedStatement.setString(7, contrat.getDate_fin());
            preparedStatement.setString(8, contrat.getCommentaire());
            preparedStatement.setString(9, contrat.getDocument());
            preparedStatement.setString(10, "ContratDao");
            preparedStatement.setString(11, dateTime);
            preparedStatement.setString(12, System.getProperty("user.name"));
            preparedStatement.setInt(13, id_unique);
            preparedStatement.setInt(14, version);
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table Contrat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER CONTRAT
  		// =================================================================================
    @Override
    public void copierContrat(Contrat contrat) throws DaoException {
    	Connection connexion = null;
    	PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        int lastid_unique =0;
        int id =0;
        int statutEnCours = 0; // ID du statut "En-cours"
        try {
			connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false); // Début de la transaction
			 // ------ Récupérer l'ID du statut "En-cours" ------
	        String sqlStatut = "SELECT id FROM statut WHERE nom = 'En-cours' LIMIT 1";
	        preparedStatement = connexion.prepareStatement(sqlStatut);
	        resultat = preparedStatement.executeQuery();
	        if (resultat.next()) {
	            statutEnCours = resultat.getInt("id");
	        } else {
	            throw new SQLException("Impossible de récupérer l'ID du statut 'En-cours'.");
	        }
	        resultat.close();
	        preparedStatement.close();

			// ------ On récupère l'id du dernier contrat créé + 1 ------
			preparedStatement = connexion.prepareStatement("SELECT MAX(id_unique) AS id_unique FROM contrat");
			resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				 lastid_unique = resultat.getInt("id_unique") + 1 ;
			}else {
				throw new SQLException("Coping contratOrganisation failed, no ID obtained.");
			}

			// ------ On crée les données contrat ------ //
            String sql = "INSERT INTO contrat(id_unique, version, id, nom, typeContrat, statut, organisation, personnel, date_demarrage, date_fin, commentaire,  document, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, lastid_unique);
            preparedStatement.setInt(2, 1);
            id = lastid_unique*10 + 1;
            preparedStatement.setInt(3, id);
            preparedStatement.setString(4, contrat.getNom());
            preparedStatement.setInt(5, contrat.getTypeContrat());
            preparedStatement.setInt(6, statutEnCours);// Utilisation de l'ID du statut "En-cours"
            preparedStatement.setInt(7, contrat.getOrganisation());
            preparedStatement.setInt(8, contrat.getPersonnel());
            preparedStatement.setString(9, contrat.getDate_demarrage());
            preparedStatement.setString(10, contrat.getDate_fin());
            preparedStatement.setString(11, contrat.getCommentaire());
            preparedStatement.setString(12, contrat.getDocument());
            preparedStatement.setString(13, "ContratDao");
            preparedStatement.setString(14, dateTime);
            preparedStatement.setString(15, System.getProperty("user.name"));
            System.out.println(preparedStatement+"statut"+statutEnCours);
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table Contrat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER CONTRAT
  		// =================================================================================

    @Override
    public void supprimerContrat(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        int id_unique =0;
        int version = 0;

     // Retrouver id et version
        id_unique = id / 10;        // Division entière pour obtenir l'id
        version = id % 10;  // Modulo pour obtenir la version

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM contrat WHERE ID_UNIQUE=? and version=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id_unique);
            preparedStatement.setInt(2, version);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table Contrat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER CONTRAT
 		// =================================================================================
 		@Override
 		public void renommerContrat(Contrat contrat) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();

 				preparedStatement = connexion.prepareStatement(
 						"UPDATE contrat SET contrat.nom=?, contrat.pgmmodification=?, contrat.datemodification=?,"
 								+ " contrat.usermodification=? where contrat.id=?;");
 				preparedStatement.setString(1, contrat.getNom());
 				preparedStatement.setString(2, "RENOMMER CONTRAT");
 				preparedStatement.setString(3, dateTime);
 				preparedStatement.setString(4, System.getProperty("user.name"));
 				preparedStatement.setInt(5, contrat.getId());
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
 	 		// TERMINER CONTRAT
 	 		// =================================================================================
 	 		@Override
 	 		public void terminerContrat(Integer id_contrat) throws DaoException {
 	 			Connection connexion = null;
 	 			PreparedStatement preparedStatement = null;

 	 			try {
 	 				connexion = daoFactory.getConnection();

 	 				preparedStatement = connexion.prepareStatement(
 	 						"UPDATE contrat SET contrat.statut=(SELECT id FROM statut WHERE nom = 'Terminé'), contrat.pgmmodification=?, contrat.datemodification=?,"
 	 								+ " contrat.usermodification=? where contrat.id=?;");
 	 				preparedStatement.setString(1, "TERMINER CONTRAT");
 	 				preparedStatement.setString(2, dateTime);
 	 				preparedStatement.setString(3, System.getProperty("user.name"));
 	 				preparedStatement.setInt(4, id_contrat);
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
 	 		// ANNULER CONTRAT
 	 		// =================================================================================
 	 		@Override
 	 		public void annulerContrat(Integer id_contrat) throws DaoException {
 	 			Connection connexion = null;
 	 			PreparedStatement preparedStatement = null;

 	 			try {
 	 				connexion = daoFactory.getConnection();

 	 				preparedStatement = connexion.prepareStatement(
 	 						"UPDATE contrat SET contrat.statut=(SELECT id FROM statut WHERE nom = 'Annulé'), contrat.pgmmodification=?, contrat.datemodification=?,"
 	 								+ " contrat.usermodification=? where contrat.id=?;");
 	 				preparedStatement.setString(1, "ANNULER CONTRAT");
 	 				preparedStatement.setString(2, dateTime);
 	 				preparedStatement.setString(3, System.getProperty("user.name"));
 	 				preparedStatement.setInt(4, id_contrat);
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
 	 		// FAIRE AVENANT CONTRAT
 	 		// =================================================================================
 	 		@Override
 	 		public void faire_avenantContrat(Contrat contrat) throws DaoException {
 	 			copierContrat(contrat);
 	 			terminerContrat(contrat.getId());
 	 		}

 	// =================================================================================
  	// LISTER DES ORGANISATIONS
  	// =================================================================================
    @Override
    public List<Contrat> listerContrat() throws DaoException {
        List<Contrat> contrats = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT * FROM contrat;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Contrat contrat = new Contrat();
                contrat.setId(resultat.getInt("id"));
                contrat.setNom(resultat.getString("nom"));
                contrat.setTypeContrat(resultat.getInt("typeContrat"));
                contrat.setStatut(resultat.getInt("statut"));
                contrat.setOrganisation(resultat.getInt("personnel"));
                contrat.setOrganisation(resultat.getInt("organisation"));
                contrat.setDate_demarrage(resultat.getString("date_demarrage"));
                contrat.setDate_fin(resultat.getString("date_fin"));
                contrat.setCommentaire(resultat.getString("commentaire"));
                contrat.setDocument(resultat.getString("document"));
                contrats.add(contrat);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Contrat"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return contrats;
    }

    	// =================================================================================
		// TROUVER CONTRAT PAR ID
		// =================================================================================
    @Override
    public Contrat trouverContrat(Integer id) throws DaoException {
        Contrat contrat = null;
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
            String sql = "SELECT * FROM contrat WHERE id_unique=? and version=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id_unique);
            preparedStatement.setInt(2, version);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                contrat = new Contrat();
                contrat.setId(id);
                contrat.setNom(resultat.getString("nom"));
                contrat.setTypeContrat(resultat.getInt("typeContrat"));
                contrat.setStatut(resultat.getInt("statut"));
                contrat.setOrganisation(resultat.getInt("organisation"));
                contrat.setDate_demarrage(resultat.getString("date_demarrage"));
                contrat.setDate_fin(resultat.getString("date_fin"));
                contrat.setCommentaire(resultat.getString("commentaire"));
                contrat.setDocument(resultat.getString("document"));
                contrat.setUsermodification(resultat.getString("usermodification"));
                contrat.setDatemodification(resultat.getDate("datemodification"));
                contrat.setPgmmodification(resultat.getString("pgmmodification"));
                contrat.setUsercreation(resultat.getString("usercreation"));
                contrat.setDatecreation(resultat.getDate("datecreation"));
                contrat.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table Contrat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return contrat;
    }

    	// =================================================================================
		// TROUVER CONTRAT PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomContrat(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM contrat WHERE nom=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table Contrat"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE CONTRAT
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheContrats(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
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
            String query = "SELECT SQL_CALC_FOUND_ROWS contrat.id_unique, contrat.version, contrat.id, contrat.nom, document, typeContrat, statut, Organisation, date_demarrage, date_fin, commentaire, typeContrat.nom, statut.nom, organisation.raison_sociale, personnel.nom, personnel.prenom FROM contrat "
            		+ " left join typeContrat on contrat.typeContrat = typeContrat.id left join statut on contrat.statut=statut.id left join organisation on contrat.organisation=organisation.id left join entite on typecontrat.entite=entite.id left join personnel on contrat.personnel=personnel.id "
            		+ ww_where
            		+ " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Map<String, Object> contratFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                contratFields.put("id", rs.getInt("id"));
                }
                if (dictionnaire_nom_colonne.containsKey("id_unique")) {
                    contratFields.put("id_unique", rs.getInt("id_unique"));
                    }
                if (dictionnaire_nom_colonne.containsKey("version")) {
                contratFields.put("version", rs.getInt("version"));
                }
                if (dictionnaire_nom_colonne.containsKey("nom")) {
                contratFields.put("nom", rs.getString("nom"));
                }
                if (dictionnaire_nom_colonne.containsKey("typeContrat")) {
                contratFields.put("typeContrat", rs.getString("typeContrat.nom"));
                }
                if (dictionnaire_nom_colonne.containsKey("statut")) {
                contratFields.put("statut", rs.getString("statut.nom"));
                }
                if (dictionnaire_nom_colonne.containsKey("organisation")) {
                contratFields.put("organisation", rs.getString("organisation.raison_sociale"));
                }
                if (dictionnaire_nom_colonne.containsKey("personnel")) {
                    contratFields.put("personnel", rs.getString("personnel.prenom")+" "+rs.getString("personnel.nom"));
                    }
                if (dictionnaire_nom_colonne.containsKey("date_demarrage")) {
                contratFields.put("date_demarrage", rs.getString("date_demarrage"));
                }
                if (dictionnaire_nom_colonne.containsKey("date_fin")) {
                contratFields.put("date_fin", rs.getString("date_fin"));
                }
                if (dictionnaire_nom_colonne.containsKey("commentaire")) {
                contratFields.put("commentaire", rs.getString("commentaire"));
                }
                if (dictionnaire_nom_colonne.containsKey("document")) {
                    contratFields.put("document", rs.getString("document"));
                    System.out.println("yes put"+rs.getString("document"));
                    }
                list.add(contratFields);
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
    public List<Map<String, Object>>  rechercheLikeContrats(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
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
            String query = "SELECT SQL_CALC_FOUND_ROWS contrat.id_unique, contrat.version, contrat.id, contrat.nom, document, typeContrat, statut, Organisation, date_demarrage, date_fin, commentaire, typeContrat.nom, statut.nom, organisation.raison_sociale, personnel.nom, personnel.prenom FROM contrat "
            		+ " left join typeContrat on contrat.typeContrat = typeContrat.id left join statut on contrat.statut=statut.id left join organisation on contrat.organisation=organisation.id left join entite on typecontrat.entite=entite.id left join personnel on contrat.personnel=personnel.id "
            		+ ww_where + " and "
            		+ select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> contratFields = new LinkedHashMap<>();
            	 if (dictionnaire_nom_colonne.containsKey("id")) {
                     contratFields.put("id", rs.getInt("id"));
                     }
            	 	if (dictionnaire_nom_colonne.containsKey("id_unique")) {
                     contratFields.put("id_unique", rs.getInt("id_unique"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("version")) {
                     contratFields.put("version", rs.getInt("version"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("nom")) {
                     contratFields.put("nom", rs.getString("nom"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("typeContrat")) {
                     contratFields.put("typeContrat", rs.getString("typeContrat"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("statut")) {
                     contratFields.put("statut", rs.getInt("statut"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("organisation")) {
                     contratFields.put("organisation", rs.getString("organisation"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("personnel")) {
                         contratFields.put("personnel", rs.getString("personnel.prenom")+" "+rs.getString("personnel.nom"));
                         }
                     if (dictionnaire_nom_colonne.containsKey("date_demarrage")) {
                     contratFields.put("date_demarrage", rs.getString("date_demarrage"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("date_fin")) {
                     contratFields.put("date_fin", rs.getString("date_fin"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("commentaire")) {
                     contratFields.put("commentaire", rs.getString("commentaire"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("document")) {
                         contratFields.put("document", rs.getString("document"));
                         }
                list.add(contratFields);
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
	// RECUPERATION N° ENREGISTREMENT ENCOURS CONTRAT
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
	// FERMETURE DES RESSOURCES CONTRAT
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