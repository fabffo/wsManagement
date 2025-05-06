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

import com.ws.beans.Activite;
import com.ws.beans.Tva;

public interface ActiviteDao {

	void ajouterActivite(Activite activite) throws DaoException;

	void modifierActivite(Activite activite) throws DaoException;

	void copierActivite(Activite activite) throws DaoException;

	void supprimerActivite(Integer id) throws DaoException;

	void renommerActivite(Activite activite) throws DaoException;

	List<Activite> listerActivite() throws DaoException;

	Activite trouverActivite(Integer id) throws DaoException;

	boolean trouverNomActivite(String nom) throws DaoException;

	int getNoOfRecords();

	Integer getIntegerRecords();
	List<Map<String, Object>> rechercheLikeActivites(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_entite);

	List<Map<String, Object>> rechercheActivites(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);

	List<Activite> listerActiviteClient() throws DaoException;

	List<Activite> listerActiviteFournisseur() throws DaoException;

	List<Activite> listerActiviteSalarie() throws DaoException;

	List<Activite> listerActiviteInterne() throws DaoException;

}