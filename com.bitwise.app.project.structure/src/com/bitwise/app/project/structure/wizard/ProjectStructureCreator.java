package com.bitwise.app.project.structure.wizard;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.LibraryLocation;

import com.bitwise.app.project.structure.CustomMessages;
import com.bitwise.app.project.structure.natures.ProjectNature;

/**
 * Class to create the Custom Project Structure.
 *
 */
public class ProjectStructureCreator {
	private static Logger logger = Logger.getLogger(ProjectStructureCreator.class.getName());
	public static final ProjectStructureCreator INSTANCE = new ProjectStructureCreator();
	public static final String [] paths = {CustomMessages.ProjectSupport_SRC, CustomMessages.ProjectSupport_SCRIPTS, 
		CustomMessages.ProjectSupport_XML, CustomMessages.ProjectSupport_LIB};
	
	private ProjectStructureCreator(){}
	
	/**
	 * Creates the custom project structure at the specified location and name
	 * The Project will have below natures:<br>
	 * <b>Java</b> and Custom nature as per <b>ETL</b> project
	 * @param projectName Project name
	 * @param location Where should the project be saved
	 * @return
	 */
	public IProject createProject(String projectName, URI location){
		if(projectName == null || projectName.trim().length() <= 0)
			throw new InvalidProjectNameException();
		
		IProject project = createBaseProject(projectName, location);
		try {
			addNature(project);
            addToProjectStructure(project, paths);
            IJavaProject javaProject = JavaCore.create(project);
            
            IFolder binFolder = project.getFolder(CustomMessages.ProjectSupport_BIN);
            javaProject.setOutputLocation(binFolder.getFullPath(), null);
            
            List<IClasspathEntry> entries = addJavaLibrariesInClassPath();
            List<String> jarList = copyExternalLibToProjectLib(Platform.getInstallLocation().getURL().getPath()+"lib", project.getLocation() + "/lib");
            //add libs to project class path
            
              for (String string : jarList) {
            	org.eclipse.core.runtime.Path path = new org.eclipse.core.runtime.Path(string);
            	entries.add(JavaCore.newLibraryEntry(path, null, null));
			} 
            
            javaProject.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), null);
            	
            //set source folder entry in classpath
            javaProject.setRawClasspath(setSourceFolderInClassPath(project, javaProject), null);
		} catch (CoreException e) {
			logger.log(Level.ALL, "Failed to create Project with parameters as projectName : " + projectName + ", location : " +location);
			project = null;
		}
		return project;
	}

	/**
	 * Sets the <b>src</b> folder as the source folder in project
	 * @param project
	 * @param javaProject
	 * @return IClasspathEntry[]
	 * @throws JavaModelException
	 */
	private IClasspathEntry[] setSourceFolderInClassPath(IProject project,	IJavaProject javaProject) throws JavaModelException {
		IFolder sourceFolder = project.getFolder(CustomMessages.ProjectSupport_SRC); //$NON-NLS-1$
		IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(sourceFolder);
		IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
		IClasspathEntry[] newEntries = new IClasspathEntry[oldEntries.length + 1];
		System.arraycopy(oldEntries, 0, newEntries, 0, oldEntries.length);
		newEntries[oldEntries.length] = JavaCore.newSourceEntry(root.getPath());
		return newEntries;
	}

	/**
	 * Adds java libraries to the classpath
	 * @return List &lt; IClasspathEntry &gt;
	 */
	private List<IClasspathEntry> addJavaLibrariesInClassPath() {
		List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
		IVMInstall vmInstall = JavaRuntime.getDefaultVMInstall();
		LibraryLocation[] locations = JavaRuntime.getLibraryLocations(vmInstall);
		for (LibraryLocation element : locations) {
		 entries.add(JavaCore.newLibraryEntry(element.getSystemLibraryPath(), null, null));
		}
		return entries;
	}

	 /**
	  * Creates the specified folder if it does not exists
	 * @param folder
	 * @throws CoreException
	 */
	private void createFolder(IFolder folder) throws CoreException {
	        IContainer parent = folder.getParent();
	        if (parent instanceof IFolder) {
	            createFolder((IFolder) parent);
	        }
	        if (!folder.exists()) {
	            folder.create(false, true, null);
	        }
	    }
	 
	/**
     * Create a folder structure as specified in the parameters
     * @param newProject 
     * @param paths contains the names of the folder that need to be created.
     * @throws CoreException
     */
    private void addToProjectStructure(IProject newProject, String[] paths) throws CoreException {
        for (String path : paths) {
            IFolder etcFolders = newProject.getFolder(path);
            createFolder(etcFolders);
        }
    }
    
	private void addNature(IProject project) throws CoreException{
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

	/**
	 * Creates the base structure of the project under specified location and name
	 * @param projectName
	 * @param location
	 * @return
	 */
	private IProject createBaseProject(String projectName, URI location) {
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

	/*
	 * Copy External jar to project lib directory
	 * @param source path
	 * @param target path
	 * @return list of added files path
	 */
	private List<String> copyExternalLibToProjectLib(String source,String target){
		List<String> path = new ArrayList<String>();
		source = source.startsWith("/") ? source.substring(1) : source;
		 final Path sourceDir = Paths.get(source);
		final Path targetDir = Paths.get(target);
		try{
				Files.walkFileTree(sourceDir, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE,
			     new SimpleFileVisitor<Path>() {
			      
			        @Override
			        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			            Files.copy(file, targetDir.resolve(sourceDir.relativize(file)));
			            return FileVisitResult.CONTINUE;
			        }
		    });
				File directory = new File(target);

			    // get all the files from a directory
			    File[] fList = directory.listFiles();
			    for (File file : fList) { 
			        if (file.isFile()) {
			        	path.add(file.getAbsolutePath());
			        }
			    }
			return path;
		} catch (Exception e) {
			e.printStackTrace(); 	
		}
		
		return Collections.EMPTY_LIST;
	}
	
	public class InvalidProjectNameException extends RuntimeException{}
}
