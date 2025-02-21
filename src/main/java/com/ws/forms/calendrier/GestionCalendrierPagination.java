/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM TYPE CONTRAT GESTION                               ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.calendrier;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypeContratCollaborateurDao;
import com.ws.beans.TypeContratCollaborateur;

import jakarta.servlet.http.HttpServletRequest;

public final class GestionCalendrierPagination {

	public Map<String, String> paginerTypeContrat(HttpServletRequest request) {
		String tri;
		String tri_id;
		String tri_cle;
		String tri_nom;
		String tri_libelle;
		String tri_statut;
		String select_tri = "id asc ";
		Map<String, String> resultat = new HashMap<String, String>();

		if (request.getParameter("tri") != null) {
			tri = request.getParameter("tri");
			request.getSession().setAttribute("tri", request.getParameter("tri"));
		} else 
			if (request.getSession().getAttribute("tri") != null) {
				tri = (String) request.getSession().getAttribute("tri");
		}else {
			tri = "ID";
		}

		if (tri.equals("ID")) {
			if (request.getParameter("tri_id") != null) {
				tri_id = request.getParameter("tri_id");
				request.getSession().setAttribute("tri_id", request.getParameter("tri_id"));
			} else {
				if (request.getSession().getAttribute("tri_id") != null) {
					tri_id = (String) request.getSession().getAttribute("tri_id");
				} else {
					tri_id = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_id = "bi bi-caret-up text-white";
		}

		if (tri.equals("NOM")) {
			if (request.getParameter("tri_nom") != null) {
				tri_nom = request.getParameter("tri_nom");
				request.getSession().setAttribute("tri_nom", request.getParameter("tri_nom"));
			} else {
				if (request.getSession().getAttribute("tri_nom") != null) {
					tri_nom = (String) request.getSession().getAttribute("tri_nom");
				} else {
					tri_nom = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_nom = "bi bi-caret-up text-white";
		}
		
		if (tri.equals("LIBELLE")) {
			if (request.getParameter("tri_libelle") != null) {
				tri_libelle = request.getParameter("tri_libelle");
				request.getSession().setAttribute("tri_libelle", request.getParameter("tri_libelle"));
			} else {
				if (request.getSession().getAttribute("tri_libelle") != null) {
					tri_libelle = (String) request.getSession().getAttribute("tri_libelle");
				} else {
					tri_libelle = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_libelle = "bi bi-caret-up text-white";
		}
		
		if (tri.equals("STATUT")) {
			if (request.getParameter("tri_statut") != null) {
				tri_statut = request.getParameter("tri_statut");
				request.getSession().setAttribute("tri_statut", request.getParameter("tri_statut"));
			} else {
				if (request.getSession().getAttribute("tri_statut") != null) {
					tri_statut = (String) request.getSession().getAttribute("tri_statut");
				} else {
					tri_statut = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_statut = "bi bi-caret-up text-white";
		}
		
		
		if (tri.equals("ID")) {

			switch (tri_id) {

			case "bi bi-caret-up text-white":
				select_tri = " id asc ";
				break;

			case "bi bi-caret-down text-white":
				select_tri = " id desc ";
				break;

			default:
				select_tri = " id asc ";
				break;
			}
		}
		if (tri.equals("NOM")) {

			switch (tri_nom) {

			case "bi bi-caret-up text-white":
				select_tri = " nom asc ";
				break;

			case "bi bi-caret-down text-white":
				select_tri = " nom desc ";
				break;

			default:
				select_tri = " nom asc ";
				break;
			}
		}
		
		if (tri.equals("LIBELLE")) {

			switch (tri_libelle) {

			case "bi bi-caret-up text-white":
				select_tri = " libelle asc ";
				break;

			case "bi bi-caret-down text-white":
				select_tri = " libelle desc ";
				break;

			default:
				select_tri = " libelle asc ";
				break;
			}
		}
		
		if (tri.equals("STATUT")) {

			switch (tri_statut) {

			case "bi bi-caret-up text-white":
				select_tri = " statut asc ";
				break;

			case "bi bi-caret-down text-white":
				select_tri = " statut desc ";
				break;

			default:
				select_tri = " statut asc ";
				break;
			}
		}
		
		
		resultat.put("tri", tri);

		if (tri.equals("ID")) {
			resultat.put("tri_cle", tri_id);
		} else if (tri.equals("NOM")) {
			resultat.put("tri_cle", tri_nom);
		} else if (tri.equals("LIBELLE")) {
			resultat.put("tri_cle", tri_libelle);
		} else if (tri.equals("STATUT")) {
			resultat.put("tri_cle", tri_statut);
		}else
			resultat.put("tri_cle", tri_id);
		

		resultat.put("select_tri", select_tri);

		return resultat;
	}

}
