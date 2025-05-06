package com.ws.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ws.beans.MaSociete;
import com.ws.beans.MaSociete;
import com.ws.configuration.DatasourceH;

public class MaSocieteDaoImpl implements MaSocieteDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  MaSocieteDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

 	// =================================================================================
  	// LISTER DES MASOCIETES
  	// =================================================================================
    @Override
    public List<MaSociete> listerMaSociete() throws DaoException {
        List<MaSociete> maSocietes = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT organisation.id, entite, raison_sociale, adresse, code_postal, ville, pays, siren, siret, code_naf, tva, metier, telephone, email, relation FROM organisation"
            		+ " left join entite on organisation.entite=entite.id where entite.nom='Interne'"
            		+ ";";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                MaSociete maSociete = new MaSociete();
                maSociete.setId(resultat.getInt("id"));
                maSociete.setEntite(resultat.getInt("entite"));
                maSociete.setRaison_sociale(resultat.getString("raison_sociale"));
                maSociete.setNom(resultat.getString("raison_sociale"));
				maSociete.setAdresse(resultat.getString("adresse"));
				maSociete.setCode_postal(resultat.getString("code_postal"));
				maSociete.setVille(resultat.getString("ville"));
				maSociete.setPays(resultat.getString("pays"));
				maSociete.setSiren(resultat.getString("siren"));
				maSociete.setSiret(resultat.getString("siret"));
				maSociete.setCode_naf(resultat.getString("code_naf"));
				maSociete.setTva(resultat.getInt("tva"));
				maSociete.setMetier(resultat.getInt("metier"));
				maSociete.setTelephone(resultat.getString("telephone"));
				maSociete.setEmail(resultat.getString("email"));
				maSociete.setRelation(resultat.getInt("relation"));
                maSocietes.add(maSociete);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table MaSociete"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return maSocietes;
    }
    @Override
    public MaSociete trouverMasocieteParId(int id) {
    	MaSociete maSociete = null;
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement ps = connexion.prepareStatement("SELECT * FROM organisation left join entite on organisation.entite=entite.id where entite.nom='Interne' and organisation.id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maSociete = new MaSociete();
                maSociete.setId(rs.getInt("id"));
                maSociete.setRaison_sociale(rs.getString("raison_sociale"));
                maSociete.setAdresse(rs.getString("adresse"));
                maSociete.setCode_postal(rs.getString("code_postal"));
                maSociete.setVille(rs.getString("ville"));
                maSociete.setPays(rs.getString("pays"));
                maSociete.setTelephone(rs.getString("telephone"));
                maSociete.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur DAO MaSociete par ID", e);
        }
        return maSociete;
    }
    // =================================================================================
	// RECUPERATION N° ENREGISTREMENT ENCOURS MASOCIETE
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
	// FERMETURE DES RESSOURCES MASOCIETE
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