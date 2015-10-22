package com.bitwise.app.graph.policy;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.editpolicies.NonResizableEditPolicy;

/**
 * 
 * @author Bitwise
 * Oct 15, 2015
 * 
 */

public class ComponentResizableEditPolicy extends NonResizableEditPolicy{

	@Override
	protected List createSelectionHandles() {
		return new ArrayList();
	}
}
