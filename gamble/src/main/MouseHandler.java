package main;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseMotionListener, MouseListener{
	
	public int x;
	public int y;
	public Point m = new Point(0,0);
	public Point click;
	public Point held;
	public boolean pressed;
	public int clickDif;
	public boolean clicked;
	public final int offsetX = 8;
	public final int offsetY = 31;
	public panel p;
//	public Point offset = new Point(offsetX,offsetY);
	
	
	public MouseHandler(panel p) {
		this.p = p;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		held = e.getPoint();
		
		m.move(e.getX() - offsetX,e.getY() - offsetY);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		x = e.getX();
		y = e.getY();
		
		m.move(e.getX() - offsetX,e.getY() - offsetY);
//		System.out.println(m.getLocation());
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
//		clicked = true;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		pressed = true;
		held = e.getPoint();
		
		clickDif++;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		pressed = false;
		held = null;
		clickDif = 0;
		clicked = false;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}
}

