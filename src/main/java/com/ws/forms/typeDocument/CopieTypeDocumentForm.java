/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM TYPEDOCUMENT COPIE                                          ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.typeDocument;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypeDocumentDao;
import com.ws.beans.TypeDocument;

import jakarta.servlet.http.HttpServletRequest;

public final class CopieTypeDocumentForm {
	private TypeDocumentDao typeDocumentDao;
	private static final String CHAMP_ID = "id";
	private static final String CHAMP_CHEMINRELATIF = "cheminRelatif";
	private static final String CHAMP_CONTRAT = "contrat";
	private static final String CHAMP_CHEMINABSOLU = "cheminAbsolu";
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	String valeurCheminRelatif = "";
	String valeurContrat = "";
	String valeurCheminAbsolu = "";
	int id;
	String cheminAbsolu = "";

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public TypeDocument copierTypeDocument(HttpServletRequest request) {

		DaoFactory daoFactory = DaoFactory.getInstance();
		this.typeDocumentDao = daoFactory.getTypeDocumentDao();
		
		// récupération des paramétres CHEMIN RELATIF
		String cheminRelatif = getValeurChamp(request, CHAMP_CHEMINRELATIF);
		try {
			valeurCheminRelatif = validationCheminRelatif(cheminRelatif);
		} catch (Exception e) {
			setErreur(CHAMP_CHEMINRELATIF, e.getMessage());
		}
		
		// récupération CONTRAT
		String contrat = getValeurChamp(request, CHAMP_CONTRAT);
		try {
			valeurContrat = validationContrat(contrat);
		} catch (Exception e) {
			setErreur(CHAMP_CONTRAT, e.getMessage());
		}

		
		// récupération CHEMIN ABSOLU
		 cheminAbsolu = getValeurChamp(request, CHAMP_CHEMINABSOLU);
		 try {
				valeurCheminAbsolu = validationCheminAbsolu(cheminAbsolu);
			} catch (Exception e) {
				setErreur(CHAMP_CHEMINABSOLU, e.getMessage());
			}
				
		// Instanciation de la classe typeDocument
		TypeDocument typeDocument = new TypeDocument();
		typeDocument.setId(id);
		typeDocument.setCheminRelatif(cheminRelatif);
		typeDocument.setContrat(contrat);
		typeDocument.setCheminAbsolu(cheminAbsolu);

		if (erreurs.isEmpty()) {
			resultat = "Succès de la copie de la TypeDocument.";
		} else {
			resultat = "Échec de la copie de la TypeDocument.";
		}
		return typeDocument;
	}

	// Contrôle de la zone cheminrelatif
	private String validationCheminRelatif(String cheminrelatif) throws Exception {
		String temp = cheminrelatif;
		if (temp != null) {
			if (temp.length() < 1) {
				throw new Exception("Le chemin relatif est obligatoire.");
			} else {
				
			}
		} else {
			throw new Exception("le chemin relatif est obligatoire.");
		}
		return temp;
	}

	// Contrôle de la zone chemin relatif
		private String validationCheminAbsolu(String cheminabsolu) throws Exception {
			String temp = cheminabsolu;
			if (temp != null) {
				if (temp.length() < 1) {
					throw new Exception("Le chemin absolu est obligatoire.");
				} else {
					
				}
			} else {
				throw new Exception("le chemin absolu est obligatoire.");
			}
			return temp;
		}
	// Contrôle de la zone contrat
	private String validationContrat(String contrat) 
			throws Exception {
		String temp = contrat;
		if (temp != null) {
		} else {
			throw new Exception("le contrat est obligatoire.");
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
	private static String getValeurChamp(HttpServletRequest request, String cheminrelatifChamp) {
		String valeur = request.getParameter(cheminrelatifChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}
}
