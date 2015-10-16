package com.bitwise.app.graph.factory;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.internal.ui.palette.editparts.SliderPaletteEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.PaletteEditPartFactory;
import org.eclipse.swt.graphics.Color;


// TODO: Auto-generated Javadoc
/**
 * A factory for creating CustomPaletteEditPart objects.
 */
public class CustomPaletteEditPartFactory extends PaletteEditPartFactory {

    private Color _entryBackgroundColor;
    
	/**
	 * Instantiates a new custom palette edit part factory.
	 * 
	 * @param entryForegroundColor
	 *            the entry foreground color
	 * @param entryBackgroundColor
	 *            the entry background color
	 */
    public CustomPaletteEditPartFactory(Color entryForegroundColor,
                                      Color entryBackgroundColor) {
        _entryBackgroundColor = entryBackgroundColor;
    }
    @Override
    protected EditPart createMainPaletteEditPart(EditPart parentEditPart, Object model) {
        return new SliderPaletteEditPart((PaletteRoot)model) {
            @Override
            public IFigure createFigure() {
                IFigure fig = super.createFigure();
                fig.setBackgroundColor(_entryBackgroundColor);
                return fig;
            }
        };
    }
   

}