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

public final class VisualisationEcritureBanque_importPagination {

	public Map<String, String> paginerVisualisationEcritureBanque_import(HttpServletRequest request) {
		String triVisualisationEcritureBanque_import;
		String triVisualisationEcritureBanque_import_cle;
		String triVisualisationEcritureBanque_numero_ligne;
		String triVisualisationEcritureBanque_date_ecriture;
		String triVisualisationEcritureBanque_libelle_ecriture;
		String triVisualisationEcritureBanque_debit;
		String triVisualisationEcritureBanque_credit;
		String select_triVisualisationEcritureBanque_import = "date_ecriture asc ";
		Map<String, String> resultat = new HashMap<String, String>();

		if (request.getParameter("triVisualisationEcritureBanque_import") != null) {
			triVisualisationEcritureBanque_import = request.getParameter("triVisualisationEcritureBanque_import");
			request.getSession().setAttribute("triVisualisationEcritureBanque_import", request.getParameter("triVisualisationEcritureBanque_import"));
		} else
			if (request.getSession().getAttribute("triVisualisationEcritureBanque_import") != null) {
				triVisualisationEcritureBanque_import = (String) request.getSession().getAttribute("triVisualisationEcritureBanque_import");
		}else {
			triVisualisationEcritureBanque_import = "DATE_ECRITURE";
		}

		if (triVisualisationEcritureBanque_import.equals("DATE_ECRITURE")) {
			if (request.getParameter("triVisualisationEcritureBanque_date_ecriture") != null) {
				triVisualisationEcritureBanque_date_ecriture = request.getParameter("triVisualisationEcritureBanque_date_ecriture");
				request.getSession().setAttribute("triVisualisationEcritureBanque_date_ecriture", request.getParameter("triVisualisationEcritureBanque_date_ecriture"));
			} else {
				if (request.getSession().getAttribute("triVisualisationEcritureBanque_date_ecriture") != null) {
					triVisualisationEcritureBanque_date_ecriture = (String) request.getSession().getAttribute("triVisualisationEcritureBanque_date_ecriture");
				} else {
					triVisualisationEcritureBanque_date_ecriture = "bi bi-caret-up text-white";
				}
			}
		} else {
			triVisualisationEcritureBanque_date_ecriture = "bi bi-caret-up text-white";
		}

		if (triVisualisationEcritureBanque_import.equals("NUMERO_LIGNE")) {
			if (request.getParameter("triVisualisationEcritureBanque_numero_ligne") != null) {
				triVisualisationEcritureBanque_numero_ligne = request.getParameter("triVisualisationEcritureBanque_numero_ligne");
				request.getSession().setAttribute("triVisualisationEcritureBanque_numero_ligne", request.getParameter("triVisualisationEcritureBanque_numero_ligne"));
			} else {
				if (request.getSession().getAttribute("triVisualisationEcritureBanque_numero_ligne") != null) {
					triVisualisationEcritureBanque_numero_ligne = (String) request.getSession().getAttribute("triVisualisationEcritureBanque_numero_ligne");
				} else {
					triVisualisationEcritureBanque_numero_ligne = "bi bi-caret-up text-white";
				}
			}
		} else {
			triVisualisationEcritureBanque_numero_ligne = "bi bi-caret-up text-white";
		}

		if (triVisualisationEcritureBanque_import.equals("LIBELLE_ECRITURE")) {
			if (request.getParameter("triVisualisationEcritureBanque_libelle_ecriture") != null) {
				triVisualisationEcritureBanque_libelle_ecriture = request.getParameter("triVisualisationEcritureBanque_libelle_ecriture");
				request.getSession().setAttribute("triVisualisationEcritureBanque_libelle_ecriture", request.getParameter("triVisualisationEcritureBanque_libelle_ecriture"));
			} else {
				if (request.getSession().getAttribute("triVisualisationEcritureBanque_libelle_ecriture") != null) {
					triVisualisationEcritureBanque_libelle_ecriture = (String) request.getSession().getAttribute("triVisualisationEcritureBanque_libelle_ecriture");
				} else {
					triVisualisationEcritureBanque_libelle_ecriture = "bi bi-caret-up text-white";
				}
			}
		} else {
			triVisualisationEcritureBanque_libelle_ecriture = "bi bi-caret-up text-white";
		}

		if (triVisualisationEcritureBanque_import.equals("DEBIT")) {
			if (request.getParameter("triVisualisationEcritureBanque_debit") != null) {
				triVisualisationEcritureBanque_debit = request.getParameter("triVisualisationEcritureBanque_debit");
				request.getSession().setAttribute("triVisualisationEcritureBanque_debit", request.getParameter("triVisualisationEcritureBanque_debit"));
			} else {
				if (request.getSession().getAttribute("triVisualisationEcritureBanque_debit") != null) {
					triVisualisationEcritureBanque_debit = (String) request.getSession().getAttribute("triVisualisationEcritureBanque_debit");
				} else {
					triVisualisationEcritureBanque_debit = "bi bi-caret-up text-white";
				}
			}
		} else {
			triVisualisationEcritureBanque_debit = "bi bi-caret-up text-white";
		}

		if (triVisualisationEcritureBanque_import.equals("CREDIT")) {
			if (request.getParameter("triVisualisationEcritureBanque_credit") != null) {
				triVisualisationEcritureBanque_credit = request.getParameter("triVisualisationEcritureBanque_credit");
				request.getSession().setAttribute("triVisualisationEcritureBanque_credit", request.getParameter("triVisualisationEcritureBanque_credit"));
			} else {
				if (request.getSession().getAttribute("triVisualisationEcritureBanque_credit") != null) {
					triVisualisationEcritureBanque_credit = (String) request.getSession().getAttribute("triVisualisationEcritureBanque_debit");
				} else {
					triVisualisationEcritureBanque_credit = "bi bi-caret-up text-white";
				}
			}
		} else {
			triVisualisationEcritureBanque_credit = "bi bi-caret-up text-white";
		}

		if (triVisualisationEcritureBanque_import.equals("DATE_ECRITURE")) {

			switch (triVisualisationEcritureBanque_date_ecriture) {

			case "bi bi-caret-up text-white":
				select_triVisualisationEcritureBanque_import = " date_ecriture asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triVisualisationEcritureBanque_import = " date_ecriture desc ";
				break;

			default:
				select_triVisualisationEcritureBanque_import = " date_ecriture asc ";
				break;
			}
		}
		if (triVisualisationEcritureBanque_import.equals("NUMERO_LIGNE")) {

			switch (triVisualisationEcritureBanque_numero_ligne) {

			case "bi bi-caret-up text-white":
				select_triVisualisationEcritureBanque_import = " numero_ligne asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triVisualisationEcritureBanque_import = " numero_ligne desc ";
				break;

			default:
				select_triVisualisationEcritureBanque_import = " numero_ligne asc ";
				break;
			}
		}

		if (triVisualisationEcritureBanque_import.equals("LIBELLE_ECRITURE")) {

			switch (triVisualisationEcritureBanque_libelle_ecriture) {

			case "bi bi-caret-up text-white":
				select_triVisualisationEcritureBanque_import = " libelle_ecriture asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triVisualisationEcritureBanque_import = " libelle_ecriture desc ";
				break;

			default:
				select_triVisualisationEcritureBanque_import = " libelle_ecriture asc ";
				break;
			}
		}

		if (triVisualisationEcritureBanque_import.equals("DEBIT")) {

			switch (triVisualisationEcritureBanque_debit) {

			case "bi bi-caret-up text-white":
				select_triVisualisationEcritureBanque_import = " debit asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triVisualisationEcritureBanque_import = " debit desc ";
				break;

			default:
				select_triVisualisationEcritureBanque_import = " debit asc ";
				break;
			}
		}

		if (triVisualisationEcritureBanque_import.equals("CREDIT")) {

			switch (triVisualisationEcritureBanque_credit) {

			case "bi bi-caret-up text-white":
				select_triVisualisationEcritureBanque_import = " credit asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triVisualisationEcritureBanque_import = " credit desc ";
				break;

			default:
				select_triVisualisationEcritureBanque_import = " credit asc ";
				break;
			}
		}

		resultat.put("triVisualisationEcritureBanque_import", triVisualisationEcritureBanque_import);

		if (triVisualisationEcritureBanque_import.equals("DATE_ECRITURE")) {
			resultat.put("triVisualisationEcritureBanque_import_cle", triVisualisationEcritureBanque_date_ecriture);
		} else if (triVisualisationEcritureBanque_import.equals("NUMERO_LIGNE")) {
			resultat.put("triVisualisationEcritureBanque_import_cle", triVisualisationEcritureBanque_numero_ligne);
		} else if (triVisualisationEcritureBanque_import.equals("LIBELLE_ECRITURE")) {
			resultat.put("triVisualisationEcritureBanque_import_cle", triVisualisationEcritureBanque_libelle_ecriture);
		} else if (triVisualisationEcritureBanque_import.equals("DEBIT")) {
			resultat.put("triVisualisationEcritureBanque_import_cle", triVisualisationEcritureBanque_debit);
		} else if (triVisualisationEcritureBanque_import.equals("CREDIT")) {
			resultat.put("triVisualisationEcritureBanque_import_cle", triVisualisationEcritureBanque_credit);
		}else
			resultat.put("triVisualisationEcritureBanque_import_cle", triVisualisationEcritureBanque_date_ecriture);


		resultat.put("select_triVisualisationEcritureBanque_import", select_triVisualisationEcritureBanque_import);

		return resultat;
	}

}
