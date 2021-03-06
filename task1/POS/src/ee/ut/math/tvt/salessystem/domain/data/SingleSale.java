package ee.ut.math.tvt.salessystem.domain.data;

import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @Transient
    private PurchaseInfoTableModel purchase;
    
    @OneToMany(mappedBy="saleId")
    private List<SoldItem> soldItems;

    public SingleSale(long id, String currentDate, double totalPrice,
                      PurchaseInfoTableModel purchase) {
        this.id = id;
        this.date = currentDate;
        this.sum = totalPrice;
        this.purchase = purchase;
    }
    public SingleSale(long id, String currentDate, double totalPrice, 
    		Set<SoldItem> soldItems) {
    	this.id = id;
    	this.date = currentDate;
    	this.sum = totalPrice;
    	this.purchase = new PurchaseInfoTableModel(soldItems);
    }
    public SingleSale(){
    	
    }



    public String getDate() {
        return date;
    }

    public Double getSum() {
        return sum;
    }

    public PurchaseInfoTableModel getPurchase() {
        return new PurchaseInfoTableModel((Set<SoldItem>) soldItems);
    }

    @Override
    public Long getId() {
        return id;
    }


}
