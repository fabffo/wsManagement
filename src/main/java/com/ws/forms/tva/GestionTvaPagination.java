/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM TVA GESTION                                        ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.tva;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.TvaDao;
import com.ws.beans.Tva;

import jakarta.servlet.http.HttpServletRequest;

public final class GestionTvaPagination {

	public Map<String, String> paginerTva(HttpServletRequest request) {
		String triTva;
		String triTva_id;
		String triTva_cle;
		String triTva_nom;
		String triTva_libelle;
		String triTva_taux;
		String select_triTva = "id asc ";
		Map<String, String> resultat = new HashMap<String, String>();

		if (request.getParameter("triTva") != null) {
			triTva = request.getParameter("triTva");
			request.getSession().setAttribute("triTva", request.getParameter("triTva"));
		} else
			if (request.getSession().getAttribute("triTva") != null) {
				triTva = (String) request.getSession().getAttribute("triTva");
		}else {
			triTva = "id";
		}

		if (triTva.equals("id")) {
			if (request.getParameter("triTva_id") != null) {
				triTva_id = request.getParameter("triTva_id");
				request.getSession().setAttribute("triTva_id", request.getParameter("triTva_id"));
			} else {
				if (request.getSession().getAttribute("triTva_id") != null) {
					triTva_id = (String) request.getSession().getAttribute("triTva_id");
				} else {
					triTva_id = "bi bi-caret-up text-white";
				}
			}
		} else {
			triTva_id = "bi bi-caret-up text-white";
		}

		if (triTva.equals("nom")) {
			if (request.getParameter("triTva_nom") != null) {
				triTva_nom = request.getParameter("triTva_nom");
				request.getSession().setAttribute("triTva_nom", request.getParameter("triTva_nom"));
			} else {
				if (request.getSession().getAttribute("triTva_nom") != null) {
					triTva_nom = (String) request.getSession().getAttribute("triTva_nom");
				} else {
					triTva_nom = "bi bi-caret-up text-white";
				}
			}
		} else {
			triTva_nom = "bi bi-caret-up text-white";
		}

		if (triTva.equals("libelle")) {
			if (request.getParameter("triTva_libelle") != null) {
				triTva_libelle = request.getParameter("triTva_libelle");
				request.getSession().setAttribute("triTva_libelle", request.getParameter("triTva_libelle"));
			} else {
				if (request.getSession().getAttribute("triTva_libelle") != null) {
					triTva_libelle = (String) request.getSession().getAttribute("triTva_libelle");
				} else {
					triTva_libelle = "bi bi-caret-up text-white";
				}
			}
		} else {
			triTva_libelle = "bi bi-caret-up text-white";
		}

		if (triTva.equals("taux")) {
			if (request.getParameter("triTva_taux") != null) {
				triTva_taux = request.getParameter("triTva_taux");
				request.getSession().setAttribute("triTva_taux", request.getParameter("triTva_taux"));
			} else {
				if (request.getSession().getAttribute("triTva_taux") != null) {
					triTva_taux = (String) request.getSession().getAttribute("triTva_taux");
				} else {
					triTva_taux = "bi bi-caret-up text-white";
				}
			}
		} else {
			triTva_taux = "bi bi-caret-up text-white";
		}


		if (triTva.equals("id")) {

			switch (triTva_id) {

			case "bi bi-caret-up text-white":
				select_triTva = " id asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTva = " id desc ";
				break;

			default:
				select_triTva = " id asc ";
				break;
			}
		}
		if (triTva.equals("nom")) {

			switch (triTva_nom) {

			case "bi bi-caret-up text-white":
				select_triTva = " nom asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTva = " nom desc ";
				break;

			default:
				select_triTva = " nom asc ";
				break;
			}
		}

		if (triTva.equals("libelle")) {

			switch (triTva_libelle) {

			case "bi bi-caret-up text-white":
				select_triTva = " libelle asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTva = " libelle desc ";
				break;

			default:
				select_triTva = " libelle asc ";
				break;
			}
		}

		if (triTva.equals("taux")) {

			switch (triTva_taux) {

			case "bi bi-caret-up text-white":
				select_triTva = " taux asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triTva = " taux desc ";
				break;

			default:
				select_triTva = " taux asc ";
				break;
			}
		}


		resultat.put("triTva", triTva);

		if (triTva.equals("id")) {
			resultat.put("triTva_cle", triTva_id);
		} else if (triTva.equals("nom")) {
			resultat.put("triTva_cle", triTva_nom);
		} else if (triTva.equals("libelle")) {
			resultat.put("triTva_cle", triTva_libelle);
		} else if (triTva.equals("taux")) {
			resultat.put("triTva_cle", triTva_taux);
		}else
			resultat.put("triTva_cle", triTva_id);


		resultat.put("select_triTva", select_triTva);

		return resultat;
	}

}
