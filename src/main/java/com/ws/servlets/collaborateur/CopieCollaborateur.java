/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET COLLABORATEUR GESTION                           ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.collaborateur;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.CollaborateurDao;
import com.ws.beans.Metier;
import com.ws.beans.Collaborateur;
import com.ws.beans.TypeContratCollaborateur;
import com.ws.beans.Utilisateur;
import com.ws.forms.collaborateur.CopieCollaborateurForm;
import com.ws.forms.collaborateur.MajCollaborateurForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MajCollaborateur
 */
public class CopieCollaborateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_COLLABORATEUR      = "collaborateur";
    public static final String ATT_FORM        = "form";
    public static final String VUE_SUCCES      = "gestionCollaborateur";
    public static final String VUE_FORM        = "/WEB-INF/JSP_collaborateur/copieCollaborateur.jsp";
    private CollaborateurDao collaborateurDao;
    private Integer id ;
    private int currentPage;
    DaoFactory daoFactory;
       
    public void init() throws ServletException {
		 daoFactory = DaoFactory.getInstance();
		this.collaborateurDao = daoFactory.getCollaborateurDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("currentPage") != null) 
        	request.getSession().setAttribute("currentPage", request.getParameter("currentPage"));
	
		id =Integer.parseInt(request.getParameter("id"));
		
		Collaborateur collaborateur = null;
		try {
			collaborateur = collaborateurDao.trouverCollaborateur(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		////////////////////
		//Liste des types de contrat
		Connection connexion = null;
     	PreparedStatement preparedStatement = null;
     	ResultSet rs = null;
	        String query = "select id, libelle from TypeContratCollaborateur";
	        try {
				List<TypeContratCollaborateur> listTypeContrat = new ArrayList<TypeContratCollaborateur>(); 
					connexion = daoFactory.getConnection(); 
					preparedStatement = connexion.prepareStatement(query); 
				    rs = preparedStatement.executeQuery();
				    System.out.print("le résultat"+rs+"le résultat");
				    while (rs.next()) { 
				    	System.out.print(rs.getInt(1));
				    	TypeContratCollaborateur typeContrat = new TypeContratCollaborateur(); 
				        typeContrat.setId(rs.getInt(1)); 
				        typeContrat.setLibelle(rs.getString(2));
				        listTypeContrat.add(typeContrat); 
				    } 
				    rs.close();
				    request.setAttribute("listTypeContrat", listTypeContrat);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        ///////////////////
	        //Liste des types collaborateur
	        List<String> listTypeCollaborateur = new ArrayList<String>(); 
	        listTypeCollaborateur.add("INTERNE");
	        listTypeCollaborateur.add("EXTERNE");
	        request.setAttribute("listTypeCollaborateur", listTypeCollaborateur);
	        ///////////////////
	        //Liste signataire_oui_non
	        List<String> listSignataire_oui_non = new ArrayList<String>(); 
	        listSignataire_oui_non.add("OUI");
	        listSignataire_oui_non.add("NON");
	        String signataire = "OUI";
	        if (collaborateur.getSignataire_contrat()==0) {
	        	signataire = "NON";
	        }
	        request.setAttribute("signataire", signataire);
	        request.setAttribute("listSignataire_oui_non", listSignataire_oui_non);
	      //Liste des types utilisateur
			connexion = null;
	     	preparedStatement = null;
	     	rs = null;
		         query = "select id, libelle from utilisateur";
		        try {
					List<Utilisateur> listUtilisateur = new ArrayList<Utilisateur>(); 
						connexion = daoFactory.getConnection(); 
						preparedStatement = connexion.prepareStatement(query); 
					    rs = preparedStatement.executeQuery();
					    while (rs.next()) { 
					    	Utilisateur utilisateur = new Utilisateur(); 
					        utilisateur.setId(rs.getInt(1)); 
					        utilisateur.setLibelle(rs.getString(2));
					        listUtilisateur.add(utilisateur);
					        System.out.print("liste utilisateur"+utilisateur.getLibelle()+"le résultat");
					    } 
					    rs.close();
					    request.setAttribute("listUtilisateur", listUtilisateur);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		        ///////////////////
		      //Liste des metiers
				connexion = null;
		     	preparedStatement = null;
		     	rs = null;
			         query = "select id, nom from metier";
			        try {
						List<Metier> listMetier = new ArrayList<Metier>(); 
							connexion = daoFactory.getConnection(); 
							preparedStatement = connexion.prepareStatement(query); 
						    rs = preparedStatement.executeQuery();
						    while (rs.next()) { 
						    	Metier metier = new Metier(); 
						        metier.setId(rs.getInt(1)); 
						        metier.setNom(rs.getString(2));
						        if(metier.getId()==collaborateur.getMetier_principal()) {
						        	metier.setPgmcreation("selected");
						        }else {
						        	metier.setPgmcreation("");
						        }
						        listMetier.add(metier);
						    } 
						    rs.close();
						    request.setAttribute("listMetier", listMetier);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
			        ///////////////////
		 request.setAttribute("collaborateur", collaborateur);
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_collaborateur/copieCollaborateur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setAttribute("id", id);
		/* Préparation de l'objet formulaire */
		CopieCollaborateurForm form = new CopieCollaborateurForm();
		/* Traitement de la requête et récupération du bean en résultant */
        Collaborateur collaborateur = form.copierCollaborateur( request );


		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_COLLABORATEUR, collaborateur);
		/* Si aucune erreur */
		if (form.getErreurs().isEmpty()) {
			try {
				collaborateurDao.copierCollaborateur(collaborateur);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				request.setAttribute("collaborateurs", collaborateurDao.listerCollaborateur());
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
