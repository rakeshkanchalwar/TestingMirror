package com.bitwise.app.parametergrid.textgridwidget.columns;

import java.util.LinkedHashMap;
import java.util.Map;

public class TextGridRowLayout {
	int numberOfColumn=0;
	private Map<Integer,TextGridColumnLayout> textGridColumns;
	
	public TextGridRowLayout(){
		textGridColumns = new LinkedHashMap<>();
	}
	
	public void addColumn(TextGridColumnLayout textGridColumnLayout){
		textGridColumns.put(numberOfColumn, textGridColumnLayout);
		numberOfColumn++;
	}

	public int getNumberOfColumn() {
		return numberOfColumn;
	}

	public Map<Integer, TextGridColumnLayout> getTextGridColumns() {
		return textGridColumns;
	}
	
	public void resetColumnData(int columnNumber,TextGridColumnLayout columnData){
		if(textGridColumns.containsKey(columnNumber)){
			textGridColumns.put(columnNumber, columnData);
		}
	}
	
}
