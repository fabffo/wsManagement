/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TYPEDOCUMENT GESTION                                     ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.typeDocument;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypeDocumentDao;
import com.ws.beans.TypeDocument;
import com.ws.forms.typeDocument.CreationTypeDocumentForm;
import com.ws.forms.typeDocument.GestionTypeDocumentPagination;
import com.ws.forms.typeDocument.MajTypeDocumentForm;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GestionTypeDocument extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TypeDocumentDao typeDocumentDao;
	public static final String MODIFIER = "MODIFIER";
	public static final String SUPPRIMER = "SUPPRIMER";
	public static final String AJOUTER = "AJOUTER";
	public static final String VISUALISER = "VISUALISER";

	public static final String TRIA = "TRIA";

	public static final String ATT_TYPEDOCUMENT = "typeDocument";
	public static final String ATT_FORM = "form";

	public static final String VUE_SUCCES = "/WEB-INF/JSP_typeDocument/gestionTypeDocument.jsp";
	public static final String VUE_FORM = "/WEB-INF/JSP_typeDocument/ajoutTypeDocument.jsp";

	private DaoFactory daoFactory;

	public void init() throws ServletException {
		daoFactory = DaoFactory.getInstance();
		this.typeDocumentDao = daoFactory.getTypeDocumentDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1; // page en cours*
		int recordsPerPage = 6; // nbr enregistrements par page
		int maxPage = 0; // fin pagination
		String triTypeDocument;
		String triTypeDocument_id;
		String select_triTypeDocument = "";
		String triTypeDocument_cheminRelatif="bi bi-caret-up text-white";
		String triTypeDocument_contrat="bi bi-caret-up text-white";
		String triTypeDocument_cheminAbsolu="bi bi-caret-up text-white";
		String select_like;
		String searchTypeDocument = "";
		String critereTypeDocument = "";
		List<TypeDocument> list = null;
		String debutsession ="debutsession";

		/* Préparation de l'objet pagination */
		GestionTypeDocumentPagination form = new GestionTypeDocumentPagination();
		/* Traitement de la requête et récupération du bean en résultant */
		Map<String, String> pagination =  form.paginerTypeDocument( request );

		triTypeDocument=pagination.get("triTypeDocument");

		if (triTypeDocument.equals("ID")) {
			triTypeDocument_id=pagination.get("triTypeDocument_cle");
		} else {
			triTypeDocument_id ="bi bi-caret-down text-white";
		}
		if (triTypeDocument.equals("CHEMINRELATIF")) {
			triTypeDocument_cheminRelatif=pagination.get("triTypeDocument_cle");
		} else {
			triTypeDocument_cheminRelatif ="bi bi-caret-down text-white";
		}
		if (triTypeDocument.equals("CONTRAT")) {
			triTypeDocument_contrat=pagination.get("triTypeDocument_cle");
		} else {
			triTypeDocument_contrat ="bi bi-caret-down text-white";
		}
		if (triTypeDocument.equals("CHEMINABSOLU")) {
			triTypeDocument_cheminAbsolu=pagination.get("triTypeDocument_cle");
		} else {
			triTypeDocument_cheminAbsolu ="bi bi-caret-down text-white";
		}
		select_triTypeDocument = pagination.get("select_triTypeDocument");

		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} else {
			if (request.getSession().getAttribute("currentPage") != null)
				currentPage = Integer.parseInt((String) request.getSession().getAttribute("currentPage"));

		}

		// récupération  recherche de la page
		if (request.getParameter("searchTypeDocument") != null) {
			if (request.getParameter("searchTypeDocument").equals("")) {
				try {
					list = typeDocumentDao.rechercheTypeDocuments((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triTypeDocument);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getSession().setAttribute("searchTypeDocument", "");
			} else {
				request.getSession().setAttribute("searchTypeDocument", request.getParameter("searchTypeDocument"));
				request.getSession().setAttribute("critereTypeDocument", request.getParameter("critereTypeDocument"));
				critereTypeDocument = request.getParameter("critereTypeDocument");
				searchTypeDocument = request.getParameter("searchTypeDocument");
				select_like=critereTypeDocument + " like '" + searchTypeDocument + "%'";
				try {
					list = typeDocumentDao.rechercheLikeTypeDocuments((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triTypeDocument, select_like);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		else {
			if (request.getSession().getAttribute("searchTypeDocument") != null) {
				System.out.print("searchTypeDocument"+request.getSession().getAttribute("searchTypeDocument"));
				if (request.getSession().getAttribute("searchTypeDocument").equals("")) {
					try {
						list = typeDocumentDao.rechercheTypeDocuments((currentPage - 1) * recordsPerPage, recordsPerPage,
								select_triTypeDocument);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					searchTypeDocument = (String) request.getSession().getAttribute("searchTypeDocument");
					critereTypeDocument = (String) request.getSession().getAttribute("critereTypeDocument");
					select_like=critereTypeDocument + " like '" + searchTypeDocument + "%'";
					try {
						list = typeDocumentDao.rechercheLikeTypeDocuments((currentPage - 1) * recordsPerPage, recordsPerPage,
								select_triTypeDocument, select_like);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				try {
					list = typeDocumentDao.rechercheTypeDocuments((currentPage - 1) * recordsPerPage, recordsPerPage,
							select_triTypeDocument);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		int noOfRecords = typeDocumentDao.getNoOfRecords(); // cheminRelatifbre enregistrements total

		//on inverse le triTypeDocument pour permettre le changement lors de l'appui sur la flèche
			if (triTypeDocument_id.equals("bi bi-caret-up text-white")) {
				request.setAttribute("triTypeDocument_id", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("triTypeDocument_id", "bi bi-caret-up text-white");
			}
			if (triTypeDocument_cheminRelatif.equals("bi bi-caret-up text-white")) {
				request.setAttribute("triTypeDocument_cheminRelatif", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("triTypeDocument_cheminRelatif", "bi bi-caret-up text-white");
			}
			if (triTypeDocument_contrat.equals("bi bi-caret-up text-white")) {
				request.setAttribute("triTypeDocument_contrat", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("triTypeDocument_contrat", "bi bi-caret-up text-white");
			}
			if (triTypeDocument_cheminAbsolu.equals("bi bi-caret-up text-white")) {
				request.setAttribute("triTypeDocument_cheminAbsolu", "bi bi-caret-down text-white");
			} else {
				request.setAttribute("triTypeDocument_cheminAbsolu", "bi bi-caret-up text-white");
			}

		// cheminRelatifbre d'enregistrements pour une page
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

		int noBegin = 1; // début pagination
		int pasPage = 2; // pas de la pagination

		if (maxPage == 0)
			maxPage = pasPage; // fin pagination

		if (request.getParameter("noBegin") != null)
			noBegin = Integer.parseInt((String) request.getParameter("noBegin"));
		if (request.getParameter("maxPage") != null)
			maxPage = Integer.parseInt((String) request.getParameter("maxPage"));


		request.setAttribute("TypeDocumentList", list); // liste enregistrements
		request.setAttribute("noOfPages", noOfPages); // paramètre cheminRelatifbres de pages
		request.setAttribute("currentPage", currentPage); // paramètre page courante
		request.setAttribute("noBegin", noBegin); // paramètre début pagination
		request.setAttribute("maxPage", maxPage); // paramètre fin pagination
		request.setAttribute("pasPage", pasPage); // paramètre pas de la pagination
		request.setAttribute("searchTypeDocument", searchTypeDocument); // paramètre searchTypeDocument

		if (critereTypeDocument.equals("cheminRelatif")){
			request.getSession().setAttribute("sel_cheminRelatif", "selected");
			request.getSession().setAttribute("sel_contrat", "");
			request.getSession().setAttribute("sel_cheminAbsolu", "");
		}else
			if (critereTypeDocument.equals("contrat")){
				request.getSession().setAttribute("sel_contrat", "selected");
				request.getSession().setAttribute("sel_cheminRelatif", "");
				request.getSession().setAttribute("sel_cheminAbsolu", "");
			}else
				if (critereTypeDocument.equals("cheminAbsolu")){
					request.getSession().setAttribute("sel_cheminAbsolu", "selected");
					request.getSession().setAttribute("sel_cheminRelatif", "");
					request.getSession().setAttribute("sel_contrat", "");
				}
			else {
				request.getSession().setAttribute("sel_contrat", "selected");
				request.getSession().setAttribute("sel_cheminRelatif", "");
				request.getSession().setAttribute("sel_cheminAbsolu", "");
			}

		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_typeDocument/gestionTypeDocument.jsp")
				.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
