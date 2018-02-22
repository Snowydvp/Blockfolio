package fr.snowy.ui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import fr.snowy.model.Crypto;
import fr.snowy.model.Fiat;
import fr.snowy.model.Order;
import fr.snowy.model.Wallet;

public class OrdersPanel extends JPanel {

	private Wallet wallet;
	private JTable ordersTable;
	private OrdersModel ordersModel;

	public OrdersPanel(Wallet wallet) {
		//TODO add rentability
		this.ordersModel = new OrdersModel(wallet.getOrders());
		this.wallet = wallet;
		this.ordersTable = new JTable(ordersModel);

		setLayout(new BorderLayout());
		add(this.ordersTable.getTableHeader(), BorderLayout.NORTH);
		add(ordersTable);
	}

	public void update() {
		this.wallet.getOrders().add(new Order(Fiat.DEFAULT, Crypto.XBT, "BUY", "market", 0.2f, 0.01f));
	}

}

class OrdersModel extends AbstractTableModel {
	private ArrayList<Order> orders;

	public OrdersModel(ArrayList<Order> orders) {
		this.orders = orders;
	}

	@Override
	public int getRowCount() {
		return this.orders.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Order order = this.orders.get(rowIndex);
		Object object;
		switch (columnIndex) {
		case 0:
			object = order.getCurrencyTo();
			break;
		case 1:
			object = order.getInstruction();
			break;
		case 2:
			object = order.getQuantity();
			break;
		case 3:
			object = order.getPrice();
			break;
		case 4:
			object = order.getCost();
			break;
		default:
			object = null;
			break;
		}
		return object;
	}

	public String getColumnName(int col) {
		String name;
		switch (col) {
		case 0:
			name = "Currency";
			break;
		case 1:
			name = "Type";
			break;
		case 2:
			name = "Volume";
			break;
		case 3:
			name = "Price";
			break;
		case 4:
			name = "Cost (w/ fees)";
			break;

		default:
			name = null;
			break;
		}
		return name;
	}

}