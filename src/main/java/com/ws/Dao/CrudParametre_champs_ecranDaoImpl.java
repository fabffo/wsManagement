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
import com.ws.beans.CrudParametre_champs_ecran;
import com.ws.configuration.DatasourceH;

public class CrudParametre_champs_ecranDaoImpl implements CrudParametre_champs_ecranDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  CrudParametre_champs_ecranDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER ENTETE PARAMETETRE SYSTEME
		// =================================================================================
    @Override
    public void ajouterCrudParametre_champs_ecran(CrudParametre_champs_ecran crudParametre_champs_ecran) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion  = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO crudparametre_champs_ecran(nom_programme, entite, numero, nom_champ, required, readonly, disabled, minlength, maxlength, type, step, placeholder, type_zone, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, crudParametre_champs_ecran.getNom_programme());
            preparedStatement.setString(2, crudParametre_champs_ecran.getEntite());
            preparedStatement.setInt(3, crudParametre_champs_ecran.getNumero());
            preparedStatement.setString(4, crudParametre_champs_ecran.getNom_champ());
            preparedStatement.setString(5, crudParametre_champs_ecran.getRequired());
            preparedStatement.setString(6, crudParametre_champs_ecran.getReadonly());
            preparedStatement.setString(7, crudParametre_champs_ecran.getDisabled());
            preparedStatement.setInt(8, crudParametre_champs_ecran.getMinlength());
            preparedStatement.setInt(9, crudParametre_champs_ecran.getMaxlength());
            preparedStatement.setString(10, crudParametre_champs_ecran.getType());
            preparedStatement.setString(11, crudParametre_champs_ecran.getStep());
            preparedStatement.setString(12, crudParametre_champs_ecran.getPlaceholder());
            preparedStatement.setString(13, crudParametre_champs_ecran.getType_zone());
            preparedStatement.setString(14, "ParametreSys_entete");
            preparedStatement.setString(15, dateTime);
            preparedStatement.setString(16, System.getProperty("user.name"));
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table CrudParametre_champs_ecran"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
		// MODIFIER DETAIL PARAMETRE
		// =================================================================================
    @Override
    public void modifierCrudParametre_champs_ecran(CrudParametre_champs_ecran crudParametre_champs_ecran) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE crudparametre_champs_ecran SET nom_programme=?, entite=?, numero=?, nom_champ=?, required=?, readonly=?, disabled=?, minlength=?, maxlength=?, type=?, step=?, placeholder=?, type_zone=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, crudParametre_champs_ecran.getNom_programme());
            preparedStatement.setString(2, crudParametre_champs_ecran.getEntite());
            preparedStatement.setInt(3, crudParametre_champs_ecran.getNumero());
            preparedStatement.setString(4, crudParametre_champs_ecran.getNom_champ());
            preparedStatement.setString(5, crudParametre_champs_ecran.getRequired());
            preparedStatement.setString(6, crudParametre_champs_ecran.getReadonly());
            preparedStatement.setString(7, crudParametre_champs_ecran.getDisabled());
            preparedStatement.setInt(8, crudParametre_champs_ecran.getMinlength());
            preparedStatement.setInt(9, crudParametre_champs_ecran.getMaxlength());
            preparedStatement.setString(10, crudParametre_champs_ecran.getType());
            preparedStatement.setString(11, crudParametre_champs_ecran.getStep());
            preparedStatement.setString(12, crudParametre_champs_ecran.getPlaceholder());
            preparedStatement.setString(13, crudParametre_champs_ecran.getType_zone());
            preparedStatement.setString(14, "ParametreSys_entete");
            preparedStatement.setString(15, dateTime);
            preparedStatement.setString(16, System.getProperty("user.name"));
            preparedStatement.setInt(17, crudParametre_champs_ecran.getId());
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table CrudParametre_champs_ecran"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// COPIER ENTETE PARAMETRE
  		// =================================================================================
    @Override
    public void copierCrudParametre_champs_ecran(CrudParametre_champs_ecran crudParametre_champs_ecran) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO crudparametre_champs_ecran(nom_programme, entite, numero, nom_champ, required, readonly, disabled, minlength, maxlength, type, step, placeholder, type_zone, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, crudParametre_champs_ecran.getNom_programme());
            preparedStatement.setString(2, crudParametre_champs_ecran.getEntite());
            preparedStatement.setInt(3, crudParametre_champs_ecran.getNumero());
            preparedStatement.setString(4, crudParametre_champs_ecran.getNom_champ());
            preparedStatement.setString(5, crudParametre_champs_ecran.getRequired());
            preparedStatement.setString(6, crudParametre_champs_ecran.getReadonly());
            preparedStatement.setString(7, crudParametre_champs_ecran.getDisabled());
            preparedStatement.setInt(8, crudParametre_champs_ecran.getMinlength());
            preparedStatement.setInt(9, crudParametre_champs_ecran.getMaxlength());
            preparedStatement.setString(10, crudParametre_champs_ecran.getType());
            preparedStatement.setString(11, crudParametre_champs_ecran.getStep());
            preparedStatement.setString(12, crudParametre_champs_ecran.getPlaceholder());
            preparedStatement.setString(13, crudParametre_champs_ecran.getType_zone());
            preparedStatement.setString(14, "ParametreSys_entete");
            preparedStatement.setString(15, dateTime);
            preparedStatement.setString(16, System.getProperty("user.name"));
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table CrudParametre_champs_ecran"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

    	// =================================================================================
  		// SUPPRIMER ENTETE PARAMETRE
  		// =================================================================================

    @Override
    public void supprimerCrudParametre_champs_ecran(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "DELETE FROM crudparametre_champs_ecran WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table CrudParametre_champs_ecran"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


    	// =================================================================================
 		// RENOMMER ENTETE PARAMETRE
 		// =================================================================================
 		@Override
 		public void renommerCrudParametre_champs_ecran(CrudParametre_champs_ecran crudParametre_champs_ecran) throws DaoException {
 			Connection connexion = null;
 			PreparedStatement preparedStatement = null;
 			try {
 				connexion = daoFactory.getConnection();

 				preparedStatement = connexion.prepareStatement(
 						"UPDATE crudparametre_champs_ecran SET crudparametre_champs_ecran.nom_programme=?, crudparametre_champs_ecran.pgmmodification=?, crudparametre_champs_ecran.datemodification=?,"
 								+ " crudparametre_champs_ecran.usermodification=? where crudparametre_champs_ecran.id=?;");
 				preparedStatement.setString(1, crudParametre_champs_ecran.getNom_programme());
 				preparedStatement.setString(2, "ParametreSys_entete");
 				preparedStatement.setString(3, dateTime);
 				preparedStatement.setString(4, System.getProperty("user.name"));
 				preparedStatement.setInt(5, crudParametre_champs_ecran.getId());
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
    public List<CrudParametre_champs_ecran> listerCrudParametre_champs_ecran() throws DaoException {
        List<CrudParametre_champs_ecran> crudParametre_champs_ecrans = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, nom_programme, entite, numero, nom_champ, required, readonly, disabled, minlength, maxlength, type, step, placeholder, type_zone FROM crudparametre_champs_ecran;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                CrudParametre_champs_ecran crudParametre_champs_ecran = new CrudParametre_champs_ecran();
                crudParametre_champs_ecran.setId(resultat.getInt("id"));
                crudParametre_champs_ecran.setNom_programme(resultat.getString("nom_programme"));
                crudParametre_champs_ecran.setEntite(resultat.getString("entite"));
                crudParametre_champs_ecran.setNumero(resultat.getInt("numero"));
                crudParametre_champs_ecran.setNom_champ(resultat.getString("nom_champ"));
                crudParametre_champs_ecran.setRequired(resultat.getString("required"));
                crudParametre_champs_ecran.setReadonly(resultat.getString("readonly"));
                crudParametre_champs_ecran.setDisabled(resultat.getString("disabled"));
                crudParametre_champs_ecran.setMinlength(resultat.getInt("minlength"));
                crudParametre_champs_ecran.setType(resultat.getString("type"));
                crudParametre_champs_ecran.setEntite(resultat.getString("step"));
                crudParametre_champs_ecran.setStep(resultat.getString("entite"));
                crudParametre_champs_ecran.setPlaceholder(resultat.getString("placeholder"));
                crudParametre_champs_ecran.setType_zone(resultat.getString("type_zone"));
                crudParametre_champs_ecrans.add(crudParametre_champs_ecran);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table CrudParametre_champs_ecran"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return crudParametre_champs_ecrans;
    }

    	// =================================================================================
		// TROUVER ENTETE PARAMETRE PAR ID
		// =================================================================================
    @Override
    public CrudParametre_champs_ecran trouverCrudParametre_champs_ecran(Integer id) throws DaoException {
        CrudParametre_champs_ecran crudParametre_champs_ecran = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM crudparametre_champs_ecran WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                crudParametre_champs_ecran = new CrudParametre_champs_ecran();
                crudParametre_champs_ecran.setId(resultat.getInt("id"));
                crudParametre_champs_ecran.setNom_programme(resultat.getString("nom_programme"));
                crudParametre_champs_ecran.setEntite(resultat.getString("entite"));
                crudParametre_champs_ecran.setNumero(resultat.getInt("numero"));
                crudParametre_champs_ecran.setNom_champ(resultat.getString("nom_champ"));
                crudParametre_champs_ecran.setRequired(resultat.getString("required"));
                crudParametre_champs_ecran.setReadonly(resultat.getString("readonly"));
                crudParametre_champs_ecran.setDisabled(resultat.getString("disabled"));
                crudParametre_champs_ecran.setMinlength(resultat.getInt("minlength"));
                crudParametre_champs_ecran.setMaxlength(resultat.getInt("maxlength"));
                crudParametre_champs_ecran.setType(resultat.getString("type"));
                crudParametre_champs_ecran.setStep(resultat.getString("step"));
                crudParametre_champs_ecran.setPlaceholder(resultat.getString("placeholder"));
                crudParametre_champs_ecran.setType_zone(resultat.getString("type_zone"));
                crudParametre_champs_ecran.setUsermodification(resultat.getString("usermodification"));
                crudParametre_champs_ecran.setDatemodification(resultat.getDate("datemodification"));
                crudParametre_champs_ecran.setPgmmodification(resultat.getString("pgmmodification"));
                crudParametre_champs_ecran.setUsercreation(resultat.getString("usercreation"));
                crudParametre_champs_ecran.setDatecreation(resultat.getDate("datecreation"));
                crudParametre_champs_ecran.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table CrudParametre_champs_ecran"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return crudParametre_champs_ecran;
    }

    	// =================================================================================
		// TROUVER ENTETE PARAMETRE PAR NOM
		// =================================================================================
    @Override
    public boolean trouverNomCrudParametre_champs_ecran(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM crudparametre_champs_ecran WHERE nom_programme=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table CrudParametre_champs_ecran"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



    	// =================================================================================
		// RECHERCHE ENTETE PARAMETRE
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheCrudParametre_champs_ecrans(Integer offset, Integer noOfRecords, String select_tri) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM crudparametre_champs_ecran ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();
             while (rs.next()) {
                Map<String, Object> entiteFields = new LinkedHashMap<>();
                entiteFields.put("id", rs.getInt("id"));
                entiteFields.put("nom_programme", rs.getString("nom_programme"));
                entiteFields.put("entite", rs.getString("entite"));
                entiteFields.put("numero", rs.getInt("numero"));
                entiteFields.put("nom_champ", rs.getString("nom_champ"));
                entiteFields.put("required", rs.getString("required"));
                entiteFields.put("readonly", rs.getString("readonly"));
                entiteFields.put("disabled", rs.getString("disabled"));
                entiteFields.put("minlength", rs.getInt("minlength"));
                entiteFields.put("maxlength", rs.getInt("maxlength"));
                entiteFields.put("type", rs.getString("type"));
                entiteFields.put("step", rs.getString("step"));
                entiteFields.put("placeholder", rs.getString("placeholder"));
                entiteFields.put("type_zone", rs.getString("type_zone"));
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
    public List<Map<String, Object>> rechercheLikeCrudParametre_champs_ecrans(Integer offset, Integer noOfRecords, String select_tri, String select_like) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM crudparametre_champs_ecran WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> entiteFields = new LinkedHashMap<>();
            	entiteFields.put("id", rs.getInt("id"));
                entiteFields.put("nom_programme", rs.getString("nom_programme"));
                entiteFields.put("entite", rs.getString("entite"));
                entiteFields.put("numero", rs.getInt("numero"));
                entiteFields.put("nom_champ", rs.getString("nom_champ"));
                entiteFields.put("required", rs.getString("required"));
                entiteFields.put("readonly", rs.getString("readonly"));
                entiteFields.put("disabled", rs.getString("disabled"));
                entiteFields.put("minlength", rs.getInt("minlength"));
                entiteFields.put("maxlength", rs.getInt("maxlength"));
                entiteFields.put("type", rs.getString("type"));
                entiteFields.put("step", rs.getString("step"));
                entiteFields.put("placeholder", rs.getString("placeholder"));
                entiteFields.put("type_zone", rs.getString("type_zone"));
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