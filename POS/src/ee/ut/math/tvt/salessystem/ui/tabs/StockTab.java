package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class StockTab implements ActionListener, MouseListener {

    private JButton addItem;
    private JTextField newID;
    private JTextField newName;
    private JTextField newDescription;
    private JTextField newPrice;
    private JTextField newQuantity;

    private SalesSystemModel model;

    public StockTab(SalesSystemModel model) {
        this.model = model;
    }

    // warehouse stock tab - consists of a menu and a table
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

        panel.add(drawStockMenuPane(), gc);

        gc.weighty = 1.0;
        gc.fill = GridBagConstraints.BOTH;
        panel.add(drawStockMainPane(), gc);
        return panel;
    }

    // warehouse menu
    private Component drawStockMenuPane() {
        JPanel panel = new JPanel();

        GridBagConstraints gc = new GridBagConstraints();
        GridBagLayout gb = new GridBagLayout();

        panel.setLayout(gb);

        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.weightx = 0;

        addItem = new JButton("Add");
        newID = new JTextField("ID");
        newName = new JTextField("Name");
        newDescription = new JTextField("Description");
        newPrice = new JTextField("Price");
        newQuantity = new JTextField("Quantity");

        newID.setPreferredSize(new Dimension(50, 20));
        newName.setPreferredSize(new Dimension(120, 20));
        newDescription.setPreferredSize(new Dimension(120, 20));
        newPrice.setPreferredSize(new Dimension(100, 20));
        newQuantity.setPreferredSize(new Dimension(100, 20));

        newID.addMouseListener(this);
        newName.addMouseListener(this);
        newDescription.addMouseListener(this);
        newQuantity.addMouseListener(this);
        newPrice.addMouseListener(this);
        addItem.addMouseListener(this);

        addItem.addActionListener(this);

        gc.gridwidth = GridBagConstraints.RELATIVE;
        gc.weightx = 1.0;

        panel.add(newID);
        panel.add(newName);
        panel.add(newDescription);
        panel.add(newPrice);
        panel.add(newQuantity);
        panel.add(addItem, gc);

        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return panel;
    }


    // table of the wareshouse stock
    private Component drawStockMainPane() {
        JPanel panel = new JPanel();

        JTable table = new JTable(model.getWarehouseTableModel());

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

        panel.setBorder(BorderFactory.createTitledBorder("Warehouse status"));
        return panel;
    }


    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isLong(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            String ID = newID.getText();
            String Name = newName.getText();
            String Description = newDescription.getText();
            String Price = newPrice.getText();
            String Quantity = newQuantity.getText();


            if (isLong(ID) && isDouble(Price) && isInteger(Quantity) && !Name.equals("")) {

                model.getWarehouseTableModel().addItem(new StockItem(Long.parseLong(ID), Name, Description, Double.parseDouble(Price), Integer.parseInt(Quantity)));

            } else {
                JOptionPane.showMessageDialog(null, "Please check insert", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
        newID.setText("ID");
        newName.setText("Name");
        newDescription.setText("Description");
        newPrice.setText("Price");
        newQuantity.setText("Quantity");

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(newID)) {
            newID.setText("");
        } else if (e.getSource().equals(newName)) {
            newName.setText("");
        } else if (e.getSource().equals(newDescription)) {
            newDescription.setText("");
        } else if (e.getSource().equals(newPrice)) {
            newPrice.setText("");
        } else if (e.getSource().equals(newQuantity)) {
            newQuantity.setText("");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
