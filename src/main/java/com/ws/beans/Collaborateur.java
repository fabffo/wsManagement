/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN COLLABORATEUR                                      ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class Collaborateur {

	private int id;
	private String nom;
	private String prenom;
	private String metier;
	private int metier_principal;
	private String type_collaborateur;
	private String adresse;
	private String code_postal;
	private String ville;
	private String pays;
	private String telephone;
	private String email;
	private String civilite;
	private String date_naissance;
	private String nationalite;
	private String type_contrat;
	private int id_type_contrat;
	private int signataire_contrat;
	private int id_utilisateur;
	private String utilisateur;
	private String telephone_secondaire;
	private String email_secondaire;
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
	public String getMetier() {
		return metier;
	}
	public void setMetier(String metier) {
		this.metier = metier;
	}
	public int getMetier_principal() {
		return metier_principal;
	}
	public void setMetier_principal(int metier_principal) {
		this.metier_principal = metier_principal;
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
	
	public String getType_collaborateur() {
		return type_collaborateur;
	}
	public void setType_collaborateur(String type_collaborateur) {
		this.type_collaborateur = type_collaborateur;
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
	public String getCivilite() {
		return civilite;
	}
	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}
	public String getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}
	public String getNationalite() {
		return nationalite;
	}
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}
	public String getType_contrat() {
		return type_contrat;
	}
	public void setType_contrat(String type_contrat) {
		this.type_contrat = type_contrat;
	}
	public int getSignataire_contrat() {
		return signataire_contrat;
	}
	public void setSignataire_contrat(int signataire_contrat) {
		this.signataire_contrat = signataire_contrat;
	}
	public String getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
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
	public int getId_utilisateur() {
		return id_utilisateur;
	}
	public void setId_utilisateur(int id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
	
	public int getId_type_contrat() {
		return id_type_contrat;
	}
	public void setId_type_contrat(int id_type_contrat) {
		this.id_type_contrat = id_type_contrat;
	}
	@Override
	public String toString() {
		return "Collaborateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", metier=" + metier
				+ ", metier_principal=" + metier_principal + ", type_collaborateur=" + type_collaborateur
				+ ", adresse=" + adresse + ", code_postal=" + code_postal + ", ville=" + ville + ", pays=" + pays
				+ ", telephone=" + telephone + ", email=" + email + ", civilite=" + civilite + ", date_naissance="
				+ date_naissance + ", nationalite=" + nationalite + ", type_contrat=" + type_contrat
				+ ", id_type_contrat=" + id_type_contrat + ", signataire_contrat=" + signataire_contrat
				+ ", id_utilisateur=" + id_utilisateur + ", utilisateur=" + utilisateur
				+ ", telephone_secondaire=" + telephone_secondaire + ", email_secondaire=" + email_secondaire
				+ ", usermodification=" + usermodification + ", datemodification=" + datemodification
				+ ", pgmmodification=" + pgmmodification + ", usercreation=" + usercreation + ", datecreation="
				+ datecreation + ", pgmcreation=" + pgmcreation + "]";
	}
	
	
}
