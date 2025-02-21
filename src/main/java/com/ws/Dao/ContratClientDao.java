/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO CONTRATCLIENT                                       ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.ws.beans.ContratClient;

public interface ContratClientDao {
	    void ajouterContratClient( ContratClient contratClient, String chemin_absolu_document_defaut, String chemin_absolu_document_reel, String chemin_relatif_document_reel ) throws DaoException;
	    List<ContratClient> listerContratClient() throws DaoException;
	    void modifierContratClient( ContratClient contratClient, String cheminRelatif_AvantModif, String cheminAbsolu_avantModif, String document_avantModif )throws DaoException;
	    void copierContratClient( ContratClient contratClient, String cheminRelatif_AvantModif, String cheminAbsolu_avantModif, String document_avantModif )throws DaoException;
	    void supprimerContratClient( Integer id )throws DaoException;
	    public ContratClient trouverIdVersion(int id_contrat, int id_avenant) throws DaoException;
	    public int getNoOfRecords();
	    boolean trouverNomContratClient(String nom) throws DaoException;
		void annulerContratClient(ContratClient contratClient) throws DaoException;
		void terminerContratClient(ContratClient contratClient) throws DaoException;
		void versionnerContratClient(ContratClient contratClient) throws DaoException;
		void renommerContratClient(ContratClient contratClient) throws DaoException;
		boolean trouverFacture(int id_contrat) throws DaoException;
		List<ContratClient> rechercheContratClients(int offset, int noOfRecords, String select_tri,
				LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);
		List<ContratClient> rechercheLikeContratClients(int offset, int noOfRecords, String select_tri,
				String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
				String type_entite);
}