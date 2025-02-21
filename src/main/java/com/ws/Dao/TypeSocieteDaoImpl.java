/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO IMPLEMENTATION TYPE SOCIETE                         ///
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

import com.ws.beans.TypeSociete;

public class TypeSocieteDaoImpl implements TypeSocieteDao {
    private DaoFactory daoFactory;
    // date du jour
    String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());

    private int noOfRecords;

    TypeSocieteDaoImpl(DaoFactory daoFactory) {
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
    public void ajouterTypeSociete(TypeSociete typeSociete) throws DaoException {
        String sql = "INSERT INTO type_Societe(Nom, libelle, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?);";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, typeSociete.getNom());
            preparedStatement.setString(2, typeSociete.getLibelle());
            preparedStatement.setString(3, "TypeSocieteDao");
            preparedStatement.setString(4, dateTime);
            preparedStatement.setString(5, System.getProperty("user.name"));
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible d'ajouter enregistrement avec la table type Societe"+ e);
        }
    }

    // LISTER LES ENREGISTREMENTS
    @Override
    public List<TypeSociete> listerTypeSociete() throws DaoException {
        List<TypeSociete> typeSocietes = new ArrayList<>();
        String sql = "SELECT id, Nom, libelle FROM type_Societe;";
        try (Connection connection = daoFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                TypeSociete typeSociete = new TypeSociete();
                typeSociete.setId(resultSet.getInt("id"));
                typeSociete.setNom(resultSet.getString("Nom"));
                typeSociete.setLibelle(resultSet.getString("libelle"));
                typeSocietes.add(typeSociete);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister enregistrement avec la table type Societe"+ e);
        }
        return typeSocietes;
    }

    // CRUD LIRE UN ENREGISTREMENT SPECIFIQUE VIA SON ID
    @Override
    public TypeSociete trouverTypeSociete(Integer id) throws DaoException {
        TypeSociete typeSociete = null;
        String sql = "SELECT * FROM TYPE_Societe WHERE ID=?;";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    typeSociete = new TypeSociete();
                    typeSociete.setId(id);
                    typeSociete.setNom(resultSet.getString("Nom"));
                    typeSociete.setLibelle(resultSet.getString("libelle"));
                    typeSociete.setPgmmodification(resultSet.getString("pgmmodification"));
                    typeSociete.setUsermodification(resultSet.getString("usermodification"));
                    typeSociete.setDatemodification(resultSet.getDate("datemodification"));
                    typeSociete.setPgmcreation(resultSet.getString("pgmcreation"));
                    typeSociete.setUsercreation(resultSet.getString("usercreation"));
                    typeSociete.setDatecreation(resultSet.getDate("datecreation"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver id enregistrement avec la table type Societe"+ e);
        }
        return typeSociete;
    }

    // EXISTENCE D'UN ENREGISTREMENT SPECIFIQUE VIA SON Nom
    @Override
    public boolean trouverNomTypeSociete(String nom) throws DaoException {
        boolean existe = false;
        String sql = "SELECT * FROM TYPE_Societe WHERE Nom=?;";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nom);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    existe = true;
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la table type Societe"+ e);
        }
        return existe;
    }

    // CRUD MODIFIER ENREGISTREMENT
    @Override
    public void modifierTypeSociete(TypeSociete typeSociete) throws DaoException {
        String sql = "UPDATE type_Societe SET Nom=?, libelle=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, typeSociete.getNom());
            preparedStatement.setString(2, typeSociete.getLibelle());
            preparedStatement.setString(3, "TypeSocieteDao");
            preparedStatement.setString(4, dateTime);
            preparedStatement.setString(5, System.getProperty("user.name"));
            preparedStatement.setInt(6, typeSociete.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de maj enregistrement avec la table type Societe"+ e);
        }
    }

    // CRUD COPIER ENREGISTREMENT
    @Override
    public void copierTypeSociete(TypeSociete typeSociete) throws DaoException {
        ajouterTypeSociete(typeSociete);
    }

    // CRUD SUPPRIMER UN ENREGISTREMENT
    @Override
    public void supprimerTypeSociete(Integer id) throws DaoException {

        String sql = "DELETE FROM TYPE_Societe WHERE ID=?;";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        	 preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer enregistrement avec la table type Societe"+ e);
        }
    }

    // Rechercher et lister les enregistrements
    @Override
    public List<TypeSociete> rechercheTypeSocietes(int offset, int noOfRecords, String select_tri) throws DaoException {
        List<TypeSociete> list = new ArrayList<>();
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM type_Societe ORDER BY " + select_tri + " LIMIT ?, ?;";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    TypeSociete typeSociete = new TypeSociete();
                    typeSociete.setId(rs.getInt("id"));
                    typeSociete.setNom(rs.getString("Nom"));
                    typeSociete.setLibelle(rs.getString("libelle"));
                    list.add(typeSociete);
                }
                try (ResultSet rsCount = preparedStatement.executeQuery("SELECT FOUND_ROWS()")) {
                    if (rsCount.next()) {
                        this.noOfRecords = rsCount.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de rechercher les enregistrements dans la table type Societe"+ e);
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
    public List<TypeSociete> rechercheLikeTypeSocietes(int offset, int noOfRecords, String select_tri, String select_like) throws DaoException {
        List<TypeSociete> list = new ArrayList<>();
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM type_Societe WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?;";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    TypeSociete typeSociete = new TypeSociete();
                    typeSociete.setId(rs.getInt("id"));
                    typeSociete.setNom(rs.getString("Nom"));
                    typeSociete.setLibelle(rs.getString("libelle"));
                    list.add(typeSociete);
                }
                try (ResultSet rsCount = preparedStatement.executeQuery("SELECT FOUND_ROWS()")) {
                    if (rsCount.next()) {
                        this.noOfRecords = rsCount.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de rechercher les enregistrements dans la table type Societe"+ e);
        }
        return list;
    }
}
