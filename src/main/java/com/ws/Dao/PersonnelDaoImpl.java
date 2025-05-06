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

import com.ws.beans.Organisation;
import com.ws.beans.Personnel;
import com.ws.beans.Signataire;

public class PersonnelDaoImpl implements PersonnelDao {
	private DaoFactory daoFactory;
	// date du jour
	String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
	private int noOfRecords;
	PersonnelDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	// CRUD CREER
	@Override
	public void ajouterPersonnel(Personnel personnel) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"INSERT INTO personnel( civilite, nom, prenom, adresse, code_postal, ville, pays, telephone, email, telephone_secondaire, email_secondaire, date_naissance, nationalite, activite, entite, signataire, utilisateur, metier, pgmcreation, datecreation, usercreation ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			preparedStatement.setString(1, personnel.getCivilite());
			preparedStatement.setString(2, personnel.getNom());
			preparedStatement.setString(3, personnel.getPrenom());
			preparedStatement.setString(4, personnel.getAdresse());
			preparedStatement.setString(5, personnel.getCode_postal());
			preparedStatement.setString(6, personnel.getVille());
			preparedStatement.setString(7, personnel.getPays());
			preparedStatement.setString(8, personnel.getTelephone());
			preparedStatement.setString(9, personnel.getEmail());
			preparedStatement.setString(10, personnel.getTelephone_secondaire());
			preparedStatement.setString(11, personnel.getEmail_secondaire());
			if ( "".equals(personnel.getDate_naissance())) {
				preparedStatement.setString(12, "1900/01/01");
			} else {
			preparedStatement.setString(12, personnel.getDate_naissance());
			}
			preparedStatement.setInt(13, personnel.getNationalite());
			preparedStatement.setInt(14, personnel.getActivite());
			preparedStatement.setInt(15, personnel.getEntite());
			preparedStatement.setInt(16, personnel.getSignataire());
			preparedStatement.setInt(17, personnel.getUtilisateur());
			preparedStatement.setInt(18, personnel.getMetier());
			preparedStatement.setString(19, "PersonnelDao");
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
			throw new DaoException("Impossible d'ajouter enregistrement avec la table personnelt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible d'ajouter enregistrement avec la table personnelt" + e);
			}
		}

	}

	// LISTER LES ENREGISTREMENTS
	@Override
	public List<Personnel> listerPersonnel() throws DaoException {
		List<Personnel> personnels = new ArrayList<Personnel>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM personnel;");

			while (resultat.next()) {
				Personnel personnel = new Personnel();
				personnel.setId(resultat.getInt("id"));
				personnel.setCivilite(resultat.getString("civilite"));
				personnel.setNom(resultat.getString("nom"));
				personnel.setPrenom(resultat.getString("prenom"));
				personnel.setAdresse(resultat.getString("adresse"));
				personnel.setCode_postal(resultat.getString("code_postal"));
				personnel.setVille(resultat.getString("ville"));
				personnel.setPays(resultat.getString("pays"));
				personnel.setTelephone(resultat.getString("telephone"));
				personnel.setTelephone_secondaire(resultat.getString("telephone_secondaire"));
				personnel.setEmail(resultat.getString("email"));
				personnel.setEmail_secondaire(resultat.getString("email_secondaire"));
				personnel.setDate_naissance(resultat.getString("date_naissance"));
				personnel.setNationalite(resultat.getInt("nationalite"));
				personnel.setActivite(resultat.getInt("activite"));
				personnel.setEntite(resultat.getInt("entite"));
				personnel.setSignataire(resultat.getInt("signataire"));
				personnel.setUtilisateur(resultat.getInt("utilisateur"));
				personnel.setMetier(resultat.getInt("metier"));
				personnels.add(personnel);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de lister enregistrement avec la table personnelt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de lister enregistrement avec la table personnelt" + e);
			}
		}
		return personnels;
	}

    @Override
    public List<Personnel> listerPersonnelClient() throws DaoException {
        List<Personnel> personnels = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT personnel.id, civilite, personnel.nom, personnel.prenom, adresse, code_postal, ville, pays, telephone, email, telephone_secondaire, email_secondaire, date_naissance, nationalite, activite, entite, signataire, utilisateur, metier FROM personnel "
            		+ "inner join entite on entite.id=personnel.entite where entite.nom='Client'"
            		+ ";";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Personnel personnel = new Personnel();
                personnel.setId(resultat.getInt("id"));
				personnel.setCivilite(resultat.getString("civilite"));
				personnel.setNom(resultat.getString("nom"));
				personnel.setPrenom(resultat.getString("prenom"));
				personnel.setAdresse(resultat.getString("adresse"));
				personnel.setCode_postal(resultat.getString("code_postal"));
				personnel.setVille(resultat.getString("ville"));
				personnel.setPays(resultat.getString("pays"));
				personnel.setTelephone(resultat.getString("telephone"));
				personnel.setTelephone_secondaire(resultat.getString("telephone_secondaire"));
				personnel.setEmail(resultat.getString("email"));
				personnel.setEmail_secondaire(resultat.getString("email_secondaire"));
				personnel.setDate_naissance(resultat.getString("date_naissance"));
				personnel.setNationalite(resultat.getInt("nationalite"));
				personnel.setActivite(resultat.getInt("activite"));
				personnel.setEntite(resultat.getInt("entite"));
				personnel.setSignataire(resultat.getInt("signataire"));
				personnel.setUtilisateur(resultat.getInt("utilisateur"));
				personnel.setMetier(resultat.getInt("metier"));
                personnels.add(personnel);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Personnel"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return personnels;
    }

    @Override
    public List<Personnel> listerPersonnelFournisseur() throws DaoException {
        List<Personnel> personnels = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT personnel.id, civilite, personnel.nom, personnel.prenom, adresse, code_postal, ville, pays, telephone, email, telephone_secondaire, email_secondaire, date_naissance, nationalite, activite, entite, signataire, utilisateur, metier FROM personnel "
            		+ "inner join entite on entite.id=personnel.entite where entite.nom='Fournisseur'"
            		+ ";";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Personnel personnel = new Personnel();
                personnel.setId(resultat.getInt("id"));
				personnel.setCivilite(resultat.getString("civilite"));
				personnel.setNom(resultat.getString("nom"));
				personnel.setPrenom(resultat.getString("prenom"));
				personnel.setAdresse(resultat.getString("adresse"));
				personnel.setCode_postal(resultat.getString("code_postal"));
				personnel.setVille(resultat.getString("ville"));
				personnel.setPays(resultat.getString("pays"));
				personnel.setTelephone(resultat.getString("telephone"));
				personnel.setTelephone_secondaire(resultat.getString("telephone_secondaire"));
				personnel.setEmail(resultat.getString("email"));
				personnel.setEmail_secondaire(resultat.getString("email_secondaire"));
				personnel.setDate_naissance(resultat.getString("date_naissance"));
				personnel.setNationalite(resultat.getInt("nationalite"));
				personnel.setActivite(resultat.getInt("activite"));
				personnel.setEntite(resultat.getInt("entite"));
				personnel.setSignataire(resultat.getInt("signataire"));
				personnel.setUtilisateur(resultat.getInt("utilisateur"));
				personnel.setMetier(resultat.getInt("metier"));
                personnels.add(personnel);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Personnel"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return personnels;
    }

    @Override
    public List<Personnel> listerPersonnelSalarie() throws DaoException {
        List<Personnel> personnels = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT personnel.id, civilite, personnel.nom, personnel.prenom, adresse, code_postal, ville, pays, telephone, email, telephone_secondaire, email_secondaire, date_naissance, nationalite, activite, entite, signataire, utilisateur, metier FROM personnel "
            		+ "inner join entite on entite.id=personnel.entite where entite.nom='Salarie'"
            		+ ";";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Personnel personnel = new Personnel();
                personnel.setId(resultat.getInt("id"));
				personnel.setCivilite(resultat.getString("civilite"));
				personnel.setNom(resultat.getString("nom"));
				personnel.setPrenom(resultat.getString("prenom"));
				personnel.setAdresse(resultat.getString("adresse"));
				personnel.setCode_postal(resultat.getString("code_postal"));
				personnel.setVille(resultat.getString("ville"));
				personnel.setPays(resultat.getString("pays"));
				personnel.setTelephone(resultat.getString("telephone"));
				personnel.setTelephone_secondaire(resultat.getString("telephone_secondaire"));
				personnel.setEmail(resultat.getString("email"));
				personnel.setEmail_secondaire(resultat.getString("email_secondaire"));
				personnel.setDate_naissance(resultat.getString("date_naissance"));
				personnel.setNationalite(resultat.getInt("nationalite"));
				personnel.setActivite(resultat.getInt("activite"));
				personnel.setEntite(resultat.getInt("entite"));
				personnel.setSignataire(resultat.getInt("signataire"));
				personnel.setUtilisateur(resultat.getInt("utilisateur"));
				personnel.setMetier(resultat.getInt("metier"));
				personnels.add(personnel);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Personnel"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return personnels;
    }
    @Override
    public List<Personnel> listerPersonnelInterne() throws DaoException {
        List<Personnel> personnels = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT personnel.id, civilite, personnel.nom, personnel.prenom, adresse, code_postal, ville, pays, telephone, email, telephone_secondaire, email_secondaire, date_naissance, nationalite, activite, entite, signataire, utilisateur, metier FROM personnel "
            		+ "inner join entite on entite.id=personnel.entite where entite.nom='Interne'"
            		+ ";";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Personnel personnel = new Personnel();
                personnel.setId(resultat.getInt("id"));
				personnel.setCivilite(resultat.getString("civilite"));
				personnel.setNom(resultat.getString("nom"));
				personnel.setPrenom(resultat.getString("prenom"));
				personnel.setAdresse(resultat.getString("adresse"));
				personnel.setCode_postal(resultat.getString("code_postal"));
				personnel.setVille(resultat.getString("ville"));
				personnel.setPays(resultat.getString("pays"));
				personnel.setTelephone(resultat.getString("telephone"));
				personnel.setTelephone_secondaire(resultat.getString("telephone_secondaire"));
				personnel.setEmail(resultat.getString("email"));
				personnel.setEmail_secondaire(resultat.getString("email_secondaire"));
				personnel.setDate_naissance(resultat.getString("date_naissance"));
				personnel.setNationalite(resultat.getInt("nationalite"));
				personnel.setActivite(resultat.getInt("activite"));
				personnel.setEntite(resultat.getInt("entite"));
				personnel.setSignataire(resultat.getInt("signataire"));
				personnel.setUtilisateur(resultat.getInt("utilisateur"));
				personnel.setMetier(resultat.getInt("metier"));
                personnels.add(personnel);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Personnel"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return personnels;
    }
	// CRUD LIRE UN ENREGISTREMENT SPECIFIQUE VIA SON ID
	@Override
	public Personnel trouverPersonnel(Integer id) throws DaoException {
		Personnel personnel = null;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"SELECT * from personnel where id=?;");
			preparedStatement.setInt(1, id);
			resultat = preparedStatement.executeQuery();
			connexion.commit();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if (resultat.next()) {
				 personnel = new Personnel();
	                personnel.setId(resultat.getInt("id"));
	                personnel.setCivilite(resultat.getString("civilite"));
					personnel.setNom(resultat.getString("nom"));
					personnel.setPrenom(resultat.getString("prenom"));
					personnel.setAdresse(resultat.getString("adresse"));
					personnel.setCode_postal(resultat.getString("code_postal"));
					personnel.setVille(resultat.getString("ville"));
					personnel.setPays(resultat.getString("pays"));
					personnel.setTelephone(resultat.getString("telephone"));
					personnel.setTelephone_secondaire(resultat.getString("telephone_secondaire"));
					personnel.setEmail(resultat.getString("email"));
					personnel.setEmail_secondaire(resultat.getString("email_secondaire"));
					personnel.setDate_naissance(resultat.getString("date_naissance"));
					personnel.setNationalite(resultat.getInt("nationalite"));
					personnel.setActivite(resultat.getInt("activite"));
					personnel.setEntite(resultat.getInt("entite"));
					personnel.setSignataire(resultat.getInt("signataire"));
					personnel.setUtilisateur(resultat.getInt("utilisateur"));
					personnel.setMetier(resultat.getInt("metier"));
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de trouver id enregistrement avec la table personnelt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de trouver id enregistrement avec la table personnelt" + e);
			}
		}
		return personnel;
	}

	// EXISTENCE D'UN ENREGISTREMENT SPECIFIQUE VIA SON NOM
	@Override
	public boolean trouverNomPersonnel(String nom) throws DaoException {
		boolean existe = false;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT * FROM personnel WHERE nom=?;");
			preparedStatement.setString(1, nom);
			resultat = preparedStatement.executeQuery();
			connexion.commit();

			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if (resultat.next()) {
				existe = true;
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de communiquer avec la table personnelt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de communiquer avec la personnelt" + e);
			}
		}

		return existe;
	}

	// CRUD MODIFIER ENREGISTREMENT
	@Override
	public void modifierPersonnel(Personnel personnel) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();

			preparedStatement = connexion.prepareStatement(
					"UPDATE personnel SET civilite=?, nom=?, prenom=?, adresse=?, code_postal=?, ville=?, pays=?, telephone=?, email=?, telephone_secondaire=?, email_secondaire=?, date_naissance=?, nationalite=?, activite=?, entite=?, signataire=?, utilisateur=? where id=?;");
			preparedStatement.setInt(18, personnel.getId());
			preparedStatement.setString(1, personnel.getCivilite());
			preparedStatement.setString(2, personnel.getNom());
			preparedStatement.setString(3, personnel.getPrenom());
			preparedStatement.setString(4, personnel.getAdresse());
			preparedStatement.setString(5, personnel.getCode_postal());
			preparedStatement.setString(6, personnel.getVille());
			preparedStatement.setString(7, personnel.getPays());
			preparedStatement.setString(8, personnel.getTelephone());
			preparedStatement.setString(9, personnel.getEmail());
			preparedStatement.setString(10, personnel.getTelephone_secondaire());
			preparedStatement.setString(11, personnel.getEmail_secondaire());
			preparedStatement.setString(12, personnel.getDate_naissance());
			preparedStatement.setInt(13, personnel.getNationalite());
			preparedStatement.setInt(14, personnel.getActivite());
			preparedStatement.setInt(15, personnel.getEntite());
			preparedStatement.setInt(16, personnel.getSignataire());
			preparedStatement.setInt(17, personnel.getUtilisateur());
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DaoException("Impossible de maj enregistrement avec la table personnelt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de maj enregistrement avec la table personnelt" + e);
			}
		}
	}

	// CRUD COPIER ENREGISTREMENT
	@Override
	public void copierPersonnel(Personnel personnel) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"INSERT INTO personnel( civilite, nom, prenom, adresse, code_postal, ville, pays, telephone, email, telephone_secondaire, email_secondaire, date_naissance, nationalite, activite, entite, signataire, utilisateur, metier, pgmcreation, datecreation, usercreation ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			preparedStatement.setString(1, personnel.getCivilite());
			preparedStatement.setString(2, personnel.getNom());
			preparedStatement.setString(3, personnel.getPrenom());
			preparedStatement.setString(4, personnel.getAdresse());
			preparedStatement.setString(5, personnel.getCode_postal());
			preparedStatement.setString(6, personnel.getVille());
			preparedStatement.setString(7, personnel.getPays());
			preparedStatement.setString(8, personnel.getTelephone());
			preparedStatement.setString(9, personnel.getEmail());
			preparedStatement.setString(10, personnel.getTelephone_secondaire());
			preparedStatement.setString(11, personnel.getEmail_secondaire());
			preparedStatement.setString(12, personnel.getDate_naissance());
			preparedStatement.setInt(13, personnel.getNationalite());
			preparedStatement.setInt(14, personnel.getActivite());
			preparedStatement.setInt(15, personnel.getEntite());
			preparedStatement.setInt(16, personnel.getSignataire());
			preparedStatement.setInt(17, personnel.getUtilisateur());
			preparedStatement.setInt(18, personnel.getMetier());
			preparedStatement.setString(19, "PersonnelDao");
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
			throw new DaoException("Impossible de maj enregistrement avec la table personnelt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de maj enregistrement avec la table personnelt" + e);
			}
		}
	}

	// CRUD SUPPRIMER UN ENREGISTREMENT
	@Override
	public void supprimerPersonnel(Integer id) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM PERSONNEL WHERE ID=?;");
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();
			connexion.commit();

		} catch (SQLException e) {
			throw new DaoException("Impossible de supprimer enregistrement avec la table personnelt" + e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de supprimer enregistrement avec la table personnelt" + e);
			}
		}

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

		 // =================================================================================
	 		// RENOMMER ACTIVITE
	 		// =================================================================================
	 		@Override
	 		public void renommerPersonnel(Personnel personnel) throws DaoException {
	 			Connection connexion = null;
	 			PreparedStatement preparedStatement = null;

	 			try {
	 				connexion = daoFactory.getConnection();

	 				preparedStatement = connexion.prepareStatement(
	 						"UPDATE personnel SET personnel.nom=?, personnel.pgmmodification=?, personnel.datemodification=?,"
	 								+ " personnel.usermodification=? where personnel.id=?;");
	 				preparedStatement.setString(1, personnel.getNom());
	 				preparedStatement.setString(2, "RENOMMER ACTIVITE");
	 				preparedStatement.setString(3, dateTime);
	 				preparedStatement.setString(4, System.getProperty("user.name"));
	 				preparedStatement.setInt(5, personnel.getId());
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
	// RECHERCHE LIGNE PARAMETRE
	// =================================================================================
	@Override
	public List<Map<String, Object>> recherchePersonnels(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
		List<Map<String, Object>> list = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connexion = daoFactory.getConnection();
			String query = "select personnel.id, civilite, personnel.nom, prenom, adresse, code_postal, ville, pays, telephone, email, telephone_secondaire, email_secondaire, date_naissance, nationalite, activite, entite, signataire, utilisateur, metier, nationalite.nom, activite.nom, entite.nom, signataire.nom, utilisateur.nom, metier.nom from personnel "

			+ "left join nationalite on nationalite.id=nationalite left join activite on activite.id=activite left join entite on entite.id=entite left join signataire on signataire.id=signataire left join utilisateur on utilisateur.id=utilisateur left join metier on metier.id=metier "
					+ " ORDER BY " + select_tri + " limit " + offset + ", " + noOfRecords;

			preparedStatement = connexion.prepareStatement(query);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Map<String, Object> entiteFields = new LinkedHashMap<>();
				if (dictionnaire_nom_colonne.containsKey("id")) {
					entiteFields.put("id", rs.getInt("personnel.id"));
				}
				if (dictionnaire_nom_colonne.containsKey("civilite")) {
					entiteFields.put("civilite", rs.getString("civilite"));
				}
				if (dictionnaire_nom_colonne.containsKey("nom")) {
					entiteFields.put("nom", rs.getString("personnel.nom"));
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
					entiteFields.put("nationalite", rs.getString("nationalite.nom"));
				}
				if (dictionnaire_nom_colonne.containsKey("activite")) {
					entiteFields.put("activite", rs.getString("activite.nom"));
				}
				if (dictionnaire_nom_colonne.containsKey("entite")) {
					entiteFields.put("entite", rs.getString("entite.nom"));
				}
				if (dictionnaire_nom_colonne.containsKey("signataire")) {
					entiteFields.put("signataire", rs.getString("signataire.nom"));
				}
				if (dictionnaire_nom_colonne.containsKey("utilisateur")) {
					entiteFields.put("utilisateur", rs.getString("utilisateur.nom"));
				}
				if (dictionnaire_nom_colonne.containsKey("metier")) {
					entiteFields.put("metier", rs.getString("metier.nom"));
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
	// RECHERCHE LIGNE PARAMETRES SUIVANT LIKE
	// =================================================================================
	@Override
	public List<Map<String, Object>> rechercheLikePersonnels(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_entite) {
		List<Map<String, Object>> list = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connexion = daoFactory.getConnection();
			String query = "select id, civilite, nom, prenom, adresse, code_postal, ville, pays, telephone, email, telephone_secondaire, email_secondaire, date_naissance, nationalite, activite, entite, signataire, utilisateur, metier from personnel "
					+ "left join nationalite on nationalite.id=nationalite left join activite on activite.id=activite left join entite on entite.id=entite left join signataire on signataire.id=signataire left join utilisateur on utilisateur.id=utilisateur left join metier on metier.id=metier "
							+ " where " + select_like + " ORDER BY " + select_tri + " limit " + offset + ", " + noOfRecords;
			preparedStatement = connexion.prepareStatement(query);
			//preparedStatement.setInt(1, offset);
			//preparedStatement.setInt(2, noOfRecords);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Map<String, Object> entiteFields = new LinkedHashMap<>();
				if (dictionnaire_nom_colonne.containsKey("id")) {
					entiteFields.put("id", rs.getInt("personnel.id"));
				}
				if (dictionnaire_nom_colonne.containsKey("civilite")) {
					entiteFields.put("civilite", rs.getString("civilite"));
				}
				if (dictionnaire_nom_colonne.containsKey("nom")) {
					entiteFields.put("nom", rs.getString("personnel.nom"));
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
					entiteFields.put("nationalite", rs.getString("nationalite.nom"));
				}
				if (dictionnaire_nom_colonne.containsKey("activite")) {
					entiteFields.put("activite", rs.getString("activite.nom"));
				}
				if (dictionnaire_nom_colonne.containsKey("entite")) {
					entiteFields.put("entite", rs.getString("entite.nom"));
				}
				if (dictionnaire_nom_colonne.containsKey("signataire")) {
					entiteFields.put("signataire", rs.getString("signataire.nom"));
				}
				if (dictionnaire_nom_colonne.containsKey("utilisateur")) {
					entiteFields.put("utilisateur", rs.getString("utilisateur.nom"));
				}
				if (dictionnaire_nom_colonne.containsKey("metier")) {
					entiteFields.put("metier", rs.getString("metier.nom"));
				}

			rs.close();
			}
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
