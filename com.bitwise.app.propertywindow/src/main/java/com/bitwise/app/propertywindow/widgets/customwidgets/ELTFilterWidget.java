package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.widgets.filterproperty.ELTFilterPropertyWizard;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultButton;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultTextBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;

public class ELTFilterWidget extends AbstractWidget {

	private LinkedHashSet<String> tempPropertyMap;
	private String propertyName;
	private List<String> propertyLst;
	private HashSet<String> set;
	
	private String componentName;

	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {
		ListenerFactory listenerFactory = new ListenerFactory();

		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(
				container.getContainerControl());
		eltSuDefaultSubgroupComposite.createContainerWidget();

		AbstractELTWidget eltDefaultLable = new ELTDefaultLable("Operation\n Fields");
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultLable);

		AbstractELTWidget eltDefaultButton = new ELTDefaultButton("Edit");
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultButton);
		Button button=(Button)eltDefaultButton.getSWTWidgetControl();
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ELTFilterPropertyWizard filterWizardObj=new ELTFilterPropertyWizard();
				
				if(getProperties().get(propertyName)==null){
					setProperties(propertyName, new HashSet<String>());
				}
					filterWizardObj.setRuntimePropertyMap((HashSet<String>) getProperties().get(propertyName));
					setProperties(propertyName,filterWizardObj.launchRuntimeWindow(null));
			
			}
		});
		

	}

	@Override
	public void setProperties(String propertyName, Object properties) {
		this.propertyName = propertyName;
		this.set = (HashSet<String>) properties;

	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		LinkedHashMap<String, Object> property=new LinkedHashMap<>();
		property.put(propertyName,this.set);
		return property;
	}

	/*@Override
	public void setComponentName(String componentName) {
			this.componentName=componentName;
	}
*/
}
