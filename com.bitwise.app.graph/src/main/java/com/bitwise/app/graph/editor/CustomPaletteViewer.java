package com.bitwise.app.graph.editor;

import java.io.IOException;
import java.util.ArrayList;
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
import org.xml.sax.SAXException;

import com.bitwise.app.common.component.config.CategoryType;
import com.bitwise.app.common.component.config.Component;
import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.common.util.XMLConfigUtil;

import com.bitwise.app.graph.processor.DynamicClassProcessor;

public class CustomPaletteViewer extends PaletteViewer {
	private List<PaletteContainer> paletteContainer;
	private Logger logger = LogFactory.INSTANCE.getLogger(XMLConfigUtil.class);

	public Control creatToolControl(Composite parent,final PaletteRoot paletteRoot,final ETLGraphicalEditor editor) {
		 final Map<String, PaletteDrawer> categoryPaletteConatiner = new HashMap<>();
		Composite container = createCompositeForSearchTextBox(parent);
		final Text text = createSearchTextBox(container);

		try {
			 final List<Component> componentsConfig = XMLConfigUtil.INSTANCE.getComponentConfig();
			

			text.addVerifyListener(new VerifyListener() {

				@Override
				public void verifyText(VerifyEvent e) {

					CombinedTemplateCreationEntry removeDuplicateComponent = null;
					ArrayList<Component> matchingComponents = new ArrayList<>();
					paletteRoot.getChildren().clear();
					String currentText = text.getText().trim();
					createPaletteContainers(paletteRoot, categoryPaletteConatiner, editor);

					String searchedString = (currentText.substring(0, e.start) + e.text + currentText.substring(e.end))
							.trim().toUpperCase();
					try {

						for (Component componentConfig : componentsConfig) {

							String componentName = componentConfig.getName().toUpperCase();
							if (componentName.contains(searchedString)) {
								System.out.println("componentName inside IF: " + componentName);
								CombinedTemplateCreationEntry component = getComponentToAddInContainer(
										editor, componentConfig);
								removeDuplicateComponent = component;
								categoryPaletteConatiner.get(componentConfig.getCategory().name()).add(component);
								matchingComponents.add(componentConfig);
								
								
							}
							if (searchedString.trim().isEmpty() || searchedString.equals("")) {
								categoryPaletteConatiner.get(componentConfig.getCategory().name()).remove(
										removeDuplicateComponent);
								CombinedTemplateCreationEntry component = getComponentToAddInContainer(
										editor, componentConfig);
								categoryPaletteConatiner.get(componentConfig.getCategory().name()).add(component);
								showClosedPaletteContainersWhenSearchTextBoxIsEmpty(paletteRoot.getChildren());
							}

						}
						
						showRequiredPaletteContainers(paletteRoot, categoryPaletteConatiner, matchingComponents);
						
						
					} catch (Exception exception) {
						logger.error(exception.getMessage());
					}

				}
			});

		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
		return container;
	}

	private void showClosedPaletteContainersWhenSearchTextBoxIsEmpty(List list) {
		for (int i = 0; i < list.size(); i++) {
			PaletteDrawer paletteDrawer = (PaletteDrawer) list.get(i);
			paletteDrawer.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
			paletteDrawer.setVisible(true);
		}
	}

	private void showRequiredPaletteContainers(PaletteRoot paletteRoot,
			Map<String, PaletteDrawer> categoryPaletteConatiner, ArrayList<Component> matchingComponents) {
		List<PaletteContainer> children = paletteRoot.getChildren();
		for (PaletteContainer paletteContainer : children) {
			paletteContainer.setVisible(false);

		}
		for (Component component : matchingComponents) {
			for (int i = 0; i < children.size(); i++) {
				if (children.get(i).equals(categoryPaletteConatiner.get(component.getCategory().name())))
					children.get(i).setVisible(true);

			}
		}

	}

	private void createPaletteContainers(PaletteRoot paletteRoot,
			Map<String, PaletteDrawer> categoryPaletteConatiner, ETLGraphicalEditor eLEtlGraphicalEditor) {
		for (CategoryType category : CategoryType.values()) {
			PaletteDrawer paletteDrawer = eLEtlGraphicalEditor.createPaletteContainer(category.name());
			paletteDrawer.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
			eLEtlGraphicalEditor.addContainerToPalette(paletteRoot, paletteDrawer);
			categoryPaletteConatiner.put(category.name(), paletteDrawer);
		}
	}

	private CombinedTemplateCreationEntry getComponentToAddInContainer(ETLGraphicalEditor eLEtlGraphicalEditor,
			Component componentConfig) {
		Class<?> clazz = DynamicClassProcessor.INSTANCE.createClass(componentConfig);

		CombinedTemplateCreationEntry component = new CombinedTemplateCreationEntry(componentConfig.getName(),
				"Custom components", clazz, new SimpleFactory(clazz),
				ImageDescriptor.createFromURL(eLEtlGraphicalEditor.prepareIconPathURL(componentConfig
						.getPaletteIconPath())), ImageDescriptor.createFromURL(eLEtlGraphicalEditor
						.prepareIconPathURL(componentConfig.getPaletteIconPath())));
		return component;
	}

	private Composite createCompositeForSearchTextBox(Composite parent) {
		Composite container = new Composite(parent, SWT.BORDER);
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);
		return container;
	}

	private Text createSearchTextBox(Composite container) {
		 Text text = new Text(container, SWT.NONE);
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

	private void callHookRootFigure() {
		// use getRootFigure depreciated method when alternate of this method is got then it will be replaced
		if (getFigureCanvas() == null)
			return;
		if (getRootFigure() instanceof Viewport) {
			
			getFigureCanvas().setViewport((Viewport) getRootFigure());
		} else {
			
			getFigureCanvas().setContents(getRootFigure());
		}
	}
}
