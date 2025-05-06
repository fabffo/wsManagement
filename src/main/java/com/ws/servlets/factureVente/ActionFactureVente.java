package com.ws.servlets.factureVente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.ws.Dao.ClientDao;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.FactureVenteDao;
import com.ws.Dao.MaSocieteDao;
import com.ws.Dao.MissionDao;
import com.ws.beans.FactureVente;
import com.ws.beans.FactureVente_detail;
import com.ws.utils.PdfGenerator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/actionFactureVente")
public class ActionFactureVente extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private FactureVenteDao dao;
	private ClientDao clientDao;
	private MaSocieteDao maSocieteDao;
	private MissionDao missionDao;

	@Override
	public void init() throws ServletException {
		this.dao = DaoFactory.getInstance().getFactureVenteDao();
		this.clientDao = DaoFactory.getInstance().getClientDao();
		this.maSocieteDao = DaoFactory.getInstance().getMaSocieteDao();
		this.missionDao = DaoFactory.getInstance().getMissionDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String target = "/WEB-INF/JSP_factureVente/factureVente.jsp";

		try {
			if ("voir".equals(action)) {
				int id = Integer.parseInt(req.getParameter("id"));
				FactureVente fv = dao.trouverFactureVente(id);
				req.setAttribute("facture", fv);
			} else if ("ajouter".equals(action)) {
				// No-op
			} else if ("copier".equals(action)) {
				int id = Integer.parseInt(req.getParameter("id"));
				FactureVente original = dao.trouverFactureVente(id);
				original.setId(0);
				original.setNom(original.getNom() + "_copie");
				dao.copierFactureVente(original, original.getLignes());
				req.setAttribute("message", "Facture copiée avec succès.");
			} else {
				List<Map<String, Object>> factures = dao.rechercheFactureVentes(0, 100, "id DESC", new LinkedHashMap<>(), "", "");
				req.setAttribute("factures", factures);
				target = "/WEB-INF/JSP_factureVente/gestionFactureVente.jsp";
			}

			req.setAttribute("clients", clientDao.listerClient());
			req.setAttribute("emetteurs", maSocieteDao.listerMaSociete());
			req.setAttribute("missions", missionDao.listerMission());

		} catch (Exception e) {
			throw new ServletException("Erreur dans la gestion des factures de vente", e);
		}
		req.getRequestDispatcher(target).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		System.out.println("action"+action);
		try {
			if ("enregistrer".equals(action)) {
				FactureVente fv = construireFactureDepuisRequest(req);
				List<FactureVente_detail> lignes = construireDetailsDepuisRequest(req);
				dao.ajouterFactureVente(fv, lignes);

				// Génération du PDF
			    byte[] pdf = PdfGenerator.genererPdfFacture(fv, lignes);

			    // Chemin permanent
			    String storagePath = "C:/factures-wavy/";
			    new File(storagePath).mkdirs();
			    String pdfName = "facture-" + fv.getId() + ".pdf";
			    File pdfFile = new File(storagePath + pdfName);
			    try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
			        fos.write(pdf);
			    }

			 // Sauvegarde chemin dans la BDD
			    fv.setDocument(pdfName);
			    dao.updatePdfPath(fv.getId(), pdfName);

				resp.sendRedirect("actionFactureVente?action=voir&id=" + fv.getId());

			} else if ("visualiser".equals(action)) {
			    String rawHtml = req.getParameter("facture_html");
			    if (rawHtml == null || rawHtml.isEmpty()) {
			        throw new ServletException("Le contenu HTML de la facture est manquant.");
			    }

			    // Nettoyage jusqu'à <!-- HTML caché pour PDF -->
			    StringBuilder cleanedHtml1 = new StringBuilder();
			    try (BufferedReader reader = new BufferedReader(new StringReader(rawHtml))) {
			        String line;
			        while ((line = reader.readLine()) != null) {
			            if (line.contains("<!-- HTML caché pour PDF -->")) break;
			            cleanedHtml1.append(line).append("\n");
			        }
			    }

			    String htmlToConvert = cleanedHtml1.toString();

			    // Nettoyage du HTML
			    String cleanedHtml = htmlToConvert
			        .replaceAll("<input[^>]*value=\"([^\"]*)\"[^>]*/?>", "<span>$1</span>")
			        .replaceAll("<select[^>]*>.*?</select>", "")
			        .replaceAll("<button[^>]*>.*?</button>", "")
			        .replaceAll("undefined", "")
			        .replaceAll("<form[^>]*>", "")
			        .replaceAll("</form>", "");

			    // Structure complète HTML
			    String htmlContent = """
			        <!DOCTYPE html>
			        <html>
			        <head>
			            <meta charset="UTF-8">
			            <title>Prévisualisation Facture</title>
			            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
			            <style>
			                body { margin: 20px; }
			                .facture-box { max-width: 960px; margin: auto; border: 1px solid #ccc; padding: 20px; background: #fff; }
			            </style>
			        </head>
			        <body>%s</body>
			        </html>
			    """.formatted(cleanedHtml);

			    // Dossiers
			    String tempPath = getServletContext().getRealPath("/factures/temp");
			    String pdfPath = getServletContext().getRealPath("/factures/pdf");
			    new File(tempPath).mkdirs();
			    new File(pdfPath).mkdirs();

			    // Enregistrement HTML temporaire
			    String baseName = "facture-preview-" + System.currentTimeMillis();
			    File htmlFile = new File(tempPath, baseName + ".html");
			    try (FileWriter writer = new FileWriter(htmlFile, StandardCharsets.UTF_8)) {
			        writer.write(htmlContent);
			    }
			    File pdfFile = new File(pdfPath, baseName + ".pdf");

			 // Génération PDF avec iText 7
			    try (OutputStream os = new FileOutputStream(pdfFile)) {
			        PdfWriter writer = new PdfWriter();
			        PdfDocument pdfDoc = new PdfDocument(writer);
			        ConverterProperties props = new ConverterProperties();
			        HtmlConverter.convertToPdf(htmlContent, pdfDoc, props);
			    } catch (Exception e) {
			        throw new ServletException("Erreur lors de la génération du PDF avec iText", e);
			    }

			    // Redirection vers HTML (ou remplace avec .pdf si tu préfères l'ouvrir directement)
			    String httpPath = req.getContextPath() + "/factures/temp/" + baseName + ".html";
			    resp.sendRedirect(httpPath);
			}
		}

		 catch (Exception e) {
			throw new ServletException("Erreur en POST facture", e);
		}
	}

	private FactureVente construireFactureDepuisRequest(HttpServletRequest req) {
		FactureVente fv = new FactureVente();
		fv.setNom(req.getParameter("numero"));
		fv.setClient(Integer.parseInt(req.getParameter("client_id")));
		fv.setMaSociete(Integer.parseInt(req.getParameter("emetteur_id")));
		fv.setDate_facture(req.getParameter("date_facture"));
		fv.setDate_echeance(req.getParameter("date_echeance"));
		fv.setRaison_sociale_emetteur(req.getParameter("raison_sociale_emetteur"));
		fv.setAdresse1_emetteur(req.getParameter("adresse1_emetteur"));
		fv.setAdresse2_emetteur(req.getParameter("adresse2_emetteur"));
		fv.setAdresse3_emetteur(req.getParameter("adresse3_emetteur"));
		fv.setSiren_emetteur(req.getParameter("siren_emetteur"));
		fv.setConditions_paiement_emetteur(req.getParameter("conditions_paiement_emetteur"));
		fv.setRib_emetteur(req.getParameter("rib_emetteur"));
		fv.setTva_intracomm_emetteur(req.getParameter("tva_intracomm_emetteur"));
		fv.setRaison_sociale_client(req.getParameter("raison_sociale_client"));
		fv.setAdresse1_client(req.getParameter("adresse1_client"));
		fv.setAdresse2_client(req.getParameter("adresse2_client"));
		fv.setAdresse3_client(req.getParameter("adresse3_client"));
		fv.setMontant_tva(Double.valueOf(req.getParameter("total_tva")));
		fv.setMontant_ht(Double.valueOf(req.getParameter("total_ht")));
		fv.setMontant_ttc(Double.valueOf(req.getParameter("total_ttc")));
		return fv;
	}

	private List<FactureVente_detail> construireDetailsDepuisRequest(HttpServletRequest req) {
		String[] produits = req.getParameterValues("produit[]");
		String[] quantites = req.getParameterValues("quantite[]");
		String[] prix_hts = req.getParameterValues("prix_ht[]");
		String[] tvas = req.getParameterValues("tva[]");

		List<FactureVente_detail> lignes = new ArrayList<>();
		for (int i = 0; i < produits.length; i++) {
			FactureVente_detail ligne = new FactureVente_detail();
			ligne.setNumero_ligne(i + 1);
			ligne.setProduit(produits[i]);
			double qte = Double.parseDouble(quantites[i]);
			double prix = Double.parseDouble(prix_hts[i]);
			double taux = Double.parseDouble(tvas[i]);
			double montantHT = qte * prix;
			double montantTVA = montantHT * taux / 100;
			ligne.setMontant_ht(montantHT);
			ligne.setMontant_tva(montantTVA);
			ligne.setMontant_ttc(montantHT + montantTVA);
			ligne.setTaux_tva(taux);
			lignes.add(ligne);
		}
		return lignes;
	}
}
