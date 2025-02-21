/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO UTILISATEUR                                          ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.List;

import com.ws.beans.TypePrix;

public interface TypePrixDao {
	    void ajouterTypePrix( TypePrix typePrix ) throws DaoException;
	    List<TypePrix> listerTypePrix() throws DaoException;
	    TypePrix trouverTypePrix( Integer id )throws DaoException;
	    void modifierTypePrix( TypePrix typePrix )throws DaoException;
	    void copierTypePrix( TypePrix typePrix )throws DaoException;
	    void supprimerTypePrix( Integer id )throws DaoException;
	    boolean trouverNomTypePrix (String nom) throws DaoException;
	    public List<TypePrix> rechercheTypePrixs(int offset, int noOfRecords, String select_tri) throws DaoException;
	    public int getNoOfRecords();
	    public List<TypePrix> rechercheLikeTypePrixs(int offset, int noOfRecords, String select_tri, String select_like) throws DaoException;
}