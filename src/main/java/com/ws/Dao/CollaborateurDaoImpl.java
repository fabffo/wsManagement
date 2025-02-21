/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO IMPLEMENTATION COLLABORATEUR                        ///
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
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ws.beans.Collaborateur;

public class CollaborateurDaoImpl implements CollaborateurDao {
	private DaoFactory daoFactory;
	// date du jour
	String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
	private int noOfRecords;
	Map<String, Integer> typeContratDictionary = new HashMap<>();
	Map<String, Integer> utilisateurDictionary = new HashMap<>();
	Map<String, Integer> metierDictionary = new HashMap<>();

	CollaborateurDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public Map<String, Integer> listerTypeContrat() {
		// liste des types de contrat disponibles
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query = "select libelle, id from typeContratCollaborateur";
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			int i = 0;
			while (rs.next()) {
				i = i + 1;
				typeContratDictionary.put(rs.getString(1), rs.getInt(2));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return typeContratDictionary;
	}

	public Map<String, Integer> listerUtilisateur() {
		// liste des types de contrat disponibles
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query = "select libelle, id from utilisateur";
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			int i = 0;
			while (rs.next()) {
				i = i + 1;
				utilisateurDictionary.put(rs.getString(1), rs.getInt(2));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utilisateurDictionary;
	}

	public Map<String, Integer> listerMetier() {
		// liste des types de contrat disponibles
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query = "select nom, id from metier";
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			int i = 0;
			while (rs.next()) {
				i = i + 1;
				metierDictionary.put(rs.getString(1), rs.getInt(2));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return metierDictionary;
	}

	// CRUD CREER
	@Override
	public void ajouterCollaborateur(Collaborateur collaborateur) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		listerTypeContrat();
		listerUtilisateur();
		listerMetier();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"INSERT INTO collaborateur(nom, prenom, adresse, code_postal, ville, pays, telephone, email, telephone_secondaire, email_secondaire, civilite, date_naissance, nationalite, id_type_contrat, type_collaborateur, signataire_wavy, id_utilisateur, metier_principal, pgmcreation, datecreation, usercreation ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

			preparedStatement.setString(1, collaborateur.getNom());
			preparedStatement.setString(2, collaborateur.getPrenom());
			preparedStatement.setString(3, collaborateur.getAdresse());
			preparedStatement.setString(4, collaborateur.getCode_postal());
			preparedStatement.setString(5, collaborateur.getVille());
			preparedStatement.setString(6, collaborateur.getPays());
			preparedStatement.setString(7, collaborateur.getTelephone());
			preparedStatement.setString(8, collaborateur.getEmail());
			preparedStatement.setString(9, collaborateur.getTelephone_secondaire());
			preparedStatement.setString(10, collaborateur.getEmail_secondaire());
			preparedStatement.setString(11, collaborateur.getCivilite());
			preparedStatement.setString(12, collaborateur.getDate_naissance());
			preparedStatement.setString(13, collaborateur.getNationalite());
			preparedStatement.setInt(14, typeContratDictionary.get(collaborateur.getType_contrat()));
			preparedStatement.setString(15, collaborateur.getType_collaborateur());
			preparedStatement.setInt(16, collaborateur.getSignataire_contrat());
			preparedStatement.setInt(17, utilisateurDictionary.get(collaborateur.getUtilisateur()));
			preparedStatement.setInt(18, metierDictionary.get(collaborateur.getMetier()));
			preparedStatement.setString(19, "CollaborateurDao");
			preparedStatement.setString(20, dateTime);
			preparedStatement.setString(21, System.getProperty("user.name"));
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DaoException("Impossible d'ajouter enregistrement avec la table collaborateurt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible d'ajouter enregistrement avec la table collaborateurt" + e);
			}
		}

	}

