package Flappy;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.event.*;

public class Menu extends JPanel {
	private static final long serialVersionUID = 1L;
	int highscore;
	ImageIcon img = new ImageIcon(Menu.class.getResource("menu.png"));
	boolean startGame = false;
	
	public Menu(){
		setFocusable(true);							
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg){
				if (arg.getButton() == MouseEvent.BUTTON1) 
					startGame = true;
			}
		});
	}
	public void paint (Graphics g){
		super.paint(g);
		g.drawImage(img.getImage(), 0, 0, null);				
	}
}
