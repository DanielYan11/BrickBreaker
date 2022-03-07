package BrickBreak;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
	
	private boolean GameStatus = false;	
	
	private int score = 0;
	
	private Timer timer;
	private int delay = 8;
	
	private int playerXdir = 310;
	
	private Random randomVel = new Random();
	
	
	private int ballXloc = 120;
	private int ballYloc = 350;
	private int ballXvel = randomVel.nextInt(-1 + 5) -5;
	private int ballYvel = randomVel.nextInt(-1 + 5) -5;
	
	private boolean brickAlive = false;
	private int xCoord;
	private int yCoord;
	private int width;
	private int length;
	
	private MapGeneration map;
	
	
	public Gameplay() {
		map = new MapGeneration();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	
	public void paint(Graphics g) {
		//for the background
		g.setColor(Color.gray);
		g.fillRect(1, 1, 792, 692);
		
		//for the borders
		g.setColor(Color.blue);
		//left border
		g.fillRect(0, 0, 5, 695);
		//top border
		g.fillRect(0, 0, 795, 5);
		//right border
		g.fillRect(780, 0, 5, 695);
		//bottom border
		g.fillRect(0, 655, 795, 5);
		
	
		if(brickAlive == false) {
		saveCoords();
		brickAlive = true;
		}
		
		//Scoreboard
		g.setColor(Color.cyan);
		g.fillRect(630, 5, 150, 50);
		g.setColor(Color.black);
		g.setFont(new Font(TOOL_TIP_TEXT_KEY, Font.TRUETYPE_FONT, 20));
		g.drawString("Score: " + score, 630, 30);
		
		//randombricks 2
		g.setColor(Color.pink);
		g.fillRect(xCoord, yCoord, width, length);
		
		
		//paddle
		g.setColor(Color.black);
		g.fillRect(playerXdir, 640, 180, 10);
		
		
		//ball
		g.setColor(Color.green);
		g.fillOval(ballXloc, ballYloc, 20, 20);
		
		
		
		
		if(ballYloc > 700) {
			GameStatus = false;
			g.setColor(Color.white);
			g.setFont(new Font(TOOL_TIP_TEXT_KEY, Font.PLAIN, 40));
			g.drawString("Game Over! Press r to Restart", 150, 300);
		}
		
		
		
		
		g.dispose();
	}
	
	public void saveCoords() {
			map.draw(null);
			xCoord = map.x;
			yCoord = map.y;
			width = map.Rwidth;
			length = map.Rlength;

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		//ball movement
		if(GameStatus) {
			Rectangle brickRect = new Rectangle(xCoord, yCoord, width, length);
			if(new Rectangle(ballXloc, ballYloc, 20, 20).intersects(new Rectangle(playerXdir, 640, 180, 10)) ) {
				ballYvel = -ballYvel;
			}
			if(new Rectangle(ballXloc, ballYloc, 20 ,20).intersects(new Rectangle(xCoord, yCoord, width, length))) {
				brickAlive = false;
				if(ballXloc+19 <= brickRect.x ||ballXloc + 1 >= brickRect.x + brickRect.width ) {
					ballXvel = -ballXvel;
				} else {
					ballYvel = -ballYvel;
				}
				score++;
			}
			ballXloc += ballXvel;
			ballYloc += ballYvel;
			if(ballXloc < 0) {
				ballXvel = -ballXvel;
			}
			if(ballYloc < 0) {
				ballYvel = -ballYvel;
			}
			if(ballXloc > 760 ) {
				ballXvel = -ballXvel;
			}
			
		}
		repaint();
		
	}


	@Override
	public void keyTyped(KeyEvent e) {}


	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()  == KeyEvent.VK_RIGHT) {
			if(playerXdir >= 580) {
				playerXdir = 580;
			}else {
				moveRight(); 
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerXdir<= 5) {
				playerXdir = 5;
			}else {
				moveLeft();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_R) {
			ballXloc = 120;
			ballYloc = 350;
			ballXvel = randomVel.nextInt(-1 + 5) -5;
			ballYvel = randomVel.nextInt(-1 + 5) -5;
			playerXdir = 310;
			score = 0;
			saveCoords();
			repaint();
		}
		
	}
	

	
	
	
	public void moveRight() {
		GameStatus = true;
		playerXdir += 20;
	}
	
	public void moveLeft() {
		GameStatus = true;
		playerXdir -= 20;
	}

	


	


	
}
