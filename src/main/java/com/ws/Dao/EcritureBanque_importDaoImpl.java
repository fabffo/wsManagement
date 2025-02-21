package com.ws.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.ws.beans.EcritureBanque_detail;
import com.ws.beans.EcritureBanque_import;

public class EcritureBanque_importDaoImpl implements EcritureBanque_importDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;
    private int noOfRecordsVisualisation;
    private int noOfRecordsRapprochement;

	  EcritureBanque_importDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	@Override
	public void importerEcritureBanque(EcritureBanque_import entete, List<EcritureBanque_detail> detailsList) throws DaoException {

			Connection connection = null;
		    PreparedStatement insertImportStmt = null;
		    PreparedStatement insertDetailStmt = null;
		    ResultSet generatedKeys = null;

		    try {
		    	connection = daoFactory.getConnection();
				connection.setAutoCommit(false); // Début de la transaction

		        // Insérer l'en-tête
		        String insertImportSQL = "INSERT INTO ecriturebanque_import (nom_import, code_banque, nbr_lignes, date_import, user_import, pgm_import) VALUES (?, ?, ?, ?, ?, ?)";
		        insertImportStmt = connection.prepareStatement(insertImportSQL, PreparedStatement.RETURN_GENERATED_KEYS);

		        // Paramètres de l'en-tête
		        insertImportStmt.setString(1, entete.getNom_import());
		        insertImportStmt.setString(2, entete.getCode_banque());
		        insertImportStmt.setInt(3, entete.getNbr_ligne());
		        insertImportStmt.setString(4, dateTime);
		        insertImportStmt.setString(5, System.getProperty("user.name"));
		        insertImportStmt.setString(6, "EcritureBanque_importDaoImpl");

		        // Exécuter l'insertion
		        insertImportStmt.executeUpdate();

		        // Récupérer l'ID généré
		        generatedKeys = insertImportStmt.getGeneratedKeys();
		        int importId = 0;
		        if (generatedKeys.next()) {
		            importId = generatedKeys.getInt(1); // ID de l'en-tête inséré
		        }

		        // Insérer les détails
		        String insertDetailSQL = "INSERT INTO ecriturebanque_detail (id, numero_ligne, date_ecriture, libelle_ecriture, debit, credit) VALUES (?, ?, ?, ?, ?, ?)";
		        insertDetailStmt = connection.prepareStatement(insertDetailSQL);
		        int ligne = 0;
		        for (EcritureBanque_detail detail : detailsList) {
		        	ligne = ligne +1;
		            insertDetailStmt.setInt(1, importId); // Utiliser l'ID de l'en-tête
		            insertDetailStmt.setInt(2, ligne);
		            insertDetailStmt.setDate(3, new java.sql.Date(detail.getDate_ecriture().getTime()));
		            insertDetailStmt.setString(4, detail.getLibelle_ecriture());
		            insertDetailStmt.setDouble(5, detail.getDebit());
		            insertDetailStmt.setDouble(6, detail.getCredit());

		            insertDetailStmt.addBatch(); // Ajouter à un lot
		        }

		        // Exécuter les insertions en lot
		        insertDetailStmt.executeBatch();
		        connection.commit(); // Valide la transaction

		    } catch (SQLException e) {
		        e.printStackTrace(); // Gérer les exceptions
		    } finally {
		        // Fermer les ressources
		        try {
		            if (generatedKeys != null) generatedKeys.close();
		            if (insertDetailStmt != null) insertDetailStmt.close();
		            if (insertImportStmt != null) insertImportStmt.close();
		            if (connection != null) connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}


	@Override
	public List<EcritureBanque_import> listerEcritureBanque_import() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public EcritureBanque_import trouverEcritureBanque_import(Integer date) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public List<EcritureBanque_import> rechercheEcritureBanque_imports(int offset, int noOfRecords, String select_tri) {
        List<EcritureBanque_import> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM ecritureBanque_import ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                EcritureBanque_import ecritureBanque_import = new EcritureBanque_import();
                ecritureBanque_import.setId(rs.getInt("id"));
                ecritureBanque_import.setDate_import(rs.getDate("date_import"));
                ecritureBanque_import.setNom_import(rs.getString("nom_import"));
                ecritureBanque_import.setUser_import(rs.getString("user_import"));
                ecritureBanque_import.setNbr_ligne(rs.getInt("nbr_lignes"));
                list.add(ecritureBanque_import);
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

    @Override
    public List<EcritureBanque_import> rechercheLikeEcritureBanque_imports(int offset, int noOfRecords, String select_tri, String select_like) {
        List<EcritureBanque_import> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM ecritureBanque_import WHERE " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                EcritureBanque_import ecritureBanque_import = new EcritureBanque_import();
                ecritureBanque_import.setId(rs.getInt("id"));
                ecritureBanque_import.setDate_import(rs.getDate("date_import"));
                ecritureBanque_import.setNom_import(rs.getString("nom_import"));
                ecritureBanque_import.setUser_import(rs.getString("user_import"));
                ecritureBanque_import.setNbr_ligne(rs.getInt("nbr_lignes"));
                list.add(ecritureBanque_import);
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


    @Override
    public List<EcritureBanque_detail> rechercheLikeVisualisationEcritureBanque_imports(int id, int offset, int noOfRecords, String select_tri, String select_like) {
        List<EcritureBanque_detail> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM ecritureBanque_detail WHERE id =" + id + " and " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
           rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	EcritureBanque_detail ecritureBanque_detail = new EcritureBanque_detail();
                ecritureBanque_detail.setId(rs.getInt("id"));
                ecritureBanque_detail.setNumero_ligne(rs.getInt("numero_ligne"));
                ecritureBanque_detail.setDate_ecriture(rs.getDate("date_ecriture"));
                ecritureBanque_detail.setLibelle_ecriture(rs.getString("libelle_ecriture"));
                ecritureBanque_detail.setDebit(rs.getInt("debit"));
                ecritureBanque_detail.setCredit(rs.getInt("credit"));
                list.add(ecritureBanque_detail);
            }

            rs.close();

            preparedStatement = connexion.prepareStatement("SELECT FOUND_ROWS()");
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                this.noOfRecordsVisualisation = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connexion, preparedStatement, rs);
        }
        return list;
    }



	@Override
    public List<EcritureBanque_detail> rechercheVisualisationEcritureBanque_imports(int id, int offset, int noOfRecords, String select_tri) {
        List<EcritureBanque_detail> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM ecritureBanque_detail WHERE id =" + id + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                EcritureBanque_detail ecritureBanque_detail = new EcritureBanque_detail();
                ecritureBanque_detail.setId(rs.getInt("id"));
                ecritureBanque_detail.setNumero_ligne(rs.getInt("numero_ligne"));
                ecritureBanque_detail.setDate_ecriture(rs.getDate("date_ecriture"));
                ecritureBanque_detail.setLibelle_ecriture(rs.getString("libelle_ecriture"));
                ecritureBanque_detail.setDebit(rs.getInt("debit"));
                ecritureBanque_detail.setCredit(rs.getInt("credit"));
                list.add(ecritureBanque_detail);
            }

            rs.close();

            preparedStatement = connexion.prepareStatement("SELECT FOUND_ROWS()");
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                this.noOfRecordsVisualisation = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connexion, preparedStatement, rs);
        }
        return list;
    }


    @Override
    public List<EcritureBanque_detail> rechercheLikeRapprochementEcritureBanques(int id, int yearMonth, int offset, int noOfRecords, String select_tri, String select_like) {
        List<EcritureBanque_detail> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM ecritureBanque_detail WHERE  annee_mois=202404" + " and " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	EcritureBanque_detail ecritureBanque_detail = new EcritureBanque_detail();
                ecritureBanque_detail.setId(rs.getInt("id"));
                ecritureBanque_detail.setNumero_ligne(rs.getInt("numero_ligne"));
                ecritureBanque_detail.setDate_ecriture(rs.getDate("date_ecriture"));
                ecritureBanque_detail.setLibelle_ecriture(rs.getString("libelle_ecriture"));
                ecritureBanque_detail.setNom_entite(rs.getString("nom_entite"));
                ecritureBanque_detail.setEntite(rs.getString("entite"));
                ecritureBanque_detail.setStatut_rapproche(rs.getString("statut_rapproche"));
                ecritureBanque_detail.setMontant_ttc(rs.getFloat("montant_ttc"));
                ecritureBanque_detail.setSigne(rs.getString("signe"));
                list.add(ecritureBanque_detail);
            }

            rs.close();

            preparedStatement = connexion.prepareStatement("SELECT FOUND_ROWS()");
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                this.noOfRecordsRapprochement = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connexion, preparedStatement, rs);
        }
        return list;
    }



	@Override
    public List<EcritureBanque_detail> rechercheRapprochementEcritureBanques(int id, int yearMonth, int offset, int noOfRecords, String select_tri) {
        List<EcritureBanque_detail> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM ecritureBanque_detail WHERE annee_mois=202404 " + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                EcritureBanque_detail ecritureBanque_detail = new EcritureBanque_detail();
                ecritureBanque_detail.setId(rs.getInt("id"));
                ecritureBanque_detail.setNumero_ligne(rs.getInt("numero_ligne"));
                ecritureBanque_detail.setDate_ecriture(rs.getDate("date_ecriture"));
                ecritureBanque_detail.setLibelle_ecriture(rs.getString("libelle_ecriture"));
                ecritureBanque_detail.setNom_entite(rs.getString("nom_entite"));
                ecritureBanque_detail.setEntite(rs.getString("entite"));
                ecritureBanque_detail.setStatut_rapproche(rs.getString("statut_rapproche"));
                ecritureBanque_detail.setMontant_ttc(rs.getFloat("montant_ttc"));
                ecritureBanque_detail.setSigne(rs.getString("signe"));
                list.add(ecritureBanque_detail);
                }

            rs.close();

            preparedStatement = connexion.prepareStatement("SELECT FOUND_ROWS()");
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                this.noOfRecordsRapprochement = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connexion, preparedStatement, rs);
        }
        return list;
    }



    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    @Override
    public int getNoOfRecordsVisualisation() {
        return noOfRecordsVisualisation;
    }

    @Override
    public int getNoOfRecordsRapprochement() {
        return noOfRecordsRapprochement;
    }

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