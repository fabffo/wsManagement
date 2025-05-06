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
import com.ws.beans.ParametreSysteme_detail;
import com.ws.configuration.DatasourceH;

public class ParametreSysteme_detailDaoImpl implements ParametreSysteme_detailDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


      ParametreSysteme_detailDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

          // =================================================================================
        // AJOUTER DETAIL PARAMETETRE SYSTEME
        // =================================================================================
    @Override
    public void ajouterParametreSysteme_detail(ParametreSysteme_detail parametreSysteme_detail) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion  = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO tableparametre_detail(nom, ecranGestion_pasPage, ecranGestion_nbrEnrgPage, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, parametreSysteme_detail.getNom());
            preparedStatement.setInt(2, parametreSysteme_detail.getLigne());
            preparedStatement.setString(3, parametreSysteme_detail.getType());
            preparedStatement.setString(4, parametreSysteme_detail.getZone());
            preparedStatement.setString(5, parametreSysteme_detail.getColonne());
            preparedStatement.setInt(2, parametreSysteme_detail.getEcranGestion_largeur());
            preparedStatement.setString(7, parametreSysteme_detail.getEcranGestion_recherche());
            preparedStatement.setString(8, "ParametreSys_detail");
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
            throw new DaoException("Impossible d'ajouter l'enregistrement avec la table ParametreSysteme_detail"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

        // =================================================================================
        // MODIFIER DETAIL PARAMETRE
        // =================================================================================
    @Override
    public void modifierParametreSysteme_detail(ParametreSysteme_detail parametreSysteme_detail) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "UPDATE tableparametre_detail SET nom=?, ligne=?, type=?, zone=?, colonne=?, ecranGestion_largeur=?, ecranGestion_recherche=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, parametreSysteme_detail.getNom());
            preparedStatement.setInt(2, parametreSysteme_detail.getLigne());
            preparedStatement.setString(3, parametreSysteme_detail.getType());
            preparedStatement.setString(4, parametreSysteme_detail.getZone());
            preparedStatement.setString(5, parametreSysteme_detail.getColonne());
            preparedStatement.setInt(6, parametreSysteme_detail.getEcranGestion_largeur());
            preparedStatement.setString(7, parametreSysteme_detail.getEcranGestion_recherche());
            preparedStatement.setString(8, "ParametreSys_detail");
            preparedStatement.setString(9, dateTime);
            preparedStatement.setString(10, System.getProperty("user.name"));
            preparedStatement.setInt(11, parametreSysteme_detail.getId());
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
            throw new DaoException("Impossible de mettre à jour l'enregistrement avec la table ParametreSysteme_detail"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

        // =================================================================================
          // COPIER DETAIL PARAMETRE
          // =================================================================================
    @Override
    public void copierParametreSysteme_detail(ParametreSysteme_detail parametreSysteme_detail) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            connexion.setAutoCommit(false);
            String sql = "INSERT INTO tableparametre_detail(nom, ligne, type, zone, colonne, ecranGestion_largeur, ecranGestion_recherche, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, parametreSysteme_detail.getNom());
            preparedStatement.setInt(2, parametreSysteme_detail.getLigne());
            preparedStatement.setString(3, parametreSysteme_detail.getType());
            preparedStatement.setString(4, parametreSysteme_detail.getZone());
            preparedStatement.setString(5, parametreSysteme_detail.getColonne());
            preparedStatement.setInt(6, parametreSysteme_detail.getEcranGestion_largeur());
            preparedStatement.setString(7, parametreSysteme_detail.getEcranGestion_recherche());
            preparedStatement.setString(8, "ParametreSys_detail");
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
            throw new DaoException("Impossible de copier l'enregistrement avec la table ParametreSysteme_detail"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }

        // =================================================================================
          // SUPPRIMER DETAIL PARAMETRE
          // =================================================================================

    @Override
    public void supprimerParametreSysteme_detail(Integer id) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "DELETE FROM tableparametre_detail WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec la table ParametreSysteme_detail"+ e);
        } finally {
            closeResources(connexion, preparedStatement, null);
        }
    }


        // =================================================================================
         // RENOMMER DETAIL PARAMETRE
         // =================================================================================
         @Override
         public void renommerParametreSysteme_detail(ParametreSysteme_detail parametreSysteme_detail) throws DaoException {
             Connection connexion = null;
             PreparedStatement preparedStatement = null;

             try {
                 connexion = daoFactory.getConnection();

                 preparedStatement = connexion.prepareStatement(
                         "UPDATE tableparametre_detail SET tableparametre_detail.nom=?, tableparametre_detail.pgmmodification=?, tableparametre_detail.datemodification=?,"
                                 + " tableparametre_detail.usermodification=? where tableparametre_detail.id=?;");
                 preparedStatement.setString(1, parametreSysteme_detail.getNom());
                 preparedStatement.setString(2, "ParametreSys_detail");
                 preparedStatement.setString(3, dateTime);
                 preparedStatement.setString(4, System.getProperty("user.name"));
                 preparedStatement.setInt(5, parametreSysteme_detail.getId());
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
      // LISTER DES DETAIL PARAMETRES
      // =================================================================================
    @Override
    public List<ParametreSysteme_detail> listerParametreSysteme_detail() throws DaoException {
        List<ParametreSysteme_detail> parametreSysteme_details = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT id, id_entete, nom, ligne, type, zone, colonne, ecranGestion_largeur, ecranGestion_recherche FROM tableparametre_detail;";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                ParametreSysteme_detail parametreSysteme_detail = new ParametreSysteme_detail();
                parametreSysteme_detail.setId_entete(resultat.getInt("id"));
                parametreSysteme_detail.setId_entete(resultat.getInt("entete"));
                parametreSysteme_detail.setCle_systeme(resultat.getString("cle_systeme"));
                parametreSysteme_detail.setNom(resultat.getString("nom"));
                parametreSysteme_detail.setLigne(resultat.getInt("ligne"));
                parametreSysteme_detail.setType(resultat.getString("type"));
                parametreSysteme_detail.setZone(resultat.getString("zone"));
                parametreSysteme_detail.setColonne(resultat.getString("colonne"));
                parametreSysteme_detail.setEcranGestion_largeur(resultat.getInt("ecranGestion_largeur"));
                parametreSysteme_detail.setEcranGestion_recherche(resultat.getString("ecranGestion_recherche"));
                parametreSysteme_details.add(parametreSysteme_detail);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table ParametreSysteme_detail"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return parametreSysteme_details;
    }

        // =================================================================================
        // TROUVER DETAIL PARAMETRE PAR ID
        // =================================================================================
    @Override
    public ParametreSysteme_detail trouverParametreSysteme_detail(Integer id) throws DaoException {
        ParametreSysteme_detail parametreSysteme_detail = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT id, cle_systeme, id_entete, nom, ligne, type, zone, colonne, ecranGestion_largeur, ecranGestion_recherche,  usermodification, datemodification, pgmmodification, usercreation, datecreation, pgmcreation FROM tableparametre_detail WHERE ID=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                parametreSysteme_detail = new ParametreSysteme_detail();
                parametreSysteme_detail.setId_entete(resultat.getInt("id"));
                parametreSysteme_detail.setId_entete(resultat.getInt("id_entete"));
                parametreSysteme_detail.setCle_systeme(resultat.getString("cle_systeme"));
                parametreSysteme_detail.setNom(resultat.getString("nom"));
                parametreSysteme_detail.setLigne(resultat.getInt("ligne"));
                parametreSysteme_detail.setType(resultat.getString("type"));
                parametreSysteme_detail.setZone(resultat.getString("zone"));
                parametreSysteme_detail.setColonne(resultat.getString("colonne"));
                parametreSysteme_detail.setEcranGestion_largeur(resultat.getInt("ecranGestion_largeur"));
                parametreSysteme_detail.setEcranGestion_recherche(resultat.getString("ecranGestion_recherche"));
                parametreSysteme_detail.setUsermodification(resultat.getString("usermodification"));
                parametreSysteme_detail.setDatemodification(resultat.getDate("datemodification"));
                parametreSysteme_detail.setPgmmodification(resultat.getString("pgmmodification"));
                parametreSysteme_detail.setUsercreation(resultat.getString("usercreation"));
                parametreSysteme_detail.setDatecreation(resultat.getDate("datecreation"));
                parametreSysteme_detail.setPgmcreation(resultat.getString("pgmcreation"));
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec la table ParametreSysteme_detail"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return parametreSysteme_detail;
    }

        // =================================================================================
        // TROUVER DETAIL PARAMETRE PAR NOM
        // =================================================================================
    @Override
    public boolean trouverNomParametreSysteme_detail(String nom) throws DaoException {
        boolean existe = false;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM tableparametre_detail WHERE nom=?;";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table ParametreSysteme_detail"+ e);
        } finally {
            closeResources(connexion, preparedStatement, resultat);
        }
        return existe;
    }



        // =================================================================================
        // RECHERCHE DETAIL PARAMETRE
        // =================================================================================
    @Override
    public List<Map<String, Object>> rechercheParametreSysteme_details(Integer offset, Integer noOfRecords, String select_tri, Map<String, String> dictionnaire_nom_colonne) {
        List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS cle_systeme, id, id_entete, nom, ligne, type, zone, colonne, ecranGestion_largeur, ecranGestion_recherche FROM tableparametre_detail order by " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Map<String, Object> entiteFields = new LinkedHashMap<>();
                entiteFields.put("id", rs.getInt("id"));
                entiteFields.put("id_entete", rs.getInt("id_entete"));
                entiteFields.put("cle_systeme", rs.getString("cle_systeme"));
                entiteFields.put("nom", rs.getString("nom"));
                entiteFields.put("ligne", rs.getInt("ligne"));
                entiteFields.put("type", rs.getString("type"));
                entiteFields.put("zone", rs.getString("zone"));
                entiteFields.put("colonne", rs.getString("colonne"));
                entiteFields.put("ecranGestion_largeur", rs.getInt("ecranGestion_largeur"));
                entiteFields.put("ecranGestion_recherche", rs.getString("ecranGestion_recherche"));
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
        // RECHERCHE DETAIL PARAMETRES SUIVANT LIKE
        // =================================================================================
    @Override
    public List<Map<String, Object>> rechercheLikeParametreSysteme_details(Integer offset, Integer noOfRecords, String select_tri, String select_like, Map<String, String> dictionnaire_nom_colonne) {
        List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS cle_systeme, id, id_entete, nom, ligne, type, zone, colonne, ecranGestion_largeur, ecranGestion_recherche FROM tableparametre_detail WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Map<String, Object> entiteFields = new LinkedHashMap<>();
                entiteFields.put("id", rs.getInt("id"));
                entiteFields.put("id_entete", rs.getInt("id_entete"));
                entiteFields.put("cle_systeme", rs.getString("cle_systeme"));
                entiteFields.put("nom", rs.getString("nom"));
                entiteFields.put("ligne", rs.getInt("ligne"));
                entiteFields.put("type", rs.getString("type"));
                entiteFields.put("zone", rs.getString("zone"));
                entiteFields.put("colonne", rs.getString("colonne"));
                entiteFields.put("ecranGestion_largeur", rs.getInt("ecranGestion_largeur"));
                entiteFields.put("ecranGestion_recherche", rs.getString("ecranGestion_recherche"));
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
    // RECUPERATION N° ENREGISTREMENT ENCOURS DETAIL PARAMETRE
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
    // FERMETURE DES RESSOURCES DETAIL PARAMETRE
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
