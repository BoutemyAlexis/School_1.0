package main;
	
import java.io.IOException;
import java.net.URL;
import classes.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	public static Stage stage;
	private static Etudiant etudiant = new Etudiant();
	private static Enseignant enseignant = new Enseignant();
	private static Secretaire secretaire = new Secretaire();
	private static Administrateur admin = new Administrateur();
	
	@Override
	public void start(Stage primaryStage) {                 
		try {
			stage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/Connexion.fxml"));
			stage.setTitle("Accueil");
			stage.setScene(new Scene(root));
			stage.sizeToScene(); 
			stage.show();
		} catch(Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static Enseignant getEnseignant() {
		return enseignant;
	}
	
	public static void setEnseignant(Enseignant en) {
		enseignant = en;
	}
	
	public static Etudiant getEtudiant() {
		return etudiant;
	}
	
	public static void setEtudiant(Etudiant et) {
		etudiant = et;
	}
	
	public static Secretaire getSecretaire() {
		return secretaire;
	}
	
	public static void setSecretaire(Secretaire se) {
		secretaire = se;
	}
	
	public static Administrateur getAdmin() {
		return admin;
	}

	public static void setAdmin(Administrateur admin) {
		Main.admin = admin;
	}
	
	
	// méthode pour changer de scene 
	public static void changeScene(String sceneName) throws IOException {
		URL url = Main.class.getResource(sceneName);
		FXMLLoader loader = new FXMLLoader(url);
		AnchorPane root = loader.load();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}