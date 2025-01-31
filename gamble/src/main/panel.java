package main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import data.SaveLoad;
import market.Account;
import market.MarketHandle;

public class panel extends JPanel implements Runnable {

	public final int width = 500;
	public final int height = 500;
	public final int gameTicks = 60;
	public final String name = "Super Bets";
	public int cursor = Cursor.DEFAULT_CURSOR;
	public int t = 0;
	public int token;

	public Dimension window = new Dimension(height, width);
	public Thread gameThread;
	public MouseHandler mh = new MouseHandler(this);
	public ui ui = new ui(this);
	public Account acconut = new Account();
	public StateMec sm = new StateMec();
	public player p = new player(this);
	public SaveLoad sl = new SaveLoad(this);
	public MarketHandle market = new MarketHandle(this);

	public panel() {

		this.setPreferredSize(window);
		this.requestFocusInWindow();

		this.startGameThread();
		sm.startGame();
//		token = this.acconut.addAccount("admin", 100);

	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void requestCursor(int cursor) {
		this.cursor = cursor;
	}

	@Override
	public void run() {

		double drawInterval = 1000000000 / gameTicks;
		double nextdrawTime = System.nanoTime() + drawInterval;

		while (gameThread != null) {

			update();

			try {

				double remaningTime = nextdrawTime - System.nanoTime();
				remaningTime = remaningTime / 1000000;

				if (remaningTime < 0) {
					remaningTime = 0;
				}

				Thread.sleep((long) remaningTime);

				nextdrawTime += drawInterval;
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	void update() {

		if (mh.pressed) {
			mh.clickDif++;
		}
		this.setCursor(new Cursor(cursor));
		this.repaint();
		this.cursor = Cursor.DEFAULT_CURSOR;
		t++;

	}

	public void paint(Graphics g) {

		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, width, height);

		ui.renderUI(g2d);

//	market.update();

	}
}
