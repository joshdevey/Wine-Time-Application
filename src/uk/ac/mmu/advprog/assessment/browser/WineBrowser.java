package uk.ac.mmu.advprog.assessment.browser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class WineBrowser extends JFrame {

    private final SearchPanel searchPanel;
    private JTable resultsTable;
    private WineDetail detailPanel;
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
            Object[] obj = {wine.name, wine.type, wine.winery, wine.country, wine.aBV};

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

        setLocationRelativeTo(null);
        setVisible(true);

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

        wines.add(new Wine("Espumante Moscatel", "Sparkling", "Casa Perini", "Brazil", "7.5"));
        wines.add(new Wine("Ancellotta", "Red", "Casa Perini", "Brazil", "12.0"));
        wines.add(new Wine("Cabernet Sauvignon", "Red", "Castellamare", "Brazil", "12.0"));
        wines.add(new Wine("Virtus Moscato", "White", "Monte Paschoal", "Brazil", "12.0"));
        wines.add(new Wine("Maison de Ville Cabernet-Merlot", "Red", "Aurora", "Brazil", "11.0"));
        wines.add(new Wine("Reserva Cabernet Sauvignon", "Red", "Aurora", "Brazil", "12.5"));
        wines.add(new Wine("Do Lugar Moscatel Espumantes", "Sparkling", "Dal Pizzol", "Brazil", "7.5"));
        wines.add(new Wine("Paradoxo Cabernet Sauvignon", "Red", "Salton", "Brazil", "13.5"));
        wines.add(new Wine("Seleção Cabernet Sauvignon-Merlot", "Red", "Miolo", "Brazil", "12.5"));
        wines.add(new Wine("Defesa Tinto", "Red", "Esporão", "Portugal", "14.0"));

        return wines;
    }

}