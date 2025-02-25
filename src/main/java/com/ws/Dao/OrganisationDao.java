/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO METIER                                              ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ws.beans.Organisation;

public interface OrganisationDao {
	    void ajouterOrganisation( Organisation Organisation ) throws DaoException;
	    List<Organisation> listerOrganisation() throws DaoException;
	    Organisation trouverOrganisation( Integer id )throws DaoException;
	    void modifierOrganisation( Organisation Organisation )throws DaoException;
	    void copierOrganisation( Organisation Organisation )throws DaoException;
	    void supprimerOrganisation( Integer id )throws DaoException;
	    boolean trouverNomOrganisation (String nom) throws DaoException;
	    public int getNoOfRecords();
		void renommerOrganisation(Organisation organisation) throws DaoException;
		Integer getIntegerRecords();
		List<Map<String, Object>> rechercheOrganisations(Integer offset, Integer noOfRecords, String select_tri,
				LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);
		List<Map<String, Object>> rechercheLikeOrganisations(Integer offset, Integer noOfRecords, String select_tri,
				String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
				String type_entite);
		List<Organisation> listerOrganisationClient() throws DaoException;
		List<Organisation> listerOrganisationFournisseur() throws DaoException;
		List<Organisation> listerOrganisationSalarie() throws DaoException;;

}