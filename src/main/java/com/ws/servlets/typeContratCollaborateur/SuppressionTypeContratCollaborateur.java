/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TYPE CONTRAT SUPPRESSION                        ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.typeContratCollaborateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypeContratCollaborateurDao;
import com.ws.beans.TypeContratCollaborateur;

/**
 * Servlet implementation class SuppressionTypeContratCollaborateur
 */
public class SuppressionTypeContratCollaborateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TypeContratCollaborateurDao typeContratCollaborateurDao;
	 public static final String VUE_SUCCES      = "gestionTypeContratCollaborateur";
	    public static final String VUE_FORM        = "/WEB-INF/JSP_typeContratCollaborateur/ajoutTypeContratCollaborateur.jsp";

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.typeContratCollaborateurDao = daoFactory.getTypeContratCollaborateurDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id =Integer.parseInt(request.getParameter("id"));
			try {
				request.setAttribute("typeContratCollaborateur", typeContratCollaborateurDao.trouverTypeContratCollaborateur(id));
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_typeContratCollaborateur/suppressionTypeContratCollaborateur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		try {
			typeContratCollaborateurDao.supprimerTypeContratCollaborateur(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			request.setAttribute("typeContratCollaborateurs", typeContratCollaborateurDao.listerTypeContratCollaborateur());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", e.getMessage());
		}
		response.sendRedirect(VUE_SUCCES);
	}

}
