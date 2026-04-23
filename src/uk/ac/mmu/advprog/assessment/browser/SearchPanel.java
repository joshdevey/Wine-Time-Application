package uk.ac.mmu.advprog.assessment.browser;

import uk.ac.mmu.advprog.assessment.shared.Queries;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchPanel extends JPanel {
//todo add a limit drop down?
    Queries queries = new Queries("jdbc:sqlite:data/winetime.db", false);
    JTextField name = new JTextField(12);
    public JTextField winery = new JTextField(12);

    ArrayList<String> availableTypes = queries.getTypes();
    String[] types = availableTypes.toArray(new String[availableTypes.size()]);

    ArrayList<String> availableCountries = queries.getCountries();
    String[] countries = availableCountries.toArray(new String[availableCountries.size()]);

    ArrayList<String> availableBlends = queries.getBlends();
    String[] blends = availableBlends.toArray(new String[availableBlends.size()]);

    public JComboBox<String> type = new JComboBox<>(types);

    public JComboBox<String> country = new JComboBox<>(countries);

    public JComboBox<String> blend = new JComboBox<>(blends);

    public JTextField grape = new JTextField(12);

    public JSlider abv = new JSlider(0, 50);

    String[] aciditys = {"Any",
            "High",
            "Medium",
            "Low"
    };

    public JComboBox<String> acidity = new JComboBox<>(aciditys);

    String[] bodys = {"Any",
            "Full-bodied",
            "Light-bodied",
            "Medium-bodied",
            "Very full-bodied",
            "Very light-bodied",
    };

    public JComboBox<String> body = new JComboBox<>(bodys);

    public JButton clearButton = new JButton("Clear");

    public SearchPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(43, 43, 43));
        setPreferredSize(new Dimension(300, 600));
        setMaximumSize(new Dimension(300, 600));
        JPanel titlePanel = new JPanel();
        JLabel searchLabel = new JLabel("Search Wines");
        titlePanel.setBackground(new Color(67, 67, 67));
        searchLabel.setForeground(Color.WHITE);
        titlePanel.add(searchLabel);
        add(titlePanel);

        JPanel namePanel = getWineNamePanel();
        add(namePanel);

        JPanel wineryPanel = getWineryPanel();
        add(wineryPanel);

        JPanel typePanel = getTypePanel();
        add(typePanel);

        JPanel countryPanel = getCountryPanel();
        add(countryPanel);

        JPanel blendPanel = getBlendPanel();
        add(blendPanel);

        JPanel grapePanel = getGrapePanel();
        add(grapePanel);

        JPanel abvPanel = getAbvPanel();
        add(abvPanel);

        JPanel acidityPanel = getAcidityPanel();
        add(acidityPanel);

        JPanel bodyPanel = getBodyPanel();
        add(bodyPanel);

        JPanel buttonContainer = new JPanel(new FlowLayout());

        clearButton.setBackground(new Color(250, 108, 14));
        clearButton.setForeground(Color.WHITE);
        clearButton.setBorderPainted(false);
        clearButton.setFocusPainted(false);
        clearButton.setOpaque(true);

        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                resetSearch();
            }
        });

        buttonContainer.setOpaque(false);
        buttonContainer.add(clearButton);

        add(buttonContainer);
    }

    private JPanel getWineNamePanel() {
        JLabel label = new JLabel("Name ");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.setOpaque(false);
        name.setBackground(new Color(70, 72, 74));
        label.setForeground(ColorUIResource.WHITE);
        name.setForeground(ColorUIResource.WHITE);
        panel.add(label);
        panel.add(name);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        return panel;
    }

    private JPanel getWineryPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Winery Name");
        panel.setOpaque(false);
        winery.setBackground(new Color(70, 72, 74));
        label.setForeground(ColorUIResource.WHITE);
        winery.setForeground(ColorUIResource.WHITE);
        panel.setLayout(new GridLayout(2, 1));
        panel.add(label);
        panel.add(winery);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        return panel;
    }

    private JPanel getTypePanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Type");
        panel.setOpaque(false);
        type.setOpaque(false);
        label.setForeground(ColorUIResource.WHITE);
        panel.setLayout(new GridLayout(2, 1));
        panel.add(label);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(type);

        return panel;
    }

    private JPanel getCountryPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Country of Origin");
        panel.setOpaque(false);
        label.setForeground(ColorUIResource.WHITE);
        panel.setLayout(new GridLayout(2, 1));
        panel.add(label);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(country);

        return panel;
    }

    private JPanel getBlendPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Blend");
        panel.setOpaque(false);
        label.setForeground(ColorUIResource.WHITE);
        panel.setLayout(new GridLayout(2, 1));
        panel.add(label);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(blend);

        return panel;
    }

    private JPanel getGrapePanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Grape");
        panel.setOpaque(false);
        grape.setBackground(new Color(70, 72, 74));
        label.setForeground(ColorUIResource.WHITE);
        grape.setForeground(Color.WHITE);
        panel.setLayout(new GridLayout(2, 1));
        panel.add(label);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(grape);

        return panel;
    }

    private JPanel getAbvPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Min ABV");
        panel.setOpaque(false);
        label.setForeground(ColorUIResource.WHITE);
        panel.setLayout(new GridLayout(2, 1));
        panel.add(label);
        abv.setValue(0);
        abv.setForeground(Color.WHITE);
        abv.setMajorTickSpacing(10);
        abv.setMinorTickSpacing(1);
        abv.setPaintTicks(true);
        abv.setPaintLabels(true);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(abv);

        return panel;
    }

    private JPanel getAcidityPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Acidity");
        panel.setOpaque(false);
        label.setForeground(ColorUIResource.WHITE);
        panel.setLayout(new GridLayout(2, 1));
        panel.add(label);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(acidity);

        return panel;
    }

    private JPanel getBodyPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Body");
        panel.setOpaque(false);
        label.setForeground(ColorUIResource.WHITE);
        panel.setLayout(new GridLayout(2, 1));
        panel.add(label);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(body);

        return panel;
    }

    private void resetSearch() {
        name.setText("");
        winery.setText("");
        type.setSelectedIndex(0);
        country.setSelectedIndex(0);
        blend.setSelectedIndex(0);
        grape.setText("");
        abv.setValue(0);
        acidity.setSelectedIndex(0);
        body.setSelectedIndex(0);
    }

    public QueryBuilder getQuery() {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setName(name.getText());
        queryBuilder.setWineryName(winery.getText());
        queryBuilder.setGrape(grape.getText());
        queryBuilder.setAbv(abv.getValue());
        queryBuilder.setType(type.getSelectedItem().toString());
        queryBuilder.setCountry(country.getSelectedItem().toString());
        queryBuilder.setBlend(blend.getSelectedItem().toString());
        queryBuilder.setAcidity(acidity.getSelectedItem().toString());
        queryBuilder.setBody(body.getSelectedItem().toString());

        return queryBuilder;
    };

}