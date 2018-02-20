package fr.snowy.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	setSize(500, 500);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	controller.update();
    }

    public void update() {
	//TODO threads
	this.balancePanel.update();
	this.ordersPanel.update();
	this.pricePanel.update();
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

    public ActionPanel(Frame frame) {
	this.frame = frame;
	this.updateButton = new JButton("Actualiser");

	this.updateButton.addActionListener(frame);
	add(updateButton);
    }

}