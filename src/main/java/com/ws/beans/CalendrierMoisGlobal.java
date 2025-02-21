/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN CALENDRIER MOIS GLOBAL                             ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

public class CalendrierMoisGlobal {

	private int jours_ouvres;
	private int jours_non_ouvres;
	private int jours_feries;
	public int getJours_ouvres() {
		return jours_ouvres;
	}
	public void setJours_ouvres(int jours_ouvres) {
		this.jours_ouvres = jours_ouvres;
	}
	public int getJours_non_ouvres() {
		return jours_non_ouvres;
	}
	public void setJours_non_ouvres(int jours_non_ouvres) {
		this.jours_non_ouvres = jours_non_ouvres;
	}
	public int getJours_feries() {
		return jours_feries;
	}
	public void setJours_feries(int jours_feries) {
		this.jours_feries = jours_feries;
	}
	@Override
	public String toString() {
		return "CalendrierMoisGlobal [jours_ouvres=" + jours_ouvres + ", jours_non_ouvres=" + jours_non_ouvres
				+ ", jours_feries=" + jours_feries + ", getJours_ouvres()=" + getJours_ouvres()
				+ ", getJours_non_ouvres()=" + getJours_non_ouvres() + ", getJours_feries()=" + getJours_feries()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
	
}
