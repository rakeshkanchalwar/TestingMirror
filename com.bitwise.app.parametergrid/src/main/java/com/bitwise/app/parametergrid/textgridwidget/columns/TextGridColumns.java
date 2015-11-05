package com.bitwise.app.parametergrid.textgridwidget.columns;

import java.util.LinkedHashMap;
import java.util.Map;

public class TextGridColumns {
	int numberOfColumn=0;
	private Map<Integer,ColumnData> textGridColumns;
	
	public TextGridColumns(){
		textGridColumns = new LinkedHashMap<>();
	}
	
	public void addColumn(ColumnData columnLayoutData){
		textGridColumns.put(numberOfColumn, columnLayoutData);
		numberOfColumn++;
	}

	public int getNumberOfColumn() {
		return numberOfColumn;
	}

	public Map<Integer, ColumnData> getTextGridColumns() {
		return textGridColumns;
	}
	
	
}
