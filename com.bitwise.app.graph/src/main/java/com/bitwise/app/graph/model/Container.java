package com.bitwise.app.graph.model;

import java.util.ArrayList;
import java.util.List;

public class Container extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8825716379098354511L;
	
	/** Property ID to use when a child is added to this diagram. */
	public static final String CHILD_ADDED_PROP = "ComponentsDiagram.ChildAdded";
	/** Property ID to use when a child is removed from this diagram. */
	public static final String CHILD_REMOVED_PROP = "ComponentsDiagram.ChildRemoved";
	
	private List<Component> components = new ArrayList<>();
	
	/**
	 * Add a shape to this diagram.
	 * @return true, if the shape was added, false otherwise
	 */
	public boolean addChild(Component component) {
		if (component != null && components.add(component)) {
			firePropertyChange(CHILD_ADDED_PROP, null, component);
			return true;
		}
		return false;
	}

	/**
	 * Return a List of Shapes in this diagram. The returned List should not be
	 * modified.
	 */
	public List<Component> getChildren() {
		return components;
	}

	/**
	 * Remove a shape from this diagram.
	 * @return true, if the shape was removed, false otherwise
	 */
	public boolean removeChild(Component component) {
		if (component != null && components.remove(component)) {
			firePropertyChange(CHILD_REMOVED_PROP, null, component);
			return true;
		}
		return false;
	}
	
	
}
