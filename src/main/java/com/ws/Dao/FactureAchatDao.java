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

import com.ws.beans.FactureAchat;
import com.ws.beans.FactureAchat;

public interface FactureAchatDao {
	void ajouterFactureAchat(FactureAchat factureAchat) throws DaoException;

	void modifierFactureAchat(FactureAchat factureAchat) throws DaoException;

	void copierFactureAchat(FactureAchat factureAchat) throws DaoException;

	void supprimerFactureAchat(Integer id) throws DaoException;

	void renommerFactureAchat(FactureAchat factureAchat) throws DaoException;

	List<FactureAchat> listerFactureAchat() throws DaoException;

	FactureAchat trouverFactureAchat(Integer id) throws DaoException;

	boolean trouverNomFactureAchat(String nom) throws DaoException;

	int getNoOfRecords();

	Integer getIntegerRecords();

	List<Map<String, Object>> rechercheFactureAchats(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_factureAchat);

	List<Map<String, Object>> rechercheLikeFactureAchats(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_factureAchat);

	List<FactureAchat> listerFactureAchatSalarie() throws DaoException;

	List<FactureAchat> listerFactureAchatFournisseur() throws DaoException;

	List<FactureAchat> listerFactureAchatClient() throws DaoException;

	   }