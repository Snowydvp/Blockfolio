package fr.snowy.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import fr.snowy.model.Market;
import fr.snowy.model.Price;

public class PricePanel extends JPanel {

    private Market market;
    private JTable priceTable;
    private JButton refreshButton;

    public PricePanel(Market market) {
	this.market = market;
	//TODO use TableModel

	try {
	    this.refreshButton = new JButton(
		    new ImageIcon(new File("resources/refresh.png").toURI().toURL(), "refresh"));
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	}

	setLayout(new BorderLayout());
	
    }

    public void update() {

	if(priceTable != null)
	    remove(priceTable);
	
	Object[][] data = new Object[market.getPrices().size()][2];
	Price currentPrice;

	for (int i = 0; i < market.getPrices().size(); i++) {
	    currentPrice = market.getPrices().get(i);
	    data[i][0] = currentPrice.getCurrencyTo();
	    data[i][1] = String.format("%5.2f", currentPrice.getValue());
	}
	this.priceTable = new JTable(data, new String[] {"Currency", "Value"} );
	add(priceTable.getTableHeader(), BorderLayout.NORTH);
	add(this.priceTable);
    }

}
