package hust.soict.dsai.aims;

import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.cart.Cart;

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
        Scanner scanner = new Scanner(System.in);            
        mainMenu(store,scanner);    
    }
//---------------------------------------------------------------------------------    
//main menu    
    public static void mainMenu(Store store, Scanner scanner) {
    	boolean exit = false;
    	 while (!exit) {
    	     System.out.println("AIMS: ");
    	     System.out.println("--------------------------------");
    	     System.out.println("1. View store");
    	     System.out.println("2. Update store");
    	     System.out.println("3. See current cart");
    	     System.out.println("0. Exit");
    	     System.out.println("--------------------------------");
    	     System.out.println("Please choose a number: 0-1-2-3");
    	     
             int choice = scanner.nextInt();
             scanner.nextLine(); // Consume newline

             switch (choice) {
                 case 1:
                     viewStore(store, scanner);
                     break;
                 case 2:
                     updateStore(store, scanner);
                     break;
                 case 3:
                     seeCurrentCart(scanner);
                     break;
                 case 0:
                     exit = true;
                     System.out.println("Exiting the program.");
                     break;
                 default:
                     System.out.println("Invalid choice. Please try again.");
             }
        }
    }
//---------------------------------------------------------------------------------  
// main menu -> view store
    public static void viewStore(Store store, Scanner scanner) {
        store.displayStore();
        storeMenu(store, scanner);
    }
    public static void storeMenu(Store store, Scanner scanner) {
        boolean back = false;
        
        while (!back) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. See a media’s details");
            System.out.println("2. Add a media to cart");
            System.out.println("3. Play a media");
            System.out.println("4. See current cart");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3-4");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    seeMediaDetails(store, scanner);
                    break;
                case 2:
                    addMediaToCart(store, scanner);
                    break;
                case 3:
                    playMedia(store, scanner);
                    break;
                case 4:
                    seeCurrentCart(scanner);
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
//---------------------------------------------------------------------------------  
// main menu -> view store -> see a media's detail
    public static void seeMediaDetails(Store store, Scanner scanner) {
        System.out.println("Enter the title of the media: ");
        String title = scanner.nextLine();
        Media media = store.findMediaByTitle(title);
        if (media != null) {
            System.out.println(media.toString());
            mediaDetailsMenu(store, media, scanner);
        } else {
            System.out.println("Media not found.");
        }
    }
//---------------------------------------------------------------------------------  
// main menu -> view store -> see a media's detail -> enters a media's name    
    public static void mediaDetailsMenu(Store store, Media media, Scanner scanner) {
        boolean back = false;
        
        while (!back) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add to cart");
            if (media instanceof DigitalVideoDisc || media instanceof CompactDisc) {
                System.out.println("2. Play");
            }
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1" + (media instanceof DigitalVideoDisc || media instanceof CompactDisc ? "-2" : ""));
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    addMediaToCart(media);
                    break;
                case 2:
                    if (media instanceof DigitalVideoDisc || media instanceof CompactDisc) {
                        playMedia(media);
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
//---------------------------------------------------------------------------------  
// main menu -> view store -> see a media's detail -> enters a media's name -> add to cart
    public static void addMediaToCart(Media media) {
            cart.addMedia(media); // Add media to cart
    }
//---------------------------------------------------------------------------------  
// main menu -> view store -> see a media's detail -> enters a media's name -> play
    public static void playMedia(Media media) {
        if (media instanceof DigitalVideoDisc) {
            ((DigitalVideoDisc) media).play();
        } else if (media instanceof CompactDisc) {
            ((CompactDisc) media).play();
        } else {
            System.out.println("Media cannot be played.");
        }
    }
//---------------------------------------------------------------------------------  
// main menu -> view store -> add a media to cart
    public static void addMediaToCart(Store store, Scanner scanner) {
        System.out.println("Enter the title of the media to add to cart: ");
        String title = scanner.nextLine();
        Media media = store.findMediaByTitle(title);
        if (media != null) cart.addMedia(media); // Add media to cart
        else System.out.println("The media was not found!");
    }
//---------------------------------------------------------------------------------  
// main menu -> view store -> play a media
    public static void playMedia(Store store, Scanner scanner) {
        System.out.println("Enter the title of the media to play: ");
        String title = scanner.nextLine();
        Media media = store.findMediaByTitle(title);
        
        if (media != null && (media instanceof DigitalVideoDisc || media instanceof CompactDisc)) {
            if (media instanceof DigitalVideoDisc) {
                ((DigitalVideoDisc) media).play();
            } else if (media instanceof CompactDisc) {
                ((CompactDisc) media).play();
            }
        } else {
            System.out.println("Media not found or cannot be played.");
        }
    }
//---------------------------------------------------------------------------------  
// main menu -> update store
    public static void updateStore(Store store, Scanner scanner) {
        boolean back = false;
        
        while (!back) {
            System.out.println("Update Store Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add a media");
            System.out.println("2. Remove a media");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    addMediaToStore(store, scanner);
                    break;
                case 2:
                    removeMediaFromStore(store, scanner);
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
//---------------------------------------------------------------------------------  
// main menu -> update store -> add a media
    public static void addMediaToStore(Store store, Scanner scanner) {
        System.out.println("Enter media type (DVD, Book, CD): ");
        String type = scanner.nextLine();
        
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        
        System.out.println("Enter category: ");
        String category = scanner.nextLine();
        
        System.out.println("Enter cost: ");
        float cost = scanner.nextFloat();
        scanner.nextLine(); // Consume newline
        
        Media media = null;
        switch (type.toUpperCase()) {
            case "DVD":
                System.out.println("Enter director: ");
                String director = scanner.nextLine();
                System.out.println("Enter length: ");
                int length = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                media = new DigitalVideoDisc(title, category, director, length, cost);
                break;
            case "BOOK":
				System.out.println("Enter authors (comma-separated): ");
				String authorsInput = scanner.nextLine();
				String[] authors = authorsInput.split(",");
				media = new Book(title, category, cost, authors);
				break;
			case "CD":
	            System.out.println("Enter director: ");
	            director = scanner.nextLine();
	            System.out.println("Enter length: ");
	            length = scanner.nextInt();
	            scanner.nextLine(); // Consume newline
	            System.out.println("Enter artist: ");
	            String artist = scanner.nextLine();
	            media = new CompactDisc(title, category, director, length, cost, artist);
	            System.out.println("Enter the number of tracks: ");
	            int numTracks = scanner.nextInt();
	            scanner.nextLine(); // Consume newline
	            for (int i = 0; i < numTracks; i++) {
	                System.out.println("Enter title of track " + (i + 1) + ": ");
	                String trackTitle = scanner.nextLine();
	                System.out.println("Enter length of track " + (i + 1) + " (in minutes): ");
	                int trackLength = scanner.nextInt();
	                scanner.nextLine(); // Consume newline
	                Track track = new Track(trackTitle, trackLength);
	                ((CompactDisc) media).addTrack(track);
	            }
	            break;
            default:
                System.out.println("Invalid media type.");
                return;
        }   
        if (media != null) {
            store.addMedia(media);
        }
    }
//---------------------------------------------------------------------------------  
// main menu -> update store -> remove a media
    public static void removeMediaFromStore(Store store, Scanner scanner) {
        System.out.println("Enter the ID of the media to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        store.removeMedia(id);
    }
//---------------------------------------------------------------------------------  
// main menu -> see current cart
// OR main menu -> view store -> see current cart    
    public static void seeCurrentCart(Scanner scanner) {
        boolean back = false;

        while (!back) {
            cart.print(); // Display the current cart contents

            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Filter medias in cart");
            System.out.println("2. Sort medias in cart");
            System.out.println("3. Remove media from cart");
            System.out.println("4. Play a media");
            System.out.println("5. Place order");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3-4-5");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    filterCart(scanner);
                    break;
                case 2:
                    sortCart(scanner);
                    break;
                case 3:
                    removeFromCart(scanner);
                    break;
                case 4:
                    playMediaInCart(scanner);
                    break;
                case 5:
                    placeOrder();
                    back = true;
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
//---------------------------------------------------------------------------------  
// see current cart  -> filter medias in cart
    public static void filterCart(Scanner scanner) {
        System.out.println("Filter options:");
        System.out.println("1. Filter by ID");
        System.out.println("2. Filter by title");
        System.out.println("Please choose a number: 1-2");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                System.out.println("Enter the ID to filter: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                cart.searchById(id);
                break;
            case 2:
                System.out.println("Enter the title to filter: ");
                String title = scanner.nextLine();
                cart.searchByTitle(title);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
//---------------------------------------------------------------------------------  
// see current cart  -> sort medias in cart
    public static void sortCart(Scanner scanner) {
        System.out.println("Sort options:");
        System.out.println("1. Sort by title");
        System.out.println("2. Sort by cost");
        System.out.println("Please choose a number: 1-2");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                cart.sortByTitle();
                System.out.println("Cart sorted by title.");
                break;
            case 2:
                cart.sortByCost();
                System.out.println("Cart sorted by cost.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
//---------------------------------------------------------------------------------  
// see current cart  -> remove media from cart
    public static void removeFromCart(Scanner scanner) {
        System.out.println("Enter the title of the media to remove from cart: ");
        String title = scanner.nextLine();
        Media media = cart.searchByTitle(title);
        cart.removeMedia(media);
    }
//---------------------------------------------------------------------------------  
// see current cart  -> play a media
    public static void playMediaInCart(Scanner scanner) {
        System.out.println("Enter the title of the media to play: ");
        String title = scanner.nextLine();
        Media media = cart.searchByTitle(title);
        if (media != null) {
            playMedia(media);
        } 
    }
//---------------------------------------------------------------------------------  
// see current cart  -> placeOrder
    public static void placeOrder() {
        cart.clear();
        System.out.println("Order placed successfully. The cart is now empty.");
    }
}
