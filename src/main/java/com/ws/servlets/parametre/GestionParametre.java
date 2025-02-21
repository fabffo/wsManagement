/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
////    PROGRAMME SERVLET PARAMETRE GESTION                               ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.parametre;

import java.lang.reflect.Method; // Pour l'invocation dynamique des méthodes
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ws.Dao.ActionDao;
import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.ParametreDao;
import com.ws.Dao.ParametreEcranDao;
import com.ws.Dao.Parametre_ecranGestion_enteteDao;
import com.ws.Dao.Parametre_ecranGestion_ligneDao;
import com.ws.Dao.StatutDao;
import com.ws.beans.EcranGestion_tableLigneTh;
import com.ws.beans.Entite;
import com.ws.beans.ParametreSysteme;
import com.ws.beans.Parametre_detail;
import com.ws.beans.Parametre_ecranCrud_ligne;
import com.ws.beans.Parametre_ecranGestion_entete;
import com.ws.beans.Parametre_ecranGestion_ligne;
import com.ws.beans.Parametre_entete;
import com.ws.beans.Statut;
import com.ws.beans.ZoneDeRecherche;
import com.ws.menu.MenuItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GestionParametre extends HttpServlet {

	// Déclaration des variables globales pour la servlet
	private Object daoInstance;  // Instance dynamique du DAO pour permettre des invocations à des DAO variables (TvaDao, etc.)
	private String daoInterfaceName;  // Nom de l'interface DAO, qui peut être modifié dynamiquement en fonction du paramètre
	private StatutDao statutDao;
	private List<Statut> listeStatuts;
	private static final long serialVersionUID = 1L;
	private ParametreDao parametreDao; // DAO spécifique à la gestion des paramètres
	private ParametreEcranDao parametreEcranDao; // DAO spécifique à la gestion des paramètres
	private Parametre_ecranGestion_enteteDao parametre_ecranGestion_enteteDao; // DAO spécifique à la gestion des paramètres
	private Parametre_ecranGestion_ligneDao parametre_ecranGestion_ligneDao; // DAO spécifique à la gestion des paramètres
	private ActionDao actionDao; // DAO spécifique action
	public static final String MODIFIER = "MODIFIER";  // Actions possibles dans le formulaire
	public static final String SUPPRIMER = "SUPPRIMER";
	public static final String AJOUTER = "AJOUTER";
	public static final String VISUALISER = "VISUALISER";
	public static final String ATT_FORM = "form";  // Clé pour les attributs de formulaire

	// Définitions des vues JSP
	public String VUE_SUCCES ;
	public String VUE_FORM ;

	// DAO Factory pour l'accès aux différentes entités DAO
	public DaoFactory daoFactory;


	// Variables utilisées pour la gestion de la recherche et de la pagination
	String select_like;
	String searchValeur = "";  // Valeur de recherche actuelle
	String critere_recherche_valeur = "nom";  // Critère de recherche par défaut (ici "nom")
	String critere_recherche_valeur_defaut = "nom";
	String critere_recherche_libelle;  // Libellé du critère de recherche
	String select_tri_libelle;
	String libelle_tri;  // Variable pour trier les colonnes dans l'interface
	List<Map<String, Object>> list;  // Liste générique des enregistrements
	List<EcranGestion_tableLigneTh> listEcranGestion_tableLigneTh;  // Liste des colonnes à afficher
	Map<String, String> dictionnaire_sel;  // Dictionnaire pour stocker les options sélectionnées
	Map<String, String> dictionnaire_tri_colonne;  // Dictionnaire pour gérer le tri des colonnes
	Map<String, String> dictionnaire_largeur_colonne;  // Dictionnaire pour stocker la largeur des colonnes
	Map<String, String> dictionnaire_detail;
	String parametre_nom;  // Nom du paramètre actuel
	String nom_ecran_gestion;
	String nom_ecran_crud;
	String critere_nom;
	List<Map<String, String>> listSelRecherche;  // Liste des critères de recherche sélectionnés
	List<ZoneDeRecherche> listZonesDeRecherche;  // Liste des zones de recherche disponibles
	//List<String> listRef;
	EcranGestion_tableLigneTh ecranGestion_tableLigneTh;  // Classe pour représenter une colonne d'une table d'affichage
	Map<String, String> dictionnaire_nom_colonne;  // Dictionnaire des noms de colonnes
	Map<String, String> dictionnaire_type_colonne;  // Dictionnaire des noms de colonnes
	String triColonne;  // Colonne actuellement triée
	String select_triColonne = "";  // Tri actif sur une colonne
	String action; //action sur recherche
	String rechercheEntites; // nom dynamique méthode pour listers les entités dispos exemple rechercheTvas
	String rechercheLikeEntites; // nom dynamique méthode pour listers avec valeur like, les entités dispos exemple recherchelikeTvas

	// Variables pour les programmes associés (gestion, ajout, maj, etc.)
	String nomProgramme_gestion;
	String nomProgramme_ajout;
	String nomProgramme_maj;
	String nomProgramme_copie;
	String nomProgramme_visualisation;
	String nomProgramme_renommage;
	String nomProgramme_suppression;

	String colonne_encours_de_tri;  // Colonne en cours de tri
	int currentPage = 1;  // Page actuelle dans la pagination
	int recordsPerPage = 6;  // Nombre d'enregistrements par page
	int maxPage = 0;  // Page maximale pour la pagination
	int noOfRecords = 0;  // Nombre total d'enregistrements
	int pasPage=2 ; //pas de page
	String type_entete;

	String tag_statut="";
	String nom_entite="";

	// Méthode d'initialisation de la servlet
	// Elle est appelée au démarrage de la servlet et permet d'initialiser la factory DAO et le DAO de gestion des paramètres
	public void init() throws ServletException {
		daoFactory = DaoFactory.getInstance();  // Initialisation de la factory pour récupérer les DAO
		this.parametreDao = daoFactory.getParametreDao();  // Récupération du DAO pour la gestion des paramètres
		this.parametreEcranDao = daoFactory.getParametreEcranDao();
		this.parametre_ecranGestion_enteteDao = daoFactory.getParametre_ecranGestion_enteteDao();  // Récupération du DAO pour la gestion des paramètres
		this.parametre_ecranGestion_ligneDao = daoFactory.getParametre_ecranGestion_ligneDao();  // Récupération du DAO pour la gestion des paramètres
		this.statutDao = daoFactory.getStatutDao();
		this.actionDao = daoFactory.getActionDao();
	}

	// Méthode doGet pour gérer les requêtes HTTP GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			initEcran(request);  // Initialisation de l'écran et récupération des données nécessaires
		} catch (DaoException e) {
			e.printStackTrace();
		}

		// Gestion de la remise à zéro des critères de recherche
		if ("AZERO".equals(action)) {
			// Réinitialisation des valeurs de recherche
			request.getSession().setAttribute("searchValeur", null);
			request.getSession().setAttribute(critere_recherche_libelle, null);
			request.setAttribute("searchValeur", null);
		}

		// Gestion du tri des colonnes dans l'interface
		switch (dictionnaire_tri_colonne.get(colonne_encours_de_tri)) {
			case "bi bi-caret-up text-white":
				if (colonne_encours_de_tri.contains(".")) {
				select_triColonne = colonne_encours_de_tri +" asc ";
				}
				else
				{
					select_triColonne = " "+ parametre_nom+"."+ colonne_encours_de_tri +" asc ";
				}
				break;
			case "bi bi-caret-down text-white":
				if (colonne_encours_de_tri.contains(".")) {
				select_triColonne =  colonne_encours_de_tri +" desc ";
				}
				else
				{
					select_triColonne =  " "+ parametre_nom+"."+colonne_encours_de_tri +" desc ";
				}
				break;
			default:
				if (colonne_encours_de_tri.contains(".")) {
				select_triColonne =  colonne_encours_de_tri +" asc ";
				}
				else
				{
					select_triColonne =  " "+ parametre_nom+"."+colonne_encours_de_tri +" asc ";
				}
				break;
		}

		// Gestion de la page courante
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));  // Récupération de la page demandée
		} else {
			// Si aucune page n'est spécifiée, on utilise celle en session
			if (request.getSession().getAttribute("currentPage") != null)
				currentPage = Integer.parseInt((String) request.getSession().getAttribute("currentPage"));
		}

		// Récupération des critères de recherche depuis la requête ou la session
		if (!"AZERO".equals(action)) {
			if (request.getParameter(critere_recherche_libelle) != null) {
				critere_recherche_valeur = request.getParameter(critere_recherche_libelle);
			} else {
				if (request.getSession().getAttribute(critere_recherche_libelle) != null) {
					critere_recherche_valeur = (String) request.getSession().getAttribute(critere_recherche_libelle);
				}
			}
			if (request.getParameter("searchValeur") != null) {
				searchValeur = request.getParameter("searchValeur");
				currentPage = 1;  // Nouvelle recherche, retour à la première page
			} else {
				if (request.getSession().getAttribute("searchValeur") != null) {
					searchValeur = (String) request.getSession().getAttribute("searchValeur");
				}
			}
		} else {
			// Si action = "AZERO", on réinitialise la valeur de recherche
			request.getSession().setAttribute("searchValeur", "");
			searchValeur = null;
		}

		// Gestion de la recherche et du tri dynamique
		if (searchValeur != null) {
			if (searchValeur.isEmpty()) {
				try {
					// Appel dynamique de la méthode  via la réflexion (exemple rechercheTvas)
					list =  (List<Map<String, Object>>) invokeDynamicMethod(rechercheEntites, (currentPage - 1) * recordsPerPage, recordsPerPage, select_triColonne, dictionnaire_nom_colonne, tag_statut, nom_entite);
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.getSession().setAttribute("searchValeur", "");
			} else {
				request.getSession().setAttribute("searchValeur", searchValeur);
				request.getSession().setAttribute(critere_recherche_libelle, critere_recherche_valeur);
				select_like = critere_recherche_valeur + " like '" + searchValeur + "%'";
				try {
					// Appel dynamique de la méthode rechercheLikeTvas via la réflexion
					list =  (List<Map<String, Object>>) invokeDynamicMethod(rechercheLikeEntites, (currentPage - 1) * recordsPerPage, recordsPerPage, select_triColonne, select_like, dictionnaire_nom_colonne, tag_statut, nom_entite);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				// Si aucune recherche spécifique, on fait un tri normal
				list =  (List<Map<String, Object>>) invokeDynamicMethod(rechercheEntites, (currentPage - 1) * recordsPerPage, recordsPerPage, select_triColonne, dictionnaire_nom_colonne, tag_statut, nom_entite);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Récupération du nombre d'enregistrements de manière dynamique
		Integer integerRecords = null;
		try {
		integerRecords = (Integer) invokeDynamicMethod("getIntegerRecords");
			noOfRecords = integerRecords;
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Mise à jour des valeurs dans le dictionnaire pour gérer le tri des colonnes
		String nom_colonne;
		String largeur_colonne;
		String zone_i;
		String ref;
		listEcranGestion_tableLigneTh = new ArrayList<>();
		for (String cle : dictionnaire_nom_colonne.keySet()) {
			ecranGestion_tableLigneTh = new EcranGestion_tableLigneTh();
			nom_colonne = cle;
			largeur_colonne = dictionnaire_largeur_colonne.get(cle);
			if (cle.equals(colonne_encours_de_tri)) {
				zone_i = (dictionnaire_tri_colonne.get(cle).equals("bi bi-caret-up text-white") ? "bi bi-caret-down text-white" : "bi bi-caret-up text-white");
			} else {
				zone_i = (dictionnaire_tri_colonne.get(cle));
			}
			ref = "gestionParametre?tri" + parametre_nom + "=" + cle + "&tri" + parametre_nom + "_" + cle + "=" + zone_i + "&currentPage=1";
			//on extrait le nom de la colonne sans afficher l'entité

			  if (nom_colonne.contains(".")) {
				  String input = nom_colonne;
				  int lastDotIndex = input.lastIndexOf("."); // Trouver le dernier "."
				  String result =input.substring(lastDotIndex + 1); // Extraire tout après le dernier "."
			  ecranGestion_tableLigneTh.setNom_colonne(result); } else {
			  ecranGestion_tableLigneTh.setNom_colonne(nom_colonne); }

			ecranGestion_tableLigneTh.setLargeur_colonne(largeur_colonne);
			ecranGestion_tableLigneTh.setZone_i(zone_i);
			ecranGestion_tableLigneTh.setRef(ref);
			listEcranGestion_tableLigneTh.add(ecranGestion_tableLigneTh);
		}

		// Gestion de la pagination
		int noOfPages = (int) Math.ceil((double) noOfRecords / recordsPerPage);
		int noBegin = 1;  // Début de la pagination
		//int pasPage = 2;  // Pas de pagination par défaut
		maxPage = (maxPage == 0) ? pasPage : maxPage;  // Si pas de maxPage, on prend le pas par défaut
		if (request.getParameter("noBegin") != null) {
			noBegin = Integer.parseInt(request.getParameter("noBegin"));
		}
		if (request.getParameter("maxPage") != null) {
			maxPage = Integer.parseInt(request.getParameter("maxPage"));
		}

		// Mise à jour des attributs de requête
		request.setAttribute("listEcranGestion_tableLigneTh", listEcranGestion_tableLigneTh);
		request.setAttribute("listEntite", list);
		request.setAttribute("critere_recherche_libelle", critere_recherche_libelle);
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("noBegin", noBegin);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("pasPage", pasPage);
		request.setAttribute("searchValeur", searchValeur);
		request.setAttribute("parametre_nom", parametre_nom);
		request.setAttribute("nom_ecran_gestion", nom_ecran_gestion);
		request.setAttribute("nom_ecran_crud", nom_ecran_crud);

		// Mise à jour des attributs de session pour le tri et la recherche
		updateSessionAttributes(request, critere_recherche_valeur);

		// Redirection vers la vue de succès (JSP)
		this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response);
	}

	// Méthode doPost pour gérer les requêtes HTTP POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			initEcran(request);  // Initialisation des données de l'écran
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	// Méthode pour mettre à jour les attributs de session pour les critères de recherche
	public void updateSessionAttributes(HttpServletRequest request, String critere_recherche_valeur) {
		for (ZoneDeRecherche zone : listZonesDeRecherche) {
			if (critere_recherche_valeur.equals(zone.getNom())) {
				zone.setSelected("selected");
			} else {
				zone.setSelected("");
			}
		}
		request.setAttribute("listZonesDeRecherche", listZonesDeRecherche);
	}

	// Méthode pour initialiser l'écran, récupérer les détails des paramètres, et gérer le tri
	private void initEcran(HttpServletRequest request) throws DaoException, ServletException {
		// gestion du paramètre typeSocieté pour filter les contrats
		// =============================================================
		if (request.getParameter("nom_entite") != null) {
			request.getSession().setAttribute("nom_entite", request.getParameter("nom_entite"));
		} else {
			if (request.getSession().getAttribute("nom_entite") == null) {
				// valeur par défaut
				request.getSession().setAttribute("nom_entite", "");
			}
		}
		nom_entite = (String) request.getSession().getAttribute("nom_entite");

		List<MenuItem> menuItems = parametreDao.getMenuItemsFromDatabase();
        // Passer la liste des menus à la JSP
        request.setAttribute("menuItems", menuItems);


        // Passer la liste des menus à la JSP
        request.setAttribute("menuItems", menuItems);

     // Gestion de la page courante
     		if (request.getParameter("currentPage") != null) {
     			currentPage = Integer.parseInt(request.getParameter("currentPage"));  // Récupération de la page demandée
     			} else {
     			// Si aucune page n'est spécifiée, on utilise celle en session
     			if (request.getSession().getAttribute("currentPage") != null)
     				currentPage = Integer.parseInt((String) request.getSession().getAttribute("currentPage"));
     		}

		action = request.getParameter("action");
		// Récupération du paramètre de l'entité
		if (request.getParameter("parametre_nom") != null) {
			parametre_nom = request.getParameter("parametre_nom");
			request.getSession().setAttribute("parametre_nom", parametre_nom);
		} else {
			if (request.getSession().getAttribute("parametre_nom") != null) {
				parametre_nom = (String) request.getSession().getAttribute("parametre_nom");
			}else {
				//parametre_nom = "Tva"; //valeur par défaut
				parametre_nom="ParametreSysteme";
			}
		}
		 Map<String, String> actionsDisponibles = null;
			try {
				actionsDisponibles = actionDao.getActionsDisponibles(parametre_nom);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        // Passer les actions disponibles à la JSP
	        request.setAttribute("actionsDisponibles", actionsDisponibles);

		// Récupération des détails des paramètres pour configurer l'interface
				//ParametreSysteme parametreSysteme = parametreDao.lireEnteteParametre(parametre_nom);
				ParametreSysteme parametreSysteme = parametreEcranDao.lireParametreSysteme(parametre_nom);
				//Parametre_ecranGestion_entete parametre_ecranGestion_entete = parametre_ecranGestion_enteteDao.trouverParametre_ecranGestion_entete(parametreSysteme.getId());
				Parametre_ecranGestion_entete parametre_ecranGestion_entete = parametreEcranDao.lireParametre_ecranGestion_entete(parametreSysteme.getId());


				List<Parametre_ecranGestion_ligne> parametreDetails = parametreEcranDao.listerParametre_ecranGestion_ligne(parametreSysteme.getId());


				daoInterfaceName = parametre_nom + "Dao";
				critere_recherche_libelle = "critere" + parametre_nom;
				select_tri_libelle = "select_tri" + parametre_nom;
				libelle_tri = "tri" + parametre_nom;
				recordsPerPage = parametre_ecranGestion_entete.getNbrEnrgPage();
				pasPage = parametre_ecranGestion_entete.getPasPage();
				type_entete= parametre_ecranGestion_entete.getType_entete();
				System.out.println("type entete"+type_entete);
				request.getSession().setAttribute("type_entete", type_entete);
				rechercheEntites="recherche" + parametre_nom +"s";
				rechercheLikeEntites="rechercheLike" + parametre_nom+"s";
				nom_ecran_gestion = parametreSysteme.getNom_ecran_gestion();
				nom_ecran_crud = parametreSysteme.getNom_ecran_crud();
				VUE_SUCCES = "/WEB-INF/JSP_PARAMETRE/"+nom_ecran_gestion+".jsp";
				VUE_FORM = "/WEB-INF/JSP_PARAMETRE/"+ nom_ecran_gestion+".jsp";


				// Initialiser le statut par défaut si entête statut
				if (type_entete.equals("statut")) {
					tag_statut = request.getParameter("tag_statut") != null ? request.getParameter("tag_statut")
							: (String) request.getSession().getAttribute("tag_statut");

					if (tag_statut == null || tag_statut.isEmpty()) {
						tag_statut = "En-cours"; // Statut par défaut
					}
					request.getSession().setAttribute("tag_statut", tag_statut);

					try {
						listeStatuts = statutDao.listerStatut();
						request.getSession().setAttribute("listeStatuts", listeStatuts);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

		// Récupérer les critères de tri depuis la requête ou la session
		String parametreTri = "";
		String valeurParametreTri = "";
		if (request.getParameter(libelle_tri) != null) {
			parametreTri = request.getParameter(libelle_tri);
			String triEntite_ = "tri" + parametre_nom + "_" + request.getParameter(libelle_tri);
			valeurParametreTri = request.getParameter(triEntite_);
			request.getSession().setAttribute(libelle_tri, request.getParameter(libelle_tri));
			request.getSession().setAttribute(triEntite_, request.getParameter(triEntite_));
		} else {
			if (request.getSession().getAttribute(libelle_tri) != null) {
				parametreTri = (String) request.getSession().getAttribute(libelle_tri);
			}
		}

		if ("AZERO".equals(action)) {
			// si réninitialisation on passe la colonne par défaut
			parametreTri = "";
			valeurParametreTri = "";
			request.getSession().setAttribute(libelle_tri, null);
		}

		// Initialisation dynamique du DAO via la réflexion
		try {
			daoInstance = daoFactory.getClass().getMethod("get" + daoInterfaceName).invoke(daoFactory);
		} catch (Exception e) {
			throw new ServletException("Erreur lors de l'initialisation du DAO : " + e.getMessage(), e);
		}

		// Configuration des programmes pour chaque action
		nomProgramme_gestion = "gestionParametre";
		nomProgramme_ajout = "crudParametre";
		//nomProgramme_maj = "maj" + parametre_nom;
		nomProgramme_maj = "crudParametre";
		nomProgramme_copie = "crudParametre";
		nomProgramme_visualisation = "crudParametre";
		nomProgramme_renommage = "crudParametre";
		nomProgramme_suppression = "crudParametre";
		request.setAttribute("nomProgramme_ajout", nomProgramme_ajout);
		request.setAttribute("nomProgramme_maj", nomProgramme_maj);
		request.setAttribute("nomProgramme_copie", nomProgramme_copie);
		request.setAttribute("nomProgramme_visualisation", nomProgramme_visualisation);
		request.setAttribute("nomProgramme_renommage", nomProgramme_renommage);
		request.setAttribute("nomProgramme_suppression", nomProgramme_suppression);

		// Alimentation des zones d'affichage (zones de recherche et tri des colonnes)
		listZonesDeRecherche = new ArrayList<>();
		//listRef = new ArrayList<String>();
		String indicateur_defaut = "N";
		dictionnaire_tri_colonne = new HashMap<>();
		dictionnaire_largeur_colonne = new HashMap<>();
		dictionnaire_nom_colonne = new LinkedHashMap<>();
		for (Parametre_ecranGestion_ligne detail : parametreDetails) {
			// Configuration des zones de recherche
				if ("true".equals(detail.getZone_recherche())) {

				ZoneDeRecherche zoneDeRecherche = new ZoneDeRecherche();

				//zoneDeRecherche.setNom(detail.getNom());
				zoneDeRecherche.setNom(detail.getNom_zone());

				String input = zoneDeRecherche.getNom();
				String type_colonne = detail.getType_colonne();
				if (input.contains(".")) {
			        int lastDotIndex = input.lastIndexOf("."); // Trouver le dernier "."
			        String result = input.substring(lastDotIndex + 1); // Extraire tout après le dernier "."
			        zoneDeRecherche.setValeur(result);
				}
				else
				{
					 zoneDeRecherche.setValeur(input);
				}
				if ("N".equals(indicateur_defaut)) {
					indicateur_defaut = "O";

					//critere_recherche_valeur_defaut = detail.getNom();
					critere_recherche_valeur_defaut = detail.getNom_zone();
				}
				// si type de colonne entité on récupère le nom de l'entité comme nom de propriété
				if (type_colonne.equals("entite")){
					zoneDeRecherche.setNom(detail.getNom_zone()+".nom");
				}
				// si type de colonne entité on récupère le nom de l'entité comme nom de propriété
				if (type_colonne.equals("reference")){
					zoneDeRecherche.setNom( parametre_nom +"."+detail.getNom_zone());
				}
				listZonesDeRecherche.add(zoneDeRecherche);
			}

			// Configuration du tri des colonnes
			if (!parametreTri.equals("")) {
				if (parametreTri.equals(detail.getNom_zone())) {
					colonne_encours_de_tri = detail.getNom_zone();
					dictionnaire_tri_colonne.put(detail.getNom_zone(), valeurParametreTri);
				} else {
					dictionnaire_tri_colonne.put(detail.getNom_zone(), "bi bi-caret-down text-white");
				}
			} else {
				// Si aucun tri n'est configuré, on tri par défaut sur la première colonne
				//if (detail.getLigne() == 1) {
					if (detail.getNumero_champ() == 1) {
					colonne_encours_de_tri = detail.getNom_zone();
					dictionnaire_tri_colonne.put(detail.getNom_zone(), "bi bi-caret-up text-white");
				} else {
					dictionnaire_tri_colonne.put(detail.getNom_zone(), "bi bi-caret-down text-white");
				}
			}
			dictionnaire_largeur_colonne.put(detail.getNom_zone(), Integer.toString(detail.getLargeur_colonne()));
			dictionnaire_nom_colonne.put(detail.getNom_zone(), detail.getNom_zone());
		}
		request.setAttribute("listZonesDeRecherche", listZonesDeRecherche);
	}

	// Méthode pour appeler dynamiquement des méthodes du DAO en utilisant la réflexion
	private Object invokeDynamicMethod(String methodName, Object... params) throws Exception {
		Class<?>[] paramTypes = new Class<?>[params.length];
		for (int i = 0; i < params.length; i++) {
			paramTypes[i] = params[i].getClass();  // Détermination des types de paramètres à partir des valeurs passées
		}
		Method method = daoInstance.getClass().getMethod(methodName, paramTypes);  // Récupération de la méthode
		return method.invoke(daoInstance, params);  // Invocation de la méthode
	}

}
