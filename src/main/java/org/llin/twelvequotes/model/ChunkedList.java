package org.llin.twelvequotes.model;


import java.util.ArrayList;
import java.util.List;

public class ChunkedList<T extends SingleQuote> {
	private List<ChunkedSubList<T>> list = new ArrayList<>();
	
	@SuppressWarnings("rawtypes")
	private List<ChunkedGroupList> groupOfLists = new ArrayList<>();

	private List<List<T>> listOfLists = new ArrayList<>();
	private int tabsPerLine;

	public ChunkedList(List<List<T>> listOfLists, int tabsPerLine) {
		this.listOfLists = listOfLists;
		this.tabsPerLine = tabsPerLine;
		process();
	}

	public List<List<T>> getListOfLists() {
		return listOfLists;
	}

	public List<ChunkedSubList<T>> getList() {
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List<ChunkedGroupList> getGroupOfLists() {
		return groupOfLists;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void process() {
		int i=0;
		for (List<T> l : listOfLists) {
			list.add((ChunkedSubList<T>) new ChunkedSubList<T>(l,i++));
		}

		List<ChunkedSubList<T>> groupList = new ArrayList<>();
		int j = 0;
		for (ChunkedSubList<T> csl : list) {
			if (j % tabsPerLine == 0 && j > 0) {				
				groupOfLists.add(new ChunkedGroupList(groupList));
				groupList = new ArrayList<>();
			}			
			groupList.add(csl);
			j++;
		}
		groupOfLists.add(new ChunkedGroupList(groupList));
	}
	
}
