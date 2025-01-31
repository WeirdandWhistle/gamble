package main;

import javax.swing.JFrame;

public class frame extends JFrame {

	public frame() {

		panel panel = new panel();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(500, 100);
		this.setTitle("BET BIG, LOOSE BIGGER");
		this.addMouseListener(panel.mh);
		this.addMouseMotionListener(panel.mh);

		this.add(panel);
		this.pack();

	}

}
