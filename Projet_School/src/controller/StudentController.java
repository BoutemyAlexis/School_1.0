package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import classes.Etudiant;
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

public class StudentController implements Initializable {
	
	////commun à chaque fenêtre des espaces
	@FXML AnchorPane leftPane;
	@FXML Button menu;
	@FXML Button dec;
	@FXML Button home;
	@FXML Button infos;
	@FXML Button cours;
	boolean vis = false;
	////
	
	@FXML private Button modif;
	@FXML private Button suppCompte;
	@FXML private Button abs;
	@FXML private Button coursLigne;
	@FXML private Button inscris;
	
	
	public void initialize(URL location, ResourceBundle resources) {
		Image menuIcon = new Image("/icons/menu.png",40,40,false,false);
		Image decoIcon = new Image("/icons/deco.png",40,40,false,false);
		menu.setGraphic(new ImageView(menuIcon));
		dec.setGraphic(new ImageView(decoIcon));
		home.setVisible(vis);
		infos.setVisible(vis);
		cours.setVisible(vis);
		DropShadow shadow = new DropShadow();
		shadow.setOffsetX(3);
		shadow.setOffsetY(3);
		shadow.setRadius(10);
		modif.setEffect(shadow);
		suppCompte.setEffect(shadow);
		abs.setEffect(shadow);
		coursLigne.setEffect(shadow);
		inscris.setEffect(shadow);
		
		modif.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				modif.setStyle("-fx-background-color: black");
				modif.setTextFill(Color.SILVER);
			}
		});
		modif.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				modif.setStyle("-fx-background-color: silver");
				modif.setTextFill(Color.BLACK);
			}
		});
				
		suppCompte.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				suppCompte.setStyle("-fx-background-color: black");
				suppCompte.setTextFill(Color.SILVER);
			}
		});
		suppCompte.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				suppCompte.setStyle("-fx-background-color: silver");
				suppCompte.setTextFill(Color.BLACK);
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
				
		coursLigne.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				coursLigne.setStyle("-fx-background-color: black");
				coursLigne.setTextFill(Color.SILVER);
			}
		});
		coursLigne.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				coursLigne.setStyle("-fx-background-color: silver");
				coursLigne.setTextFill(Color.BLACK);
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
	
	// méthode pour afficher ou non le menu
	@FXML
	private void showMenuAction(ActionEvent event) {
		if(vis) {
			vis = false;
			home.setVisible(vis);
			infos.setVisible(vis);
			cours.setVisible(vis);
		} else {
			vis = true;
			home.setVisible(vis);
			infos.setVisible(vis);
			cours.setVisible(vis);
		}
	}
		
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
	
		// méthode pour déconnecter la personne
		@FXML
		private void decoAction(ActionEvent event) {
			try {
				Main.changeScene("/fxml/Connexion.fxml");
				Main.setEtudiant(new Etudiant());
				
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
				Main.changeScene("/fxml/ModifInfos.fxml");
			} catch (IOException e) {
				System.err.println(e.getMessage());
				System.out.println("Impossible d'afficher la page de modification des infos !");
			}
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
		
		// méthode pour afficher la page des cours
		@FXML
		private void coursAction(ActionEvent event) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Vous n'avez aucuns cours à télécharger");
			alert.showAndWait();
		}
		
		// méthode pour aller dans différents espaces
		@FXML
		private void goInAction(ActionEvent event) {
			if(event.getSource() == modif) {
				try {
					Main.changeScene("/fxml/ModifInfos.fxml");
				} catch (IOException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
					System.out.println("Impossible d'afficher la page de modification des infos !");
				}
			}
			if(event.getSource() == suppCompte) {
				try {
					Main.changeScene("/fxml/SuppCompte.fxml");
				} catch (IOException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
					System.out.println("Impossible d'afficher la page de suppression de compte !");
				}
			}
			
			if(event.getSource() == abs) {
				try {
					Main.changeScene("/fxml/SeeAbs.fxml");
				} catch (IOException e) {
					System.err.println(e.getMessage());
					System.out.println("Impossible d'afficher la page des absences !");
				}
			}
			
			if(event.getSource() == inscris) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Vous êtes bien inscrit !");
				alert.setContentText("Votre paiement a été accepté et la secrétaire a activé votre compte.");
				alert.showAndWait();
			}
		}
}
