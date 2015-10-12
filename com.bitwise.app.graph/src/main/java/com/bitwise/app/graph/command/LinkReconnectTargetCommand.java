package com.bitwise.app.graph.command;

import java.util.List;

import org.eclipse.gef.commands.Command;

import com.bitwise.app.common.component.config.PortSpecification;
import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwise.app.graph.processor.DynamicClassProcessor;

public class LinkReconnectTargetCommand extends Command{
	
	Link link;
	private Component oldTarget;
	private Component newTarget;
	private String oldTargetTerminal;
	private String newTargetTerminal;
	private Component oldSource;
	private String componentName;
	
	public LinkReconnectTargetCommand(Link link){
		if (link == null) {
			throw new IllegalArgumentException();
		}
		this.link=link;
		this.oldTarget = link.getTarget();
	}
	
	public boolean canExecute(){
		List<PortSpecification> portspecification;
		if(newTarget!=null){
			if(newTarget.equals(oldSource)){
				return false;
			}
			componentName = DynamicClassProcessor.INSTANCE
					.getClazzName(newTarget.getClass());

			portspecification=XMLConfigUtil.INSTANCE.getComponent(componentName).getPort().getPortSpecification();
			for (PortSpecification p:portspecification)
			{
				String portName=p.getTypeOfPort()+p.getSequenceOfPort();
				if(portName.equals(newTargetTerminal)){
					if(p.isAllowMultipleLinks() ||
							!newTarget.hasInputPort(newTargetTerminal)){
						
					}else{
						return false;
					}
				}
			}
		}

		return true;
	}
	
	public void execute(){
		if(newTarget != null){
			link.setTarget(newTarget);
			link.setTargetTerminal(newTargetTerminal);
			newTarget.addInputPort(newTargetTerminal);
			newTarget.connectInput(link);
			link.attachTarget();
			
			oldTarget.removeInputPort(link.getTargetTerminal());
			oldTarget.disconnectInput(link);
			
		}
	}
	
	public void setNewTarget(Component linkTarget) {
		if (linkTarget == null) {
			throw new IllegalArgumentException();
		}
		newTarget = linkTarget;
		newTargetTerminal=null;
		oldTarget.disconnectInput(link);
		oldTarget.removeInputPort(link.getTargetTerminal());
	}
	
	public void setNewTargetTerminal(String newTargetTerminal){
		this.newTargetTerminal=newTargetTerminal;
	}
	
	public void setOldTarget(Link w){
		oldTarget=w.getTarget();
		oldTargetTerminal=w.getTargetTerminal();
	}
	
	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public void undo(){
		
		newTarget=link.getTarget();
		newTargetTerminal=link.getTargetTerminal();
		newTarget.disconnectInput(link);
		newTarget.removeInputPort(link.getTargetTerminal());
		link.detachTarget();
		
		link.setTarget(oldTarget);
		link.setTargetTerminal(oldTargetTerminal);
		link.attachTarget();
		
	}

}
