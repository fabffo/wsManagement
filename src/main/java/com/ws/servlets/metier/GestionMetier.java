/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET METIER GESTION                                  ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.metier;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.MetierDao;
import com.ws.beans.Metier;
import com.ws.forms.metier.CreationMetierForm;
import com.ws.forms.metier.GestionMetierPagination;
import com.ws.forms.metier.MajMetierForm;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GestionMetier extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MetierDao metierDao;
	public static final String MODIFIER = "MODIFIER";
	public static final String SUPPRIMER = "SUPPRIMER";
	public static final String AJOUTER = "AJOUTER";
	public static final String VISUALISER = "VISUALISER";

	public static final String TRIA = "TRIA";

	public static final String ATT_TYPE_MISSION = "metier";
	public static final String ATT_FORM = "form";

	public static final String VUE_SUCCES = "/WEB-INF/JSP_metier/gestionMetier.jsp";
	public static final String VUE_FORM = "/WEB-INF/JSP_metier/ajoutMetier.jsp";

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.metierDao = daoFactory.getMetierDao();
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
		String triMetier;
		String triMetier_id;
		String select_triMetier = "";
		String tri_nomMetier="bi bi-caret-up text-white";
		String tri_domaineMetier="bi bi-caret-up text-white";
		String select_like;
		String searchMetier = "";
		String critereMetier = "";
		List<Metier> list = null;
		String debutsession ="debutsession";

		/* Préparation de l'objet pagination */
		GestionMetierPagination form = new GestionMetierPagination();
		/* Traitement de la requête et récupération du bean en résultant */
		Map<String, String> pagination =  form.paginerMetier( request );

		triMetier=pagination.get("triMetier");

		if (triMetier.equals("ID")) {
			triMetier_id=pagination.get("tri_cle");
		} else {
			triMetier_id ="bi bi-caret-down text-white";
		}
		if (triMetier.equals("NOM")) {
			tri_nomMetier=pagination.get("tri_cle");
		} else {
			tri_nomMetier ="bi bi-caret-down text-white";
		}
		if (triMetier.equals("DOMAINE")) {
			tri_domaineMetier=pagination.get("tri_cle");
		} else {
			tri_domaineMetier ="bi bi-caret-down text-white";
		}

		select_triMetier = pagination.get("select_triMetier");

		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} else {
			if (request.getSession().getAttribute("currentPage") != null)
				currentPage = Integer.parseInt((String) request.getSession().getAttribute("currentPage"));

		}

		// récupération  recherche de la page
		if (request.getParameter("searchMetier") != null) {
			if (request.getParameter("searchMetier").equals("")) {
				try {
					list = metierDao.rechercheMetiers((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triMetier);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getSession().setAttribute("searchMetier", "");
			} else {
				request.getSession().setAttribute("searchMetier", request.getParameter("searchMetier"));
				request.getSession().setAttribute("critereMetier", request.getParameter("critereMetier"));
				critereMetier = request.getParameter("critereMetier");
				searchMetier = request.getParameter("searchMetier");
				select_like=critereMetier + " like '" + searchMetier + "%'";
				try {
					list = metierDao.rechercheLikeMetiers((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triMetier, select_like);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		else {
			if (request.getSession().getAttribute("searchMetier") != null) {
				if (request.getSession().getAttribute("searchMetier").equals("")) {
					try {
						list = metierDao.rechercheMetiers((currentPage - 1) * recordsPerPage, recordsPerPage,
								select_triMetier);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					searchMetier = (String) request.getSession().getAttribute("searchMetier");
					critereMetier = (String) request.getSession().getAttribute("critereMetier");
					select_like=critereMetier + " like '" + searchMetier + "%'";
					try {
						list = metierDao.rechercheLikeMetiers((currentPage - 1) * recordsPerPage, recordsPerPage,
								select_triMetier, select_like);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				try {
					list = metierDao.rechercheMetiers((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triMetier);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		int noOfRecords = metierDao.getNoOfRecords(); // nombre enregistrements total

		//on inverse le triMetier pour permettre le changement lors de l'appui sur la flèche
			if (triMetier_id.equals("bi bi-caret-up text-white")) {
				request.setAttribute("triMetier_id", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("triMetier_id", "bi bi-caret-up text-white");
			}
			if (tri_nomMetier.equals("bi bi-caret-up text-white")) {
				request.setAttribute("tri_nomMetier", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("tri_nomMetier", "bi bi-caret-up text-white");
			}
			if (tri_domaineMetier.equals("bi bi-caret-up text-white")) {
				request.setAttribute("tri_domaineMetier", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("tri_domaineMetier", "bi bi-caret-up text-white");
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

		request.setAttribute("MetierList", list); // liste enregistrements
		request.setAttribute("noOfPages", noOfPages); // paramètre nombres de pages
		request.setAttribute("currentPage", currentPage); // paramètre page courante
		request.setAttribute("noBegin", noBegin); // paramètre début pagination
		request.setAttribute("maxPage", maxPage); // paramètre fin pagination
		request.setAttribute("pasPage", pasPage); // paramètre pas de la pagination
		request.setAttribute("searchMetier", searchMetier); // paramètre searchMetier

		if (critereMetier.equals("nom")){
			request.getSession().setAttribute("sel_nom", "selected");
			request.getSession().setAttribute("sel_domaine", "");

		}else
			if (critereMetier.equals("domaine")){
				request.getSession().setAttribute("sel_domaine", "selected");
				request.getSession().setAttribute("sel_nom", "");
			}
			else {
				request.getSession().setAttribute("sel_nom", "");
				request.getSession().setAttribute("sel_domaine", "");
			}

		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_metier/gestionMetier.jsp")
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
