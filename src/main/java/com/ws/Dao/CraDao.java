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

import com.ws.beans.Cra;
import com.ws.beans.Tva;

public interface CraDao {

	void ajouterCra(Cra cra) throws DaoException;

	void modifierCra(Cra cra) throws DaoException;

	void copierCra(Cra cra) throws DaoException;

	void supprimerCra(Integer id) throws DaoException;

	void renommerCra(Cra cra) throws DaoException;

	List<Cra> listerCra() throws DaoException;

	Cra trouverCra(Integer id) throws DaoException;

	boolean trouverNomCra(String nom) throws DaoException;

	int getNoOfRecords();

	Integer getIntegerRecords();
	List<Map<String, Object>> rechercheLikeCras(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_entite);

	List<Map<String, Object>> rechercheCras(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);

	void terminerCra(Integer id_cra) throws DaoException;

	void annulerCra(Integer id_cra) throws DaoException;

}