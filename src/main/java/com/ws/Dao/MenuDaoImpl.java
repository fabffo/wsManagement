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

import com.ws.beans.ContratAncien;
import com.ws.beans.Menu;
import com.ws.configuration.DatasourceH;

public class MenuDaoImpl implements MenuDao {
	private DaoFactory daoFactory;
	private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
	private int noOfRecords;


	MenuDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	// =================================================================================
	// AJOUTER ENTETE PARAMETETRE SYSTEME
	// =================================================================================
	@Override
	public void ajouterMenu(Menu menu) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion  = daoFactory.getConnection();
			connexion.setAutoCommit(false);
			String sql = "INSERT INTO menu(nom, url, parent_id, ordre, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?);";
			preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setString(1, menu.getNom());
			preparedStatement.setString(2, menu.getUrl());
			preparedStatement.setInt(3, menu.getParent_id());
			preparedStatement.setInt(4, menu.getOrdre());
			preparedStatement.setString(5, "ParametreSys_entete");
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
			throw new DaoException("Impossible d'ajouter l'enregistrement avec la table Menu"+ e);
		} finally {
			closeResources(connexion, preparedStatement, null);
		}
	}

	// =================================================================================
	// MODIFIER ENTETE PARAMETRE
	// =================================================================================
	@Override
	public void modifierMenu(Menu menu) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false);
			String sql = "UPDATE menu SET nom=?, url=?, parent_id=?, ordre=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
			preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setString(1, menu.getNom());
			preparedStatement.setString(2, menu.getUrl());
			preparedStatement.setInt(3, menu.getParent_id());
			preparedStatement.setInt(4, menu.getOrdre());
			preparedStatement.setString(5, "ParametreSys_entete");
			preparedStatement.setString(6, dateTime);
			preparedStatement.setString(7, System.getProperty("user.name"));
			preparedStatement.setInt(8, menu.getId());
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
			throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table Menu"+ e);
		} finally {
			closeResources(connexion, preparedStatement, null);
		}
	}

	// =================================================================================
	// COPIER ENTETE PARAMETRE
	// =================================================================================
	@Override
	public void copierMenu(Menu menu) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false);
			String sql = "INSERT INTO menu(nom, url, parent_id, ordre, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?);";
			preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setString(1, menu.getNom());
			preparedStatement.setString(2, menu.getUrl());
			preparedStatement.setInt(3, menu.getParent_id());
			preparedStatement.setInt(4, menu.getOrdre());
			preparedStatement.setString(5, "ParametreSys_entete");
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
			throw new DaoException("Impossible de copier l'enregistrement avec la table Menu"+ e);
		} finally {
			closeResources(connexion, preparedStatement, null);
		}
	}

	// =================================================================================
	// SUPPRIMER ENTETE PARAMETRE
	// =================================================================================

	@Override
	public void supprimerMenu(Integer id) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			String sql = "DELETE FROM menu WHERE ID=?;";
			preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			throw new DaoException("Impossible de supprimer l'enregistrement avec la table Menu"+ e);
		} finally {
			closeResources(connexion, preparedStatement, null);
		}
	}


	// =================================================================================
	// RENOMMER ENTETE PARAMETRE
	// =================================================================================
	@Override
	public void renommerMenu(Menu menu) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();

			preparedStatement = connexion.prepareStatement(
					"UPDATE menu SET menu.nom=?, menu.pgmmodification=?, menu.datemodification=?,"
							+ " menu.usermodification=? where menu.id=?;");
			preparedStatement.setString(1, menu.getNom());
			preparedStatement.setString(2, "ParametreSys_entete");
			preparedStatement.setString(3, dateTime);
			preparedStatement.setString(4, System.getProperty("user.name"));
			preparedStatement.setInt(5, menu.getId());
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
	// LISTER DES ENTETE PARAMETRES
	// =================================================================================
	@Override
	public List<Menu> listerMenu() throws DaoException {
		List<Menu> menus = new ArrayList<>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			String sql = "SELECT id, nom, url, parent_id, ordre, FROM menu;";
			resultat = statement.executeQuery(sql);
			while (resultat.next()) {
				Menu menu = new Menu();
				menu.setId(resultat.getInt("id"));
				menu.setNom(resultat.getString("nom"));
				menu.setUrl(resultat.getString("url"));
				menu.setParent_id(resultat.getInt("parent_id"));
				menu.setOrdre(resultat.getInt("ordre"));
				menus.add(menu);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de lister les enregistrements avec la table Menu"+ e);
		} finally {
			closeResources(connexion, statement, resultat);
		}
		return menus;
	}

	// =================================================================================
	// TROUVER ENTETE PARAMETRE PAR ID
	// =================================================================================
	@Override
	public Menu trouverMenu(Integer id) throws DaoException {
		Menu menu = null;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			String sql = "SELECT * FROM menu WHERE ID=?;";
			preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				menu = new Menu();
				menu.setId(resultat.getInt("id"));
				menu.setNom(resultat.getString("nom"));
				menu.setUrl(resultat.getString("url"));
				menu.setParent_id(resultat.getInt("parent_id"));
				menu.setOrdre(resultat.getInt("ordre"));
				menu.setUsermodification(resultat.getString("usermodification"));
				menu.setDatemodification(resultat.getDate("datemodification"));
				menu.setPgmmodification(resultat.getString("pgmmodification"));
				menu.setUsercreation(resultat.getString("usercreation"));
				menu.setDatecreation(resultat.getDate("datecreation"));
				menu.setPgmcreation(resultat.getString("pgmcreation"));
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de trouver l'enregistrement avec la table Menu"+ e);
		} finally {
			closeResources(connexion, preparedStatement, resultat);
		}
		return menu;
	}

	// =================================================================================
	// TROUVER ENTETE PARAMETRE PAR NOM
	// =================================================================================
	@Override
	public boolean trouverNomMenu(String nom) throws DaoException {
		boolean existe = false;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			String sql = "SELECT * FROM menu WHERE nom=?;";
			preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setString(1, nom);
			resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				existe = true;
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de vérifier l'existence du nom dans la table Menu"+ e);
		} finally {
			closeResources(connexion, preparedStatement, resultat);
		}
		return existe;
	}



	// =================================================================================
	// RECHERCHE ENTETE PARAMETRE
	// =================================================================================
	@Override
	public List<Map<String, Object>> rechercheMenus(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
		List<Map<String, Object>> list = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connexion = daoFactory.getConnection();
			String query = "SELECT SQL_CALC_FOUND_ROWS * FROM menu ORDER BY " + select_tri + " LIMIT ?, ?";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, offset);
			preparedStatement.setInt(2, noOfRecords);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Map<String, Object> entiteFields = new LinkedHashMap<>();
				if (dictionnaire_nom_colonne.containsKey("id")) {
					entiteFields.put("id", rs.getInt("id"));
				}
				if (dictionnaire_nom_colonne.containsKey("nom")) {
					entiteFields.put("nom", rs.getString("nom"));
				}
				if (dictionnaire_nom_colonne.containsKey("url")) {
					entiteFields.put("url", rs.getString("url"));
				}
				if (dictionnaire_nom_colonne.containsKey("parent_id")) {
					entiteFields.put("parent_id", rs.getInt("parent_id"));
				}
				if (dictionnaire_nom_colonne.containsKey("ordre")) {
					entiteFields.put("ordre", rs.getInt("ordre"));
				}
				list.add(entiteFields);
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
	// RECHERCHE ENTETE PARAMETRES SUIVANT LIKE
	// =================================================================================
	@Override
	public List<Map<String, Object>> rechercheLikeMenus(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
		List<Map<String, Object>> list = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connexion = daoFactory.getConnection();
			String query = "SELECT SQL_CALC_FOUND_ROWS * FROM menu WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, offset);
			preparedStatement.setInt(2, noOfRecords);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Map<String, Object> entiteFields = new LinkedHashMap<>();
				if (dictionnaire_nom_colonne.containsKey("id")) {
					entiteFields.put("id", rs.getInt("id"));
				}
				if (dictionnaire_nom_colonne.containsKey("nom")) {
					entiteFields.put("nom", rs.getString("nom"));
				}
				if (dictionnaire_nom_colonne.containsKey("url")) {
					entiteFields.put("url", rs.getString("url"));
				}
				if (dictionnaire_nom_colonne.containsKey("parent_id")) {
					entiteFields.put("parent_id", rs.getInt("parent_id"));
				}
				if (dictionnaire_nom_colonne.containsKey("ordre")) {
					entiteFields.put("ordre", rs.getInt("ordre"));
				}
				list.add(entiteFields);
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


	// =================================================================================
	// FERMETURE DES RESSOURCES ENTETE PARAMETRE
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