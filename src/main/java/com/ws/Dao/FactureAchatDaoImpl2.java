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

import com.ws.beans.FactureAchat;

public class FactureAchatDaoImpl2 implements FactureAchatDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;
    private int noOfRecordsVisualisation;
    private int noOfRecordsRapprochement;

	  FactureAchatDaoImpl2(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	@Override
	public void importerFactureAchat(FactureAchat facture) throws DaoException {

			Connection connection = null;
		    PreparedStatement insertImportStmt = null;
		    PreparedStatement insertDetailStmt = null;
		    ResultSet generatedKeys = null;

		    try {
		    	connection = daoFactory.getConnection();
				connection.setAutoCommit(false); // Début de la transaction

		        // Insérer l'en-tête
		        String insertImportSQL = "INSERT INTO factureAchat (entite, nom_entite, ttc, date_facture, user_import, pgm_import) VALUES (?, ?, ?, ?, ?, ?, ?)";
		        insertImportStmt = connection.prepareStatement(insertImportSQL, PreparedStatement.RETURN_GENERATED_KEYS);

		        // Paramètres de l'en-tête
		        insertImportStmt.setString(1, facture.getEntite() );
		        insertImportStmt.setString(2, facture.getNom_entite());
		        insertImportStmt.setFloat(3, facture.getTtc());
		        insertImportStmt.setDate(4, (Date) facture.getDate_facture());
		        insertImportStmt.setString(5, dateTime);
		        insertImportStmt.setString(6, System.getProperty("user.name"));
		        insertImportStmt.setString(7, "FactureAchatDaoImpl");

		        // Exécuter l'insertion
		        insertImportStmt.executeUpdate();

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
	public List<FactureAchat> listerFactureAchat() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public FactureAchat trouverFactureAchat(Integer date) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public List<FactureAchat> rechercheFactureAchats(int offset, int noOfRecords, String select_tri) {
        List<FactureAchat> list = new ArrayList<>();
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
                FactureAchat ecritureBanque_import = new FactureAchat();
                ecritureBanque_import.setId(rs.getInt("id"));
                ecritureBanque_import.setDate_import(rs.getDate("date_import"));
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
    public List<FactureAchat> rechercheLikeFactureAchats(int offset, int noOfRecords, String select_tri, String select_like) {
        List<FactureAchat> list = new ArrayList<>();
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
                FactureAchat ecritureBanque_import = new FactureAchat();
                ecritureBanque_import.setId(rs.getInt("id"));
                ecritureBanque_import.setDate_import(rs.getDate("date_import"));
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
    public List<FactureAchat> rechercheLikeVisualisationFactureAchats(int id, int offset, int noOfRecords, String select_tri, String select_like) {
        List<FactureAchat> list = new ArrayList<>();
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
            	FactureAchat ecritureBanque_detail = new FactureAchat();
                ecritureBanque_detail.setId(rs.getInt("id"));
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
    public List<FactureAchat> rechercheVisualisationFactureAchats(int id, int offset, int noOfRecords, String select_tri) {
        List<FactureAchat> list = new ArrayList<>();
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
                FactureAchat ecritureBanque_detail = new FactureAchat();
                ecritureBanque_detail.setId(rs.getInt("id"));
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
    public List<FactureAchat> rechercheLikeRapprochementFactureAchats(int id, int yearMonth, int offset, int noOfRecords, String select_tri, String select_like) {
        List<FactureAchat> list = new ArrayList<>();
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
            	FactureAchat ecritureBanque_detail = new FactureAchat();
                ecritureBanque_detail.setId(rs.getInt("id"));
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
    public List<FactureAchat> rechercheRapprochementFactureAchats(int id, int yearMonth, int offset, int noOfRecords, String select_tri) {
        List<FactureAchat> list = new ArrayList<>();
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
                FactureAchat ecritureBanque_detail = new FactureAchat();
                ecritureBanque_detail.setId(rs.getInt("id"));
                ecritureBanque_detail.setNom_entite(rs.getString("nom_entite"));
                ecritureBanque_detail.setEntite(rs.getString("entite"));
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