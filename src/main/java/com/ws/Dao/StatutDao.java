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

import com.ws.beans.Statut;
import com.ws.beans.Tva;

public interface StatutDao {

	void ajouterStatut(Statut statut) throws DaoException;

	void modifierStatut(Statut statut) throws DaoException;

	void copierStatut(Statut statut) throws DaoException;

	void supprimerStatut(Integer id) throws DaoException;

	void renommerStatut(Statut statut) throws DaoException;

	List<Statut> listerStatut() throws DaoException;

	Statut trouverStatut(Integer id) throws DaoException;

	boolean trouverNomStatut(String nom) throws DaoException;

	int getNoOfRecords();

	Integer getIntegerRecords();
	List<Map<String, Object>> rechercheStatuts(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);

	List<Map<String, Object>> rechercheLikeStatuts(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_entite);

	List<Statut> listerStatutClient() throws DaoException;

	List<Statut> listerStatutFournisseur() throws DaoException;

	List<Statut> listerStatutSalarie() throws DaoException;

}