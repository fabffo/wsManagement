/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN CALENDRIER                                         ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class Calendrier {

	private int id_date;
	private Date date_cal;
	private int jour;
	private int mois;
	private int annee;
	private String nom_mois;
	private String nom_jour;
	private int annee_mois;
	private String type_jour;
	private String complement_jour;
	private String usermodification;
    private Date datemodification;
    private String pgmmodification;
    private String usercreation;
    private Date datecreation;
    private String pgmcreation;
	public int getId_date() {
		return id_date;
	}
	public void setId_date(int id_date) {
		this.id_date = id_date;
	}
	public Date getDate_cal() {
		return date_cal;
	}
	public void setDate_cal(Date date_cal) {
		this.date_cal = date_cal;
	}
	public int getJour() {
		return jour;
	}
	public void setJour(int jour) {
		this.jour = jour;
	}
	public int getMois() {
		return mois;
	}
	public void setMois(int mois) {
		this.mois = mois;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public String getNom_mois() {
		return nom_mois;
	}
	public void setNom_mois(String nom_mois) {
		this.nom_mois = nom_mois;
	}
	public String getNom_jour() {
		return nom_jour;
	}
	public void setNom_jour(String nom_jour) {
		this.nom_jour = nom_jour;
	}
	public int getAnnee_mois() {
		return annee_mois;
	}
	public void setAnnee_mois(int annee_mois) {
		this.annee_mois = annee_mois;
	}
	public String getType_jour() {
		return type_jour;
	}
	public void setType_jour(String type_jour) {
		this.type_jour = type_jour;
	}
	public String getComplement_jour() {
		return complement_jour;
	}
	public void setComplement_jour(String complement_jour) {
		this.complement_jour = complement_jour;
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
		return "Calendrier [id_date=" + id_date + ", date_cal=" + date_cal + ", jour=" + jour + ", mois=" + mois
				+ ", annee=" + annee + ", nom_mois=" + nom_mois + ", nom_jour=" + nom_jour + ", annee_mois="
				+ annee_mois + ", type_jour=" + type_jour + ", complement_jour=" + complement_jour
				+ ", usermodification=" + usermodification + ", datemodification=" + datemodification
				+ ", pgmmodification=" + pgmmodification + ", usercreation=" + usercreation + ", datecreation="
				+ datecreation + ", pgmcreation=" + pgmcreation + "]";
	}
	
	

}
