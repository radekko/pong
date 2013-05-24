package com.pong.resource;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JOptionPane;


public class ResourceLoader {

	private static ResourceLoader rl = new ResourceLoader();
	
	public static Image getImage(String filename) {
		Image i = null;
		try {
			i = Toolkit.getDefaultToolkit().getImage(rl.getClass().getResource(filename));
		}
		catch(Exception e){
			System.out.println("Nie wczytano obrazka");
		}
		return i;
	}

}
