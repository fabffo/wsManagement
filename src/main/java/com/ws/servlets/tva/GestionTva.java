/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TVA GESTION                                     ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.tva;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.ParametreDao;
import com.ws.Dao.TvaDao;
import com.ws.beans.Parametre_detail;
import com.ws.beans.Parametre_entete;
import com.ws.beans.Tva;
import com.ws.forms.tva.GestionTvaPagination;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GestionTva extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TvaDao tvaDao;
	private ParametreDao parametreDao;
	public static final String MODIFIER = "MODIFIER";
	public static final String SUPPRIMER = "SUPPRIMER";
	public static final String AJOUTER = "AJOUTER";
	public static final String VISUALISER = "VISUALISER";

	public static final String TRIA = "TRIA";

	public static final String ATT_TVA = "tva";
	public static final String ATT_FORM = "form";

	public static final  String VUE_SUCCES="/WEB-INF/JSP_tva/gestionTva.jsp";
	public static final  String VUE_FORM ="/WEB-INF/JSP_tva/gestionTva.jsp";

	public DaoFactory daoFactory;

	public void init() throws ServletException {
		daoFactory = DaoFactory.getInstance();
		this.tvaDao = daoFactory.getTvaDao();
		this.parametreDao = daoFactory.getParametreDao();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if ("AZERO".equals(action)) {
		System.out.println("oghjmfldskfmsdjkdsm");
		request.getSession().setAttribute("searchTva", null);
		request.getSession().setAttribute("critereTva", null);
		request.setAttribute("searchTva", null);
		request.setAttribute("critereTva", null);
		}
		int currentPage = 1; // page en cours*
		int recordsPerPage = 6; // nbr enregistrements par page
		int maxPage = 0; // fin pagination
		String triTva;
		String select_triTva = "";
		String triTva_id;
		String triTva_nom = "bi bi-caret-up text-white";
		String triTva_libelle = "bi bi-caret-up text-white";
		String triTva_taux = "bi bi-caret-up text-white";
		String select_like;
		String searchTva = "";
		String critereTva = "nom";
		List<Tva> list;


		// Gestion du tri des colonnes
		// ---------------------------------------------------------------------------------//
		/* Préparation de l'objet pagination */
		GestionTvaPagination form = new GestionTvaPagination();

		/* Traitement de la requête et récupération du bean en résultant */
		Map<String, String> pagination = form.paginerTva(request);

		triTva = pagination.get("triTva");
		// Initialisation par défaut
		triTva_id = "bi bi-caret-down text-white";
		triTva_nom = "bi bi-caret-down text-white";
		triTva_libelle = "bi bi-caret-down text-white";
		triTva_taux = "bi bi-caret-down text-white";

		// Vérification du triTva et attribution des valeurs appropriées
		if (triTva.equals("id")) {
			triTva_id = pagination.get("triTva_cle");
		} else if (triTva.equals("nom")) {
			triTva_nom = pagination.get("triTva_cle");
		} else if (triTva.equals("libelle")) {
			triTva_libelle = pagination.get("triTva_cle");
		} else if (triTva.equals("taux")) {
			triTva_taux = pagination.get("triTva_cle");
		}
		select_triTva = pagination.get("select_triTva");
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
		if (!"AZERO".equals(action)) {
		if (request.getParameter("critereTva")!=null) {
			critereTva = request.getParameter("critereTva");
		}else {
			if (request.getSession().getAttribute("critereTva") != null) {
				critereTva = (String) request.getSession().getAttribute("critereTva");
			}
		}
		if (request.getParameter("searchTva")!=null) {
			searchTva = request.getParameter("searchTva");
			currentPage = 1; // On met la page courante à 1 pour une nouvelle recherche
		}else {
			if (request.getSession().getAttribute("searchTva") != null) {
				searchTva = (String) request.getSession().getAttribute("searchTva") ;
			}
		}
	}

		if (searchTva != null) {
			System.out.println("searchtva non null");
			if (searchTva.isEmpty()) {
				list = tvaDao.rechercheTvas((currentPage - 1) * recordsPerPage, recordsPerPage, select_triTva);
				request.getSession().setAttribute("searchTva", "");
			} else {
				request.getSession().setAttribute("searchTva", searchTva);
				request.getSession().setAttribute("critereTva", critereTva);
				select_like = critereTva + " like '" + searchTva + "%'";
				list = tvaDao.rechercheLikeTvas((currentPage - 1) * recordsPerPage, recordsPerPage, select_triTva,
						select_like);
			}
		} else {
			System.out.println("searchtva null");
			list = tvaDao.rechercheTvas((currentPage - 1) * recordsPerPage, recordsPerPage, select_triTva);
		}
		int noOfRecords = tvaDao.getNoOfRecords(); // nombre enregistrements total
		// ---------------------------------------------------------------------------------//


		// on inverse le triTva pour permettre le changement lors de l'appui sur la
		// flèche
		// ---------------------------------------------------------------------------------//
		toggleTriTvaAttribute(request, "triTva_id", triTva_id);
		toggleTriTvaAttribute(request, "triTva_nom", triTva_nom);
		toggleTriTvaAttribute(request, "triTva_libelle", triTva_libelle);
		toggleTriTvaAttribute(request, "triTva_taux", triTva_taux);
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
		request.setAttribute("TvaList", list); // liste enregistrements
		request.setAttribute("noOfPages", noOfPages); // paramètre nombres de pages
		request.setAttribute("currentPage", currentPage); // paramètre page courante
		request.setAttribute("noBegin", noBegin); // paramètre début pagination
		request.setAttribute("maxPage", maxPage); // paramètre fin pagination
		request.setAttribute("pasPage", pasPage); // paramètre pas de la pagination
		request.setAttribute("searchTva", searchTva); // paramètre searchTva


		// Maj attribut de session critère tva
		// ---------------------------------------------------------------------------------//
		updateSessionAttributes(request, critereTva);
		// ---------------------------------------------------------------------------------//

		this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	// Méthode pour maj des attributs de session critereTva
	public void updateSessionAttributes(HttpServletRequest request, String critereTva) {
		// Réinitialisation des attributs de session
		request.getSession().setAttribute("sel_nom", "");
		request.getSession().setAttribute("sel_libelle", "");
		request.getSession().setAttribute("sel_taux", "");

		// Mise à jour de l'attribut sélectionné en fonction de critereTva
		switch (critereTva) {
		case "nom":
			request.getSession().setAttribute("sel_nom", "selected");
			break;
		case "libelle":
			request.getSession().setAttribute("sel_libelle", "selected");
			break;
		case "taux":
			request.getSession().setAttribute("sel_taux", "selected");
			break;
		default:
			request.getSession().setAttribute("sel_libelle", "selected");
			break;
		}
	}


	// Méthode pour basculer l'attribut de tri
	private void toggleTriTvaAttribute(HttpServletRequest request, String attributeName, String attributeValue) {
	    String newValue = attributeValue.equals("bi bi-caret-up text-white") ? "bi bi-caret-down text-white" : "bi bi-caret-up text-white";
	    request.setAttribute(attributeName, newValue);
	}



}
