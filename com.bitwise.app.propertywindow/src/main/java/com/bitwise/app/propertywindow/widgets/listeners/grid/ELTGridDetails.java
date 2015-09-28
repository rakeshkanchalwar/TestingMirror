package com.bitwise.app.propertywindow.widgets.listeners.grid;

import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Label;

import com.bitwise.app.propertywindow.widgets.utility.GridWidgetCommonBuilder;

public class ELTGridDetails {

	private List grids;
	private TableViewer tableViewer;
	private Label label;
	private GridWidgetCommonBuilder gridWidgetCommonBuilder;
	
	
	
	public ELTGridDetails(List grids, TableViewer tableViewer,
			Label label,GridWidgetCommonBuilder gridWidgetCommonBuilder) {
		super();
		this.grids = grids;
		this.tableViewer = tableViewer;
		this.label = label;
		this.gridWidgetCommonBuilder=gridWidgetCommonBuilder;
	}
	public List getGrids() {
		return grids;
	}

	public void setGrids(List grids) {
		this.grids = grids;
	}

	public TableViewer getTableViewer() {
		return tableViewer;
	}

	public void setTableViewer(TableViewer tableViewer) {
		this.tableViewer = tableViewer;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public GridWidgetCommonBuilder getGridWidgetCommonBuilder() {
		return gridWidgetCommonBuilder;
	}

	public void setGridWidgetCommonBuilder(
			GridWidgetCommonBuilder gridWidgetCommonBuilder) {
		this.gridWidgetCommonBuilder = gridWidgetCommonBuilder;
	}

	

}
