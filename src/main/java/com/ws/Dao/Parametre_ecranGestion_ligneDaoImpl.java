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

import com.ws.beans.ContratAncien;
import com.ws.beans.Parametre_ecranGestion_ligne;
import com.ws.configuration.DatasourceH;

public class Parametre_ecranGestion_ligneDaoImpl implements Parametre_ecranGestion_ligneDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  Parametre_ecranGestion_ligneDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER LIGNE PARAMETETRE SYSTEME
		// =================================================================================
    @Override
    public void ajouterParametre_ecranGestion_ligne(Parametre_ecranGestion_ligne parametre_ecranGestion_ligne) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion  = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO parametre_ecranGestion_ligne(parametreSysteme, numero_ligne, numero_champ, nom_zone, largeur_colonne, type_colonne, zone_recherche, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, parametre_ecranGestion_ligne.getParametreSysteme());
            preparedStatement.setInt(2, parametre_ecranGestion_ligne.getNumero_ligne());
            preparedStatement.setInt(3, parametre_ecranGestion_ligne.getNumero_champ());
            preparedStatement.setString(4, parametre_ecranGestion_ligne.getNom_zone());
            preparedStatement.setInt(5, parametre_ecranGestion_ligne.getLargeur_colonne());
            preparedStatement.setString(6, parametre_ecranGestion_ligne.getType_colonne());
            preparedStatement.setString(7, parametre_ecranGestion_ligne.getZone_recherche());
            preparedStatement.setString(8, "ParametreSys_entete");
            preparedStatement.setString(9, dateTime);
            preparedStatement.setString(10, System.getProperty("user.name"));
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table Parametre_ecranGestion_ligne"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER LIGNE PARAMETRE
		// =================================================================================
    @Override
    public void modifierParametre_ecranGestion_ligne(Parametre_ecranGestion_ligne parametre_ecranGestion_ligne) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE parametre_ecranGestion_ligne SET parametreSysteme=?, numero_ligne=?, numero_champ=?, nom_zone=?, largeur_colonne=?, type_colonne=?, zone_recherche=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, parametre_ecranGestion_ligne.getParametreSysteme());
            preparedStatement.setInt(2, parametre_ecranGestion_ligne.getNumero_ligne());
            preparedStatement.setInt(3, parametre_ecranGestion_ligne.getNumero_champ());
            preparedStatement.setString(4, parametre_ecranGestion_ligne.getNom_zone());
            preparedStatement.setInt(5, parametre_ecranGestion_ligne.getLargeur_colonne());
            preparedStatement.setString(6, parametre_ecranGestion_ligne.getType_colonne());
            preparedStatement.setString(7, parametre_ecranGestion_ligne.getZone_recherche());
            preparedStatement.setString(8, "ParametreSys_entete");
            preparedStatement.setString(9, dateTime);
            preparedStatement.setString(10, System.getProperty("user.name"));
            preparedStatement.setInt(11, parametre_ecranGestion_ligne.getId());
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table Parametre_ecranGestion_ligne"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER LIGNE PARAMETRE
  		// =================================================================================
    @Override
    public void copierParametre_ecranGestion_ligne(Parametre_ecranGestion_ligne parametre_ecranGestion_ligne) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO parametre_ecranGestion_ligne(parametreSysteme, numero_ligne, numero_champ, nom_zone, largeur_colonne, type_colonne, zone_recherche, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, parametre_ecranGestion_ligne.getParametreSysteme());
            preparedStatement.setInt(2, parametre_ecranGestion_ligne.getNumero_ligne());
            preparedStatement.setInt(3, parametre_ecranGestion_ligne.getNumero_champ());
            preparedStatement.setString(4, parametre_ecranGestion_ligne.getNom_zone());
            preparedStatement.setInt(5, parametre_ecranGestion_ligne.getLargeur_colonne());
            preparedStatement.setString(6, parametre_ecranGestion_ligne.getType_colonne());
            preparedStatement.setString(7, parametre_ecranGestion_ligne.getZone_recherche());
            preparedStatement.setString(8, "ParametreSys_entete");
            preparedStatement.setString(9, dateTime);
            preparedStatement.setString(10, System.getProperty("user.name"));
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table Parametre_ecranGestion_ligne"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER LIGNE PARAMETRE
  		// =================================================================================

    @Override
    public void supprimerParametre_ecranGestion_ligne(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM parametre_ecranGestion_ligne WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table Parametre_ecranGestion_ligne"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER LIGNE PARAMETRE
 		// =================================================================================
 		@Override
 		public void renommerParametre_ecranGestion_ligne(Parametre_ecranGestion_ligne parametre_ecranGestion_ligne) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();


 				 String sql ="UPDATE parametre_ecranGestion_ligne SET nom_zone=?, pgmmodification=?,datemodification=?, usermodification=? where id=?;";
 				preparedStatement = connexion.prepareStatement(sql);
 	            preparedStatement.setString(1, parametre_ecranGestion_ligne.getNom_zone());
 	            preparedStatement.setString(2, "ParametreSys_entete");
 	            preparedStatement.setString(3, dateTime);
 	            preparedStatement.setString(4, System.getProperty("user.name"));
 	            preparedStatement.setInt(5, parametre_ecranGestion_ligne.getId());
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
    public List<Parametre_ecranGestion_ligne> listerParametre_ecranGestion_ligne() throws DaoException {
        List<Parametre_ecranGestion_ligne> parametre_ecranGestion_lignes = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, parametreSysteme, numero_ligne, numero_champ, nom_zone, largeur_colonne, type_colonne, zone_recherche FROM parametre_ecranGestion_ligne order by numero_ligne, numero_champ;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Parametre_ecranGestion_ligne parametre_ecranGestion_ligne = new Parametre_ecranGestion_ligne();
                parametre_ecranGestion_ligne.setId(resultat.getInt("id"));
                parametre_ecranGestion_ligne.setParametreSysteme(resultat.getInt("parametreSysteme"));
                parametre_ecranGestion_ligne.setNumero_ligne(resultat.getInt("numero_ligne"));
                parametre_ecranGestion_ligne.setNumero_champ(resultat.getInt("numero_champ"));
                parametre_ecranGestion_ligne.setNom_zone(resultat.getString("nom_zone"));
                parametre_ecranGestion_ligne.setLargeur_colonne(resultat.getInt("largeur_colonne"));
                parametre_ecranGestion_ligne.setType_colonne(resultat.getString("type_colonne"));
                parametre_ecranGestion_ligne.setZone_recherche(resultat.getString("zone_recherche"));
                parametre_ecranGestion_lignes.add(parametre_ecranGestion_ligne);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Parametre_ecranGestion_ligne"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return parametre_ecranGestion_lignes;
    }

    	// =================================================================================
		// TROUVER LIGNE PARAMETRE PAR ID
		// =================================================================================
    @Override
    public Parametre_ecranGestion_ligne trouverParametre_ecranGestion_ligne(Integer id) throws DaoException {
        Parametre_ecranGestion_ligne parametre_ecranGestion_ligne = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT parametre_ecranGestion_ligne.id, parametre_ecranGestion_ligne.parametreSysteme, numero_ligne, numero_champ, nom_zone, largeur_colonne, type_colonne, zone_recherche, parametresysteme.nom, parametre_ecranGestion_ligne.usermodification, parametre_ecranGestion_ligne.datemodification, parametre_ecranGestion_ligne.pgmmodification, parametre_ecranGestion_ligne.usercreation, parametre_ecranGestion_ligne.datecreation, parametre_ecranGestion_ligne.pgmcreation FROM parametre_ecranGestion_ligne inner join parametreSysteme on parametreSysteme.id=parametre_ecranGestion_ligne.parametreSysteme WHERE parametre_ecranGestion_ligne.id=? order by numero_ligne, numero_champ;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                parametre_ecranGestion_ligne = new Parametre_ecranGestion_ligne();
                parametre_ecranGestion_ligne.setId(id);
                parametre_ecranGestion_ligne.setParametreSysteme(resultat.getInt("parametreSysteme"));
                parametre_ecranGestion_ligne.setNumero_ligne(resultat.getInt("numero_ligne"));
                parametre_ecranGestion_ligne.setNumero_champ(resultat.getInt("numero_champ"));
                parametre_ecranGestion_ligne.setNom_zone(resultat.getString("nom_zone"));
                parametre_ecranGestion_ligne.setLargeur_colonne(resultat.getInt("largeur_colonne"));
                parametre_ecranGestion_ligne.setType_colonne(resultat.getString("type_colonne"));
                parametre_ecranGestion_ligne.setZone_recherche(resultat.getString("zone_recherche"));
                parametre_ecranGestion_ligne.setUsermodification(resultat.getString("usermodification"));
                parametre_ecranGestion_ligne.setDatemodification(resultat.getDate("datemodification"));
                parametre_ecranGestion_ligne.setPgmmodification(resultat.getString("pgmmodification"));
                parametre_ecranGestion_ligne.setUsercreation(resultat.getString("usercreation"));
                parametre_ecranGestion_ligne.setDatecreation(resultat.getDate("datecreation"));
                parametre_ecranGestion_ligne.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table Parametre_ecranGestion_ligne"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return parametre_ecranGestion_ligne;
    }

    	// =================================================================================
		// TROUVER LIGNE PARAMETRE PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomParametre_ecranGestion_ligne(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM parametre_ecranGestion_ligne WHERE nom_zone=? order by numero_ligne, numero_champ;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table Parametre_ecranGestion_ligne"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE LIGNE PARAMETRE
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheParametre_ecranGestion_lignes(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS parametre_ecranGestion_ligne.id, parametre_ecranGestion_ligne.parametreSysteme, numero_ligne, numero_champ, nom_zone, largeur_colonne, type_colonne, zone_recherche, parametreSysteme.nom  FROM parametre_ecranGestion_ligne inner join parametreSysteme on parametreSysteme.id=parametre_ecranGestion_ligne.parametreSysteme ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Map<String, Object> entiteFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                 entiteFields.put("id", rs.getInt("id"));
                }
                if (dictionnaire_nom_colonne.containsKey("parametreSysteme")) {
                entiteFields.put("parametreSysteme", rs.getString("nom"));
                }
                if (dictionnaire_nom_colonne.containsKey("numero_ligne")) {
                entiteFields.put("numero_ligne", rs.getInt("numero_ligne"));
                }
                if (dictionnaire_nom_colonne.containsKey("numero_champ")) {
                entiteFields.put("numero_champ", rs.getInt("numero_champ"));
                }
                if (dictionnaire_nom_colonne.containsKey("nom_zone")) {
                entiteFields.put("nom_zone", rs.getString("nom_zone"));
                }
                if (dictionnaire_nom_colonne.containsKey("largeur_colonne")) {
                entiteFields.put("largeur_colonne", rs.getInt("largeur_colonne"));
                }
                if (dictionnaire_nom_colonne.containsKey("type_colonne")) {
                    entiteFields.put("type_colonne", rs.getString("type_colonne"));
                    }
                if (dictionnaire_nom_colonne.containsKey("zone_recherche")) {
                entiteFields.put("zone_recherche", rs.getString("zone_recherche"));
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
    public List<Map<String, Object>> rechercheLikeParametre_ecranGestion_lignes(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS parametre_ecranGestion_ligne.id, parametre_ecranGestion_ligne.parametreSysteme, numero_ligne, numero_champ, nom_zone, largeur_colonne, type_colonne, zone_recherche, parametreSysteme.nom  FROM parametre_ecranGestion_ligne inner join parametreSysteme on parametreSysteme.id=parametre_ecranGestion_ligne.parametreSysteme WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> entiteFields = new LinkedHashMap<>();
            	if (dictionnaire_nom_colonne.containsKey("id")) {
                    entiteFields.put("id", rs.getInt("id"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("parametreSysteme")) {
                    entiteFields.put("parametreSysteme", rs.getString("nom"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("numero_ligne")) {
                    entiteFields.put("numero_ligne", rs.getInt("numero_ligne"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("numero_champ")) {
                    entiteFields.put("numero_champ", rs.getInt("numero_champ"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("nom_zone")) {
                    entiteFields.put("nom_zone", rs.getString("nom_zone"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("largeur_colonne")) {
                    entiteFields.put("largeur_colonne", rs.getInt("largeur_colonne"));
                    }
                    if (dictionnaire_nom_colonne.containsKey("type_colonne")) {
                        entiteFields.put("type_colonne", rs.getString("type_colonne"));
                        }
                    if (dictionnaire_nom_colonne.containsKey("zone_recherche")) {
                    entiteFields.put("zone_recherche", rs.getString("zone_recherche"));
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