package hust.soict.dsai.aims.media;

public class Track implements Playable{
    private String title;
    private int length;
    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }
    public String getTitle() {
        return title;
    }
    public int getLength() {
        return length;
    }
    public String toString() {
        return "Track - Title: " + title +
               ", Length: " + length + " mins";
    }
	public void play() {
		System.out.println("Playing Track: " + this.getTitle());
		System.out.println("Track length: " + this.getLength());
	}	
	// override the original method 
	public boolean equals(Object anotherObject) {
		if (this == anotherObject) return true; // save time
		if (anotherObject instanceof Track) {
			Track track = (Track) anotherObject;
			return (this.getTitle().equals(track.getTitle()) && this.getLength() == track.getLength());
		}
		return false;
	}
}
