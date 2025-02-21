/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO IMPLEMENTATION SOCIETE                              ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.ws.beans.Parametre;
import com.ws.beans.ParametreSysteme;
import com.ws.beans.Societe;
import com.ws.menu.MenuItem;
import com.ws.beans.Parametre_detail;
import com.ws.beans.Parametre_ecranCrud_entete;
import com.ws.beans.Parametre_ecranCrud_ligne;
import com.ws.beans.Parametre_ecranGestion_entete;
import com.ws.beans.Parametre_ecranGestion_ligne;
import com.ws.beans.Parametre_entete;

public class ParametreEcranDaoImpl implements ParametreEcranDao {
	private DaoFactory daoFactory;
	 private Object daoInstancelist;  // Instance dynamique du DAO
	// date du jour
	String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
	private int noOfRecords;
	Map<String, Integer> contactDictionary = new HashMap<>();

	ParametreEcranDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ParametreSysteme lireParametreSysteme(String parametre) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ParametreSysteme parametreSysteme_entete =null;
	        String query = "SELECT * FROM parametreSysteme WHERE nom = ?";

	        try {
				connexion = daoFactory.getConnection();
				preparedStatement = connexion.prepareStatement(query);

				    preparedStatement.setString(1, parametre);
				    resultSet = preparedStatement.executeQuery();

				    while (resultSet.next()) {
				        parametreSysteme_entete = new ParametreSysteme();
				        parametreSysteme_entete.setId(resultSet.getInt("id"));
				        parametreSysteme_entete.setNom(resultSet.getString("nom"));
				        parametreSysteme_entete.setLibelle(resultSet.getString("libelle"));
				        parametreSysteme_entete.setNom_ecran_gestion(resultSet.getString("nom_ecran_gestion"));
				        parametreSysteme_entete.setNom_ecran_crud(resultSet.getString("nom_ecran_crud"));
				    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        return parametreSysteme_entete;
	    }

	@Override
	public Parametre_ecranGestion_entete lireParametre_ecranGestion_entete(int id_parametreSysteme) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Parametre_ecranGestion_entete parametre_ecranGestion_entete =null;
	        String query = "SELECT * FROM parametre_ecranGestion_entete WHERE parametreSysteme = ?";

	        try {
				connexion = daoFactory.getConnection();
				preparedStatement = connexion.prepareStatement(query);

				    preparedStatement.setInt(1, id_parametreSysteme);
				    resultSet = preparedStatement.executeQuery();

				    while (resultSet.next()) {
				    	parametre_ecranGestion_entete = new Parametre_ecranGestion_entete();
				    	parametre_ecranGestion_entete.setPasPage(resultSet.getInt("pasPage"));
				    	parametre_ecranGestion_entete.setNbrEnrgPage(resultSet.getInt("nbrEnrgPage"));
				    	parametre_ecranGestion_entete.setType_entete(resultSet.getString("type_entete"));
				    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        return parametre_ecranGestion_entete;
	    }

	    @Override
	    public List<Parametre_ecranGestion_ligne> listerParametre_ecranGestion_ligne(int id_parametreSysteme) throws DaoException {
			Connection connexion = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			 List<Parametre_ecranGestion_ligne> parametre_ecranGestion_lignes = new ArrayList<>();
		        String query = "SELECT id, parametreSysteme, numero_ligne, numero_champ, nom_zone, largeur_colonne, type_colonne, zone_recherche FROM parametre_ecranGestion_ligne where parametreSysteme = ? order by numero_ligne, numero_champ;";

		        try {
					connexion = daoFactory.getConnection();
					preparedStatement = connexion.prepareStatement(query);

					    preparedStatement.setInt(1, id_parametreSysteme);
					    resultSet = preparedStatement.executeQuery();

					    while (resultSet.next()) {
					    	Parametre_ecranGestion_ligne parametre_ecranGestion_ligne = new Parametre_ecranGestion_ligne();
			                parametre_ecranGestion_ligne.setId(resultSet.getInt("id"));
			                parametre_ecranGestion_ligne.setParametreSysteme(resultSet.getInt("parametreSysteme"));
			                parametre_ecranGestion_ligne.setNumero_ligne(resultSet.getInt("numero_ligne"));
			                parametre_ecranGestion_ligne.setNumero_champ(resultSet.getInt("numero_champ"));
			                parametre_ecranGestion_ligne.setNom_zone(resultSet.getString("nom_zone"));
			                parametre_ecranGestion_ligne.setLargeur_colonne(resultSet.getInt("largeur_colonne"));
			                parametre_ecranGestion_ligne.setType_colonne(resultSet.getString("type_colonne"));
			                parametre_ecranGestion_ligne.setZone_recherche(resultSet.getString("zone_recherche"));
			                parametre_ecranGestion_lignes.add(parametre_ecranGestion_ligne);
					    }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		        return parametre_ecranGestion_lignes;
		    }

	@Override
	public List<Map<String, Object>> lireParametre_ecranGestion_ligne(int id_parametreSysteme) throws SQLException {
	    List<Map<String, Object>> listeChamps = new ArrayList<>();
	    String sql = "SELECT * FROM  Parametre_ecranGestion_ligne WHERE parametreSysteme = ? order by numero_ligne, numero_champ";

	    try (Connection connection = daoFactory.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setInt(1, id_parametreSysteme);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                Map<String, Object> champ = new HashMap<>();
	                champ.put("nom_zone", resultSet.getString("nom_zone"));
	                champ.put("largeur_colonne", resultSet.getBoolean("largeur_colonne"));
	                champ.put("zone_recherche", resultSet.getBoolean("zone_recherche"));
	                listeChamps.add(champ);
	            }
	        }
	    }
	    return listeChamps;
	}


	@Override
	public Parametre_ecranCrud_entete lireParametre_ecranCrud_entete(int id_parametreSysteme, String parametre_nom_programme) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Parametre_ecranCrud_entete parametre_ecranCrud_entete =null;
	        String query = "SELECT * FROM parametre_ecranCrud_entete WHERE parametreSysteme = ? and nom_programme=?";

	        try {
				connexion = daoFactory.getConnection();
				preparedStatement = connexion.prepareStatement(query);

				    preparedStatement.setInt(1, id_parametreSysteme);
				    preparedStatement.setString(2, parametre_nom_programme);
				    resultSet = preparedStatement.executeQuery();

				    while (resultSet.next()) {
				    	parametre_ecranCrud_entete = new Parametre_ecranCrud_entete();
				    	parametre_ecranCrud_entete.setLargeur_ecran(resultSet.getInt("largeur_ecran"));
				    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        return parametre_ecranCrud_entete;
	    }

	@Override
	public List<Map<String, Object>> lireParametre_ecranCrud_ligne(int id_parametreSysteme, String parametre_nom_programme) throws SQLException {
	    List<Map<String, Object>> listeChamps = new ArrayList<>();
	    String sql = "SELECT * FROM  Parametre_ecrancrud_ligne WHERE parametreSysteme = ? and nom_programme= ? order by numero_ligne, numero_champ";

	    try (Connection connection = daoFactory.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setInt(1, id_parametreSysteme);
	        statement.setString(2, parametre_nom_programme);
	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                Map<String, Object> champ = new HashMap<>();
	                champ.put("parametreSysteme", resultSet.getInt("parametreSysteme"));
	                champ.put("numero_ligne", resultSet.getInt("numero_ligne"));
	                champ.put("numero_champ", resultSet.getInt("numero_champ"));
	                champ.put("nom_programme", resultSet.getString("nom_programme"));
	                champ.put("nom_champ", resultSet.getString("nom_champ"));
	                champ.put("required", resultSet.getBoolean("required"));
	                champ.put("readonly", resultSet.getBoolean("readonly"));
	                champ.put("disabled", resultSet.getBoolean("disabled"));
	                champ.put("minlength", resultSet.getInt("minlength"));
	                champ.put("maxlength", resultSet.getInt("maxlength"));
	                champ.put("type", resultSet.getString("type"));
	                champ.put("step", resultSet.getString("step"));
	                champ.put("placeholder", resultSet.getString("placeholder"));
	                champ.put("type_zone", resultSet.getString("type_zone"));
	                champ.put("largeur_libelle", resultSet.getInt("largeur_libelle"));
	                listeChamps.add(champ);
	            }
	        }
	    }
	    return listeChamps;
	}

