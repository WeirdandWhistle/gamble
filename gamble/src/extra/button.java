package extra;


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
			if(body.contains(mh.m)) {
//				if(cursor != -1) {
//					mh.p.setCursor(new Cursor(cursor));
//				}
				return true;
			}
			else {
//				mh.p.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				return false;
			}
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