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
import java.util.List;
import java.util.Map;

import com.ws.beans.Mission;
import com.ws.configuration.DatasourceH;

public class MissionDaoImpl implements MissionDao {
	private DaoFactory daoFactory;

	// date du jour
	String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
	private int noOfRecords;
	Map<String, Integer> type_entiteDictionary = new HashMap<>();
	Map<String, Integer> societeDictionary = new HashMap<>();

	// Récupération paramètres

	MissionDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}



	// ================================================================================
	// ====CRUD CREER
	// ================================================================================
	@Override
	public void ajouterMission(Mission mission, String chemin_absolu_document_defaut,
			String chemin_absolu_document_reel, String chemin_relatif_document_reel, String type_entite) throws DaoException {
		// ---------------------- BLOC CONNECTION ----------------------
		Connection connection = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement= null;

		try {
			connection = daoFactory.getConnection();
			connection.setAutoCommit(false); // Début de la transaction

			// ------ On crée les données mission ------ //
			preparedStatement = connection.prepareStatement(
					"INSERT INTO mission(nom, complement, statut, id_contrat, version_contrat, code_tva, prix_ht, nbr_facture, type_prix, commentaire, date_demarrage, pgmcreation, datecreation, usercreation ) "
					+ "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, mission.getNom());
			preparedStatement.setString(2, mission.getComplement());
			preparedStatement.setString(3, mission.getStatut());
			preparedStatement.setInt(4, mission.getId_contrat());
			preparedStatement.setInt(5, mission.getVersion_contrat());
			preparedStatement.setString(6, mission.getCode_tva());
			preparedStatement.setFloat(7, mission.getPrix_ht());
			preparedStatement.setInt(8, mission.getNbr_facture());
			preparedStatement.setString(9, mission.getType_prix());
			preparedStatement.setString(10, mission.getCommentaire());
			preparedStatement.setString(11, mission.getDate_demarrage());
			preparedStatement.setString(12, "MissionDao");
			preparedStatement.setString(13, dateTime);
			preparedStatement.setString(14, System.getProperty("user.name"));
			int  rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 0) {
				throw new DaoException("Insertion dans mission a échoué, aucune ligne affectée.");
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
	public void modifierMission(Mission mission, String cheminRelatif_AvantModif, String cheminAbsolu_avantModif, String document_avantModif ) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try {
			connexion = daoFactory.getConnection();

			preparedStatement = connexion.prepareStatement(
					"UPDATE mission SET nom=?, complement=?, statut=?, id_contrat=?, version_contrat=?, code_tva=?, prix_ht=?, nbr_facture=?, type_prix=?, mission.commentaire=?, "
							+ "mission.date_demarrage=?, mission.pgmmodification=?, mission.datemodification=?, mission.usermodification=? where mission.id=? ;");
			preparedStatement.setString(1, mission.getNom());
			preparedStatement.setString(2, mission.getComplement());
			preparedStatement.setString(3, mission.getStatut());
			preparedStatement.setInt(4, mission.getId_contrat());
			preparedStatement.setInt(5, mission.getVersion_contrat());
			preparedStatement.setString(6, mission.getCode_tva());
			preparedStatement.setFloat(7, mission.getPrix_ht());
			preparedStatement.setInt(8, mission.getNbr_facture());
			preparedStatement.setString(9, mission.getType_prix());
			preparedStatement.setString(10, mission.getCommentaire());
			preparedStatement.setString(11, mission.getDate_demarrage());
			preparedStatement.setString(12, "MissionDao");
			preparedStatement.setString(13, dateTime);
			preparedStatement.setString(14, System.getProperty("user.name"));
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DaoException("Impossible de maj enregistrement avec la table missiont" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de maj enregistrement avec la table missiont" + e);
			}
		}
	}

	// =================================================================================
	// CRUD COPIER ENREGISTREMENT
	// =================================================================================
	@Override
	public void copierMission(Mission mission, String cheminRelatif_AvantModif, String cheminAbsolu_avantModif, String document_avantModif, String type_entite) throws DaoException {
		int lastid = 0;
		// ---------------------- BLOC CONNECTION ----------------------
		Connection connection = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement= null;
		try {
			connection = daoFactory.getConnection();
			connection.setAutoCommit(false); // Début de la transaction

			// ------ On crée les données mission dans mission ------ //
			preparedStatement = connection.prepareStatement(
					"INSERT INTO mission(nom, complement, statut, id_contrat, version_contrat, code_tva, prix_ht, nbr_facture, type_prix, pgmcreation, datecreation, usercreation ) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			preparedStatement.setString(1, mission.getNom());
			preparedStatement.setString(2, mission.getComplement());
			preparedStatement.setString(3, mission.getStatut());
			preparedStatement.setInt(4, mission.getId_contrat());
			preparedStatement.setInt(5, mission.getVersion_contrat());
			preparedStatement.setString(6, mission.getCode_tva());
			preparedStatement.setFloat(7, mission.getPrix_ht());
			preparedStatement.setInt(8, mission.getNbr_facture());
			preparedStatement.setString(9, mission.getType_prix());
			preparedStatement.setString(10, mission.getCommentaire());
			preparedStatement.setString(11, "MissionDao");
			preparedStatement.setString(12, dateTime);
			preparedStatement.setString(13, System.getProperty("user.name"));
			int  rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 0) {
				throw new DaoException("Insertion dans mission a échoué, aucune ligne affectée.");
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
	public void supprimerMission(Integer id) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM mission WHERE ID=?;");
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();
			connexion.commit();

		} catch (SQLException e) {
			throw new DaoException("Impossible de supprimer enregistrement avec la table missiont" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de supprimer enregistrement avec la table missiont" + e);
			}
		}

	}

	// =================================================================================
	// ANNULER CONTRATCLIENT
	// =================================================================================
	@Override
	public void annulerMission(Mission mission) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();

			preparedStatement = connexion.prepareStatement(
					"UPDATE mission SET mission.statut=?, mission.pgmmodification=?, mission.datemodification=?,"
							+ " mission.usermodification=? where mission.id=? ");
			preparedStatement.setString(1, "Annulé");
			preparedStatement.setString(2, "ANNULATION");
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
			throw new DaoException("Impossible d'annuler enregistrement avec la table missiont" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible d'annuler enregistrement avec la table missiont" + e);
			}
		}
	}
	// =================================================================================
	// TERMINER CONTRATCLIENT
	// =================================================================================
	@Override
	public void terminerMission(Mission mission) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();

			preparedStatement = connexion.prepareStatement(
					"UPDATE mission SET mission.statut=?, mission.pgmmodification=?, mission.datemodification=?,"
							+ " mission.usermodification=? where mission.id=?;");
			preparedStatement.setString(1, "Terminé");
			preparedStatement.setString(2, "FIN CONTRAT");
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
			throw new DaoException("Impossible de supprimer enregistrement avec la table missiont" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de supprimer enregistrement avec la table missiont" + e);
			}
		}
	}

		// =================================================================================
		// RENOMMER CONTRATCLIENT
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
				preparedStatement.setString(2, "FIN MISSION");
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
				throw new DaoException("Impossible de supprimer enregistrement avec la table missiont" + e);
			} finally {
				try {
					if (connexion != null) {
						connexion.close();
					}
				} catch (SQLException e) {
					throw new DaoException("Impossible de supprimer enregistrement avec la table missiont" + e);
				}
			}
		}

	// ================================================================================
	// ====LISTER LES ENREGISTREMENTS
	// ================================================================================
	@Override
	public List<Mission> listerMission() throws DaoException {
		List<Mission> missions = new ArrayList<Mission>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet rs = null;
		Mission mission;
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			rs = statement.executeQuery("SELECT * FROM mission;");

			while (rs.next()) {
				mission = new Mission();
				mission.setId(rs.getInt("id"));
				mission.setNom(rs.getString("nom"));
				mission.setComplement(rs.getString("complement"));
				mission.setStatut(rs.getString("statut"));
				mission.setId_contrat(rs.getInt("id_contrat"));
				mission.setVersion_contrat(rs.getInt("version_contrat"));
				mission.setCode_tva(rs.getString("code_tva"));
				mission.setPrix_ht(rs.getFloat("prix"));
				mission.setNbr_facture(rs.getInt("nbr_facture"));
				mission.setType_prix(rs.getString("type_prix"));
				mission.setSociete(rs.getString("societe"));
				mission.setType_entite(rs.getString("type_entite"));
				mission.setType_contrat(rs.getString("type_contrat"));
				missions.add(mission);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de lister enregistrement avec la table missiont" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de lister enregistrement avec la table missiont" + e);
			}
		}
		return missions;
	}



	// ====CRUD LIRE UN ENREGISTREMENT SPECIFIQUE VIA SON ID_CONTRAT ET
	// ID_AVENANT====================================
	@Override
	public Mission trouverId(int id, String type_entite) throws DaoException {
		Mission mission = new Mission();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"Select SQL_CALC_FOUND_ROWS  mission.id, nom, complement, mission.statut, id_contrat, version_contrat, mission.code_tva, prix_ht, nbr_facture, type_prix, societe.raison_sociale, contrat.type_entite, contrat.type_contrat, mission.commentaire from mission "
					+ "	inner join contrat on mission.id_contrat=contrat.id and mission.version_contrat=contrat.version inner join societe on contrat.id_client= societe.id "
					+ " where societe.type='" + type_entite + "' " + "and mission.id=?  ");

			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();
			connexion.commit();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if (rs.next()) {
				mission.setId(rs.getInt(1));
				mission.setNom(rs.getString(2));
				mission.setComplement(rs.getString(3));
				mission.setStatut(rs.getString(4));
				mission.setId_contrat(rs.getInt(5));
				mission.setVersion_contrat(rs.getInt(6));
				mission.setCode_tva(rs.getString(7));
				mission.setPrix_ht(rs.getFloat(8));
				mission.setNbr_facture(rs.getInt(9));
				mission.setType_prix(rs.getString(10));
				mission.setSociete(rs.getString(11));
				mission.setType_entite(rs.getString(12));
				mission.setType_contrat(rs.getString(13));
				mission.setCommentaire(rs.getString(14));
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de trouver id enregistrement avec la table missiont" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException(
						"Impossible de trouver id enregistrement avec la table missiont" + e);
			}
		}
		return mission;
	}


	// ==================================================================
	// ====rechercher et lister les enregistrements
	// ==================================================================
	public List<Mission> rechercheMissions(int offset, int noOfRecords, String select_tri,
			String tag_statut, String type_entite) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		String tag_where_statut = "";
		if (! tag_statut.equals("Tout"))
		tag_where_statut = "and mission.statut='"
				+ tag_statut + "' " ;

		String query2 = "select SQL_CALC_FOUND_ROWS  mission.id, nom, complement, mission.statut, id_contrat, version_contrat, mission.code_tva, prix_ht, nbr_facture, type_prix, societe.raison_sociale, contrat.type_entite, contrat.type_contrat from mission  "
				+ "inner join contrat on mission.id_contrat=contrat.id and mission.version_contrat=contrat.version inner join societe on contrat.id_client= societe.id "
				+ tag_where_statut + " ORDER BY " + select_tri + " limit " + offset + ", " + noOfRecords;
		List<Mission> list = new ArrayList<Mission>();
		Mission mission = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query2);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				mission = new Mission();
				mission.setId(rs.getInt(1));
				mission.setNom(rs.getString(2));
				mission.setComplement(rs.getString(3));
				mission.setStatut(rs.getString(4));
				mission.setId_contrat(rs.getInt(5));
				mission.setVersion_contrat(rs.getInt(6));
				mission.setCode_tva(rs.getString(7));
				mission.setPrix_ht(rs.getFloat(8));
				mission.setNbr_facture(rs.getInt(9));
				mission.setType_prix(rs.getString(10));
				mission.setSociete(rs.getString(11));
				mission.setType_entite(rs.getString(12));
				mission.setType_contrat(rs.getString(13));
				list.add(mission);
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
	public List<Mission> rechercheLikeMissions(int offset, int noOfRecords, String select_tri,
			String select_like, String tag_statut, String type_entite) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String tag_where_statut = "";
		if (! tag_statut.equals("Tout"))
		tag_where_statut = "and mission.statut='"
				+ tag_statut + "' " ;

		String query2 = "select SQL_CALC_FOUND_ROWS  mission.id, nom, complement, mission.statut, id_contrat, version_contrat, "
				+ "mission.code_tva, prix_ht, nbr_facture, type_prix, societe.raison_sociale, contrat.type_entite, contrat.type_contrat from mission  "
				+ "inner join contrat on mission.id_contrat=contrat.id and mission.version_contrat=contrat.version inner join societe on contrat.id_client= societe.id "
				+ tag_where_statut + " and " + select_like + " ORDER BY " + select_tri + " limit " + offset + ", "
				+ noOfRecords;
		List<Mission> list = new ArrayList<Mission>();
		Mission mission = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query2);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				mission = new Mission();
				mission.setId(rs.getInt(1));
				mission.setNom(rs.getString(2));
				mission.setComplement(rs.getString(3));
				mission.setStatut(rs.getString(4));
				mission.setId_contrat(rs.getInt(5));
				mission.setVersion_contrat(rs.getInt(6));
				mission.setCode_tva(rs.getString(7));
				mission.setPrix_ht(rs.getFloat(8));
				mission.setNbr_facture(rs.getInt(9));
				mission.setType_prix(rs.getString(10));
				mission.setSociete(rs.getString(11));
				mission.setType_entite(rs.getString(12));
				mission.setType_contrat(rs.getString(13));
				list.add(mission);
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
				String sql = "select * from facture_vente_service left join mission on facture_vente_service.id_mission=mission.id where mission.id=?";
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
