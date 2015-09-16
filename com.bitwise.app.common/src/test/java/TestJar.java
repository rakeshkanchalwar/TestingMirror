package test.java;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;


public class TestJar /*implements Opcodes*/ {

	public static void main(String[] args) throws Exception {
		
		/* * File file = new File(
		 * ":/Users/rakeshka/softwares/eclipse-rcp-indigo-SR2-win32-x86_64/eclipse/plugins/ch.qos.logback.classic_1.0.0.v20111214-2030.jar"
		 * );*/
		 
		File file = new File(
				"C:/Users/rakeshka/git/master/com.bitwise.app.product/target/products/com.bitwise.app.perspective.ID/win32/win32/x86_64/plugins/com.bitwise.app.model_0.0.1.201509160935.jar");
		JarFile jarFile = new JarFile(file);
		Enumeration<JarEntry> entries = jarFile.entries();

		while (entries.hasMoreElements()) {
			System.out.println(entries.nextElement().getName());
		}
	}
		/*ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		// create class structure ex. public Class NewClass extends
		// InputComponent
		cw.visit(V1_7, ACC_PUBLIC, "com/bitwise/app/graph/model/Input", null,
				"java/lang/Object", null);

		// creates a GeneratorAdapter for the (implicit) constructor
		Method defaultConstructor = Method.getMethod("void <init> ()");
		GeneratorAdapter mg = new GeneratorAdapter(ACC_PUBLIC,
				defaultConstructor, null, null, cw);
		mg.loadThis();
		mg.invokeConstructor(Type.getType(Object.class), defaultConstructor);
		mg.returnValue();
		mg.endMethod();
		cw.visitEnd();
		byte[] code = cw.toByteArray();*/

		// Open archive file
	/*	FileOutputStream stream = new FileOutputStream(file);
		JarOutputStream out = new JarOutputStream(stream, new Manifest());
		// Add archive entry
		JarEntry jarAdd = new JarEntry("com/bitwise/app/graph/model/Input.class");
		jarAdd.setTime(file.lastModified());
		out.putNextEntry(jarAdd);

		// Write file to archive
		//FileInputStream in = new FileInputStream(code);
		ByteArrayInputStream in = new ByteArrayInputStream(code);
		while (true) {
			int nRead = in.read(code, 0, code.length);
			if (nRead <= 0)
				break;
			out.write(code, 0, code.length);
		//}
		//in.close();
		out.close();
		stream.close();
		System.out.println("Adding completed OK");*/
}
