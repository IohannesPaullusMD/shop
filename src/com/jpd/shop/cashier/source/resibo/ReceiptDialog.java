/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.jpd.shop.cashier.source.resibo;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import com.jpd.shop.cashier.source.TrayProductCard;
import com.jpd.shop.common_files.MainFrame;

/**
 *
 * @author jpd
 */
public class ReceiptDialog extends javax.swing.JDialog {

    public static void show(Component[] components, float totalPrice, int cash) {
        new ReceiptDialog(components, totalPrice, cash);
    }

    private TrayProductCard[] cards;
    private final float TOTAL_PRICE;
    private final int CASH;

    private ReceiptDialog(Component[] components, float totalPrice, int cash) {
        super(MainFrame.getInstance(), true);

        cards = new TrayProductCard[components.length];
        for (int i = 0; i < components.length; i++) {
            cards[i] = (TrayProductCard) components[i];
        }

        TOTAL_PRICE = totalPrice;
        CASH = cash;

        initComponents();
        setVisible(true);
    }

    private void loadProducts() {
        for (TrayProductCard card : cards) {
            resibo.add(new ProductResiboTemplate(card));
        }

        resibo.setPreferredSize(
                new Dimension(resibo.getWidth(), cards.length * 35));

        resibo.revalidate();
        resibo.repaint();
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        resiboScrollPane = new javax.swing.JScrollPane();
        resibo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        cash = new javax.swing.JLabel();
        change = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(400, 500));
        setMinimumSize(new java.awt.Dimension(400, 500));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setPreferredSize(new java.awt.Dimension(400, 500));
        setResizable(false);
        setSize(new java.awt.Dimension(400, 500));
        getContentPane().setLayout(null);

        resiboScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        resiboScrollPane.setMaximumSize(new java.awt.Dimension(400, 500));
        resiboScrollPane.setMinimumSize(new java.awt.Dimension(400, 500));
        resiboScrollPane.setPreferredSize(new java.awt.Dimension(400, 500));

        resibo.setMaximumSize(new java.awt.Dimension(400, 500));
        resibo.setMinimumSize(new java.awt.Dimension(400, 500));
        resibo.setPreferredSize(new java.awt.Dimension(400, 500));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0);
        flowLayout1.setAlignOnBaseline(true);
        resibo.setLayout(flowLayout1);
        loadProducts();
        resiboScrollPane.setViewportView(resibo);

        getContentPane().add(resiboScrollPane);
        resiboScrollPane.setBounds(0, 70, 400, 280);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("IKAW BAHALA");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 10, 400, 20);

        jLabel2.setBackground(new java.awt.Color(255, 102, 51));
        jLabel2.setText("Cash:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 400, 200, 16);

        jLabel3.setBackground(new java.awt.Color(255, 102, 51));
        jLabel3.setText("Change:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 430, 200, 16);

        jLabel4.setBackground(new java.awt.Color(255, 51, 102));
        jLabel4.setText("Total:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(10, 370, 200, 16);

        total.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        total.setText(Float.toString(TOTAL_PRICE)
        );
        getContentPane().add(total);
        total.setBounds(300, 370, 80, 16);

        cash.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        cash.setText(Integer.toString(CASH)
        );
        getContentPane().add(cash);
        cash.setBounds(300, 400, 80, 16);

        change.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        change.setText(Float.toString(CASH - TOTAL_PRICE)
        );
        getContentPane().add(change);
        change.setBounds(290, 430, 90, 16);

        jLabel5.setText("name");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(10, 50, 50, 16);

        jLabel6.setText("price");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(200, 50, 40, 16);

        jLabel7.setText("quantity");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(240, 50, 50, 16);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cash;
    private javax.swing.JLabel change;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel resibo;
    private javax.swing.JScrollPane resiboScrollPane;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
