package com.bitwise.app.graph.command;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.slf4j.Logger;

import com.bitwise.app.common.component.config.PortSpecification;
import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwise.app.graph.processor.DynamicClassProcessor;

public class LinkReconnectCommand extends Command {
	private static final Logger logger = LogFactory.INSTANCE.getLogger(LinkReconnectCommand.class);
	
	private Link link;

	private Component newSource, newTarget;
	private String newSourceTerminal, newTargetTerminal;
	private String oldSourceTerminal;
	private Component oldSource;
	private Component oldTarget;

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
			if (portName.equals(newSourceTerminal)) {
				if (p.isAllowMultipleLinks()
						|| !newSource.hasOutputPort(newSourceTerminal)) {

				} else{
					return false;
				}
			}
		}
		return true;
	}

	public void execute() {
		if (newSource != null) {
			link.setSource(newSource);
			link.setSourceTerminal(newSourceTerminal);
			newSource.addOutputPort(newSourceTerminal);
			newSource.connectOutput(link);
			link.attachSource();
			oldSource.removeOutputPort(link.getSourceTerminal());
			oldSource.disconnectOutput(link);
		}

	}

	public void setNewSource(Component linkSource) {
		if (linkSource == null) {
			throw new IllegalArgumentException();
		}
		newSource = linkSource;
		newSourceTerminal=null;
		oldSource.disconnectOutput(link);
		oldSource.removeOutputPort(link.getSourceTerminal());
	}

	/*public void setNewTarget(Component linkTarget) {
		if (linkTarget == null) {
			throw new IllegalArgumentException();
		}
		newTarget = linkTarget;
		oldTarget.disconnectInput(link);
		
	}*/

	public void setNewSourceTerminal(String newSourceTerminal) {
		this.newSourceTerminal = newSourceTerminal;
	}
	
	public void setOldSource(Link w) {
		oldSource = w.getSource();
		oldSourceTerminal = w.getSourceTerminal();
	}
	
	@Override
	public void redo() {
		execute();
	}
	
	
	@Override
	public void undo(){
	
		newSource=link.getSource();
		logger.debug("New source is :{}", newSource.getProperties().get("name"));
		newSourceTerminal=link.getSourceTerminal();
		newSource.disconnectOutput(link);
		newSource.removeOutputPort(link.getSourceTerminal());
		link.detachSource();
		
		link.setSource(oldSource);
		logger.debug("Old source is :{}", oldSource.getProperties().get("name"));
		link.setSourceTerminal(oldSourceTerminal);
		link.attachSource();
		
	}
}
