package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import classes.Secretaire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import main.Main;

public class HomeSecretaireController implements Initializable {
	@FXML AnchorPane leftPane;
	@FXML Button menu;
	@FXML Button dec;
	@FXML Button home;
	@FXML Button cours;
	@FXML Button absence;
	boolean vis = false;
	@FXML Button espace;
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		Image menuIcon = new Image("/icons/menu.png",40,40,false,false);
		Image decoIcon = new Image("/icons/deco.png",40,40,false,false);
		menu.setGraphic(new ImageView(menuIcon));
		dec.setGraphic(new ImageView(decoIcon));
		home.setVisible(vis);
		cours.setVisible(vis);
		absence.setVisible(vis);
		DropShadow shadow = new DropShadow();
		shadow.setOffsetX(3);
		shadow.setOffsetY(3);
		shadow.setRadius(10);
		espace.setEffect(shadow);
			
		espace.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				espace.setStyle("-fx-background-color: black");
				espace.setTextFill(Color.SILVER);
			}
		});
		espace.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				espace.setStyle("-fx-background-color: silver");
				espace.setTextFill(Color.BLACK);
			}
		});
	}

	// méthode pour afficher ou non le menu
	@FXML
	private void showMenuAction(ActionEvent event) {
		if(vis) {
			vis = false;
			home.setVisible(vis);
			cours.setVisible(vis);
			absence.setVisible(vis);
		} else {
			vis = true;
			home.setVisible(vis);
			cours.setVisible(vis);
			absence.setVisible(vis);
		}
	}
	
	// méthode pour se déconnecter
	@FXML
	private void decoAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/Connexion.fxml");
			Main.setSecretaire(new Secretaire());
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Vous avez été déconnecté avec succès");
			alert.showAndWait();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.out.println("Impossible de vous déconnecter !");
		}
	}
	
	// méthode pour retourner à l'accueil
	@FXML
	private void homeAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/HomeSecretaire.fxml");
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.out.println("Impossible de retourner à l'accueil !");
		}
	}
	
	// méthode pour aller dans l'espace personnel
	@FXML
	private void goToEspace(ActionEvent event) {
		try {
			Main.changeScene("/fxml/SpaceSecretaire.fxml");
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.out.println("Impossible d'afficher la page de l'espace personnel !");
		}
	}
	
	// méthode pour voir les cours
	@FXML
	private void CoursAction(ActionEvent e) {
		try {
			Main.changeScene("/fxml/SeeCours.fxml");
		} catch (IOException et) {
			System.err.println(et.getMessage());
			et.printStackTrace();
			System.out.println("Impossible d'afficher la page des cours !");
		}
	}
	
	// méthode pour voir les absences
	@FXML
	private void AbsAction(ActionEvent e) {
		try {
			Main.changeScene("/fxml/SeeAbs.fxml");
		} catch (IOException et) {
			System.err.println(et.getMessage());
			et.printStackTrace();
			System.out.println("Impossible d'afficher la page des absences !");
		}
	}
}
