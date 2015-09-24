package com.bitwise.app.eltproperties.widgets;

import java.util.LinkedHashMap;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.bitwise.app.eltproperties.Messages;

public class ELTComponentNameWidget extends AbstractELTWidget {

	private Text text;

	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	Group group;
	private String oldName;
	private String propertyName;

	private String newName;
	private ControlDecoration txtDecorator;

	@Override
	public void attachToPropertySubGroup(Group subGroup) {
		this.group = subGroup;
		Composite composite = new Composite(group, SWT.NONE);
		ColumnLayoutData cld_composite = new ColumnLayoutData();
		cld_composite.horizontalAlignment = ColumnLayoutData.LEFT;
		cld_composite.widthHint = 421;
		composite.setLayoutData(cld_composite);
		formToolkit.adapt(composite);
		formToolkit.paintBordersFor(composite);
		FormLayout fl_composite = new FormLayout();
		fl_composite.marginTop = 5;
		fl_composite.marginBottom = 5;
		composite.setLayout(fl_composite);

		Label lblName = new Label(composite, SWT.NONE);
		lblName.setAlignment(SWT.CENTER);
		FormData fd_lblName = new FormData();
		fd_lblName.top = new FormAttachment(0, 5);
		fd_lblName.left = new FormAttachment(0, 20);
		fd_lblName.right = new FormAttachment(0, 83);
		lblName.setLayoutData(fd_lblName);
		formToolkit.adapt(lblName, true, true);
		lblName.setText("Name : ");

		text = new Text(composite, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(0, 2);
		fd_text.left = new FormAttachment(0, 88);
		text.setLayoutData(fd_text);
		text.setTextLimit(50);
		text.setSize(350, 21);
		formToolkit.adapt(text, true, true);
		
		txtDecorator = new ControlDecoration(text, SWT.TOP|SWT.LEFT);
		FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry .DEC_ERROR);
		Image img = fieldDecoration.getImage();
		txtDecorator.setImage(img);
		txtDecorator.setDescriptionText(Messages.FIELDNAMEERROR);
		txtDecorator.hideHover();

		text.addVerifyListener(new VerifyListener() {
			@Override
			public void verifyText(VerifyEvent e) {
				/* Notice how we combine the old and new below */
				String currentText = ((Text) e.widget).getText();
				newName = (currentText.substring(0, e.start) + e.text + currentText.substring(e.end)).trim();

				System.out.println("addVerifyListener(): current text: " + oldName + ", new text: " + newName);
				if (newName == null || newName.equals("")) {
					// e.doit=false;
					text.setBackground(new Color(group.getDisplay(),255,255,204));
					text.setToolTipText("Enter valid name");
					text.setMessage("Valid Name required");
					txtDecorator.show();
					toggleOkButton(false);

				} else if (!newName.equalsIgnoreCase(oldName) && !isUniqueCompName(newName)) {
					text.setBackground(new Color(group.getDisplay(),255,255,204));
					text.setToolTipText("Enter unique name for component.");
					text.setMessage("Unique Name required");
					txtDecorator.show();
					toggleOkButton(false);

				} else {
					text.setBackground(new Color(group.getDisplay(), 255,255,255));
					text.setToolTipText("");
					text.setMessage("");
					txtDecorator.hide();
					toggleOkButton(true);
				}
			}
		});

	}

	@Override
	public void setProperties(String propertyName, Object properties) {
		System.out.println("ELTComponentNameWidget.setProperties():-propertyName: " + propertyName + ", properties: "
				+ properties);
		this.propertyName = propertyName;
		this.oldName = (String) properties;
		text.setText(oldName);

	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		LinkedHashMap<String, Object> property = new LinkedHashMap<>();
		if (newName != null && newName != "" && isUniqueCompName(newName)) {
			property.put(propertyName, newName);
			super.names.remove(oldName);
			super.names.add(newName);
		} else {
			// old name already should be there in the names arraylist
			property.put(propertyName, oldName);
		}

		return property;
	}

	@Override
	public void setComponentName(String componentName) {
		// TODO Auto-generated method stub

	}

	private boolean isUniqueCompName(String componentName) {
		componentName = componentName.trim();
		boolean result = true;

		for (String cname : super.names) {
			if (cname.equalsIgnoreCase(componentName)) {
				result = false;
				break;
			}

		}
		System.out.println("isUniqueCompName: result: " + result);

		return result;
	}

}