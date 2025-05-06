/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN COLLABORATEUR                                      ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class Mission {
	private int id;
	private int accordOrganisation;
	private String nom;
	private int statut;
	private float prix_ht;
	private int tva;
	private int typePrix;
	private String date_demarrage;
	private String date_fin;
	private String commentaire;
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
	public int getAccordOrganisation() {
		return accordOrganisation;
	}
	public void setAccordOrganisation(int accordOrganisation) {
		this.accordOrganisation = accordOrganisation;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getStatut() {
		return statut;
	}
	public void setStatut(int statut) {
		this.statut = statut;
	}
	public float getPrix_ht() {
		return prix_ht;
	}
	public void setPrix_ht(float prix_ht) {
		this.prix_ht = prix_ht;
	}
	public int getTva() {
		return tva;
	}
	public void setTva(int tva) {
		this.tva = tva;
	}
	public int getTypePrix() {
		return typePrix;
	}
	public void setTypePrix(int typePrix) {
		this.typePrix = typePrix;
	}
	public String getDate_demarrage() {
		return date_demarrage;
	}
	public void setDate_demarrage(String date_demarrage) {
		this.date_demarrage = date_demarrage;
	}
	public String getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(String date_fin) {
		this.date_fin = date_fin;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
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
		return "Mission [id=" + id + ", accordOrganisation=" + accordOrganisation + ", nom=" + nom + ", statut="
				+ statut + ", prix_ht=" + prix_ht + ", tva=" + tva + ", typePrix=" + typePrix + ", date_demarrage="
				+ date_demarrage + ", date_fin=" + date_fin + ", commentaire=" + commentaire + ", usermodification="
				+ usermodification + ", datemodification=" + datemodification + ", pgmmodification=" + pgmmodification
				+ ", usercreation=" + usercreation + ", datecreation=" + datecreation + ", pgmcreation=" + pgmcreation
				+ "]";
	}

}
