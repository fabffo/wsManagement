/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO TYPE CONTRAT                                        ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.List;

import com.ws.beans.TypeContratCollaborateur;

public interface TypeContratCollaborateurDao {
	    void ajouterTypeContratCollaborateur( TypeContratCollaborateur typeContratCollaborateur ) throws DaoException;
	    List<TypeContratCollaborateur> listerTypeContratCollaborateur() throws DaoException;
	    TypeContratCollaborateur trouverTypeContratCollaborateur( Integer id )throws DaoException;
	    void modifierTypeContratCollaborateur( TypeContratCollaborateur typeContratCollaborateur )throws DaoException;
	    void copierTypeContratCollaborateur( TypeContratCollaborateur typeContratCollaborateur )throws DaoException;
	    void supprimerTypeContratCollaborateur( Integer id )throws DaoException;
	    boolean trouverNomTypeContratCollaborateur (String nom) throws DaoException;
	    public List<TypeContratCollaborateur> rechercheTypeContratCollaborateurs(int offset, int noOfRecords, String select_tri) throws DaoException;
	    public int getNoOfRecords();
	    public List<TypeContratCollaborateur> rechercheLikeTypeContratCollaborateurs(int offset, int noOfRecords, String select_tri, String select_like) throws DaoException;
}