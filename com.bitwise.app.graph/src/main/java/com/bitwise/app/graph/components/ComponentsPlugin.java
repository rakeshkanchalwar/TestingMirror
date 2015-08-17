package com.bitwise.app.graph.components;


import org.eclipse.ui.plugin.AbstractUIPlugin;

public class ComponentsPlugin extends AbstractUIPlugin{
	/** Single plugin instance. */
	private static ComponentsPlugin singleton;

	/**
	 * Returns the shared plugin instance.
	 */
	public static ComponentsPlugin getDefault() {
		return singleton;
	}

	/**
	 * The constructor.
	 */
	public ComponentsPlugin() {
		if (singleton == null) {
			singleton = this;
		}
	}
}
