package market;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import main.MouseHandler;

public class Graph {

	Rectangle body;
	Rectangle grid;
	private ArrayList<int[]> points;
	MouseHandler mh;
	Graphics2D g2d;
	private int sub = 40;
	public int range = 0;
	public int domain = 0;
	public final int maxData = 200;
	public int t = 0;

	public Graph(Rectangle body, ArrayList<int[]> points, MouseHandler mh) {
		this.body = body;
		this.points = points;
		this.mh = mh;

		grid = new Rectangle(body.x + sub, body.y, body.width - sub, body.height - sub - 30);

		findRange();
		findDomain();
		handleNegitives();
//		System.out.println(range);

	}

	public void updatePoints(ArrayList<int[]> points) {
		this.points = points;
		findRange();
		findDomain();
		handleNegitives();

	}

	public void findDomain() {

		for (int i = 0; i <= points.size() - 1; i++) {

			if ((points.get(i)[0] > domain)) {
				domain = points.get(i)[0];
			}

		}
//		System.out.printlsn(domain);

	}

	public void findRange() {

		for (int i = 0; i <= points.size() - 1; i++) {

			if ((points.get(i)[1] > range)) {
				range = points.get(i)[1];
			}

		}

	}

	public void drawGraph(Graphics2D g2d) {
		points.trimToSize();
		this.g2d = g2d;

		this.trimPoints();

		g2d.setColor(Color.GRAY);
		g2d.drawRect(body.x, body.y, body.width, body.height);

		this.drawBackGrid(grid, 5, 5);
		this.drawPoints();

		t++;
	}

	public void drawBackGrid(Rectangle rect, int segX, int segY) {
		g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
		int x = 1;
		int y = 1;
		int i = 0;
		while (i <= segX) {
//			g2d.setColor(Color.red);
			g2d.drawLine(rect.x, y + body.y, rect.x + rect.width, y + body.y);
			y += (rect.height / segY);
//			g2d.setColor(Color.green);
			g2d.drawLine(x + body.x, rect.y, x + body.x, rect.y + rect.height);
			x += (rect.width / segX);
			i++;
		}
	}

	public void drawPoints() {

		int last = points.size() - 1;
		float DomainRat = (float) grid.width / domain;
		float RangeRat = (float) grid.height / range;
		int difY = 1;
//		System.out.println(DomainRat);
		g2d.setColor(Color.green);
		g2d.setStroke(new BasicStroke(2));
		for (int i = 0; i <= points.size() - 1; i++) {

			g2d.drawLine((int) Math.round((points.get(i)[0] * DomainRat) + grid.x),
					(int) Math.round((points.get(i)[1] * RangeRat) + grid.y) + difY,
					(i < last) ? (int) Math.round((points.get(i + 1)[0] * DomainRat) + grid.x)
							: (int) Math.round((points.get(i)[0] * DomainRat) + grid.x),
					(i < last) ? (int) Math.round((points.get(i + 1)[1] * RangeRat) + grid.y + difY)
							: (int) Math.round((points.get(i)[1] * RangeRat) + grid.y + difY));
		}
	}

	public void trimPoints() {
		while (points.size() > maxData) {
			int deltaX = points.get(1)[0] - points.get(0)[0];
			points.remove(0);
			for (int i = 0; i <= points.size() - 1; i++) {
				points.set(i, new int[] { points.get(i)[0] - deltaX, points.get(i)[1] });
			}

		}
		points.trimToSize();
		range = 0;
		domain = 0;
		this.findDomain();
		this.findRange();

	}

	public void handleNegitives() {

		for (int i = 0; i <= points.size() - 1; i++) {

			int a[][] = {
					{ points.get(i)[0] < 0 ? 0 : points.get(i)[0], points.get(i)[1] < 0 ? 0 : points.get(i)[1] } };
			points.set(i, a[0]);

		}

	}

}
