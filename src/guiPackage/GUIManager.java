package guiPackage;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class GUIManager {
	
	// The program's keywords
	String[] gate_kw = {"Gate", "AND", "OR", "NOT", "NAND", "NOR", "XOR", "XNOR", "MUX21", "MUX41", "Encoder42",
					"Wave", "Clock", "TruthTable"};
	String[] comm = {"//"};
	String[] type_kw = {"int ", "boolean ", "double ", "String ", "float ", "true", "false", "Exception ", "public ",
			"private", "final"};
	
	String s1 = "package test;\n"
			+ "import exceptionsPackage.*;\n"
			+ "import java.awt.Color;\n"
			+ "import FlipFlops.*;\n"
			+ "import gatesPackage.*;\n"
			+ "import guiPackage.*;\n"
			+ "import integratedCircuitsPackage.*;\n"
			+ "import mainPackage.*;\n"
			+ "\n"
			+ "@SuppressWarnings(\"unused\")\n"
			+ "public class Main {\n"
			+ "private void write(Object obj) { System.out.println(obj); }",
	s2 = "\n}\n";
	
	int colorSetChoice = 0;
	Color[][] ColorSet = {
			{ // Dark Color Set
					new Color(252, 144, 3),
					new Color(15, 212, 202),
					new Color(27, 194, 52)
			},
			{ // Light Color Set
					new Color(235, 5, 247),
					new Color(5, 247, 207),
					Color.GREEN
			},
			{ // Pink Color Set
					new Color(174, 5, 247),
					new Color(247, 5, 223),
					new Color(247, 5, 114)
			},
			{ // Fire Color Set
					new Color(255, 0, 0),
					new Color(250, 121, 0),
					new Color(250, 212, 0)
			},
			{ // Beach Color Set
					new Color(0, 192, 250),
					new Color(194, 178, 128),
					new Color(0, 80, 240)
			}
	};
	
	public int countAppearancesOf(String s, char c) {
		int num = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == c) {
				num++;
			}
		}
		return num;
	}
	
	public void compileAndRun(String txtAreaText) {
		
		String source = s1 + txtAreaText + s2;
		try {
			File root = Files.createTempDirectory("java").toFile();
			File sourceFile = new File(root, "test/Main.java");
			sourceFile.getParentFile().mkdirs();
			Files.write(sourceFile.toPath(), source.getBytes(StandardCharsets.UTF_8));

			// Compile source file.
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			int s = compiler.run(null, null, null, sourceFile.getPath());

			// Load and instantiate compiled class.
			if(s == 0) {
				System.out.println("----------Compilation Successful!----------");
			}
			URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
			Class<?> cls = Class.forName("test.Main", true, classLoader);
			@SuppressWarnings("unused")
			Object instance = cls.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			System.out.println("------------Compilation Failed!------------");
			System.out.println(e.toString());
		}
		
	}
	
	public void run(String txtAreaText, JTextPane console) {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		
		compileAndRun(txtAreaText);
		
		System.out.flush();
		System.setOut(old);
		console.setText(console.getText() + baos.toString());
		
	}
	
	public int colorText(StyledDocument doc, String s, int numOfChars, String[] kw, Color c) {
		
		SimpleAttributeSet attrs = new SimpleAttributeSet();
		StyleConstants.setForeground(attrs, c);
		
		String new_s = s;
		
		for(int i = 0; i < kw.length; i++) {
			int pos = new_s.indexOf(kw[i]);
			
			if(pos == -1 || countAppearancesOf(new_s.substring(0, pos), '\"') % 2 != 0)
				continue;
			else {
				if(kw[0].compareTo("//") == 0) {
					doc.setCharacterAttributes(numOfChars + pos, new_s.length() - pos, attrs, false);
					continue;
				}
				else {
					doc.setCharacterAttributes(numOfChars + pos, kw[i].length(), attrs, false);
					String temp = "";
					for(int j = 0; j < kw[i].length(); j++) {
						temp += "_";
					}
					new_s = new_s.replaceFirst(kw[i], temp);
				}
			}
			
			if(new_s.indexOf(kw[i]) != -1) {
				colorText(doc, new_s, numOfChars, kw, c);
			}
			
			new_s = s;
		}
		numOfChars += s.length() + 1;
		return numOfChars;
	}
	
	public void changeColorThread(JTextPane txtArea, Color normalTextColor) {
        Runnable cct = new Runnable() {
            @Override
            public void run() {
            	String[] temp = txtArea.getText().split("\n");
				int numOfCharsGateKW = 0, numOfCharsComment = 0, numOfCharsType = 0;
				
				SimpleAttributeSet normalAttrs = new SimpleAttributeSet();
				StyleConstants.setForeground(normalAttrs, normalTextColor);
				txtArea.getStyledDocument().setCharacterAttributes(0, txtArea.getText().length(), normalAttrs, false);
				
				for(int i = 0; i < temp.length; i++) {
					numOfCharsType = colorText(txtArea.getStyledDocument(), temp[i], numOfCharsType, type_kw, ColorSet[colorSetChoice][0]); // Orange
					numOfCharsGateKW = colorText(txtArea.getStyledDocument(), temp[i], numOfCharsGateKW, gate_kw, ColorSet[colorSetChoice][1]); // Blue
					numOfCharsComment = colorText(txtArea.getStyledDocument(), temp[i], numOfCharsComment, comm, ColorSet[colorSetChoice][2]); // Green
				}
            }
        };
        SwingUtilities.invokeLater(cct);
    }
}
