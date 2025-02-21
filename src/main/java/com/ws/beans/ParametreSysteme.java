/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN TVA                                                ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class ParametreSysteme {

	private int id;
	private String nom;
	private String libelle;
	private String nom_ecran_gestion;
	private String nom_ecran_crud;
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
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getNom_ecran_gestion() {
		return nom_ecran_gestion;
	}
	public void setNom_ecran_gestion(String nom_ecran_gestion) {
		this.nom_ecran_gestion = nom_ecran_gestion;
	}
	public String getNom_ecran_crud() {
		return nom_ecran_crud;
	}
	public void setNom_ecran_crud(String nom_ecran_crud) {
		this.nom_ecran_crud = nom_ecran_crud;
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
		return "ParametreSysteme_entete [id=" + id + ", nom=" + nom + ", libelle=" + libelle + ", nom_ecran_gestion="
				+ nom_ecran_gestion + ", nom_ecran_crud=" + nom_ecran_crud + ", usermodification="
				+ usermodification + ", datemodification=" + datemodification + ", pgmmodification=" + pgmmodification
				+ ", usercreation=" + usercreation + ", datecreation=" + datecreation + ", pgmcreation=" + pgmcreation
				+ "]";
	}

}
