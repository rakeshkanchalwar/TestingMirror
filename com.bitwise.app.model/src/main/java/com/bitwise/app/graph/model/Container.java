package com.bitwise.app.graph.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.bitwise.app.common.util.LogFactory;

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
	
	private LogFactory eltLogger = new LogFactory(getClass().getName());
	
	/**
	 * Add a shape to this diagram.
	 * @return true, if the shape was added, false otherwise
	 */
	public boolean addChild(Component component) {
		if (component != null && components.add(component)) {
			component.setParent(this);
			String compType = (String) component.getPropertyValue(NAME_PROP);
			String compName = getDefaultNameForComponent(compType.trim(),component.getBasename(),component.isNewInstance()).trim();
			component.setPropertyValue(NAME_PROP, compName);
			if (component.isNewInstance()) {
				component.setNewInstance(false);
			}
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

	private String getDefaultNameForComponent(String componentName, String baseName, boolean isNewInstance) {
		String METHOD_NAME = "Container.getDefaultNameForComponent(): ";

		if (componentName == null) {
			// TODO shouldn't be the case but what should be done if name is null
			return null;
		}
		
		eltLogger.getLogger().debug(METHOD_NAME + "baseName: " + baseName + ", isNewInstance: " + isNewInstance);

		if (!isNewInstance) {
			// OK, so it's not a new instance of the component (probably undo ), check if the component name is still
			// unique
			if (isUniqueCompName(componentName)) {
				componentNames.add(componentName);
				return componentName;
			} else {
				// not a new instance nor the name is unique. get the default name using base name
				componentName = baseName;
			}

		}

		componentName = componentName.trim();
		String newName = "";
		Integer nextSuffix = componentNextNameSuffixes.get(componentName);
		eltLogger.getLogger().debug(
				METHOD_NAME + "componentNextNameSuffixes.size(): " + componentNextNameSuffixes.size());
		int next = 1;

		if (nextSuffix == null) {
			eltLogger
					.getLogger()
					.debug(METHOD_NAME
							+ "component "
							+ componentName
							+ " not present in the map! will check if default component name is already taken by some other component. If not, then return default name.");

		} else {
			eltLogger.getLogger().debug(
					METHOD_NAME + "component exists in the map. value of nextSuffix: " + nextSuffix.intValue());
			next = nextSuffix.intValue();
		}

		newName = componentName + "_" + (next < 10 ? "0" : "") + next;

		while (true) {
			boolean continueFor = false;
			for (String cname : componentNames) {
				if (cname.equalsIgnoreCase(newName)) {
					eltLogger.getLogger().debug(METHOD_NAME + "Found duplicate name: " + cname);
					continueFor = true;
					break;
				}

			}
			if (continueFor) {
				next++;
				newName = componentName + "_" + (next < 10 ? "0" : "") + next;
				eltLogger.getLogger().debug(
						METHOD_NAME + "still didn't get the new name for the component, now checking for " + newName);
			} else {
				eltLogger.getLogger().debug(METHOD_NAME + "Got the new name for the component! " + newName);
				break;
			}

		}

		// populate Hashtable
		nextSuffix = new Integer(++next);
		Integer i = componentNextNameSuffixes.put(componentName, nextSuffix);
		eltLogger.getLogger().debug(METHOD_NAME + "previous value for component " + componentName + " in map: " + i);
		eltLogger.getLogger().debug(METHOD_NAME + "Adding New component name to the list: " + newName);
		componentNames.add(newName);

		return newName;

	}
	
	public ArrayList<String> getComponentNames() {
		return componentNames;
	}

	public void setComponentNames(ArrayList<String> componentNames) {
		this.componentNames = componentNames;
	}
	
	private boolean isUniqueCompName(String componentName) {
		String METHOD_NAME = "Container.isUniqueCompName(): ";
		componentName = componentName.trim();
		boolean result = true;

		for (String cname : componentNames) {
			if (cname.equalsIgnoreCase(componentName)) {
				result = false;
				break;
			}

		}
		eltLogger.getLogger().debug(METHOD_NAME + "Conainer.isUniqueCompName(): result: " + result);

		return result;
	}
	
	
}
