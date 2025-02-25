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

import com.ws.beans.Entite;
import com.ws.beans.Tva;

public interface EntiteDao {

	void ajouterEntite(Entite entite) throws DaoException;

	void modifierEntite(Entite entite) throws DaoException;

	void copierEntite(Entite entite) throws DaoException;

	void supprimerEntite(Integer id) throws DaoException;

	void renommerEntite(Entite entite) throws DaoException;

	List<Entite> listerEntite() throws DaoException;

	Entite trouverEntite(Integer id) throws DaoException;

	boolean trouverNomEntite(String nom) throws DaoException;

	int getNoOfRecords();

	Integer getIntegerRecords();

	List<Map<String, Object>> rechercheEntites(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);

	List<Map<String, Object>> rechercheLikeEntites(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_entite);

	List<Entite> listerEntiteSalarie() throws DaoException;

	List<Entite> listerEntiteFournisseur() throws DaoException;

	List<Entite> listerEntiteClient() throws DaoException;

}