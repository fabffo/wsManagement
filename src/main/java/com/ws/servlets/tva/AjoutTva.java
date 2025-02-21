/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TVA     CREATION                                ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.tva;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.TvaDao;
import com.ws.beans.Tva;
import com.ws.forms.tva.ControleDonneesTva;
import com.ws.forms.tva.ControleExistenceTva;

public class AjoutTva extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_TVA      = "tva";
    public static final String ATT_FORM        = "form";
    public static final String VUE_SUCCES      = "gestionTva";
    public static final String VUE_FORM        = "/WEB-INF/JSP_tva/ajoutTva.jsp";
    private TvaDao tvaDao;

    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.tvaDao = daoFactory.getTvaDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_tva/ajoutTva.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Préparation de l'objet formulaire suivant Existence ou Donnees  */
		// ---------------------------------------------------------------------
		ControleExistenceTva formX = new ControleExistenceTva();
		ControleDonneesTva formD = new ControleDonneesTva();

		/* Traitement de la requête et récupération du bean en résultant */
		// ---------------------------------------------------------------------
        Tva tva = formX.ControlerExistenceTva( request );

        // si pas d'erreur existence on controle les données
        			// si erreur de données on remplace le formulaire
     // ---------------------------------------------------------------------
        if (formX.getErreurs().isEmpty()) {
        	tva = formD.ControlerDonneesTva( request );
        	/* Ajoutl'objet métier controle Donnees à l'objet requête */
    		request.setAttribute(ATT_FORM, formD);}
    		else {
    			/* Ajout de l'objet métier controle existence*/
    			request.setAttribute(ATT_FORM, formX);
    		}

		/* Ajout du bean  à l'objet requête */
     // ---------------------------------------------------------------------
		request.setAttribute(ATT_TVA, tva);


		/* Si aucune erreur */
			// on copie l'enregistrement et on réaffiche la page
		// ---------------------------------------------------------------------
		if ((formX.getErreurs().isEmpty()) && (formD.getErreurs().isEmpty())) {
			try {
				tvaDao.ajouterTva(tva);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				request.setAttribute("tvas", tvaDao.listerTva());
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
			}
			response.sendRedirect(VUE_SUCCES);
		}


		else {
			this.getServletContext().getRequestDispatcher(VUE_FORM)
			.forward(request, response);
		}
	}

}
