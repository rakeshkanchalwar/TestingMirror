package test.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

/*import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

import com.bitwise.app.graph.models.InputComponent;*/

public class TestJarReplace /*implements Opcodes*/{
	/**
	 * main()
	 *//*
	public static void main(String[] args) throws IOException {
		// Get the jar name and entry name from the command-line.

		String jarName = "C:/Users/rakeshka/git/master/com.bitwise.app.product/target/" +
				"products/com.bitwise.app.perspective.ID/win32/win32/x86_64/plugins/com.bitwise.app.model_0.0.1.201509160643.jar";
		String inputFileName = "com/bitwise/app/graph/models/Input.class";
		String outputFileName = "com/bitwise/app/graph/models/Output.class";

		// Create file descriptors for the jar and a temp jar.

		File jarFile = new File(jarName);
		File tempJarFile = new File(jarName + ".tmp");

		// Open the jar file.

		JarFile jar = new JarFile(jarFile);
		System.out.println(jarName + " opened.");

		// Initialize a flag that will indicate that the jar was updated.

		boolean jarUpdated = false;

		try {
			// Create a temp jar file with no manifest. (The manifest will
			// be copied when the entries are copied.)

			Manifest jarManifest = jar.getManifest();
			JarOutputStream tempJar = new JarOutputStream(new FileOutputStream(
					tempJarFile));

			// Allocate a buffer for reading entry data.

			byte[] buffer = new byte[1024];
			int bytesRead;

			try {
				// Open the given file.

				//FileInputStream file = new FileInputStream(fileName);

				try {
					// Create a jar entry and add it to the temp jar.

					JarEntry entry = new JarEntry(inputFileName);
					tempJar.putNextEntry(entry);

					// Read the file and write it to the jar.

					//while ((bytesRead = file.read(buffer)) != -1) {
					byte[] code = getCode("com/bitwise/app/graph/models/Input","com/bitwise/app/graph/models/InputComponent");
						tempJar.write(code, 0, code.length);
					//}
					tempJar.closeEntry();
					System.out.println(entry.getName() + " added.");
				} finally {
					//file.close();
				}
				
				try {
					// Create a jar entry and add it to the temp jar.

					JarEntry entry = new JarEntry(outputFileName);
					tempJar.putNextEntry(entry);

					// Read the file and write it to the jar.

					//while ((bytesRead = file.read(buffer)) != -1) {
					byte[] code = getCode("com/bitwise/app/graph/models/Output", "com/bitwise/app/graph/models/OutputComponent");
						tempJar.write(code, 0, code.length);
					//}
						tempJar.closeEntry();
					System.out.println(entry.getName() + " added.");
				} finally {
					//file.close();
				}

				// Loop through the jar entries and add them to the temp jar,
				// skipping the entry that was added to the temp jar already.

				for (Enumeration entries = jar.entries(); entries
						.hasMoreElements();) {
					// Get the next entry.

					JarEntry entry = (JarEntry) entries.nextElement();

					// If the entry has not been added already, add it.

					if (!entry.getName().equals(inputFileName) && !entry.getName().equals(outputFileName)) {
						// Get an input stream for the entry.

						InputStream entryStream = jar.getInputStream(entry);

						// Read the entry and write it to the temp jar.

						tempJar.putNextEntry(entry);

						while ((bytesRead = entryStream.read(buffer)) != -1) {
							tempJar.write(buffer, 0, bytesRead);
						}
					}
				}

				jarUpdated = true;
			} catch (Exception ex) {
				System.out.println(ex);

				// Add a stub entry here, so that the jar will close without an
				// exception.

				tempJar.putNextEntry(new JarEntry("stub"));
			} finally {
				tempJar.close();
			}
		} finally {
			jar.close();
			System.out.println(jarName + " closed.");

			// If the jar was not updated, delete the temp jar file.

			if (!jarUpdated) {
				tempJarFile.delete();
			}
		}

		// If the jar was updated, delete the original jar file and rename the
		// temp jar file to the original name.

		if (jarUpdated) {
			jarFile.delete();
			tempJarFile.renameTo(jarFile);
			System.out.println(jarName + " updated.");
		}
	}

	private static byte[] getCode(String classTobeCreated, String classTobeExtended) throws IOException {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		// create class structure ex. public Class NewClass extends
		// InputComponent
		cw.visit(V1_7, ACC_PUBLIC, classTobeCreated, null, classTobeExtended, null);

		// creates a GeneratorAdapter for the (implicit) constructor
		Method defaultConstructor = Method.getMethod("void <init> ()");
		GeneratorAdapter mg = new GeneratorAdapter(ACC_PUBLIC,
				defaultConstructor, null, null, cw);
		mg.loadThis();
		mg.invokeConstructor(Type.getType(InputComponent.class), defaultConstructor);
		mg.returnValue();
		mg.endMethod();
		cw.visitEnd();
		byte[] code = cw.toByteArray();
		File file = new File("C:/" + classTobeCreated + ".class");
		if(!file.exists()){
			file.createNewFile();
		}
		FileOutputStream f = new FileOutputStream(file);
		f.write(code);
		f.close();
		return code;
	}*/
}
