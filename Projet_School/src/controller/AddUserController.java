package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.mysql.jdbc.PreparedStatement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.Connexion;
import main.Main;

public class AddUserController extends Connexion {
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
	
	// constructeur obligatoire car classe fille de Connexion
	public AddUserController(Connection cn) {
		super(cn);
	}
	
	// la méthode qui va démarrer la page avec les différents composants avec du texte 
	/*public void initialize(URL location, ResourceBundle resources) {
		if(Main.getAdmin().getId() > 0) {
			fonction.getItems().add("secretaire");
		}
	}*/
	
	// méthode pour créer un utilisateur
	public void signIn(String identifiant, String password, String nom, String prenom, String mail, String telephone, String fonction) {
		unique = true;
		connect();
		ResultSet rs = null;
		String sql = null;
		String sql2 = null;
		String id = null;
		try {
			// on met NULL pour la colonne qui est auto_increment dans la bdd
			sql2 = "INSERT INTO compte (id, identifiant, password, fonction, actif) VALUES (NULL,?,?,?,1)";
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
			if(fonction.equals("etudiant")) {
				sql = "INSERT INTO etudiant (id, identifiant, prenom, nom, mail, telephone, idGroupe)  VALUES (?,?,?,?,?,?,0)";
			}
			if(fonction.equals("secretaire")) {
				sql = "INSERT INTO secretaire (id, identifiant, prenom, nom, mail, telephone)  VALUES (?,?,?,?,?,?)";
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
					alert.setHeaderText("L'inscription et l'activation sont finis !");
					alert.showAndWait();
					if(Main.getAdmin().getId() > 0) {
						Main.changeScene("/fxml/SpaceAdmin.fxml");
					}
					else {
						Main.changeScene("/fxml/SpaceSecretaire.fxml");
					}
					
				} catch (IOException er) {
					er.printStackTrace();
				}
			}
		}
		
		if(e.getCode() == KeyCode.ESCAPE) {
			try {
				if(Main.getAdmin().getId() > 0) {
					Main.changeScene("/fxml/SpaceAdmin.fxml");
				}
				else {
					Main.changeScene("/fxml/SpaceSecretaire.fxml");
				}
			} catch (IOException er) {
				er.printStackTrace();
			}
		}
	}
	
	// méthode pour retourner à la page connexion et annuler l'incription
	@FXML
	private void homeAction(ActionEvent event) {
		try {
			if(Main.getAdmin().getId() > 0) {
				Main.changeScene("/fxml/SpaceAdmin.fxml");
			}
			else {
				Main.changeScene("/fxml/SpaceSecretaire.fxml");
			}
		} catch (IOException e) {
			e.printStackTrace();
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
					alert.setHeaderText("L'inscription et la validation sont finis !");
					alert.showAndWait();
					
					if(Main.getAdmin().getId() > 0) {
						Main.changeScene("/fxml/SpaceAdmin.fxml");
					}
					else {
						Main.changeScene("/fxml/SpaceSecretaire.fxml");
					}
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
}
