/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET CONTRATCLIENT VISUALISATION                     ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.mission;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.MissionDao;
import com.ws.beans.ContratAncien;
import com.ws.beans.Mission;
import com.ws.beans.Tva;
import com.ws.beans.TypePrix;
import com.ws.configuration.Configuration;
import com.ws.forms.mission.ControleRenommageMissionForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class VisualisationMission
 */
public class RenommageMission extends HttpServlet {
	// ===========================================================================
	// Initialisation de variables
	// ===========================================================================
	private static final long serialVersionUID = 1L;
	public static final String ATT_CONTRATCLIENT = "mission";
	public static final String ATT_FORM = "form";
	public static final String VUE_SUCCES = "gestionMission";
	public static final String VUE_FORM = "/WEB-INF/JSP_mission/renommageMission.jsp";
	private MissionDao missionDao;
	DaoFactory daoFactory;
	private Configuration configuration = new Configuration();
	public static final int TAILLE_TAMPON = 10240;
	public static String chemin_relatif_document_reel;
	public static String chemin_absolu_document_reel;
	private static final String CHAMP_NOM_CONTRAT = "nom_mission";
	private static final String CHAMP_TYPE_CONTRAT = "type_mission";
	private static final String CHAMP_DATE_DEMARRAGE = "date_demarrage";
	private static final String CHAMP_COMMENTAIRE = "commentaire";
	private int id_client;
	private String client;
	private int id_referent_collaborateur;
	private String collaborateur;
	String[] parts;
	String cheminFichier;
	int tampon_fichier;
	String action ;
	Mission mission;
	String statut;
	String contract;
	String description;
	String nomFichier;
	String nomChamp;
	int id;
	int version;
	String type_entite;

	public void init() throws ServletException {
		daoFactory = DaoFactory.getInstance();
		this.missionDao = daoFactory.getMissionDao();
	}

