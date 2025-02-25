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

import com.ws.beans.TypeUtilisateur;
import com.ws.beans.Tva;

public interface TypeUtilisateurDao {

	void ajouterTypeUtilisateur(TypeUtilisateur typeUtilisateur) throws DaoException;

	void modifierTypeUtilisateur(TypeUtilisateur typeUtilisateur) throws DaoException;

	void copierTypeUtilisateur(TypeUtilisateur typeUtilisateur) throws DaoException;

	void supprimerTypeUtilisateur(Integer id) throws DaoException;

	void renommerTypeUtilisateur(TypeUtilisateur typeUtilisateur) throws DaoException;

	List<TypeUtilisateur> listerTypeUtilisateur() throws DaoException;

	TypeUtilisateur trouverTypeUtilisateur(Integer id) throws DaoException;

	boolean trouverNomTypeUtilisateur(String nom) throws DaoException;

	int getNoOfRecords();

	Integer getIntegerRecords();
	List<Map<String, Object>> rechercheLikeTypeUtilisateurs(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_entite);

	List<Map<String, Object>> rechercheTypeUtilisateurs(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);

	List<TypeUtilisateur> listerTypeUtilisateurClient() throws DaoException;

	List<TypeUtilisateur> listerTypeUtilisateurFournisseur() throws DaoException;

	List<TypeUtilisateur> listerTypeUtilisateurSalarie() throws DaoException;

}