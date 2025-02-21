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

import com.ws.beans.Metier;

public interface MetierDao {
	    void ajouterMetier( Metier Metier ) throws DaoException;
	    List<Metier> listerMetier() throws DaoException;
	    Metier trouverMetier( Integer id )throws DaoException;
	    void modifierMetier( Metier Metier )throws DaoException;
	    void copierMetier( Metier Metier )throws DaoException;
	    void supprimerMetier( Integer id )throws DaoException;
	    boolean trouverNomMetier (String nom) throws DaoException;
	    public int getNoOfRecords();
		void renommerMetier(Metier metier) throws DaoException;
		Integer getIntegerRecords();
		List<Map<String, Object>> rechercheMetiers(Integer offset, Integer noOfRecords, String select_tri,
				LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);
		List<Map<String, Object>> rechercheLikeMetiers(Integer offset, Integer noOfRecords, String select_tri,
				String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
				String type_entite);;

}