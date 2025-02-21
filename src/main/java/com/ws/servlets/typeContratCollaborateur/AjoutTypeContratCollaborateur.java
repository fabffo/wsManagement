/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TYPE CONTRAT CREATION                           ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.typeContratCollaborateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypeContratCollaborateurDao;
import com.ws.beans.TypeContratCollaborateur;
import com.ws.forms.typeContratCollaborateur.CreationTypeCollaborateurForm;

/**
 * Servlet implementation class AjoutTypeContrat
 */
public class AjoutTypeContratCollaborateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_TYPE_CONTRAT      = "typeContratCollaborateur";
    public static final String ATT_FORM        = "form";
    public static final String VUE_SUCCES      = "gestionTypeContratCollaborateur";
    public static final String VUE_FORM        = "/WEB-INF/JSP_typeContratCollaborateur/ajoutTypeContratCollaborateur.jsp";
    private TypeContratCollaborateurDao typeContratCollaborateurDao;
       
    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.typeContratCollaborateurDao = daoFactory.getTypeContratCollaborateurDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_typeContratCollaborateur/ajoutTypeContratCollaborateur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		CreationTypeCollaborateurForm form = new CreationTypeCollaborateurForm();
		/* Traitement de la requête et récupération du bean en résultant */
        TypeContratCollaborateur typeContratCollaborateur = form.creerTypeContratCollaborateur( request );


		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_TYPE_CONTRAT, typeContratCollaborateur);
		/* Si aucune erreur */
		if (form.getErreurs().isEmpty()) {
			try {
				typeContratCollaborateurDao.ajouterTypeContratCollaborateur(typeContratCollaborateur);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				request.setAttribute("typeContrats", typeContratCollaborateurDao.listerTypeContratCollaborateur());
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
