package fr.snowy.ui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import fr.snowy.Controller;
import fr.snowy.model.Wallet;

public class Frame extends JFrame implements ActionListener {

	private ActionPanel actionPanel;
	private JTabbedPane tabbedPane;
	private JPanel panel2;
	private BalancePanel balancePanel;
	private OrdersPanel ordersPanel;
	private Controller controller;

	public Frame(Controller controller, Wallet wallet) {
		this.controller = controller;
		this.actionPanel = new ActionPanel(this);
		this.tabbedPane = new JTabbedPane();
		this.balancePanel = new BalancePanel(this, wallet);
		this.panel2 = new JPanel();

		add(tabbedPane);
		tabbedPane.addTab("Wallet", balancePanel);
		tabbedPane.addTab("Orders", panel2);
		add(actionPanel, BorderLayout.SOUTH);

		setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.update();
	}

	public void update()
	{
		this.balancePanel.update();
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