/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO UTILISATEUR                                          ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

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
	    public List<Utilisateur> rechercheUtilisateurs(int offset, int noOfRecords, String select_tri) throws DaoException;
	    public int getNoOfRecords();
	    public List<Utilisateur> rechercheLikeUtilisateurs(int offset, int noOfRecords, String select_tri, String select_like) throws DaoException;
}