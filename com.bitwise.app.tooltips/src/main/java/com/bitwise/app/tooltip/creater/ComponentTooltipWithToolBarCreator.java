package com.bitwise.app.tooltip.creater;

import java.util.LinkedHashMap;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.swt.widgets.Shell;

import com.bitwise.app.tooltip.tooltips.ComponentTooltip;

/**
 * An {@link IInformationControlCreator} for {@link ComponentTooltip}s.
 */
public class ComponentTooltipWithToolBarCreator implements IComponentTooltipCreator{
	ToolBarManager toolBarManager = new ToolBarManager();
	@Override
	public IInformationControl createInformationControl(Shell parent) {
		return new ComponentTooltip(parent, toolBarManager);
	}

	@Override
	public boolean isSupported(Object info) {
		return LinkedHashMap.class.isInstance(info);
	}
}
