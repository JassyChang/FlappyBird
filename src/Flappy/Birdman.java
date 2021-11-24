package Flappy;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Birdman {
	static int DIAMETER = 25;									//Diameter of the pikachu
	static int X = ( Game.WIDTH / 2 ) - ( DIAMETER / 2 );		//The x position of the pikachu. 需要在中間
	static int y =  Game.HEIGHT / 2;							//皮卡丘y座標
	static int acceleration = 1;								//加速度
	static int speed = 1;										//往下的速度
	
	static ImageIcon img1 = new ImageIcon(Birdman.class.getResource("4-1.png"));
	static ImageIcon img2 = new ImageIcon(Birdman.class.getResource("4-3.png"));
	static ImageIcon pika = img1;
	public Birdman(){}
	
	//gravity
	public void jump(){
		speed = -17;			
	}
	
	public static void move(){		//檢查死活
	
		if ( ( y >= 0 ) && ( y <= Game.HEIGHT )) {
			speed += acceleration;								
			y += speed;											
		}
		else {
			Timer deathTimer = new Timer(30000, (ActionListener) new ActionListener() {
				public void actionPerformed(ActionEvent event) {
				};
			});
			deathTimer.start();
			reset();
			Game.dead = true;
			
		}
	}
	public static void reset(){				//死掉					
		y = Game.HEIGHT / 2;								
		speed = 2;		
		pika = img1; 	//換圖
	}
	public static void paint(Graphics g){	
		g.drawImage(pika.getImage(), X - pika.getIconWidth()/2, y-pika.getIconHeight()/2, null);
	}
	public static Rectangle getBounds(){
		return new Rectangle(X, y, DIAMETER, DIAMETER);		
	}

}
