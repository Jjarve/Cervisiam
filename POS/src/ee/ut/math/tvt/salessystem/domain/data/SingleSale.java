package ee.ut.math.tvt.salessystem.domain.data;

import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;


public class SingleSale implements DisplayableItem {


    private long id;


    private String date;


    private Double sum;
    private PurchaseInfoTableModel purchase;

    public SingleSale(long id, String currentDate, double totalPrice,
                      PurchaseInfoTableModel purchase) {
        this.id = id;
        this.date = currentDate;
        this.sum = totalPrice;
        this.purchase = purchase;
    }

    public String getDate() {
        return date;
    }

    public Double getSum() {
        return sum;
    }

    public PurchaseInfoTableModel getPurchase() {
        return purchase;
    }

    @Override
    public Long getId() {
        return id;
    }
}
