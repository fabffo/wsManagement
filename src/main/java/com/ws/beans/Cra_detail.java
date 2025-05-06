package com.ws.beans;

import java.util.Date;

public class Cra_detail {
	private int id;
	private String nom_jour;
	private int numero_jour;
	private float temps_passe;
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
	public String getNom_jour() {
		return nom_jour;
	}
	public void setNom_jour(String nom_jour) {
		this.nom_jour = nom_jour;
	}
	public int getNumero_jour() {
		return numero_jour;
	}
	public void setNumero_jour(int numero_jour) {
		this.numero_jour = numero_jour;
	}
	public float getTemps_passe() {
		return temps_passe;
	}
	public void setTemps_passe(float temps_passe) {
		this.temps_passe = temps_passe;
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
		return "cra_detail [id=" + id + ", nom_jour=" + nom_jour + ", numero_jour=" + numero_jour + ", temps_passe="
				+ temps_passe + ", usermodification=" + usermodification + ", datemodification=" + datemodification
				+ ", pgmmodification=" + pgmmodification + ", usercreation=" + usercreation + ", datecreation="
				+ datecreation + ", pgmcreation=" + pgmcreation + ", getId()=" + getId() + ", getNom_jour()="
				+ getNom_jour() + ", getNumero_jour()=" + getNumero_jour() + ", getTemps_passe()=" + getTemps_passe()
				+ ", getUsermodification()=" + getUsermodification() + ", getDatemodification()="
				+ getDatemodification() + ", getPgmmodification()=" + getPgmmodification() + ", getUsercreation()="
				+ getUsercreation() + ", getDatecreation()=" + getDatecreation() + ", getPgmcreation()="
				+ getPgmcreation() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
