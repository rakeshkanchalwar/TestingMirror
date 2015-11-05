package com.bitwise.app.propertywindow.widgets.customwidgets.operational;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Bitwise
 * This class provides the content for the TransformGrid table
 */

public class TransformGridContentProvider implements IStructuredContentProvider {
  /**
   * Returns the TransformGrid objects
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

