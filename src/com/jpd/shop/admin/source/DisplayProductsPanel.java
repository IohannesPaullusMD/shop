/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.jpd.shop.admin.source;

import java.awt.FlowLayout;

import com.jpd.shop.common_files.Client;
import com.jpd.shop.common_files.ProductCard;
import com.jpd.shop.common_files.ProductData;

/**
 *
 * @author jpd
 */
public class DisplayProductsPanel extends javax.swing.JPanel {

    private final MainPanel MAIN_PANEL_REF;
    private final FlowLayout layout = new FlowLayout(
            FlowLayout.LEFT, 20, 20);

    public DisplayProductsPanel() {
        this(null);
    }

    public DisplayProductsPanel(MainPanel mainPanelRef) {
        MAIN_PANEL_REF = mainPanelRef;

        initComponents();

        if (mainPanelRef != null) {
            loadProducts(Client.GET_BURGER_PRODUCTS);
        }
    }

    private void loadProducts(int request) {
        ProductData[] products = null;
        Object object = MAIN_PANEL_REF.getMainFrameRef()
                .getClient().makeARequestToServer(request);

        if (object instanceof ProductData[]) {
            products = (ProductData[]) object;
        }

        if (PRODUCT_CARDS_CONTAINER.getComponents().length > 0) {
            PRODUCT_CARDS_CONTAINER.removeAll();
        }

        for (ProductData productData : products) {
            PRODUCT_CARDS_CONTAINER.add(new ProductCard(productData));
        }

        PRODUCT_CARDS_CONTAINER.setSize(
                getWidth(),
                (products.length * 200) + ((products.length + 1) * 5));
        PRODUCT_CARDS_CONTAINER.setLayout(layout);

        // PRODUCT_CARDS_CONTAINER.revalidate();
        // PRODUCT_CARDS_CONTAINER.repaint();
        PRODUCT_CARDS_CONTAINER.setVisible(true);

        scrollPane.revalidate();
        scrollPane.repaint();

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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        PRODUCT_CARDS_CONTAINER = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(700, 530));
        setMinimumSize(new java.awt.Dimension(700, 530));
        setPreferredSize(new java.awt.Dimension(700, 530));

        scrollPane.setBackground(new java.awt.Color(255, 255, 255));
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setMaximumSize(new java.awt.Dimension(700, 530));
        scrollPane.setMinimumSize(new java.awt.Dimension(700, 530));
        scrollPane.setOpaque(false);
        scrollPane.setPreferredSize(new java.awt.Dimension(700, 530));
        scrollPane.setViewportView(null);

        javax.swing.GroupLayout PRODUCT_CARDS_CONTAINERLayout = new javax.swing.GroupLayout(PRODUCT_CARDS_CONTAINER);
        PRODUCT_CARDS_CONTAINER.setLayout(PRODUCT_CARDS_CONTAINERLayout);
        PRODUCT_CARDS_CONTAINERLayout.setHorizontalGroup(
                PRODUCT_CARDS_CONTAINERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 690, Short.MAX_VALUE));
        PRODUCT_CARDS_CONTAINERLayout.setVerticalGroup(
                PRODUCT_CARDS_CONTAINERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 530, Short.MAX_VALUE));

        scrollPane.setViewportView(PRODUCT_CARDS_CONTAINER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 700,
                                javax.swing.GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 530,
                                javax.swing.GroupLayout.PREFERRED_SIZE));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PRODUCT_CARDS_CONTAINER;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
