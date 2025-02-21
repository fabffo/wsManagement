/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TYPE MISSION CREATION                           ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.typeContratSociete;

import java.io.IOException;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypeContratSocieteDao;
import com.ws.beans.TypeContratSociete;
import com.ws.forms.typeContratSociete.CreationTypeContratSocieteForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjoutTypeContratSociete
 */
public class AjoutTypeContratSociete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_TYPE_MISSION      = "typeContratSociete";
    public static final String ATT_FORM        = "form";
    public static final String VUE_SUCCES      = "gestionTypeContratSociete";
    public static final String VUE_FORM        = "/WEB-INF/JSP_typeContratSociete/ajoutTypeContratSociete.jsp";
    private TypeContratSocieteDao typeContratSocieteDao;
       
    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.typeContratSocieteDao = daoFactory.getTypeContratSocieteDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_typeContratSociete/ajoutTypeContratSociete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		CreationTypeContratSocieteForm form = new CreationTypeContratSocieteForm();
		/* Traitement de la requête et récupération du bean en résultant */
        TypeContratSociete typeContratSociete = form.creerTypeContratSociete( request );


		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_TYPE_MISSION, typeContratSociete);
		/* Si aucune erreur */
		if (form.getErreurs().isEmpty()) {
			try {
				typeContratSocieteDao.ajouterTypeContratSociete(typeContratSociete);
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
		} else {
			this.getServletContext().getRequestDispatcher(VUE_FORM)
			.forward(request, response);
		}
	}

}
