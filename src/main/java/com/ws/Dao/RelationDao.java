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

import com.ws.beans.Relation;

public interface RelationDao {
	    void ajouterRelation( Relation Relation ) throws DaoException;
	    List<Relation> listerRelation() throws DaoException;
	    Relation trouverRelation( Integer id )throws DaoException;
	    void modifierRelation( Relation Relation )throws DaoException;
	    void copierRelation( Relation Relation )throws DaoException;
	    void supprimerRelation( Integer id )throws DaoException;
	    boolean trouverNomRelation (String nom) throws DaoException;
	    public int getNoOfRecords();
		void renommerRelation(Relation relation) throws DaoException;
		Integer getIntegerRecords();
		List<Map<String, Object>> rechercheRelations(Integer offset, Integer noOfRecords, String select_tri,
				LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);
		List<Map<String, Object>> rechercheLikeRelations(Integer offset, Integer noOfRecords, String select_tri,
				String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
				String type_entite);
		List<Relation> listerRelationSalarie() throws DaoException;
		List<Relation> listerRelationFournisseur() throws DaoException;
		List<Relation> listerRelationClient() throws DaoException;;

}