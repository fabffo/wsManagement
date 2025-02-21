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
import com.ws.beans.EcritureBanque_detail;

import jakarta.servlet.http.HttpServletRequest;

public final class RapprochementEcritureBanquePagination {

	public Map<String, String> paginerRapprochementEcritureBanque(HttpServletRequest request) {
		String triRapprochementEcritureBanque;
		String triRapprochementEcritureBanque_cle;
		String triRapprochementEcritureBanque_date_ecriture;
		String triRapprochementEcritureBanque_entite;
		String triRapprochementEcritureBanque_nom_entite;
		String triRapprochementEcritureBanque_libelle_ecriture;
		String triRapprochementEcritureBanque_signe;
		String triRapprochementEcritureBanque_ttc;
		String triRapprochementEcritureBanque_statut_rapproche;
		String select_triRapprochementEcritureBanque = "date_ecriture asc ";
		Map<String, String> resultat = new HashMap<String, String>();

		if (request.getParameter("triRapprochementEcritureBanque") != null) {
			triRapprochementEcritureBanque = request.getParameter("triRapprochementEcritureBanque");
			request.getSession().setAttribute("triRapprochementEcritureBanque", request.getParameter("triRapprochementEcritureBanque"));
		} else
			if (request.getSession().getAttribute("triRapprochementEcritureBanque") != null) {
				triRapprochementEcritureBanque = (String) request.getSession().getAttribute("triRapprochementEcritureBanque");
		}else {
			triRapprochementEcritureBanque = "DATE_ECRITURE";
		}

		if (triRapprochementEcritureBanque.equals("DATE_ECRITURE")) {
			if (request.getParameter("triRapprochementEcritureBanque_date_ecriture") != null) {
				triRapprochementEcritureBanque_date_ecriture = request.getParameter("triRapprochementEcritureBanque_date_ecriture");
				request.getSession().setAttribute("triRapprochementEcritureBanque_date_ecriture", request.getParameter("triRapprochementEcritureBanque_date_ecriture"));
			} else {
				if (request.getSession().getAttribute("triRapprochementEcritureBanque_date_ecriture") != null) {
					triRapprochementEcritureBanque_date_ecriture = (String) request.getSession().getAttribute("triRapprochementEcritureBanque_date_ecriture");
				} else {
					triRapprochementEcritureBanque_date_ecriture = "bi bi-caret-up text-white";
				}
			}
		} else {
			triRapprochementEcritureBanque_date_ecriture = "bi bi-caret-up text-white";
		}

		if (triRapprochementEcritureBanque.equals("ENTITE")) {
			if (request.getParameter("triRapprochementEcritureBanque_entite") != null) {
				triRapprochementEcritureBanque_entite = request.getParameter("triRapprochementEcritureBanque_entite");
				request.getSession().setAttribute("triRapprochementEcritureBanque_entite", request.getParameter("triRapprochementEcritureBanque_entite"));
			} else {
				if (request.getSession().getAttribute("triRapprochementEcritureBanque_entite") != null) {
					triRapprochementEcritureBanque_entite = (String) request.getSession().getAttribute("triRapprochementEcritureBanque_entite");
				} else {
					triRapprochementEcritureBanque_entite = "bi bi-caret-up text-white";
				}
			}
		} else {
			triRapprochementEcritureBanque_entite = "bi bi-caret-up text-white";
		}

		if (triRapprochementEcritureBanque.equals("NOM_ENTITE")) {
			if (request.getParameter("triRapprochementEcritureBanque_entite") != null) {
				triRapprochementEcritureBanque_nom_entite = request.getParameter("triRapprochementEcritureBanque_nom_entite");
				request.getSession().setAttribute("triRapprochementEcritureBanque_nom_entite", request.getParameter("triRapprochementEcritureBanque_nom_entite"));
			} else {
				if (request.getSession().getAttribute("triRapprochementEcritureBanque_nom_entite") != null) {
					triRapprochementEcritureBanque_nom_entite = (String) request.getSession().getAttribute("triRapprochementEcritureBanque_nom_entite");
				} else {
					triRapprochementEcritureBanque_nom_entite = "bi bi-caret-up text-white";
				}
			}
		} else {
			triRapprochementEcritureBanque_nom_entite = "bi bi-caret-up text-white";
		}

		if (triRapprochementEcritureBanque.equals("LIBELLE_ECRITURE")) {
			if (request.getParameter("triRapprochementEcritureBanque_libelle_ecriture") != null) {
				triRapprochementEcritureBanque_libelle_ecriture = request.getParameter("triRapprochementEcritureBanque_libelle_ecriture");
				request.getSession().setAttribute("triRapprochementEcritureBanque_libelle_ecriture", request.getParameter("triRapprochementEcritureBanque_libelle_ecriture"));
			} else {
				if (request.getSession().getAttribute("triRapprochementEcritureBanque_libelle_ecriture") != null) {
					triRapprochementEcritureBanque_libelle_ecriture = (String) request.getSession().getAttribute("triRapprochementEcritureBanque_libelle_ecriture");
				} else {
					triRapprochementEcritureBanque_libelle_ecriture = "bi bi-caret-up text-white";
				}
			}
		} else {
			triRapprochementEcritureBanque_libelle_ecriture = "bi bi-caret-up text-white";
		}

		if (triRapprochementEcritureBanque.equals("SIGNE")) {
			if (request.getParameter("triRapprochementEcritureBanque_signe") != null) {
				triRapprochementEcritureBanque_signe = request.getParameter("triRapprochementEcritureBanque_signe");
				request.getSession().setAttribute("triRapprochementEcritureBanque_signe", request.getParameter("triRapprochementEcritureBanque_signe"));
			} else {
				if (request.getSession().getAttribute("triRapprochementEcritureBanque_signe") != null) {
					triRapprochementEcritureBanque_signe = (String) request.getSession().getAttribute("triRapprochementEcritureBanque_signe");
				} else {
					triRapprochementEcritureBanque_signe = "bi bi-caret-up text-white";
				}
			}
		} else {
			triRapprochementEcritureBanque_signe = "bi bi-caret-up text-white";
		}

		if (triRapprochementEcritureBanque.equals("TTC")) {
			if (request.getParameter("triRapprochementEcritureBanque_ttc") != null) {
				triRapprochementEcritureBanque_ttc = request.getParameter("triRapprochementEcritureBanque_signe");
				request.getSession().setAttribute("triRapprochementEcritureBanque_ttc", request.getParameter("triRapprochementEcritureBanque_ttc"));
			} else {
				if (request.getSession().getAttribute("triRapprochementEcritureBanque_ttc") != null) {
					triRapprochementEcritureBanque_ttc = (String) request.getSession().getAttribute("triRapprochementEcritureBanque_ttc");
				} else {
					triRapprochementEcritureBanque_ttc = "bi bi-caret-up text-white";
				}
			}
		} else {
			triRapprochementEcritureBanque_ttc = "bi bi-caret-up text-white";
		}

		if (triRapprochementEcritureBanque.equals("STATUT_RAPPROCHE")) {
			if (request.getParameter("triRapprochementEcritureBanque_statut_rapproche") != null) {
				triRapprochementEcritureBanque_statut_rapproche = request.getParameter("triRapprochementEcritureBanque_statut_rapproche");
				request.getSession().setAttribute("triRapprochementEcritureBanque_statut_rapproche", request.getParameter("triRapprochementEcritureBanque_statut_rapproche"));
			} else {
				if (request.getSession().getAttribute("triRapprochementEcritureBanque_statut_rapproche") != null) {
					triRapprochementEcritureBanque_statut_rapproche = (String) request.getSession().getAttribute("triRapprochementEcritureBanque_statut_rapproche");
				} else {
					triRapprochementEcritureBanque_statut_rapproche = "bi bi-caret-up text-white";
				}
			}
		} else {
			triRapprochementEcritureBanque_statut_rapproche = "bi bi-caret-up text-white";
		}

		if (triRapprochementEcritureBanque.equals("DATE_ECRITURE")) {

			switch (triRapprochementEcritureBanque_date_ecriture) {

			case "bi bi-caret-up text-white":
				select_triRapprochementEcritureBanque = " date_ecriture asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triRapprochementEcritureBanque = " date_ecriture desc ";
				break;

			default:
				select_triRapprochementEcritureBanque = " date_ecriture asc ";
				break;
			}
		}

		if (triRapprochementEcritureBanque.equals("ENTITE")) {

			switch (triRapprochementEcritureBanque_entite) {

			case "bi bi-caret-up text-white":
				select_triRapprochementEcritureBanque = " entite asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triRapprochementEcritureBanque = " entite desc ";
				break;

			default:
				select_triRapprochementEcritureBanque = " entite asc ";
				break;
			}
		}

		if (triRapprochementEcritureBanque.equals("NOM_ENTITE")) {

			switch (triRapprochementEcritureBanque_nom_entite) {

			case "bi bi-caret-up text-white":
				select_triRapprochementEcritureBanque = " nom_entite asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triRapprochementEcritureBanque = " nom_entite desc ";
				break;

			default:
				select_triRapprochementEcritureBanque = " nom_entite asc ";
				break;
			}
		}

		if (triRapprochementEcritureBanque.equals("LIBELLE_ECRITURE")) {

			switch (triRapprochementEcritureBanque_libelle_ecriture) {

			case "bi bi-caret-up text-white":
				select_triRapprochementEcritureBanque = " libelle_ecriture asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triRapprochementEcritureBanque = " libelle_ecriture desc ";
				break;

			default:
				select_triRapprochementEcritureBanque = " libelle_ecriture asc ";
				break;
			}
		}

		if (triRapprochementEcritureBanque.equals("SIGNE")) {

			switch (triRapprochementEcritureBanque_signe) {

			case "bi bi-caret-up text-white":
				select_triRapprochementEcritureBanque = " signe asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triRapprochementEcritureBanque = " signe desc ";
				break;

			default:
				select_triRapprochementEcritureBanque = " signe asc ";
				break;
			}
		}


		if (triRapprochementEcritureBanque.equals("TTC")) {

			switch (triRapprochementEcritureBanque_ttc) {

			case "bi bi-caret-up text-white":
				select_triRapprochementEcritureBanque = " signe asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triRapprochementEcritureBanque = " signe desc ";
				break;

			default:
				select_triRapprochementEcritureBanque = " signe asc ";
				break;
			}
		}

		if (triRapprochementEcritureBanque.equals("STATUT_RAPPROCHE")) {

			switch (triRapprochementEcritureBanque_statut_rapproche) {

			case "bi bi-caret-up text-white":
				select_triRapprochementEcritureBanque = " statut_rapproche asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triRapprochementEcritureBanque = " statut_rapproche desc ";
				break;

			default:
				select_triRapprochementEcritureBanque = " statut_rapproche asc ";
				break;
			}
		}
		resultat.put("triRapprochementEcritureBanque", triRapprochementEcritureBanque);

		if (triRapprochementEcritureBanque.equals("DATE_ECRITURE")) {
			resultat.put("triRapprochementEcritureBanque_cle", triRapprochementEcritureBanque_date_ecriture);
		} else if (triRapprochementEcritureBanque.equals("ENTITE")) {
			resultat.put("triRapprochementEcritureBanque_cle", triRapprochementEcritureBanque_entite);
		} else if (triRapprochementEcritureBanque.equals("NOM_ENTITE")) {
			resultat.put("triRapprochementEcritureBanque_cle", triRapprochementEcritureBanque_entite);
		}else if (triRapprochementEcritureBanque.equals("LIBELLE_ECRITURE")) {
			resultat.put("triRapprochementEcritureBanque_cle", triRapprochementEcritureBanque_libelle_ecriture);
		} else if (triRapprochementEcritureBanque.equals("SIGNE")) {
			resultat.put("triRapprochementEcritureBanque_cle", triRapprochementEcritureBanque_signe);
		} else if (triRapprochementEcritureBanque.equals("TTC")) {
			resultat.put("triRapprochementEcritureBanque_cle", triRapprochementEcritureBanque_ttc);
		}else if (triRapprochementEcritureBanque.equals("STATUT_RAPPROCHE")) {
			resultat.put("triRapprochementEcritureBanque_cle", triRapprochementEcritureBanque_statut_rapproche);
		}else
			resultat.put("triRapprochementEcritureBanque_cle", triRapprochementEcritureBanque_date_ecriture);


		resultat.put("select_triRapprochementEcritureBanque", select_triRapprochementEcritureBanque);

		return resultat;
	}

}
