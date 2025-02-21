/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO CALENDRIER                                          ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.Date;
import java.util.List;

import com.ws.beans.Calendrier;

public interface CalendrierDao {
	    void ajouterCalendrier( Calendrier utilisateur ) throws DaoException;
	    List<Calendrier> listerCalendrier() throws DaoException;
	    Calendrier trouverCalendrier( Integer id )throws DaoException;
	    void modifierCalendrier( Calendrier Calendrier )throws DaoException;
	    void copierCalendrier( Calendrier Calendrier )throws DaoException;
	    void supprimerCalendrier( Integer id_date )throws DaoException;
	    boolean trouverDateCalendrier (Date date_cal) throws DaoException;
	    public List<Object> viewAllCalendriers(int annee, int mois);
	    public List<Calendrier> rechercheCalendriers(int offset, int noOfRecords, String select_tri, String select_like);
}