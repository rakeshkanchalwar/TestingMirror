package com.bitwise.app.tooltip.manager;

import java.util.List;

import org.eclipse.jface.text.AbstractInformationControlManager;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.bitwise.app.tooltip.creater.ComponentTooltipWithStatusBarCreator;
import com.bitwise.app.tooltip.creater.ComponentTooltipWithToolBarCreator;
import com.bitwise.app.tooltip.creater.IComponentTooltipCreator;
import com.bitwise.app.tooltip.informationprovider.IInformationProvider;
import com.bitwise.app.tooltip.tooltips.ComponentTooltip;

/**
 * Information control manager.
 */
public class ComponentTooltipManager extends AbstractInformationControlManager {

	/**
	 * Internal information control closer. Listens to several events issued by its subject control
	 * and closes the information control when necessary.
	 */
	
	boolean setToolBar=false;
	
	class InformationControlCloser implements IInformationControlCloser,  FocusListener, KeyListener  {

		/** The subject control. */
		private Control subjectControl;
		/** The information control. */
		private IInformationControl informationControlToClose;
		/** Indicates whether this closer is active. */
		private boolean isActive = false;
		
		@Override
		public void setSubjectControl(Control control) {
			subjectControl = control;
		}

		@Override
		public void setInformationControl(IInformationControl control) {
			informationControlToClose = control;
		}

		@Override
		public void start(Rectangle informationArea) {
			if (isActive) {
				return;
			}
			isActive = true;

			if (subjectControl != null && !subjectControl.isDisposed()) {
				subjectControl.addKeyListener(this);
			}

			if (informationControlToClose != null) {
				informationControlToClose.addFocusListener(this);
			}
			
		}

		@Override
		public void stop() {
			if (!isActive) {
				return;
			}
			isActive = false;

			if (subjectControl != null && !subjectControl.isDisposed()) {
				subjectControl.removeKeyListener(this);
			}
			
			if (informationControlToClose != null) {
				informationControlToClose.removeFocusListener(this);
			}
		}		
		
		@Override
		public void focusGained(FocusEvent e) {
			if(!((ComponentTooltip)informationControlToClose).hasToolBarManager()){
				setToolBar=true;
				showInformation();
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			Display d = subjectControl.getDisplay();
			d.asyncExec(new Runnable() {
				// Without the asyncExec, mouse clicks to the workbench window are swallowed.
				@Override
				public void run() {
					if (informationControlToClose == null || !informationControlToClose.isFocusControl()) {
						hideInformationControl();
					}
				}
			});
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(!((ComponentTooltip)informationControlToClose).hasToolBarManager()){
				setToolBar=true;
				showInformation();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// nothing to do
		}
	}

	private final IInformationProvider informationProvider;
	private final List<IComponentTooltipCreator> customControlCreators;


	/**
	 * Creates a new information control manager that uses the given information provider and control creators.
	 * The manager is not installed on any control yet. By default, an information
	 * control closer is set that closes the information control in the event of key strokes,
	 * resizing, moves, focus changes, mouse clicks, and disposal - all of those applied to
	 * the information control's parent control. Optionally, the setup ensures that the information
	 * control when made visible will request the focus.
	 *
	 * @param informationProvider the information provider to be used
	 * @param customControlCreators the control creators to be used
	 * @param takeFocusWhenVisible set to <code>true</code> if the information control should take focus when made visible
	 */
	public ComponentTooltipManager(IInformationProvider informationProvider, List<IComponentTooltipCreator> customControlCreators, boolean takeFocusWhenVisible) {
		super(customControlCreators.get(0));
		
		this.informationProvider = informationProvider;
		this.customControlCreators = customControlCreators;

		setCloser(new InformationControlCloser());
		takesFocusWhenVisible(takeFocusWhenVisible);
		
	}

	@Override
	protected void computeInformation() {
		Point mouseLocation = getMouseLocation();

		// Compute information input
		Object info = informationProvider.getInformation(mouseLocation);
		Rectangle tooltipBounds = informationProvider.getArea(mouseLocation);
		
		//set CustomInformationControlCreater
		setCustomInformationControlCreator(getCustomControlCreater());

		// Trigger the presentation of the computed information
		setInformation(info, tooltipBounds);
		
	}

	private Point getMouseLocation() {
		Display display = getSubjectControl().getDisplay();
		Point mouseLocation = display.getCursorLocation();
		mouseLocation = getSubjectControl().toControl(mouseLocation);
		return mouseLocation;
	}

	private IInformationControlCreator getCustomControlCreater() {
		// Find an information control creator for the computed information input
		IInformationControlCreator customControlCreator = null;
		if(setToolBar){
			if(customControlCreators.get(0) instanceof ComponentTooltipWithToolBarCreator){
				customControlCreator = customControlCreators.get(0);
			}else{
				customControlCreator = customControlCreators.get(1);
			}
		}else{
			if(customControlCreators.get(0) instanceof ComponentTooltipWithStatusBarCreator){
				customControlCreator = customControlCreators.get(0);
			}else{
				customControlCreator = customControlCreators.get(1);
			}
		}
		setToolBar=false;
		return customControlCreator;
	}

	@Override
	protected Point computeLocation(Rectangle subjectArea, Point controlSize, Anchor anchor) {
		Point location = super.computeLocation(subjectArea, controlSize, anchor);
		location.x += 20;
		return location;
	}

	public void hideInformation() {
		hideInformationControl();
	}
	
	
}