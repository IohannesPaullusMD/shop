/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.jpd.shop.cashier.source;

import javax.swing.ImageIcon;

import com.jpd.shop.common_files.Colors;
import com.jpd.shop.common_files.MainFrame;
import com.jpd.shop.common_files.ProductCard.ProductCard;
import com.jpd.shop.common_files.data_types.ProductData;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSpinner;

/**
 *
 * @author jpd
 */
public class TrayProductCard extends javax.swing.JPanel {

    public final ProductData PRODUCT_DATA;

    private final int RADIUS = 18;
    private Shape shape;

    public TrayProductCard(ProductData productData) {
        PRODUCT_DATA = productData;
        initComponents();
    }

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

    public void changeQuantity(int quantity) {
        removeButton.setText(Integer.toString(quantity));
    }

    private String getProductName() {
        return ("<html><body style:'width: " + IMAGE.getWidth() + "px'><p>"
                + PRODUCT_DATA.name() + "</p></body></html>");
    }

    private String getProductPrice() {
        return Float.toString(PRODUCT_DATA.price() / 100.f);
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        IMAGE = new javax.swing.JLabel(new ImageIcon(PRODUCT_DATA.image()));
        NAME = new javax.swing.JLabel();
        PRICE_LABEL = new javax.swing.JLabel();
        QUANTITY_LABEL = new javax.swing.JLabel();
        PRICE = new javax.swing.JLabel(getProductPrice());
        removeButton = new javax.swing.JLabel(){
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
        QUANTITY_SPINNER = new javax.swing.JSpinner();

        setBackground(Colors.WHITE);
        setMaximumSize(new java.awt.Dimension(240, 145));
        setMinimumSize(new java.awt.Dimension(240, 145));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(240, 145));
        setLayout(null);

        IMAGE.setBackground(new java.awt.Color(204, 255, 255));
        IMAGE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        IMAGE.setMaximumSize(new java.awt.Dimension(100, 100));
        IMAGE.setMinimumSize(new java.awt.Dimension(100, 100));
        IMAGE.setPreferredSize(new java.awt.Dimension(100, 100));
        add(IMAGE);
        IMAGE.setBounds(10, 10, 100, 100);

        NAME.setBackground(new java.awt.Color(204, 255, 255));
        NAME.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        NAME.setMaximumSize(new java.awt.Dimension(110, 40));
        NAME.setMinimumSize(new java.awt.Dimension(110, 40));
        NAME.setPreferredSize(new java.awt.Dimension(110, 40));
        NAME.setText(getProductName());
        add(NAME);
        NAME.setBounds(120, 10, 110, 50);

        PRICE_LABEL.setText("Price:");
        PRICE_LABEL.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        add(PRICE_LABEL);
        PRICE_LABEL.setBounds(120, 60, 50, 16);

        QUANTITY_LABEL.setText("Quantity:");
        add(QUANTITY_LABEL);
        QUANTITY_LABEL.setBounds(120, 80, 60, 20);

        PRICE.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        PRICE.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        add(PRICE);
        PRICE.setBounds(170, 60, 40, 20);

        removeButton.setBackground(Colors.WHITE);
        removeButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        removeButton.setText("Remove");
        removeButton.setCursor(MainFrame.HAND_CURSOR);
        removeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                removeButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                removeButtonMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                removeButtonMouseReleased(evt);
            }
        });
        add(removeButton);
        removeButton.setBounds(120, 110, 110, 30);

        QUANTITY_SPINNER.setModel(new javax.swing.SpinnerNumberModel(1, 1, PRODUCT_DATA.stock(), 1));
        QUANTITY_SPINNER.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        QUANTITY_SPINNER.setCursor(MainFrame.HAND_CURSOR);
        QUANTITY_SPINNER.setFocusable(false);
        {
            JTextField textField = ((JSpinner.DefaultEditor) QUANTITY_SPINNER.getEditor()).getTextField();
            textField.setCaretColor(textField.getBackground());
            textField.getCaret().setBlinkRate(0);
        }
        add(QUANTITY_SPINNER);
        QUANTITY_SPINNER.setBounds(180, 80, 50, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void removeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeButtonMouseEntered
        removeButton.setBackground(Colors.RED);
        removeButton.setForeground(Colors.WHITE);
    }//GEN-LAST:event_removeButtonMouseEntered

    private void removeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeButtonMouseExited
        removeButton.setBackground(Colors.WHITE);
        removeButton.setForeground(Colors.BLACK);
    }//GEN-LAST:event_removeButtonMouseExited

    private void removeButtonMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_removeButtonMouseReleased
        ProductsPanel.getInstance().TRAY_PRODUCTS_CONTAINER.remove(this);
    }// GEN-LAST:event_removeButtonMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IMAGE;
    private javax.swing.JLabel NAME;
    private javax.swing.JLabel PRICE;
    private javax.swing.JLabel PRICE_LABEL;
    private javax.swing.JLabel QUANTITY_LABEL;
    private javax.swing.JSpinner QUANTITY_SPINNER;
    private javax.swing.JLabel removeButton;
    // End of variables declaration//GEN-END:variables
}
