package fr.snowy.ui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.snowy.model.Market;
import fr.snowy.model.Price;

public class PricePanel extends JPanel {
	
	private Market market;

	public PricePanel(Market market) {
		this.market = market;
		
		setLayout(new GridLayout(0, 2));
	}
	
	public void update()
	{
		JLabel currToLbl, valueLbl;
		
		removeAll();
		for(Price price : market.getPrices())
		{
			currToLbl = new JLabel(price.getCurrencyTo().toString());
			valueLbl = new JLabel(String.format("%.3f", price.getValue()));
			
			add(currToLbl);
			add(valueLbl);
		}
	}
    

}
