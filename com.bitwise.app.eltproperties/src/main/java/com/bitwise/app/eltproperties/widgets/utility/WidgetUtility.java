package com.bitwise.app.eltproperties.widgets.utility;

import java.util.List;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.bitwise.app.eltproperties.Messages;

public class WidgetUtility {

	public static void main(String[] args) {
	System.out.println(WidgetUtility.isFileExtention("bdbd.java", "java"));	
	}
	
	public static Label createLable(Label lable){
		lable.setForeground(new Color(Display.getDefault(), 255, 0,0));
		FormData fd_errorLable = new FormData();
		fd_errorLable.right = new FormAttachment(100, -36);
		lable.setLayoutData(fd_errorLable);
		lable.setText(Messages.FIELDNAMEERROR);
		lable.setVisible(false);
		return lable;
	}
	 
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
	
	public static FormData createFormData(){
		FormData fd = new FormData();
		fd.bottom = new FormAttachment(0, 290);
		fd.right = new FormAttachment(0, 290);
		fd.left = new FormAttachment(0, 4);
		return fd;
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


}
