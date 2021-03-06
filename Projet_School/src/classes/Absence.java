package classes;

public class Absence {
	private int id;
	private int idEtudiant;
	private String nomEtudiant;
	private String nomEnseignant;
	private String date;
	private int idSeance;
	
	public Absence() {
		super();
	}
	
	public Absence(int i, int idEtu, String nomEt, String nomEns, String da, int idSeance) {
		this.id = i;
		this.idEtudiant = idEtu;
		this.nomEtudiant = nomEt;
		this.nomEnseignant = nomEns;
		this.date = da;
		this.idSeance = idSeance;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}


	public int getIdEtudiant() {
		return idEtudiant;
	}

	public void setIdEtudiant(int idEtudiant) {
		this.idEtudiant = idEtudiant;
	}

	public String getNomEnseignant() {
		return nomEnseignant;
	}

	public void setNomEnseignant(String nomEnseignant) {
		this.nomEnseignant = nomEnseignant;
	}

	public int getIdSeance() {
		return idSeance;
	}

	public void setIdSeance(int idSeance) {
		this.idSeance = idSeance;
	}

	public String getNomEtudiant() {
		return nomEtudiant;
	}

	public void setNomEtudiant(String nomEtudiant) {
		this.nomEtudiant = nomEtudiant;
	}
	
}
