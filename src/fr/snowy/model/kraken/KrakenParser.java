package fr.snowy.model.kraken;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class KrakenParser {

    private String[] error;
    private HashMap result;
    private HashMap<String, HashMap> closed;
    private HashMap<String, HashMap> open;

    @JsonCreator
	public KrakenParser(@JsonProperty("error") String[] error, @JsonProperty("result") HashMap result) {
		this.result = result;
		this.error = error;
		
		if (result.containsKey("closed"))
		{
			this.closed = (HashMap<String, HashMap>) result.get("closed");
			
		}
		else if (result.containsKey("open")) {
		    this.open = (HashMap<String, HashMap>) result.get("open");
		}
	}

    public HashMap<String, HashMap> getOpen() {
        return open;
    }

    public String[] getError() {
	return error;
    }

    public HashMap getResult() {
	return result;
    }

    @Override
    public String toString() {
	return "OrdersKrakenParser [result=" + result + "]";
    }

    public HashMap<String, HashMap> getClosed() {
	return closed;
    }

}
