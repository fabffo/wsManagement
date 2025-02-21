/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN SOCIETE                                        ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class Organisation {

	private int id;
	private int entite;
	private String raison_sociale;
	private String adresse;
	private String code_postal;
	private String ville;
	private String pays;
	private String siren;
	private String siret;
	private String code_naf;
	private int tva;
	private int metier;
	private String telephone;
	private String email;
	private int  relation;
	private String usermodification;
    private Date datemodification;
    private String pgmmodification;
    private String usercreation;
    private Date datecreation;
    private String pgmcreation;
    private String nom;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEntite() {
		return entite;
	}
	public void setEntite(int entite) {
		this.entite = entite;
	}
	public String getRaison_sociale() {
		return raison_sociale;
	}
	public String getNom() {
		return raison_sociale;
	}
	public void setRaison_sociale(String raison_sociale) {
		this.raison_sociale = raison_sociale;
	}
	public void setNom(String nom) {
		this.nom = nom;
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
	public String getSiren() {
		return siren;
	}
	public void setSiren(String siren) {
		this.siren = siren;
	}
	public String getSiret() {
		return siret;
	}
	public void setSiret(String siret) {
		this.siret = siret;
	}
	public String getCode_naf() {
		return code_naf;
	}
	public void setCode_naf(String code_naf) {
		this.code_naf = code_naf;
	}
	public int getTva() {
		return tva;
	}
	public void setTva(int tva) {
		this.tva = tva;
	}
	public int getMetier() {
		return metier;
	}
	public void setMetier(int metier) {
		this.metier = metier;
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
	public int getRelation() {
		return relation;
	}
	public void setRelation(int relation) {
		this.relation = relation;
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
		return "Organisation [id=" + id + ", entite=" + entite + ", raison_sociale=" + raison_sociale + ", adresse="
				+ adresse + ", code_postal=" + code_postal + ", ville=" + ville + ", pays=" + pays + ", siren=" + siren
				+ ", siret=" + siret + ", code_naf=" + code_naf + ", tva=" + tva + ", metier=" + metier + ", telephone="
				+ telephone + ", email=" + email + ", relation=" + relation + ", usermodification=" + usermodification
				+ ", datemodification=" + datemodification + ", pgmmodification=" + pgmmodification + ", usercreation="
				+ usercreation + ", datecreation=" + datecreation + ", pgmcreation=" + pgmcreation + "]";
	}
}
