package com.bitwise.app.graph.processor;

import java.util.Hashtable;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.bitwise.app.common.component.config.Component;
import com.bitwise.app.graph.model.components.Filter;
import com.bitwise.app.graph.model.components.Unionall;
import com.bitwise.app.graph.model.components.IFDelimited;
import com.bitwise.app.graph.model.components.OFDelimited;
import com.bitwise.app.graph.model.components.Clone;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicClassProcessor.
 */
public class DynamicClassProcessor extends ClassLoader implements Opcodes{
	public static DynamicClassProcessor INSTANCE = new DynamicClassProcessor(); 
	private Hashtable<String, Class<?>> classMapStringToClass = new Hashtable<String, Class<?>>();
	private Hashtable<Class<?>, String> classMapClassToString = new Hashtable<Class<?>, String>();
	
	/**
	 * Contains.
	 * 
	 * @param clazz
	 *            the clazz
	 * @return true, if successful
	 */
	public boolean contains(Class<?> clazz) {
		return classMapStringToClass.containsValue(clazz);
	}
	
	/**
	 * Contains.
	 * 
	 * @param clazzName
	 *            the clazz name
	 * @return true, if successful
	 */
	public boolean contains(String clazzName) {
		return classMapClassToString.containsValue(clazzName);
	}
	
	private void put(String clazzName, Class<?> clazz){
		classMapStringToClass.put(clazzName, clazz);
		classMapClassToString.put(clazz, clazzName);
	}
	
	/**
	 * Gets the clazz name.
	 * 
	 * @param clazz
	 *            the clazz
	 * @return the clazz name
	 */
	public String getClazzName(Class<?> clazz){
		return classMapClassToString.get(clazz);
	}
	
	/**
	 * Gets the clazz.
	 * 
	 * @param className
	 *            the class name
	 * @return the clazz
	 */
	public Class<?> getClazz(String className){
		return classMapStringToClass.get(className);
	}
	
	/**
	 * Instantiates a new dynamic class processor.
	 */
	public DynamicClassProcessor(){
        super(DynamicClassProcessor.class.getClassLoader());
    }
	
	/**
	 * Creates the class.
	 * 
	 * @param componentConfig
	 *            the component config
	 * @return the class
	 */
	public Class<?> createClass(Component componentConfig){
		if(contains(componentConfig.getName())){
			return getClazz(componentConfig.getName());
		}
		else{
//			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
//	        //create class structure ex. public Class NewClass extends InputComponent 
//			cw.visit(V1_7, ACC_PUBLIC, componentConfig.getName(), null, getParentClassName(componentConfig), null);
//	
//	        // creates a GeneratorAdapter for the (implicit) constructor
//	        Method defaultConstructor = Method.getMethod("void <init> ()");
//	        GeneratorAdapter mg = new GeneratorAdapter(ACC_PUBLIC, defaultConstructor, null, null, cw);
//	        mg.loadThis();
//	        mg.invokeConstructor(Type.getType(getParentClass(componentConfig)), defaultConstructor);
//	        mg.returnValue();
//	        mg.endMethod();
//	
//	        cw.visitEnd();
//	
//	        byte[]  code = cw.toByteArray();
//	        Class<?> clazz = INSTANCE.defineClass(componentConfig.getName(), code, 0, code.length);
//	        INSTANCE.put(componentConfig.getName(), clazz);
//	        return clazz;
			
			Class<?> clazz;
			try {
				clazz = this.getClass().getClassLoader().loadClass("com.bitwise.app.graph.model.components." + componentConfig.getName());
			} catch (ClassNotFoundException e) {
				throw new RuntimeException();
				//TODO : add logger
			}
			INSTANCE.put(componentConfig.getName(), clazz);
			return clazz;
		}
	}


	private String getParentClassName(Component componentConfig) {
		return Type.getInternalName(getParentClass(componentConfig));
	}

	private Class<?> getParentClass(Component componentConfig) {
		return ComponentCategoryRepository.INSTANCE.getClassByCategotyType(componentConfig.getCategory());
	}
}