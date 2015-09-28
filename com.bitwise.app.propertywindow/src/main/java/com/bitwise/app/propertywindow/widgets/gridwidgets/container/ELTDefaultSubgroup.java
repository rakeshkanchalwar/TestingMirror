package com.bitwise.app.propertywindow.widgets.gridwidgets.container;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.forms.widgets.ColumnLayout;

import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 18, 2015
 * 
 */

public class ELTDefaultSubgroup extends AbstractELTContainerWidget{

	
	Group subGroup;
	
	private String subgroupName="Default Sub Group";
	
	public ELTDefaultSubgroup(Composite container) {
		super(container);
	}

	@Override
	public void createContainerWidget(){
		subGroup = new Group(inputContainer, SWT.NONE);
		subGroup.setText(subgroupName);
		ColumnLayout subGroupLayout = new ColumnLayout();
		subGroupLayout.maxNumColumns = 1;
		subGroupLayout.bottomMargin = 0;
		subGroupLayout.topMargin = 20;
		subGroupLayout.rightMargin = 0;
		subGroup.setLayout(subGroupLayout);
		
/*		subGroupComposite = new Composite(subGroup, SWT.NONE);
		subGroupComposite.setLayout(new GridLayout(3, false));*/
		
		//super.outputContainer = subGroupComposite;
		super.outputContainer = subGroup;
	}
	
	@Override
	public ELTDefaultSubgroup numberOfBasicWidgets(int subWidgetCount){
		//subGroupComposite.setLayout(new GridLayout(subWidgetCount, false));
		return this;
	}
	
	public ELTDefaultSubgroup subGroupName(String subgroupName){
		this.subgroupName = subgroupName;
		return this;
	}

	@Override
	public void attachWidget(AbstractELTWidget eltWidget) {
		eltWidget.attachWidget(subGroup);	
	}

	
}
