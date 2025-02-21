/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TYPE SOCIETE SUPPRESSION                        ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.typeSociete;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypeSocieteDao;
import com.ws.beans.TypeSociete;

/**
 * Servlet implementation class SuppressionTypeSociete
 */
public class SuppressionTypeSociete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TypeSocieteDao typeSocieteDao;
	 public static final String VUE_SUCCES      = "gestionTypeSociete";
	    public static final String VUE_FORM        = "/WEB-INF/JSP_typeSociete/ajoutTypeSociete.jsp";

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.typeSocieteDao = daoFactory.getTypeSocieteDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id =Integer.parseInt(request.getParameter("id"));
			try {
				request.setAttribute("typeSociete", typeSocieteDao.trouverTypeSociete(id));
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_typeSociete/suppressionTypeSociete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		System.out.print("supp id est"+id);
		try {
			typeSocieteDao.supprimerTypeSociete(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			request.setAttribute("typeSocietes", typeSocieteDao.listerTypeSociete());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", e.getMessage());
		}
		response.sendRedirect(VUE_SUCCES);
	}

}
