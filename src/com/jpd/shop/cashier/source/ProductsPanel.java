package com.jpd.shop.cashier.source;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.jpd.shop.common_files.Colors;
import com.jpd.shop.common_files.ProductCard.ProductCard;
import com.jpd.shop.common_files.data_types.Client;
import com.jpd.shop.common_files.data_types.MyPanel_Interface;
import com.jpd.shop.common_files.data_types.NavButton;
import com.jpd.shop.common_files.data_types.ProductData;

public class ProductsPanel extends javax.swing.JPanel implements MyPanel_Interface {

    private static ProductsPanel productsPanel;

    public static ProductsPanel getInstance() {
        if (productsPanel == null) {
            productsPanel = new ProductsPanel();
        }
        return productsPanel;
    }

    public static boolean hasAnInstance() {
        return productsPanel != null;
    }

    private final FlowLayout LAYOUT = new FlowLayout(
            FlowLayout.LEFT, 20, 20);

    private ProductsPanel() {
        initComponents();
        loadProducts(Client.GET_BURGER_PRODUCTS);
    }

    @Override
    public void disposePanel() {
        if (productsPanel == null) {
            return;
        }

        productsPanel = null;

        ProductCard.changeSelectedCard(null);
    }

    private void loadProducts(int category) {
        // if ()

        ProductData[] products = null;
        Object object = Client.getInstance().makeARequestToServer(category);

        if (!(object instanceof ProductData[])) {
            return;
        }

        if (PRODUCT_CARDS_CONTAINER.getComponents().length > 0) {
            PRODUCT_CARDS_CONTAINER.removeAll();

            ProductCard.changeSelectedCard(null);
        }

        products = (ProductData[]) object;
        for (ProductData productData : products) {
            ProductCard productCard = new ProductCard(productData);
            productCard.getProductCardTemplate().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    // displayProductDetails(productCard.PRODUCT_DATA);
                    // changeButtonState(false);
                    // enableAdminPanelFields(false);
                }
            });

            if (ProductCard.getSelectedCard() == null) {
                ProductCard.changeSelectedCard(productCard);
            }

            PRODUCT_CARDS_CONTAINER.add(productCard);
        }

        // 200px it height hiton mga cards
        // 25px it vertical gap hiton cards???
        // 4 nga cards it na fit kada row
        int rows = (int) Math.ceil(products.length / 4.0);
        PRODUCT_CARDS_CONTAINER.setPreferredSize(new Dimension(
                SCROLL_PANE.getWidth(),
                (rows * 225)));
        PRODUCT_CARDS_CONTAINER.setLayout(LAYOUT);

        PRODUCT_CARDS_CONTAINER.revalidate();
        PRODUCT_CARDS_CONTAINER.repaint();

        SCROLL_PANE.revalidate();
        SCROLL_PANE.repaint();

        // displayProductDetails(products.length > 0
        // ? ProductCard.getSelectedCard().PRODUCT_DATA
        // : null);

        System.gc();
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

        PRODUCT_NAME = new javax.swing.JLabel();
        javax.swing.JSeparator jSeparator1 = new javax.swing.JSeparator();
        ADD_TO_TRAY_BUTTON = new javax.swing.JLabel() {
            final int RADIUS = 18;
            Shape shape;

            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, RADIUS, RADIUS);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(getForeground());
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, RADIUS, RADIUS);
            }

            @Override
            public boolean contains(int x, int y) {
                if (shape == null || !shape.getBounds().equals(getBounds())) {
                    shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, RADIUS, RADIUS);
                }
                return shape.contains(x, y);
            }
        };
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        QUANTITY_SPINNER = new javax.swing.JSpinner();
        PRODUCT_STOCK = new javax.swing.JLabel();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1000, 530));
        setMinimumSize(new java.awt.Dimension(1000, 530));
        setPreferredSize(new java.awt.Dimension(1000, 530));
        setLayout(null);

        CATEGORY_PANEL.setBackground(Colors.WHITE);
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

        SCROLL_PANE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        SCROLL_PANE.setMaximumSize(new java.awt.Dimension(700, 530));
        SCROLL_PANE.setMinimumSize(new java.awt.Dimension(700, 530));
        SCROLL_PANE.setPreferredSize(new java.awt.Dimension(700, 530));

        PRODUCT_CARDS_CONTAINER.setBackground(Colors.WHITE_BACKGROUND);
        SCROLL_PANE.setViewportView(PRODUCT_CARDS_CONTAINER);

        add(SCROLL_PANE);
        SCROLL_PANE.setBounds(50, 0, 700, 380);

        PRODUCT_TO_TRAY_PANEL.setBackground(Colors.WHITE_BACKGROUND);
        PRODUCT_TO_TRAY_PANEL.setBorder(SCROLL_PANE.getBorder());
        PRODUCT_TO_TRAY_PANEL.setLayout(null);

        PRODUCT_NAME.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        PRODUCT_NAME.setText("jLabel1");
        PRODUCT_NAME.setPreferredSize(new java.awt.Dimension(37, 50));
        PRODUCT_TO_TRAY_PANEL.add(PRODUCT_NAME);
        PRODUCT_NAME.setBounds(20, 6, 650, 40);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        PRODUCT_TO_TRAY_PANEL.add(jSeparator1);
        jSeparator1.setBounds(0, 50, 700, 10);

        ADD_TO_TRAY_BUTTON.setBackground(Colors.WHITE);
        ADD_TO_TRAY_BUTTON.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ADD_TO_TRAY_BUTTON.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ADD_TO_TRAY_BUTTON.setText("ADD TO TRAY");
        ADD_TO_TRAY_BUTTON.setMaximumSize(new java.awt.Dimension(200, 50));
        ADD_TO_TRAY_BUTTON.setMinimumSize(new java.awt.Dimension(200, 50));
        ADD_TO_TRAY_BUTTON.setPreferredSize(new java.awt.Dimension(200, 50));
        ADD_TO_TRAY_BUTTON.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ADD_TO_TRAY_BUTTONMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                ADD_TO_TRAY_BUTTONMouseExited(evt);
            }
        });
        PRODUCT_TO_TRAY_PANEL.add(ADD_TO_TRAY_BUTTON);
        ADD_TO_TRAY_BUTTON.setBounds(340, 70, 330, 70);

        PRODUCT_PRICE.setBackground(Colors.WHITE);
        PRODUCT_PRICE.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        PRODUCT_PRICE.setText("0.00");
        PRODUCT_PRICE.setBorder(PRODUCT_TO_TRAY_PANEL.getBorder());
        PRODUCT_PRICE.setOpaque(true);
        PRODUCT_TO_TRAY_PANEL.add(PRODUCT_PRICE);
        PRODUCT_PRICE.setBounds(60, 70, 60, 20);

        jLabel4.setText("Stock:");
        PRODUCT_TO_TRAY_PANEL.add(jLabel4);
        jLabel4.setBounds(150, 70, 40, 20);

        jLabel5.setText("Quantity:");
        PRODUCT_TO_TRAY_PANEL.add(jLabel5);
        jLabel5.setBounds(20, 110, 60, 30);

        QUANTITY_SPINNER.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        QUANTITY_SPINNER.setBorder(PRODUCT_TO_TRAY_PANEL.getBorder());
        {
            JTextField textField = ((JSpinner.DefaultEditor) QUANTITY_SPINNER.getEditor()).getTextField();
            textField.setCaretColor(textField.getBackground());
            textField.getCaret().setBlinkRate(0);
        }
        PRODUCT_TO_TRAY_PANEL.add(QUANTITY_SPINNER);
        QUANTITY_SPINNER.setBounds(80, 110, 180, 30);

        PRODUCT_STOCK.setBackground(Colors.WHITE);
        PRODUCT_STOCK.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        PRODUCT_STOCK.setText("0");
        PRODUCT_STOCK.setBorder(PRODUCT_PRICE.getBorder());
        PRODUCT_STOCK.setOpaque(true);
        PRODUCT_TO_TRAY_PANEL.add(PRODUCT_STOCK);
        PRODUCT_STOCK.setBounds(190, 70, 70, 20);

        jLabel7.setText("Price:");
        PRODUCT_TO_TRAY_PANEL.add(jLabel7);
        jLabel7.setBounds(20, 70, 40, 20);

        add(PRODUCT_TO_TRAY_PANEL);
        PRODUCT_TO_TRAY_PANEL.setBounds(50, 380, 700, 150);
    }// </editor-fold>//GEN-END:initComponents

    private void ADD_TO_TRAY_BUTTONMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_ADD_TO_TRAY_BUTTONMouseEntered
        ADD_TO_TRAY_BUTTON.setBackground(Colors.WHITE_HOVER);
        // TODO: add code
    }// GEN-LAST:event_ADD_TO_TRAY_BUTTONMouseEntered

    private void ADD_TO_TRAY_BUTTONMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_ADD_TO_TRAY_BUTTONMouseExited
        ADD_TO_TRAY_BUTTON.setBackground(Colors.WHITE);
        // TODO: add code
    }// GEN-LAST:event_ADD_TO_TRAY_BUTTONMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ADD_TO_TRAY_BUTTON;
    private final javax.swing.JPanel CATEGORY_PANEL = new javax.swing.JPanel();
    private final javax.swing.JPanel PRODUCT_CARDS_CONTAINER = new javax.swing.JPanel();
    private javax.swing.JLabel PRODUCT_NAME;
    private final javax.swing.JLabel PRODUCT_PRICE = new javax.swing.JLabel();
    private javax.swing.JLabel PRODUCT_STOCK;
    private final javax.swing.JPanel PRODUCT_TO_TRAY_PANEL = new javax.swing.JPanel();
    private javax.swing.JSpinner QUANTITY_SPINNER;
    private final javax.swing.JScrollPane SCROLL_PANE = new javax.swing.JScrollPane();
    // End of variables declaration//GEN-END:variables
}
