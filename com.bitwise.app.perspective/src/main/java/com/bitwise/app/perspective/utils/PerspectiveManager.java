package com.bitwise.app.perspective.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.PlatformUI;

import com.bitwise.app.perspective.config.ETLToolPerspetiveList;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Aug 25, 2015
 *
 */

public class PerspectiveManager {
	/**
	 * Removes the unwanted perspectives from your RCP application
	 */
	public static void removeUnWantedPerspectives() {
		IPerspectiveRegistry perspectiveRegistry = PlatformUI.getWorkbench().getPerspectiveRegistry();
		IPerspectiveDescriptor[] perspectiveDescriptors = getAllRegisteredPerspectiveDescriptors();
		ArrayList<IPerspectiveDescriptor> ignorePerspectiveList = getIgnorePerspectiveList(perspectiveDescriptors);
		removePerspetives(perspectiveRegistry, ignorePerspectiveList);		
	}

	private static void removePerspetives(IPerspectiveRegistry perspectiveRegistry, List removePerspectiveDesc) {
		// If the list is non-empty then remove all such perspectives from the IExtensionChangeHandler
		if(perspectiveRegistry instanceof IExtensionChangeHandler && !removePerspectiveDesc.isEmpty()) {
			IExtensionChangeHandler extChgHandler = (IExtensionChangeHandler) perspectiveRegistry;
			extChgHandler.removeExtension(null, removePerspectiveDesc.toArray());
		}
	}

	private static IPerspectiveDescriptor[] getAllRegisteredPerspectiveDescriptors(){
		return PlatformUI.getWorkbench().getPerspectiveRegistry().getPerspectives();
	}

	private static ArrayList<IPerspectiveDescriptor> getAddPerspectiveList(IPerspectiveDescriptor[] registeredPerspetives){        
		ArrayList<IPerspectiveDescriptor> addPerspectiveList = new ArrayList<>();
		// Add the perspective descriptors with the matching perspective ids to the list
		for (IPerspectiveDescriptor perspectiveDescriptor : registeredPerspetives) {
			if(ETLToolPerspetiveList.requiredPerspetives.contains(perspectiveDescriptor.getId())) {
				addPerspectiveList.add(perspectiveDescriptor);
			}
		}

		return addPerspectiveList;
	}
	
	private static ArrayList<IPerspectiveDescriptor> getIgnorePerspectiveList(IPerspectiveDescriptor[] registeredPerspetives){        
		ArrayList<IPerspectiveDescriptor> ignorePerspectiveList = new ArrayList<>();
		// Add the perspective descriptors with the matching perspective ids to the list
		for (IPerspectiveDescriptor perspectiveDescriptor : registeredPerspetives) {
			if(!ETLToolPerspetiveList.requiredPerspetives.contains(perspectiveDescriptor.getId())) {
				ignorePerspectiveList.add(perspectiveDescriptor);
			}
		}

		return ignorePerspectiveList;
	}
}
