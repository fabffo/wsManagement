/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TYPE SOCIETE GESTION                            ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.typeSociete;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypeSocieteDao;
import com.ws.beans.TypeSociete;
import com.ws.forms.typeSociete.CreationTypeSocieteForm;
import com.ws.forms.typeSociete.GestionTypeSocietePagination;
import com.ws.forms.typeSociete.MajTypeSocieteForm;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GestionTypeSociete extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TypeSocieteDao typeSocieteDao;
	public static final String MODIFIER = "MODIFIER";
	public static final String SUPPRIMER = "SUPPRIMER";
	public static final String AJOUTER = "AJOUTER";
	public static final String VISUALISER = "VISUALISER";

	public static final String TRIA = "TRIA";

	public static final String ATT_TYPE_MISSION = "typeSociete";
	public static final String ATT_FORM = "form";

	public static final String VUE_SUCCES = "/WEB-INF/JSP_typeSociete/gestionTypeSociete.jsp";
	public static final String VUE_FORM = "/WEB-INF/JSP_typeSociete/ajoutTypeSociete.jsp";

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.typeSocieteDao = daoFactory.getTypeSocieteDao();
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
		String triTypeSociete;
		String triTypeSociete_id;
		String select_triTypeSociete = "";
		String tri_nomTypeSociete="bi bi-caret-up text-white";
		String tri_libelleTypeSociete="bi bi-caret-up text-white";
		String select_likeTypeSociete;
		String searchTypeSociete = "";
		String critereTypeSociete = "";
		List<TypeSociete> list = null;

		/* Préparation de l'objet pagination */
		GestionTypeSocietePagination form = new GestionTypeSocietePagination();
		/* Traitement de la requête et récupération du bean en résultant */
		Map<String, String> pagination =  form.paginerTypeSociete( request );

		triTypeSociete=pagination.get("triTypeSociete");

		if (triTypeSociete.equals("ID")) {
			triTypeSociete_id=pagination.get("tri_cle");
		} else {
			triTypeSociete_id ="bi bi-caret-down text-white";
		}
		if (triTypeSociete.equals("NOM")) {
			tri_nomTypeSociete=pagination.get("tri_cle");
		} else {
			tri_nomTypeSociete ="bi bi-caret-down text-white";
		}
		if (triTypeSociete.equals("LIBELLE")) {
			tri_libelleTypeSociete=pagination.get("tri_cle");
		} else {
			tri_libelleTypeSociete ="bi bi-caret-down text-white";
		}

		select_triTypeSociete = pagination.get("select_triTypeSociete");

		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} else {
			if (request.getSession().getAttribute("currentPage") != null)
				currentPage = Integer.parseInt((String) request.getSession().getAttribute("currentPage"));

		}

		// récupération  recherche de la page
		if (request.getParameter("searchTypeSociete") != null) {
			if (request.getParameter("searchTypeSociete").equals("")) {
				try {
					list = typeSocieteDao.rechercheTypeSocietes((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triTypeSociete);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getSession().setAttribute("searchTypeSociete", "");
			} else {
				request.getSession().setAttribute("searchTypeSociete", request.getParameter("searchTypeSociete"));
				request.getSession().setAttribute("critereTypeSociete", request.getParameter("critereTypeSociete"));
				critereTypeSociete = request.getParameter("critereTypeSociete");
				searchTypeSociete = request.getParameter("searchTypeSociete");
				select_likeTypeSociete=critereTypeSociete + " like '" + searchTypeSociete + "%'";
				try {
					list = typeSocieteDao.rechercheLikeTypeSocietes((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triTypeSociete, select_likeTypeSociete);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		else {
			if (request.getSession().getAttribute("searchTypeSociete") != null) {
				if (request.getSession().getAttribute("searchTypeSociete").equals("")) {
					try {
						list = typeSocieteDao.rechercheTypeSocietes((currentPage - 1) * recordsPerPage, recordsPerPage,
								select_triTypeSociete);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					searchTypeSociete = (String) request.getSession().getAttribute("searchTypeSociete");
					critereTypeSociete = (String) request.getSession().getAttribute("critereTypeSociete");
					select_likeTypeSociete=critereTypeSociete + " like '" + searchTypeSociete + "%'";
					try {
						list = typeSocieteDao.rechercheLikeTypeSocietes((currentPage - 1) * recordsPerPage, recordsPerPage,
								select_triTypeSociete, select_likeTypeSociete);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				try {
					list = typeSocieteDao.rechercheTypeSocietes((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triTypeSociete);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		int noOfRecords = typeSocieteDao.getNoOfRecords(); // nombre enregistrements total

		//on inverse le triTypeSociete pour permettre le changement lors de l'appui sur la flèche
			if (triTypeSociete_id.equals("bi bi-caret-up text-white")) {
				request.setAttribute("triTypeSociete_id", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("triTypeSociete_id", "bi bi-caret-up text-white");
			}
			if (tri_nomTypeSociete.equals("bi bi-caret-up text-white")) {
				request.setAttribute("tri_nomTypeSociete", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("tri_nomTypeSociete", "bi bi-caret-up text-white");
			}
			if (tri_libelleTypeSociete.equals("bi bi-caret-up text-white")) {
				request.setAttribute("tri_libelleTypeSociete", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("tri_libelleTypeSociete", "bi bi-caret-up text-white");
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

		request.setAttribute("TypeSocieteList", list); // liste enregistrements
		request.setAttribute("noOfPages", noOfPages); // paramètre nombres de pages
		request.setAttribute("currentPage", currentPage); // paramètre page courante
		request.setAttribute("noBegin", noBegin); // paramètre début pagination
		request.setAttribute("maxPage", maxPage); // paramètre fin pagination
		request.setAttribute("pasPage", pasPage); // paramètre pas de la pagination
		request.setAttribute("searchTypeSociete", searchTypeSociete); // paramètre searchTypeSociete

		if (critereTypeSociete.equals("nom")){
			request.getSession().setAttribute("sel_nom", "selected");
			request.getSession().setAttribute("sel_libelle", "");

		}else
			if (critereTypeSociete.equals("libelle")){
				request.getSession().setAttribute("sel_libelle", "selected");
				request.getSession().setAttribute("sel_nom", "");
			}
			else {
				request.getSession().setAttribute("sel_nom", "");
				request.getSession().setAttribute("sel_libelle", "");
			}

		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_typeSociete/gestionTypeSociete.jsp")
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
