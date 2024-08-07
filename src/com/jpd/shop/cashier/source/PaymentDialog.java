package com.jpd.shop.cashier.source;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import javax.swing.text.DocumentFilter;

import com.jpd.shop.cashier.source.resibo.ReceiptDialog;
import com.jpd.shop.common_files.Colors;
import com.jpd.shop.common_files.MainFrame;
import com.jpd.shop.common_files.data_types.Client;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 *
 * @author jpd
 */
public class PaymentDialog extends javax.swing.JDialog {

    public static void showDialog(float totalPrice) {
        new PaymentDialog(totalPrice).setVisible(true);
    }

    private final Font[] FONT = {
            new Font("Segoe UI", 0, 24),
            new Font("Segoe UI", 0, 14)
    };

    private final float TOTAL_PRICE;

    private PaymentDialog(float totalPrice) {
        super(MainFrame.getInstance(), true);

        TOTAL_PRICE = totalPrice;

        this.setUndecorated(true);
        this.initComponents();
        setBayadTextFieldDocFilter();
        this.setLocationRelativeTo(MainFrame.getInstance());
    }

    private void setBayadTextFieldDocFilter() {
        ((AbstractDocument) bayad.getDocument()).setDocumentFilter(
                new DocumentFilter() {
                    @Override
                    public void insertString(FilterBypass fb, int offset,
                            String string, AttributeSet attr) throws BadLocationException {

                        if (string == null) {
                            return;
                        }
                        if (isValidInput(string)) {
                            super.insertString(fb, offset, string, attr);
                        }
                    }

                    @Override
                    public void replace(FilterBypass fb, int offset, int length,
                            String text, AttributeSet attrs) throws BadLocationException {

                        if (text == null) {
                            return;
                        }
                        if (isValidInput(text)) {
                            super.replace(fb, offset, length, text, attrs);
                        }
                    }

                    private boolean isValidInput(String text) {
                        // Use a regular expression to check if the input is a valid integer
                        return text.matches("^[0-9]+$");
                    }
                });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JButton cancelButton = new javax.swing.JButton();
        javax.swing.JButton orderButton = new javax.swing.JButton();
        javax.swing.JButton editButton = new javax.swing.JButton();
        kulangTimKwarta = new javax.swing.JLabel();
        bayad = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 300));
        setShape(MainFrame.getInstance().getShape());
        getContentPane().setLayout(null);

        jLabel1.setBackground(getBackground());
        jLabel1.setFont(FONT[0]);
        jLabel1.setText("Cash:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 120, 70, 60);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(FONT[0]);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText(Double.toString(TOTAL_PRICE));
        jLabel2.setOpaque(true);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(110, 40, 250, 60);

        jLabel3.setBackground(getBackground());
        jLabel3.setFont(FONT[0]);
        jLabel3.setText("Total:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 40, 70, 60);

        cancelButton.setFont(FONT[1]);
        cancelButton.setText("CANCEL");
        cancelButton.setCursor(MainFrame.HAND_CURSOR);
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        getContentPane().add(cancelButton);
        cancelButton.setBounds(265, 210, 90, 60);

        orderButton.setFont(FONT[1]);
        orderButton.setText("ORDER");
        orderButton.setCursor(MainFrame.HAND_CURSOR);
        orderButton.setFocusable(false);
        orderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderButtonActionPerformed(evt);
            }
        });
        getContentPane().add(orderButton);
        orderButton.setBounds(30, 210, 90, 60);

        editButton.setFont(FONT[1]);
        editButton.setText("EDIT");
        editButton.setCursor(MainFrame.HAND_CURSOR);
        editButton.setFocusable(false);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        getContentPane().add(editButton);
        editButton.setBounds(145, 210, 90, 60);

        kulangTimKwarta.setForeground(Colors.RED);
        kulangTimKwarta.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        kulangTimKwarta.setText("cash is less than the total price");
        kulangTimKwarta.setVisible(false);
        getContentPane().add(kulangTimKwarta);
        kulangTimKwarta.setBounds(30, 10, 330, 16);

        bayad.setFont(FONT[0]);
        bayad.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        bayad.setText("0");
        getContentPane().add(bayad);
        bayad.setBounds(110, 120, 250, 60);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void orderButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_orderButtonActionPerformed
        int cash = Integer.parseInt(bayad.getText());

        if (TOTAL_PRICE > cash) {
            kulangTimKwarta.setVisible(true);
            return;
        }

        Component[] orderInTray = ProductsPanel.getInstance().TRAY_PRODUCTS_CONTAINER.getComponents();
        int[][] order = new int[orderInTray.length][2];
        Object[] dataToSend = { MainPanel.getLoggedInEmployeeName(), order };

        for (int i = 0; i < orderInTray.length; i++) {
            TrayProductCard trayProductCard = (TrayProductCard) orderInTray[i];
            order[i][0] = trayProductCard.PRODUCT_DATA.id();
            order[i][1] = trayProductCard.getQuantity(true);
        }

        // TODO: add code

        Client.getInstance().makeARequestToServer(dataToSend);
        ProductsPanel.getInstance().TRAY_PRODUCTS_CONTAINER.removeAll();

        for (MouseListener listener : ProductsPanel.getInstance().CATEGORY_PANEL
                .getComponent(0).getMouseListeners()) {

            listener.mouseReleased(null);
        }

        this.dispose();
        ReceiptDialog.show(orderInTray, TOTAL_PRICE, cash);
    }// GEN-LAST:event_orderButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_editButtonActionPerformed
        this.dispose();
    }// GEN-LAST:event_editButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelButtonActionPerformed
        ProductsPanel.getInstance().TRAY_PRODUCTS_CONTAINER.removeAll();
        this.dispose();
    }// GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bayad;
    private javax.swing.JLabel kulangTimKwarta;
    // End of variables declaration//GEN-END:variables
}
