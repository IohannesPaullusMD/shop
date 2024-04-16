package com.jpd.shop.admin.source;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.jpd.shop.common_files.Colors;
import com.jpd.shop.common_files.Panels.HistoryPanel;
import com.jpd.shop.common_files.Panels.LoginPanel;
import com.jpd.shop.common_files.MainFrame;
import com.jpd.shop.common_files.data_types.MyPanel_Interface;
import com.jpd.shop.common_files.data_types.NavButton;

/**
 *
 * @author jpd
 */
public class MainPanel extends javax.swing.JPanel {

    private static MainPanel mainPanel;
    private static MyPanel_Interface shownPanel;

    public static final MainPanel getInstance() {
        if (mainPanel == null) {
            mainPanel = new MainPanel();
            showPanel(ProductsPanel.getInstance());
        }

        return mainPanel;
    }

    public static void showPanel(MyPanel_Interface panel) {
        if (shownPanel != null) {
            mainPanel.remove((JPanel) shownPanel);
            shownPanel.disposePanel();
        }

        ((JPanel) panel).setBounds(0, 0, 1000, 530);
        ((JPanel) panel).setVisible(true);
        mainPanel.add((JPanel) panel);

        mainPanel.revalidate();
        mainPanel.repaint();

        shownPanel = panel;
    }

    private static final void dispose() {
        MainFrame.showPanel(false, mainPanel);
        mainPanel = null;

        if (ProductsPanel.hasAnInstance()) {
            ProductsPanel.getInstance().disposePanel();
        }

        // TODO: add code
        System.gc();
    }

    private final ImageIcon SHOW_PRODUCTS_ICON = new ImageIcon(getClass().getResource(
            "/com/jpd/shop/common_files/icons/logo.png"));
    private final ImageIcon BLUE_SHOW_PRODUCTS_ICON = new ImageIcon(getClass().getResource(
            "/com/jpd/shop/common_files/icons/blue_logo.png"));
    private final ImageIcon HISTORY_ICON = new ImageIcon(getClass().getResource(
            "/com/jpd/shop/common_files/icons/history.png"));
    private final ImageIcon BLUE_HISTORY_ICON = new ImageIcon(getClass().getResource(
            "/com/jpd/shop/common_files/icons/blue_history.png"));
    private final ImageIcon LOG_BOOK_ICON = new ImageIcon(getClass().getResource(
            "/com/jpd/shop/common_files/icons/address_book.png"));
    private final ImageIcon BLUE_LOG_BOOK_ICON = new ImageIcon(getClass().getResource(
            "/com/jpd/shop/common_files/icons/blue_address_book.png"));

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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        navBar = new javax.swing.JPanel();
        logoutButton = new javax.swing.JLabel();
        showProductsButton = new com.jpd.shop.common_files.data_types.NavButton(SHOW_PRODUCTS_ICON,
                BLUE_SHOW_PRODUCTS_ICON, true);
        historyButton = new com.jpd.shop.common_files.data_types.NavButton(HISTORY_ICON, BLUE_HISTORY_ICON, true);
        employeePanelButton = new com.jpd.shop.common_files.data_types.NavButton(LOG_BOOK_ICON, BLUE_LOG_BOOK_ICON,
                true);

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

        showProductsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                showProductsButtonMouseReleased(evt);
            }
        });
        NavButton.changeLastClickedButton(showProductsButton);
        navBar.add(showProductsButton);

        historyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                historyButtonMouseReleased(evt);
            }
        });
        navBar.add(historyButton);

        employeePanelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                employeePanelButtonMouseReleased(evt);
            }
        });
        navBar.add(employeePanelButton);

        add(navBar);
        navBar.setBounds(0, 530, 1000, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void employeePanelButtonMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_employeePanelButtonMouseReleased
        showPanel(EmployeesInfoPanel.getInstance());
    }// GEN-LAST:event_employeePanelButtonMouseReleased

    private void historyButtonMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_navButton1MouseReleased
        showPanel(HistoryPanel.getInstance());
    }// GEN-LAST:event_navButton1MouseReleased

    private void showProductsButtonMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_showProductsButtonMouseReleased
        showPanel(ProductsPanel.getInstance());
    }// GEN-LAST:event_showProductsButtonMouseReleased

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
    private com.jpd.shop.common_files.data_types.NavButton employeePanelButton;
    private com.jpd.shop.common_files.data_types.NavButton historyButton;
    private javax.swing.JLabel logoutButton;
    private javax.swing.JPanel navBar;
    private com.jpd.shop.common_files.data_types.NavButton showProductsButton;
    // End of variables declaration//GEN-END:variables

}
