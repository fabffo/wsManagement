/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TYPEDOCUMENT     CREATION                                ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.typeDocument;

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
import com.ws.Dao.TypeDocumentDao;
import com.ws.beans.TypeDocument;
import com.ws.forms.typeDocument.CreationTypeDocumentForm;

public class AjoutTypeDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_TYPEDOCUMENT = "typeDocument";
	public static final String ATT_FORM = "form";
	public static final String VUE_SUCCES = "gestionTypeDocument";
	public static final String VUE_FORM = "/WEB-INF/JSP_typeDocument/ajoutTypeDocument.jsp";
	private TypeDocumentDao typeDocumentDao;
	private DaoFactory daoFactory;

	public void init() throws ServletException {
		daoFactory = DaoFactory.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

////////////////////
//Liste des types de contrat
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query = "select statut, libelle from typecontratsociete";
		try {
			String contrat;
			List<String> listContrat = new ArrayList<String>();
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			System.out.print("le résultat" + rs + "le résultat");
			while (rs.next()) {
				contrat = (rs.getString(1)) + "_" + (rs.getString(2));
				;
				listContrat.add(contrat);
			}
			rs.close();
			request.setAttribute("listContrat", listContrat);
		} catch (SQLException e) {
//TODO Auto-generated catch block
			e.printStackTrace();
		}
		connexion = null;
		preparedStatement = null;
		rs = null;
		query = "select statut, libelle from typecontratsociete";
		try {
			String contrat;
			List<String> listContrat = new ArrayList<String>();
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			System.out.print("le résultat" + rs + "le résultat");
			while (rs.next()) {
				contrat = (rs.getString(1)) + "_" + (rs.getString(2));
				;
				listContrat.add(contrat);
			}
			rs.close();
			request.setAttribute("listContrat", listContrat);
		} catch (SQLException e) {
//TODO Auto-generated catch block
			e.printStackTrace();
		}
///////////////////
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_typeDocument/ajoutTypeDocument.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
////////////////////
//Liste des types de contrat
Connection connexion = null;
PreparedStatement preparedStatement = null;
ResultSet rs = null;
String query = "select statut, libelle from typecontratsociete";
try {
	String contrat;
	List<String> listContrat = new ArrayList<String>();
	connexion = daoFactory.getConnection();
	preparedStatement = connexion.prepareStatement(query);
	rs = preparedStatement.executeQuery();
	System.out.print("le résultat" + rs + "le résultat");
	while (rs.next()) {
		contrat = (rs.getString(1)) + "_" + (rs.getString(2));
		;
		listContrat.add(contrat);
	}
	rs.close();
	request.setAttribute("listContrat", listContrat);
} catch (SQLException e) {
//TODO Auto-generated catch block
	e.printStackTrace();
}
connexion = null;
preparedStatement = null;
rs = null;
query = "select statut, libelle from typecontratclient";
try {
	String contrat;
	List<String> listContrat = new ArrayList<String>();
	connexion = daoFactory.getConnection();
	preparedStatement = connexion.prepareStatement(query);
	rs = preparedStatement.executeQuery();
	System.out.print("le résultat" + rs + "le résultat");
	while (rs.next()) {
		contrat = (rs.getString(1)) + "_" + (rs.getString(2));
		;
		listContrat.add(contrat);
	}
	rs.close();
	request.setAttribute("listContrat", listContrat);
} catch (SQLException e) {
//TODO Auto-generated catch block
	e.printStackTrace();
}
///////////////////
		/* Préparation de l'objet formulaire */
		CreationTypeDocumentForm form = new CreationTypeDocumentForm();
		/* Traitement de la requête et récupération du bean en résultant */
		TypeDocument typeDocument = form.creerTypeDocument(request);

		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_TYPEDOCUMENT, typeDocument);
		/* Si aucune erreur */
		if (form.getErreurs().isEmpty()) {
			try {
				typeDocumentDao.ajouterTypeDocument(typeDocument);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				request.setAttribute("typeDocuments", typeDocumentDao.listerTypeDocument());
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
			}
			response.sendRedirect(VUE_SUCCES);
		} else {
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}
	}

}
