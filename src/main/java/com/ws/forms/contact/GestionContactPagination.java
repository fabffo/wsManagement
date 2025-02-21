/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM CONTACT GESTION                                    ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.contact;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.ContactDao;
import com.ws.beans.Contact;

import jakarta.servlet.http.HttpServletRequest;

public final class GestionContactPagination {

	public Map<String, String> paginerContact(HttpServletRequest request) {
		String triContact;
		String tri_prenomContact;
		String tri_cleContact;
		String tri_nomContact;
		String tri_emailContact;
		String tri_telephoneContact;
		String tri_societeContact;
		String select_triContact = "contact.id asc ";
		Map<String, String> resultat = new HashMap<String, String>();

		if (request.getParameter("triContact") != null) {
			triContact = request.getParameter("triContact");
			request.getSession().setAttribute("triContact", request.getParameter("triContact"));
		} else 
			if (request.getSession().getAttribute("triContact") != null) {
				triContact = (String) request.getSession().getAttribute("triContact");
		}else {
			triContact = "PRENOM";
		}

		if (triContact.equals("PRENOM")) {
			if (request.getParameter("tri_prenomContact") != null) {
				tri_prenomContact = request.getParameter("tri_prenomContact");
				request.getSession().setAttribute("tri_prenomContact", request.getParameter("tri_prenomContact"));
			} else {
				if (request.getSession().getAttribute("tri_prenomContact") != null) {
					tri_prenomContact = (String) request.getSession().getAttribute("tri_prenomContact");
				} else {
					tri_prenomContact = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_prenomContact = "bi bi-caret-up text-white";
		}

		if (triContact.equals("NOM")) {
			if (request.getParameter("tri_nomContact") != null) {
				tri_nomContact = request.getParameter("tri_nomContact");
				request.getSession().setAttribute("tri_nomContact", request.getParameter("tri_nomContact"));
			} else {
				if (request.getSession().getAttribute("tri_nomContact") != null) {
					tri_nomContact = (String) request.getSession().getAttribute("tri_nomContact");
				} else {
					tri_nomContact = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_nomContact = "bi bi-caret-up text-white";
		}
		
		if (triContact.equals("EMAIL")) {
			if (request.getParameter("tri_emailContact") != null) {
				tri_emailContact = request.getParameter("tri_emailContact");
				request.getSession().setAttribute("tri_emailContact", request.getParameter("tri_emailContact"));
			} else {
				if (request.getSession().getAttribute("tri_emailContact") != null) {
					tri_emailContact = (String) request.getSession().getAttribute("tri_emailContact");
				} else {
					tri_emailContact = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_emailContact = "bi bi-caret-up text-white";
		}
		
		if (triContact.equals("TELEPHONE")) {
			if (request.getParameter("tri_telephoneContact") != null) {
				tri_telephoneContact = request.getParameter("tri_telephoneContact");
				request.getSession().setAttribute("tri_telephoneContact", request.getParameter("tri_telephoneContact"));
			} else {
				if (request.getSession().getAttribute("tri_telephoneContact") != null) {
					tri_telephoneContact = (String) request.getSession().getAttribute("tri_telephoneContact");
				} else {
					tri_telephoneContact = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_telephoneContact = "bi bi-caret-up text-white";
		}
		
		if (triContact.equals("SOCIETE")) {
			if (request.getParameter("tri_societeContact") != null) {
				tri_societeContact = request.getParameter("tri_societeContact");
				request.getSession().setAttribute("tri_societeContact", request.getParameter("tri_societeContact"));
			} else {
				if (request.getSession().getAttribute("tri_societeContact") != null) {
					tri_societeContact = (String) request.getSession().getAttribute("tri_societeContact");
				} else {
					tri_societeContact = "bi bi-caret-up text-white";
				}
			}
		} else {
			tri_societeContact = "bi bi-caret-up text-white";
		}
		
		if (triContact.equals("PRENOM")) {

			switch (tri_prenomContact) {

			case "bi bi-caret-up text-white":
				select_triContact = " contact.prenom asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triContact = " contact.prenom desc ";
				break;

			default:
				select_triContact = " contact.prenom asc ";
				break;
			}
		}
		if (triContact.equals("NOM")) {

			switch (tri_nomContact) {

			case "bi bi-caret-up text-white":
				select_triContact = " contact.nom asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triContact = " contact.nom desc ";
				break;

			default:
				select_triContact = " contact.nom asc ";
				break;
			}
		}
		
		if (triContact.equals("EMAIL")) {

			switch (tri_emailContact) {

			case "bi bi-caret-up text-white":
				select_triContact = " contact.email1 asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triContact = " contact.email1 desc ";
				break;

			default:
				select_triContact = " contact.email1 asc ";
				break;
			}
		}
		
		if (triContact.equals("TELEPHONE")) {

			switch (tri_telephoneContact) {

			case "bi bi-caret-up text-white":
				select_triContact = " contact.telephone1 asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triContact = " contact.telephone1 desc ";
				break;

			default:
				select_triContact = " contact.telephone1 asc ";
				break;
			}
		}
		
		if (triContact.equals("SOCIETE")) {

			switch (tri_societeContact) {

			case "bi bi-caret-up text-white":
				select_triContact = " societe.raison_sociale asc ";
				break;

			case "bi bi-caret-down text-white":
				select_triContact = " societe.raison_sociale desc ";
				break;

			default:
				select_triContact = " societe.raison_sociale asc ";
				break;
			}
		}
		
		
		resultat.put("triContact", triContact);

		if (triContact.equals("PRENOM")) {
			resultat.put("tri_cleContact", tri_prenomContact);
		} else if (triContact.equals("NOM")) {
			resultat.put("tri_cleContact", tri_nomContact);
		} else if (triContact.equals("EMAIL")) {
			resultat.put("tri_cleContact", tri_emailContact);
		} else if (triContact.equals("TELEPHONE")) {
			resultat.put("tri_cleContact", tri_telephoneContact);
		}else if (triContact.equals("SOCIETE")) {
			resultat.put("tri_cleContact", tri_societeContact);
		}else
			resultat.put("tri_cleContact", tri_prenomContact);
		

		resultat.put("select_triContact", select_triContact);

		return resultat;
	}

}
