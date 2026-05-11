package uk.ac.mmu.advprog.assessment.browser;


import uk.ac.mmu.advprog.assessment.shared.Queries;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class WineBrowser extends JFrame {

    private final SearchPanel searchPanel;
    private JTable resultsTable;
    private final WineDetail detailPanel;
    private ArrayList<BrowserWine> searchResultsData;
    private JScrollPane searchResultPanel;
    private final Queries queries = new Queries("jdbc:sqlite:data/winetime.db", false);
    private QueryBuilder queryBuilder;
    public JButton searchButton = new JButton("Search");
    public JButton returnButton = new JButton("Return to Search");

    public WineBrowser() {
        this.searchPanel = new SearchPanel();
        this.detailPanel = new WineDetail();
        this.searchResultsData = new ArrayList<>();
        this.searchResultPanel = new JScrollPane();
    }

    public void displayWineBrowser() {
        setTitle("Wine Browser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 820);
        setMinimumSize(new Dimension(800, 820));
        add(searchPanel, "West");
        add(searchResultPanel, "Center");
        renderSearchButtons();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void handleResultsTable() {

        String[] columnNames = {"Name", "Type", "Winery", "Country", "ABV", "Ratings", "Rating AVG"};
        if(queryBuilder.getGrape().isEmpty()) {
            this.searchResultsData = queries.getWinesFromSearch(queryBuilder);
        } else {
            this.searchResultsData = queries.getWinesFromSearchWithGrape(queryBuilder);
        }

        resultsTable = new JTable();

        //make table read only
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        resultsTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String sortString = getClickedColumn(resultsTable.columnAtPoint(e.getPoint()));
                queryBuilder.setSortColumn(sortString);
                handleResultsTable();
            }
        });

        for (BrowserWine wine : this.searchResultsData) {
            Object[] obj = {wine.name, wine.type, wine.winery, wine.country, wine.abv, wine.ratings, wine.ratingAverage};

            tableModel.addRow(obj);
        }

        resultsTable.setModel(tableModel);

        resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultsTable.setSelectionBackground(new Color(250, 108, 14));

        resultsTable.getSelectionModel().addListSelectionListener(e -> {
//            if (!e.getValueIsAdjusting()) {
                updateFromSelection();
//            }
        });

        resultsTable.getSelectedRow();
        remove(this.searchResultPanel);
        this.searchResultPanel = new JScrollPane(resultsTable);
        searchResultPanel.setMaximumSize(new Dimension(100, 100));
        searchResultPanel.setAutoscrolls(true);

        add(searchResultPanel, "Center");
        revalidate();
    }

    public void updateFromSelection() {
        if (resultsTable.getSelectedRow() == -1) {
            this.detailPanel.clearData();
            remove(detailPanel);
            revalidate();
            return;
        }

        SelectedWine wineToAdd = queries.getWine(this.searchResultsData.get(resultsTable.getSelectedRow()).id);

        if (wineToAdd != null) {
            detailPanel.setData(wineToAdd);
            add(detailPanel, "Center");
            remove(searchButton);
            remove(this.searchResultPanel);
            remove(this.searchPanel);
            renderReturnToSearch();
            revalidate();
            repaint();
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

    public void returnToSearch() {
        detailPanel.clearData();
        remove(detailPanel);
        remove(returnButton);
        add(searchButton, "South");
        add(this.searchResultPanel, "Center");
        add(this.searchPanel, "West");

        revalidate();
        repaint();
    }

    public void renderReturnToSearch() {
        returnButton.setBackground(new Color(250, 108, 14));
        returnButton.setForeground(Color.WHITE);
        returnButton.setBorderPainted(false);
        returnButton.setFocusPainted(false);
        returnButton.setOpaque(true);

        returnButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                returnToSearch();
            }
        });

        add(returnButton, "South");
    }

    public void fetchData() {
        this.detailPanel.clearData();
        remove(detailPanel);
        revalidate();
        repaint();
        if(searchPanel.validateSearch()) {
            this.queryBuilder = searchPanel.getQuery();
            handleResultsTable();
        } else {
            JOptionPane.showMessageDialog(null, "At least one of the fields (Name, Winery, Type, Country of Origin, Blend, Grape, Acidity or Body) must be populated.", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
    }
    private String getClickedColumn(int id) {

        return switch (id) {
            case 1 -> "w.type";
            case 2 -> "wy.name";
            case 3 -> "r.country";
            case 4 -> "w.abv";
            case 5 -> "rt.ratings";
            case 6 -> "rt.ratingAverage";
            default -> "w.name";
        };
    }

}