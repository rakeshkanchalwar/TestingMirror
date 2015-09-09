package com.bitwise.app.graph.editor;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.junit.Assert;
import org.junit.Test;

public class PaletteViewTest {
	 
	 
	
@Test
public void itShouldCreateComponentCategory()
{
//given
String componentCategoryName ="Input";
ETLGraphicalEditor etlGraphEditor=new ETLGraphicalEditor();
//when

PaletteDrawer p=etlGraphEditor.createPaletteContainer(componentCategoryName);
//
Assert.assertTrue(p.isVisible());
}

@Test
public void itShouldAddComponentCategoryToPalette()
{
//given
String componentCategoryName ="Input";
ETLGraphicalEditor etlGraphEditor=new ETLGraphicalEditor();
PaletteDrawer p=etlGraphEditor.createPaletteContainer(componentCategoryName);
PaletteRoot paletteRoot=new PaletteRoot();
//when
etlGraphEditor.addContainerToPalette(paletteRoot,p);
//
Assert.assertTrue(paletteRoot.getChildren().contains(p));
}
}
