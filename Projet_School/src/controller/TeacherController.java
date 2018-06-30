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
	////commun à chaque fenêtre des espaces
	@FXML private AnchorPane leftPane;
	@FXML private Button menu;
	@FXML private Button dec;
	@FXML private Button home;
	@FXML private Button infos;
	private boolean vis = false;
	////
	
	@FXML private Button modifInfos;
	@FXML private Button ModifCours;
	@FXML private Button abs;
	@FXML private Button voirCours;
	@FXML private Button inscrit;

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
		ModifCours.setEffect(shadow);
		abs.setEffect(shadow);
		voirCours.setEffect(shadow);
		inscrit.setEffect(shadow);
		
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
		
		ModifCours.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				ModifCours.setStyle("-fx-background-color: black");
				ModifCours.setTextFill(Color.SILVER);
			}
		});
		ModifCours.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				ModifCours.setStyle("-fx-background-color: silver");
				ModifCours.setTextFill(Color.BLACK);
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
		
		voirCours.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				voirCours.setStyle("-fx-background-color: black");
				voirCours.setTextFill(Color.SILVER);
			}
		});
		voirCours.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				voirCours.setStyle("-fx-background-color: silver");
				voirCours.setTextFill(Color.BLACK);
			}
		});
				
		inscrit.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				inscrit.setStyle("-fx-background-color: black");
				inscrit.setTextFill(Color.SILVER);
			}
		});
		inscrit.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				inscrit.setStyle("-fx-background-color: silver");
				inscrit.setTextFill(Color.BLACK);
			}
		});
	}
	
	// méthode pour afficher ou non le menu
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
		
	// méthode pour déconnecter la personne
	@FXML
	private void decoAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/Connexion.fxml");
			Main.setEnseignant(new Enseignant());
				
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
		
	// méthode pour afficher la page de modification des informations
	@FXML
	private void changeInfos(ActionEvent event) {
		try {
			Main.changeScene("/fxml/ModifInfos.fxml");
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.out.println("Impossible d'afficher la page de modification des infos !");
		}
	}
		
	// méthode pour retourner à l'accueil
	@FXML
	private void homeAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/HomeTeacher.fxml");
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.out.println("Impossible de retourner à l'accueil !");
		}
	}
		
	// méthode pour aller dans différents espaces
	@FXML
	private void goInAction(ActionEvent event) {
		if(event.getSource() == modifInfos) {
			try {
				Main.changeScene("/fxml/ModifInfos.fxml");
			} catch (IOException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				System.out.println("Impossible d'afficher la page de modification des infos !");
			}
		}
		
		if(event.getSource() == ModifCours) {
			try {
				Main.changeScene("/fxml/ModifCours.fxml");
			} catch (IOException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				System.out.println("Impossible d'afficher la page de modification du cours !");
			}
		}
		
		if(event.getSource() == abs) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Pas encore accessible !");
			alert.showAndWait();
		}
		
		if(event.getSource() == voirCours) {
			try {
				Main.changeScene("/fxml/SeeCours.fxml");
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				System.out.println("Impossible d'afficher la page pour voir les cours !");
			}
		}
		
		if(event.getSource() == inscrit) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Vous êtes bien inscrit !");
			alert.setContentText("Votre paiement a été accepté et une secrétaire a activé votre compte !");
			alert.showAndWait();
		}
	}
}
