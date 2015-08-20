package com.bitwise.app.graph.components.model;

import java.util.ArrayList;
import java.util.List;

public class ComponentsDiagram extends ModelElement{
	/** Property ID to use when a child is added to this diagram. */
	public static final String CHILD_ADDED_PROP = "ComponentsDiagram.ChildAdded";
	/** Property ID to use when a child is removed from this diagram. */
	public static final String CHILD_REMOVED_PROP = "ComponentsDiagram.ChildRemoved";
	private static final long serialVersionUID = 1;
	private List components = new ArrayList();

	/**
	 * Add a component to this diagram.
	 * 
	 * @param s
	 *            a non-null shape instance
	 * @return true, if the component was added, false otherwise
	 */
	public boolean addChild(Component s) {
		if (s != null && components.add(s)) {
			firePropertyChange(CHILD_ADDED_PROP, null, s);
			return true;
		}
		return false;
	}

	/**
	 * Return a List of components in this diagram. The returned List should not be
	 * modified.
	 */
	public List getChildren() {
		return components;
	}

	/**
	 * Remove a component from this diagram.
	 * 
	 * @param s
	 *            a non-null shape instance;
	 * @return true, if the component was removed, false otherwise
	 */
	public boolean removeChild(Component s) {
		if (s != null && components.remove(s)) {
			firePropertyChange(CHILD_REMOVED_PROP, null, s);
			return true;
		}
		return false;
	}
}
