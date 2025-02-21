/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME BEAN TVA                                                ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.beans;

import java.util.Date;

public class Parametre_ecranGestion_ligne {

		private int id;
		private int parametreSysteme;
		private int numero_ligne;
		private int numero_champ;
		private String nom_zone;
		public int largeur_colonne;
	    private String type_colonne;
		public String zone_recherche;
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
		public String getNom_zone() {
			return nom_zone;
		}
		public void setNom_zone(String nom_zone) {
			this.nom_zone = nom_zone;
		}
		public int getLargeur_colonne() {
			return largeur_colonne;
		}
		public void setLargeur_colonne(int largeur_colonne) {
			this.largeur_colonne = largeur_colonne;
		}
		public String getZone_recherche() {
			return zone_recherche;
		}
		public void setZone_recherche(String zone_recherche) {
			this.zone_recherche = zone_recherche;
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
		public String getType_colonne() {
			return type_colonne;
		}
		public void setType_colonne(String type_colonne) {
			this.type_colonne = type_colonne;
		}
		@Override
		public String toString() {
			return "Parametre_ecranGestion_ligne [id=" + id + ", parametreSysteme=" + parametreSysteme
					+ ", numero_ligne=" + numero_ligne + ", numero_champ=" + numero_champ + ", nom_zone=" + nom_zone
					+ ", largeur_colonne=" + largeur_colonne + ", zone_recherche=" + zone_recherche
					+ ", usermodification=" + usermodification + ", datemodification=" + datemodification
					+ ", pgmmodification=" + pgmmodification + ", usercreation=" + usercreation + ", datecreation="
					+ datecreation + ", pgmcreation=" + pgmcreation + ", type_colonne="
					+ type_colonne + "]";
		}


}
