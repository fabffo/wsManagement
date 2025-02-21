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

import com.ws.beans.Parametre_ecranGestion_ligne;

public interface Parametre_ecranGestion_ligneDao {
	    void ajouterParametre_ecranGestion_ligne( Parametre_ecranGestion_ligne parametre_ecranGestion_ligne ) throws DaoException;
	    List<Parametre_ecranGestion_ligne> listerParametre_ecranGestion_ligne() throws DaoException;
	    Parametre_ecranGestion_ligne trouverParametre_ecranGestion_ligne( Integer id )throws DaoException;
	    void modifierParametre_ecranGestion_ligne( Parametre_ecranGestion_ligne parametre_ecranGestion_ligne )throws DaoException;
	    void copierParametre_ecranGestion_ligne( Parametre_ecranGestion_ligne parametre_ecranGestion_ligne )throws DaoException;
	    void supprimerParametre_ecranGestion_ligne( Integer id )throws DaoException;
	    boolean trouverNomParametre_ecranGestion_ligne (String nom) throws DaoException;
	    public int getNoOfRecords();
	    public String getStringRecords();
	    public Integer getIntegerRecords();
	    void renommerParametre_ecranGestion_ligne(Parametre_ecranGestion_ligne parametre_ecranGestion_ligne) throws DaoException;
		List<Map<String, Object>> rechercheParametre_ecranGestion_lignes(Integer offset, Integer noOfRecords,
				String select_tri, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
				String type_entite);
		List<Map<String, Object>> rechercheLikeParametre_ecranGestion_lignes(Integer offset, Integer noOfRecords,
				String select_tri, String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne,
				String tag_statut, String type_entite);
		}