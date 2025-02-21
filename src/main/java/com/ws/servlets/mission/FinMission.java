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
import com.ws.beans.Contrat;
import com.ws.beans.Mission;
import com.ws.beans.Tva;
import com.ws.beans.TypePrix;
import com.ws.configuration.Configuration;
import com.ws.forms.mission.ControleAnnulationMissionForm;
import com.ws.forms.mission.ControleFinMissionForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class VisualisationMission
 */
public class FinMission extends HttpServlet {
	// ===========================================================================
	// Initialisation de variables
	// ===========================================================================
	private static final long serialVersionUID = 1L;
	public static final String ATT_MISSION = "mission";
	public static final String ATT_FORM = "form";
	public static final String VUE_SUCCES = "gestionMission";
	public static final String VUE_FORM = "/WEB-INF/JSP_mission/finMission.jsp";
	private MissionDao missionDao;
	DaoFactory daoFactory;
	private int currentPage;
	private String fichierSortie;
	private Configuration configuration = new Configuration();
	public static final int TAILLE_TAMPON = 10240;
	public static String chemin_relatif_document_defaut;
	public static String chemin_absolu_document_defaut;

	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_COMPLEMENT = "complement";
	private static final String CHAMP_STATUT = "statut";
	private static final String CHAMP_ID_CONTRAT = "id_contrat";
	private static final String CHAMP_VERSION_CONTRAT = "version_contrat";
	private static final String CHAMP_CODE_TVA = "code_tva";
	private static final String CHAMP_PRIX_HT = "prix_ht";
	private static final String CHAMP_TYPE_PRIX = "type_prix";
	private static final String CHAMP_NBR_FACTURE = "nbr_facture";
	private static final String CHAMP_SOCIETE = "societe";
	private static final String CHAMP_TYPE_ENTITE = "type_entite";
	private static final String CHAMP_TYPE_CONTRAT = "type_contrat";
	private static final String CHAMP_DATE_DEMARRAGE = "date_demarrage";

	private int id_client;
	private String client;
	private int id_referent_collaborateur;
	private String collaborateur;
	String[] parts;
	String cheminFichier;
	int tampon_fichier;
	String action;
	Mission mission;
	String statut;
	String description;
	String nomFichier;
	String nomChamp;
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
		// recherche du contrat en cours
		// --------------------------------------
		try {
			mission = missionDao.trouverId(Integer.parseInt(request.getParameter("id")), type_entite);
			request.setAttribute("mission", mission);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", e.getMessage());
		}
		// -----------Récupération des listes -----------
		try {
			RecupList(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
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
		/* Préparation de l'objet formulaire suivant Existence ou Donnees */
		// -------------------------------------------------------------------
		ControleFinMissionForm formD = new ControleFinMissionForm();
		/* Traitement de la requête et récupération du bean en résultant */
		// ---------------------------------------------------------------------
			mission = formD.TerminerMission(request, mission);
		/* Ajout du bean à l'objet requête */
		// ---------------------------------------------------------------------
		request.setAttribute(ATT_MISSION, mission);

		/* Si aucune erreur */
		// on copie l'enregistrement et on réaffiche la page
		// ---------------------------------------------------------------------

		if ((formD.getErreurs().isEmpty())) {
			try {
				missionDao.terminerMission(mission);
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
			System.out.println("erreir");
			Map<String, String> erreurs = formD.getErreurs();

			// Parcourir et imprimer les valeurs de la Map
			for (Map.Entry<String, String> entry : erreurs.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue();

			    System.out.println("Erreur pour " + key + " : " + value);
			}
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}
	}

	// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	// PROCEDURES
	// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

	// ===========================================================================
	// ============ AFFICHER DATE
	// ===========================================================================
	private String affichateDate_JJMMAAAA(String date_JJMMAAAA) throws Exception {
		String selectedDate = date_JJMMAAAA;
		// Parse the selected date and transform it to aaaa-mm-jj
		if (selectedDate != null && !selectedDate.isEmpty()) {
			String[] dateParts = selectedDate.split("-");
			if (dateParts.length == 3) {
				String year = dateParts[0];
				String month = dateParts[1];
				String day = dateParts[2];
				selectedDate = day + "-" + month + "-" + year;
			}
		}
		return selectedDate;
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
			sortie = new BufferedOutputStream(new FileOutputStream(new File(cheminabsolu + fichierSortie)),
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

	// ===== Méthode utilitaire qui retourne null si un champ est vide, et son
	// contenu ===============================================================
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

		// --------- fichier
		fichierSortie = "mission.pdf";

		// --------- action
		action = request.getParameter("action");

		// --------- type entité
		type_entite = (String) request.getSession().getAttribute("type_entite");

		// ----- Récupérer la Map des missions depuis le contexte de la servlet -----
		String contract = type_entite + "_DEFAUT";
		Map<String, Map<String, String>> contracts = (Map<String, Map<String, String>>) getServletContext()
				.getAttribute("contracts");

		if (contracts != null && contracts.containsKey(contract)) {
			Map<String, String> contractPaths = contracts.get(contract);
			chemin_relatif_document_defaut = contractPaths.get("cheminRelatif");
			chemin_absolu_document_defaut = contractPaths.get("cheminAbsolu");
			request.getSession().setAttribute("chemin_relatif_document_defaut", chemin_relatif_document_defaut);
			request.getSession().setAttribute("chemin_absolu_document_defaut", chemin_absolu_document_defaut);
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
		List<Contrat> listContrat = new ArrayList<Contrat>();

		Connection connection = daoFactory.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = null;
		query = "select contrat.id, concat( contrat.id, '_', contrat.version, '_', contrat.nom_contrat,'_', contrat.type_contrat,'_', societe.raison_sociale)"
				+ " from contrat LEFT JOIN societe on contrat.id_client=societe.id where societe.type='" + type_entite
				+ "' " + "and contrat.type_entite='" + type_entite + "' " + " and contrat.id=" + mission.getId_contrat() + " and contrat.version="+mission.getVersion_contrat();
		resultSet = statement.executeQuery(query);
		if (resultSet.next()) {
			Contrat contrat = new Contrat();
			contrat.setId(resultSet.getInt(1));
			contrat.setCommentaire(resultSet.getString(2));
			request.setAttribute("contrat", contrat);
		}
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

		// ---------Liste des types de prix ---------------------
		query = "";
		List<TypePrix> listTypePrix = new ArrayList<TypePrix>();

		connection = daoFactory.getConnection();
		statement = connection.createStatement();
		resultSet = null;
		query = "select nom, libelle from typeprix";
		resultSet = statement.executeQuery(query);
		while (resultSet.next()) {
			TypePrix typePrix = new TypePrix();
			typePrix.setNom(resultSet.getString(1));
			typePrix.setLibelle(resultSet.getString(2));
			if (mission.getType_prix().equals(typePrix.getNom())) {
				typePrix.setPgmcreation("selected");
				listTypePrix.add(typePrix);
			}
		}
		request.setAttribute("listTypePrix", listTypePrix);
	}

}
