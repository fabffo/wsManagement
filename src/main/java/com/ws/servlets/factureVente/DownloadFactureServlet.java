package com.ws.servlets.factureVente;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/downloadFacture")
public class DownloadFactureServlet extends HttpServlet {
    private static final String BASE_PATH = "C:/factures-wavy/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID manquant");
            return;
        }

        File file = new File(BASE_PATH + "facture-" + id + ".pdf");
        if (!file.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Facture non trouvée");
            return;
        }

        resp.setContentType("application/pdf");
        resp.setHeader("Content-Disposition", "inline; filename=" + file.getName());
        try (FileInputStream fis = new FileInputStream(file);
             OutputStream os = resp.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
    }
}
