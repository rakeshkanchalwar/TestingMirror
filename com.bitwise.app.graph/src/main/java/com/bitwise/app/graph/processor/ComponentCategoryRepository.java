package com.bitwise.app.graph.processor;

import com.bitwise.app.common.component.config.CategoryType;
import com.bitwise.app.graph.models.InputComponent;
import com.bitwise.app.graph.models.OutputComponent;

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
