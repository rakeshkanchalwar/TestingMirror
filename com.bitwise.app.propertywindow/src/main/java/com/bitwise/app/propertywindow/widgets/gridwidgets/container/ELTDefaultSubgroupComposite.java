package com.bitwise.app.propertywindow.widgets.gridwidgets.container;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 21, 2015
 * 
 */

public class ELTDefaultSubgroupComposite extends AbstractELTContainerWidget{
	
	Composite subGroupComposite;
	
	public ELTDefaultSubgroupComposite(Composite container) {
		super(container);
	}

	@Override
	public void createContainerWidget() {
		subGroupComposite = new Composite(inputContainer, SWT.NONE);
		GridLayout layout = new GridLayout(3, false);
		layout.horizontalSpacing = 10;
		subGroupComposite.setLayout(layout);
		super.outputContainer = subGroupComposite;		
	}

	@Override
	public void attachWidget(AbstractELTWidget eltWidget) {
		eltWidget.attachWidget(subGroupComposite);		
	}
	
	@Override
	public ELTDefaultSubgroupComposite numberOfBasicWidgets(int subWidgetCount){
		subGroupComposite.setLayout(new GridLayout(subWidgetCount, false));
		return this;
	}

}
