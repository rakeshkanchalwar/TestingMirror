package com.bitwise.app.parametergrid.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ParameterFileManager {
	private String parameterFilePath;
	public ParameterFileManager(String parameterFilePath){
		this.parameterFilePath = parameterFilePath;
	}
	
	public Map<String, String> getParameterMap(){
		Properties prop = new Properties();
		Map<String, String> parameterMap = new LinkedHashMap<>();
		InputStream input = null;		
		try {
			input = new FileInputStream(parameterFilePath);
			// load a properties file
			prop.load(input);
			
			for(String parameterName : prop.stringPropertyNames()) {
				parameterMap.put(parameterName,prop.getProperty(parameterName));
	        }

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			//parameterMap=null;
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return parameterMap;
	}
	
	public void storeParameters(Map<String, String> parameterMap){
		Properties properties = new Properties();  
		FileOutputStream parameterFile = null;
		try {
			parameterFile = new FileOutputStream(parameterFilePath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        Set set = parameterMap.keySet();  
        Iterator itr = set.iterator();  
        while(itr.hasNext()){  
            String key = (String)itr.next();  
            String value = parameterMap.get(key);  
            properties.setProperty(key, value);  
        }  
    
        try {
			properties.store(parameterFile,null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
        try {
			parameterFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
