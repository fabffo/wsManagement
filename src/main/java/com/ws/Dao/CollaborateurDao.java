/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO COLLABORATEUR                                       ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ws.beans.Collaborateur;

public interface CollaborateurDao {
	    void ajouterCollaborateur( Collaborateur collaborateur ) throws DaoException;
	    List<Collaborateur> listerCollaborateur() throws DaoException;
	    Collaborateur trouverCollaborateur( Integer id )throws DaoException;
	    void modifierCollaborateur( Collaborateur collaborateur )throws DaoException;
	    void copierCollaborateur( Collaborateur collaborateur )throws DaoException;
	    void supprimerCollaborateur( Integer id )throws DaoException;
	    boolean trouverNomCollaborateur (String nom) throws DaoException;
	    public int getNoOfRecords();
	   		List<Collaborateur> rechercheCollaborateurs1(int offset, int noOfRecords, String select_tri,
				LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);
		List<Collaborateur> rechercheLikeCollaborateurs1(int offset, int noOfRecords, String select_tri,
				String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
				String type_entite);
		List<Map<String, Object>> rechercheCollaborateurs(Integer offset, Integer noOfRecords, String select_tri,
				LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);
		List<Map<String, Object>> rechercheLikeCollaborateurs(Integer offset, Integer noOfRecords, String select_tri,
				String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
				String type_entite);
		String getStringRecords();
		Integer getIntegerRecords();
}