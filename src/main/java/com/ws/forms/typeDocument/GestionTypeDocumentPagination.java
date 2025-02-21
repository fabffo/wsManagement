/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM TYPEDOCUMENT GESTION                                        ///
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

public final class GestionTypeDocumentPagination {

	public Map<String, String> paginerTypeDocument(HttpServletRequest request) {
		String triTypeDocument;
		String triTypeDocument_id;
		String triTypeDocument_cle;
		String triTypeDocument_cheminrelatif;
		String triTypeDocument_contrat;
		String triTypeDocument_cheminAbsolu;
		String select_triTypeDocument = "id asc ";
		Map<String, String> resultat = new HashMap<String, String>();

		if (request.getParameter("triTypeDocument") != null) {
			triTypeDocument = request.getParameter("triTypeDocument");
			request.getSession().setAttribute("triTypeDocument", request.getParameter("triTypeDocument"));
		} else 
			if (request.getSession().getAttribute("triTypeDocument") != null) {
				triTypeDocument = (String) request.getSession().getAttribute("triTypeDocument");
		}else {
			triTypeDocument = "ID";
		}

		if (triTypeDocument.equals("ID")) {
			if (request.getParameter("triTypeDocument_id") != null) {
				triTypeDocument_id = request.getParameter("triTypeDocument_id");
				request.getSession().setAttribute("triTypeDocument_id", request.getParameter("triTypeDocument_id"));
			} else {
				if (request.getSession().getAttribute("triTypeDocument_id") != null) {
					triTypeDocument_id = (String) request.getSession().getAttribute("triTypeDocument_id");
				} else {
					triTypeDocument_id = "bi bi-caret-up text-white";
				}
			}
		} else {
			triTypeDocument_id = "bi bi-caret-up text-white";
		}

		if (triTypeDocument.equals("CHEMINRELATIF")) {
			if (request.getParameter("triTypeDocument_cheminrelatif") != null) {
				triTypeDocument_cheminrelatif = request.getParameter("triTypeDocument_cheminrelatif");
				request.getSession().setAttribute("triTypeDocument_cheminrelatif", request.getParameter("triTypeDocument_cheminrelatif"));
			} else {
				if (request.getSession().getAttribute("triTypeDocument_cheminrelatif") != null) {
					triTypeDocument_cheminrelatif = (String) request.getSession().getAttribute("triTypeDocument_cheminrelatif");
				} else {
					triTypeDocument_cheminrelatif = "bi bi-caret-up text-white";
				}
			}
		} else {
			triTypeDocument_cheminrelatif = "bi bi-caret-up text-white";
		}
		
		if (triTypeDocument.equals("CONTRAT")) {
			if (request.getParameter("triTypeDocument_contrat") != null) {
				triTypeDocument_contrat = request.getParameter("triTypeDocument_contrat");
				request.getSession().setAttribute("triTypeDocument_contrat", request.getParameter("triTypeDocument_contrat"));
			} else {
				if (request.getSession().getAttribute("triTypeDocument_contrat") != null) {
					triTypeDocument_contrat = (String) request.getSession().getAttribute("triTypeDocument_contrat");
				} else {
					triTypeDocument_contrat = "bi bi-caret-up text-white";
				}
			}
		} else {
			triTypeDocument_contrat = "bi bi-caret-up text-white";
		}
		
		if (triTypeDocument.equals("CHEMINABSOLU")) {
			if (request.getParameter("triTypeDocument_cheminAbsolu") != null) {
				triTypeDocument_cheminAbsolu = request.getParameter("triTypeDocument_cheminAbsolu");
				request.getSession().setAttribute("triTypeDocument_cheminAbsolu", request.getParameter("triTypeDocument_cheminAbsolu"));
			} else {
				if (request.getSession().getAttribute("triTypeDocument_cheminAbsolu") != null) {
					triTypeDocument_cheminAbsolu = (String) request.getSession().getAttribute("triTypeDocument_cheminAbsolu");
				} else {
					triTypeDocument_cheminAbsolu = "bi bi-caret-up text-white";
				}
			}
		} else {
			triTypeDocument_cheminAbsolu = "bi bi-caret-up text-white";
		}
		
		
		if (triTypeDocument.equals("ID")) {

			switch (triTypeDocument_id) {

			case "bi bi-caret-up text-white":
				select_triTypeDocument = " id asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTypeDocument = " id desc ";
				break;

			default:
				select_triTypeDocument = " id asc ";
				break;
			}
		}
		if (triTypeDocument.equals("CHEMINRELATIF")) {

			switch (triTypeDocument_cheminrelatif) {

			case "bi bi-caret-up text-white":
				select_triTypeDocument = " cheminrelatif asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTypeDocument = " cheminrelatif desc ";
				break;

			default:
				select_triTypeDocument = " cheminrelatif asc ";
				break;
			}
		}
		
		if (triTypeDocument.equals("CONTRAT")) {

			switch (triTypeDocument_contrat) {

			case "bi bi-caret-up text-white":
				select_triTypeDocument = " contrat asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTypeDocument = " contrat desc ";
				break;

			default:
				select_triTypeDocument = " contrat asc ";
				break;
			}
		}
		
		if (triTypeDocument.equals("CHEMINABSOLU")) {

			switch (triTypeDocument_cheminAbsolu) {

			case "bi bi-caret-up text-white":
				select_triTypeDocument = " cheminAbsolu asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTypeDocument = " cheminAbsolu desc ";
				break;

			default:
				select_triTypeDocument = " cheminAbsolu asc ";
				break;
			}
		}
		
		
		resultat.put("triTypeDocument", triTypeDocument);

		if (triTypeDocument.equals("ID")) {
			resultat.put("triTypeDocument_cle", triTypeDocument_id);
		} else if (triTypeDocument.equals("CHEMINRELATIF")) {
			resultat.put("triTypeDocument_cle", triTypeDocument_cheminrelatif);
		} else if (triTypeDocument.equals("CONTRAT")) {
			resultat.put("triTypeDocument_cle", triTypeDocument_contrat);
		} else if (triTypeDocument.equals("CHEMINABSOLU")) {
			resultat.put("triTypeDocument_cle", triTypeDocument_cheminAbsolu);
		}else
			resultat.put("triTypeDocument_cle", triTypeDocument_id);
		

		resultat.put("select_triTypeDocument", select_triTypeDocument);

		return resultat;
	}

}
