/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET UTILISATEUR GESTION                             ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.utilisateur;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.UtilisateurDao;
import com.ws.beans.Utilisateur;
import com.ws.forms.utilisateur.CreationUtilisateurForm;
import com.ws.forms.utilisateur.GestionUtilisateurPagination;
import com.ws.forms.utilisateur.MajUtilisateurForm;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GestionUtilisateur extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;
	public static final String MODIFIER = "MODIFIER";
	public static final String SUPPRIMER = "SUPPRIMER";
	public static final String AJOUTER = "AJOUTER";
	public static final String VISUALISER = "VISUALISER";

	public static final String TRIA = "TRIA";

	public static final String ATT_UTILISATEUR = "utilisateur";
	public static final String ATT_FORM = "form";

	public static final String VUE_SUCCES = "/WEB-INF/JSP_utilisateur/gestionUtilisateur.jsp";
	public static final String VUE_FORM = "/WEB-INF/JSP_utilisateur/ajoutUtilisateur.jsp";

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.utilisateurDao = daoFactory.getUtilisateurDao();
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
		String triUtilisateur;
		String triUtilisateur_id;
		String select_triUtilisateur = "";
		String tri_nomUtilisateur="bi bi-caret-up text-white";
		String tri_libelleUtilisateur="bi bi-caret-up text-white";
		String select_like;
		String searchUtilisateur = "";
		String critereUtilisateur = "";
		List<Utilisateur> list = null;
		String debutsession ="debutsession";

		/* Préparation de l'objet pagination */
		GestionUtilisateurPagination form = new GestionUtilisateurPagination();
		/* Traitement de la requête et récupération du bean en résultant */
		Map<String, String> pagination =  form.paginerUtilisateur( request );

		triUtilisateur=pagination.get("triUtilisateur");

		if (triUtilisateur.equals("ID")) {
			triUtilisateur_id=pagination.get("tri_cle");
		} else {
			triUtilisateur_id ="bi bi-caret-down text-white";
		}
		if (triUtilisateur.equals("NOM")) {
			tri_nomUtilisateur=pagination.get("tri_cle");
		} else {
			tri_nomUtilisateur ="bi bi-caret-down text-white";
		}
		if (triUtilisateur.equals("LIBELLE")) {
			tri_libelleUtilisateur=pagination.get("tri_cle");
		} else {
			tri_libelleUtilisateur ="bi bi-caret-down text-white";
		}

		select_triUtilisateur = pagination.get("select_triUtilisateur");

		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} else {
			if (request.getSession().getAttribute("currentPage") != null)
				currentPage = Integer.parseInt((String) request.getSession().getAttribute("currentPage"));

		}

		// récupération  recherche de la page
		if (request.getParameter("searchUtilisateur") != null) {
			if (request.getParameter("searchUtilisateur").equals("")) {
				try {
					list = utilisateurDao.rechercheUtilisateurs((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triUtilisateur);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getSession().setAttribute("searchUtilisateur", "");
			} else {
				request.getSession().setAttribute("searchUtilisateur", request.getParameter("searchUtilisateur"));
				request.getSession().setAttribute("critereUtilisateur", request.getParameter("critereUtilisateur"));
				critereUtilisateur = request.getParameter("critereUtilisateur");
				searchUtilisateur = request.getParameter("searchUtilisateur");
				select_like=critereUtilisateur + " like '" + searchUtilisateur + "%'";
				try {
					list = utilisateurDao.rechercheLikeUtilisateurs((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triUtilisateur, select_like);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		else {
			if (request.getSession().getAttribute("searchUtilisateur") != null) {
				if (request.getSession().getAttribute("searchUtilisateur").equals("")) {
					try {
						list = utilisateurDao.rechercheUtilisateurs((currentPage - 1) * recordsPerPage, recordsPerPage,
								select_triUtilisateur);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					searchUtilisateur = (String) request.getSession().getAttribute("searchUtilisateur");
					critereUtilisateur = (String) request.getSession().getAttribute("critereUtilisateur");
					select_like=critereUtilisateur + " like '" + searchUtilisateur + "%'";
					try {
						list = utilisateurDao.rechercheLikeUtilisateurs((currentPage - 1) * recordsPerPage, recordsPerPage,
								select_triUtilisateur, select_like);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				try {
					list = utilisateurDao.rechercheUtilisateurs((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triUtilisateur);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		int noOfRecords = utilisateurDao.getNoOfRecords(); // nombre enregistrements total

		//on inverse le triUtilisateur pour permettre le changement lors de l'appui sur la flèche
			if (triUtilisateur_id.equals("bi bi-caret-up text-white")) {
				request.setAttribute("triUtilisateur_id", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("triUtilisateur_id", "bi bi-caret-up text-white");
			}
			if (tri_nomUtilisateur.equals("bi bi-caret-up text-white")) {
				request.setAttribute("tri_nomUtilisateur", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("tri_nomUtilisateur", "bi bi-caret-up text-white");
			}
			if (tri_libelleUtilisateur.equals("bi bi-caret-up text-white")) {
				request.setAttribute("tri_libelleUtilisateur", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("tri_libelleUtilisateur", "bi bi-caret-up text-white");
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

		request.setAttribute("UtilisateurList", list); // liste enregistrements
		request.setAttribute("noOfPages", noOfPages); // paramètre nombres de pages
		request.setAttribute("currentPage", currentPage); // paramètre page courante
		request.setAttribute("noBegin", noBegin); // paramètre début pagination
		request.setAttribute("maxPage", maxPage); // paramètre fin pagination
		request.setAttribute("pasPage", pasPage); // paramètre pas de la pagination
		request.setAttribute("searchUtilisateur", searchUtilisateur); // paramètre searchUtilisateur

		if (critereUtilisateur.equals("nom")){
			request.getSession().setAttribute("sel_nom", "selected");
			request.getSession().setAttribute("sel_libelle", "");

		}else
			if (critereUtilisateur.equals("libelle")){
				request.getSession().setAttribute("sel_libelle", "selected");
				request.getSession().setAttribute("sel_nom", "");
			}
			else {
				request.getSession().setAttribute("sel_nom", "");
				request.getSession().setAttribute("sel_libelle", "");
			}

		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_utilisateur/gestionUtilisateur.jsp")
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
