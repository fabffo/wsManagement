package com.ws.beans;

public class ZoneDeRecherche {
    private String nom;
    private String valeur;
    private String selected;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getValeur() {
		return valeur;
	}
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	@Override
	public String toString() {
		return "ZoneDeRecherche [nom=" + nom + ", valeur=" + valeur + ", selected=" + selected + "]";
	}


}

