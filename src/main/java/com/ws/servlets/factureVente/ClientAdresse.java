package com.ws.servlets.factureVente;

import java.io.IOException;
import java.io.PrintWriter;

import com.ws.Dao.ClientDao;
import com.ws.Dao.DaoFactory;
import com.ws.Dao.MissionDao;
import com.ws.beans.Client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/clientAdresse")
public class ClientAdresse extends HttpServlet {
	public DaoFactory daoFactory;
	private ClientDao clientDao;

	public void init() throws ServletException {
		daoFactory = DaoFactory.getInstance();
		this.clientDao = daoFactory.getClientDao();
	}
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {



        int id = Integer.parseInt(req.getParameter("id"));

        // Appel DAO pour récupérer l’adresse du client par id
        Client client = clientDao.trouverClientParId(id);

        // Envoi JSON
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print("{");
        out.printf("\"raison_sociale_client\":\"%s\",", client.getRaison_sociale());
        out.printf("\"adresse1\":\"%s\",", client.getAdresse());
        out.printf("\"adresse2\":\"%s\",", client.getCode_postal()+ client.getVille() + client.getPays());
        out.printf("\"adresse3\":\"%s\"", client.getEmail());
        out.print("}");
        System.out.println("CLIENT");
        out.flush();
    }
}
