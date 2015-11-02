package com.bitwise.app.graph.model.processor;

import com.bitwise.app.common.component.config.CategoryType;
import com.bitwise.app.graph.model.categories.InputCategory;
import com.bitwise.app.graph.model.categories.OutputCategory;
import com.bitwise.app.graph.model.categories.StraightPullCategory;

// TODO: Auto-generated Javadoc
/**
 * The Class ComponentCategoryRepository.
 */
public class ComponentCategoryRepository {
	public static ComponentCategoryRepository INSTANCE = new ComponentCategoryRepository();
	
	private ComponentCategoryRepository() {}

	/**
	 * Gets the class by categoty type.
	 * 
	 * @param categoryType
	 *            the category type
	 * @return the class by categoty type
	 */
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
