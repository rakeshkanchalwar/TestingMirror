package com.bitwise.app.common.textgrid;

import java.util.ArrayList;
import java.util.List;

public class TextGridData {
	private int numberOfColumns;
	private List<String> row;
	private List<String> columns;
	
	public TextGridData(int numberOfColumns){
		this.numberOfColumns = numberOfColumns;
		row = new ArrayList<>();
		columns = new ArrayList<>();
	}
	
	public void setData(String data,int rowIndex,int columnIndex){
		
		if(columnIndex < numberOfColumns){
			
		}
	}
}
