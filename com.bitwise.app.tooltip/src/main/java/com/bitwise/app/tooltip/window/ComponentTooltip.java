package com.bitwise.app.tooltip.window;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.text.AbstractInformationControl;
import org.eclipse.jface.text.IInformationControlExtension2;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ColumnLayout;

import com.bitwise.app.common.datastructures.tooltip.PropertyToolTipInformation;

public class ComponentTooltip extends AbstractInformationControl implements IInformationControlExtension2 {
	Text text;
	private ToolBarManager toolBarManager=null;
	private PropertyToolTipInformation propertyToolTipInformation;
	private Composite tooltipContainer;
	private String toolTipData;
	
	public ComponentTooltip(Shell parent, ToolBarManager toolBarManager,PropertyToolTipInformation propertyToolTipInformation) {
		super(parent, toolBarManager);
		create();
		this.toolBarManager= getToolBarManager();		
		this.propertyToolTipInformation = propertyToolTipInformation;
	}
	
	public ComponentTooltip(Shell parent, String status,PropertyToolTipInformation propertyToolTipInformation) {
		super(parent, status);
		create();
		this.propertyToolTipInformation = propertyToolTipInformation;
	}
	
	public ComponentTooltip(Shell parent, String status,String toolTipData) {
		super(parent, status);
		create();
		this.toolTipData = toolTipData;
	}
	
	public boolean hasToolBarManager(){
		if(toolBarManager != null ){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public void setLocation(Point location) {
		// TODO Auto-generated method stub
		super.setLocation(location);
	}
	
	public void setLoc(Point p){
		
	}
	
	@Override
	protected void createContent(Composite parent) {
	
		tooltipContainer = new Composite(parent,SWT.NONE);
		tooltipContainer.setLayout(new ColumnLayout());
		tooltipContainer.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
		
		text = new Text(tooltipContainer, SWT.BORDER);
		
		/*tooltipContainer = new Composite(parent,SWT.NONE);
		tooltipContainer.setLayout(new ColumnLayout());
		tooltipContainer.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
		
		Composite comp2 = new Composite(tooltipContainer,SWT.NONE);
		comp2.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_DARK_BLUE));
		
		text = new Text(tooltipContainer, SWT.BORDER);
		btnHello = new Button(tooltipContainer, SWT.NONE);
		btnHello.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox megbox= new MessageBox(tooltipContainer.getShell());
				megbox.setMessage("Hello World");
				megbox.open();
			}
		});
		btnHello.setText("Hello");*/
	}

	
	public Composite getTooltipContainer(){
		return tooltipContainer;
	}
	
	@Override
	public void setInput(Object input) {
		//text.setText(input.toString());
	}

	
	@Override
	public boolean hasContents() {
		return true;
	}

	@Override
	public void setVisible(boolean visible) {
		Shell shell = getShell();
		if (shell.isVisible() == visible) {
			return;
		}

		if (!visible) {
			super.setVisible(false);
			setInformation(null);
			return;
		}
		

		super.setVisible(true);
	}

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
	}

	@Override
	protected void handleDispose() {
		super.handleDispose();
	}

}
