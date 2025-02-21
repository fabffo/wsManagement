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

import com.ws.beans.TypeContrat;
import com.ws.beans.Tva;

public interface TypeContratDao {

	void ajouterTypeContrat(TypeContrat typeContrat) throws DaoException;

	void modifierTypeContrat(TypeContrat typeContrat) throws DaoException;

	void copierTypeContrat(TypeContrat typeContrat) throws DaoException;

	void supprimerTypeContrat(Integer id) throws DaoException;

	void renommerTypeContrat(TypeContrat typeContrat) throws DaoException;

	List<TypeContrat> listerTypeContrat() throws DaoException;

	TypeContrat trouverTypeContrat(Integer id) throws DaoException;

	boolean trouverNomTypeContrat(String nom) throws DaoException;

	int getNoOfRecords();

	Integer getIntegerRecords();

	List<Map<String, Object>> rechercheTypeContrats(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);

	List<Map<String, Object>> rechercheLikeTypeContrats(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_entite);

}