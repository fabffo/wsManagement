/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET SOCIETE COPIE                                   ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.societe;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.SocieteDao;
import com.ws.beans.Metier;
import com.ws.beans.Societe;
import com.ws.beans.TypeContratCollaborateur;
import com.ws.beans.Utilisateur;
import com.ws.forms.societe.CopieSocieteForm;
import com.ws.forms.societe.MajSocieteForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MajSociete
 */
public class CopieSociete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_SOCIETE      = "societe";
    public static final String ATT_FORM        = "form";
    public static final String VUE_SUCCES      = "gestionSociete";
    public static final String VUE_FORM        = "/WEB-INF/JSP_societe/copieSociete.jsp";
    private SocieteDao societeDao;
    private Integer id ;
    private int currentPage;
    DaoFactory daoFactory;
       
    public void init() throws ServletException {
		 daoFactory = DaoFactory.getInstance();
		this.societeDao = daoFactory.getSocieteDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("currentPage") != null) 
        	request.getSession().setAttribute("currentPage", request.getParameter("currentPage"));
	
		id =Integer.parseInt(request.getParameter("id"));
		
		Societe societe = null;
		try {
			societe = societeDao.trouverSociete(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		////////////////////
		
			        ///////////////////
		 request.setAttribute("societe", societe);
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_societe/copieSociete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setAttribute("id", id);
		/* Préparation de l'objet formulaire */
		CopieSocieteForm form = new CopieSocieteForm();
		/* Traitement de la requête et récupération du bean en résultant */
        Societe societe = form.copierSociete( request );


		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_SOCIETE, societe);
		/* Si aucune erreur */
		if (form.getErreurs().isEmpty()) {
			try {
				societeDao.copierSociete(societe);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				request.setAttribute("societes", societeDao.listerSociete());
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
