package ee.ut.math.tvt.salessystem.ui.tabs;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */



public class HistoryTab implements MouseListener{
	
	private SalesSystemModel model;
	private SalesDomainController controller;
	Logger log;

	public HistoryTab(SalesDomainController controller,
			SalesSystemModel model) {
		this.controller = controller;
		this.model = model;
		log = Logger.getLogger(getClass());
	}

	public Component draw() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		panel.setLayout(gb);

		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.weightx = 1.0d;
		gc.weighty = 0d;
		gc.weighty = 1.0;
		gc.fill = GridBagConstraints.BOTH;
		panel.add(drawHistoryMainPane(), gc);
		return panel;
	}

	private Component drawHistoryMainPane() {
		JPanel panel = new JPanel();

		JTable table = new JTable(model.getHistoryTableModel());
		table.addMouseListener(this);
		JTableHeader header = table.getTableHeader();
		header.setReorderingAllowed(false);

		JScrollPane scrollPane = new JScrollPane(table);

		GridBagConstraints gc = new GridBagConstraints();
		GridBagLayout gb = new GridBagLayout();
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1.0;
		gc.weighty = 1.0;

		panel.setLayout(gb);
		panel.add(scrollPane, gc);

		panel.setBorder(BorderFactory.createTitledBorder("Sales history"));
		return panel;
	}

	private GridBagConstraints getBasketScrollPaneConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1.0;
		gc.weighty = 1.0;

		return gc;
	}

	/*@Override
	public void valueChanged(ListSelectionEvent e) {
		
		final JFrame jFrame = new JFrame("Sale info");
		jFrame.setLocation(560, 250);
		// shopping cart pane
		JPanel basketPane = new JPanel();
		basketPane.setLayout(new GridBagLayout());

		// Create the table, put it inside a scollPane,
		// and add the scrollPane to the basketPanel.
		JTable table = new JTable(model.getHistoryTableModel().getPurchaseInfoTableByRow(e.getFirstIndex()));
		JScrollPane scrollPane = new JScrollPane(table);

		basketPane.add(scrollPane, getBacketScrollPaneConstraints());

	}
	*/

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		log.error("mouse");
		JTable target = (JTable) e.getSource();
		int row = target.getSelectedRow();
		final JFrame jFrame = new JFrame("Sale info");
		jFrame.setLocation(560, 250);
		// shopping cart pane
		JPanel basketPane = new JPanel();
		basketPane.setLayout(new GridBagLayout());

		// Create the table, put it inside a scrollPane,
		// and add the scrollPane to the basketPanel.
		JTable table = new JTable(model.getHistoryTableModel().getPurchaseInfoTableByRow(row));
		JScrollPane scrollPane = new JScrollPane(table);

		basketPane.add(scrollPane, getBasketScrollPaneConstraints());

        jFrame.add(basketPane);

        jFrame.setSize(300, 400);
        jFrame.setVisible(true);

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}