/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM TVA GESTION                                        ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.ecritureBanque;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.EcritureBanque_importDao;
import com.ws.beans.EcritureBanque_import;

import jakarta.servlet.http.HttpServletRequest;

public final class GestionEcritureBanque_importPagination {

	public Map<String, String> paginerEcritureBanque_import(HttpServletRequest request) {
		String triEcritureBanque_import;
		String triEcritureBanque_import_cle;
		String triEcritureBanque_import_nom;
		String triEcritureBanque_import_date;
		String triEcritureBanque_import_user;
		String triEcritureBanque_import_lignes;
		String select_triEcritureBanque_import = "date asc ";
		Map<String, String> resultat = new HashMap<String, String>();

		if (request.getParameter("triEcritureBanque_import") != null) {
			triEcritureBanque_import = request.getParameter("triEcritureBanque_import");
			request.getSession().setAttribute("triEcritureBanque_import", request.getParameter("triEcritureBanque_import"));
		} else
			if (request.getSession().getAttribute("triEcritureBanque_import") != null) {
				triEcritureBanque_import = (String) request.getSession().getAttribute("triEcritureBanque_import");
		}else {
			triEcritureBanque_import = "DATE";
		}

		if (triEcritureBanque_import.equals("DATE")) {
			if (request.getParameter("triEcritureBanque_import_date") != null) {
				triEcritureBanque_import_date = request.getParameter("triEcritureBanque_import_date");
				request.getSession().setAttribute("triEcritureBanque_import_date", request.getParameter("triEcritureBanque_import_date"));
			} else {
				if (request.getSession().getAttribute("triEcritureBanque_import_date") != null) {
					triEcritureBanque_import_date = (String) request.getSession().getAttribute("triEcritureBanque_import_date");
				} else {
					triEcritureBanque_import_date = "bi bi-caret-up text-white";
				}
			}
		} else {
			triEcritureBanque_import_date = "bi bi-caret-up text-white";
		}

		if (triEcritureBanque_import.equals("NOM")) {
			if (request.getParameter("triEcritureBanque_import_nom") != null) {
				triEcritureBanque_import_nom = request.getParameter("triEcritureBanque_import_nom");
				request.getSession().setAttribute("triEcritureBanque_import_nom", request.getParameter("triEcritureBanque_import_nom"));
			} else {
				if (request.getSession().getAttribute("triEcritureBanque_import_nom") != null) {
					triEcritureBanque_import_nom = (String) request.getSession().getAttribute("triEcritureBanque_import_nom");
				} else {
					triEcritureBanque_import_nom = "bi bi-caret-up text-white";
				}
			}
		} else {
			triEcritureBanque_import_nom = "bi bi-caret-up text-white";
		}

		if (triEcritureBanque_import.equals("USER")) {
			if (request.getParameter("triEcritureBanque_import_user") != null) {
				triEcritureBanque_import_user = request.getParameter("triEcritureBanque_import_user");
				request.getSession().setAttribute("triEcritureBanque_import_user", request.getParameter("triEcritureBanque_import_user"));
			} else {
				if (request.getSession().getAttribute("triEcritureBanque_import_user") != null) {
					triEcritureBanque_import_user = (String) request.getSession().getAttribute("triEcritureBanque_import_user");
				} else {
					triEcritureBanque_import_user = "bi bi-caret-up text-white";
				}
			}
		} else {
			triEcritureBanque_import_user = "bi bi-caret-up text-white";
		}

		if (triEcritureBanque_import.equals("LIGNES")) {
			if (request.getParameter("triEcritureBanque_import_lignes") != null) {
				triEcritureBanque_import_lignes = request.getParameter("triEcritureBanque_import_lignes");
				request.getSession().setAttribute("triEcritureBanque_import_lignes", request.getParameter("triEcritureBanque_import_lignes"));
			} else {
				if (request.getSession().getAttribute("triEcritureBanque_import_lignes") != null) {
					triEcritureBanque_import_lignes = (String) request.getSession().getAttribute("triEcritureBanque_import_lignes");
				} else {
					triEcritureBanque_import_lignes = "bi bi-caret-up text-white";
				}
			}
		} else {
			triEcritureBanque_import_lignes = "bi bi-caret-up text-white";
		}


		if (triEcritureBanque_import.equals("DATE")) {

			switch (triEcritureBanque_import_date) {

			case "bi bi-caret-up text-white":
				select_triEcritureBanque_import = " date_import asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triEcritureBanque_import = " date_import desc ";
				break;

			default:
				select_triEcritureBanque_import = " date_import asc ";
				break;
			}
		}
		if (triEcritureBanque_import.equals("NOM")) {

			switch (triEcritureBanque_import_nom) {

			case "bi bi-caret-up text-white":
				select_triEcritureBanque_import = " nom_import asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triEcritureBanque_import = " nom_import desc ";
				break;

			default:
				select_triEcritureBanque_import = " nom_import asc ";
				break;
			}
		}

		if (triEcritureBanque_import.equals("USER")) {

			switch (triEcritureBanque_import_user) {

			case "bi bi-caret-up text-white":
				select_triEcritureBanque_import = " user_import asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triEcritureBanque_import = " user_import desc ";
				break;

			default:
				select_triEcritureBanque_import = " user_import asc ";
				break;
			}
		}

		if (triEcritureBanque_import.equals("LIGNES")) {

			switch (triEcritureBanque_import_lignes) {

			case "bi bi-caret-up text-white":
				select_triEcritureBanque_import = " nbr_lignes asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triEcritureBanque_import = " nbr_lignes desc ";
				break;

			default:
				select_triEcritureBanque_import = " nbr_lignes asc ";
				break;
			}
		}


		resultat.put("triEcritureBanque_import", triEcritureBanque_import);

		if (triEcritureBanque_import.equals("DATE")) {
			resultat.put("triEcritureBanque_import_cle", triEcritureBanque_import_date);
		} else if (triEcritureBanque_import.equals("NOM")) {
			resultat.put("triEcritureBanque_import_cle", triEcritureBanque_import_nom);
		} else if (triEcritureBanque_import.equals("USER")) {
			resultat.put("triEcritureBanque_import_cle", triEcritureBanque_import_user);
		} else if (triEcritureBanque_import.equals("LIGNES")) {
			resultat.put("triEcritureBanque_import_cle", triEcritureBanque_import_lignes);
		}else
			resultat.put("triEcritureBanque_import_cle", triEcritureBanque_import_date);


		resultat.put("select_triEcritureBanque_import", select_triEcritureBanque_import);

		return resultat;
	}

}
