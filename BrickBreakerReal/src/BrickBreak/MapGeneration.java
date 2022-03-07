package BrickBreak;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


public class MapGeneration {
	private int bricklengthMax = 50;
	private int brickwidthMax = 100;
	private int bricklengthMin = 25;
	private int brickwidthMin = 50;
	
	int x;
	int y;
	int Rwidth;
	int Rlength;
	
	private int maxX = 690;
	private int maxY = 500;

	
	public int generateRandomX() {
		Random xCoord = new Random();
		int randomX = xCoord.nextInt(maxX);
		return randomX;
	}
	public int generateRandomY() {
		Random yCoord = new Random();
		int randomY = yCoord.nextInt(maxY);
		return randomY;
	}
	
	public int generateRandomlength() {
		int randomLength = (int)Math.floor(Math.random()*(bricklengthMax-bricklengthMin+1)+bricklengthMin);
		return randomLength;
	}
	public int generateRandomwidth() {
		int randomWidth = (int)Math.floor(Math.random()*(brickwidthMax-brickwidthMin+1)+brickwidthMin);
		return randomWidth;
	}
	
	
	
	
	public void draw(Graphics2D g) {
		x = generateRandomX();
		y = generateRandomY();
		Rwidth = generateRandomwidth();
		Rlength = generateRandomlength();
		
		
		
		
		//g.setColor(Color.pink);
		//g.fillRect(x, y, Rwidth, Rlength);
		
	}
	
}
