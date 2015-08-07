package com.bitwise.app.graph.components.figure;



import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

import com.bitwise.app.graph.components.model.Component;
import com.bitwise.app.graph.components.model.CopyToManyComponent;
import com.bitwise.app.graph.components.model.InputComponent;
import com.bitwise.app.graph.components.model.JoinComponent;
import com.bitwise.app.graph.components.model.OutputComponent;
import com.bitwise.app.graph.components.model.TransformComponent;




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
	public ComponentFigure(String name) {
		layout = new XYLayout();
		setLayoutManager(layout);
		
		labelName.setForegroundColor(ColorConstants.blue);
		labelName.setText(name);
		add(labelName);
		setConstraint(labelName, new Rectangle(5, 5, -1, -1));
		
		if(name.equals("Input"))
			component=new InputComponent();
		else if(name.equals("Output"))
			component=new OutputComponent();
		else if(name.equals("Join"))
			component=new JoinComponent();
		else if(name.equals("Transform"))
			component=new TransformComponent();
		else if(name.equals("CopyToMany"))
			component=new CopyToManyComponent();
		
		image=new ImageFigure(component.getIcon());
		add(image);
		setConstraint(image, new Rectangle(5, 19, -1, -1));
		
		
		setForegroundColor(ColorConstants.black);
		setBackgroundColor(ColorConstants.gray);
		setBorder(new LineBorder(1));
		}
		public void setLayout(Rectangle rect) {
			setBounds(rect);
		}
		public void setName(String text) {
			labelName.setText(text);
		}

	
}
