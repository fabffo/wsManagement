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

import com.ws.beans.Nationalite;

public interface NationaliteDao {

	void ajouterNationalite(Nationalite nationalite) throws DaoException;

	void modifierNationalite(Nationalite nationalite) throws DaoException;

	void copierNationalite(Nationalite nationalite) throws DaoException;

	void supprimerNationalite(Integer id) throws DaoException;

	void renommerNationalite(Nationalite nationalite) throws DaoException;

	List<Nationalite> listerNationalite() throws DaoException;

	Nationalite trouverNationalite(Integer id) throws DaoException;

	boolean trouverNomNationalite(String nom) throws DaoException;

	int getNoOfRecords();

	Integer getIntegerRecords();
	List<Map<String, Object>> rechercheLikeNationalites(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_entite);

	List<Map<String, Object>> rechercheNationalites(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);

	List<Nationalite> listerNationaliteSalarie() throws DaoException;

	List<Nationalite> listerNationaliteFournisseur() throws DaoException;

	List<Nationalite> listerNationaliteClient() throws DaoException;

}