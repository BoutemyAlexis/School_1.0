package classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Seance {
    private StringProperty id;
    private StringProperty idCours;
	private StringProperty nomCours;
	private StringProperty nomEnseignant;
    private StringProperty date;
    private StringProperty salle;
    private StringProperty idGroupe;
    
    public Seance() {
    	super();
    }
    
    public Seance(String id, String idCours, String nomCours, String nomEnseignant, String date, String salle, String idGroupe) {
		super();
		this.id = new SimpleStringProperty(id);
		this.idCours = new SimpleStringProperty(idCours);
		this.nomCours = new SimpleStringProperty(nomCours);
		this.nomEnseignant = new SimpleStringProperty(nomEnseignant);
		this.date = new SimpleStringProperty(date);
		this.salle = new SimpleStringProperty(salle);
		this.idGroupe = new SimpleStringProperty(idGroupe);
	}

	public String getId() {
		return id.get();
	}
	public void setId(StringProperty id) {
		this.id = id;
	}
	public String getDate() {
		return date.get();
	}
	public void setDate(StringProperty date) {
		this.date = date;
	}
	public String getSalle() {
		return salle.get();
	}
	public void setSalle(StringProperty salle) {
		this.salle = salle;
	}

	public String getIdCours() {
		return idCours.get();
	}

	public void setIdCours(StringProperty idCours) {
		this.idCours = idCours;
	}

	public String getNomCours() {
		return nomCours.get();
	}

	public void setNomCours(StringProperty nomCours) {
		this.nomCours = nomCours;
	}

	public String getNomEnseignant() {
		return nomEnseignant.get();
	}

	public void setNomEnseignant(StringProperty nomEnseignant) {
		this.nomEnseignant = nomEnseignant;
	}

	public String getIdGroupe() {
		return idGroupe.get();
	}

	public void setIdGroupe(StringProperty idGroupe) {
		this.idGroupe = idGroupe;
	}


}
