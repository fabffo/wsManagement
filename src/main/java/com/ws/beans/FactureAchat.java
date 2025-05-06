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
	private String nom;
	private int entite;
	private int organisation;
	private int TypeFactureAchat;
	private String document;
	private int tva;
	private String date_facture;
	private Double montant_ht;
	private Double montant_ttc;
	private Double montant_tva;
	private String date_import;
	private String user_import;
	private String pgm_import;
	private String usermodification;
    private Date datemodification;
    private String pgmmodification;
    private String usercreation;
    private Date datecreation;
    private String pgmcreation;
	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getEntite() {
		return entite;
	}
	public void setEntite(int entite) {
		this.entite = entite;
	}
	public int getOrganisation() {
		return organisation;
	}

	public void setOrganisation(int organisation) {
		this.organisation = organisation;
	}
	public int getTypeFactureAchat() {
		return TypeFactureAchat;
	}
	public void setTypeFactureAchat(int typeFactureAchat) {
		TypeFactureAchat = typeFactureAchat;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public int getTva() {
		return tva;
	}
	public void setTva(int tva) {
		this.tva = tva;
	}
	public String getDate_facture() {
		return date_facture;
	}
	public void setDate_facture(String date_facture) {
		this.date_facture = date_facture;
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
	public String getDate_import() {
		return date_import;
	}
	public void setDate_import(String date_import) {
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
		return "FactureAchat [id=" + id + ", nom=" + nom + ", entite=" + entite + ", organisation=" + organisation
				+ ", TypeFactureAchat=" + TypeFactureAchat + ", document=" + document + ", tva=" + tva
				+ ", date_facture=" + date_facture + ", montant_ht=" + montant_ht + ", montant_ttc=" + montant_ttc
				+ ", montant_tva=" + montant_tva + ", date_import=" + date_import + ", user_import=" + user_import
				+ ", pgm_import=" + pgm_import + ", usermodification=" + usermodification + ", datemodification="
				+ datemodification + ", pgmmodification=" + pgmmodification + ", usercreation=" + usercreation
				+ ", datecreation=" + datecreation + ", pgmcreation=" + pgmcreation + "]";
	}

}
