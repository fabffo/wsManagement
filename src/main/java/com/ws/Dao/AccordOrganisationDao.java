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

import com.ws.beans.AccordOrganisation;
import com.ws.beans.Tva;

public interface AccordOrganisationDao {

	void ajouterAccordOrganisation(AccordOrganisation accordOrganisation) throws DaoException;

	void modifierAccordOrganisation(AccordOrganisation accordOrganisation) throws DaoException;

	void copierAccordOrganisation(AccordOrganisation accordOrganisation) throws DaoException;

	void supprimerAccordOrganisation(Integer id) throws DaoException;

	void renommerAccordOrganisation(AccordOrganisation accordOrganisation) throws DaoException;

	List<AccordOrganisation> listerAccordOrganisation() throws DaoException;

	AccordOrganisation trouverAccordOrganisation(Integer id) throws DaoException;

	boolean trouverNomAccordOrganisation(String nom) throws DaoException;

	int getNoOfRecords();

	Integer getIntegerRecords();
	List<Map<String, Object>> rechercheLikeAccordOrganisations(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_entite);

	List<Map<String, Object>> rechercheAccordOrganisations(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);

}