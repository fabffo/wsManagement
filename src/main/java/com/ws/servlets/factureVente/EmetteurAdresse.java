package com.ws.servlets.factureVente;

import java.io.IOException;
import java.io.PrintWriter;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.MaSocieteDao;
import com.ws.beans.MaSociete;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/emetteurAdresse")
public class EmetteurAdresse extends HttpServlet {
	public DaoFactory daoFactory;
	private MaSocieteDao maSocieteDao;

	public void init() throws ServletException {
		daoFactory = DaoFactory.getInstance();
		this.maSocieteDao = daoFactory.getMaSocieteDao();
	}
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {



        int id = Integer.parseInt(req.getParameter("id"));

        // Appel DAO pour récupérer l’adresse du client par id
        MaSociete maSociete = maSocieteDao.trouverMasocieteParId(id);

        // Envoi JSON
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print("{");
        out.printf("\"raison_sociale_emetteur\":\"%s\",", maSociete.getRaison_sociale());
        out.printf("\"adresse1\":\"%s\",", maSociete.getAdresse());
        out.printf("\"adresse2\":\"%s\",", maSociete.getCode_postal()+ maSociete.getVille() + maSociete.getPays());
        out.printf("\"adresse3\":\"%s\"", maSociete.getEmail());
        out.print("}");
        System.out.println("EMETTEUR");
        out.flush();
    }
}
