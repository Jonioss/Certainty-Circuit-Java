package guiPackage;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import testPackage.*;

public class GUI extends JFrame{

	private static final long serialVersionUID = 1689787319309273196L;
	
	int txtNumOfLines = 1;
	
	JTextPane txtArea = new JTextPane();
	JTextArea console = new JTextArea(20, 50);
	
	File file;
	
	String s1 = "package testPackage;\n"
			+ "import exceptionsPackage.*;\n"
			+ "import java.awt.Color;\n"
			+ "import FlipFlops.*;\n"
			+ "import gatesPackage.*;\n"
			+ "import guiPackage.*;\n"
			+ "import integratedCircuitsPackage.*;\n"
			+ "import mainPackage.*;\n"
			+ "\n"
			+ "@SuppressWarnings(\"unused\")\n"
			+ "public class TestClass {\n"
			+ "public void test() {\n"
			+ "try {\n", 
	s2 = "\n} catch(Exception e) {\n}\n}\n}\n";
	
	// The program's keywords
	String[] kw = {"Gate", "AND", "OR", "NOT", "NAND", "NOR", "XOR", "XNOR", "MUX21", "MUX41", "Encoder42",
					"int", "boolean", "double", "String", "float", "new", "try", "catch", "print(", "println",
					"System", "out", "Wave"};
	
	public GUI() {
		
		this.setTitle("JModelSim");
		this.setSize(1200, 800);
		this.setLocationRelativeTo(null);
		this.setFocusable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.setLayout(new BorderLayout(5, 5));
		this.add(addBtnPanel(), BorderLayout.NORTH);
		this.add(addTxtPanel(), BorderLayout.CENTER);
		this.add(addConsole(), BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	private JPanel addBtnPanel() {
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JButton btn1 = new JButton("Open");
		JButton btn2 = new JButton("Save");
		JButton btn3 = new JButton("Save As");
		JButton btn4 = new JButton("Run");
		JButton btn5 = new JButton("Test btn");
		
		btn1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				openFile();
				
			}
		});
		btn2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile(file);
				
			}
		});
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveFileAs();
			}
		});
		btn4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				run();
			}
		});
		btn5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String[] temp = txtArea.getText().split("\n");
				int numOfChars = 0;
				
				SimpleAttributeSet normalAttrs = new SimpleAttributeSet();
				StyleConstants.setForeground(normalAttrs, Color.white);
				txtArea.getStyledDocument().setCharacterAttributes(0, txtArea.getText().length(), normalAttrs, false);
				
				for(int i = 0; i < temp.length; i++) {
					numOfChars = colorText(txtArea.getStyledDocument(), temp[i], numOfChars);
				}
			}
		});
		
		btnPanel.add(btn1);
		btnPanel.add(btn2);
		btnPanel.add(btn3);
		btnPanel.add(btn4);
		btnPanel.add(btn5);
		
		return btnPanel;
	}
	
	private JScrollPane addTxtPanel() {
		JPanel txtPanel = new JPanel();
		txtArea.setPreferredSize(new Dimension(getWidth()-10, 200));
		txtArea.setBackground(Color.DARK_GRAY);
		txtArea.setForeground(Color.WHITE);
		
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
			
			private void changeColorThread() {
                Runnable cct = new Runnable() {
                    @Override
                    public void run() {
                    	String[] temp = txtArea.getText().split("\n");
        				int numOfChars = 0;
        				
        				SimpleAttributeSet normalAttrs = new SimpleAttributeSet();
        				StyleConstants.setForeground(normalAttrs, Color.white);
        				txtArea.getStyledDocument().setCharacterAttributes(0, txtArea.getText().length(), normalAttrs, false);
        				
        				for(int i = 0; i < temp.length; i++) {
        					numOfChars = colorText(txtArea.getStyledDocument(), temp[i], numOfChars);
        				}
                    }
                };
                SwingUtilities.invokeLater(cct);
            }
			
		});
		
		txtPanel.add(txtArea);
		JScrollPane scroll = new JScrollPane (txtArea, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		return scroll;
	}
	
	private JPanel addConsole() {
		JPanel consolePanel = new JPanel();
		console.setPreferredSize(new Dimension(getWidth(), 50));
		console.setBackground(Color.black);
		console.setForeground(Color.white);
		consolePanel.add(console);
		return consolePanel;
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
				
				int numOfChars = 0;
				
				while(in.hasNextLine()) {
					//txtArea.append(in.nextLine() + "\n");
					StyledDocument doc = txtArea.getStyledDocument();
					s = in.nextLine();
					doc.insertString(doc.getLength(), s + "\n", null);
					numOfChars = colorText(doc, s, numOfChars);
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
	
	private int colorText(StyledDocument doc, String s, int numOfChars) {
		
		SimpleAttributeSet attrs = new SimpleAttributeSet();
		StyleConstants.setForeground(attrs, Color.ORANGE);
		
		for(int i = 0; i < kw.length; i++) {
			int pos = s.indexOf(kw[i]);
			if(pos == -1)
				continue;
			else {
				doc.setCharacterAttributes(numOfChars + pos, kw[i].length(), attrs, false);
			}
		}
		numOfChars += s.length() + 1;
		return numOfChars;
	}

	private void run() {
		File file = new File("src/testPackage/TestClass.java");
		
		PrintWriter out;
		try {
			out = new PrintWriter(file);
			
			out.println(s1 + txtArea.getText() + s2);

	        // Close the file.
	        out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		compile(file);
		TestClass tc = new TestClass();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		
		tc.test();
		
		System.out.flush();
		System.setOut(old);
		console.setText(console.getText() + baos.toString());
		
		/*new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	File f = new File("bin/testPackage/TestClass.class");
		            	f.delete();
		            }
		        }, 
		        5000 
		);*/
	}
	
	private void compile(File file) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
	    StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

	    try {
			fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays
			      .asList(new File("bin/")));
			@SuppressWarnings("unused")
			boolean success = compiler.getTask(null, fileManager, null, null, null,
			          fileManager.getJavaFileObjectsFromFiles(Arrays.asList(file))).call();
			fileManager.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
