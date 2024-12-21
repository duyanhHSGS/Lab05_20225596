package hust.soict.dsai.aims.screen;

import javax.swing.JFrame;

import hust.soict.dsai.aims.cart.Cart;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CartScreen extends JFrame {

	    private static Cart cart;
	    
	    public static void main(String[] args) {
			new CartScreen(cart);
		}

	    public CartScreen(Cart cart) {

	        super();
	        CartScreen.cart = cart;
	        JFXPanel fxPanel = new JFXPanel();
	        this.add(fxPanel);

	        this.setTitle("Cart");
	        this.setVisible(true);
	        Platform.runLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/dsai/aims/screen/cart.fxml"));
	                    CartScreenController controller = new CartScreenController(cart);
	                    loader.setController(controller);
	                    Parent root = loader.load();
	                    fxPanel.setScene(new Scene(root));
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });

	    }
	    
	}