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

import com.ws.beans.Signataire;

public interface SignataireDao {

	void ajouterSignataire(Signataire signataire) throws DaoException;

	void modifierSignataire(Signataire signataire) throws DaoException;

	void copierSignataire(Signataire signataire) throws DaoException;

	void supprimerSignataire(Integer id) throws DaoException;

	void renommerSignataire(Signataire signataire) throws DaoException;

	List<Signataire> listerSignataire() throws DaoException;

	Signataire trouverSignataire(Integer id) throws DaoException;

	boolean trouverNomSignataire(String nom) throws DaoException;

	int getNoOfRecords();

	Integer getIntegerRecords();
	List<Map<String, Object>> rechercheLikeSignataires(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_entite);

	List<Map<String, Object>> rechercheSignataires(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);

	List<Signataire> listerSignataireClient() throws DaoException;

	List<Signataire> listerSignataireFournisseur() throws DaoException;

	List<Signataire> listerSignataireSalarie() throws DaoException;

}