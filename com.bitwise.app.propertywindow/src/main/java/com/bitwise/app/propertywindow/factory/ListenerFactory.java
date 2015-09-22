package com.bitwise.app.propertywindow.factory;
import com.bitwise.app.propertywindow.widgets.listeners.ELTHelloTestListener;
import com.bitwise.app.propertywindow.widgets.listeners.ELTHiTestListener;
import com.bitwise.app.propertywindow.widgets.listeners.IELTListener;
import com.bitwise.app.propertywindow.widgets.listeners.MyCustomWidgetTextChange;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 18, 2015
 * 
 */

public class ListenerFactory {
	
	public IELTListener getListener(String listener){
		
		if(listener.equals("ELTHelloTestListener")){
			IELTListener ELTIeltListener = new ELTHelloTestListener();
			return ELTIeltListener;
		}else if(listener.equals("ELTHiTestListener")){
			IELTListener ELTIeltListener = new ELTHiTestListener();
			return ELTIeltListener;
		}else if(listener.equals("MyCustomWidgetTextChange")){
			IELTListener myCuIeltListener = new MyCustomWidgetTextChange();
			return myCuIeltListener;
		}
		
		return null;
		
	}
}
