/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET SOCIETE MAJ                                     ///
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
import com.ws.beans.Contact;
import com.ws.beans.Societe;
import com.ws.beans.TypeContratCollaborateur;
import com.ws.beans.Utilisateur;
import com.ws.forms.societe.MajSocieteForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MajSociete
 */
public class MajSociete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_SOCIETE      = "societe";
    public static final String ATT_FORM        = "form";
    public static final String VUE_SUCCES      = "gestionSociete";
    public static final String VUE_FORM        = "/WEB-INF/JSP_societe/majSociete.jsp";
    private SocieteDao societeDao;
    private DaoFactory daoFactory;
    private Integer id ;
    private int currentPage;
       
    public void init() throws ServletException {
		daoFactory = DaoFactory.getInstance();
		this.societeDao = daoFactory.getSocieteDao();
		System.out.print("init");
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
////////////////////
//Liste des types de contrat
Connection connexion = null;
PreparedStatement preparedStatement = null;
ResultSet rs = null;
  String query = "select id, Civilite, prenom, nom, email1, telephone1, fonction from contact where id_societe=?";
  try {
		List<Contact> listContact = new ArrayList<Contact>(); 
			connexion = daoFactory.getConnection(); 
			preparedStatement = connexion.prepareStatement(query); 
			preparedStatement.setInt(1, id);
		    rs = preparedStatement.executeQuery();
		    
		    while (rs.next()) { 
		    	Contact contact = new Contact(); 
		    	contact.setId(rs.getInt(1));
		    	contact.setResume_contact(rs.getString(2)+"_"+rs.getString(3)+"_"+rs.getString(4)+"_"+rs.getString(5)+"_"+rs.getString(6)+"_"+rs.getString(7));
		    	if(contact.getId()==societe.getId_contact_principal()) {
		        	contact.setPgmcreation("selected");
		        }else {
		        	contact.setPgmcreation("");
		        }
		        listContact.add(contact); 
		    } 
		    rs.close();
		    request.setAttribute("listContact", listContact);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
  ///////////////////
			        ///////////////////
		 request.setAttribute("societe", societe);
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_societe/majSociete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setAttribute("id", id);
		/* Préparation de l'objet formulaire */
		MajSocieteForm form = new MajSocieteForm();
		/* Traitement de la requête et récupération du bean en résultant */
        Societe societe = form.modifierSociete( request );


		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_SOCIETE, societe);
		/* Si aucune erreur */
		if (form.getErreurs().isEmpty()) {
			try {
				societeDao.modifierSociete(societe);
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
