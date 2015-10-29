package com.bitwise.app.graph.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;

public class ComponentLabelFigure extends Figure{
	private TextFlow textFlow;
	
	public ComponentLabelFigure(String compLabel) {
		getBounds().setSize(90, 30);
		FlowPage flowPage = new FlowPage();

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
}
