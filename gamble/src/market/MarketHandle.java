package market;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import main.panel;

public class MarketHandle {

	public panel p;
	public Graphics2D g2d;

	public boolean start = false;
	public ArrayList<int[]> tp = new ArrayList<>();
	public Graph test;
	int n = 80;
	int k = 0;
	int[][] a = { { -10, 20 }, { 10, 60 }, { 20, -10 }, { 70, 40 } };

	public MarketHandle(panel p) {

		this.p = p;
//		a = { { 0, 20 }, { 10, 60 }, { 20, 0 }, { 80, 40 } };
		tp.add(a[0]);
		tp.add(a[1]);
		tp.add(a[2]);
		tp.add(a[3]);

		test = new Graph(new Rectangle(100, 100, 300, 300), tp, p.mh);
	}

	public void start() {
		start = true;
	}

	public void stop() {
		start = false;
	}

	public void update(Graphics2D g2d) {
		n++;
		k++;
		this.g2d = g2d;
		if (!start) {
//			System.out.println("debug 2");
		} else {

			g2d.setColor(new Color(20, 20, 40));
			g2d.fillRect(0, 0, p.width, p.height);

			g2d.setColor(Color.gray);

//			System.out.println("debug 1");

			p.ui.updateBackButton(p.sm.selectState);

			test.drawGraph(g2d);

			int b[][] = { { n, (int) (Math.round((double) Math.sin(k / 30) * 30) + 30) } };
			tp.add(b[0]);
			test.updatePoints(tp);

		}
	}

}
