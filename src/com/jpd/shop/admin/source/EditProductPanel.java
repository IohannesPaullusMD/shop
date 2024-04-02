/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.jpd.shop.admin.source;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
import com.jpd.shop.common_files.ProductData;

@SuppressWarnings("finally")
/**
 *
 * @author jpd
 */
public class EditProductPanel extends javax.swing.JPanel {

    private ImageIcon addIcon = new ImageIcon(getClass().getResource(
            "/com/jpd/shop/common_files/icons/add_button.png"));
    private ImageIcon blueAddIcon = new ImageIcon(getClass().getResource(
            "/com/jpd/shop/common_files/icons/blue_add_button.png"));

    private File[] imageFile = new File[1];
    private boolean isClickedAddButton = false;
    private boolean isEditButton = true;

    private MainPanel mainPanelRef;
    private JLabel addButton = new JLabel(addIcon);
    private JLabel productImage = new JLabel();
    private JLabel uploadImageButton;
    private JTextField nameTextField = new JTextField();
    private JSpinner priceNumField = new JSpinner();
    private JSpinner stockNumField = new JSpinner();
    private JComboBox<String> categoryBox = new JComboBox<>(
            new DefaultComboBoxModel<>(ProductData.categories));
    private JLabel editSaveButton;
    private JLabel deleteCancelButton;

    /**
     * Creates new form EditProductPanel
     */
    public EditProductPanel() {
        this(null);
    }

    public EditProductPanel(MainPanel mainPanelRef) {
        this.mainPanelRef = mainPanelRef;
        initializeComponents();
    }

