package com.bitwise.app.propertywindow.widgets.gridwidgets.container;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.forms.widgets.ColumnLayout;

import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.IELTWidget;

public class ELTDefaultSubgroup extends IELTContainerWidget{

	Composite subGroupComposite;
	
	private String subgroupName="Default Sub Group";
	
	public ELTDefaultSubgroup(Composite container) {
		super(container);
	}

	@Override
	public void createContainerWidget(){
		Group subGroup = new Group(container, SWT.NONE);
		subGroup.setText(subgroupName);
		ColumnLayout subGroupLayout = new ColumnLayout();
		subGroupLayout.bottomMargin = 0;
		subGroupLayout.topMargin = 20;
		subGroup.setLayout(subGroupLayout);
		
		subGroupComposite = new Composite(subGroup, SWT.NONE);
		subGroupComposite.setLayout(new GridLayout(3, false));
	}
	
	public ELTDefaultSubgroup numberOfBasicWidgets(int subWidgetCount){
		subGroupComposite.setLayout(new GridLayout(subWidgetCount, false));
		return this;
	}
	
	public ELTDefaultSubgroup subGroupName(String subgroupName){
		this.subgroupName = subgroupName;
		return this;
	}

	@Override
	public void attachWidget(IELTWidget eltWidget) {
		eltWidget.attachWidget(subGroupComposite);	
	}

	
}
