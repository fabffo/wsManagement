/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM CONTRATCLIENT CREATIO                              ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.contrat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.ContratDao;
import com.ws.beans.Contrat;
import com.ws.beans.Tva;

import jakarta.servlet.http.HttpServletRequest;

public final class ControleDonneesContratForm {
	private ContratDao collaborateurDao;
	private static final String CHAMP_STATUT = "statut";
	private static final String CHAMP_DOCUMENT = "document";
	private static final String CHAMP_NOM_CONTRAT = "nom_contrat";
	private static final String CHAMP_TYPE_CONTRAT = "type_contrat";
	private static final String CHAMP_ID_REFERENT_COLLABORATEUR = "collaborateurSelectid";
	private static final String CHAMP_COLLABORATEUR = "collaborateur";
	private static final String CHAMP_ID_CLIENT = "clientSelectid";
	private static final String CHAMP_CLIENT = "client";
	private static final String CHAMP_DATE_DEMARRAGE = "date_demarrage";
	private static final String CHAMP_COMMENTAIRE = "commentaire";
	private static String chemin_relatif_document_reel;
	private static String chemin_absolu_document_reel;
	private String valeurStatut;
	private String valeurDocument;
	private String valeurNom_contrat;
	private String valeurType_contrat;
	private String[] valeursType_contrat;
	private String[] valeurIdETclient;
	private int valeurId_client;
	private String valeurClient;
	private String[] valeurIdETcollaborateur;
	private int valeurId_referent_collaborateur;
	private String valeurCollaborateur;
	private String valeurDate_demarrage;
	private String valeurCommentaire;
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private int id = 0;
	private int version = 0;
	private String type_entite;

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Contrat ControlerDonneesContrat(HttpServletRequest request) {

		DaoFactory daoFactory = DaoFactory.getInstance();
		this.collaborateurDao = daoFactory.getContratDao();

		Map<String, Map<String, String>> contracts = (Map<String, Map<String, String>>) request.getSession()
				.getAttribute("contracts");
		// --------- type entité

		type_entite = (String) request.getSession().getAttribute("type_entite");
//=============================================================================================================//
// récupération des paramétres
// =============================================================================================================//
// =====STATUT
		String statut = getValeurChamp(request, CHAMP_STATUT);
		try {
			valeurStatut = validationStatut(statut);
		} catch (Exception e) {
			setErreur(CHAMP_STATUT, e.getMessage());
		}
// ====== DOCUMENT
		String document = getValeurChamp(request, CHAMP_DOCUMENT);
		try {
			valeurDocument = validationDocument(document);
		} catch (Exception e) {
			setErreur(CHAMP_DOCUMENT, e.getMessage());
		}
// ====== TYPE CONTRAT
		String type_contrat = getValeurChamp(request, CHAMP_TYPE_CONTRAT);
		try {
			valeursType_contrat = validationType_contrat(type_contrat, contracts);
			valeurType_contrat = valeursType_contrat[0];
			request.getSession().setAttribute("chemin_relatif_document_reel", chemin_relatif_document_reel);
			request.getSession().setAttribute("chemin_absolu_document_reel", chemin_absolu_document_reel);

		} catch (Exception e) {
			setErreur(CHAMP_TYPE_CONTRAT, e.getMessage());
		}
// ====== NOM CONTRAT
		String nom_contrat = getValeurChamp(request, CHAMP_NOM_CONTRAT);
		try {
			valeurNom_contrat = validationNom_contrat(nom_contrat);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			setErreur(CHAMP_NOM_CONTRAT, e.getMessage());
		}

		// ======ID ET COLLABORATEUR
		try {
			valeurIdETcollaborateur = validationIdEtCollaborateur(request.getParameter("collaborateurInfo"));
			valeurId_referent_collaborateur = Integer.parseInt(valeurIdETcollaborateur[0]);
			valeurCollaborateur = valeurIdETcollaborateur[1];
		} catch (Exception e) {
			setErreur(CHAMP_COLLABORATEUR, "Choisir un collaborateur");
		}

// ======ID ET CLIENT
		try {
			valeurIdETclient = validationIdEtClient(request.getParameter("clientInfo"));
			valeurId_client = Integer.parseInt(valeurIdETclient[0]);
			valeurClient = valeurIdETclient[1];
		} catch (Exception e) {
			setErreur(CHAMP_CLIENT, "Choisir un client");
		}

// ====== DATE DEMARRAGE
		String date_demarrage = getValeurChamp(request, CHAMP_DATE_DEMARRAGE);
		try {
			valeurDate_demarrage = validationDate_demarrage(date_demarrage);
		} catch (Exception e) {
			setErreur(CHAMP_DATE_DEMARRAGE, e.getMessage());
		}

		// ====== COMMENTAIRE
		String commentaire = getValeurChamp(request, CHAMP_COMMENTAIRE);
		try {
			valeurCommentaire = validationCommentaire(commentaire);
		} catch (Exception e) {
			setErreur(CHAMP_COMMENTAIRE, e.getMessage());
		}

		// =======INSTANCIATION DE LA CLASSE CONTRATCLIENT
		// on initialise les ids quand on vient de la maj
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
		}
		try {
			version = Integer.parseInt(request.getParameter("version"));
		} catch (NumberFormatException e) {
		}
		// On alimente les zones
		Contrat contrat = new Contrat();
		contrat.setId(id);
		contrat.setVersion(version);
		contrat.setStatut(valeurStatut);
		contrat.setDocument(valeurDocument);
		contrat.setNom_contrat(valeurNom_contrat);
		contrat.setType_contrat(valeurType_contrat);
		contrat.setId_referent_collaborateur(valeurId_referent_collaborateur);
		contrat.setCollaborateur(valeurCollaborateur);
		contrat.setId_client(valeurId_client);
		contrat.setClient(valeurClient);
		contrat.setCheminAbsolu(chemin_absolu_document_reel);
		contrat.setCheminRelatif(chemin_relatif_document_reel);
		try {
			contrat.setDate_demarrage(request.getParameter(CHAMP_DATE_DEMARRAGE));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contrat.setCommentaire(commentaire);

		if (erreurs.isEmpty()) {
			resultat = "Succès de la création de la Contrat.";
		} else {
			resultat = "Échec de la création de la Contrat.";
			for (Map.Entry<String, String> entree : erreurs.entrySet()) {
			}
		}
		return contrat;
	}

