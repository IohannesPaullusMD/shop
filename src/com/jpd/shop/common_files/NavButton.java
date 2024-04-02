package com.jpd.shop.common_files;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class NavButton extends JLayeredPane implements MouseListener {
    private static NavButton[] lastClickedNavBarButton = new NavButton[1];
    private static NavButton[] lastClickedProductCategoryButton = new NavButton[1];

    public static void changeLastClickedButton(NavButton button) {
        NavButton lastClickedButton[] = (button.isNavBarButton
                ? lastClickedNavBarButton
                : lastClickedProductCategoryButton);

        lastClickedButton[0].setIcon(lastClickedButton[0].icon);
        lastClickedButton[0].clickedIndicator.setVisible(false);

        lastClickedButton[0] = button;

        lastClickedButton[0].setIcon(lastClickedButton[0].blueIcon);
        lastClickedButton[0].clickedIndicator.setVisible(true);
    }

    public JLabel clickedIndicator;
    public JLabel button;
    private boolean isNavBarButton;
    private Icon icon;
    private Icon blueIcon;

    public NavButton() {
        this(null, null, true);
    }

    public NavButton(Icon icon, Icon blueIcon, boolean isNavBarButton) {
        this.icon = icon;
        this.blueIcon = blueIcon;
        this.isNavBarButton = isNavBarButton;
        NavButton[] lastClickedButton = getLastClickedButton();
        if (lastClickedButton[0] == null) {
            lastClickedButton[0] = this;
        }
        initializeButton();
        initializeClickedIndicator(isNavBarButton);
        initializeThisClass();
    }

    public void setIcon(Icon icon) {
        button.setIcon(icon);
    }

    public Icon getIcon() {
        return button.getIcon();
    }

    private NavButton[] getLastClickedButton() {
        return (this.isNavBarButton
                ? lastClickedNavBarButton
                : lastClickedProductCategoryButton);
    }

    private void initializeButton() {
        button = new JLabel();

        button.setBounds(0, 0, 50, 50);
        button.setVerticalAlignment(JLabel.CENTER);
        button.setHorizontalAlignment(JLabel.CENTER);
        button.setBackground(Colors.WHITE_HOVER);
        button.setOpaque(false);
        button.setVisible(true);
        this.add(button, JLayeredPane.DEFAULT_LAYER);
    }

    private void initializeClickedIndicator(boolean isNavBarButton) {
        clickedIndicator = new JLabel();

        clickedIndicator.setBounds(
                (isNavBarButton ? 0 : 45), 0,
                (isNavBarButton ? 50 : 5),
                (isNavBarButton ? 5 : 50));
        clickedIndicator.setBackground(Colors.BLUE);
        clickedIndicator.setOpaque(true);
        clickedIndicator.setVisible(false);
        this.add(clickedIndicator, JLayeredPane.PALETTE_LAYER);

    }

    private void initializeThisClass() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(50, 50));
        this.addMouseListener(this);
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        NavButton.changeLastClickedButton(this);

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // if (!this.equals(getLastClickedButton()[0])) {
        button.setOpaque(true);
        button.repaint();
        // }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        button.setOpaque(false);
        button.repaint();
    }
}
