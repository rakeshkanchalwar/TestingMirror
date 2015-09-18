package com.bitwise.app.propertywindow.propertydialog;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;

import com.bitwise.app.propertywindow.factory.WidgetFactory;
import com.bitwise.app.propertywindow.property.Property;
import com.bitwise.app.propertywindow.widgets.customwidgets.AbstractWidget;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 07, 2015
 * 
 */

public class PropertyDialogBuilder {
	//<GroupName,<SubgroupName,[PropertyList...]>>
	private LinkedHashMap<String,LinkedHashMap<String,ArrayList<Property>>> propertyTree;
	private Composite container;
	private LinkedHashMap<String, Object> componentProperties;
	private ArrayList<AbstractWidget> eltWidgetList;
	private ArrayList<String> names;
	public PropertyDialogBuilder(Composite container, LinkedHashMap<String,LinkedHashMap<String,ArrayList<Property>>> propertyTree, LinkedHashMap<String, Object> componentProperties, ArrayList<String> names){
		this.container = container;
		this.propertyTree = propertyTree;
		this.componentProperties = componentProperties;
		eltWidgetList= new ArrayList<>();
		this.names = names;
	}
	
	public void buildPropertyWindow(){
		
		WidgetFactory widgetFactory = new WidgetFactory();
		
		TabFolder tabFolder = addTabFolderToPropertyWindow();
		for(String groupName : propertyTree.keySet()){
			ScrolledCompositeHolder scrolledCompositeHolder = addGroupToPropertyWindowTab(groupName,tabFolder);
			LinkedHashMap<String,ArrayList<Property>> subgroupTree = propertyTree.get(groupName);
			for(String subgroupName: subgroupTree.keySet()){
				Property property_1 = subgroupTree.get(subgroupName).get(0);
				Group subGroup;
				if(property_1 != null){
					subGroup=addSubgroupToPropertyWindowTab(property_1.getPropertySubGroup(),scrolledCompositeHolder);
				}else{
					subGroup=addSubgroupToPropertyWindowTab(subgroupName,scrolledCompositeHolder);
				}
				
				for(Property property: subgroupTree.get(subgroupName)){
					AbstractWidget eltWidget=widgetFactory.getWidget(property.getPropertyRenderer());
					eltWidget.attachToPropertySubGroup(subGroup);
					eltWidget.setProperties(property.getPropertyName(),componentProperties.get(property.getPropertyName()));
					eltWidget.setNames(this.names);
					eltWidgetList.add(eltWidget);
				}
				
			}
		}
	}

	public TabFolder addTabFolderToPropertyWindow(){
		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		final ColumnLayoutData cld_tabFolder = new ColumnLayoutData();
		cld_tabFolder.heightHint = 377;
		tabFolder.setLayoutData(cld_tabFolder);
		
		container.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				cld_tabFolder.heightHint = container.getBounds().height - 50;
			}
		});
		
		return tabFolder;
	}
	
	public ScrolledCompositeHolder addGroupToPropertyWindowTab(String groupName,TabFolder tabFolder){	
		
		TabItem tbtmPart = new TabItem(tabFolder, SWT.NONE);
		tbtmPart.setText(groupName);
				
		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.V_SCROLL);
		scrolledComposite.setAlwaysShowScrollBars(true);
		tbtmPart.setControl(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		ColumnLayout cl_composite = new ColumnLayout();
		cl_composite.maxNumColumns = 1;
		composite.setLayout(cl_composite);
		
		ScrolledCompositeHolder scrolledCompositeHolder = new ScrolledCompositeHolder(scrolledComposite,composite);
		
		return scrolledCompositeHolder;
	}
	
	public Group addSubgroupToPropertyWindowTab(String subgroupName,ScrolledCompositeHolder scrolledCompositeHolder){
		Group grpGroup = new Group(scrolledCompositeHolder.getComposite(), SWT.NONE);
		grpGroup.setText(subgroupName);
		ColumnLayout cl_grpGroup = new ColumnLayout();
		cl_grpGroup.topMargin = 20;
		cl_grpGroup.maxNumColumns = 1;
		grpGroup.setLayout(cl_grpGroup);
		
		scrolledCompositeHolder.getScrolledComposite().setContent(scrolledCompositeHolder.getComposite());
		scrolledCompositeHolder.getScrolledComposite().setMinSize(scrolledCompositeHolder.getComposite().computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		return grpGroup;
	}
	
	public ArrayList<AbstractWidget> getELTWidgetList(){
		return eltWidgetList;
	}
	
}
