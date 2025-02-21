/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO TYPE MISSION                                        ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.List;

import com.ws.beans.TypeContratSociete;

public interface TypeContratSocieteDao {
	    void ajouterTypeContratSociete( TypeContratSociete utilisateur ) throws DaoException;
	    List<TypeContratSociete> listerTypeContratSociete() throws DaoException;
	    TypeContratSociete trouverTypeContratSociete( Integer id )throws DaoException;
	    void modifierTypeContratSociete( TypeContratSociete typeContratSociete )throws DaoException;
	    void copierTypeContratSociete( TypeContratSociete typeContratSociete )throws DaoException;
	    void supprimerTypeContratSociete( Integer id )throws DaoException;
	    boolean trouverNomTypeContratSociete (String nom) throws DaoException;
	    public List<TypeContratSociete> rechercheTypeContratSocietes(int offset, int noOfRecords, String select_tri);
	    public int getNoOfRecords();
	    public List<TypeContratSociete> rechercheLikeTypeContratSocietes(int offset, int noOfRecords, String select_tri, String select_like);
}