	// =============================================================================================================//
	// Procédures de contrôle
	// =============================================================================================================//
	// =====STATUT
	private String validationStatut(String statut) throws Exception {
		String temp = statut;
		if (temp != null) {
		} else {
			throw new Exception("Choisir un statut.");
		}
		return temp;
	}

	// =====DOCUMENT
	private String validationDocument(String document) throws Exception {
		String temp = document;
		if (temp != null) {
		} else {
			// throw new Exception("Taper un nom de document.");
		}
		return temp;
	}

	// =====NOM CONTRAT
	private String validationNom_contrat(String nom_contrat) throws Exception {
		String temp = nom_contrat;
		if (temp != null) {
		} else {
			throw new Exception("Taper un nom de contrat.");
		}
		return temp;
	}

	// =====TYPE CONTRAT
	private String[] validationType_contrat(String type_contrat, Map<String, Map<String, String>> contracts)
			throws Exception {
		String temp = type_entite +"_" + type_contrat;
		String[] valeurs = new String[3];
		System.out.println("valeur entite" + temp);
		// Parcourir la structure de données
		for (Map.Entry<String, Map<String, String>> contractEntry : contracts.entrySet()) {
			String contractKey = contractEntry.getKey();
			Map<String, String> contractValues = contractEntry.getValue();

			for (Map.Entry<String, String> valueEntry : contractValues.entrySet()) {
				String innerKey = valueEntry.getKey();
				String innerValue = valueEntry.getValue();
			}
		}
		System.out.println("testcontrat");
		if (temp != null) {

			if (contracts != null && contracts.containsKey(temp)) {
				Map<String, String> contractPaths = contracts.get(temp);
				chemin_relatif_document_reel = contractPaths.get("cheminRelatif");
				chemin_absolu_document_reel = contractPaths.get("cheminAbsolu");

			} else {
				throw new Exception("le paramétrage type document "+ temp + "n'existe pas.");
			}
		} else {
			throw new Exception("erreur type de contrat.");
		}
		valeurs[0] = type_contrat;
		valeurs[1] = chemin_relatif_document_reel;
		valeurs[2] = chemin_absolu_document_reel;
		return valeurs;
	}

