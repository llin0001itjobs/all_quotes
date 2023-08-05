package org.llin.twelvequotes.model;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class ChunkedGroupList<T extends SingleQuote> {
	private List<ChunkedSubList> list = new ArrayList<>();
	
	public ChunkedGroupList(List<ChunkedSubList> groupList) {
		this.list = groupList;
	}
	
	public List<ChunkedSubList> getList() {
		return list;
	}


}
