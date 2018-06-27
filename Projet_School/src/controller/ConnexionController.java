package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.Connexion;
import main.Main;
	 
// le controller du fichier Connexion.fxml
public class ConnexionController extends Connexion {
	
	private ArrayList<String> infosUser = null;
	Connexion con = new Connexion(cn);
	
		public ConnexionController() {
			super(cn);
		}
	
		@FXML private TextField id;
		@FXML private PasswordField password;
		@FXML private Button buttonCon;
		@FXML private Button buttonSign;
		@FXML private Label error;
		
		
		public boolean connexion (String login, String mdp) {
			connect();
			ResultSet rs = null;
			Boolean b = false;
			try {
				String sql = "SELECT identifiant, password FROM compte WHERE login = ?";
				PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
				ps.setString(1, login);
				rs = ps.executeQuery();
				while (rs.next()) {
					if((login.equals(rs.getString("identifiant"))) && (mdp.equals(rs.getString("password")))){
						b = true;
						infosUser = con.info(login);
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
			// si c'est le bouton pour s'inscrire
			if(event.getSource()== buttonSign) {
				try {
					Main.changeScene("fxml/SignIn.fxml");
				} catch (IOException e) {
					System.err.println(e.getMessage());
					System.out.println("Impossible d'afficher la page d'inscription !");
				}
			}
			else { // si c'est le bouton connexion
				if(connexion(id.getText(), password.getText())) {	
	 				try {
	 					if( !(id.getText().equals("admin")) && !(password.getText().equals("admin")) ) {
	 					// on met les infos de la bd dans l'individu utilisateur de la classe main
							Main.getUser().setNom(infosUser.get(4)); // nom
							Main.getUser().setPrenom(infosUser.get(5)); // prénom
							
							// si la personne est un enseignant on lui ajoute sa matière
							if(infosUser.size() > 7) {
								Main.getUser().setCours(infosUser.get(6));
							}
	 					}
	 					
	 					Main.getUser().setFonction(infosUser.get(2)); // statut
						Main.getUser().setId(Integer.parseInt(infosUser.get(0))); // id
						Main.getUser().setPassword(infosUser.get(1)); // mdp
						
						Main.changeScene("Connected.fxml");
	 				} catch (IOException e) {
						System.err.println(e.getMessage());
						System.out.println("Impossible d'afficher la page home !");
	 				}
	 			}
				else {
					error.setVisible(true);
					password.setText("");
				}			
			}
		}
		
		// méthode pour se connecter en appuyant sur entrer 
		@FXML
		private void keyAction(KeyEvent e) {
			if(e.getCode() == KeyCode.ENTER) {
				if(id.getText().isEmpty()) {
					error.setVisible(true);
					password.setText("");
				}
				
				if(connexion(id.getText(), password.getText())) {	
	 				try {
	 					if( !(id.getText().equals("admin")) && !(password.getText().equals("admin")) ) {
		 					// on met les infos de la bd dans l'individu utilisateur de la classe main
							Main.getUser().setNom(infosUser.get(4)); // nom
							Main.getUser().setPrenom(infosUser.get(5)); // prénom
								
							// si la personne est un enseignant on lui ajoute son cours
							if(infosUser.size() > 6) {
								Main.getUser().setCours(infosUser.get(6));
							}
		 				}
		 					
		 				Main.getUser().setFonction(infosUser.get(2)); // statut
						Main.getUser().setId(Integer.parseInt(infosUser.get(0))); // id
						Main.getUser().setPassword(infosUser.get(1)); // mdp
						
						Main.changeScene("fxml/Connected.fxml");
	 				} catch (IOException er) {
						System.err.println(er.getMessage());
						System.out.println("Impossible d'afficher la page home !");
	 				}
	 			}
				else {
					error.setVisible(true);
					password.setText("");		
				}
			}
		}
}