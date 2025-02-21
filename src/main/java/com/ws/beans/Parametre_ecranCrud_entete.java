/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN TVA                                                ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class Parametre_ecranCrud_entete {
	private int id;
	private int parametreSysteme;
	private String nom_programme;
	public int largeur_ecran;
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
	public int getParametreSysteme() {
		return parametreSysteme;
	}
	public void setParametreSysteme(int parametreSysteme) {
		this.parametreSysteme = parametreSysteme;
	}
	public String getNom_programme() {
		return nom_programme;
	}
	public void setNom_programme(String nom_programme) {
		this.nom_programme = nom_programme;
	}
	public int getLargeur_ecran() {
		return largeur_ecran;
	}
	public void setLargeur_ecran(int largeur_ecran) {
		this.largeur_ecran = largeur_ecran;
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
		return "Parametre_ecranCrud_entete [id=" + id + ", parametreSysteme=" + parametreSysteme + ", nom_programme="
				+ nom_programme + ", largeur_ecran=" + largeur_ecran + ", usermodification=" + usermodification
				+ ", datemodification=" + datemodification + ", pgmmodification=" + pgmmodification + ", usercreation="
				+ usercreation + ", datecreation=" + datecreation + ", pgmcreation=" + pgmcreation
				 + "]";
	}



}
