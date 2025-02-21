/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET UTILISATEUR SUPPRESSION                         ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.UtilisateurDao;
import com.ws.beans.Utilisateur;

/**
 * Servlet implementation class SuppressionUtilisateur
 */
public class SuppressionUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;
	 public static final String VUE_SUCCES      = "gestionUtilisateur";
	    public static final String VUE_FORM        = "/WEB-INF/JSP_utilisateur/ajoutUtilisateur.jsp";

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.utilisateurDao = daoFactory.getUtilisateurDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id =Integer.parseInt(request.getParameter("id"));
		System.out.print("id est"+id);
			try {
				request.setAttribute("utilisateur", utilisateurDao.trouverUtilisateur(id));
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_utilisateur/suppressionUtilisateur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		try {
			utilisateurDao.supprimerUtilisateur(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			request.setAttribute("utilisateurs", utilisateurDao.listerUtilisateur());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", e.getMessage());
		}
		response.sendRedirect(VUE_SUCCES);
	}

}
