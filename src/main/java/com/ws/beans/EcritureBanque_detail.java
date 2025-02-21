/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN TVA                                                ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class EcritureBanque_detail {

	private int id;
	private int numero_ligne;
	private Date date_ecriture;
	private String libelle_ecriture;
	private float debit;
	private float credit;
	private String nom_entite;
	private String entite;
	private String categorie;
	private String super_categorie;
	private String statut_rapproche;
	private String libelle_rapproche;
	private Date date_rapproche;
	private String signe;
	private float taux_tva;
	private float montant_tva;
	private float montant_ht;
	private float montant_ttc;
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
	public int getNumero_ligne() {
		return numero_ligne;
	}
	public void setNumero_ligne(int numero_ligne) {
		this.numero_ligne = numero_ligne;
	}
	public Date getDate_ecriture() {
		return date_ecriture;
	}
	public void setDate_ecriture(Date date_ecriture) {
		this.date_ecriture = date_ecriture;
	}
	public String getLibelle_ecriture() {
		return libelle_ecriture;
	}
	public void setLibelle_ecriture(String libelle_ecriture) {
		this.libelle_ecriture = libelle_ecriture;
	}
	public float getDebit() {
		return debit;
	}
	public void setDebit(float debit) {
		this.debit = debit;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	public String getNom_entite() {
		return nom_entite;
	}
	public void setNom_entite(String nom_entite) {
		this.nom_entite = nom_entite;
	}
	public String getEntite() {
		return entite;
	}
	public void setEntite(String entite) {
		this.entite = entite;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getSuper_categorie() {
		return super_categorie;
	}
	public void setSuper_categorie(String super_categorie) {
		this.super_categorie = super_categorie;
	}
	public String getStatut_rapproche() {
		return statut_rapproche;
	}
	public void setStatut_rapproche(String statut_rapproche) {
		this.statut_rapproche = statut_rapproche;
	}
	public String getLibelle_rapproche() {
		return libelle_rapproche;
	}
	public void setLibelle_rapproche(String libelle_rapproche) {
		this.libelle_rapproche = libelle_rapproche;
	}
	public Date getDate_rapproche() {
		return date_rapproche;
	}
	public void setDate_rapproche(Date date_rapproche) {
		this.date_rapproche = date_rapproche;
	}
	public String getSigne() {
		return signe;
	}
	public void setSigne(String signe) {
		this.signe = signe;
	}
	public float getTaux_tva() {
		return taux_tva;
	}
	public void setTaux_tva(float taux_tva) {
		this.taux_tva = taux_tva;
	}
	public float getMontant_tva() {
		return montant_tva;
	}
	public void setMontant_tva(float montant_tva) {
		this.montant_tva = montant_tva;
	}
	public float getMontant_ht() {
		return montant_ht;
	}
	public void setMontant_ht(float montant_ht) {
		this.montant_ht = montant_ht;
	}
	public float getMontant_ttc() {
		return montant_ttc;
	}
	public void setMontant_ttc(float montant_ttc) {
		this.montant_ttc = montant_ttc;
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
		return "EcritureBanque_detail [id=" + id + ", numero_ligne=" + numero_ligne + ", date_ecriture=" + date_ecriture
				+ ", libelle_ecriture=" + libelle_ecriture + ", debit=" + debit + ", credit=" + credit + ", nom_entite="
				+ nom_entite + ", entite=" + entite + ", categorie=" + categorie + ", super_categorie="
				+ super_categorie + ", statut_rapproche=" + statut_rapproche + ", libelle_rapproche="
				+ libelle_rapproche + ", date_rapproche=" + date_rapproche + ", signe=" + signe
				+ ", taux_tva=" + taux_tva + ", montant_tva=" + montant_tva + ", montant_ht=" + montant_ht
				+ ", montant_ttc=" + montant_ttc + ", usermodification=" + usermodification + ", datemodification="
				+ datemodification + ", pgmmodification=" + pgmmodification + ", usercreation=" + usercreation
				+ ", datecreation=" + datecreation + ", pgmcreation=" + pgmcreation + "]";
	}


}
