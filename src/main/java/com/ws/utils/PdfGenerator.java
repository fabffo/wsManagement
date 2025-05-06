package com.ws.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.ws.beans.FactureVente;
import com.ws.beans.FactureVente_detail;

public class PdfGenerator {
    public static byte[] genererPdfFacture(FactureVente fv, List<FactureVente_detail> lignes) throws Exception {
        Document doc = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(doc, out);
        doc.open();

        doc.add(new Paragraph("Facture : " + fv.getNom()));
        doc.add(new Paragraph("Client : " + fv.getRaison_sociale_client()));
        doc.add(new Paragraph("Date : " + fv.getDate_facture() + " | Échéance : " + fv.getDate_echeance()));
        doc.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(4);
        table.addCell("Produit");
        table.addCell("HT");
        table.addCell("TVA");
        table.addCell("TTC");

        for (FactureVente_detail l : lignes) {
            table.addCell(l.getProduit());
            table.addCell(String.format("%.2f", l.getMontant_ht()));
            table.addCell(String.format("%.2f", l.getMontant_tva()));
            table.addCell(String.format("%.2f", l.getMontant_ttc()));
        }

        doc.add(table);
        doc.close();

        return out.toByteArray();
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

    public static void genererPdfFacture(File pdfFile, String htmlContent, String basePath) throws Exception {
        try (OutputStream os = new FileOutputStream(pdfFile)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.toStream(os);
            builder.withHtmlContent(htmlContent, "file:///" + basePath.replace("\\", "/") + "/");
            builder.run();
        }
    }

}
