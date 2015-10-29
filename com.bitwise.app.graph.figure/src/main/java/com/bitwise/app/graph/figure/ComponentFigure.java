package com.bitwise.app.graph.figure;

import java.awt.MouseInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;

import com.bitwise.app.common.component.config.PortSpecification;
import com.bitwise.app.common.datastructures.tooltip.PropertyToolTipInformation;
import com.bitwise.app.common.interfaces.tooltip.ComponentCanvas;
import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.graph.model.Component.ValidityStatus;
import com.bitwise.app.tooltip.window.ComponentTooltip;

// TODO: Auto-generated Javadoc
/**
 * The Class ComponentFigure.
 * 
 * @author Bitwise
 */
public class ComponentFigure extends Figure implements Validator{
	private static final Logger logger = LogFactory.INSTANCE.getLogger(ComponentFigure.class);
	
	private final XYLayout layout;
	private int height=0;
	
	private HashMap<String, FixedConnectionAnchor> connectionAnchors;
	private List<FixedConnectionAnchor> inputConnectionAnchors;
	private List<FixedConnectionAnchor> outputConnectionAnchors;
	private List<PortSpecification> portspecification;
	
	private int totalPortsofInType=0, totalPortsOfOutType=0;
	
	private String labelName;
	private Font labelFont = new Font(null, "", 8, SWT.NORMAL); 
	private Point labelPoint;
	
	private Color borderColor;
	private Color selectedBorderColor;	
	private Color componentColor;
	private Color selectedComponentColor;
	
	private String canvasIconPath;
	private Image canvasIcon;
	
	private String status;

	private Map<String,PropertyToolTipInformation> propertyToolTipInformation;
	private ComponentCanvas componentCanvas;
	private ComponentTooltip componentToolTip;
	org.eclipse.swt.graphics.Rectangle componentBounds;
	/**
	 * Instantiates a new component figure.
	 * 
	 * @param portSpecification
	 *            the port specification
	 * @param cIconPath
	 *            the c icon path
	 */
	public ComponentFigure(List<PortSpecification> portSpecification, String cIconPath) {
		
		this.portspecification = portSpecification;
		this.canvasIconPath = XMLConfigUtil.CONFIG_FILES_PATH + cIconPath;
		
		layout = new XYLayout();
		setLayoutManager(layout);
		
		canvasIcon = new Image(null, canvasIconPath);
		
		connectionAnchors = new HashMap<String, FixedConnectionAnchor>();
		inputConnectionAnchors = new ArrayList<FixedConnectionAnchor>();
		outputConnectionAnchors = new ArrayList<FixedConnectionAnchor>();
		

		setInitialColor();
		setComponentColorAndBorder();
		
		for(PortSpecification p:portspecification)
		{
			setPortCount(p);
			setHeight(totalPortsofInType, totalPortsOfOutType);
		}
		
		componentCanvas = (ComponentCanvas) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		attachMouseListener();
	}
	
	private void hideToolTip(){
		if(componentCanvas != null){
			if(componentCanvas.getComponentTooltip() != null){
				componentCanvas.getComponentTooltip().setVisible(false);
				componentCanvas.issueToolTip(null, null);
			}
		}
		
		if(componentToolTip != null){
			componentToolTip.setVisible(false);
			componentToolTip = null;
			componentBounds=null;
		}
		componentCanvas=null;
	}
	
	public void setPropertyToolTipInformation(Map<String,PropertyToolTipInformation> propertyToolTipInformation){
		this.propertyToolTipInformation = propertyToolTipInformation;
	}

	private ComponentTooltip getStatusToolTip(Shell parent, org.eclipse.swt.graphics.Point location){
		ComponentTooltip tooltip = new ComponentTooltip(parent, "Click to focus", propertyToolTipInformation);
		tooltip.setSize(300, 100);
		tooltip.setLocation(location);
		return tooltip;
	}
	
	private ComponentTooltip getToolBarToolTip(Shell parent, org.eclipse.swt.graphics.Rectangle toltipBounds){
		ToolBarManager toolBarManager = new ToolBarManager();
		ComponentTooltip tooltip = new ComponentTooltip(parent, toolBarManager, propertyToolTipInformation);
		org.eclipse.swt.graphics.Point location=new org.eclipse.swt.graphics.Point(toltipBounds.x, toltipBounds.y);
		tooltip.setLocation(location);
		tooltip.setSize(toltipBounds.width, toltipBounds.height);
		return tooltip;
	}
	
