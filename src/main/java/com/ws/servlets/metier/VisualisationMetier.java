/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET METIER VISUALISATION                            ///
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
 * Servlet implementation class VisualisationMetier
 */
public class VisualisationMetier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MetierDao metierDao;
	
	public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.metierDao = daoFactory.getMetierDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id =Integer.parseInt(request.getParameter("id"));
		Metier metierV = new Metier();
		try {
			request.setAttribute("metier", metierDao.trouverMetier(id));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", e.getMessage());
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_metier/visualisationMetier.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
