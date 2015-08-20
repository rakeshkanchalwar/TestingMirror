package com.bitwise.app.project.structure.wizard;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.LibraryLocation;

import com.bitwise.app.project.structure.natures.ProjectNature;

public class ProjectSupport {

	public static IProject createProject(String projectName, URI location){
		Assert.isNotNull(projectName);
		Assert.isTrue(projectName.trim().length() > 0);
		
		IProject project = createBaseProject(projectName, location);
		try {
			addNature(project);
			String[] paths = { "src", "scripts", "xml", "lib" }; //$NON-NLS-1$ //$NON-NLS-2$
            addToProjectStructure(project, paths);
            IJavaProject javaProject = JavaCore.create(project);
            IFolder binFolder = project.getFolder("bin");
            javaProject.setOutputLocation(binFolder.getFullPath(), null);
            
            List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
            IVMInstall vmInstall = JavaRuntime.getDefaultVMInstall();
            LibraryLocation[] locations = JavaRuntime.getLibraryLocations(vmInstall);
            for (LibraryLocation element : locations) {
             entries.add(JavaCore.newLibraryEntry(element.getSystemLibraryPath(), null, null));
            }
            //add libs to project class path
            javaProject.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), null);
            
            IFolder sourceFolder = project.getFolder("src");
            IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(sourceFolder);
            IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
            IClasspathEntry[] newEntries = new IClasspathEntry[oldEntries.length + 1];
            System.arraycopy(oldEntries, 0, newEntries, 0, oldEntries.length);
            newEntries[oldEntries.length] = JavaCore.newSourceEntry(root.getPath());
            javaProject.setRawClasspath(newEntries, null);
		} catch (CoreException e) {
			e.printStackTrace();
			project = null;
		}
		return project;
	}

	 private static void createFolder(IFolder folder) throws CoreException {
	        IContainer parent = folder.getParent();
	        if (parent instanceof IFolder) {
	            createFolder((IFolder) parent);
	        }
	        if (!folder.exists()) {
	            folder.create(false, true, null);
	        }
	    }
	 
	/**
     * Create a folder structure with a parent root, overlay, and a few child
     * folders.
     *
     * @param newProject
     * @param paths
     * @throws CoreException
     */
    private static void addToProjectStructure(IProject newProject, String[] paths) throws CoreException {
        for (String path : paths) {
            IFolder etcFolders = newProject.getFolder(path);
            createFolder(etcFolders);
        }
    }
    
	private static void addNature(IProject project) throws CoreException{
		if(!project.hasNature(ProjectNature.NATURE_ID)){
			IProjectDescription description = project.getDescription();
			String[] prevNatures = description.getNatureIds();
			String[] newNatures = new String[prevNatures.length + 2];
			System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
			newNatures[prevNatures.length] = ProjectNature.NATURE_ID;
			newNatures[prevNatures.length + 1] = JavaCore.NATURE_ID;
			
			// validate the natures
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IStatus status = workspace.validateNatureSet(newNatures);
			
			// only apply new nature, if the status is ok
			if (status.getCode() == IStatus.OK) {
				description.setNatureIds(newNatures);
				project.setDescription(description, null);
			}
		}
	}

	private static IProject createBaseProject(String projectName, URI location) {
		IProject newProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		
		if(!newProject.exists()){
            IProjectDescription desc = newProject.getWorkspace().newProjectDescription(newProject.getName());
            if (location != null && ResourcesPlugin.getWorkspace().getRoot().getLocationURI().equals(location)) {
            	desc.setLocationURI(null);
            }
            else{
            	desc.setLocationURI(location);
            }
            
            try {
                newProject.create(desc, null);
                if (!newProject.isOpen()) {
                    newProject.open(null);
                }
            } catch (CoreException e) {
                e.printStackTrace();
            }
 		}
		return newProject;
	}
}
