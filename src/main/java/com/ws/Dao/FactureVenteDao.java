/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO TVA                                                 ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ws.beans.FactureVente;
import com.ws.beans.FactureVente_detail;
import com.ws.beans.FactureVente;

public interface FactureVenteDao {

	void ajouterFactureVente(FactureVente fv, List<FactureVente_detail> details) throws SQLException;

	void copierFactureVente(FactureVente facture, List<FactureVente_detail> lignes) throws SQLException;

	FactureVente trouverFactureVente(int id) throws SQLException;

	List<Map<String, Object>> rechercheFactureVentes(Integer offset, Integer noOfRecords, String select_tri,
			LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut, String type_factureVente);

	List<Map<String, Object>> rechercheLikeFactureVentes(Integer offset, Integer noOfRecords, String select_tri,
			String select_like, LinkedHashMap<String, String> dictionnaire_nom_colonne, String tag_statut,
			String type_factureVente);

	int getNoOfRecords();

	Integer getIntegerRecords();

	void updatePdfPath(int id, String pdfPath) throws SQLException;


	   }