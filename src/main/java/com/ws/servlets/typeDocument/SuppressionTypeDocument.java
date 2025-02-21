/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TYPEDOCUMENT SUPPRESSION                                 ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.typeDocument;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypeDocumentDao;
import com.ws.beans.TypeDocument;

/**
 * Servlet implementation class SuppressionTypeDocument
 */
public class SuppressionTypeDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TypeDocumentDao typeDocumentDao;
	public static final String VUE_SUCCES = "gestionTypeDocument";
	public static final String VUE_FORM = "/WEB-INF/JSP_typeDocument/ajoutTypeDocument.jsp";
	private DaoFactory daoFactory;

	public void init() throws ServletException {
		daoFactory = DaoFactory.getInstance();
		this.typeDocumentDao = daoFactory.getTypeDocumentDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		try {
			request.setAttribute("typeDocument", typeDocumentDao.trouverTypeDocument(id));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_typeDocument/suppressionTypeDocument.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("supprimer");
		Integer id = Integer.parseInt(request.getParameter("id"));
		try {
			typeDocumentDao.supprimerTypeDocument(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			request.setAttribute("typeDocuments", typeDocumentDao.listerTypeDocument());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", e.getMessage());
		}
		response.sendRedirect(VUE_SUCCES);
	}

}
