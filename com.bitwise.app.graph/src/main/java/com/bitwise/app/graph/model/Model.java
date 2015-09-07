package com.bitwise.app.graph.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public abstract class Model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4073149938391231758L;
	
	/** Delegate used to implemenent property-change-support. */
	private transient PropertyChangeSupport pcsDelegate = new PropertyChangeSupport(this);
	
	
	/**
	 * Attach a non-null PropertyChangeListener to this object.
	 * 
	 * @param listner a non-null PropertyChangeListener instance
	 * @throws IllegalArgumentException if the parameter is null
	 */
	public synchronized void addPropertyChangeListener(PropertyChangeListener listner) {
		if (listner == null) {
			throw new IllegalArgumentException();
		}
		pcsDelegate.addPropertyChangeListener(listner);
	}
	
	/**
	 * Remove a PropertyChangeListener from this component.
	 * @param listner PropertyChangeListener instance
	 */
	public synchronized void removePropertyChangeListener(PropertyChangeListener listner) {
		if (listner != null) {
			pcsDelegate.removePropertyChangeListener(listner);
		}
	}
	
	/**
	 * Report a property change to registered listeners (for example editparts).
	 * @param property the programmatic name of the property that changed
	 * @param oldValue the old value of this property
	 * @param newValue the new value of this property
	 */
	protected void firePropertyChange(String property, Object oldValue,
			Object newValue) {
		if (pcsDelegate.hasListeners(property)) {
			pcsDelegate.firePropertyChange(property, oldValue, newValue);
		}
	}
}
