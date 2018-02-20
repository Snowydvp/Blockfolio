package fr.snowy.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import fr.snowy.model.Currency;
import fr.snowy.model.Price;
import fr.snowy.model.Wallet;

public class BalancePanel extends JPanel {

    private Wallet wallet;
    private JTable balanceTable;

    public BalancePanel(Wallet wallet) {
	this.wallet = wallet;

	setLayout(new BorderLayout());
    }

    public void update() {
	Object[][] data = new Object[wallet.getBalance().size()][2];
	Currency currentCurrency;
	Float currentValue;
	
	if (balanceTable != null)
	    remove(balanceTable);

	for (int i = 0; i < wallet.getBalance().size(); i++) {
	    currentCurrency = (Currency) wallet.getBalance().keySet().toArray()[i];
	    data[i][0] = currentCurrency.toString();
	    data[i][1] = String.format("%5.2f", wallet.getBalance().get(currentCurrency) + ' ' + currentCurrency.getUnit());
	}
	
	this.balanceTable = new JTable(data, new String[] {"Currency", "Balance"} );
	add(balanceTable.getTableHeader(), BorderLayout.NORTH);
	add(this.balanceTable);
    }
}
