package org.ProjetL3MiageCilsEquipeNumero2.Magasin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TimerTask;

import org.ProjetL3MiageCilsEquipeNumero2.SignatureDesign.App;

public class SimVente extends TimerTask {
	
	@Override
	public void run() {
		
		ResultSet clients = Client.getTableClient();
		ResultSet vendeurs = Vendeur.getTableVendeur();
		ResultSet quantites = Article.getTableQuantites();
		
		try {
			//client aleatoire
			clients.last();
			int nbClients = clients.getRow();
			int clientAleatoire = (int) ((Math.random()*10000)%nbClients+1);
			clients.absolute(clientAleatoire);
			int id_client = clients.getInt("Id_client");
			//vendeur aleatoire
			vendeurs.last();
			int nbVendeurs = vendeurs.getRow();
			int vendeurAleatoire = (int) ((Math.random()*10000)%nbVendeurs+1);
			vendeurs.absolute(vendeurAleatoire);
			int id_vendeur = vendeurs.getInt("Id_vendeur");
			//creation de la vente
			Vente.addVente(id_vendeur, id_client);
			Statement stm = App.db.getConnection().createStatement();
			ResultSet rs = stm.executeQuery("SELECT LAST_INSERT_ID();");
			rs.next();
			int id_vente = rs.getInt(1);
			//articles achetes
			quantites.last();
			int nbQuantites = quantites.getRow();
			int quantiteAleatoire = (int) ((Math.random()*10000)%nbQuantites)+1;
			quantites.absolute(quantiteAleatoire);
			int id_article = quantites.getInt("Id_Article");
			String taille = quantites.getString("taille");
			String couleur = quantites.getString("couleur");
			Vente.addArticleVente(id_vente, id_article, taille, couleur, (int) ((Math.random()*10000)%10));
			Article.articlesUpdate();
			Client.clientsUpdate();
			Vendeur.vendeursUpdate();
			Vente.ventesUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
