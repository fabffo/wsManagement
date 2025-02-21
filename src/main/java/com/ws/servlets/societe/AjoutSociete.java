/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET SOCIETE CREATION                                ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.societe;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
import com.ws.forms.societe.CreationSocieteForm;

/**
 * Servlet implementation class AjoutSociete
 */
public class AjoutSociete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_SOCIETE      = "societe";
    public static final String ATT_FORM        = "form";
    public static final String VUE_SUCCES      = "gestionSociete";
    public static final String VUE_FORM        = "/WEB-INF/JSP_societe/ajoutSociete.jsp";
    private SocieteDao societeDao;
    DaoFactory daoFactory;
       
    public void init() throws ServletException {
    	daoFactory = DaoFactory.getInstance();
		this.societeDao = daoFactory.getSocieteDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////////////////////
//Liste des types de contrat
Connection connexion = null;
PreparedStatement preparedStatement = null;
ResultSet rs = null;
  String query = "select id, libelle from type_contrat";
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
  //Liste des types societe
  List<String> listTypeSociete = new ArrayList<String>(); 
  listTypeSociete.add("INTERNE");
  listTypeSociete.add("EXTERNE");
  request.setAttribute("listTypeSociete", listTypeSociete);
  ///////////////////
  //Liste signataire_oui_non
  List<String> listSignataire_oui_non = new ArrayList<String>(); 
  listSignataire_oui_non.add("OUI");
  listSignataire_oui_non.add("NON");
  String signataire = "OUI";
  request.setAttribute("signataire", signataire);
  request.setAttribute("listSignataire_oui_non", listSignataire_oui_non);
//Liste des types utilisateur
	connexion = null;
	preparedStatement = null;
	rs = null;
       query = "select id, type from utilisateur";
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
    //Liste des activites
		connexion = null;
   	preparedStatement = null;
   	rs = null;
	         query = "select id, nom from activite";
	        try {
				List<Metier> listActivite = new ArrayList<Metier>(); 
					connexion = daoFactory.getConnection(); 
					preparedStatement = connexion.prepareStatement(query); 
				    rs = preparedStatement.executeQuery();
				    while (rs.next()) { 
				    	Metier activite = new Metier(); 
				        activite.setId(rs.getInt(1)); 
				        activite.setNom(rs.getString(2));
				        activite.setPgmcreation("");
				        listActivite.add(activite);
				    } 
				    rs.close();
				    request.setAttribute("listActivite", listActivite);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        ///////////////////

		
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_societe/ajoutSociete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		CreationSocieteForm form = new CreationSocieteForm();
		/* Traitement de la requête et récupération du bean en résultant */
        Societe societe = form.creerSociete( request );


		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_SOCIETE, societe);
		/* Si aucune erreur */
		if (form.getErreurs().isEmpty()) {
			try {
				societeDao.ajouterSociete(societe);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				request.setAttribute("societes", societeDao.listerSociete());
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
