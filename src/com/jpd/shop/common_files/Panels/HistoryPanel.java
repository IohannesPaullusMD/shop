/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.jpd.shop.common_files.Panels;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;

import com.jpd.shop.common_files.data_types.Client;
import com.jpd.shop.common_files.data_types.JpdTableModel;
import com.jpd.shop.common_files.data_types.MyPanel_Interface;

/**
 *
 * @author jpd
 */
public class HistoryPanel extends javax.swing.JPanel implements MyPanel_Interface {

    private static HistoryPanel historyPanel;

    public static HistoryPanel getInstance() {
        if (historyPanel == null) {
            historyPanel = new HistoryPanel();
        }

        return historyPanel;
    }

    private static final String[] TABLE1_COLUMN_NAMES = { "ID", "Date & Time", "Cashier" };
    private static final String[] TABLE2_COLUMN_NAMES = { "ID", "Product Name", "Price", "Quantity" };

    private final Object[][] TABLE1_DATA;

    private final JTable TABLE1;
    private final JTable TABLE2;

    private HistoryPanel() {
        TABLE1_DATA = (Object[][]) Client.getInstance()
                .makeARequestToServer(Client.GET_TRANSACTION_HISTORY);

        TABLE1 = new JTable(new JpdTableModel(TABLE1_DATA, TABLE1_COLUMN_NAMES));
        TABLE2 = new JTable();

        initTable1();
        initComponents();
        this.setVisible(true);
    }

    @Override
    public void disposePanel() {
        historyPanel = null;

        System.gc();
    }

    private void initTable1() {
        TableColumnModel columnModel = TABLE1.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(150);

        TABLE1.getSelectionModel().addListSelectionListener(
                e -> {
                    if (!e.getValueIsAdjusting()) {
                        loadTable2Data(Integer.parseInt(
                                (String) TABLE1_DATA[TABLE1.getSelectedRow()][0]));
                    }
                });
        TABLE1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TABLE1.setRowSelectionInterval(0, 0);
    }

    private void loadTable2Data(int id) {
        Object[][] table2Data = (Object[][]) Client.getInstance()
                .makeARequestToServer(-id);

        TABLE2.setModel(new JpdTableModel(table2Data, TABLE2_COLUMN_NAMES));
        TABLE2.getColumnModel().getColumn(0).setPreferredWidth(50);
        TABLE2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane1 = new javax.swing.JScrollPane(TABLE1);
        deleteButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        scrollPane2 = new javax.swing.JScrollPane(TABLE2);

        setPreferredSize(new java.awt.Dimension(1000, 530));
        setLayout(null);

        scrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane1.setPreferredSize(new java.awt.Dimension(250, 470));
        add(scrollPane1);
        scrollPane1.setBounds(0, 60, 250, 470);

        deleteButton.setText("Delete");
        deleteButton.setFocusable(false);
        add(deleteButton);
        deleteButton.setBounds(900, 30, 75, 23);

        editButton.setText("Edit");
        editButton.setFocusable(false);
        add(editButton);
        editButton.setBounds(810, 30, 72, 23);

        scrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane2.setPreferredSize(new java.awt.Dimension(725, 470));
        add(scrollPane2);
        scrollPane2.setBounds(270, 60, 725, 470);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JScrollPane scrollPane1;
    private javax.swing.JScrollPane scrollPane2;
    // End of variables declaration//GEN-END:variables
}
