/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO TYPEDOCUMENT                                                 ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.List;

import com.ws.beans.TypeDocument;

public interface TypeDocumentDao {
	    void ajouterTypeDocument( TypeDocument typeDocument ) throws DaoException;
	    List<TypeDocument> listerTypeDocument() throws DaoException;
	    TypeDocument trouverTypeDocument( Integer id )throws DaoException;
	    void modifierTypeDocument( TypeDocument typeDocument )throws DaoException;
	    void copierTypeDocument( TypeDocument typeDocument )throws DaoException;
	    void supprimerTypeDocument( Integer id )throws DaoException;
	    boolean trouverTypeDocument (String typeDocument) throws DaoException;
	    public List<TypeDocument> rechercheTypeDocuments(int offset, int noOfRecords, String select_tri) throws DaoException;
	    public int getNoOfRecords();
	    public List<TypeDocument> rechercheLikeTypeDocuments(int offset, int noOfRecords, String select_tri, String select_like) throws DaoException;
}