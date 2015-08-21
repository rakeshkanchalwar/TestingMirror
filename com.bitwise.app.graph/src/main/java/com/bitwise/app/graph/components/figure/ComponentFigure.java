package com.bitwise.app.graph.components.figure;



import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

import com.bitwise.app.adapters.ComponentAdapter;
import com.bitwise.app.graph.components.model.Component;
import com.bitwise.app.graph.components.model.GenericComponent;


public class ComponentFigure extends Figure{
	
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
	
	public ComponentFigure(GenericComponent tempModel, String componentName) {
		layout = new XYLayout();
		setLayoutManager(layout);
		
		labelName.setForegroundColor(ColorConstants.blue);
		labelName.setText(componentName);
		add(labelName);
		setConstraint(labelName, new Rectangle(5, 5, -1, -1));
		
		//GenericComponent genericComponent=new GenericComponent();
		GenericComponent genericComponent=tempModel;
		//genericComponent.initPropertyDescriptors(ComponentAdapter.getComponentProperty(componentName),componentName);
		genericComponent.initPropertyDescriptors(componentName,tempModel);
		//ComponentAdapter.getComponentProperty(componentName).get("Shape.icon");
		//genericComponent.setComponentName(tempModel); 
		component=genericComponent;
		
		image=new ImageFigure(component.getIcon());
		add(image);
		setConstraint(image, new Rectangle(5, 19, -1, -1));
		
		setForegroundColor(ColorConstants.black);
		setBackgroundColor(ColorConstants.gray);
		setBorder(new LineBorder(1));
		}
	/*public ComponentFigure(String componentName) {
		layout = new XYLayout();
		setLayoutManager(layout);
		
		labelName.setForegroundColor(ColorConstants.blue);
		labelName.setText(componentName);
		add(labelName);
		setConstraint(labelName, new Rectangle(5, 5, -1, -1));
		
		GenericComponent genericComponent=new GenericComponent();
		//genericComponent.initPropertyDescriptors(ComponentAdapter.getComponentProperty(componentName),componentName);
		genericComponent.initPropertyDescriptors(componentName);
		//ComponentAdapter.getComponentProperty(componentName).get("Shape.icon");
		genericComponent.setComponentName(componentName); 
		component=genericComponent;
		
		image=new ImageFigure(component.getIcon());
		add(image);
		setConstraint(image, new Rectangle(5, 19, -1, -1));
		
		setForegroundColor(ColorConstants.black);
		setBackgroundColor(ColorConstants.gray);
		setBorder(new LineBorder(1));
		}*/
	
		public void setLayout(Rectangle rect) {
			setBounds(rect);
		}
		public void setName(String text) {
			labelName.setText(text);
		}

	
}
