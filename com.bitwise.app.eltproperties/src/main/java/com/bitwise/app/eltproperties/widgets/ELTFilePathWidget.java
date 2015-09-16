package com.bitwise.app.eltproperties.widgets;

import java.io.File;
import java.util.LinkedHashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class ELTFilePathWidget extends AbstractELTWidget{
	Group grpGroup_1;
	private Object properties;
	private String propertyName;
	private Text filename;
	
	@Override
	public void attachToPropertySubGroup(Group subGroup) {
		Composite composite = new Composite(subGroup, SWT.NONE);
		composite.setLayout(new FormLayout());
		final Shell shell = composite.getShell();
		Label lblNewLabel = new Label(composite, SWT.BORDER | SWT.CENTER);
		lblNewLabel.setLayoutData(new FormData());
		lblNewLabel.setBounds(40, 83, 70, 21);
		lblNewLabel.getBackground();
		lblNewLabel.setText("File Path:");
		filename = new Text(composite, SWT.BORDER);
		FormData fd_filename = new FormData();
		fd_filename.left = new FormAttachment(lblNewLabel, 22);
		filename.setLayoutData(fd_filename);
		filename.setBounds(137, 83, 200, 21);
		filename.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (filename.getText().isEmpty()) {
					filename.setBackground(new Color(Display.getDefault(), 255,
							255, 204));
				} else
					filename.setBackground(new Color(Display.getDefault(), 255,
							255, 255));
			}
		});

		Button btnNewButton = new Button(composite, SWT.PUSH);
		fd_filename.right = new FormAttachment(100, -241);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(lblNewLabel, 0, SWT.TOP);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setBounds(350, 83, 20, 21);
		btnNewButton.setText("...");
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog filedialog = new FileDialog(shell, SWT.NULL);
				String path = filedialog.open();

				if (path != null) {
					File file = new File(path);
					filename.setText(file.getAbsolutePath());
					/*if (file.isFile())
						displayFiles(new String[] { file.getAbsoluteFile()
								.toString() });*/
				}
				// displayFiles(file.list());
			}
		});
		
	}

	@Override
	public void setProperties(String propertyName, Object properties) {
		this.properties =  properties;
		this.propertyName = propertyName;
		if(properties != null)
			filename.setText((String) properties);
		else
			filename.setText("");
		
	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		
		LinkedHashMap<String, Object> property=new LinkedHashMap<>();
		property.put(propertyName, filename.getText());
		return property;
	}

	@Override
	public void setComponentName(String componentName) {
		// TODO Auto-generated method stub
		
	}
}
