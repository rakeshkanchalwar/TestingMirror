package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.property.ComponentConfigrationProperty;
import com.bitwise.app.propertywindow.property.ComponentMiscellaneousProperties;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTRadioButton;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;

public class ELTRetentionlogicWidget extends AbstractWidget{
	private static final Logger logger = LogFactory.INSTANCE.getLogger(ELTRetentionlogicWidget.class);
	
	private final String propertyName;
	private final  LinkedHashMap<String, Object> property=new LinkedHashMap<>();
	private String properties;
	 AbstractELTWidget First,Last,Unique;
	
	public ELTRetentionlogicWidget(
			ComponentConfigrationProperty componentConfigrationProperty,
			ComponentMiscellaneousProperties componentMiscellaneousProperties,
			PropertyDialogButtonBar propertyDialogButtonBar) {
		super(componentConfigrationProperty, componentMiscellaneousProperties,
				propertyDialogButtonBar);
		this.propertyName = componentConfigrationProperty.getPropertyName();
		this.properties =  (String)componentConfigrationProperty.getPropertyValue();
		
	}

	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {
		ListenerFactory listenerFactory = new ListenerFactory();
		
		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(container.getContainerControl());
		eltSuDefaultSubgroupComposite.createContainerWidget();
		eltSuDefaultSubgroupComposite.numberOfBasicWidgets(4);
		
		AbstractELTWidget eltDefaultLable = new ELTDefaultLable("Retention\n Logic");
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultLable);
		
		
		SelectionListener selectionListener = new SelectionAdapter () {
			
	         @Override
			public void widgetSelected(SelectionEvent event) {
	        	 Button button = ((Button) event.widget);
	           properties = button.getText();
	           propertyDialogButtonBar.enableApplyButton(true);
	            logger.debug( "Radio Button Value",button.getText());
	           // button.getSelection();
	         };
	      };
		
		First = new ELTRadioButton("First");
		eltSuDefaultSubgroupComposite.attachWidget(First);
		((Button) First.getSWTWidgetControl()).addSelectionListener(selectionListener);
		//button=(Button) First.getSWTWidgetControl();
		
		Last = new ELTRadioButton("Last");
		eltSuDefaultSubgroupComposite.attachWidget(Last);
		((Button) Last.getSWTWidgetControl()).addSelectionListener(selectionListener);
		
		Unique = new ELTRadioButton("Unique");
		eltSuDefaultSubgroupComposite.attachWidget(Unique);
		((Button) Unique.getSWTWidgetControl()).addSelectionListener(selectionListener);
		
		populateWidget();
		
	}
	
	private void populateWidget(){
		switch(this.properties)
		{
		case "First":((Button) First.getSWTWidgetControl()).setSelection(true);break;
		case "Last":((Button) Last.getSWTWidgetControl()).setSelection(true); break;
		case "Unique":((Button) Unique.getSWTWidgetControl()).setSelection(true);  break;
		}
		
	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		property.put(propertyName, properties);
		return property;
	}

}
