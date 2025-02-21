/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO TVA                                                 ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.List;

import com.ws.beans.FactureAchat;

public interface FactureAchatDao {
	    void importerFactureAchat( FactureAchat entete ) throws DaoException;
	    List<FactureAchat> listerFactureAchat() throws DaoException;
	    FactureAchat trouverFactureAchat( Integer id )throws DaoException;
	    public List<FactureAchat> rechercheFactureAchats(int offset, int noOfRecords, String select_tri);
	    public int getNoOfRecords();
	    public List<FactureAchat> rechercheLikeFactureAchats(int offset, int noOfRecords, String select_tri, String select_like);
		List<FactureAchat> rechercheLikeVisualisationFactureAchats(int id, int offset,
				int noOfRecords, String select_tri, String select_like);
		List<FactureAchat> rechercheVisualisationFactureAchats(int id, int offset, int noOfRecords,
				String select_tri);
		int getNoOfRecordsVisualisation();
		int getNoOfRecordsRapprochement();
		List<FactureAchat> rechercheRapprochementFactureAchats(int id, int yearMonth, int offset, int noOfRecords,
				String select_tri);
		List<FactureAchat> rechercheLikeRapprochementFactureAchats(int id, int yearMonth, int offset,
				int noOfRecords, String select_tri, String select_like);
	   }