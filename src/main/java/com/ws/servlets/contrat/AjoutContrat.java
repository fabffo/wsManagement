/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET CONTRATCLIENT CREATION                          ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.contrat;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.tribes.ChannelSender;

import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.ContratDao;
import com.ws.beans.Societe;
import com.ws.beans.Tva;
import com.ws.beans.TypeContratSociete;
import com.ws.beans.Collaborateur;
import com.ws.beans.Contrat;
import com.ws.beans.Utilisateur;
import com.ws.configuration.Configuration;
import com.ws.configuration.DatasourceH;
import com.ws.forms.contrat.ControleDonneesContratForm;
import com.ws.forms.contrat.ControleExistenceContratForm;
import com.ws.forms.tva.ControleDonneesTva;
import com.ws.forms.tva.ControleExistenceTva;

/**
 * Servlet implementation class AjoutContrat
 */
public class AjoutContrat extends HttpServlet {
	// ===========================================================================
	// Initialisation de variables
	// ===========================================================================
	private static final long serialVersionUID = 1L;
	public static final String ATT_CONTRATCLIENT = "contrat";
	public static final String ATT_FORM = "form";
	public static final String VUE_SUCCES = "gestionContrat";
	public static final String VUE_FORM = "/WEB-INF/JSP_contrat/ajoutContrat.jsp";
	private ContratDao contratDao;
	DaoFactory daoFactory;
	private int currentPage;
	private String fichierSortie;
	private Configuration configuration = new Configuration();
	public static final int TAILLE_TAMPON = 10240;
	public static String chemin_relatif_document_defaut;
	public static String chemin_absolu_document_defaut;
	private static final String CHAMP_STATUT = "statut";
	private static final String CHAMP_DOCUMENT = "document";
	private static final String CHAMP_NOM_CONTRAT = "nom_contrat";
	private static final String CHAMP_TYPE_CONTRAT = "type_contrat";
	private static final String CHAMP_ID_REFERENT_COLLABORATEUR = "collaborateurSelectid";
	private static final String CHAMP_COLLABORATEUR = "collaborateur";
	private static final String CHAMP_ID_CLIENT = "clientSelectid";
	private static final String CHAMP_CLIENT = "client";
	private static final String CHAMP_DATE_DEMARRAGE = "date_demarrage";
	private static final String CHAMP_COMMENTAIRE = "commentaire";
	private int id_client;
	private String client;
	private int id_referent_collaborateur;
	private String collaborateur;
	String[] parts;
	String cheminFichier;
	int tampon_fichier;
	String action;
	Contrat contrat;
	String statut;
	String description;
	String nomFichier;
	String nomChamp;
	String type_entite;

