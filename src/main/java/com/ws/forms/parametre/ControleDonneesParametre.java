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
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.ParametreDao;
import com.ws.Dao.ParametreEcranDao;
import com.ws.beans.GenericObjectFactoryParNomClasse;
import com.ws.beans.ParametreSysteme;
import com.ws.beans.Parametre_ecranCrud_entete;

import jakarta.servlet.http.HttpServletRequest;

public final class ControleDonneesParametre {
	private Object daoInstance;  // Instance dynamique du DAO
	private String daoInterfaceName;  // Nom de l'interface DAO
	private String parametre_nom;
	private String comWsBeansEntite;
	private String trouverNomEntite;
	private ParametreDao parametreDao; // DAO pour les paramètres
	private ParametreEcranDao parametreEcranDao; // DAO pour les paramètres
	ParametreSysteme parametreSysteme;
	Parametre_ecranCrud_entete parametre_ecranCrud_entete;
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private String CleNom;
	String valeurNom = "";
	String valeurLibelle = "";
	String valeurTaux = "";
	int entite_id;
	float taux = 0;
	Map<String, Map<String, Object>> fields;
	Map<String, Object> entiteProperties = new HashMap<>();
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	public Map<String, Map<String, Object>> getFields() {
		return fields;
	}

	public String getResultat() {
		return resultat;
	}

