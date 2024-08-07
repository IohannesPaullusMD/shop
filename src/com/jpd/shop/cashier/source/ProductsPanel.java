package com.jpd.shop.cashier.source;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.jpd.shop.common_files.Colors;
import com.jpd.shop.common_files.MainFrame;
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
    private Dimension dimension;

    private ProductsPanel() {
        initComponents();
        dimension = new Dimension(TRAY_SCROLL_PANE.getPreferredSize().width, 1);
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

    public JPanel getTray() {
        return TRAY_PRODUCTS_CONTAINER;
    }

    private void loadProducts(int category) {

        if (PRODUCT_CARDS_CONTAINER.getComponents().length > 0) {
            PRODUCT_CARDS_CONTAINER.removeAll();

            ProductCard.changeSelectedCard(null);
        }

        ProductData[] products = null;
        Object object = Client.getInstance().makeARequestToServer(category);

        if (object instanceof ProductData[]) {
            products = (ProductData[]) object;
        } else {
            return;
        }

        for (ProductData productData : products) {

            ProductCard productCard = new ProductCard(productData) {
                @Override
                protected void cardClicked() {
                }// do nothing
            };

            productCard.getProductCardTemplate().addMouseListener(new MouseAdapter() {
                private final Point POINT = new Point();

                private boolean isProductInTray() {
                    Component[] cards = TRAY_PRODUCTS_CONTAINER.getComponents();
                    for (int i = 0; i < cards.length; i++) {
                        if (productData.id() == ((TrayProductCard) cards[i]).PRODUCT_DATA.id()) {
                            POINT.y = (145 + 10) * i;
                            return true;
                        }
                    }

                    // else
                    return false;
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                    // add hin TrayProductCard if waray pa ini nga Product ha tray
                    if (!isProductInTray()) {
                        TrayProductCard trayProductCard = new TrayProductCard(productData);

                        TRAY_PRODUCTS_CONTAINER.add(trayProductCard);
                        recalculateTotalPrice(trayProductCard, true, 0, 1);
                        POINT.y = (145 + 10) * TRAY_PRODUCTS_CONTAINER.getComponents().length;

                        TRAY_SCROLL_PANE.getViewport().setViewPosition(POINT);// ayusa ini
                        // TODO: add code
                    }

                    // 145 = height han TrayProductCard
                    // 10 = vgap
                    TRAY_SCROLL_PANE.getViewport().setViewPosition(POINT);
                }
            });

            PRODUCT_CARDS_CONTAINER.add(productCard);
        }

        // 225px it height hiton mga cards
        // 25px it vertical gap hiton cards???
        // 4 nga cards it na fit kada row
        int rows = (int) Math.ceil(products.length / 4.0);
        PRODUCT_CARDS_CONTAINER.setPreferredSize(new Dimension(
                SCROLL_PANE.getWidth(),
                (rows * 250)));
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

    public void recalculateTotalPrice(TrayProductCard card, boolean newCard,
            float prevQuantity, float newQuantity) {

        float totalPrice = Float.parseFloat(totalPriceLabel.getText());
        float productTotalPrice = newCard ? 0 : (card.PRODUCT_DATA.price() / 100.0f) * prevQuantity;

        totalPrice -= productTotalPrice;

        productTotalPrice = (card.PRODUCT_DATA.price() / 100.0f) * newQuantity;

        totalPrice += productTotalPrice;

        totalPriceLabel.setText(Float.toString(totalPrice));
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

        totalPriceLabel = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        checkoutButton = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1000, 530));
        setMinimumSize(new java.awt.Dimension(1000, 530));
        setPreferredSize(new java.awt.Dimension(1000, 530));
        setLayout(null);

        totalPriceLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        totalPriceLabel.setText("0.0");
        add(totalPriceLabel);
        totalPriceLabel.setBounds(820, 420, 160, 20);

        CATEGORY_PANEL.setBackground(Colors.WHITE);
        CATEGORY_PANEL.setPreferredSize(new java.awt.Dimension(50, 530));
        CATEGORY_PANEL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CATEGORY_PANELMouseExited(evt);
            }
        });
        // post-listener
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

            button.setCursor(MainFrame.HAND_CURSOR);
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
            button.setCursor(MainFrame.HAND_CURSOR);
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
            button.setCursor(MainFrame.HAND_CURSOR);
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

        SCROLL_PANE.setBackground(new java.awt.Color(242, 242, 242));
        SCROLL_PANE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        SCROLL_PANE.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        SCROLL_PANE.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        SCROLL_PANE.setMaximumSize(new java.awt.Dimension(700, 530));
        SCROLL_PANE.setMinimumSize(new java.awt.Dimension(700, 530));
        SCROLL_PANE.setPreferredSize(new java.awt.Dimension(700, 530));

        PRODUCT_CARDS_CONTAINER.setBackground(Colors.WHITE_BACKGROUND);
        SCROLL_PANE.setViewportView(PRODUCT_CARDS_CONTAINER);

        SCROLL_PANE.getVerticalScrollBar().setUnitIncrement(16);
        add(SCROLL_PANE);
        SCROLL_PANE.setBounds(50, 0, 700, 530);

        TRAY_SCROLL_PANE.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        TRAY_SCROLL_PANE.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        TRAY_SCROLL_PANE.setMaximumSize(new java.awt.Dimension(250, 400));
        TRAY_SCROLL_PANE.setMinimumSize(new java.awt.Dimension(250, 400));
        TRAY_SCROLL_PANE.setPreferredSize(new java.awt.Dimension(250, 400));

        TRAY_PRODUCTS_CONTAINER.setMaximumSize(new java.awt.Dimension(250, 10000));
        TRAY_PRODUCTS_CONTAINER.setMinimumSize(new java.awt.Dimension(250, 1));
        TRAY_PRODUCTS_CONTAINER.setPreferredSize(new java.awt.Dimension(250, 1));
        TRAY_PRODUCTS_CONTAINER.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 5));
        TRAY_SCROLL_PANE.setViewportView(TRAY_PRODUCTS_CONTAINER);

        TRAY_SCROLL_PANE.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        TRAY_SCROLL_PANE.getVerticalScrollBar().setUnitIncrement(16);
        add(TRAY_SCROLL_PANE);
        TRAY_SCROLL_PANE.setBounds(750, 0, 250, 400);

        jLabel2.setText("Total:");
        add(jLabel2);
        jLabel2.setBounds(770, 420, 40, 20);

        checkoutButton.setText("PROCEED TO CHECKOUT");
        checkoutButton.setCursor(MainFrame.HAND_CURSOR);
        checkoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkoutButtonActionPerformed(evt);
            }
        });
        add(checkoutButton);
        checkoutButton.setBounds(770, 470, 210, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void checkoutButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_checkoutButtonActionPerformed
        if (TRAY_PRODUCTS_CONTAINER.getComponents().length == 0) {
            return;
        }
        PaymentDialog.showDialog(Float.parseFloat(totalPriceLabel.getText()));
    }// GEN-LAST:event_checkoutButtonActionPerformed

    private void CATEGORY_PANELMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_CATEGORY_PANELMouseExited
        // TODO add your handling code here:
    }// GEN-LAST:event_CATEGORY_PANELMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public final javax.swing.JPanel CATEGORY_PANEL = new javax.swing.JPanel();
    private final javax.swing.JPanel PRODUCT_CARDS_CONTAINER = new javax.swing.JPanel();
    private final javax.swing.JScrollPane SCROLL_PANE = new javax.swing.JScrollPane();
    final javax.swing.JPanel TRAY_PRODUCTS_CONTAINER = new javax.swing.JPanel() {
        // 145 it height han TrayProductCard
        // 10 it vgap
        @Override
        public void removeAll() {
            super.removeAll();
            this.revalidate();
            this.repaint();
            totalPriceLabel.setText("0.0");
        }

        @Override
        public Component add(Component comp) {
            if (comp instanceof TrayProductCard) {
                super.add(comp);

                dimension = new Dimension(dimension.width, dimension.height + 145 + 10);

                this.setPreferredSize(dimension);

                this.revalidate();
                this.repaint();
                // TRAY_SCROLL_PANE.revalidate();
                // TRAY_SCROLL_PANE.repaint();

                System.gc();
            }

            return comp;
        }

        @Override
        public void remove(Component comp) {
            super.remove(comp);

            dimension = new Dimension(dimension.width, dimension.height - 145 - 10);

            this.setPreferredSize(dimension);
            this.revalidate();
            this.repaint();
            System.gc();
        }
    };
    private final javax.swing.JScrollPane TRAY_SCROLL_PANE = new javax.swing.JScrollPane();
    private javax.swing.JButton checkoutButton;
    private javax.swing.JLabel totalPriceLabel;
    // End of variables declaration//GEN-END:variables
}
