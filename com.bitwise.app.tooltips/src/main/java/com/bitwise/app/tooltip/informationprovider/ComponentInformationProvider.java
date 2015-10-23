package com.bitwise.app.tooltip.informationprovider;

import java.util.LinkedHashMap;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableItem;



/**
 * An {@link IInformationProvider} that provides information about
 * the {@link TableItem}s of a {@link TableViewer}.
 */
public class ComponentInformationProvider implements IInformationProvider {

	private final LinkedHashMap<String,String> data;
	private Button tooltipBounds ;
	/**
	 * Creates a new information provider for the given table viewer.
	 * @param button 
	 * @param viewer the table viewer
	 */
	public ComponentInformationProvider(LinkedHashMap<String,String> data, Button button) {
		this.data = data;
		tooltipBounds = button;
	}

	@Override
	public Object getInformation(Point location) {
		return data;
	}

	@Override
	public Rectangle getArea(Point location) {
		return tooltipBounds.getBounds();
	}

}
