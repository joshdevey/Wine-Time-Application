package uk.ac.mmu.advprog.assessment.browser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class WineBrowser extends JFrame {

    private final SearchPanel searchPanel;
    private JTable resultsTable;
    private final WineDetail detailPanel;
    private String[][] searchResultsData = {};

    public WineBrowser() {
//        String[][] data = {};
        String[][] data = {
                {"Espumante Moscatel", "Sparkling", "Casa Perini", "Brazil", "7.5"},
                {"Ancellotta", "Red", "Casa Perini", "Brazil", "12.0"},
                {"Cabernet Sauvignon", "Red", "Castellamare", "Brazil", "12.0"},
                {"Virtus Moscato", "White", "Monte Paschoal", "Brazil", "12.0"},
                {"Maison de Ville Cabernet-Merlot", "Red", "Aurora", "Brazil", "11.0"},
                {"Reserva Cabernet Sauvignon", "Red", "Aurora", "Brazil", "12.5"},
                {"Do Lugar Moscatel Espumantes", "Sparkling", "Dal Pizzol", "Brazil", "7.5"},
                {"Paradoxo Cabernet Sauvignon", "Red", "Salton", "Brazil", "13.5"},
                {"Seleção Cabernet Sauvignon-Merlot", "Red", "Miolo", "Brazil", "12.5"},
                {"Defesa Tinto", "Red", "Esporão", "Portugal", "14.0"}
        };

        this.searchPanel = new SearchPanel();
        this.detailPanel = new WineDetail();
        this.searchResultsData = data;
    }

    public void displaySearch() {
        setTitle("Wine Browser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 820);

        add(searchPanel, "West");

        if(this.searchResultsData.length > 0) {
            String[] columnNames = { "Name", "Type", "Winery", "Country", "ABV" };
            resultsTable = new JTable(searchResultsData, columnNames);

            //make table read only
            DefaultTableModel tableModel = new DefaultTableModel(searchResultsData, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                  return false;
                };
            };

            resultsTable.setModel(tableModel);

            JScrollPane sp = new JScrollPane(resultsTable);
            sp.setMaximumSize(new Dimension(100, 100));
            sp.setAutoscrolls(true);

            add(sp, "Center");
        }

        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void search() {
        System.out.println("Test");
    }

}