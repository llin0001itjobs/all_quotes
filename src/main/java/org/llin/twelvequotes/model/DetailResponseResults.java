package org.llin.twelvequotes.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DetailResponseResults {

	@JsonProperty("v")
	private double volumeOfTrade;

	@JsonProperty("vw")
	private double volumeWeightedAveragePrice;

	@JsonProperty("o")
	private double openPrice;

	@JsonProperty("c")
	private double closePrice;

	@JsonProperty("h")
	private double highestPrice;

	@JsonProperty("l")
	private double lowestPrice;

	@JsonProperty("n")
	private int numberOfTransactions;

	/**
	 * Over-the-counter (OTC) or off-exchange trading or pink sheet trading is done
	 * directly between two parties, without the supervision of an exchange.[1] It
	 * is contrasted with exchange trading, which occurs via exchanges. A stock
	 * exchange has the benefit of facilitating liquidity, providing transparency,
	 * and maintaining the current market price. In an OTC trade, the price is not
	 * necessarily publicly disclosed.
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean otc;

	@JsonProperty("t")
	private long timeStampUnixMsec;

	public double getVolumeOfTrade() {
		return volumeOfTrade;
	}

	public void setVolumeOfTrade(double volumeOfTrade) {
		this.volumeOfTrade = volumeOfTrade;
	}

	public double getVolumeWeightedAveragePrice() {
		return volumeWeightedAveragePrice;
	}

	public void setVolumeWeightedAveragePrice(double volumeWeightedAveragePrice) {
		this.volumeWeightedAveragePrice = volumeWeightedAveragePrice;
	}

	public double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}

	public double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}

	public double getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(double highestPrice) {
		this.highestPrice = highestPrice;
	}

	public double getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(double lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public int getNumberOfTransactions() {
		return numberOfTransactions;
	}

	public void setNumberOfTransactions(int numberOfTransactions) {
		this.numberOfTransactions = numberOfTransactions;
	}

	public Boolean getOtc() {
		return otc;
	}

	public void setOtc(Boolean otc) {
		this.otc = otc;
	}

	public long getTimeStampUnixMsec() {
		return timeStampUnixMsec;
	}

	public void setTimeStampUnixMsec(long timeStampUnixMsec) {
		this.timeStampUnixMsec = timeStampUnixMsec;
	}

	@Override
	public String toString() {
		return "DetailResponseResults [volumeOfTrade=" + volumeOfTrade + ", volumeWeightedAveragePrice="
				+ volumeWeightedAveragePrice + ", openPrice=" + openPrice + ", closePrice=" + closePrice
				+ ", highestPrice=" + highestPrice + ", lowestPrice=" + lowestPrice + ", numberOfTransactions="
				+ numberOfTransactions + ", otc=" + otc + ", timeStampUnixMsec=" + timeStampUnixMsec + "]";
	}
}
