/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO UTILISATEUR                                          ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.ws.beans.Utilisateur;

public interface UtilisateurDao {
	    void ajouterUtilisateur( Utilisateur utilisateur ) throws DaoException;
	    List<Utilisateur> listerUtilisateur() throws DaoException;
	    Utilisateur trouverUtilisateur( Integer id )throws DaoException;
	    void modifierUtilisateur( Utilisateur utilisateur )throws DaoException;
	    void copierUtilisateur( Utilisateur utilisateur )throws DaoException;
	    void supprimerUtilisateur( Integer id )throws DaoException;
	    boolean trouverNomUtilisateur (String nom) throws DaoException;
	    public int getNoOfRecords();
	    List<Utilisateur> listerUtilisateurSalarie() throws DaoException;
		List<Utilisateur> listerUtilisateurClient() throws DaoException;
		List<Utilisateur> listerUtilisateurFournisseur() throws DaoException;
		List<Utilisateur> rechercheUtilisateurs(Integer offset, Integer noOfRecords, String select_tri,
				LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite)
				throws DaoException;
		List<Utilisateur> rechercheLikeUtilisateurs(Integer offset, Integer noOfRecords, String select_tri,
				String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
				String type_entite) throws DaoException;
		Integer getIntegerRecords();
}