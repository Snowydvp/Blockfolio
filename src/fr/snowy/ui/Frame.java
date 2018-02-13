package fr.snowy.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import fr.snowy.Controller;

public class Frame extends JFrame implements ActionListener{
	
	private ActionPanel actionPanel;
	private JTabbedPane tabbedPane;
	private JPanel panel2;
	private WalletPanel walletPanel;
	private Controller controller;
	
	public Frame(Controller controller)
	{
		this.actionPanel = new ActionPanel(this);
		this.tabbedPane = new JTabbedPane();
		this.walletPanel = new WalletPanel();
		this.panel2 = new JPanel();
		
		add(tabbedPane);
		tabbedPane.addTab("Wallet", walletPanel);
		tabbedPane.addTab("Orders", panel2);
		add(actionPanel, BorderLayout.SOUTH);
		
		setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public WalletPanel getWalletPanel()
	{
		return this.walletPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (tabbedPane.getSelectedIndex()) {
		case 0:	//Wallet
			controller.refreshWallet();
			break;

		default:
			break;
		}
		
	}

	
}

class ActionPanel extends JPanel {
	
	private JFrame frame;
	private JButton updateButton;
	private JProgressBar progressBar;
	
	public ActionPanel(Frame frame)
	{
		this.frame = frame;
		this.updateButton = new JButton("Actualiser");
		this.progressBar = new JProgressBar();
		
		this.updateButton.addActionListener(frame);
		this.progressBar.setIndeterminate(true);
		add(updateButton);
		add(progressBar);
	}
}