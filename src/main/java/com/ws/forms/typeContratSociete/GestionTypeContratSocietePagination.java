/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM TYPE MISSION GESTION                               ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.typeContratSociete;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypeContratSocieteDao;
import com.ws.beans.TypeContratSociete;

import jakarta.servlet.http.HttpServletRequest;

public final class GestionTypeContratSocietePagination {

	public Map<String, String> paginerTypeContratSociete(HttpServletRequest request) {
		String triTypeContratSociete;
		String triTypeContratSociete_id;
		String tri_cle;
		String triTypeContratSociete_nom;
		String triTypeContratSociete_libelle;
		String select_triTypeContratSociete = "id asc ";
		Map<String, String> resultat = new HashMap<String, String>();

		if (request.getParameter("triTypeContratSociete") != null) {
			triTypeContratSociete = request.getParameter("triTypeContratSociete");
			request.getSession().setAttribute("triTypeContratSociete", request.getParameter("triTypeContratSociete"));
		} else 
			if (request.getSession().getAttribute("triTypeContratSociete") != null) {
				triTypeContratSociete = (String) request.getSession().getAttribute("triTypeContratSociete");
		}else {
			triTypeContratSociete = "ID";
		}

		if (triTypeContratSociete.equals("ID")) {
			if (request.getParameter("triTypeContratSociete_id") != null) {
				triTypeContratSociete_id = request.getParameter("triTypeContratSociete_id");
				request.getSession().setAttribute("triTypeContratSociete_id", request.getParameter("triTypeContratSociete_id"));
			} else {
				if (request.getSession().getAttribute("triTypeContratSociete_id") != null) {
					triTypeContratSociete_id = (String) request.getSession().getAttribute("triTypeContratSociete_id");
				} else {
					triTypeContratSociete_id = "bi bi-caret-up text-white";
				}
			}
		} else {
			triTypeContratSociete_id = "bi bi-caret-up text-white";
		}

		if (triTypeContratSociete.equals("NOM")) {
			if (request.getParameter("triTypeContratSociete_nom") != null) {
				triTypeContratSociete_nom = request.getParameter("triTypeContratSociete_nom");
				request.getSession().setAttribute("triTypeContratSociete_nom", request.getParameter("triTypeContratSociete_nom"));
			} else {
				if (request.getSession().getAttribute("triTypeContratSociete_nom") != null) {
					triTypeContratSociete_nom = (String) request.getSession().getAttribute("triTypeContratSociete_nom");
				} else {
					triTypeContratSociete_nom = "bi bi-caret-up text-white";
				}
			}
		} else {
			triTypeContratSociete_nom = "bi bi-caret-up text-white";
		}
		
		if (triTypeContratSociete.equals("LIBELLE")) {
			if (request.getParameter("triTypeContratSociete_libelle") != null) {
				triTypeContratSociete_libelle = request.getParameter("triTypeContratSociete_libelle");
				request.getSession().setAttribute("triTypeContratSociete_libelle", request.getParameter("triTypeContratSociete_libelle"));
			} else {
				if (request.getSession().getAttribute("triTypeContratSociete_libelle") != null) {
					triTypeContratSociete_libelle = (String) request.getSession().getAttribute("triTypeContratSociete_libelle");
				} else {
					triTypeContratSociete_libelle = "bi bi-caret-up text-white";
				}
			}
		} else {
			triTypeContratSociete_libelle = "bi bi-caret-up text-white";
		}
		
		
		if (triTypeContratSociete.equals("ID")) {

			switch (triTypeContratSociete_id) {

			case "bi bi-caret-up text-white":
				select_triTypeContratSociete = " id asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTypeContratSociete = " id desc ";
				break;

			default:
				select_triTypeContratSociete = " id asc ";
				break;
			}
		}
		if (triTypeContratSociete.equals("NOM")) {

			switch (triTypeContratSociete_nom) {

			case "bi bi-caret-up text-white":
				select_triTypeContratSociete = " nom asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTypeContratSociete = " nom desc ";
				break;

			default:
				select_triTypeContratSociete = " nom asc ";
				break;
			}
		}
		
		if (triTypeContratSociete.equals("LIBELLE")) {

			switch (triTypeContratSociete_libelle) {

			case "bi bi-caret-up text-white":
				select_triTypeContratSociete = " libelle asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTypeContratSociete = " libelle desc ";
				break;

			default:
				select_triTypeContratSociete = " libelle asc ";
				break;
			}
		}
		
		
		resultat.put("triTypeContratSociete", triTypeContratSociete);

		if (triTypeContratSociete.equals("ID")) {
			resultat.put("tri_cle", triTypeContratSociete_id);
		} else if (triTypeContratSociete.equals("NOM")) {
			resultat.put("tri_cle", triTypeContratSociete_nom);
		} else if (triTypeContratSociete.equals("LIBELLE")) {
			resultat.put("tri_cle", triTypeContratSociete_libelle);
		} else
			resultat.put("tri_cle", triTypeContratSociete_id);
		

		resultat.put("select_triTypeContratSociete", select_triTypeContratSociete);

		return resultat;
	}

}