	@Override
	public List<List<Map<String, Object>>> lireParametre_ecranCrud_multiligne(int id_parametreSysteme, String parametre_nom_programme, Object classe, String entite) throws SQLException, Exception {
	    String sql = "SELECT * FROM Parametre_ecrancrud_ligne WHERE parametreSysteme = ? AND nom_programme = ? ORDER BY numero_ligne, numero_champ";
	    List<List<Map<String, Object>>> rows = new ArrayList<>();
	    String nomChamp = null;

	    try (Connection connection = daoFactory.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setInt(1, id_parametreSysteme);
	        statement.setString(2, parametre_nom_programme);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            List<Map<String, Object>> currentRow = null;
	            int currentNumero = -1;

	            while (resultSet.next()) {
	                int numero = resultSet.getInt("numero_ligne");

	                // Nouvelle ligne si le numéro change
	                if (numero != currentNumero) {
	                    currentRow = new ArrayList<>();
	                    rows.add(currentRow);
	                    currentNumero = numero;
	                }

	                // Gestion d'un champ
	                Map<String, Object> champ = mapperChamp(resultSet);

	                // Si le champ est une entité on vérifie et on remplace par le nom de l'entité client, fournisseur...
	                nomChamp = (String) champ.get("nom_champ");
                    if (nomChamp.equals("Entite")) {
                    	nomChamp= entite;
                    }
	                // Gérer la valeur du champ via réflexion si nécessaire
	                if (!"ajout".equals(parametre_nom_programme)) {
	                    champ.put("valeur", resolveFieldValue(classe, nomChamp));
	                } else {
	                    champ.put("valeur", ""); // Valeur par défaut pour un ajout
	                }

	                // Gérer les listes de sélection si applicable
	                if ("select".equals(champ.get("type_zone"))) {

	                    String nomEntite = nomChamp.substring(0, 1).toUpperCase() + nomChamp.substring(1);
	                    champ.put("listSelect", obtenirListSelect(nomEntite));
	                }

	                currentRow.add(champ);
	                }
	        }
	    }

