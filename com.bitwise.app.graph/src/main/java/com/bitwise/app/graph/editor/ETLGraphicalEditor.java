package com.bitwise.app.graph.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.ui.IEditorInput;

public class ETLGraphicalEditor extends GraphicalEditorWithFlyoutPalette{
	
	public static final String ID = "com.bitwise.app.graph.etlgraphicaleditor";
	
	public ETLGraphicalEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}
	
	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);
		if (getEditorInput() instanceof ETLGraphicalEditorInput) {
			setPartName(getEditorInput().getName());
		}
		//setPartName("Components Graph");
	}
	
	@Override
	protected PaletteRoot getPaletteRoot() {
		return null;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
	}
	
	

}
