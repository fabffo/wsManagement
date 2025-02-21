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
import com.ws.beans.Parametre_ecranCrud_entete;
import com.ws.beans.Parametre_ecranCrud_ligne;
import com.ws.beans.Parametre_ecranGestion_entete;
import com.ws.beans.Parametre_ecranGestion_ligne;
import com.ws.beans.Parametre_entete;
import com.ws.beans.Tva;
import com.ws.menu.MenuItem;

public interface ParametreEcranDao {

	ParametreSysteme lireParametreSysteme(String parametre) throws DaoException;

	Parametre_ecranGestion_entete lireParametre_ecranGestion_entete(int id_parametreSysteme);

	List<Map<String, Object>> lireParametre_ecranGestion_ligne(int id_parametreSysteme) throws SQLException;

	List<Parametre_ecranGestion_ligne> listerParametre_ecranGestion_ligne(int id_parametreSysteme) throws DaoException;

	Parametre_ecranCrud_entete lireParametre_ecranCrud_entete(int id_parametreSysteme, String parametre_nom_programme);

	List<Map<String, Object>> lireParametre_ecranCrud_ligne(int id_parametreSysteme, String nom_programme)
			throws SQLException;

	Map<String, Map<String, Object>> getParametre_ecranCrud_ligne(int id_parametreSysteme,
			String parametre_nom_programme) throws SQLException;

	List<List<Map<String, Object>>> lireParametre_ecranCrud_multiligne(int id_parametreSysteme,
			String parametre_nom_programme, Object classe, String entite) throws SQLException, Exception;


}