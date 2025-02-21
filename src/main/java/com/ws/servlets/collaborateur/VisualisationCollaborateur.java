/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET COLLABORATEUR VISUALISATION                     ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.collaborateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.CollaborateurDao;
import com.ws.beans.Collaborateur;

/**
 * Servlet implementation class VisualisationCollaborateur
 */
public class VisualisationCollaborateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CollaborateurDao collaborateurDao;
	
	public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.collaborateurDao = daoFactory.getCollaborateurDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id =Integer.parseInt(request.getParameter("id"));
		id =Integer.parseInt(request.getParameter("id"));
		Collaborateur collaborateur = null;
		try {
			collaborateur = collaborateurDao.trouverCollaborateur(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String signataire = "OUI";
        if (collaborateur.getSignataire_contrat()==0) {
        	signataire = "NON";
        }
        request.setAttribute("signataire", signataire);
		request.setAttribute("collaborateur", collaborateur);
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_collaborateur/visualisationCollaborateur.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
