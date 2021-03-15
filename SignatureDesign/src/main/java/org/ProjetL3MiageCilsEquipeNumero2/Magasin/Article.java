package org.ProjetL3MiageCilsEquipeNumero2.Magasin;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.ProjetL3MiageCilsEquipeNumero2.SignatureDesign.App;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

	public static void articlesUpdate() {
		articles.clear();
		ResultSet tableArticles = Article.getTableArticles();
		try {
			while (tableArticles.next()) {
				ResultSet tableQuantites = getQuantitesId(tableArticles.getInt("Id_Article"));
				ObservableList<Quantite> quantites = FXCollections.observableArrayList();
				while (tableQuantites.next()) {
					quantites.add(new Quantite(tableQuantites.getString("Taille"), tableQuantites.getString("Couleur"),
							tableQuantites.getInt("Quantite")));
				}
				articles.add(new Article(tableArticles.getInt("Id_Article"), tableArticles.getString("nom_article"),
						tableArticles.getDouble("prix_article"), tableArticles.getString("marque_article"),
						tableArticles.getString("categorie_article"), quantites));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * retourne la table ARTICLES
	 */
	public static ResultSet getTableArticles() {
		ResultSet rs = null;
		try {
			CallableStatement cs = App.db.getConnection().prepareCall("{call GET_ARTICLES}");
			rs = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * ajoute 1 article a la bdd
	 * 
	 * @param
	 * 
	 */
	public static void createArticle(String nom, double prix, String marque, String cat) {
		CallableStatement cs;
		try {
			cs = App.db.getConnection().prepareCall("{call AJOUT_ARTICLE(?,?,?,?)}");
			cs.setString(1, nom);
			cs.setDouble(2, prix);
			cs.setString(3, marque);
			cs.setString(4, cat);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createQuantite(String taille, String couleur, int qte) {
		CallableStatement cs;
		try {
			cs = App.db.getConnection().prepareCall("{call AJOUT_QUANTITE(?,?,?,?)}");
			cs.setInt(1, this.getId());
			cs.setString(2, taille);
			cs.setString(3, couleur);
			cs.setInt(4, qte);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ajoute une ligne a la table Quantites
	 * 
	 * @param id
	 * @param taille
	 * @param couleur
	 * @param qte
	 * 
	 */
	public static void createQuantite(int id, String taille, String couleur, int qte) {
		CallableStatement cs;
		try {
			cs = App.db.getConnection().prepareCall("{call AJOUT_QUANTITE(?,?,?,?)}");
			cs.setInt(1, id);
			cs.setString(2, taille);
			cs.setString(3, couleur);
			cs.setInt(4, qte);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * supprime l'article
	 */
	public void delete() {
		CallableStatement cs;
		try {
			cs = App.db.getConnection().prepareCall("{call DELETE_ARTICLE(?)}");
			cs.setInt(1, this.getId());
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * retourne les quantites d'un article à partir de son id
	 * 
	 */
	public static ResultSet getQuantitesId(int id) {
		ResultSet rs = null;
		try {
			CallableStatement cs = App.db.getConnection().prepareCall("{call GET_QUANTITES_ID(?)}");
			cs.setInt(1, id);
			rs = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	
	public Article(int id, String nom, Double prix, String marque, String categorie,
			ObservableList<Quantite> quantites) {
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
