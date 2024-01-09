package guiPackage;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.undo.UndoManager;

public class GUI extends JFrame{

	private static final long serialVersionUID = 1689787319309273196L;
	
	int txtNumOfLines = 1;
	
	JTextPane txtArea = new JTextPane();
	JTextArea console = new JTextArea(20, 40);
	JMenuBar mb;
	JMenu fileMenu, editMenu, compileMenu, helpMenu;
	JMenuItem runItem, saveItem, saveAsItem, loadItem, copyItem, selectAllItem, pasteItem,
				undoItem;
	
	File file;
	
	// The program's keywords
	String[] gate_kw = {"Gate", "AND", "OR", "NOT", "NAND", "NOR", "XOR", "XNOR", "MUX21", "MUX41", "Encoder42",
					"Wave", "Clock"};
	String[] code_kw = {"int", "boolean", "double", "String", "float", "new", "try", "catch", "print(", "println",
					"System", "out", "write", "hi", "true", "showWave", "setInputs", "setWaveColors", "setDimensions", "setName", "printTruthTable",
					"getInputs", "isValidValue"};
	
	private GUIManager guiManager = new GUIManager();
	private UndoManager undoManager = new UndoManager();
	
	public GUI() {
		
		this.setTitle("JModelSim");
		this.setSize(1200, 800);
		this.setLocationRelativeTo(null);
		this.setFocusable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		
		this.setLayout(new BorderLayout(5, 5));
		
		JSplitPane jsp = new JSplitPane(SwingConstants.VERTICAL, addTxtPanel(), addConsole());
		jsp.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		addBtnPanel();
		//this.add(addBtnPanel(), BorderLayout.NORTH);
		this.add(jsp, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	private void addBtnPanel() {
		
		mb = new JMenuBar();
		
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		compileMenu = new JMenu("Compile");
		helpMenu = new JMenu("Help");
		
		
		runItem = new JMenuItem("Run");
		runItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				console.setText("");
				guiManager.run(txtArea.getText(), console);
			}
		});
		
		saveItem = new JMenuItem("Save");
		saveItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile(file);
				
			}
		});
		
		saveAsItem = new JMenuItem("Save As");
		saveAsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveFileAs();
			}
		});
		
		loadItem = new JMenuItem("Load");
		loadItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				openFile();
				
			}
		});
		
		copyItem = new JMenuItem("Copy");
		copyItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtArea.copy();
			}
			
		});
		
		selectAllItem = new JMenuItem("Select All");
		selectAllItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtArea.selectAll();
			}
			
		});
		
		pasteItem = new JMenuItem("Paste");
		pasteItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtArea.paste();
			}
			
		});
		
		undoItem = new JMenuItem("Undo");
		undoItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				undoManager.undo();
				undoManager.undo();
				undoManager.undo();
			}
			
		});
		
		
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);
		fileMenu.add(loadItem);
		
		compileMenu.add(runItem);
		
		editMenu.add(copyItem);
		editMenu.add(pasteItem);
		editMenu.add(selectAllItem);
		editMenu.add(undoItem);
		
		mb.add(fileMenu);
		mb.add(compileMenu);
		mb.add(editMenu);
		
		this.add(mb);
		this.setJMenuBar(mb);
	}
	
	private JScrollPane addTxtPanel() {
		
		JPanel txtPanel = new JPanel();
		txtArea.setPreferredSize(new Dimension(getWidth()-10, 200));
		txtArea.setBackground(Color.DARK_GRAY);
		txtArea.setForeground(Color.WHITE);
		txtArea.getDocument().addUndoableEditListener(undoManager);
		
		txtArea.getStyledDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changeColorThread();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changeColorThread();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
		txtPanel.add(txtArea);
		JScrollPane scroll = new JScrollPane (txtArea, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		
		return scroll;
	}
	
	private JScrollPane addConsole() {
		JPanel consolePanel = new JPanel();
		console.setEditable(false);
		console.setPreferredSize(new Dimension(getWidth(), 50));
		console.setBackground(Color.black);
		console.setForeground(Color.white);
		consolePanel.add(console);
		JScrollPane scroll = new JScrollPane (console, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		return scroll;
	}
	
	private void openFile(){
		JFileChooser fileChooser = new JFileChooser();
		//FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "TXT", "txt");
		//fileChooser.setFileFilter(filter);
		if (fileChooser.showOpenDialog(this.getComponent(0)) == JFileChooser.APPROVE_OPTION) {
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
					changeColorThread();
				}
			} catch (Exception e) {
				System.out.println("File Reading Ended");
			}
		} else {
			System.out.println("File Open Failed");
		}
	}
	
	private void saveFile(File file) {
		// Open the file.
        PrintWriter out;
		try {
			out = new PrintWriter(file);
			
			out.println(txtArea.getText());

	        // Close the file.
	        out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void saveFileAs() {
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showSaveDialog(this.getComponent(0)) == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			System.out.println("File Save Successful");
			saveFile(file);
			
		} else {
			System.out.println("File Save Failed");
		}
	}
	
	private int colorText(StyledDocument doc, String s, int numOfChars, String[] kw, Color c) {
		
		SimpleAttributeSet attrs = new SimpleAttributeSet();
		StyleConstants.setForeground(attrs, c);
		
		for(int i = 0; i < kw.length; i++) {
			int pos = s.indexOf(kw[i]);
			if(pos == -1 || guiManager.countAppearancesOf(s.substring(0, pos), '\"') % 2 != 0)
				continue;
			else {
				doc.setCharacterAttributes(numOfChars + pos, kw[i].length(), attrs, false);
			}
		}
		if(System.getProperty("os.name").toLowerCase().startsWith("windows")) {
			numOfChars += s.length();
		}
		else {
			numOfChars += s.length() + 1;
		}
		return numOfChars;
	}
	
	private void changeColorThread() {
        Runnable cct = new Runnable() {
            @Override
            public void run() {
            	String[] temp = txtArea.getText().split("\n");
				int numOfChars1 = 0, numOfChars2 = 0;
				
				SimpleAttributeSet normalAttrs = new SimpleAttributeSet();
				StyleConstants.setForeground(normalAttrs, Color.white);
				txtArea.getStyledDocument().setCharacterAttributes(0, txtArea.getText().length(), normalAttrs, false);
				
				for(int i = 0; i < temp.length; i++) {
					numOfChars2 = colorText(txtArea.getStyledDocument(), temp[i], numOfChars2, gate_kw, Color.CYAN);
					numOfChars1 = colorText(txtArea.getStyledDocument(), temp[i], numOfChars1, code_kw, Color.ORANGE);
				}
            }
        };
        SwingUtilities.invokeLater(cct);
    }
	
}
