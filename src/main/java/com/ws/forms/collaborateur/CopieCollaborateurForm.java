/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM COLLABORATEUR COPIE                                ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.collaborateur;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.CollaborateurDao;
import com.ws.beans.Collaborateur;

import jakarta.servlet.http.HttpServletRequest;

public final class CopieCollaborateurForm {
	private CollaborateurDao collaborateurDao;
	private static final String CHAMP_ID = "id";
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_SIGNATAIRE = "signataire";
	private static final String CHAMP_METIER = "metier";
	private static final String CHAMP_TYPE_CONTRAT = "type_contrat";
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	String valeurNom = "";
	String valeurPrenom = "";
	int valeurSignataire = 0;
	String valeurMetier = "";
	int id;

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Collaborateur copierCollaborateur(HttpServletRequest request) {

		DaoFactory daoFactory = DaoFactory.getInstance();
		this.collaborateurDao = daoFactory.getCollaborateurDao();

		String nom = getValeurChamp(request, CHAMP_NOM);
		String prenom = getValeurChamp(request, CHAMP_PRENOM);
		String adresse = request.getParameter("adresse");
		String code_postal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		String pays = request.getParameter("pays");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String telephone_secondaire = request.getParameter("telephone2");
		String email_secondaire = request.getParameter("email2");
		String civilite = request.getParameter("civilite");
		String date_naissance = request.getParameter("date_naissance");
		String nationalite = request.getParameter("nationalite");
		String type_contrat = request.getParameter("type_contrat");
		String type_collaborateur = request.getParameter("type_collaborateur");
		String signataire_oui_non = request.getParameter("Signataire_oui_non");
		int signataire_contrat = 0;
		String metier = request.getParameter("metier");

		// int id_utilisateur= Integer.parseInt(request.getParameter("id_utilisateur"));
		String utilisateur = request.getParameter("utilisateur");
		;
		System.out.print("type utilisateur est" + utilisateur);

		Collaborateur collaborateur = new Collaborateur();

		try {
			valeurNom = validationNom(nom);
		} catch (Exception e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}

		try {
			valeurPrenom = validationPrenom(prenom);
		} catch (Exception e) {
			setErreur(CHAMP_PRENOM, e.getMessage());
		}

		try {
			valeurSignataire = validationSignataire_oui_non(signataire_oui_non);
		} catch (Exception e) {
			setErreur(CHAMP_SIGNATAIRE, e.getMessage());
		}

		try {
			valeurMetier = validationMetier(metier);
		} catch (Exception e) {
			setErreur(CHAMP_METIER, e.getMessage());
		}
		collaborateur.setNom(nom);
		collaborateur.setPrenom(prenom);
		collaborateur.setAdresse(adresse);
		collaborateur.setCode_postal(code_postal);
		collaborateur.setVille(ville);
		collaborateur.setPays(pays);
		collaborateur.setTelephone(telephone);
		collaborateur.setEmail(email);
		collaborateur.setTelephone_secondaire(telephone_secondaire);
		collaborateur.setEmail_secondaire(email_secondaire);
		collaborateur.setCivilite(civilite);
		collaborateur.setDate_naissance(date_naissance);
		collaborateur.setNationalite(nationalite);
		collaborateur.setType_contrat(type_contrat);
		collaborateur.setType_collaborateur(type_collaborateur);
		collaborateur.setSignataire_contrat(valeurSignataire);
		collaborateur.setUtilisateur(utilisateur);
		collaborateur.setMetier(metier);

		if (erreurs.isEmpty()) {
			resultat = "Succès de la création de la Collaborateur.";
		} else {
			resultat = "Échec de la création de la Collaborateur.";
		}
		return collaborateur;
	}

	private String validationNom(String nom) throws Exception {
		String temp = nom;
		if (temp != null) {

		} else {
			throw new Exception("Merci d'entrer un nom.");
		}
		return temp;
	}

	private String validationPrenom(String prenom) throws Exception {
		String temp = prenom;
		if (temp != null) {
		} else {
			throw new Exception("Merci d'entrer un prénom.");
		}
		return temp;
	}

	private int validationSignataire_oui_non(String signataire_oui_non) throws Exception {
		String temp = signataire_oui_non;
		System.out.print("peut signer" + signataire_oui_non);
		int signataire = 0;
		if (temp != null) {
			if (temp.equals("OUI")) {
				System.out.print("peut signer" + signataire_oui_non);
				signataire = 1;
			} else {
				signataire = 0;
				System.out.print("ne peut pas signer" + signataire_oui_non);
			}
		} else {
			throw new Exception("Merci d'entrer oui ou non.");
		}
		return signataire;
	}

	private String validationMetier(String metier) throws Exception {
		String temp = metier;
		if (temp != null) {

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
	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}
}
