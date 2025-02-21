/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN TYPE CONTRAT                                       ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class TypeContrat {

	private int id;
	private String nom;
	private String libelle;
	private int entite;
	private int activite;
	private String cheminRelatif;
	private String cheminAbsolu;
	private int typeIntervenant;
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
	public int getEntite() {
		return entite;
	}
	public void setEntite(int entite) {
		this.entite = entite;
	}
	public int getActivite() {
		return activite;
	}
	public void setActivite(int activite) {
		this.activite = activite;
	}
	public String getCheminRelatif() {
		return cheminRelatif;
	}
	public void setCheminRelatif(String cheminRelatif) {
		this.cheminRelatif = cheminRelatif;
	}
	public String getCheminAbsolu() {
		return cheminAbsolu;
	}
	public void setCheminAbsolu(String cheminAbsolu) {
		this.cheminAbsolu = cheminAbsolu;
	}
	public int getTypeIntervenant() {
		return typeIntervenant;
	}
	public void setTypeIntervenant(int typeIntervenant) {
		this.typeIntervenant = typeIntervenant;
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
		return "TypeContrat [id=" + id + ", nom=" + nom + ", libelle=" + libelle + ", entite=" + entite + ", activite="
				+ activite + ", cheminRelatif=" + cheminRelatif + ", cheminAbsolu=" + cheminAbsolu
				+ ", typeIntervenant=" + typeIntervenant + ", usermodification=" + usermodification
				+ ", datemodification=" + datemodification + ", pgmmodification=" + pgmmodification + ", usercreation="
				+ usercreation + ", datecreation=" + datecreation + ", pgmcreation=" + pgmcreation + "]";
	}

}
