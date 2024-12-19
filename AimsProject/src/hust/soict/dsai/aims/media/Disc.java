package hust.soict.dsai.aims.media;

public abstract class Disc extends Media {
	protected float length;
	protected String director;
	public Disc() {
		super();
	}
	public float getLength() {
		return length;
	}
	public String getDirector() {
		return director;
	}
	public Disc(String title, String category, String director, int length, float cost) {
		super();
		this.title = title;
		this.category = category;
		this.director = director;
		this.length = length;
		this.cost = cost;
	}
}
