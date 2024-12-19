package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();
    public CompactDisc() {
        super();
    }
    // comprehensive Constructor
    public CompactDisc(String title, String category, String director, int length, float cost, String artist) {
        super(title, category, director, length, cost);
        this.artist = artist;
    }
    public String getArtist() {
        return artist;
    }
    public void addTrack(Track track) {
        if (tracks.contains(track)) {
        	System.out.println("Track " + track.getTitle() + " is already in the list.");
        	return;
        }
        tracks.add(track);
        System.out.println("Track " + track.getTitle() + " has been successfully added.");
    }
    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track " + track.getTitle() + " has been successfully removed.");
        } else {
            System.out.println("Track " + track.getTitle() + " is not in the list.");
        }
    }
    public float getLength() {
        float totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }
    public String toString() {
        return "CD - " + title + 
               " - " + (category != null ? category : "Unknown") +
               " - " + (director != null ? director : "Unknown") +
               " - " + (length > 0 ? length + " mins" : "Unknown Length") +
               " - Artist: " + (artist != null ? artist : "Unknown") +
               ": " + cost + " $";
    }
	public void play() {
		System.out.println("Playing CD: " + this.getTitle());
		System.out.println("CD Artist: " + this.getArtist());
		System.out.println("CD total length: " + this.getLength() + " mins");
		for (Track track : tracks) {
			track.play();
		}
	}
}
