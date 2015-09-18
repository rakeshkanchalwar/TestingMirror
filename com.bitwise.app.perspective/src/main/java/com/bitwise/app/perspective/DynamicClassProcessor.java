package com.bitwise.app.perspective;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;
import org.osgi.framework.Bundle;
import org.xml.sax.SAXException;

import com.bitwise.app.common.component.config.Component;
import com.bitwise.app.common.util.XMLConfigUtil;

public class DynamicClassProcessor extends ClassLoader implements Opcodes{
	public static DynamicClassProcessor INSTANCE = new DynamicClassProcessor(); 
	public static boolean foundBundle = false;
	/*private Hashtable<String, Class<?>> classMapStringToClass = new Hashtable<String, Class<?>>();
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
	}*/
	
	public DynamicClassProcessor(){
		try {
			overrideJar();
			createTempFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	private void createTempFile() throws Exception {
		// This application is running from packaged product
		if(foundBundle == true)
			return;
		Bundle bundle = Platform.getBundle("com.bitwise.app.model");
		String location = bundle.getLocation();
		location = location.substring(location.indexOf("/") + 1);
		for (Component component : XMLConfigUtil.INSTANCE.getComponentConfig()) {
			File filePath = new File(location + "/src/com/bitwise/app/graph/models/" + "/" + component.getName() + ".java");
			if(filePath.exists()){
				filePath.deleteOnExit();
				continue;
			}
			Files.createFile(filePath.toPath());
			filePath.deleteOnExit();
			String parentClassName = getParentClass(component).substring(getParentClass(component).lastIndexOf("/") + 1);
			FileWriter fileWriter = new FileWriter(filePath);
			fileWriter.write("package com.bitwise.app.graph.models;");
			fileWriter.write(System.lineSeparator());
			fileWriter.write("public class " + component.getName() + " extends " + parentClassName + "{");
			fileWriter.write(System.lineSeparator());
			fileWriter.write("}"); 
			fileWriter.flush();      
			fileWriter.close();
		}
	}
	
	public void initiate(){
	}
	
	/*public Class<?> getClass(Component componentConfig){
		if(contains(componentConfig.getName())){
			return getClazz(componentConfig.getName());
		}
		else{
			Class<?> clazz = null;
			try {
				clazz = INSTANCE.getClass().getClassLoader().loadClass("com.bitwise.app.graph.models." + componentConfig.getName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			INSTANCE.put(componentConfig.getName(), clazz);
			return clazz;
		}
	}*/

	private String getParentClass(Component componentConfig) {
		return ComponentCategoryRepository.INSTANCE.getClassByCategotyType(componentConfig.getCategory());
	}
	
	private void overrideJar() throws FileNotFoundException, IOException{
		String existingJarFileName = getBundlePath();
		if(existingJarFileName == null){
			//this application is running in development mode
			return;
		}
		// Create file descriptors for the jar and a temp jar.
		File existingJarFile = new File(existingJarFileName);
		File tempJarFile = new File(existingJarFileName + ".tmp");
		// Open the jar file.
		JarFile existingJar = new JarFile(existingJarFile);
		// Initialize a flag that will indicate that the jar was updated.
		boolean jarUpdated = false;

		try {
			// Create a temp jar file with no manifest. (The manifest will
			// be copied when the entries are copied.)
			Manifest jarManifest = existingJar.getManifest();
			JarOutputStream tempJar = new JarOutputStream(new FileOutputStream(tempJarFile));

			try {
				createJarEntryOfTheClasses(tempJar);
				addExistingJarEntries(existingJar, tempJar);
				jarUpdated = true;
			} catch (Exception exception) {
				// Add a stub entry here, so that the jar will close without an exception.
				tempJar.putNextEntry(new JarEntry("stub"));
			} finally {
				tempJar.close();
			}
		} finally {
			existingJar.close();
			// If the jar was not updated, delete the temp jar file.
			if (!jarUpdated) {
				tempJarFile.delete();
			}
		}

		// If the jar was updated, delete the original jar file and rename the
		// temp jar file to the original name.
		if (jarUpdated) {
			Status status = new Status(IStatus.INFO, "com.bitwise.app.common", "Reached with" , null);
			StatusManager.getManager().handle(status, StatusManager.BLOCK);
			existingJarFile.delete();
			status = new Status(IStatus.INFO, "com.bitwise.app.common", "Deleted", null);
			StatusManager.getManager().handle(status, StatusManager.BLOCK);
			tempJarFile.renameTo(existingJarFile);
			status = new Status(IStatus.INFO, "com.bitwise.app.common", "Renamed", null);
			StatusManager.getManager().handle(status, StatusManager.BLOCK);
		}
	}

	private String getBundlePath() {
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
		String existingJarFileName = null;
		if(bundleName != null){
			foundBundle = true;
			existingJarFileName = pathToPlugins + "/" + bundleName;
		}
		return existingJarFileName;
	}

	private void addExistingJarEntries(JarFile existingJar, JarOutputStream tempJar) throws IOException {
		int bytesRead;
		// Allocate a buffer for reading entry data.
		byte[] buffer = new byte[1024];
		// Loop through the jar entries and add them to the temp jar,
		// skipping the entry that was added to the temp jar already.
		for (Enumeration entries = existingJar.entries(); entries.hasMoreElements();) {
			// Get the next entry.
			JarEntry entry = (JarEntry) entries.nextElement();
			// Get an input stream for the entry.
			InputStream entryStream = existingJar.getInputStream(entry);
			// Read the entry and write it to the temp jar.
			tempJar.putNextEntry(entry);
			while ((bytesRead = entryStream.read(buffer)) != -1) {
				tempJar.write(buffer, 0, bytesRead);
			}
			tempJar.closeEntry();
		}
	}

	private void createJarEntryOfTheClasses(JarOutputStream tempJar)
			throws SAXException, IOException {
		for (Component component : XMLConfigUtil.INSTANCE.getComponentConfig()) {
			// Create a jar entry and add it to the temp jar.
			JarEntry entry = new JarEntry("com/bitwise/app/graph/models/" + component.getName() + ".class");
			tempJar.putNextEntry(entry);
			byte[] code = generateByteCodeForClass(component);
			tempJar.write(code, 0, code.length);
			tempJar.closeEntry();
		}
	}

	private byte[] generateByteCodeForClass(Component component) {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        //create class structure ex. public Class NewClass extends InputComponent 
		cw.visit(V1_7, ACC_PUBLIC, "com/bitwise/app/graph/models/" + component.getName(), null, getParentClass(component), null);

        // creates a GeneratorAdapter for the (implicit) constructor
        Method defaultConstructor = Method.getMethod("void <init> ()");
        GeneratorAdapter mg = new GeneratorAdapter(ACC_PUBLIC, defaultConstructor, null, null, cw);
        mg.loadThis();
        mg.invokeConstructor(Type.getType(getParentClass(component)), defaultConstructor);
        mg.returnValue();
        mg.endMethod();

        cw.visitEnd();
        byte[]  code = cw.toByteArray();
        return code;
	}
}