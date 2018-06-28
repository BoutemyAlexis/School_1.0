package classes;

//classe non-utilisée
public class Groupe {
    private int id;
	private String mention;
    private int place;
    private Cours cours;
    
    public Groupe() {
    	super();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    public String getMention() {
		return mention;
	}

	public void setMention(String mention) {
		this.mention = mention;
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}
	
}
