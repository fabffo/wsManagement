/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO IMPLEMENTATION SOCIETE                              ///
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ws.beans.Societe;

public class SocieteDaoImpl implements SocieteDao {
	private DaoFactory daoFactory;
	// date du jour
	String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
	private int noOfRecords;
	Map<String, Integer> contactDictionary = new HashMap<>();

	SocieteDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public Map<String, Integer> listerContact(int id_societe) {
		// liste des types de contrat disponibles
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query = "select id, civilite, prenom, nom, email1, telephone1, fonction from contact where id_societe=?";
		try {
			String resume_contact;
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, id_societe);
			rs = preparedStatement.executeQuery();
			int i = 0;
			while (rs.next()) {
				i=i+1;
				resume_contact =(rs.getString(2)+"_"+rs.getString(3)+"_"+rs.getString(4)+"_"+rs.getString(5)+"_"+rs.getString(6)+"_"+rs.getString(7));;
				contactDictionary.put(resume_contact, rs.getInt(1));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return contactDictionary;
	}
	// CRUD CREER
	@Override
	public void ajouterSociete(Societe societe) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"INSERT INTO societe(raison_sociale, type, adresse, code_postal, ville, pays, code_tva, siret, code_naf, siren, metier, id_contact_principal, pgmcreation, datecreation, usercreation ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

			preparedStatement.setString(1, societe.getRaison_sociale());
			preparedStatement.setString(2, societe.getType());
			preparedStatement.setString(3, societe.getAdresse());
			preparedStatement.setString(4, societe.getCode_postal());
			preparedStatement.setString(5, societe.getVille());
			preparedStatement.setString(6, societe.getPays());
			preparedStatement.setString(7, societe.getCode_tva());
			preparedStatement.setString(8, societe.getSiret());
			preparedStatement.setString(9, societe.getCode_naf());
			preparedStatement.setString(10, societe.getSiren());
			preparedStatement.setString(11, societe.getMetier());
			preparedStatement.setInt(12, societe.getId_contact_principal());
			preparedStatement.setString(13, "SocieteDao");
			preparedStatement.setString(14, dateTime);
			preparedStatement.setString(15, System.getProperty("user.name"));
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DaoException("Impossible d'ajouter enregistrement avec la table societet" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible d'ajouter enregistrement avec la table societet" + e);
			}
		}

	}

	// LISTER LES ENREGISTREMENTS
	@Override
	public List<Societe> listerSociete() throws DaoException {
		List<Societe> societes = new ArrayList<Societe>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM societe;");

			while (resultat.next()) {
				int id = resultat.getInt("id");
				String raison_sociale = resultat.getString("raison_sociale");
				String type = resultat.getString("type");
				String adresse = resultat.getString("adresse");
				String code_postal = resultat.getString("code_postal");
				String ville = resultat.getString("ville");
				String pays = resultat.getString("pays");
				String code_tva = resultat.getString("code_tva");
				String siret = resultat.getString("siret");
				String metier = resultat.getString("metier");
				String code_naf = resultat.getString("code_tva");
				String siren = resultat.getString("siren");
				int id_contact_societe = resultat.getInt("id_contact_societe");

				Societe societe = new Societe();
				societe.setId(id);
				societe.setRaison_sociale(raison_sociale);
				societe.setType(type);
				societe.setAdresse(adresse);
				societe.setCode_postal(code_postal);
				societe.setVille(ville);
				societe.setPays(pays);
				societe.setCode_tva(code_tva);
				societe.setCode_naf(code_naf);
				societe.setSiret(siret);
				societe.setSiren(siren);
				societe.setMetier(metier);
				societe.setId_contact_principal(id_contact_societe);
				societes.add(societe);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de lister enregistrement avec la table societet" );
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de lister enregistrement avec la table societe" );
			}
		}
		return societes;
	}

	// CRUD LIRE UN ENREGISTREMENT SPECIFIQUE VIA SON ID
	@Override
	public Societe trouverSociete(Integer id) throws DaoException {
		Societe societe = new Societe();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"SELECT societe.id, societe.raison_sociale, societe.type, societe.adresse, societe.code_postal, societe.ville, societe.pays, societe.siret, societe.code_tva, societe.siren, societe.code_naf, societe.metier, societe.id_contact_principal, contact.civilite, contact.prenom, contact.nom, contact.email1, contact.telephone1 from societe left join contact  on  societe.id_contact_principal=contact.id where societe.id=?;");
			preparedStatement.setInt(1, id);
			resultat = preparedStatement.executeQuery();
			connexion.commit();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if (resultat.next()) {

				String raison_sociale = resultat.getString("societe.raison_sociale");
				String type = resultat.getString("societe.type");
				String adresse = resultat.getString("societe.adresse");
				String code_postal = resultat.getString("societe.code_postal");
				String ville = resultat.getString("societe.ville");
				String pays = resultat.getString("societe.pays");
				String code_tva = resultat.getString("societe.code_tva");
				String siret = resultat.getString("societe.siret");
				String code_naf = resultat.getString("societe.code_naf");
				String siren = resultat.getString("societe.siren");
				String metier = resultat.getString("societe.metier");
				int id_contact_principal= resultat.getInt("societe.id_contact_principal");
				String civilite = resultat.getString("contact.civilite");
				String prenom = resultat.getString("contact.prenom");
				String nom = resultat.getString("contact.nom");
				String email1 = resultat.getString("contact.email1");
				String telephone1 = resultat.getString("contact.telephone1");


				societe.setId(id);
				societe.setRaison_sociale(raison_sociale);
				societe.setType(type);
				societe.setAdresse(adresse);
				societe.setCode_postal(code_postal);
				societe.setVille(ville);
				societe.setPays(pays);
				societe.setCode_tva(code_tva);
				societe.setCode_naf(code_naf);
				societe.setSiret(siret);
				societe.setSiren(siren);
				societe.setMetier(metier);
				societe.setId_contact_principal(id_contact_principal);
				societe.setContact_principal(civilite+ "_" + nom + "_" + prenom+ "_" + email1+ "_" + telephone1+ "_" + metier);
				societe.setEmail(email1);
				societe.setTelephone(telephone1);

			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de trouver id enregistrement avec la table societet" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de trouver id enregistrement avec la table societet" + e);
			}
		}
		return societe;
	}

	// EXISTENCE D'UN ENREGISTREMENT SPECIFIQUE VIA SON NOM
	@Override
	public boolean trouverRaison_socialeSociete(String raison_sociale) throws DaoException {
		boolean existe = false;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT * FROM COLLABORATEUR WHERE raison_sociale=?;");
			preparedStatement.setString(1, raison_sociale);
			resultat = preparedStatement.executeQuery();
			connexion.commit();

			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if (resultat.next()) {
				existe = true;
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de communiquer avec la table societet" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de communiquer avec la societet" + e);
			}
		}

		return existe;
	}

	// CRUD MODIFIER ENREGISTREMENT
	@Override
	public void modifierSociete(Societe societe) throws DaoException {
		contactDictionary = listerContact(societe.getId());

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();

			preparedStatement = connexion.prepareStatement(
			"UPDATE societe SET raison_sociale=?, type=?, adresse=?, code_postal=?, ville=?, pays=?, code_tva=?, siret=?, code_naf=?, siren=?, metier=?, id_contact_principal=? where id=?;");
			preparedStatement.setInt(13, societe.getId());
			preparedStatement.setString(1, societe.getRaison_sociale());
			preparedStatement.setString(2, societe.getType());
			preparedStatement.setString(3, societe.getAdresse());
			preparedStatement.setString(4, societe.getCode_postal());
			preparedStatement.setString(5, societe.getVille());
			preparedStatement.setString(6, societe.getPays());
			preparedStatement.setString(7, societe.getCode_tva());
			preparedStatement.setString(8, societe.getSiret());
			preparedStatement.setString(9, societe.getCode_naf());
			preparedStatement.setString(10, societe.getSiren());
			preparedStatement.setString(11, societe.getMetier());

			for (Map.Entry<String, Integer> entry : contactDictionary.entrySet()) {
	            int id = entry.getValue();
	            String name = entry.getKey();
	        }
			preparedStatement.setInt(12, contactDictionary.get(societe.getContact_principal()));
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DaoException("Impossible de maj enregistrement avec la table societe" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de maj enregistrement avec la table societe" + e);
			}
		}
	}

	// CRUD COPIER ENREGISTREMENT
	@Override
	public void copierSociete(Societe societe) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"INSERT INTO societe(raison_sociale, type, adresse, code_postal, ville, pays, code_tva, siret, code_naf, siren, metier, id_contact_principal, pgmcreation, datecreation, usercreation ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

			preparedStatement.setString(1, societe.getRaison_sociale());
			preparedStatement.setString(2, societe.getType());
			preparedStatement.setString(3, societe.getAdresse());
			preparedStatement.setString(4, societe.getCode_postal());
			preparedStatement.setString(5, societe.getVille());
			preparedStatement.setString(6, societe.getPays());
			preparedStatement.setString(7, societe.getCode_tva());
			preparedStatement.setString(8, societe.getSiret());
			preparedStatement.setString(9, societe.getCode_naf());
			preparedStatement.setString(10, societe.getSiren());
			preparedStatement.setString(11, societe.getMetier());
			preparedStatement.setInt(12, 0);
			preparedStatement.setString(13, "SocieteDao");
			preparedStatement.setString(14, dateTime);
			preparedStatement.setString(15, System.getProperty("user.name"));
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DaoException("Impossible de maj enregistrement avec la table societe" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de maj enregistrement avec la table societet" + e);
			}
		}
	}

	// CRUD SUPPRIMER UN ENREGISTREMENT
	@Override
	public void supprimerSociete(Integer id) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM SOCIETE WHERE ID=?;");
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();
			connexion.commit();

		} catch (SQLException e) {
			throw new DaoException("Impossible de supprimer enregistrement avec la table societet" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de supprimer enregistrement avec la table societet" + e);
			}
		}

	}

	// rechercher et lister les enregistrements
	public List<Societe> rechercheSocietes(int offset, int noOfRecords, String select_tri, String typeSociete) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		String query2 = "select SQL_CALC_FOUND_ROWS societe.id, societe.raison_sociale, societe.type, societe.adresse, societe.code_postal, societe.ville, societe.pays, societe.siret, societe.code_tva, societe.siren, societe.code_naf, societe.metier, societe.id_contact_principal, contact.prenom, contact.nom, contact.email1, contact.telephone1 from societe left join contact on societe.id_contact_principal=contact.id where EXISTS (select * from type_societe where type_societe.libelle = societe.type and " +   "type_societe.libelle="   + "'" + typeSociete + "'"+ ")  "
				+ " ORDER BY " + select_tri + " limit " + offset + ", " + noOfRecords;
		List<Societe> list = new ArrayList<Societe>();
		Societe societe = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query2);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				societe = new Societe();
				societe.setId(rs.getInt(1));
				societe.setRaison_sociale(rs.getString(2));
				societe.setType(rs.getString(3));
				societe.setAdresse(rs.getString(4));
				societe.setCode_postal(rs.getString(5));
				societe.setVille(rs.getString(6));
				societe.setPays(rs.getString(7));
				societe.setSiret(rs.getString(8));
				societe.setCode_tva(rs.getString(9));
				societe.setSiren(rs.getString(10));
				societe.setCode_naf(rs.getString(11));
				societe.setMetier(rs.getString(12));
				societe.setId_contact_principal(rs.getInt(13));
				societe.setContact_principal(rs.getString(14) + " " + rs.getString(15));
				societe.setEmail(rs.getString(16));
				societe.setTelephone(rs.getString(17));
				list.add(societe);
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

	//Avoir le N° enregistrement en cours
	public int getNoOfRecords() {
		return noOfRecords;
	}

	// rechercher et lister les enregistrements suivant like
	public List<Societe> rechercheLikeSocietes(int offset, int noOfRecords, String select_tri,
			String select_like, String typeSociete) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		String query2 = "select SQL_CALC_FOUND_ROWS societe.id, societe.raison_sociale, societe.type, societe.adresse, societe.code_postal, societe.ville, societe.pays, societe.code_tva, societe.siret, societe.code_naf, societe.siren, societe.metier, societe.id_contact_principal, contact.prenom, contact.nom, contact.email1, contact.telephone1 from societe left join contact on societe.id_contact_principal=contact.id where EXISTS (select * from type_societe where type_societe.libelle = societe.type and " +   "type_societe.libelle= "   + "'"+ typeSociete + "'"+ ") "
				+ " AND " + select_like + " ORDER BY " + select_tri + " limit " + offset + ", " + noOfRecords;
		// + societe.id =activite.id and societe.raison_sociale like 'f%' ORDER BY
		// activite_principale desc limit 0, 6;
		List<Societe> list = new ArrayList<Societe>();
		Societe societe = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query2);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				societe = new Societe();
				societe.setId(rs.getInt(1));
				societe.setRaison_sociale(rs.getString(2));
				societe.setType(rs.getString(3));
				societe.setAdresse(rs.getString(4));
				societe.setCode_postal(rs.getString(5));
				societe.setVille(rs.getString(6));
				societe.setPays(rs.getString(7));
				societe.setSiret(rs.getString(8));
				societe.setCode_tva(rs.getString(9));
				societe.setSiren(rs.getString(10));
				societe.setCode_naf(rs.getString(11));
				societe.setMetier(rs.getString(12));
				societe.setId_contact_principal(rs.getInt(13));
				societe.setContact_principal(rs.getString(14) + " " + rs.getString(15));
				societe.setEmail(rs.getString(16));
				societe.setTelephone(rs.getString(17));
				list.add(societe);
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
}
