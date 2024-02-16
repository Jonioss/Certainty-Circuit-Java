package guiPackage;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

public class GUISaveLoadManager {
	
	File file = null;
	
	public void openFile(GUIManager guiManager, JTextPane txtArea, GUI gui){
		JFileChooser fileChooser = new JFileChooser();
		//FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "TXT", "txt");
		//fileChooser.setFileFilter(filter);
		if (fileChooser.showOpenDialog(gui.getComponent(0)) == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			System.out.println("File Open Successful");
			
			Scanner in;
			try {
				in = new Scanner(file);
				txtArea.setText("");
				String s;
				
				while(in.hasNextLine()) {
					//txtArea.append(in.nextLine() + "\n");
					StyledDocument doc = txtArea.getStyledDocument();
					s = in.nextLine();
					doc.insertString(doc.getLength(), s + "\n", null);
					guiManager.changeColorThread(txtArea);
				}
			} catch (Exception e) {
				System.out.println("File Reading Ended");
			}
		} else {
			System.out.println("File Open Failed");
		}
	}
	
	public void saveFile(JTextPane txtArea, GUI gui) {
		// Open the file.
		if(file == null) {
			saveFileAs(txtArea, gui);
			return;
		}
        PrintWriter out;
		try {
			out = new PrintWriter(file);
			
			out.println(txtArea.getText());

	        // Close the file.
	        out.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(gui, e.getStackTrace());
		}
	}
	
	public void saveFileAs(JTextPane txtArea, GUI gui) {
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showSaveDialog(gui.getComponent(0)) == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			System.out.println("File Save Successful");
			saveFile(txtArea, gui);
			
		} else {
			System.out.println("File Save Failed");
		}
	}
}
