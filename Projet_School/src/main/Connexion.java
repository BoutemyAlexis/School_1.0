package main;

import java.sql.*;
import java.util.ArrayList;
import com.mysql.jdbc.PreparedStatement;
import classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// la classe de connexion � la Base de donn�es
public class Connexion {
	private String url = "jdbc:mysql://localhost:3306/school?useSSL=false";
	private String login = "root";
	private String passwd = "";
	protected static Connection cn = null;
	private Statement st = null;
	
	public Connexion(Connection cn) {
		this.cn = cn;
	}

	// m�thode pour se connecter � la base de donn�es
	public void connect() {
		try {
			//Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
			//Etape 2 : r�cup�ration de la connexion
			cn = DriverManager.getConnection(url, login, passwd);
			st = cn.createStatement();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException f) {
			f.printStackTrace();
		} 
	}
	
	public static Connection getConnection() {
		return cn;
	}
	
	public void close() {
		try {
			//Etape 3 : lib�rer ressources de la m�moire
			cn.close();
			st.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Permet de r�cup�rer le statut d'une personne � partir de son login
	public String getFonction(String identifiant) {
		connect();
		String statut = null;;
		ResultSet rs = null;
		try {
			String sql = "SELECT fonction FROM compte WHERE identifiant = ?";
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, login);
			rs = ps.executeQuery();
			while (rs.next()) {
				statut = rs.getString("statut");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
		return statut;
	}
	
	//Permet de r�cup�rer l'id de connexion (auto impl�ment� par la bdd) d'une personne
	// � partir de son login
	public String getId(String identifiant) {
		connect();
		String id = null;;
		ResultSet rs = null;
		try {
			String sql = "SELECT id FROM compte WHERE identifiant = ?";
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, identifiant);
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getString("IdConnexion");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
		return id;
	}
	
	//Permet de r�cup�rer les informations d'une personne gr�ce � son login
	public ArrayList<Object> getInfos(String identifiant) {		
		connect();
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ArrayList<Object> info = new ArrayList<Object>();
		String fonction = null;
		String id = null;
		String sql2 = "";
		int cpt = 1;
		int max = 0;
		try {
			String sql = "SELECT * FROM compte WHERE identifiant = ? AND actif = 1";
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, identifiant);
			rs = ps.executeQuery();
			while (rs.next()) {
				fonction = rs.getString("fonction");
				System.out.println("La personne qui se connecte est un : " + fonction);
				id = rs.getString("id");
				info.add(rs.getString("fonction"));
			}
			if(fonction.equals("enseignant") || fonction.equals("Enseignant")) {
				sql2 = "SELECT * FROM enseignant WHERE id = ?";
				max = 7; //le nombre de colonnes dans la table enseignant
			}
			if(fonction.equals("etudiant") || fonction.equals("Etudiant")) {
				sql2 = "SELECT * FROM etudiant WHERE id = ?";
				max = 7; //le nombre de colonnes dans la table etudiant
			}
			if(fonction.equals("secretaire") || fonction.equals("Secretaire")) {
				sql2 = "SELECT * FROM secretaire WHERE id = ?";
				max = 6; // le nombre de colonnes dans la table secretaire
			}
			if(fonction.equals("administrateur") || fonction.equals("Administrateur")) {
				sql2 = "SELECT * FROM compte WHERE id = ?";
				max = 3;
			}
			// on pr�pare la requ�te choisi au dessus
			PreparedStatement ps2 = (PreparedStatement) cn.prepareStatement(sql2);
			ps2.setString(1, id);
			// on l'execute maintenant pour r�cup�rer la personne
			rs2 = ps2.executeQuery();
			while (rs2.next()) {
				while(cpt <= max){
					info.add(rs2.getString(cpt));
					cpt++;
				}
			}
			if(fonction.equals("enseignant")|| fonction.equals("Enseignant")) {
				// Pour r�cup�rer le cours de l'enseignant
				String sql3 = "SELECT * FROM cours WHERE idEnseignant = ?";
				PreparedStatement ps3;
				ps3 = (PreparedStatement) cn.prepareStatement(sql3);
				ps3.setString(1, id);
				rs3 = ps3.executeQuery();
				Cours cours = new Cours();
				while (rs3.next()) {
					cours.setIdCours(rs3.getString("idCours"));
					cours.setNomCours(rs3.getString("nomCours"));
					cours.setDescription(rs3.getString("description"));
				}
				info.add(cours);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
		return info;			
	}
	
	public void deleteEtudiant(int id) {
		connect();
		try {
			// premi�re requete pour effacer de la table etudiant
			String requete = "DELETE FROM etudiant WHERE id = ?";
			PreparedStatement ps;
			ps = (PreparedStatement) cn.prepareStatement(requete);
			ps.setInt(1, id);
			ps.executeUpdate();
			// deuxi�me requete pour effacer de la table compte
			String requete2 = "DELETE FROM compte WHERE id = ?";
			PreparedStatement ps2;
			ps2 = (PreparedStatement) cn.prepareStatement(requete2);
			ps2.setInt(1, id);
			ps2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void deleteEnseignant(int id) {
		connect();
		try {
			// premi�re requete pour effacer de la table etudiant
			String requete = "DELETE FROM enseignant WHERE id = ?";
			PreparedStatement ps;
			ps = (PreparedStatement) cn.prepareStatement(requete);
			ps.setInt(1, id);
			ps.executeUpdate();
			// deuxi�me requete pour effacer de la table compte
			String requete2 = "DELETE FROM compte WHERE id = ?";
			PreparedStatement ps2;
			ps2 = (PreparedStatement) cn.prepareStatement(requete2);
			ps2.setInt(1, id);
			ps2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void deleteSecretaire(int id) {
		connect();
		try {
			// premi�re requete pour effacer de la table secretaire
			String requete = "DELETE FROM secretaire WHERE id = ?";
			PreparedStatement ps;
			ps = (PreparedStatement) cn.prepareStatement(requete);
			ps.setInt(1, id);
			ps.executeUpdate();
			// deuxi�me requete pour effacer de la table compte
			String requete2 = "DELETE FROM compte WHERE id = ?";
			PreparedStatement ps2;
			ps2 = (PreparedStatement) cn.prepareStatement(requete2);
			ps2.setInt(1, id);
			ps2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public ObservableList<Seance> getCours(int idGrp) {
		connect();
		ObservableList<Seance> cours = FXCollections.observableArrayList();
		try {
			// requete pour r�cup�rer les s�ances de l'�tudiant
			String requete = "SELECT * FROM seance WHERE idGroupe = ?";
			PreparedStatement ps;
			ps = (PreparedStatement) cn.prepareStatement(requete);
			ps.setInt(1, idGrp);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				// on r�cup�re les infos de la s�ance
				cours.add(new Seance(Integer.toString(rs.getInt("idSeance")), Integer.toString(rs.getInt("idCours")), rs.getString("nomCours"), rs.getString("nomEnseignant"), rs.getString("date"), Integer.toString(rs.getInt("salle")), Integer.toString(rs.getInt("idGroupe"))));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cours;
	}
	
	public ObservableList<Seance> getTeacherCours(int idCours) {
		connect();
		ObservableList<Seance> cours = FXCollections.observableArrayList();
		try {
			// requete pour r�cup�rer les s�ances de l'enseignant
			String requete = "SELECT * FROM seance WHERE idCours = ?";
			PreparedStatement ps;
			ps = (PreparedStatement) cn.prepareStatement(requete);
			ps.setInt(1, idCours);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				// on r�cup�re les infos de la s�ance
				cours.add(new Seance(Integer.toString(rs.getInt("idSeance")), Integer.toString(rs.getInt("idCours")), rs.getString("nomCours"), rs.getString("nomEnseignant"), rs.getString("date"), Integer.toString(rs.getInt("salle")), Integer.toString(rs.getInt("idGroupe"))));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cours;
	}
	
	public ObservableList<Seance> getAllCours() {
		connect();
		ObservableList<Seance> cours = FXCollections.observableArrayList();
		try {
			// requete pour r�cup�rer toutes les s�ances
			String requete = "SELECT * FROM seance";
			PreparedStatement ps;
			ps = (PreparedStatement) cn.prepareStatement(requete);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				// on r�cup�re les infos de chaque s�ances
				cours.add(new Seance(Integer.toString(rs.getInt("idSeance")), Integer.toString(rs.getInt("idCours")), rs.getString("nomCours"), rs.getString("nomEnseignant"), rs.getString("date"), Integer.toString(rs.getInt("salle")), Integer.toString(rs.getInt("idGroupe"))));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cours;
	}
	
	public ObservableList<Absence> getAbs(int idEtudiant) {
		connect();
		ObservableList<Absence> abs = FXCollections.observableArrayList();
		try {
			// requete pour r�cup�rer les absences de l'�tudiant
			String requete = "SELECT * FROM absence WHERE idEtudiant= ?";
			PreparedStatement ps;
			ps = (PreparedStatement) cn.prepareStatement(requete);
			ps.setInt(1, idEtudiant);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				// on r�cup�re les infos de l'absence
				abs.add(new Absence(rs.getInt("idAbsence"), rs.getInt("idEtudiant"), rs.getString("nomEtudiant"), rs.getString("nomEnseignant"), rs.getString("date"), rs.getInt("idSeance")) );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return abs;
	}
	
	public ObservableList<Absence> getAllAbs() {
		connect();
		ObservableList<Absence> abs = FXCollections.observableArrayList();
		try {
			// requete pour r�cup�rer les absences de l'�tudiant
			String requete = "SELECT * FROM absence";
			PreparedStatement ps;
			ps = (PreparedStatement) cn.prepareStatement(requete);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				// on r�cup�re les infos de chaque absences
				abs.add(new Absence(rs.getInt("idAbsence"), rs.getInt("idEtudiant"), rs.getString("nomEtudiant"), rs.getString("nomEnseignant"), rs.getString("date"), rs.getInt("idSeance")) );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return abs;
	}
	
	// fonction pour r�cup�rer les gens qui n'ont pas de compte activ�s
	public ObservableList<Utilisateur> getUserNonActif() {
		connect();
		ObservableList<Utilisateur> users = FXCollections.observableArrayList();
		try {
			// requete pour r�cup�rer tous les utilisateurs non inscrit
			String requete = "SELECT * FROM compte WHERE actif = 0";
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(requete);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				users.add(new Utilisateur(rs.getInt("id"), rs.getString("identifiant"), rs.getString("password"), rs.getString("fonction")));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return users;
	}
	
	// fonction pour r�cup�rer tous les �tudiants
	public ObservableList<Utilisateur> getStudents() {
		connect();
		ObservableList<Utilisateur> users = FXCollections.observableArrayList();
		try {
			String requete = "SELECT * FROM etudiant";
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(requete);
			ResultSet rs = ps.executeQuery();
			String fonction = "etudiant";
			while(rs.next()) {
				users.add(new Utilisateur(rs.getInt("id"), rs.getString("identifiant"), rs.getString("prenom"), rs.getString("nom"), rs.getString("mail"), rs.getString("telephone"), fonction));
			}
				
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return users;
	}
	
	// fonction pour r�cup�rer toutes les secretaires
	public ObservableList<Utilisateur> getSecretaires() {
		connect();
		ObservableList<Utilisateur> users = FXCollections.observableArrayList();
		try {
			String requete = "SELECT * FROM secretaire";
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(requete);
			ResultSet rs = ps.executeQuery();
			String fonction = "secretaire";
			while(rs.next()) {
				users.add(new Utilisateur(rs.getInt("id"), rs.getString("identifiant"), rs.getString("prenom"), rs.getString("nom"), rs.getString("mail"), rs.getString("telephone"), fonction));
			}
				
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return users;
	}
	
	// fonction pour r�cup�rer tous les profs
	public ObservableList<Utilisateur> getEnseignants() {
		connect();
		ObservableList<Utilisateur> users = FXCollections.observableArrayList();
		try {
			String requete = "SELECT * FROM enseignant WHERE idCours = 0";
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(requete);
			ResultSet rs = ps.executeQuery();
			String fonction = "enseignant";
			while(rs.next()) {
				users.add(new Utilisateur(rs.getInt("id"), rs.getString("identifiant"), rs.getString("prenom"), rs.getString("nom"), rs.getString("mail"), rs.getString("telephone"), fonction));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return users;
	}
	
	public void Activer(int id) {
		connect();
		try {
			String requete="UPDATE compte SET actif = 1 WHERE id = ?";
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(requete);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
}