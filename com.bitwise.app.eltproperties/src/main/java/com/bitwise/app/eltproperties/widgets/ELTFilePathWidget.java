package com.bitwise.app.eltproperties.widgets;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class ELTFilePathWidget extends AbstractELTWidget{
	private Text text_1;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	Group grpGroup_1;
	private Object properties;
	private String propertyName;
	
	@Override
	public void attachToPropertySubGroup(Group subGroup) {
		this.grpGroup_1 = subGroup;
		Composite composite_3 = new Composite(grpGroup_1, SWT.NONE);
		ColumnLayoutData cld_composite_3 = new ColumnLayoutData();
		cld_composite_3.horizontalAlignment = ColumnLayoutData.LEFT;
		cld_composite_3.widthHint = 421;
		composite_3.setLayoutData(cld_composite_3);
		formToolkit.adapt(composite_3);
		formToolkit.paintBordersFor(composite_3);
		FormLayout fl_composite_3 = new FormLayout();
		fl_composite_3.marginTop = 5;
		fl_composite_3.marginBottom = 5;
		composite_3.setLayout(fl_composite_3);
		
		Label lblAdesss = new Label(composite_3, SWT.NONE);
		lblAdesss.setAlignment(SWT.CENTER);
		FormData fd_lblAdesss = new FormData();
		fd_lblAdesss.top = new FormAttachment(0, 5);
		fd_lblAdesss.left = new FormAttachment(0, 20);
		fd_lblAdesss.right = new FormAttachment(0, 83);
		lblAdesss.setLayoutData(fd_lblAdesss);
		formToolkit.adapt(lblAdesss, true, true);
		lblAdesss.setText("File Path: ");
		
		text_1 = new Text(composite_3, SWT.BORDER);
		FormData fd_text_1 = new FormData();
		fd_text_1.top = new FormAttachment(0, 2);
		fd_text_1.left = new FormAttachment(0, 88);
		text_1.setLayoutData(fd_text_1);
		formToolkit.adapt(text_1, true, true);
		
		text_1.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				if(text_1.getText().isEmpty()){
					text_1.setBackground(new Color(grpGroup_1.getDisplay(),255,255,204));
				}
				else
					text_1.setBackground(new Color(grpGroup_1.getDisplay(), 255,255,255));
			}
		});
		
		 final Composite composite = new Composite(grpGroup_1, SWT.NONE);
         composite.setLayout(new FillLayout());
		
		Button btnAdd = new Button(composite_3, SWT.BOLD);
		fd_text_1.right = new FormAttachment(btnAdd, -7);
		FormData fd_btnAdd = new FormData();
		fd_btnAdd.top = new FormAttachment(0);
		fd_btnAdd.right = new FormAttachment(80, -10);
		fd_btnAdd.left = new FormAttachment(100, -110);
		btnAdd.setLayoutData(fd_btnAdd);
		formToolkit.adapt(btnAdd, true, true);
		btnAdd.setText("...");
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override 
		    public void widgetSelected(SelectionEvent e){
				FileDialog filedialog =new FileDialog(composite.getShell(), SWT.NULL);
		 		
	        String path=filedialog.open();
	      
	        if(path != null) {
	        	 File file=new File(path);
	        	 text_1.setText(file.getAbsolutePath());
	        	//if (file.isFile()) 
	        		//displayFiles(new String[] {file.getAbsoluteFile().toString()});
	        } 
			}
		});
		
		text_1.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent e) {
				String string=e.text;
				Matcher matchs=Pattern.compile("[^0-9]*$").matcher(string);
				if(!matchs.matches())
					e.doit=false;
			}
		});
		
	}

	@Override
	public void setProperties(String propertyName, Object properties) {
		this.properties =  properties;
		this.propertyName = propertyName;
		if(properties != null)
			text_1.setText((String) properties);
		else
			text_1.setText("");
		
	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		
		LinkedHashMap<String, Object> property=new LinkedHashMap<>();
		property.put(propertyName, text_1.getText());
		return property;
	}

	@Override
	public void setComponentName(String componentName) {
		// TODO Auto-generated method stub
		
	}
}
