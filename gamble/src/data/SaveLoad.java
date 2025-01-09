package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import main.panel;

public class SaveLoad {
	
	panel p;
	
	public SaveLoad(panel p) {
		this.p = p;
	}
	public void save() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			storeObject so = new storeObject();
			
			so.money = p.p.money;
			
			oos.writeObject(so);
			
//			oos.close();
//			System.out.println("Saved properly!");
		} catch (Exception e) {
			System.out.println("failed to save!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void load() {
		try {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
		storeObject so = (storeObject)ois.readObject();
		
		p.p.money = so.money;
		
//		System.out.println("Loaded properly");
		
		} catch(Exception e) {
			System.out.println("Failed to load!");
			e.printStackTrace();
		}
	}

}
