package com.bitwise.app.perspective;

import com.bitwise.app.common.component.config.CategoryType;

public class ComponentCategoryRepository {
	public static ComponentCategoryRepository INSTANCE = new ComponentCategoryRepository();
	
	private ComponentCategoryRepository() {}

	public String getClassByCategotyType(CategoryType categoryType){
		switch(categoryType){
			
			case INPUT:
					return "com/bitwise/app/graph/models/InputComponent";
					
			case OUTPUT:
					return "com/bitwise/app/graph/models/OutputComponent";
					
			default :
				//TODO : Add Logger
				throw new RuntimeException();
		}
	}
}
