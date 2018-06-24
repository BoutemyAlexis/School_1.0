package classes;


public class Cours {
    private String idCours;
    private Enseignant enseignant;
    private String nomCours;
    private String description;
    
    public Cours() {
    	super();
    }
    
	public String getIdCours() {
		return idCours;
	}
	public void setIdCours(String idCours) {
		this.idCours = idCours;
	}
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	public String getNomCours() {
		return nomCours;
	}
	public void setNomCours(String nomCours) {
		this.nomCours = nomCours;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
}
