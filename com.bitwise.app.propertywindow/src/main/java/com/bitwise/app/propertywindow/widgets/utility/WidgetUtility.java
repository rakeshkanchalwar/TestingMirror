package com.bitwise.app.propertywindow.widgets.utility;

import java.util.List;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;


public class WidgetUtility {
	
	 
	public static TableViewer createTableViewer( TableViewer tableViewer,IStructuredContentProvider iStructuredContentProvider,ITableLabelProvider iTableLabelProvider){
		tableViewer.setContentProvider(iStructuredContentProvider);
		tableViewer.setLabelProvider(iTableLabelProvider);
		return tableViewer;

	}
	
	public static void createTableColumns(Table table,String[] fields){
		for (String field : fields) {
			new TableColumn(table, SWT.CENTER).setText(field);
		}
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
	}
	
	public static Layout createLayout(Object...z){
		return null;
	}
	
	
	public static CellEditor[] createCellEditor(List<CellEditor> cellEditorList){
		CellEditor[] editors = new CellEditor[cellEditorList.size()];
		editors = (CellEditor[]) cellEditorList.toArray();
		return editors;
	}
	
	/**
	 * This Method use to create error message decorator,Its show an error image with message on applied controller field. 
	 * @param control
	 * @param message
	 * @return ControlDecoration
	 */
	
	public static ControlDecoration addDecorator(Control control,String message){
		ControlDecoration txtDecorator = new ControlDecoration(control, SWT.TOP|SWT.LEFT);
		FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry .DEC_ERROR);
		Image img = fieldDecoration.getImage();
		txtDecorator.setImage(img);
		txtDecorator.setDescriptionText(message);
		// hiding it initially
		txtDecorator.hide();
		return txtDecorator; 
	} 
	


public static boolean isFileExtention(String file,String extention) {
     try {
        return extention.equalsIgnoreCase(file.substring(file.lastIndexOf(".")));

    } catch (Exception e) {
        return false;
    }

}
public static void errorMessage(String message) {
	Shell shell = new Shell();
	MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
	messageBox.setText("Error");
	messageBox.setMessage(message);
	messageBox.open();
}

public static boolean eltConfirmMessage(String message){
	Shell shell = new Shell();
MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION
        | SWT.YES | SWT.NO);
    messageBox.setMessage(message);
    int response = messageBox.open();
    if (response == SWT.YES)
    return true;

return false;
}

}
