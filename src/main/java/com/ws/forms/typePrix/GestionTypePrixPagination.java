/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM TYPEPRIX  GESTION                               ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.typePrix;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypePrixDao;
import com.ws.beans.TypePrix;

import jakarta.servlet.http.HttpServletRequest;

public final class GestionTypePrixPagination {

	public Map<String, String> paginerTypePrix(HttpServletRequest request) {
		String triTypePrix;
		String triTypePrix_id;
		String tri_cle;
		String tri_nomTypePrix;
		String tri_libelleTypePrix;
		String select_triTypePrix = "id asc ";
		Map<String, String> resultat = new HashMap<String, String>();

		if (request.getParameter("triTypePrix") != null) {
			triTypePrix = request.getParameter("triTypePrix");
			request.getSession().setAttribute("triTypePrix", request.getParameter("triTypePrix"));
		} else 
			if (request.getSession().getAttribute("triTypePrix") != null) {
				triTypePrix = (String) request.getSession().getAttribute("triTypePrix");
		}else {
			triTypePrix = "ID";
		}

		if (triTypePrix.equals("ID")) {
			if (request.getParameter("triTypePrix_id") != null) {
				triTypePrix_id = request.getParameter("triTypePrix_id");
				request.getSession().setAttribute("triTypePrix_id", request.getParameter("triTypePrix_id"));
			} else {
				if (request.getSession().getAttribute("triTypePrix_id") != null) {
					triTypePrix_id = (String) request.getSession().getAttribute("triTypePrix_id");
				} else {
					triTypePrix_id = "bi bi-caret-up text-white";
				}
			}
		} else {
			triTypePrix_id = "bi bi-caret-up text-white";
		}

		if (triTypePrix.equals("NOM")) {
			if (request.getParameter("tri_nomTypePrix") != null) {
				tri_nomTypePrix = request.getParameter("tri_nomTypePrix");
				request.getSession().setAttribute("tri_nomTypePrix", request.getParameter("tri_nomTypePrix"));
			} else {
				if (request.getSession().getAttribute("tri_nomTypePrix") != null) {
					tri_nomTypePrix = (String) request.getSession().getAttribute("tri_nomTypePrix");
				} else {
					tri_nomTypePrix = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_nomTypePrix = "bi bi-caret-up text-white";
		}
		
		if (triTypePrix.equals("LIBELLE")) {
			if (request.getParameter("tri_libelleTypePrix") != null) {
				tri_libelleTypePrix = request.getParameter("tri_libelleTypePrix");
				request.getSession().setAttribute("tri_libelleTypePrix", request.getParameter("tri_libelleTypePrix"));
			} else {
				if (request.getSession().getAttribute("tri_libelleTypePrix") != null) {
					tri_libelleTypePrix = (String) request.getSession().getAttribute("tri_libelleTypePrix");
				} else {
					tri_libelleTypePrix = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_libelleTypePrix = "bi bi-caret-up text-white";
		}
		
		
		if (triTypePrix.equals("ID")) {

			switch (triTypePrix_id) {

			case "bi bi-caret-up text-white":
				select_triTypePrix = " id asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTypePrix = " id desc ";
				break;

			default:
				select_triTypePrix = " id asc ";
				break;
			}
		}
		if (triTypePrix.equals("NOM")) {

			switch (tri_nomTypePrix) {

			case "bi bi-caret-up text-white":
				select_triTypePrix = " nom asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTypePrix = " nom desc ";
				break;

			default:
				select_triTypePrix = " nom asc ";
				break;
			}
		}
		
		if (triTypePrix.equals("LIBELLE")) {

			switch (tri_libelleTypePrix) {

			case "bi bi-caret-up text-white":
				select_triTypePrix = " libelle asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTypePrix = " libelle desc ";
				break;

			default:
				select_triTypePrix = " libelle asc ";
				break;
			}
		}
		
		
		resultat.put("triTypePrix", triTypePrix);

		if (triTypePrix.equals("ID")) {
			resultat.put("tri_cle", triTypePrix_id);
		} else if (triTypePrix.equals("NOM")) {
			resultat.put("tri_cle", tri_nomTypePrix);
		} else if (triTypePrix.equals("LIBELLE")) {
			resultat.put("tri_cle", tri_libelleTypePrix);
		} else
			resultat.put("tri_cle", triTypePrix_id);
		

		resultat.put("select_triTypePrix", select_triTypePrix);

		return resultat;
	}

}
