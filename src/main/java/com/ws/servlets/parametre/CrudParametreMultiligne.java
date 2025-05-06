/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
////    PROGRAMME crud multiligne                                         ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.parametre;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import com.ws.configuration.Configuration;
import com.ws.forms.parametre.ControleDonneesParametre;
import com.ws.forms.parametre.ControleDonneesParametreMultiligne;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

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
    String type_entite; // parametre type_entite
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
    private String annulerEntite; // exemple annuler tva;
    private String terminerEntite;// exemple terminer tva;
    private String faire_avenantEntite;// exemple faire avenant tva;
    private Integer classe_id;
    private DaoFactory daoFactory;
    private String getEntiteDao;

    private String action;
    private String[] parts;
    private String cheminFichier;
    private int tampon_fichier;
    private String fichierSortie;
    String description;
	String nomFichier;
	String nomChamp;
	private Configuration configuration = new Configuration();
	public static final int TAILLE_TAMPON = 10240;
	public static String chemin_relatif_document_defaut;
	public static String chemin_absolu_document_defaut;

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
                 List<List<Map<String, Object>>> rows = parametreEcranDao.lireParametre_ecranCrud_multiligne(parametreSysteme.getId(), request.getParameter("parametre_nom_programme"), classe, type_entite);

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
    	if ("uploadFile".equals(action)) {
			try {
				traiterUpload(request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
            if (form.getErreurs().isEmpty() && !"uploadFile".equals(action)) {
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
            } else if ("uploadFile".equals(action)) {
            	this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
            }
            else
            {
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

     // appel DAO pour annuler
        if (request.getParameter("parametre_nom_programme").equals("annulation")) {
            // Invocation de la méthode modifierTva de manière dynamique
            try {
                invokeDynamicMethod(annulerEntite, classe_id);
                response.sendRedirect(VUE_SUCCES);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // appel DAO pour annuler
        if (request.getParameter("parametre_nom_programme").equals("fin")) {
            // Invocation de la méthode modifierTva de manière dynamique
            try {
                invokeDynamicMethod(terminerEntite, classe_id);
                response.sendRedirect(VUE_SUCCES);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // appel DAO pour annuler
        if (request.getParameter("parametre_nom_programme").equals("avenant")) {
            // Invocation de la méthode modifierTva de manière dynamique
            try {
                invokeDynamicMethod(faire_avenantEntite, classe_id);
                response.sendRedirect(VUE_SUCCES);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    // Initialisation de l'écran, récupération des détails de paramètres
    private void initEcran(HttpServletRequest request) throws DaoException, ServletException {
    	//initialisation des variables de session
    	request.getSession().removeAttribute("document");

    	// --------- action
    			action = request.getParameter("action");
    	// gestion du paramètre type d'entite  pour filter les contrats
    			// =============================================================
    			if (request.getParameter("type_entite") != null) {
    				request.getSession().setAttribute("type_entite", request.getParameter("type_entite"));
    			} else {
    				if (request.getSession().getAttribute("type_entite") == null) {
    					// valeur par défaut
    					request.getSession().setAttribute("type_entite", "");
    				}
    			}
    			type_entite = (String) request.getSession().getAttribute("type_entite");

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
        		System.out.println("parametre_nom"+parametre_nom+" idparam "+parametreSysteme.getId()+" parametre_nom_programme "+parametre_nom_programme);
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
                annulerEntite = "annuler"+parametre_nom;
                terminerEntite = "terminer"+parametre_nom;
                faire_avenantEntite = "faire_avenant"+parametre_nom;



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
        System.out.println("methodeappeler"+methodName+paramTypes);
        	Method method = daoInstance.getClass().getMethod(methodName, paramTypes);
        	 return method.invoke(daoInstance, params);
    }

 // ===========================================================================
 	// ============ ECRIRE FICHIER
 	// ===========================================================================
 	private void ecrireFichier(Part part, String nomFichier, String cheminabsolu, String cheminrelatif)
 			throws IOException {
 		BufferedInputStream entree = null;
 		BufferedOutputStream sortie = null;
 		int tampon_fichier = configuration.getTampon();
 		try {
 			entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
 			sortie = new BufferedOutputStream(new FileOutputStream(new File(cheminabsolu + nomFichier)),
 					TAILLE_TAMPON);

 			byte[] tampon = new byte[TAILLE_TAMPON];
 			int longueur;
 			while ((longueur = entree.read(tampon)) > 0) {
 				sortie.write(tampon, 0, longueur);
 			}
 			// Appel pour rafraîchir le répertoire
 			File uploads = new File(cheminabsolu);
 			refreshDirectory(uploads);

 		} finally {
 			try {
 				sortie.close();
 			} catch (IOException ignore) {
 			}
 			try {
 				entree.close();
 			} catch (IOException ignore) {
 			}
 		}
 	}

 	// ==========================================================================
 	// ============ RECUPERER NOM FICHIER
 	// ===========================================================================
 	private static String getNomFichier(Part part) {
 		for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
 			if (contentDisposition.trim().startsWith("filename")) {
 				return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
 			}
 		}
 		return null;
 	}

 	// ===========================================================================
 	// ============ RAFRAICHIR REPERTOIRE
 	// ===========================================================================
 	private void refreshDirectory(File directory) {
 		// Récupérer la liste des fichiers dans le répertoire
 		File[] files = directory.listFiles();
 		if (files != null) {
 			for (File file : files) {
 			}
 		}
 	}

 // ===========================================================================
 	// ============ TRAITER UPLOAD
 	// ===========================================================================
 	private void traiterUpload(HttpServletRequest request) throws Exception {
 		// récupérer parametre id type contrat
 		//String parametre_nom = request.getParameter("parametre_nom");
 		System.out.println("parametre_nom"+parametre_nom);
 		String id_typeContrat = request.getParameter("type"+parametre_nom);
 		Map<String, Map<String, String>> contracts = (Map<String, Map<String, String>>) getServletContext().getAttribute("cheminsContrats");
 		if (contracts != null && contracts.containsKey(id_typeContrat)) {
			Map<String, String> contractPaths = contracts.get(id_typeContrat);
			chemin_relatif_document_defaut = contractPaths.get("cheminRelatif");
			chemin_absolu_document_defaut = contractPaths.get("cheminAbsolu");
		} else {
		}
 		if (contracts != null) {
 		    for (Map.Entry<String, Map<String, String>> entry : contracts.entrySet()) {
 		        String contractId = entry.getKey();
 		        Map<String, String> contractDetails = entry.getValue();
 		    }
 		} else {
 		    System.out.println("Aucun contrat trouvé.");
 		}
 		// On récupère le champ description comme d'habitude
 		description = request.getParameter("description");
 		request.setAttribute("description", description);

 		// On récupère le champ du fichier
 		Part part = request.getPart("fichier");

 		// On vérifie qu'on a bien reçu un fichier
 		nomFichier = getNomFichier(part);

 		// Si on a bien un fichier
 		if (nomFichier != null && !nomFichier.isEmpty()) {
 			nomChamp = part.getName();
 			request.setAttribute("NomfichierRecu", nomChamp);
 			// Corrige un bug du fonctionnement d'Internet Explorer
 			nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
 					.substring(nomFichier.lastIndexOf('\\') + 1);
 			nomFichier = nomFichier.replaceAll("\\s+", "");
 			// On écrit définitivement le fichier sur le disque
 			ecrireFichier(part, nomFichier, chemin_absolu_document_defaut, chemin_relatif_document_defaut);
 			request.getSession().setAttribute("document", chemin_relatif_document_defaut + nomFichier);
 			System.out.println("le docensession"+(String) request.getSession().getAttribute("document"));
 		}
 	}

}
