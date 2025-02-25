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

import com.ws.beans.Tva;

public interface TvaDao {
	    void ajouterTva( Tva tva ) throws DaoException;
	    List<Tva> listerTva() throws DaoException;
	    Tva trouverTva( Integer id )throws DaoException;
	    void modifierTva( Tva tva )throws DaoException;
	    void copierTva( Tva tva )throws DaoException;
	    void supprimerTva( Integer id )throws DaoException;
	    boolean trouverNomTva (String nom) throws DaoException;
	    public int getNoOfRecords();
	    public String getStringRecords();
	    public Integer getIntegerRecords();
	    void renommerTva(Tva tva) throws DaoException;
	    List<Map<String, Object>> rechercheTvas(Integer offset, Integer noOfRecords, String select_tri,
				LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);
		List<Map<String, Object>> rechercheLikeTvas(Integer offset, Integer noOfRecords, String select_tri,
				String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
				String type_entite);
		List<Tva> listerTvaSalarie() throws DaoException;
		List<Tva> listerTvaFournisseur() throws DaoException;
		List<Tva> listerTvaClient() throws DaoException;
}