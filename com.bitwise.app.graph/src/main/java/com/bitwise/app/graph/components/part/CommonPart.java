package com.bitwise.app.graph.components.part;

import java.awt.geom.GeneralPath;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

import com.bitwise.app.common.component.config.Policy;
import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.graph.components.dynamic.DynamicClassProcessor;
import com.bitwise.app.graph.components.model.ModelElement;

public abstract class CommonPart extends AbstractGraphicalEditPart implements
		PropertyChangeListener {
	/**
	 * Upon activation, attach to the model element as a property change
	 * listener.
	 */
	public void activate() {
		if (!isActive()) {
			super.activate();
			((ModelElement) getModel()).addPropertyChangeListener(this);
		}
	}

	/**
	 * Upon deactivation, detach from the model element as a property change
	 * listener.
	 */
	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			((ModelElement) getModel()).removePropertyChangeListener(this);
		}
	}

	protected void createEditPolicies() {
		AbstractEditPolicy editPolicy;
		for (com.bitwise.app.common.component.config.Component components : XMLConfigUtil.INSTANCE
				.getComponentConfig()) {
			applyGeneralPolicy(components);
		}
	}

	public void applyGeneralPolicy(
			com.bitwise.app.common.component.config.Component component) {
		AbstractEditPolicy editPolicy;
		String componentName = DynamicClassProcessor.INSTANCE
				.getClazzName(getModel().getClass());
		for ( Policy generalPolicy : XMLConfigUtil.INSTANCE.getPoliciesForComponent(component, componentName)) {
			try {
				editPolicy= (AbstractEditPolicy) Class.forName(generalPolicy.getValue()).newInstance();
				 installEditPolicy(generalPolicy.getName(), editPolicy);
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}

	}

}
