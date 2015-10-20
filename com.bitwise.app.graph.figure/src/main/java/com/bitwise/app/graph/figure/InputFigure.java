package com.bitwise.app.graph.figure;


import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.graphics.Image;

import com.bitwise.app.common.component.config.PortSpecification;
import com.bitwise.app.common.util.XMLConfigUtil;

public class InputFigure extends ComponentFigure
implements HandleBounds{
	

	Image canvasIcon;
	public InputFigure(List<PortSpecification> portSpecification) {
		super(portSpecification);
		
		String imagePath = XMLConfigUtil.CONFIG_FILES_PATH + "/icons/input_canvas.png" ;
		canvasIcon = new Image(null, imagePath);
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {		
		super.paintFigure(graphics);
		Rectangle r = getBounds().getCopy();
		graphics.translate(r.getLocation());
		Rectangle q = new Rectangle(4, 4, r.width-8, r.height-8);
		graphics.fillRoundRectangle(q, 5, 5);

		drawLable(r, graphics);
		Rectangle rectangle = getBounds().getCopy();
		graphics.translate(rectangle.getLocation());
		//graphics.setBackgroundColor(ELTColorConstants.lightGrey);
		Rectangle fillRectangleArea = new Rectangle(4, 4, rectangle.width-8, rectangle.height-8);
		graphics.fillRoundRectangle(fillRectangleArea, 5, 5);
		//TODO : Enable once the Error mapping for property window is done
		//drawStatus(graphics);
		drawLable(rectangle, graphics);
		
		graphics.drawImage(canvasIcon, new Point(r.width/2-10, r.height/2 - 14));
	}

	@Override
	public Rectangle getHandleBounds() {
		return getBounds().getCopy();
	}

}
