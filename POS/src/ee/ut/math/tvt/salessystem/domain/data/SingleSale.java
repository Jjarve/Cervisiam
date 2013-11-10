package ee.ut.math.tvt.salessystem.domain.data;

import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

import javax.persistence.*;

@Entity
@Table(name = "SINGLESALE")
public class SingleSale implements DisplayableItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date")
    private String date;

    @Column(name = "pricesum")
    private Double sum;

    @Embedded
    private PurchaseInfoTableModel purchase;

    public SingleSale(long id, String currentDate, double totalPrice,
                      PurchaseInfoTableModel purchase) {
        this.id = id;
        this.date = currentDate;
        this.sum = totalPrice;
        this.purchase = purchase;
    }

    public SingleSale() {
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
