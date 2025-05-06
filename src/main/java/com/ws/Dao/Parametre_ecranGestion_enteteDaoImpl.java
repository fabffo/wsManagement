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
import com.ws.beans.Parametre_ecranGestion_entete;
import com.ws.configuration.DatasourceH;

public class Parametre_ecranGestion_enteteDaoImpl implements Parametre_ecranGestion_enteteDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  Parametre_ecranGestion_enteteDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER ENTETE PARAMETETRE SYSTEME
		// =================================================================================
    @Override
    public void ajouterParametre_ecranGestion_entete(Parametre_ecranGestion_entete parametre_ecranGestion_entete) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion  = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO parametre_ecranGestion_entete( parametreSysteme, pasPage, nbrEnrgPage, type_entete, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, parametre_ecranGestion_entete.getParametreSysteme());
            preparedStatement.setInt(2, parametre_ecranGestion_entete.getPasPage());
            preparedStatement.setInt(3, parametre_ecranGestion_entete.getNbrEnrgPage());
            preparedStatement.setString(4, parametre_ecranGestion_entete.getType_entete());
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table Parametre_ecranGestion_entete"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER ENTETE PARAMETRE
		// =================================================================================
    @Override
    public void modifierParametre_ecranGestion_entete(Parametre_ecranGestion_entete parametre_ecranGestion_entete) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE parametre_ecranGestion_entete SET parametreSysteme=?, pasPage=?, nbrEnrgPage=?, type_entete=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, parametre_ecranGestion_entete.getParametreSysteme());
            preparedStatement.setInt(2, parametre_ecranGestion_entete.getPasPage());
            preparedStatement.setInt(3, parametre_ecranGestion_entete.getNbrEnrgPage());
            preparedStatement.setString(4, parametre_ecranGestion_entete.getType_entete());
            preparedStatement.setString(5, "ParametreSys_entete");
            preparedStatement.setString(6, dateTime);
            preparedStatement.setString(7, System.getProperty("user.name"));
            preparedStatement.setInt(8, parametre_ecranGestion_entete.getId());
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table Parametre_ecranGestion_entete"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER ENTETE PARAMETRE
  		// =================================================================================
    @Override
    public void copierParametre_ecranGestion_entete(Parametre_ecranGestion_entete parametre_ecranGestion_entete) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO parametre_ecranGestion_entete(nom, parametreSysteme, pasPage, nbrEnrgPage, type_entete, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, parametre_ecranGestion_entete.getParametreSysteme());
            preparedStatement.setInt(2, parametre_ecranGestion_entete.getPasPage());
            preparedStatement.setInt(3, parametre_ecranGestion_entete.getNbrEnrgPage());
            preparedStatement.setString(4, parametre_ecranGestion_entete.getType_entete());
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table Parametre_ecranGestion_entete"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER ENTETE PARAMETRE
  		// =================================================================================

    @Override
    public void supprimerParametre_ecranGestion_entete(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM parametre_ecranGestion_entete WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table Parametre_ecranGestion_entete"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER ENTETE PARAMETRE
 		// =================================================================================
 		@Override
 		public void renommerParametre_ecranGestion_entete(Parametre_ecranGestion_entete parametre_ecranGestion_entete) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;

 			try {
 				connexion = daoFactory.getConnection();

 				preparedStatement = connexion.prepareStatement(
 						"UPDATE parametre_ecranGestion_entete SET parametre_ecranGestion_entete.pgmmodification=?, parametre_ecranGestion_entete.datemodification=?,"
 								+ " parametre_ecranGestion_entete.usermodification=? where parametre_ecranGestion_entete.id=?;");
 				preparedStatement.setString(2, "ParametreSys_entete");
 				preparedStatement.setString(3, dateTime);
 				preparedStatement.setString(4, System.getProperty("user.name"));
 				preparedStatement.setInt(5, parametre_ecranGestion_entete.getId());
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
    public List<Parametre_ecranGestion_entete> listerParametre_ecranGestion_entete() throws DaoException {
        List<Parametre_ecranGestion_entete> parametre_ecranGestion_entetes = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT parametre_ecranGestion_entete.id, nom, parametre_ecranGestion_entete.parametreSysteme, pasPage, nbrEnrgPage, type_entete FROM parametre_ecranGestion_entete;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Parametre_ecranGestion_entete parametre_ecranGestion_entete = new Parametre_ecranGestion_entete();
                parametre_ecranGestion_entete.setId(resultat.getInt("id"));
                parametre_ecranGestion_entete.setParametreSysteme(resultat.getString("parametreSysteme"));
                parametre_ecranGestion_entete.setPasPage(resultat.getInt("pasPage"));
                parametre_ecranGestion_entete.setNbrEnrgPage(resultat.getInt("nbrEnrgPage"));
                parametre_ecranGestion_entete.setType_entete(resultat.getString("type_entete"));
                parametre_ecranGestion_entetes.add(parametre_ecranGestion_entete);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Parametre_ecranGestion_entete"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return parametre_ecranGestion_entetes;
    }

    	// =================================================================================
		// TROUVER ENTETE PARAMETRE PAR ID
		// =================================================================================
    @Override
    public Parametre_ecranGestion_entete trouverParametre_ecranGestion_entete(Integer id) throws DaoException {
        Parametre_ecranGestion_entete parametre_ecranGestion_entete = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT parametre_ecranGestion_entete.id, parametre_ecranGestion_entete.parametreSysteme, pasPage, nbrEnrgPage, type_entete, parametre_ecranGestion_entete.usermodification, parametre_ecranGestion_entete.datemodification, parametre_ecranGestion_entete.pgmmodification, parametre_ecranGestion_entete.usercreation, parametre_ecranGestion_entete.datecreation, parametre_ecranGestion_entete.pgmcreation FROM parametre_ecranGestion_entete inner join parametreSysteme on parametreSysteme.id=parametre_ecranGestion_entete.parametreSysteme WHERE parametre_ecranGestion_entete.id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, id.toString());
             resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                parametre_ecranGestion_entete = new Parametre_ecranGestion_entete();
                parametre_ecranGestion_entete.setId(id);
                parametre_ecranGestion_entete.setParametreSysteme(resultat.getString("parametre_ecranGestion_entete.parametreSysteme"));
                parametre_ecranGestion_entete.setPasPage(resultat.getInt("pasPage"));
                parametre_ecranGestion_entete.setNbrEnrgPage(resultat.getInt("nbrEnrgPage"));
                parametre_ecranGestion_entete.setType_entete(resultat.getString("type_entete"));
                parametre_ecranGestion_entete.setUsermodification(resultat.getString("parametre_ecranGestion_entete.usermodification"));
                parametre_ecranGestion_entete.setDatemodification(resultat.getDate("parametre_ecranGestion_entete.datemodification"));
                parametre_ecranGestion_entete.setPgmmodification(resultat.getString("parametre_ecranGestion_entete.pgmmodification"));
                parametre_ecranGestion_entete.setUsercreation(resultat.getString("parametre_ecranGestion_entete.usercreation"));
                parametre_ecranGestion_entete.setDatecreation(resultat.getDate("parametre_ecranGestion_entete.datecreation"));
                parametre_ecranGestion_entete.setPgmcreation(resultat.getString("parametre_ecranGestion_entete.pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table Parametre_ecranGestion_entete"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return parametre_ecranGestion_entete;
    }

    	// =================================================================================
		// TROUVER ENTETE PARAMETRE PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomParametre_ecranGestion_entete(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM parametre_ecranGestion_entete WHERE nom=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table Parametre_ecranGestion_entete"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE ENTETE PARAMETRE
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheParametre_ecranGestion_entetes(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS parametre_ecranGestion_entete.id, parametre_ecranGestion_entete.parametreSysteme, pasPage, nbrEnrgPage, type_entete, parametreSysteme.nom FROM parametre_ecranGestion_entete inner join parametreSysteme on parametreSysteme.id=parametre_ecranGestion_entete.parametreSysteme ORDER BY " + select_tri + " LIMIT ?, ?";
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
                      entiteFields.put("parametreSysteme", rs.getString("parametreSysteme.nom"));
                    }
                if (dictionnaire_nom_colonne.containsKey("pasPage")) {
                entiteFields.put("pasPage", rs.getInt("pasPage"));
                }
                if (dictionnaire_nom_colonne.containsKey("nbrEnrgPage")) {
                entiteFields.put("nbrEnrgPage", rs.getString("nbrEnrgPage"));
                }
                if (dictionnaire_nom_colonne.containsKey("type_entete")) {
                    entiteFields.put("type_entete", rs.getString("type_entete"));
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
    public List<Map<String, Object>> rechercheLikeParametre_ecranGestion_entetes(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS parametre_ecranGestion_entete.id, parametre_ecranGestion_entete.parametreSysteme, pasPage, nbrEnrgPage, type_entete, parametreSysteme.nom  FROM parametre_ecranGestion_entete inner join parametreSysteme on parametreSysteme.id=parametre_ecranGestion_entete.parametreSysteme WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
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
					entiteFields.put("parametreSysteme", rs.getString("parametreSysteme.nom"));
				}
				if (dictionnaire_nom_colonne.containsKey("pasPage")) {
					entiteFields.put("pasPage", rs.getInt("pasPage"));
				}
				if (dictionnaire_nom_colonne.containsKey("nbrEnrgPage")) {
					entiteFields.put("nbrEnrgPage", rs.getString("nbrEnrgPage"));
				}
				if (dictionnaire_nom_colonne.containsKey("type_entete")) {
                    entiteFields.put("type_entete", rs.getString("type_entete"));
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