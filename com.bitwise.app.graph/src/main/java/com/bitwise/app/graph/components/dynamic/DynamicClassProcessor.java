package com.bitwise.app.graph.components.dynamic;

import java.util.Hashtable;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

import com.bitwise.app.graph.components.model.Component;

public class DynamicClassProcessor extends ClassLoader implements Opcodes{
	public static DynamicClassProcessor INSTANCE = new DynamicClassProcessor(); 
	private Hashtable<String, Class<?>> classes = new Hashtable<String, Class<?>>();
	private Hashtable<Class<?>, String> classess = new Hashtable<Class<?>, String>();
	
	public boolean contains(Class<?> clazz) {
		return classes.containsValue(clazz);
	}
	
	private void put(String clazzName, Class<?> clazz){
		classes.put(clazzName, clazz);
		classess.put(clazz, clazzName);
	}
	
	public String getClazzName(Class<?> clazz){
		return classess.get(clazz);
	}
	
	public Class<?> getClazz(String className){
		return classes.get(className);
	}
	
	public DynamicClassProcessor(){
        super(DynamicClassProcessor.class.getClassLoader());
    }
	
	public static Class<?> createClass(String fileName){
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		//TraceClassVisitor traceClassVisitor = new TraceClassVisitor(cw, new PrintWriter(System.out)); 
        //create class structure ex. public Class NewClass extends InputComponent 
		cw.visit(V1_7, ACC_PUBLIC, fileName, null, "com/bitwise/app/graph/components/model/Component", null);

        // creates a GeneratorAdapter for the (implicit) constructor
        Method defaultConstructor = Method.getMethod("void <init> ()");
        GeneratorAdapter mg = new GeneratorAdapter(ACC_PUBLIC, defaultConstructor, null, null, cw);
        mg.loadThis();
        mg.invokeConstructor(Type.getType(Component.class), defaultConstructor);
        mg.returnValue();
        mg.endMethod();

        cw.visitEnd();

        byte[]  code = cw.toByteArray();
        Class<?>exampleClass = INSTANCE.defineClass(fileName, code, 0, code.length);
        INSTANCE.put(fileName, exampleClass);
        return exampleClass;
	}
}