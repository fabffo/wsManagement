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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ws.beans.Activite;
import com.ws.beans.ContratAncien;
import com.ws.beans.FactureVente;
import com.ws.beans.FactureVente_detail;
import com.ws.configuration.DatasourceH;

public class FactureVenteDaoImpl implements FactureVenteDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  FactureVenteDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

	  	// =================================================================================
		// AJOUTER FACTUREVENTE
		// =================================================================================
    @Override
    public void ajouterFactureVente(FactureVente fv, List<FactureVente_detail> details) throws SQLException {
        Connection conn = daoFactory.getConnection();
        PreparedStatement psFacture = null, psDetail = null;
        System.out.println(fv.toString()+details.toString());
        try {
            conn.setAutoCommit(false);
            String sqlF = "INSERT INTO factureVente(nom, maSociete, client, typeFactureVente, document, tva, date_facture, date_echeance, montant_ht, montant_ttc, montant_tva, usercreation, datecreation, pgmcreation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            psFacture = conn.prepareStatement(sqlF, Statement.RETURN_GENERATED_KEYS);

            psFacture.setString(1, fv.getNom());
            psFacture.setInt(2, fv.getMaSociete());
            psFacture.setInt(3, fv.getClient());
            psFacture.setInt(4, fv.getTypeFactureVente());
            psFacture.setString(5, fv.getDocument());
            psFacture.setInt(6, fv.getTva());
            psFacture.setString(7, fv.getDate_facture());
            psFacture.setString(8, fv.getDate_echeance());
            psFacture.setDouble(9, fv.getMontant_ht());
            psFacture.setDouble(10, fv.getMontant_ttc());
            psFacture.setDouble(11, fv.getMontant_tva());
            psFacture.setString(12, System.getProperty("user.name"));
            psFacture.setString(13, dateTime);
            psFacture.setString(14, "FactureVenteDao");

            psFacture.executeUpdate();
            ResultSet rs = psFacture.getGeneratedKeys();
            int id_facture = rs.next() ? rs.getInt(1) : 0;

            String sqlD = "INSERT INTO factureVente_detail(id, numero_ligne, mission, taux_tva, montant_ht, montant_tva, montant_ttc, usercreation, datecreation, pgmcreation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            psDetail = conn.prepareStatement(sqlD);
            for (FactureVente_detail d : details) {
                psDetail.setInt(1, id_facture);
                psDetail.setInt(2, d.getNumero_ligne());
                psDetail.setString(3, d.getProduit());
                psDetail.setDouble(4, d.getTaux_tva());
                psDetail.setDouble(5, d.getMontant_ht());
                psDetail.setDouble(6, d.getMontant_tva());
                psDetail.setDouble(7, d.getMontant_ttc());
                psDetail.setString(8, System.getProperty("user.name"));
                psDetail.setString(9, dateTime);
                psDetail.setString(10, "FactureVenteDao");
                psDetail.addBatch();
            }
            psDetail.executeBatch();
            conn.commit();
        } catch (Exception e) {
            if (conn != null) conn.rollback();
            throw new SQLException("Erreur insertion FactureVente : " + e.getMessage());
        } finally {
            if (psFacture != null) psFacture.close();
            if (psDetail != null) psDetail.close();
            if (conn != null) conn.close();
        }
    }

    	// =================================================================================
  		// COPIER FACTUREVENTE
  		// =================================================================================
    @Override
    public void copierFactureVente(FactureVente facture, List<FactureVente_detail> lignes) throws SQLException {
        ajouterFactureVente(facture, lignes);
    }


    	// =================================================================================
		// TROUVER FACTUREVENTE PAR ID
		// =================================================================================
    @Override
    public FactureVente trouverFactureVente(int id) throws SQLException {
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        FactureVente fv = null;

        try {
            String sql = "SELECT * FROM factureVente WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                fv = new FactureVente();
                fv.setId(rs.getInt("id"));
                fv.setNom(rs.getString("nom"));
                fv.setMaSociete(rs.getInt("maSociete"));
                fv.setClient(rs.getInt("client"));
                fv.setTypeFactureVente(rs.getInt("typeFactureVente"));
                fv.setDocument(rs.getString("document"));
                fv.setTva(rs.getInt("tva"));
                fv.setDate_facture(rs.getString("date_facture"));
                fv.setDate_echeance(rs.getString("date_echeance"));
                fv.setMontant_ht(rs.getDouble("montant_ht"));
                fv.setMontant_ttc(rs.getDouble("montant_ttc"));
                fv.setMontant_tva(rs.getDouble("montant_tva"));
                fv.setUsermodification(rs.getString("usermodification"));
                fv.setDatemodification(rs.getTimestamp("datemodification"));
                fv.setPgmmodification(rs.getString("pgmmodification"));
                fv.setUsercreation(rs.getString("usercreation"));
                fv.setDatecreation(rs.getTimestamp("datecreation"));
                fv.setPgmcreation(rs.getString("pgmcreation"));

                // charger les lignes associées
                fv.setLignes(trouverLignesFacture(fv.getId()));
            }

        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return fv;
    }



    public List<FactureVente_detail> trouverLignesFacture(int id_facture) throws SQLException {
        List<FactureVente_detail> lignes = new ArrayList<>();
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM factureVente_detail WHERE id_factureVente = ?");
            ps.setInt(1, id_facture);
            rs = ps.executeQuery();

            while (rs.next()) {
                FactureVente_detail ligne = new FactureVente_detail();
                ligne.setId(rs.getInt("id"));
                ligne.setNumero_ligne(rs.getInt("numero_ligne"));
                ligne.setProduit(rs.getString("produit"));
                ligne.setTaux_tva(rs.getDouble("taux_tva"));
                ligne.setMontant_ht(rs.getDouble("montant_ht"));
                ligne.setMontant_ttc(rs.getDouble("montant_ttc"));
                ligne.setMontant_tva(rs.getDouble("montant_tva"));
                ligne.setUsermodification(rs.getString("usermodification"));
                ligne.setDatemodification(rs.getTimestamp("datemodification"));
                ligne.setPgmmodification(rs.getString("pgmmodification"));
                ligne.setUsercreation(rs.getString("usercreation"));
                ligne.setDatecreation(rs.getTimestamp("datecreation"));
                ligne.setPgmcreation(rs.getString("pgmcreation"));
                lignes.add(ligne);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return lignes;
    }


    	// =================================================================================
		// RECHERCHE FACTUREVENTE
		// =================================================================================
    @Override
    public List<Map<String, Object>> rechercheFactureVentes(Integer offset, Integer noOfRecords, String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_factureVente) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
        	connexion = daoFactory.getConnection();
            String query = "SELECT SQL_CALC_FOUND_ROWS  factureVente.id, factureVente.nom, document, factureVente.maSociete, factureVente.tva, date_facture, montant_ht, montant_ttc, montant_tva, tva.nom, maSociete.nom, client.raison_sociale, typeFactureVente.nom FROM factureVente "
            		+ "left join tva on factureVente.tva=tva.id left join maSociete on factureVente.maSociete=maSociete.id left join client on factureVente.client=client.id left join typeFactureVente on factureVente.typeFactureVente=typeFactureVente.id "
            		+ " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            System.out.println(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Map<String, Object> factureVenteFields = new LinkedHashMap<>();
                if (dictionnaire_nom_colonne.containsKey("id")) {
                	factureVenteFields.put("id", rs.getInt("id"));
				}
                if (dictionnaire_nom_colonne.containsKey("nom")) {
                	factureVenteFields.put("nom", rs.getString("nom"));
				}
                if (dictionnaire_nom_colonne.containsKey("maSociete")) {
                	factureVenteFields.put("maSociete", rs.getString("maSociete.nom"));
				}
                if (dictionnaire_nom_colonne.containsKey("client")) {
                	factureVenteFields.put("client", rs.getString("client.raison_sociale"));
				}
                if (dictionnaire_nom_colonne.containsKey("typeFactureVente")) {
                	factureVenteFields.put("typeFactureVente", rs.getString("typeFactureVente.nom"));
				}
                if (dictionnaire_nom_colonne.containsKey("document")) {
                	factureVenteFields.put("document", rs.getString("document"));
				}
                if (dictionnaire_nom_colonne.containsKey("tva")) {
                	factureVenteFields.put("tva", rs.getString("tva.nom"));
				}
                if (dictionnaire_nom_colonne.containsKey("date_facture")) {
                	factureVenteFields.put("date_facture", rs.getString("date_facture"));
				}
                if (dictionnaire_nom_colonne.containsKey("montant_ht")) {
                	factureVenteFields.put("montant_ht", rs.getDouble("montant_ht"));
				}
                if (dictionnaire_nom_colonne.containsKey("montant_ttc")) {
                	factureVenteFields.put("montant_ttc", rs.getDouble("montant_ttc"));
				}
                if (dictionnaire_nom_colonne.containsKey("montant_tva")) {
                	factureVenteFields.put("montant_tva", rs.getDouble("montant_tva"));
				}
                list.add(factureVenteFields);
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
		// RECHERCHE FACTUREVENTES SUIVANT LIKE
		// =================================================================================
    @Override
    public List<Map<String, Object>>  rechercheLikeFactureVentes(Integer offset, Integer noOfRecords, String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_factureVente) {
    	List<Map<String, Object>> list = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            String query ="SELECT SQL_CALC_FOUND_ROWS  factureVente.id, factureVente.nom, factureVente.maSociete, document, factureVente.tva, date_facture, montant_ht, montant_ttc, montant_tva, tva.nom, maSociete.nom, client.raison_sociale, typeFactureVente.nom FROM factureVente "
            		+ "left join tva on factureVente.tva=tva.id left join maSociete on factureVente.maSociete=maSociete.id  left join client on factureVente.client=client.id  left join typeFactureVente on factureVente.typeFactureVente=typeFactureVente.id "
            		+ " and " + select_like + " ORDER BY " + select_tri + " LIMIT ?, ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Map<String, Object> factureVenteFields = new LinkedHashMap<>();
            	 if (dictionnaire_nom_colonne.containsKey("id")) {
                 	factureVenteFields.put("id", rs.getInt("id"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("nom")) {
                 	factureVenteFields.put("nom", rs.getString("nom"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("maSociete")) {
                 	factureVenteFields.put("maSociete", rs.getString("maSociete.nom"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("client")) {
                 	factureVenteFields.put("client", rs.getString("client.raison_sociale"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("typeFactureVente")) {
                 	factureVenteFields.put("typeFactureVente", rs.getString("typeFactureVente.nom"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("document")) {
                 	factureVenteFields.put("document", rs.getString("document"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("tva")) {
                 	factureVenteFields.put("tva", rs.getString("tva.nom"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("date_facture")) {
                 	factureVenteFields.put("date_facture", rs.getString("date_facture"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("montant_ht")) {
                 	factureVenteFields.put("montant_ht", rs.getDouble("montant_ht"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("montant_ttc")) {
                 	factureVenteFields.put("montant_ttc", rs.getDouble("montant_ttc"));
 				}
                 if (dictionnaire_nom_colonne.containsKey("montant_tva")) {
                 	factureVenteFields.put("montant_tva", rs.getDouble("montant_tva"));
 				}
                list.add(factureVenteFields);
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
    public void updatePdfPath(int id, String pdfPath) throws SQLException {
        String sql = "UPDATE facturevente SET document = ? WHERE id = ?";
        try (Connection c = daoFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, pdfPath);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }


    // =================================================================================
	// RECUPERATION N° ENREGISTREMENT ENCOURS FACTUREVENTE
	// =================================================================================
    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    @Override
    public Integer getIntegerRecords() {
    	Integer integerRecords = noOfRecords;
        return integerRecords;
    }


    // =================================================================================
	// FERMETURE DES RESSOURCES FACTUREVENTE
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