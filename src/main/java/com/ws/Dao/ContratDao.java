/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO CONTRATCLIENT                                       ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ws.beans.Contrat;

public interface ContratDao {
	    void ajouterContrat( Contrat contrat, String chemin_absolu_document_defaut, String chemin_absolu_document_reel, String chemin_relatif_document_reel, String type_entite ) throws DaoException;
	    List<Contrat> listerContrat() throws DaoException;
	    void modifierContrat( Contrat contrat, String cheminRelatif_AvantModif, String cheminAbsolu_avantModif, String document_avantModif )throws DaoException;
	    void copierContrat( Contrat contrat, String cheminRelatif_AvantModif, String cheminAbsolu_avantModif, String document_avantModif, String type_entite )throws DaoException;
	    void supprimerContrat( Integer id )throws DaoException;
	    public Contrat trouverIdVersion(int id, int version, String type_entite) throws DaoException;
	    public int getNoOfRecords();
	    Integer getIntegerRecords();
		String getStringRecords();
	    boolean trouverNomContrat(String nom) throws DaoException;
		void annulerContrat(Contrat contrat) throws DaoException;
		void terminerContrat(Contrat contrat) throws DaoException;
		void versionnerContrat(Contrat contrat) throws DaoException;
		void renommerContrat(Contrat contrat) throws DaoException;
		boolean trouverFacture(int id) throws DaoException;
		List<Contrat> rechercheContrats1(int offset, int noOfRecords, String select_tri,
				LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);
		List<Map<String, Object>> rechercheContrats(Integer offset, Integer noOfRecords, String select_tri,
				LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);
		List<Contrat> rechercheLikeContrats1(int offset, int noOfRecords, String select_tri, String select_like,
				LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);
		List<Map<String, Object>> rechercheLikeContrats(int offset, int noOfRecords, String select_tri,
				String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
				String type_entite);

}