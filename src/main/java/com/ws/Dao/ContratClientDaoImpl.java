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

import com.ws.beans.ContratClient;
import com.ws.configuration.DatasourceH;

public class ContratClientDaoImpl implements ContratClientDao {
	private DaoFactory daoFactory;

	// date du jour
	String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
	private int noOfRecords;
	Map<String, Integer> type_entiteDictionary = new HashMap<>();
	Map<String, Integer> societeDictionary = new HashMap<>();

	// Récupération paramètres

	ContratClientDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public Map<String, Integer> listerTypeContrat() {
		// liste des types de contrat disponibles
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query = "select libelle, id from type_entiteSociete";
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			int i = 0;
			while (rs.next()) {
				i = i + 1;
				type_entiteDictionary.put(rs.getString(1), rs.getInt(2));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return type_entiteDictionary;
	}

	public Map<String, Integer> listerSociete() {
		// liste des types de contrat disponibles
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query = "select raison_sociale, id from societe";
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			int i = 0;
			while (rs.next()) {
				i = i + 1;
				societeDictionary.put(rs.getString(1), rs.getInt(2));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return societeDictionary;
	}


	// ================================================================================
	// ====CRUD CREER
	// ================================================================================
	@Override
	public void ajouterContratClient(ContratClient contratClient, String chemin_absolu_document_defaut,
			String chemin_absolu_document_reel, String chemin_relatif_document_reel) throws DaoException {
		// == Initialisation paramètres
		int lastid = 0;
		listerTypeContrat();
		listerSociete();


		// ---------------------- BLOC CONNECTION ----------------------
		Connection connection = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement= null;
		try {
			connection = daoFactory.getConnection();
			connection.setAutoCommit(false); // Début de la transaction

			// ------- Création en premier du n° de contrat dans CONTRATCLIENT -------
			preparedStatement = connection.prepareStatement(
					"INSERT INTO contratClient( pgmcreation, datecreation, usercreation ) VALUES( ?, ?, ?);");
			preparedStatement.setString(1, "ContratClientDao");
			preparedStatement.setString(2, dateTime);
			preparedStatement.setString(3, System.getProperty("user.name"));
			preparedStatement.executeUpdate();


			// ------ On récupère l'id du contrat créé ------
			preparedStatement = connection.prepareStatement("SELECT MAX(id) AS id FROM contratclient");
			resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				lastid = resultat.getInt("id");
			}else {
				throw new SQLException("Creating contratClient failed, no ID obtained.");
			}



			// ------ On crée les données contrat dans AVENANTCLIENT ------ //
			preparedStatement = connection.prepareStatement(
					"INSERT INTO avenantClient(avenantclient.id_contrat, avenantclient.id_avenant, avenantclient.statut, avenantclient.document, avenantclient.cheminRelatif, avenantclient.cheminAbsolu, avenantclient.nom_contrat, avenantclient.type_contrat, avenantclient.id_referent_collaborateur,  avenantclient.id_client, avenantclient.date_demarrage, avenantclient.commentaire, pgmcreation, datecreation, usercreation ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);");
			preparedStatement.setInt(1, lastid);
			preparedStatement.setInt(2, 1);
			preparedStatement.setString(3, contratClient.getStatut());
			String ancienNomFichier = "contrat.pdf";
			String nouveauNomFichier = "contrat_" + contratClient.getClient() + "_" + lastid + "_1.pdf";
			File filetemporaire = new File(chemin_absolu_document_defaut + ancienNomFichier);
			File fileDefinitif = new File(chemin_absolu_document_reel + nouveauNomFichier);
			if (	 filetemporaire.renameTo(fileDefinitif)) {
				filetemporaire = fileDefinitif;
			};
			preparedStatement.setString(4, nouveauNomFichier);
			preparedStatement.setString(5, chemin_relatif_document_reel);
			preparedStatement.setString(6, chemin_absolu_document_reel);
			preparedStatement.setString(7, contratClient.getNom_contrat());
			preparedStatement.setString(8, contratClient.getType_contrat());
			preparedStatement.setInt(9, contratClient.getId_referent_collaborateur());
			preparedStatement.setInt(10, contratClient.getId_client());
			preparedStatement.setString(11, dateTime);
			preparedStatement.setString(12, contratClient.getCommentaire());
			preparedStatement.setString(13, "ContratClientDao");
			preparedStatement.setString(14, dateTime);
			preparedStatement.setString(15, System.getProperty("user.name"));
			int  rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 0) {
				throw new DaoException("Insertion dans avenantClient a échoué, aucune ligne affectée.");
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
	public void modifierContratClient(ContratClient contratClient, String cheminRelatif_AvantModif, String cheminAbsolu_avantModif, String document_avantModif ) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		listerTypeContrat();
		listerSociete();

		String nouveauNomFichier = "contrat_" + contratClient.getClient() + "_" + contratClient.getId_contrat() + "_" +contratClient.getId_avenant() + ".pdf";
		File filetemporaire = new File(cheminAbsolu_avantModif + document_avantModif);
		File fileDefinitif = new File(contratClient.getCheminAbsolu() + nouveauNomFichier);
		if (	 filetemporaire.renameTo(fileDefinitif)) {
			filetemporaire = fileDefinitif;
		};

		try {
			connexion = daoFactory.getConnection();

			preparedStatement = connexion.prepareStatement(
					"UPDATE avenantClient SET avenantclient.statut=?, avenantclient.document=?, "
							+ "avenantclient.cheminRelatif=?, avenantclient.cheminAbsolu=?, avenantclient.nom_contrat=?, "
							+ "avenantclient.type_contrat=?, avenantclient.id_referent_collaborateur=?,  "
							+ "avenantclient.id_client=?, avenantclient.date_demarrage=?, avenantclient.commentaire=?, "
							+ "avenantclient.pgmmodification=?, avenantclient.datemodification=?, avenantclient.usermodification=? where avenantclient.id_contrat=? and avenantclient.id_avenant=?;");
			preparedStatement.setString(1, contratClient.getStatut());
			preparedStatement.setString(2, nouveauNomFichier);
			preparedStatement.setString(3, contratClient.getCheminRelatif());
			preparedStatement.setString(4, contratClient.getCheminAbsolu());
			preparedStatement.setString(5, contratClient.getNom_contrat());
			preparedStatement.setString(6, contratClient.getType_contrat());
			preparedStatement.setInt(7, contratClient.getId_referent_collaborateur());
			preparedStatement.setInt(8, contratClient.getId_client());
			preparedStatement.setString(9, contratClient.getDate_demarrage());
			preparedStatement.setString(10, contratClient.getCommentaire());
			preparedStatement.setString(11, "ContratClientDao");
			preparedStatement.setString(12, dateTime);
			preparedStatement.setString(13, System.getProperty("user.name"));
			preparedStatement.setInt(14, contratClient.getId_contrat());
			preparedStatement.setInt(15, contratClient.getId_avenant());
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DaoException("Impossible de maj enregistrement avec la table contratClientt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de maj enregistrement avec la table contratClientt" + e);
			}
		}
	}

	// =================================================================================
	// CRUD COPIER ENREGISTREMENT
	// =================================================================================
	@Override
	public void copierContratClient(ContratClient contratClient, String cheminRelatif_AvantModif, String cheminAbsolu_avantModif, String document_avantModif) throws DaoException {
		// == Initialisation paramètres
		int lastid = 0;
		listerTypeContrat();
		listerSociete();

		// ---------------------- BLOC CONNECTION ----------------------
		Connection connection = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement= null;
		try {
			connection = daoFactory.getConnection();
			connection.setAutoCommit(false); // Début de la transaction

			// ------- Création en premier du n° de contrat dans CONTRATCLIENT -------
			preparedStatement = connection.prepareStatement(
					"INSERT INTO contratClient( pgmcreation, datecreation, usercreation ) VALUES( ?, ?, ?);");
			preparedStatement.setString(1, "ContratClientDao");
			preparedStatement.setString(2, dateTime);
			preparedStatement.setString(3, System.getProperty("user.name"));
			preparedStatement.executeUpdate();
			// ------ On récupère l'id du contrat créé ------
			preparedStatement = connection.prepareStatement("SELECT MAX(id) AS id FROM contratclient");
			resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				lastid = resultat.getInt("id");
			}else {
				throw new SQLException("Creating contratClient failed, no ID obtained.");
			}

			// ------ On crée les données contrat dans AVENANTCLIENT ------ //
			preparedStatement = connection.prepareStatement(
					"INSERT INTO avenantClient(avenantclient.id_contrat, avenantclient.id_avenant, avenantclient.statut, avenantclient.document, avenantclient.cheminRelatif, avenantclient.cheminAbsolu, avenantclient.nom_contrat, avenantclient.type_contrat, avenantclient.id_referent_collaborateur,  avenantclient.id_client, avenantclient.date_demarrage, avenantclient.commentaire, pgmcreation, datecreation, usercreation ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);");
			preparedStatement.setInt(1, lastid);
			preparedStatement.setInt(2, 1);
			preparedStatement.setString(3, contratClient.getStatut());
			String nouveauNomFichier = "contrat_" + contratClient.getClient() + "_" + lastid + "_1.pdf";
			File filetemporaire = new File(cheminAbsolu_avantModif + document_avantModif);
			File fileDefinitif = new File(contratClient.getCheminAbsolu() + nouveauNomFichier);
			if (	 filetemporaire.renameTo(fileDefinitif)) {
				filetemporaire = fileDefinitif;
			};

			preparedStatement.setString(4, nouveauNomFichier);
			preparedStatement.setString(5, contratClient.getCheminRelatif());
			preparedStatement.setString(6, contratClient.getCheminAbsolu());
			preparedStatement.setString(7, contratClient.getNom_contrat());
			preparedStatement.setString(8, contratClient.getType_contrat());
			preparedStatement.setInt(9, contratClient.getId_referent_collaborateur());
			preparedStatement.setInt(10, contratClient.getId_client());
			preparedStatement.setString(11, dateTime);
			preparedStatement.setString(12, contratClient.getCommentaire());
			preparedStatement.setString(13, "ContratClientDao");
			preparedStatement.setString(14, dateTime);
			preparedStatement.setString(15, System.getProperty("user.name"));
			int  rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 0) {
				throw new DaoException("Insertion dans avenantClient a échoué, aucune ligne affectée.");
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
	public void supprimerContratClient(Integer id_contrat) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM CONTRATCLIENT WHERE ID_CONTRAT=?;");
			preparedStatement.setInt(1, id_contrat);

			preparedStatement.executeUpdate();
			connexion.commit();

		} catch (SQLException e) {
			throw new DaoException("Impossible de supprimer enregistrement avec la table contratClientt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de supprimer enregistrement avec la table contratClientt" + e);
			}
		}

	}

	// =================================================================================
	// ANNULER CONTRATCLIENT
	// =================================================================================
	@Override
	public void annulerContratClient(ContratClient contratClient) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		listerTypeContrat();
		listerSociete();

		try {
			connexion = daoFactory.getConnection();

			preparedStatement = connexion.prepareStatement(
					"UPDATE avenantClient SET avenantclient.statut=?, avenantclient.pgmmodification=?, avenantclient.datemodification=?,"
							+ " avenantclient.usermodification=? where avenantclient.id_contrat=? and avenantclient.id_avenant=?;");
			preparedStatement.setString(1, "Annulé");
			preparedStatement.setString(2, "ANNULATION");
			preparedStatement.setString(3, dateTime);
			preparedStatement.setString(4, System.getProperty("user.name"));
			preparedStatement.setInt(5, contratClient.getId_contrat());
			preparedStatement.setInt(6, contratClient.getId_avenant());
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DaoException("Impossible d'annuler enregistrement avec la table contratClientt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible d'annuler enregistrement avec la table contratClientt" + e);
			}
		}
	}
	// =================================================================================
	// TERMINER CONTRATCLIENT
	// =================================================================================
	@Override
	public void terminerContratClient(ContratClient contratClient) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		listerTypeContrat();
		listerSociete();

		try {
			connexion = daoFactory.getConnection();

			preparedStatement = connexion.prepareStatement(
					"UPDATE avenantClient SET avenantclient.statut=?, avenantclient.pgmmodification=?, avenantclient.datemodification=?,"
							+ " avenantclient.usermodification=? where avenantclient.id_contrat=? and avenantclient.id_avenant=?;");
			preparedStatement.setString(1, "Terminé");
			preparedStatement.setString(2, "FIN CONTRAT");
			preparedStatement.setString(3, dateTime);
			preparedStatement.setString(4, System.getProperty("user.name"));
			preparedStatement.setInt(5, contratClient.getId_contrat());
			preparedStatement.setInt(6, contratClient.getId_avenant());
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DaoException("Impossible de supprimer enregistrement avec la table contratClientt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de supprimer enregistrement avec la table contratClientt" + e);
			}
		}
	}

	// =================================================================================
		// versionner CONTRATCLIENT
		// =================================================================================
		@Override
		public void versionnerContratClient(ContratClient contratClient) throws DaoException {
			Connection connexion = null;
			PreparedStatement preparedStatement = null;
			listerTypeContrat();
			listerSociete();

			try {
				connexion = daoFactory.getConnection();

				preparedStatement = connexion.prepareStatement(
						"INSERT INTO avenantclient (id_contrat, id_avenant, avenantclient.statut, avenantclient.document, avenantclient.cheminRelatif, avenantclient.cheminAbsolu, avenantclient.nom_contrat, avenantclient.type_contrat, avenantclient.id_referent_collaborateur,  avenantclient.id_client, usermodification, datemodification, pgmmodification) " +
				                   "SELECT ?, COALESCE(MAX(id_avenant), 0) + 1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? " +
				                   "FROM avenantclient WHERE id_contrat = ?");
				preparedStatement.setInt(1, contratClient.getId_contrat());
				preparedStatement.setString(2, "En-cours");
				preparedStatement.setString(3, "");
				preparedStatement.setString(4, contratClient.getCheminRelatif());
				preparedStatement.setString(5, contratClient.getCheminAbsolu());
				preparedStatement.setString(6, contratClient.getNom_contrat());
				preparedStatement.setString(7, contratClient.getType_contrat());
				preparedStatement.setInt(8, contratClient.getId_referent_collaborateur());
				preparedStatement.setInt(9, contratClient.getId_client());

				preparedStatement.setString(10, System.getProperty("user.name"));
				preparedStatement.setString(11, dateTime);
				preparedStatement.setString(12, "VERSION");
				preparedStatement.setInt(13, contratClient.getId_contrat());
				preparedStatement.executeUpdate();
				connexion.commit();
			} catch (SQLException e) {
				try {
					if (connexion != null) {
						connexion.rollback();
					}
				} catch (SQLException e2) {
				}
				throw new DaoException("Impossible de versionner enregistrement avec la table contratClientt" + e);
			} finally {
				try {
					if (connexion != null) {
						connexion.close();
					}
				} catch (SQLException e) {
					throw new DaoException("Impossible de supprimer enregistrement avec la table contratClientt" + e);
				}
			}
			terminerContratClient(contratClient);
		}

		// =================================================================================
		// RENOMMER CONTRATCLIENT
		// =================================================================================
		@Override
		public void renommerContratClient(ContratClient contratClient) throws DaoException {
			Connection connexion = null;
			PreparedStatement preparedStatement = null;
			listerTypeContrat();
			listerSociete();

			try {
				connexion = daoFactory.getConnection();

				preparedStatement = connexion.prepareStatement(
						"UPDATE avenantClient SET avenantclient.nom_contrat=?, avenantclient.pgmmodification=?, avenantclient.datemodification=?,"
								+ " avenantclient.usermodification=? where avenantclient.id_contrat=? and avenantclient.id_avenant=?;");
				preparedStatement.setString(1, contratClient.getNom_contrat());
				preparedStatement.setString(2, "FIN CONTRAT");
				preparedStatement.setString(3, dateTime);
				preparedStatement.setString(4, System.getProperty("user.name"));
				preparedStatement.setInt(5, contratClient.getId_contrat());
				preparedStatement.setInt(6, contratClient.getId_avenant());
				preparedStatement.executeUpdate();
				connexion.commit();
			} catch (SQLException e) {
				try {
					if (connexion != null) {
						connexion.rollback();
					}
				} catch (SQLException e2) {
				}
				throw new DaoException("Impossible de supprimer enregistrement avec la table contratClientt" + e);
			} finally {
				try {
					if (connexion != null) {
						connexion.close();
					}
				} catch (SQLException e) {
					throw new DaoException("Impossible de supprimer enregistrement avec la table contratClientt" + e);
				}
			}
		}

	// ================================================================================
	// ====LISTER LES ENREGISTREMENTS
	// ================================================================================
	@Override
	public List<ContratClient> listerContratClient() throws DaoException {
		List<ContratClient> contratClients = new ArrayList<ContratClient>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM contratClient;");

			while (resultat.next()) {
				int id_contrat = resultat.getInt("id_contrat");
				int id_avenant = resultat.getInt("id_avenant");
				String type_contrat = resultat.getString("type_contrat");
				String documentClient = resultat.getString("documentClient");

				ContratClient contratClient = new ContratClient();
				contratClient.setId_contrat(id_contrat);
				contratClient.setId_avenant(id_avenant);
				contratClient.setType_contrat(type_contrat);
				contratClient.setDocument(documentClient);

				contratClients.add(contratClient);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de lister enregistrement avec la table contratClientt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de lister enregistrement avec la table contratClientt" + e);
			}
		}
		return contratClients;
	}



	// ====CRUD LIRE UN ENREGISTREMENT SPECIFIQUE VIA SON ID_CONTRAT ET
	// ID_AVENANT====================================
	@Override
	public ContratClient trouverIdVersion(int id_contrat, int id_avenant) throws DaoException {
		ContratClient contratClient = new ContratClient();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"SELECT avenantclient.type_contrat, avenantclient.document, avenantclient.id_referent_collaborateur, avenantclient.id_client,"
							+ " avenantclient.date_demarrage,  avenantclient.nom_contrat, avenantclient.statut, avenantclient.commentaire, "
							+ "societe.raison_sociale, collaborateur.prenom, collaborateur.nom, avenantclient.cheminrelatif, avenantclient.cheminAbsolu from avenantClient "
							+ "LEFT JOIN collaborateur on avenantclient.id_referent_collaborateur= collaborateur.id "
							+ "LEFT JOIN societe on avenantclient.id_client=societe.id where societe.type='CL' "
							+ "and avenantclient.id_contrat=? and avenantclient.id_avenant=? ");

			preparedStatement.setInt(1, id_contrat);
			preparedStatement.setInt(2, id_avenant);
			resultat = preparedStatement.executeQuery();
			connexion.commit();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if (resultat.next()) {
				contratClient.setId_contrat(id_contrat);
				contratClient.setId_avenant(id_avenant);
				contratClient.setType_contrat(resultat.getString("avenantclient.type_contrat"));
				contratClient.setDocument(resultat.getString("avenantclient.document"));
				contratClient.setId_referent_collaborateur(resultat.getInt("avenantclient.id_referent_collaborateur"));
				contratClient.setId_client(resultat.getInt("avenantclient.id_client"));
				contratClient.setClient(resultat.getString("societe.raison_sociale"));
				contratClient.setCollaborateur(resultat.getString("collaborateur.prenom")  + resultat.getString("collaborateur.nom") );
				contratClient.setDate_demarrage(resultat.getString("avenantclient.date_demarrage"));
				contratClient.setNom_contrat(resultat.getString("avenantclient.nom_contrat"));
				contratClient.setStatut(resultat.getString("avenantclient.statut"));
				contratClient.setCommentaire(resultat.getString("avenantclient.commentaire"));
				contratClient.setCheminRelatif(resultat.getString("avenantclient.cheminRelatif"));
				contratClient.setCheminAbsolu(resultat.getString("avenantclient.cheminAbsolu"));
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de trouver id_contrat enregistrement avec la table contratClientt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException(
						"Impossible de trouver id_contrat enregistrement avec la table contratClientt" + e);
			}
		}
		return contratClient;
	}


	// ==================================================================
	// ====rechercher et lister les enregistrements
	// ==================================================================
	@Override
	public List<ContratClient> rechercheContratClients(int offset, int noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne,
			String tag_statut, String type_entite) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		String tag_where_statut = "";
		if (! tag_statut.equals("Tout"))
		tag_where_statut = "and avenantclient.statut='"
				+ tag_statut + "' " ;

		String query2 = "select SQL_CALC_FOUND_ROWS avenantclient.id_contrat, avenantclient.id_avenant, avenantclient.statut, avenantclient.document, avenantclient.cheminRelatif, avenantclient.cheminRelatif, avenantclient.nom_contrat, avenantclient.type_contrat, avenantclient.id_referent_collaborateur,  avenantclient.id_client, avenantclient.date_demarrage, avenantclient.commentaire, societe.raison_sociale from avenantClient  "
				+ "LEFT JOIN societe on avenantclient.id_client=societe.id where societe.type='" +type_entite+"' "+ "and avenantClient.type_entite='"+type_entite+"' "
				+ tag_where_statut + " ORDER BY " + select_tri + " limit " + offset + ", " + noOfRecords;
		List<ContratClient> list = new ArrayList<ContratClient>();
		ContratClient contratClient = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query2);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				contratClient = new ContratClient();
				contratClient.setId_contrat(rs.getInt(1));
				contratClient.setId_avenant(rs.getInt(2));
				contratClient.setStatut(rs.getString(3));
				contratClient.setDocument(rs.getString(5) + rs.getString(4));
				contratClient.setNom_contrat(rs.getString(7));
				contratClient.setType_contrat(rs.getString(8));
				contratClient.setId_referent_collaborateur(rs.getInt(9));
				contratClient.setId_client(rs.getInt(10));
				contratClient.setDate_demarrage(rs.getString(11));
				contratClient.setCommentaire(rs.getString(12));
				contratClient.setClient(rs.getString(13));
				list.add(contratClient);
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
	// ====Avoir le N° enregistrement en cours
	// ==================================================================
	public int getNoOfRecords() {
		return noOfRecords;
	}

	// ======================================================================
	// ====rechercher et lister les enregistrements suivant like
	// ======================================================================
	@Override
	public List<ContratClient> rechercheLikeContratClients(int offset, int noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String tag_where_statut = "";
		if (! tag_statut.equals("Tout"))
		tag_where_statut = "and avenantclient.statut='"
				+ tag_statut + "' " ;

		String query2 = "select SQL_CALC_FOUND_ROWS avenant client.id_contrat, avenantclient.id_avenant, avenantclient.statut, avenantclient.document, avenantclient.cheminRelatif, avenantclient.cheminRelatif, avenantclient.nom_contrat, avenantclient.type_contrat, avenantclient.id_referent_collaborateur,  avenantclient.id_client, avenantclient.date_demarrage, avenantclient.commentaire, societe.raison_sociale  from avenantClient  "
				+ "LEFT JOIN societe on avenantclient.id_client=societe.id where societe.type='" +type_entite+"' "+ "and avenantClient.type_entite='"+type_entite+"' "
				+ tag_where_statut + " and " + select_like + " ORDER BY " + select_tri + " limit " + offset + ", "
				+ noOfRecords;
		List<ContratClient> list = new ArrayList<ContratClient>();
		ContratClient contratClient = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query2);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				contratClient = new ContratClient();
				contratClient.setId_contrat(rs.getInt(1));
				contratClient.setId_avenant(rs.getInt(2));
				contratClient.setStatut(rs.getString(3));
				contratClient.setDocument(rs.getString(5) + rs.getString(4));
				contratClient.setNom_contrat(rs.getString(7));
				contratClient.setType_contrat(rs.getString(8));
				contratClient.setId_referent_collaborateur(rs.getInt(9));
				contratClient.setId_client(rs.getInt(10));
				contratClient.setDate_demarrage(rs.getString(11));
				contratClient.setCommentaire(rs.getString(12));
				contratClient.setClient(rs.getString(13));
				list.add(contratClient);
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


	// =============================================================
	// TROUVER NOM CLIENT
	// =============================================================
	@Override
	public boolean trouverNomContratClient(String nom) throws DaoException {
		boolean existe = false;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			String sql = "SELECT * FROM avenantClient WHERE nom_contrat=?;";
			preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setString(1, nom);
			resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				existe = true;
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de vérifier l'existence du nom dans la table ContratClient"+ e);
		} finally {
			closeResources(connexion, preparedStatement, resultat);
		}
		return existe;
	}

	// =============================================================
		// TROUVER FACTURE
		// =============================================================
		@Override
		public boolean trouverFacture(int id_contrat) throws DaoException {
			boolean existe = false;
			Connection connexion = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultat = null;

			try {
				connexion = daoFactory.getConnection();
				String sql = "select * from facture_vente_service left join mission on facture_vente_service.id_mission=mission.id where mission.mission_id_contrat_client=?";
				preparedStatement = connexion.prepareStatement(sql);
				preparedStatement.setInt(1, id_contrat);
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
