/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN TVA                                                ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class FactureVente_detail {
	private int id;
	private int numero_ligne;
	private String produit;
	private Double taux_tva;
	private Double montant_ht;
	private Double montant_ttc;
	private Double montant_tva;
	private String usermodification;
    private Date datemodification;
    private String pgmmodification;
    private String usercreation;
    private Date datecreation;
    private String pgmcreation;
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
	public String getProduit() {
		return produit;
	}
	public void setProduit(String produit) {
		this.produit = produit;
	}
	public Double getTaux_tva() {
		return taux_tva;
	}
	public void setTaux_tva(Double taux_tva) {
		this.taux_tva = taux_tva;
	}
	public Double getMontant_ht() {
		return montant_ht;
	}
	public void setMontant_ht(Double montant_ht) {
		this.montant_ht = montant_ht;
	}
	public Double getMontant_ttc() {
		return montant_ttc;
	}
	public void setMontant_ttc(Double montant_ttc) {
		this.montant_ttc = montant_ttc;
	}
	public Double getMontant_tva() {
		return montant_tva;
	}
	public void setMontant_tva(Double montant_tva) {
		this.montant_tva = montant_tva;
	}
	public String getUsermodification() {
		return usermodification;
	}
	public void setUsermodification(String usermodification) {
		this.usermodification = usermodification;
	}
	public Date getDatemodification() {
		return datemodification;
	}
	public void setDatemodification(Date datemodification) {
		this.datemodification = datemodification;
	}
	public String getPgmmodification() {
		return pgmmodification;
	}
	public void setPgmmodification(String pgmmodification) {
		this.pgmmodification = pgmmodification;
	}
	public String getUsercreation() {
		return usercreation;
	}
	public void setUsercreation(String usercreation) {
		this.usercreation = usercreation;
	}
	public Date getDatecreation() {
		return datecreation;
	}
	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}
	public String getPgmcreation() {
		return pgmcreation;
	}
	public void setPgmcreation(String pgmcreation) {
		this.pgmcreation = pgmcreation;
	}
	@Override
	public String toString() {
		return "FactureVente_detail [id=" + id + ", numero_ligne=" + numero_ligne
				+ ", produit=" + produit + ", taux_tva=" + taux_tva + ", montant_ht=" + montant_ht + ", montant_ttc="
				+ montant_ttc + ", montant_tva=" + montant_tva + ", usermodification=" + usermodification
				+ ", datemodification=" + datemodification + ", pgmmodification=" + pgmmodification + ", usercreation="
				+ usercreation + ", datecreation=" + datecreation + ", pgmcreation=" + pgmcreation + "]";
	}


}
