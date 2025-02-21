/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TYPE CONTRAT COPIE                              ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.typeContratCollaborateur;

import java.io.IOException;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypeContratCollaborateurDao;
import com.ws.beans.TypeContratCollaborateur;
import com.ws.forms.typeContratCollaborateur.CopieTypeContratCollaborateurForm;
import com.ws.forms.typeContratCollaborateur.MajTypeContratCollaborateurForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MajTypeContrat
 */
public class CopieTypeContratCollaborateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_TYPE_CONTRAT      = "typeContratCollaborateur";
    public static final String ATT_FORM        = "form";
    public static final String VUE_SUCCES      = "gestionTypeContratCollaborateur";
    public static final String VUE_FORM        = "/WEB-INF/JSP_typeContratCollaborateur/copieTypeContratCollaborateur.jsp";
    private TypeContratCollaborateurDao typeContratCollaborateurDao;
    private Integer id ;
    private int currentPage;

    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.typeContratCollaborateurDao = daoFactory.getTypeContratCollaborateurDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("currentPage") != null)
        	request.getSession().setAttribute("currentPage", request.getParameter("currentPage"));

		id =Integer.parseInt(request.getParameter("id"));

		 try {
				request.setAttribute("typeContratCollaborateur", typeContratCollaborateurDao.trouverTypeContratCollaborateur(id));
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
			}
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setAttribute("id", id);
		/* Préparation de l'objet formulaire */
		CopieTypeContratCollaborateurForm form = new CopieTypeContratCollaborateurForm();
		/* Traitement de la requête et récupération du bean en résultant */
        TypeContratCollaborateur typeContratCollaborateur = form.copierTypeContratCollaborateur( request );


		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_TYPE_CONTRAT, typeContratCollaborateur);
		/* Si aucune erreur */
		if (form.getErreurs().isEmpty()) {
			try {
				typeContratCollaborateurDao.copierTypeContratCollaborateur(typeContratCollaborateur);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				request.setAttribute("typeContratCollaborateurs", typeContratCollaborateurDao.listerTypeContratCollaborateur());
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
