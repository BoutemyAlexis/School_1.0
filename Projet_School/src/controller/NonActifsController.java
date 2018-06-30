package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import classes.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Connexion;
import main.Main;

public class NonActifsController implements Initializable {
	@FXML TableView<Utilisateur> tableau;
    @FXML TableColumn<Utilisateur, String> id = new TableColumn<Utilisateur, String>("Id de l'étudiant");
    @FXML TableColumn<Utilisateur, String> identifiant = new TableColumn<Utilisateur, String>("Identifiant");
    @FXML TableColumn<Utilisateur, String> pre = new TableColumn<Utilisateur, String>("Prénom");
    @FXML TableColumn<Utilisateur, String> nom = new TableColumn<Utilisateur, String>("Nom ");
    @FXML TableColumn<Utilisateur, String> mail = new TableColumn<Utilisateur, String>("Mail ");
    @FXML TableColumn<Utilisateur, String> tel = new TableColumn<Utilisateur, String>("Telephone ");
    @FXML TableColumn<Utilisateur, String> fon = new TableColumn<Utilisateur, String>("Fonction ");
    
    @FXML Button retour;
	@FXML Button Supp;
	@FXML Button Active;
	ObservableList<Utilisateur> users = FXCollections.observableArrayList();
	Connexion cn = new Connexion(null);
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		if(Main.getSecretaire().getId() > 0) {
			users = cn.getUserNonActif();
			if(users.size() == 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Il n'y aucun utilisateur !");
				alert.showAndWait();
			}
		}
		
		this.id.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("id"));
	    this.identifiant.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("identifiant"));
	    this.pre.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("prenom"));
	    this.nom.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("nom"));
	    this.mail.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("mail"));
	    this.tel.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("telephone"));
	    this.fon.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("fonction"));
	    
	    tableau.getColumns().addAll(this.id, this.identifiant, this.pre, this.nom, this.mail, this.tel, this.fon);
	    this.tableau.setItems(this.users);
	}
	
	@FXML
	private void HomeAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/SpaceSecretaire.fxml");
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.out.println("Impossible de retourner à l'accueil !");
		}
	}
	
	@FXML
	private void SuppAction(ActionEvent event) {
		Utilisateur user = tableau.getSelectionModel().getSelectedItem();
		int id = user.getId();
		String fonction = user.getFonction();
		if(fonction.equals("etudiant")) {
			tableau.getItems().remove(user);
			cn.deleteEtudiant(id);
		}
		if(fonction.equals("enseignant")) {
			tableau.getItems().remove(user);
			cn.deleteEnseignant(id);
		}
	}
	
	@FXML
	private void ActiveAction(ActionEvent event) {
		Utilisateur user = tableau.getSelectionModel().getSelectedItem();
		int id = user.getId();
		cn.Activer(id);
		tableau.getItems().remove(user);
	}
}
