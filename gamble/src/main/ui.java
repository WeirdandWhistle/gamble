package main;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import extra.SpriteSheet;
import extra.UTool;
import extra.button;
import extra.slider;

public class ui {
	
	panel p;
	Graphics2D g2d;
	public int prevState = 0;
	public int currentState;
	public Font terminal,SansSerif40,SansSerif80;
	public BufferedImage iconsImg;
	public SpriteSheet icons;
	public BufferedImage backIcon;
	

	public button[] but = new button[10];
	public slider[] slider = new slider[5];
	
	public ArrayList<int[]> CursorReq = new ArrayList<>();
	
	
	public ui(panel p) {
		
		this.p = p;
		
		terminal = new Font("Terminal",Font.PLAIN,20);
		SansSerif40 = new Font("SansSerif",Font.PLAIN,40);
		SansSerif80 = new Font("SansSerif",Font.PLAIN,80);
		iconsImg = setup("/buttons/icons.png",64,64);
		icons = new SpriteSheet(iconsImg);
		backIcon = icons.getSprite(0, 0,16,16);
		
		
		
//		currentState = panel.sm.gameState;
		
	}
	public void renderUI(Graphics2D g2d) {
		
		//TODO: make a requsting cursor method to orginize cursors
		
		//TODO: make slider work
		
//		p.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		currentState = p.sm.gameState;
		this.g2d = g2d;
//		System.out.println("debug");
//		System.out.println(this.currentState);
		
		if(prevState != currentState) {
			p.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			if(currentState == p.sm.startState) {
				button startButton  = new button(p.mh, new Rectangle(50,(int)((p.height/2)-50-20),(int)(p.width-100),100),"start");
				but[0] = startButton;
				startButton = null;
			}
			if(currentState == p.sm.menuState) {
				button tradGames = new button(p.mh,new Rectangle(100,200,300,50),"OG Games");
				but[0] = tradGames;
				tradGames = null;
				button newExp = new button(p.mh, new Rectangle(100,260,300,50), "New Games");
				but[1] = newExp;
				newExp = null;
				button wallet = new button(p.mh, new Rectangle(100,320,300,50), "Wallet");
				but[2] = wallet;
				wallet = null;
			}
			if(currentState == p.sm.walletState) {
				button backButton = new button(p.mh,new Rectangle(0,0,48,48),null);
				but[0] = backButton;
				
				slider moneySlider = new slider(p.mh,new Point(100,100),300,5);
				slider[0] = moneySlider;
				moneySlider = null;
			}
		}
		if(currentState == p.sm.startState) {
			drawStartState();
		}
		if(currentState == p.sm.menuState) {
			drawMainMenu();
		}
		if(currentState == p.sm.walletState) {
			drawWallet();
		}
		
		this.ReqCusor(Cursor.DEFAULT_CURSOR, 1);
		p.setCursor(new Cursor(this.selectCursor()));
		
		prevState = currentState;
	}	
	public void drawWallet() {
		g2d.setColor(new Color(10,10,30));
		g2d.fillRect(0, 0, p.width, p.height);
		
		g2d.setColor(Color.gray);
		
		g2d.setFont(this.SansSerif40);
		g2d.drawString("Balance: " + String.valueOf(p.p.money),125,50);
		
		g2d.drawImage(backIcon,but[0].body.x,but[0].body.y,but[0].body.width,but[0].body.height,null);
		if(but[0].clicked()) {
			p.sm.gameState = p.sm.menuState;
		}
		
		slider[0].drawSlider(g2d);
		
	}
	public void drawMainMenu() {
		
		g2d.setColor(new Color(10,10,30));
		g2d.fillRect(0, 0, p.width, p.height);
		
		g2d.setColor(Color.gray);
		
		this.drawRoundedButton(but[0],this.SansSerif40);
		this.drawRoundedButton(but[1],this.SansSerif40);
		this.drawRoundedButton(but[2],this.SansSerif40);
		
		g2d.setFont(SansSerif80);
		g2d.drawString(p.name + '!',50,100);
		
//		System.out.println("but2 clicked: " + but[2].clicked());
		
		if(but[0].clicked()) {}
		if(but[1].clicked()) {}
		if(but[2].clicked()) {p.sm.gameState = p.sm.walletState;}
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
	public void ReqCusor(int reqCursor, int priority) {
		
		int[] x = {priority,reqCursor};
		
		this.CursorReq.add(x);
		
	}
	public int selectCursor() {
		//**1(biggest priority) 2(selected Cursor) 3(how many times priority is repeated)**\\
		int MaxPri[] = {0,0,0};
		
		ArrayList<Integer> index = new ArrayList<>();
		
		for(int i = 0; i <= CursorReq.size() - 1;i++) {
			int[] x = CursorReq.get(i);
			if(x[0] > MaxPri[0]) {
				MaxPri[1] = x[1];
				MaxPri[2] = 0;
				index.clear();
			}
			if(x[0] == MaxPri[0]) {
				MaxPri[2]++;
				index.add(i);
			}
		}
		if(MaxPri[2] == 1) {
			return CursorReq.get(MaxPri[2])[1];
		}
		else {
			HashMap<Integer, Integer> polls = new HashMap<>();
			ArrayList<Integer> canidents = new ArrayList<>();
			
			for(int i = 0; i <= MaxPri[2] - 1; i++) {
				int x = 0;
				if(polls.get(CursorReq.get(index.get(i))[1]) != null) {
					x = polls.get(CursorReq.get(index.get(i))[1]);
					canidents.add(x);
					x++;
				}
				polls.put(CursorReq.get(index.get(i))[1], x);
			}
			int leader = 0;
			for(int i = 0; i <= canidents.size()-1;i++) {
				if(polls.get(canidents.get(i)) > leader) {
					leader = polls.get(i);
				}
			}
			return leader;
		}
	}
	public void drawRoundedButton(button but, Font font) {
		if(but.entered()) {
			g2d.setStroke(new BasicStroke(3));
			this.ReqCusor(Cursor.HAND_CURSOR, 5);
		}
		else {
			this.ReqCusor(Cursor.DEFAULT_CURSOR, 1);
		}
		g2d.drawRoundRect(but.body.x, but.body.y, but.body.width, but.body.height, 50, 50);
		g2d.setStroke(new BasicStroke(1));
		
		g2d.setFont(font);
		g2d.drawString(but.text, this.getXForCenteredTextRect(but.text, but.body.width) + but.body.x, but.body.y + this.GetHeightOfString(but.text)-10);
		
		
	}
	public int getXForCenteredTextRect(String text, int width) {
		int length = GetWidthOfString(text);
		int x = width/2 - length/2;
		return x;
				
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