/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM TVA GESTION                                        ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.factureAchat;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.FactureAchatDao;
import com.ws.beans.FactureAchat;

import jakarta.servlet.http.HttpServletRequest;

public final class GestionFactureAchatPagination {

	public Map<String, String> paginerFactureAchat(HttpServletRequest request) {
		String triFactureAchat;
		String triFactureAchat_cle;
		String triFactureAchat_nom;
		String triFactureAchat_date;
		String triFactureAchat_user;
		String triFactureAchat_lignes;
		String select_triFactureAchat = "date asc ";
		Map<String, String> resultat = new HashMap<String, String>();

		if (request.getParameter("triFactureAchat") != null) {
			triFactureAchat = request.getParameter("triFactureAchat");
			request.getSession().setAttribute("triFactureAchat", request.getParameter("triFactureAchat"));
		} else
			if (request.getSession().getAttribute("triFactureAchat") != null) {
				triFactureAchat = (String) request.getSession().getAttribute("triFactureAchat");
		}else {
			triFactureAchat = "DATE";
		}

		if (triFactureAchat.equals("DATE")) {
			if (request.getParameter("triFactureAchat_date") != null) {
				triFactureAchat_date = request.getParameter("triFactureAchat_date");
				request.getSession().setAttribute("triFactureAchat_date", request.getParameter("triFactureAchat_date"));
			} else {
				if (request.getSession().getAttribute("triFactureAchat_date") != null) {
					triFactureAchat_date = (String) request.getSession().getAttribute("triFactureAchat_date");
				} else {
					triFactureAchat_date = "bi bi-caret-up text-white";
				}
			}
		} else {
			triFactureAchat_date = "bi bi-caret-up text-white";
		}

		if (triFactureAchat.equals("NOM")) {
			if (request.getParameter("triFactureAchat_nom") != null) {
				triFactureAchat_nom = request.getParameter("triFactureAchat_nom");
				request.getSession().setAttribute("triFactureAchat_nom", request.getParameter("triFactureAchat_nom"));
			} else {
				if (request.getSession().getAttribute("triFactureAchat_nom") != null) {
					triFactureAchat_nom = (String) request.getSession().getAttribute("triFactureAchat_nom");
				} else {
					triFactureAchat_nom = "bi bi-caret-up text-white";
				}
			}
		} else {
			triFactureAchat_nom = "bi bi-caret-up text-white";
		}

		if (triFactureAchat.equals("USER")) {
			if (request.getParameter("triFactureAchat_user") != null) {
				triFactureAchat_user = request.getParameter("triFactureAchat_user");
				request.getSession().setAttribute("triFactureAchat_user", request.getParameter("triFactureAchat_user"));
			} else {
				if (request.getSession().getAttribute("triFactureAchat_user") != null) {
					triFactureAchat_user = (String) request.getSession().getAttribute("triFactureAchat_user");
				} else {
					triFactureAchat_user = "bi bi-caret-up text-white";
				}
			}
		} else {
			triFactureAchat_user = "bi bi-caret-up text-white";
		}

		if (triFactureAchat.equals("LIGNES")) {
			if (request.getParameter("triFactureAchat_lignes") != null) {
				triFactureAchat_lignes = request.getParameter("triFactureAchat_lignes");
				request.getSession().setAttribute("triFactureAchat_lignes", request.getParameter("triFactureAchat_lignes"));
			} else {
				if (request.getSession().getAttribute("triFactureAchat_lignes") != null) {
					triFactureAchat_lignes = (String) request.getSession().getAttribute("triFactureAchat_lignes");
				} else {
					triFactureAchat_lignes = "bi bi-caret-up text-white";
				}
			}
		} else {
			triFactureAchat_lignes = "bi bi-caret-up text-white";
		}


		if (triFactureAchat.equals("DATE")) {

			switch (triFactureAchat_date) {

			case "bi bi-caret-up text-white":
				select_triFactureAchat = " date_import asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triFactureAchat = " date_import desc ";
				break;

			default:
				select_triFactureAchat = " date_import asc ";
				break;
			}
		}
		if (triFactureAchat.equals("NOM")) {

			switch (triFactureAchat_nom) {

			case "bi bi-caret-up text-white":
				select_triFactureAchat = " nom_import asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triFactureAchat = " nom_import desc ";
				break;

			default:
				select_triFactureAchat = " nom_import asc ";
				break;
			}
		}

		if (triFactureAchat.equals("USER")) {

			switch (triFactureAchat_user) {

			case "bi bi-caret-up text-white":
				select_triFactureAchat = " user_import asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triFactureAchat = " user_import desc ";
				break;

			default:
				select_triFactureAchat = " user_import asc ";
				break;
			}
		}

		if (triFactureAchat.equals("LIGNES")) {

			switch (triFactureAchat_lignes) {

			case "bi bi-caret-up text-white":
				select_triFactureAchat = " nbr_lignes asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triFactureAchat = " nbr_lignes desc ";
				break;

			default:
				select_triFactureAchat = " nbr_lignes asc ";
				break;
			}
		}


		resultat.put("triFactureAchat", triFactureAchat);

		if (triFactureAchat.equals("DATE")) {
			resultat.put("triFactureAchat_cle", triFactureAchat_date);
		} else if (triFactureAchat.equals("NOM")) {
			resultat.put("triFactureAchat_cle", triFactureAchat_nom);
		} else if (triFactureAchat.equals("USER")) {
			resultat.put("triFactureAchat_cle", triFactureAchat_user);
		} else if (triFactureAchat.equals("LIGNES")) {
			resultat.put("triFactureAchat_cle", triFactureAchat_lignes);
		}else
			resultat.put("triFactureAchat_cle", triFactureAchat_date);


		resultat.put("select_triFactureAchat", select_triFactureAchat);

		return resultat;
	}

}
