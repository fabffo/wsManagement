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

import com.ws.beans.Cra_detail;
import com.ws.beans.Tva;

public interface Cra_detailDao {

	void ajouterCra_detail(Cra_detail cra_detail) throws DaoException;

	void modifierCra_detail(Cra_detail cra_detail) throws DaoException;

	void copierCra_detail(Cra_detail cra_detail) throws DaoException;

	void supprimerCra_detail(Integer id) throws DaoException;

	void renommerCra_detail(Cra_detail cra_detail) throws DaoException;

	List<Cra_detail> listerCra_detail() throws DaoException;

	Cra_detail trouverCra_detail(Integer id) throws DaoException;

	boolean trouverNomCra_detail(String nom) throws DaoException;

	int getNoOfRecords();

	Integer getIntegerRecords();
	List<Map<String, Object>> rechercheLikeCra_details(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_entite);

	List<Map<String, Object>> rechercheCra_details(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);

	void terminerCra_detail(Integer id_cra_detail) throws DaoException;

	void annulerCra_detail(Integer id_cra_detail) throws DaoException;

}