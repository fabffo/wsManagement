/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN TVA                                                ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class Parametre_detail {

	private int id;
	private int id_entete;
	private int ligne;
	private String type;
	private String nom;
	private String zone;
	private String colonne;
	private String ecranGestion_largeur;
	private String ecranGestion_recherche;
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
	public int getId_entete() {
		return id_entete;
	}
	public void setId_entete(int id_entete) {
		this.id_entete = id_entete;
	}
	public int getLigne() {
		return ligne;
	}
	public void setLigne(int ligne) {
		this.ligne = ligne;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getColonne() {
		return colonne;
	}
	public void setColonne(String colonne) {
		this.colonne = colonne;
	}
	public String getEcranGestion_largeur() {
		return ecranGestion_largeur;
	}
	public void setEcranGestion_largeur(String ecranGestion_largeur) {
		this.ecranGestion_largeur = ecranGestion_largeur;
	}
	public String getEcranGestion_recherche() {
		return ecranGestion_recherche;
	}
	public void setEcranGestion_recherche(String ecranGestion_recherche) {
		this.ecranGestion_recherche = ecranGestion_recherche;
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
		return "Parametre_detail [id=" + id + ", id_entete=" + id_entete + ", ligne=" + ligne + ", type=" + type
				+ ", nom=" + nom + ", zone=" + zone + ", colonne=" + colonne + ", ecranGestion_largeur="
				+ ecranGestion_largeur + ", ecranGestion_recherche=" + ecranGestion_recherche + ", usermodification="
				+ usermodification + ", datemodification=" + datemodification + ", pgmmodification=" + pgmmodification
				+ ", usercreation=" + usercreation + ", datecreation=" + datecreation + ", pgmcreation=" + pgmcreation
				+ "]";
	}



}
