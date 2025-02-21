/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM METIER  GESTION                                    ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.metier;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.MetierDao;
import com.ws.beans.Metier;

import jakarta.servlet.http.HttpServletRequest;

public final class GestionMetierPagination {

	public Map<String, String> paginerMetier(HttpServletRequest request) {
		String triMetier;
		String triMetier_id;
		String tri_cle;
		String tri_nomMetier;
		String tri_domaineMetier;
		String select_triMetier = "id asc ";
		Map<String, String> resultat = new HashMap<String, String>();

		if (request.getParameter("triMetier") != null) {
			triMetier = request.getParameter("triMetier");
			request.getSession().setAttribute("triMetier", request.getParameter("triMetier"));
		} else 
			if (request.getSession().getAttribute("triMetier") != null) {
				triMetier = (String) request.getSession().getAttribute("triMetier");
		}else {
			triMetier = "ID";
		}

		if (triMetier.equals("ID")) {
			if (request.getParameter("triMetier_id") != null) {
				triMetier_id = request.getParameter("triMetier_id");
				request.getSession().setAttribute("triMetier_id", request.getParameter("triMetier_id"));
			} else {
				if (request.getSession().getAttribute("triMetier_id") != null) {
					triMetier_id = (String) request.getSession().getAttribute("triMetier_id");
				} else {
					triMetier_id = "bi bi-caret-up text-white";
				}
			}
		} else {
			triMetier_id = "bi bi-caret-up text-white";
		}

		if (triMetier.equals("NOM")) {
			if (request.getParameter("tri_nomMetier") != null) {
				tri_nomMetier = request.getParameter("tri_nomMetier");
				request.getSession().setAttribute("tri_nomMetier", request.getParameter("tri_nomMetier"));
			} else {
				if (request.getSession().getAttribute("tri_nomMetier") != null) {
					tri_nomMetier = (String) request.getSession().getAttribute("tri_nomMetier");
				} else {
					tri_nomMetier = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_nomMetier = "bi bi-caret-up text-white";
		}
		
		if (triMetier.equals("DOMAINE")) {
			if (request.getParameter("tri_domaineMetier") != null) {
				tri_domaineMetier = request.getParameter("tri_domaineMetier");
				request.getSession().setAttribute("tri_domaineMetier", request.getParameter("tri_domaineMetier"));
			} else {
				if (request.getSession().getAttribute("tri_domaineMetier") != null) {
					tri_domaineMetier = (String) request.getSession().getAttribute("tri_domaineMetier");
				} else {
					tri_domaineMetier = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_domaineMetier = "bi bi-caret-up text-white";
		}
		
		
		if (triMetier.equals("ID")) {

			switch (triMetier_id) {

			case "bi bi-caret-up text-white":
				select_triMetier = " id asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triMetier = " id desc ";
				break;

			default:
				select_triMetier = " id asc ";
				break;
			}
		}
		if (triMetier.equals("NOM")) {

			switch (tri_nomMetier) {

			case "bi bi-caret-up text-white":
				select_triMetier = " nom asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triMetier = " nom desc ";
				break;

			default:
				select_triMetier = " nom asc ";
				break;
			}
		}
		
		if (triMetier.equals("DOMAINE")) {

			switch (tri_domaineMetier) {

			case "bi bi-caret-up text-white":
				select_triMetier = " domaine asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triMetier = " domaine desc ";
				break;

			default:
				select_triMetier = " domaine asc ";
				break;
			}
		}
		
		
		resultat.put("triMetier", triMetier);

		if (triMetier.equals("ID")) {
			resultat.put("tri_cle", triMetier_id);
		} else if (triMetier.equals("NOM")) {
			resultat.put("tri_cle", tri_nomMetier);
		} else if (triMetier.equals("DOMAINE")) {
			resultat.put("tri_cle", tri_domaineMetier);
		} else
			resultat.put("tri_cle", triMetier_id);
		

		resultat.put("select_triMetier", select_triMetier);

		return resultat;
	}

}
