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

import com.ws.beans.ParametreSysteme;

public interface ParametreSystemeDao {
	    void ajouterParametreSysteme( ParametreSysteme parametreSysteme ) throws DaoException;
	    List<ParametreSysteme> listerParametreSysteme() throws DaoException;
	    ParametreSysteme trouverParametreSysteme( Integer id )throws DaoException;
	    void modifierParametreSysteme( ParametreSysteme parametreSysteme )throws DaoException;
	    void copierParametreSysteme( ParametreSysteme parametreSysteme )throws DaoException;
	    void supprimerParametreSysteme( Integer id )throws DaoException;
	    boolean trouverNomParametreSysteme (String nom) throws DaoException;
	    public int getNoOfRecords();
	    public String getStringRecords();
	    public Integer getIntegerRecords();
	    void renommerParametreSysteme(ParametreSysteme parametreSysteme) throws DaoException;
		List<Map<String, Object>> rechercheParametreSystemes(Integer offset, Integer noOfRecords, String select_tri,
				LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);
		List<Map<String, Object>> rechercheLikeParametreSystemes(Integer offset, Integer noOfRecords, String select_tri,
				String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
				String type_entite);
}