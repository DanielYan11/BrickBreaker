package BrickBreak;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Gameplay game = new Gameplay();
		frame.setBounds(10, 10, 800, 700);
		frame.setTitle("Brick Breaker");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.setVisible(true);
	}
}