	// ===========================================================================
	// ============ DO GET
	// ===========================================================================
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// -----------Récupération des paramètres -----------
		try {
			RecupParam(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// -----------Récupération des listes -----------
		try {
			RecupList(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// recherche du mission en cours
		//--------------------------------------
		try {
			mission = missionDao.trouverId(Integer.parseInt(request.getParameter("id")), type_entite);
			request.setAttribute("mission", mission);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", e.getMessage());
		}
		//on enregistre les infos documents avant changement
		//----------------------------------------------------
		request.getSession().setAttribute(ATT_CONTRATCLIENT, mission);

		this.getServletContext().getRequestDispatcher(VUE_FORM)
		.forward(request, response);
	}

	// ===========================================================================
	// ============ DO POST
	// ===========================================================================
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// -----------Récupération des paramètres -----------
		try {
			RecupParam(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// -----------Récupération des listes -----------
		try {
			RecupList(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// -----------Récupération du formulaire -----------
			/* Préparation de l'objet formulaire suivant Existence ou Donnees  */
			// ---------------------------------------------------------------------
			ControleRenommageMissionForm formD = new ControleRenommageMissionForm();

			/* Traitement de la requête et récupération du bean en résultant */
			// ---------------------------------------------------------------------
			mission = formD.RenommerMission( request, (Mission) request.getSession().getAttribute(ATT_CONTRATCLIENT) );

			/* Ajout durequest.getSession(). bean  à l'objet requête */
			// ---------------------------------------------------------------------
			request.setAttribute(ATT_CONTRATCLIENT, mission);


			/* Si aucune erreur */
			// on copie l'enregistrement et on réaffiche la page
			// ---------------------------------------------------------------------
			if ((formD.getErreurs().isEmpty())) {
				try {
					missionDao.renommerMission(mission);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					request.setAttribute("missions", missionDao.listerMission());
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					request.setAttribute("erreur", e.getMessage());
				}
				response.sendRedirect(VUE_SUCCES);
			}


			else {
				/* Ajout de l'objet métier controle existence*/
				request.setAttribute(ATT_FORM, formD);
				this.getServletContext().getRequestDispatcher(VUE_FORM)
				.forward(request, response);
			}

	}

	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	// PROCEDURES
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
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

	// ==================================================================================
	// ===== Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	// ==================================================================================
	private static String getValeurChamp(HttpServletRequest request, String versionChamp) {
		String valeur = request.getParameter(versionChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}


	// ===========================================================================
	// ============ RECUPERER LES PARAMETRES
	// ===========================================================================
	private void RecupParam(HttpServletRequest request) throws Exception {
		// récupération de l'enregistrement modifié
		// ---------------------------------------------------------------------
		id =Integer.parseInt(request.getParameter("id"));
		version =Integer.parseInt(request.getParameter("version"));

		// --------- type entité
		type_entite = (String) request.getSession().getAttribute("type_entite");

		// --------- action
		action = request.getParameter("action");

		// ----- Récupérer la Map des missions depuis le contexte de la servlet -----
		contract = "CLIENT_"+ request.getParameter("type_mission");;
		Map<String, Map<String, String>> contracts = (Map<String, Map<String, String>>) getServletContext()
				.getAttribute("contracts");

		if (contracts != null && contracts.containsKey(contract)) {
			Map<String, String> contractPaths = contracts.get(contract);
			chemin_relatif_document_reel = contractPaths.get("cheminRelatif");
			chemin_absolu_document_reel = contractPaths.get("cheminAbsolu");
			request.getSession().setAttribute("chemin_relatif_document_reel", chemin_relatif_document_reel);
			request.getSession().setAttribute("chemin_absolu_document_reel", chemin_absolu_document_reel);
			request.getSession().setAttribute("contracts", contracts);

		} else {
		}

		// ---------Récupération page en cours -------------------------------
		if (request.getParameter("currentPage") != null)
			request.getSession().setAttribute("currentPage", request.getParameter("currentPage"));
	}


	// ===========================================================================
	// ============ RECUPERER LES LISTES
	// ===========================================================================
	private void RecupList(HttpServletRequest request) throws Exception {
		// ---------date du jour par défaut -----------------------------
		// Obtenir la date du jour
		Date dateDuJour = new Date();
		// Formatter pour le format dd-MM-yyyy
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		mission.setDate_demarrage(formatter.format(dateDuJour));

		// ---------contrat ---------------------
		String query = "";
		List<ContratAncien> listContrat = new ArrayList<ContratAncien>();

		Connection connection = daoFactory.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = null;
		query = "select contrat.id, concat( contrat.id, '_', contrat.version, '_', contrat.nom_contrat,'_', contrat.type_contrat,'_', societe.raison_sociale)"
				+ " from contrat LEFT JOIN societe on contrat.id_client=societe.id where societe.type='" + type_entite
				+ "' " + "and contrat.type_entite='" + type_entite + "' " + " and contrat.id=" + mission.getId_contrat() + " and contrat.version="+mission.getVersion_contrat();
		System.out.println(query);
		resultSet = statement.executeQuery(query);
		if (resultSet.next()) {
			ContratAncien contrat = new ContratAncien();
			contrat.setId(resultSet.getInt(1));
			contrat.setCommentaire(resultSet.getString(2));
			request.setAttribute("contrat", contrat);
		}


		// ---------tva ---------------------
		query = "";
		connection = daoFactory.getConnection();
		statement = connection.createStatement();
		resultSet = null;
		query = "select concat(taux, ' ', libelle) from tva where tva.nom='"+mission.getCode_tva()+"'";
		resultSet = statement.executeQuery(query);
		while (resultSet.next()) {
			Tva tva = new Tva();
			tva.setLibelle(resultSet.getString(1));
			System.out.println(tva.getLibelle());
			request.setAttribute("tva", tva);
		}


		// ---------types de prix ---------------------
		query = "";
		connection = daoFactory.getConnection();
		statement = connection.createStatement();
		resultSet = null;
		query = "select nom, libelle from typeprix where nom='"+ mission.getType_prix() +"'";
		resultSet = statement.executeQuery(query);
		if (resultSet.next()) {
			TypePrix typePrix = new TypePrix();
			typePrix.setLibelle(resultSet.getString(1));
			request.setAttribute("typePrix", typePrix);
		}

	}
}
