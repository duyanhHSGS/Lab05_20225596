package hust.soict.dsai.aims.media;

import java.time.Duration;
import hust.soict.dsai.aims.exception.PlayerException;

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
    public String formatDuration(int durationInSeconds) {
        Duration duration = Duration.ofSeconds(durationInSeconds);
        return String.format("%02d:%02d", duration.toMinutes(), duration.minusMinutes(duration.toMinutes()).getSeconds());
    }
    public String play() throws PlayerException {
        if (this.getLength() > 0) {
            return "Playing track: " + this.getTitle() + "\n" + 
                "Track length: " + formatDuration(this.getLength());
        } else {
            throw new PlayerException("ERROR: Track length is non-positive!");
        }
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
