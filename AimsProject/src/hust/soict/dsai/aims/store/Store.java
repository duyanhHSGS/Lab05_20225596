package hust.soict.dsai.aims.store;

import java.util.ArrayList;
import hust.soict.dsai.aims.media.Media;

public class Store {
    private ArrayList<Media> itemsInStore;
    // Constructor to initialize the store with an empty ArrayList
    public Store() {
        itemsInStore = new ArrayList<>();
    }
    // Method to add a media item to the store
    public void addMedia(Media media) {
        itemsInStore.add(media);
        System.out.println("Media added to the store: " + media);
    }
    // Method to remove a media item from the store by ID
    public void removeMedia(int id) {
        boolean found = false;
        for (int i = 0; i < itemsInStore.size(); i++) {
            if (itemsInStore.get(i).getId() == id) {
                System.out.println("Media removed from the store: " + itemsInStore.get(i));
                itemsInStore.remove(i);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No media found with ID: " + id);
        }
    }
    // Method to display all media items in the store
    public void displayStore() {
        System.out.println("*************** STORE INVENTORY ***************");
        if (itemsInStore.isEmpty()) {
            System.out.println("No items in the store.");
        } else {
            for (Media media : itemsInStore) {
                System.out.println("ID: " + media.getId() + " - " + media);
            }
        }
        System.out.println("************************************************");
    }
	public Media findMediaByTitle(String title) {
		for (Media media : itemsInStore) {
			if (media.getTitle().equalsIgnoreCase(title)) {
				return media;
			}
		}
		return null;
	}
}
