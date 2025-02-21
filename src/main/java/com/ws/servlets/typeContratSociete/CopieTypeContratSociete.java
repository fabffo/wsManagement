/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TYPE MISSION COPIE                              ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.typeContratSociete;

import java.io.IOException;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypeContratSocieteDao;
import com.ws.beans.TypeContratSociete;
import com.ws.forms.typeContratSociete.CopieTypeContratSocieteForm;
import com.ws.forms.typeContratSociete.MajTypeContratSocieteForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MajTypeContratSociete
 */
public class CopieTypeContratSociete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_TYPE_MISSION      = "typeContratSociete";
    public static final String ATT_FORM        = "form";
    public static final String VUE_SUCCES      = "gestionTypeContratSociete";
    public static final String VUE_FORM        = "/WEB-INF/JSP_typeContratSociete/copieTypeContratSociete.jsp";
    private TypeContratSocieteDao typeContratSocieteDao;
    private Integer id ;
    private int currentPage;
       
    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.typeContratSocieteDao = daoFactory.getTypeContratSocieteDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("currentPage") != null) 
        	request.getSession().setAttribute("currentPage", request.getParameter("currentPage"));
	
		id =Integer.parseInt(request.getParameter("id"));
		
		 try {
				request.setAttribute("typeContratSociete", typeContratSocieteDao.trouverTypeContratSociete(id));
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
			}
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_typeContratSociete/copieTypeContratSociete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setAttribute("id", id);
		/* Préparation de l'objet formulaire */
		CopieTypeContratSocieteForm form = new CopieTypeContratSocieteForm();
		/* Traitement de la requête et récupération du bean en résultant */
        TypeContratSociete typeContratSociete = form.copierTypeContratSociete( request );


		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_TYPE_MISSION, typeContratSociete);
		/* Si aucune erreur */
		if (form.getErreurs().isEmpty()) {
			try {
				typeContratSocieteDao.copierTypeContratSociete(typeContratSociete);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				request.setAttribute("typeContratSocietes", typeContratSocieteDao.listerTypeContratSociete());
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
