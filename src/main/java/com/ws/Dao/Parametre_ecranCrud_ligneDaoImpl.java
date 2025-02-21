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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ws.beans.Contrat;
import com.ws.beans.Parametre_ecranCrud_ligne;
import com.ws.configuration.DatasourceH;

public class Parametre_ecranCrud_ligneDaoImpl implements Parametre_ecranCrud_ligneDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  Parametre_ecranCrud_ligneDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER LIGNE PARAMETETRE SYSTEME
		// =================================================================================
    @Override
    public void ajouterParametre_ecranCrud_ligne(Parametre_ecranCrud_ligne parametre_ecranCrud_ligne) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion  = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO parametre_ecranCrud_ligne(parametreSysteme, numero_ligne, numero_champ, nom_programme, nom_champ, required, readonly, disabled, minlength, maxlength, type, step, placeholder, type_zone, largeur_libelle, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, parametre_ecranCrud_ligne.getParametreSysteme());
            preparedStatement.setInt(2, parametre_ecranCrud_ligne.getNumero_ligne());
            preparedStatement.setInt(3, parametre_ecranCrud_ligne.getNumero_champ());
            preparedStatement.setString(4, parametre_ecranCrud_ligne.getNom_programme());
            preparedStatement.setString(5, parametre_ecranCrud_ligne.getNom_champ());
            preparedStatement.setString(6, parametre_ecranCrud_ligne.getRequired());
            preparedStatement.setString(7, parametre_ecranCrud_ligne.getReadonly());
            preparedStatement.setString(8, parametre_ecranCrud_ligne.getDisabled());
            preparedStatement.setInt(9, parametre_ecranCrud_ligne.getMinlength());
            preparedStatement.setInt(10, parametre_ecranCrud_ligne.getMaxlength());
            preparedStatement.setString(11, parametre_ecranCrud_ligne.getType());
            preparedStatement.setString(12, parametre_ecranCrud_ligne.getStep());
            preparedStatement.setString(13, parametre_ecranCrud_ligne.getPlaceholder());
            preparedStatement.setString(14, parametre_ecranCrud_ligne.getType_zone());
            preparedStatement.setInt(15, parametre_ecranCrud_ligne.getLargeur_libelle());
            preparedStatement.setString(16, "ParametreSys_entete");
            preparedStatement.setString(17, dateTime);
            preparedStatement.setString(18, System.getProperty("user.name"));
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table Parametre_ecranCrud_ligne"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER LIGNE PARAMETRE
		// =================================================================================
    @Override
    public void modifierParametre_ecranCrud_ligne(Parametre_ecranCrud_ligne parametre_ecranCrud_ligne) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE parametre_ecranCrud_ligne SET parametreSysteme=?, numero_ligne=?, numero_champ=?, nom_programme=?, nom_champ=?, required=?, readonly=?, disabled=?, minlength=?, maxlength=?, type=?, step=?, placeholder=?, type_zone=?, largeur_libelle=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, parametre_ecranCrud_ligne.getParametreSysteme());
            preparedStatement.setInt(2, parametre_ecranCrud_ligne.getNumero_ligne());
            preparedStatement.setInt(3, parametre_ecranCrud_ligne.getNumero_champ());
            preparedStatement.setString(4, parametre_ecranCrud_ligne.getNom_programme());
            preparedStatement.setString(5, parametre_ecranCrud_ligne.getNom_champ());
            preparedStatement.setString(6, parametre_ecranCrud_ligne.getRequired());
            preparedStatement.setString(7, parametre_ecranCrud_ligne.getReadonly());
            preparedStatement.setString(8, parametre_ecranCrud_ligne.getDisabled());
            preparedStatement.setInt(9, parametre_ecranCrud_ligne.getMinlength());
            preparedStatement.setInt(10, parametre_ecranCrud_ligne.getMaxlength());
            preparedStatement.setString(11, parametre_ecranCrud_ligne.getType());
            preparedStatement.setString(12, parametre_ecranCrud_ligne.getStep());
            preparedStatement.setString(13, parametre_ecranCrud_ligne.getPlaceholder());
            preparedStatement.setString(14, parametre_ecranCrud_ligne.getType_zone());
            preparedStatement.setInt(15, parametre_ecranCrud_ligne.getLargeur_libelle());
            preparedStatement.setString(16, "ParametreSys_entete");
            preparedStatement.setString(17, dateTime);
            preparedStatement.setString(18, System.getProperty("user.name"));
            preparedStatement.setInt(19, parametre_ecranCrud_ligne.getId());
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table Parametre_ecranCrud_ligne"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER LIGNE PARAMETRE
  		// =================================================================================
    @Override
    public void copierParametre_ecranCrud_ligne(Parametre_ecranCrud_ligne parametre_ecranCrud_ligne) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO parametre_ecranCrud_ligne(parametreSysteme, numero_ligne, numero_champ, nom_programme, nom_champ, required, readonly, disabled, minlength, maxlength, type, step, placeholder, type_zone, largeur_libelle, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, parametre_ecranCrud_ligne.getParametreSysteme());
            preparedStatement.setInt(2, parametre_ecranCrud_ligne.getNumero_ligne());
            preparedStatement.setInt(3, parametre_ecranCrud_ligne.getNumero_champ());
            preparedStatement.setString(4, parametre_ecranCrud_ligne.getNom_programme());
            preparedStatement.setString(5, parametre_ecranCrud_ligne.getNom_champ());
            preparedStatement.setString(6, parametre_ecranCrud_ligne.getRequired());
            preparedStatement.setString(7, parametre_ecranCrud_ligne.getReadonly());
            preparedStatement.setString(8, parametre_ecranCrud_ligne.getDisabled());
            preparedStatement.setInt(9, parametre_ecranCrud_ligne.getMinlength());
            preparedStatement.setInt(10, parametre_ecranCrud_ligne.getMaxlength());
            preparedStatement.setString(11, parametre_ecranCrud_ligne.getType());
            preparedStatement.setString(12, parametre_ecranCrud_ligne.getStep());
            preparedStatement.setString(13, parametre_ecranCrud_ligne.getPlaceholder());
            preparedStatement.setString(14, parametre_ecranCrud_ligne.getType_zone());
            preparedStatement.setInt(15, parametre_ecranCrud_ligne.getLargeur_libelle());
            preparedStatement.setString(16, "ParametreSys_entete");
            preparedStatement.setString(17, dateTime);
            preparedStatement.setString(18, System.getProperty("user.name"));
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table Parametre_ecranCrud_ligne"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER LIGNE PARAMETRE
  		// =================================================================================

    @Override
    public void supprimerParametre_ecranCrud_ligne(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM parametre_ecranCrud_ligne WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table Parametre_ecranCrud_ligne"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER LIGNE PARAMETRE
 		// =================================================================================
 		@Override
 		public void renommerParametre_ecranCrud_ligne(Parametre_ecranCrud_ligne parametre_ecranCrud_ligne) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();


 				 String sql ="UPDATE parametre_ecranCrud_ligne SET nom_champ=?, pgmmodification=?,datemodification=?, usermodification=? where id=?;";
 				preparedStatement = connexion.prepareStatement(sql);
 	            preparedStatement.setString(1, parametre_ecranCrud_ligne.getNom_champ());
 	            preparedStatement.setString(2, "ParametreSys_entete");
 	            preparedStatement.setString(3, dateTime);
 	            preparedStatement.setString(4, System.getProperty("user.name"));
 	            preparedStatement.setInt(5, parametre_ecranCrud_ligne.getId());
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
  	// LISTER DES LIGNE PARAMETRES
  	// =================================================================================
    @Override
    public List<Parametre_ecranCrud_ligne> listerParametre_ecranCrud_ligne() throws DaoException {
        List<Parametre_ecranCrud_ligne> parametre_ecranCrud_lignes = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, parametreSysteme, nom_programme, numero_ligne, numero_champ, nom_champ, required, readonly, disabled, minlength, maxlength, type, step, placeholder, type_zone, largeur_libelle FROM parametre_ecranCrud_ligne order bu numero_ligne, numero_champ;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Parametre_ecranCrud_ligne parametre_ecranCrud_ligne = new Parametre_ecranCrud_ligne();
                parametre_ecranCrud_ligne.setId(resultat.getInt("id"));
                parametre_ecranCrud_ligne.setParametreSysteme(resultat.getInt("parametreSysteme"));
                parametre_ecranCrud_ligne.setNom_programme(resultat.getString("nom_programme"));
                parametre_ecranCrud_ligne.setNumero_ligne(resultat.getInt("numero_ligne"));
                parametre_ecranCrud_ligne.setNumero_champ(resultat.getInt("numero_champ"));
                parametre_ecranCrud_ligne.setNom_champ(resultat.getString("nom_champ"));
                parametre_ecranCrud_ligne.setRequired(resultat.getString("required"));
                parametre_ecranCrud_ligne.setReadonly(resultat.getString("readonly"));
                parametre_ecranCrud_ligne.setDisabled(resultat.getString("disabled"));
                parametre_ecranCrud_ligne.setMinlength(resultat.getInt("minlength"));
                parametre_ecranCrud_ligne.setMaxlength(resultat.getInt("maxlength"));
                parametre_ecranCrud_ligne.setType(resultat.getString("type"));
                parametre_ecranCrud_ligne.setStep(resultat.getString("step"));
                parametre_ecranCrud_ligne.setPlaceholder(resultat.getString("placeholder"));
                parametre_ecranCrud_ligne.setType_zone(resultat.getString("type_zone"));
                parametre_ecranCrud_ligne.setLargeur_libelle(resultat.getInt("largeur_libelle"));
                parametre_ecranCrud_lignes.add(parametre_ecranCrud_ligne);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Parametre_ecranCrud_ligne"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return parametre_ecranCrud_lignes;
    }

    	// =================================================================================
		// TROUVER LIGNE PARAMETRE PAR ID
		// =================================================================================
    @Override
    public Parametre_ecranCrud_ligne trouverParametre_ecranCrud_ligne(Integer id) throws DaoException {
        Parametre_ecranCrud_ligne parametre_ecranCrud_ligne = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT parametre_ecranCrud_ligne.id, parametre_ecranCrud_ligne.parametreSysteme, numero_ligne, numero_champ, nom_programme, nom_champ, required, readonly, disabled, minlength, maxlength, type, step, placeholder, type_zone, largeur_libelle, parametreSysteme.nom, parametre_ecranCrud_ligne.pgmcreation, parametre_ecranCrud_ligne.datecreation, parametre_ecranCrud_ligne.usercreation, parametre_ecranCrud_ligne.pgmmodification, parametre_ecranCrud_ligne.datemodification, parametre_ecranCrud_ligne.usermodification FROM parametre_ecranCrud_ligne inner join parametreSysteme on parametreSysteme.id=parametre_ecranCrud_ligne.parametreSysteme WHERE parametre_ecranCrud_ligne.id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                parametre_ecranCrud_ligne = new Parametre_ecranCrud_ligne();
                parametre_ecranCrud_ligne.setId(id);
                parametre_ecranCrud_ligne.setParametreSysteme(resultat.getInt("parametreSysteme"));
                parametre_ecranCrud_ligne.setNumero_ligne(resultat.getInt("numero_ligne"));
                parametre_ecranCrud_ligne.setNumero_champ(resultat.getInt("numero_champ"));
                parametre_ecranCrud_ligne.setNom_programme(resultat.getString("nom_programme"));
                parametre_ecranCrud_ligne.setNom_champ(resultat.getString("nom_champ"));
                parametre_ecranCrud_ligne.setRequired(resultat.getString("required"));
                parametre_ecranCrud_ligne.setReadonly(resultat.getString("readonly"));
                parametre_ecranCrud_ligne.setDisabled(resultat.getString("disabled"));
                parametre_ecranCrud_ligne.setMinlength(resultat.getInt("minlength"));
                parametre_ecranCrud_ligne.setMaxlength(resultat.getInt("maxlength"));
                parametre_ecranCrud_ligne.setType(resultat.getString("type"));
                parametre_ecranCrud_ligne.setStep(resultat.getString("step"));
                parametre_ecranCrud_ligne.setPlaceholder(resultat.getString("placeholder"));
                parametre_ecranCrud_ligne.setType_zone(resultat.getString("type_zone"));
                parametre_ecranCrud_ligne.setLargeur_libelle(resultat.getInt("largeur_libelle"));
                parametre_ecranCrud_ligne.setUsermodification(resultat.getString("usermodification"));
                parametre_ecranCrud_ligne.setDatemodification(resultat.getDate("datemodification"));
                parametre_ecranCrud_ligne.setPgmmodification(resultat.getString("pgmmodification"));
                parametre_ecranCrud_ligne.setUsercreation(resultat.getString("usercreation"));
                parametre_ecranCrud_ligne.setDatecreation(resultat.getDate("datecreation"));
                parametre_ecranCrud_ligne.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table Parametre_ecranCrud_ligne"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return parametre_ecranCrud_ligne;
    }

    	// =================================================================================
		// TROUVER LIGNE PARAMETRE PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomParametre_ecranCrud_ligne(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM parametre_ecranCrud_ligne WHERE nom_zone=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table Parametre_ecranCrud_ligne"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE LIGNE PARAMETRE
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheParametre_ecranCrud_lignes(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS parametre_ecranCrud_ligne.id, parametre_ecranCrud_ligne.parametreSysteme, numero_ligne, numero_champ, nom_programme, nom_champ, required, readonly, disabled, minlength, maxlength, type, step, placeholder, type_zone, largeur_libelle, parametreSysteme.nom FROM parametre_ecranCrud_ligne inner join parametreSysteme on parametreSysteme.id=parametre_ecranCrud_ligne.parametreSysteme ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            System.out.println(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Map<String, Object> entiteFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                    entiteFields.put("id", rs.getInt("id"));
                    }
                if (dictionnaire_nom_colonne.containsKey("parametreSysteme")) {
                entiteFields.put("parametreSysteme", rs.getString("nom"));
                }
                if (dictionnaire_nom_colonne.containsKey("nom_programme")) {
                entiteFields.put("nom_programme", rs.getString("nom_programme"));
                }
                if (dictionnaire_nom_colonne.containsKey("numero_ligne")) {
                entiteFields.put("numero_ligne", rs.getInt("numero_ligne"));
                }
                if (dictionnaire_nom_colonne.containsKey("numero_champ")) {
                entiteFields.put("numero_champ", rs.getInt("numero_champ"));
                }
                if (dictionnaire_nom_colonne.containsKey("nom_champ")) {
                entiteFields.put("nom_champ", rs.getString("nom_champ"));
                }
                if (dictionnaire_nom_colonne.containsKey("required")) {
                entiteFields.put("required", rs.getString("required"));
                }
                if (dictionnaire_nom_colonne.containsKey("readonly")) {
                entiteFields.put("readonly", rs.getString("readonly"));
                }
                if (dictionnaire_nom_colonne.containsKey("disabled")) {
                entiteFields.put("disabled", rs.getString("disabled"));
                }
                if (dictionnaire_nom_colonne.containsKey("minlength")) {
                entiteFields.put("minlength", rs.getInt("minlength"));
                }
                if (dictionnaire_nom_colonne.containsKey("maxlength")) {
                entiteFields.put("maxlength", rs.getInt("maxlength"));
                }
                if (dictionnaire_nom_colonne.containsKey("type")) {
                entiteFields.put("type", rs.getString("type"));
                }
                if (dictionnaire_nom_colonne.containsKey("step")) {
                entiteFields.put("step", rs.getString("step"));
                }
                if (dictionnaire_nom_colonne.containsKey("placeholder")) {
                entiteFields.put("placeholder", rs.getString("placeholder"));
                }
                if (dictionnaire_nom_colonne.containsKey("type_zone")) {
                entiteFields.put("type_zone", rs.getString("type_zone"));
                }
                if (dictionnaire_nom_colonne.containsKey("largeur_libelle")) {
                entiteFields.put("largeur_libelle", rs.getInt("largeur_libelle"));
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
    public List<Map<String, Object>> rechercheLikeParametre_ecranCrud_lignes(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS parametre_ecranCrud_ligne.id, parametre_ecranCrud_ligne.parametreSysteme, numero_ligne, numero_champ, nom_programme, nom_champ, required, readonly, disabled, minlength, maxlength, type, step, placeholder, type_zone, largeur_libelle, parametreSysteme.nom  FROM parametre_ecranCrud_ligne inner join parametreSysteme on parametreSysteme.id=parametre_ecranCrud_ligne.parametreSysteme WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            System.out.println(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> entiteFields = new LinkedHashMap<>();
                     if (dictionnaire_nom_colonne.containsKey("id")) {
                     entiteFields.put("id", rs.getInt("id"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("parametreSysteme")) {
                         entiteFields.put("parametreSysteme", rs.getString("nom"));
                         }
                     if (dictionnaire_nom_colonne.containsKey("nom_programme")) {
                     entiteFields.put("nom_programme", rs.getString("nom_programme"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("numero_ligne")) {
                     entiteFields.put("numero_ligne", rs.getInt("numero_ligne"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("numero_champ")) {
                     entiteFields.put("numero_champ", rs.getInt("numero_champ"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("nom_champ")) {
                     entiteFields.put("nom_champ", rs.getString("nom_champ"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("required")) {
                     entiteFields.put("required", rs.getString("required"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("readonly")) {
                     entiteFields.put("readonly", rs.getString("readonly"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("disabled")) {
                     entiteFields.put("disabled", rs.getString("disabled"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("minlength")) {
                     entiteFields.put("minlength", rs.getInt("minlength"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("maxlength")) {
                     entiteFields.put("maxlength", rs.getInt("maxlength"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("type")) {
                     entiteFields.put("type", rs.getString("type"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("step")) {
                     entiteFields.put("step", rs.getString("step"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("placeholder")) {
                     entiteFields.put("placeholder", rs.getString("placeholder"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("type_zone")) {
                     entiteFields.put("type_zone", rs.getString("type_zone"));
                     }
                     if (dictionnaire_nom_colonne.containsKey("largeur_libelle")) {
                     entiteFields.put("largeur_libelle", rs.getInt("largeur_libelle"));
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