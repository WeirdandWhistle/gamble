package extra;

import java.awt.Point;
import java.awt.Rectangle;

import main.MouseHandler;

public class button{
	
	public MouseHandler mh;
	public Rectangle body;
	public String text;
	private int clickDif = 0;
	
	public button(MouseHandler mh, Rectangle body, String text) {
		
		this.mh = mh;
		this.body = body;
		this.text = text;
		
	}
	public boolean entered() {
		
		if(mh.m != null) {
		return body.contains(mh.m);
		}else return false;
	}
	public boolean clicked() {
		
		boolean clicked = false;
		clickDif = mh.clickDif;
//		System.out.println("click dif: " + mh.clickDif+text);
		if(clickDif == 1) {
			clicked = true;
			clickDif++;
			
		}
//		System.out.println("clicked: "+clicked+text);
//		System.out.println("entered: "+entered()+text);
		if(entered() && clicked) {
//			System.out.println("debug 2");
			return true;
		}
		else {
			clickDif = 0;
			return false;
		}
	}
}