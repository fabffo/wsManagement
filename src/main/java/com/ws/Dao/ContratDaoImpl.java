/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO IMPLEMENTATION CONTRATCLIENT                        ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.io.File;
import java.io.IOException;
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


public class ContratDaoImpl implements ContratDao {
	private DaoFactory daoFactory;

	// date du jour
	String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
	private int noOfRecords;
	Map<String, Integer> type_entiteDictionary = new HashMap<>();
	Map<String, Integer> societeDictionary = new HashMap<>();

	// Récupération paramètres

	ContratDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}



	// ================================================================================
	// ====CRUD CREER
	// ================================================================================
	@Override
	public void ajouterContrat(Contrat contrat, String chemin_absolu_document_defaut,
			String chemin_absolu_document_reel, String chemin_relatif_document_reel, String type_entite) throws DaoException {
		int lastid =0;
		// ---------------------- BLOC CONNECTION ----------------------
		Connection connection = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement= null;

		try {
			connection = daoFactory.getConnection();
			connection.setAutoCommit(false); // Début de la transaction

			// ------ On récupère l'id du dernier contrat créé + 1 ------
			preparedStatement = connection.prepareStatement("SELECT MAX(id) AS id FROM contrat");
			resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				 lastid = resultat.getInt("id") + 1 ;
			}else {
				throw new SQLException("Creating contratClient failed, no ID obtained.");
			}

			// ------ On crée les données contrat ------ //
			preparedStatement = connection.prepareStatement(
					"INSERT INTO contrat(contrat.version, contrat.statut, contrat.document, contrat.cheminRelatif, contrat.cheminAbsolu, contrat.nom_contrat, contrat.type_contrat, contrat.id_referent_collaborateur,  contrat.id_client, contrat.date_demarrage, contrat.commentaire, pgmcreation, datecreation, usercreation, type_entite ) "
					+ "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, 1);
			preparedStatement.setString(2, contrat.getStatut());
			String ancienNomFichier = "contrat.pdf";
			String nouveauNomFichier = "contrat_" + contrat.getClient() + "_" + lastid + "_1.pdf";
			File filetemporaire = new File(chemin_absolu_document_defaut + ancienNomFichier);
			File fileDefinitif = new File(chemin_absolu_document_reel + nouveauNomFichier);
			if (	 filetemporaire.renameTo(fileDefinitif)) {
				filetemporaire = fileDefinitif;
			};
			preparedStatement.setString(3, nouveauNomFichier);
			preparedStatement.setString(4, chemin_relatif_document_reel);
			preparedStatement.setString(5, chemin_absolu_document_reel);
			preparedStatement.setString(6, contrat.getNom_contrat());
			preparedStatement.setString(7, contrat.getType_contrat());
			preparedStatement.setInt(8, contrat.getId_referent_collaborateur());
			preparedStatement.setInt(9, contrat.getId_client());
			preparedStatement.setString(10, dateTime);
			preparedStatement.setString(11, contrat.getCommentaire());
			preparedStatement.setString(12, "ContratDao");
			preparedStatement.setString(13, dateTime);
			preparedStatement.setString(14, System.getProperty("user.name"));
			preparedStatement.setString(15, type_entite);
			int  rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 0) {
				throw new DaoException("Insertion dans contrat a échoué, aucune ligne affectée.");
			}

			connection.commit(); // Valide la transaction

		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback(); // Annule la transaction en cas d'erreur
				} catch (SQLException rollbackEx) {
					rollbackEx.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			// Fermeture des ressources
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}


	}

	//===============================================================================
	// CRUD MODIFIER ENREGISTREMENT
	//===============================================================================
	@Override
	public void modifierContrat(Contrat contrat, String cheminRelatif_AvantModif, String cheminAbsolu_avantModif, String document_avantModif ) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		String nouveauNomFichier = "contrat_" + contrat.getClient() + "_" + contrat.getId() + "_" +contrat.getVersion() + ".pdf";
		File filetemporaire = new File(cheminAbsolu_avantModif + document_avantModif);
		File fileDefinitif = new File(contrat.getCheminAbsolu() + nouveauNomFichier);
		if (	 filetemporaire.renameTo(fileDefinitif)) {
			filetemporaire = fileDefinitif;
		};

		try {
			connexion = daoFactory.getConnection();

			preparedStatement = connexion.prepareStatement(
					"UPDATE contrat SET contrat.statut=?, contrat.document=?, "
							+ "contrat.cheminRelatif=?, contrat.cheminAbsolu=?, contrat.nom_contrat=?, "
							+ "contrat.type_contrat=?, contrat.id_referent_collaborateur=?,  "
							+ "contrat.id_client=?, contrat.date_demarrage=?, contrat.commentaire=?, "
							+ "contrat.pgmmodification=?, contrat.datemodification=?, contrat.usermodification=?, contrat.type_entite=? where contrat.id=? and contrat.version=?;");
			preparedStatement.setString(1, contrat.getStatut());
			preparedStatement.setString(2, nouveauNomFichier);
			preparedStatement.setString(3, contrat.getCheminRelatif());
			preparedStatement.setString(4, contrat.getCheminAbsolu());
			preparedStatement.setString(5, contrat.getNom_contrat());
			preparedStatement.setString(6, contrat.getType_contrat());
			preparedStatement.setInt(7, contrat.getId_referent_collaborateur());
			preparedStatement.setInt(8, contrat.getId_client());
			preparedStatement.setString(9, contrat.getDate_demarrage());
			preparedStatement.setString(10, contrat.getCommentaire());
			preparedStatement.setString(11, "ContratDao");
			preparedStatement.setString(12, dateTime);
			preparedStatement.setString(13, System.getProperty("user.name"));
			preparedStatement.setInt(14, contrat.getId());
			preparedStatement.setInt(15, contrat.getVersion());
			preparedStatement.setString(16, contrat.getType_entite());
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DaoException("Impossible de maj enregistrement avec la table contratt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de maj enregistrement avec la table contratt" + e);
			}
		}
	}

	// =================================================================================
	// CRUD COPIER ENREGISTREMENT
	// =================================================================================
	@Override
	public void copierContrat(Contrat contrat, String cheminRelatif_AvantModif, String cheminAbsolu_avantModif, String document_avantModif, String type_entite) throws DaoException {
		int lastid = 0;
		// ---------------------- BLOC CONNECTION ----------------------
		Connection connection = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement= null;
		try {
			connection = daoFactory.getConnection();
			connection.setAutoCommit(false); // Début de la transaction

			// ------ On récupère l'id du dernier contrat créé + 1 ------
			preparedStatement = connection.prepareStatement("SELECT MAX(id) AS id FROM contratclient");
			resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				lastid = resultat.getInt("id")+1;
			}else {
				throw new SQLException("Creating contrat failed, no ID obtained.");
			}

			// ------ On crée les données contrat dans contrat ------ //
			preparedStatement = connection.prepareStatement(
					"INSERT INTO contrat(contrat.version, contrat.statut, contrat.document, contrat.cheminRelatif, contrat.cheminAbsolu, contrat.nom_contrat, contrat.type_contrat, contrat.id_referent_collaborateur,  contrat.id_client, contrat.date_demarrage, contrat.commentaire, pgmcreation, datecreation, usercreation, type_entite ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);");
			preparedStatement.setInt(1, 1);
			preparedStatement.setString(2, contrat.getStatut());
			String nouveauNomFichier = "contrat_" + contrat.getClient() + "_" + lastid + "_1.pdf";
			File filetemporaire = new File(cheminAbsolu_avantModif + document_avantModif);
			File fileDefinitif = new File(contrat.getCheminAbsolu() + nouveauNomFichier);
			if (	 filetemporaire.renameTo(fileDefinitif)) {
				filetemporaire = fileDefinitif;
			};

			preparedStatement.setString(3, nouveauNomFichier);
			preparedStatement.setString(4, contrat.getCheminRelatif());
			preparedStatement.setString(5, contrat.getCheminAbsolu());
			preparedStatement.setString(6, contrat.getNom_contrat());
			preparedStatement.setString(7, contrat.getType_contrat());
			preparedStatement.setInt(8, contrat.getId_referent_collaborateur());
			preparedStatement.setInt(9, contrat.getId_client());
			preparedStatement.setString(10, dateTime);
			preparedStatement.setString(11, contrat.getCommentaire());
			preparedStatement.setString(12, "ContratDao");
			preparedStatement.setString(13, dateTime);
			preparedStatement.setString(14, System.getProperty("user.name"));
			preparedStatement.setString(15, type_entite);
			int  rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 0) {
				throw new DaoException("Insertion dans contrat a échoué, aucune ligne affectée.");
			}

			connection.commit(); // Valide la transaction

		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback(); // Annule la transaction en cas d'erreur
				} catch (SQLException rollbackEx) {
					rollbackEx.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			// Fermeture des ressources
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}


	}

	// =================================================================================
	// CRUD SUPPRIMER UN ENREGISTREMENT*
	// =================================================================================
	@Override
	public void supprimerContrat(Integer id) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM CONTRATCLIENT WHERE ID_CONTRAT=?;");
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();
			connexion.commit();

		} catch (SQLException e) {
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
	// ANNULER CONTRATCLIENT
	// =================================================================================
	@Override
	public void annulerContrat(Contrat contrat) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();

			preparedStatement = connexion.prepareStatement(
					"UPDATE contrat SET contrat.statut=?, contrat.pgmmodification=?, contrat.datemodification=?,"
							+ " contrat.usermodification=? where contrat.id=? and contrat.version=?;");
			preparedStatement.setString(1, "Annulé");
			preparedStatement.setString(2, "ANNULATION");
			preparedStatement.setString(3, dateTime);
			preparedStatement.setString(4, System.getProperty("user.name"));
			preparedStatement.setInt(5, contrat.getId());
			preparedStatement.setInt(6, contrat.getVersion());
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DaoException("Impossible d'annuler enregistrement avec la table contratt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible d'annuler enregistrement avec la table contratt" + e);
			}
		}
	}
	// =================================================================================
	// TERMINER CONTRATCLIENT
	// =================================================================================
	@Override
	public void terminerContrat(Contrat contrat) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();

			preparedStatement = connexion.prepareStatement(
					"UPDATE contrat SET contrat.statut=?, contrat.pgmmodification=?, contrat.datemodification=?,"
							+ " contrat.usermodification=? where contrat.id=? and contrat.version=?;");
			preparedStatement.setString(1, "Terminé");
			preparedStatement.setString(2, "FIN CONTRAT");
			preparedStatement.setString(3, dateTime);
			preparedStatement.setString(4, System.getProperty("user.name"));
			preparedStatement.setInt(5, contrat.getId());
			preparedStatement.setInt(6, contrat.getVersion());
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
		// versionner CONTRATCLIENT
		// =================================================================================
		@Override
		public void versionnerContrat(Contrat contrat) throws DaoException {
			Connection connexion = null;
			PreparedStatement preparedStatement = null;

			try {
				connexion = daoFactory.getConnection();

				preparedStatement = connexion.prepareStatement(
						"INSERT INTO contrat (id, version, contrat.statut, contrat.document, contrat.cheminRelatif, contrat.cheminAbsolu, contrat.nom_contrat, contrat.type_contrat, contrat.id_referent_collaborateur, contrat.id_client, contrat.type_entite, usermodification, datemodification, pgmmodification) " +
				                   "SELECT ?, COALESCE(MAX(version), 0) + 1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? " +
				                   "FROM contrat WHERE id = ?");
				preparedStatement.setInt(1, contrat.getId());
				preparedStatement.setString(2, "En-cours");
				preparedStatement.setString(3, "");
				preparedStatement.setString(4, contrat.getCheminRelatif());
				preparedStatement.setString(5, contrat.getCheminAbsolu());
				preparedStatement.setString(6, contrat.getNom_contrat());
				preparedStatement.setString(7, contrat.getType_contrat());
				preparedStatement.setInt(8, contrat.getId_referent_collaborateur());
				preparedStatement.setInt(9, contrat.getId_client());
				preparedStatement.setString(10, contrat.getType_entite());
				preparedStatement.setString(11, System.getProperty("user.name"));
				preparedStatement.setString(12, dateTime);
				preparedStatement.setString(13, "VERSION");
				preparedStatement.setInt(14, contrat.getId());
				preparedStatement.executeUpdate();
				connexion.commit();
			} catch (SQLException e) {
				try {
					if (connexion != null) {
						connexion.rollback();
					}
				} catch (SQLException e2) {
				}
				throw new DaoException("Impossible de versionner enregistrement avec la table contratt" + e);
			} finally {
				try {
					if (connexion != null) {
						connexion.close();
					}
				} catch (SQLException e) {
					throw new DaoException("Impossible de supprimer enregistrement avec la table contratt" + e);
				}
			}
			terminerContrat(contrat);
		}

		// =================================================================================
		// RENOMMER CONTRATCLIENT
		// =================================================================================
		@Override
		public void renommerContrat(Contrat contrat) throws DaoException {
			Connection connexion = null;
			PreparedStatement preparedStatement = null;

			try {
				connexion = daoFactory.getConnection();

				preparedStatement = connexion.prepareStatement(
						"UPDATE contrat SET contrat.nom_contrat=?, contrat.pgmmodification=?, contrat.datemodification=?,"
								+ " contrat.usermodification=? where contrat.id=? and contrat.version=?;");
				preparedStatement.setString(1, contrat.getNom_contrat());
				preparedStatement.setString(2, "FIN CONTRAT");
				preparedStatement.setString(3, dateTime);
				preparedStatement.setString(4, System.getProperty("user.name"));
				preparedStatement.setInt(5, contrat.getId());
				preparedStatement.setInt(6, contrat.getVersion());
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

	// ================================================================================
	// ====LISTER LES ENREGISTREMENTS
	// ================================================================================
	@Override
	public List<Contrat> listerContrat() throws DaoException {
		List<Contrat> contrats = new ArrayList<Contrat>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM contrat;");

			while (resultat.next()) {
				int id = resultat.getInt("id");
				int version = resultat.getInt("version");
				String type_contrat = resultat.getString("type_contrat");
				String documentClient = resultat.getString("documentClient");

				Contrat contrat = new Contrat();
				contrat.setId(id);
				contrat.setVersion(version);
				contrat.setType_contrat(type_contrat);
				contrat.setDocument(documentClient);

				contrats.add(contrat);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de lister enregistrement avec la table contratt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de lister enregistrement avec la table contratt" + e);
			}
		}
		return contrats;
	}



	// ====CRUD LIRE UN ENREGISTREMENT SPECIFIQUE VIA SON ID_CONTRAT ET
	// ID_AVENANT====================================
	@Override
	public Contrat trouverIdVersion(int id, int version, String type_entite) throws DaoException {
		Contrat contrat = new Contrat();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"SELECT contrat.type_contrat, contrat.document, contrat.id_referent_collaborateur, contrat.id_client,"
							+ " contrat.date_demarrage,  contrat.nom_contrat, contrat.statut, contrat.commentaire, "
							+ "societe.raison_sociale, collaborateur.prenom, collaborateur.nom, contrat.cheminrelatif, contrat.cheminAbsolu, contrat.type_entite from contrat "
							+ "LEFT JOIN collaborateur on contrat.id_referent_collaborateur= collaborateur.id "
							+ "LEFT JOIN societe on contrat.id_client=societe.id where societe.type='" + type_entite + "' "
							+ "and contrat.id=? and contrat.version=? ");

			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, version);
			resultat = preparedStatement.executeQuery();
			connexion.commit();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if (resultat.next()) {
				contrat.setId(id);
				contrat.setVersion(version);
				contrat.setType_contrat(resultat.getString("contrat.type_contrat"));
				contrat.setDocument(resultat.getString("contrat.document"));
				contrat.setId_referent_collaborateur(resultat.getInt("contrat.id_referent_collaborateur"));
				contrat.setId_client(resultat.getInt("contrat.id_client"));
				contrat.setClient(resultat.getString("societe.raison_sociale"));
				contrat.setCollaborateur(resultat.getString("collaborateur.prenom")  + resultat.getString("collaborateur.nom") );
				contrat.setDate_demarrage(resultat.getString("contrat.date_demarrage"));
				contrat.setNom_contrat(resultat.getString("contrat.nom_contrat"));
				contrat.setStatut(resultat.getString("contrat.statut"));
				contrat.setCommentaire(resultat.getString("contrat.commentaire"));
				contrat.setCheminRelatif(resultat.getString("contrat.cheminRelatif"));
				contrat.setCheminAbsolu(resultat.getString("contrat.cheminAbsolu"));
				contrat.setType_entite(resultat.getString("contrat.type_entite"));
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de trouver id enregistrement avec la table contratt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException(
						"Impossible de trouver id enregistrement avec la table contratt" + e);
			}
		}
		return contrat;
	}


	// ==================================================================
	// ====rechercher et lister les enregistrements
	// ==================================================================
	@Override
	public List<Contrat> rechercheContrats1(int offset, int noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne,String tag_statut, String type_entite) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		String tag_where_statut = "";
		if (! tag_statut.equals("Tout"))
		tag_where_statut = "and contrat.statut='"
				+ tag_statut + "' " ;

		String query2 = "select SQL_CALC_FOUND_ROWS contrat.id, contrat.version, contrat.statut, contrat.document, contrat.cheminRelatif, contrat.cheminRelatif, contrat.nom_contrat, contrat.type_contrat, contrat.id_referent_collaborateur,  contrat.id_client, contrat.date_demarrage, contrat.commentaire, societe.raison_sociale, contrat.type_entite from contrat  "
				+ "LEFT JOIN societe on contrat.id_client=societe.id where societe.type='" +type_entite+"' "+ "and contrat.type_entite='"+type_entite+"' "
				+ tag_where_statut + " ORDER BY " + select_tri + " limit " + offset + ", " + noOfRecords;
		List<Contrat> list = new ArrayList<Contrat>();
		Contrat contrat = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query2);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				contrat = new Contrat();
				contrat.setId(rs.getInt(1));
				contrat.setVersion(rs.getInt(2));
				contrat.setStatut(rs.getString(3));
				contrat.setDocument(rs.getString(5) + rs.getString(4));
				contrat.setNom_contrat(rs.getString(7));
				contrat.setType_contrat(rs.getString(8));
				contrat.setId_referent_collaborateur(rs.getInt(9));
				contrat.setId_client(rs.getInt(10));
				contrat.setDate_demarrage(rs.getString(11));
				contrat.setCommentaire(rs.getString(12));
				contrat.setClient(rs.getString(13));
				contrat.setType_entite(rs.getString(14));
				list.add(contrat);
			}

			// rs.close();
			rs = preparedStatement.executeQuery("SELECT FOUND_ROWS()");

			if (rs.next())
				this.noOfRecords = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connexion != null)
					connexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// ==================================================================
		// ====rechercher et lister les enregistrements
		// ==================================================================
		@Override
		public List<Map<String, Object>> rechercheContrats(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
			List<Map<String, Object>> list = new ArrayList<>();
			Connection connexion = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;

			String tag_where_statut = "";
			if (! tag_statut.equals("Tout"))
			tag_where_statut = "and contrat.statut='"
					+ tag_statut + "' " ;

			String query2 = "select SQL_CALC_FOUND_ROWS contrat.id, contrat.version, contrat.statut, contrat.document, contrat.cheminRelatif, contrat.cheminRelatif, contrat.nom_contrat, contrat.type_contrat, contrat.id_referent_collaborateur,  contrat.id_client, contrat.date_demarrage, contrat.commentaire, societe.raison_sociale, contrat.type_entite from contrat  "
					+ "LEFT JOIN societe on contrat.id_client=societe.id where societe.type='" +type_entite+"' "+ "and contrat.type_entite='"+type_entite+"' "
					+ tag_where_statut + " ORDER BY " + select_tri + " limit " + offset + ", " + noOfRecords;

			try {
				connexion = daoFactory.getConnection();
				preparedStatement = connexion.prepareStatement(query2);
				rs = preparedStatement.executeQuery();

				while (rs.next()) {
					 Map<String, Object> contratFields = new LinkedHashMap<>();
		                contratFields.put("id", rs.getInt("id"));
		                contratFields.put("version", rs.getString("version"));
		                contratFields.put("statut", rs.getString("statut"));
		                contratFields.put("document", rs.getInt("document"));
		                contratFields.put("nom_contrat", rs.getString("nom_contrat"));
		                contratFields.put("type_contrat", rs.getString("type_contrat"));
		                contratFields.put("id_referent", rs.getInt("id_referent"));
		                contratFields.put("id_client", rs.getString("id_client"));
		                contratFields.put("date_demarrage", rs.getString("date_demarrage"));
		                contratFields.put("commentaire", rs.getInt("commentaire"));
		                contratFields.put("client", rs.getString("client"));
		                contratFields.put("type_entite", rs.getString("type_entite"));
		                list.add( contratFields);
				}

				// rs.close();
				rs = preparedStatement.executeQuery("SELECT FOUND_ROWS()");

				if (rs.next())
					this.noOfRecords = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (connexion != null)
						connexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return list;
		}

	// =================================================================================
		// RECUPERATION N° ENREGISTREMENT ENCOURS ENTETE PARAMETRE
		// =================================================================================
	    @Override
	    public int getNoOfRecords() {
	        return noOfRecords;
	    }

	    @Override
	    public String getStringRecords() {
	    	String StringRecords = Integer.toString(noOfRecords);
	        return StringRecords;
	    }

	    @Override
	    public Integer getIntegerRecords() {
	    	Integer integerRecords = noOfRecords;
	        return integerRecords;
	    }



	// ======================================================================
	// ====rechercher et lister les enregistrements suivant like
	// ======================================================================
	    @Override
	    public List<Contrat> rechercheLikeContrats1(int offset, int noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne,  String tag_statut, String type_entite) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String tag_where_statut = "";
		if (! tag_statut.equals("Tout"))
		tag_where_statut = "and contrat.statut='"
				+ tag_statut + "' " ;

		String query2 = "select SQL_CALC_FOUND_ROWS contrat.id, contrat.version, contrat.statut, contrat.document, contrat.cheminRelatif, contrat.cheminRelatif, contrat.nom_contrat, contrat.type_contrat, contrat.id_referent_collaborateur,  contrat.id_client, contrat.date_demarrage, contrat.commentaire, societe.raison_sociale, contrat.type_entite  from contrat  "
				+ "LEFT JOIN societe on contrat.id_client=societe.id where societe.type='" +type_entite+"' "+ "and contrat.type_entite='"+type_entite+"' "
				+ tag_where_statut + " and " + select_like + " ORDER BY " + select_tri + " limit " + offset + ", "
				+ noOfRecords;
		List<Contrat> list = new ArrayList<Contrat>();
		Contrat contrat = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query2);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				contrat = new Contrat();
				contrat.setId(rs.getInt(1));
				contrat.setVersion(rs.getInt(2));
				contrat.setStatut(rs.getString(3));
				contrat.setDocument(rs.getString(5) + rs.getString(4));
				contrat.setNom_contrat(rs.getString(7));
				contrat.setType_contrat(rs.getString(8));
				contrat.setId_referent_collaborateur(rs.getInt(9));
				contrat.setId_client(rs.getInt(10));
				contrat.setDate_demarrage(rs.getString(11));
				contrat.setCommentaire(rs.getString(12));
				contrat.setClient(rs.getString(13));
				contrat.setType_entite(rs.getString(14));
				list.add(contrat);
			}

			rs.close();
			rs = preparedStatement.executeQuery("SELECT FOUND_ROWS()");

			if (rs.next())
				this.noOfRecords = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connexion != null)
					connexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	    @Override
	    public List<Map<String, Object>> rechercheLikeContrats(int offset, int noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne,  String tag_statut, String type_entite) {
	    	List<Map<String, Object>> list = new ArrayList<>();
			Connection connexion = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;

			String tag_where_statut = "";
			if (! tag_statut.equals("Tout"))
			tag_where_statut = "and contrat.statut='"
					+ tag_statut + "' " ;

			String query2 = "select SQL_CALC_FOUND_ROWS contrat.id, contrat.version, contrat.statut, contrat.document, contrat.cheminRelatif, contrat.cheminRelatif, contrat.nom_contrat, contrat.type_contrat, contrat.id_referent_collaborateur,  contrat.id_client, contrat.date_demarrage, contrat.commentaire, societe.raison_sociale, contrat.type_entite from contrat  "
					+ "LEFT JOIN societe on contrat.id_client=societe.id where societe.type='" +type_entite+"' "+ "and contrat.type_entite='"+type_entite+"' "
					+ tag_where_statut + " ORDER BY " + select_tri + " limit " + offset + ", " + noOfRecords;

			try {
				connexion = daoFactory.getConnection();
				preparedStatement = connexion.prepareStatement(query2);
				rs = preparedStatement.executeQuery();

				while (rs.next()) {
					 Map<String, Object> contratFields = new LinkedHashMap<>();
		                contratFields.put("id", rs.getInt("id"));
		                contratFields.put("version", rs.getString("version"));
		                contratFields.put("statut", rs.getString("statut"));
		                contratFields.put("document", rs.getInt("document"));
		                contratFields.put("nom_contrat", rs.getString("nom_contrat"));
		                contratFields.put("type_contrat", rs.getString("type_contrat"));
		                contratFields.put("id_referent", rs.getInt("id_referent"));
		                contratFields.put("id_client", rs.getString("id_client"));
		                contratFields.put("date_demarrage", rs.getString("date_demarrage"));
		                contratFields.put("commentaire", rs.getInt("commentaire"));
		                contratFields.put("client", rs.getString("client"));
		                contratFields.put("type_entite", rs.getString("type_entite"));
		                list.add( contratFields);
				}

				// rs.close();
				rs = preparedStatement.executeQuery("SELECT FOUND_ROWS()");

				if (rs.next())
					this.noOfRecords = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (connexion != null)
						connexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return list;
	}


	// =============================================================
	// TROUVER NOM CLIENT
	// =============================================================
	@Override
	public boolean trouverNomContrat(String nom) throws DaoException {
		boolean existe = false;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			String sql = "SELECT * FROM contrat WHERE nom_contrat=?;";
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

	// =============================================================
		// TROUVER FACTURE
		// =============================================================
		@Override
		public boolean trouverFacture(int id) throws DaoException {
			boolean existe = false;
			Connection connexion = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultat = null;

			try {
				connexion = daoFactory.getConnection();
				String sql = "select * from facture_vente_service left join mission on facture_vente_service.id_mission=mission.id where mission.mission_id_client=?";
				preparedStatement = connexion.prepareStatement(sql);
				preparedStatement.setInt(1, id);
				resultat = preparedStatement.executeQuery();
				if (resultat.next()) {
					existe = true;
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de vérifier l'existence de factures"+ e);
			} finally {
				closeResources(connexion, preparedStatement, resultat);
			}
			return existe;
		}


	// =============================================================
	// RAFRAICHIR REPERTOIRE
	// =============================================================
	private void refreshDirectory(File directory) {
		// Récupérer la liste des fichiers dans le répertoire
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
			}
		}
	}

	// =============================================================
	// FERMER RESSOURCES
	// =============================================================
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
