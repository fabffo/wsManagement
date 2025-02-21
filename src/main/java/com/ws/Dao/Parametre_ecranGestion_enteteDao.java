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

import com.ws.beans.Parametre_ecranGestion_entete;

public interface Parametre_ecranGestion_enteteDao {
	    void ajouterParametre_ecranGestion_entete( Parametre_ecranGestion_entete parametre_ecranGestion_entete ) throws DaoException;
	    List<Parametre_ecranGestion_entete> listerParametre_ecranGestion_entete() throws DaoException;
	    Parametre_ecranGestion_entete trouverParametre_ecranGestion_entete( Integer id )throws DaoException;
	    void modifierParametre_ecranGestion_entete( Parametre_ecranGestion_entete parametre_ecranGestion_entete )throws DaoException;
	    void copierParametre_ecranGestion_entete( Parametre_ecranGestion_entete parametre_ecranGestion_entete )throws DaoException;
	    void supprimerParametre_ecranGestion_entete( Integer id )throws DaoException;
	    public int getNoOfRecords();
	    public String getStringRecords();
	    public Integer getIntegerRecords();
	    void renommerParametre_ecranGestion_entete(Parametre_ecranGestion_entete parametre_ecranGestion_entete) throws DaoException;
		boolean trouverNomParametre_ecranGestion_entete(String nom) throws DaoException;
		List<Map<String, Object>> rechercheParametre_ecranGestion_entetes(Integer offset, Integer noOfRecords,
				String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
				String type_entite);
		List<Map<String, Object>> rechercheLikeParametre_ecranGestion_entetes(Integer offset, Integer noOfRecords,
				String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne,
				String tag_statut, String type_entite);
}