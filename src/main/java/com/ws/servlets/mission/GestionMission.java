/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET CONTRATCLIENT GESTION                           ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.mission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ws.Dao.MissionDao;
import com.ws.Dao.DaoFactory;
import com.ws.beans.Mission;
import com.ws.forms.mission.GestionMissionPagination;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GestionMission extends HttpServlet {

	// Initialisation de variables
	private static final long serialVersionUID = 1L;
	private MissionDao missionDao;
	public static final String MODIFIER = "MODIFIER";
	public static final String SUPPRIMER = "SUPPRIMER";
	public static final String AJOUTER = "AJOUTER";
	public static final String VISUALISER = "VISUALISER";
	public static final String TRIA = "TRIA";
	public static final String ATT_CONTRATCLIENT = "mission";
	public static final String ATT_FORM = "form";
	public static final String VUE_SUCCES = "/WEB-INF/JSP_mission/gestionMission.jsp";
	public static final String VUE_FORM = "/WEB-INF/JSP_mission/ajoutMission.jsp";
	public DaoFactory daoFactory;

	public void init() throws ServletException {
		daoFactory = DaoFactory.getInstance();
		this.missionDao = daoFactory.getMissionDao();
	}

	// ============ DO GET =======================================================
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Initialisation de variables
		int currentPage = 1; // page en cours*
		int recordsPerPage = 6; // nbr enregistrements par page
		int maxPage = 0; // fin pagination
		String triMission;
		String select_triMission = "";
		String triMission_id = "bi bi-caret-up text-white";
		String triMission_nom="bi bi-caret-up text-white";
		String triMission_id_contrat="bi bi-caret-up text-white";
		String triMission_type_contrat = "bi bi-caret-up text-white";
		String triMission_societe = "bi bi-caret-up text-white";
		String triMission_statut = "bi bi-caret-up text-white";
		String select_like;
		String searchMission = "";
		String critereMission = "";
		List<Mission> list;
		String debutsession = "debutsession";
		String tag_statut;

		// gestion du paramètre typeSocieté pour filter les missions
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

		// initialisation selection tag statut
		// ----------------------------------------------------
		if (request.getParameter("tag_statut") != null) {
			tag_statut = request.getParameter("tag_statut");
			request.getSession().setAttribute("tag_statut", tag_statut);
		} else if (request.getSession().getAttribute("tag_statut") != null) {
			tag_statut = (String) request.getSession().getAttribute("tag_statut");
		} else {
			tag_statut = "En-cours";
		}
		if (tag_statut.equals("")) {
			tag_statut = "En-cours";
		}
		request.getSession().setAttribute("tag_statut", tag_statut);

		// Créer la liste de statuts
		List<Map<String, String>> listStatuts = new ArrayList<>();

		// Ajouter les éléments à la liste
		if (tag_statut.equals("En-cours")) {
			listStatuts.add(createStatut("En-cours", "selected"));
			listStatuts.add(createStatut("Terminé", ""));
			listStatuts.add(createStatut("Annulé", ""));
			listStatuts.add(createStatut("Tout", ""));
		}
		if (tag_statut.equals("Terminé")) {
			listStatuts.add(createStatut("Terminé", "selected"));
			listStatuts.add(createStatut("En-cours", ""));
			listStatuts.add(createStatut("Annulé", ""));
			listStatuts.add(createStatut("Tout", ""));
		}
		if (tag_statut.equals("Annulé")) {
			listStatuts.add(createStatut("Annulé", "selected"));
			listStatuts.add(createStatut("En-cours", ""));
			listStatuts.add(createStatut("Terminé", ""));
			listStatuts.add(createStatut("Tout", ""));
		}
		if (tag_statut.equals("Tout")) {
			listStatuts.add(createStatut("Tout", "selected"));
			listStatuts.add(createStatut("En-cours", ""));
			listStatuts.add(createStatut("Terminé", ""));
			listStatuts.add(createStatut("Annulé", ""));
		}
		request.getSession().setAttribute("listStatuts", listStatuts);

		/* Préparation de l'objet pagination */
		GestionMissionPagination form = new GestionMissionPagination();
		/* Traitement de la requête et récupération du bean en résultant */
		Map<String, String> pagination = form.paginerMission(request);

		// ----------------Définition du tri du mission ------------------------
		triMission = pagination.get("triMission");
		if (triMission.equals("ID")) {
			triMission_id = pagination.get("tri_cle");
		} else {
			triMission_id = "bi bi-caret-down text-white";
		}
		if (triMission.equals("NOM")) {
			triMission_nom = pagination.get("tri_cle");
		} else {
			triMission_nom = "bi bi-caret-down text-white";
		}
		if (triMission.equals("CONTRAT")) {
			triMission_id_contrat = pagination.get("tri_cle");
		} else {
			triMission_id_contrat = "bi bi-caret-down text-white";
		}
		if (triMission.equals("TYPE_CONTRAT")) {
			triMission_type_contrat = pagination.get("tri_cle");
		} else {
			triMission_type_contrat = "bi bi-caret-down text-white";
		}
		if (triMission.equals("CLIENT")) {
			triMission_societe = pagination.get("tri_cle");
		} else {
			triMission_societe = "bi bi-caret-down text-white";
		}

		if (triMission.equals("STATUT")) {
			triMission_statut = pagination.get("tri_cle");
		} else {
			triMission_statut = "bi bi-caret-down text-white";
		}

		select_triMission = pagination.get("select_triMission");
		// ----------------Définition du tri du mission ------------------------

		// ----------------Définition del la page courante ---------------------
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} else {
			if (request.getSession().getAttribute("currentPage") != null)
				currentPage = Integer.parseInt((String) request.getSession().getAttribute("currentPage"));

		}
		// ----------------Définition del la page courante ---------------------

		// ----------------Récupération recherche de la page ------------------
		if (request.getParameter("searchMission") != null) {
			if (request.getParameter("searchMission").equals("")) {
				list = missionDao.rechercheMissions((currentPage - 1) * recordsPerPage, recordsPerPage,
						select_triMission, tag_statut, type_entite);
				request.getSession().setAttribute("searchMission", "");
				select_like = "";
			} else {
				currentPage = 1; // On met la page courante à 1 pour une nouvelle recherche
				request.getSession().setAttribute("searchMission", request.getParameter("searchMission"));
				request.getSession().setAttribute("critereMission", request.getParameter("critereMission"));
				critereMission = request.getParameter("critereMission");
				searchMission = request.getParameter("searchMission");
				select_like = critereMission + " like '" + searchMission + "%'";
				list = missionDao.rechercheLikeMissions((currentPage - 1) * recordsPerPage, recordsPerPage,
						select_triMission, select_like, tag_statut, type_entite);
			}
		}

		else {
			if (request.getSession().getAttribute("searchMission") != null) {
				if (request.getSession().getAttribute("searchMission").equals("")) {
					list = missionDao.rechercheMissions((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triMission, tag_statut, type_entite);
					select_like = "";
				} else {
					searchMission = (String) request.getSession().getAttribute("searchMission");
					critereMission = (String) request.getSession().getAttribute("critereMission");
					select_like = critereMission + " like '" + searchMission + "%'";
					list = missionDao.rechercheLikeMissions((currentPage - 1) * recordsPerPage,
							recordsPerPage, select_triMission, select_like, tag_statut, type_entite);
				}
			} else {
				select_like = "";
				list = missionDao.rechercheMissions((currentPage - 1) * recordsPerPage, recordsPerPage,
						select_triMission, tag_statut, type_entite);
			}
		}
		// ----------------Récupération recherche de la page ------------------

		int noOfRecords = missionDao.getNoOfRecords(); // nombre enregistrements total

		// ---on inverse le triMission pour permettre le changement lors de
		// l'appui sur la flèche ---
		if (triMission_id_contrat.equals("bi bi-caret-up text-white")) {
			request.setAttribute("triMission_id_contrat", "bi bi-caret-down text-white");
		} else {
			request.setAttribute("triMission_id_contrat", "bi bi-caret-up text-white");
		}
		if (triMission_id.equals("bi bi-caret-up text-white")) {
			request.setAttribute("triMission_id", "bi bi-caret-down text-white");
		} else {
			request.setAttribute("triMission_id", "bi bi-caret-up text-white");
		}
		if (triMission_nom.equals("bi bi-caret-up text-white")) {
			request.setAttribute("triMission_nom", "bi bi-caret-down text-white");
		} else {
			request.setAttribute("triMission_nom", "bi bi-caret-up text-white");
		}
		if (triMission_type_contrat.equals("bi bi-caret-up text-white")) {
			request.setAttribute("triMission_type_contrat", "bi bi-caret-down text-white");
		} else {
			request.setAttribute("triMission_type_contrat", "bi bi-caret-up text-white");
		}
		if (triMission_societe.equals("bi bi-caret-up text-white")) {
			request.setAttribute("triMission_societe", "bi bi-caret-down text-white");
		} else {
			request.setAttribute("triMission_societe", "bi bi-caret-up text-white");
		}
		if (triMission_statut.equals("bi bi-caret-up text-white")) {
			request.setAttribute("triMission_statut", "bi bi-caret-down text-white");
		} else {
			request.setAttribute("triMission_statut", "bi bi-caret-up text-white");
		}
		// ---on inverse le triMission pour permettre le changement lors de
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
		request.setAttribute("MissionList", list); // liste enregistrements
		request.setAttribute("noOfPages", noOfPages); // paramètre nombres de pages
		request.setAttribute("currentPage", currentPage); // paramètre page courante
		request.setAttribute("noBegin", noBegin); // paramètre début pagination
		request.setAttribute("maxPage", maxPage); // paramètre fin pagination
		request.setAttribute("pasPage", pasPage); // paramètre pas de la pagination
		request.setAttribute("searchMission", searchMission); // paramètre searchMission
		request.setAttribute("tag_statut", tag_statut); // paramètre général pour la valeur du statut
		// ------------------- Récupération des paramètres de la page
		// ------------------------------

		// ------------------- Définition de la valeur selectionné dans la barre de
		// recherche -------
		if (critereMission.equals("avenantClient.id")) {
			request.getSession().setAttribute("sel_id", "selected");
			request.getSession().setAttribute("sel_type_mission", "");
			request.getSession().setAttribute("sel_client", "");
			request.getSession().setAttribute("sel_statut", "");
		} else if (critereMission.equals("avenantClient.type_mission")) {
			request.getSession().setAttribute("sel_type_mission", "selected");
			request.getSession().setAttribute("sel_version", "");
			request.getSession().setAttribute("sel_client", "");
			request.getSession().setAttribute("sel_statut", "");
		} else if (critereMission.equals("societe.raison_sociale")) {
			request.getSession().setAttribute("sel_client", "selected");
			request.getSession().setAttribute("sel_version", "");
			request.getSession().setAttribute("sel_type_mission", "");
			request.getSession().setAttribute("sel_statut", "");
		} else if (critereMission.equals("avenantClient.statut")) {
			request.getSession().setAttribute("sel_statut", "selected");
			request.getSession().setAttribute("sel_version", "");
			request.getSession().setAttribute("sel_type_mission", "");
			request.getSession().setAttribute("sel_client", "");
		} else {
			request.getSession().setAttribute("sel_type_mission", "selected");
			request.getSession().setAttribute("sel_version", "");
			request.getSession().setAttribute("sel_client", "");
			request.getSession().setAttribute("sel_statut", "");
		}
		// ------------------- Définition de la valeur selectionné dans la barre de
		// recherche -------

		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_mission/gestionMission.jsp")
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
