/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TYPE MISSION GESTION                            ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.typeContratSociete;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypeContratSocieteDao;
import com.ws.beans.TypeContratSociete;
import com.ws.forms.typeContratSociete.CreationTypeContratSocieteForm;
import com.ws.forms.typeContratSociete.GestionTypeContratSocietePagination;
import com.ws.forms.typeContratSociete.MajTypeContratSocieteForm;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GestionTypeContratSociete extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TypeContratSocieteDao typeContratSocieteDao; 
	public static final String MODIFIER = "MODIFIER";
	public static final String SUPPRIMER = "SUPPRIMER";
	public static final String AJOUTER = "AJOUTER";
	public static final String VISUALISER = "VISUALISER";

	public static final String TRIA = "TRIA";

	public static final String ATT_TYPE_MISSION = "typeContratSociete";
	public static final String ATT_FORM = "form";

	public static final String VUE_SUCCES = "/WEB-INF/JSP_typeContratSociete/gestionTypeContratSociete.jsp";
	public static final String VUE_FORM = "/WEB-INF/JSP_typeContratSociete/ajoutTypeContratSociete.jsp";

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.typeContratSocieteDao = daoFactory.getTypeContratSocieteDao();
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
		String triTypeContratSociete;
		String triTypeContratSociete_id;
		String select_triTypeContratSociete = "";
		String triTypeContratSociete_nom="bi bi-caret-up text-white";
		String triTypeContratSociete_libelle="bi bi-caret-up text-white";
		String select_like;
		String searchTypeContratSociete = "";
		String critereTypeContratSociete = "";
		List<TypeContratSociete> list;
		String debutsession ="debutsession";

		/* Préparation de l'objet pagination */
		GestionTypeContratSocietePagination form = new GestionTypeContratSocietePagination();
		/* Traitement de la requête et récupération du bean en résultant */
		Map<String, String> pagination =  form.paginerTypeContratSociete( request );
		
		triTypeContratSociete=pagination.get("triTypeContratSociete");
		
		if (triTypeContratSociete.equals("ID")) {
			triTypeContratSociete_id=pagination.get("tri_cle");	
		} else {
			triTypeContratSociete_id ="bi bi-caret-down text-white";
		}
		if (triTypeContratSociete.equals("NOM")) {
			triTypeContratSociete_nom=pagination.get("tri_cle");	
		} else {
			triTypeContratSociete_nom ="bi bi-caret-down text-white";
		}
		if (triTypeContratSociete.equals("LIBELLE")) {
			triTypeContratSociete_libelle=pagination.get("tri_cle");	
		} else {
			triTypeContratSociete_libelle ="bi bi-caret-down text-white";
		}
		
		select_triTypeContratSociete = pagination.get("select_triTypeContratSociete");
		
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} else {
			if (request.getSession().getAttribute("currentPage") != null)
				currentPage = Integer.parseInt((String) request.getSession().getAttribute("currentPage"));

		}

		// récupération  recherche de la page
		if (request.getParameter("searchTypeContratSociete") != null) {
			if (request.getParameter("searchTypeContratSociete").equals("")) {
				list = typeContratSocieteDao.rechercheTypeContratSocietes((currentPage - 1) * recordsPerPage, recordsPerPage,
						select_triTypeContratSociete);
				request.getSession().setAttribute("searchTypeContratSociete", "");
			} else {
				request.getSession().setAttribute("searchTypeContratSociete", request.getParameter("searchTypeContratSociete"));
				request.getSession().setAttribute("critereTypeContratSociete", request.getParameter("critereTypeContratSociete"));
				critereTypeContratSociete = request.getParameter("critereTypeContratSociete");
				searchTypeContratSociete = request.getParameter("searchTypeContratSociete");
				select_like=critereTypeContratSociete + " like '" + searchTypeContratSociete + "%'";
				list = typeContratSocieteDao.rechercheLikeTypeContratSocietes((currentPage - 1) * recordsPerPage, recordsPerPage,
						select_triTypeContratSociete, select_like);
			}
		}

		else {
			if (request.getSession().getAttribute("searchTypeContratSociete") != null) {
				if (request.getSession().getAttribute("searchTypeContratSociete").equals("")) {
					list = typeContratSocieteDao.rechercheTypeContratSocietes((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triTypeContratSociete);
				} else {
					searchTypeContratSociete = (String) request.getSession().getAttribute("searchTypeContratSociete");
					critereTypeContratSociete = (String) request.getSession().getAttribute("critereTypeContratSociete");
					select_like=critereTypeContratSociete + " like '" + searchTypeContratSociete + "%'";
					list = typeContratSocieteDao.rechercheLikeTypeContratSocietes((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triTypeContratSociete, select_like);
				}
			} else {
				list = typeContratSocieteDao.rechercheTypeContratSocietes((currentPage - 1) * recordsPerPage, recordsPerPage,
						select_triTypeContratSociete);
			}
		}
		
		int noOfRecords = typeContratSocieteDao.getNoOfRecords(); // nombre enregistrements total
		
		//on inverse le triTypeContratSociete pour permettre le changement lors de l'appui sur la flèche
			if (triTypeContratSociete_id.equals("bi bi-caret-up text-white")) {
				request.setAttribute("triTypeContratSociete_id", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("triTypeContratSociete_id", "bi bi-caret-up text-white");
			}
			if (triTypeContratSociete_nom.equals("bi bi-caret-up text-white")) {
				request.setAttribute("triTypeContratSociete_nom", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("triTypeContratSociete_nom", "bi bi-caret-up text-white");
			}
			if (triTypeContratSociete_libelle.equals("bi bi-caret-up text-white")) {
				request.setAttribute("triTypeContratSociete_libelle", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("triTypeContratSociete_libelle", "bi bi-caret-up text-white");
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

		request.setAttribute("TypeContratSocieteList", list); // liste enregistrements
		request.setAttribute("noOfPages", noOfPages); // paramètre nombres de pages
		request.setAttribute("currentPage", currentPage); // paramètre page courante
		request.setAttribute("noBegin", noBegin); // paramètre début pagination
		request.setAttribute("maxPage", maxPage); // paramètre fin pagination
		request.setAttribute("pasPage", pasPage); // paramètre pas de la pagination
		request.setAttribute("searchTypeContratSociete", searchTypeContratSociete); // paramètre searchTypeContratSociete
		
		if (critereTypeContratSociete.equals("nom")){
			request.getSession().setAttribute("sel_nom", "selected");
			request.getSession().setAttribute("sel_libelle", "");
			
		}else 
			if (critereTypeContratSociete.equals("libelle")){
				request.getSession().setAttribute("sel_libelle", "selected");	
				request.getSession().setAttribute("sel_nom", "");
			}
			else {
				request.getSession().setAttribute("sel_nom", "");
				request.getSession().setAttribute("sel_libelle", "");
			}

		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_typeContratSociete/gestionTypeContratSociete.jsp")
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
