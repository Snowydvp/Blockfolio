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

public class Frame extends JFrame implements ActionListener {

	private ActionPanel actionPanel;
	private JTabbedPane tabbedPane;
	private JPanel panel2;
	private WalletPanel walletPanel;
	private Controller controller;

	public Frame(Controller controller) {
		this.controller = controller;
		this.actionPanel = new ActionPanel(this);
		this.tabbedPane = new JTabbedPane();
		this.walletPanel = new WalletPanel(this);
		this.panel2 = new JPanel();

		add(tabbedPane);
		tabbedPane.addTab("Wallet", walletPanel);
		tabbedPane.addTab("Orders", panel2);
		add(actionPanel, BorderLayout.SOUTH);

		setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (tabbedPane.getSelectedIndex()) {
		case 0: // Wallet
			controller.refreshWallet();
			break;

		default:
			controller.refreshWallet();
			break;
		}
	}

	public WalletPanel getWalletPanel() {
		return this.walletPanel;
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