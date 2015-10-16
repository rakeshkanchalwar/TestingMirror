package com.bitwise.app.graph.command;

import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.bitwise.app.common.util.ComponentCacheUtil;
import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Container;
import com.bitwise.app.graph.processor.DynamicClassProcessor;

// TODO: Auto-generated Javadoc
/**
 * The Class ComponentCreateCommand.
 */
public class ComponentCreateCommand extends Command {
	/** The new shape. */
	private Component component;
	/** Container to add to. */
	private final Container parent;
	/** The bounds of the new Shape. */
	private Rectangle bounds;
	
	/**
	 * Create a command that will add a new Shape in Container.
	 * 
	 * @param component the new Shape that is to be added
	 * @param parent the Container that will hold the new element
	 * @param bounds the bounds of the new shape; the size can be (-1, -1) if not known
	 * @throws IllegalArgumentException if any parameter is null, or the request does not provide a
	 *             new Shape instance
	 */
	public ComponentCreateCommand(Component component, Container parent, Rectangle bounds) {
		String componentName = DynamicClassProcessor.INSTANCE.getClazzName(component.getClass());
		com.bitwise.app.common.component.config.Component components = XMLConfigUtil.INSTANCE.getComponent(componentName);
		Map<String, Object> properties = ComponentCacheUtil.INSTANCE.getProperties(componentName);
		properties.put("name", components.getName());
		component.setProperties(properties);
		component.setBasename(components.getName());
		component.setCategory(components.getCategory().value());
		//int defaultWidth = (component.getBasename().length()+3)*7+30;
		//int defaultHeight = defaultWidth * 6/8;
		Dimension newSize = new Dimension(component.getSize().width, component.getSize().height);
		//component.setSize(newSize);
		this.component = component;
		this.parent = parent;
		//this.bounds = bounds;
		Point p = new Point(bounds.x, bounds.y);
		this.bounds = new Rectangle(p, newSize);
		setLabel("component creation");
		
	}
	
	public void execute() {
		component.setLocation(bounds.getLocation());
		Dimension size = bounds.getSize();
		if (size.width > 0 && size.height > 0)
			component.setSize(size);
		redo();
	}
	
	/**
	 * Can execute if all the necessary information has been provided.
	 * 
	 */
	public boolean canExecute() {
		return component != null && parent != null && bounds != null;
	}
	
	/**
	 * Add component to container
	 */
	public void redo() {
		parent.addChild(component);
	}

	/**
	 * Undo add action
	 */
	public void undo() {
		parent.removeChild(component);
	}
}
