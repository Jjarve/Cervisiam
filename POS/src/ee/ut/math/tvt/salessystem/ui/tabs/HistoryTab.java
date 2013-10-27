package ee.ut.math.tvt.salessystem.ui.tabs;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {

    private Object rowData[][] = {};
    private Object columnNames[] = {"Date", "Time", "Order price"};

    // TODO - implement!

    public HistoryTab() {
    }

    public Component draw() {

        JPanel panel = new JPanel();
        JTable table = new JTable(rowData, columnNames);


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


        // TODO - Sales history tabel
        return panel;
    }
}