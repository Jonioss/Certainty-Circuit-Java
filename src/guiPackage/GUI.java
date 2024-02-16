package guiPackage;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class GUI extends JFrame{

	private static final long serialVersionUID = 1689787319309273196L;
	
	LinkedList<String> prevStrings = new LinkedList<String>();
	
	JTextPane txtArea = new JTextPane();
	JTextPane console = new JTextPane();
	JMenuBar mb;
	JMenu fileMenu, editMenu, compileMenu, helpMenu;
	JMenuItem runItem, saveItem, saveAsItem, loadItem, copyItem, selectAllItem, pasteItem, 
				undoItem;
	
	private GUIManager guiManager = new GUIManager();
	private GUISaveLoadManager SLManager = new GUISaveLoadManager();
	private GUI gui = this;
	
	public GUI() {
		
		this.setTitle("Certainty Circuit");
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
		
		txtArea.setText("public Main() {\n\n"
			+ "\ttry {\n\n\t\t// Implement your circuit here\n\n"
			+ "\t} catch(Exception e) { e.printStackTrace(); }\n}\n\n"
			+ "// Use this area to implement new circuits");
		
		prevStrings.add(txtArea.getText());
		
		jsp.setDividerLocation(.6);
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
				SLManager.saveFile(txtArea, gui);
				
			}
		});
		
		saveAsItem = new JMenuItem("Save As");
		saveAsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SLManager.saveFileAs(txtArea, gui);
			}
		});
		
		loadItem = new JMenuItem("Load");
		loadItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				SLManager.openFile(guiManager, txtArea, gui);
				
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
				undo();
			}
			
		});
		
		
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);
		fileMenu.add(loadItem);
		
		compileMenu.add(runItem);
		
		editMenu.add(copyItem);
		editMenu.add(pasteItem);
		editMenu.add(selectAllItem);
		
		mb.add(fileMenu);
		mb.add(compileMenu);
		mb.add(editMenu);
		mb.add(undoItem);
		
		this.add(mb);
		this.setJMenuBar(mb);
	}
	
	private JScrollPane addTxtPanel() {
		
		JPanel txtPanel = new JPanel();
		txtArea.setPreferredSize(new Dimension(100, 200));
		txtArea.setBackground(Color.DARK_GRAY);
		txtArea.setForeground(Color.WHITE);
		txtArea.setCaretColor(Color.WHITE);
		
		txtArea.getStyledDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				undoItem.setEnabled(true);
				try {
					String c = e.getDocument().getText(e.getOffset(), e.getLength());
					if(c.compareTo(" ") == 0 || c.compareTo("\n") == 0) {
						prevStrings.add(txtArea.getText());
					}
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				guiManager.changeColorThread(txtArea);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
			
			
		});
		
		txtArea.addKeyListener(new KeyListener(){
		    @Override
		    public void keyPressed(KeyEvent e){
		        if(e.getKeyCode() == KeyEvent.VK_Z && e.isControlDown()){
		        	undo();
		        }
		        else if(e.getKeyCode() == KeyEvent.VK_R && e.isControlDown()) {
		        	console.setText("");
					guiManager.run(txtArea.getText(), console);
		        }
		        else if(e.getKeyCode() == KeyEvent.VK_S && e.isControlDown()) {
		        	SLManager.saveFileAs(txtArea, gui);
		        }
		    }

		    @Override
		    public void keyTyped(KeyEvent e) {
		    }

		    @Override
		    public void keyReleased(KeyEvent e) {
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
		console.setPreferredSize(new Dimension(100, 200));
		console.setBackground(Color.black);
		console.setForeground(Color.white);
		consolePanel.add(console);
		JScrollPane scroll = new JScrollPane (console, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		return scroll;
	}
	
	private void undo() {
		if(prevStrings.size() > 1) {
			prevStrings.removeLast();
		}
		txtArea.setText(prevStrings.getLast());
		
		if(prevStrings.size() <= 1) {
			undoItem.setEnabled(false);
		}
	}
	
	
}
