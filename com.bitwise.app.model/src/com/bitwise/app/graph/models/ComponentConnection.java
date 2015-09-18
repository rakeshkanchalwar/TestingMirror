package com.bitwise.app.graph.models;

import org.eclipse.draw2d.Graphics;

import com.bitwise.app.graph.models.Component;
import com.bitwise.app.graph.models.Model;

public class ComponentConnection extends Model {
	private static final long serialVersionUID = -4969635974273718739L;

	/** Line drawing style for this connection. */
	private int lineStyle = Graphics.LINE_SOLID;
	
	/**
	 * Used for indicating that a Connection with solid line style should be
	 * created.
	 */
	public static final Integer SOLID_CONNECTION = new Integer(Graphics.LINE_SOLID);
	/** Property ID to use when the line style of this connection is modified. */
	public static final String LINESTYLE_PROP = "LineStyle";
	
	/** True, if the connection is attached to its endpoints. */
	private boolean isConnected;
	
	/** Connection's source endpoint. */
	private Component source;
	
	/** Connection's target endpoint. */
	private Component target;
	
	protected String sourceTerminal, targetTerminal;
	
	
	/**
	 * Create a (solid) connection between two distinct components.
	 * 
	 * @param source
	 *            a source endpoint for this connection (non null)
	 * @param target
	 *            a target endpoint for this connection (non null)
	 * @throws IllegalArgumentException
	 *             if any of the parameters are null or source == target
	 */
	public ComponentConnection(Component source, Component target) {
		
		
	}
	public ComponentConnection() {
		// TODO Auto-generated constructor stub
	}
	
	public void attachSource() {
		if (getSource() == null
				|| getSource().getSourceConnections().contains(this))
			return;
		
		getSource().connectOutput(this);
	}

	public void attachTarget() {
		if (getTarget() == null
				|| getTarget().getTargetConnections().contains(this))
			return;
		getTarget().connectInput(this);
	}
	
	public Component getSource() {
		return source;
	}

	public String getSourceTerminal() {
		return sourceTerminal;
	}
	
	public Component getTarget() {
		return target;
	}

	public String getTargetTerminal() {
		return targetTerminal;
	}
	
	public void setSource(Component e) {
		Object old = source;
		source = e;
		firePropertyChange("source", old, source);
	}
	public void setSourceTerminal(String s) {
		Object old = sourceTerminal;
		sourceTerminal = s;
		firePropertyChange("sourceTerminal", old, sourceTerminal);
	}

	public void setTarget(Component e) {
		Object old = target;
		target = e;
		firePropertyChange("target", old, target);
	}

	public void setTargetTerminal(String s) {
		Object old = targetTerminal;
		targetTerminal = s;
		firePropertyChange("targetTerminal", old, targetTerminal);
	}
	/**
	 * Returns the line drawing style of this connection.
	 * 
	 * @return an int value (Graphics.LINE_DASH or Graphics.LINE_SOLID)
	 */
	public int getLineStyle() {
		return lineStyle;
	}
	
		
	/**
	 * Set the line drawing style of this connection.
	 * @param lineStyle one of following values: Graphics.LINE_SOLID
	 * @see Graphics#LINE_SOLID
	 * @throws IllegalArgumentException
	 *             if lineStyle does not have one of the above values
	 */
	public void setLineStyle(int lineStyle) {
		if (lineStyle != Graphics.LINE_SOLID) {
			throw new IllegalArgumentException();
		}
		this.lineStyle = lineStyle;
		firePropertyChange(LINESTYLE_PROP, null, new Integer(this.lineStyle));
	}
}
