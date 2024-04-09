package com.jpd.shop.common_files.Panels;

import com.jpd.shop.common_files.data_types.Client;
import com.jpd.shop.common_files.Colors;
import com.jpd.shop.common_files.data_types.EmployeeLoginInfo;
import com.jpd.shop.common_files.MainFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author jpd
 */
public class LoginPanel extends javax.swing.JPanel {

    private static LoginPanel loginPanel = null;

    public static boolean hasNoInstance() {
        return (loginPanel == null);
    }

    public static LoginPanel getInstance() {
        if (hasNoInstance()) {
            loginPanel = new LoginPanel();
        }

        return loginPanel;
    }

    private static void closeLoginPanel() {
        MainFrame.showPanel(false, loginPanel);
        loginPanel = null;
        System.gc();
    }

    private LoginPanel() {
        initComponents();

        this.setBounds(0, 0, 1000, 580);
        this.setVisible(true);
    }

    public void enableUsernameAndPasswordField(boolean enable) {
        usernameField.setEnabled(enable);
        passwordField.setEnabled(enable);
    }

    private boolean isCorrectUserAndPassword() {
        Object serverResponce;
        int result = -1;
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        serverResponce = Client.getInstance().makeARequestToServer(
                new EmployeeLoginInfo(0, "", username, password));

        if (serverResponce instanceof Integer) {
            result = ((Integer) serverResponce).intValue();
        } else {
            return false;
        }

        switch (result) {
            case 1:
                return true;

            case 0:
                if (!MainFrame.getInstance().IS_ADMIN_APP) {
                    return true;
                }

                JOptionPane.showMessageDialog(null,
                        "You do not have access to this app.");
                break;

            case -1:
                JOptionPane.showMessageDialog(null,
                        "Incorrect user name or password.");
                break;
        }

        return false;
    }

    private boolean isFieldsEmpty() {
        return (usernameField.getText().equals("")
                && (passwordField.getPassword().length == 0));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        usernameField = new JTextField() {
            private Shape shape;
            private final int RADIUS = 18;

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
                    shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1,
                            getHeight() - 1, RADIUS, RADIUS);
                }
                return shape.contains(x, y);
            }
        };
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField() {
            private Shape shape;
            private final int RADIUS = 18;

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
                    shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1,
                            getHeight() - 1, RADIUS, RADIUS);
                }
                return shape.contains(x, y);
            }
        };
        loginButton = new javax.swing.JLabel() {
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

        setBackground(Colors.WHITE);
        setMaximumSize(new java.awt.Dimension(1000, 580));
        setMinimumSize(new java.awt.Dimension(1000, 580));
        setLayout(null);

        jLabel1.setBackground(getBackground());
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("User:");
        jLabel1.setMaximumSize(new java.awt.Dimension(200, 25));
        jLabel1.setMinimumSize(new java.awt.Dimension(200, 25));
        jLabel1.setOpaque(true);
        jLabel1.setPreferredSize(new java.awt.Dimension(200, 25));
        add(jLabel1);
        jLabel1.setBounds(400, 175, 200, 25);

        usernameField.setMaximumSize(new java.awt.Dimension(200, 25));
        usernameField.setMinimumSize(new java.awt.Dimension(200, 25));
        usernameField.setOpaque(false);
        usernameField.setPreferredSize(new java.awt.Dimension(200, 25));
        add(usernameField);
        usernameField.setBounds(400, 200, 200, 25);

        jLabel2.setBackground(getBackground());
        jLabel2.setText("Password:");
        jLabel2.setPreferredSize(new java.awt.Dimension(200, 25));
        add(jLabel2);
        jLabel2.setBounds(400, 250, 200, 25);

        passwordField.setOpaque(false);
        passwordField.setPreferredSize(new java.awt.Dimension(200, 25));
        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordFieldKeyReleased(evt);
            }
        });
        add(passwordField);
        passwordField.setBounds(400, 275, 200, 25);

        loginButton.setBackground(Colors.WHITE);
        loginButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginButton.setText("Log in");
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginButton.setOpaque(false);
        loginButton.setPreferredSize(new java.awt.Dimension(200, 50));
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButtonMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButtonMouseExited(evt);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                loginButtonMousePressed(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                loginButtonMouseReleased(evt);
            }
        });
        add(loginButton);
        loginButton.setBounds(400, 325, 200, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_loginButtonMousePressed
        loginButton.setBackground(Colors.BLUE);
        loginButton.setForeground(Colors.WHITE);
    }// GEN-LAST:event_loginButtonMousePressed

    private void loginButtonMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_loginButtonMouseReleased

        if (loginButton.getBackground().equals(Colors.BLUE)) {
            loginButton.setBackground(Colors.WHITE_HOVER);
            loginButton.setForeground(Colors.BLACK);
        }

        if (isFieldsEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Field/s is/are empty.");

            usernameField.setText("");
            passwordField.setText("");
            return;
        }

        if (isCorrectUserAndPassword()) {

            JPanel panel = null;

            if (MainFrame.getInstance().isAdminApp()) {
                panel = com.jpd.shop.admin.source.MainPanel.getInstance();
            } else {
                panel = com.jpd.shop.cashier.source.MainPanel.getInstance();
            }

            MainFrame.showPanel(true, panel);

            closeLoginPanel();

        }

        passwordField.setText("");

        // TODO: ayusa ini
        // this.setVisible(false);
    }// GEN-LAST:event_loginButtonMouseReleased

    private void passwordFieldKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jPasswordField1KeyReleased
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            loginButtonMouseReleased(null);
        }
    }// GEN-LAST:event_jPasswordField1KeyReleased

    private void loginButtonMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_loginButtonMouseEntered
        loginButton.setBackground(Colors.WHITE_HOVER);
    }// GEN-LAST:event_loginButtonMouseEntered

    private void loginButtonMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_loginButtonMouseExited
        loginButton.setBackground(Colors.WHITE);
    }// GEN-LAST:event_loginButtonMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
