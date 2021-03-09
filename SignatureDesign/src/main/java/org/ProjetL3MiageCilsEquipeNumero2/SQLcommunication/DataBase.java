package org.ProjetL3MiageCilsEquipeNumero2.SQLcommunication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    public static Connection connexion;

    /**
     * initialise une connexion en tant que admin du serveur mysql et creé la bdd
     * signaturedesign si elle n'existe pas
     * 
     * @param nom  = le nom de l'admin
     * @param pass = le mot de passe de l'admin
     */
    public DataBase(String nom, String pass) {
        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/", nom, pass);
            Statement requete = connexion.createStatement();
            requete.executeUpdate("CREATE DATABASE IF NOT EXISTS SignatureDesign; USE SignatureDesign;");
            createSchemaBdd();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * cree le schema de la bdd
     */
    public void createSchemaBdd() {
        //creation des tables
        createTableArticles();
        createTableQuantites();
        createTableVendeurs();
        createTableFournisseurs();
        createTableClients();
        createTableVentes();
        createTableVenteProduits();
        createTableDepenses();
        createTableCommande();
        createTableApprovisionnements();
        createTableApprovisionnementProduits();
        //stored procedures
    }

    /**
     * cree la table articles
     */
    public void createTableArticles() {
        String create = "CREATE TABLE IF NOT EXISTS `ARTICLES`" + " ( `Id_Article` int NOT NULL AUTO_INCREMENT,"
                + "`Nom_Article` varchar(45) DEFAULT NULL," + "`Prix_Article` double DEFAULT NULL,"
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
                + "`Couleur` varchar(45) NOT NULL," + "`Quantite` int DEFAULT NULL," + " `Id_Article` int NOT NULL,"
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
                + "`Salaire_Vendeur` double DEFAULT NULL," + "PRIMARY KEY (`NSS_Vendeur`)" + ");";
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
                + "`Id_Vendeur` int DEFAULT NULL," + "`Id_Client` int DEFAULT NULL,"
                + "`PrixTotal` double DEFAULT NULL," + "`Date` datetime DEFAULT NULL," + "PRIMARY KEY (`Id_Vente`),"
                + "FOREIGN KEY (`Id_Client`) REFERENCES `CLIENTS` (`NSS_Client`),"
                + "FOREIGN KEY (`Id_Vendeur`) REFERENCES `VENDEURS` (`NSS_Vendeur`)" + ");";
        try (Statement stmt = connexion.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * cree la table VenntesProduits qui associe les produits à une vente
     */
    public void createTableVenteProduits() {
        String create = "CREATE TABLE IF NOT EXISTS `VENTES_PRODUITS` (" + "`Id_Produit` int NOT NULL,"
                + "`Id_Vente` int NOT NULL," + "`Taille` varchar(45) NOT NULL," + "`Couleur` varchar(45) NOT NULL,"
                + "`Quantite` varchar(45) NOT NULL," + "PRIMARY KEY (`Id_Produit`,`Id_Vente`,`Taille`,`Couleur`),"
                + "FOREIGN KEY (`Id_Produit`) REFERENCES `PRODUITS` (`Id_Produit`),"
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
                + "`Nom_Depense` varchar(45) DEFAULT NULL," + "`Montant_Depense` varchar(45) DEFAULT NULL,"
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
                + "`Id_Vente` int NOT NULL," + "`DatePassageCommande` datetime DEFAULT NULL,"
                + "`DateVentePrevue` datetime DEFAULT NULL," + "PRIMARY KEY (`Id_Commande`,`Id_Vente`),"
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
                + "`Id_Approvisionnement` int NOT NULL AUTO_INCREMENT," + "`Id_Fournisseur` int DEFAULT NULL,"
                + "`Prix_Approvisionnement` double DEFAULT NULL," + "`Date_Reception` datetime DEFAULT NULL,"
                + "PRIMARY KEY (`Id_Approvisionnement`),"
                + "FOREIGN KEY (`Id_Fournisseur`) REFERENCES `FOURNISSEURS` (`NSS_Fournisseur`)" + ");";
        try (Statement stmt = connexion.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * cree la table ApprovisionnementProduits
     */
    public void createTableApprovisionnementProduits() {
        String create = "CREATE TABLE `APPROVISIONNEMENT_PRODUITS` (" + "`Id_Approvisionnement` int NOT NULL,"
                + "`Id_Produit` int NOT NULL," + "`Taille` varchar(45) NOT NULL," + "`Couleur` varchar(45) NOT NULL,"
                + "`Quantite` varchar(45) DEFAULT NULL,"
                + "PRIMARY KEY (`Id_Approvisionnement`,`Id_Produit`,`Taille`,`Couleur`),"
                + "FOREIGN KEY (`Id_Approvisionnement`) REFERENCES `APPROVISIONNEMENT` (`Id_Approvisionnement`),"
                + "FOREIGN KEY (`Id_Produit`) REFERENCES `PRODUITS` (`Id_Produit`)" + ");";
        try (Statement stmt = connexion.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * initialise une connexion
     * 
     * @param nom  = le nom de l'user
     * @param pass = le mot de passe de l'user
     * @return true si connexion etablie, faux sinon
     */
    public boolean init(String nom, String pass) {
        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/SignatureDesign", nom, pass);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
