package fr.snowy;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.kraken.KrakenExchange;
import org.knowm.xchange.service.marketdata.MarketDataService;

public class TestController {

	public static void main(String[] args) {
		Exchange kraken = ExchangeFactory.INSTANCE.createExchange(KrakenExchange.class.getName());
		
		MarketDataService market = kraken.getMarketDataService();
		
		Ticker ticker;
		try {
			ticker = market.getTicker(CurrencyPair.BTC_EUR);
			System.out.println(ticker);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}
