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

    public final String getId() {
		return id.get();
	}

	public final String getIdCours() {
		return idCours.get();
	}

	public final String getNomCours() {
		return nomCours.get();
	}

	public final String getNomEnseignant() {
		return nomEnseignant.get();
	}

	public final  String getDate() {
		return date.get();
	}

	public final String getSalle() {
		return salle.get();
	}

	public final String getIdGroupe() {
		return idGroupe.get();
	}
	
	public final StringProperty idProperty() {
		return id;
	}
	public final void setId(StringProperty id) {
		this.id = id;
	}
	public final StringProperty dateProperty() {
		return date;
	}
	public final void setDate(StringProperty date) {
		this.date = date;
	}
	public final StringProperty salleProperty() {
		return salle;
	}
	public final void setSalle(StringProperty salle) {
		this.salle = salle;
	}

	public final StringProperty idCoursProperty() {
		return idCours;
	}

	public final void setIdCours(StringProperty idCours) {
		this.idCours = idCours;
	}

	public final StringProperty nomCoursProperty() {
		return nomCours;
	}

	public final void setNomCours(StringProperty nomCours) {
		this.nomCours = nomCours;
	}

	public final StringProperty nomEnseignantProperty() {
		return nomEnseignant;
	}

	public final void setNomEnseignant(StringProperty nomEnseignant) {
		this.nomEnseignant = nomEnseignant;
	}

	public final StringProperty idGroupeProperty() {
		return idGroupe;
	}

	public final void setIdGroupe(StringProperty idGroupe) {
		this.idGroupe = idGroupe;
	}


}
