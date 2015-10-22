package com.bitwise.app.perspective.config;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * @author Bitwise
 * Aug 25, 2015
 *
 */

public class PerspectiveRemover {
	/**
	 * Removes the unwanted perspectives from your RCP application
	 */
	public void removeUnWantedPerspectives() {
		IPerspectiveRegistry perspectiveRegistry = PlatformUI.getWorkbench().getPerspectiveRegistry();
		IPerspectiveDescriptor[] perspectiveDescriptors = getAllRegisteredPerspectiveDescriptors();
		ArrayList<IPerspectiveDescriptor> ignorePerspectiveList = getIgnorePerspectiveList(perspectiveDescriptors);
		removePerspetives(perspectiveRegistry, ignorePerspectiveList);
	}

	private void removePerspetives(IPerspectiveRegistry perspectiveRegistry, List<IPerspectiveDescriptor> removePerspectiveDesc) {
		// If the list is non-empty then remove all such perspectives from the IExtensionChangeHandler
		if(perspectiveRegistry instanceof IExtensionChangeHandler && !removePerspectiveDesc.isEmpty()) {
			IExtensionChangeHandler extChgHandler = (IExtensionChangeHandler) perspectiveRegistry;
			extChgHandler.removeExtension(null, removePerspectiveDesc.toArray());
		}
	}

	private IPerspectiveDescriptor[] getAllRegisteredPerspectiveDescriptors(){
		return PlatformUI.getWorkbench().getPerspectiveRegistry().getPerspectives();
	}
	
	private ArrayList<IPerspectiveDescriptor> getIgnorePerspectiveList(IPerspectiveDescriptor[] registeredPerspetives){        
		ArrayList<IPerspectiveDescriptor> ignorePerspectiveList = new ArrayList<>();
		for (IPerspectiveDescriptor perspectiveDescriptor : registeredPerspetives) {
			if(!ELTPerspectives.contains(perspectiveDescriptor.getId())) {
				ignorePerspectiveList.add(perspectiveDescriptor);
			}
		}
		return ignorePerspectiveList;
	}
}
