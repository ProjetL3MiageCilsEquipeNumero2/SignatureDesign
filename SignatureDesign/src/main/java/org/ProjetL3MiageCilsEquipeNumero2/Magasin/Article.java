package org.ProjetL3MiageCilsEquipeNumero2.Magasin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.ProjetL3MiageCilsEquipeNumero2.SQLcommunication.SQLcomm;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class Article {
	private static ObservableList<Article> articles = FXCollections.observableArrayList();

	private SimpleIntegerProperty id;
	private SimpleStringProperty nom;
	private SimpleDoubleProperty prix;
	private SimpleStringProperty marque;
	private SimpleStringProperty categorie;
	private SimpleListProperty<Quantite> quantites;



	/**
	 * 
	 * @return la liste observable contenant tous les articles
	 */
	public static ObservableList<Article> getArticles() {
		return articles;
	}


	//TODO: à modifier après avoir fait les differentes fonctions
	/*
	public static void articlesUpdate() {
		Statement requeteTableArticles = SQLcomm.requete();
		Statement requeteTableQuantites = SQLcomm.requete();
		ResultSet tmp = table(requeteTableArticles);
		try {
			while (tmp.next()) {
				ResultSet tmpqt = idToQts(requeteTableQuantites, tmp.getInt(1));
				ObservableList<Quantite> quantites = FXCollections.observableArrayList();
				while(tmpqt.next()) {
					quantites.add(new Quantite(tmpqt.getString("Taille"), tmpqt.getString("Coleur"), tmpqt.getInt("Quantite")));
				}
				articles.add(new Article(tmp.getInt(1), tmp.getString(2), tmp.getDouble(3), tmp.getString(4),
						tmp.getString(5), quantites));
			}
			requeteTableArticles.close();
			requeteTableQuantites.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/

	//TODO: fct ajout article
	/**
	 * ajoute 1 article a la bdd
	 * 
	 * @param valeurs = nom, prix, marque, categorie
	 * 
	 */

	//TODO: get table article
	/**
	 * retourne la table Produits
	 * 
	 */
	
	//TODO:get quantites à partir d'id
	/**
	 * retourne les quantites d'un produit à partir de son id
	 * 
	 */

	//TODO: ajout qte
	/**
	 * ajoute une ligne a la table Quantites
	 * 
	 * @param taille
	 * @param couleur
	 * @param qte
	 * 
	 */

	
	public Article(int id, String nom, Double prix, String marque, String categorie, ObservableList<Quantite> quantites) {
		this.nom = new SimpleStringProperty(nom);
		this.prix = new SimpleDoubleProperty(prix);
		this.marque = new SimpleStringProperty(marque);
		this.categorie = new SimpleStringProperty(categorie);
		this.id = new SimpleIntegerProperty(id);
		this.quantites = new SimpleListProperty<>(quantites);
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

	public final SimpleStringProperty nomProperty() {
		return this.nom;
	}

	public final String getNom() {
		return this.nomProperty().get();
	}

	public final void setNom(final String nom) {
		this.nomProperty().set(nom);
	}

	public final SimpleDoubleProperty prixProperty() {
		return this.prix;
	}

	public final double getPrix() {
		return this.prixProperty().get();
	}

	public final void setPrix(final double prix) {
		this.prixProperty().set(prix);
	}

	public final SimpleStringProperty marqueProperty() {
		return this.marque;
	}

	public final String getMarque() {
		return this.marqueProperty().get();
	}

	public final void setMarque(final String marque) {
		this.marqueProperty().set(marque);
	}

	public final SimpleStringProperty categorieProperty() {
		return this.categorie;
	}

	public final String getCategorie() {
		return this.categorieProperty().get();
	}

	public final void setCategorie(final String categorie) {
		this.categorieProperty().set(categorie);
	}

	
	public final SimpleListProperty<Quantite> quantitesProperty() {
		return this.quantites;
	}
	

	
	public final ObservableList<Quantite> getQuantites() {
		return this.quantitesProperty().get();
	}
	

	

	public final void setQuantites(final ObservableList<Quantite> quantites) {
		this.quantitesProperty().set(quantites);
	}
	



}
