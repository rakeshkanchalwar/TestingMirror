package com.bitwise.app.adapters;

import java.util.ArrayList;

import java.util.LinkedHashMap;

public class ComponentAdapter {
	public static LinkedHashMap<String, LinkedHashMap<String,ComponentProperty>> component;
	
	static{
		component= new LinkedHashMap<>();
		
		
		// ---------------- Input Componnet
		ComponentProperty componentProperty1=new ComponentProperty("Shape.icon","icon","icons/input2.png");
		ComponentProperty componentProperty2=new ComponentProperty("Shape.Path","path","");
		ComponentProperty componentProperty3=new ComponentProperty("Shape.Schema","schema","");
		ComponentProperty componentProperty4=new ComponentProperty("Shape.Delimiter","delimiter","");
		
		LinkedHashMap<String,ComponentProperty> componentProperties=new LinkedHashMap<>();
		componentProperties.put("Shape.icon",componentProperty1);
		componentProperties.put("Shape.Path",componentProperty2);
		componentProperties.put("Shape.Schema",componentProperty3);
		componentProperties.put("Shape.Delimiter",componentProperty4);
		
		component.put("Input", componentProperties);
		
		// --------------------- OutputComp
		ComponentProperty OComponentProperty1=new ComponentProperty("Shape.icon","icon","icons/OutputFile_Palette.png");
		ComponentProperty OComponentProperty2=new ComponentProperty("Shape.Path","path","");
		ComponentProperty OComponentProperty3=new ComponentProperty("Shape.Schema","schema","");
//		ComponentProperty OComponentProperty4=new ComponentProperty("Shape.Delimiter","delimiter","");
		
		LinkedHashMap<String,ComponentProperty> OComponentProperties=new LinkedHashMap<>();
		OComponentProperties.put("Shape.icon",OComponentProperty1);
		OComponentProperties.put("Shape.Path",OComponentProperty2);
		OComponentProperties.put("Shape.Schema",OComponentProperty3);
	//	OComponentProperties.put("Shape.Delimiter",OComponentProperty4);
		
		component.put("Output", componentProperties);
		
	}
	
	public static LinkedHashMap<String,ComponentProperty> getComponentProperty(String compName){
		return component.get(compName);
	}
}
