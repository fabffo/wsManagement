/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME SERVLET TVA GESTION                                     ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.servlets.genererParametrage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.ParametreDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GestiongenererParametrage extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public DaoFactory daoFactory;
	private static ParametreDao parametreDao;
	public static final  String VUE_SUCCES="/genererParametrage.jsp";
	public static final  String VUE_FORM ="/genererParametrage.jsp";

	public void init() throws ServletException {
		daoFactory = DaoFactory.getInstance();
		this.parametreDao = daoFactory.getParametreDao();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 String entiteName = request.getParameter("genererParametre"); // Nom de l'entité
	        try {
				generateTableData(entiteName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response);
	}

	public static void generateTableData(String entiteName) throws Exception {
		String fieldType;
		String fieldName = null;
		// Charger dynamiquement la classe de l'entité
		Class<?> clazz = Class.forName("com.ws.beans." + entiteName);
		java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
		 // Liste pour stocker les noms et types des champs
        List<Map<String, String>> fieldsInfo = new ArrayList<>();

		for (java.lang.reflect.Field field : fields) {
			 fieldType = mapJavaTypeToSqlType(field.getType().getSimpleName());
			 fieldName = field.getName();
			System.out.println("nom "+ fieldName + " type "+ fieldType);
			// Ajouter les informations dans la liste
            fieldsInfo.add(Map.of("fieldName", fieldName, "fieldType", fieldType));
		}


		parametreDao.genererUnParametre(entiteName, fieldsInfo);


	}

	private static String mapJavaTypeToSqlType(String javaType) {
		return switch (javaType.toLowerCase()) {
		case "string" -> "text";
		case "double", "float" -> "number";
		case "int", "integer" -> "int";
		default -> "text";
		};
    }

}
