/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET METIER SUPPRESSION                              ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.metier;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.MetierDao;
import com.ws.beans.Metier;

/**
 * Servlet implementation class SuppressionMetier
 */
public class SuppressionMetier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MetierDao metierDao;
	 public static final String VUE_SUCCES      = "gestionMetier";
	    public static final String VUE_FORM        = "/WEB-INF/JSP_metier/ajoutMetier.jsp";

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.metierDao = daoFactory.getMetierDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id =Integer.parseInt(request.getParameter("id"));
		System.out.print("id est"+id);
			try {
				request.setAttribute("metier", metierDao.trouverMetier(id));
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_metier/suppressionMetier.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		try {
			metierDao.supprimerMetier(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			request.setAttribute("metiers", metierDao.listerMetier());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", e.getMessage());
		}
		response.sendRedirect(VUE_SUCCES);
	}

}
