package extra;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import main.MouseHandler;

public class slider {
	
	public Point start;
	public double value = 0;
	int width;
	int stroke;
	MouseHandler mh;
	public button tool;
	private boolean held = false;
	private int x = 0;
	private int prevX = x;
	private int deltaX = 0;
	public int tol = 2;
	
	
	public slider(MouseHandler mh, Point start, int width, int stroke) {
		
		this.mh = mh;
		this.start = start;
		this.width = width;
		this.stroke = stroke;
		
		tool = new button(mh, new Rectangle(start.x - (stroke*2)  ,start.y - (stroke*2),5*stroke,5*stroke),null);
	}
	public void drawSlider(Graphics2D g2d) {
		
		
		
//		mh.p.setCursor(tool.entered()?new Cursor(Cursor.HAND_CURSOR):new Cursor(Cursor.DEFAULT_CURSOR));
		g2d.setColor(Color.gray);
		g2d.setStroke(new BasicStroke(1));
		g2d.fillOval(tool.body.x,tool.body.y,tool.body.width,tool.body.height);
		
//		g2d.setColor(Color.gray);
		g2d.setStroke(new BasicStroke(stroke));
		g2d.drawLine(start.x,start.y,width + start.x,start.y);
		
//		System.out.println("mh.held: "+mh.held);
//		System.out.println("tool.held: "+tool.held());
//		System.out.println("tool.clicked: "+tool.clicked());
		x = mh.m.x;
		if(tool.held()) {
//			System.out.println("debug");
			
			
			deltaX = x - prevX;
			
			tool.body.x += deltaX;
			
			deltaX = 0;
			
			
		}
		
		if(tool.body.x < start.x- (stroke*2)) {
			tool.body.x = start.x- (stroke*2);
		}
		if(tool.body.x + ((5*stroke)/2) > width + start.x + tol) {
			tool.body.x =  width - (5*stroke)/2 + start.x; //need to be reset
		}
		
		prevX = x;
	}
	public double getValue() {
		
		double X = tool.body.x - (start.x - (stroke*2));
//		
		if(tool.body.x + ((5*stroke)/2) > width + start.x - tol) {
			X += tol;
		}
//		System.out.println("X: "+X);
//		System.out.println("width: "+width);
		
		value = X/width;
		
		value = Math.round(value * 100.0) / 100.0;
		
		value = Math.clamp(value, 0, 1);
		
		return value;
		
		
	}
	
	

}