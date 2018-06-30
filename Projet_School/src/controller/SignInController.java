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
	@FXML private TextField mail; 
	@FXML private TextField telephone; 
	@FXML private ChoiceBox<String> fonction; 
	
	@FXML private Button buttonSignIn;
	@FXML private Button buttonHome;
	public boolean unique = true;
	
	public void signIn(String identifiant, String password, String nom, String prenom, String mail, String telephone, String fonction) {
		unique = true;
		connect();
		ResultSet rs = null;
		String sql = null;
		String sql2 = null;
		String id = null;
		try {
			// on met NULL pour la colonne qui est auto_increment dans la bdd
			sql2 = "INSERT INTO compte (id, identifiant, password, fonction, actif) VALUES (NULL,?,?,?,0)";
			PreparedStatement ps2 = (PreparedStatement) cn.prepareStatement(sql2);
			ps2.setString(1, identifiant);
			ps2.setString(2, password);
			ps2.setString(3, fonction);
			ps2.executeUpdate();
			String sql3 = "SELECT id FROM compte WHERE identifiant = ?";
			PreparedStatement ps3 = (PreparedStatement) cn.prepareStatement(sql3);
			ps3.setString(1, identifiant);
			rs = ps3.executeQuery();
			while (rs.next()) {
				id = rs.getString("id");
			}
			if(fonction.equals("enseignant")) {
				sql = "INSERT INTO enseignant (id, identifiant, prenom, nom, mail, telephone, idCours) VALUES (?,?,?,?,?,?,0)";
			}
			else {
				sql = "INSERT INTO etudiant (id, identifiant, prenom, nom, mail, telephone, idGroupe)  VALUES (?,?,?,?,?,?,0)";
			}
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, identifiant);
			ps.setString(3, prenom);
			ps.setString(4, nom);
			ps.setString(5, mail);
			ps.setString(6, telephone);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			unique = false;
		}
		finally {
			close();
		}
	}
	
	// méthode pour inscrire une personne
	@FXML
	private void signInAction(ActionEvent event) {
		
		if(id.getText().isEmpty() || password.getText().isEmpty() || nom.getText().isEmpty() || prenom.getText().isEmpty() || mail.getText().isEmpty() || telephone.getText().isEmpty() || fonction.getSelectionModel().isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Il manque des informations !");
			alert.showAndWait();
		}
		else {
			try {
				signIn(id.getText(), password.getText(), prenom.getText(), nom.getText(), mail.getText(), telephone.getText(), fonction.getSelectionModel().getSelectedItem() );
				
				if(unique) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information");
					alert.setHeaderText("Votre demande d'inscription a bien été prise en compte.");
					alert.setContentText("Une secrétaire va traiter votre demande et vous allez être redirigé sur la page de connexion.");
					alert.showAndWait();
					
					Main.changeScene("/fxml/Connexion.fxml");
				}
				else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information");
					alert.setHeaderText("Inscription impossible !");
					alert.setContentText("Cet identifiant est déjà utilisé, veuillez en choisir un autre !");
					alert.showAndWait();
				}
			} catch (IOException e) {
			}
		}
	}
	
	// méthode pour retourner à la page connexion et annuler l'incription
	@FXML
	private void homeAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/Connexion.fxml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// méthode pour intéragir avec les touches entrée et échap
	@FXML
	private void keyAction(KeyEvent e) {
		if(e.getCode() == KeyCode.ENTER) {
			if(id.getText().isEmpty() || password.getText().isEmpty() || nom.getText().isEmpty() || prenom.getText().isEmpty() || mail.getText().isEmpty() || telephone.getText().isEmpty() || fonction.getSelectionModel().isEmpty()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Il manque des informations !");
				alert.showAndWait();
			}
			else {
				try {
					signIn(id.getText(), password.getText(), prenom.getText(), nom.getText(), mail.getText(), telephone.getText(), fonction.getSelectionModel().getSelectedItem() );
					Alert alert = new Alert(AlertType.INFORMATION);
					
					alert.setTitle("Information");
					alert.setHeaderText("Votre demande d'inscription a bien été pris en compte.");
					alert.setContentText("Une secrétaire va traiter votre demande et vous allez être redirigé sur la page de connexion.");
					alert.showAndWait();
					Main.changeScene("/fxml/Connexion.fxml");
				} catch (IOException er) {
					er.printStackTrace();
				}
			}
		}
		
		if(e.getCode() == KeyCode.ESCAPE) {
			try {
				Main.changeScene("/fxml/Connexion.fxml");
			} catch (IOException er) {
				er.printStackTrace();
			}
		}
	}
}
