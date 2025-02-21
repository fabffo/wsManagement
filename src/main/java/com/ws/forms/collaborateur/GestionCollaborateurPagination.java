/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM COLLABORATEUR GESTION                              ///
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

public final class GestionCollaborateurPagination {

	public Map<String, String> paginerCollaborateur(HttpServletRequest request) {
		String triCollaborateur;
		String triCollaborateur_id;
		String tri_cle;
		String tri_nomCollaborateur;
		String tri_prenomCollaborateur;
		String tri_metierCollaborateur;
		String tri_type_collaborateur;
		String select_triCollaborateur = "collaborateur.id asc ";
		Map<String, String> resultat = new HashMap<String, String>();

		if (request.getParameter("triCollaborateur") != null) {
			triCollaborateur = request.getParameter("triCollaborateur");
			request.getSession().setAttribute("triCollaborateur", request.getParameter("triCollaborateur"));
		} else 
			if (request.getSession().getAttribute("triCollaborateur") != null) {
				triCollaborateur = (String) request.getSession().getAttribute("triCollaborateur");
		}else {
			triCollaborateur = "ID";
		}

		if (triCollaborateur.equals("ID")) {
			if (request.getParameter("triCollaborateur_id") != null) {
				triCollaborateur_id = request.getParameter("triCollaborateur_id");
				request.getSession().setAttribute("triCollaborateur_id", request.getParameter("triCollaborateur_id"));
			} else {
				if (request.getSession().getAttribute("triCollaborateur_id") != null) {
					triCollaborateur_id = (String) request.getSession().getAttribute("triCollaborateur_id");
				} else {
					triCollaborateur_id = "bi bi-caret-up text-white";
				}
			}
		} else {
			triCollaborateur_id = "bi bi-caret-up text-white";
		}

		if (triCollaborateur.equals("NOM")) {
			if (request.getParameter("tri_nomCollaborateur") != null) {
				tri_nomCollaborateur = request.getParameter("tri_nomCollaborateur");
				request.getSession().setAttribute("tri_nomCollaborateur", request.getParameter("tri_nomCollaborateur"));
			} else {
				if (request.getSession().getAttribute("tri_nomCollaborateur") != null) {
					tri_nomCollaborateur = (String) request.getSession().getAttribute("tri_nomCollaborateur");
				} else {
					tri_nomCollaborateur = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_nomCollaborateur = "bi bi-caret-up text-white";
		}
		
		if (triCollaborateur.equals("PRENOM")) {
			if (request.getParameter("tri_prenomCollaborateur") != null) {
				tri_prenomCollaborateur = request.getParameter("tri_prenomCollaborateur");
				request.getSession().setAttribute("tri_prenomCollaborateur", request.getParameter("tri_prenomCollaborateur"));
			} else {
				if (request.getSession().getAttribute("tri_prenomCollaborateur") != null) {
					tri_prenomCollaborateur = (String) request.getSession().getAttribute("tri_prenomCollaborateur");
				} else {
					tri_prenomCollaborateur = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_prenomCollaborateur = "bi bi-caret-up text-white";
		}
		
		if (triCollaborateur.equals("METIER")) {
			if (request.getParameter("tri_metierCollaborateur") != null) {
				tri_metierCollaborateur = request.getParameter("tri_metierCollaborateur");
				request.getSession().setAttribute("tri_metierCollaborateur", request.getParameter("tri_metierCollaborateur"));
			} else {
				if (request.getSession().getAttribute("tri_metierCollaborateur") != null) {
					tri_metierCollaborateur = (String) request.getSession().getAttribute("tri_metierCollaborateur");
				} else {
					tri_metierCollaborateur = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_metierCollaborateur = "bi bi-caret-up text-white";
		}
		
		if (triCollaborateur.equals("TYPE_COLLABORATEUR")) {
			if (request.getParameter("tri_type_collaborateur") != null) {
				tri_type_collaborateur = request.getParameter("tri_type_collaborateur");
				request.getSession().setAttribute("tri_type_collaborateur", request.getParameter("tri_type_collaborateur"));
			} else {
				if (request.getSession().getAttribute("tri_type_collaborateur") != null) {
					tri_type_collaborateur = (String) request.getSession().getAttribute("tri_type_collaborateur");
				} else {
					tri_type_collaborateur = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_type_collaborateur = "bi bi-caret-up text-white";
		}
		
		if (triCollaborateur.equals("ID")) {

			switch (triCollaborateur_id) {

			case "bi bi-caret-up text-white":
				select_triCollaborateur = " collaborateur.id asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triCollaborateur = " collaborateur.id desc ";
				break;

			default:
				select_triCollaborateur = " collaborateur.id asc ";
				break;
			}
		}
		if (triCollaborateur.equals("NOM")) {

			switch (tri_nomCollaborateur) {

			case "bi bi-caret-up text-white":
				select_triCollaborateur = " collaborateur.nom asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triCollaborateur = " collaborateur.nom desc ";
				break;

			default:
				select_triCollaborateur = " collaborateur.nom asc ";
				break;
			}
		}
		
		if (triCollaborateur.equals("PRENOM")) {

			switch (tri_prenomCollaborateur) {

			case "bi bi-caret-up text-white":
				select_triCollaborateur = " collaborateur.prenom asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triCollaborateur = " collaborateur.prenom desc ";
				break;

			default:
				select_triCollaborateur = " collaborateur.prenom asc ";
				break;
			}
		}
		
		if (triCollaborateur.equals("METIER")) {

			switch (tri_metierCollaborateur) {

			case "bi bi-caret-up text-white":
				select_triCollaborateur = " collaborateur.metier_principal asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triCollaborateur = " collaborateur.metier_principal desc ";
				break;

			default:
				select_triCollaborateur = " collaborateur.metier_principal asc ";
				break;
			}
		}
		
		if (triCollaborateur.equals("TYPE_COLLABORATEUR")) {

			switch (tri_type_collaborateur) {

			case "bi bi-caret-up text-white":
				select_triCollaborateur = " collaborateur.type_collaborateur asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triCollaborateur = " collaborateur.type_collaborateur desc ";
				break;

			default:
				select_triCollaborateur = " collaborateur.type_collaborateur asc ";
				break;
			}
		}
		
		
		resultat.put("triCollaborateur", triCollaborateur);

		if (triCollaborateur.equals("ID")) {
			resultat.put("tri_cle", triCollaborateur_id);
		} else if (triCollaborateur.equals("NOM")) {
			resultat.put("tri_cle", tri_nomCollaborateur);
		} else if (triCollaborateur.equals("PRENOM")) {
			resultat.put("tri_cle", tri_prenomCollaborateur);
		} else if (triCollaborateur.equals("METIER")) {
			resultat.put("tri_cle", tri_metierCollaborateur);
		}else if (triCollaborateur.equals("TYPE_COLLABORATEUR")) {
			resultat.put("tri_cle", tri_type_collaborateur);
		}else
			resultat.put("tri_cle", triCollaborateur_id);
		

		resultat.put("select_triCollaborateur", select_triCollaborateur);

		return resultat;
	}

}
