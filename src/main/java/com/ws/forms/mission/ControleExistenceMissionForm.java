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
import com.ws.beans.Mission;
import com.ws.beans.Tva;

import jakarta.servlet.http.HttpServletRequest;

public final class ControleExistenceMissionForm {
	private MissionDao missionDao;
	private static final String CHAMP_CONTRAT_INFO = "contratInfo";
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_COMPLEMENT = "complement";
	private static final String CHAMP_STATUT = "statut";
	private static final String CHAMP_ID_CONTRAT = "id_contrat";
	private static final String CHAMP_VERSION_CONTRAT = "version_contrat";
	private static final String CHAMP_CODE_TVA = "code_tva";
	private static final String CHAMP_PRIX_HT = "prix_ht";
	private static final String CHAMP_TYPE_PRIX = "type_prix";
	private static final String CHAMP_NBR_FACTURE = "nbr_facture";
	private static final String CHAMP_SOCIETE = "societe";
	private static final String CHAMP_TYPE_ENTITE = "type_entite";
	private static final String CHAMP_TYPE_CONTRAT = "type_contrat";
	private static final String CHAMP_DATE_DEMARRAGE = "date_demarrage";
	private static final String CHAMP_COMMENTAIRE = "commentaire";

	private String valeurNom;
	private String valeurComplement;
	private String valeurStatut;
	private int valeurId_contrat;
	private int valeurVersion_contrat;
	private String valeurcode_tva;
	private float valeurPrix_ht;
	private String valeurType_prix;
	private int valeurNbr_facture;
	private String[] valeurIdEtContrat;
	private String valeurDate_demarrage;
	private String valeurCommentaire;

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Mission ControlerExistenceMission(HttpServletRequest request) {

		DaoFactory daoFactory = DaoFactory.getInstance();
		this.missionDao = daoFactory.getMissionDao();

		Map<String, Map<String, String>> contracts = (Map<String, Map<String, String>>) request.getSession()
				.getAttribute("contracts");

		//=============================================================================================================//
		// récupération des paramétres
		// =============================================================================================================//
		// ======ID ET VERSION CONTRAT
				try {
					valeurIdEtContrat = validationIdEtVersionContrat(request.getParameter("contratInfo"));
					valeurId_contrat = Integer.parseInt(valeurIdEtContrat[0]);
					valeurId_contrat = Integer.parseInt(valeurIdEtContrat[1]);
				} catch (Exception e) {
					setErreur(CHAMP_ID_CONTRAT, "erreur contrat");
				}
				valeurComplement = getValeurChamp(request, CHAMP_COMPLEMENT); 		  // complément
				valeurStatut = getValeurChamp(request, CHAMP_STATUT);		  		  // statut
				valeurcode_tva = getValeurChamp(request, CHAMP_CODE_TVA);	          // tva
				valeurPrix_ht = getValeurFloat(request, CHAMP_PRIX_HT);		 		  // prix ht
				valeurType_prix = getValeurChamp(request, CHAMP_TYPE_PRIX);			  // type prix
				valeurNbr_facture = getValeurInt(request, CHAMP_NBR_FACTURE); 		  // nbr factures
				valeurDate_demarrage = getValeurChamp(request, CHAMP_DATE_DEMARRAGE); // Date demarrage
				valeurCommentaire = getValeurChamp(request, CHAMP_COMMENTAIRE); 	  // Commentaire
		// =============================================================================================================//
		// CONTROLE NOM MISSION
		// =============================================================================================================//
		String nom_mission = getValeurChamp(request, CHAMP_NOM);
		try {
			valeurNom = validationNom(nom_mission);
		} catch (Exception e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}



		// Instanciation de la classe mission
		Mission mission = new Mission();
		mission.setNom(valeurNom);
		mission.setComplement(valeurComplement);
		mission.setStatut(valeurStatut);
		mission.setId_contrat(valeurId_contrat);
		mission.setVersion_contrat(valeurVersion_contrat);
		mission.setCode_tva(valeurcode_tva);
		mission.setPrix_ht(valeurPrix_ht);
		mission.setNbr_facture(valeurNbr_facture);
		mission.setType_prix(valeurType_prix);
		mission.setDate_demarrage(valeurDate_demarrage);
		mission.setCommentaire(valeurCommentaire);

		if (erreurs.isEmpty()) {
			resultat = "Succès de la création de la Mission.";
		} else {
			resultat = "Échec de la création de la Mission.";
			for (Map.Entry<String, String> entree : erreurs.entrySet()) {
			}
		}
		return mission;
	}

	// =============================================================================================================//
	// Procédures de contrôle
	// =============================================================================================================//
	// =====ID ET VERSION CONTRAT
	// ===== --> L'id et le collaborateur sont dans un même string séparé par "_"
	private String[] validationIdEtVersionContrat(String idEtVersion) throws Exception {
		String temp = idEtVersion;
		String[] parts;
		if (temp != null) {
			String contratInfo = temp;
			parts = contratInfo.split("_");
			// Vérifier que la chaîne a bien été splittée en deux parties
			if (parts.length > 2) {
				try {
					// Convertir la première partie en entier
					int id = Integer.parseInt(parts[0]);

					// La deuxième partie reste un int
					int version = Integer.parseInt(parts[1]);
				} catch (NumberFormatException e) {
					throw new Exception("erreur contrat");
				}
			}
		} else {
			throw new Exception("erreur contrat");
		}
		return parts;
	}

	// Contrôle existence du nom
	private String validationNom(String nom) throws Exception {
		String temp = nom;
		Boolean existe = missionDao.trouverNomMission(nom);
		if (existe == true) {
			throw new Exception("Le nom existe déjà.");
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
	 //Méthode utilitaire qui retourne un int
    private static int getValeurInt( HttpServletRequest request, String nomInt ) {
        int valeur = Integer.parseInt(request.getParameter( nomInt ));
            return valeur;
    }
	 //Méthode utilitaire qui retourne un float
    private static Float getValeurFloat( HttpServletRequest request, String nomFloat ) {
        Float valeur = Float.parseFloat(request.getParameter( nomFloat ));
            return valeur;
    }

}
