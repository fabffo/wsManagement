/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN COLLABORATEUR                                      ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class Personnel {

	private int id;
	private String civilite;
	private String nom;
	private String prenom;
	private String adresse;
	private String code_postal;
	private String ville;
	private String pays;
	private String telephone;
	private String email;
	private String telephone_secondaire;
	private String email_secondaire;
	private String date_naissance;
	private int nationalite;
	private int activite;
	private int entite;
	private int signataire;
	private int utilisateur;
	private int metier;
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
	public String getCivilite() {
		return civilite;
	}
	public void setCivilite(String civilite) {
		this.civilite = civilite;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone_secondaire() {
		return telephone_secondaire;
	}
	public void setTelephone_secondaire(String telephone_secondaire) {
		this.telephone_secondaire = telephone_secondaire;
	}
	public String getEmail_secondaire() {
		return email_secondaire;
	}
	public void setEmail_secondaire(String email_secondaire) {
		this.email_secondaire = email_secondaire;
	}
	public String getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}
	public int getNationalite() {
		return nationalite;
	}
	public void setNationalite(int nationalite) {
		this.nationalite = nationalite;
	}
	public int getActivite() {
		return activite;
	}
	public void setActivite(int activite) {
		this.activite = activite;
	}
	public int getEntite() {
		return entite;
	}
	public void setEntite(int entite) {
		this.entite = entite;
	}
	public int getSignataire() {
		return signataire;
	}
	public void setSignataire(int signataire) {
		this.signataire = signataire;
	}
	public int getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(int utilisateur) {
		this.utilisateur = utilisateur;
	}
	public int getMetier() {
		return metier;
	}
	public void setMetier(int metier) {
		this.metier = metier;
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
		return "Personnel [id=" + id + ", civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + ", adresse="
				+ adresse + ", code_postal=" + code_postal + ", ville=" + ville + ", pays=" + pays + ", telephone="
				+ telephone + ", email=" + email + ", telephone_secondaire=" + telephone_secondaire
				+ ", email_secondaire=" + email_secondaire + ", date_naissance=" + date_naissance + ", nationalite="
				+ nationalite + ", activite=" + activite + ", entite=" + entite + ", signataire=" + signataire
				+ ", utilisateur=" + utilisateur + ", metier=" + metier + ", usermodification=" + usermodification
				+ ", datemodification=" + datemodification + ", pgmmodification=" + pgmmodification + ", usercreation="
				+ usercreation + ", datecreation=" + datecreation + ", pgmcreation=" + pgmcreation + "]";
	}

}
