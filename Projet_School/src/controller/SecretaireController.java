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

public class SecretaireController implements Initializable {
	@FXML AnchorPane leftPane;
	@FXML Button menu;
	@FXML Button dec;
	@FXML Button home;
	@FXML Button cours;
	@FXML Button absence;
	boolean vis = false;
	
	@FXML private Button AddUser;
	@FXML private Button suppCompte;
	@FXML private Button abs;
	@FXML private Button coursLigne;
	@FXML private Button compte;
	@FXML private Button AddCours;
	
	public void initialize(URL location, ResourceBundle resources) {
		Image menuIcon = new Image("/icons/menu.png",40,40,false,false);
		Image decoIcon = new Image("/icons/deco.png",40,40,false,false);
		menu.setGraphic(new ImageView(menuIcon));
		dec.setGraphic(new ImageView(decoIcon));
		home.setVisible(vis);
		absence.setVisible(vis);
		cours.setVisible(vis);
		DropShadow shadow = new DropShadow();
		shadow.setOffsetX(3);
		shadow.setOffsetY(3);
		shadow.setRadius(10);
		AddUser.setEffect(shadow);
		suppCompte.setEffect(shadow);
		abs.setEffect(shadow);
		coursLigne.setEffect(shadow);
		compte.setEffect(shadow);
		AddCours.setEffect(shadow);
		
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
		
		AddCours.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				AddCours.setStyle("-fx-background-color: black");
				AddCours.setTextFill(Color.SILVER);
			}
		});
		AddCours.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				AddCours.setStyle("-fx-background-color: silver");
				AddCours.setTextFill(Color.BLACK);
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
				
		compte.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				compte.setStyle("-fx-background-color: black");
				compte.setTextFill(Color.SILVER);
			}
		});
		compte.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				compte.setStyle("-fx-background-color: silver");
				compte.setTextFill(Color.BLACK);
			}
		});
		
	}

	// m�thode pour afficher ou non le menu
	@FXML
	private void showMenuAction(ActionEvent event) {
		if(vis) {
			vis = false;
			home.setVisible(vis);
			absence.setVisible(vis);
			cours.setVisible(vis);
		} else {
			vis = true;
			home.setVisible(vis);
			absence.setVisible(vis);
			cours.setVisible(vis);
		}
	}
		
	// m�thode pour voir les cours
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
	
	// m�thode pour voir les absences
	@FXML
	private void AbsAction(ActionEvent e) {
		try {
			Main.changeScene("/fxml/SeeAbs.fxml");
		} catch (IOException et) {
			System.err.println(et.getMessage());
			et.printStackTrace();
			System.out.println("Impossible d'afficher la page des cours !");
		}
	}
	
	// m�thode pour d�connecter la personne
	@FXML
	private void decoAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/Connexion.fxml");
			Main.setSecretaire(new Secretaire());
				
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Vous avez �t� d�connect� avec succ�s");
			alert.showAndWait();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.out.println("Impossible de vous d�connecter !");
		}
	}
	
	// m�thode pour retourner � l'accueil
	@FXML
	private void homeAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/HomeSecretaire.fxml");
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.out.println("Impossible de retourner � l'accueil !");
		}
	}
	
	// m�thode pour voir les comptes non activ�s
	@FXML
	private void CompteAction(ActionEvent event) {
		
		
	}
	
	// m�thode pour ajouter un utilisateur (eleve ou enseignant)
	@FXML
	private void AddAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/AddUser.fxml");
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	// m�thode pour ajouter un cours
	@FXML
	private void AddCoursAction(ActionEvent event) {
			
		
	}
		
	// m�thode pour supprimer un utilisateur (eleve)
	@FXML
	private void SuppAction(ActionEvent event) {
		
		
		
	}
}
