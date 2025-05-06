package com.ws.beans;

import java.util.Date;
public class Cra {
	private int id;
	private int mission;
	private int personnel;
	private int annee;
	private String mois;
	private float nbr_jours_ouvres;
	private float nbr_jours_activites;
	private int statut;
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
	public int getMission() {
		return mission;
	}
	public void setMission(int mission) {
		this.mission = mission;
	}
	public int getPersonnel() {
		return personnel;
	}
	public void setPersonnel(int personnel) {
		this.personnel = personnel;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public String getMois() {
		return mois;
	}
	public void setMois(String mois) {
		this.mois = mois;
	}
	public float getNbr_jours_ouvres() {
		return nbr_jours_ouvres;
	}
	public void setNbr_jours_ouvres(float nbr_jours_ouvres) {
		this.nbr_jours_ouvres = nbr_jours_ouvres;
	}
	public float getNbr_jours_activites() {
		return nbr_jours_activites;
	}
	public void setNbr_jours_activites(float nbr_jours_activites) {
		this.nbr_jours_activites = nbr_jours_activites;
	}
	public int getStatut() {
		return statut;
	}
	public void setStatut(int statut) {
		this.statut = statut;
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
		return "cra [id=" + id + ", mission=" + mission + ", personnel=" + personnel + ", annee=" + annee
				+ ", mois=" + mois + ", nbr_jours_ouvres=" + nbr_jours_ouvres + ", nbr_jours_activites="
				+ nbr_jours_activites + ", statut=" + statut + ", usermodification=" + usermodification
				+ ", datemodification=" + datemodification + ", pgmmodification=" + pgmmodification + ", usercreation="
				+ usercreation + ", datecreation=" + datecreation + ", pgmcreation=" + pgmcreation + "]";
	}

}
