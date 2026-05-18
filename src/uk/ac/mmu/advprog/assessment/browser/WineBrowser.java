package uk.ac.mmu.advprog.assessment.browser;


import uk.ac.mmu.advprog.assessment.shared.Queries;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class WineBrowser extends JFrame {

    private final Queries queries = new Queries("jdbc:sqlite:data/winetime.db", false);

    private final SearchPanel searchPanel;
    private JTable resultsTable;
    private final WineDetail detailPanel;
    private ArrayList<BrowserWine> searchResultsData;
    private JScrollPane searchResultPanel;
    private QueryBuilder queryBuilder;
    private JPanel buttonContainer;
    public JButton searchButton = new JButton("Search");
    public JButton returnButton = new JButton("Return to Search");

    public WineBrowser() {
        this.searchPanel = new SearchPanel();
        this.detailPanel = new WineDetail();
        this.searchResultsData = new ArrayList<>();
        this.searchResultPanel = new JScrollPane();
        this.resultsTable = new JTable();
        this.buttonContainer = new JPanel();
    }

    public void displayWineBrowser() {
        setTitle("Wine Browser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 920);
        setMinimumSize(new Dimension(800, 920));
        add(searchPanel, "West");
        handleResultsTable();
        add(searchResultPanel, "Center");
        renderButtons();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleResultsTable() {

        resultsTable.setAutoCreateRowSorter(true);

        resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultsTable.setSelectionBackground(new Color(250, 108, 14));

        resultsTable.getSelectionModel().addListSelectionListener(e -> {
            updateFromSelection();
        });

        JTableHeader header = resultsTable.getTableHeader();
        header.setReorderingAllowed(true);
        header.setResizingAllowed(true);

        resultsTable.getSelectedRow();
        remove(this.searchResultPanel);
        this.searchResultPanel = new JScrollPane(resultsTable);
        searchResultPanel.setMaximumSize(new Dimension(100, 100));
        searchResultPanel.setAutoscrolls(true);

        add(searchResultPanel, "Center");
        revalidate();
    }

    private void updateFromSelection() {
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
            remove(buttonContainer);
            remove(this.searchResultPanel);
            remove(this.searchPanel);
            renderReturnToSearch();
            revalidate();
            repaint();
        }
    }

    private JButton getClearButton() {
        JButton clearButton = new JButton("Clear");
        clearButton.setBackground(new Color(250, 108, 14));
        clearButton.setForeground(Color.WHITE);
        clearButton.setBorderPainted(false);
        clearButton.setFocusPainted(false);
        clearButton.setOpaque(true);

        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                searchPanel.resetSearch();
            }
        });

        return clearButton;
    }

    private JButton getSearchButton() {

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

        return searchButton;
    }

    private void renderButtons() {
        buttonContainer.setBackground(new Color(43, 43, 43));
        buttonContainer.add(getSearchButton());
        buttonContainer.add(getClearButton());

        add(buttonContainer, "South");

    }

    private void returnToSearch() {
        detailPanel.clearData();
        remove(detailPanel);
        remove(returnButton);
        add(buttonContainer, "South");
        add(this.searchResultPanel, "Center");
        add(this.searchPanel, "West");

        revalidate();
        repaint();
    }

    private void renderReturnToSearch() {
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

    private void fetchData() {
        this.detailPanel.clearData();
        this.searchResultsData.clear();
        String[] columnNames = {""};

        //make table read only
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] obj = {"Fetching Data..."};

        tableModel.addRow(obj);

        resultsTable.setModel(tableModel);


        if (searchPanel.validateSearch()) {

            this.queryBuilder = searchPanel.getQuery();

            /*When ratings added, query times got wild... After
            * a bit of researching found SwingWorker got when queries
            * are on the large side.
            *
            * https://docs.oracle.com/javase/8/docs/api/javax/swing/SwingWorker.html
            *
            */
            SwingWorker worker = new SwingWorker() {

                @Override
                protected ArrayList<BrowserWine> doInBackground() throws Exception {
                    if (queryBuilder.getGrape().isEmpty()) {
                        return queries.getWinesFromSearch(queryBuilder);
                    } else {
                        return queries.getWinesFromSearchWithGrape(queryBuilder);
                    }
                }

                @Override
                protected void done() {
                    try {
                        ArrayList<BrowserWine> result = (ArrayList<BrowserWine>) get();

                        String[] columnNames = {"Name", "Type", "Winery", "Country", "ABV", "Ratings", "Rating AVG"};

                        //make table read only
                        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
                            @Override
                            public boolean isCellEditable(int row, int column) {
                                return false;
                            }
                        };

                        for (BrowserWine wine : result) {
                            Object[] obj = {wine.name, wine.type, wine.winery, wine.country, wine.abv, wine.ratings, wine.ratingAverage};

                            tableModel.addRow(obj);
                        }

                        searchResultsData = result;
                        resultsTable.setModel(tableModel);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            };
            worker.execute();
        } else {
            JOptionPane.showMessageDialog(null, "At least one of the fields (Name, Winery, Type, Country of Origin, Blend, Grape, Acidity or Body) must be populated.", "WARNING", JOptionPane.WARNING_MESSAGE);
        }

    }

}