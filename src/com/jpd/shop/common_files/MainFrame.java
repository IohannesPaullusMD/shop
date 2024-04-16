package com.jpd.shop.common_files;

import com.jpd.shop.common_files.data_types.Client;
import com.jpd.shop.common_files.Panels.ConnectionConfigPanel;
import com.jpd.shop.common_files.Panels.LoginPanel;

import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author jpd
 */
public class MainFrame extends javax.swing.JFrame {

    public static final Cursor HAND_CURSOR = new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR);
    public static final Cursor DEFAULT_CURSOR = new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR);

    private static MainFrame mainFrame;

    public static final MainFrame createInstance(boolean isAdminApp) {
        mainFrame = new MainFrame(isAdminApp);

        return mainFrame;
    }

    public static final MainFrame getInstance() {
        return mainFrame;
    }

    public static final void showPanel(boolean show, JPanel panel) {
        if (show) {
            mainFrame.layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
            mainFrame.layeredPane.revalidate();
            mainFrame.layeredPane.repaint();
        } else {
            mainFrame.layeredPane.remove(panel);
        }
    }

    private final ImageIcon closeIcon = new ImageIcon(getClass().getResource(
            "/com/jpd/shop/common_files/icons/close_button.png"));
    private final ImageIcon redCloseIcon = new ImageIcon(getClass().getResource(
            "/com/jpd/shop/common_files/icons/red_close_button.png"));

    public final boolean IS_ADMIN_APP;

    private Client client;

    private MainFrame(boolean isAdminApp) {
        IS_ADMIN_APP = isAdminApp;
        initComponents();
    }

    JLayeredPane getJLayeredPane() {
        return layeredPane;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public com.jpd.shop.common_files.Panels.ConnectionConfigPanel getConnectionConfigPanel() {
        return connectionConfigPanel;
    }

    public boolean isAdminApp() {
        return IS_ADMIN_APP;
    }

    public javax.swing.JLabel getConnectionButton() {
        return connectionButton;
    }

    public javax.swing.JPanel getTransparentPanel() {
        return transparentPanel;
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
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        connectionButtonContainer = new javax.swing.JPanel();
        closeButton = new javax.swing.JLabel();
        connectionButton = new javax.swing.JLabel();
        layeredPane = new javax.swing.JLayeredPane();
        transparentPanel = new javax.swing.JPanel();
        connectionConfigPanel = new ConnectionConfigPanel(this);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setUndecorated(true);
        setResizable(false);
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0,
                this.getWidth(), this.getHeight(), 20, 20));
        setSize(new java.awt.Dimension(1000, 600));
        getContentPane().setLayout(null);

        connectionButtonContainer.setBackground(Colors.GRAY);
        connectionButtonContainer.setMaximumSize(new java.awt.Dimension(1000, 20));
        connectionButtonContainer.setMinimumSize(new java.awt.Dimension(1000, 20));
        connectionButtonContainer.setPreferredSize(new java.awt.Dimension(1000, 20));
        connectionButtonContainer.setLayout(null);

        closeButton.setIcon(
                new javax.swing.ImageIcon(getClass().getResource("/com/jpd/shop/common_files/icons/close_button.png"))); // NOI18N
        closeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeButton.setMaximumSize(new java.awt.Dimension(20, 20));
        closeButton.setMinimumSize(new java.awt.Dimension(20, 20));
        closeButton.setPreferredSize(new java.awt.Dimension(20, 20));
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeButtonMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeButtonMouseExited(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                closeButtonMouseReleased(evt);
            }
        });
        connectionButtonContainer.add(closeButton);
        closeButton.setBounds(960, 0, 20, 20);

        connectionButton.setBackground(connectionButtonContainer.getBackground());
        connectionButton.setIcon(
                new javax.swing.ImageIcon(getClass().getResource("/com/jpd/shop/common_files/icons/red_wifi.png"))); // NOI18N
        connectionButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        connectionButton.setMaximumSize(new java.awt.Dimension(20, 20));
        connectionButton.setMinimumSize(new java.awt.Dimension(20, 20));
        connectionButton.setOpaque(true);
        connectionButton.setPreferredSize(new java.awt.Dimension(20, 20));
        connectionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                connectionButtonMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                connectionButtonMouseExited(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                connectionButtonMouseReleased(evt);
            }
        });
        connectionButtonContainer.add(connectionButton);
        connectionButton.setBounds(930, 0, 20, 20);

        getContentPane().add(connectionButtonContainer);
        connectionButtonContainer.setBounds(0, 0, 1000, 20);

        layeredPane.setBackground(new java.awt.Color(204, 204, 204));
        layeredPane.setMaximumSize(new java.awt.Dimension(1000, 580));
        layeredPane.setMinimumSize(new java.awt.Dimension(1000, 580));
        layeredPane.setOpaque(true);

        transparentPanel.setMaximumSize(layeredPane.getPreferredSize());
        transparentPanel.setMinimumSize(layeredPane.getPreferredSize());
        transparentPanel.setOpaque(false);
        transparentPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ConsumeMouseEventsForTransparentPanel(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ConsumeMouseEventsForTransparentPanel(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                ConsumeMouseEventsForTransparentPanel(evt);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                ConsumeMouseEventsForTransparentPanel(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ConsumeMouseEventsForTransparentPanel(evt);
            }
        });
        transparentPanel.setLayout(null);
        transparentPanel.setPreferredSize(layeredPane.getPreferredSize());
        transparentPanel.repaint();
        layeredPane.setLayer(transparentPanel, javax.swing.JLayeredPane.POPUP_LAYER);
        layeredPane.add(transparentPanel);
        transparentPanel.setBounds(0, 0, 1000, 580);
        layeredPane.setLayer(connectionConfigPanel, javax.swing.JLayeredPane.DRAG_LAYER);
        layeredPane.add(connectionConfigPanel);
        connectionConfigPanel.setBounds(680, 20, 300, 225);

        LoginPanel.getInstance().enableUsernameAndPasswordField(false);
        layeredPane.add(LoginPanel.getInstance(), JLayeredPane.DEFAULT_LAYER);
        getContentPane().add(layeredPane);
        layeredPane.setBounds(0, 20, 1000, 580);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ConsumeMouseEventsForTransparentPanel(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_ConsumeMouseEventsForTransparentPanel
        evt.consume();
    }// GEN-LAST:event_ConsumeMouseEventsForTransparentPanel

    private void connectionButtonMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_connectionButtonMousePressed
        // TODO add your handling code here:
    }// GEN-LAST:event_connectionButtonMousePressed

    private void connectionButtonMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_connectionButtonMouseEntered
        connectionButton.setBackground(Colors.WHITE_HOVER);
    }// GEN-LAST:event_connectionButtonMouseEntered

    private void connectionButtonMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_connectionButtonMouseExited
        if (!connectionConfigPanel.isVisible()) {
            connectionButton.setBackground(connectionButtonContainer.getBackground());
        }
    }// GEN-LAST:event_connectionButtonMouseExited

    private void connectionButtonMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_connectionButtonMouseReleased
        connectionConfigPanel.setVisible(!connectionConfigPanel.isVisible());
    }// GEN-LAST:event_connectionButtonMouseReleased

    private void closeButtonMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_closeButtonMouseReleased
        System.exit(0);
    }// GEN-LAST:event_closeButtonMouseReleased

    private void closeButtonMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_closeButtonMouseExited
        closeButton.setIcon(closeIcon);
    }// GEN-LAST:event_closeButtonMouseExited

    private void closeButtonMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_closeButtonMouseEntered
        closeButton.setIcon(redCloseIcon);
    }// GEN-LAST:event_closeButtonMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel closeButton;
    private javax.swing.JLabel connectionButton;
    private javax.swing.JPanel connectionButtonContainer;
    private com.jpd.shop.common_files.Panels.ConnectionConfigPanel connectionConfigPanel;
    private javax.swing.JLayeredPane layeredPane;
    private javax.swing.JPanel transparentPanel;
    // End of variables declaration//GEN-END:variables

}
