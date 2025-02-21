/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN TVA                                                ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class EcranGestion_tableLigneTh {

	private String largeur_colonne;
	private String ref;
	private String zone_i;
	private String nom_colonne;

	public String getLargeur_colonne() {
		return largeur_colonne;
	}
	public void setLargeur_colonne(String largeur_colonne) {
		this.largeur_colonne = largeur_colonne;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getZone_i() {
		return zone_i;
	}
	public void setZone_i(String zone_i) {
		this.zone_i = zone_i;
	}
	public String getNom_colonne() {
		return nom_colonne;
	}
	public void setNom_colonne(String nom_colonne) {
		this.nom_colonne = nom_colonne;
	}



}
