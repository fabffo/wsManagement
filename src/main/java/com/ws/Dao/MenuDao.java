/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO TVA                                                 ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ws.beans.Menu;

public interface MenuDao {
	    void ajouterMenu( Menu menu ) throws DaoException;
	    List<Menu> listerMenu() throws DaoException;
	    Menu trouverMenu( Integer id )throws DaoException;
	    void modifierMenu( Menu menu )throws DaoException;
	    void copierMenu( Menu menu )throws DaoException;
	    void supprimerMenu( Integer id )throws DaoException;
	    boolean trouverNomMenu (String nom) throws DaoException;
	    public int getNoOfRecords();
	    public String getStringRecords();
	    public Integer getIntegerRecords();
	    void renommerMenu(Menu menu) throws DaoException;
		List<Map<String, Object>> rechercheMenus(Integer offset, Integer noOfRecords, String select_tri,
				LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);
		List<Map<String, Object>> rechercheLikeMenus(Integer offset, Integer noOfRecords, String select_tri,
				String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
				String type_entite);
}