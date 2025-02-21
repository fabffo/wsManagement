/////////////////////////////////////////////////////////////////////////////

////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO IMPLEMENTATION CALENDRIER                           ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ws.beans.Calendrier;
import com.ws.beans.CalendrierMois;
import com.ws.beans.CalendrierMoisGlobal;

public class CalendrierDaoImpl implements CalendrierDao {
	private DaoFactory daoFactory;
	// date du jour
	String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());

	CalendrierDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	// CRUD CREER
	@Override
	public void ajouterCalendrier(Calendrier calendrier) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"INSERT INTO calendrier(Date_cal, jour, mois, annee, nom_mois, nom_jour, annee_mois, type_jour, complement_jour, pgmcreation, datecreation, usercreation ) VALUES(?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?);");
			preparedStatement.setDate(1, (java.sql.Date) calendrier.getDate_cal());
			preparedStatement.setInt(2, calendrier.getJour());
			preparedStatement.setInt(3, calendrier.getMois());
			preparedStatement.setInt(4, calendrier.getAnnee());
			preparedStatement.setString(5, calendrier.getNom_mois());
			preparedStatement.setString(6, calendrier.getNom_jour());
			preparedStatement.setInt(7, calendrier.getAnnee_mois());
			preparedStatement.setString(8, calendrier.getType_jour());
			preparedStatement.setString(9, calendrier.getComplement_jour());
			preparedStatement.setString(10, "CalendrierDao");
			preparedStatement.setString(11, dateTime);
			preparedStatement.setString(12, System.getProperty("user.name"));
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DaoException("Impossible d'ajouter enregistrement avec la table Calendrier");
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible d'ajouter enregistrement avec la table Calendrier");
			}
		}

	}

	// LISTER LES ENREGISTREMENTS
	@Override
	public List<Calendrier> listerCalendrier() throws DaoException {
		List<Calendrier> calendriers = new ArrayList<Calendrier>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery(
					"SELECT id, Date_cal, jour, mois, annee, nom_mois, nom_jour, annee_mois, type_jour, complement_jour FROM calendrier;");

			while (resultat.next()) {
				int id_date = resultat.getInt("id");
				Date Date_cal = resultat.getDate("Date_cal");
				int jour = resultat.getInt("jour");
				int mois = resultat.getInt("mois");
				int annee = resultat.getInt("annee");
				String nom_mois = resultat.getString("nom_mois");
				String nom_jour = resultat.getString("nom_jour");
				int annee_mois = resultat.getInt("annee_mois");
				String type_jour = resultat.getString("type_jour");
				String complement_jour = resultat.getString("complement_jour");
				Calendrier calendrier = new Calendrier();
				calendrier.setId_date(id_date);
				calendrier.setDate_cal(Date_cal);
				calendrier.setJour(jour);
				calendrier.setMois(mois);
				calendrier.setAnnee(annee);
				calendrier.setNom_mois(nom_mois);
				calendrier.setNom_jour(nom_jour);
				calendrier.setAnnee_mois(annee_mois);
				calendrier.setType_jour(type_jour);
				calendrier.setComplement_jour(complement_jour);

				calendriers.add(calendrier);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de lister enregistrement avec la table Calendrier");
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de lister enregistrement avec la table Calendrier");
			}
		}
		return calendriers;
	}

	// CRUD LIRE UN ENREGISTREMENT SPECIFIQUE VIA SON ID
	@Override
	public Calendrier trouverCalendrier(Integer id_date) throws DaoException {
		Calendrier calendrier = new Calendrier();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT * FROM Calendrier WHERE ID_DATE=?;");
			preparedStatement.setInt(1, id_date);
			resultat = preparedStatement.executeQuery();
			connexion.commit();

			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if (resultat.next()) {
				Date Date_cal = resultat.getDate("Date_cal");
				Integer jour = resultat.getInt("jour");
				int mois = resultat.getInt("mois");
				int annee = resultat.getInt("annee");
				String nom_mois = resultat.getString("nom_mois");
				String nom_jour = resultat.getString("nom_jour");
				int annee_mois = resultat.getInt("annee_mois");
				String type_jour = resultat.getString("type_jour");
				String complement_jour = resultat.getString("complement_jour");
				String usermodification = resultat.getString("usermodification");
				Date datemodification = (Date) resultat.getDate("datemodification");
				String pgmmodification = resultat.getString("pgmmodification");
				String usercreation = resultat.getString("usercreation");
				Date datecreation = (Date) resultat.getDate("datecreation");
				String pgmcreation = resultat.getString("pgmcreation");

				calendrier.setId_date(id_date);
				calendrier.setDate_cal(Date_cal);
				calendrier.setJour(jour);
				calendrier.setMois(mois);
				calendrier.setAnnee(annee);
				calendrier.setNom_mois(nom_mois);
				calendrier.setNom_jour(nom_jour);
				calendrier.setAnnee_mois(annee_mois);
				calendrier.setType_jour(type_jour);
				calendrier.setComplement_jour(complement_jour);
				calendrier.setPgmmodification(pgmmodification);
				calendrier.setUsermodification(usermodification);
				calendrier.setDatemodification(datemodification);
				calendrier.setPgmcreation(pgmcreation);
				calendrier.setUsercreation(usercreation);
				calendrier.setDatecreation(datecreation);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de trouver id_date enregistrement avec la table Calendrier"
);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de trouver id_date enregistrement avec la table Calendrier" );
			}
		}

		return calendrier;
	}

	// EXISTENCE D'UN ENREGISTREMENT SPECIFIQUE VIA SON Date_cal
	@Override
	public boolean trouverDateCalendrier(Date date_cal) throws DaoException {
		boolean existe = false;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT * FROM Calendrier WHERE Date_cal=?;");
			preparedStatement.setDate(1, (java.sql.Date) date_cal);
			resultat = preparedStatement.executeQuery();
			connexion.commit();

			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if (resultat.next()) {
				existe = true;
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de communiquer avec la table Calendrier" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de communiquer avec la Calendrier" + e);
			}
		}

		return existe;
	}

	// CRUD MODIFIER ENREGISTREMENT
	@Override
	public void modifierCalendrier(Calendrier calendrier) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"UPDATE calendrier SET Date_cal=?, jour=?, mois=?, annee=?, nom_mois=?, nom_jour=?, annee_mois=?, type_jour=?, complement_jour=?, pgmmodification=?, datemodification=?, usermodification=? where id=?;");
			preparedStatement.setInt(13, calendrier.getId_date());
			preparedStatement.setDate(1, (java.sql.Date) calendrier.getDate_cal());
			preparedStatement.setInt(2, calendrier.getJour());
			preparedStatement.setInt(3, calendrier.getMois());
			preparedStatement.setInt(4, calendrier.getAnnee());
			preparedStatement.setString(5, calendrier.getNom_mois());
			preparedStatement.setString(6, calendrier.getNom_jour());
			preparedStatement.setInt(7, calendrier.getAnnee_mois());
			preparedStatement.setString(8, calendrier.getType_jour());
			preparedStatement.setString(9, calendrier.getComplement_jour());
			preparedStatement.setString(10, "CalendrierDao");
			preparedStatement.setString(11, dateTime);
			preparedStatement.setString(12, System.getProperty("user.name"));
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DaoException("Impossible de maj enregistrement avec la table Calendrier" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de maj enregistrement avec la table Calendrier" + e);
			}
		}
	}

	// CRUD COPIER ENREGISTREMENT
	@Override
	public void copierCalendrier(Calendrier calendrier) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"INSERT INTO calendrier(date_cal, jour, mois, annee, nom_mois, nom_jour, annee_mois, type_jour, complement_jour, pgmcreation, datecreation, usercreation ) VALUES(?, ?, ?, ?,?);");
			preparedStatement.setDate(1, (java.sql.Date) calendrier.getDate_cal());
			preparedStatement.setInt(2, calendrier.getJour());
			preparedStatement.setInt(3, calendrier.getMois());
			preparedStatement.setInt(4, calendrier.getAnnee());
			preparedStatement.setString(5, calendrier.getNom_mois());
			preparedStatement.setString(6, calendrier.getNom_jour());
			preparedStatement.setInt(7, calendrier.getAnnee_mois());
			preparedStatement.setString(8, calendrier.getType_jour());
			preparedStatement.setString(9, calendrier.getComplement_jour());
			preparedStatement.setString(10, "CalendrierDao");
			preparedStatement.setString(11, dateTime);
			preparedStatement.setString(12, System.getProperty("user.name"));
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DaoException("Impossible de maj enregistrement avec la table Calendrier" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de maj enregistrement avec la table Calendrier" + e);
			}
		}
	}

	// CRUD SUPPRIMER UN ENREGISTREMENT
	@Override
	public void supprimerCalendrier(Integer id) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM Calendrier WHERE ID=?;");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			connexion.commit();

		} catch (SQLException e) {
			throw new DaoException("Impossible de supprimer enregistrement avec la table Calendrier" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de supprimer enregistrement avec la table Calendrier" + e);
			}
		}

	}

	public List<Object> viewAllCalendriers(int annee, int mois) {

		int w_jours_ouvres =0;
		int w_jours_non_ouvres =0;
		int w_jours_feries =0;

		int num_semaine = 0;
		String nom_jour = null;
		int jour = 0;
		String type_jour = null;
		String complement_jour = null;
		String w_type_jour = null;
		String w_jour = null;
		String w_classe = null;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query = "select num_semaine, nom_jour, jour, type_jour, complement_jour from calendrier where annee="
				+ annee + " and mois=" + mois + ";";

		List<CalendrierMois> list = new ArrayList<CalendrierMois>();
		CalendrierMois calendrierMois = null;
		List<CalendrierMoisGlobal> listG = new ArrayList<CalendrierMoisGlobal>();
		CalendrierMoisGlobal calendrierMoisGlobal = null;
		List<Object> listeObject = new ArrayList<>();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);

			rs = preparedStatement.executeQuery();
			calendrierMoisGlobal = new CalendrierMoisGlobal();
			while (rs.next()) {

				// on instancie le calendrier la 1ère fois
				if (num_semaine == (0)) {
					calendrierMois = new CalendrierMois();
				} else {
					// quand on change de semaine on ajoute une instance à la liste
					// et on instancie de nouveau
					if (num_semaine != (rs.getInt(1))) {
						list.add(calendrierMois);
						listeObject.add(calendrierMois);
						calendrierMois = new CalendrierMois();
					} else {
					}
				}


				num_semaine = rs.getInt(1);
				nom_jour = rs.getString(2);
				jour = rs.getInt(3);
				type_jour = rs.getString(4);
				complement_jour = rs.getString(5);

				if(type_jour.equals("OUVERT")) {
					w_type_jour = "";
					w_classe = " text-info";
					w_jours_ouvres = w_jours_ouvres + 1;
				}
				else {
					w_type_jour = type_jour;
					w_classe = " text-secondary";
					w_jours_non_ouvres = w_jours_non_ouvres + 1;
				}
				if(jour==0) {
					w_jour = "";
				}
				else {
					w_jour = String.valueOf(jour);
				}


				switch (nom_jour) {

				case "LUNDI":
					calendrierMois.setClasse_lundi(w_classe);
					if (complement_jour.equals(null)) {
						calendrierMois.setValeur_lundi(w_jour + " "+ w_type_jour);
					} else if (complement_jour.equals("")) {
						calendrierMois.setValeur_lundi(w_jour + " "+ w_type_jour);
					} else{
						calendrierMois.setValeur_lundi(w_jour + " "+ complement_jour);
						if (complement_jour.equals("WEEK-END")) {

						} else{
						w_jours_feries = w_jours_feries + 1;
						}
					}

					break;
				case "MARDI":
					calendrierMois.setClasse_mardi(w_classe);
					if (complement_jour.equals(null)) {
						calendrierMois.setValeur_mardi(w_jour + " "+ w_type_jour);
					} else if (complement_jour.equals("")) {
						calendrierMois.setValeur_mardi(w_jour + " "+ w_type_jour);
					} else{
						calendrierMois.setValeur_mardi(w_jour + " "+ complement_jour);
						if (complement_jour.equals("WEEK-END")) {

						} else{
						w_jours_feries = w_jours_feries + 1;
						}
					}
					break;
				case "MERCREDI":
					calendrierMois.setClasse_mercredi(w_classe);
					if (complement_jour.equals(null)) {
						calendrierMois.setValeur_mercredi(w_jour + " "+ w_type_jour);
					} else if (complement_jour.equals("")) {
						calendrierMois.setValeur_mercredi(w_jour + " "+ w_type_jour);
					} else{
						calendrierMois.setValeur_mercredi(w_jour + " "+ complement_jour);
						if (complement_jour.equals("WEEK-END")) {

						} else{
						w_jours_feries = w_jours_feries + 1;
						}
					}
					break;
				case "JEUDI":
					calendrierMois.setClasse_jeudi(w_classe);
					if (complement_jour.equals(null)) {
						calendrierMois.setValeur_jeudi(w_jour + " "+ w_type_jour);
					} else if (complement_jour.equals("")) {
						calendrierMois.setValeur_jeudi(w_jour + " "+ w_type_jour);
					} else{
						calendrierMois.setValeur_jeudi(w_jour + " "+ complement_jour);
						if (complement_jour.equals("WEEK-END")) {

						} else{
						w_jours_feries = w_jours_feries + 1;
						}
					}
					break;
				case "VENDREDI":
					calendrierMois.setClasse_vendredi(w_classe);
					if (complement_jour.equals(null)) {
						calendrierMois.setValeur_vendredi(w_jour + " "+ w_type_jour);
					} else if (complement_jour.equals("")) {
						calendrierMois.setValeur_vendredi(w_jour + " "+ w_type_jour);
					} else{
						calendrierMois.setValeur_vendredi(w_jour + " "+ complement_jour);
						if (complement_jour.equals("WEEK-END")) {

						} else{
						w_jours_feries = w_jours_feries + 1;
						}
					}
					break;
				case "SAMEDI":
					calendrierMois.setClasse_samedi(w_classe);
					if (complement_jour.equals(null)) {
						calendrierMois.setValeur_samedi(w_jour + " "+ w_type_jour);
					} else if (complement_jour.equals("")) {
						calendrierMois.setValeur_samedi(w_jour + " "+ w_type_jour);
					} else{
						calendrierMois.setValeur_samedi(w_jour + " "+ complement_jour);
						if (complement_jour.equals("WEEK-END")) {

						} else{
						w_jours_feries = w_jours_feries + 1;
						}
					}
					break;
				case "DIMANCHE":
					calendrierMois.setClasse_dimanche(w_classe);
					if (complement_jour.equals(null)) {
						calendrierMois.setValeur_dimanche(w_jour + " "+ w_type_jour);
					} else if (complement_jour.equals("")) {
						calendrierMois.setValeur_dimanche(w_jour + " "+ w_type_jour);
					} else{
						calendrierMois.setValeur_dimanche(w_jour + " "+ complement_jour);
						if (complement_jour.equals("WEEK-END")) {

						} else{
						w_jours_feries = w_jours_feries + 1;
						}
					}

				}

			}
			// si au moins un enregistement alors on crée la liste
			if (num_semaine != (0)) {

				calendrierMoisGlobal.setJours_feries(w_jours_feries);
				calendrierMoisGlobal.setJours_non_ouvres(w_jours_non_ouvres);
				calendrierMoisGlobal.setJours_ouvres(w_jours_ouvres);

				list.add(calendrierMois);
				listG.add(calendrierMoisGlobal);

				listeObject.add(calendrierMois);
				listeObject.add(calendrierMoisGlobal);

			}
			rs.close();
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
		return listeObject;
	}

	public List<Calendrier> rechercheCalendriers(int offset, int noOfRecords, String select_tri, String select_like) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query = "select SQL_CALC_FOUND_ROWS * from calendrier where " + select_like + " ORDER BY " + select_tri
				+ " limit " + offset + ", " + noOfRecords;
		List<Calendrier> list = new ArrayList<Calendrier>();
		Calendrier Calendrier = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Calendrier = new Calendrier();
				Calendrier.setId_date(rs.getInt(1));
				Calendrier.setDate_cal(rs.getDate(2));
				Calendrier.setJour(rs.getInt(3));
				Calendrier.setMois(rs.getInt(4));
				Calendrier.setAnnee(rs.getInt(5));
				Calendrier.setNom_mois(rs.getString(6));
				Calendrier.setNom_jour(rs.getString(7));
				Calendrier.setAnnee_mois(rs.getInt(8));
				Calendrier.setType_jour(rs.getString(9));
				Calendrier.setComplement_jour(rs.getString(10));
				list.add(Calendrier);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

}
