package com.bitwise.app.propertywindow.widgets.gridwidgets.container;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;

public class ELTSubgroupComposite extends AbstractELTContainerWidget{
	
	Composite composite;

	public ELTSubgroupComposite(Composite container) {
		super(container);
	}

	@Override
	public void createContainerWidget() {
		composite = new Composite(inputContainer, SWT.NONE);
		GridLayout layout = new GridLayout(4, false);
		layout.horizontalSpacing = 3;
		layout.marginTop = 0;
		layout.marginLeft = 290;
		//layout.marginRight = 50;
		
		composite.setLayout(layout);
		super.outputContainer = composite;	
		
	}

	@Override
	public AbstractELTContainerWidget numberOfBasicWidgets(int subWidgetCount) {
		composite.setLayout(new GridLayout(subWidgetCount, false));
		return this;
	}

	@Override
	public void attachWidget(AbstractELTWidget eltWidget) {
		eltWidget.attachWidget(composite);
		
	}

}
