package com.bitwise.app.graph.processor;

import java.util.Hashtable;

import org.eclipse.core.runtime.Platform;

import com.bitwise.app.common.component.config.Component;

public class DynamicClassProcessor extends ClassLoader{
	public static DynamicClassProcessor INSTANCE = new DynamicClassProcessor(); 
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
	
	public DynamicClassProcessor(){
		/*try {
			overrideJar();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
    }
	
	public Class<?> getClass(Component componentConfig){
		if(contains(componentConfig.getName())){
			return getClazz(componentConfig.getName());
		}
		else{
			Class<?> clazz = null;
			try {
				clazz = Platform.getBundle("com.bitwise.app.model").loadClass("com.bitwise.app.graph.models." + componentConfig.getName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			INSTANCE.put(componentConfig.getName(), clazz);
			return clazz;
		}
	}

	/*private String getParentClassName(Component componentConfig) {
		return Type.getInternalName(getParentClass(componentConfig));
	}

	private Class<?> getParentClass(Component componentConfig) {
		return ComponentCategoryRepository.INSTANCE.getClassByCategotyType(componentConfig.getCategory());
	}
	
	private void overrideJar() throws FileNotFoundException, IOException{
		String existingJarFileName = getBundlePath();

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
		cw.visit(V1_7, ACC_PUBLIC, "com/bitwise/app/graph/models/" + component.getName(), null, getParentClassName(component), null);

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
	}*/
}