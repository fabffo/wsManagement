/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM CONTRATCLIENT CREATIO                              ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.mission;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.MissionDao;
import com.ws.Dao.ContratDao;
import com.ws.beans.Contrat;
import com.ws.beans.Mission;
import com.ws.beans.Tva;

import jakarta.servlet.http.HttpServletRequest;

public final class ControleRenommageMissionForm {
	private MissionDao missionDao;
	private static final String CHAMP_STATUT = "statut";
	private static final String CHAMP_DOCUMENT = "document";
	private static final String CHAMP_NOM_CONTRAT = "nom_mission";
	private static final String CHAMP_TYPE_CONTRAT = "type_mission";
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
	private String valeurNom_mission;
	private String valeurType_mission;
	private String[] valeursType_mission;
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
	private int valeurIdFacture;

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Mission RenommerMission(HttpServletRequest request, Mission mission) {

		DaoFactory daoFactory = DaoFactory.getInstance();
		this.missionDao = daoFactory.getMissionDao();

		Map<String, Map<String, String>> contracts = (Map<String, Map<String, String>>) request.getSession()
				.getAttribute("contracts");

		return mission;
	}

	// =============================================================================================================//
	// Procédures de contrôle
	// =============================================================================================================//
	// =====recherche facture existante
	private int validationIdFacture(int id) throws Exception {
		int temp = id;
		if (temp != 0) {
			if(missionDao.trouverFacture(temp)) {
				throw new Exception("le mission ne peut être annulé car présence de facture(s) ");
			}else
				{}
		} else {
			throw new Exception("Choisir un id.");
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
	private String validationNom_mission(String nom_mission) throws Exception {
		String temp = nom_mission;
		if (temp != null) {
		} else {
			throw new Exception("Taper un nom de mission.");
		}
		return temp;
	}

	// =====TYPE CONTRAT
	private String[] validationType_mission(String type_mission, Map<String, Map<String, String>> contracts)
			throws Exception {
		String temp = "CLIENT_" + type_mission;
		String[] valeurs = new String[3];

		// Parcourir la structure de données
		for (Map.Entry<String, Map<String, String>> contractEntry : contracts.entrySet()) {
			String contractKey = contractEntry.getKey();
			Map<String, String> contractValues = contractEntry.getValue();

			for (Map.Entry<String, String> valueEntry : contractValues.entrySet()) {
				String innerKey = valueEntry.getKey();
				String innerValue = valueEntry.getValue();
			}
		}

		if (temp != null) {

			if (contracts != null && contracts.containsKey(temp)) {
				Map<String, String> contractPaths = contracts.get(temp);
				chemin_relatif_document_reel = contractPaths.get("cheminRelatif");
				chemin_absolu_document_reel = contractPaths.get("cheminAbsolu");

			} else {
				throw new Exception("Choisir un type de mission.");
			}
		} else {
			throw new Exception("Choisir un type de mission.");
		}
		valeurs[0] = type_mission;
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
