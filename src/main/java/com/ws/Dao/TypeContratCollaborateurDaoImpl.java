/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME IMPLEMENTATION DAO TYPE CONTRAT                                        ///
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
import java.util.Date;
import java.util.List;

import com.ws.beans.TypeContratCollaborateur;

public class TypeContratCollaborateurDaoImpl implements TypeContratCollaborateurDao {
    private DaoFactory daoFactory;
    //date du jour
    String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());

    private int noOfRecords;

    TypeContratCollaborateurDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    // CRUD CREER
    @Override
    public void ajouterTypeContratCollaborateur(TypeContratCollaborateur typeContratCollaborateur) throws DaoException {
        String sql = "INSERT INTO typeContratCollaborateur(nom, libelle, statut, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?);";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {
            connexion.setAutoCommit(false);
            preparedStatement.setString(1, typeContratCollaborateur.getNom());
            preparedStatement.setString(2, typeContratCollaborateur.getLibelle());
            preparedStatement.setString(3, typeContratCollaborateur.getStatut());
            preparedStatement.setString(4, "TypeContratDao");
            preparedStatement.setString(5, dateTime);
            preparedStatement.setString(6, System.getProperty("user.name"));
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible d'ajouter l'enregistrement dans la table typeContratCollaborateur"+ e);
        }
    }

    // LISTER LES ENREGISTREMENTS
    @Override
    public List<TypeContratCollaborateur> listerTypeContratCollaborateur() throws DaoException {
        List<TypeContratCollaborateur> typeContratCollaborateurs = new ArrayList<>();
        String sql = "SELECT id, nom, libelle, statut FROM typeContratCollaborateur;";
        try (Connection connexion = daoFactory.getConnection();
             Statement statement = connexion.createStatement();
             ResultSet resultat = statement.executeQuery(sql)) {
            while (resultat.next()) {
                TypeContratCollaborateur typeContratCollaborateur = new TypeContratCollaborateur();
                typeContratCollaborateur.setId(resultat.getInt("id"));
                typeContratCollaborateur.setNom(resultat.getString("nom"));
                typeContratCollaborateur.setLibelle(resultat.getString("libelle"));
                typeContratCollaborateur.setStatut(resultat.getString("statut"));
                typeContratCollaborateurs.add(typeContratCollaborateur);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements dans la table typeContratCollaborateur"+ e);
        }
        return typeContratCollaborateurs;
    }

    // CRUD LIRE UN ENREGISTREMENT SPECIFIQUE VIA SON ID
    @Override
    public TypeContratCollaborateur trouverTypeContratCollaborateur(Integer id) throws DaoException {
        TypeContratCollaborateur typeContratCollaborateur = null;
        String sql = "SELECT * FROM typeContratCollaborateur WHERE ID=?;";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultat = preparedStatement.executeQuery()) {
                if (resultat.next()) {
                    typeContratCollaborateur = new TypeContratCollaborateur();
                    typeContratCollaborateur.setId(id);
                    typeContratCollaborateur.setNom(resultat.getString("nom"));
                    typeContratCollaborateur.setLibelle(resultat.getString("libelle"));
                    typeContratCollaborateur.setStatut(resultat.getString("statut"));
                    typeContratCollaborateur.setPgmmodification(resultat.getString("pgmmodification"));
                    typeContratCollaborateur.setUsermodification(resultat.getString("usermodification"));
                    typeContratCollaborateur.setDatemodification(resultat.getDate("datemodification"));
                    typeContratCollaborateur.setPgmcreation(resultat.getString("pgmcreation"));
                    typeContratCollaborateur.setUsercreation(resultat.getString("usercreation"));
                    typeContratCollaborateur.setDatecreation(resultat.getDate("datecreation"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement dans la table typeContratCollaborateur"+ e);
        }
        return typeContratCollaborateur;
    }

    // EXISTENCE D'UN ENREGISTREMENT SPECIFIQUE VIA SON NOM
    @Override
    public boolean trouverNomTypeContratCollaborateur(String nom) throws DaoException {
        boolean existe = false;
        String sql = "SELECT * FROM typeContratCollaborateur WHERE nom=?;";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {
            preparedStatement.setString(1, nom);
            try (ResultSet resultat = preparedStatement.executeQuery()) {
                if (resultat.next()) {
                    existe = true;
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table typeContratCollaborateur"+ e);
        }
        return existe;
    }

    // CRUD MODIFIER ENREGISTREMENT
    @Override
    public void modifierTypeContratCollaborateur(TypeContratCollaborateur typeContratCollaborateur) throws DaoException {
        String sql = "UPDATE typeContratCollaborateur SET nom=?, libelle=?, statut=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {
            connexion.setAutoCommit(false);
            preparedStatement.setString(1, typeContratCollaborateur.getNom());
            preparedStatement.setString(2, typeContratCollaborateur.getLibelle());
            preparedStatement.setString(3, typeContratCollaborateur.getStatut());
            preparedStatement.setString(4, "TypeContratDao");
            preparedStatement.setString(5, dateTime);
            preparedStatement.setString(6, System.getProperty("user.name"));
            preparedStatement.setInt(7, typeContratCollaborateur.getId());
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de mettre à jour l'enregistrement dans la table typeContratCollaborateur"+ e);
        }
    }

    // CRUD COPIER ENREGISTREMENT
    @Override
    public void copierTypeContratCollaborateur(TypeContratCollaborateur typeContratCollaborateur) throws DaoException {
        ajouterTypeContratCollaborateur(typeContratCollaborateur);
    }

    // CRUD SUPPRIMER UN ENREGISTREMENT
    @Override
    public void supprimerTypeContratCollaborateur(Integer id) throws DaoException {
        String sql = "DELETE FROM typeContratCollaborateur WHERE ID=?;";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement dans la table typeContratCollaborateur"+ e);
        }
    }

    // Rechercher et lister les enregistrements
    @Override
    public List<TypeContratCollaborateur> rechercheTypeContratCollaborateurs(int offset, int noOfRecords, String select_tri) throws DaoException {
        List<TypeContratCollaborateur> list = new ArrayList<>();
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM typeContratCollaborateur ORDER BY " + select_tri + " LIMIT ?, ?";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    TypeContratCollaborateur typeContratCollaborateur = new TypeContratCollaborateur();
                    typeContratCollaborateur.setId(rs.getInt("id"));
                    typeContratCollaborateur.setNom(rs.getString("nom"));
                    typeContratCollaborateur.setLibelle(rs.getString("libelle"));
                    typeContratCollaborateur.setStatut(rs.getString("statut"));
                    list.add(typeContratCollaborateur);
                }
                try (ResultSet rsCount = preparedStatement.executeQuery("SELECT FOUND_ROWS()")) {
                    if (rsCount.next()) {
                        this.noOfRecords = rsCount.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de rechercher les enregistrements dans la table typeContratCollaborateur"+ e);
        }
        return list;
    }

    // Avoir le N° enregistrement en cours
    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    // Rechercher et lister les enregistrements suivant like
    @Override
    public List<TypeContratCollaborateur> rechercheLikeTypeContratCollaborateurs(int offset, int noOfRecords, String select_tri, String select_like) throws DaoException {
        List<TypeContratCollaborateur> list = new ArrayList<>();
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM typeContratCollaborateur WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    TypeContratCollaborateur typeContratCollaborateur = new TypeContratCollaborateur();
                    typeContratCollaborateur.setId(rs.getInt("id"));
                    typeContratCollaborateur.setNom(rs.getString("nom"));
                    typeContratCollaborateur.setLibelle(rs.getString("libelle"));
                    typeContratCollaborateur.setStatut(rs.getString("statut"));
                    list.add(typeContratCollaborateur);
                }
                try (ResultSet rsCount = preparedStatement.executeQuery("SELECT FOUND_ROWS()")) {
                    if (rsCount.next()) {
                        this.noOfRecords = rsCount.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de rechercher les enregistrements dans la table typeContratCollaborateur"+ e);
        }
        return list;
    }
}
