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

import com.ws.beans.TypeFactureVente;
import com.ws.beans.Tva;

public interface TypeFactureVenteDao {

	void ajouterTypeFactureVente(TypeFactureVente typeContrat) throws DaoException;

	void modifierTypeFactureVente(TypeFactureVente typeContrat) throws DaoException;

	void copierTypeFactureVente(TypeFactureVente typeContrat) throws DaoException;

	void supprimerTypeFactureVente(Integer id) throws DaoException;

	void renommerTypeFactureVente(TypeFactureVente typeContrat) throws DaoException;

	List<TypeFactureVente> listerTypeFactureVente() throws DaoException;

	TypeFactureVente trouverTypeFactureVente(Integer id) throws DaoException;

	boolean trouverNomTypeFactureVente(String nom) throws DaoException;

	int getNoOfRecords();

	Integer getIntegerRecords();

	List<Map<String, Object>> rechercheTypeFactureVentes(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_entite);

	List<Map<String, Object>> rechercheLikeTypeFactureVentes(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_entite);

	List<TypeFactureVente> listerTypeFactureVenteClient() throws DaoException;

	List<TypeFactureVente> listerTypeFactureVenteFournisseur() throws DaoException;

	List<TypeFactureVente> listerTypeFactureVenteSalarie() throws DaoException;

	List<TypeFactureVente> listerTypeFactureVenteInterne() throws DaoException;

}