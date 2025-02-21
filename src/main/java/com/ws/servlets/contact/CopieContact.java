/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET COLLABORATEUR COPIE                             ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.contact;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.ContactDao;
import com.ws.beans.Metier;
import com.ws.beans.Societe;
import com.ws.beans.Contact;
import com.ws.beans.TypeContratCollaborateur;
import com.ws.beans.Utilisateur;
import com.ws.forms.contact.CopieContactForm;
import com.ws.forms.contact.MajContactForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MajContact
 */
public class CopieContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_CONTACT      = "contact";
    public static final String ATT_FORM        = "form";
    public static final String VUE_SUCCES      = "gestionContact";
    public static final String VUE_FORM        = "/WEB-INF/JSP_contact/copieContact.jsp";
    private ContactDao contactDao;
    private Integer id ;
    private int currentPage;
    DaoFactory daoFactory;
       
    public void init() throws ServletException {
		 daoFactory = DaoFactory.getInstance();
		this.contactDao = daoFactory.getContactDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("currentPage") != null) 
        	request.getSession().setAttribute("currentPage", request.getParameter("currentPage"));
	
		id =Integer.parseInt(request.getParameter("id"));
		
		Contact contact = null;
		try {
			contact = contactDao.trouverContact(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		////////////////////
		//Liste societe
		Connection connexion = null;
     	PreparedStatement preparedStatement = null;
     	ResultSet rs = null;
	        String query = "select id, raison_sociale from societe";
	        try {
				List<Societe> listSociete = new ArrayList<Societe>(); 
					connexion = daoFactory.getConnection(); 
					preparedStatement = connexion.prepareStatement(query); 
				    rs = preparedStatement.executeQuery();
				    while (rs.next()) { 
				    	System.out.print(rs.getInt(1));
				    	Societe societe = new Societe(); 
				        societe.setId(rs.getInt(1)); 
				        societe.setRaison_sociale(rs.getString(2));
				        listSociete.add(societe); 
				    } 
				    rs.close();
				    request.setAttribute("listSociete", listSociete);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	       
		 request.setAttribute("contact", contact);
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_contact/copieContact.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setAttribute("id", id);
		/* Préparation de l'objet formulaire */
		CopieContactForm form = new CopieContactForm();
		/* Traitement de la requête et récupération du bean en résultant */
        Contact contact = form.copierContact( request );


		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_CONTACT, contact);
		/* Si aucune erreur */
		if (form.getErreurs().isEmpty()) {
			try {
				contactDao.copierContact(contact);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				request.setAttribute("contacts", contactDao.listerContact());
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
