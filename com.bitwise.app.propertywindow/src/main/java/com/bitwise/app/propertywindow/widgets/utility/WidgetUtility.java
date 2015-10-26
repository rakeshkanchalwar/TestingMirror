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


// TODO: Auto-generated Javadoc
/**
 * The Class WidgetUtility.
 * 
 * @author Bitwise
 */
public class WidgetUtility {
	
	 
	/**
	 * Creates the table viewer.
	 * 
	 * @param tableViewer
	 *            the table viewer
	 * @param iStructuredContentProvider
	 *            the i structured content provider
	 * @param iTableLabelProvider
	 *            the i table label provider
	 * @return the table viewer
	 */
	public static TableViewer createTableViewer( TableViewer tableViewer,IStructuredContentProvider iStructuredContentProvider,ITableLabelProvider iTableLabelProvider){
		tableViewer.setContentProvider(iStructuredContentProvider);
		tableViewer.setLabelProvider(iTableLabelProvider);
		return tableViewer;

	}
	
	/**
	 * Creates the table columns.
	 * 
	 * @param table
	 *            the table
	 * @param fields
	 *            the fields
	 */
	public static void createTableColumns(Table table,String[] fields){
		for (String field : fields) {
			new TableColumn(table, SWT.CENTER).setText(field);
		}
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
	}
	
	/**
	 * Creates the layout.
	 * 
	 * @param z
	 *            the z
	 * @return the layout
	 */
	public static Layout createLayout(Object...z){
		return null;
	}
	
	
	/**
	 * Creates the cell editor.
	 * 
	 * @param cellEditorList
	 *            the cell editor list
	 * @return the cell editor[]
	 */
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
		FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
		Image img = fieldDecoration.getImage();
		txtDecorator.setImage(img);
		txtDecorator.setDescriptionText(message);
		/*// hiding it initially
		txtDecorator.hide();*/
		return txtDecorator; 
	} 
	


/**
 * Checks if is file extention.
 * 
 * @param file
 *            the file
 * @param extention
 *            the extention
 * @return true, if is file extention
 */
public static boolean isFileExtention(String file,String extention) {
     try {
        return extention.equalsIgnoreCase(file.substring(file.lastIndexOf(".")));

    } catch (Exception e) {
        return false;
    }

}

/**
 * Error message.
 * 
 * @param message
 *            the message
 */
public static void errorMessage(String message) {
	Shell shell = new Shell();
	MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
	messageBox.setText("Error");
	messageBox.setMessage(message);
	messageBox.open();
}

/**
 * Elt confirm message.
 * 
 * @param message
 *            the message
 * @return true, if successful
 */
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
