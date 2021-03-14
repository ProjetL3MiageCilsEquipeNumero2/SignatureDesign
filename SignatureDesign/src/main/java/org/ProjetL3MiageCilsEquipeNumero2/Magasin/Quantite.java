package org.ProjetL3MiageCilsEquipeNumero2.Magasin;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Quantite {
	private SimpleStringProperty taille;
	private SimpleStringProperty couleur;
	private SimpleIntegerProperty quantite;
	
	public Quantite(String taille, String couleur, int quantite) {
		this.taille = new SimpleStringProperty(taille);
		this.couleur = new SimpleStringProperty(couleur);
		this.quantite = new SimpleIntegerProperty(quantite);
		
	}

	public final SimpleStringProperty tailleProperty() {
		return this.taille;
	}
	

	public final String getTaille() {
		return this.tailleProperty().get();
	}
	

	public final void setTaille(final String taille) {
		this.tailleProperty().set(taille);
	}
	

	public final SimpleStringProperty couleurProperty() {
		return this.couleur;
	}
	

	public final String getCouleur() {
		return this.couleurProperty().get();
	}
	

	public final void setCouleur(final String couleur) {
		this.couleurProperty().set(couleur);
	}
	

	public final SimpleIntegerProperty quantiteProperty() {
		return this.quantite;
	}
	

	public final int getQuantite() {
		return this.quantiteProperty().get();
	}
	

	public final void setQuantite(final int quantite) {
		this.quantiteProperty().set(quantite);
	}
	
	
	
}
