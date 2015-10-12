package com.bitwise.app.graph.factory;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.internal.ui.palette.editparts.SliderPaletteEditPart;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.PaletteEditPartFactory;
import org.eclipse.swt.graphics.Color;


public class CustomPaletteEditPartFactory extends PaletteEditPartFactory {

    private Color _entryBackgroundColor;
    private Color _entryForegroundColor;
    
    public CustomPaletteEditPartFactory(Color entryForegroundColor,
                                      Color entryBackgroundColor) {
        _entryBackgroundColor = entryBackgroundColor;
        _entryForegroundColor = entryForegroundColor;
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