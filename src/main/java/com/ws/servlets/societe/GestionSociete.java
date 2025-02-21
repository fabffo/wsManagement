/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET SOCIETE GESTION                                 ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.societe;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.SocieteDao;
import com.ws.beans.Societe;
import com.ws.beans.TypeSociete;
import com.ws.forms.societe.CreationSocieteForm;
import com.ws.forms.societe.GestionSocietePagination;
import com.ws.forms.societe.MajSocieteForm;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GestionSociete extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private SocieteDao societeDao;
	public static final String MODIFIER = "MODIFIER";
	public static final String SUPPRIMER = "SUPPRIMER";
	public static final String AJOUTER = "AJOUTER";
	public static final String VISUALISER = "VISUALISER";

	public static final String TRIA = "TRIA";

	public static final String ATT_SOCIETE = "societe";
	public static final String ATT_FORM = "form";

	public static final String VUE_SUCCES = "/WEB-INF/JSP_societe/gestionSociete.jsp";
	public static final String VUE_FORM = "/WEB-INF/JSP_societe/ajoutSociete.jsp";

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.societeDao = daoFactory.getSocieteDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("typeSociete", request.getParameter("typeSociete"));
		System.out.println("typesociete"+request.getParameter("typeSociete"));
		int currentPage = 1; // page en cours*
		int recordsPerPage = 6; // nbr enregistrements par page
		int maxPage = 0; // fin pagination
		String triSociete;
		String triSociete_id;
		String select_triSociete = "";
		String tri_raison_socialeSociete="bi bi-caret-up text-white";
		String tri_contact_principalSociete="bi bi-caret-up text-white";
		String tri_emailSociete="bi bi-caret-up text-white";
		String tri_telephoneSociete="bi bi-caret-up text-white";
		String select_like;
		String searchSociete = "";
		String critereSociete = "";
		List<Societe> list;
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
		GestionSocietePagination form = new GestionSocietePagination();
		/* Traitement de la requête et récupération du bean en résultant */
		Map<String, String> pagination =  form.paginerSociete( request );

		triSociete=pagination.get("triSociete");

		if (triSociete.equals("ID")) {
			triSociete_id=pagination.get("tri_cle");
		} else {
			triSociete_id ="bi bi-caret-down text-white";
		}
		if (triSociete.equals("RAISON_SOCIALE")) {
			tri_raison_socialeSociete=pagination.get("tri_cle");
		} else {
			tri_raison_socialeSociete ="bi bi-caret-down text-white";
		}
		if (triSociete.equals("CONTACT_PRINCIPAL")) {
			tri_contact_principalSociete=pagination.get("tri_cle");
		} else {
			tri_contact_principalSociete ="bi bi-caret-down text-white";
		}
		if (triSociete.equals("EMAIL")) {
			tri_emailSociete=pagination.get("tri_cle");
		} else {
			tri_emailSociete ="bi bi-caret-down text-white";
		}
		if (triSociete.equals("TELEPHONE")) {
			tri_telephoneSociete=pagination.get("tri_cle");
		} else {
			tri_telephoneSociete ="bi bi-caret-down text-white";
		}
		select_triSociete = pagination.get("select_triSociete");

		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} else {
			if (request.getSession().getAttribute("currentPage") != null)
				currentPage = Integer.parseInt((String) request.getSession().getAttribute("currentPage"));

		}

		// récupération  recherche de la page
		System.out.print("début rechereche");
		if (request.getParameter("searchSociete") != null) {
			if (request.getParameter("searchSociete").equals("")) {
				list = societeDao.rechercheSocietes((currentPage - 1) * recordsPerPage, recordsPerPage,
						select_triSociete, typeSociete);
				request.getSession().setAttribute("searchSociete", "");
				select_like = "";
			} else {
				request.getSession().setAttribute("searchSociete", request.getParameter("searchSociete"));
				request.getSession().setAttribute("critereSociete", request.getParameter("critereSociete"));
				critereSociete = request.getParameter("critereSociete");
				searchSociete = request.getParameter("searchSociete");
				select_like=critereSociete + " like '" + searchSociete + "%'";
				list = societeDao.rechercheLikeSocietes((currentPage - 1) * recordsPerPage, recordsPerPage,
						select_triSociete, select_like, typeSociete);
			}
			System.out.print("rechereche parametre non blanc:"+select_like);
		}

		else {
			if (request.getSession().getAttribute("searchSociete") != null) {
				if (request.getSession().getAttribute("searchSociete").equals("")) {
					list = societeDao.rechercheSocietes((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triSociete, typeSociete);
					select_like = "";
				} else {
					searchSociete = (String) request.getSession().getAttribute("searchSociete");
					critereSociete = (String) request.getSession().getAttribute("critereSociete");
					select_like=critereSociete + " like '" + searchSociete + "%'";
					list = societeDao.rechercheLikeSocietes((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triSociete, select_like, typeSociete);
				}
			} else {
				select_like = "";
				list = societeDao.rechercheSocietes((currentPage - 1) * recordsPerPage, recordsPerPage,
						select_triSociete, typeSociete);
			}
			System.out.print("rechereche parametre session non blanc:"+select_like);
		}

		int noOfRecords = societeDao.getNoOfRecords(); // raison_socialebre enregistrements total

		//on inverse le triSociete pour permettre le changement lors de l'appui sur la flèche
		if (triSociete_id.equals("bi bi-caret-up text-white")) {
			request.setAttribute("triSociete_id", "bi bi-caret-down text-white");
		} else {
			request.setAttribute("triSociete_id", "bi bi-caret-up text-white");
		}
		if (tri_raison_socialeSociete.equals("bi bi-caret-up text-white")) {
			request.setAttribute("tri_raison_socialeSociete", "bi bi-caret-down text-white");
		} else {
			request.setAttribute("tri_raison_socialeSociete", "bi bi-caret-up text-white");
		}
		if (tri_contact_principalSociete.equals("bi bi-caret-up text-white")) {
			request.setAttribute("tri_contact_principalSociete", "bi bi-caret-down text-white");
		} else {
			request.setAttribute("tri_contact_principalSociete", "bi bi-caret-up text-white");
		}
		if (tri_emailSociete.equals("bi bi-caret-up text-white")) {
			request.setAttribute("tri_emailSociete", "bi bi-caret-down text-white");
		} else {
			request.setAttribute("tri_emailSociete", "bi bi-caret-up text-white");
		}
		if (tri_telephoneSociete.equals("bi bi-caret-up text-white")) {
			request.setAttribute("tri_telephoneSociete", "bi bi-caret-down text-white");
		} else {
			request.setAttribute("tri_telephoneSociete", "bi bi-caret-up text-white");
		}
		// raison_socialebre d'enregistrements pour une page
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

		int noBegin = 1; // début pagination
		int pasPage = 2; // pas de la pagination

		if (maxPage == 0)
			maxPage = pasPage; // fin pagination

		if (request.getParameter("noBegin") != null)
			noBegin = Integer.parseInt((String) request.getParameter("noBegin"));
		if (request.getParameter("maxPage") != null)
			maxPage = Integer.parseInt((String) request.getParameter("maxPage"));


		request.setAttribute("SocieteList", list); // liste enregistrements
		request.setAttribute("noOfPages", noOfPages); // paramètre raison_socialebres de pages
		request.setAttribute("currentPage", currentPage); // paramètre page courante
		request.setAttribute("noBegin", noBegin); // paramètre début pagination
		request.setAttribute("maxPage", maxPage); // paramètre fin pagination
		request.setAttribute("pasPage", pasPage); // paramètre pas de la pagination
		request.setAttribute("searchSociete", searchSociete); // paramètre searchSociete

		if (critereSociete.equals("societe.raison_sociale")){
			request.getSession().setAttribute("sel_raison_sociale", "selected");
			request.getSession().setAttribute("sel_contact_principal", "");
			request.getSession().setAttribute("sel_email", "");
			request.getSession().setAttribute("sel_telephone", "");
		}else
			if (critereSociete.equals("societe.contact_principal")){
				request.getSession().setAttribute("sel_contact_principal", "selected");
				request.getSession().setAttribute("sel_raison_sociale", "");
				request.getSession().setAttribute("sel_email", "");
				request.getSession().setAttribute("sel_telephone", "");
			}else
				if (critereSociete.equals("email.raison_sociale")){
					request.getSession().setAttribute("sel_email", "selected");
					request.getSession().setAttribute("sel_raison_sociale", "");
					request.getSession().setAttribute("sel_contact_principal", "");
					request.getSession().setAttribute("sel_telephone", "");
				}
				else if (critereSociete.equals("societe.telephone")){
					request.getSession().setAttribute("sel_telephone", "selected");
					request.getSession().setAttribute("sel_raison_sociale", "");
					request.getSession().setAttribute("sel_contact_principal", "");
					request.getSession().setAttribute("sel_email", "");
				}
				else {
					request.getSession().setAttribute("sel_contact_principal", "selected");
					request.getSession().setAttribute("sel_raison_sociale", "");
					request.getSession().setAttribute("sel_email", "");
					request.getSession().setAttribute("sel_telephone", "");
				}

		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_societe/gestionSociete.jsp")
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
