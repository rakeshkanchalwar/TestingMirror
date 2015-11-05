package com.bitwise.app.graph.figure;

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.TextFlow;

/**
 * TooltipFigure class which will display the tooltip. This Figure extends a
 * FlowPage and adds to it a TextFlow where the tooltip’s message is written
 * 
 * @author Bitwise
 */
public class TooltipFigure extends FlowPage implements IFigure {
	private final Border TOOLTIP_BORDER = new MarginBorder(0, 2, 1, 0);
	private final TextFlow message;

	public TooltipFigure() {
		setOpaque(true);
		setBorder(TOOLTIP_BORDER);
		message = new TextFlow();
		message.setText("");
		add(message);
	}

	@Override
	public Dimension getPreferredSize(int w, int h) {
		Dimension d = super.getPreferredSize(-1, -1);
		if (d.width > 150)
			d = super.getPreferredSize(150, -1);
		return d;
	}

	public void setMessage(String txt) {
		message.setText(txt);
		revalidate();
		repaint();
	}

}