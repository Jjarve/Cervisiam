package ee.ut.math.tvt.salessystem.domain.controller.impl;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.SingleSale;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {
	Logger log = Logger.getLogger(getClass());

	public void submitCurrentPurchase(List<SoldItem> goods, SingleSale sale)
			throws VerificationFailedException {
		// Let's assume we have checked and found out that the buyer is
		// underaged and
		// cannot buy chupa-chups
		// throw new VerificationFailedException("Underaged!");
		// XXX - Save purchase
		Session session = HibernateUtil.currentSession();
		
		Transaction transaction = session.beginTransaction();
		session.save(sale);
		transaction.commit();
		
		transaction = session.beginTransaction();
		for (int i = 0; i < goods.size(); i++) {
			session.saveOrUpdate(goods.get(i).getStockItem());
		}
		transaction.commit();
		transaction = session.beginTransaction();
		for (int i = 0; i < goods.size(); i++) {
			goods.get(i).setSaleId(sale);
			session.save(goods.get(i));
		}
		transaction.commit();
		transaction = session.beginTransaction();
		session.save(sale);
		log.debug("Stock updated in db - confirmed sale");
		

	}

	public void cancelCurrentPurchase() throws VerificationFailedException {
		// XXX - Cancel current purchase
	}

	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}

	public List<StockItem> loadWarehouseState() {

		Query query = HibernateUtil.currentSession().createQuery(
				"from StockItem");
		List<StockItem> dataset = query.list();
		return dataset;
	}

	public List<SingleSale> loadSalesHistory() {
		Query query = HibernateUtil.currentSession().createQuery(
				"from SingleSale");
		List<SingleSale> dataset = query.list();
		return dataset;
	}

	public void endSession() {
		HibernateUtil.closeSession();
	}
	

	@Override
	public void submitStockUpdate(StockItem addedItem) {
		Session session = HibernateUtil.currentSession();
		Transaction transaction = session.beginTransaction();
		session.merge(addedItem);
		transaction.commit();

		log.debug("Stock updated in db - adding stock");

	}
}