    private void initializeComponents() {
        this.add(addDivider(0, 0, 1, 530));

        // initialize addButton
        {
            addButton.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseReleased(MouseEvent e) {
                    isClickedAddButton = !isClickedAddButton;

                    clearAdminPanelFields();
                    enableAdminPanelFields(isClickedAddButton);
                    changeButtonsState(isClickedAddButton);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    addButton.setIcon(blueAddIcon);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!isClickedAddButton) {
                        addButton.setIcon(addIcon);
                    }
                }
            });
            addButton.setBounds(200, 9, 36, 36);
            addButton.setVisible(true);
            this.add(addButton);
        }

        // initialize productImage
        {
            uploadImageButton = new JLabel("Upload Image") {
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

            productImage.setBackground(Colors.GRAY); // temp la ini
            productImage.setOpaque(true);
            productImage.setBounds(50, 50, 150, 150);
            productImage.setVisible(true);
            this.add(productImage);

            uploadImageButton.addMouseListener(new MouseAdapter() {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "PNG, JPG, JPEG", "png", "jpg", "jpeg");

                {
                    fileChooser.setAcceptAllFileFilterUsed(false);
                    fileChooser.setFileFilter(filter);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    uploadImageButton.setBackground(Colors.BLUE);
                    uploadImageButton.setForeground(Colors.WHITE);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    uploadImageButton.setBackground(Colors.WHITE_HOVER);
                    uploadImageButton.setForeground(Colors.BLACK);

                    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        imageFile[0] = fileChooser.getSelectedFile();

                        ImageIcon icon = new ImageIcon(imageFile[0].getAbsolutePath());
                        icon = new ImageIcon(icon.getImage().getScaledInstance(
                                productImage.getWidth(), productImage.getHeight(),
                                Image.SCALE_SMOOTH));

                        productImage.setIcon(icon);
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    uploadImageButton.setBackground(Colors.WHITE_HOVER);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    uploadImageButton.setBackground(Colors.WHITE);
                }
            });
            uploadImageButton.setBackground(Colors.WHITE); // temp la ini
            uploadImageButton.setOpaque(false);
            uploadImageButton.setHorizontalAlignment(JLabel.CENTER);
            uploadImageButton.setVerticalAlignment(JLabel.CENTER);
            uploadImageButton.setBounds(75, 210, 100, 25);
            uploadImageButton.setVisible(false);
            this.add(uploadImageButton);
        }

        // initialize nameTextField
        {
            JLabel nameLabel = new JLabel("Name:");
            nameLabel.setBounds(25, 260, 50, 25);
            nameLabel.setVisible(true);
            this.add(nameLabel);

            nameTextField.setBounds(25, 285, 200, 25);
            nameTextField.setEnabled(false);
            nameTextField.setVisible(true);
            this.add(nameTextField);
        }

        // initialize priceNumField
        {
            JLabel priceLabel = new JLabel("Price:");
            priceLabel.setBounds(25, 320, 50, 25);
            priceLabel.setVisible(true);
            this.add(priceLabel);

            priceNumField.setBounds(25, 345, 95, 25);
            priceNumField.setEnabled(false);
            priceNumField.setVisible(true);
            this.add(priceNumField);
        }

        // initialize stockNumField
        {
            JLabel stockLabel = new JLabel("Stock");
            stockLabel.setBounds(130, 320, 50, 25);
            stockLabel.setVisible(true);
            this.add(stockLabel);

            stockNumField.setBounds(130, 345, 95, 25);
            stockNumField.setEnabled(false);
            stockNumField.setVisible(true);
            this.add(stockNumField);
        }

        // initialize categoryBox
        {
            JLabel categoryLabel = new JLabel("Category:");
            categoryLabel.setBounds(25, 380, 75, 25);
            categoryLabel.setVisible(true);
            this.add(categoryLabel);

            categoryBox.setBounds(25, 405, 200, 25);
            categoryBox.setEnabled(false);
            categoryBox.setFocusable(false);
            categoryBox.setVisible(true);
            this.add(categoryBox);
        }

        // initialize edit/save button
        {
            editSaveButton = new JLabel("Edit") {
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

            editSaveButton.addMouseListener(new MouseAdapter() {
                boolean isValidInputs;

                @Override
                public void mouseReleased(MouseEvent e) {
                    editSaveButton.setBackground(Colors.WHITE_HOVER);
                    editSaveButton.setForeground(Colors.BLACK);

                    if (!isEditButton) {
                        isValidInputs = checkIfValidInputs();
                    }

                    if (!isValidInputs && !isEditButton) {
                        JOptionPane.showMessageDialog(null,
                                "Please input a proper value.");
                        return;
                    }

                    if (!isEditButton) {
                        mainPanelRef.getMainFrameRef().getClient().makeARequestToServer(
                                getNewProductDetails());
                    }

                    changeButtonsState(isEditButton);
                    enableAdminPanelFields(!isEditButton);

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    editSaveButton.setBackground(Colors.BLUE);
                    editSaveButton.setForeground(Colors.WHITE);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    editSaveButton.setBackground(Colors.WHITE_HOVER);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    editSaveButton.setBackground(Colors.WHITE);
                }

                private boolean checkIfValidInputs() {
                    return ((productImage.getIcon() != null)
                            && !nameTextField.getText().equals("")
                            && (((Number) priceNumField.getValue()).floatValue() != 0.0)
                            && categoryBox.getSelectedIndex() != 0);
                }

            });
            editSaveButton.setBackground(Colors.WHITE);
            editSaveButton.setOpaque(false);
            editSaveButton.setHorizontalAlignment(JLabel.CENTER);
            editSaveButton.setVerticalAlignment(JLabel.CENTER);
            editSaveButton.setBounds(25, 455, 95, 50);
            editSaveButton.setVisible(true);
            this.add(editSaveButton);

        }

        // initialize delete/cancel button
        {
            deleteCancelButton = new JLabel("Delete") {
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
            deleteCancelButton.addMouseListener(new MouseAdapter() {
                boolean isDeleteButton = true;

                @Override
                public void mouseReleased(MouseEvent e) {
                    deleteCancelButton.setBackground(Colors.WHITE_HOVER);
                    deleteCancelButton.setForeground(Colors.BLACK);

                    // TODO: add code

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    deleteCancelButton.setBackground(Colors.BLUE);
                    deleteCancelButton.setForeground(Colors.WHITE);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    deleteCancelButton.setBackground(Colors.WHITE_HOVER);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    deleteCancelButton.setBackground(Colors.WHITE);
                }

            });
            deleteCancelButton.setBackground(Colors.WHITE);
            deleteCancelButton.setOpaque(false);
            deleteCancelButton.setHorizontalAlignment(JLabel.CENTER);
            deleteCancelButton.setVerticalAlignment(JLabel.CENTER);
            deleteCancelButton.setBounds(130, 455, 95, 50);
            deleteCancelButton.setVisible(true);
            this.add(deleteCancelButton);
        }

        // initialize this class
        this.setLayout(null);
        this.setBackground(Colors.WHITE);
        this.setBounds(750, 0, 250, 530);
        this.setVisible(true);
    }

    private void enableAdminPanelFields(boolean state) {
        uploadImageButton.setVisible(state);
        nameTextField.setEnabled(state);
        priceNumField.setEnabled(state);
        stockNumField.setEnabled(state);
        categoryBox.setEnabled(state);
    }

    private void clearAdminPanelFields() {
        productImage.setIcon(null);
        nameTextField.setText("");
        priceNumField.setValue(0);
        stockNumField.setValue(0);
        categoryBox.setSelectedIndex(0);
    }

    private void changeButtonsState(boolean state) {
        if (isClickedAddButton && editSaveButton.getText().equals("Save")) {
            isClickedAddButton = !isClickedAddButton;
            addButton.getMouseListeners()[0].mouseExited(null);
        }

        isEditButton = !state;
        editSaveButton.setText(state ? "Save" : "Edit");
        deleteCancelButton.setText(state ? "Cancel" : "Delete");
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
        String name = nameTextField.getText();
        int price = ((int) ((Number) priceNumField.getValue()).floatValue()) * 100;
        int stock = Integer.valueOf(stockNumField.getValue().toString());
        String category = categoryBox.getSelectedItem().toString();
        byte[] imageInByte = null;
        ProductData newProduct = null;

        try {

            Icon icon = productImage.getIcon();
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
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
