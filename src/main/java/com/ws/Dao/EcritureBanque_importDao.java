/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO TVA                                                 ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.List;

import com.ws.beans.EcritureBanque_detail;
import com.ws.beans.EcritureBanque_import;

public interface EcritureBanque_importDao {
	    void importerEcritureBanque( EcritureBanque_import entete, List<EcritureBanque_detail> detailsList ) throws DaoException;
	    List<EcritureBanque_import> listerEcritureBanque_import() throws DaoException;
	    EcritureBanque_import trouverEcritureBanque_import( Integer id )throws DaoException;
	    public List<EcritureBanque_import> rechercheEcritureBanque_imports(int offset, int noOfRecords, String select_tri);
	    public int getNoOfRecords();
	    public List<EcritureBanque_import> rechercheLikeEcritureBanque_imports(int offset, int noOfRecords, String select_tri, String select_like);
		List<EcritureBanque_detail> rechercheLikeVisualisationEcritureBanque_imports(int id, int offset,
				int noOfRecords, String select_tri, String select_like);
		List<EcritureBanque_detail> rechercheVisualisationEcritureBanque_imports(int id, int offset, int noOfRecords,
				String select_tri);
		int getNoOfRecordsVisualisation();
		int getNoOfRecordsRapprochement();
		List<EcritureBanque_detail> rechercheRapprochementEcritureBanques(int id, int yearMonth, int offset, int noOfRecords,
				String select_tri);
		List<EcritureBanque_detail> rechercheLikeRapprochementEcritureBanques(int id, int yearMonth, int offset,
				int noOfRecords, String select_tri, String select_like);
	   }