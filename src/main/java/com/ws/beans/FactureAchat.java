/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN TVA                                                ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class FactureAchat {
	private int id;
	private String entite;
	private String nom_entite;
	private float ttc;
	private Date date_facture;
	private float taux_tva;
	private float montant_tva;
	private float ht;
	private Date date_import;
	private String user_import;
	private String pgm_import;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEntite() {
		return entite;
	}
	public void setEntite(String entite) {
		this.entite = entite;
	}
	public String getNom_entite() {
		return nom_entite;
	}
	public void setNom_entite(String nom_entite) {
		this.nom_entite = nom_entite;
	}
	public float getTtc() {
		return ttc;
	}
	public void setTtc(float ttc) {
		this.ttc = ttc;
	}
	public Date getDate_facture() {
		return date_facture;
	}
	public void setDate_facture(Date date_facture) {
		this.date_facture = date_facture;
	}
	public float getTaux_tva() {
		return taux_tva;
	}
	public void setTaux_tva(float taux_tva) {
		this.taux_tva = taux_tva;
	}
	public float getMontant_tva() {
		return montant_tva;
	}
	public void setMontant_tva(float montant_tva) {
		this.montant_tva = montant_tva;
	}
	public float getHt() {
		return ht;
	}
	public void setHt(float ht) {
		this.ht = ht;
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
		return "facture_achat [id=" + id + ", entite=" + entite + ", nom_entite=" + nom_entite + ", ttc=" + ttc
				+ ", date_facture=" + date_facture + ", taux_tva=" + taux_tva + ", montant_tva=" + montant_tva + ", ht="
				+ ht + ", date_import=" + date_import + ", user_import=" + user_import + ", pgm_import=" + pgm_import
				+ "]";
	}


}
