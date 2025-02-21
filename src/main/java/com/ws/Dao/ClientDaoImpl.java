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

import com.ws.beans.Contrat;
import com.ws.beans.Organisation;
import com.ws.configuration.DatasourceH;

public class ClientDaoImpl implements ClientDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  ClientDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }



 	// =================================================================================
  	// LISTER DES ORGANISATIONS
  	// =================================================================================
    @Override
    public List<Organisation> listerClient() throws DaoException {
        List<Organisation> organisations = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT organisation.id, organisation.entite, organisation.raison_sociale, adresse, code_postal, ville, pays, siren, siret, code_naf, organisation.tva, organisation.metier, organisation.telephone, organisation.email, organisation.relation FROM organisation "
            		+ " left join typecontrat on typecontrat.id=organisation.entite left join entite on organisation.entite=entite.id and entite.nom='CLIENT';";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Organisation organisation = new Organisation();
                organisation.setId(resultat.getInt("id"));
                organisation.setEntite(resultat.getInt("entite"));
                organisation.setRaison_sociale(resultat.getString("raison_sociale"));
                organisation.setNom(resultat.getString("raison_sociale"));
				organisation.setAdresse(resultat.getString("adresse"));
				organisation.setCode_postal(resultat.getString("code_postal"));
				organisation.setVille(resultat.getString("ville"));
				organisation.setPays(resultat.getString("pays"));
				organisation.setSiren(resultat.getString("siren"));
				organisation.setSiret(resultat.getString("siret"));
				organisation.setCode_naf(resultat.getString("code_naf"));
				organisation.setTva(resultat.getInt("tva"));
				organisation.setMetier(resultat.getInt("metier"));
				organisation.setTelephone(resultat.getString("telephone"));
				organisation.setEmail(resultat.getString("email"));
				organisation.setRelation(resultat.getInt("relation"));
                organisations.add(organisation);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Organisation"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return organisations;
    }
    // =================================================================================
	// FERMETURE DES RESSOURCES ORGANISATION
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