	    return rows;
	}

	private Map<String, Object> mapperChamp(ResultSet resultSet) throws SQLException {
	    Map<String, Object> champ = new HashMap<>();
	    champ.put("parametreSysteme", resultSet.getInt("parametreSysteme"));
	    champ.put("numero_ligne", resultSet.getInt("numero_ligne"));
	    champ.put("numero_champ", resultSet.getInt("numero_champ"));
	    champ.put("nom_programme", resultSet.getString("nom_programme"));
	    champ.put("nom_champ", resultSet.getString("nom_champ"));
	    champ.put("required", resultSet.getBoolean("required"));
	    champ.put("readonly", resultSet.getBoolean("readonly"));
	    champ.put("disabled", resultSet.getBoolean("disabled"));
	    champ.put("minlength", resultSet.getInt("minlength"));
	    champ.put("maxlength", resultSet.getInt("maxlength"));
	    champ.put("type", resultSet.getString("type"));
	    champ.put("step", resultSet.getString("step"));
	    champ.put("placeholder", resultSet.getString("placeholder"));
	    champ.put("type_zone", resultSet.getString("type_zone"));
	    champ.put("largeur_libelle", resultSet.getInt("largeur_libelle"));
	    return champ;
	}

	private Object resolveFieldValue(Object classe, String fieldName) throws Exception {
	    String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	    Method method = classe.getClass().getMethod(methodName);
	    return  method.invoke(classe);
	}

	private List<Object> obtenirListSelect(String nomEntite) throws Exception {
	    // Générer dynamiquement le nom de la méthode DAO
	    String nomMethodelist = "lister" + nomEntite;

	    // Obtenir l'instance du DAO pour l'entité
	    Object daoInstance = daoFactory.getClass()
	                                   .getMethod("get" + nomEntite + "Dao")
	                                   .invoke(daoFactory);

	    if (daoInstance == null) {
	        throw new IllegalArgumentException("Impossible de trouver le DAO pour l'entité : " + nomEntite);
	    }

	    // Invoquer dynamiquement la méthode de liste
	    return invokeDynamicMethodList(daoInstance, nomMethodelist);
	}

	// Méthode générique pour invoquer dynamiquement des méthodes
	private <T> List<T> invokeDynamicMethodList(Object daoInstance, String methodName) throws Exception {
	    // Obtenir la méthode à partir du DAO
	    Method method = daoInstance.getClass().getMethod(methodName);

	    // Appeler la méthode et retourner le résultat en tant que liste
	    Object result = method.invoke(daoInstance);

	    if (!(result instanceof List<?>)) {
	        throw new IllegalArgumentException("La méthode " + methodName + " n'a pas retourné une liste.");
	    }

	    // Suppression du cast explicite en utilisant des génériques
	    return (List<T>) result;
	}

	@Override
	public Map<String, Map<String, Object>> getParametre_ecranCrud_ligne(int id_parametreSysteme, String parametre_nom_programme) throws SQLException {
		Map<String, Map<String, Object>> validations = new HashMap<>();
		Map<String, Object> config = new HashMap<>();

		List<Map<String, Object>> listeChamps = new ArrayList<>();
	    String sql =  "SELECT * FROM  Parametre_ecrancrud_ligne WHERE parametreSysteme = ? and nom_programme= ? order by numero_ligne, numero_champ";

	    try (Connection connection = daoFactory.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	    	statement.setInt(1, id_parametreSysteme);
	        statement.setString(2, parametre_nom_programme);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                Map<String, Object> champ = new HashMap<>();
	                champ.put("parametreSysteme", resultSet.getInt("parametreSysteme"));
	                champ.put("numero_ligne", resultSet.getInt("numero_ligne"));
	                champ.put("numero_champ", resultSet.getInt("numero_champ"));
	                champ.put("nom_programme", resultSet.getString("nom_programme"));
	                champ.put("nom_champ", resultSet.getString("nom_champ"));
	                champ.put("required", resultSet.getBoolean("required"));
	                champ.put("readonly", resultSet.getBoolean("readonly"));
	                champ.put("disabled", resultSet.getBoolean("disabled"));
	                champ.put("minlength", resultSet.getInt("minlength"));
	                champ.put("maxlength", resultSet.getInt("maxlength"));
	                champ.put("type", resultSet.getString("type"));
	                champ.put("step", resultSet.getString("step"));
	                champ.put("placeholder", resultSet.getString("placeholder"));
	                champ.put("type_zone", resultSet.getString("type_zone"));
	                champ.put("largeur_libelle", resultSet.getInt("largeur_libelle"));
	                champ.put("valeur", null);
	                validations.put(resultSet.getString("nom_champ"), champ);;
	            }
	        }
	    }
	    return validations;
	}


	 // =================================================================================
		// FERMETURE DES RESSOURCES LIGNE PARAMETRE
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
