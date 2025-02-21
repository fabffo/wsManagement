/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM CONTRATCLIENT GESTION                              ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.mission;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

public final class GestionMissionPagination {

	//====Page form Gestion CONTRAT CLIENT     ======================
	public Map<String, String> paginerMission(HttpServletRequest request) {

		// Initialisation de variables
		String triMission;
		String triMission_contrat;
		String triMission_id;
		String triMission_nom;
		String triMission_type_contrat;
		String triMission_client;
		String triMission_statut;
		String select_triMission = "contrat.id asc ";
		Map<String, String> resultat = new HashMap<String, String>();


		// ----------------Définition du tri du mission ------------------------
		if (request.getParameter("triMission") != null) {
			triMission = request.getParameter("triMission");
			request.getSession().setAttribute("triMission", request.getParameter("triMission"));
		} else
			if (request.getSession().getAttribute("triMission") != null) {
				triMission = (String) request.getSession().getAttribute("triMission");
		}else {
			triMission = "CONTRAT";
		}


		if (triMission.equals("CONTRAT")) {
			if (request.getParameter("triMission_contrat") != null) {
				triMission_contrat = request.getParameter("triMission_contrat");
				request.getSession().setAttribute("triMission_contrat", request.getParameter("triMission_contrat"));
			} else {
				if (request.getSession().getAttribute("triMission_contrat") != null) {
					triMission_contrat = (String) request.getSession().getAttribute("triMission_contrat");
				} else {
					triMission_contrat = "bi bi-caret-up text-white";
				}
			}
		} else {
			triMission_contrat = "bi bi-caret-up text-white";
		}

		if (triMission.equals("ID")) {
			if (request.getParameter("triMission_id") != null) {
				triMission_id = request.getParameter("triMission_id");
				request.getSession().setAttribute("triMission_id", request.getParameter("triMission_id"));
			} else {
				if (request.getSession().getAttribute("triMission_id") != null) {
					triMission_id = (String) request.getSession().getAttribute("triMission_id");
				} else {
					triMission_id = "bi bi-caret-up text-white";
				}
			}
		} else {
			triMission_id = "bi bi-caret-up text-white";
		}

		if (triMission.equals("NOM")) {
			if (request.getParameter("triMission_nom") != null) {
				triMission_nom = request.getParameter("triMission_nom");
				request.getSession().setAttribute("triMission_nom", request.getParameter("triMission_nom"));
			} else {
				if (request.getSession().getAttribute("triMission_nom") != null) {
					triMission_nom = (String) request.getSession().getAttribute("triMission_nom");
				} else {
					triMission_nom = "bi bi-caret-up text-white";
				}
			}
		} else {
			triMission_nom = "bi bi-caret-up text-white";
		}

		if (triMission.equals("TYPE_CONTRAT")) {
			if (request.getParameter("triMission_type_contrat") != null) {
				triMission_type_contrat = request.getParameter("triMission_type_contrat");
				request.getSession().setAttribute("triMission_type_contrat", request.getParameter("triMission_type_contrat"));
			} else {
				if (request.getSession().getAttribute("triMission_type_contrat") != null) {
					triMission_type_contrat = (String) request.getSession().getAttribute("triMission_type_contrat");
				} else {
					triMission_type_contrat = "bi bi-caret-up text-white";
				}
			}
		} else {
			triMission_type_contrat = "bi bi-caret-up text-white";
		}

		if (triMission.equals("CLIENT")) {
			if (request.getParameter("triMission_client") != null) {
				triMission_client = request.getParameter("triMission_client");
				request.getSession().setAttribute("triMission_client", request.getParameter("triMission_client"));
			} else {
				if (request.getSession().getAttribute("triMission_client") != null) {
					triMission_client = (String) request.getSession().getAttribute("triMission_client");
				} else {
					triMission_client = "bi bi-caret-up text-white";
				}
			}
		} else {
			triMission_client = "bi bi-caret-up text-white";
		}

		if (triMission.equals("STATUT")) {
			if (request.getParameter("triMission_statut") != null) {
				triMission_statut = request.getParameter("triMission_statut");
				request.getSession().setAttribute("triMission_statut", request.getParameter("triMission_statut"));
			} else {
				if (request.getSession().getAttribute("triMission_statut") != null) {
					triMission_statut = (String) request.getSession().getAttribute("triMission_statut");
				} else {
					triMission_statut = "bi bi-caret-up text-white";
				}
			}
		} else {
			triMission_statut = "bi bi-caret-up text-white";
		}
		// ----------------Définition du tri du mission ------------------------

		// ----------------Récupération des valeurs du tri du mission ------------------------
		if (triMission.equals("CONTRAT")) {

			switch (triMission_contrat) {

			case "bi bi-caret-up text-white":
				select_triMission = " contrat.id asc, contrat.version asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triMission = " contrat.id desc, contrat.version desc ";
				break;

			default:
				select_triMission = " contrat.id asc, contrat.version asc ";
				break;
			}
		}
		if (triMission.equals("ID")) {

			switch (triMission_id) {

			case "bi bi-caret-up text-white":
				select_triMission = " mission.id asc, mission.version asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triMission = " mission.id desc, mission.version desc ";
				break;

			default:
				select_triMission = " mission.id asc, mission.version asc ";
				break;
			}
		}

		if (triMission.equals("NOM")) {

			switch (triMission_type_contrat) {

			case "bi bi-caret-up text-white":
				select_triMission = "mission.nom asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triMission = " mission.nom  desc ";
				break;

			default:
				select_triMission = " mission.nom  asc ";
				break;
			}
		}
		if (triMission.equals("TYPE_CONTRAT")) {

			switch (triMission_type_contrat) {

			case "bi bi-caret-up text-white":
				select_triMission = " contrat.type_contrat asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triMission = " contrat.type_contrat  desc ";
				break;

			default:
				select_triMission = " contrat.type_contrat  asc ";
				break;
			}
		}
		if (triMission.equals("CLIENT")) {

			switch (triMission_client) {

			case "bi bi-caret-up text-white":
				select_triMission = " societe.raison_sociale asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triMission = " societe.raison_sociale desc ";
				break;

			default:
				select_triMission = " societe.raison_sociale asc ";
				break;
			}
		}

		if (triMission.equals("STATUT")) {

			switch (triMission_statut) {

			case "bi bi-caret-up text-white":
				select_triMission = " mission.statut asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triMission = " mission.statut desc ";
				break;

			default:
				select_triMission = " mission.statut asc ";
				break;
			}
		}
		// ----------------Récupération des valeurs du tri du mission ------------------------


		// ----------------Mise en résultat du tri ------------------------
		resultat.put("triMission", triMission);
		if (triMission.equals("CONTRAT")) {
			resultat.put("tri_cle", triMission_contrat);
		} else if (triMission.equals("ID")) {
			resultat.put("tri_cle", triMission_id);
		} else if (triMission.equals("NOM")) {
			resultat.put("tri_cle", triMission_nom);
		} else if (triMission.equals("TYPE_CONTRAT")) {
			resultat.put("tri_cle", triMission_type_contrat);
		} else if (triMission.equals("CLIENT")) {
			resultat.put("tri_cle", triMission_client);
		} else if (triMission.equals("STATUT")) {
			resultat.put("tri_cle", triMission_statut);
		}else
			resultat.put("tri_cle", triMission_id);


		resultat.put("select_triMission", select_triMission);
		// ----------------Mise en résultat du tri ------------------------

		return resultat;
	}
	//====Page form Gestion CONTRAT CLIENT     ======================

}
