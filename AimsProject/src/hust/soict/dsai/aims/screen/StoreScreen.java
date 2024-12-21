package hust.soict.dsai.aims.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.store.Store;


public class StoreScreen extends JFrame {
    private static Store store = new Store();
    private static Cart cart = new Cart();

    public static void initSetup() {

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);     
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star War", "Science Fiction", "George Lucas", 87, 24.95f); 
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", null, 0, 18.99f);
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);
        
    
        Book book = new Book("The Valley of Fear", "Detective", 20.00f);
        Book book1 = new Book("A Living Remedy: A Memoir", "Biography", 202.00f);
        Book book2 = new Book("On the Origin of Time: Stephen Hawking's Final Theory", "Science", 120.00f);
        store.addMedia(book);
        store.addMedia(book1);
        store.addMedia(book2);
        
    }
    
    public static void main(String[] args) {
        initSetup();
		new StoreScreen(store);
	}


    public StoreScreen(Store store) {
        StoreScreen.store = store;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);
        
        setTitle("Store");
		setSize(1024, 768);
		setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }   

    JPanel createNorth() {
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.add(createMenuBar());
		north.add(createHeader());
		return north;
	}

    JMenuBar createMenuBar() {

        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem smAddBook = new JMenuItem("Add Book");
        JMenuItem smAddCD = new JMenuItem("Add CD");
        JMenuItem smAddDVD = new JMenuItem("Add DVD");
        smUpdateStore.add(smAddBook);
        smUpdateStore.add(smAddCD);
        smUpdateStore.add(smAddDVD);        

        smAddBook.addActionListener(new btnMenuListener());
        smAddCD.addActionListener(new btnMenuListener());
        smAddDVD.addActionListener(new btnMenuListener());

        menu.add(smUpdateStore);

        JMenuItem viewStoreMenu = new JMenuItem("View store");
        JMenuItem viewCartMenu = new JMenuItem("View cart");
        menu.add(viewStoreMenu);
        menu.add(viewCartMenu);
        viewStoreMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StoreScreen(store);
            }
        });
        viewCartMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CartScreen(cart);
            }
        });

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    private class btnMenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if (command.equals("Add Book")) {
				new AddBookToStoreScreen(store);
			} else if (command.equals("Add CD")) {
				new AddCompactDiscToStoreScreen(store);
			} else if (command.equals("Add DVD")) {
				new AddDigitalVideoDiscToStoreScreen(store);
			} 
		}
	}

    JPanel createHeader() {

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);
    
        JButton cartBtn = new JButton("View cart");
        cartBtn.setPreferredSize(new Dimension(100, 50));
        cartBtn.setMaximumSize(new Dimension(100, 50));
        cartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CartScreen(cart);
            }
        });
        


        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cartBtn);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    JPanel createCenter() {

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));


        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for (int i = 0; i < mediaInStore.size(); i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i), cart);
            center.add(cell);
        }
        
        return center;
    }

}