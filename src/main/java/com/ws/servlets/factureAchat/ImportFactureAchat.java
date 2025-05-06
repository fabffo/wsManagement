package com.ws.servlets.factureAchat;

import java.io.File;
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

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ws.Dao.ContratDaoAncien;
import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.FactureAchatDao;
import com.ws.beans.FactureAchat;
import com.ws.configuration.Configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class ImportFactureAchat
 */
public class ImportFactureAchat extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VUE_FORM = "/WEB-INF/JSP_factureAchat/factureAchat.jsp";

    private DaoFactory daoFactory;
    private FactureAchatDao factureAchatDao;

    @Override
    public void init() throws ServletException {
        daoFactory = DaoFactory.getInstance();
        this.factureAchatDao = daoFactory.getFactureAchatDao();
    }

    // ============ DO GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
    }


    String UPLOAD_DIR = "";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres du formulaire
        String entite = request.getParameter("entite");
        String nomEntite = request.getParameter("nomEntite");
        String montantTTC = request.getParameter("montantTTC");
        String date = request.getParameter("date");

        FactureAchat factureAchat = new FactureAchat();
        factureAchat.setEntite(entite);
        factureAchat.setNom_entite(nomEntite);
        factureAchat.setTtc(Float.parseFloat(montantTTC));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Format de la date
        formatter.setLenient(false); // Pour s'assurer que la date est valide
        try {
        	factureAchat.setDate_facture(formatter.parse(date));    ; // Conversion de String à Date
            System.out.println("Date : " + date);
        } catch (ParseException e) {
            System.out.println("Erreur de parsing : " + e.getMessage());
        }
        try {
			factureAchatDao.importerFactureAchat(factureAchat);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Générer le nom du fichier
        String fileName = entite + "_" + nomEntite + "_" + montantTTC + "_" + date + ".pdf";

        // Récupérer le fichier uploadé
        Part filePart = request.getPart("file");

        // Obtenir le chemin absolu du répertoire de téléchargement
       UPLOAD_DIR="C:\\Users\\fougery\\eclipse-workspace\\wsManagement\\src\\main\\webapp\\Document\\FactureAchat";

        // Sauvegarder le fichier avec le nom généré
        filePart.write(UPLOAD_DIR + File.separator + fileName);

        // Répondre à l'utilisateur avec succès
        response.getWriter().println("Fichier PDF importé avec succès sous le nom : " + fileName);
    }
}
