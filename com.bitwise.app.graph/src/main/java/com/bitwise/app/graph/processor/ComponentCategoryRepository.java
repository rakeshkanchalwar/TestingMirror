package com.bitwise.app.graph.processor;

import com.bitwise.app.common.component.config.CategoryType;
import com.bitwise.app.graph.model.categories.InputCategory;
import com.bitwise.app.graph.model.categories.OutputCategory;
import com.bitwise.app.graph.model.categories.StraightPullCategory;

public class ComponentCategoryRepository {
	public static ComponentCategoryRepository INSTANCE = new ComponentCategoryRepository();
	
	private ComponentCategoryRepository() {}

	public Class<?> getClassByCategotyType(CategoryType categoryType){
		switch(categoryType){
			
			case INPUT:
					return InputCategory.class;
					
			case OUTPUT:
					return OutputCategory.class;
			
			case STRAIGHTPULL:
				return StraightPullCategory.class;			
					
			default :
				//TODO : Add Logger
				throw new RuntimeException();
		}
	}
}
