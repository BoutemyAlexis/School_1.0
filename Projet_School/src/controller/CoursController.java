package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.PreparedStatement;

import classes.Enseignant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import main.Connexion;
import main.Main;

public class CoursController implements Initializable {
	@FXML private Button home;
	@FXML private Button save;
	
	@FXML private TextField id;
	@FXML private TextField enseignant; 
	@FXML private TextField nom; 
	@FXML private TextField desc; 
	
	Connexion cn = new Connexion(null);
	
	public void initialize(URL location, ResourceBundle resources) {
		if(Main.getEnseignant().getId() > 0) {
			Enseignant en = Main.getEnseignant();
			id.setText(Integer.toString(en.getIdCours()));
			id.setEditable(false);
			enseignant.setText(en.getNom());
			nom.setText(en.getCours().getNomCours());
			desc.setText(en.getCours().getDescription());
		}
	}

	// méthode pour retourner à la page d'accueil
	@FXML
	private void homeAction(ActionEvent event) {
		try {
			if(Main.getEnseignant().getId() > 0) {
				Main.changeScene("/fxml/SpaceTeacher.fxml");
			}
			if(Main.getSecretaire().getId() > 0) {
				Main.changeScene("/fxml/SpaceSecretaire.fxml");
			}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Les modifications n'ont pas été sauvegardées !");
			alert.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void saveAction(ActionEvent event) {
		if(Main.getSecretaire().getId() > 0) {
			cn.connect();
			try {
				String req="INSERT INTO cours (idCours, idEnseignant, nomCours, description) VALUES (NULL,?,?,?)";
				PreparedStatement ps;
				ps = (PreparedStatement) cn.getConnection().prepareStatement(req);
				ps.setString(1, enseignant.getText());
				ps.setString(2, nom.getText());
				ps.setString(3, desc.getText());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				cn.close();
			}
		}
		if(Main.getEnseignant().getId() > 0) {
			cn.connect();
			try {
				String req="UPDATE cours SET nomCours = ? ,description = ? WHERE idCours = ?";
				PreparedStatement ps;
				ps = (PreparedStatement) cn.getConnection().prepareStatement(req);
				ps.setString(1, nom.getText());
				ps.setString(2, desc.getText() );
				ps.setString(3, Main.getEnseignant().getCours().getIdCours());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				cn.close();
			}
		}
	}
}
