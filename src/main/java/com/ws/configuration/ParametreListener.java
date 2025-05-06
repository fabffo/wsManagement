package com.ws.configuration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;




@WebListener
public class ParametreListener implements ServletContextListener {
	private Map<String, Map<String, String>> contracts = new HashMap<>();
	private Map<String, Map<String, String>> cheminsContrats = new HashMap<>();

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    	  String id_typecontrat=null;
    	  String contrat = null;
		  String cheminRelatif;
		  String cheminAbsolu;

		  try (Connection connection = DatasourceH.getConnection();
				  Statement statement = connection.createStatement();
				  ResultSet resultSet = statement.executeQuery("SELECT contrat, cheminAbsolu, cheminRelatif FROM typedocument"
		  )) { while (resultSet.next()) {

		  // Récupérer les paramètres du formulaire
			  Map<String, String> contractDetails = new HashMap<>();
			   contrat = resultSet.getString("contrat");
			   cheminRelatif = resultSet.getString("cheminRelatif");
			   cheminAbsolu = resultSet.getString("cheminAbsolu");
			  contractDetails.put("cheminRelatif", cheminRelatif); contractDetails.put("cheminAbsolu", cheminAbsolu);
		  // Ajouter la Map à la Map des contrats
			  contracts.put(contrat, contractDetails);
			  }

		  } catch (Exception e) { e.printStackTrace(); }

		  // Stocker les paramètres dans le ServletContext
		  sce.getServletContext().setAttribute("contracts", contracts);

		  try (Connection connection = DatasourceH.getConnection();
				  Statement statement = connection.createStatement();
				  ResultSet resultSet = statement.executeQuery("SELECT id, cheminAbsolu, cheminRelatif FROM typecontrat"
		  )) { while (resultSet.next()) {

		  // Récupérer les paramètres du formulaire
			  Map<String, String> cheminsContrat = new HashMap<>();
			   id_typecontrat = resultSet.getString("id");
			   cheminRelatif = resultSet.getString("cheminRelatif");
			   cheminAbsolu = resultSet.getString("cheminAbsolu");
			   cheminsContrat.put("cheminRelatif", cheminRelatif);
			   cheminsContrat.put("cheminAbsolu", cheminAbsolu);
		  // Ajouter la Map à la Map des contrats
			  cheminsContrats.put(id_typecontrat, cheminsContrat);
			  }

		  } catch (Exception e) { e.printStackTrace(); }

		  // Stocker les paramètres dans le ServletContext
		  sce.getServletContext().setAttribute("cheminsContrats", cheminsContrats);

    }


	@Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Optionnel: nettoyage des ressources si nécessaire
    }
}
