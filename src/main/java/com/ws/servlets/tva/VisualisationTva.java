/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TVA VISUALISATION                               ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.tva;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.TvaDao;
import com.ws.beans.Tva;

public class VisualisationTva extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TvaDao tvaDao;

	public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.tvaDao = daoFactory.getTvaDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		// récupération de l'enregistrement à supprimer
		// ---------------------------------------------------------------------
		Integer id =Integer.parseInt(request.getParameter("id"));
		try {
			request.setAttribute("tva", tvaDao.trouverTva(id));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", e.getMessage());
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_tva/visualisationTva.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
