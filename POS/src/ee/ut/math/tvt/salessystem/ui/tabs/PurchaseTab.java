package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.SingleSale;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.HistoryTableModel;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;
import ee.ut.math.tvt.salessystem.ui.panels.PurchaseItemPanel;

import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "Point-of-sale" in the menu).
 */
public class PurchaseTab {

    private static final Logger log = Logger.getLogger(PurchaseTab.class);

    private final SalesDomainController domainController;

    private JButton newPurchase;

    private JButton submitPurchase;

    private JButton cancelPurchase;

    private PurchaseItemPanel purchasePane;

    private SalesSystemModel model;




    public PurchaseTab(SalesDomainController controller,
                       SalesSystemModel model) {
        this.domainController = controller;
        this.model = model;
    }


    /**
     * The purchase tab. Consists of the purchase menu, current purchase dialog and
     * shopping cart table.
     */
    public Component draw() {
        JPanel panel = new JPanel();

        // Layout
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setLayout(new GridBagLayout());

        // Add the purchase menu
        panel.add(getPurchaseMenuPane(), getConstraintsForPurchaseMenu());

        // Add the main purchase-panel
        purchasePane = new PurchaseItemPanel(model);
        panel.add(purchasePane, getConstraintsForPurchasePanel());

        return panel;
    }


    // The purchase menu. Contains buttons "New purchase", "Submit", "Cancel".
    private Component getPurchaseMenuPane() {
        JPanel panel = new JPanel();

        // Initialize layout
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gc = getConstraintsForMenuButtons();

        // Initialize the buttons
        newPurchase = createNewPurchaseButton();
        submitPurchase = createConfirmButton();
        cancelPurchase = createCancelButton();

        // Add the buttons to the panel, using GridBagConstraints we defined above
        panel.add(newPurchase, gc);
        panel.add(submitPurchase, gc);
        panel.add(cancelPurchase, gc);

        return panel;
    }


