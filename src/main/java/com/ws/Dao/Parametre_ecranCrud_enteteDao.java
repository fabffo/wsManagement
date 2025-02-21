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

import com.ws.beans.Parametre_ecranCrud_entete;

public interface Parametre_ecranCrud_enteteDao {
	    void ajouterParametre_ecranCrud_entete( Parametre_ecranCrud_entete parametre_ecranCrud_entete ) throws DaoException;
	    List<Parametre_ecranCrud_entete> listerParametre_ecranCrud_entete() throws DaoException;
	    Parametre_ecranCrud_entete trouverParametre_ecranCrud_entete( Integer id )throws DaoException;
	    void modifierParametre_ecranCrud_entete( Parametre_ecranCrud_entete parametre_ecranCrud_entete )throws DaoException;
	    void copierParametre_ecranCrud_entete( Parametre_ecranCrud_entete parametre_ecranCrud_entete )throws DaoException;
	    void supprimerParametre_ecranCrud_entete( Integer id )throws DaoException;
	    boolean trouverNomParametre_ecranCrud_entete (String nom) throws DaoException;
	    public int getNoOfRecords();
	    public String getStringRecords();
	    public Integer getIntegerRecords();
	    void renommerParametre_ecranCrud_entete(Parametre_ecranCrud_entete parametre_ecranCrud_entete) throws DaoException;
		List<Map<String, Object>> rechercheLikeParametre_ecranCrud_entetes(Integer offset, Integer noOfRecords,
				String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne,
				String tag_statut, String type_entite);
		List<Map<String, Object>> rechercheParametre_ecranCrud_entetes(Integer offset, Integer noOfRecords,
				String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
				String type_entite);
}