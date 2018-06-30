package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.PreparedStatement;
import classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.*;
	 
// le controller du fichier Connexion.fxml
public class ConnexionController extends Connexion {
	
	private ArrayList<Object> infosUser = null;
	Connexion con = new Connexion(cn);
	
		public ConnexionController() {
			super(cn);
		}
	
		@FXML private TextField id;
		@FXML private PasswordField password;
		@FXML private Button buttonCon;
		@FXML private Button buttonSign;
		@FXML private Label error;
		@FXML private Label active;
		
		public boolean connexion (String identifiant, String mdp) {
			connect();
			ResultSet rs = null;
			Boolean b = false;
			try {
				String sql = "SELECT identifiant, password FROM compte WHERE identifiant = ? AND actif = 1";
				PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
				ps.setString(1, identifiant);
				rs = ps.executeQuery();
				while (rs.next()) {
					if((identifiant.equals(rs.getString("identifiant"))) && (mdp.equals(rs.getString("password")))){
						b = true;
						infosUser = con.getInfos(identifiant);
						System.out.println("Taille de l'ArrayList contenant les informations = " + infosUser.size());
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				close();
			}
			return b;
		}		
	
		// Méthode pour récupérer ce que l'utilisateur a tapé et comparé
		@FXML
		private void connexionAction(ActionEvent event) {
			active.setVisible(false);
			error.setVisible(false);
			System.out.println(Main.getEtudiant().getId());
			System.out.println(Main.getEnseignant().getId());
			// si c'est le bouton pour s'inscrire
			if(event.getSource()== buttonSign) {
				try {
					Main.changeScene("/fxml/SignIn.fxml");
				} catch (IOException e) {
					System.err.println(e.getMessage());
					System.out.println("Impossible d'afficher la page d'inscription !");
				}
			}
			else { // si c'est le bouton connexion
				if(id.getText().isEmpty() || password.getText().isEmpty() ) {
					error.setVisible(true);
					password.setText("");
				}
				else {
					if(connexion(id.getText(), password.getText())) {	
		 				try {
		 					if( !(id.getText().equals("admin")) && !(password.getText().equals("admin")) ) {
		 						String fonction = infosUser.get(0).toString();
		 						
		 						if(fonction.equals("etudiant") || fonction.equals("Etudiant")) {
		 							Main.getEtudiant().setId(Integer.parseInt(infosUser.get(1).toString()));
		 							Main.getEtudiant().setIdentifiant(infosUser.get(2).toString());
		 							Main.getEtudiant().setPrenom(infosUser.get(3).toString());
		 							Main.getEtudiant().setNom(infosUser.get(4).toString());
		 							Main.getEtudiant().setMail(infosUser.get(5).toString());
		 							Main.getEtudiant().setTelephone(infosUser.get(6).toString());
		 							Main.getEtudiant().setIdGroupe(Integer.parseInt(infosUser.get(7).toString()));
		 							
		 							Main.changeScene("/fxml/HomeStudent.fxml");
		 						}
		 						if(fonction.equals("enseignant")|| fonction.equals("Enseigant")) {
		 							Main.getEnseignant().setId(Integer.parseInt(infosUser.get(1).toString()));
		 							Main.getEnseignant().setIdentifiant(infosUser.get(2).toString());
		 							Main.getEnseignant().setPrenom(infosUser.get(3).toString());
		 							Main.getEnseignant().setNom(infosUser.get(4).toString());
		 							Main.getEnseignant().setMail(infosUser.get(5).toString());
		 							Main.getEnseignant().setTelephone(infosUser.get(6).toString());
		 							Main.getEnseignant().setIdCours(Integer.parseInt(infosUser.get(7).toString()));
		 							Main.getEnseignant().setCours((Cours) infosUser.get(8));

		 							Main.changeScene("/fxml/HomeTeacher.fxml");
		 						}
		 						if(fonction.equals("secretaire")|| fonction.equals("Secetaire")) {
		 							Main.getSecretaire().setId(Integer.parseInt(infosUser.get(1).toString()));
		 							Main.getSecretaire().setIdentifiant(infosUser.get(2).toString());
		 							Main.getSecretaire().setPrenom(infosUser.get(3).toString());
		 							Main.getSecretaire().setNom(infosUser.get(4).toString());
		 							Main.getSecretaire().setMail(infosUser.get(5).toString());
		 							Main.getSecretaire().setTelephone(infosUser.get(6).toString());
		 							
		 							Main.changeScene("/fxml/Connected.fxml");
		 						}
		 					}
		 				} catch (IOException e) {
		 					e.printStackTrace();
							System.err.println(e.getMessage());
							System.out.println("Impossible d'afficher la page suivante !");
		 				}
		 			} else {
		 				active.setVisible(true);
						password.setText("");
		 			}
				}		
			}
		}
		
		// méthode pour se connecter en appuyant sur entrer 
		@FXML
		private void keyAction(KeyEvent e) {
			active.setVisible(false);
			error.setVisible(false);
			if(e.getCode() == KeyCode.ENTER) {
				if(id.getText().isEmpty() || password.getText().isEmpty()) {
					error.setVisible(true);
					password.setText("");
				}
				if(connexion(id.getText(), password.getText())) {	
	 				try {
	 					if( !(id.getText().equals("admin")) && !(password.getText().equals("admin")) ) {
	 						String fonction = infosUser.get(0).toString();
	 						
	 						if(fonction.equals("etudiant") || fonction.equals("Etudiant")) {
	 							Main.getEtudiant().setId(Integer.parseInt(infosUser.get(1).toString()));
	 							Main.getEtudiant().setIdentifiant(infosUser.get(2).toString());
	 							Main.getEtudiant().setPrenom(infosUser.get(3).toString());
	 							Main.getEtudiant().setNom(infosUser.get(4).toString());
	 							Main.getEtudiant().setMail(infosUser.get(5).toString());
	 							Main.getEtudiant().setTelephone(infosUser.get(6).toString());
	 						}
	 						if(fonction.equals("enseignant")|| fonction.equals("Enseigant")) {
	 							Main.getEnseignant().setId(Integer.parseInt(infosUser.get(1).toString()));
	 							Main.getEnseignant().setIdentifiant(infosUser.get(2).toString());
	 							Main.getEnseignant().setPrenom(infosUser.get(3).toString());
	 							Main.getEnseignant().setNom(infosUser.get(4).toString());
	 							Main.getEnseignant().setMail(infosUser.get(5).toString());
	 							Main.getEnseignant().setTelephone(infosUser.get(6).toString());
	 							Main.getEnseignant().setCours((Cours) infosUser.get(8));
	 						}
	 						if(fonction.equals("secretaire")|| fonction.equals("Secetaire")) {
	 							Main.getSecretaire().setId(Integer.parseInt(infosUser.get(1).toString()));
	 							Main.getSecretaire().setIdentifiant(infosUser.get(2).toString());
	 							Main.getSecretaire().setPrenom(infosUser.get(3).toString());
	 							Main.getSecretaire().setNom(infosUser.get(4).toString());
	 							Main.getSecretaire().setMail(infosUser.get(5).toString());
	 							Main.getSecretaire().setTelephone(infosUser.get(6).toString());
	 						}
	 					}
	 					
						Main.changeScene("/fxml/Connected.fxml");
	 				} catch (IOException er) {
						System.err.println(er.getMessage());
						System.out.println("Impossible d'afficher la page home !");
	 				}
	 			}
				else {
					active.setVisible(true);
					password.setText("");		
				}
			}
		}
}