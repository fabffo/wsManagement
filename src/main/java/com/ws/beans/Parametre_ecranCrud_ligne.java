/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN TVA                                                ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class Parametre_ecranCrud_ligne {
	private int id;
	private int parametreSysteme;
	private String nom_programme;
	private int numero_ligne;
	private int numero_champ;
	private String nom_champ;
	private String required;
	private String readonly;
	private String disabled;
	private String hidden;
	public int minlength;
	public int maxlength;
	private String type;
	private String step;
	private String placeholder;
	private String type_zone;
	public int largeur_libelle;
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
	public int getNumero_ligne() {
		return numero_ligne;
	}
	public void setNumero_ligne(int numero_ligne) {
		this.numero_ligne = numero_ligne;
	}
	public int getNumero_champ() {
		return numero_champ;
	}
	public void setNumero_champ(int numero_champ) {
		this.numero_champ = numero_champ;
	}
	public String getNom_champ() {
		return nom_champ;
	}
	public void setNom_champ(String nom_champ) {
		this.nom_champ = nom_champ;
	}
	public String getRequired() {
		return required;
	}
	public void setRequired(String required) {
		this.required = required;
	}
	public String getReadonly() {
		return readonly;
	}
	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	public String getHidden() {
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public int getMinlength() {
		return minlength;
	}
	public void setMinlength(int minlength) {
		this.minlength = minlength;
	}
	public int getMaxlength() {
		return maxlength;
	}
	public void setMaxlength(int maxlength) {
		this.maxlength = maxlength;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getPlaceholder() {
		return placeholder;
	}
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	public String getType_zone() {
		return type_zone;
	}
	public void setType_zone(String type_zone) {
		this.type_zone = type_zone;
	}
	public int getLargeur_libelle() {
		return largeur_libelle;
	}
	public void setLargeur_libelle(int largeur_libelle) {
		this.largeur_libelle = largeur_libelle;
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
		return "Parametre_ecranCrud_ligne [id=" + id + ", parametreSysteme=" + parametreSysteme + ", nom_programme="
				+ nom_programme + ", numero_ligne=" + numero_ligne + ", numero_champ=" + numero_champ + ", nom_champ="
				+ nom_champ + ", required=" + required + ", readonly=" + readonly + ", disabled=" + disabled
				+ ", hidden=" + hidden + ", minlength=" + minlength + ", maxlength=" + maxlength + ", type=" + type
				+ ", step=" + step + ", placeholder=" + placeholder + ", type_zone=" + type_zone + ", largeur_libelle="
				+ largeur_libelle + ", usermodification=" + usermodification + ", datemodification=" + datemodification
				+ ", pgmmodification=" + pgmmodification + ", usercreation=" + usercreation + ", datecreation="
				+ datecreation + ", pgmcreation=" + pgmcreation + "]";
	}

	}
