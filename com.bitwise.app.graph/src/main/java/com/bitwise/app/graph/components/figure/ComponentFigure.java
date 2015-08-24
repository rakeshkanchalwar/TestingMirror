package com.bitwise.app.graph.components.figure;



import java.util.logging.Logger;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

import com.bitwise.app.graph.components.model.Component;



public class ComponentFigure extends Figure{
	private static Logger logger = Logger.getLogger(ComponentFigure.class.getName());
	private Label labelName = new Label();
	
		
	private IFigure image;
	private Component component;
	private XYLayout layout;
	
	public Label getLabelName() {
		return labelName;
	}
	public void setLabelName(Label labelName) {
		this.labelName = labelName;
	}
	/*public ComponentFigure(String componentName) {
		layout = new XYLayout();
		setLayoutManager(layout);
		
		labelName.setForegroundColor(ColorConstants.blue);
		labelName.setText(componentName);
		add(labelName);
		setConstraint(labelName, new Rectangle(5, 5, -1, -1));
		
		Class<?> clazz = DynamicClassProcessor.INSTANCE.getClazz(componentName);
		try {
			component = (Component) clazz.newInstance();
			component.setDescriptors(ComponentInitUtil.INSTANCE.getPropertyDescriptors(componentName)); 
			component.setProperties(ComponentInitUtil.INSTANCE.getProperties(componentName));
		} catch (Exception exception) {
			logger.log(Level.SEVERE, "Failed to Instantiate Class", exception);
			throw new RuntimeException(exception);
		}
		
		GenericComponent genericComponent=new GenericComponent();
		genericComponent.initPropertyDescriptors(ComponentAdapter.getComponentProperty(componentName));
		component=genericComponent;
		
		image=new ImageFigure(null);
		add(image);
		setConstraint(image, new Rectangle(5, 19, -1, -1));
		
		setForegroundColor(ColorConstants.black);
		setBackgroundColor(ColorConstants.gray);
		setBorder(new LineBorder(1));
	}*/
	
	public ComponentFigure(Object model, String componentName) {
		setLayoutManager(new XYLayout());
		labelName.setForegroundColor(ColorConstants.blue);
		labelName.setText(componentName);
		add(labelName);
		setConstraint(labelName, new Rectangle(5, 5, -1, -1));
		
		setBorder(new LineBorder(1));
	}
	
	public void setLayout(Rectangle rect) {
		setBounds(rect);
	}
	public void setName(String text) {
		labelName.setText(text);
	}

	
}
