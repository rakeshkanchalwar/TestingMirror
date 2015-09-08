package com.bitwise.app.eltproperties.widgets.schemagrid;

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
    if (ELTSchemaWidget.FIELDNAME.equals(property))
      return p.getFieldName();
    else if (ELTSchemaWidget.LIMIT.equals(property))
      return String.valueOf(p.getLimit());
    else if (ELTSchemaWidget.DATATYPE.equals(property))
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
    if (ELTSchemaWidget.FIELDNAME.equals(property))
      p.setFieldName((String) value);
    else if (ELTSchemaWidget.LIMIT.equals(property))
      p.setLimit( value.toString()); 
    else if (ELTSchemaWidget.DATATYPE.equals(property))
      p.setDataType((Integer)value);
   
    viewer.refresh();
  }
  
  
  
}