package com.bitwise.app.tooltip.window;

import java.util.Map;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.text.AbstractInformationControl;
import org.eclipse.jface.text.IInformationControlExtension2;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ColumnLayout;

import com.bitwise.app.common.datastructures.tooltip.PropertyToolTipInformation;

public class ComponentTooltip extends AbstractInformationControl implements IInformationControlExtension2 {
	Text text;
	private ToolBarManager toolBarManager=null;
	//private PropertyToolTipInformation propertyToolTipInformation;
	private Map<String,PropertyToolTipInformation> componentToolTipInformation;
	private Composite tooltipContainer;
	private String toolTipData;
	
	public ComponentTooltip(Shell parent, ToolBarManager toolBarManager,Map<String,PropertyToolTipInformation> propertyToolTipInformation) {
		super(parent, toolBarManager);
		this.toolBarManager= getToolBarManager();		
		this.componentToolTipInformation = propertyToolTipInformation;
		create();
	}
	
	public ComponentTooltip(Shell parent, String status,Map<String,PropertyToolTipInformation> propertyToolTipInformation) {
		super(parent, status);
		this.componentToolTipInformation = propertyToolTipInformation;
		create();
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
	
	
	@Override
	protected void createContent(Composite parent) {
	
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		text = new Text(container, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		
		StringBuilder stringBuilder = new StringBuilder();
		for(String property: componentToolTipInformation.keySet()){
			PropertyToolTipInformation propertyInfo = componentToolTipInformation.get(property);
			if(propertyInfo.isShowAsTooltip()){
				if(propertyInfo.getTooltipDataType().equalsIgnoreCase("TEXT") || propertyInfo.getTooltipDataType().equalsIgnoreCase("LINK")){
					if(propertyInfo.getPropertyValue() != null){
						if(propertyInfo.getPropertyName().equalsIgnoreCase("oprationClass")){
							stringBuilder.append(propertyInfo.getPropertyValue());
							stringBuilder.append("\n");
						}else{
							stringBuilder.append(propertyInfo.getPropertyName() + " : " + propertyInfo.getPropertyValue());
							stringBuilder.append("\n");
						}	
					}
					
					
				}else if(propertyInfo.getTooltipDataType().equalsIgnoreCase("LIST")){
					if(propertyInfo.getPropertyValue() != null){
						stringBuilder.append(propertyInfo.getPropertyName() + " : " + propertyInfo.getPropertyValue().toString());
						stringBuilder.append("\n");
					}
				}
			}
		}
		text.setText(stringBuilder.toString());
		text.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
		text.setEditable(false);
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
		text.dispose();
		//tooltipContainer.dispose();
		super.handleDispose();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
}
