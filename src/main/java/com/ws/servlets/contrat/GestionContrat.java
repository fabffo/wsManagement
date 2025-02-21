/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET CONTRATCLIENT GESTION                           ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.contrat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ws.Dao.ContratDao;
import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.StatutDao;
import com.ws.beans.Contrat;
import com.ws.beans.Statut;
import com.ws.forms.contrat.GestionContratPagination;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GestionContrat extends HttpServlet {

	// Initialisation de variables
	private static final long serialVersionUID = 1L;
	private ContratDao contratDao;
	private StatutDao statutDao;
	private List<Statut> lesStatuts;
	public static final String MODIFIER = "MODIFIER";
	public static final String SUPPRIMER = "SUPPRIMER";
	public static final String AJOUTER = "AJOUTER";
	public static final String VISUALISER = "VISUALISER";
	public static final String TRIA = "TRIA";
	public static final String ATT_CONTRATCLIENT = "contrat";
	public static final String ATT_FORM = "form";
	public static final String VUE_SUCCES = "/WEB-INF/JSP_contrat/gestionContrat.jsp";
	public static final String VUE_FORM = "/WEB-INF/JSP_contrat/ajoutContrat.jsp";
	public DaoFactory daoFactory;

	public void init() throws ServletException {
		daoFactory = DaoFactory.getInstance();
		this.contratDao = daoFactory.getContratDao();
		this.statutDao = daoFactory.getStatutDao();
	}

	// ============ DO GET =======================================================
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Initialisation de variables
		int currentPage = 1; // page en cours*
		int recordsPerPage = 6; // nbr enregistrements par page
		int maxPage = 0; // fin pagination
		String triContrat;
		String select_triContrat = "";
		String triContrat_id = "bi bi-caret-up text-white";
		String triContrat_type_contrat = "bi bi-caret-up text-white";
		String triContrat_client = "bi bi-caret-up text-white";
		String triContrat_statut = "bi bi-caret-up text-white";
		String select_like;
		String searchContrat = "";
		String critereContrat = "";
		List<Contrat> list;
		String debutsession = "debutsession";
		String tag_statut;

		// gestion du paramètre typeSocieté pour filter les contrats
		// =============================================================
		if (request.getParameter("type_entite") != null) {
			request.getSession().setAttribute("type_entite", request.getParameter("type_entite"));
		} else {
			if (request.getSession().getAttribute("type_entite") == null) {
				// valeur par défaut
				request.getSession().setAttribute("type_entite", "CLIENT");
			}
		}
		String type_entite = (String) request.getSession().getAttribute("type_entite");

		// Initialiser le statut par défaut
		 tag_statut = request.getParameter("tag_statut") != null
		        ? request.getParameter("tag_statut")
		        : (String) request.getSession().getAttribute("tag_statut");

		if (tag_statut == null || tag_statut.isEmpty()) {
		    tag_statut = "En-cours"; // Statut par défaut
		}
		request.getSession().setAttribute("tag_statut", tag_statut);

		// Liste fixe des statuts possibles
		String[] statutsPossibles = {"En-cours", "Termine", "Annule", "Tout"};

		// Générer la liste de statuts
		List<Map<String, String>> listStatuts = new ArrayList<>();
		for (String statut : statutsPossibles) {
		    Map<String, String> mapStatut = new HashMap<>();
		    mapStatut.put("nom", statut);
		    mapStatut.put("selected", statut.equals(tag_statut) ? "selected" : "");
		    listStatuts.add(mapStatut);
		}
		try {
			 lesStatuts = statutDao.listerStatut();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Mettre la liste dans la session
		request.getSession().setAttribute("listStatuts", listStatuts);
		request.getSession().setAttribute("lesStatuts", lesStatuts);

		/* Préparation de l'objet pagination */
		GestionContratPagination form = new GestionContratPagination();
		/* Traitement de la requête et récupération du bean en résultant */
		Map<String, String> pagination = form.paginerContrat(request);

		// ----------------Définition du tri du contrat ------------------------
		triContrat = pagination.get("triContrat");
		if (triContrat.equals("ID_CONTRAT")) {
			triContrat_id = pagination.get("tri_cle");
		} else {
			triContrat_id = "bi bi-caret-down text-white";
		}

		if (triContrat.equals("TYPE_CONTRAT")) {
			triContrat_type_contrat = pagination.get("tri_cle");
		} else {
			triContrat_type_contrat = "bi bi-caret-down text-white";
		}

		if (triContrat.equals("CLIENT")) {
			triContrat_client = pagination.get("tri_cle");
		} else {
			triContrat_client = "bi bi-caret-down text-white";
		}

		if (triContrat.equals("STATUT")) {
			triContrat_statut = pagination.get("tri_cle");
		} else {
			triContrat_statut = "bi bi-caret-down text-white";
		}

		select_triContrat = pagination.get("select_triContrat");
		// ----------------Définition du tri du contrat ------------------------

		// ----------------Définition del la page courante ---------------------
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} else {
			if (request.getSession().getAttribute("currentPage") != null)
				currentPage = Integer.parseInt((String) request.getSession().getAttribute("currentPage"));

		}
		// ----------------Définition del la page courante ---------------------

		// ----------------Récupération recherche de la page ------------------
		if (request.getParameter("searchContrat") != null) {
			if (request.getParameter("searchContrat").equals("")) {
				list = contratDao.rechercheContrats1((currentPage - 1) * recordsPerPage, recordsPerPage,
						select_triContrat, null, tag_statut, type_entite);
				request.getSession().setAttribute("searchContrat", "");
				select_like = "";
			} else {
				currentPage = 1; // On met la page courante à 1 pour une nouvelle recherche
				request.getSession().setAttribute("searchContrat", request.getParameter("searchContrat"));
				request.getSession().setAttribute("critereContrat", request.getParameter("critereContrat"));
				critereContrat = request.getParameter("critereContrat");
				searchContrat = request.getParameter("searchContrat");
				select_like = critereContrat + " like '" + searchContrat + "%'";
				list = contratDao.rechercheLikeContrats1((currentPage - 1) * recordsPerPage, recordsPerPage,
						select_triContrat, select_like, null, tag_statut, type_entite);
			}
		}

		else {
			if (request.getSession().getAttribute("searchContrat") != null) {
				if (request.getSession().getAttribute("searchContrat").equals("")) {
					list = contratDao.rechercheContrats1((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triContrat, null, tag_statut, type_entite);
					select_like = "";
				} else {
					searchContrat = (String) request.getSession().getAttribute("searchContrat");
					critereContrat = (String) request.getSession().getAttribute("critereContrat");
					select_like = critereContrat + " like '" + searchContrat + "%'";
					list = contratDao.rechercheLikeContrats1((currentPage - 1) * recordsPerPage,
							recordsPerPage, select_triContrat, select_like, null, tag_statut, type_entite);
				}
			} else {
				select_like = "";
				list = contratDao.rechercheContrats1((currentPage - 1) * recordsPerPage, recordsPerPage,
						select_triContrat, null, tag_statut, type_entite);
			}
		}
		// ----------------Récupération recherche de la page ------------------

		int noOfRecords = contratDao.getNoOfRecords(); // nombre enregistrements total

		// ---on inverse le triContrat pour permettre le changement lors de
		// l'appui sur la flèche ---
		if (triContrat_id.equals("bi bi-caret-up text-white")) {
			request.setAttribute("triContrat_id", "bi bi-caret-down text-white");
		} else {
			request.setAttribute("triContrat_id", "bi bi-caret-up text-white");
		}
		if (triContrat_type_contrat.equals("bi bi-caret-up text-white")) {
			request.setAttribute("triContrat_type_contrat", "bi bi-caret-down text-white");
		} else {
			request.setAttribute("triContrat_type_contrat", "bi bi-caret-up text-white");
		}
		if (triContrat_client.equals("bi bi-caret-up text-white")) {
			request.setAttribute("triContrat_client", "bi bi-caret-down text-white");
		} else {
			request.setAttribute("triContrat_client", "bi bi-caret-up text-white");
		}
		if (triContrat_statut.equals("bi bi-caret-up text-white")) {
			request.setAttribute("triContrat_statut", "bi bi-caret-down text-white");
		} else {
			request.setAttribute("triContrat_statut", "bi bi-caret-up text-white");
		}
		// ---on inverse le triContrat pour permettre le changement lors de
		// l'appui sur la flèche ---

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

		// ------------------- Récupération des paramètres de la page
		// ------------------------------
		request.setAttribute("ContratList", list); // liste enregistrements
		request.setAttribute("noOfPages", noOfPages); // paramètre nombres de pages
		request.setAttribute("currentPage", currentPage); // paramètre page courante
		request.setAttribute("noBegin", noBegin); // paramètre début pagination
		request.setAttribute("maxPage", maxPage); // paramètre fin pagination
		request.setAttribute("pasPage", pasPage); // paramètre pas de la pagination
		request.setAttribute("searchContrat", searchContrat); // paramètre searchContrat
		request.setAttribute("tag_statut", tag_statut); // paramètre général pour la valeur du statut
		// ------------------- Récupération des paramètres de la page
		// ------------------------------

		// ------------------- Définition de la valeur selectionné dans la barre de
		// recherche -------
		if (critereContrat.equals("avenantClient.id")) {
			request.getSession().setAttribute("sel_id", "selected");
			request.getSession().setAttribute("sel_type_contrat", "");
			request.getSession().setAttribute("sel_client", "");
			request.getSession().setAttribute("sel_statut", "");
		} else if (critereContrat.equals("avenantClient.type_contrat")) {
			request.getSession().setAttribute("sel_type_contrat", "selected");
			request.getSession().setAttribute("sel_version", "");
			request.getSession().setAttribute("sel_client", "");
			request.getSession().setAttribute("sel_statut", "");
		} else if (critereContrat.equals("societe.raison_sociale")) {
			request.getSession().setAttribute("sel_client", "selected");
			request.getSession().setAttribute("sel_version", "");
			request.getSession().setAttribute("sel_type_contrat", "");
			request.getSession().setAttribute("sel_statut", "");
		} else if (critereContrat.equals("avenantClient.statut")) {
			request.getSession().setAttribute("sel_statut", "selected");
			request.getSession().setAttribute("sel_version", "");
			request.getSession().setAttribute("sel_type_contrat", "");
			request.getSession().setAttribute("sel_client", "");
		} else {
			request.getSession().setAttribute("sel_type_contrat", "selected");
			request.getSession().setAttribute("sel_version", "");
			request.getSession().setAttribute("sel_client", "");
			request.getSession().setAttribute("sel_statut", "");
		}
		// ------------------- Définition de la valeur selectionné dans la barre de
		// recherche -------

		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_contrat/gestionContrat.jsp")
				.forward(request, response);
	}
	// ============ DO GET =======================================================

	// ============ DO POST ======================================================
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	// ============ DO POST ======================================================

	// Méthode utilitaire pour créer un statut sous forme de Map
	private static Map<String, String> createStatut(String tagStatut, String selectStatut) {
		Map<String, String> statut = new HashMap<>();
		statut.put("tag_statut", tagStatut);
		statut.put("select_statut", selectStatut);
		return statut;
	}

}
