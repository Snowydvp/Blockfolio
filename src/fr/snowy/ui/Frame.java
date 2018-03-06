package fr.snowy.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import fr.snowy.Controller;
import fr.snowy.model.Wallet;

public class Frame extends JFrame implements ActionListener, ChangeListener {

	private ActionPanel actionPanel;
	private JTabbedPane tabbedPane;
	private BalancePanel balancePanel;
	private OrdersPanel ordersPanel;
	private PricePanel pricePanel;
	private Controller controller;
	private Wallet wallet;

	public Frame(Controller controller, Wallet wallet) {
		this.controller = controller;
		this.actionPanel = new ActionPanel(this);
		this.tabbedPane = new JTabbedPane();
		this.balancePanel = new BalancePanel(wallet);
		this.ordersPanel = new OrdersPanel(wallet);
		this.pricePanel = new PricePanel();

		add(tabbedPane);
		tabbedPane.addTab("Balance", balancePanel);
		tabbedPane.addTab("Orders", ordersPanel);
		tabbedPane.addTab("Prices", pricePanel);
		tabbedPane.addChangeListener(this);
		add(actionPanel, BorderLayout.SOUTH);

		this.add(actionPanel, BorderLayout.SOUTH);

		setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component component = this.tabbedPane.getSelectedComponent();
		if (component.equals(this.balancePanel))
		{
			controller.updateBalance();
			actionPanel.updateTime(wallet.getLastBalanceTimestamp());
		}
		else if (component.equals(this.ordersPanel))
		{
			controller.updateOrders();
			actionPanel.updateTime(wallet.getLastOrderTimestamp());
		}
		else if (component.equals(this.pricePanel))
		{
			controller.updatePrices();
//			actionPanel.updateTime(market.getLastRefreshedTimestamp());
		}
		repaint();
	}

	public BalancePanel getBalancePanel() {
		return balancePanel;
	}

	public OrdersPanel getOrdersPanel() {
		return ordersPanel;
	}

	@Override
	public void stateChanged(ChangeEvent e) {

		Component component = this.tabbedPane.getSelectedComponent();
		if (component.equals(this.balancePanel))
			actionPanel.updateTime(wallet.getLastBalanceTimestamp());
		else if (component.equals(this.ordersPanel))
			actionPanel.updateTime(wallet.getLastOrderTimestamp());
//		else if (component.equals(this.pricePanel))
//			actionPanel.updateTime(market.getLastRefreshedTimestamp());

	}

}

class ActionPanel extends JPanel {

	private JFrame frame;
	private JButton updateButton;
	private ImageIcon refreshIcon;
	private JLabel lastUpdatedTimestamp;

	public ActionPanel(Frame frame) {
		this.frame = frame;
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.refreshIcon = new ImageIcon(
				new ImageIcon("resources/refresh.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT),
				"Actualiser");
		this.updateButton = new JButton(refreshIcon);
		this.lastUpdatedTimestamp = new JLabel();

		this.updateButton.addActionListener(frame);
		add(lastUpdatedTimestamp);
		add(updateButton);
	}

	
	public void updateTime(long time)
	{
		String timelabel;
		if (time == 0)
			timelabel = null;
		else
			timelabel = new SimpleDateFormat("MM/dd/yy HH:mm:ss").format(new Date(time));
		this.lastUpdatedTimestamp.setText(timelabel);
	}

}