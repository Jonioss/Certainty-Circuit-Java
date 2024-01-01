package mainPackage;

import guiPackage.GUI;

public class MainClass {
	@SuppressWarnings("unused")
	public static void main(String args[]) {
		System.out.println(String.join(" ", "Hello,", "World!"));
		
		GUI gui = new GUI();
	}
}
