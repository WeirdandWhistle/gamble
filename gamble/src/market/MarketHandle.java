package market;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import main.panel;

public class MarketHandle {

	public panel p;
	public Graphics2D g2d;
	public final int MarketStandard = 1;
	public int state = 0;

	public boolean start = false;
	public ArrayList<int[]> tp = new ArrayList<>();
	public Graph test;
	public BotA bot;

//	int[][] a = { { -10, 20 }, { 10, 60 }, { 20, -10 }, { 70, 40 } };

	public StandardLayout sl;

	public MarketHandle(panel p) {

		this.p = p;
//		a = { { 0, 20 }, { 10, 60 }, { 20, 0 }, { 80, 40 } };
//		tp.add(a[0]);
//		tp.add(a[1]);
//		tp.add(a[2]);
//		tp.add(a[3]);

//		test = new Graph(new Rectangle(100, 100, 300, 300), tp, 500, p.mh);
		sl = new StandardLayout(p);
		sl.sellStarters(30, 10);

		bot = new BotA(p, sl);
	}

	public void start() {
		start = true;
	}

	public void stop() {
		start = false;
	}

	public void update(Graphics2D g2d) {
		this.g2d = g2d;
		if (!start) {
//			System.out.println("debug 2");
		} else {
//			bot.run();

			g2d.setColor(new Color(20, 20, 40));
			g2d.fillRect(0, 0, p.width, p.height);

			g2d.setColor(Color.gray);

//			System.out.println("debug 1");

			p.ui.updateBackButton(p.sm.selectState);

			if (true) {
				state = this.MarketStandard;
			}
			if (this.state == this.MarketStandard) {
				sl.render(g2d);
			}

		}
	}

}
