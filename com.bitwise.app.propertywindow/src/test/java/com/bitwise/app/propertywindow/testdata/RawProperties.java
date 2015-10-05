package com.bitwise.app.propertywindow.testdata;

import java.util.ArrayList;

import com.bitwise.app.common.component.config.DataType;
import com.bitwise.app.common.component.config.Group;
import com.bitwise.app.common.component.config.Property;
import com.bitwise.app.common.component.config.PropertyRenderer;
import com.bitwise.app.common.component.config.PropertyType;
import com.bitwise.app.common.component.config.SubGroup;
import com.bitwise.app.propertywindow.utils.WordUtils;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Oct 05, 2015
 * 
 */

public class RawProperties {
	
	private ArrayList<Property> rawProperties=null;
	
	public RawProperties(){
		rawProperties = new ArrayList<>();
	}
	
	private Property getRawProperty(String propertyString){
		Property property = new Property();
		
		propertyString = propertyString.replace("[", "").replace("]", "");
		
		String[] propertyTokens = propertyString.split(",");
		
		
		PropertyType propType = PropertyType.fromValue((propertyTokens[1].split("="))[1]);
		property.setType(propType);
				
		String readableString = getReadableString((propertyTokens[2].split("="))[1]);		
		PropertyRenderer propertyRenderer = PropertyRenderer.fromValue(readableString);
		property.setRenderer(propertyRenderer);
		
		readableString = (propertyTokens[3].split("="))[1].toLowerCase();
		DataType dataType = DataType.fromValue(readableString);		
		property.setDataType(dataType);
		
				
		property.setName((propertyTokens[4].split("="))[1]);
		
		property.setValue("");
		
		Group group = Group.fromValue((propertyTokens[6].split("="))[1]);
		property.setGroup(group);
		
		SubGroup subGroup = SubGroup.fromValue((propertyTokens[7].split("="))[1]);
		property.setSubGroup(subGroup);
		
		return property;
	}

	private String getReadableString(String string) {		
		String bbb = string.replace("_", " ").toLowerCase();
		String ccc = WordUtils.capitalize(bbb, null);
		ccc=ccc.replace("Elt", "ELT").replace(" ", "").trim();
		return ccc;
	}
	
	public ArrayList<Property> getRawProperties(){
		
		rawProperties.add(getRawProperty("[validation=null, type=USER, renderer=ELT_COMPONENT_NAME_WIDGET, dataType=STRING, name=name, value=null, group=GENERAL, subGroup=DISPLAY]"));
		rawProperties.add(getRawProperty("[validation=null, type=USER, renderer=ELT_SCHEMA_WIDGET, dataType=STRING, name=schema, value=null, group=SCHEMA, subGroup=CONFIGURATION]"));
		rawProperties.add(getRawProperty("[validation=null, type=USER, renderer=ELT_FILE_PATH_WIDGET, dataType=STRING, name=path, value=null, group=GENERAL, subGroup=CONFIGURATION]"));
		rawProperties.add(getRawProperty("[validation=null, type=USER, renderer=ELT_DELIMETER_WIDGET, dataType=STRING, name=delimiter, value=null, group=GENERAL, subGroup=CONFIGURATION]"));
		rawProperties.add(getRawProperty("[validation=null, type=USER, renderer=ELT_SAFE_PROPERTY_WIDGET, dataType=BOOLEAN, name=safe, value=null, group=GENERAL, subGroup=CONFIGURATION]"));
		rawProperties.add(getRawProperty("[validation=null, type=USER, renderer=ELT_HAS_HEADER_WIDGET, dataType=BOOLEAN, name=has_header, value=null, group=GENERAL, subGroup=CONFIGURATION]"));
		rawProperties.add(getRawProperty("[validation=null, type=USER, renderer=ELT_CHARACTER_SET_WIDGET, dataType=STRING, name=charset, value=null, group=GENERAL, subGroup=CONFIGURATION]"));
		rawProperties.add(getRawProperty("[validation=null, type=USER, renderer=ELT_RUNTIME_PROPERTIES_WIDGET, dataType=STRING, name=runtime_properties, value=null, group=GENERAL, subGroup=CONFIGURATION]"));
		rawProperties.add(getRawProperty("[validation=null, type=USER, renderer=ELT_PHASE_WIDGET, dataType=STRING, name=phase, value=null, group=GENERAL, subGroup=CONFIGURATION]"));
		
		return rawProperties;
	}
}
