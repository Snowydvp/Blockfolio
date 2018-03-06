package fr.snowy.ui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.knowm.xchange.currency.Currency;

import fr.snowy.model.Wallet;

public class BalancePanel extends JPanel {

    private Wallet wallet;
    private JTable balanceTable;
    private BalanceModel balanceModel;

    public BalancePanel(Wallet wallet) {
	this.wallet = wallet;
	this.balanceModel = new BalanceModel(wallet);
	this.balanceTable = new JTable(balanceModel);
	
	setLayout(new BorderLayout());
	add(this.balanceTable.getTableHeader(), BorderLayout.NORTH);
	add(balanceTable);
    }

    public void update() {
    }
}

class BalanceModel extends AbstractTableModel
{
	private Wallet wallet;
	
	public BalanceModel(Wallet wallet) {
		this.wallet = wallet;
	}

	@Override
	public int getRowCount() {
		return this.wallet.getBalance().size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Currency currentCurrency = (Currency) this.wallet.getBalance().keySet().toArray()[rowIndex];
		Object object;
		switch (columnIndex) {
		case 0:
			object = currentCurrency;
			break;

		case 1:
			object = this.wallet.getBalance().get(currentCurrency);
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
			name = "Balance";
			break;
		default:
			name = null;
			break;
		}
		return name;
	}
}