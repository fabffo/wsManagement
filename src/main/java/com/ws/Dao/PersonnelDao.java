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

import com.ws.beans.Personnel;

public interface PersonnelDao {
	    void ajouterPersonnel( Personnel personnel ) throws DaoException;
	    List<Personnel> listerPersonnel() throws DaoException;
	    Personnel trouverPersonnel( Integer id )throws DaoException;
	    void modifierPersonnel( Personnel personnel )throws DaoException;
	    void copierPersonnel( Personnel personnel )throws DaoException;
	    void supprimerPersonnel( Integer id )throws DaoException;
	    boolean trouverNomPersonnel (String nom) throws DaoException;
	    public int getNoOfRecords();
		List<Map<String, Object>> recherchePersonnels(Integer offset, Integer noOfRecords, String select_tri,
				LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);
		List<Map<String, Object>> rechercheLikePersonnels(Integer offset, Integer noOfRecords, String select_tri,
				String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
				String type_entite);
		String getStringRecords();
		Integer getIntegerRecords();
		void renommerPersonnel(Personnel personnel) throws DaoException;
		List<Personnel> listerPersonnelClient() throws DaoException;
		List<Personnel> listerPersonnelFournisseur() throws DaoException;
		List<Personnel> listerPersonnelSalarie() throws DaoException;
		List<Personnel> listerPersonnelInterne() throws DaoException;
}