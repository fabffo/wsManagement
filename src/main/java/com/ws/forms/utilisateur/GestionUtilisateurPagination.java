/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM UTILISATEUR  GESTION                               ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.utilisateur;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.UtilisateurDao;
import com.ws.beans.Utilisateur;

import jakarta.servlet.http.HttpServletRequest;

public final class GestionUtilisateurPagination {

	public Map<String, String> paginerUtilisateur(HttpServletRequest request) {
		String triUtilisateur;
		String triUtilisateur_id;
		String tri_cle;
		String tri_nomUtilisateur;
		String tri_libelleUtilisateur;
		String select_triUtilisateur = "id asc ";
		Map<String, String> resultat = new HashMap<String, String>();

		if (request.getParameter("triUtilisateur") != null) {
			triUtilisateur = request.getParameter("triUtilisateur");
			request.getSession().setAttribute("triUtilisateur", request.getParameter("triUtilisateur"));
		} else 
			if (request.getSession().getAttribute("triUtilisateur") != null) {
				triUtilisateur = (String) request.getSession().getAttribute("triUtilisateur");
		}else {
			triUtilisateur = "ID";
		}

		if (triUtilisateur.equals("ID")) {
			if (request.getParameter("triUtilisateur_id") != null) {
				triUtilisateur_id = request.getParameter("triUtilisateur_id");
				request.getSession().setAttribute("triUtilisateur_id", request.getParameter("triUtilisateur_id"));
			} else {
				if (request.getSession().getAttribute("triUtilisateur_id") != null) {
					triUtilisateur_id = (String) request.getSession().getAttribute("triUtilisateur_id");
				} else {
					triUtilisateur_id = "bi bi-caret-up text-white";
				}
			}
		} else {
			triUtilisateur_id = "bi bi-caret-up text-white";
		}

		if (triUtilisateur.equals("NOM")) {
			if (request.getParameter("tri_nomUtilisateur") != null) {
				tri_nomUtilisateur = request.getParameter("tri_nomUtilisateur");
				request.getSession().setAttribute("tri_nomUtilisateur", request.getParameter("tri_nomUtilisateur"));
			} else {
				if (request.getSession().getAttribute("tri_nomUtilisateur") != null) {
					tri_nomUtilisateur = (String) request.getSession().getAttribute("tri_nomUtilisateur");
				} else {
					tri_nomUtilisateur = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_nomUtilisateur = "bi bi-caret-up text-white";
		}
		
		if (triUtilisateur.equals("LIBELLE")) {
			if (request.getParameter("tri_libelleUtilisateur") != null) {
				tri_libelleUtilisateur = request.getParameter("tri_libelleUtilisateur");
				request.getSession().setAttribute("tri_libelleUtilisateur", request.getParameter("tri_libelleUtilisateur"));
			} else {
				if (request.getSession().getAttribute("tri_libelleUtilisateur") != null) {
					tri_libelleUtilisateur = (String) request.getSession().getAttribute("tri_libelleUtilisateur");
				} else {
					tri_libelleUtilisateur = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_libelleUtilisateur = "bi bi-caret-up text-white";
		}
		
		
		if (triUtilisateur.equals("ID")) {

			switch (triUtilisateur_id) {

			case "bi bi-caret-up text-white":
				select_triUtilisateur = " id asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triUtilisateur = " id desc ";
				break;

			default:
				select_triUtilisateur = " id asc ";
				break;
			}
		}
		if (triUtilisateur.equals("NOM")) {

			switch (tri_nomUtilisateur) {

			case "bi bi-caret-up text-white":
				select_triUtilisateur = " nom asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triUtilisateur = " nom desc ";
				break;

			default:
				select_triUtilisateur = " nom asc ";
				break;
			}
		}
		
		if (triUtilisateur.equals("LIBELLE")) {

			switch (tri_libelleUtilisateur) {

			case "bi bi-caret-up text-white":
				select_triUtilisateur = " libelle asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triUtilisateur = " libelle desc ";
				break;

			default:
				select_triUtilisateur = " libelle asc ";
				break;
			}
		}
		
		
		resultat.put("triUtilisateur", triUtilisateur);

		if (triUtilisateur.equals("ID")) {
			resultat.put("tri_cle", triUtilisateur_id);
		} else if (triUtilisateur.equals("NOM")) {
			resultat.put("tri_cle", tri_nomUtilisateur);
		} else if (triUtilisateur.equals("LIBELLE")) {
			resultat.put("tri_cle", tri_libelleUtilisateur);
		} else
			resultat.put("tri_cle", triUtilisateur_id);
		

		resultat.put("select_triUtilisateur", select_triUtilisateur);

		return resultat;
	}

}
