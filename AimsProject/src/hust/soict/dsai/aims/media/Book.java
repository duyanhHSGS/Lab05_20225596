package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
	private List<String> authors = new ArrayList<String>();
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	// comprehensive constructor
	public Book(String title, String category, float cost, String... authors) {
	    this.title = title;
	    this.category = category;
	    this.cost = cost;
	    for (String author : authors) {
	        this.authors.add(author);
	    }
	}
	public Book() {
	}
	public String toString() {
	    return "Book - " + getTitle() +
	           " - " + (getCategory() != null ? getCategory() : "Unknown Category") +
	           " - " + getAuthors().toString() +
	           ": " + getCost() + " $";
	}

}