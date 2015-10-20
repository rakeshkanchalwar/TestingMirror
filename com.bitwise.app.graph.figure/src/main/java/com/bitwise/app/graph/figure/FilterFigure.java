package com.bitwise.app.graph.figure;


import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.graphics.Image;

import com.bitwise.app.common.component.config.PortSpecification;
import com.bitwise.app.common.util.XMLConfigUtil;

public class FilterFigure extends ComponentFigure
implements HandleBounds{

	
	Image canvasIcon;
	
	public FilterFigure(List<PortSpecification> portSpecification)
	{
		super(portSpecification);	
		String imagePath = XMLConfigUtil.CONFIG_FILES_PATH + "/icons/filter_canvas.png" ;
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
		graphics.drawImage(canvasIcon, new Point(r.width/2-16, r.height/2 - 14));
	}

	@Override
	public Rectangle getHandleBounds() {

		return getBounds().getCopy();
	}

}
