package fr.snowy.ui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import fr.snowy.model.Crypto;
import fr.snowy.model.Fiat;
import fr.snowy.model.Market;
import fr.snowy.model.Order;
import fr.snowy.model.Price;

public class PricePanel extends JPanel {

    private Market market;
    private JTable priceTable;
    private PricesModel pricesModel;

    public PricePanel(Market market) {
	this.market = market;
	this.pricesModel = new PricesModel(market.getPrices());
	this.priceTable = new JTable(pricesModel);
	
	setLayout(new BorderLayout());
	add(this.priceTable.getTableHeader(), BorderLayout.NORTH);
	add(priceTable);
    }

    public void update() {
    }
    
}

class PricesModel extends AbstractTableModel
{
	private ArrayList<Price> prices; 
	
	public PricesModel(ArrayList<Price> prices) {
		this.prices = prices;
	}

	@Override
	public int getRowCount() {
		return this.prices.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Price currentPrice = this.prices.get(rowIndex);
		Object object;
		switch (columnIndex) {
		case 0:
			object = currentPrice.getCurrencyTo();
			break;

		case 1:
			object = currentPrice.getValue();
			break;
		default:
			object = null;
			break;
		}
		return object;
	}
	
	public String getColumnName(int col)
	{
		String name;
		switch (col) {
		case 0:
			name = "Currency";
			break;

		case 1:
			name = "Price";
			break;
		default:
			name = null;
			break;
		}
		return name;
	}
}