package com.bitwise.app.propertywindow.widgets.customwidgets.schema;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Item;

/**
 * @author rahulma
 * This class represents the cell modifier for the SchemaEditor program
 */

class SchemaGridCellModifier implements ICellModifier {
  private Viewer viewer;

  public SchemaGridCellModifier(Viewer viewer) {
    this.viewer = viewer;
  }
 
  /**
   * Returns whether the property can be modified
   * 
   * @param element
   *            the element
   * @param property
   *            the property
   * @return boolean
   */
  public boolean canModify(Object element, String property) {
    // Allow editing of all values
    return true;
  }

  /**
   * Returns the value for the property
   * 
   * @param element
   *            the element
   * @param property
   *            the property
   * @return Object
   */ 
  public Object getValue(Object element, String property) {
    SchemaGrid p = (SchemaGrid) element;
    if (ELTSchemaGridWidget.FIELDNAME.equals(property))
      return p.getFieldName();
    else if (ELTSchemaGridWidget.DATEFORMAT.equals(property))
      return String.valueOf(p.getDateFormat());
    else if (ELTSchemaGridWidget.SCALE.equals(property))
    	return String.valueOf(p.getScale());
    else if (ELTSchemaGridWidget.DATATYPE.equals(property))
      return p.getDataType();
    else
      return null;
  }

  /**
   * Modifies the element
   * 
   * @param element
   *            the element
   * @param property
   *            the property
   * @param value
   *            the value
   */
  public void modify(Object element, String property, Object value) {
    if (element instanceof Item)
      element = ((Item) element).getData();
 
    SchemaGrid p = (SchemaGrid) element;
    if (ELTSchemaGridWidget.FIELDNAME.equals(property))
      p.setFieldName((String) value);
    else if (ELTSchemaGridWidget.DATEFORMAT.equals(property))
      p.setDateFormat( value.toString()); 
    else if (ELTSchemaGridWidget.SCALE.equals(property))
        p.setScale( value.toString()); 
    else if (ELTSchemaGridWidget.DATATYPE.equals(property)){
      p.setDataType((Integer)value);
      p.setDataTypeValue(GeneralGridWidgetBuilder.getDataTypeValue()[(Integer)value]);
    }
    viewer.refresh();
  }
  
  
  
}