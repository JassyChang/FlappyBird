package Flappy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Main {
	static JFrame frame = new JFrame(); //	初始JFrame
	public static void main(String[] args) throws InterruptedException{
		frame.setSize(Game.WIDTH, Game.HEIGHT);					
		frame.setVisible(true);									
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setLocationRelativeTo(null);						
		runnit();	
	}
	public static void runnit() throws InterruptedException{ //程式進程 1初始遊戲 2加載Menu 3開始遊戲加載Game 4更新畫面（幀
		final Menu menu = new Menu();							
		final Game game = new Game();							
		Timer animationTimer = new Timer(25, new ActionListener(){		
			  public void actionPerformed(ActionEvent event){
				 game.repaint();
				 game.move();
			  };
		});

		frame.add(menu);							
		menu.setVisible(true);						
		frame.revalidate();							
		frame.repaint();
		while (menu.startGame == false)		
			Thread.sleep(10);
		frame.remove(menu);							
		frame.add(game);							
		game.setVisible(true);			 			
		frame.revalidate();
		animationTimer.start();
	}
}
