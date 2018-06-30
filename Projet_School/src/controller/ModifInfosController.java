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
	@FXML private TextField nom; 
	@FXML private TextField prenom; 
	@FXML private TextField mail; 
	@FXML private TextField telephone; 
	@FXML private Label groupe;
	
	@FXML private Label grp;
	@FXML private Label header;
	
	@FXML private Button buttonHome;
	@FXML private Button buttonSave;
	
	// fonction pour modifier les informations
	public void modif(int id, String identifiant, String nom, String prenom, String mail, String telephone) {
		connect();
		String sql = null;
		String sql2 = null;
		ResultSet rs = null;
		String fonct = null;
		try {
			sql = "UPDATE compte SET identifiant = ? WHERE id = ?";
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, identifiant);
			ps.setInt(2, id);
			ps.executeUpdate();
			String sql3 = "SELECT fonction FROM compte WHERE id = ?";
			PreparedStatement ps3 = (PreparedStatement) cn.prepareStatement(sql3);
			ps3.setInt(1, id);
			rs = ps3.executeQuery();
			while (rs.next()) {
				fonct = rs.getString("fonction");
			}
			if (fonct.equals("Enseignant")) {
				sql2 = "UPDATE enseignant SET identifiant = ?, prenom = ?, nom = ?, mail = ?, telephone = ? WHERE id = ?";
			}
			else {
				sql2 = "UPDATE etudiant SET identifiant = ?, prenom = ?, nom = ?, mail = ?, telephone = ? WHERE id = ?";
			}
			PreparedStatement ps2 = (PreparedStatement) cn.prepareStatement(sql2);
			ps2.setString(1, identifiant);
			ps2.setString(2, prenom);
			ps2.setString(3, nom);
			ps2.setString(4, mail);
			ps2.setString(5, telephone);
			ps2.setInt(6, id);
			ps2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

	// méthode pour sauvegarder les changements
	@FXML
	private void saveAction(ActionEvent event) {
		int idEtudiant = Main.getEtudiant().getId();
		int idEnseignant = Main.getEnseignant().getId();
		
		if(idEtudiant > 0) {
			// appliquer les changements dans la base de données
			modif(idEtudiant, id.getText(), prenom.getText(), nom.getText(), mail.getText(), telephone.getText());
			Main.getEtudiant().setIdentifiant(id.getText());
			Main.getEtudiant().setPrenom(prenom.getText());
			Main.getEtudiant().setNom(nom.getText());
			Main.getEtudiant().setMail(mail.getText());
			Main.getEtudiant().setTelephone(telephone.getText());
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Les modifications ont été sauvegardées !");
			alert.showAndWait();
			
			try {
				Main.changeScene("/fxml/SpaceStudent.fxml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(idEnseignant > 0) {
			// appliquer les changements dans la base de données
			modif(idEnseignant, id.getText(), prenom.getText(), nom.getText(), mail.getText(), telephone.getText());
			Main.getEnseignant().setIdentifiant(id.getText());
			Main.getEnseignant().setPrenom(prenom.getText());
			Main.getEnseignant().setNom(nom.getText());
			Main.getEnseignant().setMail(mail.getText());
			Main.getEnseignant().setTelephone(telephone.getText());
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Les modifications ont été sauvegardées !");
			alert.showAndWait();
			
			try {
				Main.changeScene("/fxml/SpaceTeacher.fxml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// méthode pour retourner à la page d'accueil
	@FXML
	private void homeAction(ActionEvent event) {
		try {
			if(Main.getEtudiant().getId() > 0) {
				Main.changeScene("/fxml/SpaceStudent.fxml");
			}
			else {
				Main.changeScene("/fxml/SpaceTeacher.fxml");
			}
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Les modifications n'ont pas été sauvegardées !");
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
				if(Main.getEtudiant().getId() > 0) {
					Main.changeScene("/fxml/SpaceStudent.fxml");
				}
				else {
					Main.changeScene("/fxml/SpaceTeacher.fxml");
				}
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Les modifications n'ont pas été sauvegardées !");
				alert.showAndWait();
			} catch (IOException er) {
				er.printStackTrace();
			}
		}
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		if(Main.getEnseignant().getId() > 0) {
			id.setText(Main.getEnseignant().getIdentifiant());
			prenom.setText(Main.getEnseignant().getPrenom());
			nom.setText(Main.getEnseignant().getNom());
			mail.setText(Main.getEnseignant().getMail());
			telephone.setText(Main.getEnseignant().getTelephone());
			grp.setText("Nom de votre cours :");
			groupe.setText(Main.getEnseignant().getCours().getNomCours());
		}
		
		if(Main.getEtudiant().getId() > 0) {
			id.setText(Main.getEtudiant().getIdentifiant());
			prenom.setText(Main.getEtudiant().getPrenom());
			nom.setText(Main.getEtudiant().getNom());
			mail.setText(Main.getEtudiant().getMail());
			telephone.setText(Main.getEtudiant().getTelephone());
			groupe.setText(Integer.toString(Main.getEtudiant().getIdGroupe()));
		}
	}
	
}
