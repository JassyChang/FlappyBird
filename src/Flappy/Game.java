package Flappy;

import java.awt.Color;
import java.awt.Font;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel {
	static int HEIGHT = 800;						
	static int WIDTH = 600;							
	Birdman birdy = new Birdman();					
	Wall wall = new Wall(WIDTH);					
	Wall wall2 = new Wall(WIDTH + (WIDTH / 2));		
	static int score = 0;							
	static int best_score = 0;
	int scrollX = 0;								
	static boolean dead = false;					
	static String deathMessage = "" ; 				
	static String tips = "" ;
	static boolean mute = false;
	Clip clip = null;
	ImageIcon img = new ImageIcon(Game.class.getResource("bg.png"));
	ImageIcon img_mute = new ImageIcon(Game.class.getResource("mute.png"));
	
	public Game(){   //遊戲初始化
		
		try{
		     AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/Flappy/bgm.wav"));
		     clip = AudioSystem.getClip();
		     clip.open(audioInputStream);
		     clip.start();
	    }
	    catch(Exception e){
	    	System.out.println("Wrong music");
	    };
		
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent arg0) {				
				if (arg0.getButton() == MouseEvent.BUTTON3) {			//遊戲音樂用右鍵控制
					if(!mute) clip.stop();
					else clip.start();
					mute = !mute;
				}
				else if (arg0.getButton() == MouseEvent.BUTTON1)		//左鍵控制飛行
				birdy.jump();
			}
		});
	}
	@SuppressWarnings("static-access")
	public void paint(Graphics g){ //不斷更新畫面
		super.paint(g);
		g.drawImage(img.getImage(), scrollX, 0, null);								
		g.drawImage(img.getImage(), scrollX + 1800, 0, null);			
		wall.paint(g);			
		wall2.paint(g);			
 		birdy.paint(g);			
 		g.setFont(new Font("comicsans", Font.BOLD, 40));
 		g.setColor(Color.WHITE);
 		g.drawString("Highest Score: "+ best_score + "   Score: " + score, 10, 750);
 		g.drawString(deathMessage, 180, 200);				
 		g.setFont(new Font("comicsans", Font.BOLD, 20));
 		g.setColor(Color.BLACK);
 		g.drawString(tips, 120, 50);
 		if(mute) g.drawImage(img_mute.getImage(), 15, 15, null);
	}
	@SuppressWarnings("static-access")
	public void move(){
		if(!dead) {					//活著更新遊戲進程
			deathMessage = "";
			tips = "";
			wall.move();			
			wall2.move();			
			birdy.move();			
			scrollX += Wall.speed;	
			if (scrollX == -1800)	
				scrollX = 0;
			
			if ( (wall.x == Birdman.X) || (wall2.x == Birdman.X) ) 	
				score();
		}
		else {						//死掉遊戲進程
			birdy.pika = birdy.img2;
			deathMessage = "Game Over!";
			tips = "\" Right Button For Music On/Off \"";
			wall.x = 600;
			wall2.x = 600 + (WIDTH / 2);
			if(score > best_score) best_score = score;
			Timer deathTimer = new Timer(30000, (ActionListener) new ActionListener() {
				public void actionPerformed(ActionEvent event) {
				};
			});
			  
			deathTimer.start();
			addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent arg1) {
					if (arg1.getButton() == MouseEvent.BUTTON1) {
					if(dead){
						score = 0;
						birdy.reset();
					} 
					dead = false;
					}
				}
			});
		}
	}
	public static void score(){		//得分
		score += 1;
	}
	
}
