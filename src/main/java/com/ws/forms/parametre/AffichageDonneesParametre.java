/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM TVA CREATION                                       ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.parametre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mysql.cj.result.Field;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.TvaDao;
import com.ws.beans.Tva;

import jakarta.servlet.http.HttpServletRequest;

public final class AffichageDonneesParametre {
	private TvaDao tvaDao;
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_LIBELLE = "libelle";
	private static final String CHAMP_TAUX = "taux";
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	String valeurNom = "";
	String valeurLibelle = "";
	String valeurTaux = "";
	int id;
	float taux = 0;
	Map<String, Map<String, Object>> fields;

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

	public Tva AfficherDonneesParametre(HttpServletRequest request) {
		System.out.println("PARAMETREEEESSS");
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.tvaDao = daoFactory.getTvaDao();

		// Récupération id
		id = Integer.parseInt(request.getParameter("id"));

		// récupération des paramétres nom
		String nom = getValeurChamp(request, CHAMP_NOM);
		try {
			valeurNom = validationNom(nom);
		} catch (Exception e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}

		// récupération et contrôle libellé
		String libelle = getValeurChamp(request, CHAMP_LIBELLE);
		try {
			valeurLibelle = validationLibelle(libelle);
		} catch (Exception e) {
			setErreur(CHAMP_LIBELLE, e.getMessage());
		}

		// récupération et contrôle taux
		String tauxString = getValeurChamp(request, CHAMP_TAUX);
		try {
			taux = validationTaux(tauxString);
		} catch (Exception e) {
			setErreur(CHAMP_TAUX, e.getMessage());
		}

		// Instanciation de la classe tva
		Tva tva = new Tva();
		((Tva) tva).setId(id);
		((Tva) tva).setNom(nom);
		((Tva) tva).setLibelle(libelle);
		((Tva) tva).setTaux(taux);

		// Définir les configurations des champs
	    fields = new HashMap<>();

	    // Configuration dynamique des champs
	    Map<String, Object> field = new HashMap<>();
	    field.put("label", "nom");
	    field.put("valeur", tva.getNom());
	    field.put("valeur_classe", tva.getNom());
	    field.put("required", true);
	    field.put("minlength", 2);
	    field.put("maxlength", 20);
	    field.put("type", "text");
	    fields.put("field1", field);


	    field = new HashMap<>();
	    field.put("label", "libelle");
	    field.put("valeur", tva.getLibelle());
	    field.put("valeur_classe", tva.getLibelle());
	    field.put("required", true);
	    field.put("minlength", 2);
	    field.put("maxlength", 20);
	    field.put("type", "text");
	    fields.put("field2", field);


	    field = new HashMap<>();
	    field.put("label", "Taux");
	    field.put("valeur", 0 );
	    field.put("valeur_classe", tva.getTaux());
	    field.put("required", false);
	    field.put("minlength", 3);
	    field.put("maxlength", 5);
	    field.put("type", "number");
	    field.put("step", "0.01");
	    field.put("placeholder", "0.00");
	    fields.put("field3", field);
	    System.out.println("champs remplis");
		return tva;
	}



	// Contrôle de la zone nom en création copie
	private String validationNom(String nom) throws Exception {
		String temp = nom;
		if (temp != null) {
			if (temp.length() > 5) {
				throw new Exception("Le nom ne doit pas dépasser 5 caractères.");
			}
		} else {
			throw new Exception("le nom est obligatoire.");
		}
		return temp;
	}

	// Contrôle de la zone libellé
	private String validationLibelle(String libelle) throws Exception {
		String temp = libelle;
		if (temp != null) {
		} else {
			throw new Exception("le libellé est obligatoire.");
		}
		return temp;
	}

	// Contrôle taux
	private float validationTaux(String taux) throws Exception {
		String temp = taux;
		boolean b = true;
		Float f = (float) 0;
		if (temp != null) {
			try {
				f = Float.parseFloat(temp);
			} catch (NumberFormatException e) {
				b = false;
			}
			if (b == false)
				throw new Exception("Le taux doit être un nombre décimal");
		} else {
			throw new Exception("le taux est obligatoire.");
		}
		return f;
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}
}

