package com.bitwise.app.graph.components.commands;

import java.util.HashMap;
import java.util.List;

import org.eclipse.draw2d.graph.Node;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.bitwise.app.graph.components.model.Component;


public class RenameAction extends SelectionAction{
	
	public static final String ID = "rename";
	
	public String getId() {
		return ID;
	}
	public RenameAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
	}
	protected void init() {
		setText("Rename...");
		setToolTipText("Rename");
		
//		ImageDescriptor icon = AbstractUIPlugin.imageDescriptorFromPlugin("TutoGEF",
//				"icons/rename-icon.png");
//		if (icon != null)
//			setImageDescriptor(icon);
//		setEnabled(false);
	}
	@Override
	protected boolean calculateEnabled() {
		// On laisse les EditPolicy decider si la commande est disponible ou non
		Command cmd = createRenameCommand("");
		if (cmd == null)
			return false;
		return true;
	}
	public Command createRenameCommand(String name) {
		Request renameReq = new Request("rename");
		Command cmd=null;
		HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newName", name);
		renameReq.setExtendedData(reqData);
		if(!getSelectedObjects().isEmpty()){
			EditPart object = (EditPart)getSelectedObjects().get(0);
			cmd = object.getCommand(renameReq);
		}
		return cmd;
	}
	public void run() {
		
		Component node = getSelectedNode();
		RenameWizard wizard = new RenameWizard(node.getComponentName());
		
		WizardDialog dialog = new WizardDialog(getWorkbenchPart().getSite().getShell(),
		wizard);
		dialog.create();
		dialog.getShell().setSize(500, 300);
		dialog.setTitle("Rename wizard");
		dialog.setMessage("Rename");
		if (dialog.open() == WizardDialog.OK) {
		String name = wizard.getRenameValue();
		execute(createRenameCommand(name));
		}
	}
	// Helper
	private Component getSelectedNode() {
		List objects = getSelectedObjects();
		if (objects.isEmpty())
			return null;
		if (!(objects.get(0) instanceof EditPart))
			return null;
		EditPart part = (EditPart)objects.get(0);
		return (Component)part.getModel();
	}
}
