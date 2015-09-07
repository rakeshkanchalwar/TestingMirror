package com.bitwise.app.graph.processor;

import com.bitwise.app.common.component.config.CategoryType;
import com.bitwise.app.graph.model.InputComponent;
import com.bitwise.app.graph.model.OutputComponent;

public class ComponentCategoryRepository {
	public static ComponentCategoryRepository INSTANCE = new ComponentCategoryRepository();
	
	private ComponentCategoryRepository() {}

	public Class<?> getClassByCategotyType(CategoryType categoryType){
		switch(categoryType){
			
			case INPUT:
					return InputComponent.class;
					
			case OUTPUT:
					return OutputComponent.class;
					
			default :
				//TODO : Add Logger
				throw new RuntimeException();
		}
	}
}
