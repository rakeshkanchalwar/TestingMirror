package com.bitwise.app.graph.command;

import java.util.List;

import org.eclipse.gef.commands.Command;

import com.bitwise.app.common.component.config.PortSpecification;
import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwise.app.graph.processor.DynamicClassProcessor;

public class LinkReconnectCommand extends Command {

	private Link link;

	private Component newSource, newTarget;
	protected String sourceTerminal, targetTerminal;
	protected String oldSourceTerminal;
	protected Component oldSource;
	protected Component oldTarget;

	private String componentName;

	public LinkReconnectCommand(Link link) {
		if (link == null) {
			throw new IllegalArgumentException();
		}
		this.link = link;
		this.oldSource = link.getSource();
		this.oldTarget = link.getTarget();
	}

	public boolean canExecute() {
		List<PortSpecification> portspecification;
		if (newSource != null)
			if (newSource.equals(oldTarget)) {
				return false;
			}

		// Out Port
		componentName = DynamicClassProcessor.INSTANCE.getClazzName(newSource
				.getClass());
		portspecification = XMLConfigUtil.INSTANCE.getComponent(componentName)
				.getPort().getPortSpecification();

		for (PortSpecification p : portspecification) {
			String portName = p.getTypeOfPort() + p.getSequenceOfPort();
			if (portName.equals(sourceTerminal)) {
				if (p.isAllowMultipleLinks()
						|| !newSource.hasOutputPort(sourceTerminal)) {

				} else{
					return false;
				}
			}
		}
		return true;
	}

	public void execute() {
		if (newSource != null) {
			link.reconnect(newSource,sourceTerminal);
			/*link.setSource(newSource);
			link.setSourceTerminal(sourceTerminal);
			link.attachSource();
			newSource.addOutputPort(sourceTerminal);*/

			// delete link
			if (oldSource == null && oldTarget == null) {
				link.detachSource();
				link.detachTarget();
				link.getSource().removeOutputPort(link.getSourceTerminal());
				link.setSource(null);
			}
		}

	}

	public void setNewSource(Component linkSource) {
		if (linkSource == null) {
			throw new IllegalArgumentException();
		}
		newSource = linkSource;
		oldSource.disconnectOutput(link);
		oldSource.removeOutputPort(link.getSourceTerminal());
		
	}

	public void setNewTarget(Component linkTarget) {
		if (linkTarget == null) {
			throw new IllegalArgumentException();
		}
		newTarget = linkTarget;
		oldTarget.disconnectInput(link);
		
	}

	public void setSourceTerminal(String newSourceTerminal) {
		oldSourceTerminal=sourceTerminal;
		sourceTerminal = newSourceTerminal;
	}
	
	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public void undo(){
		
		link.reconnect(oldSource, oldSourceTerminal);
		/*newSource=link.getSource();
		sourceTerminal=link.getSourceTerminal();
		
		link.detachSource();
		
		link.setSource(oldSource);
		link.setSourceTerminal(oldSourceTerminal);
		link.attachSource();*/
		
	}
}
