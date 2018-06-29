package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.mysql.jdbc.PreparedStatement;
import classes.Seance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Connexion;
import main.Main;

public class SeeCoursController implements Initializable {
	
	@FXML private TableView<Seance> tableau;
	@FXML private TableColumn<Seance, String> idS;
	@FXML private TableColumn<Seance, String> idC;
	@FXML private TableColumn<Seance, String> nomC;
	@FXML private TableColumn<Seance, String> nomE;
	@FXML private TableColumn<Seance, String> da;
	@FXML private TableColumn<Seance, String> sa;
	
	@FXML Button retour;
	private ObservableList<Seance> cours;
	
	@FXML
	private void HomeAction(ActionEvent event) {
		try {
			Main.changeScene("/fxml/SpaceStudent.fxml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		cours = FXCollections.observableArrayList();
		int idGrp = Main.getEtudiant().getIdGroupe();
		Connexion cn = new Connexion(null);
		try {
			cn.connect();
			// requete pour récupérer les séances de l'étudiant
			String requete = "SELECT * FROM seance WHERE idGroupe = ?";
			PreparedStatement ps;
			ps = (PreparedStatement) cn.getConnection().prepareStatement(requete);
			ps.setInt(1, idGrp);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				// on récupère les infos de la séance
				cours.add(new Seance(Integer.toString(rs.getInt("idSeance")), Integer.toString(rs.getInt("idCours")), rs.getString("nomCours"), rs.getString("nomEnseignant"), rs.getString("date"), Integer.toString(rs.getInt("salle")), Integer.toString(rs.getInt("idGroupe"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		System.out.print(cours.size());
		
		this.tableau.setItems(cours);
		
		this.idS.setCellValueFactory(new PropertyValueFactory<>("id"));
		System.out.println(idS);
		this.idC.setCellValueFactory(new PropertyValueFactory<>("idCours"));
		this.nomC.setCellValueFactory(new PropertyValueFactory<>("nomCours"));
		this.nomE.setCellValueFactory(new PropertyValueFactory<>("nomEnseignant"));
		this.da.setCellValueFactory(new PropertyValueFactory<>("date"));
		this.sa.setCellValueFactory(new PropertyValueFactory<>("salle"));
		
	}
}