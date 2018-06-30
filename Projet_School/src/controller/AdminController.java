package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import classes.Administrateur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import main.Main;

public class AdminController implements Initializable {
	////commun à chaque fenêtre des espaces
	@FXML private AnchorPane leftPane;
	@FXML private Button menu;
	@FXML private Button dec;
	@FXML private Button home;
	private boolean vis = false;
	////
	
	@FXML private Button AddUser;
	@FXML private Button SuppUser;
	
	public void initialize(URL location, ResourceBundle resources) {
		Image menuIcon = new Image("/icons/menu.png",40,40,false,false);
		Image decoIcon = new Image("/icons/deco.png",40,40,false,false);
		menu.setGraphic(new ImageView(menuIcon));
		dec.setGraphic(new ImageView(decoIcon));
		home.setVisible(vis);
		
		AddUser.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				AddUser.setStyle("-fx-background-color: black");
				AddUser.setTextFill(Color.SILVER);
			}
		});
		AddUser.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				AddUser.setStyle("-fx-background-color: silver");
				AddUser.setTextFill(Color.BLACK);
			}
		});
		
		SuppUser.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				SuppUser.setStyle("-fx-background-color: black");
				SuppUser.setTextFill(Color.SILVER);
			}
		});
		SuppUser.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				SuppUser.setStyle("-fx-background-color: silver");
				SuppUser.setTextFill(Color.BLACK);
			}
		});
	}
	
	// méthode pour afficher ou non le menu
	@FXML
	private void showMenuAction(ActionEvent event) {
		if(vis) {
			vis = false;
			home.setVisible(vis);
		} else {
			vis = true;
			home.setVisible(vis);
		}
	}
			
	// méthode pour déconnecter la personne
	@FXML
	private void decoAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/Connexion.fxml");
			Main.setAdmin(new Administrateur());
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Vous avez été déconnecté avec succès");
			alert.showAndWait();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.out.println("Impossible de vous déconnecter !");
		}
	}
			
	// méthode pour retourner à l'accueil
	@FXML
	private void homeAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/SpaceAdmin.fxml");
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.out.println("Impossible de retourner à l'accueil !");
		}
	}
	
	// méthode pour ajouter un utilisateur
	@FXML
	private void AddAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/AddUser.fxml");
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	// méthode pour supprimer un utilisateur
	@FXML
	private void SuppAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/SeeEtudiants.fxml");
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}	
}
