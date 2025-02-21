/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN SOCIETE                                        ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class Societe {

	private int id;
	private String type;
	private String raison_sociale;
	private String adresse;
	private String code_postal;
	private String ville;
	private String pays;
	private String siren;
	private String siret;
	private String code_naf;
	private String code_tva;
	private String metier;
	private int id_contact_principal;
	private String contact_principal;
	private String email;
	private String telephone;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRaison_sociale() {
		return raison_sociale;
	}
	public void setRaison_sociale(String raison_sociale) {
		this.raison_sociale = raison_sociale;
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
	public String getCode_tva() {
		return code_tva;
	}
	public void setCode_tva(String code_tva) {
		this.code_tva = code_tva;
	}
	public String getMetier() {
		return metier;
	}
	public void setMetier(String metier) {
		this.metier = metier;
	}
	public int getId_contact_principal() {
		return id_contact_principal;
	}
	public void setId_contact_principal(int id_contact_principal) {
		this.id_contact_principal = id_contact_principal;
	}
	public String getContact_principal() {
		return contact_principal;
	}
	public void setContact_principal(String contact_principal) {
		this.contact_principal = contact_principal;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
		return "Societe [id=" + id + ", type=" + type + ", raison_sociale=" + raison_sociale + ", adresse=" + adresse
				+ ", code_postal=" + code_postal + ", ville=" + ville + ", pays=" + pays + ", siren=" + siren
				+ ", siret=" + siret + ", code_naf=" + code_naf + ", code_tva=" + code_tva + ", metier=" + metier
				+ ", id_contact_principal=" + id_contact_principal + ", contact_principal=" + contact_principal
				+ ", email=" + email + ", telephone=" + telephone + ", usermodification=" + usermodification
				+ ", datemodification=" + datemodification + ", pgmmodification=" + pgmmodification + ", usercreation="
				+ usercreation + ", datecreation=" + datecreation + ", pgmcreation=" + pgmcreation + ", getId()="
				+ getId() + ", getType()=" + getType() + ", getRaison_sociale()=" + getRaison_sociale()
				+ ", getAdresse()=" + getAdresse() + ", getCode_postal()=" + getCode_postal() + ", getVille()="
				+ getVille() + ", getPays()=" + getPays() + ", getSiren()=" + getSiren() + ", getSiret()=" + getSiret()
				+ ", getCode_naf()=" + getCode_naf() + ", getCode_tva()=" + getCode_tva() + ", getMetier()="
				+ getMetier() + ", getId_contact_principal()=" + getId_contact_principal() + ", getContact_principal()="
				+ getContact_principal() + ", getEmail()=" + getEmail() + ", getTelephone()=" + getTelephone()
				+ ", getUsermodification()=" + getUsermodification() + ", getDatemodification()="
				+ getDatemodification() + ", getPgmmodification()=" + getPgmmodification() + ", getUsercreation()="
				+ getUsercreation() + ", getDatecreation()=" + getDatecreation() + ", getPgmcreation()="
				+ getPgmcreation() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
    
    
}
