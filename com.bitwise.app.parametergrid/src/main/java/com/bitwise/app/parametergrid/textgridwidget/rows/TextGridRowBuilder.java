package com.bitwise.app.parametergrid.textgridwidget.rows;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.bitwise.app.parametergrid.textgridwidget.columns.TextGridColumnDataLayout;
import com.bitwise.app.parametergrid.textgridwidget.columns.TextGridColumns;

public class TextGridRowBuilder {
	private TextGridColumns textGridColumns;
	private Composite container;
	public TextGridRowBuilder(Composite container,TextGridColumns textGridColumns){
		this.textGridColumns = textGridColumns;
		this.container = container;
	}
	
	public Composite addRaw(){
		Composite composite = new Composite(container, SWT.NONE);
		GridLayout gl_composite = new GridLayout(textGridColumns.getNumberOfColumn(), false);
		gl_composite.horizontalSpacing = 1;
		gl_composite.marginWidth = 1;
		gl_composite.marginHeight = 0;
		gl_composite.verticalSpacing = 1;
		composite.setLayout(gl_composite);
				
		Map<Integer, TextGridColumnDataLayout> columns = textGridColumns.getTextGridColumns();
		for(int columnNumber:columns.keySet()){
			if(!columns.get(columnNumber).grabHorizantalAccessSpace()){
				Text text = new Text(composite, SWT.BORDER);		
				GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_text.widthHint = columns.get(columnNumber).getColumnWidth();
				text.setLayoutData(gd_text);
				
				text.setEditable(columns.get(columnNumber).isEditable());
				text.setEnabled(columns.get(columnNumber).isEnabled());
			}else{
				Text text = new Text(composite, SWT.BORDER);
				text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				text.setBounds(0, 0, 76, 21);
				text.setFocus();
			}
		}
		
		return composite;
	}
}
