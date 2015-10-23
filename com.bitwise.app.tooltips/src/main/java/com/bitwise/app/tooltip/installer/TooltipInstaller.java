package com.bitwise.app.tooltip.installer;

import java.util.List;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.widgets.Control;

import com.bitwise.app.tooltip.creater.IComponentTooltipCreator;
import com.bitwise.app.tooltip.informationprovider.IInformationProvider;
import com.bitwise.app.tooltip.manager.ComponentTooltipManager;

public class TooltipInstaller {

	private TooltipInstaller() {
		// nothing to do
	}

	/**
	 * Installs tooltip support on a component.
	 * @param control the control to install tooltip support on
	 * @param provider information provider to get information about elements
	 * @param controlCreators information control creators to create the tooltips
	 * @param takeFocusWhenVisible set to <code>true</code> if the information control should take focus when made visible
	 */
	public static void install(Control control, IInformationProvider provider, List<IComponentTooltipCreator> controlCreators, boolean takeFocusWhenVisible) {
		final ComponentTooltipManager informationControlManager = new ComponentTooltipManager(provider, controlCreators, takeFocusWhenVisible);
		informationControlManager.install(control);

		// MouseListener to show the information when the user hovers a table item
		control.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseHover(MouseEvent event) {
				informationControlManager.showInformation();
			}
		});

		control.getParent().addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseHover(MouseEvent event) {
				informationControlManager.hideInformation();
			}
		});
		
		control.getParent().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				informationControlManager.hideInformation();
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// nothing to do
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// nothing to do
			}
		});
		
		// DisposeListener to uninstall the information control manager
		control.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				informationControlManager.dispose();
			}
		});
	}
}
