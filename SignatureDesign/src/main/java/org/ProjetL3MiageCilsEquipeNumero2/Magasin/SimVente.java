package org.ProjetL3MiageCilsEquipeNumero2.Magasin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TimerTask;

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
			int clientAleatoire = (int) ((Math.random()*10000)%nbClients);
			clients.absolute(clientAleatoire);
			int id_client = clients.getInt("Id_client");
			//vendeur aleatoire
			vendeurs.last();
			int nbVendeurs = vendeurs.getRow();
			int vendeurAleatoire = (int) ((Math.random()*10000)%nbVendeurs);
			vendeurs.absolute(vendeurAleatoire);
			int id_vendeur = clients.getInt("Id_vendeur");
			//creation de la vente
			Vente.addVente(id_vendeur, id_client);
						
			//articles achetes
			quantites.last();
			int nbQuantites = quantites.getRow();
			int quantiteAleatoire = (int) ((Math.random()*10000)%nbQuantites);
			quantites.absolute(quantiteAleatoire);
			int id_article = quantites.getInt("Id_Article");
			String taille = quantites.getString("taille");
			String couleur = quantites.getString("couleur");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
