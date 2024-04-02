/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.jpd.shop.admin.source;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jpd.shop.common_files.Client;
import com.jpd.shop.common_files.ProductCard;
import com.jpd.shop.common_files.ProductData;

/**
 *
 * @author jpd
 */
public class DisplayProductsPanel extends javax.swing.JPanel {

    private final MainPanel MAIN_PANEL_REF;
    private final JPanel CONTAINER = new JPanel(
            new FlowLayout(FlowLayout.LEADING, 20, 20));

    public DisplayProductsPanel() {
        this(null);
    }

    public DisplayProductsPanel(MainPanel mainPanelRef) {
        MAIN_PANEL_REF = mainPanelRef;

        initComponents();
    }

    public void loadProducts() {
        Object object = MAIN_PANEL_REF.getMainFrameRef().getClient().makeARequestToServer(
                Client.GET_PRODUCTS_LIST);
        ProductData[] products = null;

        if (object instanceof ProductData[]) {
            products = (ProductData[]) object;
        }

        for (ProductData productData : products) {
            CONTAINER.add(new ProductCard(productData));
        }

        CONTAINER.setPreferredSize(
                new Dimension(
                        getWidth(),
                        (products.length * 200) + ((products.length + 1) * 5)));

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
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();

        setMaximumSize(new java.awt.Dimension(530, 450));
        setMinimumSize(new java.awt.Dimension(530, 450));
        setPreferredSize(new java.awt.Dimension(530, 450));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}