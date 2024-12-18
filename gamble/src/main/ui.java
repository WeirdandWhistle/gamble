package main;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import extra.UTool;
import extra.button;

public class ui {
	
	panel p;
	Graphics2D g2d;
	public int prevState = 0;
	public int currentState;
	public Font terminal,SansSerif40,SansSerif80;
	

	public button[] but = new button[10];
	
	
	public ui(panel p) {
		
		this.p = p;
		
		terminal = new Font("Terminal",Font.PLAIN,20);
		SansSerif40 = new Font("SansSerif",Font.PLAIN,40);
		SansSerif80 = new Font("SansSerif",Font.PLAIN,80);
		
//		currentState = panel.sm.gameState;
		
	}
	public void renderUI(Graphics2D g2d) {
		currentState = p.sm.gameState;
		this.g2d = g2d;
//		System.out.println("debug");
//		System.out.println(this.currentState);
		
		if(prevState != currentState) {
			if(currentState == p.sm.startState) {
				button startButton  = new button(p.mh, new Rectangle(50,(int)((p.height/2)-50-20),(int)(p.width-100),100),"start");
				but[0] = startButton;
				startButton = null;
			}
			if(currentState == p.sm.menuState) {
				
			}
		}
		if(currentState == p.sm.startState) {
			drawStartState();
		}
		if(currentState == p.sm.menuState) {
			drawMainMenu();
		}
		prevState = currentState;
	}	
	public void drawMainMenu() {
		
		g2d.setColor(new Color(100,100,100));
		g2d.fillRect(0, 0, p.width, p.height);
		
	}
	public void drawStartState() {
		
		g2d.setColor(new Color(10,10,30));
		g2d.setFont(new Font("Terminal",Font.PLAIN,80));
		g2d.fillRect(0, 0, p.width, p.height);
		
		g2d.setColor(Color.gray);
		
		g2d.setStroke((but[0].entered()) ? new BasicStroke(3):new BasicStroke(1));
		
//		g2d.drawRect(but1.body.x, but1.body.y, but1.body.width, but1.body.height);
		g2d.drawRoundRect(but[0].body.x, but[0].body.y, but[0].body.width, but[0].body.height, 50, 50);
		
		
		g2d.drawString(but[0].text, this.getXForCenteredText(but[0].text), but[0].body.y + this.GetHeightOfString(but[0].text) - 20);
		
		if(but[0].clicked()) {
			p.sm.gameState = p.sm.menuState;
			
		}
		
	}
	public int getXForCenteredText(String text) {
		int length = GetWidthOfString(text);
		int x = p.width/2 - length/2;
		return x;
				
	}
	public int GetWidthOfString(String text) {
		return (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
	}
	public int GetHeightOfString(String text) {
		return (int)g2d.getFontMetrics().getStringBounds(text, g2d).getHeight();
	}
	public BufferedImage setup(String filePath, int width, int height) {
		UTool ut = new UTool();
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(getClass().getResourceAsStream(filePath));
			img = ut.scaleImage(img, width, height);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
}