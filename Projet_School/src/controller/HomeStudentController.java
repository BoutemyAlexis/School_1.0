package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class HomeStudentController implements Initializable {
	
	@FXML AnchorPane leftPane;
	@FXML Button menu;
	@FXML Button dec;
	@FXML Button home;
	@FXML Button infos;
	@FXML Button cours;
	boolean vis = true;
	@FXML private Button espace;
	@FXML AnchorPane anchor;
	
	public void initialize(URL location, ResourceBundle resources) {
		Image menuIcon = new Image("/icons/menu.png",40,40,false,false);
		Image decoIcon = new Image("/icons/deco.png",40,40,false,false);
		menu.setGraphic(new ImageView(menuIcon));
		dec.setGraphic(new ImageView(decoIcon));
		home.setVisible(vis);
		infos.setVisible(vis);
		cours.setVisible(vis);
		
		espace.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				espace.setStyle("-fx-background-color: black");
				espace.setTextFill(Color.ORANGE);
			}
		});
		espace.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				espace.setStyle("-fx-background-color: orange");
				espace.setTextFill(Color.BLACK);
			}
		});
	}
	
	// méthode pour afficher ou non le menu
	@FXML
	private void showMenuAction(ActionEvent event) {
		if(vis) {
			vis = false;
			leftPane.setStyle("-fx-background-color: white");
			home.setVisible(vis);
			infos.setVisible(vis);
			cours.setVisible(vis);
		} else {
			vis = true;
			leftPane.setStyle("-fx-background-color: silver");
			home.setVisible(vis);
			infos.setVisible(vis);
			cours.setVisible(vis);
		}
	}
	
	// méthode pour se déconnecter
	@FXML
	private void decoAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/Connexion.fxml");
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Vous avez été déconnecté avec succès");
			alert.showAndWait();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.out.println("Impossible de vous déconnecter !");
		}
	}
	
	// méthode pour afficher la page de modification des informations
	@FXML
	private void changeInfos(ActionEvent event) {
		System.out.println("coucou");
	}
	
	// méthode pour retourner à l'accueil
	@FXML
	private void homeAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/HomeStudent.fxml");
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.out.println("Impossible de retourner à l'accueil !");
		}
	}
	
	
}