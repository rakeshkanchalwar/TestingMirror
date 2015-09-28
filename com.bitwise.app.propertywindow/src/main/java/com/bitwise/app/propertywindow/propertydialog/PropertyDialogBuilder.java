package com.bitwise.app.propertywindow.propertydialog;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;

import com.bitwise.app.propertywindow.factory.WidgetFactory;
import com.bitwise.app.propertywindow.property.Property;
import com.bitwise.app.propertywindow.utils.WordUtils;
import com.bitwise.app.propertywindow.widgets.customwidgets.AbstractWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroup;

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
	private PropertyDialogButtonBar propertyDialogButtonBar;
	
	private PropertyDialogBuilder(){
		
	}
	
	public PropertyDialogBuilder(Composite container, LinkedHashMap<String,LinkedHashMap<String,ArrayList<Property>>> propertyTree, LinkedHashMap<String, Object> componentProperties, ArrayList<String> names,PropertyDialogButtonBar propertyDialogButtonBar){
		this.container = container;
		this.propertyTree = propertyTree;
		this.componentProperties = componentProperties;
		eltWidgetList= new ArrayList<>();
		this.names = names;
		this.propertyDialogButtonBar = propertyDialogButtonBar;
	}
	
	public void buildPropertyWindow(){
		
		WidgetFactory widgetFactory = new WidgetFactory();
		
		TabFolder tabFolder = addTabFolderToPropertyWindow();
		for(String groupName : propertyTree.keySet()){
			ScrolledCompositeHolder scrolledCompositeHolder = addGroupToPropertyWindowTab(groupName,tabFolder);
			LinkedHashMap<String,ArrayList<Property>> subgroupTree = propertyTree.get(groupName);
			for(String subgroupName: subgroupTree.keySet()){
				Property property_1 = subgroupTree.get(subgroupName).get(0);
				AbstractELTContainerWidget subGroupContainer;
				if(property_1 != null){
					subGroupContainer=addSubgroupToPropertyWindowTab(property_1.getPropertySubGroup(),scrolledCompositeHolder);
				}else{
					subGroupContainer=addSubgroupToPropertyWindowTab(subgroupName,scrolledCompositeHolder);
				}
				
				int index = subgroupTree.get(subgroupName).size();
				for(Property property: subgroupTree.get(subgroupName)){
					AbstractWidget eltWidget=widgetFactory.getWidget(property.getPropertyRenderer());
					eltWidget.setpropertyDialogButtonBar(propertyDialogButtonBar);
					eltWidget.setNames(this.names);
					eltWidget.attachToPropertySubGroup(subGroupContainer);
					eltWidget.setProperties(property.getPropertyName(),componentProperties.get(property.getPropertyName()));					
					eltWidgetList.add(eltWidget);
					index--;
					if(index != 0){
						subGroupContainer.attchProertySeperator();
					}
				}
				
			}
			AbstractELTContainerWidget subGroupContainerx=addSubgroupToPropertyWindowTab("",scrolledCompositeHolder);
			ColumnLayout subGroupLayout = new ColumnLayout();
			subGroupLayout.maxNumColumns = 1;
			subGroupLayout.bottomMargin = 0;
			subGroupLayout.topMargin = 0;
			subGroupLayout.rightMargin = 0;
			((Group)subGroupContainerx.getContainerControl()).setLayout(subGroupLayout);
			((Group)subGroupContainerx.getContainerControl()).setVisible(false);
			
		}
	}

	public TabFolder addTabFolderToPropertyWindow(){
		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		final ColumnLayoutData cld_tabFolder = new ColumnLayoutData();
		cld_tabFolder.heightHint = 303;
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
		tbtmPart.setText(WordUtils.capitalize(groupName.replace("_", " ").toLowerCase(), null));
						
		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.V_SCROLL);
		scrolledComposite.setAlwaysShowScrollBars(true);
		tbtmPart.setControl(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		attachMouseScrollButtonListener(scrolledComposite);
				
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		ColumnLayout cl_composite = new ColumnLayout();
		cl_composite.maxNumColumns = 1;
		composite.setLayout(cl_composite);
		cl_composite.bottomMargin = -10;
		
		
		ScrolledCompositeHolder scrolledCompositeHolder = new ScrolledCompositeHolder(scrolledComposite,composite);
		
		return scrolledCompositeHolder;
	}
	
	private void attachMouseScrollButtonListener(final ScrolledComposite scrolledComposite){
		scrolledComposite.addListener(SWT.MouseWheel, new Listener() {
            public void handleEvent(Event event) {
                int wheelCount = event.count;
                wheelCount = (int) Math.ceil(wheelCount / 3.0f);
                while (wheelCount < 0) {
                    scrolledComposite.getVerticalBar().setIncrement(4);
                    wheelCount++;
                }

                while (wheelCount > 0) {
                    scrolledComposite.getVerticalBar().setIncrement(-4);
                    wheelCount--;
                }
            }
        });
	}
	
	public AbstractELTContainerWidget addSubgroupToPropertyWindowTab(String subgroupName,ScrolledCompositeHolder scrolledCompositeHolder){
		
		AbstractELTContainerWidget eltDefaultSubgroup= new ELTDefaultSubgroup(scrolledCompositeHolder.getComposite()).subGroupName(WordUtils.capitalize(subgroupName.replace("_", " ").toLowerCase(), null));
		eltDefaultSubgroup.createContainerWidget();
		
		scrolledCompositeHolder.getScrolledComposite().setContent(scrolledCompositeHolder.getComposite());
		scrolledCompositeHolder.getScrolledComposite().setMinSize(scrolledCompositeHolder.getComposite().computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		return eltDefaultSubgroup;
	}
	
	public ArrayList<AbstractWidget> getELTWidgetList(){
		return eltWidgetList;
	}
	
}
