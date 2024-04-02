package com.jpd.shop.admin;

import com.jpd.shop.admin.source.MainPanel;
import com.jpd.shop.common_files.MainFrame;
import com.jpd.shop.common_files.MainPanel_Interface;

/**
 *
 * @author jpd
 */
public class AdminEntryPoint {
    public static void main(String[] args) {
        MainPanel_Interface mainPanel = new MainPanel();
        MainFrame mainFrame = new MainFrame(mainPanel, true);

        mainFrame.setVisible(true);
    }
}
