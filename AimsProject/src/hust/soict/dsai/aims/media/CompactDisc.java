package hust.soict.dsai.aims.media;

import java.time.Duration;
import java.util.ArrayList;

import hust.soict.dsai.aims.exception.PlayerException;

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
    public String formatDuration(float f) {
        Duration duration = Duration.ofSeconds((long) f);
        return String.format("%02d:%02d", duration.toMinutes(), duration.minusMinutes(duration.toMinutes()).getSeconds());
    }
    public String play() throws PlayerException {
        if(this.getLength() > 0) {
            String output =  "Playing CD: " + this.getTitle() + "\n" + 
                            "CD length: " + formatDuration(this.getLength()) + "\n"+ "\n";
            for (Track track : tracks) {
                try {
                    output += track.play() + "\n";
                } catch (PlayerException e) {
                    output += track.getTitle() + "\n" + e.getMessage();
                }
            }
            return output;
            } else {
                throw new PlayerException("ERROR: CD length is non-positive!");
            }
    }
	
}
