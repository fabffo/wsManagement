/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO TVA                                                 ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ws.beans.Contrat;
import com.ws.beans.Tva;

public interface ContratDao {

	void ajouterContrat(Contrat contrat) throws DaoException;

	void modifierContrat(Contrat contrat) throws DaoException;

	void copierContrat(Contrat contrat) throws DaoException;

	void supprimerContrat(Integer id) throws DaoException;

	void renommerContrat(Contrat contrat) throws DaoException;

	List<Contrat> listerContrat() throws DaoException;

	Contrat trouverContrat(Integer id) throws DaoException;

	boolean trouverNomContrat(String nom) throws DaoException;

	int getNoOfRecords();

	Integer getIntegerRecords();
	List<Map<String, Object>> rechercheLikeContrats(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_entite);

	List<Map<String, Object>> rechercheContrats(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);

	void terminerContrat(Integer id_contrat) throws DaoException;

	void annulerContrat(Integer id_contrat) throws DaoException;

	void faire_avenantContrat(Contrat contrat) throws DaoException;

}