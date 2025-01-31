package market;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import extra.button;
import extra.slider;
import main.panel;

public class StandardLayout {

	public panel p;
	public Graphics2D g2d;
	public ArrayList<int[]> lifetimePlot = new ArrayList<>();
	public ArrayList<int[]> currentPlot = new ArrayList<>();
	public Rectangle lifetimeRect;
	public Rectangle currentRect;
	public Graph currentGraph;
	public Graph lifetimeGraph;
	public button buy;
	public button sell;
	Font SansSerif20 = new Font("SansSerif", Font.PLAIN, 20);
//	public double tokenBuy;
	public int tokenSell;
	public ArrayList<String> buyRes = new ArrayList<>();
	public ArrayList<String> sellRes = new ArrayList<>();
	public int LastSell = 0;
	public int[] sellToken = { 0, -1, 0 };

	public ArrayList<Integer[]> buyOrder = new ArrayList<>();
	public ArrayList<Integer[]> sellOrder = new ArrayList<>();

	public slider slider;

	public StandardLayout(panel p) {
		this.p = p;

		currentRect = new Rectangle(25, 50, 200, 200);
		lifetimeRect = new Rectangle(275, 50, 200, 200);

		currentGraph = new Graph(currentRect, currentPlot, 500);
		lifetimeGraph = new Graph(lifetimeRect, lifetimePlot, -1);

		buy = new button(p.mh, new Rectangle(50, 275, 100, 50), "buy");
		sell = new button(p.mh, new Rectangle(350, 275, 100, 50), "sell");

		slider = new slider(p.mh, new Point(50, 400), 400, 5);

//		tokenBuy = p.acconut.addAccount("playerBuy", 100);
		tokenSell = p.acconut.addAccount("playerSell", 100);

	}

	public void render(Graphics2D g2d) {
		p.p.money = p.acconut.accounts.get(p.p.token);
		if (sold()[0] == p.p.token) {
			p.p.stock++;
		}
		p.market.bot.run();

		this.g2d = g2d;
		g2d.setColor(new Color(20, 20, 40));
		g2d.fillRect(0, 0, p.width, p.height);

		this.update();
		g2d.setFont(this.SansSerif20);
		slider.drawSlider(g2d);
		int value = slider.getValue();
		g2d.drawString("Bet: " + (value), 210, 375);
		g2d.drawString("stocks: " + (p.p.stock), 210, 450);
		g2d.drawString("Bot stocks: " + (p.market.bot.stock), 300, 450);

		lifetimeGraph.drawGraph(g2d);
		currentGraph.drawGraph(g2d);

		g2d.setColor(Color.red);
		p.ui.drawRoundedButton(buy, p.ui.SansSerif40);
		p.ui.drawRoundedButton(sell, p.ui.SansSerif40);
		this.drawRes();

		if (buy.clicked()) {
			this.buy(p.p.token, value, 0);
//			System.out.println(p.p.token);

		}
		if (sell.clicked()) {
			if (p.p.stock > 0) {
				this.sell(p.p.token, value, 0);
				p.p.stock--;
			}

		}
		if (p.ui.updateBackButton(p.sm.marketHome)) {
			p.market.state = 0;
		}
		g2d.setFont(p.ui.SansSerif40);
		g2d.setColor(Color.gray);
		g2d.drawString("your Money: " + p.acconut.accounts.get(p.p.token), 150, 40);
//		System.out.println("lifetime maxDara: " + this.lifetimeGraph.maxData);
//		System.out.println("current maxDara: " + this.currentGraph.maxData);
//		System.out.println(p.p.stock);

	}

	public void drawRes() {
		g2d.setColor(Color.gray);
		g2d.setFont(p.ui.terminal);

		for (int i = 0; i <= buyRes.size() - 1; i++) {
			g2d.drawString(buyRes.get(i), 50, 335 + (i * 10));
		}
		for (int i = 0; i <= sellRes.size() - 1; i++) {
			g2d.drawString(sellRes.get(i), 300, 335 + (i * 10));
		}

		while (buyRes.size() > 5) {
			buyRes.remove(0);
		}
		while (sellRes.size() > 5) {
			sellRes.remove(0);
		}

	}

	public int[] sold() {
		return sellToken;
	}

	public void sellStarters(int amount, int price) {
//		System.out.println("p.token: " + this.tokenSell);
		for (int i = 0; i <= amount; i++) {
			this.sell(this.tokenSell, price, 0);
		}
		this.sell(this.tokenSell, 500, 0);
	}

