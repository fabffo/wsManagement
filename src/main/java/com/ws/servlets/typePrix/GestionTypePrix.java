/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TYPEPRIX GESTION                             ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.typePrix;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypePrixDao;
import com.ws.beans.TypePrix;
import com.ws.forms.typePrix.CreationTypePrixForm;
import com.ws.forms.typePrix.GestionTypePrixPagination;
import com.ws.forms.typePrix.MajTypePrixForm;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GestionTypePrix extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TypePrixDao typePrixDao;
	public static final String MODIFIER = "MODIFIER";
	public static final String SUPPRIMER = "SUPPRIMER";
	public static final String AJOUTER = "AJOUTER";
	public static final String VISUALISER = "VISUALISER";

	public static final String TRIA = "TRIA";

	public static final String ATT_TYPEPRIX = "typePrix";
	public static final String ATT_FORM = "form";

	public static final String VUE_SUCCES = "/WEB-INF/JSP_typePrix/gestionTypePrix.jsp";
	public static final String VUE_FORM = "/WEB-INF/JSP_typePrix/ajoutTypePrix.jsp";

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.typePrixDao = daoFactory.getTypePrixDao();
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
		String triTypePrix;
		String triTypePrix_id;
		String select_triTypePrix = "";
		String tri_nomTypePrix="bi bi-caret-up text-white";
		String tri_libelleTypePrix="bi bi-caret-up text-white";
		String select_like;
		String searchTypePrix = "";
		String critereTypePrix = "";
		List<TypePrix> list = null;
		String debutsession ="debutsession";

		/* Préparation de l'objet pagination */
		GestionTypePrixPagination form = new GestionTypePrixPagination();
		/* Traitement de la requête et récupération du bean en résultant */
		Map<String, String> pagination =  form.paginerTypePrix( request );

		triTypePrix=pagination.get("triTypePrix");

		if (triTypePrix.equals("ID")) {
			triTypePrix_id=pagination.get("tri_cle");
		} else {
			triTypePrix_id ="bi bi-caret-down text-white";
		}
		if (triTypePrix.equals("NOM")) {
			tri_nomTypePrix=pagination.get("tri_cle");
		} else {
			tri_nomTypePrix ="bi bi-caret-down text-white";
		}
		if (triTypePrix.equals("LIBELLE")) {
			tri_libelleTypePrix=pagination.get("tri_cle");
		} else {
			tri_libelleTypePrix ="bi bi-caret-down text-white";
		}

		select_triTypePrix = pagination.get("select_triTypePrix");

		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} else {
			if (request.getSession().getAttribute("currentPage") != null)
				currentPage = Integer.parseInt((String) request.getSession().getAttribute("currentPage"));

		}

		// récupération  recherche de la page
		if (request.getParameter("searchTypePrix") != null) {
			if (request.getParameter("searchTypePrix").equals("")) {
				try {
					list = typePrixDao.rechercheTypePrixs((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triTypePrix);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getSession().setAttribute("searchTypePrix", "");
			} else {
				request.getSession().setAttribute("searchTypePrix", request.getParameter("searchTypePrix"));
				request.getSession().setAttribute("critereTypePrix", request.getParameter("critereTypePrix"));
				critereTypePrix = request.getParameter("critereTypePrix");
				searchTypePrix = request.getParameter("searchTypePrix");
				select_like=critereTypePrix + " like '" + searchTypePrix + "%'";
				try {
					list = typePrixDao.rechercheLikeTypePrixs((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triTypePrix, select_like);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		else {
			if (request.getSession().getAttribute("searchTypePrix") != null) {
				if (request.getSession().getAttribute("searchTypePrix").equals("")) {
					try {
						list = typePrixDao.rechercheTypePrixs((currentPage - 1) * recordsPerPage, recordsPerPage,
								select_triTypePrix);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					searchTypePrix = (String) request.getSession().getAttribute("searchTypePrix");
					critereTypePrix = (String) request.getSession().getAttribute("critereTypePrix");
					select_like=critereTypePrix + " like '" + searchTypePrix + "%'";
					try {
						list = typePrixDao.rechercheLikeTypePrixs((currentPage - 1) * recordsPerPage, recordsPerPage,
								select_triTypePrix, select_like);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				try {
					list = typePrixDao.rechercheTypePrixs((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triTypePrix);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		int noOfRecords = typePrixDao.getNoOfRecords(); // nombre enregistrements total

		//on inverse le triTypePrix pour permettre le changement lors de l'appui sur la flèche
			if (triTypePrix_id.equals("bi bi-caret-up text-white")) {
				request.setAttribute("triTypePrix_id", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("triTypePrix_id", "bi bi-caret-up text-white");
			}
			if (tri_nomTypePrix.equals("bi bi-caret-up text-white")) {
				request.setAttribute("tri_nomTypePrix", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("tri_nomTypePrix", "bi bi-caret-up text-white");
			}
			if (tri_libelleTypePrix.equals("bi bi-caret-up text-white")) {
				request.setAttribute("tri_libelleTypePrix", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("tri_libelleTypePrix", "bi bi-caret-up text-white");
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

		request.setAttribute("TypePrixList", list); // liste enregistrements
		request.setAttribute("noOfPages", noOfPages); // paramètre nombres de pages
		request.setAttribute("currentPage", currentPage); // paramètre page courante
		request.setAttribute("noBegin", noBegin); // paramètre début pagination
		request.setAttribute("maxPage", maxPage); // paramètre fin pagination
		request.setAttribute("pasPage", pasPage); // paramètre pas de la pagination
		request.setAttribute("searchTypePrix", searchTypePrix); // paramètre searchTypePrix

		if (critereTypePrix.equals("nom")){
			request.getSession().setAttribute("sel_nom", "selected");
			request.getSession().setAttribute("sel_libelle", "");

		}else
			if (critereTypePrix.equals("libelle")){
				request.getSession().setAttribute("sel_libelle", "selected");
				request.getSession().setAttribute("sel_nom", "");
			}
			else {
				request.getSession().setAttribute("sel_nom", "");
				request.getSession().setAttribute("sel_libelle", "");
			}

		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_typePrix/gestionTypePrix.jsp")
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
