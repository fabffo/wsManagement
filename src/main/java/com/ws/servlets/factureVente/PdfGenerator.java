package com.ws.servlets.factureVente;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.ws.beans.FactureVente;
import com.ws.beans.FactureVente_detail;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfGenerator {

    public static byte[] genererPdfFacture(FactureVente facture, List<FactureVente_detail> lignes) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4, 36, 36, 54, 36);
        PdfWriter.getInstance(document, baos);
        document.open();

        Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        Font normal = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

        document.add(new Paragraph("Facture Vente - Wavy Services", titleFont));
        document.add(new Paragraph("Numéro : " + facture.getNom(), normal));
        document.add(new Paragraph("Date émission : " + facture.getDate_facture(), normal));
        document.add(new Paragraph("Date échéance : " + facture.getDate_echeance(), normal));
        document.add(Chunk.NEWLINE);

        PdfPTable tableInfos = new PdfPTable(2);
        tableInfos.setWidthPercentage(100);
        tableInfos.setSpacingBefore(10);
        tableInfos.addCell(getCell("Émetteur :", PdfPCell.ALIGN_LEFT, true));
        tableInfos.addCell(getCell("Client :", PdfPCell.ALIGN_LEFT, true));

        tableInfos.addCell(getCell(facture.getRaison_sociale_emetteur() + "\n" +
                facture.getAdresse1_emetteur() + "\n" +
                facture.getAdresse2_emetteur() + "\n" +
                facture.getAdresse3_emetteur(), PdfPCell.ALIGN_LEFT, false));
        tableInfos.addCell(getCell(facture.getRaison_sociale_client() + "\n" +
                facture.getAdresse1_client() + "\n" +
                facture.getAdresse2_client() + "\n" +
                facture.getAdresse3_client(), PdfPCell.ALIGN_LEFT, false));

        document.add(tableInfos);
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);
        table.setWidths(new float[]{3, 1, 2, 1, 2, 2});

        String[] headers = {"Produit", "Qté", "Prix HT", "TVA", "Montant TVA", "Total TTC"};
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
        }

        for (FactureVente_detail d : lignes) {
            table.addCell(String.valueOf(d.getProduit()));
            table.addCell(String.valueOf(d.getNumero_ligne())); // you can use quantite if exists
            table.addCell(String.format("%.2f", d.getMontant_ht()));
            table.addCell(String.valueOf(d.getTaux_tva()));
            table.addCell(String.format("%.2f", d.getMontant_tva()));
            table.addCell(String.format("%.2f", d.getMontant_ttc()));
        }

        document.add(table);
        document.add(Chunk.NEWLINE);

        PdfPTable totaux = new PdfPTable(2);
        totaux.setWidthPercentage(50);
        totaux.setHorizontalAlignment(Element.ALIGN_RIGHT);

        totaux.addCell("Total HT");
        totaux.addCell(String.format("%.2f", facture.getMontant_ht()));

        totaux.addCell("Total TVA");
        totaux.addCell(String.format("%.2f", facture.getMontant_tva()));

        totaux.addCell("Total TTC");
        totaux.addCell(String.format("%.2f", facture.getMontant_ttc()));

        document.add(totaux);
        document.add(Chunk.NEWLINE);

        document.add(new Paragraph("Conditions de paiement : " + facture.getConditions_paiement_emetteur(), normal));
        document.add(new Paragraph("RIB : " + facture.getRib_emetteur(), normal));
        document.add(new Paragraph("SIREN : " + facture.getSiren_emetteur(), normal));
        document.add(new Paragraph("TVA intracom : " + facture.getTva_intracomm_emetteur(), normal));

        document.close();
        return baos.toByteArray();
    }

    private static PdfPCell getCell(String text, int alignment, boolean bold) {
        Font font = bold ? new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD) : new Font(Font.FontFamily.HELVETICA, 12);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    public static byte[] genererPdfDepuisHtml(String htmlContent) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, baos);
        document.open();

        // Affiche le contenu HTML brut dans le PDF
        document.add(new Paragraph(htmlContent));

        document.close();
        return baos.toByteArray();
    }
}