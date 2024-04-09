/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.jpd.shop.cashier.source;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import com.jpd.shop.common_files.data_types.Client;
import com.jpd.shop.common_files.data_types.NavButton;
import com.jpd.shop.common_files.data_types.ProductData;

public class ProductsPanel extends javax.swing.JPanel {

    /**
     * Creates new form ProductsPanel
     */
    public ProductsPanel() {
        initComponents();
    }

    private void loadProducts(int category) {
        // if ()

        ProductData[] products = null;
        Object object = Client.getInstance().makeARequestToServer(category);

        if (!(object instanceof ProductData[])) {
            return;
        }

        // if (PRODUCT_CARDS_CONTAINER.getComponents().length > 0) {
        // PRODUCT_CARDS_CONTAINER.removeAll();

        // ProductCard.changeSelectedCard(null);

        // if (EDIT_SAVE_BUTTON.getText().equals("Save")) {
        // changeButtonState(false);
        // enableAdminPanelFields(false);
        // }
        // }

        // products = (ProductData[]) object;
        // for (ProductData productData : products) {
        // ProductCard productCard = new ProductCard(productData);
        // productCard.getProductCardTemplate().addMouseListener(new MouseAdapter() {
        // @Override
        // public void mouseReleased(MouseEvent e) {
        // displayProductDetails(productCard.PRODUCT_DATA);
        // changeButtonState(false);
        // enableAdminPanelFields(false);
        // }
        // });

        // if (ProductCard.getSelectedCard() == null) {
        // ProductCard.changeSelectedCard(productCard);
        // }

        // PRODUCT_CARDS_CONTAINER.add(productCard);
        // }

        // // 200px it height hiton mga cards
        // // 25px it vertical gap hiton cards???
        // // 4 nga cards it na fit kada row
        // int rows = (int) Math.ceil(products.length / 4.0);
        // PRODUCT_CARDS_CONTAINER.setPreferredSize(new Dimension(
        // SCROLL_PANE.getWidth(),
        // (rows * 225)));
        // PRODUCT_CARDS_CONTAINER.setLayout(layout);

        // PRODUCT_CARDS_CONTAINER.revalidate();
        // PRODUCT_CARDS_CONTAINER.repaint();

        // SCROLL_PANE.revalidate();
        // SCROLL_PANE.repaint();

        // displayProductDetails(products.length > 0
        // ? ProductCard.getSelectedCard().PRODUCT_DATA
        // : null);

        // System.gc();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        PRODUCT_NAME = new javax.swing.JLabel();
        javax.swing.JSeparator jSeparator1 = new javax.swing.JSeparator();
        ADD_TO_TRAY_BUTTON = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        QUANTITY_SPINNER = new javax.swing.JSpinner();
        PRODUCT_STOCK = new javax.swing.JLabel();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1000, 530));
        setMinimumSize(new java.awt.Dimension(1000, 530));
        setPreferredSize(new java.awt.Dimension(1000, 530));
        setLayout(null);

        CATEGORY_PANEL.setBackground(new java.awt.Color(204, 255, 255));
        CATEGORY_PANEL.setPreferredSize(new java.awt.Dimension(50, 530));
        CATEGORY_PANEL.setLayout(new java.awt.GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(5, 0, 5, 0);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.anchor = GridBagConstraints.CENTER;

        ImageIcon icon;
        ImageIcon blueIcon;
        NavButton button;

        // initialize burger button
        {
            icon = new ImageIcon(getClass().getResource(
                "/com/jpd/shop/common_files/icons/hamburger_button.png"));
        blueIcon = new ImageIcon(getClass().getResource(
            "/com/jpd/shop/common_files/icons/blue_hamburger_button.png"));
    button = new NavButton(icon, blueIcon, false);

    NavButton.changeLastClickedButton(button);
    button.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) {
            loadProducts(Client.GET_BURGER_PRODUCTS);
        }
    });
    button.setVisible(true);
    CATEGORY_PANEL.add(button, constraints);
    }

    // initialize fries button
    {
        icon = new ImageIcon(getClass().getResource(
            "/com/jpd/shop/common_files/icons/fries_button.png"));
    blueIcon = new ImageIcon(getClass().getResource(
        "/com/jpd/shop/common_files/icons/blue_fries_button.png"));
button = new NavButton(icon, blueIcon, false);

button.setIcon(icon);
button.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseReleased(MouseEvent e) {
        loadProducts(Client.GET_FRIES_PRODUCTS);
    }
    });
    button.setVisible(true);
    CATEGORY_PANEL.add(button, constraints);
    }

    // initialize drinks button
    {
        icon = new ImageIcon(getClass().getResource(
            "/com/jpd/shop/common_files/icons/drinks_button.png"));
    blueIcon = new ImageIcon(getClass().getResource(
        "/com/jpd/shop/common_files/icons/blue_drinks_button.png"));
button = new NavButton(icon, blueIcon, false);

button.setIcon(icon);
button.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseReleased(MouseEvent e) {
        loadProducts(Client.GET_DRINKS_PRODUCTS);
    }
    });
    button.setVisible(true);
    CATEGORY_PANEL.add(button, constraints);
    }
    add(CATEGORY_PANEL);
    CATEGORY_PANEL.setBounds(0, 0, 50, 530);

    jScrollPane1.setMaximumSize(new java.awt.Dimension(700, 530));
    jScrollPane1.setMinimumSize(new java.awt.Dimension(700, 530));
    jScrollPane1.setPreferredSize(new java.awt.Dimension(700, 530));
    add(jScrollPane1);
    jScrollPane1.setBounds(50, 0, 700, 380);

    jPanel1.setBackground(new java.awt.Color(204, 204, 255));
    jPanel1.setLayout(null);

    PRODUCT_NAME.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
    PRODUCT_NAME.setText("jLabel1");
    PRODUCT_NAME.setPreferredSize(new java.awt.Dimension(37, 50));
    jPanel1.add(PRODUCT_NAME);
    PRODUCT_NAME.setBounds(20, 6, 650, 40);

    jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
    jPanel1.add(jSeparator1);
    jSeparator1.setBounds(0, 50, 700, 10);

    ADD_TO_TRAY_BUTTON.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
    ADD_TO_TRAY_BUTTON.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    ADD_TO_TRAY_BUTTON.setText("ADD TO TRAY");
    ADD_TO_TRAY_BUTTON.setBorder(PRODUCT_PRICE.getBorder());
    ADD_TO_TRAY_BUTTON.setMaximumSize(new java.awt.Dimension(200, 50));
    ADD_TO_TRAY_BUTTON.setMinimumSize(new java.awt.Dimension(200, 50));
    ADD_TO_TRAY_BUTTON.setOpaque(true);
    ADD_TO_TRAY_BUTTON.setPreferredSize(new java.awt.Dimension(200, 50));
    jPanel1.add(ADD_TO_TRAY_BUTTON);
    ADD_TO_TRAY_BUTTON.setBounds(340, 70, 330, 70);

    PRODUCT_PRICE.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    PRODUCT_PRICE.setText("0.00");
    PRODUCT_PRICE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
    jPanel1.add(PRODUCT_PRICE);
    PRODUCT_PRICE.setBounds(50, 70, 70, 20);

    jLabel4.setText("Stock:");
    jPanel1.add(jLabel4);
    jLabel4.setBounds(150, 70, 40, 20);

    jLabel5.setText("Quantity:");
    jPanel1.add(jLabel5);
    jLabel5.setBounds(20, 110, 60, 30);

    QUANTITY_SPINNER.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
    jPanel1.add(QUANTITY_SPINNER);
    QUANTITY_SPINNER.setBounds(70, 110, 190, 30);

    PRODUCT_STOCK.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    PRODUCT_STOCK.setText("0");
    PRODUCT_STOCK.setBorder(PRODUCT_PRICE.getBorder());
    jPanel1.add(PRODUCT_STOCK);
    PRODUCT_STOCK.setBounds(190, 70, 70, 20);

    jLabel7.setText("Price:");
    jPanel1.add(jLabel7);
    jLabel7.setBounds(20, 70, 29, 20);

    add(jPanel1);
    jPanel1.setBounds(50, 380, 700, 150);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ADD_TO_TRAY_BUTTON;
    private final javax.swing.JPanel CATEGORY_PANEL = new javax.swing.JPanel();
    private javax.swing.JLabel PRODUCT_NAME;
    private final javax.swing.JLabel PRODUCT_PRICE = new javax.swing.JLabel();
    private javax.swing.JLabel PRODUCT_STOCK;
    private javax.swing.JSpinner QUANTITY_SPINNER;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
