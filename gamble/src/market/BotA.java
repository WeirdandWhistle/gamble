package market;

import java.util.ArrayList;

import main.panel;

public class BotA {

	panel p;
	StandardLayout m;
	int token;
	int money = 75;
	public int stock = 0;
	int prevSale = 0;
	int reactionBuy = 2;
	int reactionSell = 3;
	int average = 0;
	int lastPasive = 6;
	int PasiveCal = 0;
	double tol = 0.01;
	ArrayList<Integer> AvgP = new ArrayList<>();
	int dataPoints = 0;
	String name = "superCoolBot1!FUROFY6ertf6k fuyfgkuyafsdk";

	public BotA(panel p, StandardLayout m) {
		this.p = p;
		this.m = m;
		token = p.acconut.addAccount(name, (int) money);
		intit();
	}

	public void intit() {
		m.buy(token, lastPasive, PasiveCal);
	}

	public void run() {
		if (m.sold()[0] == token && m.sold()[1] == PasiveCal) {
			lastPasive = m.LastSell + 1;
			m.buy(token, lastPasive, 0);
			sell(m.LastSell + 3, 0);
		}
		if (m.sold()[0] == token) {
			stock += m.sold()[2];
		}
		if (m.LastSell - prevSale > 5) {
			for (int i = 0; i < 10; i++) {
				m.buy(token, m.LastSell + 4, this.reactionBuy);
			}
		}
		if (prevSale - m.LastSell > 5) {
			for (int i = 0; i < stock * 3 / 4; i++) {
				sell(m.LastSell + 4, this.reactionSell);
			}
		}
		if (stock > 1) {
			sell(m.LastSell + 5, 10);
		}

		prevSale = m.LastSell;

	}

	public boolean checkTol(double a, double b) {

		if (a >= b + tol || a <= b - tol) {
			return true;
		}

		return false;
	}

	public void calcAvg(int input) {
		AvgP.add(input);
		dataPoints++;
		for (int i = 0; i <= AvgP.size() - 1; i++) {
			average += AvgP.get(i);
		}
		average /= dataPoints;
	}

	public void sell(int low, int reactionSell) {
		if (stock > 0) {
			m.sell(token, low, reactionSell);
			stock--;
		}
	}

}
