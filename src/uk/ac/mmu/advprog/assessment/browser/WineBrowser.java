package uk.ac.mmu.advprog.assessment.browser;


import uk.ac.mmu.advprog.assessment.shared.Queries;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WineBrowser extends JFrame {

    private final SearchPanel searchPanel;
    private JTable resultsTable;
    private final WineDetail detailPanel;
    private ArrayList<Wine> searchResultsData;
    private Queries queries = new Queries("jdbc:sqlite:data/winetime.db", false);
    public JButton searchButton = new JButton("Search");

    public WineBrowser() {
        this.searchPanel = new SearchPanel();
        this.detailPanel = new WineDetail();
        this.searchResultsData = new ArrayList<>();
    }

    public void displaySearch() {
        setTitle("Wine Browser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 820);
        setMinimumSize(new Dimension(800, 820));
        add(searchPanel, "West");
        renderSearchButtons();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void handleResultsTable(ArrayList<Wine> results) {

        String[] columnNames = {"Name", "Type", "Winery", "Country", "ABV"};
        this.searchResultsData = results;
        resultsTable = new JTable();

        //make table read only
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Wine wine : results) {
            Object[] obj = {wine.name, wine.type, wine.winery, wine.country, wine.abv};

            tableModel.addRow(obj);
        }

        resultsTable.setModel(tableModel);

        resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultsTable.setSelectionBackground(new Color(250, 108, 14));

        resultsTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                updateFromSelection();
            }
        });

        resultsTable.getSelectedRow();

        JScrollPane sp = new JScrollPane(resultsTable);
        sp.setMaximumSize(new Dimension(100, 100));
        sp.setAutoscrolls(true);

        add(sp, "Center");
        revalidate();
    }

    public void updateFromSelection() {
        if (resultsTable.getSelectedRow() == -1) {
            this.detailPanel.clearData();
            remove(detailPanel);
            revalidate();
            return;
        }

        Wine wineToAdd = queries.getWine(this.searchResultsData.get(resultsTable.getSelectedRow()).id);

        if (wineToAdd != null) {
            detailPanel.setData(wineToAdd);
            add(detailPanel, "East");
            revalidate();
        }
    }

    public void renderSearchButtons() {

        searchButton.setBackground(new Color(250, 108, 14));
        searchButton.setForeground(Color.WHITE);
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setOpaque(true);

        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fetchData();
            }
        });

        add(searchButton, "South");
    }

    public void fetchData() {
        handleResultsTable(queries.getInitialWines());
    }

}