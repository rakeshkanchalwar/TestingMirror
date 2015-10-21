package com.bitwise.app.propertywindow.widgets.listeners;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;
import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.customwidgets.AbstractWidget.ValidationStatus;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper.HelperType;

public class ELTVerifyTextListener implements IELTListener{
	private ValidationStatus validationStatus;
	private ControlDecoration txtDecorator;
	Logger logger = LogFactory.INSTANCE.getLogger(ELTVerifyTextListener.class);
	@Override
	public int getListenerType() {
		// TODO Auto-generated method stub
		return SWT.Verify;
	}

	@Override
	public Listener getListener(PropertyDialogButtonBar propertyDialogButtonBar, ListenerHelper helper,  Widget... widgets) {
			if (helper != null) {
				txtDecorator = (ControlDecoration) helper.get(HelperType.CONTROL_DECORATION);
				validationStatus = (ValidationStatus) helper.get(HelperType.VALIDATION_STATUS); 
			}
				Listener listener=new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					String string=event.text;
					Matcher matchs=Pattern.compile("[\\@]{1}[\\{]{1}[\\w]*[\\}]{1}||[\\w]*").matcher(string);
					logger.debug(this+"::ELTVerifyTextListener is called");
					if(!matchs.matches()){
						txtDecorator.setDescriptionText(Messages.CHARACTERSET);
						txtDecorator.show();
						event.doit=false;
						setValidationStatus(false);
						logger.debug(this+"::ELTVerifyTextListener :: !matchs.matches()::"+string);
				}else
					txtDecorator.hide();
					setValidationStatus(true);
					logger.debug(this+"::ELTVerifyTextListener :: ELSE::"+string);
				}
			};
		return listener;
	}
	
	private void setValidationStatus(boolean status) {
		if(validationStatus != null){
			validationStatus.setIsValid(status);
		}
	}
}


