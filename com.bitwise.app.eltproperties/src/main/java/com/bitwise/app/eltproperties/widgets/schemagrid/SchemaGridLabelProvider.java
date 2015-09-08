package com.bitwise.app.eltproperties.widgets.schemagrid;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * 
 * @author rahulma
 *
 */
class SchemaGridLabelProvider implements ITableLabelProvider {
  /**
   * Returns the image
   * 
   * @param element
   *            the element
   * @param columnIndex
   *            the column index
   * @return Image
   */
  public Image getColumnImage(Object element, int columnIndex) {
    return null;
  }

  /**
   * Returns the column text
   * 
   * @param element
   *            the element
   * @param columnIndex
   *            the column index
   * @return String
   */
  public String getColumnText(Object element, int columnIndex) {
    SchemaGrid schemaGrid = (SchemaGrid) element;
    switch (columnIndex) {
    case 0:
      return schemaGrid.getFieldName();
    case 1:
    	 return SchemaEditor.getDataType()[schemaGrid.getDataType().intValue()];   
    case 2:
    	 return schemaGrid.getLimit(); 
    }
    return null;
  }
 
  /**
   * Adds a listener
   * 
   * @param listener
   *            the listener
   */
  public void addListener(ILabelProviderListener listener) {
    // Ignore it
  }

  /**
   * Disposes any created resources
   */
  public void dispose() {
    // Nothing to dispose
  }

  /**
   * Returns whether altering this property on this element will affect the
   * label
   * 
   * @param element
   *            the element
   * @param property
   *            the property
   * @return boolean
   */
  public boolean isLabelProperty(Object element, String property) {
    return false;
  }

  /**
   * Removes a listener
   * 
   * @param listener
   *            the listener
   */
  public void removeListener(ILabelProviderListener listener) {
    // Ignore
  }
}