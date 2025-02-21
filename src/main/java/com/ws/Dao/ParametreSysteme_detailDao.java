/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO TVA                                                 ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.List;
import java.util.Map;

import com.ws.beans.ParametreSysteme_detail;

public interface ParametreSysteme_detailDao {
	    void ajouterParametreSysteme_detail( ParametreSysteme_detail parametreSysteme_detail ) throws DaoException;
	    List<ParametreSysteme_detail> listerParametreSysteme_detail() throws DaoException;
	    ParametreSysteme_detail trouverParametreSysteme_detail( Integer id )throws DaoException;
	    void modifierParametreSysteme_detail( ParametreSysteme_detail parametreSysteme_detail )throws DaoException;
	    void copierParametreSysteme_detail( ParametreSysteme_detail parametreSysteme_detail )throws DaoException;
	    void supprimerParametreSysteme_detail( Integer id )throws DaoException;
	    boolean trouverNomParametreSysteme_detail (String nom) throws DaoException;
	    public int getNoOfRecords();
	    public String getStringRecords();
	    public Integer getIntegerRecords();
	    void renommerParametreSysteme_detail(ParametreSysteme_detail parametreSysteme_detail) throws DaoException;
		List<Map<String, Object>> rechercheParametreSysteme_details(Integer offset, Integer noOfRecords,
				String select_tri, Map<String, String> dictionnaire_nom_colonne);
		List<Map<String, Object>> rechercheLikeParametreSysteme_details(Integer offset, Integer noOfRecords,
				String select_tri, String select_like, Map<String, String> dictionnaire_nom_colonne);
}