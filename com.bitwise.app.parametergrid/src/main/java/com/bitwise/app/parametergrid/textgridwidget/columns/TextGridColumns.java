package com.bitwise.app.parametergrid.textgridwidget.columns;

import java.util.LinkedHashMap;
import java.util.Map;

public class TextGridColumns {
	int numberOfColumn=0;
	private Map<Integer,TextGridColumnDataLayout> textGridColumns;
	
	public TextGridColumns(){
		textGridColumns = new LinkedHashMap<>();
	}
	
	public void addColumn(TextGridColumnDataLayout columnLayoutData){
		textGridColumns.put(numberOfColumn, columnLayoutData);
		numberOfColumn++;
	}

	public int getNumberOfColumn() {
		return numberOfColumn;
	}

	public Map<Integer, TextGridColumnDataLayout> getTextGridColumns() {
		return textGridColumns;
	}
	
	public void resetColumnData(int columnNumber,TextGridColumnDataLayout columnData){
		if(textGridColumns.containsKey(columnNumber)){
			textGridColumns.put(columnNumber, columnData);
		}
	}
	
}
