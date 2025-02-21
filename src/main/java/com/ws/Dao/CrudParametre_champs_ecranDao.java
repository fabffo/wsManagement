/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO TVA                                                 ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.util.List;
import java.util.Map;

import com.ws.beans.CrudParametre_champs_ecran;

public interface CrudParametre_champs_ecranDao {
	    void ajouterCrudParametre_champs_ecran( CrudParametre_champs_ecran crudParametre_champs_ecran ) throws DaoException;
	    List<CrudParametre_champs_ecran> listerCrudParametre_champs_ecran() throws DaoException;
	    CrudParametre_champs_ecran trouverCrudParametre_champs_ecran( Integer id )throws DaoException;
	    void modifierCrudParametre_champs_ecran( CrudParametre_champs_ecran crudParametre_champs_ecran )throws DaoException;
	    void copierCrudParametre_champs_ecran( CrudParametre_champs_ecran crudParametre_champs_ecran )throws DaoException;
	    void supprimerCrudParametre_champs_ecran( Integer id )throws DaoException;
	    boolean trouverNomCrudParametre_champs_ecran (String nom) throws DaoException;
	    public List<Map<String, Object>> rechercheCrudParametre_champs_ecrans(Integer offset, Integer noOfRecords, String select_tri);
	    public int getNoOfRecords();
	    public String getStringRecords();
	    public Integer getIntegerRecords();
	    public List<Map<String, Object>> rechercheLikeCrudParametre_champs_ecrans(Integer offset, Integer noOfRecords, String select_tri, String select_like);
		void renommerCrudParametre_champs_ecran(CrudParametre_champs_ecran crudParametre_champs_ecran) throws DaoException;
}