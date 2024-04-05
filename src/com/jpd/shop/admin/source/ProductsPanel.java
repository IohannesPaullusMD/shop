/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.jpd.shop.admin.source;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jpd.shop.common_files.Colors;
import com.jpd.shop.common_files.NavButton;
import com.jpd.shop.common_files.ProductData;
import com.jpd.shop.common_files.functinal_interfaces.FunctionWithOneBooleanParameterButNoReturnValue;

@SuppressWarnings("finally")
public class ProductsPanel extends javax.swing.JPanel {

    private final ImageIcon ADD_ICON = new ImageIcon(getClass().getResource(
            "/com/jpd/shop/common_files/icons/add_button.png"));
    private final ImageIcon BLUE_ADD_ICON = new ImageIcon(getClass().getResource(
            "/com/jpd/shop/common_files/icons/blue_add_button.png"));

    private final JLabel ADD_BUTTON = new JLabel(ADD_ICON);
    private final JLabel PRODUCT_IMAGE = new JLabel();
    private final JTextField NAME_TEXT_FIELD = new JTextField();
    private final JSpinner PRICE_NUM_FIELD = new JSpinner();
    private final JSpinner STOCK_NUM_FIELD = new JSpinner();
    private final JComboBox<String> CATEGORY_BOX = new JComboBox<>(
            new DefaultComboBoxModel<>(ProductData.categories));
    private JLabel UPLOAD_IMAGE_BUTTON = new JLabel("Upload Image") {

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

    private final JLabel EDIT_SAVE_BUTTON = new JLabel("Edit") {
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

    private final JLabel DELETE_CANCEL_BUTTON = new JLabel("Delete") {
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

    private final File[] imageFile = new File[1];
    private boolean isClickedADD_BUTTON = false;
    private boolean isEditButton = true;

    public ProductsPanel() {
        initComponents();
    }

    private void enableAdminPanelFields(boolean state) {
        UPLOAD_IMAGE_BUTTON.setVisible(state);
        NAME_TEXT_FIELD.setEnabled(state);
        PRICE_NUM_FIELD.setEnabled(state);
        STOCK_NUM_FIELD.setEnabled(state);
        CATEGORY_BOX.setEnabled(state);
    }

    private void changeButtonsState(boolean state) {
        if (isClickedADD_BUTTON && EDIT_SAVE_BUTTON.getText().equals("Save")) {
            isClickedADD_BUTTON = !isClickedADD_BUTTON;
            ADD_BUTTON.getMouseListeners()[0].mouseExited(null);
        }

        isEditButton = !state;
        EDIT_SAVE_BUTTON.setText(state ? "Save" : "Edit");
        DELETE_CANCEL_BUTTON.setText(state ? "Cancel" : "Delete");
    }

    private JLabel addDivider(int x, int y, int w, int h) {
        JLabel divider = new JLabel();

        divider.setBackground(Colors.GRAY);
        divider.setOpaque(true);
        divider.setBounds(x, y, w, h);
        divider.setVisible(true);

        return divider;
    }

    private ProductData getNewProductDetails() {
        String name = NAME_TEXT_FIELD.getText();
        int price = ((int) ((Number) PRICE_NUM_FIELD.getValue()).floatValue()) * 100;
        int stock = Integer.valueOf(STOCK_NUM_FIELD.getValue().toString());
        String category = CATEGORY_BOX.getSelectedItem().toString();
        byte[] imageInByte = null;
        ProductData newProduct = null;

        try {

            Icon icon = PRODUCT_IMAGE.getIcon();
            BufferedImage bufferedImage = new BufferedImage(
                    icon.getIconWidth(),
                    icon.getIconHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            icon.paintIcon(null, g2d, 0, 0);
            g2d.dispose();
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            byteArrayOutputStream.flush();
            imageInByte = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();

            newProduct = new ProductData(name, price, stock,
                    category, imageInByte, ProductData.NO_ID_YET);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return newProduct;
        }
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        final FunctionWithOneBooleanParameterButNoReturnValue ENABLE_ADMIN_PANEL_FIELDS = (state) -> {
            UPLOAD_IMAGE_BUTTON.setVisible(state);
            NAME_TEXT_FIELD.setEnabled(state);
            PRICE_NUM_FIELD.setEnabled(state);
            STOCK_NUM_FIELD.setEnabled(state);
            CATEGORY_BOX.setEnabled(state);
        };
        //\n
        final javax.swing.JPanel CATEGORY_PANEL = new javax.swing.JPanel();
        final javax.swing.JScrollPane SCROLL_PANE = new javax.swing.JScrollPane();
        final javax.swing.JPanel PRODUCT_CARDS_CONTAINER = new javax.swing.JPanel();
        final javax.swing.JPanel EDIT_PRODUCT_PANEL = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(1000, 530));
        setMinimumSize(new java.awt.Dimension(1000, 530));
        setLayout(null);

        CATEGORY_PANEL.setBackground(Colors.WHITE);
        CATEGORY_PANEL.setMaximumSize(new java.awt.Dimension(50, 529));
        CATEGORY_PANEL.setMinimumSize(new java.awt.Dimension(50, 529));
        CATEGORY_PANEL.setPreferredSize(new java.awt.Dimension(50, 529));
        CATEGORY_PANEL.setLayout(new GridBagLayout());
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
            //TODO: add code
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
        //TODO: add code
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
        //TODO: add code
    }
    });
    button.setVisible(true);
    CATEGORY_PANEL.add(button, constraints);
    }
    add(CATEGORY_PANEL);
    CATEGORY_PANEL.setBounds(0, 0, 50, 529);

