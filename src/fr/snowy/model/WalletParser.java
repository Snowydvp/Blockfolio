package fr.snowy.model;

import java.util.Arrays;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class WalletParser {

	private String[] error;
	protected HashMap<String, String> result;

	@JsonCreator
	public WalletParser(@JsonProperty("error") String[] error, @JsonProperty("result") HashMap result) {
		this.error = error;
		this.result = result;
	}

	public void setError(String[] error) {
		this.error = error;
	}

	public String[] getError() {
		return error;
	}

	public HashMap getResult() {
		return result;
	}

	public void setResult(HashMap result) {
		this.result = result;
	}

	public abstract Wallet convertToKraken();

	public String toString() {
		return "Wallet [error=" + Arrays.toString(error) + ", result=" + result + "]";
	}

}
