package org.llin.twelvequotes.service;

import java.util.ArrayList;
import java.util.List;

import org.llin.twelvequotes.model.SingleQuote;
import org.llin.twelvequotes.respository.SingleQuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingleQuoteService<T extends SingleQuote> {

	private List<SingleQuote> list = new ArrayList<>();
	
	@Autowired
	private SingleQuoteRepository sqr;
	
	public List<SingleQuote> findAll() {	
		list = list.size() == 0 ? (List<SingleQuote>) sqr.findAll() : list;
		return list;
	}
	
	public long count() {
		return sqr.count();
	}
	
	public void saveAll(List<SingleQuote> list) {
		sqr.saveAll(list);		
	}
	
}
