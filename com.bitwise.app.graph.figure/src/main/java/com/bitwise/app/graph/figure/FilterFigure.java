package com.bitwise.app.graph.figure;


import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import com.bitwise.app.common.component.config.PortSpecification;

public class FilterFigure extends ComponentFigure
implements HandleBounds{
	Point labelPoint;
	Font labelFont = new Font(null, "", 10, 1); 
public FilterFigure(List<PortSpecification> portSpecification)
	{
		super(portSpecification);
	}

	@Override
	protected void paintFigure(Graphics graphics) {

		super.paintFigure(graphics);
		Rectangle r = getBounds().getCopy();
		graphics.translate(r.getLocation());
		graphics.setBackgroundColor(new Color(null,235, 176, 182));
		graphics.setForegroundColor(ColorConstants.black);
		graphics.fillRectangle(4, 4, r.width-8, r.height-8);

		int x = (r.width - getLabelName().length() * 7) / 2;
		labelPoint = new Point(x, r.height / 2 - 10);
		graphics.setFont(labelFont);
		graphics.drawText(getLabelName(), labelPoint);

		PointList connector = new PointList();
		connector.addPoint(4, 4);
		connector.addPoint(4, -4);
		connector.addPoint(-4, -4);
		connector.addPoint(-4, 4);

		graphics.translate(-r.getLocation().x, -r.getLocation().y);
		for(PortSpecification p:portspecification)
		{
			Point portPoint=getPortLocation(r,p.getNumberOfPorts(),p.getTypeOfPort(),p.getSequenceOfPort());
			graphics.translate(portPoint);
			graphics.setBackgroundColor(new Color(null,191, 52, 114));
			graphics.fillPolygon(connector);
			graphics.translate(portPoint.getNegated());
		}
	}
	
	@Override
	public void validate() {
		super.validate();

		if (isValid())
			return;

	}


	protected boolean useLocalCoordinates() {
		return true;
	}

	public Rectangle getHandleBounds() {
		//return getBounds().getCropped(new Insets(2, 0, 2, 0));
		return getBounds().getCopy();
	}



}
