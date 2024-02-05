package guiPackage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javax.swing.JTextArea;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class GUIManager {
	
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
			compiler.run(null, null, null, sourceFile.getPath());

			// Load and instantiate compiled class.
			System.out.println("----------Compilation Successful!----------");
			URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
			Class<?> cls = Class.forName("test.Main", true, classLoader);
			@SuppressWarnings("unused")
			Object instance = cls.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			System.out.println("------------Compilation Failed!------------");
		}
		
	}
	
	public void run(String txtAreaText, JTextArea console) {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		
		compileAndRun(txtAreaText);
		
		System.out.flush();
		System.setOut(old);
		console.setText(console.getText() + baos.toString());
		
	}
}
