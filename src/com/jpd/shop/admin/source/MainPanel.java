package com.jpd.shop.admin.source;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.jpd.shop.common_files.Colors;
import com.jpd.shop.common_files.Panels.LoginPanel;
import com.jpd.shop.common_files.MainFrame;
import com.jpd.shop.common_files.Panels.MainPanel_Interface;
import com.jpd.shop.common_files.data_types.NavButton;

/**
 *
 * @author jpd
 */
public class MainPanel extends javax.swing.JPanel implements MainPanel_Interface {

    private static MainPanel mainPanel;

    public static final MainPanel getInstance() {
        if (mainPanel == null) {
            mainPanel = new MainPanel();
            showPanel(true, ProductsPanel.getInstance());
        }

        return mainPanel;
    }

    public static void showPanel(boolean show, JPanel panel) {
        if (show) {
            panel.setBounds(0, 0, 1000, 530);
            panel.setVisible(true);
            mainPanel.add(panel);
        } else {
            mainPanel.remove(panel);
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private static final void dispose() {
        MainFrame.showPanel(false, mainPanel);
        mainPanel = null;
        System.gc();
    }

    private final ImageIcon SHOW_PRODUCTS_ICON = new ImageIcon(getClass().getResource(
            "/com/jpd/shop/common_files/icons/logo.png"));
    private final ImageIcon BLUE_SHOW_PRODUCTS_ICON = new ImageIcon(getClass().getResource(
            "/com/jpd/shop/common_files/icons/blue_logo.png"));

    public MainPanel() {
        initComponents();
        customInit();
    }

    private void customInit() {
        this.setBounds(0, 0, 1000, 580);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        navBar = new javax.swing.JPanel();
        logoutButton = new javax.swing.JLabel();
        showProductsButton = new com.jpd.shop.common_files.data_types.NavButton(SHOW_PRODUCTS_ICON, BLUE_SHOW_PRODUCTS_ICON, true);

        setMaximumSize(new java.awt.Dimension(1000, 580));
        setMinimumSize(new java.awt.Dimension(1000, 580));
        setPreferredSize(new java.awt.Dimension(1000, 580));
        setLayout(null);

        navBar.setBackground(Colors.GRAY);
        navBar.setPreferredSize(new java.awt.Dimension(1000, 50));
        navBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        logoutButton.setBackground(navBar.getBackground());
        logoutButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoutButton.setIcon(
                new javax.swing.ImageIcon(getClass().getResource("/com/jpd/shop/common_files/icons/logout.png"))); // NOI18N
        logoutButton.setToolTipText("");
        logoutButton.setOpaque(true);
        logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutButtonMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutButtonMouseExited(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                logoutButtonMouseReleased(evt);
            }
        });
        navBar.add(logoutButton);
        NavButton.changeLastClickedButton(showProductsButton);
        navBar.add(showProductsButton);

        add(navBar);
        navBar.setBounds(0, 530, 1000, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void logoutButtonMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_logoutButtonMouseReleased
        dispose();
        MainFrame.showPanel(true, LoginPanel.getInstance());
    }// GEN-LAST:event_logoutButtonMouseReleased

    private void logoutButtonMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_logoutButtonMouseExited
        logoutButton.setBackground(navBar.getBackground());
    }// GEN-LAST:event_logoutButtonMouseExited

    private void logoutButtonMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_logoutButtonMouseEntered
        logoutButton.setBackground(Colors.WHITE_HOVER);
    }// GEN-LAST:event_logoutButtonMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel logoutButton;
    private javax.swing.JPanel navBar;
    private com.jpd.shop.common_files.data_types.NavButton showProductsButton;
    // End of variables declaration//GEN-END:variables

}
