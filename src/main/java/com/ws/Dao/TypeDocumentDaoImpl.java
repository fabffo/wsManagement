/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
////    PROGRAMME DAO IMPLEMENTATION TYPEDOCUMENT                          ///
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

import com.ws.beans.TypeDocument;

public class TypeDocumentDaoImpl implements TypeDocumentDao {
    private DaoFactory daoFactory;
    // date du jour
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;

    TypeDocumentDaoImpl(DaoFactory daoFactory) {
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
    public void ajouterTypeDocument(TypeDocument typeDocument) throws DaoException {
        String sql = "INSERT INTO typeDocument(cheminRelatif, contrat, cheminAbsolu, pgmcreation, datecreation, usercreation) VALUES(?, ?, ?, ?, ?, ?);";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, typeDocument.getCheminRelatif());
            preparedStatement.setString(2, typeDocument.getContrat());
            preparedStatement.setString(3, typeDocument.getCheminAbsolu());
            preparedStatement.setString(4, "TypeDocumentDao");
            preparedStatement.setString(5, dateTime);
            preparedStatement.setString(6, System.getProperty("user.name"));
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible d'ajouter un enregistrement dans la table typeDocument"+ e);
        }
    }

    // LISTER LES ENREGISTREMENTS
    @Override
    public List<TypeDocument> listerTypeDocument() throws DaoException {
        List<TypeDocument> typeDocuments = new ArrayList<>();
        String sql = "SELECT id, cheminRelatif, contrat, cheminAbsolu FROM typeDocument;";
        try (Connection connection = daoFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                TypeDocument typeDocument = new TypeDocument();
                typeDocument.setId(resultSet.getInt("id"));
                typeDocument.setCheminRelatif(resultSet.getString("cheminRelatif"));
                typeDocument.setContrat(resultSet.getString("contrat"));
                typeDocument.setCheminAbsolu(resultSet.getString("cheminAbsolu"));
                typeDocuments.add(typeDocument);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements dans la table typeDocument"+ e);
        }
        return typeDocuments;
    }

    // CRUD LIRE UN ENREGISTREMENT SPECIFIQUE VIA SON ID
    @Override
    public TypeDocument trouverTypeDocument(Integer id) throws DaoException {
        TypeDocument typeDocument = null;
        String sql = "SELECT * FROM typeDocument WHERE ID=?;";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    typeDocument = new TypeDocument();
                    typeDocument.setId(id);
                    typeDocument.setCheminRelatif(resultSet.getString("cheminRelatif"));
                    typeDocument.setContrat(resultSet.getString("contrat"));
                    typeDocument.setCheminAbsolu(resultSet.getString("cheminAbsolu"));
                    typeDocument.setPgmmodification(resultSet.getString("pgmmodification"));
                    typeDocument.setUsermodification(resultSet.getString("usermodification"));
                    typeDocument.setDatemodification(resultSet.getDate("datemodification"));
                    typeDocument.setPgmcreation(resultSet.getString("pgmcreation"));
                    typeDocument.setUsercreation(resultSet.getString("usercreation"));
                    typeDocument.setDatecreation(resultSet.getDate("datecreation"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de trouver l'enregistrement avec l'ID " + id + " dans la table typeDocument"+ e);
        }
        return typeDocument;
    }

    // EXISTENCE D'UN ENREGISTREMENT SPECIFIQUE VIA SON NOM
    @Override
    public boolean trouverTypeDocument(String typeDocument) throws DaoException {
        boolean existe = false;
        String sql = "SELECT * FROM typeDocument WHERE cheminRelatif=?;";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, typeDocument);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    existe = true;
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de vérifier l'existence du cheminRelatif dans la table typeDocument"+ e);
        }
        return existe;
    }

    // CRUD MODIFIER ENREGISTREMENT
    @Override
    public void modifierTypeDocument(TypeDocument typeDocument) throws DaoException {
        String sql = "UPDATE typeDocument SET cheminRelatif=?, contrat=?, cheminAbsolu=?, pgmmodification=?, datemodification=?, usermodification=? WHERE id=?;";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, typeDocument.getCheminRelatif());
            preparedStatement.setString(2, typeDocument.getContrat());
            preparedStatement.setString(3, typeDocument.getCheminAbsolu());
            preparedStatement.setString(4, "TypeDocumentDao");
            preparedStatement.setString(5, dateTime);
            preparedStatement.setString(6, System.getProperty("user.name"));
            preparedStatement.setInt(7, typeDocument.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de mettre à jour l'enregistrement dans la table typeDocument"+ e);
        }
    }

    // CRUD COPIER ENREGISTREMENT
    @Override
    public void copierTypeDocument(TypeDocument typeDocument) throws DaoException {
        ajouterTypeDocument(typeDocument);
    }

    // CRUD SUPPRIMER UN ENREGISTREMENT
    @Override
    public void supprimerTypeDocument(Integer id) throws DaoException {
        String sql = "DELETE FROM typeDocument WHERE ID= ?" ;
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de supprimer l'enregistrement avec l'ID " + id + " dans la table typeDocument"+ e);
        }
    }

    // Rechercher et lister les enregistrements
    @Override
    public List<TypeDocument> rechercheTypeDocuments(int offset, int noOfRecords, String select_tri) throws DaoException {
        List<TypeDocument> list = new ArrayList<>();
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM typeDocument ORDER BY " + select_tri + " LIMIT ?, ?;";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    TypeDocument typeDocument = new TypeDocument();
                    typeDocument.setId(rs.getInt("id"));
                    typeDocument.setCheminRelatif(rs.getString("cheminRelatif"));
                    typeDocument.setContrat(rs.getString("contrat"));
                    typeDocument.setCheminAbsolu(rs.getString("cheminAbsolu"));
                    list.add(typeDocument);
                }
                try (ResultSet rsCount = preparedStatement.executeQuery("SELECT FOUND_ROWS()")) {
                    if (rsCount.next()) {
                        this.noOfRecords = rsCount.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de rechercher les enregistrements dans la table typeDocument"+ e);
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
    public List<TypeDocument> rechercheLikeTypeDocuments(int offset, int noOfRecords, String select_tri, String select_like) throws DaoException {
        List<TypeDocument> list = new ArrayList<>();
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM typeDocument WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?;";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    TypeDocument typeDocument = new TypeDocument();
                    typeDocument.setId(rs.getInt("id"));
                    typeDocument.setCheminRelatif(rs.getString("cheminRelatif"));
                    typeDocument.setContrat(rs.getString("contrat"));
                    typeDocument.setCheminAbsolu(rs.getString("cheminAbsolu"));
                    list.add(typeDocument);
                }
                try (ResultSet rsCount = preparedStatement.executeQuery("SELECT FOUND_ROWS()")) {
                    if (rsCount.next()) {
                        this.noOfRecords = rsCount.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de rechercher les enregistrements dans la table typeDocument"+ e);
        }
        return list;
    }
}
