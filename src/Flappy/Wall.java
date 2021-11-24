package Flappy;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Wall {
	Random rnd = new Random();						
	int x ;											
	int y = rnd.nextInt(Game.HEIGHT - 400) + 200;	
	static int speed = - 6;							
	int WIDTH = 45;									
	int height = Game.HEIGHT - y;					
	int GAP = 250;									
	
	static BufferedImage img = null;{
		try {
			img = ImageIO.read(getClass().getResource("wall2.png"));
		}catch(IOException e){
			System.out.println("Wrong wall");
		}
	}	
	
	public Wall(int i){								
		this.x = i;
	}
	
	public void paint(Graphics g){
		g.drawImage(img, x, y, null);								
		g.drawImage(img, x, ( -Game.HEIGHT ) + ( y - GAP), null);	
	}
	
	public void move(){
		x += speed;								
		
		Rectangle wallBounds = new Rectangle(x, y, WIDTH, height);
		Rectangle wallBoundsTop = new Rectangle(x, 0, WIDTH, Game.HEIGHT - (height + GAP));
		
		if ( (wallBounds.intersects(Birdman.getBounds()) ) || (wallBoundsTop.intersects(Birdman.getBounds()))){
			Birdman.reset();
			died();
		}
		
		if (x <= 0 - WIDTH){
			x = Game.WIDTH;
			y = rnd.nextInt(Game.HEIGHT - 400) + 200;
			height = Game.HEIGHT - y;
		}		
	}
	
	public void died(){
		Timer deathTimer = new Timer(30000, (ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			};
		});
		  
		deathTimer.start();
		y = rnd.nextInt(Game.HEIGHT - 400) + 200;
		height = Game.HEIGHT - y;
		Game.dead = true;
		
	}
}
