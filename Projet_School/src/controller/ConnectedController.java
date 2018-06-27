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
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import main.Main;

// la classe controller de la page Connected.fxml
public class ConnectedController implements Initializable {

	//// commun à chaque fenêtre des espaces
	@FXML AnchorPane leftPane;
	@FXML Button menu;
	@FXML Button dec;
	@FXML Button home;
	@FXML Button infos;
	@FXML Button cours;
	@FXML Button planning;
	boolean vis = false;
	////
	
	@FXML Button espEtudiant;
	@FXML Button espEnseignant;
	@FXML Button espAdmin;
	@FXML Button espCompta;
	
	// Méthode pour initialiser la page avec les icones
	public void initialize(URL location, ResourceBundle resources) {
		Image menuIcon = new Image("icon/menu.png",40,40,false,false);
		Image decoIcon = new Image("icon/deco.png",40,40,false,false);
		menu.setGraphic(new ImageView(menuIcon));
		dec.setGraphic(new ImageView(decoIcon));
		home.setVisible(vis);
		infos.setVisible(vis);
		cours.setVisible(vis);
		planning.setVisible(vis);

		
		// Evènement souris entrée et sortie pour l'espace étudiant
		espEtudiant.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				espEtudiant.setStyle("-fx-background-color: black");
				espEtudiant.setTextFill(Color.ORANGE);
			}
		});
		espEtudiant.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				espEtudiant.setStyle("-fx-background-color: orange");
				espEtudiant.setTextFill(Color.BLACK);
			}
		});
		
		// Evènement souris entrée et sortie pour l'espace enseignant
		espEnseignant.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				espEnseignant.setStyle("-fx-background-color: black");
				espEnseignant.setTextFill(Color.ORANGE);
			}
		});
		espEnseignant.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				espEnseignant.setStyle("-fx-background-color: orange");
				espEnseignant.setTextFill(Color.BLACK);
			}
		});
		
		// Evènement souris entrée et sortie pour l'espace admin
		espAdmin.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				espAdmin.setStyle("-fx-background-color: black");
				espAdmin.setTextFill(Color.ORANGE);
			}
		});
		espAdmin.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				espAdmin.setStyle("-fx-background-color: orange");
				espAdmin.setTextFill(Color.BLACK);
			}
		});
		
		// Evènement souris entrée et sortie pour l'espace compta
		espCompta.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				espCompta.setStyle("-fx-background-color: black");
				espCompta.setTextFill(Color.ORANGE);
			}
		});
		espCompta.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				espCompta.setStyle("-fx-background-color: orange");
				espCompta.setTextFill(Color.BLACK);
			}
		});
	}

	// méthode pour afficher ou non le menu
	/*@FXML
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

			if(Main.getUser().getFonction().equals("Enseignant")) {
				cours.setDisable(true);
				planning.setVisible(vis);
				infos.setVisible(vis);
			}
			else {
				if(Main.getUser().getFonction().equals("Secrétaire")) {
					cours.setVisible(false);
					planning.setVisible(false);
					infos.setVisible(false);
				}
				else {
					cours.setVisible(vis);
					planning.setVisible(vis);
					infos.setVisible(vis);
				}
			}
		}
	}*/
	
	// méthode pour déconnecter la personne
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
	
	// méthode pour retourner à l'accueil
	@FXML
	private void homeAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/Connected.fxml");
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.out.println("Impossible de retourner à l'accueil !");
		}
	}
	
	// méthode pour aller dans différents espaces
	/*@FXML
	private void goInAction(ActionEvent event) {
		if(event.getSource() == espEtudiant) {
			if(Main.getUser().getFonction().equals("Secrétaire") || Main.getUser().getFonction().equals("Enseignant")) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Cette espace est réservé aux étudiants !");
				alert.showAndWait();
			}
			else {
				try {
					Main.changeScene("SpaceStudent.fxml");
				} catch (IOException e) {
					System.err.println(e.getMessage());
					System.out.println("Impossible d'aller dans l'espace étudiant !");
				}
			}
		}
		if(event.getSource() == espEnseignant) {
			if(Main.getUser().getFonction().equals("Etudiant") || Main.getUser().getFonction().equals("Secrétaire")) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Cette espace est réservé pour les enseignants !");
				alert.showAndWait();
			}
			else {
				try {
					Main.changeScene("SpaceTeacher.fxml");
				} catch (IOException e) {
					System.err.println(e.getMessage());
					System.out.println("Impossible d'aller dans l'espace enseignant !");
				}
			}
		}
		if(event.getSource() == espAdmin) {
			if(Main.getUser().getFonction().equals("Etudiant") || Main.getUser().getFonction().equals("Enseignant")) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Cette espace est réservé à l'administrateur !");
				alert.showAndWait();
			}
			else {
				try {
					Main.changeScene("SpaceAdmin.fxml");
				} catch (IOException e) {
					System.err.println(e.getMessage());
					System.out.println("Impossible d'aller dans l'espace administratif !");
				}
			}
		}
		if(event.getSource() == espCompta) {
			if(Main.getUser().getFonction().equals("Etudiant") || Main.getUser().getFonction().equals("Enseignant")) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Cette espace est réservé à l'administrateur !");
				alert.showAndWait();
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Cette espace sera disponible très prochainement.");
				alert.showAndWait();
			}
		}
	}*/
	
	// méthode pour afficher la page de modification des informations
	@FXML
	private void changeInfos(ActionEvent event) {
		try {
			Main.changeScene("/fxml/modifInfos.fxml");
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.out.println("Impossible d'afficher la page de modification des infos !");
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
}
