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
import com.ws.Dao.ContratDaoAncien;
import com.ws.beans.ContratAncien;
import com.ws.beans.Tva;

import jakarta.servlet.http.HttpServletRequest;

public final class ControleExistenceContratForm {
	private ContratDaoAncien contratDao;
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
	private static  String chemin_relatif_document_reel ;
	private static  String chemin_absolu_document_reel  ;
	private String valeurStatut;
	private String valeurDocument;
	private String valeurNom_contrat;
	private String valeurType_contrat;
	private String [] valeursType_contrat;
	private String [] valeurIdETclient;
	private int valeurId_client;
	private String valeurClient;
	private String [] valeurIdETcollaborateur;
	private int valeurId_referent_collaborateur;
	private String valeurCollaborateur;
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

	public ContratAncien ControlerExistenceContrat(HttpServletRequest request) {

		DaoFactory daoFactory = DaoFactory.getInstance();
		this.contratDao = daoFactory.getContratDao();

		Map<String, Map<String, String>> contracts =  (Map<String, Map<String, String>>) request.getSession().getAttribute("contracts");

//=============================================================================================================//
// récupération des paramétres
// =============================================================================================================//
// =====STATUT
	valeurStatut = getValeurChamp(request, CHAMP_STATUT);

// ====== DOCUMENT
	valeurDocument = getValeurChamp(request, CHAMP_DOCUMENT);

// ====== TYPE CONTRAT
			valeurType_contrat = getValeurChamp(request, CHAMP_TYPE_CONTRAT);

// ====== NOM CONTRAT
		String nom_contrat = getValeurChamp(request, CHAMP_NOM_CONTRAT);
		try {
			valeurNom_contrat = validationNom(nom_contrat);
		} catch (Exception e) {
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
			valeurDate_demarrage = getValeurChamp(request, CHAMP_DATE_DEMARRAGE);

// ====== COMMENTAIRE
					valeurCommentaire = getValeurChamp(request, CHAMP_COMMENTAIRE);

		// Instanciation de la classe contrat
		ContratAncien contrat = new ContratAncien();
		contrat.setStatut(valeurStatut);
		contrat.setDocument(valeurDocument);
		contrat.setNom_contrat(valeurNom_contrat);
		contrat.setType_contrat(valeurType_contrat);
		contrat.setId_referent_collaborateur(valeurId_referent_collaborateur);
		contrat.setCollaborateur(valeurCollaborateur);
		contrat.setId_client(valeurId_client);
		contrat.setClient(valeurClient);
		contrat.setDate_demarrage(valeurDate_demarrage);
		contrat.setCommentaire(valeurCommentaire);

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
				}else {
					throw new Exception("Choisir un collaborateur.");
				}
				return parts;
			}

	// =====ID ET CLIENT --> L'id et le client sont dans un même string séparé par "_"
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
			}else {
				throw new Exception("Choisir un client.");
			}
			return parts;
		}

		//Contrôle existence du nom
	    private String validationNom( String nom ) throws Exception {
	        String temp=nom;
	            Boolean existe = contratDao.trouverNomContrat(nom);
	            	if ( existe == true ) {
	                    throw new Exception( "Le nom existe déjà." );
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
}
