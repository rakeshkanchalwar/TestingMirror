package com.bitwise.app.graph.model;

import java.util.ArrayList;
import java.util.Hashtable;
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
	
	private static final String NAME_PROP = "name";
	
	private List<Component> components = new ArrayList<>();
	private Hashtable<String, Integer> componentNextNameSuffixes = new Hashtable<>();
	private ArrayList<String> componentNames = new ArrayList<>();
	
	/**
	 * Add a shape to this diagram.
	 * @return true, if the shape was added, false otherwise
	 */
	public boolean addChild(Component component) {
		if (component != null && components.add(component)) {
			component.setParent(this);
			String compType = (String) component.getPropertyValue(NAME_PROP);
			String compName = getDefaultNameForComponent(compType.trim()).trim();
			component.setPropertyValue(NAME_PROP, compName);
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
			componentNames.remove(component.getPropertyValue(NAME_PROP));
			firePropertyChange(CHILD_REMOVED_PROP, null, component);
			return true;
		}
		return false;
	}

	private String getDefaultNameForComponent(String component) {

		if (component == null) {
			return null;
		}
		component = component.trim();
		String newName = "";
		Integer nextSuffix = componentNextNameSuffixes.get(component);
		System.out.println("componentNextNameSuffixes.size(): " + componentNextNameSuffixes.size());
		int next = 1;

		if (nextSuffix == null) {
			System.out
					.println("component not present in the map! will check if default component name is already taken by some other component. If not, then return default name.");

		} else {
			System.out.println("component exists in the map. value of nextSuffix: " + nextSuffix.intValue());
			next = nextSuffix.intValue();
		}

		newName = component + "_" + (next < 10 ? "0" : "") + next;

		while (true) {
			boolean continueFor = false;
			for (String cname : componentNames) {
				if (cname.equalsIgnoreCase(newName)) {
					System.out.println("Found duplicate name: " + cname);
					continueFor = true;
					break;
				}

			}
			if (continueFor) {
				next++;
				newName = component + "_" + (next < 10 ? "0" : "") + next;
				System.out.println("still didn't get the new name for the component, now checking for " + newName);
			} else {
				System.out.println("Got the new name for the component! " + newName);
				break;
			}

		}

		// populate Hashtable
		nextSuffix = new Integer(++next);
		Integer i = componentNextNameSuffixes.put(component, nextSuffix);
		System.out.println("previous value for component " + component + " in map: " + i);
		System.out.println("Adding New component name to the list: " + newName);
		componentNames.add(newName);

		return newName;

	}
	
	public ArrayList<String> getComponentNames() {
		return componentNames;
	}

	public void setComponentNames(ArrayList<String> componentNames) {
		this.componentNames = componentNames;
	}
	
	
}
