/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM TVA CREATION                                       ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.parametre;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import com.mysql.cj.result.Field;
import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.GenericObjectFactory;
import com.ws.Dao.ParametreDao;
import com.ws.Dao.TvaDao;
import com.ws.beans.Tva;

import jakarta.servlet.http.HttpServletRequest;

public final class ControleDonneesParametreApres {
	private TvaDao tvaDao;
	private ParametreDao parametreDao; // DAO pour les paramètres
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_LIBELLE = "libelle";
	private static final String CHAMP_TAUX = "taux";
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private Map<String, Object> configLibelle = new HashMap<>();
	private Map<String, Object> configNom = new HashMap<>();
	private Map<String, Object> configTaux = new HashMap<>();
	String valeurNom = "";
	String valeurLibelle = "";
	String valeurTaux = "";
	int entite_id;
	float taux = 0;
	Map<String, Map<String, Object>> fields;
	private String fieldName;
	private String fielvalue;
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	public Map<String, Map<String, Object>> getFields() {
		System.out.println("je passe dans fields");
		return fields;
	}

	public String getResultat() {
		return resultat;
	}

	public Object ControlerDonneesParametre(HttpServletRequest request) {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.tvaDao = daoFactory.getTvaDao();
		   daoFactory = DaoFactory.getInstance();
	        this.parametreDao = daoFactory.getParametreDao();  // Récupération du DAO pour la gestion des paramètres

		// Récupération entite_id
		entite_id = Integer.parseInt(request.getParameter("entite_id"));


		// Préparer la Map des propriétés pour initialiser un objet Tva
		Map<String, Object> tvaProperties = new HashMap<>();
		tvaProperties.put("id", entite_id);
		//tvaProperties.put("nom", (request.getParameter("nom")));
		//tvaProperties.put("libelle", (request.getParameter("libelle")));
		//tvaProperties.put("taux", (Double.parseDouble(request.getParameter("taux"))));




		// Créer la map des validations
		Map<String, Map<String, Object>> validations = new HashMap<>();

		// Appel de la méthode pour récupérer les paramètres
        List<Map<String, Object>> listeChamps = null;
		try {
			listeChamps = parametreDao.getParametresChampsEcran(request.getParameter("nom_programme"), "TVA");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Boucle sur chaque configuration pour ajouter les validations dynamiquement
        try {
            for (Map<String, Object> champConfig : listeChamps) {
            	// Vérifier que le champ a un type défini dans la configuration et traiter en conséquence
            	if ("number".equals(champConfig.get("type"))) {
            	    String stepValue = (String) champConfig.get("step");
            	    String fieldValue = request.getParameter((String) champConfig.get("nom_champ"));

            	    // Vérifier si le step contient des chiffres et un point
            	    if (stepValue != null && stepValue.matches("\\d+\\.\\d+")) {
            	        try {
            	            // Convertir la zone en Double si les conditions sont remplies
            	            double taux = Double.parseDouble(request.getParameter("taux"));
            	            System.out.println("Valeur de " + fieldName + " (Double) : " + taux);
            	            tvaProperties.put("taux", (Double.parseDouble(request.getParameter("taux"))));
            	            champConfig.put("valeur", Double.parseDouble(request.getParameter("taux")));
            	            // Ajouter cette configuration à la map 'validations'
            	            validations.put((String) champConfig.get("nom_champ"), champConfig);
            	        } catch (NumberFormatException e) {
            	            System.err.println("Erreur de conversion pour le champ " + fieldName + ": " + e.getMessage());
            	        }
            	    } else {
            	        System.out.println("Le champ " + fieldName + " ne correspond pas aux critères de conversion en double.");
            	        tvaProperties.put((String) champConfig.get("nom_champ"), (request.getParameter((String) champConfig.get("nom_champ"))));
            	        champConfig.put("valeur", request.getParameter((String) champConfig.get("nom_champ")));
            	        // Ajouter cette configuration à la map 'validations'
            	        validations.put((String) champConfig.get("nom_champ"), champConfig);

            	    }
            	} else {
            	    System.out.println("Le champ " + fieldName + " n'est pas de type 'number' et n'a pas besoin d'être converti.");
            	    tvaProperties.put((String) champConfig.get("nom_champ"), (request.getParameter((String) champConfig.get("nom_champ"))));
        	        champConfig.put("valeur", request.getParameter((String) champConfig.get("nom_champ")));
        	        // Ajouter cette configuration à la map 'validations'
        	        validations.put((String) champConfig.get("nom_champ"), champConfig);
            	}
            	//tvaProperties.put((String) champConfig.get("nom_champ"), (request.getParameter((String) champConfig.get("nom_champ"))));
            	System.out.println("nomcham"+ (String) champConfig.get("nom_champ") + "valeurchamp" + (request.getParameter((String) champConfig.get("nom_champ"))) );
            	validateField((String) champConfig.get("nom_champ"), (request.getParameter((String) champConfig.get("nom_champ"))), champConfig, erreurs);
        		// on teste l'existence de la zone nom si on est en copie ou création ou en renommage
        		if (request.getParameter("nom_programme").equals("copie") || request.getParameter("nom_programme").equals("ajout") || request.getParameter("nom_programme").equals("renommage")) {
        			Boolean existe = false;
        			try {
        				existe = tvaDao.trouverNomTva((String) configNom.get("valeur"));
        			} catch (DaoException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			if ( existe == true ) {
        				erreurs.put("nom", "Le nom existe déjà.");
        			}
        		}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

     // Créer dynamiquement un objet entité exemple Tva sans utiliser son nom directement
     		Object tvaInstance = GenericObjectFactory.createAndInitialize(Tva.class, tvaProperties);
     		Tva tva2 = (Tva) tvaInstance;
     		System.out.println("nomtva"+tva2.getNom());

     // Boucle sur chaque configuration pour ajouter les validations dynamiquement
		/*
		 * try { for (Map<String, Object> champConfig : listeChamps) {
		 * ajouterValidation(validations, tvaInstance, (String)
		 * champConfig.get("nom_champ"), (Boolean) champConfig.get("required"),
		 * (Boolean) champConfig.get("readonly"), (int) champConfig.get("minlenght"),
		 * (int) champConfig.get("maxlenght"), (String) champConfig.get("type"),
		 * (String) champConfig.get("step"), (String) champConfig.get("placeholder")); }
		 * } catch (Exception e) { e.printStackTrace(); }
		 */




		/*
		 * // Ajouter la configuration pour "nom" configNom.put("required", true);
		 * configNom.put("maxLength", 5); configNom.put("valeur",
		 * request.getParameter("nom")); configNom.put("minlength", 2);
		 * configNom.put("maxlength", 20); configNom.put("type", "text");
		 * validations.put("nom", configNom);
		 *
		 * // Ajouter la configuration pour "libelle" configLibelle.put("required",
		 * true); configLibelle.put("minlength", 3); configLibelle.put("maxLength", 10);
		 *
		 * // Appeler la méthode dynamiquement pour "libelle" String libelleValue =
		 * invokeGetter(tvaInstance, "getLibelle"); // Appeler dynamiquement getLibelle
		 * configLibelle.put("valeur", request.getParameter("libelle"));
		 * configLibelle.put("type", "text"); validations.put("libelle", configLibelle);
		 *
		 * // Ajouter la configuration pour "taux" configTaux.put("valeur",
		 * (request.getParameter("taux"))); configTaux.put("required", false);
		 * configTaux.put("minlength", 3); configTaux.put("maxlength", 5);
		 * configTaux.put("type", "number"); configTaux.put("step", "0.01");
		 * configTaux.put("placeholder", "0.00"); validations.put("taux", configTaux);
		 */

		// Validation des champs
        //System.out.println();
		//validateField("nom", (String) configNom.get("valeur"), configNom, erreurs);
		// on teste l'existence de la zone nom si on est en copie ou création ou en renommage
		/*
		 * if (request.getParameter("nom_programme").equals("copie") ||
		 * request.getParameter("nom_programme").equals("ajout") ||
		 * request.getParameter("nom_programme").equals("renommage")) { Boolean existe =
		 * false; try { existe = tvaDao.trouverNomTva((String) configNom.get("valeur"));
		 * } catch (DaoException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } if ( existe == true ) { erreurs.put("nom",
		 * "Le nom existe déjà."); } }
		 *
		 * validateField("libelle", (String) configLibelle.get("valeur"), configLibelle,
		 * erreurs); validateField("taux", String.valueOf(configTaux.get("valeur")),
		 * configTaux, erreurs);
		 */

		// Passer la map 'validations' à la JSP
		request.setAttribute("validations", validations);
		System.out.println("tva"+tva2.getNom()+tva2.getLibelle()+tva2.getTaux());
		return tvaInstance;
	}

	// Méthode pour appeler dynamiquement une méthode getter sur un objet
	private String invokeGetter(Object object, String methodName) {
		try {
			Method method = object.getClass().getMethod(methodName);
			return (String) method.invoke(object);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Fonction générique de validation des champs
	private void validateField(String fieldName, String fieldValue, Map<String, Object> config, Map<String, String> erreurs) {
		// Vérification si le champ est requis
		if ((boolean) config.get("required") && (fieldValue == null || fieldValue.trim().isEmpty())) {
			erreurs.put(fieldName, "Le champ " + fieldName + " est requis.");
			return;
		}

		// Vérification de la longueur minimale
		if (config.get("minlength") != null) {
			System.out.println("verif minimal"+ fieldName);
			int minLength = (int) config.get("minlength");
			if (fieldValue != null && fieldValue.length() < minLength) {
				System.out.println("erreur");
				erreurs.put(fieldName, "Le champ " + fieldName + " doit avoir au moins " + minLength + " caractères.");
				configNom.put("type", "text");
			}
		}

		// Vérification de la longueur maximale
		if (config.get("maxlength") != null) {
			int maxLength = (int) config.get("maxlength");
			if (fieldValue != null && fieldValue.length() > maxLength) {
				erreurs.put(fieldName, "Le champ " + fieldName + " doit avoir au plus " + maxLength + " caractères.");
			}
		}

		// Vérification si le champ est un nombre pour les zones numériques (ex: taux)
		if ("number".equals(config.get("type")) && fieldValue != null && !fieldValue.trim().isEmpty()) {
			if (!isNumeric(fieldValue)) {
				erreurs.put(fieldName, "Le champ " + fieldName + " doit être un nombre valide.");
			} else {
				// Si on a défini un nombre de décimales
				String step = (String) config.get("step");
				if (step != null && !isValidDecimal(fieldValue, step)) {
					erreurs.put(fieldName, "Le champ " + fieldName + " doit avoir " + step.split("\\.")[1].length() + " décimales.");
				}
			}}
	}


	// Vérifie si un champ est numérique
	private boolean isNumeric(String strNum) {
		try {
			Double.parseDouble(strNum);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	// Vérifie le nombre de décimales
	private boolean isValidDecimal(String strNum, String step) {
		int decimalPlaces = step.split("\\.")[1].length(); // Nombre de décimales attendues
		String decimalPattern = "^-?\\d+(\\.\\d{1," + decimalPlaces + "})?$";
		return Pattern.matches(decimalPattern, strNum);
	}


	// Méthode générique pour ajouter une configuration dynamique de validation
    private static void ajouterValidation(Map<String, Map<String, Object>> validations, Object entite, String fieldName,
                                          boolean required, boolean disabled, int minlength, int maxlength, String type, String step, String placeholder) throws Exception {
        Map<String, Object> config = new HashMap<>();
        config.put("required", required);
        config.put("disabled", disabled);
        config.put("minlength", minlength);
        config.put("maxlength", maxlength);
        config.put("type", type);
        if (step != null) config.put("step", step);
        if (placeholder != null) config.put("placeholder", placeholder);

        // Génération du nom de la méthode getter (par exemple, getNom pour 'nom')
        String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

        // Utilisation de la réflexion pour appeler dynamiquement la méthode getter
        Class<?> clazz = entite.getClass();
        Method method = clazz.getMethod(methodName);

        // Récupérer la valeur du champ
        Object valeur = method.invoke(entite);

        // Ajouter la valeur au dictionnaire
        config.put("valeur", valeur);

        // Ajouter cette configuration à la map 'validations'
        validations.put(fieldName, config);
    }


}

