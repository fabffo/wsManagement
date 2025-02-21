/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET ECRITURE BANQUE IMPORT VISUALISATION                  ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.ecritureBanque;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.EcritureBanque_importDao;
import com.ws.beans.EcritureBanque_detail;
import com.ws.beans.EcritureBanque_import;
import com.ws.forms.ecritureBanque.GestionEcritureBanque_importPagination;
import com.ws.forms.ecritureBanque.VisualisationEcritureBanque_importPagination;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class VisualisationEcritureBanque_import extends HttpServlet {

	private static final long serialVersionUDATE_ECRITURE = 1L;
	private EcritureBanque_importDao ecritureBanque_importDao;
	public static final String MODIFIER = "MODIFIER";
	public static final String SUPPRIMER = "SUPPRIMER";
	public static final String AJOUTER = "AJOUTER";
	public static final String VISUALISER = "VISUALISER";

	public static final String TRIA = "TRIA";

	public static final String ATT_TVA = "ecritureBanque_import";
	public static final String ATT_FORM = "form";

	public static final String VUE_SUCCES = "/WEB-INF/JSP_ecritureBanque/VisualisationEcritureBanque_import.jsp";
	public static final String VUE_FORM = "/WEB-INF/JSP_ecritureBanque/VisualisationEcritureBanque_import.jsp";

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
		String triVisualisationEcritureBanque_import;
		String select_triVisualisationEcritureBanque_import = "";
		String triVisualisationEcritureBanque_numero_ligne;
		String triVisualisationEcritureBanque_date_ecriture;
		String triVisualisationEcritureBanque_libelle_ecriture = "bi bi-caret-up text-white";
		String triVisualisationEcritureBanque_debit = "bi bi-caret-up text-white";
		String triVisualisationEcritureBanque_credit = "bi bi-caret-up text-white";
		String select_like;
		String searchVisualisationEcritureBanque_import = "";
		String critereVisualisationEcritureBanque_import = "nom";
		List<EcritureBanque_detail> list;
		int id_ecritureBanque = 0;

		// id
		String idEcritureBanqueParam = request.getParameter("id_ecritureBanque");

		if (idEcritureBanqueParam != null) {  // Vérifier si le paramètre n'est pas nul
		    id_ecritureBanque = Integer.parseInt(idEcritureBanqueParam);
		    request.getSession().setAttribute("id_ecritureBanque", id_ecritureBanque);
		} else {
		    Object sessionAttribute = request.getSession().getAttribute("id_ecritureBanque");
		    if (sessionAttribute != null) {  // Vérifier si l'attribut de session n'est pas nul
		        id_ecritureBanque = Integer.parseInt(sessionAttribute.toString()); // Convertir l'objet en String et le parser
		    }
		}



		// Visualisation du tri des colonnes
		// ---------------------------------------------------------------------------------//
		/* Préparation de l'objet pagination */
		VisualisationEcritureBanque_importPagination form = new VisualisationEcritureBanque_importPagination();

		/* Traitement de la requête et récupération du bean en résultant */
		Map<String, String> pagination = form.paginerVisualisationEcritureBanque_import(request);

		triVisualisationEcritureBanque_import = pagination.get("triVisualisationEcritureBanque_import");
		// Initialisation par défaut
		triVisualisationEcritureBanque_numero_ligne = "bi bi-caret-down text-white";
		triVisualisationEcritureBanque_date_ecriture = "bi bi-caret-down text-white";
		triVisualisationEcritureBanque_libelle_ecriture = "bi bi-caret-down text-white";
		triVisualisationEcritureBanque_debit = "bi bi-caret-down text-white";
		triVisualisationEcritureBanque_credit = "bi bi-caret-down text-white";

		// Vérification du triVisualisationEcritureBanque_import et attribution des valeurs appropriées
		if (triVisualisationEcritureBanque_import.equals("DATE_ECRITURE")) {
			triVisualisationEcritureBanque_date_ecriture = pagination.get("triVisualisationEcritureBanque_import_cle");
		} else if (triVisualisationEcritureBanque_import.equals("LIBELLE_ECRITURE")) {
			System.out.println(triVisualisationEcritureBanque_import);
			triVisualisationEcritureBanque_libelle_ecriture = pagination.get("triVisualisationEcritureBanque_import_cle");
			System.out.println(triVisualisationEcritureBanque_libelle_ecriture);
		} else if (triVisualisationEcritureBanque_import.equals("DEBIT")) {
			triVisualisationEcritureBanque_debit = pagination.get("triVisualisationEcritureBanque_import_cle");
		} else if (triVisualisationEcritureBanque_import.equals("CREDIT")) {
			triVisualisationEcritureBanque_credit = pagination.get("triVisualisationEcritureBanque_import_cle");
		}else if (triVisualisationEcritureBanque_import.equals("NUMERO_LIGNE")) {
			triVisualisationEcritureBanque_numero_ligne = pagination.get("triVisualisationEcritureBanque_import_cle");
		}
		select_triVisualisationEcritureBanque_import = pagination.get("select_triVisualisationEcritureBanque_import");
		// ---------------------------------------------------------------------------------//


		// Visualisation de la page courante
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
		if (request.getParameter("critereVisualisationEcritureBanque_import")!=null) {
			critereVisualisationEcritureBanque_import = request.getParameter("critereVisualisationEcritureBanque_import");
			System.out.println("critere"+critereVisualisationEcritureBanque_import);
		}else {
			if (request.getSession().getAttribute("critereVisualisationEcritureBanque_import") != null) {
				critereVisualisationEcritureBanque_import = (String) request.getSession().getAttribute("critereVisualisationEcritureBanque_import");
			}
		}
		if (request.getParameter("searchVisualisationEcritureBanque_import")!=null) {
			searchVisualisationEcritureBanque_import = request.getParameter("searchVisualisationEcritureBanque_import");
			currentPage = 1; // On met la page courante à 1 pour une nouvelle recherche
		}else {
			if (request.getSession().getAttribute("searchVisualisationEcritureBanque_import") != null) {
				searchVisualisationEcritureBanque_import = (String) request.getSession().getAttribute("searchVisualisationEcritureBanque_import") ;
			}
		}

		if (searchVisualisationEcritureBanque_import != null) {
			if (searchVisualisationEcritureBanque_import.isEmpty()) {
				list = ecritureBanque_importDao.rechercheVisualisationEcritureBanque_imports(id_ecritureBanque, (currentPage - 1) * recordsPerPage, recordsPerPage, select_triVisualisationEcritureBanque_import);
				request.getSession().setAttribute("searchVisualisationEcritureBanque_import", "");
			} else {
				request.getSession().setAttribute("searchVisualisationEcritureBanque_import", searchVisualisationEcritureBanque_import);
				request.getSession().setAttribute("critereVisualisationEcritureBanque_import", critereVisualisationEcritureBanque_import);
				select_like = critereVisualisationEcritureBanque_import + " like '" + searchVisualisationEcritureBanque_import + "%'";
				list = ecritureBanque_importDao.rechercheLikeVisualisationEcritureBanque_imports(id_ecritureBanque, (currentPage - 1) * recordsPerPage, recordsPerPage, select_triVisualisationEcritureBanque_import,
						select_like);
			}
		} else {
			list = ecritureBanque_importDao.rechercheVisualisationEcritureBanque_imports(id_ecritureBanque, (currentPage - 1) * recordsPerPage, recordsPerPage, select_triVisualisationEcritureBanque_import);
		}
		int noOfRecords = ecritureBanque_importDao.getNoOfRecords(); // nombre enregistrements total
		// ---------------------------------------------------------------------------------//


		// on inverse le triVisualisationEcritureBanque_import pour permettre le changement lors de l'appui sur la
		// flèche
		// ---------------------------------------------------------------------------------//
		toggleTriEcritureBanque_importAttribute(request, "triVisualisationEcritureBanque_numero_ligne", triVisualisationEcritureBanque_numero_ligne);
		toggleTriEcritureBanque_importAttribute(request, "triVisualisationEcritureBanque_date_ecriture", triVisualisationEcritureBanque_date_ecriture);
		toggleTriEcritureBanque_importAttribute(request, "triVisualisationEcritureBanque_libelle_ecriture", triVisualisationEcritureBanque_libelle_ecriture);
		toggleTriEcritureBanque_importAttribute(request, "triVisualisationEcritureBanque_debit", triVisualisationEcritureBanque_debit);
		toggleTriEcritureBanque_importAttribute(request, "triVisualisationEcritureBanque_credit", triVisualisationEcritureBanque_credit);
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
		request.setAttribute("searchVisualisationEcritureBanque_import", searchVisualisationEcritureBanque_import); // paramètre searchVisualisationEcritureBanque_import


		// Maj attribut de session critère ecritureBanque_import
		// ---------------------------------------------------------------------------------//
		updateSessionAttributes(request, critereVisualisationEcritureBanque_import);
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

	// Méthode pour maj des attributs de session critereVisualisationEcritureBanque_import
	public void updateSessionAttributes(HttpServletRequest request, String critereVisualisationEcritureBanque_import) {
		// Réinitialisation des attributs de session
		request.getSession().setAttribute("sel_numero_ligne", "");
		request.getSession().setAttribute("sel_date_ecriture", "");
		request.getSession().setAttribute("sel_libelle_ecriture", "");

		// Mise à jour de l'attribut sélectionné en fonction de critereVisualisationEcritureBanque_import
		switch (critereVisualisationEcritureBanque_import) {
		case "date_ecriture":
			request.getSession().setAttribute("sel_date_ecriture", "selected");
			break;
		case "numero_ligne":
			request.getSession().setAttribute("sel_numero_ligne", "selected");
			break;
		case "libelle_ecriture":
			request.getSession().setAttribute("sel_libelle_ecriture", "selected");
			break;
		default:
			request.getSession().setAttribute("sel_numero_ligne", "selected");
			break;
		}
	}


	// Méthode pour basculer l'attribut de tri
	private void toggleTriEcritureBanque_importAttribute(HttpServletRequest request, String attributeName, String attributeValue) {
	    String newValue = attributeValue.equals("bi bi-caret-up text-white") ? "bi bi-caret-down text-white" : "bi bi-caret-up text-white";
	    request.setAttribute(attributeName, newValue);
	}

}
