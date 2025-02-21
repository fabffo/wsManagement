/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO SOCIETE                                             ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.List;

import com.ws.beans.Societe;

public interface SocieteDao {
	    void ajouterSociete( Societe utilisateur ) throws DaoException;
	    List<Societe> listerSociete() throws DaoException;
	    Societe trouverSociete( Integer id )throws DaoException;
	    void modifierSociete( Societe societe )throws DaoException;
	    void copierSociete( Societe societe )throws DaoException;
	    void supprimerSociete( Integer id )throws DaoException;
	    boolean trouverRaison_socialeSociete (String nom) throws DaoException;
	    public List<Societe> rechercheSocietes(int offset, int noOfRecords, String select_tri, String typeSociete);
	    public int getNoOfRecords();
	    public List<Societe> rechercheLikeSocietes(int offset, int noOfRecords, String select_tri, String select_like, String typeSociete);
}