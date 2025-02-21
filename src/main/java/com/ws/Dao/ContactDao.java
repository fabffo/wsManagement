/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO CONTACT                                             ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.List;

import com.ws.beans.Contact;

public interface ContactDao {
	    void ajouterContact( Contact contact ) throws DaoException;
	    List<Contact> listerContact() throws DaoException;
	    Contact trouverContact( Integer id )throws DaoException;
	    void modifierContact( Contact contact )throws DaoException;
	    void copierContact( Contact contact )throws DaoException;
	    void supprimerContact( Integer id )throws DaoException;
	    boolean trouverNomContact (String nom) throws DaoException;
	    public List<Contact> rechercheContacts(int offset, int noOfRecords, String select_tri, String typeSociete);
	    public int getNoOfRecords();
	    public List<Contact> rechercheLikeContacts(int offset, int noOfRecords, String select_tri, String select_like, String typeSociete);
		String getStringRecords();
		Integer getIntegerRecords();
}