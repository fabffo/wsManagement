/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TYPE SOCIETE COPIE                              ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.typeSociete;

import java.io.IOException;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypeSocieteDao;
import com.ws.beans.TypeSociete;
import com.ws.forms.typeSociete.CopieTypeSocieteForm;
import com.ws.forms.typeSociete.MajTypeSocieteForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MajTypeSociete
 */
public class CopieTypeSociete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_TYPE_MISSION      = "typeSociete";
    public static final String ATT_FORM        = "form";
    public static final String VUE_SUCCES      = "gestionTypeSociete";
    public static final String VUE_FORM        = "/WEB-INF/JSP_typeSociete/copieTypeSociete.jsp";
    private TypeSocieteDao typeSocieteDao;
    private Integer id ;
    private int currentPage;
       
    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.typeSocieteDao = daoFactory.getTypeSocieteDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("currentPage") != null) 
        	request.getSession().setAttribute("currentPage", request.getParameter("currentPage"));
	
		id =Integer.parseInt(request.getParameter("id"));
		
		 try {
				request.setAttribute("typeSociete", typeSocieteDao.trouverTypeSociete(id));
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
			}
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_typeSociete/copieTypeSociete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setAttribute("id", id);
		/* Préparation de l'objet formulaire */
		CopieTypeSocieteForm form = new CopieTypeSocieteForm();
		/* Traitement de la requête et récupération du bean en résultant */
        TypeSociete typeSociete = form.copierTypeSociete( request );


		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_TYPE_MISSION, typeSociete);
		/* Si aucune erreur */
		if (form.getErreurs().isEmpty()) {
			try {
				typeSocieteDao.copierTypeSociete(typeSociete);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				request.setAttribute("typeSocietes", typeSocieteDao.listerTypeSociete());
			} catch (DaoException e) {
				// TODO Auto-generated catch response.sendRedirect(VUE_SUCCES);block
				request.setAttribute("erreur", e.getMessage());
			}
			response.sendRedirect(VUE_SUCCES);
		} else {
			this.getServletContext().getRequestDispatcher(VUE_FORM)
			.forward(request, response);
		}

	}

}
