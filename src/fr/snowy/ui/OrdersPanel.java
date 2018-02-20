package fr.snowy.ui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.snowy.model.Order;
import fr.snowy.model.Wallet;

public class OrdersPanel extends JPanel {
    
    private Wallet wallet;
    
    public OrdersPanel(Wallet wallet) {
	
	this.wallet = wallet;
	
	setLayout(new GridLayout(0, 4));
    }
    
    public void update()
    {
	
	removeAll();
	
	JLabel instructionLbl, curr1Lbl, typeLbl, priceLbl;  
	
	for (Order order : wallet.getOrders())
	{
	    instructionLbl = new JLabel(order.getInstruction());
	    curr1Lbl = new JLabel(order.getCurrencyTo().toString());
	    priceLbl = new JLabel(String.format("%.3f", order.getPrice()) + ' ' + order.getCurrencyFrom().getUnit());
	    typeLbl = new JLabel(order.getType());
	    
	    add(instructionLbl);
	    System.out.println(instructionLbl.getText());
	    add(curr1Lbl);
	    add(priceLbl);
	    add(typeLbl);
	}
	
    }

}
