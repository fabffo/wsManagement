/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET CONTRATCLIENT GESTION                           ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.contrat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.ws.Dao.ContratDao;
import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.beans.Collaborateur;
import com.ws.beans.Contrat;
import com.ws.beans.Societe;
import com.ws.beans.TypeContratSociete;
import com.ws.configuration.Configuration;
import com.ws.forms.contrat.ControleDonneesContratForm;
import com.ws.forms.contrat.ControleExistenceContratForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class CopieContrat extends HttpServlet {
	// ===========================================================================
		// Initialisation de variables
		// ===========================================================================
		private static final long serialVersionUID = 1L;
		public static final String ATT_CONTRATCLIENT = "contrat";
		public static final String ATT_FORM = "form";
		public static final String VUE_SUCCES = "gestionContrat";
		public static final String VUE_FORM = "/WEB-INF/JSP_contrat/copieContrat.jsp";
		private ContratDao contratDao;
		DaoFactory daoFactory;
		private Configuration configuration = new Configuration();
		public static final int TAILLE_TAMPON = 10240;
		public static String chemin_relatif_document_reel;
		public static String chemin_absolu_document_reel;
		private static final String CHAMP_NOM_CONTRAT = "nom_contrat";
		private static final String CHAMP_TYPE_CONTRAT = "type_contrat";
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
		Contrat contrat;
		String statut;
		String contract;
		String description;
		String nomFichier;
		String nomChamp;
		int id_contrat;
		int id_avenant;
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

			// recherche du contrat en cours
			//--------------------------------------
			try {
				contrat = contratDao.trouverIdVersion(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("version")), type_entite);
				request.setAttribute("contrat", contrat);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
			}
			//on enregistre les infos documents avant changement
			//----------------------------------------------------
			request.getSession().setAttribute("chemin_relatif_document_avantmodif", contrat.getCheminRelatif());
			request.getSession().setAttribute("chemin_absolu_document_avantmodif", contrat.getCheminAbsolu());
			request.getSession().setAttribute("document_avantmodif", contrat.getDocument());

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
				/* Préparation de l'objet formulaire suivant Existence ou Donnees  */
				// ---------------------------------------------------------------------
				ControleExistenceContratForm formX = new ControleExistenceContratForm();
				ControleDonneesContratForm formD = new ControleDonneesContratForm();

				/* Traitement de la requête et récupération du bean en résultant */
				// ---------------------------------------------------------------------
				Contrat contrat = formX.ControlerExistenceContrat( request );

				// si pas d'erreur existence on controle les données
				// si erreur de données on remplace le formulaire
				// ---------------------------------------------------------------------
				if (formX.getErreurs().isEmpty()) {
					contrat = formD.ControlerDonneesContrat( request );
					/* Ajoutl'objet métier controle Donnees à l'objet requête */
					request.setAttribute(ATT_FORM, formD);}
				else {
					/* Ajout de l'objet métier controle existence*/
					request.setAttribute(ATT_FORM, formX);
				}

				/* Ajout du bean  à l'objet requête */
				// ---------------------------------------------------------------------
				request.setAttribute(ATT_CONTRATCLIENT, contrat);


				/* Si aucune erreur */
				// on copie l'enregistrement et on réaffiche la page
				// ---------------------------------------------------------------------
				if ((formX.getErreurs().isEmpty()) && (formD.getErreurs().isEmpty())) {
					try {
						contratDao.copierContrat(contrat, (String) request.getSession().getAttribute("chemin_relatif_document_avantmodif"),
								(String) request.getSession().getAttribute("chemin_absolu_document_avantmodif"), (String) request.getSession().getAttribute("document_avantmodif"),  type_entite);
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
					this.getServletContext().getRequestDispatcher(VUE_FORM)
					.forward(request, response);
				}
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
		private static String getValeurChamp(HttpServletRequest request, String id_avenantChamp) {
			String valeur = request.getParameter(id_avenantChamp);
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

			// --------- action
			action = request.getParameter("action");

			// --------- type entité
			type_entite = (String) request.getSession().getAttribute("type_entite");

			// ----- Récupérer la Map des contrats depuis le contexte de la servlet -----
			contract = "CLIENT_"+ request.getParameter("type_contrat");;
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

			// ---------Statut par défaut en cours -----------------------------
			statut = "En-cours";
			contrat = new Contrat();
			contrat.setStatut(statut);

			List<String> listStatut = new ArrayList<String>();
			listStatut.add("Termine");
			listStatut.add("Annule");
			request.setAttribute("listStatut", listStatut);

			// ---------date du jour par défaut  -----------------------------
			// Obtenir la date du jour
	        Date dateDuJour = new Date();
	        // Formatter pour le format dd-MM-yyyy
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        contrat.setDate_demarrage(formatter.format(dateDuJour));

			String query = "";
			List<TypeContratSociete> listTypeContratSociete = new ArrayList<TypeContratSociete>();

			Connection connection = daoFactory.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = null;

			// ---------Liste des types de contrat société ---------------------
			try {
				query = " select id, libelle from typecontratSociete";
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					TypeContratSociete typeContratSociete = new TypeContratSociete();
					typeContratSociete.setId(resultSet.getInt(1));
					typeContratSociete.setLibelle(resultSet.getString(2));
					typeContratSociete.setPgmcreation("");
					listTypeContratSociete.add(typeContratSociete);
				}
				request.setAttribute("listTypeContratSociete", listTypeContratSociete);
				// ---------Liste des types de contrat société ---------------------

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
				// ---------Liste collaborateurs ------------------------------------

				// ---------Liste clients -------------------------------------------
				List<Societe> listClient = new ArrayList<Societe>();
				query = "select id, raison_sociale from societe where type='CL'";
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					Societe societe = new Societe();
					societe.setId(resultSet.getInt(1));
					societe.setRaison_sociale(resultSet.getString(2));
					societe.setPgmcreation("");
					listClient.add(societe);
				}
				request.setAttribute("listClient", listClient);
			} catch (SQLException e) {
				if (connection != null) {
					try {
						connection.rollback(); // Annule la transaction en cas d'erreur
					} catch (SQLException rollbackEx) {
						rollbackEx.printStackTrace();
					}
				}
			}// Fermeture des ressources
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

			// ====== CALCUL ID ET NOM COLLABORATEUR
			// --------------------------------------------------------------------------
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
			} else
			{
				id_referent_collaborateur = 0;
				collaborateur = "";
			}


			// ======CALCUL ID ET NOM CLIENT
			// ---------------------------------------------------------------------------
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

			// ====== CALCUL FICHIER
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
				String nomFichierSortie = "contrat_" + client + "_" + id_contrat + "_" +id_avenant + ".pdf";
				ecrireFichier(part, nomFichierSortie, chemin_absolu_document_reel, chemin_relatif_document_reel);
				//on enregistre les infos documents avant changement
				request.getSession().setAttribute("chemin_relatif_document_avantmodif", chemin_relatif_document_reel);
				request.getSession().setAttribute("chemin_absolu_document_avantmodif", chemin_absolu_document_reel);
				request.getSession().setAttribute("document_avantmodif", nomFichierSortie);
			}


			// Alimentation de la classe contrat
			// (l'instanciation est faite au moment de l'initialisation idem pour le statut
			contrat.setId(id_contrat);
			contrat.setVersion(id_avenant);
			contrat.setDocument((String) request.getSession().getAttribute("document_avantmodif"));
			contrat.setCheminAbsolu(chemin_absolu_document_reel);
			contrat.setCheminRelatif(chemin_relatif_document_reel);
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
