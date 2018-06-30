package classes;

// les etudiants, enseignants et secretaires sont des utilisateurs
public class Utilisateur {
	private int id;
	private String identifiant;
	private String password;
	private String prenom;
	private String nom;
	private String mail;
	private String telephone;
	private String fonction;
	
	public Utilisateur(int i, String id, String pw, String fo) {
		this.id = i;
		this.identifiant = id;
		this.password = pw;
		this.fonction = fo;
	}
	
	public Utilisateur(int i, String id, String prenom, String nom, String mail, String telephone, String fo) {
		this.id = i;
		this.identifiant = id;
		this.prenom = prenom;
		this.nom = nom;
		this.mail = mail;
		this.telephone = telephone;
		this.fonction = fo;
	}
	
	public Utilisateur() {
		super();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int i) {
		this.id = i;
	}
	
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	
}
