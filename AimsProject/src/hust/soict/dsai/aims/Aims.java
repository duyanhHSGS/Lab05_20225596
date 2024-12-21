package hust.soict.dsai.aims;

import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.screen.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Aims {	
	public static void loadStoreData(Store store) {
	    try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] mediaInfo = line.split(",");
	            Media media = null;
	            switch (mediaInfo[0]) {
	                case "DVD":
	                    if (mediaInfo.length == 2) {
	                        media = new DigitalVideoDisc(mediaInfo[1]);
	                    } else if (mediaInfo.length == 4) {
	                        media = new DigitalVideoDisc(mediaInfo[1], mediaInfo[2], Float.parseFloat(mediaInfo[3]));
	                    } else if (mediaInfo.length == 6) {
	                        media = new DigitalVideoDisc(
	                            mediaInfo[1],
	                            mediaInfo[2],
	                            mediaInfo[3],
	                            Integer.parseInt(mediaInfo[4]),
	                            Float.parseFloat(mediaInfo[5])
	                        );
	                    }
	                    break;
	                case "Book":
	                    if (mediaInfo.length >= 4) {
	                        Book book = new Book();
	                        book.setTitle(mediaInfo[1]);
	                        book.setCategory(mediaInfo[2]);
	                        book.setCost(Float.parseFloat(mediaInfo[3]));
	                        for (int i = 4; i < mediaInfo.length; i++) {
	                            book.getAuthors().add(mediaInfo[i]);
	                        }
	                        media = book;
	                    }
	                    break;
	                case "CD":
	                    if (mediaInfo.length >= 6) {
	                        CompactDisc cd = new CompactDisc(
	                            mediaInfo[1],
	                            mediaInfo[2],
	                            mediaInfo[3],
	                            Integer.parseInt(mediaInfo[4]),
	                            Float.parseFloat(mediaInfo[5]),
	                            mediaInfo[6]
	                        );
	                        media = cd;
	                        if (media != null) {
	                            for (int i = 7; i < mediaInfo.length; i += 2) {
	                                if (i + 1 < mediaInfo.length) {
	                                    Track track = new Track(mediaInfo[i], Integer.parseInt(mediaInfo[i + 1]));
	                                    cd.addTrack(track);
	                                }
	                            }
	                        }
	                    }
	                    break;
	                default:
	                    continue; // skip
	            }
	            if (media != null) {
	                store.addMedia(media);
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace(); // debug
	    }
	}
	//initiate
	private static Cart cart = new Cart();
	private static Store store = new Store();
//---------------------------------------------------------------------------------
	//main
//---------------------------------------------------------------------------------
    public static void main(String[] args) {
        loadStoreData(store);
        new StoreScreen(store);
    }
//---------------------------------------------------------------------------------     
}
    