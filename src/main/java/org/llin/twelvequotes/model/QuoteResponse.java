package org.llin.twelvequotes.model;

import java.util.ArrayList;
import java.util.List;

public class QuoteResponse {
	private String errorMsg;
	private String ticker;
	private List<QuoteDetailResponse> list = new ArrayList<>();
	private String jsonText;
	

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public List<QuoteDetailResponse> getList() {
		return list;
	}

	public void setList(List<QuoteDetailResponse> list) {
		this.list = list;
	}

	public String getJsonText() {
		return jsonText;
	}

	public void setJsonText(String jsonText) {
		this.jsonText = jsonText;
	}

	@Override
	public String toString() {
		return "QuoteResponse [errorMsg=" + errorMsg + ", ticker=" + ticker + ", list=" + list + ", jsonText="
				+ jsonText + "]";
	}

}
