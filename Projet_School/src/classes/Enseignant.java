package classes;


public class Enseignant extends Utilisateur {
	private Cours cours;

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}
}