	public boolean update() {
		sellToken = new int[] { 0, -1, 0 };
		int amount = 0;
		double tol = 0.01;
//		System.out.println("");
		ArrayList<Integer> toRemoveBuy = new ArrayList<>();
		ArrayList<Integer> toRemoveSell = new ArrayList<>();

		int[] ts = {};

		boolean sold = false;
//		System.out.println("sellORder token: " + sellOrder.get(0)[0]);
		for (int i = 0; i <= buyOrder.size() - 1; i++) {
			for (int k = 0; k <= sellOrder.size() - 1; k++) {

				if (sellOrder.get(k)[1] <= buyOrder.get(i)[1]) {
//					System.out.println("f: 2");
					if (sellOrder.get(k)[0] != buyOrder.get(i)[0]) {
//						System.out.println("if: 3");
						for (int n = 0; n <= buyOrder.get(i)[1] - sellOrder.get(k)[1]; n++) {
//							System.out.println("if: 4");
//							System.out.println("n: " + n);
//							System.out.println("nDIf: " + (buyOrder.get(k)[1] - sellOrder.get(i)[1]));

							boolean can = true;
							if (p.acconut.accounts.get(buyOrder.get(i)[0]) < sellOrder.get(k)[1] + n) {
								can = false;
//								sold = true;
//								System.out.println("if: 5");
//								break;
							}
							if (sellOrder.get(k)[1] + n == buyOrder.get(i)[1] && can) {
//								
								LastSell = (int) (sellOrder.get(k)[1] + n);
//								System.out.println("is null? " + sellOrder.get(k)[0]);
//								System.out.println(
//										"sellAcount money before: " + p.acconut.accounts.get(sellOrder.get(k)[0]));
//								System.out.println(
//										"buyAcount money before: " + p.acconut.accounts.get(buyOrder.get(i)[0]));
								p.acconut.accounts.put(sellOrder.get(k)[0],
										(p.acconut.accounts.get(sellOrder.get(k)[0]) + sellOrder.get(k)[1] + n));
								p.acconut.accounts.put(buyOrder.get(i)[0],
										((p.acconut.accounts.get(buyOrder.get(i)[0]) - sellOrder.get(i)[1] - n)));
//								System.out.println("Transaction at buyOrder: " + buyOrder.get(i)[0] + " sellOrder: "
//										+ sellOrder.get(k)[0]);
//								System.out.println(
//										"sellAcount money before: " + p.acconut.accounts.get(sellOrder.get(k)[0]));
//								System.out.println(
//										"buyAcount money before: " + p.acconut.accounts.get(buyOrder.get(i)[0]));
								toRemoveSell.add(k);
								toRemoveBuy.add(i);
								sold = true;
								amount++;
								ts = new int[] { buyOrder.get(i)[0], buyOrder.get(i)[2] };
								System.out.println("ts: " + ts);
								break;

							}

//							if (sold) {
//								break;
//							}
						}
					}

				}
//				if (sold) {
//					break;
//				}
			}
//			if (sold) {
//				break;
//			}
//			return false;

		}
		for (int i = toRemoveBuy.size() - 1; i >= 0 && sold; i--) {
			buyOrder.remove((int) toRemoveBuy.get(i));
		}
		for (int k = toRemoveSell.size() - 1; k >= 0 && sold; k--) {
			sellOrder.remove((int) toRemoveSell.get(k));
		}
		if (sold == true) {
			sellToken = new int[] { ts[0], ts[1], amount };
		}
//		System.out.println("last sell: " + this.LastSell);
		this.lifetimePlot.add(new int[] { p.t, LastSell });
		this.currentPlot.add(new int[] { p.t, LastSell });

		lifetimeGraph.updatePoints(lifetimePlot);
		currentGraph.updatePoints(currentPlot);
		return false;
	}

	public void buy(int token, int high, int amount) {

		buyOrder.add(new Integer[] { token, high, amount });
		buyRes.add("An order to buy was placed at: " + high);
//		System.out.println("buy order was placed");

	}

	public void sell(int token, int low, int amount) {

		sellOrder.add(new Integer[] { token, low, amount });
//		System.out.println("token: " + token);
//		System.out.println("low: " + low);
//		System.out.println("if: 5");
		sellRes.add("An order to sell was placed at: " + low);

	}

}
