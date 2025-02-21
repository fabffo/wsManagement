/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET ECRITURE BANQUE IMPORT GESTION                  ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.factureAchat;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.FactureAchatDao;
import com.ws.beans.FactureAchat;
import com.ws.forms.factureAchat.GestionFactureAchatPagination;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GestionFactureAchat extends HttpServlet {

	private static final long serialVersionUDATE = 1L;
	private FactureAchatDao factureAchatDao;
	public static final String MODIFIER = "MODIFIER";
	public static final String SUPPRIMER = "SUPPRIMER";
	public static final String AJOUTER = "AJOUTER";
	public static final String VISUALISER = "VISUALISER";

	public static final String TRIA = "TRIA";

	public static final String ATT_TVA = "factureAchat";
	public static final String ATT_FORM = "form";

	public static final String VUE_SUCCES = "/WEB-INF/JSP_factureAchat/GestionFactureAchat.jsp";
	public static final String VUE_FORM = "/WEB-INF/JSP_factureAchat/GestionFactureAchat.jsp";

	public DaoFactory daoFactory;

	public void init() throws ServletException {
		daoFactory = DaoFactory.getInstance();
		this.factureAchatDao = daoFactory.getFactureAchatDao();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int currentPage = 1; // page en cours*
		int recordsPerPage = 6; // nbr enregistrements par page
		int maxPage = 0; // fin pagination
		String triFactureAchat;
		String select_triFactureAchat = "";
		String triFactureAchat_date;
		String triFactureAchat_nom = "bi bi-caret-up text-white";
		String triFactureAchat_user = "bi bi-caret-up text-white";
		String triFactureAchat_lignes = "bi bi-caret-up text-white";
		String select_like;
		String searchFactureAchat = "";
		String critereFactureAchat = "nom";
		List<FactureAchat> list;


		// Gestion du tri des colonnes
		// ---------------------------------------------------------------------------------//
		/* Préparation de l'objet pagination */
		GestionFactureAchatPagination form = new GestionFactureAchatPagination();

		/* Traitement de la requête et récupération du bean en résultant */
		Map<String, String> pagination = form.paginerFactureAchat(request);

		triFactureAchat = pagination.get("triFactureAchat");
		// Initialisation par défaut
		triFactureAchat_date = "bi bi-caret-down text-white";
		triFactureAchat_nom = "bi bi-caret-down text-white";
		triFactureAchat_user = "bi bi-caret-down text-white";
		triFactureAchat_lignes = "bi bi-caret-down text-white";

		// Vérification du triFactureAchat et attribution des valeurs appropriées
		if (triFactureAchat.equals("DATE")) {
			triFactureAchat_date = pagination.get("triFactureAchat_cle");
		} else if (triFactureAchat.equals("NOM")) {
			triFactureAchat_nom = pagination.get("triFactureAchat_cle");
		} else if (triFactureAchat.equals("USER")) {
			triFactureAchat_user = pagination.get("triFactureAchat_cle");
		} else if (triFactureAchat.equals("LIGNES")) {
			triFactureAchat_lignes = pagination.get("triFactureAchat_cle");
		}
		select_triFactureAchat = pagination.get("select_triFactureAchat");
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
		if (request.getParameter("critereFactureAchat")!=null) {
			critereFactureAchat = request.getParameter("critereFactureAchat");
		}else {
			if (request.getSession().getAttribute("critereFactureAchat") != null) {
				critereFactureAchat = (String) request.getSession().getAttribute("critereFactureAchat");
			}
		}
		if (request.getParameter("searchFactureAchat")!=null) {
			searchFactureAchat = request.getParameter("searchFactureAchat");
			currentPage = 1; // On met la page courante à 1 pour une nouvelle recherche
		}else {
			if (request.getSession().getAttribute("searchFactureAchat") != null) {
				searchFactureAchat = (String) request.getSession().getAttribute("searchFactureAchat") ;
			}
		}

		if (searchFactureAchat != null) {
			if (searchFactureAchat.isEmpty()) {
				list = factureAchatDao.rechercheFactureAchats((currentPage - 1) * recordsPerPage, recordsPerPage, select_triFactureAchat);
				request.getSession().setAttribute("searchFactureAchat", "");
			} else {
				request.getSession().setAttribute("searchFactureAchat", searchFactureAchat);
				request.getSession().setAttribute("critereFactureAchat", critereFactureAchat);
				select_like = critereFactureAchat + " like '" + searchFactureAchat + "%'";
				list = factureAchatDao.rechercheLikeFactureAchats((currentPage - 1) * recordsPerPage, recordsPerPage, select_triFactureAchat,
						select_like);
			}
		} else {
			list = factureAchatDao.rechercheFactureAchats((currentPage - 1) * recordsPerPage, recordsPerPage, select_triFactureAchat);
		}
		int noOfRecords = factureAchatDao.getNoOfRecords(); // nombre enregistrements total
		// ---------------------------------------------------------------------------------//


		// on inverse le triFactureAchat pour permettre le changement lors de l'appui sur la
		// flèche
		// ---------------------------------------------------------------------------------//
		toggleTriFactureAchatAttribute(request, "triFactureAchat_date", triFactureAchat_date);
		toggleTriFactureAchatAttribute(request, "triFactureAchat_nom", triFactureAchat_nom);
		toggleTriFactureAchatAttribute(request, "triFactureAchat_user", triFactureAchat_user);
		toggleTriFactureAchatAttribute(request, "triFactureAchat_lignes", triFactureAchat_lignes);
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
		request.setAttribute("FactureAchatList", list); // liste enregistrements
		request.setAttribute("noOfPages", noOfPages); // paramètre nombres de pages
		request.setAttribute("currentPage", currentPage); // paramètre page courante
		request.setAttribute("noBegin", noBegin); // paramètre début pagination
		request.setAttribute("maxPage", maxPage); // paramètre fin pagination
		request.setAttribute("pasPage", pasPage); // paramètre pas de la pagination
		request.setAttribute("searchFactureAchat", searchFactureAchat); // paramètre searchFactureAchat


		// Maj attribut de session critère factureAchat
		// ---------------------------------------------------------------------------------//
		updateSessionAttributes(request, critereFactureAchat);
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

	// Méthode pour maj des attributs de session critereFactureAchat
	public void updateSessionAttributes(HttpServletRequest request, String critereFactureAchat) {
		// Réinitialisation des attributs de session
		request.getSession().setAttribute("sel_nom", "");
		request.getSession().setAttribute("sel_user", "");
		request.getSession().setAttribute("sel_lignes", "");

		// Mise à jour de l'attribut sélectionné en fonction de critereFactureAchat
		switch (critereFactureAchat) {
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
	private void toggleTriFactureAchatAttribute(HttpServletRequest request, String attributeName, String attributeValue) {
	    String newValue = attributeValue.equals("bi bi-caret-up text-white") ? "bi bi-caret-down text-white" : "bi bi-caret-up text-white";
	    request.setAttribute(attributeName, newValue);
	}

}
