package com.bitwise.app.graph.processor;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;
/*import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;*/

import com.bitwise.app.common.component.config.Component;
import com.bitwise.app.common.util.XMLConfigUtil;

public class CopyOfDynamicClassProcessor /*extends DefaultClassLoader implements Opcodes */ {
	/*public static CopyOfDynamicClassProcessor INSTANCE = new CopyOfDynamicClassProcessor(); 
	private Hashtable<String, Class<?>> classMapStringToClass = new Hashtable<String, Class<?>>();
	private Hashtable<Class<?>, String> classMapClassToString = new Hashtable<Class<?>, String>();
	
	public boolean contains(Class<?> clazz) {
		return classMapStringToClass.containsValue(clazz);
	}
	
	public boolean contains(String clazzName) {
		return classMapClassToString.containsValue(clazzName);
	}
	
	private void put(String clazzName, Class<?> clazz){
		classMapStringToClass.put(clazzName, clazz);
		classMapClassToString.put(clazz, clazzName);
	}
	
	public String getClazzName(Class<?> clazz){
		return classMapClassToString.get(clazz);
	}
	
	public Class<?> getClazz(String className){
		return classMapStringToClass.get(className);
	}
	
	public CopyOfDynamicClassProcessor(){
        //super(DynamicClassProcessor.class.getClassLoader());
    }
	
	public Class<?> createClass(Component componentConfig){
		if(contains(componentConfig.getName())){
			return getClazz(componentConfig.getName());
		}
		else{
			byte[] code = createDynamicClass(componentConfig);
			writeToJar(code);
	        Class<?> clazz = null;
			try {
				clazz = INSTANCE.getClass().getClassLoader().loadClass("com.bitwise.app.graph.models." + componentConfig.getName());
				Status status = new Status(IStatus.ERROR, "com.bitwise.app.common", "XML read Success", null);
				StatusManager.getManager().handle(status, StatusManager.BLOCK);
			} catch (Exception e) {
				Status status = new Status(IStatus.ERROR, "com.bitwise.app.common", "XML read Failed", null);
				StatusManager.getManager().handle(status, StatusManager.BLOCK);
			}
	    	INSTANCE.put(componentConfig.getName(), clazz);
			Class<?> clazz = null;
			try {
				clazz = this.getClass().getClassLoader().loadClass("com.bitwise.app.graph.models." + componentConfig.getName());
				INSTANCE.put(componentConfig.getName(), clazz);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return clazz;
		}
	}

	private void writeToJar(byte[] code) {
		String pathToPlugins = Platform.getInstallLocation().getURL().getPath() + "plugins";
		FilenameFilter filter = XMLConfigUtil.INSTANCE.getFileNameFilter(".jar");
		String[] filteredFiles = XMLConfigUtil.INSTANCE.getFilteredFiles(pathToPlugins, filter);
		String bundleName = null;
		for (int i = 0; i < filteredFiles.length; i++) {
			if(filteredFiles[i].startsWith("com.bitwise.app.model")){
				bundleName = filteredFiles[i];
				break;
			}
		}
		
		if(bundleName != null){
			try {
				print(pathToPlugins + "/" + bundleName);	
				File file = new File(pathToPlugins + "/" + bundleName);
				
				FileOutputStream stream = new FileOutputStream(file);
				JarOutputStream out = new JarOutputStream(stream, new Manifest());

				
				// Add archive entry
				JarEntry jarAdd = new JarEntry("com/bitwise/app/graph/models/Input.class");
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
				}
				out.closeEntry();
				
				in.close();
				out.close();
				stream.close();
				
				
				print(pathToPlugins + "/" + bundleName);	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void print(String path) throws Exception{
		JarFile jarFile = new JarFile(path);
		Enumeration<JarEntry> entries = jarFile.entries();

		while (entries.hasMoreElements()) {
			JarEntry nextElement = entries.nextElement();
			System.out.println(nextElement.getName());
		}	
	}
	
	private byte[] createDynamicClass(Component componentConfig){
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        //create class structure ex. public Class NewClass extends InputComponent 
		cw.visit(V1_7, ACC_PUBLIC,  componentConfig.getName(), null, getParentClassName(componentConfig), null);

        // creates a GeneratorAdapter for the (implicit) constructor
        Method defaultConstructor = Method.getMethod("void <init> ()");
        GeneratorAdapter mg = new GeneratorAdapter(ACC_PUBLIC, defaultConstructor, null, null, cw);
        mg.loadThis();
        mg.invokeConstructor(Type.getType(getParentClass(componentConfig)), defaultConstructor);
        mg.returnValue();
        mg.endMethod();
        cw.visitEnd();

        byte[] code = cw.toByteArray();
		return code;
	}
	
	private void OverrideTheJar(){
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
					byte[] code = createDynamicClass();
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
	
	private String getParentClassName(Component componentConfig) {
		return Type.getInternalName(getParentClass(componentConfig));
	}

	private Class<?> getParentClass(Component componentConfig) {
		return ComponentCategoryRepository.INSTANCE.getClassByCategotyType(componentConfig.getCategory());
	}*/
}