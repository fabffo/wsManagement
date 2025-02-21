/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET COLLABORATEUR GESTION                           ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.collaborateur;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.CollaborateurDao;
import com.ws.beans.Collaborateur;
import com.ws.forms.collaborateur.CreationCollaborateurForm;
import com.ws.forms.collaborateur.GestionCollaborateurPagination;
import com.ws.forms.collaborateur.MajCollaborateurForm;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GestionCollaborateur extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CollaborateurDao collaborateurDao;
	public static final String MODIFIER = "MODIFIER";
	public static final String SUPPRIMER = "SUPPRIMER";
	public static final String AJOUTER = "AJOUTER";
	public static final String VISUALISER = "VISUALISER";

	public static final String TRIA = "TRIA";

	public static final String ATT_COLLABORATEUR = "collaborateur";
	public static final String ATT_FORM = "form";

	public static final String VUE_SUCCES = "/WEB-INF/JSP_collaborateur/gestionCollaborateur.jsp";
	public static final String VUE_FORM = "/WEB-INF/JSP_collaborateur/ajoutCollaborateur.jsp";

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.collaborateurDao = daoFactory.getCollaborateurDao();
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
		String triCollaborateur;
		String triCollaborateur_id;
		String select_triCollaborateur = "";
		String tri_nomCollaborateur="bi bi-caret-up text-white";
		String tri_prenomCollaborateur="bi bi-caret-up text-white";
		String tri_metierCollaborateur="bi bi-caret-up text-white";
		String tri_type_collaborateur="bi bi-caret-up text-white";
		String select_like;
		String searchCollaborateur = "";
		String critereCollaborateur = "";
		List<Collaborateur> list;
		String tag_statut=null;
		String type_entite = null;
		String debutsession ="debutsession";

		/* Préparation de l'objet pagination */
		GestionCollaborateurPagination form = new GestionCollaborateurPagination();
		/* Traitement de la requête et récupération du bean en résultant */
		Map<String, String> pagination =  form.paginerCollaborateur( request );

		triCollaborateur=pagination.get("triCollaborateur");

		if (triCollaborateur.equals("ID")) {
			triCollaborateur_id=pagination.get("tri_cle");
		} else {
			triCollaborateur_id ="bi bi-caret-down text-white";
		}
		if (triCollaborateur.equals("NOM")) {
			tri_nomCollaborateur=pagination.get("tri_cle");
		} else {
			tri_nomCollaborateur ="bi bi-caret-down text-white";
		}
		if (triCollaborateur.equals("PRENOM")) {
			tri_prenomCollaborateur=pagination.get("tri_cle");
		} else {
			tri_prenomCollaborateur ="bi bi-caret-down text-white";
		}
		if (triCollaborateur.equals("METIER")) {
			tri_metierCollaborateur=pagination.get("tri_cle");
		} else {
			tri_metierCollaborateur ="bi bi-caret-down text-white";
		}
		if (triCollaborateur.equals("TYPE_COLLABORATEUR")) {
			tri_type_collaborateur=pagination.get("tri_cle");
		} else {
			tri_type_collaborateur ="bi bi-caret-down text-white";
		}
		select_triCollaborateur = pagination.get("select_triCollaborateur");

		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} else {
			if (request.getSession().getAttribute("currentPage") != null)
				currentPage = Integer.parseInt((String) request.getSession().getAttribute("currentPage"));

		}

		// récupération  recherche de la page
		System.out.print("début rechereche");
		if (request.getParameter("searchCollaborateur") != null) {
			if (request.getParameter("searchCollaborateur").equals("")) {
				list = collaborateurDao.rechercheCollaborateurs1((currentPage - 1) * recordsPerPage, recordsPerPage,
						select_triCollaborateur, null, tag_statut, type_entite);
				request.getSession().setAttribute("searchCollaborateur", "");
				select_like = "";
			} else {
				request.getSession().setAttribute("searchCollaborateur", request.getParameter("searchCollaborateur"));
				request.getSession().setAttribute("critereCollaborateur", request.getParameter("critereCollaborateur"));
				critereCollaborateur = request.getParameter("critereCollaborateur");
				searchCollaborateur = request.getParameter("searchCollaborateur");
				select_like=critereCollaborateur + " like '" + searchCollaborateur + "%'";
				list = collaborateurDao.rechercheLikeCollaborateurs1((currentPage - 1) * recordsPerPage, recordsPerPage,
						select_triCollaborateur, select_like, null, tag_statut, type_entite);
			}
			System.out.print("rechereche parametre non blanc:"+select_like);
		}

		else {
			if (request.getSession().getAttribute("searchCollaborateur") != null) {
				if (request.getSession().getAttribute("searchCollaborateur").equals("")) {
					list = collaborateurDao.rechercheCollaborateurs1((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triCollaborateur, null, tag_statut, type_entite);
					select_like = "";
				} else {
					searchCollaborateur = (String) request.getSession().getAttribute("searchCollaborateur");
					critereCollaborateur = (String) request.getSession().getAttribute("critereCollaborateur");
					select_like=critereCollaborateur + " like '" + searchCollaborateur + "%'";
					list = collaborateurDao.rechercheLikeCollaborateurs1((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triCollaborateur, select_like, null, tag_statut, type_entite);
				}
			} else {
				select_like = "";
				list = collaborateurDao.rechercheCollaborateurs1((currentPage - 1) * recordsPerPage, recordsPerPage,
						select_triCollaborateur, null, tag_statut, type_entite);
			}
			System.out.print("rechereche parametre session non blanc:"+select_like);
		}

		int noOfRecords = collaborateurDao.getNoOfRecords(); // nombre enregistrements total

		//on inverse le triCollaborateur pour permettre le changement lors de l'appui sur la flèche
			if (triCollaborateur_id.equals("bi bi-caret-up text-white")) {
				request.setAttribute("triCollaborateur_id", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("triCollaborateur_id", "bi bi-caret-up text-white");
			}
			if (tri_nomCollaborateur.equals("bi bi-caret-up text-white")) {
				request.setAttribute("tri_nomCollaborateur", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("tri_nomCollaborateur", "bi bi-caret-up text-white");
			}
			if (tri_prenomCollaborateur.equals("bi bi-caret-up text-white")) {
				request.setAttribute("tri_prenomCollaborateur", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("tri_prenomCollaborateur", "bi bi-caret-up text-white");
			}
			if (tri_metierCollaborateur.equals("bi bi-caret-up text-white")) {
				request.setAttribute("tri_metierCollaborateur", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("tri_metierCollaborateur", "bi bi-caret-up text-white");
			}
			if (tri_type_collaborateur.equals("bi bi-caret-up text-white")) {
				request.setAttribute("tri_type_collaborateur", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("tri_type_collaborateur", "bi bi-caret-up text-white");
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


		request.setAttribute("CollaborateurList", list); // liste enregistrements
		request.setAttribute("noOfPages", noOfPages); // paramètre nombres de pages
		request.setAttribute("currentPage", currentPage); // paramètre page courante
		request.setAttribute("noBegin", noBegin); // paramètre début pagination
		request.setAttribute("maxPage", maxPage); // paramètre fin pagination
		request.setAttribute("pasPage", pasPage); // paramètre pas de la pagination
		request.setAttribute("searchCollaborateur", searchCollaborateur); // paramètre searchCollaborateur

		if (critereCollaborateur.equals("collaborateur.nom")){
			request.getSession().setAttribute("sel_nom", "selected");
			request.getSession().setAttribute("sel_prenom", "");
			request.getSession().setAttribute("sel_metier", "");
			request.getSession().setAttribute("sel_type_collaborateur", "");
		}else
			if (critereCollaborateur.equals("collaborateur.prenom")){
				request.getSession().setAttribute("sel_prenom", "selected");
				request.getSession().setAttribute("sel_nom", "");
				request.getSession().setAttribute("sel_metier", "");
				request.getSession().setAttribute("sel_type_collaborateur", "");
			}else
				if (critereCollaborateur.equals("metier.nom")){
					request.getSession().setAttribute("sel_metier", "selected");
					request.getSession().setAttribute("sel_nom", "");
					request.getSession().setAttribute("sel_prenom", "");
					request.getSession().setAttribute("sel_type_collaborateur", "");
				}
			else if (critereCollaborateur.equals("collaborateur.type_collaborateur")){
				request.getSession().setAttribute("sel_type_collaborateur", "selected");
				request.getSession().setAttribute("sel_nom", "");
				request.getSession().setAttribute("sel_prenom", "");
				request.getSession().setAttribute("sel_metier", "");
			}
		else {
				request.getSession().setAttribute("sel_prenom", "selected");
				request.getSession().setAttribute("sel_nom", "");
				request.getSession().setAttribute("sel_metier_principal", "");
				request.getSession().setAttribute("sel_type_collaborateur", "");
			}

		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_collaborateur/gestionCollaborateur.jsp")
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
