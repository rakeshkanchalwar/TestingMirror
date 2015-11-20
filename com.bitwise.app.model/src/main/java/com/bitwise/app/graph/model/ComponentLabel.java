package com.bitwise.app.graph.model;

import java.io.IOException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.draw2d.geometry.Dimension;

/**
 * The Class ComponentLabel.
 * @author Bitwise
 */

public class ComponentLabel extends Model{

	private static final int DEFAULT_WIDTH = 50;
	static final long serialVersionUID = 1;
	private String text;
	private Dimension size = new Dimension(-1, 15);;

	private static int count;

	public ComponentLabel(String compLabel) {
		this.size.width = DEFAULT_WIDTH;
		this.text = compLabel;
	}

	public String getLabelContents() {
		return text;
	}

	protected String getNewID() {
		return Integer.toString(count++);
	}

	public Dimension getSize() {
		return new Dimension(size.width, -1);
	}

	private void readObject(java.io.ObjectInputStream s) throws IOException,
	ClassNotFoundException {
		s.defaultReadObject();
	}

	public void setSize(Dimension d) {
		d.height = -1;
		if (size.equals(d))
			return;
		size = d;
		firePropertyChange("size", null, size);
	}

	public void setLabelContents(String s) {
		text = s;
		firePropertyChange("labelContents", null, text); //$NON-NLS-2$//$NON-NLS-1$
	}

	public String toString() {
		return "Label"
				+ "=" + getLabelContents(); //$NON-NLS-1$ 
	}

}
