/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM TYPE SOCIETE GESTION                               ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.typeSociete;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypeSocieteDao;
import com.ws.beans.TypeSociete;

import jakarta.servlet.http.HttpServletRequest;

public final class GestionTypeSocietePagination {

	public Map<String, String> paginerTypeSociete(HttpServletRequest request) {
		String triTypeSociete;
		String triTypeSociete_id;
		String tri_cle;
		String tri_nomTypeSociete;
		String tri_libelleTypeSociete;
		String select_triTypeSociete = "id asc ";
		Map<String, String> resultat = new HashMap<String, String>();

		if (request.getParameter("triTypeSociete") != null) {
			triTypeSociete = request.getParameter("triTypeSociete");
			request.getSession().setAttribute("triTypeSociete", request.getParameter("triTypeSociete"));
		} else 
			if (request.getSession().getAttribute("triTypeSociete") != null) {
				triTypeSociete = (String) request.getSession().getAttribute("triTypeSociete");
		}else {
			triTypeSociete = "ID";
		}

		if (triTypeSociete.equals("ID")) {
			if (request.getParameter("triTypeSociete_id") != null) {
				triTypeSociete_id = request.getParameter("triTypeSociete_id");
				request.getSession().setAttribute("triTypeSociete_id", request.getParameter("triTypeSociete_id"));
			} else {
				if (request.getSession().getAttribute("triTypeSociete_id") != null) {
					triTypeSociete_id = (String) request.getSession().getAttribute("triTypeSociete_id");
				} else {
					triTypeSociete_id = "bi bi-caret-up text-white";
				}
			}
		} else {
			triTypeSociete_id = "bi bi-caret-up text-white";
		}

		if (triTypeSociete.equals("NOM")) {
			if (request.getParameter("tri_nomTypeSociete") != null) {
				tri_nomTypeSociete = request.getParameter("tri_nomTypeSociete");
				request.getSession().setAttribute("tri_nomTypeSociete", request.getParameter("tri_nomTypeSociete"));
			} else {
				if (request.getSession().getAttribute("tri_nomTypeSociete") != null) {
					tri_nomTypeSociete = (String) request.getSession().getAttribute("tri_nomTypeSociete");
				} else {
					tri_nomTypeSociete = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_nomTypeSociete = "bi bi-caret-up text-white";
		}
		
		if (triTypeSociete.equals("LIBELLE")) {
			if (request.getParameter("tri_libelleTypeSociete") != null) {
				tri_libelleTypeSociete = request.getParameter("tri_libelleTypeSociete");
				request.getSession().setAttribute("tri_libelleTypeSociete", request.getParameter("tri_libelleTypeSociete"));
			} else {
				if (request.getSession().getAttribute("tri_libelleTypeSociete") != null) {
					tri_libelleTypeSociete = (String) request.getSession().getAttribute("tri_libelleTypeSociete");
				} else {
					tri_libelleTypeSociete = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_libelleTypeSociete = "bi bi-caret-up text-white";
		}
		
		
		if (triTypeSociete.equals("ID")) {

			switch (triTypeSociete_id) {

			case "bi bi-caret-up text-white":
				select_triTypeSociete = " id asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTypeSociete = " id desc ";
				break;

			default:
				select_triTypeSociete = " id asc ";
				break;
			}
		}
		if (triTypeSociete.equals("NOM")) {

			switch (tri_nomTypeSociete) {

			case "bi bi-caret-up text-white":
				select_triTypeSociete = " nom asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTypeSociete = " nom desc ";
				break;

			default:
				select_triTypeSociete = " nom asc ";
				break;
			}
		}
		
		if (triTypeSociete.equals("LIBELLE")) {

			switch (tri_libelleTypeSociete) {

			case "bi bi-caret-up text-white":
				select_triTypeSociete = " libelle asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTypeSociete = " libelle desc ";
				break;

			default:
				select_triTypeSociete = " libelle asc ";
				break;
			}
		}
		
		
		resultat.put("triTypeSociete", triTypeSociete);

		if (triTypeSociete.equals("ID")) {
			resultat.put("tri_cle", triTypeSociete_id);
		} else if (triTypeSociete.equals("NOM")) {
			resultat.put("tri_cle", tri_nomTypeSociete);
		} else if (triTypeSociete.equals("LIBELLE")) {
			resultat.put("tri_cle", tri_libelleTypeSociete);
		} else
			resultat.put("tri_cle", triTypeSociete_id);
		

		resultat.put("select_triTypeSociete", select_triTypeSociete);

		return resultat;
	}

}
