/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM SOCIETE GESTION                                    ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.societe;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.SocieteDao;
import com.ws.beans.Societe;

import jakarta.servlet.http.HttpServletRequest;

public final class GestionSocietePagination {

	public Map<String, String> paginerSociete(HttpServletRequest request) {
		String triSociete;
		String triSociete_id;
		String tri_cle;
		String tri_raison_socialeSociete;
		String tri_contact_principalSociete;
		String tri_emailSociete;
		String tri_telephoneSociete;
		String select_triSociete = "id asc ";
		Map<String, String> resultat = new HashMap<String, String>();

		if (request.getParameter("triSociete") != null) {
			triSociete = request.getParameter("triSociete");
			request.getSession().setAttribute("triSociete", request.getParameter("triSociete"));
		} else 
			if (request.getSession().getAttribute("triSociete") != null) {
				triSociete = (String) request.getSession().getAttribute("triSociete");
		}else {
			triSociete = "ID";
		}

		if (triSociete.equals("ID")) {
			if (request.getParameter("triSociete_id") != null) {
				triSociete_id = request.getParameter("triSociete_id");
				request.getSession().setAttribute("triSociete_id", request.getParameter("triSociete_id"));
			} else {
				if (request.getSession().getAttribute("triSociete_id") != null) {
					triSociete_id = (String) request.getSession().getAttribute("triSociete_id");
				} else {
					triSociete_id = "bi bi-caret-up text-white";
				}
			}
		} else {
			triSociete_id = "bi bi-caret-up text-white";
		}

		if (triSociete.equals("RAISON_SOCIALE")) {
			if (request.getParameter("tri_raison_socialeSociete") != null) {
				tri_raison_socialeSociete = request.getParameter("tri_raison_socialeSociete");
				request.getSession().setAttribute("tri_raison_socialeSociete", request.getParameter("tri_raison_socialeSociete"));
			} else {
				if (request.getSession().getAttribute("tri_raison_socialeSociete") != null) {
					tri_raison_socialeSociete = (String) request.getSession().getAttribute("tri_raison_socialeSociete");
				} else {
					tri_raison_socialeSociete = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_raison_socialeSociete = "bi bi-caret-up text-white";
		}
		
		if (triSociete.equals("CONTACT_PRINCIPAL")) {
			if (request.getParameter("tri_contact_principalSociete") != null) {
				tri_contact_principalSociete = request.getParameter("tri_contact_principalSociete");
				request.getSession().setAttribute("tri_contact_principalSociete", request.getParameter("tri_contact_principalSociete"));
			} else {
				if (request.getSession().getAttribute("tri_contact_principalSociete") != null) {
					tri_contact_principalSociete = (String) request.getSession().getAttribute("tri_contact_principalSociete");
				} else {
					tri_contact_principalSociete = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_contact_principalSociete = "bi bi-caret-up text-white";
		}
		
		if (triSociete.equals("EMAIL")) {
			if (request.getParameter("tri_emailSociete") != null) {
				tri_emailSociete = request.getParameter("tri_emailSociete");
				request.getSession().setAttribute("tri_emailSociete", request.getParameter("tri_emailSociete"));
			} else {
				if (request.getSession().getAttribute("tri_emailSociete") != null) {
					tri_emailSociete = (String) request.getSession().getAttribute("tri_emailSociete");
				} else {
					tri_emailSociete = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_emailSociete = "bi bi-caret-up text-white";
		}
		
		if (triSociete.equals("TELEPHONE")) {
			if (request.getParameter("tri_telephoneSociete") != null) {
				tri_telephoneSociete = request.getParameter("tri_telephoneSociete");
				request.getSession().setAttribute("tri_telephoneSociete", request.getParameter("tri_telephoneSociete"));
			} else {
				if (request.getSession().getAttribute("tri_telephoneSociete") != null) {
					tri_telephoneSociete = (String) request.getSession().getAttribute("tri_telephoneSociete");
				} else {
					tri_telephoneSociete = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_telephoneSociete = "bi bi-caret-up text-white";
		}
		
		if (triSociete.equals("ID")) {

			switch (triSociete_id) {

			case "bi bi-caret-up text-white":
				select_triSociete = " societe.id asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triSociete = " societe.id desc ";
				break;

			default:
				select_triSociete = " societe.id asc ";
				break;
			}
		}
		if (triSociete.equals("RAISON_SOCIALE")) {

			switch (tri_raison_socialeSociete) {

			case "bi bi-caret-up text-white":
				select_triSociete = " societe.raison_sociale asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triSociete = " societe.raison_sociale desc ";
				break;

			default:
				select_triSociete = " societe.raison_sociale asc ";
				break;
			}
		}
		
		if (triSociete.equals("CONTACT_PRINCIPAL")) {

			switch (tri_contact_principalSociete) {

			case "bi bi-caret-up text-white":
				select_triSociete = " contact.prenom asc, contact.nom  asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triSociete = " contact.prenom desc, contact.nom desc ";
				break;

			default:
				select_triSociete = " contact.prenom asc, contact.nom  asc ";
				break;
			}
		}
		
		if (triSociete.equals("EMAIL")) {

			switch (tri_emailSociete) {

			case "bi bi-caret-up text-white":
				select_triSociete = " contact.email1 asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triSociete = " contact.email1 desc ";
				break;

			default:
				select_triSociete = " contact.email1 asc ";
				break;
			}
		}
		
		if (triSociete.equals("TELEPHONE")) {

			switch (tri_telephoneSociete) {

			case "bi bi-caret-up text-white":
				select_triSociete = " contact.telephone1 asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triSociete = " contact.telephone1 desc ";
				break;

			default:
				select_triSociete = " contact.telephone1 asc ";
				break;
			}
		}
		
		
		resultat.put("triSociete", triSociete);

		if (triSociete.equals("ID")) {
			resultat.put("tri_cle", triSociete_id);
		} else if (triSociete.equals("RAISON_SOCIALE")) {
			resultat.put("tri_cle", tri_raison_socialeSociete);
		} else if (triSociete.equals("CONTACT_PRINCIPAL")) {
			resultat.put("tri_cle", tri_contact_principalSociete);
		} else if (triSociete.equals("EMAIL")) {
			resultat.put("tri_cle", tri_emailSociete);
		}else if (triSociete.equals("TELEPHONE")) {
			resultat.put("tri_cle", tri_telephoneSociete);
		}else
			resultat.put("tri_cle", triSociete_id);
		

		resultat.put("select_triSociete", select_triSociete);

		return resultat;
	}

}
