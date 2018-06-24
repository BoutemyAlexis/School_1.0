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

public class StudentController implements Initializable {
	
	////commun à chaque fenêtre des espaces
	@FXML AnchorPane leftPane;
	@FXML Button menu;
	@FXML Button dec;
	@FXML Button home;
	@FXML Button infos;
	@FXML Button cours;
	@FXML Button planning;
	boolean vis = false;
	////
	
	@FXML private Button modif;
	@FXML private Button notes;
	@FXML private Button abs;
	@FXML private Button coursLigne;
	@FXML private Button inscris;
	
	
	public void initialize(URL location, ResourceBundle resources) {
		Image menuIcon = new Image("icon/menu.png",40,40,false,false);
		Image decoIcon = new Image("icon/deco.png",40,40,false,false);
		menu.setGraphic(new ImageView(menuIcon));
		dec.setGraphic(new ImageView(decoIcon));
		home.setVisible(vis);
		infos.setVisible(vis);
		cours.setVisible(vis);
		planning.setVisible(vis);
		
		modif.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				modif.setStyle("-fx-background-color: black");
				modif.setTextFill(Color.ORANGE);
			}
		});
		modif.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				modif.setStyle("-fx-background-color: orange");
				modif.setTextFill(Color.BLACK);
			}
		});
				
		notes.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				notes.setStyle("-fx-background-color: black");
				notes.setTextFill(Color.ORANGE);
			}
		});
		notes.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				notes.setStyle("-fx-background-color: orange");
				notes.setTextFill(Color.BLACK);
			}
		});
				
		abs.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				abs.setStyle("-fx-background-color: black");
				abs.setTextFill(Color.ORANGE);
			}
		});
		abs.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				abs.setStyle("-fx-background-color: orange");
				abs.setTextFill(Color.BLACK);
			}
		});
				
		coursLigne.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				coursLigne.setStyle("-fx-background-color: black");
				coursLigne.setTextFill(Color.ORANGE);
			}
		});
		coursLigne.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				coursLigne.setStyle("-fx-background-color: orange");
				coursLigne.setTextFill(Color.BLACK);
			}
		});
				
		inscris.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				inscris.setStyle("-fx-background-color: black");
				inscris.setTextFill(Color.ORANGE);
			}
		});
		inscris.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				inscris.setStyle("-fx-background-color: orange");
				inscris.setTextFill(Color.BLACK);
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
				planning.setVisible(vis);
			} else {
				vis = true;
				leftPane.setStyle("-fx-background-color: silver");
				home.setVisible(vis);
				infos.setVisible(vis);
				cours.setVisible(vis);
				planning.setVisible(vis);
			}
		}
		
		// méthode pour déconnecter la personne
		@FXML
		private void decoAction(ActionEvent event) {
			try {
				Main.changeScene("Connexion.fxml");
				
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
			try {
				Main.changeScene("modifInfos.fxml");
			} catch (IOException e) {
				System.err.println(e.getMessage());
				System.out.println("Impossible d'afficher la page de modification des infos !");
			}
		}
		
		// méthode pour retourner à l'accueil
		@FXML
		private void homeAction(ActionEvent event) {
			try {
				Main.changeScene("Connected.fxml");
			} catch (IOException e) {
				System.err.println(e.getMessage());
				System.out.println("Impossible de retourner à l'accueil !");
			}
		}
		
		// méthode pour afficher la page des cours
		@FXML
		private void coursAction(ActionEvent event) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Vous n'avez aucuns cours à télécharger");
			alert.showAndWait();
		}
		
		// méthode pour afficher la page des examens avec dates
		@FXML
		private void examenAction(ActionEvent event) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Vous n'avez pas d'examens pendant les prochains mois");
			alert.showAndWait();
		}
		
		// méthode pour aller dans différents espaces
		@FXML
		private void goInAction(ActionEvent event) {
			if(event.getSource() == modif) {
				try {
					Main.changeScene("modifInfos.fxml");
				} catch (IOException e) {
					System.err.println(e.getMessage());
					System.out.println("Impossible d'afficher la page de modification des infos !");
				}
			}
			if(event.getSource() == notes) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Vous n'avez aucunes notes pour le moment");
				alert.showAndWait();
			}
			if(event.getSource() == abs) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Vous n'avez jamais été absent");
				alert.showAndWait();
			}
			if(event.getSource() == coursLigne) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Il n'y a pas de cours en ligne pour le moment");
				alert.showAndWait();
			}
			if(event.getSource() == inscris) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Vous êtes bien inscris !");
				alert.setContentText("Votre paiement a été accepté.");
				alert.showAndWait();
			}
		}
}
