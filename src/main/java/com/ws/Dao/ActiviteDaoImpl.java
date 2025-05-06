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

import com.ws.beans.Activite;

public class ActiviteDaoImpl implements ActiviteDao {
	private DaoFactory daoFactory;
	private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
	private int noOfRecords;


	ActiviteDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	// =================================================================================
	// AJOUTER ACTIVITE
	// =================================================================================
	@Override
	public void ajouterActivite(Activite activite) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion  = daoFactory.getConnection();
			connexion.setAutoCommit(false);
			String sql = "INSERT INTO activite(nom, libelle, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?);";
			preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setString(1, activite.getNom());
			preparedStatement.setString(2, activite.getLibelle());
			preparedStatement.setString(3, "ActiviteDao");
			preparedStatement.setString(4, dateTime);
			preparedStatement.setString(5, System.getProperty("user.name"));
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
			throw new DaoException("Impossible d'ajouter l'enregistrement avec la table Activite"+ e);
		} finally {
			closeResources(connexion, preparedStatement, null);
		}
	}

	// =================================================================================
	// MODIFIER ACTIVITE
	// =================================================================================
	@Override
	public void modifierActivite(Activite activite) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false);
			String sql = "UPDATE activite SET nom=?, libelle=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
			preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setString(1, activite.getNom());
			preparedStatement.setString(2, activite.getLibelle());
			preparedStatement.setString(3, "ActiviteDao");
			preparedStatement.setString(4, dateTime);
			preparedStatement.setString(5, System.getProperty("user.name"));
			preparedStatement.setInt(6, activite.getId());
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
			throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table Activite"+ e);
		} finally {
			closeResources(connexion, preparedStatement, null);
		}
	}

	// =================================================================================
	// COPIER ACTIVITE
	// =================================================================================
	@Override
	public void copierActivite(Activite activite) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false);
			String sql = "INSERT INTO activite(nom, libelle, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?);";
			preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setString(1, activite.getNom());
			preparedStatement.setString(2, activite.getLibelle());
			preparedStatement.setString(3, "ActiviteDao");
			preparedStatement.setString(4, dateTime);
			preparedStatement.setString(5, System.getProperty("user.name"));
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
			throw new DaoException("Impossible de copier l'enregistrement avec la table Activite"+ e);
		} finally {
			closeResources(connexion, preparedStatement, null);
		}
	}

	// =================================================================================
	// SUPPRIMER ACTIVITE
	// =================================================================================

	@Override
	public void supprimerActivite(Integer id) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			String sql = "DELETE FROM activite WHERE ID=?;";
			preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			throw new DaoException("Impossible de supprimer l'enregistrement avec la table Activite"+ e);
		} finally {
			closeResources(connexion, preparedStatement, null);
		}
	}


	// =================================================================================
	// RENOMMER ACTIVITE
	// =================================================================================
	@Override
	public void renommerActivite(Activite activite) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();

			preparedStatement = connexion.prepareStatement(
					"UPDATE activite SET activite.nom=?, activite.pgmmodification=?, activite.datemodification=?,"
							+ " activite.usermodification=? where activite.id=?;");
			preparedStatement.setString(1, activite.getNom());
			preparedStatement.setString(2, "RENOMMER ACTIVITE");
			preparedStatement.setString(3, dateTime);
			preparedStatement.setString(4, System.getProperty("user.name"));
			preparedStatement.setInt(5, activite.getId());
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
	public List<Activite> listerActivite() throws DaoException {
		List<Activite> activites = new ArrayList<>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			String sql = "SELECT id, nom, libelle FROM activite;";
			resultat = statement.executeQuery(sql);
			while (resultat.next()) {
				Activite activite = new Activite();
				activite.setId(resultat.getInt("id"));
				activite.setNom(resultat.getString("nom"));
				activite.setLibelle(resultat.getString("libelle"));
				activites.add(activite);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de lister les enregistrements avec la table Activite"+ e);
		} finally {
			closeResources(connexion, statement, resultat);
		}
		return activites;
	}

	@Override
	public List<Activite> listerActiviteClient() throws DaoException {
		return listerActivite();
	}

	@Override
	public List<Activite> listerActiviteFournisseur() throws DaoException {
		return listerActivite();
	}

	@Override
	public List<Activite> listerActiviteSalarie() throws DaoException {
		return listerActivite();
	}
	@Override
	public List<Activite> listerActiviteInterne() throws DaoException {
		return listerActivite();
	}
	// =================================================================================
	// TROUVER ACTIVITE PAR ID
	// =================================================================================
	@Override
	public Activite trouverActivite(Integer id) throws DaoException {
		Activite activite = null;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			String sql = "SELECT * FROM activite WHERE ID=?;";
			preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				activite = new Activite();
				activite.setId(id);
				activite.setNom(resultat.getString("nom"));
				activite.setLibelle(resultat.getString("libelle"));
				activite.setUsermodification(resultat.getString("usermodification"));
				activite.setDatemodification(resultat.getDate("datemodification"));
				activite.setPgmmodification(resultat.getString("pgmmodification"));
				activite.setUsercreation(resultat.getString("usercreation"));
				activite.setDatecreation(resultat.getDate("datecreation"));
				activite.setPgmcreation(resultat.getString("pgmcreation"));
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de trouver l'enregistrement avec la table Activite"+ e);
		} finally {
			closeResources(connexion, preparedStatement, resultat);
		}
		return activite;
	}

	// =================================================================================
	// TROUVER ACTIVITE PAR NOM
	// =================================================================================
	@Override
	public boolean trouverNomActivite(String nom) throws DaoException {
		boolean existe = false;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			String sql = "SELECT * FROM activite WHERE nom=?;";
			preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setString(1, nom);
			resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				existe = true;
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de vérifier l'existence du nom dans la table Activite"+ e);
		} finally {
			closeResources(connexion, preparedStatement, resultat);
		}
		return existe;
	}



	// =================================================================================
	// RECHERCHE ACTIVITE
	// =================================================================================
	@Override
	public List<Map<String, Object>> rechercheActivites(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
		List<Map<String, Object>> list = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connexion = daoFactory.getConnection();
			String query = "SELECT SQL_CALC_FOUND_ROWS * FROM activite ORDER BY " + select_tri + " LIMIT ?, ?";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, offset);
			preparedStatement.setInt(2, noOfRecords);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				Map<String, Object> activiteFields = new LinkedHashMap<>();
				if (dictionnaire_nom_colonne.containsKey("id")) {
					activiteFields.put("id", rs.getInt("id"));
				}
				if (dictionnaire_nom_colonne.containsKey("nom")) {
					activiteFields.put("nom", rs.getString("nom"));
				}
				if (dictionnaire_nom_colonne.containsKey("libelle")) {
					activiteFields.put("libelle", rs.getString("libelle"));
				}
				list.add(activiteFields);
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
	public List<Map<String, Object>>  rechercheLikeActivites(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
		List<Map<String, Object>> list = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connexion = daoFactory.getConnection();
			String query = "SELECT SQL_CALC_FOUND_ROWS * FROM activite WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, offset);
			preparedStatement.setInt(2, noOfRecords);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Map<String, Object> activiteFields = new LinkedHashMap<>();
				if (dictionnaire_nom_colonne.containsKey("id")) {
					activiteFields.put("id", rs.getInt("id"));
				}
				if (dictionnaire_nom_colonne.containsKey("nom")) {
					activiteFields.put("nom", rs.getString("nom"));
				}
				if (dictionnaire_nom_colonne.containsKey("libelle")) {
					activiteFields.put("libelle", rs.getString("libelle"));
				}
				list.add(activiteFields);
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
	// RECUPERATION N° ENREGISTREMENT ENCOURS ACTIVITE
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
	// FERMETURE DES RESSOURCES ACTIVITE
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