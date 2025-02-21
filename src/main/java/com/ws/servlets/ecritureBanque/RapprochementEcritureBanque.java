/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET ECRITURE BANQUE IMPORT VISUALISATION                  ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.ecritureBanque;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.EcritureBanque_importDao;
import com.ws.beans.EcritureBanque_detail;
import com.ws.beans.EcritureBanque_import;
import com.ws.forms.ecritureBanque.GestionEcritureBanque_importPagination;
import com.ws.forms.ecritureBanque.RapprochementEcritureBanquePagination;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RapprochementEcritureBanque extends HttpServlet {

	private static final long serialVersionUDATE_ECRITURE = 1L;
	private EcritureBanque_importDao ecritureBanque_importDao;
	public static final String MODIFIER = "MODIFIER";
	public static final String SUPPRIMER = "SUPPRIMER";
	public static final String AJOUTER = "AJOUTER";
	public static final String VISUALISER = "VISUALISER";

	public static final String TRIA = "TRIA";

	public static final String ATT_TVA = "ecritureBanque_import";
	public static final String ATT_FORM = "form";

	public static final String VUE_SUCCES = "/WEB-INF/JSP_ecritureBanque/RapprochementEcritureBanque.jsp";
	public static final String VUE_FORM = "/WEB-INF/JSP_ecritureBanque/RapprochementEcritureBanque.jsp";

	public DaoFactory daoFactory;

	int yearMonth;

	public void init() throws ServletException {
		daoFactory = DaoFactory.getInstance();
		this.ecritureBanque_importDao = daoFactory.getEcritureBanque_importDao();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int currentPage = 1; // page en cours*
		int recordsPerPage = 6; // nbr enregistrements par page
		int maxPage = 0; // fin pagination

		String triRapprochementEcritureBanque;
		String select_triRapprochementEcritureBanque = "";
		String triRapprochementEcritureBanque_date_ecriture;
		String triRapprochementEcritureBanque_entite;
		String triRapprochementEcritureBanque_nom_entite = "bi bi-caret-up text-white";
		String triRapprochementEcritureBanque_libelle_ecriture = "bi bi-caret-up text-white";
		String triRapprochementEcritureBanque_signe = "bi bi-caret-up text-white";
		String triRapprochementEcritureBanque_ttc = "bi bi-caret-up text-white";
		String triRapprochementEcritureBanque_statut_rapproche = "bi bi-caret-up text-white";
		String select_like;
		String searchRapprochementEcritureBanque = "";
		String critereRapprochementEcritureBanque = "nom";
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

		String yearMonthParam = request.getParameter("yearMonth");
		if (yearMonthParam != null) {  // Vérifier si le paramètre n'est pas nul
			yearMonth = Integer.parseInt(yearMonthParam);
		    request.getSession().setAttribute("yearMonth", yearMonth);
		} else {
		    Object sessionAttribute = request.getSession().getAttribute("yearMonth");
		    if (sessionAttribute != null) {  // Vérifier si l'attribut de session n'est pas nul
		    yearMonth = Integer.parseInt(sessionAttribute.toString()); // Convertir l'objet en String et le parser
		    }
		}

		 // Obtenir la date actuelle
        LocalDate currentDate = LocalDate.now();
        // Extraire l'année et le mois
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue(); // Le mois sous forme d'entier (1 pour janvier, 2 pour février, etc.)
        // Combiner l'année et le mois sous la forme AAAAMM
        yearMonth = year * 100 + month;




		// Rapprochement du tri des colonnes
		// ---------------------------------------------------------------------------------//
		/* Préparation de l'objet pagination */
		RapprochementEcritureBanquePagination form = new RapprochementEcritureBanquePagination();

		/* Traitement de la requête et récupération du bean en résultant */
		Map<String, String> pagination = form.paginerRapprochementEcritureBanque(request);

		triRapprochementEcritureBanque = pagination.get("triRapprochementEcritureBanque");
		// Initialisation par défaut
		triRapprochementEcritureBanque_entite = "bi bi-caret-down text-white";
		triRapprochementEcritureBanque_nom_entite = "bi bi-caret-down text-white";
		triRapprochementEcritureBanque_date_ecriture = "bi bi-caret-down text-white";
		triRapprochementEcritureBanque_libelle_ecriture = "bi bi-caret-down text-white";
		triRapprochementEcritureBanque_signe = "bi bi-caret-down text-white";
		triRapprochementEcritureBanque_ttc = "bi bi-caret-down text-white";
		triRapprochementEcritureBanque_statut_rapproche = "bi bi-caret-down text-white";


		// Vérification du triRapprochementEcritureBanque et attribution des valeurs appropriées
		if (triRapprochementEcritureBanque.equals("DATE_ECRITURE")) {
			triRapprochementEcritureBanque_date_ecriture = pagination.get("triRapprochementEcritureBanque_cle");
		} else if (triRapprochementEcritureBanque.equals("LIBELLE_ECRITURE")) {
			System.out.println(triRapprochementEcritureBanque);
			triRapprochementEcritureBanque_libelle_ecriture = pagination.get("triRapprochementEcritureBanque_cle");
		} else if (triRapprochementEcritureBanque.equals("ENTITE")) {
			triRapprochementEcritureBanque_entite = pagination.get("triRapprochementEcritureBanque_cle");
		} else if (triRapprochementEcritureBanque.equals("NOM_ENTITE")) {
			triRapprochementEcritureBanque_nom_entite = pagination.get("triRapprochementEcritureBanque_cle");
		}else if (triRapprochementEcritureBanque.equals("SIGNE")) {
			triRapprochementEcritureBanque_signe = pagination.get("triRapprochementEcritureBanque_cle");
		}else if (triRapprochementEcritureBanque.equals("TTC")) {
			triRapprochementEcritureBanque_ttc = pagination.get("triRapprochementEcritureBanque_cle");
		}else if (triRapprochementEcritureBanque.equals("STATUT_RAPPROCHE")) {
			triRapprochementEcritureBanque_statut_rapproche = pagination.get("triRapprochementEcritureBanque_cle");
		}
		select_triRapprochementEcritureBanque = pagination.get("select_triRapprochementEcritureBanque");
		// ---------------------------------------------------------------------------------//


		// Rapprochement de la page courante
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
		if (request.getParameter("critereRapprochementEcritureBanque")!=null) {
			critereRapprochementEcritureBanque = request.getParameter("critereRapprochementEcritureBanque");
			System.out.println("critere"+critereRapprochementEcritureBanque);
		}else {
			if (request.getSession().getAttribute("critereRapprochementEcritureBanque") != null) {
				critereRapprochementEcritureBanque = (String) request.getSession().getAttribute("critereRapprochementEcritureBanque");
			}
		}
		if (request.getParameter("searchRapprochementEcritureBanque")!=null) {
			searchRapprochementEcritureBanque = request.getParameter("searchRapprochementEcritureBanque");
			currentPage = 1; // On met la page courante à 1 pour une nouvelle recherche
		}else {
			if (request.getSession().getAttribute("searchRapprochementEcritureBanque") != null) {
				searchRapprochementEcritureBanque = (String) request.getSession().getAttribute("searchRapprochementEcritureBanque") ;
			}
		}

		if (searchRapprochementEcritureBanque != null) {
			if (searchRapprochementEcritureBanque.isEmpty()) {
				list = ecritureBanque_importDao.rechercheRapprochementEcritureBanques(id_ecritureBanque, yearMonth, (currentPage - 1) * recordsPerPage, recordsPerPage, select_triRapprochementEcritureBanque);
				request.getSession().setAttribute("searchRapprochementEcritureBanque", "");
			} else {
				request.getSession().setAttribute("searchRapprochementEcritureBanque", searchRapprochementEcritureBanque);
				request.getSession().setAttribute("critereRapprochementEcritureBanque", critereRapprochementEcritureBanque);
				select_like = critereRapprochementEcritureBanque + " like '" + searchRapprochementEcritureBanque + "%'";
				list = ecritureBanque_importDao.rechercheLikeRapprochementEcritureBanques(id_ecritureBanque, yearMonth, (currentPage - 1) * recordsPerPage, recordsPerPage, select_triRapprochementEcritureBanque,
						select_like);
			}
		} else {
			list = ecritureBanque_importDao.rechercheRapprochementEcritureBanques(id_ecritureBanque, yearMonth, (currentPage - 1) * recordsPerPage, recordsPerPage, select_triRapprochementEcritureBanque);
		}
		int noOfRecords = ecritureBanque_importDao.getNoOfRecords(); // nombre enregistrements total
		// ---------------------------------------------------------------------------------//


		// on inverse le triRapprochementEcritureBanque pour permettre le changement lors de l'appui sur la
		// flèche
		// ---------------------------------------------------------------------------------//
		toggleTriEcritureBanque_importAttribute(request, "triRapprochementEcritureBanque_date_ecriture", triRapprochementEcritureBanque_date_ecriture);
		toggleTriEcritureBanque_importAttribute(request, "triRapprochementEcritureBanque_libelle_ecriture", triRapprochementEcritureBanque_libelle_ecriture);
		toggleTriEcritureBanque_importAttribute(request, "triRapprochementEcritureBanquenom_entite", triRapprochementEcritureBanque_entite);
		toggleTriEcritureBanque_importAttribute(request, "triRapprochementEcritureBanquenom_entite", triRapprochementEcritureBanque_nom_entite);
		toggleTriEcritureBanque_importAttribute(request, "triRapprochementEcritureBanque_signe", triRapprochementEcritureBanque_signe);
		toggleTriEcritureBanque_importAttribute(request, "triRapprochementEcritureBanque_ecriture_ttc", triRapprochementEcritureBanque_ttc);
		toggleTriEcritureBanque_importAttribute(request, "triRapprochementEcritureBanque_statut_rapproche", triRapprochementEcritureBanque_statut_rapproche);
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
		request.setAttribute("yearMonth", yearMonth); // paramètre pas de la pagination
		request.setAttribute("EcritureBanque_List", list); // liste enregistrements
		request.setAttribute("noOfPages", noOfPages); // paramètre nombres de pages
		request.setAttribute("currentPage", currentPage); // paramètre page courante
		request.setAttribute("noBegin", noBegin); // paramètre début pagination
		request.setAttribute("maxPage", maxPage); // paramètre fin pagination
		request.setAttribute("pasPage", pasPage); // paramètre pas de la pagination
		request.setAttribute("searchRapprochementEcritureBanque", searchRapprochementEcritureBanque); // paramètre searchRapprochementEcritureBanque


		// Maj attribut de session critère ecritureBanque_import
		// ---------------------------------------------------------------------------------//
		updateSessionAttributes(request, critereRapprochementEcritureBanque);
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

	// Méthode pour maj des attributs de session critereRapprochementEcritureBanque
	public void updateSessionAttributes(HttpServletRequest request, String critereRapprochementEcritureBanque) {
		// Réinitialisation des attributs de session
		request.getSession().setAttribute("sel_entite", "");
		request.getSession().setAttribute("sel_date_ecriture", "");
		request.getSession().setAttribute("sel_libelle_ecriture", "");

		// Mise à jour de l'attribut sélectionné en fonction de critereRapprochementEcritureBanque
		switch (critereRapprochementEcritureBanque) {
		case "date_ecriture":
			request.getSession().setAttribute("sel_date_ecriture", "selected");
			break;
		case "numero_ligne":
			request.getSession().setAttribute("sel_entite", "selected");
			break;
		case "libelle_ecriture":
			request.getSession().setAttribute("sel_libelle_ecriture", "selected");
			break;
		default:
			request.getSession().setAttribute("sel_entite", "selected");
			break;
		}
	}


	// Méthode pour basculer l'attribut de tri
	private void toggleTriEcritureBanque_importAttribute(HttpServletRequest request, String attributeName, String attributeValue) {
	    String newValue = attributeValue.equals("bi bi-caret-up text-white") ? "bi bi-caret-down text-white" : "bi bi-caret-up text-white";
	    request.setAttribute(attributeName, newValue);
	}

}
