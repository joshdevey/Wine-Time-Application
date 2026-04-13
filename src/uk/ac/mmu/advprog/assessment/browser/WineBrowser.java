package uk.ac.mmu.advprog.assessment.browser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class WineBrowser extends JFrame {

    private final SearchPanel searchPanel;
    private JTable resultsTable;
    private final WineDetail detailPanel;
    private ArrayList<Wine> searchResultsData;

    public WineBrowser() {
        this.searchPanel = new SearchPanel();
        this.detailPanel = new WineDetail();
        this.searchResultsData = getTestData();
    }

    public void displaySearch() {
        setTitle("Wine Browser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 820);
        setMinimumSize(new Dimension(800, 820));
        add(searchPanel, "West");

        handleResultsTable();

        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void handleResultsTable() {

        String[] columnNames = { "Name", "Type", "Winery", "Country", "ABV" };

        resultsTable = new JTable();

        //make table read only
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };

        for(Wine wine: this.searchResultsData) {
            Object[] obj = {wine.name, wine.type, wine.winery, wine.country, wine.abv};

            tableModel.addRow(obj);
        }

        resultsTable.setModel(tableModel);

        resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultsTable.setSelectionBackground(new Color(250, 108, 14));

        resultsTable.getSelectionModel().addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()) {
                updateFromSelection();
            }
        });

        resultsTable.getSelectedRow();

        JScrollPane sp = new JScrollPane(resultsTable);
        sp.setMaximumSize(new Dimension(100, 100));
        sp.setAutoscrolls(true);

        add(sp, "Center");
    }

    public void updateFromSelection() {
        if(resultsTable.getSelectedRow() == -1) {
            this.detailPanel.clearData();
            remove(detailPanel);
            revalidate();
            return;
        }

        detailPanel.setData(this.searchResultsData.get(resultsTable.getSelectedRow()));
        add(detailPanel, "East");
        revalidate();
    }

    public ArrayList<Wine> getTestData() {
        ArrayList<Wine> wines = new ArrayList<>();

        try (Connection c = DriverManager.getConnection("jdbc:sqlite:data/winetime.db")) {

            ResultSet rs = c.createStatement().executeQuery("select w.name, w.type, w.abv, wy.name as winery_name, r.country from Wine as w inner join Winery as wy on w.winery_id = wy.id inner join Region as r on r.id = wy.region_id limit 100");

            while(rs.next()) {
                Wine wine = new Wine(rs.getString("name"), rs.getString("type"), rs.getString("winery_name"), rs.getString("country"), rs.getString("abv"));

                wines.add(wine);

            }

        }
        catch (SQLException se) {
            se.printStackTrace();
        }

        return wines;
    }

}