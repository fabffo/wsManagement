package com.ws.menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.ParametreDao;


public class MenuServlet extends HttpServlet {

	public DaoFactory daoFactory;
	private ParametreDao parametreDao;

	public void init() throws ServletException {
		daoFactory = DaoFactory.getInstance();
		this.parametreDao = daoFactory.getParametreDao();
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



    	List<MenuItem> menuItems = parametreDao.getMenuItemsFromDatabase();

        // Passer la liste des menus à la JSP
        request.setAttribute("menuItems", menuItems);

        // Redirection vers la page JSP pour afficher le menu
        request.getRequestDispatcher("/WEB-INF/menu.jsp").forward(request, response);
    }
}
