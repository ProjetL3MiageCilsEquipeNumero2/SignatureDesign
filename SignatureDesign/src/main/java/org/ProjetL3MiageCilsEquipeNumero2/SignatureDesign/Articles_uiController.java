package org.ProjetL3MiageCilsEquipeNumero2.SignatureDesign;



import org.ProjetL3MiageCilsEquipeNumero2.Magasin.Article;
import org.ProjetL3MiageCilsEquipeNumero2.Magasin.Quantite;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;

import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TableColumn;

public class Articles_uiController {
	@FXML
	private TextField search_bar;
	@FXML
	private Button filtres_button;
	@FXML
	private TableView<Article> main_table;
	@FXML
	private TableColumn<Article, Integer> id_col;
	@FXML
	private TableColumn<Article, String> nom_col;
	@FXML
	private TableColumn<Article, Double> prix_col;
	@FXML
	private TableColumn qtmax_col;
	@FXML
	private Label id_label;
	@FXML
	private Label v_id;
	@FXML
	private Label nom_label;
	@FXML
	private Label v_nom;
	@FXML
	private Label prix_label;
	@FXML
	private Label v_prix;
	@FXML
	private Label marque_label;
	@FXML
	private Label cat_label;
	@FXML
	private Label v_marque;
	@FXML
	private Label v_cat;
	@FXML
	private TableView<Quantite> sec_table;
	@FXML
	private TableColumn<Quantite, String> taille_col;
	@FXML
	private TableColumn<Quantite, String> coleur_col;
	@FXML
	private TableColumn<Quantite, Integer> qt_col;

	@FXML
	public void initialize() {
		id_col.setCellValueFactory(new PropertyValueFactory<Article, Integer>("id"));
		nom_col.setCellValueFactory(new PropertyValueFactory<Article, String>("nom"));
		prix_col.setCellValueFactory(new PropertyValueFactory<Article, Double>("prix"));
		taille_col.setCellValueFactory(new PropertyValueFactory<Quantite, String>("taille"));
		coleur_col.setCellValueFactory(new PropertyValueFactory<Quantite, String>("couleur"));
		qt_col.setCellValueFactory(new PropertyValueFactory<Quantite, Integer>("quantite"));
		Article.articlesUpdate();
		main_table.setItems(Article.getArticles());

		// lorsque l'utilisateur clique sur un article le card lateral s'actualise
		TableViewSelectionModel<Article> selectionModel = main_table.getSelectionModel();
		ObservableList<Article> selection = selectionModel.getSelectedItems();
		selection.addListener(new ListChangeListener<Article>() {
			@Override
			public void onChanged(Change<? extends Article> change) {
				Article focus = selection.get(0);
				v_id.setText(Integer.toString(focus.getId()));
				v_nom.setText(focus.getNom());
				v_prix.setText(Double.toString(focus.getPrix()));
				v_marque.setText(focus.getMarque());
				v_cat.setText(focus.getCategorie());
				sec_table.setItems(focus.getQuantites());

			}
		});
	}

}
