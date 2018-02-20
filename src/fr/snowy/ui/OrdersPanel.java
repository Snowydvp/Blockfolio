package fr.snowy.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import fr.snowy.model.Order;
import fr.snowy.model.Wallet;

public class OrdersPanel extends JPanel {
    
    private Wallet wallet;
    private JTable ordersTable;
    
    public OrdersPanel(Wallet wallet) {
	
	this.wallet = wallet;
	
	setLayout(new BorderLayout());
    }
    
    public void update()
    {
	Order currenctOrder;
	Object[][] data = new Object[wallet.getOrders().size()][5];
	
	if (ordersTable != null)
	    remove(ordersTable);
	
	
	for (int i = 0; i < wallet.getOrders().size(); i ++)
	{
	    currenctOrder = wallet.getOrders().get(i);
	    data[i][0] = currenctOrder.getCurrencyTo();
	    data[i][1] = currenctOrder.getInstruction();
	    data[i][2] = currenctOrder.getPrice();
	    data[i][3] = currenctOrder.getQuantity();
	    data[i][4] = currenctOrder.getCost();
	}
	
	this.ordersTable = new JTable(data, new String[] {"Currency", "Order", "Price", "Volume", "Cost"} );
	add(ordersTable.getTableHeader(), BorderLayout.NORTH);
	add(this.ordersTable);
    }

}