    // Creates the button "New purchase"
    private JButton createNewPurchaseButton() {
        JButton b = new JButton("New purchase");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newPurchaseButtonClicked();
            }
        });

        return b;
    }

    // Creates the "Confirm" button
    private JButton createConfirmButton() {
        JButton b = new JButton("Confirm");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                final JFrame jFrame = new JFrame("Paying");
                jFrame.setLocation(560, 250);

                JPanel mainjPanel = new JPanel(new CardLayout());
                mainjPanel.setLayout(new FlowLayout());

                JPanel top = new JPanel(new FlowLayout());
                JPanel top1 = new JPanel(new FlowLayout());
                JPanel top2 = new JPanel(new FlowLayout());
                JPanel top3 = new JPanel(new FlowLayout());



                JLabel jLabel = new JLabel("Price: ");
                jLabel.setFont(new Font("Courier", Font.BOLD, 14));
                JLabel price = new JLabel(String.valueOf(getTotalPrice()));
                price.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel payingSum = new JLabel("Payment amount: ");
                payingSum.setFont(new Font("Courier", Font.BOLD, 14));

                final JTextField enterSum = new JTextField();
                enterSum.setPreferredSize(new Dimension(100, 20));




                JLabel changeAmount = new JLabel("Change Amount: ");
                changeAmount.setFont(new Font("Courier", Font.BOLD, 14));

                final JLabel backMoney = new JLabel();


                JButton accept = new JButton("Accept");
                JButton cancel = new JButton("Cancel");


                accept.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getActionCommand().equals("Accept")) {
                            if (Double.valueOf(backMoney.getText()) < 0.0) {
                                JOptionPane.showMessageDialog(null, "Please check insert", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {

                                JOptionPane.showMessageDialog(null, "Order accepted");
                                quantityDecrease();
                                
                                submitPurchaseButtonClicked(savePurchase());
                                jFrame.dispose();
                                
                            }
                        }

                    }
                });

                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getActionCommand().equals("Cancel")) {
                            jFrame.dispose();
                        }
                    }
                });

                enterSum.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        double back =Math.round((Double.parseDouble(enterSum.getText()) - getTotalPrice())*100.0)/ 100.0;
                        backMoney.setText(String.valueOf(back));

                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }
                });

                enterSum.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char ch = e.getKeyChar();

                        if(Character.isLetter(ch)){
                            JOptionPane.showMessageDialog(null, "Please check insert", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }

                });


                top.add(jLabel);
                top.add(price);

                top1.add(payingSum);
                top1.add(enterSum);

                top2.add(changeAmount);
                top2.add(backMoney);

                top3.add(accept);
                top3.add(cancel);


                mainjPanel.add(top);
                mainjPanel.add(top1);
                mainjPanel.add(top2);
                mainjPanel.add(top3);


                jFrame.add(mainjPanel);

                jFrame.setSize(300, 400);
                jFrame.setVisible(true);


            }
        });
        b.setEnabled(false);

        return b;
    }




    // Creates the "Cancel" button
    private JButton createCancelButton() {
        JButton b = new JButton("Cancel");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelPurchaseButtonClicked();
            }
        });
        b.setEnabled(false);

        return b;
    }


    

    private double getTotalPrice(){
        double total = 0;
        int count = 0;

        while (model.getCurrentPurchaseTableModel().getRowCount() > count){
            total += Double.parseDouble(String.valueOf(model.getCurrentPurchaseTableModel().getValueAt(count, 4)));
            count++;
        }
        return total;
    }

    private void quantityDecrease() {
        PurchaseInfoTableModel purchaseInfoTableModel = model.getCurrentPurchaseTableModel();
        StockTableModel stockTableModel = model.getWarehouseTableModel();

        for (SoldItem solditem: purchaseInfoTableModel.getTableRows()) {
            StockItem stockitem = stockTableModel.getItemById(solditem.getId());
            int oldQuantity = stockitem.getQuantity();
            stockitem.setQuantity(oldQuantity - solditem.getQuantity());
        }
    }
    private SingleSale savePurchase(){
    	PurchaseInfoTableModel purchase = model.getCurrentPurchaseTableModel();
    	HistoryTableModel historyTableModel = model.getHistoryTableModel();
    	
    	historyTableModel.addSale(purchase, getTotalPrice());
    	return historyTableModel.getItemById(historyTableModel.getRowCount()-1);
    	
    }


  /* === Event handlers for the menu buttons
   *     (get executed when the buttons are clicked)
   */


    /**
     * Event handler for the <code>new purchase</code> event.
     */
    protected void newPurchaseButtonClicked() {
        log.info("New sale process started");
        try {
            domainController.startNewPurchase();
            startNewSale();
        } catch (VerificationFailedException e1) {
            log.error(e1.getMessage());
        }
    }


    /**
     * Event handler for the <code>cancel purchase</code> event.
     */
    protected void cancelPurchaseButtonClicked() {
        log.info("Sale cancelled");
        try {
            domainController.cancelCurrentPurchase();
            endSale();
            model.getCurrentPurchaseTableModel().clear();
        } catch (VerificationFailedException e1) {
            log.error(e1.getMessage());
        }
    }


    /**
     * Event handler for the <code>submit purchase</code> event.
     */
    protected void submitPurchaseButtonClicked(SingleSale sale) {
        log.info("Sale complete");
        try {
            log.debug("Contents of the current basket:\n" + model.getCurrentPurchaseTableModel());
            domainController.submitCurrentPurchase(
                    model.getCurrentPurchaseTableModel().getTableRows(), sale
            );
            endSale();
            model.getCurrentPurchaseTableModel().clear();
        } catch (VerificationFailedException e1) {
            log.error(e1.getMessage());
        }
    }



  /* === Helper methods that bring the whole purchase-tab to a certain state
   *     when called.
   */

    // switch UI to the state that allows to proceed with the purchase
    private void startNewSale() {
        purchasePane.reset();

        purchasePane.setEnabled(true);
        submitPurchase.setEnabled(true);
        cancelPurchase.setEnabled(true);
        newPurchase.setEnabled(false);
    }

    // switch UI to the state that allows to initiate new purchase
    private void endSale() {
        purchasePane.reset();

        cancelPurchase.setEnabled(false);
        submitPurchase.setEnabled(false);
        newPurchase.setEnabled(true);
        purchasePane.setEnabled(false);
    }




  /* === Next methods just create the layout constraints objects that control the
   *     the layout of different elements in the purchase tab. These definitions are
   *     brought out here to separate contents from layout, and keep the methods
   *     that actually create the components shorter and cleaner.
   */

    private GridBagConstraints getConstraintsForPurchaseMenu() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.weightx = 1.0d;
        gc.weighty = 0d;

        return gc;
    }


    private GridBagConstraints getConstraintsForPurchasePanel() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.weightx = 1.0d;
        gc.weighty = 1.0;

        return gc;
    }


    // The constraints that control the layout of the buttons in the purchase menu
    private GridBagConstraints getConstraintsForMenuButtons() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridwidth = GridBagConstraints.RELATIVE;

        return gc;
    }

}
