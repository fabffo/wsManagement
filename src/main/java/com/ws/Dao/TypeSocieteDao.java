/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO TYPE SOCIETE                                        ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.List;

import com.ws.beans.TypeSociete;

public interface TypeSocieteDao {
	    void ajouterTypeSociete( TypeSociete utilisateur ) throws DaoException;
	    List<TypeSociete> listerTypeSociete() throws DaoException;
	    TypeSociete trouverTypeSociete( Integer id )throws DaoException;
	    void modifierTypeSociete( TypeSociete typeSociete )throws DaoException;
	    void copierTypeSociete( TypeSociete typeSociete )throws DaoException;
	    void supprimerTypeSociete( Integer id )throws DaoException;
	    boolean trouverNomTypeSociete (String nom) throws DaoException;
	    public List<TypeSociete> rechercheTypeSocietes(int offset, int noOfRecords, String select_tri) throws DaoException;
	    public int getNoOfRecords();
	    public List<TypeSociete> rechercheLikeTypeSocietes(int offset, int noOfRecords, String select_tri, String select_like) throws DaoException;
}