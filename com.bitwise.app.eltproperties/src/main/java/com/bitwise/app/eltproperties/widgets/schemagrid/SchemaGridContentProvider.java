package com.bitwise.app.eltproperties.widgets.schemagrid;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author rahulma
 * This class provides the content for the schemaGrid table
 */

class SchemaGridContentProvider implements IStructuredContentProvider {
  /**
   * Returns the SchemaGrid objects
   */
  public Object[] getElements(Object inputElement) {
    return ((List) inputElement).toArray();
  }
 
  /**
   * Disposes any created resources
   */
  public void dispose() {
    // Do nothing
  }

  /**
   * Called when the input changes
   */
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // Ignore
  }
}

