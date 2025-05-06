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

import com.ws.beans.Client;
import com.ws.configuration.DatasourceH;

public class ClientDaoImpl implements ClientDao {
    private DaoFactory daoFactory;
    private String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private int noOfRecords;


	  ClientDaoImpl(DaoFactory daoFactory) { this.daoFactory = daoFactory; }

 	// =================================================================================
  	// LISTER DES CLIENTS
  	// =================================================================================
    @Override
    public List<Client> listerClient() throws DaoException {
        List<Client> clients = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "SELECT organisation.id, entite, raison_sociale, adresse, code_postal, ville, pays, siren, siret, code_naf, tva, metier, telephone, email, relation FROM organisation"
            		+ " left join entite on organisation.entite=entite.id where entite.nom='Client'"
            		+ ";";
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                Client client = new Client();
                client.setId(resultat.getInt("id"));
                client.setEntite(resultat.getInt("entite"));
                client.setRaison_sociale(resultat.getString("raison_sociale"));
                client.setNom(resultat.getString("raison_sociale"));
				client.setAdresse(resultat.getString("adresse"));
				client.setCode_postal(resultat.getString("code_postal"));
				client.setVille(resultat.getString("ville"));
				client.setPays(resultat.getString("pays"));
				client.setSiren(resultat.getString("siren"));
				client.setSiret(resultat.getString("siret"));
				client.setCode_naf(resultat.getString("code_naf"));
				client.setTva(resultat.getInt("tva"));
				client.setMetier(resultat.getInt("metier"));
				client.setTelephone(resultat.getString("telephone"));
				client.setEmail(resultat.getString("email"));
				client.setRelation(resultat.getInt("relation"));
                clients.add(client);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister les enregistrements avec la table Client"+ e);
        } finally {
            closeResources(connexion, statement, resultat);
        }
        return clients;
    }

    @Override
    public Client trouverClientParId(int id) {
        Client client = null;
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement ps = connexion.prepareStatement("SELECT * FROM organisation left join entite on organisation.entite=entite.id where entite.nom='Client' and organisation.id = ?")) {
            ps.setInt(1, id);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                client = new Client();
                client.setId(rs.getInt("id"));
                client.setRaison_sociale(rs.getString("raison_sociale"));
                client.setAdresse(rs.getString("adresse"));
                client.setCode_postal(rs.getString("code_postal"));
                client.setVille(rs.getString("ville"));
                client.setPays(rs.getString("pays"));
                client.setTelephone(rs.getString("telephone"));
                client.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur DAO Client par ID", e);
        }
        return client;
    }


    // =================================================================================
	// RECUPERATION N° ENREGISTREMENT ENCOURS CLIENT
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
	// FERMETURE DES RESSOURCES CLIENT
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