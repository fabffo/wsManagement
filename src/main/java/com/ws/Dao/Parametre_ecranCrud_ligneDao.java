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

import com.ws.beans.Parametre_ecranCrud_ligne;

public interface Parametre_ecranCrud_ligneDao {
	    void ajouterParametre_ecranCrud_ligne( Parametre_ecranCrud_ligne parametre_ecranCrud_ligne ) throws DaoException;
	    List<Parametre_ecranCrud_ligne> listerParametre_ecranCrud_ligne() throws DaoException;
	    Parametre_ecranCrud_ligne trouverParametre_ecranCrud_ligne( Integer id )throws DaoException;
	    void modifierParametre_ecranCrud_ligne( Parametre_ecranCrud_ligne parametre_ecranCrud_ligne )throws DaoException;
	    void copierParametre_ecranCrud_ligne( Parametre_ecranCrud_ligne parametre_ecranCrud_ligne )throws DaoException;
	    void supprimerParametre_ecranCrud_ligne( Integer id )throws DaoException;
	    boolean trouverNomParametre_ecranCrud_ligne (String nom) throws DaoException;
	    public int getNoOfRecords();
	    public String getStringRecords();
	    public Integer getIntegerRecords();
	    void renommerParametre_ecranCrud_ligne(Parametre_ecranCrud_ligne parametre_ecranCrud_ligne) throws DaoException;
		List<Map<String, Object>> rechercheParametre_ecranCrud_lignes(Integer offset, Integer noOfRecords,
				String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
				String type_entite);
		List<Map<String, Object>> rechercheLikeParametre_ecranCrud_lignes(Integer offset, Integer noOfRecords,
				String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne,
				String tag_statut, String type_entite);

}