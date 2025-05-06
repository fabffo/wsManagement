package com.ws.servlets.factureVente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.UUID;


@WebServlet("/AjoutfactureVente")
public class AjoutFactureVente extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP_factureVente/ajoutFactureVente.jsp").forward(request, response);
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	        String[] libelles = request.getParameterValues("libelle[]");
    	        String[] quantites = request.getParameterValues("quantite[]");
    	        String[] prix_hts = request.getParameterValues("prix_ht[]");
    	        String[] taux_tvas = request.getParameterValues("tva[]");
    	        String[] montant_tvas = request.getParameterValues("montant_tva[]");
    	        String[] total_ttcs = request.getParameterValues("total_ttc[]");

    	        String total_ht = request.getParameter("total_ht");
    	        String total_tva = request.getParameter("total_tva");
    	        String total_ttc = request.getParameter("total_ttc");

    	        String numeroFacture = UUID.randomUUID().toString().substring(0, 8);
    	        int factureId = 0;

    	        // --- Sauvegarde en BDD ---
    	        try {
    	            Class.forName("com.mysql.cj.jdbc.Driver");
    	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdb", "user", "password");

    	            String insertFacture = "INSERT INTO facture (numero, total_ht, total_tva, total_ttc) VALUES (?, ?, ?, ?)";
    	            PreparedStatement stmt = conn.prepareStatement(insertFacture, PreparedStatement.RETURN_GENERATED_KEYS);
    	            stmt.setString(1, numeroFacture);
    	            stmt.setDouble(2, Double.parseDouble(total_ht));
    	            stmt.setDouble(3, Double.parseDouble(total_tva));
    	            stmt.setDouble(4, Double.parseDouble(total_ttc));
    	            stmt.executeUpdate();

    	            var rs = stmt.getGeneratedKeys();
    	            if (rs.next()) {
    	                factureId = rs.getInt(1);
    	            }

    	            // Détails facture
    	            String insertDetail = "INSERT INTO detail_facture (facture_id, libelle, quantite, prix_ht, tva, montant_tva, total_ttc) VALUES (?, ?, ?, ?, ?, ?, ?)";
    	            PreparedStatement detailStmt = conn.prepareStatement(insertDetail);
    	            for (int i = 0; i < libelles.length; i++) {
    	                detailStmt.setInt(1, factureId);
    	                detailStmt.setString(2, libelles[i]);
    	                detailStmt.setInt(3, Integer.parseInt(quantites[i]));
    	                detailStmt.setDouble(4, Double.parseDouble(prix_hts[i]));
    	                detailStmt.setDouble(5, Double.parseDouble(taux_tvas[i]));
    	                detailStmt.setDouble(6, Double.parseDouble(montant_tvas[i]));
    	                detailStmt.setDouble(7, Double.parseDouble(total_ttcs[i]));
    	                detailStmt.addBatch();
    	            }
    	            detailStmt.executeBatch();

    	            stmt.close();
    	            detailStmt.close();
    	            conn.close();
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }

    	        // --- Génération PDF avec iText ---
    	        try {
    	            Document document = new Document();
    	            String fileName = "facture_" + numeroFacture + ".pdf";
    	            String pdfPath = getServletContext().getRealPath("/factures/") + fileName;
    	            PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
    	            document.open();

    	            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
    	            Font regularFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

    	            document.add(new Paragraph("Facture Wavy Services", titleFont));
    	            document.add(new Paragraph("N° : " + numeroFacture));
    	            document.add(new Paragraph(" "));

    	            PdfPTable table = new PdfPTable(6);
    	            table.setWidthPercentage(100);
    	            table.setWidths(new float[]{3, 1, 1, 1, 1, 1});
    	            table.addCell("Libellé");
    	            table.addCell("Qté");
    	            table.addCell("Prix HT");
    	            table.addCell("TVA %");
    	            table.addCell("Montant TVA");
    	            table.addCell("TTC");

    	            for (int i = 0; i < libelles.length; i++) {
    	                table.addCell(libelles[i]);
    	                table.addCell(quantites[i]);
    	                table.addCell(prix_hts[i]);
    	                table.addCell(taux_tvas[i]);
    	                table.addCell(montant_tvas[i]);
    	                table.addCell(total_ttcs[i]);
    	            }

    	            document.add(table);
    	            document.add(new Paragraph(" "));
    	            document.add(new Paragraph("Total HT: " + total_ht, regularFont));
    	            document.add(new Paragraph("Total TVA: " + total_tva, regularFont));
    	            document.add(new Paragraph("Total TTC: " + total_ttc, regularFont));
    	            document.close();

    	            // Affiche options Visualiser ou Télécharger
    	            response.setContentType("text/html;charset=UTF-8");
    	            PrintWriter out = response.getWriter();
    	            out.println("<html><body>");
    	            out.println("<h3>Facture enregistrée avec succès</h3>");
    	            out.println("<a href='factures/" + fileName + "' target='_blank' class='btn btn-info'>Visualiser PDF</a> ");
    	            out.println("<a href='factures/" + fileName + "' download class='btn btn-success'>Télécharger PDF</a>");
    	            out.println("</body></html>");
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }
    	    }
}
