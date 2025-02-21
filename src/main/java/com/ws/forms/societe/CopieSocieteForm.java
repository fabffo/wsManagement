/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM SOCIETE  COPIE                                     ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.societe;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.SocieteDao;
import com.ws.beans.Societe;

import jakarta.servlet.http.HttpServletRequest;

public final class CopieSocieteForm {
	private SocieteDao societeDao;
	private static final String CHAMP_ID = "id";
	private static final String CHAMP_RAISON_SOCIALE = "raison_sociale";
	private static final String CHAMP_TYPE = "type";
	private static final String CHAMP_TVA = "tva";
	private static final String CHAMP_NAF = "naf";
	private static final String CHAMP_METIER = "metier";
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	String valeurRaison_sociale = "";
	String valeurType = "";
	String valeurMetier = "";
	int id;

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Societe copierSociete(HttpServletRequest request) {

		DaoFactory daoFactory = DaoFactory.getInstance();
		this.societeDao = daoFactory.getSocieteDao();

		String raison_sociale = getValeurChamp(request, CHAMP_RAISON_SOCIALE);
		String type = getValeurChamp(request, CHAMP_TYPE);
		String adresse = request.getParameter("adresse");
		String code_postal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		String pays = request.getParameter("pays");
		String code_naf = request.getParameter("code_naf");
		String code_tva = request.getParameter("code_tva");
		String siren = request.getParameter("siren");
		String siret = request.getParameter("siret");
		String metier = request.getParameter("metier");
		Societe societe = new Societe();

		try {
			valeurRaison_sociale = validationRaison_sociale(raison_sociale);
		} catch (Exception e) {
			setErreur(CHAMP_RAISON_SOCIALE, e.getMessage());
		}

		try {
			valeurType = validationType(type);
		} catch (Exception e) {
			setErreur(CHAMP_TYPE, e.getMessage());
		}

		

		
		societe.setRaison_sociale(raison_sociale);
		societe.setType(type);
		societe.setAdresse(adresse);
		societe.setCode_postal(code_postal);
		societe.setVille(ville);
		societe.setPays(pays);
		societe.setCode_naf(code_naf);
		societe.setCode_tva(code_tva);
		societe.setSiren(siren);
		societe.setSiret(siret);
		societe.setMetier(metier);
		

		if (erreurs.isEmpty()) {
			resultat = "Succès de la création de la Societe.";
		} else {
			resultat = "Échec de la création de la Societe.";
		}
		return societe;
	}

	private String validationRaison_sociale(String raison_sociale) throws Exception {
		String temp = raison_sociale;
		if (temp != null) {

		} else {
			throw new Exception("Merci d'entrer un raison_sociale.");
		}
		return temp;
	}

	private String validationType(String type) throws Exception {
		String temp = type;
		if (temp != null) {
		} else {
			throw new Exception("Merci d'entrer un type.");
		}
		return temp;
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
	private static String getValeurChamp(HttpServletRequest request, String raison_socialeChamp) {
		String valeur = request.getParameter(raison_socialeChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}
}
