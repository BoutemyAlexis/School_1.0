package main;
	
import java.io.IOException;
import classes.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class Main extends Application {
	public static Stage stage;
	private static Utilisateur utilisateur = new Utilisateur();
	private static Etudiant etudiant = new Etudiant();
	private static Enseignant enseignant = new Enseignant();
	private static Secretaire secretaire = new Secretaire();
	
	@Override
	public void start(Stage primaryStage) {                 
		try {
			stage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/Connexion.fxml"));
			stage.setTitle("Accueil");
			stage.setScene(new Scene(root));
			stage.sizeToScene(); 
			stage.show();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Pour vous connecter à la base de données vérifiez que l'utilisateur est root et qu'il n'y a pas de mot de passe");
			alert.showAndWait();
		} catch(Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static Utilisateur getUser() {
		return utilisateur;
	}
	
	public static Enseignant getEnseignant() {
		return enseignant;
	}
	
	public static Etudiant getEtudiant() {
		return etudiant;
	}
	
	public static Secretaire getSecretaire() {
		return secretaire;
	}
	
	// méthode pour changer de scene 
	public static void changeScene(String sceneName) throws IOException {
		Parent root = FXMLLoader.load(Main.class.getResource(sceneName));
		stage.setScene(new Scene(root));
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}