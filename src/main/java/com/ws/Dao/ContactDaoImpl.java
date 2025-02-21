/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO IMPLEMENTATION CONTACT                              ///
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

import com.ws.beans.Contact;

public class ContactDaoImpl implements ContactDao {
	private DaoFactory daoFactory;
	// date du jour
	String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
	private int noOfRecords;
	Map<String, Integer> typeContratDictionary = new HashMap<>();
	Map<String, Integer> societeDictionary = new HashMap<>();
	Map<String, Integer> metierDictionary = new HashMap<>();

	ContactDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
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
				i=i+1;
				societeDictionary.put(rs.getString(1), rs.getInt(2));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return societeDictionary;
	}

	// CRUD CREER
	@Override
	public void ajouterContact(Contact contact) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		listerSociete();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"INSERT INTO contact(nom, prenom, adresse, code_postal, ville, pays, telephone1, email1, telephone2, email2, civilite, id_societe, pgmcreation, datecreation, usercreation ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

			preparedStatement.setString(1, contact.getNom());
			preparedStatement.setString(2, contact.getPrenom());
			preparedStatement.setString(3, contact.getAdresse());
			preparedStatement.setString(4, contact.getCode_postal());
			preparedStatement.setString(5, contact.getVille());
			preparedStatement.setString(6, contact.getPays());
			preparedStatement.setString(7, contact.getTelephone());
			preparedStatement.setString(8, contact.getEmail());
			preparedStatement.setString(9, contact.getTelephone_secondaire());
			preparedStatement.setString(10, contact.getEmail_secondaire());
			preparedStatement.setString(11, contact.getCivilite());
			preparedStatement.setInt(12, societeDictionary.get(contact.getSociete()));
			preparedStatement.setString(13, "ContactDao");
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
			throw new DaoException("Impossible d'ajouter enregistrement avec la table contactt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible d'ajouter enregistrement avec la table contactt" + e);
			}
		}

	}

	// LISTER LES ENREGISTREMENTS
	@Override
	public List<Contact> listerContact() throws DaoException {
		List<Contact> contacts = new ArrayList<Contact>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM contact;");

			while (resultat.next()) {
				int id = resultat.getInt("id");
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");
				String adresse = resultat.getString("adresse");
				String code_postal = resultat.getString("code_postal");
				String ville = resultat.getString("ville");
				String pays = resultat.getString("pays");
				String telephone = resultat.getString("telephone1");
				String email = resultat.getString("email1");
				String telephone_secondaire = resultat.getString("telephone2");
				String email_secondaire = resultat.getString("email2");
				String civilite = resultat.getString("civilite");
				int id_societe = resultat.getInt("id_societe");

				Contact contact = new Contact();
				contact.setId(id);
				contact.setNom(nom);
				contact.setPrenom(prenom);
				contact.setAdresse(adresse);
				contact.setCode_postal(code_postal);
				contact.setVille(ville);
				contact.setPays(pays);
				contact.setTelephone(telephone);
				contact.setTelephone_secondaire(telephone_secondaire);
				contact.setEmail(email);
				contact.setEmail_secondaire(email_secondaire);
				contact.setCivilite(civilite);
				contact.setId_societe(id_societe);
				contacts.add(contact);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de lister enregistrement avec la table contactt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de lister enregistrement avec la table contactt" + e);
			}
		}
		return contacts;
	}

	// CRUD LIRE UN ENREGISTREMENT SPECIFIQUE VIA SON ID
	@Override
	public Contact trouverContact(Integer id) throws DaoException {
		Contact contact = new Contact();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"SELECT contact.id, contact.nom, contact.prenom, contact.adresse, contact.code_postal, contact.ville, contact.pays, contact.email1, contact.telephone1, contact.email2, contact.telephone2, contact.civilite, contact.id_societe, societe.raison_sociale from contact, societe, type_contrat where contact.id_societe = societe.id and contact.id=?;");
			preparedStatement.setInt(1, id);
			resultat = preparedStatement.executeQuery();
			connexion.commit();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if (resultat.next()) {

				String nom = resultat.getString("contact.nom");
				String prenom = resultat.getString("contact.prenom");
				String adresse = resultat.getString("contact.adresse");
				String code_postal = resultat.getString("contact.code_postal");
				String ville = resultat.getString("contact.ville");
				String pays = resultat.getString("contact.pays");
				String telephone = resultat.getString("contact.telephone1");
				String email = resultat.getString("contact.email1");
				String telephone_secondaire = resultat.getString("contact.telephone2");
				String email_secondaire = resultat.getString("contact.email2");
				String civilite = resultat.getString("contact.civilite");
				int id_societe = resultat.getInt("contact.id_societe");
				String societe = resultat.getString("societe.raison_sociale");
				contact.setId(id);
				contact.setNom(nom);
				contact.setPrenom(prenom);
				contact.setAdresse(adresse);
				contact.setCode_postal(code_postal);
				contact.setVille(ville);
				contact.setPays(pays);
				contact.setTelephone(telephone);
				contact.setTelephone_secondaire(telephone_secondaire);
				contact.setEmail(email);
				contact.setEmail_secondaire(email_secondaire);
				contact.setCivilite(civilite);
				contact.setId_societe(id_societe);
				contact.setSociete(societe);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de trouver id enregistrement avec la table contactt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de trouver id enregistrement avec la table contactt" + e);
			}
		}
		return contact;
	}

	// EXISTENCE D'UN ENREGISTREMENT SPECIFIQUE VIA SON NOM
	@Override
	public boolean trouverNomContact(String nom) throws DaoException {
		boolean existe = false;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT * FROM contact WHERE nom=?;");
			preparedStatement.setString(1, nom);
			resultat = preparedStatement.executeQuery();
			connexion.commit();

			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if (resultat.next()) {
				existe = true;
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de communiquer avec la table contactt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de communiquer avec la contactt" + e);
			}
		}

		return existe;
	}

	// CRUD MODIFIER ENREGISTREMENT
	@Override
	public void modifierContact(Contact contact) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		listerSociete();
		try {
			connexion = daoFactory.getConnection();

			preparedStatement = connexion.prepareStatement(
			"UPDATE contact SET nom=?, prenom=?, adresse=?, code_postal=?, ville=?, pays=?, telephone1=?, email1=?, telephone2=?, email2=?, civilite=?, id_societe=? where id=?;");
			preparedStatement.setInt(13, contact.getId());
			preparedStatement.setString(1, contact.getNom());
			preparedStatement.setString(2, contact.getPrenom());
			preparedStatement.setString(3, contact.getAdresse());
			preparedStatement.setString(4, contact.getCode_postal());
			preparedStatement.setString(5, contact.getVille());
			preparedStatement.setString(6, contact.getPays());
			preparedStatement.setString(7, contact.getTelephone());
			preparedStatement.setString(8, contact.getEmail());
			preparedStatement.setString(9, contact.getTelephone_secondaire());
			preparedStatement.setString(10, contact.getEmail_secondaire());
			preparedStatement.setString(11, contact.getCivilite());
			preparedStatement.setInt(12, societeDictionary.get(contact.getSociete()));
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DaoException("Impossible de maj enregistrement avec la table contactt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de maj enregistrement avec la table contactt" + e);
			}
		}
	}

	// CRUD COPIER ENREGISTREMENT
	@Override
	public void copierContact(Contact contact) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		listerSociete();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"INSERT INTO contact(nom, prenom, adresse, code_postal, ville, pays, telephone1, email1, telephone2, email2, civilite, id_societe, pgmcreation, datecreation, usercreation ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

			preparedStatement.setString(1, contact.getNom());
			preparedStatement.setString(2, contact.getPrenom());
			preparedStatement.setString(3, contact.getAdresse());
			preparedStatement.setString(4, contact.getCode_postal());
			preparedStatement.setString(5, contact.getVille());
			preparedStatement.setString(6, contact.getPays());
			preparedStatement.setString(7, contact.getTelephone());
			preparedStatement.setString(8, contact.getEmail());
			preparedStatement.setString(9, contact.getTelephone_secondaire());
			preparedStatement.setString(10, contact.getEmail_secondaire());
			preparedStatement.setString(11, contact.getCivilite());
			preparedStatement.setInt(12, societeDictionary.get(contact.getSociete()));
			preparedStatement.setString(13, "ContactDao");
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
			throw new DaoException("Impossible de maj enregistrement avec la table contactt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de maj enregistrement avec la table contactt" + e);
			}
		}
	}

	// CRUD SUPPRIMER UN ENREGISTREMENT
	@Override
	public void supprimerContact(Integer id) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM contact WHERE ID=?;");
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();
			connexion.commit();

		} catch (SQLException e) {
			throw new DaoException("Impossible de supprimer enregistrement avec la table contactt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de supprimer enregistrement avec la table contactt" + e);
			}
		}

	}

	// rechercher et lister les enregistrements
	public List<Contact> rechercheContacts(int offset, int noOfRecords, String select_tri, String typeSociete) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query2 = "select SQL_CALC_FOUND_ROWS contact.id, contact.nom, contact.prenom, contact.adresse, contact.code_postal, contact.ville, contact.pays, contact.email1, contact.telephone1, contact.email2, contact.telephone2, contact.civilite, contact.id_societe, societe.raison_sociale from contact left join societe on contact.id_societe=societe.id where  EXISTS (select * from type_societe where type_societe.libelle = societe.type and  " +   "type_societe.libelle="   + "'"+typeSociete+"'"+ ") "
			+ " ORDER BY " + select_tri + " limit " + offset + ", " + noOfRecords;
		List<Contact> list = new ArrayList<Contact>();
		Contact contact = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query2);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				contact = new Contact();
				contact.setId(rs.getInt(1));
				contact.setNom(rs.getString(2));
				contact.setPrenom(rs.getString(3));
				contact.setAdresse(rs.getString(4));
				contact.setCode_postal(rs.getString(5));
				contact.setVille(rs.getString(6));
				contact.setPays(rs.getString(7));
				contact.setEmail(rs.getString(8));
				contact.setTelephone(rs.getString(9));
				contact.setEmail_secondaire(rs.getString(10));
				contact.setTelephone_secondaire(rs.getString(11));
				contact.setCivilite(rs.getString(12));
				contact.setId_societe(rs.getInt(13));
				contact.setSociete(rs.getString(14));
				list.add(contact);
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
	public List<Contact> rechercheLikeContacts(int offset, int noOfRecords, String select_tri,
			String select_like, String typeSociete) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query2 = "select SQL_CALC_FOUND_ROWS contact.id, contact.nom, contact.prenom, contact.adresse, contact.code_postal, contact.ville, contact.pays, contact.email1, contact.telephone1, contact.telephone2, contact.email2, contact.civilite, contact.id_societe, societe.raison_sociale from contact left join societe on contact.id_societe=societe.id where EXISTS (select * from type_societe where type_societe.libelle = societe.type and " +   "type_societe.libelle="   +"'"+ typeSociete+"'"+ ") "
				+ " AND " + select_like + " ORDER BY " + select_tri + " limit " + offset + ", " + noOfRecords;
		// + contact.id =metier.id and contact.nom like 'f%' ORDER BY
		// metier_principal desc limit 0, 6;
		List<Contact> list = new ArrayList<Contact>();
		Contact contact = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query2);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				contact = new Contact();
				contact.setId(rs.getInt(1));
				contact.setNom(rs.getString(2));
				contact.setPrenom(rs.getString(3));
				contact.setAdresse(rs.getString(4));
				contact.setCode_postal(rs.getString(5));
				contact.setVille(rs.getString(6));
				contact.setPays(rs.getString(7));
				contact.setEmail(rs.getString(8));
				contact.setTelephone(rs.getString(9));
				contact.setEmail_secondaire(rs.getString(10));
				contact.setTelephone_secondaire(rs.getString(11));
				contact.setCivilite(rs.getString(12));
				contact.setId_societe(rs.getInt(13));
				contact.setSociete(rs.getString(14));


				list.add(contact);
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
