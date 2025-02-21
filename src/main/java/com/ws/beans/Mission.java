/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN COLLABORATEUR                                      ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class Mission {

	private int id;
	private String nom;
	private String complement;
	private String statut;
	private int id_contrat;
	private int version_contrat;
	private String code_tva;
	private float prix_ht;
	private int nbr_facture;
	private String type_prix;
	private String societe;
	private String type_entite;
	private String type_contrat;
	private String date_demarrage;
	private String commentaire;
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
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public int getId_contrat() {
		return id_contrat;
	}
	public void setId_contrat(int id_contrat) {
		this.id_contrat = id_contrat;
	}
	public int getVersion_contrat() {
		return version_contrat;
	}
	public void setVersion_contrat(int version_contrat) {
		this.version_contrat = version_contrat;
	}
	public String getCode_tva() {
		return code_tva;
	}
	public void setCode_tva(String code_tva) {
		this.code_tva = code_tva;
	}
	public float getPrix_ht() {
		return prix_ht;
	}
	public void setPrix_ht(float prix_ht) {
		this.prix_ht = prix_ht;
	}
	public int getNbr_facture() {
		return nbr_facture;
	}
	public void setNbr_facture(int nbr_facture) {
		this.nbr_facture = nbr_facture;
	}
	public String getType_prix() {
		return type_prix;
	}
	public void setType_prix(String type_prix) {
		this.type_prix = type_prix;
	}
	public String getSociete() {
		return societe;
	}
	public void setSociete(String societe) {
		this.societe = societe;
	}
	public String getType_entite() {
		return type_entite;
	}
	public void setType_entite(String type_entite) {
		this.type_entite = type_entite;
	}
	public String getType_contrat() {
		return type_contrat;
	}
	public void setType_contrat(String type_contrat) {
		this.type_contrat = type_contrat;
	}
	public String getDate_demarrage() {
		return date_demarrage;
	}
	public void setDate_demarrage(String date_demarrage) {
		this.date_demarrage = date_demarrage;
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
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	@Override
	public String toString() {
		return "Mission [id=" + id + ", nom=" + nom + ", complement=" + complement + ", statut=" + statut
				+ ", id_contrat=" + id_contrat + ", version_contrat=" + version_contrat + ", code_tva=" + code_tva
				+ ", prix_ht=" + prix_ht + ", nbr_facture=" + nbr_facture + ", type_prix=" + type_prix + ", societe="
				+ societe + ", type_entite=" + type_entite + ", type_contrat=" + type_contrat + ", date_demarrage="
				+ date_demarrage + ", commentaire=" + commentaire + ", usermodification=" + usermodification
				+ ", datemodification=" + datemodification + ", pgmmodification=" + pgmmodification + ", usercreation="
				+ usercreation + ", datecreation=" + datecreation + ", pgmcreation=" + pgmcreation + "]";
	}

}
