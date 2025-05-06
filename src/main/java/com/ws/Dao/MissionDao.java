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

import com.ws.beans.Mission;
import com.ws.beans.Tva;

public interface MissionDao {

	void ajouterMission(Mission mission) throws DaoException;

	void modifierMission(Mission mission) throws DaoException;

	void copierMission(Mission mission) throws DaoException;

	void supprimerMission(Integer id) throws DaoException;

	void renommerMission(Mission mission) throws DaoException;

	List<Mission> listerMission() throws DaoException;

	Mission trouverMission(Integer id) throws DaoException;

	boolean trouverNomMission(String nom) throws DaoException;

	int getNoOfRecords();

	Integer getIntegerRecords();
	List<Map<String, Object>> rechercheLikeMissions(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_entite);

	List<Map<String, Object>> rechercheMissions(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);

	void terminerMission(Integer id_mission) throws DaoException;

	void annulerMission(Integer id_mission) throws DaoException;

}