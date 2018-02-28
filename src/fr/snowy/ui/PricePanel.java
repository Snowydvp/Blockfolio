package fr.snowy.ui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import fr.snowy.model.Market;
import fr.snowy.model.Price;

public class PricePanel extends JPanel {

    private Market market;
    private JTable priceTable;
    private PricesModel pricesModel;

    public PricePanel(Market market) {
	this.market = market;
	this.pricesModel = new PricesModel(market);
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
	Market market;
	
	public PricesModel(Market market) {
		this.market = market;
	}

	@Override
	public int getRowCount() {
		return market.getPrices().size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Price currentPrice = this.market.getPrices().get(rowIndex);
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