/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TYPEPRIX MAJ                                 ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.typePrix;

import java.io.IOException;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypePrixDao;
import com.ws.beans.TypePrix;
import com.ws.forms.typePrix.MajTypePrixForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MajTypePrix
 */
public class MajTypePrix extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_TYPEPRIX      = "typePrix";
    public static final String ATT_FORM        = "form";
    public static final String VUE_SUCCES      = "gestionTypePrix";
    public static final String VUE_FORM        = "/WEB-INF/JSP_typePrix/majTypePrix.jsp";
    private TypePrixDao typePrixDao;
    private Integer id ;
    private int currentPage;
       
    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.typePrixDao = daoFactory.getTypePrixDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("currentPage") != null) 
        	request.getSession().setAttribute("currentPage", request.getParameter("currentPage"));
	
		id =Integer.parseInt(request.getParameter("id"));
		
		 try {
				request.setAttribute("typePrix", typePrixDao.trouverTypePrix(id));
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
			}
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_typePrix/majTypePrix.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setAttribute("id", id);
		/* Préparation de l'objet formulaire */
		MajTypePrixForm form = new MajTypePrixForm();
		/* Traitement de la requête et récupération du bean en résultant */
        TypePrix typePrix = form.modifierTypePrix( request );


		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_TYPEPRIX, typePrix);
		/* Si aucune erreur */
		if (form.getErreurs().isEmpty()) {
			try {
				typePrixDao.modifierTypePrix(typePrix);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				request.setAttribute("typePrixs", typePrixDao.listerTypePrix());
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
