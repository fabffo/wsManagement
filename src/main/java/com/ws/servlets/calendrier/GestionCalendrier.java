/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET GESTION CALENDRIER                              ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.calendrier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.beans.CalendrierMois;
import com.ws.beans.CalendrierMoisGlobal;
import com.ws.beans.TypeContratCollaborateur;
import com.ws.Dao.CalendrierDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GestionCalendrier extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CalendrierDao calendrierDao; 
	public static final String MODIFIER = "MODIFIER";
	public static final String SUPPRIMER = "SUPPRIMER";
	public static final String AJOUTER = "AJOUTER";
	public static final String VISUALISER = "VISUALISER";
	 private static final String CHAMP_MOIS         = "mois";

	public static final String TRIA = "TRIA";

	public static final String ATT_Calendrier = "calendrier";
	public static final String ATT_FORM = "form";

	public static final String VUE_SUCCES = "/WEB-INF/JSP_calendrier/gestionCalendrier.jsp";
	public static final String VUE_FORM = "/WEB-INF/JSP_calendrier/ajoutCalendrier.jsp";
	
	List<CalendrierMois> listCalendrierMois = new ArrayList<CalendrierMois>();
	List<Object> listeObject;
	List<CalendrierMoisGlobal> listCalendrierMoisGlobal= new ArrayList<CalendrierMoisGlobal>();
	
	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.calendrierDao = daoFactory.getCalendrierDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int annee = 2023;
		String mois = "janvier";
		int num_mois = 1;
		int annee_mois;
		
		if (request.getParameter("annee") != null) {
			annee = Integer.parseInt(request.getParameter("annee"));
			if (request.getParameter("type").equals("moins")) {
				annee = annee -1;
			} else
			{
				annee = annee +1;	
			}
			mois = "Janvier";
			num_mois = 1;
		} else if (request.getParameter("mois") != null) {
			mois = request.getParameter("mois");
			annee_mois = Integer.parseInt(request.getParameter("annee_mois"));
			if (request.getParameter("type").equals("moins")) {
				Object[][][]tableaumoisAnnee = validationMoisMoins( mois, annee_mois );
				mois = (String) tableaumoisAnnee[0][0][0];
				annee = (int) tableaumoisAnnee[0][0][1];
				num_mois = (int) tableaumoisAnnee[0][0][2];
		   
			} else
			{
				Object[][][]tableaumoisAnnee = validationMoisPlus( mois, annee_mois );
				mois = (String) tableaumoisAnnee[0][0][0];
				annee = (int) tableaumoisAnnee[0][0][1];
				num_mois = (int) tableaumoisAnnee[0][0][2];
			}
			
		} else
		{
			//Valeur par défaut
			annee = 2023;
			mois = "Janvier";
			num_mois = 1;
		}
		request.setAttribute("annee", annee); // liste enregistrements
		request.setAttribute("mois", mois); // paramètre nombres de pages
		//listCalendrierMois = calendrierDao.viewAllCalendriers(annee, num_mois);
		listeObject = calendrierDao.viewAllCalendriers(annee, num_mois);
		// Parcourir la liste et afficher les informations
		listCalendrierMois = new ArrayList<CalendrierMois>();
		List<CalendrierMoisGlobal> listCalendrierMoisGlobal= new ArrayList<CalendrierMoisGlobal>();
        for (Object objet : listeObject) {
            if (objet instanceof CalendrierMois) {
                CalendrierMois calendrierMois = (CalendrierMois) objet;
                listCalendrierMois.add(calendrierMois);
            } else if (objet instanceof CalendrierMoisGlobal) {
                CalendrierMoisGlobal calendrierMoisGlobal = (CalendrierMoisGlobal) objet;
                listCalendrierMoisGlobal.add(calendrierMoisGlobal);
            }
        }
		request.setAttribute("CalendrierList", listCalendrierMois); // liste enregistrements
		request.setAttribute("CalendrierListGlobal", listCalendrierMoisGlobal); // liste enregistrements
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_calendrier/gestionCalendrier.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	
    private Object[][][] validationMoisMoins( String mois, int annee_mois ){
       int numeroMois_case = 1;
       String mois_case;
       int annee_mois_case = annee_mois;
    // Création d'un tableau d'Objects pour stocker les paires d'int et String
       Object[][][] tableaumoisAnnee = new Object[1][1][3];

       // Ajout de quelques paires
       

			switch (mois) {

			case "Janvier":
				mois_case = "Décembre";
				numeroMois_case = 12;
				annee_mois_case = annee_mois - 1;
				tableaumoisAnnee[0][0][0] = mois_case;
			    tableaumoisAnnee[0][0][1] = annee_mois_case;
			    tableaumoisAnnee[0][0][2] = numeroMois_case;
				break;

			case "Février":
				mois_case = "Janvier";
				numeroMois_case = 1;
				tableaumoisAnnee[0][0][0] = mois_case;
			    tableaumoisAnnee[0][0][1] = annee_mois_case;
			    tableaumoisAnnee[0][0][2] = numeroMois_case;
				break;

			case "Mars":
				mois_case = "Février";
				numeroMois_case = 2;
				tableaumoisAnnee[0][0][0] = mois_case;
			    tableaumoisAnnee[0][0][1] = annee_mois_case;
			    tableaumoisAnnee[0][0][2] = numeroMois_case;
				break;
				
			case "Avril":
				mois_case = "Mars";
				numeroMois_case = 3;
				tableaumoisAnnee[0][0][0] = mois_case;
			    tableaumoisAnnee[0][0][1] = annee_mois_case;
			    tableaumoisAnnee[0][0][2] = numeroMois_case;
				break;
				
			case "Mai":
				mois_case = "Avril";
				numeroMois_case = 4;
				tableaumoisAnnee[0][0][0] = mois_case;
			    tableaumoisAnnee[0][0][1] = annee_mois_case;
			    tableaumoisAnnee[0][0][2] = numeroMois_case;
				break;
				
			case "Juin":
				mois_case = "Mai";
				numeroMois_case = 5;
				tableaumoisAnnee[0][0][0] = mois_case;
			    tableaumoisAnnee[0][0][1] = annee_mois_case;
			    tableaumoisAnnee[0][0][2] = numeroMois_case;
				break;
		
			case "Juillet":
				mois_case = "Juin";
				numeroMois_case = 6;
				tableaumoisAnnee[0][0][0] = mois_case;
			    tableaumoisAnnee[0][0][1] = annee_mois_case;
			    tableaumoisAnnee[0][0][2] = numeroMois_case;
				break;
				
			case "Août":
				mois_case = "Juillet";
				numeroMois_case = 7;
				tableaumoisAnnee[0][0][0] = mois_case;
			    tableaumoisAnnee[0][0][1] = annee_mois_case;
			    tableaumoisAnnee[0][0][2] = numeroMois_case;
				break;
				
			case "Septembre":
				mois_case = "Août";
				numeroMois_case = 8;
				tableaumoisAnnee[0][0][0] = mois_case;
			    tableaumoisAnnee[0][0][1] = annee_mois_case;
			    tableaumoisAnnee[0][0][2] = numeroMois_case;
				break;
				
			case "Octobre":
				mois_case = "Septembre";
				numeroMois_case = 9;
				tableaumoisAnnee[0][0][0] = mois_case;
			    tableaumoisAnnee[0][0][1] = annee_mois_case;
			    tableaumoisAnnee[0][0][2] = numeroMois_case;
				break;
				
			case "Novembre":
				mois_case = "Octobre";
				numeroMois_case = 10;
				tableaumoisAnnee[0][0][0] = mois_case;
			    tableaumoisAnnee[0][0][1] = annee_mois_case;
			    tableaumoisAnnee[0][0][2] = numeroMois_case;
				break;
				
			case "Décembre":
				mois_case = "Novembre";
				numeroMois_case = 11;
				tableaumoisAnnee[0][0][0] = mois_case;
			    tableaumoisAnnee[0][0][1] = annee_mois_case;
			    tableaumoisAnnee[0][0][2] = numeroMois_case;
				break;
			}
			
			
		
        return tableaumoisAnnee;
        }
	
    private Object[][][] validationMoisPlus( String mois, int annee_mois ){
        int numeroMois_case = 1;
        String mois_case;
        int annee_mois_case = annee_mois;
     // Création d'un tableau d'Objects pour stocker les paires d'int et String
        Object[][][] tableaumoisAnnee = new Object[1][1][3];

        // Ajout de quelques paires
        

 			switch (mois) {

 			case "Janvier":
 				mois_case = "Février";
 				numeroMois_case = 2;
 				tableaumoisAnnee[0][0][0] = mois_case;
 			    tableaumoisAnnee[0][0][1] = annee_mois_case;
 			    tableaumoisAnnee[0][0][2] = numeroMois_case;
 				break;

 			case "Février":
 				mois_case = "Mars";
 				numeroMois_case = 3;
 				tableaumoisAnnee[0][0][0] = mois_case;
 			    tableaumoisAnnee[0][0][1] = annee_mois_case;
 			    tableaumoisAnnee[0][0][2] = numeroMois_case;
 				break;

 			case "Mars":
 				mois_case = "Avril";
 				numeroMois_case = 4;
 				tableaumoisAnnee[0][0][0] = mois_case;
 			    tableaumoisAnnee[0][0][1] = annee_mois_case;
 			    tableaumoisAnnee[0][0][2] = numeroMois_case;
 				break;
 				
 			case "Avril":
 				mois_case = "Mai";
 				numeroMois_case = 5;
 				tableaumoisAnnee[0][0][0] = mois_case;
 			    tableaumoisAnnee[0][0][1] = annee_mois_case;
 			    tableaumoisAnnee[0][0][2] = numeroMois_case;
 				break;
 				
 			case "Mai":
 				mois_case = "Juin";
 				numeroMois_case = 6;
 				tableaumoisAnnee[0][0][0] = mois_case;
 			    tableaumoisAnnee[0][0][1] = annee_mois_case;
 			    tableaumoisAnnee[0][0][2] = numeroMois_case;
 				break;
 				
 			case "Juin":
 				mois_case = "Juillet";
 				numeroMois_case = 7;
 				tableaumoisAnnee[0][0][0] = mois_case;
 			    tableaumoisAnnee[0][0][1] = annee_mois_case;
 			    tableaumoisAnnee[0][0][2] = numeroMois_case;
 				break;
 		
 			case "Juillet":
 				mois_case = "Août";
 				numeroMois_case = 8;
 				tableaumoisAnnee[0][0][0] = mois_case;
 			    tableaumoisAnnee[0][0][1] = annee_mois_case;
 			    tableaumoisAnnee[0][0][2] = numeroMois_case;
 				break;
 				
 			case "Août":
 				mois_case = "Septembre";
 				numeroMois_case = 9;
 				tableaumoisAnnee[0][0][0] = mois_case;
 			    tableaumoisAnnee[0][0][1] = annee_mois_case;
 			    tableaumoisAnnee[0][0][2] = numeroMois_case;
 				break;
 				
 			case "Septembre":
 				mois_case = "Octobre";
 				numeroMois_case = 10;
 				tableaumoisAnnee[0][0][0] = mois_case;
 			    tableaumoisAnnee[0][0][1] = annee_mois_case;
 			    tableaumoisAnnee[0][0][2] = numeroMois_case;
 				break;
 				
 			case "Octobre":
 				mois_case = "Novembre";
 				numeroMois_case = 11;
 				tableaumoisAnnee[0][0][0] = mois_case;
 			    tableaumoisAnnee[0][0][1] = annee_mois_case;
 			    tableaumoisAnnee[0][0][2] = numeroMois_case;
 				break;
 				
 			case "Novembre":
 				mois_case = "Décembre";
 				numeroMois_case = 12;
 				tableaumoisAnnee[0][0][0] = mois_case;
 			    tableaumoisAnnee[0][0][1] = annee_mois_case;
 			    tableaumoisAnnee[0][0][2] = numeroMois_case;
 				break;
 				
 			case "Décembre":
 				mois_case = "Janvier";
 				numeroMois_case = 1;
 				annee_mois_case = annee_mois + 1;
 				tableaumoisAnnee[0][0][0] = mois_case;
 			    tableaumoisAnnee[0][0][1] = annee_mois_case;
 			    tableaumoisAnnee[0][0][2] = numeroMois_case;
 				break;
 			}
 			
 			
 		
         return tableaumoisAnnee;
         }
    
}
