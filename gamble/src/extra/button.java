package extra;

import java.awt.Point;
import java.awt.Rectangle;

import main.MouseHandler;

public class button{
	
	public MouseHandler mh;
	public Rectangle body;
	public String text;
	
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
		if(mh.clickDif == 1) {
			clicked = true;
			mh.clickDif++;
		}
		
		if(entered() && clicked) {
			return true;
		}
		else {
			return false;
		}
	}
}