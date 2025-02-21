/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET UTILISATEUR CREATION                            ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.utilisateur;

import java.io.IOException;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.UtilisateurDao;
import com.ws.beans.Utilisateur;
import com.ws.forms.utilisateur.CreationUtilisateurForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjoutUtilisateur
 */
public class AjoutUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_UTILISATEUR      = "utilisateur";
    public static final String ATT_FORM        = "form";
    public static final String VUE_SUCCES      = "gestionUtilisateur";
    public static final String VUE_FORM        = "/WEB-INF/JSP_utilisateur/ajoutUtilisateur.jsp";
    private UtilisateurDao utilisateurDao;
       
    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.utilisateurDao = daoFactory.getUtilisateurDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_utilisateur/ajoutUtilisateur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		CreationUtilisateurForm form = new CreationUtilisateurForm();
		/* Traitement de la requête et récupération du bean en résultant */
        Utilisateur utilisateur = form.creerUtilisateur( request );


		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_UTILISATEUR, utilisateur);
		/* Si aucune erreur */
		if (form.getErreurs().isEmpty()) {
			try {
				utilisateurDao.ajouterUtilisateur(utilisateur);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				request.setAttribute("utilisateurs", utilisateurDao.listerUtilisateur());
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
