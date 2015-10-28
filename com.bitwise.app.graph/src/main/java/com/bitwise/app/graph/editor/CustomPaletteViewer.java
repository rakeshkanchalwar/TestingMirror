package com.bitwise.app.graph.editor;
import java.util.HashMap;
import java.util.List;

import java.util.Map;



import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.Viewport;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;

import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import org.eclipse.swt.widgets.Text;
import org.slf4j.Logger;

import com.bitwise.app.common.component.config.CategoryType;
import com.bitwise.app.common.component.config.Component;
import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.common.util.XMLConfigUtil;

import com.bitwise.app.graph.processor.DynamicClassProcessor;
public class CustomPaletteViewer extends PaletteViewer{
	private List<PaletteContainer> paletteContainer;
	private Logger logger=LogFactory.INSTANCE.getLogger(XMLConfigUtil.class);
	


	public Control creatToolControl(Composite parent, final PaletteRoot paletteRoot) {
		final Map<String, PaletteDrawer> categoryPaletteConatiner = new HashMap<>();
		final ETLGraphicalEditor eLEtlGraphicalEditor=new ETLGraphicalEditor();
		Composite container = createCompositeForSearchTextBox(parent);
		final Text text = createSearchTextBox(container);
		text.addVerifyListener(new VerifyListener() {

			@Override
			public void verifyText(VerifyEvent e) {

				CombinedTemplateCreationEntry removeDuplicateComponent=null;
				paletteRoot.getChildren().clear();
				String searchedString=text.getText().trim().toUpperCase();
				createPaletteContainers(paletteRoot, categoryPaletteConatiner,eLEtlGraphicalEditor);

				try {
					List<Component> 	componentsConfig = XMLConfigUtil.INSTANCE
							.getComponentConfig();
					for (Component componentConfig : componentsConfig) {

						if(componentConfig.getName().toUpperCase().equals(searchedString)||componentConfig.getName().toUpperCase().startsWith(searchedString))
						{
							CombinedTemplateCreationEntry component = getComponentToAddInContainer(eLEtlGraphicalEditor, componentConfig);
							removeDuplicateComponent=component;
							categoryPaletteConatiner.get(componentConfig.getCategory().name()).add(component);
							showRequiredPaletteContainers(paletteRoot,categoryPaletteConatiner, componentConfig);
						}
						if(text.getText().trim().isEmpty()||text.getText().trim().length()==1)
						{
							categoryPaletteConatiner.get(componentConfig.getCategory().name()).remove(removeDuplicateComponent);
							CombinedTemplateCreationEntry component = getComponentToAddInContainer(eLEtlGraphicalEditor, componentConfig);
							categoryPaletteConatiner.get(componentConfig.getCategory().name()).add(component);
							paletteContainer=paletteRoot.getChildren();
							showClosedPaletteContainersWhenSearchTextBoxIsEmpty();
						}

					}
				} catch (Exception exception) {
						logger.error(exception.getMessage());
				} 
			}

			private void showClosedPaletteContainersWhenSearchTextBoxIsEmpty() {
				for(int i=0;i<paletteContainer.size();i++)
				{
					PaletteDrawer paletteDrawer=(PaletteDrawer) paletteContainer.get(i);
					paletteDrawer.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
					paletteDrawer.setVisible(true);
				}
			}

			private void showRequiredPaletteContainers(final PaletteRoot paletteRoot,final Map<String, PaletteDrawer> categoryPaletteConatiner,
					Component componentConfig) {
				paletteContainer=paletteRoot.getChildren();
				for(int i=0;i<paletteContainer.size();i++)
				{
					if(paletteContainer.get(i).equals(categoryPaletteConatiner.get(componentConfig.getCategory().name())))
						paletteContainer.get(i).setVisible(true);
					else
						paletteContainer.get(i).setVisible(false);
				}
			}

			private void createPaletteContainers(
					final PaletteRoot paletteRoot,
					final Map<String, PaletteDrawer> categoryPaletteConatiner,
					final ETLGraphicalEditor eLEtlGraphicalEditor) {
				for (CategoryType category : CategoryType.values()) {	
					PaletteDrawer p = eLEtlGraphicalEditor.createPaletteContainer(category.name());
					p.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
					eLEtlGraphicalEditor.addContainerToPalette(paletteRoot, p);
					categoryPaletteConatiner.put(category.name(), p);
				}
			}

			private CombinedTemplateCreationEntry getComponentToAddInContainer(
					final ETLGraphicalEditor eLEtlGraphicalEditor,
					Component componentConfig) {
				Class<?> clazz = DynamicClassProcessor.INSTANCE
						.createClass(componentConfig);

				CombinedTemplateCreationEntry component = new CombinedTemplateCreationEntry(
						componentConfig.getName(), "Custom components", clazz,
						new SimpleFactory(clazz),
						ImageDescriptor
						.createFromURL(eLEtlGraphicalEditor.prepareIconPathURL(componentConfig
								.getPaletteIconPath())),
								ImageDescriptor
								.createFromURL(eLEtlGraphicalEditor.prepareIconPathURL(componentConfig
										.getPaletteIconPath())));
				return component;
			}
		});


		return container;
	}


	private Composite createCompositeForSearchTextBox(Composite parent) {
		Composite container = new Composite(parent, SWT.BORDER);
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);
		return container;
	}


	private Text createSearchTextBox(Composite container) {
		final Text text = new Text(container, SWT.NONE);
		GridData textGridData = new GridData(SWT.FILL, SWT.CENTER, true, true);
		text.setLayoutData(textGridData);
		text.setToolTipText("Enter component name");
		return text;
	}
	public LightweightSystem getLightweightSys() {
		return getLightweightSystem();
	}
	public void setFigureCanvas(FigureCanvas canvas) {
		setControl(canvas);
		callHookRootFigure();
	}
	
	
	private void callHookRootFigure(){

		if (getFigureCanvas() == null)
			return;
		if (getRootFigure() instanceof Viewport)
			getFigureCanvas().setViewport((Viewport) getRootFigure());
		else
			getFigureCanvas().setContents(getRootFigure());
	}
}
