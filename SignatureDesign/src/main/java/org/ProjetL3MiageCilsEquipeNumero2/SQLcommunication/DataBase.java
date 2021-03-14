package org.ProjetL3MiageCilsEquipeNumero2.SQLcommunication;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.ProjetL3MiageCilsEquipeNumero2.Magasin.Article;
import org.ProjetL3MiageCilsEquipeNumero2.SignatureDesign.App;

public class DataBase {
	private Connection connexion;

	public Connection getConnection() {
		return connexion;
	}

	/**
	 * initialise une connexion en tant que admin du serveur mysql et creé la bdd
	 * signaturedesign si elle n'existe pas
	 * 
	 * @param nom  = le nom de l'admin
	 * @param pass = le mot de passe de l'admin
	 */
	public boolean DataBase(String nom, String pass) {
		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/", nom, pass);
			Statement requete = connexion.createStatement();
			requete.executeUpdate("CREATE DATABASE IF NOT EXISTS SignatureDesign;");
			requete.execute("Use SignatureDesign;");
			createSchemaBdd();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * cree le schema de la bdd
	 */
	public void createSchemaBdd() {
		// creation des tables
		createTableArticles();
		createTableQuantites();
		createTableVendeurs();
		createTableFournisseurs();
		createTableClients();
		createTableVentes();
		createTableVenteArticles();
		createTableDepenses();
		createTableCommande();
		createTableApprovisionnements();
		createTableApprovisionnementArticles();
		// stored procedures
		createGetProcedures();
		createGetProceduresId();
		createAjoutProcedures();
		
		//populer la bdd
		populateBdd();
	}

	/**
	 * cree la table articles
	 */
	public void createTableArticles() {
		String create = "CREATE TABLE IF NOT EXISTS `ARTICLES`" + " ( `Id_Article` int NOT NULL AUTO_INCREMENT,"
				+ "`Nom_Article` varchar(45) DEFAULT NULL," + "`Prix_Article` double NOT NULL,"
				+ "`Marque_Article` varchar(45) DEFAULT NULL," + "`Categorie_Article` varchar(45) DEFAULT NULL,"
				+ "PRIMARY KEY (`Id_Article`) );";
		try (Statement stmt = connexion.createStatement()) {
			stmt.executeUpdate(create);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * cree la table quantites
	 */
	public void createTableQuantites() {
		String create = "CREATE TABLE IF NOT EXISTS `QUANTITES`" + "( `Taille` varchar(45) NOT NULL,"
				+ "`Couleur` varchar(45) NOT NULL," + "`Quantite` int NOT NULL," + " `Id_Article` int NOT NULL,"
				+ " PRIMARY KEY (`Taille`,`Couleur`,`Id_Article`),"
				+ "FOREIGN KEY (`Id_Article`) REFERENCES `ARTICLES` (`Id_Article`)" + ");";
		try (Statement stmt = connexion.createStatement()) {
			stmt.executeUpdate(create);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * cree la table Vendeurs
	 */
	public void createTableVendeurs() {
		String create = "CREATE TABLE IF NOT EXISTS `VENDEURS`" + "(  `NSS_Vendeur` int NOT NULL,"
				+ "`Nom_Vendeur` varchar(45) DEFAULT NULL," + "`Prenom_Vendeur` varchar(45) DEFAULT NULL,"
				+ "`Salaire_Vendeur` double NOT NULL," + "PRIMARY KEY (`NSS_Vendeur`)" + ");";
		try (Statement stmt = connexion.createStatement()) {
			stmt.executeUpdate(create);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * cree la table Fournisseurs
	 */
	public void createTableFournisseurs() {
		String create = "CREATE TABLE IF NOT EXISTS `FOURNISSEURS`" + "( `NSS_Fournisseur` int NOT NULL,"
				+ "`Adresse_Fournisseur` varchar(60) DEFAULT NULL," + "`NTel_Fournisseur` varchar(45) DEFAULT NULL,"
				+ "`Email_Fournisseur` varchar(45) DEFAULT NULL," + "`URL_Fournisseur` varchar(150) DEFAULT NULL,"
				+ "PRIMARY KEY (`NSS_Fournisseur`)" + ");";
		try (Statement stmt = connexion.createStatement()) {
			stmt.executeUpdate(create);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * cree la table CLIENTS
	 */
	public void createTableClients() {
		String create = "CREATE TABLE IF NOT EXISTS `CLIENTS`" + "( `NSS_Client` int NOT NULL,"
				+ "`Nom_Client` varchar(45) DEFAULT NULL," + "`Prenom_Client` varchar(45) DEFAULT NULL,"
				+ "`Adresse_Client` varchar(60) DEFAULT NULL," + "`NTel_Client` varchar(45) DEFAULT NULL,"
				+ "`Email_Client` varchar(45) DEFAULT NULL," + "PRIMARY KEY (`NSS_Client`)" + "); ";
		try (Statement stmt = connexion.createStatement()) {
			stmt.executeUpdate(create);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * cree la table Ventes
	 */
	public void createTableVentes() {
		String create = "CREATE TABLE IF NOT EXISTS `VENTES` (" + "`Id_Vente` int NOT NULL AUTO_INCREMENT,"
				+ "`Id_Vendeur` int NOT NULL," + "`Id_Client` int NOT NULL," + "`PrixTotal` double NOT NULL,"
				+ "`Date` datetime NOT NULL," + "PRIMARY KEY (`Id_Vente`),"
				+ "FOREIGN KEY (`Id_Client`) REFERENCES `CLIENTS` (`NSS_Client`),"
				+ "FOREIGN KEY (`Id_Vendeur`) REFERENCES `VENDEURS` (`NSS_Vendeur`)" + ");";
		try (Statement stmt = connexion.createStatement()) {
			stmt.executeUpdate(create);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * cree la table VenntesARTICLES qui associe les produits à une vente
	 */
	public void createTableVenteArticles() {
		String create = "CREATE TABLE IF NOT EXISTS `VENTES_ARTICLES` (" + "`Id_Produit` int NOT NULL,"
				+ "`Id_Vente` int NOT NULL," + "`Taille` varchar(45) NOT NULL," + "`Couleur` varchar(45) NOT NULL,"
				+ "`Quantite` int NOT NULL," + "PRIMARY KEY (`Id_Produit`,`Id_Vente`,`Taille`,`Couleur`),"
				+ "FOREIGN KEY (`Id_Produit`) REFERENCES `ARTICLES` (`Id_Article`),"
				+ "FOREIGN KEY (`Id_Vente`) REFERENCES `VENTES` (`Id_Vente`)" + " ); ";
		try (Statement stmt = connexion.createStatement()) {
			stmt.executeUpdate(create);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * cree la table Depenses
	 */
	public void createTableDepenses() {
		String create = "CREATE TABLE IF NOT EXISTS `DEPENSES` (" + "`Id_Depense` int NOT NULL,"
				+ "`Nom_Depense` varchar(45) DEFAULT NULL," + "`Montant_Depense` double NOT NULL,"
				+ "`Type_Depense` varchar(45) DEFAULT NULL," + "`Date_Depense` date DEFAULT NULL,"
				+ "PRIMARY KEY (`Id_Depense`)" + ");";
		try (Statement stmt = connexion.createStatement()) {
			stmt.executeUpdate(create);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * cree la table Commandes
	 */
	public void createTableCommande() {
		String create = "CREATE TABLE IF NOT EXISTS `COMMANDES` (" + "`Id_Commande` int NOT NULL AUTO_INCREMENT,"
				+ "`Id_Vente` int NOT NULL," + "`DatePassageCommande` datetime NOT NULL,"
				+ "`DateVentePrevue` datetime NOT NULL," + "PRIMARY KEY (`Id_Commande`,`Id_Vente`),"
				+ "FOREIGN KEY (`Id_Vente`) REFERENCES `VENTES` (`Id_Vente`)" + ");";
		try (Statement stmt = connexion.createStatement()) {
			stmt.executeUpdate(create);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * cree la table Approvisionnements
	 */
	public void createTableApprovisionnements() {
		String create = "CREATE TABLE IF NOT EXISTS `APPROVISIONNEMENTS` ("
				+ "`Id_Approvisionnement` int NOT NULL AUTO_INCREMENT," + "`Id_Fournisseur` int NOT NULL,"
				+ "`Prix_Approvisionnement` double NOT NULL," + "`Date_Reception` datetime NOT NULL,"
				+ "PRIMARY KEY (`Id_Approvisionnement`),"
				+ "FOREIGN KEY (`Id_Fournisseur`) REFERENCES `FOURNISSEURS` (`NSS_Fournisseur`)" + ");";
		try (Statement stmt = connexion.createStatement()) {
			stmt.executeUpdate(create);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * cree la table ApprovisionnementArticles
	 */
	public void createTableApprovisionnementArticles() {
		String create = "CREATE TABLE IF NOT EXISTS `APPROVISIONNEMENT_ARTICLES` ("
				+ "`Id_Approvisionnement` int NOT NULL," + "`Id_Produit` int NOT NULL,"
				+ "`Taille` varchar(45) NOT NULL," + "`Couleur` varchar(45) NOT NULL," + "`Quantite` int NOT NULL,"
				+ "PRIMARY KEY (`Id_Approvisionnement`,`Id_Produit`,`Taille`,`Couleur`),"
				+ "FOREIGN KEY (`Id_Approvisionnement`) REFERENCES `APPROVISIONNEMENTS` (`Id_Approvisionnement`),"
				+ "FOREIGN KEY (`Id_Produit`) REFERENCES `ARTICLES` (`Id_Article`)" + ");";
		try (Statement stmt = connexion.createStatement()) {
			stmt.executeUpdate(create);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * cree des procedures GET<NOMTABLE>() qui retournent une table entiere
	 */
	public void createGetProcedures() {
		String[] nom_tables = { "ARTICLES", "CLIENTS", "VENDEURS", "FOURNISSEURS", "DEPENSES", "APPROVISIONNEMENTS",
				"COMMANDES" };
		// pour chacune de ces tables on cree une procedure qui renvoi toute la table
		for (String s : nom_tables) {
			// on supprime la procedure si elle existe deja
			String drop = "DROP PROCEDURE IF EXISTS GET_" + s;
			// on la cree
			String createProcedure = " create procedure GET_" + s + "() begin " + "SELECT * FROM " + s + "; " + "end  ";
			try (Statement stmt = connexion.createStatement()) {
				stmt.execute(drop);
				stmt.executeUpdate(createProcedure);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * cree des procedures GET<NOMTABLE>_ID qui retournent des entrees d'une table
	 * associées à un id ie. GETQUANTITES_ID(1) retourne un result set <id_article,
	 * taille, couleur, quantite> associe à l'article dont l'id est 1
	 */
	public void createGetProceduresId() {
		// association table1=>table details
		Map<String, String> tables = new HashMap<String, String>();
		tables.put("QUANTITES", "Article");
		tables.put("VENTES_ARTICLES", "Vente");
		tables.put("APPROVISIONNEMNET_ARTICLES", "Approvisionnement");
		Set<Map.Entry<String, String>> couples = tables.entrySet();
		for (Map.Entry<String, String> s : couples) {
			String t = s.getKey(); // table
			String att = s.getValue(); // nom attribut associe ie. quantites=id_article
			// on supprime la procedure si elle existe deja
			String drop = "DROP PROCEDURE IF EXISTS GET_" + t + "_ID";
			// on la cree
			String createProcedure = " create procedure GET_" + t + "_ID(IN id int) begin " + "SELECT * FROM " + t
					+ " WHERE Id_" + att + " = id; " + "end  ";
			try (Statement stmt = connexion.createStatement()) {
				stmt.execute(drop);
				stmt.executeUpdate(createProcedure);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void populateBdd() {
		Article.createArticle("article1", 12.5, "marque1", "categorie1");
		Article.createArticle("article2", 50.40, "marque1", "categorie2");
		Article.createQuantite(1, "S", "vert", 20);
		Article.createQuantite(1, "S", "rouge", 15);
		Article.createQuantite(2, "M", "jaune", 20);
	}
	
	public void createAjoutArticleProc() {
		String drop = "DROP PROCEDURE IF EXISTS AJOUT_ARTICLE";
		String createProcedure = " create procedure AJOUT_ARTICLE(IN vnom_article varchar(45), IN vprix_article double,"
				+ " IN vmarque_article varchar(45)," + " IN vcategorie_article varchar(45) " + ")" + "begin " +
				"INSERT INTO ARTICLES ( nom_article ,  prix_article , marque_article , categorie_article ) " +
				"VALUES ( vnom_article ,  vprix_article , vmarque_article , vcategorie_article)"+"; "
				+ "end  ";
		// createProcedure
		try (Statement stmt = connexion.createStatement()) {
			stmt.execute(drop);
			stmt.executeUpdate(createProcedure);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createAjoutQuantiteProc() {
		String drop = "DROP PROCEDURE IF EXISTS AJOUT_QUANTITE";
		String createProcedure = " create procedure AJOUT_QUANTITE(IN vId_article int, IN vtaille varchar(45),"
				+ " IN vcouleurs varchar(45)," + " IN vquantite int " + ")" + "begin " +
				"INSERT INTO QUANTITES ( Id_article ,  Taille , Couleur , Quantite ) " +
				"VALUES ( vId_article ,  vtaille , vcouleurs , vquantite)"+"; "
				+ "end  ";
		// createProcedure
		try (Statement stmt = connexion.createStatement()) {
			stmt.execute(drop);
			stmt.executeUpdate(createProcedure);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// TODO ajouter les autres tables
	/*
	 * cree des procedures qui permettent de populer une table ie.
	 * AJOUT_ARTICLE("nom", prix, "marque", "cat")
	 */
	public void createAjoutProcedures() {
		createAjoutArticleProc();
		createAjoutQuantiteProc();
		}

	/**
	 * 
	 * @return true si connexion fermée avec succes
	 */
	public boolean close() {
		if (connexion == null)
			return false;
		try {
			connexion.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
