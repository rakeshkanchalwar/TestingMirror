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

public class ELTCharacterSetWidget extends AbstractELTWidget{

	Combo combo;
	private Text text_1;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	Group grpGroup_1;
	private Object properties;
	private String propertyName;
	private ControlDecoration txtDecorator;
	
	private static LinkedHashMap<String, Object> property=new LinkedHashMap<>();
	
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
		
		Label lblAdesss = new Label(composite_3, SWT.READ_ONLY);
		lblAdesss.setAlignment(SWT.CENTER);
		FormData fd_lblAdesss = new FormData();
		fd_lblAdesss.top = new FormAttachment(0, 5);
		fd_lblAdesss.left = new FormAttachment(0, 20);
		fd_lblAdesss.right = new FormAttachment(0, 83);
		lblAdesss.setLayoutData(fd_lblAdesss);
		formToolkit.adapt(lblAdesss, true, true);
		lblAdesss.setText("Character Set: ");
		

		combo = new Combo(composite_3, SWT.READ_ONLY);
		/*combo.setText("select");
		combo.add("True");
		combo.add("False");
		combo.add("Parameter");*/
		combo.setItems(new String[]{"UniCode","UTF-8","ASCII","Parameter"});
		combo.select(1);
		//combo.setItems();
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
		
		text_1 = new Text(composite_3, SWT.BORDER);
		//text_1.setText("$");
		text_1.setVisible(false);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(0, 8);
		fd_text.right = new FormAttachment(combo, 160, SWT.RIGHT);
		fd_text.left = new FormAttachment(combo, 36);
		text_1.setLayoutData(fd_text);
		formToolkit.adapt(text_1, true, true);
		
		txtDecorator = new ControlDecoration(text_1, SWT.TOP|SWT.LEFT);
		FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry .DEC_ERROR);
		Image img = fieldDecoration.getImage();
		txtDecorator.setImage(img);
		txtDecorator.setDescriptionText(Messages.CHARACTERSET);
		
		//combo.setItems(Items);
		//new AutoCompleteField(combo, new ComboContentAdapter(), Items);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override 
		    public void widgetSelected(SelectionEvent e) {
				
				if(combo.getText().equals("Parameter")) {
				
					text_1.setVisible(true);
					//txtDecorator.show();
					
				}else {
					
					text_1.setVisible(false);
					txtDecorator.hide();
				}
			}
		});
		
		text_1.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				if( text_1.getText().isEmpty()) {
					
					txtDecorator.show();
					text_1.setBackground(new Color(grpGroup_1.getDisplay(),255,255,204));	
				}
				else{
					txtDecorator.hide();
					text_1.setBackground(new Color(grpGroup_1.getDisplay(), 255,255,255));
					
					
			}
				
			}
		});
		text_1.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent e) {
				String string=e.text;
				Matcher matchs=Pattern.compile("[\\w]*").matcher(string);
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
          if(properties != null){
                if(((String) properties).equalsIgnoreCase("Parameter"))
                      text_1.setVisible(true);  
                text_1.setText("$"+(String) property.get("text_value"));
                combo.setText((String)properties);
          }else{
                text_1.setText(" ");
               text_1.setBackground(new Color(grpGroup_1.getDisplay(),255,255,204));
              
          }
    }


	@Override
	public LinkedHashMap<String, Object> getProperties() {
	
		property.put(propertyName, combo.getText());
		property.put("text_value", text_1.getText());
		
		return property;
	}

	@Override
	public void setComponentName(String componentName) {
		// TODO Auto-generated method stub
		
	}
}
