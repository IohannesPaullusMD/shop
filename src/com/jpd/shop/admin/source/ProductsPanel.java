package com.jpd.shop.admin.source;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import com.jpd.shop.common_files.data_types.Client;
import com.jpd.shop.common_files.data_types.JLabelWithCurvedBorder;
import com.jpd.shop.common_files.data_types.MyPanel_Interface;
import com.jpd.shop.common_files.Colors;
import com.jpd.shop.common_files.data_types.NavButton;
import com.jpd.shop.common_files.ProductCard.ProductCard;
import com.jpd.shop.common_files.data_types.ProductData;
import com.jpd.shop.common_files.functional_interfaces.FunctionWithFourIntegerParametersButReturnsJLabel;
import com.jpd.shop.common_files.functional_interfaces.FunctionWithNoParameterButReturnsProductData;
import com.jpd.shop.common_files.functional_interfaces.FunctionWithOneBooleanParameterButNoReturnValue;

@SuppressWarnings("finally")
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

    private final JLabel ADD_BUTTON = new JLabel();
    private final JLabel PRODUCT_IMAGE = new JLabel();
    private final JLabel UPLOAD_IMAGE_BUTTON = new JLabelWithCurvedBorder("Upload Image");
    private final JTextField NAME_TEXT_FIELD = new JTextField();
    private final JSpinner PRICE_NUM_FIELD = new JSpinner();
    private final JSpinner STOCK_NUM_FIELD = new JSpinner();
    private final JComboBox<String> CATEGORY_BOX = new JComboBox<>(
            new DefaultComboBoxModel<>(ProductData.categories));
    private final JLabel EDIT_SAVE_BUTTON = new JLabelWithCurvedBorder("Edit");
    private final JLabel DELETE_CANCEL_BUTTON = new JLabelWithCurvedBorder("Delete");

    private boolean isClickedADD_BUTTON = false;
    private final FlowLayout layout = new FlowLayout(
            FlowLayout.LEFT, 20, 20);
    private ProductData displayedProductData;

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

            if (EDIT_SAVE_BUTTON.getText().equals("Save")) {
                changeButtonState(false);
                enableAdminPanelFields(false);
            }
        }

        products = (ProductData[]) object;

        for (ProductData productData : products) {
            ProductCard productCard = new ProductCard(productData);
            productCard.getProductCardTemplate().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    displayProductDetails(productCard.PRODUCT_DATA);
                    changeButtonState(false);
                    enableAdminPanelFields(false);
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
        PRODUCT_CARDS_CONTAINER.setLayout(layout);

        PRODUCT_CARDS_CONTAINER.revalidate();
        PRODUCT_CARDS_CONTAINER.repaint();

        SCROLL_PANE.revalidate();
        SCROLL_PANE.repaint();

        displayProductDetails(products.length > 0
                ? ProductCard.getSelectedCard().PRODUCT_DATA
                : null);

        System.gc();
    }

    private void displayProductDetails(ProductData productData) {
        PRODUCT_IMAGE.setIcon(productData != null
                ? new ImageIcon(productData.image())
                : null);

        NAME_TEXT_FIELD.setText(productData != null
                ? productData.name()
                : "");

        PRICE_NUM_FIELD.setValue(productData != null
                ? productData.price() / 100.0f
                : 0);

        STOCK_NUM_FIELD.setValue(productData != null
                ? productData.stock()
                : 0);

        CATEGORY_BOX.setSelectedIndex(productData != null
                ? productData.category()
                : 0);

        if (productData != null) {
            displayedProductData = productData;
        }
    }

    private void changeButtonState(boolean state) {
        if (isClickedADD_BUTTON && EDIT_SAVE_BUTTON.getText().equals("Edit")) {
            isClickedADD_BUTTON = !isClickedADD_BUTTON;
            ADD_BUTTON.getMouseListeners()[0].mouseExited(null);
        }

        EDIT_SAVE_BUTTON.setText(state ? "Save" : "Edit");
        DELETE_CANCEL_BUTTON.setText(state ? "Cancel" : "Delete");
    }

    private void enableAdminPanelFields(boolean state) {
        UPLOAD_IMAGE_BUTTON.setVisible(state);
        NAME_TEXT_FIELD.setEnabled(state);
        PRICE_NUM_FIELD.setEnabled(state);
        STOCK_NUM_FIELD.setEnabled(state);
        CATEGORY_BOX.setEnabled(state);
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
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMaximumSize(new java.awt.Dimension(1000, 530));
        setMinimumSize(new java.awt.Dimension(1000, 530));
        setLayout(null);

        CATEGORY_PANEL.setBackground(Colors.WHITE);
        CATEGORY_PANEL.setMaximumSize(new java.awt.Dimension(50, 529));
        CATEGORY_PANEL.setMinimumSize(new java.awt.Dimension(50, 529));
        CATEGORY_PANEL.setPreferredSize(new java.awt.Dimension(50, 529));
        final ImageIcon ADD_ICON = new ImageIcon(getClass().getResource(
                "/com/jpd/shop/common_files/icons/add_button.png"));
        final ImageIcon BLUE_ADD_ICON = new ImageIcon(getClass().getResource(
                "/com/jpd/shop/common_files/icons/blue_add_button.png"));

        //

        final FunctionWithFourIntegerParametersButReturnsJLabel ADD_DIVIDER_LINE = (x, y, w, h) -> {
            JLabel divider = new JLabel();

            divider.setBackground(Colors.GRAY);
            divider.setOpaque(true);
            divider.setBounds(x, y, w, h);
            divider.setVisible(true);

            return divider;
        };

        final FunctionWithNoParameterButReturnsProductData GET_NEW_PRODUCT_DATA = () -> {
            String name = NAME_TEXT_FIELD.getText();
            int price = (int) (((Number) PRICE_NUM_FIELD.getValue()).floatValue() * 100);
            int stock = Integer.valueOf(STOCK_NUM_FIELD.getValue().toString());
            int category = CATEGORY_BOX.getSelectedIndex();
            byte[] imageInByte = null;
            int id = (!isClickedADD_BUTTON)
                    ? displayedProductData.id()
                    : ProductData.NO_ID_YET;
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
                        category, imageInByte, id);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return newProduct;
            }

        };

        //
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
        CATEGORY_PANEL.setBounds(0, 0, 50, 529);

        SCROLL_PANE.setBorder(null);
        SCROLL_PANE.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        SCROLL_PANE.setMaximumSize(new java.awt.Dimension(700, 530));
        SCROLL_PANE.setMinimumSize(new java.awt.Dimension(700, 530));
        SCROLL_PANE.setPreferredSize(new java.awt.Dimension(700, 530));

        PRODUCT_CARDS_CONTAINER.setBackground(Colors.WHITE_BACKGROUND);
        PRODUCT_CARDS_CONTAINER.setMinimumSize(SCROLL_PANE.getPreferredSize());
        PRODUCT_CARDS_CONTAINER.setPreferredSize(SCROLL_PANE.getPreferredSize());
        PRODUCT_CARDS_CONTAINER.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        PRODUCT_CARDS_CONTAINER.setVisible(true);
        SCROLL_PANE.setViewportView(PRODUCT_CARDS_CONTAINER);

        add(SCROLL_PANE);
        SCROLL_PANE.setBounds(50, 0, 700, 530);
        SCROLL_PANE.getVerticalScrollBar().setUnitIncrement(16);

        EDIT_PRODUCT_PANEL.setPreferredSize(new java.awt.Dimension(250, 530));
        EDIT_PRODUCT_PANEL.setLayout(null);
        EDIT_PRODUCT_PANEL.add(ADD_DIVIDER_LINE.call(0, 0, 1, 530));

        // initialize ADD_BUTTON
        {
            ADD_BUTTON.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseReleased(MouseEvent e) {
                    isClickedADD_BUTTON = !isClickedADD_BUTTON;

                    displayProductDetails(!isClickedADD_BUTTON
                            ? null
                            : displayedProductData);

                    ProductCard.changeSelectedCard(null);

                    enableAdminPanelFields(isClickedADD_BUTTON);
                    changeButtonState(isClickedADD_BUTTON);
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
            ADD_BUTTON.setIcon(ADD_ICON);
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
                        File imageFile = fileChooser.getSelectedFile();

                        ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
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
                boolean isEditButton;

                @Override
                public void mouseReleased(MouseEvent e) {
                    EDIT_SAVE_BUTTON.setBackground(Colors.WHITE_HOVER);
                    EDIT_SAVE_BUTTON.setForeground(Colors.BLACK);

                    isEditButton = EDIT_SAVE_BUTTON.getText().equals("Edit");

                    if (!isEditButton) {
                        isValidInputs = checkIfValidInputs();
                    }

                    if (!isValidInputs && !isEditButton) {
                        JOptionPane.showMessageDialog(null,
                                "Please input a proper value.");
                        return;
                    }

                    if (!isEditButton) {
                        Client.getInstance()
                                .makeARequestToServer(GET_NEW_PRODUCT_DATA.call());

                        for (MouseListener listener : CATEGORY_PANEL.getComponents()[CATEGORY_BOX.getSelectedIndex()
                                - 1].getMouseListeners()) {

                            listener.mouseReleased(null);
                        }
                        return;
                    }

                    changeButtonState(isEditButton);
                    enableAdminPanelFields(isEditButton);
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

                @Override
                public void mouseReleased(MouseEvent e) {
                    DELETE_CANCEL_BUTTON.setBackground(Colors.WHITE_HOVER);
                    DELETE_CANCEL_BUTTON.setForeground(Colors.BLACK);

                    if (DELETE_CANCEL_BUTTON.getText().equals("Delete")) {
                        Client.getInstance().makeARequestToServer(
                                new ProductData("", 0, 0, 0, null, displayedProductData.id()));
                        loadProducts(displayedProductData.category() + 10);
                    } else {
                        displayProductDetails(displayedProductData);
                        enableAdminPanelFields(false);
                        changeButtonState(false);
                    }

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
    private final javax.swing.JPanel CATEGORY_PANEL = new javax.swing.JPanel();
    private final javax.swing.JPanel EDIT_PRODUCT_PANEL = new javax.swing.JPanel();
    private final javax.swing.JPanel PRODUCT_CARDS_CONTAINER = new javax.swing.JPanel();
    private final javax.swing.JScrollPane SCROLL_PANE = new javax.swing.JScrollPane();
    // End of variables declaration//GEN-END:variables

}
