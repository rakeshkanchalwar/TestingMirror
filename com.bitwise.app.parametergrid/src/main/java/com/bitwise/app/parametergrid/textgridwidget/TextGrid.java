package com.bitwise.app.parametergrid.textgridwidget;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;

import com.bitwise.app.parametergrid.textgridwidget.columns.TextGridColumns;
import com.bitwise.app.parametergrid.textgridwidget.rows.TextGridRowBuilder;

public class TextGrid {
	private Composite container;
	private TextGridColumns textGridColumns;
	private int numberOfRows;
	
	private Composite innerComposite;	
	private ScrolledComposite textGridComposite;
	private ColumnLayoutData textGridCompositeLayoutData;
	
	private TextGridRowBuilder textGridRowBuilder;
		
	private Composite lastAddedRow;
	
	private List<Composite> textGrid;
	
	public TextGrid(){
		textGrid = new ArrayList<>();
	}
	
	public void attachGrid(Composite container,int numberOfRows,TextGridColumns textGridColumns){
		this.textGridColumns = textGridColumns;
		this.numberOfRows = numberOfRows;	
		this.container = container;
		initializeGridComposites();		
		textGridRowBuilder = new TextGridRowBuilder(innerComposite, this.textGridColumns);		
		createInitialRows();
	}
	
	public List<Composite> getGrid(){
		return textGrid;
	}
		
	public void addRow(){				
		lastAddedRow = textGridRowBuilder.addRaw();	
		textGrid.add(lastAddedRow);
		
	}
	
	public void scrollToLastRow(){
		textGridComposite.showControl(lastAddedRow.getChildren()[0]);
	}
	
	private void initializeGridComposites(){
		textGridComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		textGridCompositeLayoutData = new ColumnLayoutData();
		textGridCompositeLayoutData.heightHint = 267;
		textGridComposite.setLayoutData(textGridCompositeLayoutData);
		textGridComposite.setExpandVertical(true);
		textGridComposite.setExpandHorizontal(true);
		
		innerComposite = new Composite(textGridComposite, SWT.NONE);
		ColumnLayout cl_composite_1 = new ColumnLayout();
		cl_composite_1.maxNumColumns = 1;
		innerComposite.setLayout(cl_composite_1);
		
		textGridComposite.setContent(innerComposite);
	}
	
	private void createInitialRows(){
		for(int i=0;i<numberOfRows;i++){
			addRow();
		}
	}
	
	public void refresh(){
		textGridComposite.setMinSize(innerComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		innerComposite.setBounds(innerComposite.getBounds().x, innerComposite.getBounds().y, innerComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).x, innerComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
	}
	
	public void setHeight(int height){
		textGridCompositeLayoutData.heightHint = height;
	}

	public TextGridColumns getTextGridColumns() {
		return textGridColumns;
	}
	
	public void setData(List<String[]> gridData) throws Exception{
			for(int i=0;i<textGrid.size();i++){
				Control[] rowColumns = textGrid.get(i).getChildren();
				String[] rowData = gridData.get(i);
				for(int j=0;j<rowColumns.length;j++){
					((Text)rowColumns[j]).setText(rowData[j]);
				}
			}		
	}	
	
	public List<String[]> getData(){
		List<String[]> gridData = new ArrayList<>();
		for(int i=0;i<textGrid.size();i++){
			Control[] rowColumns = textGrid.get(i).getChildren();
			String[] rowData = new String[textGridColumns.getNumberOfColumn()];
			for(int j=0;j<textGridColumns.getNumberOfColumn();j++){
				rowData[j] = ((Text)rowColumns[j]).getText();
			}
			gridData.add(rowData);
		}
		return gridData;
	}
	
}
