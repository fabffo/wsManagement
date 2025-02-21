/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
////    PROGRAMME crud multiligne                                         ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.parametre;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.ast.EqualExpression;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.ParametreDao;
import com.ws.Dao.ParametreEcranDao;
import com.ws.beans.CrudParametre_champs_ecran;
import com.ws.beans.Entite;
import com.ws.beans.Menu;
import com.ws.beans.ParametreSysteme;
import com.ws.beans.Parametre_detail;
import com.ws.beans.Parametre_ecranCrud_entete;
import com.ws.beans.Parametre_ecranGestion_entete;
import com.ws.beans.Parametre_entete;
import com.ws.beans.Tva;
import com.ws.forms.parametre.ControleDonneesParametre;
import com.ws.forms.parametre.ControleDonneesParametreMultiligne;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CrudParametreMultiligne extends HttpServlet {

    private Object daoInstance;  // Instance dynamique du DAO
    private Object daoInstancelist;  // Instance dynamique du DAO
    private String daoInterfaceName;  // Nom de l'interface DAO
    private ParametreDao parametreDao; // DAO pour les paramètres
    private ParametreEcranDao parametreEcranDao;
    ParametreSysteme parametreSysteme;
	Parametre_ecranCrud_entete parametre_ecranCrud_entete;
	int largeur_ecran ;
    private List<Object> listSelect; //liste pour zone en liste (options proposer en select)
    String parametre_nom;  // Nom du paramètre
    //Class<?> clazz; //classe générique
    private String parametre_nom_programme; //nom du programme recu de gestion

    private static final long serialVersionUID = 1L;
    public static final String ATT_FORM = "form";
    public static final String VUE_SUCCES = "gestionParametre";
    public static final String VUE_FORM = "/WEB-INF/JSP_PARAMETRE/crudMultiligne.jsp";
    private String trouverClasse;	// exemple trouver tva
    private String actionEntite; // exemple modifier tva ou copier visualiser supprimer renommer
    private String modifierEntite; // exemple modifier tva
    private String copierEntite; // exemple modifier tva
    private String visualiserEntite;	//exemple visualiser tva
    private String supprimerEntite; // exemple supprimer tva
    private String renommerEntite; // exemple renommer tva
    private String ajouterEntite; // exemple ajouter tva
    private Integer classe_id;
    private DaoFactory daoFactory;
    private String getEntiteDao;

    public void init() throws ServletException {
        daoFactory = DaoFactory.getInstance();
        this.parametreDao = daoFactory.getParametreDao();  // Récupération du DAO pour la gestion des paramètres
        this.parametreEcranDao = daoFactory.getParametreEcranDao();  // Récupération du DAO pour la gestion des paramètres
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            initEcran(request);  // Initialisation de l'écran et récupération des données nécessaires
        } catch (DaoException e) {
            e.printStackTrace();
        }

        // Récupération de la page en cours
        if (request.getParameter("currentPage") != null)
            request.getSession().setAttribute("currentPage", request.getParameter("currentPage"));


        // Instanciation dynamique du DAO et invocation dynamique pour récupérer les infos de la page
        // suivant le programme en cours maj visualisation copie renommage suppression
            try {
                // Invocation de la méthode "trouverTva" sur l'instance du DAO
                Object classe = invokeDynamicMethod(trouverClasse, classe_id);

                // Appel de la méthode pour récupérer les paramètres
                //List<Map<String, Object>> listeChamps = parametreDao.getParametresChampsEcran(request.getParameter("parametre_nom_programme"), parametre_nom);
                List<List<Map<String, Object>>> rows = parametreEcranDao.lireParametre_ecranCrud_multiligne(parametreSysteme.getId(), request.getParameter("parametre_nom_programme"), classe, parametre_nom);

                // Passer la map 'validations' à la JSP
                request.setAttribute("rows", rows);

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("erreur", "Erreur lors de l'invocation dynamique : " + e.getMessage());
            }

        this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Object classe = new Object();
    	try {
            initEcran(request);  // Initialisation de l'écran
        } catch (DaoException e) {
            e.printStackTrace();
        }
        // Préparation du formulaire et traitement des données
        if (request.getParameter("parametre_nom_programme").equals("copie") || request.getParameter("parametre_nom_programme").equals("ajout") || request.getParameter("parametre_nom_programme").equals("maj") || request.getParameter("parametre_nom_programme").equals("renommage")) {
        	ControleDonneesParametreMultiligne form = new ControleDonneesParametreMultiligne();
			try {
				classe = form.ControlerDonneesParametreMultiligne(request);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            // Ajout des données au contexte de la requête
            request.setAttribute(ATT_FORM, form);

            // Si le formulaire est valide, mise à jour de la TVA
            if (form.getErreurs().isEmpty()) {
                try {
                    daoInstance = daoFactory.getClass().getMethod(getEntiteDao).invoke(daoFactory);

                    // appel DAO pour maj
                    if (request.getParameter("parametre_nom_programme").equals("maj")) {
                        // Invocation de la méthode modifierTva de manière dynamique
                        invokeDynamicMethod(modifierEntite, classe);
                    }

                    // appel DAO pour copie
                    if (request.getParameter("parametre_nom_programme").equals("copie")) {
                        // Invocation de la méthode modifierTva de manière dynamique
                        invokeDynamicMethod(copierEntite, classe);
                    }

                    // appel DAO pour renommer ==> on teste que le nom n'existe pas déjà
                    if (request.getParameter("parametre_nom_programme").equals("renommage")) {
                        // Invocation de la méthode renommer de manière dynamique
                        invokeDynamicMethod(renommerEntite, classe);
                    }

                    // appel DAO pour renommer ==> on teste que le nom n'existe pas déjà
                    if (request.getParameter("parametre_nom_programme").equals("ajout")) {
                        // Invocation de la méthode renommer de manière dynamique
                        invokeDynamicMethod(ajouterEntite, classe);
                    }

                    response.sendRedirect(VUE_SUCCES);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("erreur", "Erreur lors de la mise à jour : " + e.getMessage());
                }
            } else {
            	Map<String, String> erreurs = form.getErreurs();
            	for (Map.Entry<String, String> entry : erreurs.entrySet()) {
            	    String fieldName = entry.getKey();
            	    String errorMessage = entry.getValue();
            	    }

                this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
            }
        }
     // appel DAO pour supprimer
        if (request.getParameter("parametre_nom_programme").equals("suppression")) {
            // Invocation de la méthode modifierTva de manière dynamique
            try {
                invokeDynamicMethod(supprimerEntite, classe_id);
                response.sendRedirect(VUE_SUCCES);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    // Initialisation de l'écran, récupération des détails de paramètres
    private void initEcran(HttpServletRequest request) throws DaoException, ServletException {
        // Récupération du paramètre nom programme
                if (request.getParameter("parametre_nom_programme") != null) {
                    parametre_nom_programme = request.getParameter("parametre_nom_programme");
                    request.getSession().setAttribute("parametre_nom_programme", parametre_nom_programme);
                } else {
                    if (request.getSession().getAttribute("parametre_nom_programme") != null) {
                        parametre_nom_programme = (String) request.getSession().getAttribute("parametre_nom_programme");
                    }else {
                        parametre_nom_programme = "programme non trouvé"; //valeur par défaut
                    }
                }
        // Récupération de l'enregistrement modifié
        //classe_id = Integer.parseInt(request.getParameter("classe_id"));
     // Récupération du paramètre de l'entité (exemple Tva)
                if ( !"ajout".equals(request.getParameter("parametre_nom_programme"))) {
                    if (request.getParameter("classe_id") != null) {
                        classe_id = Integer.parseInt(request.getParameter("classe_id"));
                        request.getSession().setAttribute("classe_id", classe_id);
                    } else {
                        if (request.getSession().getAttribute("classe_id") != null) {
                            classe_id = Integer.parseInt((String) request.getSession().getAttribute("classe_id"));
                        }else {
                            classe_id = 1; //valeur par défaut
                        }
                    }
                }else {
                    classe_id = 0; //valeur par défaut
                }

     // Récupération du paramètre de l'entité (exemple Tva)
        if (request.getParameter("parametre_nom") != null) {
            parametre_nom = request.getParameter("parametre_nom");
            request.getSession().setAttribute("parametre_nom", parametre_nom);
        } else {
            if (request.getSession().getAttribute("parametre_nom") != null) {
                parametre_nom = (String) request.getSession().getAttribute("parametre_nom");
            }else {
                parametre_nom = "Tva"; //valeur par défaut
            }
        }
                // Récupération des détails des paramètres pour configurer l'interface
        		parametreSysteme = parametreEcranDao.lireParametreSysteme(parametre_nom);
        		parametre_ecranCrud_entete = parametreEcranDao.lireParametre_ecranCrud_entete(parametreSysteme.getId(), parametre_nom_programme);
        		largeur_ecran = parametre_ecranCrud_entete.getLargeur_ecran();
        		request.setAttribute("largeur_ecran", largeur_ecran);


        		ParametreSysteme parametreEntete = parametreDao.lireEnteteParametre(parametre_nom);
                //List<Parametre_detail> parametreDetails = parametreDao.listerDetailParametre(parametreEntete.getId());
                daoInterfaceName = parametre_nom + "Dao";
                trouverClasse = "trouver"+parametre_nom;
                modifierEntite = "modifier"+parametre_nom;
                copierEntite = "copier"+parametre_nom;
                visualiserEntite = "visualiser"+parametre_nom;
                supprimerEntite = "supprimer"+parametre_nom;
                renommerEntite = "renommer"+parametre_nom;
                ajouterEntite = "ajouter"+parametre_nom;



                getEntiteDao = "get"+ parametre_nom+ "Dao"; //exemple getTvaDao

        // Initialisation dynamique du DAO correspondant
        try {
            daoInstance = daoFactory.getClass().getMethod("get" + daoInterfaceName).invoke(daoFactory);
        } catch (Exception e) {
            throw new ServletException("Erreur lors de l'initialisation du DAO : " + e.getMessage(), e);
        }
    }

    // Méthode pour invoquer dynamiquement des méthodes du DAO de l'entité en cours
    private Object invokeDynamicMethod(String methodName, Object... params) throws Exception {
        Class<?>[] paramTypes = new Class<?>[params.length];
        for (int i = 0; i < params.length; i++) {
            paramTypes[i] = params[i].getClass();
               }
        	Method method = daoInstance.getClass().getMethod(methodName, paramTypes);
        	 return method.invoke(daoInstance, params);
    }


 // Méthode pour invoquer dynamiquement des méthodes du DAO de l'entité en cours
    private Object invokeDynamicMethodList(String methodName) throws Exception {
        Method method = daoInstancelist.getClass().getMethod(methodName);
        return method.invoke(daoInstancelist);
    }

 // Méthode générique pour ajouter une configuration dynamique de validation
    private static void ajouterValidation(Map<String, Map<String, Object>> validations, String parametre_nom_programme,Object classe, String fieldName,
                                          boolean required, boolean readonly, int minlength, int maxlength, String type, String step, String placeholder, String type_zone, int largeur_libelle, List<Object> listSelect ) throws Exception {
        Map<String, Object> config = new HashMap<>();
        config.put("required", required);
        config.put("readonly", readonly);
        config.put("minlength", minlength);
        config.put("maxlength", maxlength);
        config.put("type", type);
        config.put("type_zone", type_zone);
        config.put("largeur_libelle", largeur_libelle);
        if (step != null) config.put("step", step);
        if (placeholder != null) config.put("placeholder", placeholder);

        if ( !("ajout".equals(parametre_nom_programme))) {
        // Génération du nom de la méthode getter (par exemple, getNom pour 'nom')
        String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        // Utilisation de la réflexion pour appeler dynamiquement la méthode getter
        Class<?> clazz = classe.getClass();
        Method method = clazz.getMethod(methodName);
        // Récupérer la valeur du champ
        Object valeur = method.invoke(classe);
        // Ajouter la valeur au dictionnaire
        config.put("valeur", valeur);
        } else
        {
            // Ajouter la valeur au dictionnaire en défaut à blanc
            config.put("valeur", "");
        }

        if ("select".equals(type_zone)) {
        // Ajouter la liste sous un nom spécifique
        config.put("listSelect", listSelect);
        }
        // Ajouter cette configuration à la map 'validations'
        validations.put(fieldName, config);
    }

}
