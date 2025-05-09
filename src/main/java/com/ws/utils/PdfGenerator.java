package com.ws.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ws.beans.FactureVente;
import com.ws.beans.FactureVente_detail;

public class PdfGenerator {

    public static byte[] genererPdfFacture(FactureVente fv, List<FactureVente_detail> lignes) throws Exception {
        Document doc = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(doc, out);
        doc.open();

        Font titleFont = new Font(FontFamily.HELVETICA, 16, Font.BOLD);
        Font normalFont = new Font(FontFamily.HELVETICA, 12);

        Paragraph title = new Paragraph("Wavy Services", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        doc.add(title);
        doc.add(Chunk.NEWLINE);

        PdfPTable header = new PdfPTable(2);
        header.setWidthPercentage(100);
        header.addCell(getCell("N° Facture : " + fv.getNom(), Element.ALIGN_LEFT, normalFont));
        header.addCell(getCell("Date : " + fv.getDate_facture() + " | Échéance : " + fv.getDate_echeance(), Element.ALIGN_RIGHT, normalFont));
        doc.add(header);
        doc.add(Chunk.NEWLINE);

        PdfPTable parties = new PdfPTable(2);
        parties.setWidthPercentage(100);
        parties.addCell(getCell(
                "Émetteur :\n" +
                fv.getRaison_sociale_emetteur() + "\n" +
                fv.getAdresse1_emetteur() + "\n" +
                fv.getAdresse2_emetteur() + "\n" +
                fv.getAdresse3_emetteur(), Element.ALIGN_LEFT, normalFont));
        parties.addCell(getCell(
                "Client :\n" +
                fv.getRaison_sociale_client() + "\n" +
                fv.getAdresse1_client() + "\n" +
                fv.getAdresse2_client() + "\n" +
                fv.getAdresse3_client(), Element.ALIGN_LEFT, normalFont));
        doc.add(parties);
        doc.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{4, 2, 2, 2});
        table.addCell("Produit");
        table.addCell("Montant HT");
        table.addCell("TVA");
        table.addCell("Total TTC");

        for (FactureVente_detail l : lignes) {
            table.addCell(l.getProduit());
            table.addCell(String.format("%.2f", l.getMontant_ht()));
            table.addCell(String.format("%.2f", l.getMontant_tva()));
            table.addCell(String.format("%.2f", l.getMontant_ttc()));
        }

        doc.add(table);
        doc.add(Chunk.NEWLINE);

        PdfPTable totals = new PdfPTable(2);
        totals.setHorizontalAlignment(Element.ALIGN_RIGHT);
        totals.setWidthPercentage(40);
        totals.setWidths(new float[]{2, 2});
        totals.addCell("Total HT");
        totals.addCell(String.format("%.2f", fv.getMontant_ht()));
        totals.addCell("Total TVA");
        totals.addCell(String.format("%.2f", fv.getMontant_tva()));
        totals.addCell("Total TTC");
        totals.addCell(String.format("%.2f", fv.getMontant_ttc()));
        doc.add(totals);
        doc.add(Chunk.NEWLINE);

        Paragraph conditions = new Paragraph(
        	    "Conditions de paiement : " + (fv.getConditions_paiement_emetteur() != null ? fv.getConditions_paiement_emetteur() : "") + "\n" +
        	    "RIB : " + (fv.getRib_emetteur() != null ? fv.getRib_emetteur() : "") + "\n" +
        	    "SIREN : " + (fv.getSiren_emetteur() != null ? fv.getSiren_emetteur() : "") + "\n" +
        	    "TVA intracom : " + (fv.getTva_intracomm_emetteur() != null ? fv.getTva_intracomm_emetteur() : ""),
        	    normalFont
        	);
        	doc.add(conditions);


        doc.close();
        return out.toByteArray();
    }

    private static PdfPCell getCell(String text, int alignment, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, font));
        cell.setPadding(5);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }

    public static void genererPdfFacture(File pdfFile, String htmlContent, String basePath) throws Exception {
        try (OutputStream os = new FileOutputStream(pdfFile)) {
            com.openhtmltopdf.pdfboxout.PdfRendererBuilder builder = new com.openhtmltopdf.pdfboxout.PdfRendererBuilder();
            builder.useFastMode();
            builder.toStream(os);
            builder.withHtmlContent(htmlContent, "file:///" + basePath.replace("\\", "/") + "/");
            builder.run();
        }
    }
}
