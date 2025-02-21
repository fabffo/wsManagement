/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET ECRITURE BANQUE IMPORT GESTION                  ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.ecritureBanque;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.EcritureBanque_importDao;
import com.ws.beans.EcritureBanque_import;
import com.ws.forms.ecritureBanque.GestionEcritureBanque_importPagination;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GestionEcritureBanque_import extends HttpServlet {

	private static final long serialVersionUDATE = 1L;
	private EcritureBanque_importDao ecritureBanque_importDao;
	public static final String MODIFIER = "MODIFIER";
	public static final String SUPPRIMER = "SUPPRIMER";
	public static final String AJOUTER = "AJOUTER";
	public static final String VISUALISER = "VISUALISER";

	public static final String TRIA = "TRIA";

	public static final String ATT_TVA = "ecritureBanque_import";
	public static final String ATT_FORM = "form";

	public static final String VUE_SUCCES = "/WEB-INF/JSP_ecritureBanque/GestionEcritureBanque_import.jsp";
	public static final String VUE_FORM = "/WEB-INF/JSP_ecritureBanque/GestionEcritureBanque_import.jsp";

	public DaoFactory daoFactory;

	public void init() throws ServletException {
		daoFactory = DaoFactory.getInstance();
		this.ecritureBanque_importDao = daoFactory.getEcritureBanque_importDao();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int currentPage = 1; // page en cours*
		int recordsPerPage = 6; // nbr enregistrements par page
		int maxPage = 0; // fin pagination
		String triEcritureBanque_import;
		String select_triEcritureBanque_import = "";
		String triEcritureBanque_import_date;
		String triEcritureBanque_import_nom = "bi bi-caret-up text-white";
		String triEcritureBanque_import_user = "bi bi-caret-up text-white";
		String triEcritureBanque_import_lignes = "bi bi-caret-up text-white";
		String select_like;
		String searchEcritureBanque_import = "";
		String critereEcritureBanque_import = "nom";
		List<EcritureBanque_import> list;


		// Gestion du tri des colonnes
		// ---------------------------------------------------------------------------------//
		/* Préparation de l'objet pagination */
		GestionEcritureBanque_importPagination form = new GestionEcritureBanque_importPagination();

		/* Traitement de la requête et récupération du bean en résultant */
		Map<String, String> pagination = form.paginerEcritureBanque_import(request);

		triEcritureBanque_import = pagination.get("triEcritureBanque_import");
		// Initialisation par défaut
		triEcritureBanque_import_date = "bi bi-caret-down text-white";
		triEcritureBanque_import_nom = "bi bi-caret-down text-white";
		triEcritureBanque_import_user = "bi bi-caret-down text-white";
		triEcritureBanque_import_lignes = "bi bi-caret-down text-white";

		// Vérification du triEcritureBanque_import et attribution des valeurs appropriées
		if (triEcritureBanque_import.equals("DATE")) {
			triEcritureBanque_import_date = pagination.get("triEcritureBanque_import_cle");
		} else if (triEcritureBanque_import.equals("NOM")) {
			triEcritureBanque_import_nom = pagination.get("triEcritureBanque_import_cle");
		} else if (triEcritureBanque_import.equals("USER")) {
			triEcritureBanque_import_user = pagination.get("triEcritureBanque_import_cle");
		} else if (triEcritureBanque_import.equals("LIGNES")) {
			triEcritureBanque_import_lignes = pagination.get("triEcritureBanque_import_cle");
		}
		select_triEcritureBanque_import = pagination.get("select_triEcritureBanque_import");
		// ---------------------------------------------------------------------------------//


		// Gestion de la page courante
		// ---------------------------------------------------------------------------------//
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} else {
			if (request.getSession().getAttribute("currentPage") != null)
				currentPage = Integer.parseInt((String) request.getSession().getAttribute("currentPage"));

		}
		// ---------------------------------------------------------------------------------//


		// récupération recherche de la page
		// ---------------------------------------------------------------------------------//
		if (request.getParameter("critereEcritureBanque_import")!=null) {
			critereEcritureBanque_import = request.getParameter("critereEcritureBanque_import");
		}else {
			if (request.getSession().getAttribute("critereEcritureBanque_import") != null) {
				critereEcritureBanque_import = (String) request.getSession().getAttribute("critereEcritureBanque_import");
			}
		}
		if (request.getParameter("searchEcritureBanque_import")!=null) {
			searchEcritureBanque_import = request.getParameter("searchEcritureBanque_import");
			currentPage = 1; // On met la page courante à 1 pour une nouvelle recherche
		}else {
			if (request.getSession().getAttribute("searchEcritureBanque_import") != null) {
				searchEcritureBanque_import = (String) request.getSession().getAttribute("searchEcritureBanque_import") ;
			}
		}

		if (searchEcritureBanque_import != null) {
			if (searchEcritureBanque_import.isEmpty()) {
				list = ecritureBanque_importDao.rechercheEcritureBanque_imports((currentPage - 1) * recordsPerPage, recordsPerPage, select_triEcritureBanque_import);
				request.getSession().setAttribute("searchEcritureBanque_import", "");
			} else {
				request.getSession().setAttribute("searchEcritureBanque_import", searchEcritureBanque_import);
				request.getSession().setAttribute("critereEcritureBanque_import", critereEcritureBanque_import);
				select_like = critereEcritureBanque_import + " like '" + searchEcritureBanque_import + "%'";
				list = ecritureBanque_importDao.rechercheLikeEcritureBanque_imports((currentPage - 1) * recordsPerPage, recordsPerPage, select_triEcritureBanque_import,
						select_like);
			}
		} else {
			list = ecritureBanque_importDao.rechercheEcritureBanque_imports((currentPage - 1) * recordsPerPage, recordsPerPage, select_triEcritureBanque_import);
		}
		int noOfRecords = ecritureBanque_importDao.getNoOfRecords(); // nombre enregistrements total
		// ---------------------------------------------------------------------------------//


		// on inverse le triEcritureBanque_import pour permettre le changement lors de l'appui sur la
		// flèche
		// ---------------------------------------------------------------------------------//
		toggleTriEcritureBanque_importAttribute(request, "triEcritureBanque_import_date", triEcritureBanque_import_date);
		toggleTriEcritureBanque_importAttribute(request, "triEcritureBanque_import_nom", triEcritureBanque_import_nom);
		toggleTriEcritureBanque_importAttribute(request, "triEcritureBanque_import_user", triEcritureBanque_import_user);
		toggleTriEcritureBanque_importAttribute(request, "triEcritureBanque_import_lignes", triEcritureBanque_import_lignes);
		// ---------------------------------------------------------------------------------//


		// récupération infos n° page
		// ---------------------------------------------------------------------------------//
		// Calcul du nombre de pages nécessaires
		int noOfPages = (int) Math.ceil((double) noOfRecords / recordsPerPage);

		// Définition des valeurs par défaut
		int noBegin = 1; // Début de la pagination
		int pasPage = 2; // Pas de la pagination
		maxPage = (maxPage == 0) ? pasPage : maxPage; // Fin de la pagination

		// Mise à jour des valeurs en fonction des paramètres de la requête
		if (request.getParameter("noBegin") != null) {
			noBegin = Integer.parseInt(request.getParameter("noBegin"));
		}

		if (request.getParameter("maxPage") != null) {
			maxPage = Integer.parseInt(request.getParameter("maxPage"));
		}
		// ---------------------------------------------------------------------------------//


		// maj des attributs de request
		// ---------------------------------------------------------------------------------//
		request.setAttribute("EcritureBanque_importList", list); // liste enregistrements
		request.setAttribute("noOfPages", noOfPages); // paramètre nombres de pages
		request.setAttribute("currentPage", currentPage); // paramètre page courante
		request.setAttribute("noBegin", noBegin); // paramètre début pagination
		request.setAttribute("maxPage", maxPage); // paramètre fin pagination
		request.setAttribute("pasPage", pasPage); // paramètre pas de la pagination
		request.setAttribute("searchEcritureBanque_import", searchEcritureBanque_import); // paramètre searchEcritureBanque_import


		// Maj attribut de session critère ecritureBanque_import
		// ---------------------------------------------------------------------------------//
		updateSessionAttributes(request, critereEcritureBanque_import);
		// ---------------------------------------------------------------------------------//

		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	// Méthode pour maj des attributs de session critereEcritureBanque_import
	public void updateSessionAttributes(HttpServletRequest request, String critereEcritureBanque_import) {
		// Réinitialisation des attributs de session
		request.getSession().setAttribute("sel_nom", "");
		request.getSession().setAttribute("sel_user", "");
		request.getSession().setAttribute("sel_lignes", "");

		// Mise à jour de l'attribut sélectionné en fonction de critereEcritureBanque_import
		switch (critereEcritureBanque_import) {
		case "date":
			request.getSession().setAttribute("sel_date", "selected");
			break;
		case "nom":
			request.getSession().setAttribute("sel_nom", "selected");
			break;
		case "user":
			request.getSession().setAttribute("sel_user", "selected");
			break;
		case "lignes":
			request.getSession().setAttribute("sel_lignes", "selected");
			break;
		default:
			request.getSession().setAttribute("sel_user", "selected");
			break;
		}
	}


	// Méthode pour basculer l'attribut de tri
	private void toggleTriEcritureBanque_importAttribute(HttpServletRequest request, String attributeName, String attributeValue) {
	    String newValue = attributeValue.equals("bi bi-caret-up text-white") ? "bi bi-caret-down text-white" : "bi bi-caret-up text-white";
	    request.setAttribute(attributeName, newValue);
	}

}
