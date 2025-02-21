/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO TVA                                                 ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ws.beans.Action;
import com.ws.beans.Tva;

public interface ActionDao {

	void ajouterAction(Action action) throws DaoException;

	void modifierAction(Action action) throws DaoException;

	void copierAction(Action action) throws DaoException;

	void supprimerAction(Integer id) throws DaoException;

	List<Action> listerAction() throws DaoException;

	Action trouverAction(Integer id) throws DaoException;

	boolean trouverNomAction(String nom) throws DaoException;

	int getNoOfRecords();

	Integer getIntegerRecords();
	List<Map<String, Object>> rechercheLikeActions(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_entite);

	List<Map<String, Object>> rechercheActions(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);

	Map<String, String> getActionsDisponibles(String parametreNomProgramme) throws SQLException;

}