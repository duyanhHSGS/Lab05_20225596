package hust.soict.dsai.aims.screen;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.cart.Cart;

public class MediaStore extends JPanel {
	private Media media;
	private Cart cart;
	public MediaStore(Media media) {

		this.media = media;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel(media.getTitle());
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel cost = new JLabel("" + media.getCost() + " $");
		cost.setAlignmentX(CENTER_ALIGNMENT);

		JPanel container = new JPanel();
		container.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton addToCartButton = new JButton("Add to cart");
		container.add(addToCartButton);

		JButton playButton = null;
		if (media instanceof Playable) {
			playButton = new JButton("Play");
			container.add(playButton);
		}

		this.add(Box.createVerticalGlue());
		this.add(title);
		this.add(cost);
		this.add(Box.createVerticalGlue());
		this.add(container);

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	cart.addMedia(media);
            }
        });

        if (playButton != null) {
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog playDialog = new JDialog();
                    playDialog.setTitle("Playing " + media.getTitle());
                    playDialog.setSize(300, 200);
                    playDialog.add(new JLabel("Playing: " + media.getTitle()));
                    playDialog.setVisible(true);
                }
            });
        }
	}
	
	

}
