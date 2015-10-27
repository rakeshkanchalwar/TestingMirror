package com.bitwise.app.graph.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.graph.Messages;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;

/**
 *	Handler class to create the graphical editor.
 */
public class RunJobHandler extends AbstractHandler {

	private Logger logger=LogFactory.INSTANCE.getLogger(RunJobHandler.class);

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		 try{  
	    	MessageConsole myConsole = findConsole("GradleConsole"); 
	    	final MessageConsoleStream  out = myConsole.newMessageStream();
	    	IEditorPart iEditorPart= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
	    	String XML_PATH=""; 
	    	if(iEditorPart!=null)  
	    	{
	    	XML_PATH= iEditorPart.getEditorInput().getToolTipText().replace("job","xml"); 
			String projectName = XML_PATH.split("/", 2)[0];
	    	IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);    	
	    	System.out.println("---"+Messages.GRADLE_RUN+Messages.XMLPATH);
	    	String[] command = {"cmd","/c",Messages.GRADLE_RUN+" "+Messages.XMLPATH+"="+XML_PATH.split("/", 2)[1]+" "+Messages.PARAM_FILE+"=param/test.properties"};
			ProcessBuilder pb = new ProcessBuilder(command);
	    	pb.directory(new File(project.getLocation().toOSString()));    
	    	pb.redirectErrorStream(true);   
	    	Process process = pb.start();     	    	
	    	final InputStream stream = process.getInputStream();	  
	    	new Thread(new Runnable() {   
		    public void run() { 
		      BufferedReader reader = null;
		      try {
		        reader = new BufferedReader(new InputStreamReader(stream));
		        String line = null;
		        while ((line = reader.readLine()) != null) {
		        	out.println(line);
		        }
		      } catch (Exception e) { 
		        // TODO
		      } finally {
		        if (reader != null) {
		          try {
		            reader.close();
		          } catch (IOException e) {
		            // ignore
		          }
		        }
		      }
		    }
		  }).start();
	    	}
	    	 else{
				 WidgetUtility.errorMessage("No Graph Opened."); 
			 }
		 } catch (Exception ex) {
			   ex.printStackTrace();
			   }
		 
		
		return null; 
	}
	 private MessageConsole findConsole(String name) {
	      ConsolePlugin plugin = ConsolePlugin.getDefault();
	      IConsoleManager conMan = plugin.getConsoleManager();
	      IConsole[] existing = conMan.getConsoles();
	      for (int i = 0; i < existing.length; i++) 
	      {
	         if (name.equals(existing[i].getName()))
	            return (MessageConsole) existing[i];
	      }
	         //no console found, so create a new one
	      MessageConsole myConsole = new MessageConsole(name, null);
	      conMan.addConsoles(new IConsole[]{myConsole}); 
	      return myConsole;
	   }
}
