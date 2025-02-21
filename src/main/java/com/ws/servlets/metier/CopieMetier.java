/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET METIER COPIE                                    ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.metier;

import java.io.IOException;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.MetierDao;
import com.ws.beans.Metier;
import com.ws.forms.metier.CopieMetierForm;
import com.ws.forms.metier.MajMetierForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MajMetier
 */
public class CopieMetier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_TYPE_MISSION      = "metier";
    public static final String ATT_FORM        = "form";
    public static final String VUE_SUCCES      = "gestionMetier";
    public static final String VUE_FORM        = "/WEB-INF/JSP_metier/copieMetier.jsp";
    private MetierDao metierDao;
    private Integer id ;
    private int currentPage;
       
    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.metierDao = daoFactory.getMetierDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("currentPage") != null) 
        	request.getSession().setAttribute("currentPage", request.getParameter("currentPage"));
	
		id =Integer.parseInt(request.getParameter("id"));
		
		 try {
				request.setAttribute("metier", metierDao.trouverMetier(id));
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
			}
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_metier/copieMetier.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setAttribute("id", id);
		/* Préparation de l'objet formulaire */
		CopieMetierForm form = new CopieMetierForm();
		/* Traitement de la requête et récupération du bean en résultant */
        Metier metier = form.copierMetier( request );


		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_TYPE_MISSION, metier);
		/* Si aucune erreur */
		if (form.getErreurs().isEmpty()) {
			try {
				metierDao.copierMetier(metier);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				request.setAttribute("metiers", metierDao.listerMetier());
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
