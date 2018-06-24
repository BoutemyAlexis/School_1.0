package main;
	
import java.io.IOException;

import classes.Utilisateur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	public static Stage stage;
	public static Utilisateur utilisateur = new Utilisateur();
	
	@Override
	public void start(Stage primaryStage) {                 
		try {
			stage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("/application/fxml/Connexion.fxml"));
			stage.setTitle("Accueil");
			stage.setScene(new Scene(root));
			stage.sizeToScene(); 
			stage.show();
		} catch(Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static Utilisateur getUser() {
		return utilisateur;
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