    SCROLL_PANE.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    SCROLL_PANE.setMaximumSize(new java.awt.Dimension(700, 530));
    SCROLL_PANE.setMinimumSize(new java.awt.Dimension(700, 530));
    SCROLL_PANE.setPreferredSize(new java.awt.Dimension(700, 530));

    PRODUCT_CARDS_CONTAINER.setPreferredSize(SCROLL_PANE.getPreferredSize());
    SCROLL_PANE.setViewportView(PRODUCT_CARDS_CONTAINER);

    add(SCROLL_PANE);
    SCROLL_PANE.setBounds(50, 0, 700, 530);

    EDIT_PRODUCT_PANEL.setPreferredSize(new java.awt.Dimension(250, 530));
    EDIT_PRODUCT_PANEL.setLayout(null);
    EDIT_PRODUCT_PANEL.add(addDivider(0, 0, 1, 530));

    // initialize ADD_BUTTON
    {
        ADD_BUTTON.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                isClickedADD_BUTTON = !isClickedADD_BUTTON;

                PRODUCT_IMAGE.setIcon(null);
                NAME_TEXT_FIELD.setText("");
                PRICE_NUM_FIELD.setValue(0);
                STOCK_NUM_FIELD.setValue(0);
                CATEGORY_BOX.setSelectedIndex(0);

                ENABLE_ADMIN_PANEL_FIELDS.call(isClickedADD_BUTTON);
                changeButtonsState(isClickedADD_BUTTON);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ADD_BUTTON.setIcon(BLUE_ADD_ICON);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isClickedADD_BUTTON) {
                    ADD_BUTTON.setIcon(ADD_ICON);
                }
            }
        });
        ADD_BUTTON.setBounds(200, 9, 36, 36);
        ADD_BUTTON.setVisible(true);
        EDIT_PRODUCT_PANEL.add(ADD_BUTTON);
    }

    // initialize PRODUCT_IMAGE
    {
        PRODUCT_IMAGE.setBackground(Colors.GRAY); // temp la ini
        PRODUCT_IMAGE.setOpaque(true);
        PRODUCT_IMAGE.setBounds(50, 50, 150, 150);
        PRODUCT_IMAGE.setVisible(true);
        EDIT_PRODUCT_PANEL.add(PRODUCT_IMAGE);

        UPLOAD_IMAGE_BUTTON.addMouseListener(new MouseAdapter() {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PNG, JPG, JPEG", "png", "jpg", "jpeg");

            {
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setFileFilter(filter);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                UPLOAD_IMAGE_BUTTON.setBackground(Colors.BLUE);
                UPLOAD_IMAGE_BUTTON.setForeground(Colors.WHITE);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                UPLOAD_IMAGE_BUTTON.setBackground(Colors.WHITE_HOVER);
                UPLOAD_IMAGE_BUTTON.setForeground(Colors.BLACK);

                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    imageFile[0] = fileChooser.getSelectedFile();

                    ImageIcon icon = new ImageIcon(imageFile[0].getAbsolutePath());
                    icon = new ImageIcon(icon.getImage().getScaledInstance(
                        PRODUCT_IMAGE.getWidth(), PRODUCT_IMAGE.getHeight(),
                        Image.SCALE_SMOOTH));

                PRODUCT_IMAGE.setIcon(icon);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            UPLOAD_IMAGE_BUTTON.setBackground(Colors.WHITE_HOVER);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            UPLOAD_IMAGE_BUTTON.setBackground(Colors.WHITE);
        }
    });
    UPLOAD_IMAGE_BUTTON.setBackground(Colors.WHITE); // temp la ini
    UPLOAD_IMAGE_BUTTON.setOpaque(false);
    UPLOAD_IMAGE_BUTTON.setHorizontalAlignment(JLabel.CENTER);
    UPLOAD_IMAGE_BUTTON.setVerticalAlignment(JLabel.CENTER);
    UPLOAD_IMAGE_BUTTON.setBounds(75, 210, 100, 25);
    UPLOAD_IMAGE_BUTTON.setVisible(false);
    EDIT_PRODUCT_PANEL.add(UPLOAD_IMAGE_BUTTON);
    }

    // initialize NAME_TEXT_FIELD
    {
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(25, 260, 50, 25);
        nameLabel.setVisible(true);
        EDIT_PRODUCT_PANEL.add(nameLabel);

        NAME_TEXT_FIELD.setBounds(25, 285, 200, 25);
        NAME_TEXT_FIELD.setEnabled(false);
        NAME_TEXT_FIELD.setVisible(true);
        EDIT_PRODUCT_PANEL.add(NAME_TEXT_FIELD);
    }

    // initialize PRICE_NUM_FIELD
    {
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(25, 320, 50, 25);
        priceLabel.setVisible(true);
        EDIT_PRODUCT_PANEL.add(priceLabel);

        PRICE_NUM_FIELD.setBounds(25, 345, 95, 25);
        PRICE_NUM_FIELD.setEnabled(false);
        PRICE_NUM_FIELD.setVisible(true);
        EDIT_PRODUCT_PANEL.add(PRICE_NUM_FIELD);
    }

    // initialize STOCK_NUM_FIELD
    {
        JLabel stockLabel = new JLabel("Stock");
        stockLabel.setBounds(130, 320, 50, 25);
        stockLabel.setVisible(true);
        EDIT_PRODUCT_PANEL.add(stockLabel);

        STOCK_NUM_FIELD.setBounds(130, 345, 95, 25);
        STOCK_NUM_FIELD.setEnabled(false);
        STOCK_NUM_FIELD.setVisible(true);
        EDIT_PRODUCT_PANEL.add(STOCK_NUM_FIELD);
    }

    // initialize CATEGORY_BOX
    {
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(25, 380, 75, 25);
        categoryLabel.setVisible(true);
        EDIT_PRODUCT_PANEL.add(categoryLabel);

        CATEGORY_BOX.setBounds(25, 405, 200, 25);
        CATEGORY_BOX.setEnabled(false);
        CATEGORY_BOX.setFocusable(false);
        CATEGORY_BOX.setVisible(true);
        EDIT_PRODUCT_PANEL.add(CATEGORY_BOX);
    }

    // initialize edit/save button
    {
        EDIT_SAVE_BUTTON.addMouseListener(new MouseAdapter() {
            boolean isValidInputs;

            @Override
            public void mouseReleased(MouseEvent e) {
                EDIT_SAVE_BUTTON.setBackground(Colors.WHITE_HOVER);
                EDIT_SAVE_BUTTON.setForeground(Colors.BLACK);

                if (!isEditButton) {
                    isValidInputs = checkIfValidInputs();
                }

                if (!isValidInputs && !isEditButton) {
                    JOptionPane.showMessageDialog(null,
                        "Please input a proper value.");
                    return;
                }

                if (!isEditButton) {
                    // TODO: add code
                }

                changeButtonsState(isEditButton);
                enableAdminPanelFields(!isEditButton);

            }

            @Override
            public void mousePressed(MouseEvent e) {
                EDIT_SAVE_BUTTON.setBackground(Colors.BLUE);
                EDIT_SAVE_BUTTON.setForeground(Colors.WHITE);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                EDIT_SAVE_BUTTON.setBackground(Colors.WHITE_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                EDIT_SAVE_BUTTON.setBackground(Colors.WHITE);
            }

            boolean checkIfValidInputs() {
                return ((PRODUCT_IMAGE.getIcon() != null)
                    && !NAME_TEXT_FIELD.getText().equals("")
                    && (((Number) PRICE_NUM_FIELD.getValue()).floatValue() != 0.0)
                    && CATEGORY_BOX.getSelectedIndex() != 0);
            }

        });
        EDIT_SAVE_BUTTON.setBackground(Colors.WHITE);
        EDIT_SAVE_BUTTON.setOpaque(false);
        EDIT_SAVE_BUTTON.setHorizontalAlignment(JLabel.CENTER);
        EDIT_SAVE_BUTTON.setVerticalAlignment(JLabel.CENTER);
        EDIT_SAVE_BUTTON.setBounds(25, 455, 95, 50);
        EDIT_SAVE_BUTTON.setVisible(true);
        EDIT_PRODUCT_PANEL.add(EDIT_SAVE_BUTTON);

    }

    // initialize delete/cancel button
    {
        DELETE_CANCEL_BUTTON.addMouseListener(new MouseAdapter() {
            boolean isDeleteButton = true;

            @Override
            public void mouseReleased(MouseEvent e) {
                DELETE_CANCEL_BUTTON.setBackground(Colors.WHITE_HOVER);
                DELETE_CANCEL_BUTTON.setForeground(Colors.BLACK);

                // TODO: add code

            }

            @Override
            public void mousePressed(MouseEvent e) {
                DELETE_CANCEL_BUTTON.setBackground(Colors.BLUE);
                DELETE_CANCEL_BUTTON.setForeground(Colors.WHITE);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                DELETE_CANCEL_BUTTON.setBackground(Colors.WHITE_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                DELETE_CANCEL_BUTTON.setBackground(Colors.WHITE);
            }

        });

        DELETE_CANCEL_BUTTON.setBackground(Colors.WHITE);
        DELETE_CANCEL_BUTTON.setOpaque(false);
        DELETE_CANCEL_BUTTON.setHorizontalAlignment(JLabel.CENTER);
        DELETE_CANCEL_BUTTON.setVerticalAlignment(JLabel.CENTER);
        DELETE_CANCEL_BUTTON.setBounds(130, 455, 95, 50);
        DELETE_CANCEL_BUTTON.setVisible(true);
        EDIT_PRODUCT_PANEL.add(DELETE_CANCEL_BUTTON);
    }
    add(EDIT_PRODUCT_PANEL);
    EDIT_PRODUCT_PANEL.setBounds(750, 0, 250, 530);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
