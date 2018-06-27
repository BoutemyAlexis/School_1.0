package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.PreparedStatement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.Connexion;
import main.Main;

// classe controller du fichier SignIn.fxml
public class SignInController extends Connexion {

	public SignInController() {
		super(cn);
	}

	@FXML private TextField id;
	@FXML private TextField password;
	@FXML private TextField nom; 
	@FXML private TextField prenom; 
	@FXML private ChoiceBox<String> fonction; 
	
	@FXML private Button buttonSignIn;
	@FXML private Button buttonHome;
	
	public void signin(String nom, String prenom, String statut, String login, String passwd) {
		connect();
		ResultSet rs = null;
		String sql = null;
		String sql2 = null;
		String id = null;
		try {
			sql2 = "INSERT INTO connexion (IdConnexion, login, passwd, Statut) VALUES (NULL,?,?,?)";
			PreparedStatement ps2 = (PreparedStatement) cn.prepareStatement(sql2);
			ps2.setString(1, login);
			ps2.setString(2, passwd);
			ps2.setString(3, statut);
			ps2.executeUpdate();
			String sql3 = "SELECT IdConnexion FROM connexion WHERE login = ?";
			PreparedStatement ps3 = (PreparedStatement) cn.prepareStatement(sql3);
			ps3.setString(1, login);
			rs = ps3.executeQuery();
			while (rs.next()) {
				id = rs.getString("Idconnexion");
			}
			
			if(statut.equals("Enseignant")) {
				sql = "INSERT INTO enseignant (idEnseignant, nomEnseignant, prenomEnseignant, matiere1Enseignant, matiere2Enseignant,"
						+ "matiere2Enseignant_2, Id_Connexion) VALUES (NULL,?,?,'','','',?)";
			}
			else {
				sql = "INSERT INTO etudiant (idEtudiant, nomEtudiant, prenomEtudiant, Id_Connexion) VALUES (NULL,?,?,?)";
			}
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, nom);
			ps.setString(2, prenom);
			ps.setString(3, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
	// méthode pour inscrire une personne
	@FXML
	private void signInAction(ActionEvent event) {
		System.out.println(fonction.getSelectionModel().getSelectedItem());
		
		if(id.getText().isEmpty() || password.getText().isEmpty() || nom.getText().isEmpty() || prenom.getText().isEmpty() || fonction.getSelectionModel().isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Il manque des informations !");
			alert.showAndWait();
		}
		else {
			try {
				signin(nom.getText(), prenom.getText(), fonction.getSelectionModel().getSelectedItem(), id.getText(), password.getText());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Votre demande d'inscription a bien été prise en compte.");
				alert.setContentText("Une secrétaire va traiter votre demande et vous allez être redirigé sur la page de connexion.");
				alert.showAndWait();
				Main.changeScene("fxml/Connexion.fxml");
			} catch (IOException e) {
			}
		}
	}
	
	// méthode pour retourner à la page connexion et annuler l'incription
	@FXML
	private void homeAction(ActionEvent event) {
		try {
			Main.changeScene("fxml/Connexion.fxml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// méthode pour intéragir avec les touches entrée et échap
	@FXML
	private void keyAction(KeyEvent e) {
		if(e.getCode() == KeyCode.ENTER) {
			if(id.getText().isEmpty() || password.getText().isEmpty() || nom.getText().isEmpty() || prenom.getText().isEmpty() || fonction.getSelectionModel().isEmpty()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Il manque des informations !");
				alert.showAndWait();
			}
			else {
				try {
					signin(nom.getText(), prenom.getText(), fonction.getSelectionModel().getSelectedItem(), id.getText(), password.getText());
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information");
					alert.setHeaderText("Votre demande d'inscription a bien été pris en compte.");
					alert.setContentText("Une secrétaire va traiter votre demande et vous allez être redirigé sur la page de connexion.");
					alert.showAndWait();
					Main.changeScene("fxml/Connexion.fxml");
				} catch (IOException er) {
				}
			}
		}
		
		if(e.getCode() == KeyCode.ESCAPE) {
			try {
				Main.changeScene("fxml/Connexion.fxml");
			} catch (IOException er) {
				er.printStackTrace();
			}
		}
	}
}
