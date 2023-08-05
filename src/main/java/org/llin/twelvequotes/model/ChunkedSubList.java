package org.llin.twelvequotes.model;

import java.util.ArrayList;
import java.util.List;

public class ChunkedSubList<T extends SingleQuote> {
	private List<T> list = new ArrayList<>();	
	private String tabLabel;	
	private	int index;
	
	public ChunkedSubList(List<T> list, int index) {
		this.list = list;
		this.setIndex(index);
		process();
	}
	
	public List<T> getList() {
		return list;
	}
	
	public String getTabLabel() {
		return tabLabel;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	private void process() {
		tabLabel = list.get(0).getSymbol() + "-" + list.get(list.size() - 1).getSymbol(); 
	}


	
}
