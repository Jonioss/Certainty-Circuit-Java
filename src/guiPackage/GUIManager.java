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
		String[] code_kw = {"new", "try", "catch", "print(", "println", "()",
						"System", "out", "write", "showWave", "setInputs", "setWaveColors", "setDimensions", "setName", "printTruthTable",
						"getInputs", "isValidValue", "printStackTrace", "for", "while", "simplify"};
		String[] comm = {"//"};
		String[] type_kw = {"int", "boolean", "double", "String", "float", "true", "false", "Exception", "Main", "public"};
	
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
		
		for(int i = 0; i < kw.length; i++) {
			int pos = s.indexOf(kw[i]);
			
			if(pos == -1 || countAppearancesOf(s.substring(0, pos), '\"') % 2 != 0)
				continue;
			else {
				if(kw[0].compareTo("//") == 0) {
					doc.setCharacterAttributes(numOfChars + pos, s.length() - pos, attrs, false);
				}
				else {
					doc.setCharacterAttributes(numOfChars + pos, kw[i].length(), attrs, false);
				}
				
			}
		}
		numOfChars += s.length() + 1;
		return numOfChars;
	}
	
	public void changeColorThread(JTextPane txtArea) {
        Runnable cct = new Runnable() {
            @Override
            public void run() {
            	String[] temp = txtArea.getText().split("\n");
				int numOfCharsCodeKW = 0, numOfCharsGateKW = 0, numOfCharsComment = 0, numOfCharsType = 0;
				
				SimpleAttributeSet normalAttrs = new SimpleAttributeSet();
				StyleConstants.setForeground(normalAttrs, Color.white);
				txtArea.getStyledDocument().setCharacterAttributes(0, txtArea.getText().length(), normalAttrs, false);
				
				for(int i = 0; i < temp.length; i++) {
					numOfCharsType = colorText(txtArea.getStyledDocument(), temp[i], numOfCharsType, type_kw, Color.YELLOW);
					numOfCharsGateKW = colorText(txtArea.getStyledDocument(), temp[i], numOfCharsGateKW, gate_kw, Color.CYAN);
					numOfCharsCodeKW = colorText(txtArea.getStyledDocument(), temp[i], numOfCharsCodeKW, code_kw, Color.ORANGE);
					numOfCharsComment = colorText(txtArea.getStyledDocument(), temp[i], numOfCharsComment, comm, Color.GREEN);
				}
            }
        };
        SwingUtilities.invokeLater(cct);
    }
}
