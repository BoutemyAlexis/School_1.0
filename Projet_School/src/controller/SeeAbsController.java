package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import classes.Absence;
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

public class SeeAbsController implements Initializable {
	
	@FXML TableView<Absence> tableau;
	@FXML TableColumn<Absence, String> idA = new TableColumn<Absence, String>("Id de l'absence");
	@FXML TableColumn<Absence, String> idE = new TableColumn<Absence, String>("Id de l'étudiant");
    @FXML TableColumn<Absence, String> date = new TableColumn<Absence, String>("Absent le ");
    @FXML TableColumn<Absence, String> nomEt = new TableColumn<Absence, String>("Nom de l'etudiant");
    @FXML TableColumn<Absence, String> nomE = new TableColumn<Absence, String>("Nom de l'enseignant");
    @FXML TableColumn<Absence, String> idS = new TableColumn<Absence, String>("Id de la séance");
	
	@FXML Button retour;
	ObservableList<Absence> abs;
	
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
		abs = FXCollections.observableArrayList();
		Connexion cn = new Connexion(null);
		
		if(Main.getEtudiant().getId() > 0) {
			int id = Main.getEtudiant().getId();
			abs = cn.getAbs(id);
		
			if(abs.size() == 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Vous n'avez aucunes absences pour le moment !");
				alert.showAndWait();
			}
		}
		if(Main.getSecretaire().getId() > 0) {
			abs = cn.getAllAbs();
			if(abs.size() == 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Il n'y a aucunes absences !");
				alert.showAndWait();
			}
		}
	    this.idA.setCellValueFactory(new PropertyValueFactory<Absence, String>("id"));
	    this.idE.setCellValueFactory(new PropertyValueFactory<Absence, String>("idEtudiant"));
	    this.nomEt.setCellValueFactory(new PropertyValueFactory<Absence, String>("nomEtudiant"));
	    this.nomE.setCellValueFactory(new PropertyValueFactory<Absence, String>("nomEnseignant"));
	    this.date.setCellValueFactory(new PropertyValueFactory<Absence, String>("date"));
	    this.idS.setCellValueFactory(new PropertyValueFactory<Absence, String>("idSeance"));

	    tableau.getColumns().addAll(this.idA, this.idE, this.nomEt, this.nomE, this.date, this.idS);
	    this.tableau.setItems(this.abs);
	}
}
