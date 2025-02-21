////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                        ///
////    PROGRAMME DAO IMPLEMENTATION UTILISATEUR                        ///
////    Créé par Fabrice FOUGERY le 29/04/2024                          ///
////    Modifié par ....... .... le ../../....                          ///
////////////////////////////////////////////////////////////////////////////

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

import com.ws.beans.TypePrix;

public class TypePrixDaoImpl implements TypePrixDao {
    private DaoFactory daoFactory;
    // date du jour
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;

    TypePrixDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    // Méthode utilitaire pour la gestion des transactions et la fermeture des connexions
    private void close(Connection connection) throws DaoException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException("Impossible de fermer la connexion"+ e);
            }
        }
    }

    // CRUD CREER
    @Override
    public void ajouterTypePrix(TypePrix typePrix) throws DaoException {
        String sql = "INSERT INTO typePrix(Nom, libelle, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?);";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, typePrix.getNom());
            preparedStatement.setString(2, typePrix.getLibelle());
            preparedStatement.setString(3, "TypePrixDao");
            preparedStatement.setString(4, dateTime);
            preparedStatement.setString(5, System.getProperty("user.name"));
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible d'ajouter un enregistrement dans la table typePrix"+ e);
        }
    }

    // LISTER LES ENREGISTREMENTS
    @Override
    public List<TypePrix> listerTypePrix() throws DaoException {
        List<TypePrix> typePrixs = new ArrayList<>();
        String sql = "SELECT id, Nom, libelle FROM typePrix;";
        try (Connection connection = daoFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                TypePrix typePrix = new TypePrix();
                typePrix.setId(resultSet.getInt("id"));
                typePrix.setNom(resultSet.getString("Nom"));
                typePrix.setLibelle(resultSet.getString("libelle"));
                typePrixs.add(typePrix);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements dans la table typePrix"+ e);
        }
        return typePrixs;
    }

    // CRUD LIRE UN ENREGISTREMENT SPECIFIQUE VIA SON ID
    @Override
    public TypePrix trouverTypePrix(Integer id) throws DaoException {
        TypePrix typePrix = null;
        String sql = "SELECT * FROM typePrix WHERE ID=?;";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    typePrix = new TypePrix();
                    typePrix.setId(id);
                    typePrix.setNom(resultSet.getString("Nom"));
                    typePrix.setLibelle(resultSet.getString("libelle"));
                    typePrix.setPgmmodification(resultSet.getString("pgmmodification"));
                    typePrix.setUsermodification(resultSet.getString("usermodification"));
                    typePrix.setDatemodification(resultSet.getDate("datemodification"));
                    typePrix.setPgmcreation(resultSet.getString("pgmcreation"));
                    typePrix.setUsercreation(resultSet.getString("usercreation"));
                    typePrix.setDatecreation(resultSet.getDate("datecreation"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec l'ID " + id + " dans la table typePrix"+ e);
        }
        return typePrix;
    }

    // EXISTENCE D'UN ENREGISTREMENT SPECIFIQUE VIA SON Nom
    @Override
    public boolean trouverNomTypePrix(String nom) throws DaoException {
        boolean existe = false;
        String sql = "SELECT * FROM typePrix WHERE Nom=?;";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nom);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    existe = true;
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du nom dans la table typePrix"+ e);
        }
        return existe;
    }

    // CRUD MODIFIER ENREGISTREMENT
    @Override
    public void modifierTypePrix(TypePrix typePrix) throws DaoException {
        String sql = "UPDATE typePrix SET Nom=?, libelle=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, typePrix.getNom());
            preparedStatement.setString(2, typePrix.getLibelle());
            preparedStatement.setString(3, "TypePrixDao");
            preparedStatement.setString(4, dateTime);
            preparedStatement.setString(5, System.getProperty("user.name"));
            preparedStatement.setInt(6, typePrix.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de mettre à jour l'enregistrement dans la table typePrix"+ e);
        }
    }

    // CRUD COPIER ENREGISTREMENT
    @Override
    public void copierTypePrix(TypePrix typePrix) throws DaoException {
        ajouterTypePrix(typePrix);
    }

    // CRUD SUPPRIMER UN ENREGISTREMENT
    @Override
    public void supprimerTypePrix(Integer id) throws DaoException {
        String sql = "DELETE FROM typePrix WHERE ID=?;";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec l'ID " + id + " dans la table typePrix"+ e);
        }
    }

    // Rechercher et lister les enregistrements
    @Override
    public List<TypePrix> rechercheTypePrixs(int offset, int noOfRecords, String select_tri) throws DaoException {
        List<TypePrix> list = new ArrayList<>();
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM typePrix ORDER BY " + select_tri + " LIMIT ?, ?;";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    TypePrix typePrix = new TypePrix();
                    typePrix.setId(rs.getInt("id"));
                    typePrix.setNom(rs.getString("Nom"));
                    typePrix.setLibelle(rs.getString("libelle"));
                    list.add(typePrix);
                }
                try (ResultSet rsCount = preparedStatement.executeQuery("SELECT FOUND_ROWS()")) {
                    if (rsCount.next()) {
                        this.noOfRecords = rsCount.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de rechercher les enregistrements dans la table typePrix"+ e);
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
    public List<TypePrix> rechercheLikeTypePrixs(int offset, int noOfRecords, String select_tri, String select_like) throws DaoException {
        List<TypePrix> list = new ArrayList<>();
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM typePrix WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?;";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    TypePrix typePrix = new TypePrix();
                    typePrix.setId(rs.getInt("id"));
                    typePrix.setNom(rs.getString("Nom"));
                    typePrix.setLibelle(rs.getString("libelle"));
                    list.add(typePrix);
                }
                try (ResultSet rsCount = preparedStatement.executeQuery("SELECT FOUND_ROWS()")) {
                    if (rsCount.next()) {
                        this.noOfRecords = rsCount.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de rechercher les enregistrements dans la table typePrix"+ e);
        }
        return list;
    }
}
