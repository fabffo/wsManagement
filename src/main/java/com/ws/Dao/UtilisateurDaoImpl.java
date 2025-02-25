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
import java.util.LinkedHashMap;
import java.util.List;

import com.ws.beans.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {
    private DaoFactory daoFactory;
    //date du jour
    String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());

    private int noOfRecords;

    UtilisateurDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    // CRUD CREER
    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur) throws DaoException {
        String sql = "INSERT INTO utilisateur(Nom, libelle, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?);";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {
            connexion.setAutoCommit(false);
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getLibelle());
            preparedStatement.setString(3, "UtilisateurDao");
            preparedStatement.setString(4, dateTime);
            preparedStatement.setString(5, System.getProperty("user.name"));
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible d'ajouter l'enregistrement dans la table utilisateur"+ e);
        }
    }

    // LISTER LES ENREGISTREMENTS
    @Override
    public List<Utilisateur> listerUtilisateur() throws DaoException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT id, Nom, libelle FROM utilisateur;";
        try (Connection connexion = daoFactory.getConnection();
             Statement statement = connexion.createStatement();
             ResultSet resultat = statement.executeQuery(sql)) {
            while (resultat.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId(resultat.getInt("id"));
                utilisateur.setNom(resultat.getString("Nom"));
                utilisateur.setLibelle(resultat.getString("libelle"));
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements dans la table utilisateur"+ e);
        }
        return utilisateurs;
    }
    @Override
    public List<Utilisateur> listerUtilisateurClient() throws DaoException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT id, Nom, libelle FROM utilisateur;";
        try (Connection connexion = daoFactory.getConnection();
             Statement statement = connexion.createStatement();
             ResultSet resultat = statement.executeQuery(sql)) {
            while (resultat.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId(resultat.getInt("id"));
                utilisateur.setNom(resultat.getString("Nom"));
                utilisateur.setLibelle(resultat.getString("libelle"));
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements dans la table utilisateur"+ e);
        }
        return utilisateurs;
    }
    @Override
    public List<Utilisateur> listerUtilisateurFournisseur() throws DaoException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT id, Nom, libelle FROM utilisateur;";
        try (Connection connexion = daoFactory.getConnection();
             Statement statement = connexion.createStatement();
             ResultSet resultat = statement.executeQuery(sql)) {
            while (resultat.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId(resultat.getInt("id"));
                utilisateur.setNom(resultat.getString("Nom"));
                utilisateur.setLibelle(resultat.getString("libelle"));
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements dans la table utilisateur"+ e);
        }
        return utilisateurs;
    }
    @Override
    public List<Utilisateur> listerUtilisateurSalarie() throws DaoException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT id, Nom, libelle FROM utilisateur;";
        try (Connection connexion = daoFactory.getConnection();
             Statement statement = connexion.createStatement();
             ResultSet resultat = statement.executeQuery(sql)) {
            while (resultat.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId(resultat.getInt("id"));
                utilisateur.setNom(resultat.getString("Nom"));
                utilisateur.setLibelle(resultat.getString("libelle"));
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements dans la table utilisateur"+ e);
        }
        return utilisateurs;
    }
    // CRUD LIRE UN ENREGISTREMENT SPECIFIQUE VIA SON ID
    @Override
    public Utilisateur trouverUtilisateur(Integer id) throws DaoException {
        Utilisateur utilisateur = null;
        String sql = "SELECT * FROM utilisateur WHERE ID=?;";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultat = preparedStatement.executeQuery()) {
                if (resultat.next()) {
                    utilisateur = new Utilisateur();
                    utilisateur.setId(id);
                    utilisateur.setNom(resultat.getString("Nom"));
                    utilisateur.setLibelle(resultat.getString("libelle"));
                    utilisateur.setPgmmodification(resultat.getString("pgmmodification"));
                    utilisateur.setUsermodification(resultat.getString("usermodification"));
                    utilisateur.setDatemodification(resultat.getDate("datemodification"));
                    utilisateur.setPgmcreation(resultat.getString("pgmcreation"));
                    utilisateur.setUsercreation(resultat.getString("usercreation"));
                    utilisateur.setDatecreation(resultat.getDate("datecreation"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement dans la table utilisateur"+ e);
        }
        return utilisateur;
    }

    // EXISTENCE D'UN ENREGISTREMENT SPECIFIQUE VIA SON Nom
    @Override
    public boolean trouverNomUtilisateur(String nom) throws DaoException {
        boolean existe = false;
        String sql = "SELECT * FROM utilisateur WHERE Nom=?;";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {
            preparedStatement.setString(1, nom);
            try (ResultSet resultat = preparedStatement.executeQuery()) {
                if (resultat.next()) {
                    existe = true;
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table utilisateur"+ e);
        }
        return existe;
    }

    // CRUD MODIFIER ENREGISTREMENT
    @Override
    public void modifierUtilisateur(Utilisateur utilisateur) throws DaoException {
        String sql = "UPDATE utilisateur SET Nom=?, libelle=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {
            connexion.setAutoCommit(false);
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getLibelle());
            preparedStatement.setString(3, "UtilisateurDao");
            preparedStatement.setString(4, dateTime);
            preparedStatement.setString(5, System.getProperty("user.name"));
            preparedStatement.setInt(6, utilisateur.getId());
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de mettre à jour l'enregistrement dans la table utilisateur"+ e);
        }
    }

    // CRUD COPIER ENREGISTREMENT
    @Override
    public void copierUtilisateur(Utilisateur utilisateur) throws DaoException {
        ajouterUtilisateur(utilisateur); // Réutilisation de la méthode ajouterUtilisateur pour copier l'enregistrement
    }

    // CRUD SUPPRIMER UN ENREGISTREMENT
    @Override
    public void supprimerUtilisateur(Integer id) throws DaoException {
        String sql = "DELETE FROM utilisateur WHERE ID=?;";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement dans la table utilisateur"+ e);
        }
    }

    // Rechercher et lister les enregistrements
    @Override
    public List<Utilisateur> rechercheUtilisateurs(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) throws DaoException {
        List<Utilisateur> list = new ArrayList<>();
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM utilisateur ORDER BY " + select_tri + " LIMIT ?, ?";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setId(rs.getInt("id"));
                    utilisateur.setNom(rs.getString("Nom"));
                    utilisateur.setLibelle(rs.getString("libelle"));
                    list.add(utilisateur);
                }
                try (ResultSet rsCount = preparedStatement.executeQuery("SELECT FOUND_ROWS()")) {
                    if (rsCount.next()) {
                        this.noOfRecords = rsCount.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de rechercher les enregistrements dans la table utilisateur"+ e);
        }
        return list;
    }

    // Avoir le N° enregistrement en cours
    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }
    @Override
    public Integer getIntegerRecords() {
    	Integer integerRecords = noOfRecords;
        return integerRecords;
    }


    // Rechercher et lister les enregistrements suivant like
    @Override
    public List<Utilisateur> rechercheLikeUtilisateurs(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite) throws DaoException {
        List<Utilisateur> list = new ArrayList<>();
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM utilisateur WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setId(rs.getInt("id"));
                    utilisateur.setNom(rs.getString("Nom"));
                    utilisateur.setLibelle(rs.getString("libelle"));
                    list.add(utilisateur);
                }
                try (ResultSet rsCount = preparedStatement.executeQuery("SELECT FOUND_ROWS()")) {
                    if (rsCount.next()) {
                        this.noOfRecords = rsCount.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de rechercher les enregistrements dans la table utilisateur avec les critères donnés"+ e);
        }
        return list;
    }
}
