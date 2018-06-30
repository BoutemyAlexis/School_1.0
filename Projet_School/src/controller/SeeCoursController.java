package controller;

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
	
	@FXML TableView<Seance> tableau;
	@FXML TableColumn<Seance, String> idS;
	@FXML TableColumn<Seance, String> idC;
	@FXML TableColumn<Seance, String> nomC;
	@FXML TableColumn<Seance, String> nomE;
	@FXML TableColumn<Seance, String> da;
	@FXML TableColumn<Seance, String> sa;
	
	@FXML Button retour;
	ObservableList<Seance> cours;
	
	@FXML
	private void HomeAction(ActionEvent event) {
		
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
		System.out.print(cours.get(0).getId());
		System.out.print(cours.get(0).getIdCours());
		System.out.print(cours.get(0).getNomCours());
		System.out.print(cours.get(0).getNomEnseignant());
		System.out.print(cours.get(0).getDate());
		System.out.println(cours.get(0).getSalle());
		
		
		idS.setCellValueFactory(new PropertyValueFactory<Seance, String>("id"));
		idC.setCellValueFactory(new PropertyValueFactory<Seance, String>("idCours"));
		nomC.setCellValueFactory(new PropertyValueFactory<Seance, String>("nomCours"));
		nomE.setCellValueFactory(new PropertyValueFactory<Seance, String>("nomEnseignant"));
		da.setCellValueFactory(new PropertyValueFactory<Seance, String>("date"));
		sa.setCellValueFactory(new PropertyValueFactory<Seance, String>("salle"));
		
		
		tableau.setItems(cours);
	}
}