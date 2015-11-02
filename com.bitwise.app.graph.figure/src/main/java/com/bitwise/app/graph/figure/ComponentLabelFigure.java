package com.bitwise.app.graph.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;

/**
 * The Class ComponentLabelFigure.
 * 
 * @author Bitwise
 */

public class ComponentLabelFigure extends Figure{
	private TextFlow textFlow;
	private FlowPage flowPage;
	
	public ComponentLabelFigure(String compLabel) {
		getBounds().setSize(98, 15);
		flowPage = new FlowPage();

		textFlow = new TextFlow();

		textFlow.setLayoutManager(new ParagraphTextLayout(textFlow,
				ParagraphTextLayout.WORD_WRAP_SOFT));

		textFlow.setText(compLabel);
		
		flowPage.setHorizontalAligment(PositionConstants.CENTER);
		
		flowPage.add(textFlow);
		setLayoutManager(new StackLayout());
		
		add(flowPage);
	}
	
	public String getComponentLabel(){
		return this.textFlow.getText();
	}
	public void setComponentLabel(String compLabel){
		this.textFlow.setText(compLabel);
	}
	
	public void selectLabel(){
		
		textFlow.setForegroundColor(ColorConstants.white);
		setBackgroundColor(ELTColorConstants.bgComponentSelected);
	}
	
	public void deSelectlabel(){

		textFlow.setForegroundColor(ColorConstants.black);
		setBackgroundColor(ColorConstants.white);
	}
	

}
