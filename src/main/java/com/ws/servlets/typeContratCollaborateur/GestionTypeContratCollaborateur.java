/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TYPE CONTRAT GESTION                            ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.typeContratCollaborateur;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypeContratCollaborateurDao;
import com.ws.beans.TypeContratCollaborateur;
import com.ws.forms.typeContratCollaborateur.CreationTypeCollaborateurForm;
import com.ws.forms.typeContratCollaborateur.GestionTypeContratCollaborateurPagination;
import com.ws.forms.typeContratCollaborateur.MajTypeContratCollaborateurForm;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GestionTypeContratCollaborateur extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TypeContratCollaborateurDao typeContratCollaborateurDao;
	public static final String MODIFIER = "MODIFIER";
	public static final String SUPPRIMER = "SUPPRIMER";
	public static final String AJOUTER = "AJOUTER";
	public static final String VISUALISER = "VISUALISER";

	public static final String TRIA = "TRIA";

	public static final String ATT_TYPE_CONTRAT = "typeContratCollaborateur";
	public static final String ATT_FORM = "form";

	public static final String VUE_SUCCES = "/WEB-INF/JSP_typeContratCollaborateur/gestionTypeContratCollaborateur.jsp";
	public static final String VUE_FORM = "/WEB-INF/JSP_typeContratCollaborateur/ajoutTypeContratCollaborateur.jsp";

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.typeContratCollaborateurDao = daoFactory.getTypeContratCollaborateurDao();
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
		String triTypeContratCollaborateur;
		String triTypeContratCollaborateur_id;
		String select_triTypeContratCollaborateur = "";
		String triTypeContratCollaborateur_nom="bi bi-caret-up text-white";
		String triTypeContratCollaborateur_libelle="bi bi-caret-up text-white";
		String triTypeContratCollaborateur_statut="bi bi-caret-up text-white";
		String select_like;
		String searchTypeContratCollaborateur = "";
		String critereTypeContratCollaborateur = "";
		List<TypeContratCollaborateur> list = null;
		String debutsession ="debutsession";

		/* Préparation de l'objet pagination */
		GestionTypeContratCollaborateurPagination form = new GestionTypeContratCollaborateurPagination();
		/* Traitement de la requête et récupération du bean en résultant */
		Map<String, String> pagination =  form.paginertypeContratCollaborateur( request );

		triTypeContratCollaborateur=pagination.get("triTypeContratCollaborateur");

		if (triTypeContratCollaborateur.equals("ID")) {
			triTypeContratCollaborateur_id=pagination.get("tri_cle");
		} else {
			triTypeContratCollaborateur_id ="bi bi-caret-down text-white";
		}
		if (triTypeContratCollaborateur.equals("NOM")) {
			triTypeContratCollaborateur_nom=pagination.get("tri_cle");
		} else {
			triTypeContratCollaborateur_nom ="bi bi-caret-down text-white";
		}
		if (triTypeContratCollaborateur.equals("LIBELLE")) {
			triTypeContratCollaborateur_libelle=pagination.get("tri_cle");
		} else {
			triTypeContratCollaborateur_libelle ="bi bi-caret-down text-white";
		}
		if (triTypeContratCollaborateur.equals("STATUT")) {
			triTypeContratCollaborateur_statut=pagination.get("tri_cle");
		} else {
			triTypeContratCollaborateur_statut ="bi bi-caret-down text-white";
		}
		select_triTypeContratCollaborateur = pagination.get("select_triTypeContratCollaborateur");

		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} else {
			if (request.getSession().getAttribute("currentPage") != null)
				currentPage = Integer.parseInt((String) request.getSession().getAttribute("currentPage"));

		}

		// récupération  recherche de la page
		if (request.getParameter("searchTypeContratCollaborateur") != null) {
			if (request.getParameter("searchTypeContratCollaborateur").equals("")) {
				try {
					list = typeContratCollaborateurDao.rechercheTypeContratCollaborateurs((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triTypeContratCollaborateur);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getSession().setAttribute("searchTypeContratCollaborateur", "");
			} else {
				request.getSession().setAttribute("searchTypeContratCollaborateur", request.getParameter("searchTypeContrat"));
				request.getSession().setAttribute("critereTypeContratCollaborateur", request.getParameter("critereTypeContrat"));
				critereTypeContratCollaborateur = request.getParameter("critereTypeContratCollaborateur");
				searchTypeContratCollaborateur = request.getParameter("searchTypeContratCollaborateur");
				select_like=critereTypeContratCollaborateur + " like '" + searchTypeContratCollaborateur + "%'";
				try {
					list = typeContratCollaborateurDao.rechercheLikeTypeContratCollaborateurs((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triTypeContratCollaborateur, select_like);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		else {
			if (request.getSession().getAttribute("searchTypeContratCollaborateur") != null) {
				if (request.getSession().getAttribute("searchTypeContratCollaborateur").equals("")) {
					try {
						list = typeContratCollaborateurDao.rechercheTypeContratCollaborateurs((currentPage - 1) * recordsPerPage, recordsPerPage,
								select_triTypeContratCollaborateur);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					searchTypeContratCollaborateur = (String) request.getSession().getAttribute("searchTypeContratCollaborateur");
					critereTypeContratCollaborateur = (String) request.getSession().getAttribute("critereTypeContratCollaborateur");
					select_like=critereTypeContratCollaborateur + " like '" + searchTypeContratCollaborateur + "%'";
					try {
						list = typeContratCollaborateurDao.rechercheLikeTypeContratCollaborateurs((currentPage - 1) * recordsPerPage, recordsPerPage,
								select_triTypeContratCollaborateur, select_like);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				try {
					list = typeContratCollaborateurDao.rechercheTypeContratCollaborateurs((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triTypeContratCollaborateur);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		int noOfRecords = typeContratCollaborateurDao.getNoOfRecords(); // nombre enregistrements total

		//on inverse le triTypeContrat pour permettre le changement lors de l'appui sur la flèche
			if (triTypeContratCollaborateur_id.equals("bi bi-caret-up text-white")) {
				request.setAttribute("triTypeContratCollaborateur_id", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("triTypeContratCollaborateur_id", "bi bi-caret-up text-white");
			}
			if (triTypeContratCollaborateur_nom.equals("bi bi-caret-up text-white")) {
				request.setAttribute("triTypeContratCollaborateur_nom", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("triTypeContratCollaborateur_nom", "bi bi-caret-up text-white");
			}
			if (triTypeContratCollaborateur_libelle.equals("bi bi-caret-up text-white")) {
				request.setAttribute("triTypeContratCollaborateur_libelle", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("triTypeContratCollaborateur_libelle", "bi bi-caret-up text-white");
			}
			if (triTypeContratCollaborateur_statut.equals("bi bi-caret-up text-white")) {
				request.setAttribute("triTypeContratCollaborateur_statut", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("triTypeContratCollaborateur_statut", "bi bi-caret-up text-white");
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


		request.setAttribute("TypeContratCollaborateurList", list); // liste enregistrements
		request.setAttribute("noOfPages", noOfPages); // paramètre nombres de pages
		request.setAttribute("currentPage", currentPage); // paramètre page courante
		request.setAttribute("noBegin", noBegin); // paramètre début pagination
		request.setAttribute("maxPage", maxPage); // paramètre fin pagination
		request.setAttribute("pasPage", pasPage); // paramètre pas de la pagination
		request.setAttribute("searchTypeContratCollaborateur", searchTypeContratCollaborateur); // paramètre searchTypeContrat

		if (critereTypeContratCollaborateur.equals("nom")){
			request.getSession().setAttribute("sel_nom", "selected");
			request.getSession().setAttribute("sel_libelle", "");
			request.getSession().setAttribute("sel_statut", "");
		}else
			if (critereTypeContratCollaborateur.equals("libelle")){
				request.getSession().setAttribute("sel_libelle", "selected");
				request.getSession().setAttribute("sel_nom", "");
				request.getSession().setAttribute("sel_statut", "");
			}else
				if (critereTypeContratCollaborateur.equals("statut")){
					request.getSession().setAttribute("sel_statut", "selected");
					request.getSession().setAttribute("sel_nom", "");
					request.getSession().setAttribute("sel_libelle", "");
				}
			else {
				request.getSession().setAttribute("sel_libelle", "selected");
				request.getSession().setAttribute("sel_nom", "");
				request.getSession().setAttribute("sel_statut", "");
			}

		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_typeContratCollaborateur/gestionTypeContratCollaborateur.jsp")
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
