package com.bitwise.app.propertywindow.widgets.customwidgets.schema;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Bitwise
 * This class provides the content for the schemaGrid table
 */

public class SchemaGridContentProvider implements IStructuredContentProvider {
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

