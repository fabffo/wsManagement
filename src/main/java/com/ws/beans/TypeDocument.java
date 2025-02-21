/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN TVA                                                ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class TypeDocument {

	private int id;
	private String cheminRelatif;
	private String contrat;
	private String cheminAbsolu;
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
	public String getCheminRelatif() {
		return cheminRelatif;
	}
	public void setCheminRelatif(String cheminRelatif) {
		this.cheminRelatif = cheminRelatif;
	}
	public String getContrat() {
		return contrat;
	}
	public void setContrat(String contrat) {
		this.contrat = contrat;
	}
	public String getCheminAbsolu() {
		return cheminAbsolu;
	}
	public void setCheminAbsolu(String cheminAbsolu) {
		this.cheminAbsolu = cheminAbsolu;
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
		return "Tva [id=" + id + ", cheminRelatif=" + cheminRelatif + ", contrat=" + contrat + ", cheminAbsolu=" + cheminAbsolu + ", usermodification="
				+ usermodification + ", datemodification=" + datemodification + ", pgmmodification=" + pgmmodification
				+ ", usercreation=" + usercreation + ", datecreation=" + datecreation + ", pgmcreation=" + pgmcreation
				+ "]";
	}
	
}
