package hust.soict.dsai.aims.media;

public class DigitalVideoDisc extends Disc implements Playable{
	private static int nbDigitalVideoDiscs = 0;
	// update this thing so the following constructors overload this
    // comprehensive constructor
	public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title,category,director,length,cost);
        nbDigitalVideoDiscs++;
        setId(nbDigitalVideoDiscs);
    }
    public DigitalVideoDisc(String title) {
        this(title, null, null, 0, 0.0f);
    }
    public DigitalVideoDisc(String title, String category, float cost) {
        this(title, category, null, 0, cost); 
    }
    public String toString() {
        return "DVD - " + title + 
               " - " + (category != null ? category : "Unknown") +
               " - " + (director != null ? director : "Unknown") +
               " - " + (length > 0 ? length + " mins" : "Unknown Length") +
               ": " + cost + " $";
    }
    public boolean isMatch(String title) {
        return this.title != null && this.title.equalsIgnoreCase(title);
    }
	public void play() {
		System.out.println("Playing DVD: " + this.getTitle());
		System.out.println("DVD length: " + this.getLength());
	}
}