	public Object ControlerDonneesParametre(HttpServletRequest request) throws DaoException {
		// Récupération du paramètre de l'entité (exemple Tva)
					if (request.getParameter("parametre_nom") != null) {
						parametre_nom = request.getParameter("parametre_nom");
						request.getSession().setAttribute("parametre_nom", parametre_nom);
					} else {
						if (request.getSession().getAttribute("parametre_nom") != null) {
							parametre_nom = (String) request.getSession().getAttribute("parametre_nom");
						}else {
							parametre_nom = "Tva"; //valeur par défaut
						}
					}

			DaoFactory daoFactory = DaoFactory.getInstance();
			daoFactory = DaoFactory.getInstance();
	        this.parametreDao = daoFactory.getParametreDao();  // Récupération du DAO pour la gestion des paramètres
	        this.parametreEcranDao = daoFactory.getParametreEcranDao();  // Récupération du DAO pour la gestion des paramètres
	     // Récupération des détails des paramètres pour configurer l'interface
    		parametreSysteme = parametreEcranDao.lireParametreSysteme(parametre_nom);
    		//parametre_ecranCrud_entete = parametreEcranDao.lireParametre_ecranCrud_entete(parametreSysteme.getId(), parametre_nom_programme);




			//Récupération du nom du bean, des méthodes à utiliser
			comWsBeansEntite = "com.ws.beans."+ parametre_nom;
			trouverNomEntite = "trouverNom"+ parametre_nom;
			daoInterfaceName = parametre_nom + "Dao";

			// Initialisation dynamique du DAO correspondant
	            try {
					daoInstance = daoFactory.getClass().getMethod("get" + daoInterfaceName).invoke(daoFactory);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


		// Récupération entite_id
		entite_id = Integer.parseInt(request.getParameter("entite_id"));

		// Préparer la Map des propriétés pour initialiser un objet Tva
		entiteProperties.put("id", (int) entite_id);
		System.out.println("debut map"+ entite_id);

 		Map<String, Map<String, Object>> validations = new HashMap<>();
            // Charger la configuration des champs pour l'entité et le programme donnés
            try {
            	//validations = parametreDao.getMapParametresChampsEcran(request.getParameter("parametre_nom_programme"), parametre_nom);
    			validations = parametreEcranDao.getParametre_ecranCrud_ligne(parametreSysteme.getId(), request.getParameter("parametre_nom_programme"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            for (Map.Entry<String, Object> entry : entiteProperties.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                System.out.println("avant"+key+value);
            }

            // Mettre à jour chaque champ avec la valeur provenant de la requête
            for (Map.Entry<String, Map<String, Object>> entry : validations.entrySet()) {
                String nomChamp = entry.getKey();
                String valeurChamp = request.getParameter(nomChamp);
                if ("nom".equals(nomChamp))
                	CleNom = valeurChamp;// pour tester l'existence du nom
                Map<String, Object> champConfig = entry.getValue();

                // Vérifier si le champ est de type number et contient un step, puis parser en Double
                if ("number".equals(champConfig.get("type")) && champConfig.get("step") != null) {
                    try {
                    	  entiteProperties.put(nomChamp, valeurChamp != null ? Double.parseDouble(valeurChamp) : null);// renseigner l'entite classe
                    	champConfig.put("valeur", valeurChamp != null ? Double.parseDouble(valeurChamp) : null);
                    } catch (NumberFormatException e) {
                    	  entiteProperties.put(nomChamp, null);// renseigner l'entite classe
                    	champConfig.put("valeur", null);
                    }
                } else {
                	  entiteProperties.put(nomChamp, (request.getParameter(nomChamp)));// renseigner l'entite classe
                    champConfig.put("valeur", valeurChamp); // Mettre à jour la valeur pour les autres types
                }

                //ajout des contrôles
             // Vérification si le champ est requis
        		if ((boolean) champConfig.get("required") && (valeurChamp == null || valeurChamp.trim().isEmpty())) {
        			erreurs.put(nomChamp, "Le champ " + nomChamp + " est requis.");
        		}

        		// Vérification de la longueur minimale
        		if (champConfig.get("minlength") != null) {
        			int minLength = (int) champConfig.get("minlength");
        			if (valeurChamp != null && valeurChamp.length() < minLength) {
        				erreurs.put(nomChamp, "Le champ " + nomChamp + " doit avoir au moins " + minLength + " caractères.");
        			}
        		}

        		// Vérification de la longueur maximale
        		if (champConfig.get("maxlength") != null) {
        			int maxLength = (int) champConfig.get("maxlength");
        			if (valeurChamp != null && valeurChamp.length() > maxLength) {
        				erreurs.put(nomChamp, "Le champ " + nomChamp + " doit avoir au plus " + maxLength + " caractères.");
        			}
        		}

        		// Vérification si le champ est un nombre pour les zones numériques (ex: taux)
        		if ("number".equals(champConfig.get("type")) && valeurChamp != null && !valeurChamp.trim().isEmpty()) {
        			if (!isNumeric(valeurChamp)) {
        				erreurs.put(nomChamp, "Le champ " + nomChamp + " doit être un nombre valide.");
        			} else {
        				// Si on a défini un nombre de décimales
        				String step = (String) champConfig.get("step");
        				if (step != null && !isValidDecimal(valeurChamp, step)) {
        					erreurs.put(nomChamp, "Le champ " + nomChamp + " doit avoir " + step.split("\\.")[1].length() + " décimales.");
        				}
        			}
        		}

            }

            for (Map.Entry<String, Object> entry : entiteProperties.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                System.out.println("après"+key+value);
            }

   		 // Créer dynamiquement un objet entité exemple Tva sans utiliser son nom directement
    		//Object entiteInstance = GenericObjectFactory.createAndInitialize(Tva.class, entiteProperties);
            Object entiteInstance = null;
			try {
				// Créer dynamiquement un objet entité exemple Tva sans utiliser son nom directement
				entiteInstance = GenericObjectFactoryParNomClasse.createAndInitialize(comWsBeansEntite, entiteProperties);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            // Passer la map 'validations' en attribut de requête pour la JSP
            request.setAttribute("validations", validations);


		// on teste l'existence de la zone nom si on est en copie ou création ou en renommage
		  if (request.getParameter("parametre_nom_programme").equals("copie") || request.getParameter("parametre_nom_programme").equals("ajout") ||
				  request.getParameter("parametre_nom_programme").equals("renommage"))
		  {
			  Boolean existe = false;

				  //existe = tvaDao.trouverNomTva((String) CleNom);
				  try {
					existe = (Boolean) invokeDynamicMethod(trouverNomEntite, CleNom);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			  if ( existe == true )
			  { erreurs.put("nom", "Le nom existe déjà.");
			  }
		  }

		return entiteInstance;
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

	// Méthode pour invoquer dynamiquement des méthodes du DAO
    private Object invokeDynamicMethod(String methodName, Object... params) throws Exception {
        Class<?>[] paramTypes = new Class<?>[params.length];
        for (int i = 0; i < params.length; i++) {
            paramTypes[i] = params[i].getClass();
        }
        Method method = daoInstance.getClass().getMethod(methodName, paramTypes);
        return method.invoke(daoInstance, params);
    }

}

