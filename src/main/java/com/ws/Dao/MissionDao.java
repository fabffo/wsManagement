/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO CONTRATCLIENT                                       ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.List;

import com.ws.beans.Mission;

public interface MissionDao {
	    void ajouterMission( Mission mission, String chemin_absolu_document_defaut, String chemin_absolu_document_reel, String chemin_relatif_document_reel, String type_entite ) throws DaoException;
	    List<Mission> listerMission() throws DaoException;
	    void modifierMission( Mission mission, String cheminRelatif_AvantModif, String cheminAbsolu_avantModif, String document_avantModif )throws DaoException;
	    void copierMission( Mission mission, String cheminRelatif_AvantModif, String cheminAbsolu_avantModif, String document_avantModif, String type_entite )throws DaoException;
	    void supprimerMission( Integer id )throws DaoException;
	    public List<Mission> rechercheMissions(int offset, int noOfRecords, String select_tri, String tag_statut,String type_entite);
	    public int getNoOfRecords();
	    public List<Mission> rechercheLikeMissions(int offset, int noOfRecords, String select_tri, String select_like, String tag_statut, String type_entite);
		boolean trouverNomMission(String nom) throws DaoException;
		void annulerMission(Mission mission) throws DaoException;
		void terminerMission(Mission mission) throws DaoException;
		void renommerMission(Mission mission) throws DaoException;
		boolean trouverFacture(int id) throws DaoException;
		Mission trouverId(int id, String type_entite) throws DaoException;
}