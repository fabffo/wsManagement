/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TVA MAJ                                         ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.tva;

import java.io.IOException;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.TvaDao;
import com.ws.beans.Tva;
import com.ws.forms.tva.ControleDonneesTva;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MajTva extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_TVA      = "tva";
    public static final String ATT_FORM        = "form";
    public static final String VUE_SUCCES      = "gestionTva";
    public static final String VUE_FORM        = "/WEB-INF/JSP_tva/majTva.jsp";
    private TvaDao tvaDao;
    private Integer id ;
    private int currentPage;

    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.tvaDao = daoFactory.getTvaDao();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// récupération page en cours
		// ---------------------------------------------------------------------
		if (request.getParameter("currentPage") != null)
        	request.getSession().setAttribute("currentPage", request.getParameter("currentPage"));

		// récupération de l'enregistrement modifié
		// ---------------------------------------------------------------------
		id =Integer.parseInt(request.getParameter("id"));

		 try {
				request.setAttribute("tva", tvaDao.trouverTva(id));
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
			}

		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_tva/majTva.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* Préparation de l'objet formulaire */
		// ---------------------------------------------------------------------
		ControleDonneesTva form = new ControleDonneesTva();
		/* Traitement de la requête et récupération du bean en résultant */
        Tva tva = form.ControlerDonneesTva( request );


		/* Ajout du bean et de l'objet métier à l'objet requête */
     // ---------------------------------------------------------------------
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_TVA, tva);
		/* Si aucune erreur */
		if (form.getErreurs().isEmpty()) {
			try {
				tvaDao.modifierTva(tva);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				request.setAttribute("tvas", tvaDao.listerTva());
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
