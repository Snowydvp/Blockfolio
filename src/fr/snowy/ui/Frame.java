package fr.snowy.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import fr.snowy.Controller;
import fr.snowy.model.Market;
import fr.snowy.model.Wallet;

public class Frame extends JFrame implements ActionListener {

    private ActionPanel actionPanel;
    private JTabbedPane tabbedPane;
    private BalancePanel balancePanel;
    private OrdersPanel ordersPanel;
    private PricePanel pricePanel;
    private Controller controller;
    private Market market;

    public Frame(Controller controller, Wallet wallet, Market market) {
	this.controller = controller;
	this.market = market;
	this.actionPanel = new ActionPanel(this);
	this.tabbedPane = new JTabbedPane();
	this.balancePanel = new BalancePanel(wallet);
	this.ordersPanel = new OrdersPanel(wallet);
	this.pricePanel = new PricePanel(market);

	add(tabbedPane);
	tabbedPane.addTab("Wallet", balancePanel);
	tabbedPane.addTab("Orders", ordersPanel);
	tabbedPane.addTab("Prices", pricePanel);
	add(actionPanel, BorderLayout.SOUTH);
	
	this.add(actionPanel, BorderLayout.SOUTH);
	
	setSize(500, 500);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	switch (this.tabbedPane.getSelectedIndex()) {
    	case 0:
    		controller.updateBalance();
    		this.balancePanel.update();
    		break;
    	case 1:
    		controller.updateOrders();
    		this.ordersPanel.update();
    		break;
    	case 2:
    		controller.updatePrices();
    		this.pricePanel.update();
    		break;
    	default:
    		break;
    	}
    	repaint();
    }

    public BalancePanel getBalancePanel() {
        return balancePanel;
    }

    public OrdersPanel getOrdersPanel() {
        return ordersPanel;
    }

}

class ActionPanel extends JPanel {

    private JFrame frame;
    private JButton updateButton;
    private ImageIcon refreshIcon;

    public ActionPanel(Frame frame) {
	this.frame = frame;
	setLayout(new FlowLayout(FlowLayout.RIGHT));
	this.refreshIcon =  new ImageIcon(new ImageIcon("resources/refresh.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT), "Actualiser");
	this.updateButton = new JButton(refreshIcon);

	this.updateButton.addActionListener(frame);
	add(updateButton);
    }

}