/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM TYPE CONTRAT GESTION                               ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.typeContratCollaborateur;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypeContratCollaborateurDao;
import com.ws.beans.TypeContratCollaborateur;

import jakarta.servlet.http.HttpServletRequest;

public final class GestionTypeContratCollaborateurPagination {

	public Map<String, String> paginertypeContratCollaborateur(HttpServletRequest request) {
		String triTypeContratCollaborateur;
		String triTypeContratCollaborateur_id;
		String tri_cle;
		String triTypeContratCollaborateur_nom;
		String triTypeContratCollaborateur_libelle;
		String triTypeContratCollaborateur_statut;
		String select_triTypeContratCollaborateur = "id asc ";
		Map<String, String> resultat = new HashMap<String, String>();

		if (request.getParameter("triTypeContratCollaborateur") != null) {
			triTypeContratCollaborateur = request.getParameter("triTypeContratCollaborateur");
			request.getSession().setAttribute("triTypeContratCollaborateur", request.getParameter("triTypeContratCollaborateur"));
		} else 
			if (request.getSession().getAttribute("triTypeContratCollaborateur") != null) {
				triTypeContratCollaborateur = (String) request.getSession().getAttribute("triTypeContratCollaborateur");
		}else {
			triTypeContratCollaborateur = "ID";
		}

		if (triTypeContratCollaborateur.equals("ID")) {
			if (request.getParameter("triTypeContratCollaborateur_id") != null) {
				triTypeContratCollaborateur_id = request.getParameter("triTypeContratCollaborateur_id");
				request.getSession().setAttribute("triTypeContratCollaborateur_id", request.getParameter("triTypeContratCollaborateur_id"));
			} else {
				if (request.getSession().getAttribute("triTypeContratCollaborateur_id") != null) {
					triTypeContratCollaborateur_id = (String) request.getSession().getAttribute("triTypeContratCollaborateur_id");
				} else {
					triTypeContratCollaborateur_id = "bi bi-caret-up text-white";
				}
			}
		} else {
			triTypeContratCollaborateur_id = "bi bi-caret-up text-white";
		}

		if (triTypeContratCollaborateur.equals("NOM")) {
			if (request.getParameter("triTypeContratCollaborateur_nom") != null) {
				triTypeContratCollaborateur_nom = request.getParameter("triTypeContratCollaborateur_nom");
				request.getSession().setAttribute("triTypeContratCollaborateur_nom", request.getParameter("triTypeContratCollaborateur_nom"));
			} else {
				if (request.getSession().getAttribute("triTypeContratCollaborateur_nom") != null) {
					triTypeContratCollaborateur_nom = (String) request.getSession().getAttribute("triTypeContratCollaborateur_nom");
				} else {
					triTypeContratCollaborateur_nom = "bi bi-caret-up text-white";
				}
			}
		} else {
			triTypeContratCollaborateur_nom = "bi bi-caret-up text-white";
		}
		
		if (triTypeContratCollaborateur.equals("LIBELLE")) {
			if (request.getParameter("triTypeContratCollaborateur_libelle") != null) {
				triTypeContratCollaborateur_libelle = request.getParameter("triTypeContratCollaborateur_libelle");
				request.getSession().setAttribute("triTypeContratCollaborateur_libelle", request.getParameter("triTypeContratCollaborateur_libelle"));
			} else {
				if (request.getSession().getAttribute("triTypeContratCollaborateur_libelle") != null) {
					triTypeContratCollaborateur_libelle = (String) request.getSession().getAttribute("triTypeContratCollaborateur_libelle");
				} else {
					triTypeContratCollaborateur_libelle = "bi bi-caret-up text-white";
				}
			}
		} else {
			triTypeContratCollaborateur_libelle = "bi bi-caret-up text-white";
		}
		
		if (triTypeContratCollaborateur.equals("STATUT")) {
			if (request.getParameter("triTypeContratCollaborateur_statut") != null) {
				triTypeContratCollaborateur_statut = request.getParameter("triTypeContratCollaborateur_statut");
				request.getSession().setAttribute("triTypeContratCollaborateur_statut", request.getParameter("triTypeContratCollaborateur_statut"));
			} else {
				if (request.getSession().getAttribute("triTypeContratCollaborateur_statut") != null) {
					triTypeContratCollaborateur_statut = (String) request.getSession().getAttribute("triTypeContratCollaborateur_statut");
				} else {
					triTypeContratCollaborateur_statut = "bi bi-caret-up text-white";
				}
			}
		} else {
			triTypeContratCollaborateur_statut = "bi bi-caret-up text-white";
		}
		
		if (triTypeContratCollaborateur.equals("ID")) {

			switch (triTypeContratCollaborateur_id) {

			case "bi bi-caret-up text-white":
				select_triTypeContratCollaborateur = " id asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTypeContratCollaborateur = " id desc ";
				break;

			default:
				select_triTypeContratCollaborateur = " id asc ";
				break;
			}
		}
		if (triTypeContratCollaborateur.equals("NOM")) {

			switch (triTypeContratCollaborateur_nom) {

			case "bi bi-caret-up text-white":
				select_triTypeContratCollaborateur = " nom asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTypeContratCollaborateur = " nom desc ";
				break;

			default:
				select_triTypeContratCollaborateur = " nom asc ";
				break;
			}
		}
		
		if (triTypeContratCollaborateur.equals("LIBELLE")) {

			switch (triTypeContratCollaborateur_libelle) {

			case "bi bi-caret-up text-white":
				select_triTypeContratCollaborateur = " libelle asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTypeContratCollaborateur = " libelle desc ";
				break;

			default:
				select_triTypeContratCollaborateur = " libelle asc ";
				break;
			}
		}
		
		if (triTypeContratCollaborateur.equals("STATUT")) {

			switch (triTypeContratCollaborateur_statut) {

			case "bi bi-caret-up text-white":
				select_triTypeContratCollaborateur = " statut asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTypeContratCollaborateur = " statut desc ";
				break;

			default:
				select_triTypeContratCollaborateur = " statut asc ";
				break;
			}
		}
		
		
		resultat.put("triTypeContratCollaborateur", triTypeContratCollaborateur);

		if (triTypeContratCollaborateur.equals("ID")) {
			resultat.put("tri_cle", triTypeContratCollaborateur_id);
		} else if (triTypeContratCollaborateur.equals("NOM")) {
			resultat.put("tri_cle", triTypeContratCollaborateur_nom);
		} else if (triTypeContratCollaborateur.equals("LIBELLE")) {
			resultat.put("tri_cle", triTypeContratCollaborateur_libelle);
		} else if (triTypeContratCollaborateur.equals("STATUT")) {
			resultat.put("tri_cle", triTypeContratCollaborateur_statut);
		}else
			resultat.put("tri_cle", triTypeContratCollaborateur_id);
		

		resultat.put("select_triTypeContratCollaborateur", select_triTypeContratCollaborateur);

		return resultat;
	}

}
