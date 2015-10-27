package com.bitwise.app.graph.command;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.bitwise.app.common.component.config.PortSpecification;
import com.bitwise.app.common.component.config.Property;
import com.bitwise.app.common.datastructures.tooltip.PropertyToolTipInformation;
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
	private static final String NAME = "name";
	
	/** The new shape. */
	private final Component component;
	/** Container to add to. */
	private final Container parent;
	/** The bounds of the new Shape. */
	private final Rectangle bounds;
	
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
		
		
		//attach tooltip information to component
		Map<String,PropertyToolTipInformation> tooltipInformation = new LinkedHashMap<>();
		for(Property property : components.getProperty()){
			tooltipInformation.put(property.getName(),new PropertyToolTipInformation(property.getName(), property.getShowAsTooltip().value(), property.getTooltipDataType().value()));
		}
		component.setTooltipInformation(tooltipInformation);
		
		int totalPortsofInType=0, totalPortsOfOutType=0;
		List<PortSpecification> portSpecification = XMLConfigUtil.INSTANCE.getComponent(componentName).getPort().getPortSpecification();
		for(PortSpecification p:portSpecification)
		{	
			if(p.getTypeOfPort().equalsIgnoreCase("in")){
				totalPortsofInType=p.getNumberOfPorts();
			}
			else{
				totalPortsOfOutType=p.getNumberOfPorts();
			}
		}
		int heightFactor=totalPortsofInType > totalPortsOfOutType ? totalPortsofInType : totalPortsOfOutType;
		int height = (heightFactor+1)*25;
		

		setupComponent(component);		

		//int defaultWidth = (component.getBasename().length()+3)*7+30;
		//int defaultHeight = defaultWidth * 6/8;
		Dimension newSize = new Dimension(component.getSize().width, height);
		//component.setSize(newSize);
		this.component = component;
		this.parent = parent;
		//this.bounds = bounds;
		Point p = new Point(bounds.x, bounds.y);
		this.bounds = new Rectangle(p, newSize);
		setLabel("component creation");
		
	}
	
	@Override
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
	@Override
	public boolean canExecute() {
		return component != null && parent != null && bounds != null;
	}
	
	/**
	 * Add component to container
	 */
	@Override
	public void redo() {
		parent.addChild(component);
	}

	/**
	 * Undo add action
	 */
	@Override
	public void undo() {
		parent.removeChild(component);
	}
	
	private void setupComponent(Component component) {
		String componentName = DynamicClassProcessor.INSTANCE.getClazzName(component.getClass());
		com.bitwise.app.common.component.config.Component componentConfig = XMLConfigUtil.INSTANCE.getComponent(componentName);
		component.setProperties(prepareComponentProperties(componentName));
		component.setBasename(componentConfig.getName());
		component.setCategory(componentConfig.getCategory().value());
	}
	
	private Map<String, Object> prepareComponentProperties(String componentName) {
		Map<String, Object> properties = ComponentCacheUtil.INSTANCE.getProperties(componentName);
		properties.put(NAME, componentName);
		properties.put(Component.Props.VALIDITY_STATUS.getValue(), Component.ValidityStatus.WARN.name());
		return properties;
	}
}
