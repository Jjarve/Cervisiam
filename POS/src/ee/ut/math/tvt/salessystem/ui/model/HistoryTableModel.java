package ee.ut.math.tvt.salessystem.ui.model;

import ee.ut.math.tvt.salessystem.domain.data.SingleSale;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryTableModel extends SalesSystemTableModel<SingleSale> {
    Logger log;

    public HistoryTableModel() {
        super(new String[]{"id", "Date", "Sum"});
        log = Logger.getLogger(getClass());
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public void addSale(final PurchaseInfoTableModel purchase,
                        final double totalPrice) {
<<<<<<< HEAD
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
=======
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
>>>>>>> b9d4a41708915cbcc8e8819b3668808bc17290f6
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        SingleSale sale = new SingleSale(this.getRowCount() + 1, currentDate,
                totalPrice, purchase);
        rows.add(sale);
        fireTableDataChanged();
    }

    public PurchaseInfoTableModel getPurchaseInfoTableByRow(int row) {
        try {
            return rows.get(row).getPurchase();
        } catch (IllegalArgumentException e) {
            log.error("Row index out of range");
            return null;
        }
    }

    @Override
    protected Object getColumnValue(SingleSale sale, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return sale.getId();
            case 1:
                return sale.getDate();
            case 2:
                return sale.getSum();
        }
        throw new IllegalArgumentException("Column index out of range");
    }

}
