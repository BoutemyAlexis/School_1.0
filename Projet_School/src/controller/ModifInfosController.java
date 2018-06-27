package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.mysql.jdbc.PreparedStatement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.Connexion;
import main.Main;

public class ModifInfosController extends Connexion implements Initializable {

	public ModifInfosController() {
		super(cn);
	}

	@FXML private TextField id;
	@FXML private TextField password;
	@FXML private TextField nom; 
	@FXML private TextField prenom; 
	@FXML private TextField mail; 
	@FXML private TextField telephone; 
	
	@FXML private Label header;
	
	@FXML private Button buttonHome;
	@FXML private Button buttonSave;
	/*
	public void modif(String nom, String prenom, String login, String passwd) {
		connect();
		String sql = null;
		String sql2 = null;
		String s = null;
		String id = null;
		ResultSet rs = null;
		try {
			sql = "UPDATE connexion SET login = ?, passwd = ? WHERE login = ?";
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, passwd);
			ps.setString(3, Integer.toString(Main.getUser().getId()));
			ps.executeUpdate();
			String sql3 = "SELECT IdConnexion, statut FROM connexion WHERE login = ?";
			PreparedStatement ps3 = (PreparedStatement) cn.prepareStatement(sql3);
			ps3.setString(1, login);
			rs = ps3.executeQuery();
			while (rs.next()) {
				id = rs.getString("IdConnexion");
				s = rs.getString("statut");
			}
			if (s.equals("Enseignant")) {
				sql2 = "UPDATE enseignant SET nomEnseignant = ?, prenomEnseignant = ? WHERE Id_Connexion = ?";
			}
			else {
				sql2 = "UPDATE etudiant SET nomEtudiant = ?, prenomEtudiant = ? WHERE Id_Connexion = ?";
			}
			PreparedStatement ps2 = (PreparedStatement) cn.prepareStatement(sql2);
			ps2.setString(1, nom);
			ps2.setString(2, prenom);
			ps2.setString(3, id);
			ps2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	*/

	// méthode pour retourner à la page d'accueil
	@FXML
	private void homeAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/HomeStudent.fxml");
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Les modifications n'ont pas été sauvegardé !");
			alert.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// méthode pour intéragir avec la touche échap et retourner à l'accueil
	@FXML
	private void keyAction(KeyEvent e) {
		if(e.getCode() == KeyCode.ESCAPE) {
			try {
				Main.changeScene("/fxml/HomeStudent.fxml");
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Les modifications n'ont pas été sauvegardé !");
				alert.showAndWait();
			} catch (IOException er) {
				er.printStackTrace();
			}
		}
	}
		/*
	// méthode pour sauvegarder les changements
	@FXML
	private void saveAction(ActionEvent event) {
		// appliquer les changements dans la base de données
		modif(nom.getText(), prenom.getText(), id.getText(), password.getText());
		
		Main.getUser().setId(Integer.parseInt(id.getText()));
		Main.getUser().setPassword(password.getText());
		Main.getUser().setNom(nom.getText());
		Main.getUser().setPrenom(prenom.getText());
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("Les modifications ont été sauvegardé !");
		alert.showAndWait();
		try {
			Main.changeScene("fxml/Connected.fxml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	public void initialize(URL arg0, ResourceBundle arg1) {
		if(Main.getEnseignant().getId() > 0) {
			id.setText(Integer.toString(Main.getEnseignant().getId()));
			nom.setText(Main.getEnseignant().getNom());
			prenom.setText(Main.getEnseignant().getPrenom());
			mail.setText(Main.getEnseignant().getMail());
			telephone.setText(Main.getEnseignant().getTelephone());
		}
		if(Main.getEtudiant().getId() > 0) {
			id.setText(Integer.toString(Main.getEtudiant().getId()));
			nom.setText(Main.getEtudiant().getNom());
			prenom.setText(Main.getEtudiant().getPrenom());
			mail.setText(Main.getEtudiant().getMail());
			telephone.setText(Main.getEtudiant().getTelephone());
		}
	}
	
}
