package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import classes.Seance;
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

public class SeeCoursController implements Initializable {
	
	@FXML TableView<Seance> tableau;
    @FXML TableColumn<Seance, String> idS = new TableColumn<Seance, String>("Id de la séance");
    @FXML TableColumn<Seance, String> idC = new TableColumn<Seance, String>("Id du cours");
    @FXML TableColumn<Seance, String> nomC = new TableColumn<Seance, String>("Nom du cours");
    @FXML TableColumn<Seance, String> nomE = new TableColumn<Seance, String>("Nom de l'enseignant");
    @FXML TableColumn<Seance, String> da = new TableColumn<Seance, String>("Date ");
    @FXML TableColumn<Seance, String> sa = new TableColumn<Seance, String>("Salle ");
	
	@FXML Button retour;
	ObservableList<Seance> cours;
	
	@FXML
	private void HomeAction(ActionEvent event) {
		if(Main.getEtudiant().getId() > 0) {
			try {
				Main.changeScene("/fxml/SpaceStudent.fxml");
			} catch (IOException e) {
				System.err.println(e.getMessage());
				System.out.println("Impossible de retourner à l'accueil !");
			}
		}
		if(Main.getEnseignant().getId() > 0) {
			try {
				Main.changeScene("/fxml/SpaceTeacher.fxml");
			} catch (IOException e) {
				System.err.println(e.getMessage());
				System.out.println("Impossible de retourner à l'accueil !");
			}
		}
		if(Main.getSecretaire().getId() > 0) {
			try {
				Main.changeScene("/fxml/SpaceSecretaire.fxml");
			} catch (IOException e) {
				System.err.println(e.getMessage());
				System.out.println("Impossible de retourner à l'accueil !");
			}
		}
	}
		
	public void initialize(URL arg0, ResourceBundle arg1) {
		cours = FXCollections.observableArrayList();
		Connexion cn = new Connexion(null);
		
		if(Main.getEtudiant().getId() > 0) {
			int idGrp = Main.getEtudiant().getIdGroupe();
			cours = cn.getCours(idGrp);
			if(cours.size() == 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Vous n'avez aucun cours pour le moment !");
				alert.showAndWait();
			}
		}
		if(Main.getEnseignant().getIdCours() > 0) {
			int idCours = Main.getEnseignant().getIdCours();
			cours = cn.getTeacherCours(idCours);
			if(cours.size() == 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Vous n'avez aucun cours pour le moment !");
				alert.showAndWait();
			}
		}
		if(Main.getSecretaire().getId() > 0) {
			cours = cn.getAllCours();
			if(cours.size() == 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Il n'y a aucune séance de cours !");
				alert.showAndWait();
			}
		}
		
	    this.idS.setCellValueFactory(new PropertyValueFactory<Seance, String>("id"));
	    this.idC.setCellValueFactory(new PropertyValueFactory<Seance, String>("idCours"));
	    this.nomC.setCellValueFactory(new PropertyValueFactory<Seance, String>("nomCours"));
	    this.nomE.setCellValueFactory(new PropertyValueFactory<Seance, String>("nomEnseignant"));
	    this.da.setCellValueFactory(new PropertyValueFactory<Seance, String>("date"));
	    this.sa.setCellValueFactory(new PropertyValueFactory<Seance, String>("salle"));

	    tableau.getColumns().addAll(this.idS, this.idC, this.nomC, this.nomE, this.da, this.sa);
	    this.tableau.setItems(this.cours);
	}
}