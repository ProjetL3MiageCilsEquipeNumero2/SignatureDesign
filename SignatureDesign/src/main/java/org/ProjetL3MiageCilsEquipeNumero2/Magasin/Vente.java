package org.ProjetL3MiageCilsEquipeNumero2.Magasin;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.ProjetL3MiageCilsEquipeNumero2.SignatureDesign.App;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Vente {

	private static ObservableList<Vente> ventes = FXCollections.observableArrayList();

	private SimpleIntegerProperty id;
	private SimpleIntegerProperty id_vendeur;
	private SimpleIntegerProperty id_client;
	private SimpleObjectProperty<Timestamp> date;
	private SimpleListProperty<VenteArticle> articles;

	public Vente(int id, int id_vendeur, int id_client, Timestamp date, ObservableList<VenteArticle> articles) {
		this.id = new SimpleIntegerProperty(id);
		this.id_client = new SimpleIntegerProperty(id_client);
		this.id_vendeur = new SimpleIntegerProperty(id_vendeur);
		this.date = new SimpleObjectProperty<Timestamp>(date);
		this.articles = new SimpleListProperty<VenteArticle>(articles);

	}

	/**
	 * 
	 * @return la liste observable contenant toutes les ventes
	 */
	public static ObservableList<Vente> getVentes() {
		return ventes;
	}

	/*
	 * met à jour les articles
	 */
	public static void ventesUpdate() {
		ventes.clear();
		ResultSet tableVentes = Vente.getTableVentes();
		try {
			while (tableVentes.next()) {
				ResultSet tableArticles = getArticlesId(tableVentes.getInt("Id_Vente"));
				ObservableList<VenteArticle> articles = FXCollections.observableArrayList();
				while (tableArticles.next()) {
					articles.add(new VenteArticle(tableArticles.getInt("id_article"), tableArticles.getString("Taille"),
							tableArticles.getString("Couleur"), tableArticles.getInt("Quantite")));
				}
				ventes.add(new Vente(tableVentes.getInt("id_vente"), tableVentes.getInt("id_vendeur"),
						tableVentes.getInt("id_client"), tableVentes.getTimestamp("date"), articles));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * retourne la table VENTES
	 */
	public static ResultSet getTableVentes() {
		ResultSet rs = null;
		try {
			CallableStatement cs = App.db.getConnection().prepareCall("{call GET_VENTES}");
			rs = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * retourne les articles d'une ventes à partir de son id
	 * 
	 */
	public static ResultSet getArticlesId(int id) {
		ResultSet rs = null;
		try {
			CallableStatement cs = App.db.getConnection().prepareCall("{call GET_VENTES_ARTICLES_ID(?)}");
			cs.setInt(1, id);
			rs = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void addVente(int id_vendeur, int id_client) {
		CallableStatement cs;
		try {
			cs = App.db.getConnection().prepareCall("{call AJOUT_VENTE(?,?)}");
			cs.setInt(1, id_vendeur);
			cs.setInt(2, id_client);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addArticleVente(int id_article, String taille, String couleur, int qte) {
		CallableStatement cs;
		try {
			cs = App.db.getConnection().prepareCall("{call AJOUT_VENTE_ARTICLES(?,?,?,?,?)}");
			cs.setInt(1, this.getId());
			cs.setInt(2, id_article);
			cs.setString(3, taille);
			cs.setString(4, couleur);
			cs.setInt(5, qte);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addArticleVente(int id_vente, int id_article, String taille, String couleur, int qte) {
		CallableStatement cs;
		try {
			cs = App.db.getConnection().prepareCall("{call AJOUT_VENTE_ARTICLES(?,?,?,?,?)}");
			cs.setInt(1, id_vente);
			cs.setInt(2, id_article);
			cs.setString(3, taille);
			cs.setString(4, couleur);
			cs.setInt(5, qte);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public final SimpleIntegerProperty id_vendeurProperty() {
		return this.id_vendeur;
	}

	public final int getId_vendeur() {
		return this.id_vendeurProperty().get();
	}

	public final void setId_vendeur(final int id_vendeur) {
		this.id_vendeurProperty().set(id_vendeur);
	}

	public final SimpleIntegerProperty id_clientProperty() {
		return this.id_client;
	}

	public final int getId_client() {
		return this.id_clientProperty().get();
	}

	public final void setId_client(final int id_client) {
		this.id_clientProperty().set(id_client);
	}

	public final SimpleObjectProperty<Timestamp> dateProperty() {
		return this.date;
	}

	public final Timestamp getDate() {
		return this.dateProperty().get();
	}

	public final void setDate(final Timestamp date) {
		this.dateProperty().set(date);
	}

	public final SimpleListProperty<VenteArticle> articlesProperty() {
		return this.articles;
	}

	public final ObservableList<VenteArticle> getArticles() {
		return this.articlesProperty().get();
	}

	public final void setArticles(final ObservableList<VenteArticle> articles) {
		this.articlesProperty().set(articles);
	}

}
