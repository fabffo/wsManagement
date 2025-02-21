/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN CONTACT                                            ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class Contact {

	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String code_postal;
	private String ville;
	private String pays;
	private String telephone;
	private String telephone_secondaire;
	private String email;
	private String email_secondaire;
	private String civilite;
	private int id_societe;
	private String societe;
	private String fonction;
	private String resume_contact;
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getTelephone_secondaire() {
		return telephone_secondaire;
	}
	public void setTelephone_secondaire(String telephone_secondaire) {
		this.telephone_secondaire = telephone_secondaire;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail_secondaire() {
		return email_secondaire;
	}
	public void setEmail_secondaire(String email_secondaire) {
		this.email_secondaire = email_secondaire;
	}
	public String getCivilite() {
		return civilite;
	}
	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}
	public int getId_societe() {
		return id_societe;
	}
	public void setId_societe(int id_societe) {
		this.id_societe = id_societe;
	}
	public String getSociete() {
		return societe;
	}
	public void setSociete(String societe) {
		this.societe = societe;
	}
	public String getFonction() {
		return fonction;
	}
	public String getResume_contact() {
		return resume_contact;
	}
	public void setResume_contact(String resume_contact) {
		this.resume_contact = resume_contact;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
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
		return "Contact [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", code_postal="
				+ code_postal + ", ville=" + ville + ", pays=" + pays + ", telephone=" + telephone
				+ ", telephone_secondaire=" + telephone_secondaire + ", email=" + email + ", email_secondaire="
				+ email_secondaire + ", civilite=" + civilite + ", id_societe=" + id_societe + ", societe=" + societe
				+ ", fonction=" + fonction + ", resume_contact=" + resume_contact + ", usermodification="
				+ usermodification + ", datemodification=" + datemodification + ", pgmmodification=" + pgmmodification
				+ ", usercreation=" + usercreation + ", datecreation=" + datecreation + ", pgmcreation=" + pgmcreation
				+ "]";
	}
	
    
}
