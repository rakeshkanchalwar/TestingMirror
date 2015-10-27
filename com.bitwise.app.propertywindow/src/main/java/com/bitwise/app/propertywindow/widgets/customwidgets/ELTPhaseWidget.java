package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.widgets.Text;
import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.propertywindow.factory.ListenerFactory.Listners;
import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.property.ComponentConfigrationProperty;
import com.bitwise.app.propertywindow.property.ComponentMiscellaneousProperties;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultTextBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;
import com.bitwise.app.propertywindow.widgets.listeners.IELTListener;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper.HelperType;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;

/**
 * Widget for showing phase text box in property window.
 * 
 * @author Bitwise
 */
public class ELTPhaseWidget extends AbstractWidget {
	private static final Logger logger = LogFactory.INSTANCE.getLogger(ELTPhaseWidget.class);
	private static final String PHASE = "Phase";
	private static final Listners[] listeners = {Listners.NORMAL_FOCUS_IN, Listners.NORMAL_FOCUS_OUT, Listners.VERIFY_NUMERIC,
												 Listners.EVENT_CHANGE, Listners.MODIFY};
	
	private Text textBox;
	private String properties;
	private String propertyName;
	private ControlDecoration txtDecorator;

	/**
	 * Instantiates a new ELT phase widget.
	 * 
	 * @param componentConfigrationProperty
	 *            the component configration property
	 * @param componentMiscellaneousProperties
	 *            the component miscellaneous properties
	 * @param propertyDialogButtonBar
	 *            the property dialog button bar
	 */
	public ELTPhaseWidget(ComponentConfigrationProperty componentConfigrationProperty,	
			ComponentMiscellaneousProperties componentMiscellaneousProperties,	PropertyDialogButtonBar propertyDialogButtonBar) {
		super(componentConfigrationProperty, componentMiscellaneousProperties, propertyDialogButtonBar);
		
		this.properties = (String)componentConfigrationProperty.getPropertyValue();
		this.propertyName = componentConfigrationProperty.getPropertyName();
	}

	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {
		logger.debug("Starting {} textbox creation", PHASE);
		ELTDefaultSubgroupComposite lableAndTextBox = new ELTDefaultSubgroupComposite(container.getContainerControl());
		lableAndTextBox.createContainerWidget();
		
		AbstractELTWidget lable = new ELTDefaultLable(PHASE + " ");
		lableAndTextBox.attachWidget(lable);
		
		AbstractELTWidget textBoxWidget = new ELTDefaultTextBox().textBoxWidth(80).grabExcessHorizontalSpace(false);
		lableAndTextBox.attachWidget(textBoxWidget);

		textBox = (Text) textBoxWidget.getSWTWidgetControl();

		txtDecorator = WidgetUtility.addDecorator(textBox, Messages.bind(Messages.EMPTY_FIELD, PHASE));

		ListenerHelper helper = prepareListenerHelper();
		
		try {
			for (int i = 0; i < listeners.length; i++) {
				IELTListener listener = listeners[i].getListener();
				textBoxWidget.attachListener(listener, propertyDialogButtonBar, helper, textBoxWidget.getSWTWidgetControl());
			}
		} catch (Exception e1) {
			logger.error("Failed in attaching listeners to {}", PHASE);
		}
		populateWidget();
		logger.debug("Finished {} textbox creation", PHASE);
	}

	private ListenerHelper prepareListenerHelper() {
		ListenerHelper helper = new ListenerHelper();
		helper.put(HelperType.CONTROL_DECORATION, txtDecorator);
		helper.put(HelperType.VALIDATION_STATUS, validationStatus);
		return helper;
	}

	
	private void populateWidget(){		
		logger.debug("Populating {} textbox", PHASE);
		if (StringUtils.isNotBlank(properties)){
			textBox.setText(properties);
			txtDecorator.hide();
		}
		else{
			textBox.setText("");
			txtDecorator.show();
		}
	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		LinkedHashMap<String, Object> property = new LinkedHashMap<>();
		property.put(propertyName, textBox.getText());
		return property;
	}
}
