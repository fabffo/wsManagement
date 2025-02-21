/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM CONTRATCLIENT GESTION                              ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.contrat;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

public final class GestionContratPagination {

	//====Page form Gestion CONTRAT CLIENT     ======================
	public Map<String, String> paginerContrat(HttpServletRequest request) {

		// Initialisation de variables
		String triContrat;
		String triContrat_id_contrat;
		String triContrat_type_contrat;
		String triContrat_client;
		String triContrat_statut;
		String select_triContrat = "collaborateur.id asc ";
		Map<String, String> resultat = new HashMap<String, String>();


		// ----------------Définition du tri du contrat ------------------------
		if (request.getParameter("triContrat") != null) {
			triContrat = request.getParameter("triContrat");
			request.getSession().setAttribute("triContrat", request.getParameter("triContrat"));
		} else
			if (request.getSession().getAttribute("triContrat") != null) {
				triContrat = (String) request.getSession().getAttribute("triContrat");
		}else {
			triContrat = "ID";
		}

		if (triContrat.equals("ID")) {
			if (request.getParameter("triContrat_id_contrat") != null) {
				triContrat_id_contrat = request.getParameter("triContrat_id_contrat");
				request.getSession().setAttribute("triContrat_id_contrat", request.getParameter("triContrat_id_contrat"));
			} else {
				if (request.getSession().getAttribute("triContrat_id_contrat") != null) {
					triContrat_id_contrat = (String) request.getSession().getAttribute("triContrat_id_contrat");
				} else {
					triContrat_id_contrat = "bi bi-caret-up text-white";
				}
			}
		} else {
			triContrat_id_contrat = "bi bi-caret-up text-white";
		}

		if (triContrat.equals("TYPE_CONTRAT")) {
			if (request.getParameter("triContrat_type_contrat") != null) {
				triContrat_type_contrat = request.getParameter("triContrat_type_contrat");
				request.getSession().setAttribute("triContrat_type_contrat", request.getParameter("triContrat_type_contrat"));
			} else {
				if (request.getSession().getAttribute("triContrat_type_contrat") != null) {
					triContrat_type_contrat = (String) request.getSession().getAttribute("triContrat_type_contrat");
				} else {
					triContrat_type_contrat = "bi bi-caret-up text-white";
				}
			}
		} else {
			triContrat_type_contrat = "bi bi-caret-up text-white";
		}

		if (triContrat.equals("CLIENT")) {
			if (request.getParameter("triContrat_client") != null) {
				triContrat_client = request.getParameter("triContrat_client");
				request.getSession().setAttribute("triContrat_client", request.getParameter("triContrat_client"));
			} else {
				if (request.getSession().getAttribute("triContrat_client") != null) {
					triContrat_client = (String) request.getSession().getAttribute("triContrat_client");
				} else {
					triContrat_client = "bi bi-caret-up text-white";
				}
			}
		} else {
			triContrat_client = "bi bi-caret-up text-white";
		}

		if (triContrat.equals("STATUT")) {
			if (request.getParameter("triContrat_statut") != null) {
				triContrat_statut = request.getParameter("triContrat_statut");
				request.getSession().setAttribute("triContrat_statut", request.getParameter("triContrat_statut"));
			} else {
				if (request.getSession().getAttribute("triContrat_statut") != null) {
					triContrat_statut = (String) request.getSession().getAttribute("triContrat_statut");
				} else {
					triContrat_statut = "bi bi-caret-up text-white";
				}
			}
		} else {
			triContrat_statut = "bi bi-caret-up text-white";
		}
		// ----------------Définition du tri du contrat ------------------------

		// ----------------Récupération des valeurs du tri du contrat ------------------------
		if (triContrat.equals("ID")) {

			switch (triContrat_id_contrat) {

			case "bi bi-caret-up text-white":
				select_triContrat = " contrat.id asc, contrat.version asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triContrat = " contrat.id desc, contrat.version desc ";
				break;

			default:
				select_triContrat = " contrat.id asc, contrat.version asc ";
				break;
			}
		}
		if (triContrat.equals("TYPE_CONTRAT")) {

			switch (triContrat_type_contrat) {

			case "bi bi-caret-up text-white":
				select_triContrat = " contrat.type_contrat asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triContrat = " contrat.type_contrat desc ";
				break;

			default:
				select_triContrat = " contrat.type_contrat asc ";
				break;
			}
		}

		if (triContrat.equals("CLIENT")) {

			switch (triContrat_client) {

			case "bi bi-caret-up text-white":
				select_triContrat = " societe.raison_sociale asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triContrat = " societe.raison_sociale desc ";
				break;

			default:
				select_triContrat = " societe.raison_sociale asc ";
				break;
			}
		}

		if (triContrat.equals("STATUT")) {

			switch (triContrat_statut) {

			case "bi bi-caret-up text-white":
				select_triContrat = " contrat.statut asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triContrat = " contrat.statut desc ";
				break;

			default:
				select_triContrat = " contrat.statut asc ";
				break;
			}
		}
		// ----------------Récupération des valeurs du tri du contrat ------------------------


		// ----------------Mise en résultat du tri ------------------------
		resultat.put("triContrat", triContrat);

		if (triContrat.equals("ID")) {
			resultat.put("tri_cle", triContrat_id_contrat);
		} else if (triContrat.equals("TYPE_CONTRAT")) {
			resultat.put("tri_cle", triContrat_type_contrat);
		} else if (triContrat.equals("CLIENT")) {
			resultat.put("tri_cle", triContrat_client);
		} else if (triContrat.equals("STATUT")) {
			resultat.put("tri_cle", triContrat_statut);
		}else
			resultat.put("tri_cle", triContrat_id_contrat);


		resultat.put("select_triContrat", select_triContrat);
		// ----------------Mise en résultat du tri ------------------------

		return resultat;
	}
	//====Page form Gestion CONTRAT CLIENT     ======================

}
