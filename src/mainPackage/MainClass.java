package mainPackage;

import guiPackage.MainGUI;

public class MainClass {
	@SuppressWarnings("unused")
	public static void main(String args[]) {
		System.out.println(String.join(" ", "Hello,", "World!"));
		
		MainGUI gui = new MainGUI();
	}
}
