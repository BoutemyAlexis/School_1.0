package main;

import java.sql.*;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import classes.Cours;

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
		int max;
		try {
			String sql = "SELECT * FROM compte WHERE identifiant = ?";
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, identifiant);
			rs = ps.executeQuery();
			while (rs.next()) {
				fonction = rs.getString("fonction");
				id = rs.getString("id");
				info.add(rs.getString("fonction"));
			}
			if(fonction.equals("enseignant") || fonction.equals("Enseignant")) {
				sql2 = "SELECT * FROM enseignant WHERE id = ?";
				max = 7; //le nombre de colonnes dans la table enseignant
			}
			if(fonction.equals("etudiant") || fonction.equals("Etudiant")) {
				sql2 = "SELECT * FROM etudiant WHERE id = ?";
				max = 6; //le nombre de colonnes dans la table etudiant
			}
			else {
				sql2 = "SELECT * FROM etudiant WHERE id = ?";
				max = 6; // le nombre de colonnes dans la table secretaire
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
				ps3.setInt(1, (int) info.get(7));
				rs3 = ps3.executeQuery();
				Cours cours = new Cours();
				while (rs3.next()) {
					cours.setIdCours(rs3.getString("idCours"));
					cours.setNomCours(rs3.getString("nomCours"));
					cours.setDescription(rs3.getString("description"));
					info.add(cours);
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
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			// deuxi�me requete pour effacer de la table compte
			String requete2 = "DELETE FROM compte WHERE id = ?";
			PreparedStatement ps2;
			ps2 = (PreparedStatement) cn.prepareStatement(requete2);
			ps2.setLong(1, id);
			ResultSet rs2 = ps.executeQuery();
			// troisi�me requete pour effacer de la table groupe
			String requete3 = "DELETE FROM groupe WHERE id = ?";
			PreparedStatement ps3;
			ps3 = (PreparedStatement) cn.prepareStatement(requete3);
			ps3.setLong(1, id);
			ResultSet rs3 = ps.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
}