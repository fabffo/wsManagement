/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN COLLABORATEUR                                      ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class Contrat {

	private int id;
	private int version;
	private String type_entite;
	private String statut;
	private String document;
	private String cheminAbsolu;
	private String cheminRelatif;
	private String nom_contrat;
	private String type_contrat;
	private int id_referent_collaborateur;
	private String collaborateur;
	private int id_client;
	private String client;
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
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getCheminAbsolu() {
		return cheminAbsolu;
	}
	public void setCheminAbsolu(String cheminAbsolu) {
		this.cheminAbsolu = cheminAbsolu;
	}
	public String getCheminRelatif() {
		return cheminRelatif;
	}
	public void setCheminRelatif(String cheminRelatif) {
		this.cheminRelatif = cheminRelatif;
	}
	public String getNom_contrat() {
		return nom_contrat;
	}
	public void setNom_contrat(String nom_contrat) {
		this.nom_contrat = nom_contrat;
	}
	public String getType_contrat() {
		return type_contrat;
	}
	public void setType_contrat(String type_contrat) {
		this.type_contrat = type_contrat;
	}
	public int getId_referent_collaborateur() {
		return id_referent_collaborateur;
	}
	public void setId_referent_collaborateur(int id_referent_collaborateur) {
		this.id_referent_collaborateur = id_referent_collaborateur;
	}
	public String getCollaborateur() {
		return collaborateur;
	}
	public void setCollaborateur(String collaborateur) {
		this.collaborateur = collaborateur;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getDate_demarrage() {
		return date_demarrage;
	}
	public void setDate_demarrage(String date_demarrage) {
		this.date_demarrage = date_demarrage;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
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

	public String getType_entite() {
		return type_entite;
	}
	public void setType_entite(String type_entite) {
		this.type_entite = type_entite;
	}
	@Override
	public String toString() {
		return "Contrat [id=" + id + ", version=" + version + ", type_entite=" + type_entite + ", statut=" + statut
				+ ", document=" + document + ", cheminAbsolu=" + cheminAbsolu + ", cheminRelatif=" + cheminRelatif
				+ ", nom_contrat=" + nom_contrat + ", type_contrat=" + type_contrat + ", id_referent_collaborateur="
				+ id_referent_collaborateur + ", collaborateur=" + collaborateur + ", id_client=" + id_client
				+ ", client=" + client + ", date_demarrage=" + date_demarrage + ", commentaire=" + commentaire
				+ ", usermodification=" + usermodification + ", datemodification=" + datemodification
				+ ", pgmmodification=" + pgmmodification + ", usercreation=" + usercreation + ", datecreation="
				+ datecreation + ", pgmcreation=" + pgmcreation + "]";
	}


}
