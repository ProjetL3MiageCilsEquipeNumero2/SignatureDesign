package org.ProjetL3MiageCilsEquipeNumero2.Magasin;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class VenteArticle {

	private SimpleIntegerProperty id;
	private SimpleStringProperty taille;
	private SimpleStringProperty couleur;
	private SimpleIntegerProperty qte;

	public VenteArticle(int id, String taille, String couleur, int qte) {
		this.id = new SimpleIntegerProperty(id);
		this.taille = new SimpleStringProperty(taille);
		this.couleur = new SimpleStringProperty(couleur);
		this.qte = new SimpleIntegerProperty(qte);
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

	public final SimpleIntegerProperty qteProperty() {
		return this.qte;
	}

	public final int getQte() {
		return this.qteProperty().get();
	}

	public final void setQte(final int qte) {
		this.qteProperty().set(qte);
	}


	public final SimpleIntegerProperty idProperty() {
		return this.id;
	}
	


	public final int getId() {
		return this.idProperty().get();
	}
	


	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	

}
