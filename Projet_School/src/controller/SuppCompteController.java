package controller;

import java.io.IOException;

import classes.Etudiant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import main.*;

public class SuppCompteController {
	
	@FXML Button annuler;
	@FXML Button supprimer;
	
	// m�thode pour retourner � la page d'accueil
	@FXML
	private void CancelAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/SpaceStudent.fxml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// m�thode pour supprimer le compte
	@FXML
	private void SuppAction(ActionEvent event) {
		Connexion cn = new Connexion(null);
		cn.deleteEtudiant(Main.getEtudiant().getId());
		Main.setEtudiant(new Etudiant());
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("Votre compte a �t� supprim�, vous allez �tre d�connect� !");
		alert.showAndWait();
		try {
			Main.changeScene("/fxml/Connexion.fxml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
