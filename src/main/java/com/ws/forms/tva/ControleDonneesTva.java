/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM TVA CREATION                                       ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.tva;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.TvaDao;
import com.ws.beans.Tva;

import jakarta.servlet.http.HttpServletRequest;

public final class ControleDonneesTva {
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

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Tva ControlerDonneesTva(HttpServletRequest request) {
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
		tva.setId(id);
		tva.setNom(nom);
		tva.setLibelle(libelle);
		tva.setTaux(taux);

		if (erreurs.isEmpty()) {
			resultat = "Succès de la copie de la Tva.";
		} else {
			resultat = "Échec de la copie de la Tva.";
		}
		System.out.println(resultat + libelle);
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
