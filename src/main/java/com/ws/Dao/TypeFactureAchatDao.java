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

import com.ws.beans.TypeFactureAchat;
import com.ws.beans.Tva;

public interface TypeFactureAchatDao {

	void ajouterTypeFactureAchat(TypeFactureAchat typeContrat) throws DaoException;

	void modifierTypeFactureAchat(TypeFactureAchat typeContrat) throws DaoException;

	void copierTypeFactureAchat(TypeFactureAchat typeContrat) throws DaoException;

	void supprimerTypeFactureAchat(Integer id) throws DaoException;

	void renommerTypeFactureAchat(TypeFactureAchat typeContrat) throws DaoException;

	List<TypeFactureAchat> listerTypeFactureAchat() throws DaoException;

	TypeFactureAchat trouverTypeFactureAchat(Integer id) throws DaoException;

	boolean trouverNomTypeFactureAchat(String nom) throws DaoException;

	int getNoOfRecords();

	Integer getIntegerRecords();

	List<Map<String, Object>> rechercheTypeFactureAchats(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);

	List<Map<String, Object>> rechercheLikeTypeFactureAchats(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_entite);

	List<TypeFactureAchat> listerTypeFactureAchatClient() throws DaoException;

	List<TypeFactureAchat> listerTypeFactureAchatFournisseur() throws DaoException;

	List<TypeFactureAchat> listerTypeFactureAchatSalarie() throws DaoException;

	List<TypeFactureAchat> listerTypeFactureAchatInterne() throws DaoException;

}