	public void init() throws ServletException {
		daoFactory = DaoFactory.getInstance();
		this.contratDao = daoFactory.getContratDao();
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

		request.setAttribute("contrat", contrat);
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

		// -----------Récupération du fichier -----------
		if ("uploadFile".equals(action)) {
			try {
				traiterUpload(request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ----------Affichier vue form
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}

		// -----------Récupération du formulaire -----------
		else {
			/* Préparation de l'objet formulaire suivant Existence ou Donnees */
			// ---------------------------------------------------------------------
			ControleExistenceContratForm formX = new ControleExistenceContratForm();
			ControleDonneesContratForm formD = new ControleDonneesContratForm();

			/* Traitement de la requête et récupération du bean en résultant */
			// ---------------------------------------------------------------------
			Contrat contrat = formX.ControlerExistenceContrat(request);

			// si pas d'erreur existence on controle les données
			// si erreur de données on remplace le formulaire
			// ---------------------------------------------------------------------
			if (formX.getErreurs().isEmpty()) {
				contrat = formD.ControlerDonneesContrat(request);
				/* Ajoutl'objet métier controle Donnees à l'objet requête */
				request.setAttribute(ATT_FORM, formD);
			} else {
				/* Ajout de l'objet métier controle existence */
				request.setAttribute(ATT_FORM, formX);
			}

			/* Ajout du bean à l'objet requête */
			// ---------------------------------------------------------------------
			request.setAttribute(ATT_CONTRATCLIENT, contrat);

			/* Si aucune erreur */
			// on copie l'enregistrement et on réaffiche la page
			// ---------------------------------------------------------------------
			if ((formX.getErreurs().isEmpty()) && (formD.getErreurs().isEmpty())) {
				try {
					contratDao.ajouterContrat(contrat, chemin_absolu_document_defaut,
							(String) request.getSession().getAttribute("chemin_absolu_document_reel"),
							(String) request.getSession().getAttribute("chemin_relatif_document_reel"),
							type_entite);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					request.setAttribute("contrats", contratDao.listerContrat());
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					request.setAttribute("erreur", e.getMessage());
				}
				response.sendRedirect(VUE_SUCCES);
			}

			else {
				this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
			}
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
		fichierSortie = "contrat.pdf";

		// --------- action
		action = request.getParameter("action");

		// --------- type entité
		type_entite = (String) request.getSession().getAttribute("type_entite");

		// ----- Récupérer la Map des contrats depuis le contexte de la servlet -----
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

		// ---------Statut par défaut en cours -----------------------------
		statut = "En-cours";
		contrat = new Contrat();
		contrat.setStatut(statut);

		List<String> listStatut = new ArrayList<String>();
		listStatut.add("Termine");
		listStatut.add("Annule");
		request.setAttribute("listStatut", listStatut);

		// ---------date du jour par défaut -----------------------------
		// Obtenir la date du jour
		Date dateDuJour = new Date();
		// Formatter pour le format dd-MM-yyyy
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		contrat.setDate_demarrage(formatter.format(dateDuJour));

		String query = "";
		List<TypeContratSociete> listTypeContrat = new ArrayList<TypeContratSociete>();

		Connection connection = daoFactory.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = null;

		// ---------Liste des types de contrat  ---------------------
		try {
			if (type_entite.equals("SALARIE") || type_entite.equals("PRESTATAIRE")) {
				query = "select id, libelle from typecontratcollaborateur where statut ='" +type_entite + "'";
		}

		else {
			query = " select id, libelle from typecontratSociete where statut='"
					+ (String) request.getSession().getAttribute("type_entite") + "'";}

			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				TypeContratSociete typeContratSociete = new TypeContratSociete();
				typeContratSociete.setId(resultSet.getInt(1));
				typeContratSociete.setLibelle(resultSet.getString(2));
				typeContratSociete.setPgmcreation("");
				listTypeContrat.add(typeContratSociete);
			}
			request.setAttribute("listTypeContrat", listTypeContrat);


			// ---------Liste collaborateurs ------------------------------------
			query = "select id, nom, prenom from collaborateur where signataire_wavy=1";
			List<Collaborateur> listCollaborateur = new ArrayList<Collaborateur>();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Collaborateur collaborateur = new Collaborateur();
				collaborateur.setId(resultSet.getInt(1));
				collaborateur.setNom(resultSet.getString(2) + resultSet.getString(3));
				collaborateur.setPgmcreation("");
				listCollaborateur.add(collaborateur);
			}
			request.setAttribute("listCollaborateur", listCollaborateur);


			// ---------Liste entité -------------------------------------------
			List<Societe> listEntite = new ArrayList<Societe>();
			if (type_entite.equals("SALARIE") || type_entite.equals("PRESTATAIRE")) {
					query = "select id, concat(prenom, '_', nom) from collaborateur where type_collaborateur ='" +type_entite + "'";
			}

			else {
				query = "select id, raison_sociale from societe where type='"
						+ (String) request.getSession().getAttribute("type_entite") + "'";
			}
			System.out.println(query);
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Societe societe = new Societe();
				societe.setId(resultSet.getInt(1));
				societe.setRaison_sociale(resultSet.getString(2));
				societe.setPgmcreation("");
				listEntite.add(societe);
			}
			request.setAttribute("listEntite", listEntite);
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback(); // Annule la transaction en cas d'erreur
				} catch (SQLException rollbackEx) {
					rollbackEx.printStackTrace();
				}
			}
		} // Fermeture des ressources
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// ---------Liste clients -------------------------------------------
	}

	// ===========================================================================
	// ============ TRAITER UPLOAD
	// ===========================================================================
	private void traiterUpload(HttpServletRequest request) throws Exception {
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
			// On écrit définitivement le fichier sur le disque
			ecrireFichier(part, nomFichier, chemin_absolu_document_defaut, chemin_relatif_document_defaut);
			request.getSession().setAttribute("document", chemin_relatif_document_defaut + fichierSortie);
		}

		// ===========================================================================
		// ====== CALCUL ID ET NOM COLLABORATEUR
		// ===========================================================================
		String collaborateurInfo = request.getParameter("collaborateurInfo");
		String[] parts = collaborateurInfo.split("_");
		if (collaborateurInfo != null) {
			parts = collaborateurInfo.split("_");
			// Vérifier que la chaîne a bien été splittée en deux parties
			if (parts.length == 2) {
				try {
					// Convertir la première partie en entier
					id_referent_collaborateur = Integer.parseInt((parts[0]));

					// La deuxième partie reste une chaîne
					collaborateur = (parts[1]);
				} catch (NumberFormatException e) {
					id_referent_collaborateur = 0;
					collaborateur = "";
				}
			}
		} else {
			id_referent_collaborateur = 0;
			collaborateur = "";
		}

		// ===========================================================================
		// ======CALCUL ID ET NOM CLIENT
		// ===========================================================================
		String clientInfo = request.getParameter("clientInfo");
		parts = clientInfo.split("_");
		if (clientInfo != null) {
			parts = clientInfo.split("_");
			// Vérifier que la chaîne a bien été splittée en deux parties
			if (parts.length == 2) {
				try {
					// Convertir la première partie en entier
					id_client = Integer.parseInt((parts[0]));

					// La deuxième partie reste une chaîne
					client = (parts[1]);
				} catch (NumberFormatException e) {
					id_client = 0;
					client = "";
				}
			}
		} else {
			id_client = 0;
			client = "";
		}

		// Alimentation de la classe contrat
		// (l'instanciation est faite au moment de l'initialisation idem pour le statut
		contrat.setDocument((String) request.getSession().getAttribute("document"));
		contrat.setNom_contrat(getValeurChamp(request, CHAMP_NOM_CONTRAT));
		contrat.setType_contrat(getValeurChamp(request, CHAMP_TYPE_CONTRAT));
		contrat.setId_referent_collaborateur(id_referent_collaborateur);
		contrat.setCollaborateur(collaborateur);
		contrat.setId_client(id_client);
		contrat.setClient(client);
		contrat.setDate_demarrage(getValeurChamp(request, CHAMP_DATE_DEMARRAGE));
		contrat.setCommentaire(getValeurChamp(request, CHAMP_COMMENTAIRE));
		request.setAttribute("contrat", contrat);
		/////

	}

}
