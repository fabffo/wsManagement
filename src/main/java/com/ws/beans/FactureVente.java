/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN TVA                                                ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;
import java.util.List;

public class FactureVente {
	private int id;
	private String nom;
	private int maSociete;
	private int client;
	private int TypeFactureVente;
	private String document;
	private int tva;
	private String date_facture;
	private String date_echeance;
	private Double montant_ht;
	private Double montant_ttc;
	private Double montant_tva;
	private String raison_sociale_emetteur;
	private String adresse1_emetteur;
	private String adresse2_emetteur;
	private String adresse3_emetteur;
	private String siren_emetteur;
	private String conditions_paiement_emetteur;
	private String rib_emetteur;
	private String tva_intracomm_emetteur;
	private String raison_sociale_client;
	private String adresse1_client;
	private String adresse2_client;
	private String adresse3_client;
	private List<FactureVente_detail> lignes;
	private String usermodification;
    private Date datemodification;
    private String pgmmodification;
    private String usercreation;
    private Date datecreation;
    private String pgmcreation;
	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getMaSociete() {
		return maSociete;
	}
	public void setMaSociete(int maSociete) {
		this.maSociete = maSociete;
	}
	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}
	public int getTypeFactureVente() {
		return TypeFactureVente;
	}
	public void setTypeFactureVente(int typeFactureVente) {
		TypeFactureVente = typeFactureVente;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public int getTva() {
		return tva;
	}
	public void setTva(int tva) {
		this.tva = tva;
	}
	public String getDate_facture() {
		return date_facture;
	}
	public void setDate_facture(String date_facture) {
		this.date_facture = date_facture;
	}

	public String getDate_echeance() {
		return date_echeance;
	}

	public void setDate_echeance(String date_echeance) {
		this.date_echeance = date_echeance;
	}

	public Double getMontant_ht() {
		return montant_ht;
	}
	public void setMontant_ht(Double montant_ht) {
		this.montant_ht = montant_ht;
	}
	public Double getMontant_ttc() {
		return montant_ttc;
	}
	public void setMontant_ttc(Double montant_ttc) {
		this.montant_ttc = montant_ttc;
	}
	public Double getMontant_tva() {
		return montant_tva;
	}
	public void setMontant_tva(Double montant_tva) {
		this.montant_tva = montant_tva;
	}
	public String getRaison_sociale_emetteur() {
		return raison_sociale_emetteur;
	}
	public void setRaison_sociale_emetteur(String raison_sociale_emetteur) {
		this.raison_sociale_emetteur = raison_sociale_emetteur;
	}

	public String getAdresse1_emetteur() {
		return adresse1_emetteur;
	}

	public void setAdresse1_emetteur(String adresse1_emetteur) {
		this.adresse1_emetteur = adresse1_emetteur;
	}

	public String getAdresse2_emetteur() {
		return adresse2_emetteur;
	}

	public void setAdresse2_emetteur(String adresse2_emetteur) {
		this.adresse2_emetteur = adresse2_emetteur;
	}

	public String getAdresse3_emetteur() {
		return adresse3_emetteur;
	}

	public void setAdresse3_emetteur(String adresse3_emetteur) {
		this.adresse3_emetteur = adresse3_emetteur;
	}

	public String getSiren_emetteur() {
		return siren_emetteur;
	}

	public void setSiren_emetteur(String siren_emetteur) {
		this.siren_emetteur = siren_emetteur;
	}

	public String getConditions_paiement_emetteur() {
		return conditions_paiement_emetteur;
	}

	public void setConditions_paiement_emetteur(String conditions_paiement_emetteur) {
		this.conditions_paiement_emetteur = conditions_paiement_emetteur;
	}

	public String getRib_emetteur() {
		return rib_emetteur;
	}

	public void setRib_emetteur(String rib_emetteur) {
		this.rib_emetteur = rib_emetteur;
	}

	public String getTva_intracomm_emetteur() {
		return tva_intracomm_emetteur;
	}

	public void setTva_intracomm_emetteur(String tva_intracomm_emetteur) {
		this.tva_intracomm_emetteur = tva_intracomm_emetteur;
	}

	public String getRaison_sociale_client() {
		return raison_sociale_client;
	}

	public void setRaison_sociale_client(String raison_sociale_client) {
		this.raison_sociale_client = raison_sociale_client;
	}

	public String getAdresse1_client() {
		return adresse1_client;
	}

	public void setAdresse1_client(String adresse1_client) {
		this.adresse1_client = adresse1_client;
	}

	public String getAdresse2_client() {
		return adresse2_client;
	}

	public void setAdresse2_client(String adresse2_client) {
		this.adresse2_client = adresse2_client;
	}

	public String getAdresse3_client() {
		return adresse3_client;
	}

	public void setAdresse3_client(String adresse3_client) {
		this.adresse3_client = adresse3_client;
	}
	public List<FactureVente_detail> getLignes() {
		return lignes;
		}
	public void setLignes(List<FactureVente_detail> lignes) {
		this.lignes = lignes;
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
		return "FactureVente [id=" + id + ", nom=" + nom + ", maSociete=" + maSociete + ", client=" + client
				+ ", TypeFactureVente=" + TypeFactureVente + ", document=" + document + ", tva=" + tva
				+ ", date_facture=" + date_facture + ", date_echeance=" + date_echeance + ", montant_ht=" + montant_ht
				+ ", montant_ttc=" + montant_ttc + ", montant_tva=" + montant_tva + ", raison_sociale_emetteur="
				+ raison_sociale_emetteur + ", adresse1_emetteur=" + adresse1_emetteur + ", adresse2_emetteur="
				+ adresse2_emetteur + ", adresse3_emetteur=" + adresse3_emetteur + ", siren_emetteur=" + siren_emetteur
				+ ", conditions_paiement_emetteur=" + conditions_paiement_emetteur + ", rib_emetteur=" + rib_emetteur
				+ ", tva_intracomm_emetteur=" + tva_intracomm_emetteur + ", raison_sociale_client="
				+ raison_sociale_client + ", adresse1_client=" + adresse1_client + ", adresse2_client="
				+ adresse2_client + ", adresse3_client=" + adresse3_client + ", usermodification=" + usermodification
				+ ", datemodification=" + datemodification + ", pgmmodification=" + pgmmodification + ", usercreation="
				+ usercreation + ", datecreation=" + datecreation + ", pgmcreation=" + pgmcreation + "]";
	}

}
