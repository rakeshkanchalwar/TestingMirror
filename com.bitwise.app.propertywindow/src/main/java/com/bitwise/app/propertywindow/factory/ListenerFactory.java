package com.bitwise.app.propertywindow.factory;

import com.bitwise.app.propertywindow.widgets.listeners.ELTBrowseFileListener;
import com.bitwise.app.propertywindow.widgets.listeners.ELTCheckFileExtensionListener;
import com.bitwise.app.propertywindow.widgets.listeners.ELTCreateNewClassListener;
import com.bitwise.app.propertywindow.widgets.listeners.ELTEmptyTextModifyListener;
import com.bitwise.app.propertywindow.widgets.listeners.ELTEnableButtonListener;
import com.bitwise.app.propertywindow.widgets.listeners.ELTFileDialogSelectionListener;
import com.bitwise.app.propertywindow.widgets.listeners.ELTFocusListener;
import com.bitwise.app.propertywindow.widgets.listeners.ELTFocusOutListener;
import com.bitwise.app.propertywindow.widgets.listeners.ELTHelloTestListener;
import com.bitwise.app.propertywindow.widgets.listeners.ELTHiTestListener;
import com.bitwise.app.propertywindow.widgets.listeners.ELTOpenFileEditorListener;
import com.bitwise.app.propertywindow.widgets.listeners.ELTRuntimeButtonClickListener;
import com.bitwise.app.propertywindow.widgets.listeners.ELTSelectionListener;
import com.bitwise.app.propertywindow.widgets.listeners.ELTVerifyComponentNameListener;
import com.bitwise.app.propertywindow.widgets.listeners.ELTVerifyNumbericListener;
import com.bitwise.app.propertywindow.widgets.listeners.ELTVerifyTextListener;
import com.bitwise.app.propertywindow.widgets.listeners.IELTListener;
import com.bitwise.app.propertywindow.widgets.listeners.MyCustomWidgetTextChange;
import com.bitwise.app.propertywindow.widgets.listeners.grid.ELTGridAddSelectionListener;
import com.bitwise.app.propertywindow.widgets.listeners.grid.ELTGridDeleteAllSelectionListener;
import com.bitwise.app.propertywindow.widgets.listeners.grid.ELTGridDeleteSelectionListener;
import com.bitwise.app.propertywindow.widgets.listeners.grid.ELTGridMouseDoubleClickListener;
import com.bitwise.app.propertywindow.widgets.listeners.grid.ELTGridMouseDownListener;

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
		}else if(listener.equals("ELTSelectionListener")){
			IELTListener selectionlistener = new ELTSelectionListener();
			return selectionlistener;
		}else if(listener.equals("ELTVerifyTextListener")){  
			IELTListener verifytextlistener = new ELTVerifyTextListener();
			return verifytextlistener;
		}else if (listener.equals("ELTVerifyComponentNameListener")){
			IELTListener eltListener = new ELTVerifyComponentNameListener();
			return eltListener;
		}else if (listener.equals("ELTRuntimeButtonClickListener")){
			IELTListener eltListener = new ELTRuntimeButtonClickListener();
			return eltListener;

		}else if (listener.equals("ELTFileDialogSelectionListener")){
			IELTListener filedialoglistener = new ELTFileDialogSelectionListener();
			return filedialoglistener;
		}
		else if (listener.equals("ELTFocusOutListener")){
			IELTListener filedialoglistener = new ELTFocusOutListener();
			return filedialoglistener;
 
		} 
		else if (listener.equals("ELTEmptyTextModifyListener")){
			IELTListener eltListener = new ELTEmptyTextModifyListener();
			return eltListener;
 
		}
		else if (listener.equals("ELTCheckFileExtensionListener")){
			IELTListener eltListener = new ELTCheckFileExtensionListener();
			return eltListener;
		}
		else if (listener.equals("ELTOpenFileEditorListener")){
			IELTListener eltListener = new ELTOpenFileEditorListener();
			return eltListener;
		}
		else if (listener.equals("ELTCreateNewClassListener")){
			IELTListener eltListener = new ELTCreateNewClassListener();
			return eltListener;
		}
		else if (listener.equals("ELTBrowseFileListener")){
			IELTListener eltListener = new ELTBrowseFileListener();
			return eltListener;
		}
		else if (listener.equals("ELTEnableButtonListener")){
			IELTListener eltListener = new ELTEnableButtonListener();
			return eltListener;
		}else if (listener.equals("ELTVerifyNumbericListener")){
			IELTListener eltListener = new ELTVerifyNumbericListener();
			return eltListener;
		}
		else if (listener.equals("ELTGridMouseDoubleClickListener")){ 
			IELTListener eltListener = new ELTGridMouseDoubleClickListener();
			return eltListener;
		}
		else if (listener.equals("ELTGridMouseDownListener")){ 
			IELTListener eltListener = new ELTGridMouseDownListener();
			return eltListener;
		}
		else if (listener.equals("ELTGridAddSelectionListener")){ 
			IELTListener eltListener = new ELTGridAddSelectionListener();
			return eltListener;
		}
		else if (listener.equals("ELTGridDeleteSelectionListener")){ 
			IELTListener eltListener = new ELTGridDeleteSelectionListener();
			return eltListener;
		}
		else if (listener.equals("ELTGridDeleteAllSelectionListener")){ 
			IELTListener eltListener = new ELTGridDeleteAllSelectionListener();
			return eltListener;
		}else if (listener.equals("ELTFocusListener")){ 
			IELTListener eltListener = new ELTFocusListener();
			return eltListener;
		}
		
		return null;
		
	}
}

