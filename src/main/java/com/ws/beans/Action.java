/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN CONTACT                                            ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class Action {

	private int id;
	private String nom_programme;
	private String action;
	private String disponible;
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
	public String getNom_programme() {
		return nom_programme;
	}
	public void setNom_programme(String nom_programme) {
		this.nom_programme = nom_programme;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDisponible() {
		return disponible;
	}
	public void setDisponible(String disponible) {
		this.disponible = disponible;
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
		return "Actions_disponibles [id=" + id + ", nom_programme=" + nom_programme + ", action=" + action
				+ ", disponible=" + disponible + ", usermodification=" + usermodification + ", datemodification="
				+ datemodification + ", pgmmodification=" + pgmmodification + ", usercreation=" + usercreation
				+ ", datecreation=" + datecreation + ", pgmcreation=" + pgmcreation + "]";
	}

}
