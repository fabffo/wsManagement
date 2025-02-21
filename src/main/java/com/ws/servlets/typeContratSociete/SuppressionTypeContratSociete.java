/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TYPE MISSION SUPPRESSION                        ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.typeContratSociete;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypeContratSocieteDao;
import com.ws.beans.TypeContratSociete;

/**
 * Servlet implementation class SuppressionTypeContratSociete
 */
public class SuppressionTypeContratSociete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TypeContratSocieteDao typeContratSocieteDao;
	 public static final String VUE_SUCCES      = "gestionTypeContratSociete";
	    public static final String VUE_FORM        = "/WEB-INF/JSP_typeContratSociete/ajoutTypeContratSociete.jsp";

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.typeContratSocieteDao = daoFactory.getTypeContratSocieteDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id =Integer.parseInt(request.getParameter("id"));
		System.out.print("id est"+id);
			try {
				request.setAttribute("typeContratSociete", typeContratSocieteDao.trouverTypeContratSociete(id));
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_typeContratSociete/suppressionTypeContratSociete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		System.out.println("servelt sup");
		try {
			typeContratSocieteDao.supprimerTypeContratSociete(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			request.setAttribute("typeContratSocietes", typeContratSocieteDao.listerTypeContratSociete());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", e.getMessage());
		}
		response.sendRedirect(VUE_SUCCES);
	}

}
