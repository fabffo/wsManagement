/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN TVA                                                ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class EcritureBanque_import {
	private int id;
	private int numero_ligne;
	private String nom_import;
	private String code_banque;
	private int nbr_ligne;
	private Date date_import;
	private String user_import;
	private String pgm_import;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumero_ligne() {
		return numero_ligne;
	}
	public void setNumero_ligne(int numero_ligne) {
		this.numero_ligne = numero_ligne;
	}
	public String getNom_import() {
		return nom_import;
	}
	public void setNom_import(String nom_import) {
		this.nom_import = nom_import;
	}
	public String getCode_banque() {
		return code_banque;
	}
	public void setCode_banque(String code_banque) {
		this.code_banque = code_banque;
	}
	public int getNbr_ligne() {
		return nbr_ligne;
	}
	public void setNbr_ligne(int nbr_ligne) {
		this.nbr_ligne = nbr_ligne;
	}
	public Date getDate_import() {
		return date_import;
	}
	public void setDate_import(Date date_import) {
		this.date_import = date_import;
	}
	public String getUser_import() {
		return user_import;
	}
	public void setUser_import(String user_import) {
		this.user_import = user_import;
	}
	public String getPgm_import() {
		return pgm_import;
	}
	public void setPgm_import(String pgm_import) {
		this.pgm_import = pgm_import;
	}
	@Override
	public String toString() {
		return "EcritureBanque_import [id=" + id + ", numero_ligne=" + numero_ligne + ", nom_import=" + nom_import
				+ ", code_banque=" + code_banque + ", nbr_ligne=" + nbr_ligne + ", "
						+ "date_import=" + date_import + ", user_import=" + user_import + ", pgm_import=" + pgm_import + "]";
	}


}
