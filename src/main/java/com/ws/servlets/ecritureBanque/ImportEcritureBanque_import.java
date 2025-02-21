package com.ws.servlets.ecritureBanque;

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
import com.ws.Dao.ContratDao;
import com.ws.Dao.DaoException;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.EcritureBanque_importDao;
import com.ws.beans.EcritureBanque_detail;
import com.ws.beans.EcritureBanque_import;
import com.ws.configuration.Configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class ImportEcritureBanque
 */
public class ImportEcritureBanque_import extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VUE_FORM = "/WEB-INF/JSP_ecritureBanque/ecritureBanque_import.jsp";

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
        String codeBanque = request.getParameter("codeBanque");
        Part filePart = request.getPart("fichier");
        List<String[]> data = new ArrayList<>();
        List<EcritureBanque_detail> detailsList = new ArrayList<>();
        Date date = null;
        EcritureBanque_import entete = null ;
        EcritureBanque_detail detail = null;
        // Vérifiez si le fichier a été sélectionné
        if (filePart != null && filePart.getSize() > 0) {
            try (InputStream fileContent = filePart.getInputStream();
                 CSVReader reader = new CSVReader(new InputStreamReader(fileContent))) {

                String[] line;
                int ligneNumero =0;
                while ((line = reader.readNext()) != null) {
                    if (line.length < 4) continue; // Ignore les lignes mal formées

                    	ligneNumero = ligneNumero + 1;
                    	System.out.println("numéroligne" +ligneNumero);
                    	String dateString = line[0]; // Chaîne à parser
                    	System.out.println("datestring"+ dateString);
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Format de la date
                        formatter.setLenient(false); // Pour s'assurer que la date est valide

                        try {
                            date = formatter.parse(dateString); // Conversion de String à Date
                            System.out.println("Date : " + date);
                        } catch (ParseException e) {
                            System.out.println("Erreur de parsing : " + e.getMessage());
                        }

                        // Utilisation de Calendar pour extraire l'année et le mois
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);

                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH) + 1; //

                     // Combinaison de l'année et du mois sous la forme AAAAMM
                        int yearMonth = year * 100 + month;



    	                String libelle = line[1];
    	                float debit = Float.parseFloat(line[2]);
                        float credit = Float.parseFloat(line[3]);

                    // Instanciation d'EcritureBanqueDetail
                    detail = new EcritureBanque_detail();
                    detail.setDate_ecriture(date);
                    detail.setLibelle_ecriture(libelle);
                    detail.setDebit(debit);
                    detail.setCredit(credit);
                    detailsList.add(detail);

                }
                if (ligneNumero>0) {
                entete = new EcritureBanque_import();
                entete.setCode_banque("CRAGPRO");
                entete.setNom_import(filePart.getSubmittedFileName());
                entete.setNbr_ligne(ligneNumero);
                }
                // Passer les données à la JSP
                //request.setAttribute("data", data);
                //request.setAttribute("codeBanque", codeBanque);
                //request.getRequestDispatcher(VUE_FORM).forward(request, response);
                //return; // Arrêtez l'exécution après le forward

            } catch (CsvValidationException e) {
                e.printStackTrace();
                // Ajoutez un message d'erreur si besoin
                request.setAttribute("errorMessage", "Erreur de validation du CSV.");
            } catch (Exception e) {
                e.printStackTrace();
                // Gérer les autres exceptions
                request.setAttribute("errorMessage", "Une erreur est survenue lors du traitement du fichier.");
            }
        } else {
            request.setAttribute("errorMessage", "Aucun fichier sélectionné.");
        }

        try {
        	System.out.println("majdao");
			ecritureBanque_importDao.importerEcritureBanque(entete, detailsList);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // En cas d'erreur, retournez à la vue de formulaire
        this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
    }
}
