/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO TVA                                                 ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ws.beans.Parametre;
import com.ws.beans.ParametreSysteme;
import com.ws.beans.Parametre_detail;
import com.ws.beans.Parametre_ecranGestion_entete;
import com.ws.beans.Parametre_entete;
import com.ws.beans.Tva;
import com.ws.menu.MenuItem;

public interface ParametreDao {

	List<Parametre_detail> listerDetailParametre(int id_entete) throws DaoException;

	ParametreSysteme lireEnteteParametre(String parametre) throws DaoException;

	List<Map<String, Object>> getParametresChampsEcran(String nomProgramme, String entite) throws SQLException;

	Map<String, Map<String, Object>> getMapParametresChampsEcran(String nomProgramme, String entite)
			throws SQLException;

	List<MenuItem> getMenuItemsFromDatabase();

	int genererEnteteParametre(Connection connexion, String entiteName) throws DaoException, SQLException;

	void genererDetailParametre(Connection connexion, String entiteName, int id_entete,
			List<Map<String, String>> fieldsInfo) throws DaoException, SQLException;

	void genererCrudparametreChampsEcran(Connection connexion, String entiteName, int id_entete,
			List<Map<String, String>> fieldsInfo) throws DaoException, SQLException;

	void genererParametres(String entiteName, List<Map<String, String>> fieldsInfo);

	void genererUnParametre(String entiteName, List<Map<String, String>> fieldsInfo);

	int genererParametreSysteme(Connection connexion, String entiteName) throws DaoException, SQLException;

	void genererParametre_ecranGestion_entete(Connection connexion, String entiteName, int id_systeme,
			List<Map<String, String>> fieldsInfo) throws DaoException, SQLException;

	void genererParametre_ecranGestion_ligne(Connection connexion, String entiteName, int id_systeme,
			List<Map<String, String>> fieldsInfo) throws DaoException, SQLException;

	void genererParametre_ecranCrud_entete(Connection connexion, String entiteName, int id_systeme,
			List<Map<String, String>> fieldsInfo) throws DaoException, SQLException;

	void genererParametre_ecranCrud_ligne(Connection connexion, String entiteName, int id_systeme,
			List<Map<String, String>> fieldsInfo) throws DaoException, SQLException;


}