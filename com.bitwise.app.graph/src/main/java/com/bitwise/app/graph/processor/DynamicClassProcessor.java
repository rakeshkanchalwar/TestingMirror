package com.bitwise.app.graph.processor;

import java.util.Hashtable;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

import com.bitwise.app.common.component.config.Component;

public class DynamicClassProcessor extends ClassLoader implements Opcodes{
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
        super(DynamicClassProcessor.class.getClassLoader());
    }
	
	public Class<?> createClass(Component componentConfig){
		if(contains(componentConfig.getName())){
			return getClazz(componentConfig.getName());
		}
		else{
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
	        //create class structure ex. public Class NewClass extends InputComponent 
			cw.visit(V1_7, ACC_PUBLIC, componentConfig.getName(), null, getParentClassName(componentConfig), null);
	
	        // creates a GeneratorAdapter for the (implicit) constructor
	        Method defaultConstructor = Method.getMethod("void <init> ()");
	        GeneratorAdapter mg = new GeneratorAdapter(ACC_PUBLIC, defaultConstructor, null, null, cw);
	        mg.loadThis();
	        mg.invokeConstructor(Type.getType(getParentClass(componentConfig)), defaultConstructor);
	        mg.returnValue();
	        mg.endMethod();
	
	        cw.visitEnd();
	
	        byte[]  code = cw.toByteArray();
	        Class<?> clazz = INSTANCE.defineClass(componentConfig.getName(), code, 0, code.length);
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