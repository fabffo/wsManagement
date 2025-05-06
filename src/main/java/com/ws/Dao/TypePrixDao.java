/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO UTILISATEUR                                          ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ws.beans.TypePrix;
import com.ws.beans.TypePrix;

public interface TypePrixDao {
	void ajouterTypePrix(TypePrix typePrix) throws DaoException;

	void modifierTypePrix(TypePrix typePrix) throws DaoException;

	void copierTypePrix(TypePrix typePrix) throws DaoException;

	void supprimerTypePrix(Integer id) throws DaoException;

	void renommerTypePrix(TypePrix typePrix) throws DaoException;

	List<TypePrix> listerTypePrix() throws DaoException;

	TypePrix trouverTypePrix(Integer id) throws DaoException;

	boolean trouverNomTypePrix(String nom) throws DaoException;

	int getNoOfRecords();

	Integer getIntegerRecords();
	List<Map<String, Object>> rechercheTypePrixs(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_typePrix, String type_entite);

	List<Map<String, Object>> rechercheLikeTypePrixs(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_typePrix,
			String type_entite);

	List<TypePrix> listerTypePrixClient() throws DaoException;

	List<TypePrix> listerTypePrixFournisseur() throws DaoException;

	List<TypePrix> listerTypePrixSalarie() throws DaoException;
}