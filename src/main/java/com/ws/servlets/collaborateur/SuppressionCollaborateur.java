/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET COLLABORATEUR SUPPRESSION                       ///
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
 * Servlet implementation class SuppressionCollaborateur
 */
public class SuppressionCollaborateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CollaborateurDao collaborateurDao;
	 public static final String VUE_SUCCES      = "gestionCollaborateur";
	    public static final String VUE_FORM        = "/WEB-INF/JSP_collaborateur/ajoutCollaborateur.jsp";

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
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_collaborateur/suppressionCollaborateur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		try {
			collaborateurDao.supprimerCollaborateur(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			request.setAttribute("collaborateurs", collaborateurDao.listerCollaborateur());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", e.getMessage());
		}
		response.sendRedirect(VUE_SUCCES);
	}

}