	// =====ID REFERENT ET COLLABORATEUR
	// ===== --> L'id et le collaborateur sont dans un même string séparé par "_"
	private String[] validationIdEtCollaborateur(String idEtCollaborateur) throws Exception {
		String temp = idEtCollaborateur;
		String[] parts;
		if (temp != null) {
			String collaborateurInfo = temp;
			parts = collaborateurInfo.split("_");
			// Vérifier que la chaîne a bien été splittée en deux parties
			if (parts.length == 2) {
				try {
					// Convertir la première partie en entier
					int id = Integer.parseInt(parts[0]);

					// La deuxième partie reste une chaîne
					String name = parts[1];
				} catch (NumberFormatException e) {
					throw new Exception("Choisir un collaborateur.");
				}
			}
		} else {
			throw new Exception("Choisir un collaborateur.");
		}
		return parts;
	}

	// =====ID ET CLIENT --> L'id et le client sont dans un même string séparé par
	// "_"
	private String[] validationIdEtClient(String idEtClient) throws Exception {
		String temp = idEtClient;
		String[] parts;
		if (temp != null) {
			String clientInfo = temp;
			parts = clientInfo.split("_");
			// Vérifier que la chaîne a bien été splittée en deux parties
			if (parts.length == 2) {
				try {
					// Convertir la première partie en entier
					int id = Integer.parseInt(parts[0]);

					// La deuxième partie reste une chaîne
					String name = parts[1];
				} catch (NumberFormatException e) {
					throw new Exception("Choisir un client.");
				}
			}
		} else {
			throw new Exception("Choisir un client.");
		}
		return parts;
	}

// =====DATE DEMARRAGE
	private String validationDate_demarrage(String date_demarrage) throws Exception {
		String selectedDate = date_demarrage;
		// Parse the selected date and transform it to aaaa-mm-jj
		if (selectedDate != null && !selectedDate.isEmpty()) {
			String[] dateParts = selectedDate.split("-");
			if (dateParts.length == 3) {
				String day = dateParts[0];
				String month = dateParts[1];
				String year = dateParts[2];
				selectedDate = year + "-" + month + "-" + day;
			} else
				throw new Exception("La date doit être au format dd/mm/yyyy");
		}
		return selectedDate;
	}

	// =====COMMENTAIRE
	private String validationCommentaire(String commentaire) throws Exception {
		String temp = commentaire;
		if (temp != null) {
		}
		return temp;
	}

	// Ajoute un message correspondant au champ spécifié à la map des erreurs.
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	// Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	private static String getValeurChamp(HttpServletRequest request, String id_avenantChamp) {
		String valeur = request.getParameter(id_avenantChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}

	// ===========================================================================
	// ============ AFFICHER DATE
	// ===========================================================================
	private String affichateDate_JJMMAAAA(String date_JJMMAAAA) throws Exception {
		String selectedDate = date_JJMMAAAA;
		// Parse the selected date and transform it to aaaa-mm-jj
		if (selectedDate != null && !selectedDate.isEmpty()) {
			String[] dateParts = selectedDate.split("-");
			if (dateParts.length == 3) {
				String year = dateParts[0];
				String month = dateParts[1];
				String day = dateParts[2];
				selectedDate = day + "-" + month + "-" + year;
			}
		}
		return selectedDate;
	}

}
