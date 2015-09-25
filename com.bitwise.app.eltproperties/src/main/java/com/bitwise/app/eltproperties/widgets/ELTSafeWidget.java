package com.bitwise.app.eltproperties.widgets;

import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.bitwise.app.eltproperties.Messages;
import com.bitwise.app.eltproperties.widgets.properties.model.common.ComboBoxParameter;
import com.bitwise.app.eltproperties.widgets.properties.model.filter.OperationClassProperty;

public class ELTSafeWidget extends AbstractELTWidget{
    private Combo combo;
	private Text text;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	Group grpGroup_1;
	private Object properties;
	private String propertyName;
	private LinkedHashMap<String, Object> property=new LinkedHashMap<>();
	private ComboBoxParameter comboBoxParameter=new ComboBoxParameter();
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
			lblAdesss.setText("Safe");
			

			combo = new Combo(composite_3, SWT.READ_ONLY);
			
			combo.setItems(new String[]{"True","False","Parameter"});
			combo.select(1);
			fd_lblAdesss.top = new FormAttachment(combo, 0, SWT.TOP);
			fd_lblAdesss.bottom = new FormAttachment(combo, -8, SWT.BOTTOM);
			fd_lblAdesss.right = new FormAttachment(combo, -6);
			FormData fd_combo = new FormData();
			fd_combo.right = new FormAttachment(100, -230);
			fd_combo.left = new FormAttachment(0, 104);
			fd_combo.bottom = new FormAttachment(0, 30);
			fd_combo.top = new FormAttachment(0, 7);
			combo.setLayoutData(fd_combo);
			formToolkit.adapt(combo);
			formToolkit.paintBordersFor(combo);
			
			text = new Text(composite_3, SWT.BORDER);
		
			text.setVisible(false);
			FormData fd_text = new FormData();
			fd_text.top = new FormAttachment(0,8);
			fd_text.right = new FormAttachment(combo, 160, SWT.RIGHT);
			fd_text.left = new FormAttachment(combo, 36);
			text.setLayoutData(fd_text);
			formToolkit.adapt(text, true, true);
			
			final ControlDecoration txtDecorator = new ControlDecoration(text, SWT.TOP|SWT.LEFT);
			FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry .DEC_ERROR);
			Image img = fieldDecoration.getImage();
			txtDecorator.setImage(img);
			txtDecorator.setDescriptionText(Messages.CHARACTERSET);
			txtDecorator.hideHover();
			
			combo.addSelectionListener(new SelectionAdapter() {
				@Override 
			    public void widgetSelected(SelectionEvent e) {
					
					if(combo.getText().equals("Parameter")) {
					
						text.setVisible(true);
					}else {
						
						text.setVisible(false);
					}
				}
			});
			
			 
//			text.addModifyListener(new ModifyListener() {
//				
//				@Override
//				public void modifyText(ModifyEvent e) {
//					
//					if( text.getText().isEmpty()) {
//						text.setBackground(new Color(grpGroup_1.getDisplay(),255,255,204));
//						
//					}
//					else{
//								
//						text.setBackground(new Color(grpGroup_1.getDisplay(), 255,255,255));
//						
//						
//				}
//					
//				}
//			});	
			text.addVerifyListener(new VerifyListener() {
				
				@Override
				public void verifyText(VerifyEvent e) {
					String string=e.text;
					Matcher matchs=Pattern.compile("^[\\w]*").matcher(string);
					if(!matchs.matches()){
						txtDecorator.show();
						e.doit=false;
				}else
						txtDecorator.hide();
				
				}
			});	
		}
	

	@Override
	public void setProperties(String propertyName, Object properties) {
		this.properties =  properties; 
		this.propertyName = propertyName;
		if(properties != null && properties instanceof ComboBoxParameter){
			
			if(!combo.getText().equalsIgnoreCase("Parameter"))
			{
				text.setVisible(true);
				combo.setText(((ComboBoxParameter)properties).getOption());
				if(((ComboBoxParameter)properties).getOptionValue()!=null)
				text.setText(((ComboBoxParameter)properties).getOptionValue());
				
			}
			else
			{
				text.setVisible(false);
				combo.setText(((ComboBoxParameter)properties).getOption());
				}
			
		}

			
	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		
		if( combo.getText().equalsIgnoreCase("Parameter"))
		{
			comboBoxParameter.setOption(combo.getText());
			comboBoxParameter.setOptionValue(text.getText());
		}
		
		else
		{
			comboBoxParameter.setOption(combo.getText());
			comboBoxParameter.setOptionValue("");
		}
			property.put(propertyName,comboBoxParameter);
		return property;
	}
	@Override
	public void setComponentName(String componentName) {
		// TODO Auto-generated method stub
		
	}
	
}
