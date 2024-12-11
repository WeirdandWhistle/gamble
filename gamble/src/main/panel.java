package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class panel extends JPanel implements Runnable {
	
	public final int width = 500;
	public final int height = 500;
	public final int gameTicks = 60;
	
	public Dimension window = new Dimension(height,width);
	public Thread gameThread;
	
	public panel() {
		
		this.setPreferredSize(window);
		this.requestFocusInWindow();
		
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

	double drawInterval = 1000000000/gameTicks;
	double nextdrawTime = System.nanoTime() + drawInterval;
			
	while(gameThread != null) {
		}
	update();

	try {
	
		double remaningTime = nextdrawTime - System.nanoTime();
		remaningTime = remaningTime / 1000000;
				
		if(remaningTime < 0) {
			remaningTime = 0;
		}
				
		Thread.sleep((long) remaningTime);
				
		nextdrawTime += drawInterval;
		} catch (InterruptedException e) {
				
			e.printStackTrace();
		}
	}
	void update() {
		
	}
	public void paint(Graphics g) {
		
		
		
	super.paint(g);
		
	Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(Color.black);
	g2d.fillRect(0, 0,width,height);
		
	}
}