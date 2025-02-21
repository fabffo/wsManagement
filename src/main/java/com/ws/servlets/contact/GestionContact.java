/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET COLLABORATEUR GESTION                           ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.contact;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.ContactDao;
import com.ws.beans.Contact;
import com.ws.beans.TypeSociete;
import com.ws.forms.contact.CreationContactForm;
import com.ws.forms.contact.GestionContactPagination;
import com.ws.forms.contact.MajContactForm;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GestionContact extends HttpServlet {

	private static final long serialVersionUPRENOM = 1L;
	private ContactDao contactDao; 
	public static final String MODIFIER = "MODIFIER";
	public static final String SUPPRIMER = "SUPPRIMER";
	public static final String AJOUTER = "AJOUTER";
	public static final String VISUALISER = "VISUALISER";

	public static final String TRIA = "TRIA";

	public static final String ATT_CONTACT = "contact";
	public static final String ATT_FORM = "form";

	public static final String VUE_SUCCES = "/WEB-INF/JSP_contact/gestionContact.jsp";
	public static final String VUE_FORM = "/WEB-INF/JSP_contact/ajoutContact.jsp";

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.contactDao = daoFactory.getContactDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1; // page en cours*
		int recordsPerPage = 6; // nbr enregistrements par page
		int maxPage = 0; // fin pagination
		String triContact;
		String tri_prenomContact;
		String select_triContact = "";
		String tri_nomContact="bi bi-caret-up text-white";
		String tri_emailContact="bi bi-caret-up text-white";
		String tri_telephoneContact="bi bi-caret-up text-white";
		String tri_societeContact="bi bi-caret-up text-white";
		String select_like;
		String searchContact = "";
		String critereContact = "";
		List<Contact> list;
		String debutsession ="debutsession";

		//gestion du paramètre typeSocieté pour filter les contacts
		if (request.getParameter("typeSociete")!= null){
		request.getSession().setAttribute("typeSociete", request.getParameter("typeSociete"));
		} else {
			if (request.getSession().getAttribute("typeSociete")== null){
			//valeur par défaut
			request.getSession().setAttribute("typeSociete", "SOCIETE");
		}
		}
		String typeSociete = (String) request.getSession().getAttribute("typeSociete");
		
		/* Préparation de l'objet pagination */
		GestionContactPagination form = new GestionContactPagination();
		/* Traitement de la requête et récupération du bean en résultant */
		Map<String, String> pagination =  form.paginerContact( request );
		
		triContact=pagination.get("triContact");
		
		if (triContact.equals("PRENOM")) {
			tri_prenomContact=pagination.get("tri_cleContact");	
		} else {
			tri_prenomContact ="bi bi-caret-down text-white";
		}
		if (triContact.equals("NOM")) {
			tri_nomContact=pagination.get("tri_cleContact");	
		} else {
			tri_nomContact ="bi bi-caret-down text-white";
		}
		if (triContact.equals("EMAIL")) {
			tri_emailContact=pagination.get("tri_cleContact");	
		} else {
			tri_emailContact ="bi bi-caret-down text-white";
		}
		if (triContact.equals("TELEPHONE")) {
			tri_telephoneContact=pagination.get("tri_cleContact");	
		} else {
			tri_telephoneContact ="bi bi-caret-down text-white";
		}
		if (triContact.equals("SOCIETE")) {
			tri_societeContact=pagination.get("tri_cleContact");	
		} else {
			tri_societeContact ="bi bi-caret-down text-white";
		}
		select_triContact = pagination.get("select_triContact");
		
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} else {
			if (request.getSession().getAttribute("currentPage") != null)
				currentPage = Integer.parseInt((String) request.getSession().getAttribute("currentPage"));

		}

		// récupération  recherche de la page
		System.out.print("début rechereche");
		if (request.getParameter("searchContact") != null) {
			if (request.getParameter("searchContact").equals("")) {
				list = contactDao.rechercheContacts((currentPage - 1) * recordsPerPage, recordsPerPage,
						select_triContact, typeSociete);
				request.getSession().setAttribute("searchContact", "");
				select_like = "";
			} else {
				request.getSession().setAttribute("searchContact", request.getParameter("searchContact"));
				request.getSession().setAttribute("critereContact", request.getParameter("critereContact"));
				critereContact = request.getParameter("critereContact");
				searchContact = request.getParameter("searchContact");
				select_like=critereContact + " like '" + searchContact + "%'";
				list = contactDao.rechercheLikeContacts((currentPage - 1) * recordsPerPage, recordsPerPage,
						select_triContact, select_like, typeSociete);
			}
			System.out.print("rechereche parametre non blanc:"+select_like);
		}

		else {
			if (request.getSession().getAttribute("searchContact") != null) {
				if (request.getSession().getAttribute("searchContact").equals("")) {
					list = contactDao.rechercheContacts((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triContact, typeSociete);
					select_like = "";
				} else {
					searchContact = (String) request.getSession().getAttribute("searchContact");
					critereContact = (String) request.getSession().getAttribute("critereContact");
					select_like=critereContact + " like '" + searchContact + "%'";
					list = contactDao.rechercheLikeContacts((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triContact, select_like, typeSociete);
				}
			} else {
				select_like = "";
				list = contactDao.rechercheContacts((currentPage - 1) * recordsPerPage, recordsPerPage,
						select_triContact, typeSociete);
			}
			System.out.print("rechereche parametre session non blanc:"+select_like);
		}
	
		int noOfRecords = contactDao.getNoOfRecords(); // nombre enregistrements total
		
		//on inverse le triContact pour permettre le changement lors de l'appui sur la flèche
			if (tri_prenomContact.equals("bi bi-caret-up text-white")) {
				request.setAttribute("tri_prenomContact", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("tri_prenomContact", "bi bi-caret-up text-white");
			}
			if (tri_nomContact.equals("bi bi-caret-up text-white")) {
				request.setAttribute("tri_nomContact", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("tri_nomContact", "bi bi-caret-up text-white");
			}
			if (tri_emailContact.equals("bi bi-caret-up text-white")) {
				request.setAttribute("tri_emailContact", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("tri_emailContact", "bi bi-caret-up text-white");
			}
			if (tri_telephoneContact.equals("bi bi-caret-up text-white")) {
				request.setAttribute("tri_telephoneContact", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("tri_telephoneContact", "bi bi-caret-up text-white");
			}
			if (tri_societeContact.equals("bi bi-caret-up text-white")) {
				request.setAttribute("tri_societeContact", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("tri_societeContact", "bi bi-caret-up text-white");
			}
		// nombre d'enregistrements pour une page
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

		int noBegin = 1; // début pagination
		int pasPage = 2; // pas de la pagination

		if (maxPage == 0)
			maxPage = pasPage; // fin pagination

		if (request.getParameter("noBegin") != null)
			noBegin = Integer.parseInt((String) request.getParameter("noBegin"));
		if (request.getParameter("maxPage") != null)
			maxPage = Integer.parseInt((String) request.getParameter("maxPage"));
		

		request.setAttribute("ContactList", list); // liste enregistrements
		request.setAttribute("noOfPages", noOfPages); // paramètre nombres de pages
		request.setAttribute("currentPage", currentPage); // paramètre page courante
		request.setAttribute("noBegin", noBegin); // paramètre début pagination
		request.setAttribute("maxPage", maxPage); // paramètre fin pagination
		request.setAttribute("pasPage", pasPage); // paramètre pas de la pagination
		request.setAttribute("searchContact", searchContact); // paramètre searchContact
		
		if (critereContact.equals("contact.nom")){
			request.getSession().setAttribute("sel_nom", "selected");
			request.getSession().setAttribute("sel_email", "");
			request.getSession().setAttribute("sel_telephone", "");
			request.getSession().setAttribute("sel_societe", "");
		}else 
			if (critereContact.equals("contact.email")){
				request.getSession().setAttribute("sel_email", "selected");	
				request.getSession().setAttribute("sel_nom", "");
				request.getSession().setAttribute("sel_telephone", "");
				request.getSession().setAttribute("sel_societe", "");
			}else 
				if (critereContact.equals("telephone.nom")){
					request.getSession().setAttribute("sel_telephone", "selected");
					request.getSession().setAttribute("sel_nom", "");
					request.getSession().setAttribute("sel_email", "");
					request.getSession().setAttribute("sel_societe", "");
				}
			else if (critereContact.equals("contact.societe")){
				request.getSession().setAttribute("sel_societe", "selected");
				request.getSession().setAttribute("sel_nom", "");
				request.getSession().setAttribute("sel_email", "");
				request.getSession().setAttribute("sel_telephone", "");
			}
		else {
				request.getSession().setAttribute("sel_email", "selected");	
				request.getSession().setAttribute("sel_nom", "");
				request.getSession().setAttribute("sel_telephone_principal", "");
				request.getSession().setAttribute("sel_societe", "");
			}

		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_contact/gestionContact.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
