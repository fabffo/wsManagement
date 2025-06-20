package com.ws.servlets.ecritureBanque;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.log.SysoCounter;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.ws.Dao.ContratDaoAncien;
import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.EcritureBanque_importDao;
import com.ws.beans.EcritureBanque_detail;
import com.ws.beans.EcritureBanque_import;
import com.ws.configuration.Configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class ImportEcritureBanque
 */
@MultipartConfig
public class ImportEcritureBanque_import extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VUE_FORM = "/WEB-INF/JSP_ecritureBanque/GestionEcritureBanque_import.jsp";


    private DaoFactory daoFactory;
    private EcritureBanque_importDao ecritureBanque_importDao;

    @Override
    public void init() throws ServletException {
        daoFactory = DaoFactory.getInstance();
        this.ecritureBanque_importDao = daoFactory.getEcritureBanque_importDao();
    }

    // ============ DO GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
    }

    // ============ DO POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("fichier");
        List<EcritureBanque_detail> detailsList = new ArrayList<>();
        EcritureBanque_import entete = null;
        int ligneNumero = 0;

        if (filePart != null && filePart.getSize() > 0) {
            System.out.println("debut import");

            try (
                InputStream fileContent = filePart.getInputStream();
                BufferedReader rawReader = new BufferedReader(new InputStreamReader(fileContent, "UTF-8"));
            ) {
                // Lire tout le contenu brut
                StringBuilder rawCsv = new StringBuilder();
                String line;
                while ((line = rawReader.readLine()) != null) {
                    rawCsv.append(line).append("\n"); // force LF pour unifier
                }

                // Nettoyage CRLF: garde uniquement \r\n, retire LF/CR orphelins
                System.out.println(rawCsv.toString());
                String cleanedCsv = rawCsv.toString().replace("\n", "")  ;            // supprimer LF orphelins

                System.out.println(cleanedCsv.toString());
                // Lecture CSV avec ; comme séparateur
                try (
                    InputStream cleanedStream = new ByteArrayInputStream(cleanedCsv.getBytes("UTF-8"));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(cleanedStream));
                    CSVReader csvReader = new CSVReaderBuilder(reader)
                        .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                        .build()
                ) {
                    String[] row;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    formatter.setLenient(false);

                    while ((row = csvReader.readNext()) != null) {
                        System.out.println("Nb colonnes = " + row.length);
                        if (row.length < 4) continue;

                        String dateStr = row[0].trim();
                        if (!dateStr.matches("\\d{2}/\\d{2}/\\d{4}")) continue;

                        Date date;
                        try {
                            date = formatter.parse(dateStr);
                        } catch (ParseException e) {
                            continue;
                        }

                        StringBuilder libelleBuilder = new StringBuilder();
                        for (int i = 1; i < row.length - 2; i++) {
                            libelleBuilder.append(row[i].trim()).append(" ");
                        }

                        String libelle = libelleBuilder.toString().replaceAll("\\s{2,}", " ").trim();
                        String debitStr = row[row.length - 2].trim().replace(",", ".").replaceAll("[^0-9.]", "");
                        String creditStr = row[row.length - 1].trim().replace(",", ".").replaceAll("[^0-9.]", "");

                        float debit = debitStr.isEmpty() ? 0f : Float.parseFloat(debitStr);
                        float credit = creditStr.isEmpty() ? 0f : Float.parseFloat(creditStr);

                        EcritureBanque_detail detail = new EcritureBanque_detail();
                        detail.setDate_ecriture(date);
                        detail.setLibelle_ecriture(libelle);
                        detail.setDebit(debit);
                        detail.setCredit(credit);
                        detailsList.add(detail);
                        ligneNumero++;
                    }

                    if (ligneNumero > 0) {
                        entete = new EcritureBanque_import();
                        entete.setCode_banque("CRAGPRO");
                        entete.setNom_import(filePart.getSubmittedFileName());
                        entete.setNbr_ligne(ligneNumero);
                        ecritureBanque_importDao.importerEcritureBanque(entete, detailsList);
                        request.setAttribute("message", ligneNumero + " écritures importées.");
                    }

                } catch (CsvValidationException e) {
                    request.setAttribute("errorMessage", "Erreur de lecture CSV.");
                }

            } catch (Exception e) {
                request.setAttribute("errorMessage", "Erreur lors du traitement du fichier.");
            }

        } else {
            request.setAttribute("errorMessage", "Aucun fichier sélectionné.");
        }



        request.getRequestDispatcher(VUE_FORM).forward(request, response);
    }
}
