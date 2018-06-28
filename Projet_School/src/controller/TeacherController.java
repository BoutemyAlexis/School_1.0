package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import classes.Enseignant;
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

public class TeacherController implements Initializable {
	////commun � chaque fen�tre des espaces
	@FXML private AnchorPane leftPane;
	@FXML private Button menu;
	@FXML private Button dec;
	@FXML private Button home;
	@FXML private Button infos;
	private boolean vis = false;
	////
	
	@FXML private Button modifInfos;
	@FXML private Button addCours;
	@FXML private Button abs;
	@FXML private Button suppCours;
	@FXML private Button inscris;

	public void initialize(URL location, ResourceBundle resources) {
		Image menuIcon = new Image("/icons/menu.png",40,40,false,false);
		Image decoIcon = new Image("/icons/deco.png",40,40,false,false);
		menu.setGraphic(new ImageView(menuIcon));
		dec.setGraphic(new ImageView(decoIcon));
		home.setVisible(vis);
		infos.setVisible(vis);
		DropShadow shadow = new DropShadow();
		shadow.setOffsetX(3);
		shadow.setOffsetY(3);
		shadow.setRadius(10);
		modifInfos.setEffect(shadow);
		addCours.setEffect(shadow);
		abs.setEffect(shadow);
		suppCours.setEffect(shadow);
		inscris.setEffect(shadow);
		
		modifInfos.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				modifInfos.setStyle("-fx-background-color: black");
				modifInfos.setTextFill(Color.SILVER);
			}
		});
		modifInfos.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				modifInfos.setStyle("-fx-background-color: silver");
				modifInfos.setTextFill(Color.BLACK);
			}
		});
		
		addCours.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				addCours.setStyle("-fx-background-color: black");
				addCours.setTextFill(Color.SILVER);
			}
		});
		addCours.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				addCours.setStyle("-fx-background-color: silver");
				addCours.setTextFill(Color.BLACK);
			}
		});
				
		abs.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				abs.setStyle("-fx-background-color: black");
				abs.setTextFill(Color.SILVER);
			}
		});
		abs.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				abs.setStyle("-fx-background-color: silver");
				abs.setTextFill(Color.BLACK);
			}
		});
		
		suppCours.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				suppCours.setStyle("-fx-background-color: black");
				suppCours.setTextFill(Color.SILVER);
			}
		});
		suppCours.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				suppCours.setStyle("-fx-background-color: silver");
				suppCours.setTextFill(Color.BLACK);
			}
		});
				
		inscris.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				inscris.setStyle("-fx-background-color: black");
				inscris.setTextFill(Color.SILVER);
			}
		});
		inscris.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				inscris.setStyle("-fx-background-color: silver");
				inscris.setTextFill(Color.BLACK);
			}
		});
	}
	
	// m�thode pour afficher ou non le menu
	@FXML
	private void showMenuAction(ActionEvent event) {
		if(vis) {
			vis = false;
			home.setVisible(vis);
			infos.setVisible(vis);
		} else {
			vis = true;
			home.setVisible(vis);
			infos.setVisible(vis);
		}
	}
		
	// m�thode pour d�connecter la personne
	@FXML
	private void decoAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/Connexion.fxml");
			Main.setEnseignant(new Enseignant());
				
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Vous avez �t� d�connect� avec succ�s");
			alert.showAndWait();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.out.println("Impossible de vous d�connecter !");
		}
	}
		
	// m�thode pour afficher la page de modification des informations
	@FXML
	private void changeInfos(ActionEvent event) {
		try {
			Main.changeScene("/fxml/ModifInfos.fxml");
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.out.println("Impossible d'afficher la page de modification des infos !");
		}
	}
		
	// m�thode pour retourner � l'accueil
	@FXML
	private void homeAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/HomeTeacher.fxml");
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.out.println("Impossible de retourner � l'accueil !");
		}
	}
		
	// m�thode pour aller dans diff�rents espaces
	@FXML
	private void goInAction(ActionEvent event) {
		if(event.getSource() == modifInfos) {
			try {
				Main.changeScene("/fxml/ModifInfos.fxml");
			} catch (IOException e) {
				System.err.println(e.getMessage());
				System.out.println("Impossible d'afficher la page de modification des infos !");
			}
		}
		
		if(event.getSource() == addCours) {
			
		}
		
		if(event.getSource() == abs) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Vous n'avez jamais �t� absent");
			alert.showAndWait();
		}
		
		if(event.getSource() == suppCours) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Vous n'avez pas les droits pour supprimer des fichiers");
			alert.setContentText("Demandez � un administrateur !");
			alert.showAndWait();
		}
		
		if(event.getSource() == inscris) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Vous �tes bien inscrit !");
			alert.setContentText("Votre paiement a �t� accept� et une secr�taire a activ� votre compte !");
			alert.showAndWait();
		}
	}
}