	private void setStatusToolTipFocusListener() {
		
		componentToolTip.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// Do nothing
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				showToolBarToolTip();
			}
		});
	}
	
	private void showStatusToolTip(
			org.eclipse.swt.graphics.Point location) {
	
		componentCanvas = (ComponentCanvas) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if(componentCanvas.getComponentTooltip() == null){
			componentToolTip = getStatusToolTip(componentCanvas.getCanvasControl().getShell(), location);
			componentBounds = getComponentBounds();			
			componentCanvas.issueToolTip(componentToolTip, componentBounds);
			
			componentToolTip.setVisible(true);
			setStatusToolTipFocusListener();
		}else{
			System.out.println("There is tool tip, So I am not shousing new one");
		}
	}

	private org.eclipse.swt.graphics.Rectangle getComponentBounds() {
		Rectangle tempComponuntBound = getBounds();
		org.eclipse.swt.graphics.Rectangle componentBound = new org.eclipse.swt.graphics.Rectangle(tempComponuntBound.x, tempComponuntBound.y, tempComponuntBound.width, tempComponuntBound.height);
		return componentBound;
	}
	
	private void showToolBarToolTip() {
		org.eclipse.swt.graphics.Rectangle toltipBounds = componentToolTip.getBounds();
		
		hideToolTip();
		
		componentCanvas = (ComponentCanvas) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		
		componentToolTip = getToolBarToolTip(componentCanvas.getCanvasControl().getShell(),toltipBounds);
		componentBounds = getComponentBounds();
		componentCanvas.issueToolTip(componentToolTip, componentBounds);
		
		componentToolTip.setVisible(true);
	}
	
	private void attachMouseListener() {
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(org.eclipse.draw2d.MouseEvent arg0) {
				// Do nothing
			}
			
			@Override
			public void mouseHover(org.eclipse.draw2d.MouseEvent arg0) {
				arg0.consume();
				java.awt.Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
				org.eclipse.swt.graphics.Point location = new org.eclipse.swt.graphics.Point(mouseLocation.x, mouseLocation.y);
								
				showStatusToolTip(location);
			}
			
			@Override
			public void mouseExited(org.eclipse.draw2d.MouseEvent arg0) {
				// Do nothing
			}
			
			@Override
			public void mouseEntered(org.eclipse.draw2d.MouseEvent arg0) {
				// Do nothing
			}
			
			@Override
			public void mouseDragged(org.eclipse.draw2d.MouseEvent arg0) {
				// Do nothing
			}
		});
		
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(org.eclipse.draw2d.MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(org.eclipse.draw2d.MouseEvent arg0) {
				// TODO Auto-generated method stub
				hideToolTip();
			}
			
			@Override
			public void mouseDoubleClicked(org.eclipse.draw2d.MouseEvent arg0) {
				// TODO Auto-generated method stub
				hideToolTip();
			}
		});
	}

	private void setPortCount(PortSpecification p) {
		if(("in").equalsIgnoreCase(p.getTypeOfPort())){
			totalPortsofInType=p.getNumberOfPorts();
		}
		else{
			totalPortsOfOutType=p.getNumberOfPorts();
		}
		
	}

	private void setHeight(int totalPortsofInType, int totalPortsOfOutType) {
		int heightFactor=totalPortsofInType > totalPortsOfOutType ? totalPortsofInType : totalPortsOfOutType;
		this.height = (heightFactor+1)*25;
	}

	public int getHeight() {
		return height;
	}
	

	public void setAnchors(FixedConnectionAnchor fCAnchor) {		
		connectionAnchors.put(fCAnchor.getType()+fCAnchor.getSequence(), fCAnchor);
		if(("out").equalsIgnoreCase(fCAnchor.getType()))
			outputConnectionAnchors.add(fCAnchor);
		else
			inputConnectionAnchors.add(fCAnchor);
	}
	
	/**
	 * Draws the status image to right corner of the component
	 * @param graphics
	 */
	protected void drawStatus(Graphics graphics){
		Image statusImage = null;
		Rectangle rectangle = getBounds().getCopy();
		if(getStatus().equals(ValidityStatus.WARN.name())){
			statusImage = new Image(null, XMLConfigUtil.CONFIG_FILES_PATH + "/icons/warn.png");
		}
		else if (getStatus().equals(ValidityStatus.ERROR.name())){
			statusImage = new Image(null, XMLConfigUtil.CONFIG_FILES_PATH + "/icons/error.png");
		}
		logger.debug("Component has {} status.", getStatus());
		if(statusImage != null){
			graphics.drawImage(statusImage, new Point(rectangle.width - 25, 8+ELTFigureConstants.componentLabelMargin));
		}
	}
	
		
	/**
	 * Sets the component color and border.
	 */
	public void setComponentColorAndBorder(){
		setBackgroundColor(componentColor);
		setBorder(new ComponentBorder(borderColor));
	}
	
	/**
	 * Sets the selected component color and border.
	 */
	public void setSelectedComponentColorAndBorder(){
		setBackgroundColor(selectedComponentColor);
		setBorder(new ComponentBorder(selectedBorderColor,2));
	}
	
	private void setInitialColor(){
		componentColor = ELTColorConstants.bgComponent;
		borderColor = ELTColorConstants.componentBorder;
		selectedComponentColor = ELTColorConstants.bgComponentSelected;
		selectedBorderColor = ELTColorConstants.componentSelectedBorder;
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		Rectangle r = getBounds().getCopy();
		graphics.translate(r.getLocation());
		Rectangle q = new Rectangle(4, 4+ELTFigureConstants.componentLabelMargin, r.width-8, r.height-8-ELTFigureConstants.componentLabelMargin);
		graphics.fillRoundRectangle(q, 5, 5);

		//drawLable(r, graphics);
		
		//graphics.drawImage(canvasIcon, new Point(r.width/2-16, r.height/2 - 20));
		graphics.drawImage(canvasIcon, new Point(r.width/2-16, r.height/2-4));
		drawStatus(graphics);
	}
	
	private void drawLable(Rectangle r, Graphics graphics){
		int x = (r.width - getLabelName().length() * 6) / 2;
		labelPoint = new Point(x, r.height / 2 + 8);
		graphics.setForegroundColor(ELTColorConstants.black);
		graphics.setFont(labelFont);
		graphics.drawText(getLabelName(), labelPoint);
	}


	/**
	 * Gets the connection anchor.
	 * 
	 * @param terminal
	 *            the terminal
	 * @return the connection anchor
	 */
	public ConnectionAnchor getConnectionAnchor(String terminal) {

		return connectionAnchors.get(terminal);
	}

	/**
	 * Gets the connection anchor name.
	 * 
	 * @param c
	 *            the c
	 * @return the connection anchor name
	 */
	public String getConnectionAnchorName(ConnectionAnchor c) {
		
		Set<String> keys = connectionAnchors.keySet();
		String key;
		Iterator<String> it = keys.iterator();
		
	     while(it.hasNext()){
	    	 key=it.next();
	    	 if (connectionAnchors.get(key).equals(c))
				return key;
	     }
		return null;
	}

	private ConnectionAnchor closestAnchor(Point p, List<FixedConnectionAnchor> connectionAnchors) {
		ConnectionAnchor closest = null;
		double min = Double.MAX_VALUE;
		for(ConnectionAnchor c : connectionAnchors){
			double d = p.getDistance(c.getLocation(null));
			if (d < min) {
				min = d;
				closest = c;
			}
		}
		return closest;
	}
	
	/**
	 * Gets the source connection anchor at.
	 * 
	 * @param p
	 *            the p
	 * @return the source connection anchor at
	 */
	public ConnectionAnchor getSourceConnectionAnchorAt(Point p) {
		return closestAnchor(p, outputConnectionAnchors);
	}

	/**
	 * Gets the target connection anchor at.
	 * 
	 * @param p
	 *            the p
	 * @return the target connection anchor at
	 */
	public ConnectionAnchor getTargetConnectionAnchorAt(Point p) {
		return closestAnchor(p, inputConnectionAnchors);
	}
	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	
	@Override
	public void validate() {
		super.validate();

		if (isValid())
			return;

	}
	@Override
	public String getStatus() {
		return status;
	}
	
	@Override
	public void setStatus(String status) {
		this.status = status;
	}
}
