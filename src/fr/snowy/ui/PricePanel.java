package fr.snowy.ui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class PricePanel extends JPanel {

    private JTable priceTable;
    private PricesModel pricesModel;

    public PricePanel() {
	this.pricesModel = new PricesModel();
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
	
	public PricesModel() {
	}

	@Override
	public int getRowCount() {
		return 0;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
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