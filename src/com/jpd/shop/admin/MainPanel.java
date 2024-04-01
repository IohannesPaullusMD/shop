package com.jpd.shop.admin;

import javax.swing.ImageIcon;

/**
 *
 * @author jpd
 */
public class MainPanel extends javax.swing.JPanel {

    /**
     * Creates new form MainPanel
     */
    public MainPanel() {
        initComponents();
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        navBar = new javax.swing.JPanel();
        logoutButton = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1000, 580));
        setMinimumSize(new java.awt.Dimension(1000, 580));
        setPreferredSize(new java.awt.Dimension(1000, 580));
        setLayout(null);

        navBar.setBackground(Colors.GRAY);
        navBar.setPreferredSize(new java.awt.Dimension(1000, 50));
        navBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        logoutButton.setBackground(navBar.getBackground());
        logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jpd/shop/admin/icons/logout.png"))); // NOI18N
        logoutButton.setToolTipText("");
        logoutButton.setOpaque(true);
        logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutButtonMouseEntered(evt);
            }
        });
        navBar.add(logoutButton);

        add(navBar);
        navBar.setBounds(0, 530, 1000, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void logoutButtonMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_logoutButtonMouseEntered
        logoutButton.setBackground(Colors.WHITE_HOVER);
    }// GEN-LAST:event_logoutButtonMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel logoutButton;
    private javax.swing.JPanel navBar;
    // End of variables declaration//GEN-END:variables
}
