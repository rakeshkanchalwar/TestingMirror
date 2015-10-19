package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
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
	private Button button;
	private final LinkedHashMap<String, Object> property=new LinkedHashMap<>();
	private final   String properties;
	AbstractELTWidget first,last,unique;
	
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
		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(container.getContainerControl());
		eltSuDefaultSubgroupComposite.createContainerWidget();
		eltSuDefaultSubgroupComposite.numberOfBasicWidgets(4);
		
		AbstractELTWidget eltDefaultLable = new ELTDefaultLable("Retention\n Logic");
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultLable);
		
		SelectionListener selectionListener = new SelectionAdapter () {
	         @Override
			public void widgetSelected(SelectionEvent event) {
	           button = ((Button) event.widget);
	           // property.put(propertyName, button.getText());
	            logger.debug( "Radio Button Value",button.getText());
	           // button.getSelection();
	         };
	      };
		
		first = new ELTRadioButton("First");
		eltSuDefaultSubgroupComposite.attachWidget(first);
		((Button) first.getSWTWidgetControl()).addSelectionListener(selectionListener);
		//buttonOne=(Button) First.getSWTWidgetControl();
		
		last = new ELTRadioButton("Last");
		eltSuDefaultSubgroupComposite.attachWidget(last);
		((Button) last.getSWTWidgetControl()).addSelectionListener(selectionListener);
		
		unique = new ELTRadioButton("Unique");
		eltSuDefaultSubgroupComposite.attachWidget(unique);
		((Button) unique.getSWTWidgetControl()).addSelectionListener(selectionListener);
		 
		populateWidget();
		
	}
	
	private void populateWidget(){
		switch(this.properties)
		{
		case "First":((Button) first.getSWTWidgetControl()).setSelection(true);break;
		case "Last":((Button) last.getSWTWidgetControl()).setSelection(true); break;
		case "Unique":((Button) unique.getSWTWidgetControl()).setSelection(true);  break;
		}
	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		property.put(propertyName, button.getText());
		return property;
	}

}
