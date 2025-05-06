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

import com.ws.beans.TypeIntervenant;
import com.ws.beans.Tva;

public interface TypeIntervenantDao {

	void ajouterTypeIntervenant(TypeIntervenant typeIntervenant) throws DaoException;

	void modifierTypeIntervenant(TypeIntervenant typeIntervenant) throws DaoException;

	void copierTypeIntervenant(TypeIntervenant typeIntervenant) throws DaoException;

	void supprimerTypeIntervenant(Integer id) throws DaoException;

	void renommerTypeIntervenant(TypeIntervenant typeIntervenant) throws DaoException;

	List<TypeIntervenant> listerTypeIntervenant() throws DaoException;

	TypeIntervenant trouverTypeIntervenant(Integer id) throws DaoException;

	boolean trouverNomTypeIntervenant(String nom) throws DaoException;

	int getNoOfRecords();

	Integer getIntegerRecords();
	List<Map<String, Object>> rechercheLikeTypeIntervenants(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_entite);

	List<Map<String, Object>> rechercheTypeIntervenants(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);

	List<TypeIntervenant> listerTypeIntervenantSalarie() throws DaoException;

	List<TypeIntervenant> listerTypeIntervenantFournisseur() throws DaoException;

	List<TypeIntervenant> listerTypeIntervenantClient() throws DaoException;

	List<TypeIntervenant> listerTypeIntervenantInterne() throws DaoException;

}