	// LISTER LES ENREGISTREMENTS
	@Override
	public List<Collaborateur> listerCollaborateur() throws DaoException {
		List<Collaborateur> collaborateurs = new ArrayList<Collaborateur>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM collaborateur;");

			while (resultat.next()) {
				int id = resultat.getInt("id");
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");
				int metier_principal = resultat.getInt("metier_principal");
				String type_collaborateur = resultat.getString("type_collaborateur");
				;
				String adresse = resultat.getString("adresse");
				;
				String code_postal = resultat.getString("code_postal");
				;
				String ville = resultat.getString("ville");
				;
				String pays = resultat.getString("pays");
				;
				String telephone = resultat.getString("telephone");
				;
				String email = resultat.getString("email");
				;
				String civilite = resultat.getString("civilite");
				;
				String date_naissance = resultat.getString("date_naissance");
				;
				int id_type_contrat = resultat.getInt("id_type_contrat");
				;
				int signataire_contrat = resultat.getInt("signataire_wavy");
				;
				int id_utilisateur = resultat.getInt("id_utilisateur");
				;
				String utilisateur = resultat.getString("utilisateur");
				;
				String telephone_secondaire = resultat.getString("telephone_secondaire");
				;
				String email_secondaire = resultat.getString("email_secondaire");
				;

				Collaborateur collaborateur = new Collaborateur();
				collaborateur.setId(id);
				collaborateur.setNom(nom);
				collaborateur.setPrenom(prenom);
				collaborateur.setType_collaborateur(type_collaborateur);
				collaborateur.setAdresse(adresse);
				collaborateur.setCode_postal(code_postal);
				collaborateur.setVille(ville);
				collaborateur.setPays(pays);
				collaborateur.setTelephone(telephone);
				collaborateur.setTelephone_secondaire(telephone_secondaire);
				collaborateur.setEmail(email);
				collaborateur.setEmail_secondaire(email_secondaire);
				collaborateur.setCivilite(civilite);
				collaborateur.setDate_naissance(date_naissance);
				collaborateur.setId_type_contrat(id_type_contrat);
				collaborateur.setSignataire_contrat(signataire_contrat);
				collaborateur.setId_utilisateur(id_utilisateur);

				// Récupération de l'activité
				Connection connexion2 = null;
				PreparedStatement preparedStatement2 = null;
				ResultSet resultat2 = null;
				/* Récupération d'une connexion depuis la Factory */
				connexion2 = daoFactory.getConnection();
				preparedStatement2 = connexion2.prepareStatement("SELECT * FROM metier WHERE ID=?;");
				preparedStatement2.setInt(1, metier_principal);
				resultat2 = preparedStatement2.executeQuery();
				connexion2.commit();
				/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
				if (resultat2.next()) {
					collaborateur.setMetier(resultat2.getString("nom"));
				}
				// Récupération du type utilisateur
				Connection connexion3 = null;
				PreparedStatement preparedStatement3 = null;
				ResultSet resultat3 = null;
				/* Récupération d'une connexion depuis la Factory */
				connexion3 = daoFactory.getConnection();
				preparedStatement3 = connexion2.prepareStatement("SELECT * FROM utilisateur WHERE ID=?;");
				preparedStatement3.setInt(1, id_utilisateur);
				resultat3 = preparedStatement2.executeQuery();
				connexion3.commit();
				/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
				if (resultat3.next()) {
					collaborateur.setUtilisateur(utilisateur);
				}
				collaborateurs.add(collaborateur);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de lister enregistrement avec la table collaborateurt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de lister enregistrement avec la table collaborateurt" + e);
			}
		}
		return collaborateurs;
	}

	// CRUD LIRE UN ENREGISTREMENT SPECIFIQUE VIA SON ID
	@Override
	public Collaborateur trouverCollaborateur(Integer id) throws DaoException {
		Collaborateur collaborateur = new Collaborateur();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"SELECT collaborateur.id, collaborateur.nom, collaborateur.prenom, collaborateur.adresse, collaborateur.code_postal, collaborateur.ville, collaborateur.pays, collaborateur.email, collaborateur.telephone, collaborateur.email_secondaire, collaborateur.telephone_secondaire, collaborateur.civilite, collaborateur.date_naissance, collaborateur.nationalite, collaborateur.id_type_contrat, typeContratCollaborateur.libelle, collaborateur.type_collaborateur, collaborateur.signataire_wavy, collaborateur.id_utilisateur, utilisateur.libelle, metier.id, metier.nom from collaborateur, metier, utilisateur, typeContratCollaborateur where collaborateur.metier_principal =metier.id and collaborateur.id_utilisateur = utilisateur.id and collaborateur.id_type_contrat =typeContratCollaborateur.id and collaborateur.id=?;");
			preparedStatement.setInt(1, id);
			resultat = preparedStatement.executeQuery();
			connexion.commit();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if (resultat.next()) {

				String nom = resultat.getString("collaborateur.nom");
				String prenom = resultat.getString("collaborateur.prenom");
				String adresse = resultat.getString("collaborateur.adresse");
				String code_postal = resultat.getString("collaborateur.code_postal");
				String ville = resultat.getString("collaborateur.ville");
				String pays = resultat.getString("collaborateur.pays");
				String telephone = resultat.getString("collaborateur.telephone");
				String email = resultat.getString("collaborateur.email");
				String telephone_secondaire = resultat.getString("collaborateur.telephone_secondaire");
				String email_secondaire = resultat.getString("collaborateur.email_secondaire");
				String civilite = resultat.getString("collaborateur.civilite");
				String date_naissance = resultat.getString("collaborateur.date_naissance");
				String nationalite = resultat.getString("collaborateur.nationalite");

				int id_type_contrat = resultat.getInt("id_type_contrat");
				String typeContratCollaborateur = resultat.getString("typeContratCollaborateur.libelle");

				String type_collaborateur = resultat.getString("collaborateur.type_collaborateur");

				int signataire_contrat = resultat.getInt("collaborateur.signataire_wavy");
				int id_utilisateur = resultat.getInt("collaborateur.id_utilisateur");
				String utilisateur = resultat.getString("utilisateur.libelle");

				int metier_principal = resultat.getInt("metier.id");
				String metier = resultat.getString("metier.nom");
				collaborateur.setId(id);
				collaborateur.setNom(nom);
				collaborateur.setPrenom(prenom);
				collaborateur.setAdresse(adresse);
				collaborateur.setCode_postal(code_postal);
				collaborateur.setVille(ville);
				collaborateur.setPays(pays);
				collaborateur.setTelephone(telephone);
				collaborateur.setTelephone_secondaire(telephone_secondaire);
				collaborateur.setEmail(email);
				collaborateur.setEmail_secondaire(email_secondaire);
				collaborateur.setCivilite(civilite);
				collaborateur.setDate_naissance(date_naissance);
				collaborateur.setNationalite(nationalite);
				collaborateur.setId_type_contrat(id_type_contrat);
				collaborateur.setType_contrat(typeContratCollaborateur);
				collaborateur.setType_collaborateur(type_collaborateur);
				collaborateur.setSignataire_contrat(signataire_contrat);
				collaborateur.setId_utilisateur(id_utilisateur);
				collaborateur.setUtilisateur(utilisateur);
				collaborateur.setMetier_principal(metier_principal);
				collaborateur.setMetier(metier);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de trouver id enregistrement avec la table collaborateurt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de trouver id enregistrement avec la table collaborateurt" + e);
			}
		}
		return collaborateur;
	}

	// EXISTENCE D'UN ENREGISTREMENT SPECIFIQUE VIA SON NOM
	@Override
	public boolean trouverNomCollaborateur(String nom) throws DaoException {
		boolean existe = false;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT * FROM COLLABORATEUR WHERE nom=?;");
			preparedStatement.setString(1, nom);
			resultat = preparedStatement.executeQuery();
			connexion.commit();

			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if (resultat.next()) {
				existe = true;
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de communiquer avec la table collaborateurt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de communiquer avec la collaborateurt" + e);
			}
		}

		return existe;
	}

	// CRUD MODIFIER ENREGISTREMENT
	@Override
	public void modifierCollaborateur(Collaborateur collaborateur) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		listerTypeContrat();
		listerUtilisateur();
		listerMetier();

		try {
			connexion = daoFactory.getConnection();

			preparedStatement = connexion.prepareStatement(
					"UPDATE collaborateur SET nom=?, prenom=?, adresse=?, code_postal=?, ville=?, pays=?, telephone=?, email=?, telephone_secondaire=?, email_secondaire=?, civilite=?, date_naissance=?, nationalite=?, id_type_contrat=?, type_collaborateur=?, signataire_wavy=?, id_utilisateur=?, metier_principal=? where id=?;");
			preparedStatement.setInt(19, collaborateur.getId());
			preparedStatement.setString(1, collaborateur.getNom());
			preparedStatement.setString(2, collaborateur.getPrenom());
			preparedStatement.setString(3, collaborateur.getAdresse());
			preparedStatement.setString(4, collaborateur.getCode_postal());
			preparedStatement.setString(5, collaborateur.getVille());
			preparedStatement.setString(6, collaborateur.getPays());
			preparedStatement.setString(7, collaborateur.getTelephone());
			preparedStatement.setString(8, collaborateur.getEmail());
			preparedStatement.setString(9, collaborateur.getTelephone_secondaire());
			preparedStatement.setString(10, collaborateur.getEmail_secondaire());
			preparedStatement.setString(11, collaborateur.getCivilite());
			preparedStatement.setString(12, collaborateur.getDate_naissance());
			preparedStatement.setString(13, collaborateur.getNationalite());
			preparedStatement.setInt(14, typeContratDictionary.get(collaborateur.getType_contrat()));
			preparedStatement.setString(15, collaborateur.getType_collaborateur());
			preparedStatement.setInt(16, collaborateur.getSignataire_contrat());
			preparedStatement.setInt(17, utilisateurDictionary.get(collaborateur.getUtilisateur()));
			preparedStatement.setInt(18, metierDictionary.get(collaborateur.getMetier()));
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DaoException("Impossible de maj enregistrement avec la table collaborateurt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de maj enregistrement avec la table collaborateurt" + e);
			}
		}
	}

	// CRUD COPIER ENREGISTREMENT
	@Override
	public void copierCollaborateur(Collaborateur collaborateur) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		listerTypeContrat();
		listerUtilisateur();
		listerMetier();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"INSERT INTO collaborateur(nom, prenom, adresse, code_postal, ville, pays, telephone, email, telephone_secondaire, email_secondaire, civilite, date_naissance, nationalite, id_type_contrat, type_collaborateur, signataire_wavy, id_utilisateur, metier_principal, pgmcreation, datecreation, usercreation ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

			preparedStatement.setString(1, collaborateur.getNom());
			preparedStatement.setString(2, collaborateur.getPrenom());
			preparedStatement.setString(3, collaborateur.getAdresse());
			preparedStatement.setString(4, collaborateur.getCode_postal());
			preparedStatement.setString(5, collaborateur.getVille());
			preparedStatement.setString(6, collaborateur.getPays());
			preparedStatement.setString(7, collaborateur.getTelephone());
			preparedStatement.setString(8, collaborateur.getEmail());
			preparedStatement.setString(9, collaborateur.getTelephone_secondaire());
			preparedStatement.setString(10, collaborateur.getEmail_secondaire());
			preparedStatement.setString(11, collaborateur.getCivilite());
			preparedStatement.setString(12, collaborateur.getDate_naissance());
			preparedStatement.setString(13, collaborateur.getNationalite());
			preparedStatement.setInt(14, typeContratDictionary.get(collaborateur.getType_contrat()));

			preparedStatement.setString(15, collaborateur.getType_collaborateur());
			preparedStatement.setInt(16, collaborateur.getSignataire_contrat());

			preparedStatement.setInt(17, utilisateurDictionary.get(collaborateur.getUtilisateur()));
			preparedStatement.setInt(18, metierDictionary.get(collaborateur.getMetier()));

			preparedStatement.setString(19, "CollaborateurDao");
			preparedStatement.setString(20, dateTime);
			preparedStatement.setString(21, System.getProperty("user.name"));
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DaoException("Impossible de maj enregistrement avec la table collaborateurt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de maj enregistrement avec la table collaborateurt" + e);
			}
		}
	}

	// CRUD SUPPRIMER UN ENREGISTREMENT
	@Override
	public void supprimerCollaborateur(Integer id) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM COLLABORATEUR WHERE ID=?;");
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();
			connexion.commit();

		} catch (SQLException e) {
			throw new DaoException("Impossible de supprimer enregistrement avec la table collaborateurt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de supprimer enregistrement avec la table collaborateurt" + e);
			}
		}

	}

	// rechercher et lister les enregistrements
	@Override
	public List<Collaborateur> rechercheCollaborateurs1(int offset, int noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query2 = "select SQL_CALC_FOUND_ROWS collaborateur.id, collaborateur.nom, collaborateur.prenom, collaborateur.adresse, collaborateur.code_postal, collaborateur.ville, collaborateur.pays, collaborateur.email, collaborateur.telephone, collaborateur.email_secondaire, collaborateur.telephone_secondaire, collaborateur.civilite, collaborateur.date_naissance, collaborateur.nationalite, collaborateur.id_type_contrat, typeContratCollaborateur.libelle, collaborateur.type_collaborateur, collaborateur.signataire_wavy, collaborateur.id_utilisateur, utilisateur.libelle, metier.id, metier.nom from collaborateur, metier, utilisateur, typeContratCollaborateur where collaborateur.metier_principal =metier.id and collaborateur.id_utilisateur = utilisateur.id and collaborateur.id_type_contrat =typeContratCollaborateur.id "
				+ " ORDER BY " + select_tri + " limit " + offset + ", " + noOfRecords;
		List<Collaborateur> list = new ArrayList<Collaborateur>();
		Collaborateur collaborateur = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query2);
			System.out.println("sql: " + preparedStatement);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				collaborateur = new Collaborateur();
				collaborateur.setId(rs.getInt(1));
				collaborateur.setNom(rs.getString(2));
				collaborateur.setPrenom(rs.getString(3));
				collaborateur.setAdresse(rs.getString(4));
				collaborateur.setCode_postal(rs.getString(5));
				collaborateur.setVille(rs.getString(6));
				collaborateur.setPays(rs.getString(7));
				collaborateur.setEmail(rs.getString(8));
				collaborateur.setTelephone(rs.getString(9));
				collaborateur.setEmail_secondaire(rs.getString(10));
				collaborateur.setTelephone_secondaire(rs.getString(11));
				collaborateur.setCivilite(rs.getString(12));
				collaborateur.setDate_naissance(rs.getString(13));
				collaborateur.setNationalite(rs.getString(14));
				collaborateur.setId_type_contrat(rs.getInt(15));
				collaborateur.setType_contrat(rs.getString(16));
				collaborateur.setType_collaborateur(rs.getString(17));
				collaborateur.setSignataire_contrat(rs.getInt(18));
				collaborateur.setId_utilisateur(rs.getInt(19));
				collaborateur.setUtilisateur(rs.getString(20));
				collaborateur.setMetier_principal(rs.getInt(21));
				collaborateur.setMetier(rs.getString(22));

				list.add(collaborateur);
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
			// RECUPERATION N° ENREGISTREMENT ENCOURS LIGNE PARAMETRE
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


	// rechercher et lister les enregistrements suivant like
	@Override
	public List<Collaborateur> rechercheLikeCollaborateurs1(int offset, int noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_entite) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query2 = "select SQL_CALC_FOUND_ROWS collaborateur.id, collaborateur.nom, collaborateur.prenom, collaborateur.adresse, collaborateur.code_postal, collaborateur.ville, collaborateur.pays, collaborateur.telephone, collaborateur.email, collaborateur.telephone_secondaire, collaborateur.email_secondaire, collaborateur.civilite, collaborateur.date_naissance, collaborateur.nationalite, collaborateur.id_type_contrat, typeContratCollaborateur.libelle, collaborateur.type_collaborateur, collaborateur.signataire_wavy, collaborateur.metier_principal, collaborateur.type_collaborateur, metier.id, metier.nom, utilisateur.nom from collaborateur, metier, utilisateur, typeContratCollaborateur where collaborateur.metier_principal =metier.id and collaborateur.id_utilisateur = utilisateur.id and collaborateur.id_type_contrat =typeContratCollaborateur.id "
				+ " AND " + select_like + " ORDER BY " + select_tri + " limit " + offset + ", " + noOfRecords;
		System.out.print("select" + query2);
		// + collaborateur.id =metier.id and collaborateur.nom like 'f%' ORDER BY
		// metier_principal desc limit 0, 6;
		List<Collaborateur> list = new ArrayList<Collaborateur>();
		Collaborateur collaborateur = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query2);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				collaborateur = new Collaborateur();
				collaborateur.setId(rs.getInt(1));
				collaborateur.setNom(rs.getString(2));
				collaborateur.setPrenom(rs.getString(3));
				collaborateur.setAdresse(rs.getString(4));
				collaborateur.setCode_postal(rs.getString(5));
				collaborateur.setVille(rs.getString(6));
				collaborateur.setPays(rs.getString(7));
				collaborateur.setEmail(rs.getString(8));
				collaborateur.setTelephone(rs.getString(9));
				collaborateur.setEmail_secondaire(rs.getString(10));
				collaborateur.setTelephone_secondaire(rs.getString(11));
				collaborateur.setCivilite(rs.getString(12));
				collaborateur.setDate_naissance(rs.getString(13));
				collaborateur.setNationalite(rs.getString(14));
				collaborateur.setId_type_contrat(rs.getInt(15));
				collaborateur.setType_contrat(rs.getString(16));
				collaborateur.setType_collaborateur(rs.getString(17));
				collaborateur.setSignataire_contrat(rs.getInt(18));
				collaborateur.setId_utilisateur(rs.getInt(19));
				collaborateur.setUtilisateur(rs.getString(20));
				collaborateur.setMetier_principal(rs.getInt(21));
				collaborateur.setMetier(rs.getString(22));

				list.add(collaborateur);
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

	// =================================================================================
	// RECHERCHE LIGNE PARAMETRE
	// =================================================================================
	@Override
	public List<Map<String, Object>> rechercheCollaborateurs(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
		List<Map<String, Object>> list = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connexion = daoFactory.getConnection();
			String query = "select SQL_CALC_FOUND_ROWS collaborateur.id, collaborateur.nom, collaborateur.prenom, collaborateur.adresse, collaborateur.code_postal, collaborateur.ville, collaborateur.pays, collaborateur.email, collaborateur.telephone, collaborateur.email_secondaire, collaborateur.telephone_secondaire, collaborateur.civilite, collaborateur.date_naissance, collaborateur.nationalite, collaborateur.id_type_contrat, typeContratCollaborateur.libelle, collaborateur.type_collaborateur, collaborateur.signataire_wavy, collaborateur.id_utilisateur, utilisateur.libelle, metier.id, metier.nom from collaborateur, metier, utilisateur, typeContratCollaborateur where collaborateur.metier_principal =metier.id and collaborateur.id_utilisateur = utilisateur.id and collaborateur.id_type_contrat =typeContratCollaborateur.id "
					+ " ORDER BY " + select_tri + " limit " + offset + ", " + noOfRecords;

			preparedStatement = connexion.prepareStatement(query);
			//preparedStatement.setInt(1, offset);
			//preparedStatement.setInt(2, noOfRecords);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Map<String, Object> entiteFields = new LinkedHashMap<>();
				if (dictionnaire_nom_colonne.containsKey("id")) {
					entiteFields.put("id", rs.getInt("id"));
				}
				if (dictionnaire_nom_colonne.containsKey("nom")) {
					entiteFields.put("nom", rs.getString("nom"));
				}
				if (dictionnaire_nom_colonne.containsKey("prenom")) {
					entiteFields.put("prenom", rs.getString("prenom"));
				}
				if (dictionnaire_nom_colonne.containsKey("adresse")) {
					entiteFields.put("adresse", rs.getString("adresse"));
				}
				if (dictionnaire_nom_colonne.containsKey("code_postal")) {
					entiteFields.put("code_postal", rs.getString("code_postal"));
				}
				if (dictionnaire_nom_colonne.containsKey("ville")) {
					entiteFields.put("ville", rs.getString("ville"));
				}
				if (dictionnaire_nom_colonne.containsKey("pays")) {
					entiteFields.put("pays", rs.getString("pays"));
				}
				if (dictionnaire_nom_colonne.containsKey("email")) {
					entiteFields.put("email", rs.getString("email"));
				}
				if (dictionnaire_nom_colonne.containsKey("telephone")) {
					entiteFields.put("telephone", rs.getString("telephone"));
				}
				if (dictionnaire_nom_colonne.containsKey("email_secondaire")) {
					entiteFields.put("email_secondaire", rs.getString("email_secondaire"));
				}
				if (dictionnaire_nom_colonne.containsKey("telephone_secondaire")) {
					entiteFields.put("telephone_secondaire", rs.getString("telephone_secondaire"));
				}
				if (dictionnaire_nom_colonne.containsKey("date_naissance")) {
					entiteFields.put("date_naissance", rs.getString("date_naissance"));
				}
				if (dictionnaire_nom_colonne.containsKey("nationalite")) {
					entiteFields.put("nationalite", rs.getString("nationalite"));
				}
				if (dictionnaire_nom_colonne.containsKey("id_type_contrat")) {
					entiteFields.put("id_type_contrat", rs.getInt("id_type_contrat"));
				}
				if (dictionnaire_nom_colonne.containsKey("type_contrat")) {
					entiteFields.put("type_contrat", rs.getString("type_contrat"));
				}
				if (dictionnaire_nom_colonne.containsKey("type_collaborateur")) {
					entiteFields.put("type_collaborateur", rs.getString("type_collaborateur"));
				}
				if (dictionnaire_nom_colonne.containsKey("signataire_contrat")) {
					entiteFields.put("signataire_contrat", rs.getInt("signataire_contrat"));
				}
				if (dictionnaire_nom_colonne.containsKey("id_utilisateur")) {
					entiteFields.put("id_utilisateur", rs.getString("id_utilisateur"));
				}
				if (dictionnaire_nom_colonne.containsKey("setUtilisateur")) {
					entiteFields.put("setUtilisateur", rs.getString("setUtilisateur"));
				}
				if (dictionnaire_nom_colonne.containsKey("metier_principal")) {
					entiteFields.put("metier_principal", rs.getString("metier_principal"));
				}
				if (dictionnaire_nom_colonne.containsKey("metier")) {
					entiteFields.put("metier", rs.getString("metier"));
				}

				list.add(entiteFields);
			}
			list.sort(Comparator.comparing(map -> (Integer) map.get("numero_champ")));
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
	// RECHERCHE LIGNE PARAMETRES SUIVANT LIKE
	// =================================================================================
	@Override
	public List<Map<String, Object>> rechercheLikeCollaborateurs(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_entite) {
		List<Map<String, Object>> list = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connexion = daoFactory.getConnection();
			String query = "select SQL_CALC_FOUND_ROWS collaborateur.id, collaborateur.nom, collaborateur.prenom, collaborateur.adresse, collaborateur.code_postal, collaborateur.ville, collaborateur.pays, collaborateur.telephone, collaborateur.email, collaborateur.telephone_secondaire, collaborateur.email_secondaire, collaborateur.civilite, collaborateur.date_naissance, collaborateur.nationalite, collaborateur.id_type_contrat, typeContratCollaborateur.libelle, collaborateur.type_collaborateur, collaborateur.signataire_wavy, collaborateur.metier_principal, collaborateur.type_collaborateur, metier.id, metier.nom, utilisateur.nom from collaborateur, metier, utilisateur, typeContratCollaborateur where collaborateur.metier_principal =metier.id and collaborateur.id_utilisateur = utilisateur.id and collaborateur.id_type_contrat =typeContratCollaborateur.id "
					 + select_like + " ORDER BY " + select_tri + " limit " + offset + ", " + noOfRecords;
			preparedStatement = connexion.prepareStatement(query);
			//preparedStatement.setInt(1, offset);
			//preparedStatement.setInt(2, noOfRecords);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Map<String, Object> entiteFields = new LinkedHashMap<>();
				if (dictionnaire_nom_colonne.containsKey("id")) {
					entiteFields.put("id", rs.getInt("id"));
				}
				if (dictionnaire_nom_colonne.containsKey("nom")) {
					entiteFields.put("nom", rs.getString("nom"));
				}
				if (dictionnaire_nom_colonne.containsKey("prenom")) {
					entiteFields.put("prenom", rs.getString("prenom"));
				}
				if (dictionnaire_nom_colonne.containsKey("adresse")) {
					entiteFields.put("adresse", rs.getString("adresse"));
				}
				if (dictionnaire_nom_colonne.containsKey("code_postal")) {
					entiteFields.put("code_postal", rs.getString("code_postal"));
				}
				if (dictionnaire_nom_colonne.containsKey("ville")) {
					entiteFields.put("ville", rs.getString("ville"));
				}
				if (dictionnaire_nom_colonne.containsKey("pays")) {
					entiteFields.put("pays", rs.getString("pays"));
				}
				if (dictionnaire_nom_colonne.containsKey("email")) {
					entiteFields.put("email", rs.getString("email"));
				}
				if (dictionnaire_nom_colonne.containsKey("telephone")) {
					entiteFields.put("telephone", rs.getString("telephone"));
				}
				if (dictionnaire_nom_colonne.containsKey("email_secondaire")) {
					entiteFields.put("email_secondaire", rs.getString("email_secondaire"));
				}
				if (dictionnaire_nom_colonne.containsKey("telephone_secondaire")) {
					entiteFields.put("telephone_secondaire", rs.getString("telephone_secondaire"));
				}
				if (dictionnaire_nom_colonne.containsKey("date_naissance")) {
					entiteFields.put("date_naissance", rs.getString("date_naissance"));
				}
				if (dictionnaire_nom_colonne.containsKey("nationalite")) {
					entiteFields.put("nationalite", rs.getString("nationalite"));
				}
				if (dictionnaire_nom_colonne.containsKey("id_type_contrat")) {
					entiteFields.put("id_type_contrat", rs.getInt("id_type_contrat"));
				}
				if (dictionnaire_nom_colonne.containsKey("type_contrat")) {
					entiteFields.put("type_contrat", rs.getString("type_contrat"));
				}
				if (dictionnaire_nom_colonne.containsKey("type_collaborateur")) {
					entiteFields.put("type_collaborateur", rs.getString("type_collaborateur"));
				}
				if (dictionnaire_nom_colonne.containsKey("signataire_contrat")) {
					entiteFields.put("signataire_contrat", rs.getInt("signataire_contrat"));
				}
				if (dictionnaire_nom_colonne.containsKey("id_utilisateur")) {
					entiteFields.put("id_utilisateur", rs.getString("id_utilisateur"));
				}
				if (dictionnaire_nom_colonne.containsKey("setUtilisateur")) {
					entiteFields.put("setUtilisateur", rs.getString("setUtilisateur"));
				}
				if (dictionnaire_nom_colonne.containsKey("metier_principal")) {
					entiteFields.put("metier_principal", rs.getString("metier_principal"));
				}
				if (dictionnaire_nom_colonne.containsKey("metier")) {
					entiteFields.put("metier", rs.getString("metier"));
				}
				list.add(entiteFields);
			}
			list.sort(Comparator.comparing(map -> (Integer) map.get("numero_champ")));
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
	// FERMETURE DES RESSOURCES LIGNE PARAMETRE
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
