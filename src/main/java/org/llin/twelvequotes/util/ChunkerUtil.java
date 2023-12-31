package org.llin.twelvequotes.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.llin.twelvequotes.model.AllQuotes;
import org.llin.twelvequotes.model.ChunkedList;
import org.llin.twelvequotes.model.SingleQuote;

public class ChunkerUtil<T extends SingleQuote> {

	// Helper method to divide the list into smaller chunks	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> void chunkList(AllQuotes allQuotes, int chunkSize, int tabsPerLine) {
		List<T> list = (List<T>) allQuotes.getList();
		List<List<T>> chunkedList = IntStream.range(0, list.size()).boxed()
				.collect(Collectors.groupingBy(index -> index / chunkSize)).values().stream()
				.map(indices -> indices.stream().map(list::get).collect(Collectors.toList()))
				.collect(Collectors.toList());
			
		allQuotes.setChunkedList(new ChunkedList(chunkedList, tabsPerLine));
	}
}
